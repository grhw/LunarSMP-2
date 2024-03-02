package light.breeze.items.echobow;

import light.breeze.items.ItemUtils;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;

public class EchoArrowProjTask extends BukkitRunnable {
    private final Projectile arrow;

    public EchoArrowProjTask(Projectile arrow) {
        this.arrow = arrow;
    }

    @Override
    public void run() {
        if (arrow == null||arrow.isDead()||arrow.getTicksLived() > 1800) {
            this.cancel();
        } else {

            List<LivingEntity> entityList = ItemUtils.getEntitiesInRadius(arrow.getLocation(), 50);
            LivingEntity closest = null;
            Double closestDist = 150.0;

            for (LivingEntity entity : entityList) {
                Double dist = entity.getLocation().distance(arrow.getLocation());
                if (entity != arrow.getShooter()&&closestDist > dist&&entity instanceof LivingEntity) {
                    closestDist = dist;
                    closest = entity;
                }
            }
            if (closest != null) {
                arrow.setVelocity((arrow.getLocation().subtract((closest.getEyeLocation().add(closest.getLocation())).multiply(0.5))).toVector().normalize().multiply(-1.25));
            }
        }
    }
}