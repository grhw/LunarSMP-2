package light.breeze.commands;

import light.breeze.items.endingot.EndIngot;
import light.breeze.items.globofmana.GlobOfMana;
import light.breeze.items.small_potion.SmallPotion;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CustomProcessor implements CommandExecutor {
    public Map<Player,Merchant> merchants;
    public CustomProcessor() {
        this.merchants = new HashMap<>();
    }
    public Merchant Brewer(Player player) {
        if (!this.merchants.containsKey(player)) {
            Merchant merchant = Bukkit.createMerchant(player.getDisplayName() + "'s Custom Brewer");
            List<MerchantRecipe> mrl = new ArrayList<>();

            mrl.add(DoubleRecipe(new SmallPotion().createSmallPotion(Material.LAVA_BUCKET), new EndIngot().createEndIngot(),new SmallPotion().createSmallPotionWithFly()));
            mrl.add(DoubleRecipe(new SmallPotion().createSmallPotion(Material.WATER_BUCKET), new GlobOfMana().createGlobOfMana(),new SmallPotion().createSmallPotionWithMana()));
            merchant.setRecipes(mrl);

            this.merchants.put(player,merchant);
        }
        return this.merchants.get(player);
    }
    public MerchantRecipe SingleRecipe(ItemStack Ingredient, ItemStack Result) {
        MerchantRecipe mr = new MerchantRecipe(Result,99999);
        mr.addIngredient(Ingredient);
        return mr;
    }
    public MerchantRecipe DoubleRecipe(ItemStack Ingredient, ItemStack Ingredient1, ItemStack Result) {
        MerchantRecipe mr = new MerchantRecipe(Result,99999);
        mr.addIngredient(Ingredient);
        mr.addIngredient(Ingredient1);
        return mr;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        player.openMerchant(Brewer(player),true);
        return true;
    }
}
