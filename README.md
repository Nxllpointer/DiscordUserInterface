# DiscordUserInterface

Create application-like messages with buttons and other features for JDA.

[![](https://jitpack.io/v/LiquidDevect/DiscordUserInterface.svg)](https://jitpack.io/#LiquidDevect/DiscordUserInterface)

## How to use

**Step 1:** create instance of the UiController class.

```java
import de.liquid.discordui.UiController;
UiController uiController = new UiController(JDA, TextChannel);
```

**Step 2:** create menu class(es) and implement the required methods and the constructor.

```java
import de.liquid.discordui.UiController;
import de.liquid.discordui.buttons.UiButton;
import de.liquid.discordui.menus.UserInterface;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Collection;

public class ExampleMenu extends UserInterface {

    public ExampleMenu(UiController uiController) {
        super(uiController);
    }

    @Override
    public @NotNull String getTitle() {
        return null;
    }

    @Override
    public @Nullable String getDescription() {
        return null;
    }

    @Override
    public @NotNull Color getColor() {
        return null;
    }

    @Override
    public @Nullable Collection<UiButton> getButtons() {
        return null;
    }

}
```

**Step 3:** Fill in your method return values. Pay attention if the method is nullable or not. You can also implement
other methods of the UserInterface class.

```java
import de.liquid.discordui.UiController;
import de.liquid.discordui.buttons.UiButton;
import de.liquid.discordui.menus.UserInterface;
import net.dv8tion.jda.api.interactions.components.ButtonStyle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExampleMenu extends UserInterface {

    public ExampleMenu(UiController uiController) {
        // Required to allow access to the UiController class
        super(uiController);
    }

    @Override
    public @NotNull String getTitle() {
        return "Cool Menu";
    }

    @Override
    public @Nullable String getDescription() {
        return "I Am A Description";
    }

    @Override
    public @NotNull Color getColor() {
        return Color.ORANGE;
    }

    @Override
    public @Nullable Collection<UiButton> getButtons() {

        List<UiButton> buttonList = new ArrayList<>();

        // Add a button to our list
        buttonList.add(
                new UiButton("Example Button", ButtonStyle.PRIMARY, false, () -> {

                    // This is the executed code when clicked
                    uiController.uiChannel.sendMessage("Hello world! The examplebutton was clicked!").queue();

                })
        );

        return buttonList;
    }

    // Other examples of implemented methods

    @Override
    public @Nullable String getImageUrl() {
        // Set my discord profile picture as image
        return "https://cdn.discordapp.com/avatars/593168169477079041/9a12e88c8af177ac12222ac37166ca27.png";
    }

    @Override
    public @Nullable TemporalAccessor getTimestamp() {

        // Disable the timestamp
        return null;
    }
}
```

**Step 4** The last step we have to do is displaying our UI. You can also use this method in buttons to switch between
menus as example.

```java
uiController.displayUI(new ExampleMenu(uiController));
```

### Result:

<img src="https://i.imgur.com/9HkaaVR.png"/>

#   

#

#

**Todo** | **Status**
---------|-----------
Method to get user input | Not begun