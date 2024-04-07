package light.breeze.items.necklace_of_the_gods;

import light.breeze.mana.ManaSystem;
import light.breeze.utils.CustomModelDatas;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class NecklaceOfTheGodsEvents {
    public static class LoopTask extends BukkitRunnable { // Whoops! Lag!
        private ManaSystem mana = new ManaSystem();

        @Override
        public void run() {
            for ( Player player : Bukkit.getServer().getOnlinePlayers() ) {
                ItemStack chestPiece = player.getInventory().getArmorContents()[ 2 ];
                if ( chestPiece != null && CustomModelDatas.checkFor( chestPiece, "necklace_of_the_gods" ) && player.getHealth() < 20 && mana.checkManaWarn( player ) ) {
                    mana.addMana( player, - 20 );
                    player.setHealth( player.getHealth() + 2.5 );
                }
            }
        }
    }
}
