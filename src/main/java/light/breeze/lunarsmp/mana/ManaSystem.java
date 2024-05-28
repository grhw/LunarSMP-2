package light.breeze.lunarsmp.mana;

import light.breeze.lunarsmp.Lang;
import light.breeze.lunarsmp.utils.FileStorage;
import light.breeze.lunarsmp.utils.ServerUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

public class ManaSystem {
    private static final FileStorage fs = new FileStorage( ServerUtils.getPlugin(), "mana.yml" );

    public static Integer getMaxMana( Player player ) {
        return player.getLevel() * 105;
    }

    public static Integer getMana( Player player ) {
        if ( fs.get( player.getName() ) == null ) {
            fs.store( player.getName(), "0" );
        }
        return Integer.parseInt( fs.get( player.getName() ) );
    }

    public static boolean checkManaWarn( Player player ) {
        if ( getMana( player ) < 1 ) {
            //player.sendMessage(lang.no_mana);
            player.playSound( player, Sound.ENTITY_ENDERMAN_TELEPORT, SoundCategory.MASTER, 1f, 0.5f );
            player.sendActionBar( Lang.no_mana );
            return false;
        }
        return true;
    }

    public static void setMana( Player player, Integer mana ) {
        if ( fs.get( player.getName() ) == null ) {
            fs.store( player.getName(), "0" );
        }
        fs.store( player.getName(), Math.max( Math.min( mana, getMaxMana( player ) ), - 100 ) + "" );
    }

    public static void addMana( Player player, Integer mana ) {
        Integer newMana = getMana( player ) + mana;
        setMana( player, newMana );
        if ( mana >= 0 ) {
            //player.sendMessage(lang.notify_increase_mana.replace("$1", mana + "").replace("$2", newMana + ""));
            player.sendActionBar( Component.text( "[LunarSMP] " ).color( TextColor.color( 140, 255, 164 ) ).append( Component.text( "+" + mana + " Mana" ) ) );
            player.spawnParticle( Particle.WITCH, player.getLocation().add( 0, 1, 0 ), mana * 2, 0, 0.5, 0, 0.1 );
        } else {
            player.sendActionBar( Component.text( "[LunarSMP] " ).color( TextColor.color( 255, 0, 170 ) ).append( Component.text( mana + " Mana" ) ) );

            //Utils.Actionbar( player, lang.notify_use_mana.replace( "$1", mana + "" ) );
            //player.sendMessage(lang.notify_use_mana.replace("$1", - mana + "").replace("$2", newMana + ""));
        }
    }
}