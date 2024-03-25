package light.breeze.items.featherfalltotem;

import light.breeze.CustomModelDatas;
import light.breeze.utils.Utils;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TotemOfFeatherfallEvents implements Listener {
    @EventHandler(priority= EventPriority.HIGH)
    public void onUse(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        ItemStack hand = player.getInventory().getItemInMainHand();
        if (player.getFallDistance() > 7&&CustomModelDatas.checkFor(hand,("featherfall_totem"))) {
            hand.setAmount(hand.getAmount() - 1);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 360, 1));
            player.playSound(player.getLocation(), Sound.ITEM_TOTEM_USE, SoundCategory.MASTER, 3, 2f);
        }
    }
}