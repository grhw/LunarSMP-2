package light.breeze.commands;

import light.breeze.ManaSystem;
import light.breeze.lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class ManaCommand implements CommandExecutor {
    public ManaSystem mana;
    public ManaCommand() {
        this.mana = new ManaSystem();
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        commandSender.sendMessage(lang.notify_mana.replace("$1",this.mana.getMana((Player) commandSender) + ""));
        return true;
    }
}