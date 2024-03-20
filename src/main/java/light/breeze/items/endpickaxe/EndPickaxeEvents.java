package light.breeze.items.endpickaxe;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EndPickaxeEvents implements Listener {
    @EventHandler(priority= EventPriority.HIGH)
    public void onUse(BlockBreakEvent event) {
        if (event.getPlayer() != null) {
            Player player = event.getPlayer();
            ItemStack hand = player.getInventory().getItemInMainHand();
            if (hand.hasItemMeta()&&hand.getItemMeta().hasCustomModelData()&&hand.getItemMeta().getCustomModelData() == 9003004) {
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_FALL, SoundCategory.MASTER, 1, 2f);
                Location pos = event.getBlock().getLocation();
                World world = event.getBlock().getWorld();
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        for (int z = 0; z < 3; z++) {
                            world.getBlockAt(pos.add(x, y, z)).breakNaturally(hand);
                        }
                    }
                }
            }
        }
    }
}