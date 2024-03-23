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
import org.bukkit.event.player.PlayerLoginEvent;

import java.net.InetAddress;
import java.util.logging.Level;

public class VPNLogger implements Listener {
        @EventHandler(priority= EventPriority.HIGH)
        public void checkVPN(PlayerLoginEvent event) {
            FileStorage fs = new FileStorage(Utils.getPlugin(),"lunarlog.yml");
            String ip = event.getPlayer().getAddress().getHostName();
            Utils.log("Checking");
            if (fs.get(ip) == null) {
                Utils.log("Sending");
                String data = Http.get("http://v2.api.iphub.info/ip/" + ip + "?key=" + fs.get("lunarlog-api-key"));
                //event.getPlayer().sendMessage(data);
                JsonObject ipinfo = new JsonParser().parse(data).getAsJsonObject();
                fs.store(ip + ".isvpn","undetermined");

                if (ipinfo.get("block").getAsInt() == 1) {
                    fs.store(ip + ".isvpn","vpn");
                } else {
                    fs.store(ip + ".isvpn","no");
                }
                fs.store(ip + ".country",ipinfo.get("countryName").getAsString());
                fs.store(ip + ".blocklevel","" + ipinfo.get("block").getAsString());
                fs.store(ip + ".isp",ipinfo.get("isp").getAsString());
            }

            if (fs.get(ip + ".isvpn").matches("vpn")) {
                Utils.log("Was using a VPN.");
                event.disallow(PlayerLoginEvent.Result.KICK_BANNED,"Turn your damn VPN off!");
                event.getPlayer().getServer().banIP(event.getPlayer().getAddress().getAddress());
            }
        }
}
