package light.breeze.lunarsmp.items;

import light.breeze.lunarsmp.mana.ManaSystem;
import light.breeze.lunarsmp.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class WardenBound implements Listener {
    public static ItemStack create() {
        ArrayList<TextComponent> lore = new ArrayList<>();

        lore.add(
                Component.text()
                        .color( TextColor.color( 217, 183, 255 ) )
                        .decorate( TextDecoration.ITALIC )
                        .append( Component.text( "In the age of legends, a brave warrior killed a mighty warden." ) )
                        .build()
        );
        lore.add(
                Component.text()
                        .color( TextColor.color( 174, 121, 255 ) )
                        .decorate( TextDecoration.ITALIC )
                        .append( Component.text( "This is the spirit of that warden, infused into this very sword." ) )
                        .build()
        );
        lore.add( Component.text( "" ) );
        FakeAttributes.ability( lore, "Used", "Shoots Warden Ray" );
        return ItemBuilder.createItem( true, "warden_bound_sword", Material.DIAMOND_SWORD, Component.text().color( Colors.DARK_PURPLE ).decoration( TextDecoration.ITALIC, TextDecoration.State.FALSE ).decorate( TextDecoration.BOLD ).append( Component.text( "WardenBound Sword" ) ).build(), lore );
    }

    private static boolean checkIfShielded( LivingEntity entity ) {
        boolean allowHit = false;
        if ( entity instanceof HumanEntity ) {
            HumanEntity converted_entity = (HumanEntity) entity;
            if ( ! converted_entity.isBlocking() ) {
                allowHit = true;
            }
        } else {
            allowHit = true;
        }
        return allowHit;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onUse( PlayerInteractEvent event ) {
        Player player = event.getPlayer();
        ItemStack hand = player.getInventory().getItemInMainHand();

        if ( event.getAction() == Action.RIGHT_CLICK_AIR ) {
            if ( CmdIdSystem.compareId( hand, "warden_bound_sword" ) && player.getCooldown( hand.getType() ) < 1 && ManaSystem.checkManaWarn( player ) ) {
                List<LivingEntity> exclude = new ArrayList<>();
                exclude.add( player );
                player.getWorld().playSound( player.getLocation(), Sound.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.MASTER, 3, 0.75f );
                player.getWorld().playSound( player.getLocation(), Sound.ENTITY_WARDEN_AGITATED, SoundCategory.MASTER, 3, 1f );
                player.setCooldown( hand.getType(), 1800 );
                ManaSystem.addMana( player, - 50 );

                for ( int i = 2; i <= 20; i += 2 ) {
                    Location loc = ServerUtils.parseRelativeLocation( player.getLocation(), "^ ^ ^" + i ).add( 0, 1, 0 );
                    player.getWorld().spawnParticle( Particle.SONIC_BOOM, loc, 1 );
                    List<LivingEntity> entityList = ServerUtils.getEntitiesInRadius( loc, 2 );
                    for ( LivingEntity entity : entityList ) {
                        if ( ! exclude.contains( entity ) ) {
                            boolean allowHit = checkIfShielded( entity );
                            if ( allowHit ) {
                                entity.damage( 16 + ( Math.random() * 2 ) );
                                exclude.add( entity );
                            }
                        }
                    }
                }
            }
        }
    }
}
