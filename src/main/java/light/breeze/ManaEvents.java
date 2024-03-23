package light.breeze;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
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
        this.mana.addMana(event.getPlayer(), event.getAmount());
    }
}