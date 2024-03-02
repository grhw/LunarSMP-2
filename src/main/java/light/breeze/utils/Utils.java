package light.breeze.utils;

import org.bukkit.*;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Utils {

    public static Plugin getPlugin() {
        return Bukkit.getPluginManager().getPlugin("LunarSMP");
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

    public static Player getPlayer(String player_name) {
        for (Player plr: Bukkit.getServer().getOnlinePlayers()) {
            if (plr.getName().toLowerCase().matches(player_name.toLowerCase())) {
                return plr;
            }
        }
        return null;
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
            double distance = center.distance(playerLocation);
            if (distance <= radius) {
                playersInRadius.add(player);
            }
        }
        return playersInRadius;
    }
}