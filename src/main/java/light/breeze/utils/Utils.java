package light.breeze.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Utils {

    public static Plugin getPlugin() {
        return Bukkit.getPluginManager().getPlugin("LunarSMP");
    }

    public static void log( String str ) {
        Bukkit.getLogger().log(Level.INFO, str);
    }


    public static Player getPlayer( String player_name ) {
        /*for (Player plr: Bukkit.getServer().getOnlinePlayers()) {
            if (plr.getName().toLowerCase().matches(player_name.toLowerCase())) {
                return plr;
            }
        }
        return null;*/
        return Bukkit.getPlayer(player_name);
    }

    // No NMS for me !
    public static boolean setBlockNBT( Location location, String nbt ) { // no nms for me!
        Server server = Utils.getPlugin().getServer();
        return server.dispatchCommand(server.getConsoleSender(), "data merge block " + location.getX() + " " + location.getY() + " " + location.getZ() + " " + nbt);
    }

    public static boolean setEntityNBT( Entity entity, String nbt ) {
        Server server = Utils.getPlugin().getServer();
        return server.dispatchCommand(server.getConsoleSender(), "data merge entity " + entity.getUniqueId() + " " + nbt);
    }

    public static String Capitalize( String name ) {
        return ( name.charAt(0) + "" ).toUpperCase() + name.substring(1);
    }

    public static void customDurability( ItemStack item ) {
        ItemMeta bim = item.getItemMeta();
        String old = bim.getDisplayName();
        String[] num = old.split("\\[")[1].split("\\]")[0].split("/");
        bim.setDisplayName(old.split("\\[")[0] + "[" + ( Integer.parseInt(num[0]) - 1 ) + "/" + Integer.parseInt(num[1]) + "]");
        item.setItemMeta(bim);
        if (Integer.parseInt(num[0]) < 1) {
            item.setAmount(0);
        }
    }

    public static Location parseRelativeLocation( Location location, String relativeCoords ) {
        String[] coords = relativeCoords.split(" ");
        double z_offset = parseRelativeCoordinate(location, coords[2]);

        // Apply rotation (yaw and pitch) adjustments
        double yaw = Math.toRadians(location.getYaw());
        double pitch = - Math.toRadians(location.getPitch());

        double x_adjusted = z_offset * ( Math.sin(yaw) * - Math.cos(pitch) );
        double y_adjusted = z_offset * ( Math.sin(pitch) );
        double z_adjusted = z_offset * ( Math.cos(yaw) * - Math.cos(pitch) );

        // Create and return the new location
        return location.clone().add(x_adjusted, y_adjusted, - z_adjusted);
    }

    private static double parseRelativeCoordinate( Location location, String coord ) {
        if (coord.equals("^")) {
            return 0;
        } else if (coord.startsWith("^")) {
            return Double.parseDouble(coord.substring(1));
        } else {
            return Double.parseDouble(coord);
        }
    }

    public static List<Player> getPlayersInRadius( Location center, double radius ) {
        List<Player> players_list = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            Location player_location = player.getLocation();
            if (center.getWorld() == player_location.getWorld()) {
                double distance = center.distance(player_location);
                if (distance <= radius) {
                    players_list.add(player);
                }
            }
        }
        return players_list;
    }

    public static List<LivingEntity> getEntitiesInRadius( Location center, double radius ) {
        World world = center.getWorld();
        List<LivingEntity> entities_list = new ArrayList<>();
        for (LivingEntity entity : world.getLivingEntities()) {
            if (entity.getLocation().distance(center) <= radius) {
                entities_list.add(entity);
            }
        }
        return entities_list;
    }

    public static Boolean inList( ArrayList<String> list, String searchString ) {
        for (String cur_val : list) {
            if (cur_val.matches(searchString)) {
                return true;
            }
        }
        return false;
    }

    public static Boolean checkIfMeta( ItemStack item ) {
        return item.hasItemMeta() && item.getItemMeta().hasCustomModelData();
    }

    public static Long getTime() {
        return Instant.now().getEpochSecond();
    }
}