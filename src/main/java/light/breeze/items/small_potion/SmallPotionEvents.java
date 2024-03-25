package light.breeze.items.small_potion;

import light.breeze.CustomModelDatas;
import light.breeze.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmallPotionEvents implements Listener {
    public Map<Player,Long> flyPotions;
    public SmallPotionEvents() {
        this.flyPotions = new HashMap<>();
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onFill(PlayerBucketFillEvent event) {
        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
        String name = event.getBlock().getTranslationKey().split("\\.")[2];

        if (Utils.checkIfMeta(item)&&item.getItemMeta().getCustomModelData() == CustomModelDatas.getCustomModelData("small_potion")) {
            event.setCancelled(true);
            event.getBlock().setType(Material.AIR);
            String a = (name + "_" + "bucket").toUpperCase();
            Utils.log(a);
            event.getPlayer().getInventory().setItemInMainHand(new SmallPotion().createSmallPotion(Material.matchMaterial(a)));
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onEmpty(PlayerBucketEmptyEvent event) {
        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();

        if (Utils.checkIfMeta(item)&&item.getItemMeta().getCustomModelData() == CustomModelDatas.getCustomModelData("small_potion")) {
            event.setCancelled(true);
            event.getBlock().setType(Material.getMaterial(item.getItemMeta().getDisplayName().split("of ")[1].toUpperCase()));
            event.getPlayer().getInventory().setItemInMainHand(new SmallPotion().createSmallPotion(Material.BUCKET));
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void checkPotions(PlayerMoveEvent e) { // oops! lag!
        List<Player> ar = new ArrayList<>(this.flyPotions.keySet());
        Long epoch = Utils.getTime();
        for (int i = 0; i < ar.size(); i += 1) {
            if (this.flyPotions.get(ar.get(i)) < epoch) {
                this.flyPotions.remove(ar.get(i));
                ar.get(i).setFlying(false);
                ar.get(i).setAllowFlight(false);
            } else {
                ar.get(i).setAllowFlight(true);
                ar.get(i).setFlying(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onDrink(PlayerItemConsumeEvent event) {
        if (Utils.checkIfMeta(event.getItem())) {
            Integer cmd = event.getItem().getItemMeta().getCustomModelData();
            if (cmd == CustomModelDatas.getCustomModelData("small_potion_fly")) {
                event.setCancelled(true);
                event.getPlayer().getInventory().setItemInMainHand(new SmallPotion().createSmallPotion(Material.BUCKET));
                this.flyPotions.put(event.getPlayer(), Utils.getTime()+150);
            }
        } else if (event.getItem().getType() == Material.MILK_BUCKET) {
            this.flyPotions.put(event.getPlayer(),(long) 0);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void damageDebuff(EntityDamageEvent event) {
        if (this.flyPotions.containsKey(event.getEntity())) {
            this.flyPotions.put((Player) event.getEntity(),(long) 0);
        }
    }
}