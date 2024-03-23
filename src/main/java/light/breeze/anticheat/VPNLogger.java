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
        private FileStorage fs;
        public VPNLogger() {
            this.fs = new FileStorage(Utils.getPlugin(),"lunarlog.yml");
        }
        @EventHandler(priority= EventPriority.HIGH)
        public void checkVPN(PlayerJoinEvent event) {
            String ip = event.getPlayer().getAddress().getAddress().getHostAddress();
            Utils.log("Checking " + ip);
            if (this.fs.get(ip) == null) {
                Utils.log("Sending");
                String data = Http.get("http://v2.api.iphub.info/ip/" + ip + "?key=" + this.fs.get("lunarlog-api-key"));
                //event.getPlayer().sendMessage(data);
                JsonObject ipinfo = new JsonParser().parse(data).getAsJsonObject();
                this.fs.store(ip + ".isvpn","undetermined");

                if (ipinfo.get("block").getAsInt() == 1) {
                    this.fs.store(ip + ".isvpn","vpn");
                } else {
                    this.fs.store(ip + ".isvpn","no");
                }
                this.fs.store(ip + ".country",ipinfo.get("countryName").getAsString());
                this.fs.store(ip + ".blocklevel","" + ipinfo.get("block").getAsString());
                this.fs.store(ip + ".isp",ipinfo.get("isp").getAsString());
            }

            if (this.fs.get(ip + ".isvpn").matches("vpn")) {
                Utils.log("Was using a VPN.");
                event.getPlayer().kickPlayer("Turn your damn VPN off!");
                event.getPlayer().getServer().banIP(ip);
            }
        }
}
