package light.breeze.lunarsmp.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.ArrayList;

public class FakeAttributes {
    // When <when>:
    // <then>
    public static void ability( ArrayList<TextComponent> lore, String when, String then ) {
        lore.add( Component.text()
                .decoration( TextDecoration.ITALIC, TextDecoration.State.FALSE )
                .color( Colors.GRAY )
                .append( Component.text( "When " + when + ":" ) )
                .build()
        );
        lore.add( Component.text()
                .decoration( TextDecoration.ITALIC, TextDecoration.State.FALSE )
                .color( Colors.GREEN )
                .append( Component.text( then ) )
                .build()
        );
    }
}
