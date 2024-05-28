package light.breeze.lunarsmp.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TPARequest {
    public Player requester;
    public Player target;
    public Boolean teleporting_to;
    public Boolean accepted = false;
    public Boolean expired = false;
    public TPARequest( Player requester, Player target, boolean teleporting_to ) {
        this.requester = requester;
        this.target = target;
        this.teleporting_to = teleporting_to;

        this.requester.sendMessage( "Sent a request to " + this.target.getName() );
        String additional = "You are teleporting to them.";
        if (teleporting_to) {
            additional = "They are teleporting to you.";
        }
        this.target.sendMessage( "You have a request from " + this.requester.getName() + "\n" + additional );
        Bukkit.getScheduler().runTaskLater( ServerUtils.getPlugin(), bukkitTask -> {
            this.accepted = false;
            this.expired = false;
            this.requester.sendMessage( "Your request to " + this.target.getName() + " expired." );
            this.target.sendMessage( "The request from " + this.requester.getName() + " expired." );
        },60 * 60 );
    }

    public void accept() {
        if (!this.expired) { // we really dont need to check since it checks it on teleport() anyway
            this.accepted = true;
            this.teleport();

            this.requester.sendMessage( "Accepted " + this.target.getName() + "'s TPA" );
            this.target.sendMessage( this.requester.getName() + " accepted your TPA" );
        }
    }
    public void decline() {
        if (!this.expired) {
            this.expired = true;

            this.requester.sendMessage( "Declined " + this.target.getName() + "'s TPA" );
            this.target.sendMessage( this.requester.getName() + " declined your TPA" );
        }
    }
    public void teleport() {
        if (this.accepted&&!this.expired) {
            this.requester.sendMessage( "Teleporting..." );
            this.target.sendMessage( "Teleporting..." );
            if (teleporting_to) {
                requester.teleport( target );
            } else {
                target.teleport( requester );
            }
        }
    }
}
