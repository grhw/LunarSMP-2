package light.breeze;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class ManaEvents implements Listener {
    public ManaSystem mana;
    public ManaEvents() {
        this.mana = new ManaSystem();
    }

    public boolean canUse(Player player) {
        return this.mana.getMana(player) > 0;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void rechargeXP(PlayerExpChangeEvent event) {
        this.mana.addMana(event.getPlayer(), event.getAmount()*2);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void rechargeXPByEntity(EntityDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            this.mana.addMana(event.getEntity().getKiller(),(int) Math.ceil(event.getEntity().getMaxHealth()/5));
        }
    }
}