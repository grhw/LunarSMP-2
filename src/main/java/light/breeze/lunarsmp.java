package light.breeze;

import light.breeze.anticheat.AntiArtifactRename;
import light.breeze.anticheat.StopRedstoneClocks;
import light.breeze.anticheat.VPNLogger;
import light.breeze.commands.*;
import light.breeze.cosmetics.Cosmetics;
import light.breeze.items.small_potion.SmallPotionEvents;
import light.breeze.recipes.Drops;
import light.breeze.items.endpickaxe.EndPickaxeEvents;
import light.breeze.recipes.CraftingTable;
import light.breeze.items.burningaxe.BurningAxeEvents;
import light.breeze.items.echobow.EchobowEvents;
import light.breeze.items.featherfalltotem.TotemOfFeatherfallEvents;
import light.breeze.items.wardenbound.WardenBoundEvents;
import light.breeze.items.withersword.WitherSwordEvents;
import light.breeze.recipes.HijackCraftingTable;
import light.breeze.recipes.Smelting;
import light.breeze.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class LunarSMP extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Loading LunarSMP");
        PluginManager pm = Bukkit.getPluginManager();

        ////// Mana ////
        pm.registerEvents(new ManaEvents(), this);

        ////// Register Commands ////
        this.getCommand("lunarsmp").setExecutor(new Credits());

        this.getCommand("customgive").setExecutor(new CustomGive());
        this.getCommand("customgive").setTabCompleter(new CustomGiveAutoCompleter());

        this.getCommand("echotarget").setExecutor(new EchoTarget());
        this.getCommand("echotarget").setTabCompleter(new EchoTargetAutoCompleter());

        this.getCommand("dev").setExecutor(new Dev());

        this.getCommand("openbrewer").setExecutor(new CustomProcessor());

        this.getCommand("tpa").setExecutor(new TPA());

        this.getCommand("mana").setExecutor(new ManaCommand());
        this.getCommand("mana").setTabCompleter(new ManaCommandAutoCompletor());

        ////// Register Events ////

        pm.registerEvents(new WardenBoundEvents(), this);
        pm.registerEvents(new BurningAxeEvents(), this);
        pm.registerEvents(new TotemOfFeatherfallEvents(), this);
        pm.registerEvents(new WitherSwordEvents(), this);
        pm.registerEvents(new EchobowEvents(), this);
        pm.registerEvents(new EndPickaxeEvents(), this);
        pm.registerEvents(new StopRedstoneClocks(), this);
        pm.registerEvents(new SmallPotionEvents(), this);

        pm.registerEvents(new VPNLogger(), this);
        pm.registerEvents(new AntiArtifactRename(), this);
        //pm.registerEvents(new AntiFly(), this);
        //pm.registerEvents(new ChunkLogger(), this);


        pm.registerEvents(new Cosmetics(), this);

        ////// Mob Drops ////
        pm.registerEvents(new Drops(), this);

        ////// Ascii Art ////
        getLogger().info("Loaded!" + lang.ascii_art);
        getLogger().info("Plugin by Gust");
        Plugin plugin = Utils.getPlugin();
        plugin.getServer().broadcastMessage("LunarSMP Loaded!");

        ////// Register Recipes ////
        try {

            CraftingTable recipes = new CraftingTable();
            Smelting smeltrecipes = new Smelting();
            HijackCraftingTable customrecipes = new HijackCraftingTable();

            customrecipes.EndPickaxe();
            pm.registerEvents(customrecipes, plugin);

            recipes.SmallPotion(new NamespacedKey(plugin, "smallpotion"));

            recipes.TOFRecipe(new NamespacedKey(plugin, "featherfall"));
            recipes.TophatRecipe(new NamespacedKey(plugin, "tophat"));

            recipes.Witherbane(new NamespacedKey(plugin, "witherbane"));
            recipes.Echobow(new NamespacedKey(plugin, "echobow"));

            recipes.EchoshardDuplication(new NamespacedKey(plugin, "echosharddupe"));

            smeltrecipes.Axolotl(new NamespacedKey(plugin, "axolotl"));
            smeltrecipes.Bonemeal(new NamespacedKey(plugin, "bonemeal"));
            smeltrecipes.RottenLeather(new NamespacedKey(plugin, "rotten_leather"));
        } catch (Exception e) {
            Utils.log("Either something went TERRIBLY wrong, or plugin was reloaded using PlugMan.");
            this.getLogger().log(Level.WARNING,"",e);
        }
    }


    @Override
    public void onDisable() {

    }
}
