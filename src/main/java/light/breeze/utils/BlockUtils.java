package light.breeze.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.GlowItemFrame;

public class BlockUtils {

    public static FileStorage getCustomBlockLocations() {
        return new FileStorage(Utils.getPlugin(), "blocklocations.yml");
    }

    public static void placeBlock( World world, Material baseBlock, Integer customModelData, Location position, String blockId ) {
        Entity gif = world.spawn(position, GlowItemFrame.class);
        Utils.setEntityNBT(gif, "{Fixed:1b,Invisible:1b,Item:{id:\"minecraft:zombie_head\",Count:1b,tag:{CustomModelData:7001000}}}");

        world.setBlockData(position, baseBlock.createBlockData());
        Block block = world.getBlockAt(position);

        BlockUtils.getCustomBlockLocations().store(position.toString(), blockId);
    }
}