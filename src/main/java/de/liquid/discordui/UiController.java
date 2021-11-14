package de.liquid.discordui;

import de.liquid.discordui.buttons.ButtonClickHandler;
import de.liquid.discordui.buttons.UiButton;
import de.liquid.discordui.menus.UserInterface;
import de.liquid.discordui.files.FilenameDataPair;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UiController {

    public JDA jda;
    public TextChannel uiChannel;
    public Guild uiGuild;
    public Message uiMessage = null;
    public UserInterface currentUi = null;
    public ButtonClickHandler buttonClickHandler;

    public UiController(JDA jda, TextChannel uiChannel) {

        this.jda = jda;
        this.uiChannel = uiChannel;
        this.uiGuild = uiChannel.getGuild();
        this.buttonClickHandler = new ButtonClickHandler();

        this.jda.addEventListener(buttonClickHandler);

    }

    public void displayUI(UserInterface ui) {

        MessageBuilder messageBuilder = new MessageBuilder();
        EmbedBuilder embedBuilder = new EmbedBuilder();

        FilenameDataPair image = ui.getImageBytes();
        FilenameDataPair thumbnail = ui.getThumbnailBytes();
        Collection<UiButton> uiButtons = ui.getButtons();
        Collection<MessageEmbed.Field> fields = ui.getFields();

        embedBuilder.setTitle(ui.getTitle());
        embedBuilder.setColor(ui.getColor());
        embedBuilder.setDescription(ui.getDescription());
        embedBuilder.setAuthor(ui.getAuthorName(), ui.getAuthorLink(), ui.getAuthorIconUrl());
        embedBuilder.setThumbnail(thumbnail == null ? ui.getThumbnailUrl() : "attachment://" + thumbnail.filename);
        embedBuilder.setImage(image == null ? ui.getImageUrl() : "attachment://" + image.filename);
        embedBuilder.setFooter(ui.getFooter());
        embedBuilder.setTimestamp(ui.getTimestamp());
        if (fields != null) fields.forEach(embedBuilder::addField);

        messageBuilder.setContent(ui.getUnembeddedMessage());
        messageBuilder.setEmbed(embedBuilder.build());

        if (uiButtons != null && !uiButtons.isEmpty()) {

            List<Button> buttons = new ArrayList<>();
            uiButtons.forEach(uiButton -> {
                buttons.add(uiButton.button);
                buttonClickHandler.registerButton(uiButton);
            });
            messageBuilder.setActionRows(ActionRow.of(buttons));

        }


        if (uiMessage != null && uiChannel.retrieveMessageById(uiMessage.getId()) != null) {
            uiMessage.delete().queue();
        }


        MessageAction messageAction;

        messageAction = uiChannel.sendMessage(messageBuilder.build());

        if (thumbnail != null) {
            messageAction = messageAction.addFile(thumbnail.data, thumbnail.filename);
        }

        if (image != null) {
            messageAction = messageAction.addFile(image.data, image.filename);
        }

        messageAction.queue(message -> {
            uiMessage = message;
            currentUi = ui;
        });

    }

}
