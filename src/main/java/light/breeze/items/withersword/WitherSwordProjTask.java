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
    private final Projectile egg;

    public WitherSwordProjTask(Projectile egg) {
        this.egg = egg;
    }

    @Override
    public void run() {
        if (egg == null||egg.isDead()) {
            this.cancel();
        } else {
            Vector vel = egg.getVelocity();
            List<LivingEntity> entityList = Utils.getEntitiesInRadius(egg.getLocation(), vel.length());

            for (LivingEntity entity : entityList) {
                if (entity != egg.getShooter()) {
                    entity.getWorld().spawnParticle(Particle.REDSTONE, entity.getLocation().add(0,1,0), 4, new Particle.DustOptions(Color.BLACK,5));
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,60,2));
                }
            }
            egg.getWorld().spawnParticle(Particle.REDSTONE, egg.getLocation(), 1, new Particle.DustOptions(Color.BLACK,5));
        }
    }
}