package light.breeze.mana;

import light.breeze.items.small_potion.SmallPotion;
import light.breeze.utils.CustomModelDatas;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class ManaEvents implements Listener {
    public ManaSystem mana;

    public ManaEvents() {
        this.mana = new ManaSystem();
    }

    public boolean canUse( Player player ) {
        return this.mana.getMana( player ) > 0;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void rechargeXP( PlayerExpChangeEvent event ) {
        this.mana.addMana( event.getPlayer(), event.getAmount() * 2 );
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void rechargeXPByEntity( EntityDeathEvent event ) {
        if ( event.getEntity().getKiller() != null ) {
            this.mana.addMana( event.getEntity().getKiller(), (int) Math.ceil( event.getEntity().getMaxHealth() / 5 ) );
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void eatManaGlob( PlayerItemConsumeEvent event ) {
        if ( CustomModelDatas.checkFor( event.getItem(), "glob_of_mana" ) ) {
            this.mana.addMana( event.getPlayer(), 10 );
            ItemStack gimh = event.getPlayer().getInventory().getItemInMainHand();
            gimh.setAmount( gimh.getAmount() - 1 );
        } else if ( CustomModelDatas.checkFor( event.getItem(), "small_potion_of_mana" ) ) {
            event.setCancelled( true );
            event.getPlayer().getInventory().setItemInMainHand( new SmallPotion().createSmallPotion( Material.BUCKET ) );
            this.mana.addMana( event.getPlayer(), 20 );
        }
    }
}