package light.breeze.items.spearofjustice;

import light.breeze.mana.ManaSystem;
import light.breeze.utils.CustomModelDatas;
import light.breeze.utils.Utils;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class SpearOfJusticeEvents implements Listener {
    private ManaSystem ms = new ManaSystem();

    @EventHandler( priority = EventPriority.HIGH )
    public void onUse( PlayerInteractEvent event ) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if ( CustomModelDatas.checkForWithCooldown( item, "spear_of_justice", player ) && player.getFallDistance() < 2 && this.ms.checkManaWarn( player ) ) {
            player.setVelocity( new Vector( 0, 2, 0 ) );
            player.getWorld().spawnParticle( Particle.CAMPFIRE_COSY_SMOKE, player.getLocation().add( 0, 0, 0 ), 50, 0, 0, 0, 1 );
            this.ms.addMana( player, - 10 );
        }
    }

    @EventHandler( priority = EventPriority.HIGH )
    public void onUse( ProjectileLaunchEvent event ) {
        if ( event.getEntity().getShooter() instanceof Player ) {
            Player player = (Player) event.getEntity().getShooter();
            if ( this.ms.checkManaWarn( player ) && CustomModelDatas.checkForWithCooldown( player.getInventory().getItemInMainHand(), "spear_of_justice", player ) ) {
                player.setCooldown( player.getInventory().getItemInMainHand().getType(), 240 );
                player.addPotionEffect( new PotionEffect( PotionEffectType.LEVITATION, ( 5 * 5 ), 255, false, false, false ) );
                this.ms.addMana( player, - 30 );

                player.getWorld().playSound( player.getLocation(), Sound.ITEM_TRIDENT_THUNDER, SoundCategory.MASTER, 1, 2 );

                for ( int i = 0; i < 5; i++ ) {
                    new BarrageTask( player, this.ms ).runTaskLater( Utils.getPlugin(), i * 5 );
                }
                new FallTask( player, this.ms ).runTaskLater( Utils.getPlugin(), ( 5 * 5 ) + 5 );
                event.setCancelled( true );
            }
        }
    }

    @EventHandler( priority = EventPriority.HIGH )
    public void removeOnHit( ProjectileHitEvent event ) {
        if ( event.getEntity().getType() == EntityType.TRIDENT ) {
            if ( event.getEntity().getCustomName().contains( "Spear of Justice" ) ) {
                event.getEntity().remove();
            }
            if ( event.getEntity().getCustomName().contains( "Thundering" ) ) {
                event.getEntity().getWorld().strikeLightning( event.getEntity().getLocation() );
            }
        }
    }

    public static class BarrageTask extends BukkitRunnable {
        private final Player player;
        private final ManaSystem ms;

        public BarrageTask( Player player, ManaSystem ms ) {
            this.player = player;
            this.ms = ms;
        }

        @Override
        public void run() {
            if ( ms.checkManaWarn( player ) ) {
                ms.addMana( player, - 10 );
                Trident trident = this.player.launchProjectile( Trident.class );
                trident.setVelocity( player.getLocation().getDirection().toBlockVector().multiply( 4 ) );
                trident.setCustomName( "Spear of Justice" );
                player.getWorld().playSound( player.getLocation(), Sound.ITEM_TRIDENT_THROW, SoundCategory.MASTER, 1, 1 );
                player.getWorld().spawnParticle( Particle.SONIC_BOOM, player.getLocation().add( player.getLocation().getDirection().toBlockVector() ), 50, 0, 0, 0, 0 );
                trident.setPickupStatus( AbstractArrow.PickupStatus.DISALLOWED );
            }
        }
    }

    public static class FallTask extends BukkitRunnable {
        private final Player player;
        private final ManaSystem ms;

        public FallTask( Player player, ManaSystem ms ) {
            this.player = player;
            this.ms = ms;
        }

        @Override
        public void run() {
            if ( ms.checkManaWarn( player ) ) {
                ms.addMana( player, - 20 );
                Trident trident = this.player.launchProjectile( Trident.class );
                trident.setVelocity( player.getLocation().getDirection().toBlockVector().multiply( 4 ) );
                trident.setCustomName( "Thundering Spear of Justice" );
                trident.setPickupStatus( AbstractArrow.PickupStatus.DISALLOWED );
                player.getWorld().playSound( player.getLocation(), Sound.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.MASTER, 1, 1 );
            }
            player.addPotionEffect( new PotionEffect( PotionEffectType.LEVITATION, 20, 150, false, false, false ) );
        }
    }
}
