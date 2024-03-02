package light.breeze.items.echobow;

import com.sun.org.apache.xpath.internal.operations.Bool;
import light.breeze.items.ItemUtils;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ArrowBodyCountChangeEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class EchobowEvents implements Listener {


    @EventHandler(priority= EventPriority.HIGH)
    public void onEntityShootBow(EntityShootBowEvent event) {
        LivingEntity shooter = event.getEntity();
        Boolean ignoreBecauseNotPlayer = false;
        Player player = null;

        Bukkit.getLogger().log(Level.INFO,"yup");

        if (shooter instanceof Player) {
            Bukkit.getLogger().log(Level.INFO,"player");
            player = (Player) shooter;
        } else {
            Bukkit.getLogger().log(Level.INFO,"not player");
            ignoreBecauseNotPlayer = true;
        }
        if (ignoreBecauseNotPlayer||(event.getBow().hasItemMeta() && event.getBow().getItemMeta().hasCustomModelData() && event.getBow().getItemMeta().getCustomModelData() == 9001001)) {
            Bukkit.getLogger().log(Level.INFO,"is echo bow");
            if (!ignoreBecauseNotPlayer) {
                Bukkit.getLogger().log(Level.INFO,"player");
                if (player.getInventory().contains(Material.ENDER_PEARL)) {
                    Bukkit.getLogger().log(Level.INFO,"has pearl");
                    EchoArrowProjTask projTask = new EchoArrowProjTask((Projectile) event.getProjectile());
                    projTask.runTaskTimer(ItemUtils.getPlugin(),0,1);
                }
            }
        }
    }
}