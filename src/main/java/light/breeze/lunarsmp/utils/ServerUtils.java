package light.breeze.lunarsmp.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class ServerUtils {
    public static JavaPlugin getPlugin() {
        return (JavaPlugin) Bukkit.getPluginManager().getPlugin( "LunarRevamped" );
    }

    private static double parseRelativeCoordinate( String coord ) {
        if ( coord.equals( "^" ) ) {
            return 0;
        } else if ( coord.startsWith( "^" ) ) {
            return Double.parseDouble( coord.substring( 1 ) );
        } else {
            return Double.parseDouble( coord );
        }
    }

    public static Location parseRelativeLocation( Location location, String relativeCoords ) {
        String[] coords = relativeCoords.split( " " );
        double z_offset = parseRelativeCoordinate( coords[ 2 ] );

        double yaw = Math.toRadians( location.getYaw() );
        double pitch = - Math.toRadians( location.getPitch() );

        double x_adjusted = z_offset * ( Math.sin( yaw ) * - Math.cos( pitch ) );
        double y_adjusted = z_offset * ( Math.sin( pitch ) );
        double z_adjusted = z_offset * ( Math.cos( yaw ) * - Math.cos( pitch ) );

        return location.clone().add( x_adjusted, y_adjusted, - z_adjusted );
    }


    public static List<Player> getPlayersInRadius( Location center, double radius ) {
        List<Player> players_list = new ArrayList<>();
        for ( LivingEntity e : getEntitiesInRadius( center, radius ) ) {
            if ( e instanceof Player ) {
                players_list.add( (Player) e );
            }
        }
        return players_list;
    }

    public static List<LivingEntity> getEntitiesInRadius( Location center, double radius ) {
        World world = center.getWorld();
        List<LivingEntity> entities_list = new ArrayList<>();
        for ( Entity e : world.getNearbyEntities( center, radius, radius, radius ) ) {
            if ( e instanceof LivingEntity ) {
                entities_list.add( (LivingEntity) e );
            }
        }
        return entities_list;
    }
}
