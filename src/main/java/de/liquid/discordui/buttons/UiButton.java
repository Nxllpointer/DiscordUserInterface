package de.liquid.discordui.buttons;

import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.ButtonStyle;
import net.dv8tion.jda.internal.interactions.ButtonImpl;

import java.util.UUID;

public class UiButton {

    public String id;
    public Button button;
    public Runnable onClick;

    public UiButton(String label, ButtonStyle style, boolean disabled, Runnable onClick) {

        this.onClick = onClick;
        this.id = UUID.randomUUID().toString();
        this.button = new ButtonImpl(this.id, label, style, disabled, null);

    }

    public UiButton(Emoji emoji, ButtonStyle style, boolean disabled, Runnable onClick) {

        this.onClick = onClick;
        this.id = UUID.randomUUID().toString();
        this.button = new ButtonImpl(this.id, null, style, disabled, emoji);

    }

    public UiButton(String label, String link, boolean disabled, Runnable onClick) {

        this.onClick = onClick;
        this.id = UUID.randomUUID().toString();
        this.button = new ButtonImpl(this.id, label, ButtonStyle.LINK, link, disabled, null);

    }

}
