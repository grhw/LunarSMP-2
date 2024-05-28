package light.breeze.lunarsmp.items;

import light.breeze.lunarsmp.utils.Colors;
import light.breeze.lunarsmp.utils.FakeAttributes;
import light.breeze.lunarsmp.utils.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class EnderiteTemplate {
    public static ItemStack create() {
        ArrayList<TextComponent> lore = new ArrayList<>();

        lore.add(
                Component.text()
                        .color( TextColor.color( 141, 71, 255 ) )
                        .decorate( TextDecoration.ITALIC )
                        .append( Component.text( "Enderite. its like netherite except u switched" ) )
                        .build()
        );
        lore.add(
                Component.text()
                        .color( TextColor.color( 159, 160, 255 ) )
                        .decorate( TextDecoration.ITALIC )
                        .append( Component.text( "the first 'n' and 'e' and you turned the 'th' into D" ) )
                        .build()
        );
        lore.add( Component.text( "" ) );
        FakeAttributes.ability( lore, "Used at a Smithing Table", "well your netherite turns to enderite" );
        return ItemBuilder.createItem( true, "enderite_template", Material.DIAMOND, Component.text().color( Colors.DARK_PURPLE ).decoration( TextDecoration.ITALIC, TextDecoration.State.FALSE ).decorate( TextDecoration.BOLD ).append( Component.text( "Enderite Template" ) ).build(), lore );
    }


}
