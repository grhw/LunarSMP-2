package light.breeze.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.GlowItemFrame;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class BlockUtils {

    public static FileStorage getCustomBlockLocations() {
        return new FileStorage(Utils.getPlugin(),"blocklocations");
    }
    public static void placeBlock(World world,Material baseBlock,Integer customModelData,Location position,String blockId) {
        Entity gif = world.spawn(position,GlowItemFrame.class);
        Utils.setEntityNBT(gif,"{Fixed:1b,Invisible:1b,Item:{id:\"minecraft:zombie_head\",Count:1b,tag:{CustomModelData:7001000}}}");

        world.setBlockData(position,baseBlock.createBlockData());
        Block block = world.getBlockAt(position);

        BlockUtils.getCustomBlockLocations().store(position.toString(),blockId);
    }
}