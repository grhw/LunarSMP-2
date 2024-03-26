package light.breeze.items.echobow;

import light.breeze.utils.FileStorage;
import light.breeze.utils.Utils;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;

public class EchoArrowProjTask extends BukkitRunnable {
    private final Projectile arrow;
    private final String target;

    public EchoArrowProjTask( Projectile arrow ) {
        FileStorage fs = new FileStorage(Utils.getPlugin(), "echotargets.yml");
        this.arrow = arrow;

        this.target = fs.get(( (Player) arrow.getShooter() ).getUniqueId().toString());
    }

    @Override
    public void run() {
        if (arrow == null || arrow.isDead() || arrow.getTicksLived() > 1800 || arrow.isOnGround()) {
            this.cancel();
        } else {

            List<LivingEntity> entity_list = Utils.getEntitiesInRadius(arrow.getLocation(), 50);
            LivingEntity closest = null;
            Double closestDist = 150.0;

            Player gpl = Utils.getPlayer(this.target);
            for (LivingEntity entity : entity_list) {
                Double dist = entity.getLocation().distance(arrow.getLocation());
                if (entity != arrow.getShooter() && ( ( gpl != null && gpl.getUniqueId() == entity.getUniqueId() ) || this.target.matches(entity.getType().getName()) ) && closestDist > dist && entity instanceof LivingEntity) {
                    closestDist = dist;
                    closest = entity;
                }
            }
            if (closest != null) {
                Vector target_vec = ( arrow.getLocation().subtract(( closest.getEyeLocation().add(closest.getLocation()) ).multiply(0.5)) ).toVector().normalize().multiply(- 1.35);
                arrow.setVelocity(target_vec.add(arrow.getVelocity()).multiply(0.5));
            }
        }
    }
}