package light.breeze;

import light.breeze.utils.FileStorage;
import light.breeze.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class ManaSystem {
    private final FileStorage fs;

    public ManaSystem() {
        this.fs = new FileStorage(Utils.getPlugin(),"mana.yml");
    }

    public Integer getMana(Player player) {
        if (this.fs.get(player.getName()) == null) {
            this.fs.store(player.getName(),"0");
        }
        return Integer.parseInt(this.fs.get(player.getName()));
    }
    public void setMana(Player player,Integer mana) {
        if (this.fs.get(player.getName()) == null) {
            this.fs.store(player.getName(),"0");
        }
        this.fs.store(player.getName(),mana+"");
    }
    public void addMana(Player player,Integer mana) {
        this.setMana(player,this.getMana(player)+mana);
    }
}