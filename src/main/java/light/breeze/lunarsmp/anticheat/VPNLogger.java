package light.breeze.lunarsmp.anticheat;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import light.breeze.lunarsmp.utils.FileStorage;
import light.breeze.lunarsmp.utils.Logger;
import light.breeze.lunarsmp.utils.Requests;
import light.breeze.lunarsmp.utils.ServerUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class VPNLogger implements Listener {
    private final FileStorage fs;

    public VPNLogger() {
        this.fs = new FileStorage( ServerUtils.getPlugin(), "vpn_log.yml" );
    }

    public boolean check( String ip, String name, int num ) {
        return this.fs.get( ip + ".player" + num ).matches( name ) || this.fs.get( ip + ".player" + num ) == null;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void checkVPN( PlayerJoinEvent event ) {
        String ip = event.getPlayer().getAddress().getAddress().getHostAddress();
        Logger.log( "Checking " + ip );
        if ( this.fs.get( ip ) == null ) {
            Logger.log( "Sending" );
            String data = Requests.get( "http://v2.api.iphub.info/ip/" + ip + "?key=" + this.fs.get( "iphub-api-key" ) );
            JsonObject ipinfo = new JsonParser().parse( data ).getAsJsonObject();
            this.fs.store( ip + ".isvpn", "undetermined" );
            if ( ipinfo.get( "block" ).getAsInt() == 1 ) {
                this.fs.store( ip + ".isvpn", "vpn" );
            } else {
                this.fs.store( ip + ".isvpn", "no" );
            }
            this.fs.store( ip + ".country", ipinfo.get( "countryName" ).getAsString() );
            this.fs.store( ip + ".blocklevel", ipinfo.get( "block" ).getAsString() );
            this.fs.store( ip + ".isp", ipinfo.get( "isp" ).getAsString() );
        }

        boolean multi_account_kick = true;
        if ( this.fs.get( ip + ".maxplayers" ) == null ) {
            this.fs.store( ip + ".maxplayers", "1" );
        }
        int max = Integer.parseInt( this.fs.get( ip + ".maxplayers" ) );
        for ( int i = 1; i <= max; i++ ) {
            if ( check( ip, event.getPlayer().getName(), i ) ) {
                multi_account_kick = false;
                this.fs.store( ip + ".player" + i, event.getPlayer().getName() );
            }
        }
        if ( multi_account_kick ) {
            Logger.log( "There's already a player on this IP!" );
            event.getPlayer().kickPlayer( "Multi-accounting is not allowed." );
            event.getPlayer().getServer().broadcastMessage( "Kicked due to multi-accounting." );
        }

        if ( this.fs.get( ip + ".isvpn" ).matches( "vpn" ) ) {
            Logger.log( "Was using a VPN." );
            event.getPlayer().kickPlayer( "Turn your damn VPN off!" );
            event.getPlayer().getServer().broadcastMessage( "Kicked due to VPN usage." );
            event.getPlayer().getServer().dispatchCommand( event.getPlayer().getServer().getConsoleSender(), "ban-ip " + ip + " Turn your damn VPN off!" );
            event.getPlayer().getServer().banIP( ip );
        }
    }
}