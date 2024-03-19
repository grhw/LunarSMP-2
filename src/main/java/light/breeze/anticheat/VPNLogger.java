package light.breeze.anticheat;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import light.breeze.utils.Http;
import light.breeze.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import light.breeze.utils.FileStorage;

public class VPNLogger implements Listener {
        @EventHandler(priority= EventPriority.HIGH)
        public void onUse(PlayerJoinEvent event) {
            FileStorage fs = new FileStorage(Utils.getPlugin(),"lunarlog.yml");
            String ip = event.getPlayer().getAddress().getHostName();
            if (fs.get(ip) == null) {
                String data = Http.get("https://vpnapi.io/api/" + ip);
                JsonObject ipdata = JsonParser.parseString(data).getAsJsonObject();
                JsonObject flags = ipdata.get("security").getAsJsonObject();

                if (flags.get("vpn").getAsBoolean()||flags.get("proxy").getAsBoolean()||flags.get("tor").getAsBoolean()||flags.get("relay").getAsBoolean()) {
                    fs.store(ip,"vpn");
                } else {
                    fs.store(ip,"no");
                }
            }

            if (fs.get(ip) == "vpn") {
                event.getPlayer().kickPlayer("Turn your damn VPN off!");
            }
        }
}
