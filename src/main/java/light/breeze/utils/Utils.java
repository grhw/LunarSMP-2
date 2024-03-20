package light.breeze.utils;

import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;

public class Utils {

    public static Plugin getPlugin() {
        return Bukkit.getPluginManager().getPlugin("LunarSMP");
    }
    public static void log(String str) {
        Bukkit.getLogger().log(Level.INFO,str);
    }


    public static Player getPlayer(String player_name) {
        for (Player plr: Bukkit.getServer().getOnlinePlayers()) {
            if (plr.getName().toLowerCase().matches(player_name.toLowerCase())) {
                return plr;
            }
        }
        return null;
    }

    // No NMS for me !
    public static boolean setBlockNBT(Location location, String nbt) { // no nms for me!
        Server server = Utils.getPlugin().getServer();
        return server.dispatchCommand(server.getConsoleSender(),"data merge block " + location.getX() + " " + location.getY() + " " + location.getZ() + " " + nbt);
    }

    public static boolean setEntityNBT(Entity entity, String nbt) {
        Server server = Utils.getPlugin().getServer();
        return server.dispatchCommand(server.getConsoleSender(),"data merge entity " + entity.getUniqueId() + " " + nbt);
    }

    public static void customDurability(ItemStack item) {
        ItemMeta bim = item.getItemMeta();
        String old = bim.getDisplayName();
        String[] num = old.split("\\[")[1].split("\\]")[0].split("/");
        bim.setDisplayName(old.split("\\[")[0] + "[" + (Integer.parseInt(num[0])-1) + "/" + Integer.parseInt(num[1]) + "]");
        item.setItemMeta(bim);
        if (Integer.parseInt(num[0]) < 1) {
            item.setAmount(0);
        }
    }

    public static Location parseRelativeLocation(Location location, String relativeCoords) {
        String[] coords = relativeCoords.split(" ");
        double zOffset = parseRelativeCoordinate(location, coords[2]);

        // Apply rotation (yaw and pitch) adjustments
        double yaw = Math.toRadians(location.getYaw());
        double pitch = -Math.toRadians(location.getPitch());

        double xAdjusted = zOffset * (Math.sin(yaw) * -Math.cos(pitch));
        double yAdjusted = zOffset * (Math.sin(pitch));
        double zAdjusted = zOffset * (Math.cos(yaw) * -Math.cos(pitch));

        // Create and return the new location
        return location.clone().add(xAdjusted, yAdjusted, -zAdjusted);
    }

    private static double parseRelativeCoordinate(Location location, String coord) {
        if (coord.equals("^")) {
            return 0;
        } else if (coord.startsWith("^")) {
            double relativeOffset = Double.parseDouble(coord.substring(1));
            return relativeOffset;
        } else {
            return Double.parseDouble(coord);
        }
    }
    public static List<Player> getPlayersInRadius(Location center, double radius) {
        List<Player> playersInRadius = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            Location playerLocation = player.getLocation();
            if (center.getWorld() == playerLocation.getWorld()) {
                double distance = center.distance(playerLocation);
                if (distance <= radius) {
                    playersInRadius.add(player);
                }
            }
        }
        return playersInRadius;
    }
    public static List<LivingEntity> getEntitiesInRadius(Location center, double radius) {
        World world = center.getWorld();
        List<LivingEntity> entitiesInRadius = new ArrayList<>();
        for (LivingEntity entity : world.getLivingEntities()) {
            if (entity.getLocation().distance(center) <= radius) {
                entitiesInRadius.add(entity);
            }
        }
        return entitiesInRadius;
    }
    public static Boolean inList(ArrayList<String> list, String searchString) {
        for (String curVal : list){
            if (curVal.matches(searchString)){
               return true;
            }
        }
        return false;
    }
}