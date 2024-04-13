package light.breeze.items.wardenbound;

import light.breeze.mana.ManaSystem;
import light.breeze.utils.CustomModelDatas;
import light.breeze.utils.Utils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
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

public class WardenBoundEvents implements Listener {
    private final ManaSystem mana;

    public WardenBoundEvents() {
        this.mana = new ManaSystem();
    }

    private static boolean checkIfShielded( LivingEntity entity ) {
        boolean allowHit = false;
        if ( entity instanceof HumanEntity converted_entity ) {
            if ( ! converted_entity.isBlocking() ) {
                allowHit = true;
            }
        } else {
            allowHit = true;
        }
        return allowHit;
    }

    @EventHandler( priority = EventPriority.HIGH )
    public void onUse( PlayerInteractEvent event ) {
        Player player = event.getPlayer();
        ItemStack hand = player.getInventory().getItemInMainHand();

        if ( event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK ) {
            if ( CustomModelDatas.checkForWithCooldown( hand, "wardenbound_sword", player ) && this.mana.checkManaWarn( player ) ) {
                List<LivingEntity> exclude = new ArrayList<>();
                exclude.add( player );
                player.playSound( player.getLocation(), Sound.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.MASTER, 3, 0.75f );
                player.playSound( player.getLocation(), Sound.ENTITY_WARDEN_AGITATED, SoundCategory.MASTER, 3, 1f );
                player.setCooldown( hand.getType(), 1800 );
                this.mana.addMana( player, - 50 );

                for ( int i = 2; i <= 20; i += 2 ) {
                    Location loc = Utils.parseRelativeLocation( player.getLocation(), "^ ^ ^" + i ).add( 0, 1, 0 );
                    player.getWorld().spawnParticle( Particle.SONIC_BOOM, loc, 1 );
                    //Utils.runCommandAt(player,"particle minecraft:sonic_boom ^ ^ ^" + i);
                    List<LivingEntity> entityList = Utils.getEntitiesInRadius( loc, 2 );
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