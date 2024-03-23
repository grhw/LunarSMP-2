package light.breeze.items.endpickaxe;

import light.breeze.CustomModelDatas;
import light.breeze.utils.Utils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class EndPickaxeEvents implements Listener {
    @EventHandler(priority= EventPriority.HIGH)
    public void onUse(BlockBreakEvent event) {
        if (event.getPlayer() != null) {
            Player player = event.getPlayer();
            ItemStack hand = player.getInventory().getItemInMainHand();

            if (hand.hasItemMeta()&&hand.getItemMeta().hasCustomModelData()&&hand.getItemMeta().getCustomModelData() == CustomModelDatas.getCustomModelData("end_pickaxe")) {
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_FALL, SoundCategory.MASTER, 1, 2f);
                Location pos = event.getBlock().getLocation();
                World world = event.getBlock().getWorld();
                for (int x = -1; x < 2; x++) {
                    for (int y = -1; y < 2; y++) {
                        for (int z = -1; z < 2; z++) {
                            Block block = world.getBlockAt(pos.clone().add(x, y, z));
                            if (block.getType().getHardness() > 0&&block.getType().getHardness() < Material.REINFORCED_DEEPSLATE.getHardness()) {
                                block.breakNaturally(hand);
                                block.getWorld().spawnParticle(Particle.SQUID_INK,block.getLocation(),0,0,0,0,0);
                            }
                        }
                    }
                }
                if (!hand.containsEnchantment(Enchantment.PIERCING)) {
                    Utils.customDurability(hand);
                }
            }
        }
    }
}