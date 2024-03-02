package light.breeze.commands;

import light.breeze.cosmetics.hats.DevHat;
import light.breeze.items.burningaxe.BurningAxe;
import light.breeze.items.featherfalltotem.TotemOfFeatherfall;
import light.breeze.items.wardenbound.WardenBound;
import light.breeze.items.withersword.WitherSword;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class CustomGive implements CommandExecutor {

    public Map<String, ItemStack> getCustomItems() {
        Map<String, ItemStack> customItems = new HashMap<>();
        customItems.put("wardenbound_sword", new WardenBound().createWardenBound());
        customItems.put("burning_axe", new BurningAxe().createBurningAxe());
        customItems.put("totem_of_featherfall", new TotemOfFeatherfall().createTOF());
        customItems.put("devhat", new DevHat().createDevHat());
        customItems.put("wither_sword", new WitherSword().createWitherSword());
        return customItems;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Map<String, ItemStack> customItems = getCustomItems();

        if (customItems.containsKey(args[0])) {
            Player player = (Player) sender; // copied and pasted directly from the fucking wiki
            player.getInventory().addItem(customItems.get(args[0]));
            sender.sendMessage("Gave " + sender.getName() + " 1 ["  + args[0] + "].");
        } else {
            sender.sendMessage("Item " + args[0] + " doesn't exist.");
        }
        return true;
    }
}
