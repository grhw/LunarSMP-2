package light.breeze.commands;

import light.breeze.cosmetics.hats.DevHat;
import light.breeze.cosmetics.hats.FeatherHat;
import light.breeze.cosmetics.hats.TopHat;
import light.breeze.cosmetics.hats.WitchHat;
import light.breeze.items.burningaxe.BurningAxe;
import light.breeze.items.echobow.EchoBow;
import light.breeze.items.endingot.EndIngot;
import light.breeze.items.endpickaxe.EndPickaxe;
import light.breeze.items.featherfalltotem.TotemOfFeatherFall;
import light.breeze.items.small_potion.SmallPotion;
import light.breeze.items.wardenbound.WardenBound;
import light.breeze.items.withersword.WitherSword;
import light.breeze.utils.Utils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class CustomGive implements CommandExecutor {

    public Map<String, ItemStack> getCustomItems() {
        Map<String, ItemStack> custom_items = new HashMap<>();
        custom_items.put("wardenbound_sword", new WardenBound().createWardenBound());
        custom_items.put("burning_axe", new BurningAxe().createBurningAxe());
        custom_items.put("totem_of_featherfall", new TotemOfFeatherFall().createTOF());
        custom_items.put("end_ingot", new EndIngot().createEndIngot());
        custom_items.put("wither_sword", new WitherSword().createWitherSword());
        custom_items.put("echo_bow", new EchoBow().createEchoBow("50"));

        ItemStack ebna = new EchoBow().createEchoBow("1");
        ebna.addUnsafeEnchantment(Enchantment.SILK_TOUCH,1);
        custom_items.put("eb_no_ammo",ebna);

        custom_items.put("end_pickaxe", new EndPickaxe().createEndPickaxe("1600"));

        ItemStack uep = new EndPickaxe().createEndPickaxe("1");
        uep.addUnsafeEnchantment(Enchantment.PIERCING,1);
        custom_items.put("event_end_pickaxe",uep);

        custom_items.put("small_potion", new SmallPotion().createSmallPotion(Material.BUCKET));
        custom_items.put("small_potion_of_flight", new SmallPotion().createSmallPotionWithFly());
        custom_items.put("small_potion_of_mana", new SmallPotion().createSmallPotionWithMana());

        custom_items.put("devhat", new DevHat().createDevHat());
        custom_items.put("featherhat", new FeatherHat().createFeatherHat());
        custom_items.put("tophat", new TopHat().createTopHat());
        custom_items.put("witchhat", new WitchHat().createWitchHat());
        return custom_items;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Map<String, ItemStack> custom_items = getCustomItems();

        if (custom_items.containsKey(args[0])) {
            Player player = null;
            if (args.length > 1) {
                player = Utils.getPlayer(args[1]);
            } else {
                player = (Player) sender;
            }
            player.getInventory().addItem(custom_items.get(args[0]));
            sender.sendMessage("Gave " + sender.getName() + " 1 ["  + args[0] + "].");
            player.sendMessage("Received [" + args[0] + "] from "  + sender.getName() + ".");
        } else {
            sender.sendMessage("Item " + args[0] + " doesn't exist.");
            return false;
        }
        return true;
    }
}
