package light.breeze;

import light.breeze.anticheat.AntiArtifactRename;
import light.breeze.anticheat.StopRedStoneClocks;
import light.breeze.anticheat.VPNLogger;
import light.breeze.commands.*;
import light.breeze.cosmetics.Cosmetics;
import light.breeze.items.burningaxe.BurningAxeEvents;
import light.breeze.items.echobow.EchobowEvents;
import light.breeze.items.endpickaxe.EndPickaxeEvents;
import light.breeze.items.endsword.EndSwordEvents;
import light.breeze.items.featherfalltotem.TotemOfFeatherfallEvents;
import light.breeze.items.manabox.ManaBoxEvents;
import light.breeze.items.necklace_of_the_gods.NecklaceOfTheGodsEvents;
import light.breeze.items.small_potion.SmallPotionEvents;
import light.breeze.items.spearofjustice.SpearOfJusticeEvents;
import light.breeze.items.wardenbound.WardenBoundEvents;
import light.breeze.items.withersword.WitherSwordEvents;
import light.breeze.mana.ManaEvents;
import light.breeze.recipes.CraftingTable;
import light.breeze.recipes.Drops;
import light.breeze.recipes.HijackCraftingTable;
import light.breeze.recipes.Smelting;
import light.breeze.utils.Utils;
import light.breeze.website.webbukit.WebBukkit;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class LunarSMP extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info( "Loading LunarSMP" );
        PluginManager pm = Bukkit.getPluginManager();

        ////// Mana ////
        pm.registerEvents( new ManaEvents(), this );

        ////// Register Commands ////
        this.getCommand( "lunar_smp" ).setExecutor( new Credits() );

        this.getCommand( "custom_give" ).setExecutor( new CustomGive() );
        this.getCommand( "custom_give" ).setTabCompleter( new CustomGiveAutoCompleter() );

        this.getCommand( "echo_target" ).setExecutor( new EchoTarget() );
        this.getCommand( "echo_target" ).setTabCompleter( new EchoTargetAutoCompleter() );

        this.getCommand( "dev" ).setExecutor( new Dev() );

        this.getCommand( "open_brewer" ).setExecutor( new CustomProcessor() );

        this.getCommand( "tpa" ).setExecutor( new TPA() );
        this.getCommand( "tpa" ).setTabCompleter( new TPAAutoCompleter() );


        this.getCommand( "mana" ).setExecutor( new ManaCommand() );
        this.getCommand( "mana" ).setTabCompleter( new ManaCommandAutoCompletor() );

        ////// Register Events ////

        pm.registerEvents( new WardenBoundEvents(), this );
        pm.registerEvents( new BurningAxeEvents(), this );
        pm.registerEvents( new EndSwordEvents(), this );
        pm.registerEvents( new TestDummyEvents(), this );
        pm.registerEvents( new TotemOfFeatherfallEvents(), this );
        pm.registerEvents( new WitherSwordEvents(), this );
        pm.registerEvents( new EchobowEvents(), this );
        pm.registerEvents( new EndPickaxeEvents(), this );
        pm.registerEvents( new SpearOfJusticeEvents(), this );
        pm.registerEvents( new SmallPotionEvents(), this );
        pm.registerEvents( new ManaBoxEvents(), this );

        pm.registerEvents( new StopRedStoneClocks(), this );
        pm.registerEvents( new VPNLogger(), this );
        pm.registerEvents( new AntiArtifactRename(), this );
        pm.registerEvents( new CustomMessages(), this );

        ////// Ticking Tasks //
        new NecklaceOfTheGodsEvents.LoopTask().runTaskTimer( this, 20, 20 * 10 ); // 20t = 1s
        new CustomInfoBar.Updater().runTaskTimer( this, 20, 20 );

        pm.registerEvents( new Cosmetics(), this );

        ////// Mob Drops ////
        pm.registerEvents( new Drops(), this );

        ////// Ascii Art ////
        getLogger().info( "Loaded!" + lang.ascii_art );
        getLogger().info( "Plugin by Gust" );
        Plugin plugin = Utils.getPlugin();
        plugin.getServer().broadcastMessage( "LunarSMP Loaded!" );

        ////// Register Recipes ////
        try {

            CraftingTable recipes = new CraftingTable();
            Smelting smelt_recipes = new Smelting();
            HijackCraftingTable custom_recipes = new HijackCraftingTable();

            custom_recipes.EndPickaxe();
            pm.registerEvents( custom_recipes, plugin );

            recipes.SmallPotion( new NamespacedKey( plugin, "small_potion" ) );

            recipes.TOFRecipe( new NamespacedKey( plugin, "feather_fall" ) );
            recipes.TophatRecipe( new NamespacedKey( plugin, "top_hat" ) );

            recipes.Witherbane( new NamespacedKey( plugin, "wither_bane" ) );
            recipes.Echobow( new NamespacedKey( plugin, "echo_bow" ) );

            recipes.EchoshardDuplication( new NamespacedKey( plugin, "echo_shard_dupe" ) );

            smelt_recipes.Axolotl( new NamespacedKey( plugin, "axolotl" ) );
            smelt_recipes.Bonemeal( new NamespacedKey( plugin, "bone_meal" ) );
            smelt_recipes.RottenLeather( new NamespacedKey( plugin, "rotten_leather" ) );
        } catch ( Exception e ) {
            Utils.log( "Either something went TERRIBLY wrong, or plugin was reloaded using PlugMan." );
            this.getLogger().log( Level.WARNING, "", e );
        }

        try {
            WebBukkit.instance.start( 5028 );
        } catch ( Exception e ) {
            Utils.log( "We couldn't start the website!" );
            //this.getLogger().log(Level.WARNING, "", e); // WILL NOT SHUT THE FUCK UP IN SERVER LOGS
        }
    }


    @Override
    public void onDisable() {

    }
}
