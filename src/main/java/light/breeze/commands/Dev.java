package light.breeze.commands;

import light.breeze.CustomModelDatas;
import light.breeze.utils.FileStorage;
import light.breeze.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dev implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (args.length > 0) {
            if (args[0].contains("koth")) {
                player.sendMessage("Creating KOTH Container at your location.");
                ArmorStand arm = player.getWorld().spawn(player.getLocation(), ArmorStand.class);
                arm.setSmall(true);
                arm.setInvulnerable(true);
                arm.setInvisible(true);
                arm.setGravity(false);
                arm.setCustomName("Step For Points");
                KothTask armTask = new KothTask(arm);
                armTask.runTaskTimer(Utils.getPlugin(),1,10);
            } else if (args[0].contains("get_cmd")) {
                sender.sendMessage(Integer.toString(CustomModelDatas.getCustomModelData(args[1])));
            }
        } else {
            player.sendMessage("hi :3");
        }
        return true;
    }

    public class KothTask extends BukkitRunnable {
        private final ArmorStand stand;
        private final Map<Player,Integer> scores;

        public KothTask(ArmorStand stand) {
            this.stand = stand;
            this.scores = new HashMap<>();
        }

        @Override
        public void run() {
            if (this.stand.getHealth() < 1||this.stand.isDead()) {
                this.cancel();
                FileStorage fs = new FileStorage(Utils.getPlugin(), "lastResults.yml");
                for (Player player : this.scores.keySet()) {
                    fs.store(player.getName(), this.scores.get(player).toString());
                }
            }
            List<Player> players = Utils.getPlayersInRadius(this.stand.getEyeLocation(),2);
            for (Player player:players) {
                this.scores.put(player,this.scores.getOrDefault(player,1)+1);
            }
            List<Player> disablePlayers = Utils.getPlayersInRadius(this.stand.getEyeLocation(),60);
            for (Player player:disablePlayers) {
                if (player.isGliding()) {
                    player.setVelocity(new Vector(0,-5,0));
                    player.sendMessage("You are not allowed to use elytras in this area!");
                }
            }
        }
    }
}
