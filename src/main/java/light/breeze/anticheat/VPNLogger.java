package light.breeze.anticheat;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import light.breeze.utils.Http;
import light.breeze.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import light.breeze.utils.FileStorage;

import java.util.logging.Level;

public class VPNLogger implements Listener {
        @EventHandler(priority= EventPriority.HIGH)
        public void checkVPN(PlayerJoinEvent event) {
            FileStorage fs = new FileStorage(Utils.getPlugin(),"lunarlog.yml");
            String ip = event.getPlayer().getAddress().getHostName();
            Utils.log("Checking");
            if (fs.get(ip) == null) {
                Utils.log("Sending");
                String data = Http.get("https://vpnapi.io/api/" + ip);
                //event.getPlayer().sendMessage(data);
                JsonObject ipdata = new JsonParser().parse(data).getAsJsonObject();
                fs.store(ip + ".isvpn","undetermined");
                JsonObject flags = ipdata.get("security").getAsJsonObject();
                JsonObject ipinfo = ipdata.get("location").getAsJsonObject();

                if (flags.get("vpn").getAsBoolean()||flags.get("proxy").getAsBoolean()||flags.get("tor").getAsBoolean()||flags.get("relay").getAsBoolean()) {
                    fs.store(ip + ".isvpn","vpn");
                } else {
                    fs.store(ip + ".isvpn","no");
                }
                fs.store(ip + ".country",ipinfo.get("country").getAsString());
                fs.store(ip + ".continent",ipinfo.get("continent").getAsString());
            }

            if (fs.get(ip + ".isvpn") == "vpn") {
                Utils.log("Was using a VPN.");
                event.getPlayer().kickPlayer("Turn your damn VPN off!");
            }
        }
}
