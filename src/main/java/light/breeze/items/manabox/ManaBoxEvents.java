package light.breeze.items.manabox;

import light.breeze.items.globofmana.GlobOfMana;
import light.breeze.mana.ManaSystem;
import light.breeze.utils.CustomModelDatas;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerFishEvent;

public class ManaBoxEvents implements Listener {
    ManaSystem ms = new ManaSystem();

    @EventHandler( priority = EventPriority.HIGH )
    public void attemptCatchEntity( PlayerFishEvent event ) {
        if ( CustomModelDatas.checkFor( event.getPlayer().getInventory().getItemInMainHand(), "mana_box" ) ) {
            if ( event.getState().name().matches( "CAUGHT_ENTITY" ) ) {
                if ( event.getCaught() instanceof Player ) {
                    event.getPlayer().sendMessage( "you cant catch players" );
                } else {
                    double req = ( (LivingEntity) event.getCaught() ).getHealth();
                    if ( this.ms.checkManaWarn( event.getPlayer() ) ) {
                        this.ms.addMana( event.getPlayer(), (int) -req );
                        long roll = Math.round( Math.random() * ( req ) );
                        if ( roll == 1 ) {
                            event.getPlayer().playSound( event.getCaught().getLocation(), Sound.BLOCK_SHULKER_BOX_OPEN,5,2 );
                            event.getPlayer().spawnParticle( Particle.WHITE_SMOKE, event.getCaught().getLocation(), 1,1,1, 0., 150 );
                            event.getCaught().getWorld().dropItemNaturally( event.getCaught().getLocation(), new FilledManaBox().createFilledManaBox( event.getCaught().createSnapshot(), event.getCaught().getName() )  );
                            event.getCaught().remove();
                        } else {
                            event.getPlayer().playSound( event.getCaught().getLocation(), Sound.ENTITY_CAMEL_DASH,5,1 );
                            event.getPlayer().spawnParticle( Particle.VILLAGER_ANGRY, event.getCaught().getLocation(), 1,1,1, 0.5, 25 );
                            event.getCaught().getWorld().dropItemNaturally( event.getCaught().getLocation(), new GlobOfMana().createGlobOfMana(6) );
                        }
                        event.getPlayer().getInventory().setItemInMainHand( null );
                    }
                }
            }
            if (event.getState().name().matches( "FISHING" ) ) {
                ArmorStand arm = event.getPlayer().getWorld().spawn( event.getPlayer().getLocation(), ArmorStand.class );
                arm.setInvisible( true );
                arm.setSmall( true );
                arm.setHelmet( new ManaBox().createManaBox() );
                arm.setInvulnerable( true );
                event.getHook().addPassenger( arm );
            } else {
                for (Entity entity : event.getHook().getPassengers()) {
                    entity.remove();
                }
            }
        }
    }
}
