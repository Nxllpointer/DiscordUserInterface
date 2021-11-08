package de.liquid.discordui.buttons;

import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.Button;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ButtonClickHandler extends ListenerAdapter {

    private final HashMap<String, UiButton> uiButtonMap = new HashMap<>();

    public void registerButton(UiButton uiButton) {

        uiButtonMap.put(uiButton.id, uiButton);

    }

    @Override
    public void onButtonClick(@NotNull ButtonClickEvent event) {

        Button clickedButton = event.getButton();

        if (uiButtonMap.containsKey(clickedButton.getId())) {

            event.deferEdit().queue();

            UiButton uiButton = uiButtonMap.get(clickedButton.getId());
            uiButton.onClick.run();

        }
    }
}
