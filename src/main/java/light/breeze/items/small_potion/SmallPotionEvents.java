package light.breeze.items.small_potion;

import light.breeze.utils.CustomModelDatas;
import light.breeze.lang;
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
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmallPotionEvents implements Listener {
    public Map<Player, Long> fly_potions;
    public Map<Player, Long> void_potions;

    public SmallPotionEvents() {
        this.fly_potions = new HashMap<>();
        this.void_potions = new HashMap<>();
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onFill( PlayerBucketFillEvent event ) {
        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
        String name = event.getBlock().getTranslationKey().split("\\.")[2];

        if (CustomModelDatas.checkFor(item, "small_potion")) {
            event.setCancelled(true);
            event.getBlock().setType(Material.AIR);
            String a = ( name + "_" + "bucket" ).toUpperCase();
            Utils.log(a);
            ItemStack smallPotion = event.getPlayer().getInventory().getItemInMainHand();
            event.getPlayer().getInventory().addItem(new SmallPotion().createSmallPotion(Material.matchMaterial(a)));
            smallPotion.setAmount(smallPotion.getAmount()-1);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onEmpty( PlayerBucketEmptyEvent event ) {
        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();

        if (CustomModelDatas.checkFor(item, "small_potion")) {
            event.setCancelled(true);
            event.getBlock().setType(Material.getMaterial(item.getItemMeta().getDisplayName().split("of ")[1].toUpperCase()));
            event.getPlayer().getInventory().setItemInMainHand(new SmallPotion().createSmallPotion(Material.BUCKET));
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void checkPotions( PlayerMoveEvent e ) { // oops! lag!
        List<Player> fly_potion_players = new ArrayList<>(this.fly_potions.keySet());
        List<Player> void_potion_players = new ArrayList<>(this.void_potions.keySet());
        Long epoch = Utils.getTime();
        for (Player fly_potion_player : fly_potion_players) {
            if (this.fly_potions.get(fly_potion_player) < epoch) {
                this.fly_potions.remove(fly_potion_player);
                fly_potion_player.setFlying(false);
                fly_potion_player.setAllowFlight(false);
            } else {
                fly_potion_player.setAllowFlight(true);
                fly_potion_player.setFlying(true);
            }
        }
        for (Player void_potion_player : void_potion_players) {
            if (this.void_potions.get(void_potion_player) < epoch) {
                this.void_potions.remove(void_potion_player);
                void_potion_player.sendMessage(lang.notify_potion_void_timed_out);
            } else if (void_potion_player.getLocation().getY() < -60) {
                void_potion_player.setVelocity(new Vector(0,25,0));
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onDrink( PlayerItemConsumeEvent event ) {
        if (Utils.checkIfMeta(event.getItem())) {
            if (CustomModelDatas.checkFor(event.getItem(), "small_potion_fly")) {
                this.fly_potions.put(event.getPlayer(), Utils.getTime() + 150);
            } else if (CustomModelDatas.checkFor(event.getItem(), "small_potion_void_protection")) {
                Utils.log("asd");
                this.void_potions.put(event.getPlayer(), Utils.getTime() + 600);
            }
            event.setCancelled(true);
            event.getPlayer().getInventory().setItemInMainHand(new SmallPotion().createSmallPotion(Material.BUCKET));
        } else if (event.getItem().getType() == Material.MILK_BUCKET) {
            this.fly_potions.put(event.getPlayer(), (long) 0);
            this.void_potions.put(event.getPlayer(), (long) 0);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void damageDebuff( EntityDamageEvent event ) {
        if (this.fly_potions.containsKey(event.getEntity())) {
            this.fly_potions.put((Player) event.getEntity(), (long) 0);
            event.getEntity().sendMessage(lang.notify_potion_fly_damage);
        }
    }
}