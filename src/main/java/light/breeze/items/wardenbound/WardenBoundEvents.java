package light.breeze.items.wardenbound;

import light.breeze.utils.Utils;
import org.bukkit.*;
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


    @EventHandler(priority= EventPriority.HIGH)
    public void onUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack hand = player.getInventory().getItemInMainHand();

        if (event.getAction() == Action.RIGHT_CLICK_AIR||event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (hand.hasItemMeta()&&hand.getItemMeta().hasCustomModelData()&&hand.getItemMeta().getCustomModelData() == 9002002&&!player.hasCooldown(hand.getType())&&player.getCooldown(hand.getType()) < 1) {
                List<LivingEntity> exclude = new ArrayList<>();
                player.playSound(player.getLocation(), Sound.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.MASTER, 3, 0.75f);
                player.playSound(player.getLocation(), Sound.ENTITY_WARDEN_AGITATED, SoundCategory.MASTER, 3, 1f);

                for (int i = 2; i <= 20; i += 2) {
                    Location loc = Utils.parseRelativeLocation(player.getLocation(), "^ ^ ^" + i).add(0, 1, 0);
                    player.getWorld().spawnParticle(Particle.SONIC_BOOM, loc, 1);
                    //Utils.runCommandAt(player,"particle minecraft:sonic_boom ^ ^ ^" + i);
                    List<LivingEntity> entityList = Utils.getEntitiesInRadius(loc, 2);
                    for (LivingEntity entity : entityList) {
                        if (!exclude.contains(entity)) {
                            entity.damage(16 + (Math.random() * 2));
                            exclude.add(entity);
                            player.setCooldown(hand.getType(), 120);
                        }
                    }
                }
            }
        }
    }
}