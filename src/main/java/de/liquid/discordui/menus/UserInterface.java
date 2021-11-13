package de.liquid.discordui.menus;


import de.liquid.discordui.UiController;
import de.liquid.discordui.buttons.UiButton;
import de.liquid.discordui.util.FilenameDataPair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.time.Instant;
import java.time.temporal.TemporalAccessor;
import java.util.Collection;

public abstract class UserInterface {

    public UiController uiController;

    public UserInterface(UiController uiController) {
        this.uiController = uiController;
    }

    @NotNull
    public abstract String getTitle();

    @Nullable
    public abstract String getDescription();

    @Nullable
    public abstract Color getColor();

    public abstract @Nullable Collection<UiButton> getButtons();

    @Nullable
    public String getAuthor() {
        return null;
    }

    @Nullable
    public String getThumbnailUrl() {
        return null;
    }

    @Nullable
    public FilenameDataPair getThumbnailBytes() {
        return null;
    }

    @Nullable
    public String getImageUrl() {
        return null;
    }

    @Nullable
    public FilenameDataPair getImageBytes() {
        return null;
    }

    @Nullable
    public String getFooter() {
        return null;
    }

    @Nullable
    public String getUnembeddedMessage() {
        return null;
    }

    @Nullable
    public TemporalAccessor getTimestamp() {
        return Instant.now();
    }

}
