package light.breeze.items.withersword;

import light.breeze.CustomModelDatas;
import light.breeze.ManaSystem;
import light.breeze.utils.Utils;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WitherSwordEvents implements Listener {
    private final ManaSystem mana;

    public WitherSwordEvents() {
        this.mana = new ManaSystem();
    }

    @EventHandler(priority= EventPriority.HIGH)
    public void onUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack hand = player.getInventory().getItemInMainHand();
        if (event.getAction() == Action.RIGHT_CLICK_AIR||event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (hand.hasItemMeta()&&hand.getItemMeta().hasCustomModelData()&&hand.getItemMeta().getCustomModelData() == CustomModelDatas.getCustomModelData("witherbane_sword")&&!player.hasCooldown(hand.getType())&&player.getCooldown(hand.getType()) < 1) {
                String name = hand.getItemMeta().getDisplayName();
                player.setCooldown(hand.getType(),60);
                this.mana.addMana(player,-5);
                Integer left = Integer.valueOf(name.split(" \\[")[1].split("\\/")[0]);
                if (left > 0) {
                    left -= 1;
                    ItemMeta m = hand.getItemMeta();
                    m.setDisplayName(name.split(" \\[")[0] + " [" + left + "/50]");
                    hand.setItemMeta(m);


                    player.playSound(player.getLocation(), Sound.ENTITY_WITHER_HURT, SoundCategory.MASTER, 3, 2f);
                    Projectile proj = player.launchProjectile(Snowball.class);
                    proj.setVisualFire(true);
                    proj.setVelocity(player.getLocation().getDirection().multiply(3));
                    WitherSwordProjTask projTask = new WitherSwordProjTask(proj);
                    projTask.runTaskTimer(Utils.getPlugin(),0,1);
                }
            }
        }
    }
}