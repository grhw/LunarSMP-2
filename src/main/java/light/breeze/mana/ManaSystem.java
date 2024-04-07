package light.breeze.mana;

import light.breeze.lang;
import light.breeze.utils.FileStorage;
import light.breeze.utils.Utils;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

public class ManaSystem {
    private final FileStorage fs;

    public ManaSystem() {
        this.fs = new FileStorage( Utils.getPlugin(), "mana.yml" );
    }

    public static Integer getMaxMana( Player player ) {
        return player.getLevel() * 105;
    }

    public Integer getMana( Player player ) {
        if ( this.fs.get( player.getName() ) == null ) {
            this.fs.store( player.getName(), "0" );
        }
        return Integer.parseInt( this.fs.get( player.getName() ) );
    }

    public boolean checkManaWarn( Player player ) {
        if ( this.getMana( player ) < 1 ) {
            //player.sendMessage(lang.no_mana);
            player.playSound( player, Sound.ENTITY_ENDERMAN_TELEPORT, SoundCategory.MASTER, 1f, 0.5f );
            Utils.Actionbar( player, lang.no_mana );
            return false;
        }
        return true;
    }

    public void setMana( Player player, Integer mana ) {
        if ( this.fs.get( player.getName() ) == null ) {
            this.fs.store( player.getName(), "0" );
        }
        this.fs.store( player.getName(), Math.max( Math.min( mana, getMaxMana( player ) ), - 100 ) + "" );
    }

    public void addMana( Player player, Integer mana ) {
        Integer newMana = this.getMana( player ) + mana;
        this.setMana( player, newMana );
        if ( mana >= 0 ) {
            //player.sendMessage(lang.notify_increase_mana.replace("$1", mana + "").replace("$2", newMana + ""));
            Utils.Actionbar( player, lang.notify_increase_mana.replace( "$1", mana + "" ) );
            player.spawnParticle( Particle.SPELL_WITCH, player.getLocation().add( 0, 1, 0 ), mana * 2, 0, 0.5, 0, 0.1 );
        } else {
            Utils.Actionbar( player, lang.notify_use_mana.replace( "$1", mana + "" ) );
            //player.sendMessage(lang.notify_use_mana.replace("$1", - mana + "").replace("$2", newMana + ""));
        }
    }
}