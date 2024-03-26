package light.breeze.items.withersword;

import light.breeze.utils.Utils;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;

public class WitherSwordProjTask extends BukkitRunnable {
    private final Projectile snowball;

    public WitherSwordProjTask(Projectile snowball) {
        this.snowball = snowball;
    }

    @Override
    public void run() {
        if (snowball == null||snowball.isDead()) {
            this.cancel();
        } else {
            Vector vel = snowball.getVelocity();
            List<LivingEntity> entity_list = Utils.getEntitiesInRadius(snowball.getLocation(), vel.length());

            for (LivingEntity entity : entity_list) {
                if (entity != snowball.getShooter()) {
                    entity.getWorld().spawnParticle(Particle.REDSTONE, entity.getLocation().add(0,1,0), 4, new Particle.DustOptions(Color.BLACK,5));
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,60,2));
                }
            }
            snowball.getWorld().spawnParticle(Particle.REDSTONE, snowball.getLocation(), 1, new Particle.DustOptions(Color.BLACK,5));
        }
    }
}