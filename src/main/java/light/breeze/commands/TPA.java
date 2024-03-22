package light.breeze.commands;

import light.breeze.lang;
import light.breeze.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class TPA implements CommandExecutor {
    private Map<String, Map<String,TPARequest>> requests = new HashMap<>();
    private Map<String, String> lastTargetRequests = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!requests.containsKey(sender.getName())) {
            requests.put(sender.getName(),new HashMap<>());
        }
        String target = "";
        if (lastTargetRequests.containsKey(sender.getName())) {
            target = lastTargetRequests.get(sender.getName());
        }
        if (args.length > 0) {
            target = args[0];
        }
        Map<String,TPARequest> userRequests = requests.get(sender.getName());
        Map<String,TPARequest> targRequests = requests.get(target);
        Player player = (Player) sender;
        Player targ = Utils.getPlayer(target);
        if (label.contains("accept")) {
            if (targRequests.containsKey(player.getName())) {
                TPARequest request = targRequests.get(player.getName());
                request.accept();
                userRequests.remove(target);
                return true;
            } else {
                sender.sendMessage(lang.tpa_no_requests);
            };
        } else if (label.contains("decline")) {
            if (targRequests.containsKey(player.getName())) {
                userRequests.get(target).decline();
                targRequests.remove(target);
            } else {
                sender.sendMessage(lang.tpa_no_requests);
            }
        } else if (label.contains("cancel")) {
            if (userRequests.containsKey(target)) {
                userRequests.get(target).cancel_request();
                userRequests.remove(target);
            } else {
                sender.sendMessage(lang.tpa_no_requests);
            }
        } else if (label.contains("here")) {
            TPARequest request = new TPARequest(false,player,targ);
            lastTargetRequests.put(target,player.getName());
            targRequests.put(player.getName(),request);
        } else {
            TPARequest request = new TPARequest(true,player,targ);
            lastTargetRequests.put(target,player.getName());
            targRequests.put(player.getName(),request);
        }
        return true;
    }
    public class TPARequest extends BukkitRunnable {
        public final Boolean isTpaHere;
        public final Player requester;
        public final Player target;
        public Boolean cancelled;

        public TPARequest(Boolean isTpaHere, Player requester, Player target) {
            this.isTpaHere = isTpaHere;
            this.requester = requester;
            this.target = target;
            this.cancelled = false;
            this.runTaskLaterAsynchronously(Utils.getPlugin(),600);

            if (this.isTpaHere) {
                this.requester.sendMessage(lang.tpa_request_here.replace("$1",this.target.getName()));
                this.target.sendMessage(lang.tpa_notif_there.replace("$1",this.requester.getName()));
            } else {
                this.requester.sendMessage(lang.tpa_request_to.replace("$1",this.target.getName()));
                this.target.sendMessage(lang.tpa_notif_to.replace("$1",this.requester.getName()));
            }
        }

        public void cancel_request() {
            this.cancelled = true;
            this.requester.sendMessage(lang.tpa_cancelled.replace("$1",this.target.getName()));
            this.target.sendMessage(lang.tpa_notify_cancelled.replace("$1",this.requester.getName()));
        }

        public void decline() {
            this.cancelled = true;
            this.requester.sendMessage(lang.tpa_decline.replace("$1",this.target.getName()));
            this.target.sendMessage(lang.tpa_notify_decline.replace("$1",this.requester.getName()));
        }

        public void accept() {
            if (!this.cancelled) {
                new TeleportWait(this.requester,this.target,this.isTpaHere).runTaskLater(Utils.getPlugin(),100);
                this.cancelled = true;
                this.requester.sendMessage(lang.tpa_notify_accepted.replace("$1",this.target.getName()));
                this.target.sendMessage(lang.tpa_accepted.replace("$1",this.requester.getName()));
            } else {
                this.requester.sendMessage(lang.tpa_expired);
            }
        }

        @Override
        public void run() {
            if (!this.cancelled) {
                this.requester.sendMessage(lang.tpa_expired);
                this.target.sendMessage(lang.tpa_expired);
            }
            this.cancelled = true;
        }
    }

    public class TeleportWait extends BukkitRunnable {
        private final Player player;
        private final Double health;
        private final Boolean isTpaHere;
        private final Player target;

        public TeleportWait(Player player, Player target, Boolean isTpaHere) {
            this.player = player;
            this.target = target;
            this.isTpaHere = isTpaHere;

            this.health = this.player.getHealth();
        }

        @Override
        public void run() {
            if (this.player.getHealth() >= this.health) {
                if (this.isTpaHere) {
                    this.player.teleport(this.target);

                    this.player.sendMessage(lang.tpa_teleport_1.replace("$1",this.target.getName()));
                    this.target.sendMessage(lang.tpa_teleport_2.replace("$1",this.player.getName()));
                } else {
                    this.target.teleport(this.player);

                    this.player.sendMessage(lang.tpa_teleport_2.replace("$1",this.target.getName()));
                    this.target.sendMessage(lang.tpa_teleport_1.replace("$1",this.player.getName()));
                }
            } else {
                this.player.sendMessage(lang.tpa_cancel_move);
                this.target.sendMessage(lang.tpa_notify_cancel_move.replace("$1",this.player.getName()));
            }
        }
    }
}
