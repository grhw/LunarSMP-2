package light.breeze.items.featherfalltotem;

import light.breeze.LunarSMP;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionBrewer;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class TotemOfFeatherfallEvents implements Listener {
    @EventHandler(priority= EventPriority.HIGH)
    public void onUse(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        ItemStack hand = player.getInventory().getItemInMainHand();
        if (player.getFallDistance() > 7&&hand.hasItemMeta()&&hand.getItemMeta().hasCustomModelData()&&hand.getItemMeta().getCustomModelData() == 9002001) {
            hand.setAmount(hand.getAmount() - 1);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 360, 1));
            player.playSound(player.getLocation(), Sound.ITEM_TOTEM_USE, SoundCategory.MASTER, 3, 2f);
        }
    }
}