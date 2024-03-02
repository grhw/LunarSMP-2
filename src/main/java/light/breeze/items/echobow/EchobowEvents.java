package light.breeze.items.echobow;

import light.breeze.utils.Utils;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

public class EchobowEvents implements Listener {


    @EventHandler(priority= EventPriority.HIGH)
    public void onEntityShootBow(EntityShootBowEvent event) {
        LivingEntity shooter = event.getEntity();
        Boolean ignoreBecauseNotPlayer = false;
        Player player = null;

        if (shooter instanceof Player) {
            player = (Player) shooter;
        } else {
            ignoreBecauseNotPlayer = true;
        }
        if (ignoreBecauseNotPlayer||(event.getBow().hasItemMeta() && event.getBow().getItemMeta().hasCustomModelData() && event.getBow().getItemMeta().getCustomModelData() == 9001001)) {
            if (!ignoreBecauseNotPlayer) {
                if (player.getInventory().contains(Material.ENDER_PEARL)) {
                    player.getInventory().removeItem(new ItemStack(Material.ENDER_PEARL,1));
                    EchoArrowProjTask projTask = new EchoArrowProjTask((Projectile) event.getProjectile());
                    projTask.runTaskTimer(Utils.getPlugin(),0,1);
                }
            }
        }
    }
}