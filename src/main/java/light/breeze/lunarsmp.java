package light.breeze;

import light.breeze.anticheat.AntiFly;
import light.breeze.anticheat.ChunkLogger;
import light.breeze.anticheat.StopRedstoneClocks;
import light.breeze.anticheat.VPNLogger;
import light.breeze.commands.*;
import light.breeze.cosmetics.Cosmetics;
import light.breeze.items.endpickaxe.EndPickaxeEvents;
import light.breeze.recipes.CraftingTable;
import light.breeze.items.burningaxe.BurningAxeEvents;
import light.breeze.items.echobow.EchobowEvents;
import light.breeze.items.featherfalltotem.TotemOfFeatherfallEvents;
import light.breeze.items.wardenbound.WardenBoundEvents;
import light.breeze.items.withersword.WitherSwordEvents;
import light.breeze.recipes.Smelting;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class LunarSMP extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Loading LunarSMP");


        ////// Register Commands ////
        this.getCommand("lunarsmp").setExecutor(new Credits());

        this.getCommand("customgive").setExecutor(new CustomGive());
        this.getCommand("customgive").setTabCompleter(new CustomGiveAutoCompleter());

        this.getCommand("echotarget").setExecutor(new EchoTarget());
        this.getCommand("echotarget").setTabCompleter(new EchoTargetAutoCompleter());

        this.getCommand("dev").setExecutor(new Dev());
        this.getCommand("ltpa").setExecutor(new TPA());

        ////// Register Events ////
        Bukkit.getPluginManager().registerEvents(new WardenBoundEvents(), this);
        Bukkit.getPluginManager().registerEvents(new BurningAxeEvents(), this);
        Bukkit.getPluginManager().registerEvents(new TotemOfFeatherfallEvents(), this);
        Bukkit.getPluginManager().registerEvents(new WitherSwordEvents(), this);
        Bukkit.getPluginManager().registerEvents(new EchobowEvents(), this);
        Bukkit.getPluginManager().registerEvents(new EndPickaxeEvents(), this);
        Bukkit.getPluginManager().registerEvents(new StopRedstoneClocks(), this);

        Bukkit.getPluginManager().registerEvents(new VPNLogger(), this);
        //Bukkit.getPluginManager().registerEvents(new AntiFly(), this);
        //Bukkit.getPluginManager().registerEvents(new ChunkLogger(), this);


        Bukkit.getPluginManager().registerEvents(new Cosmetics(), this);

        ////// Ascii Art ////
        getLogger().info("Loaded!" + lang.ascii_art);
        getLogger().info("Plugin by Gust");


    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
