package PlayerJobSystem;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;

public class PlayerJobEvent implements Listener {

    //사냥꾼 경험치 획득
    @EventHandler
    public void onEntityKill(EntityDeathEvent event) {
        Player player = event.getEntity().getKiller();
        if (player != null) {
            PlayerData data = PlayerData.get(player);
            if (data.getJob() == PlayerJob.HUNTER) {
                data.addExperience(10); //사냥시 경험치량
                player.sendMessage("경험치를 획득하였습니다.");
            }
        }
    }

    //농부 경험치 획득
    @EventHandler
    public void onHarvest(PlayerHarvestBlockEvent event) {
        Player player = event.getPlayer();
        PlayerData data = PlayerData.get(player);
        if (data.getJob() == PlayerJob.FARMER) {
            data.addExperience(5); //사냥시 경험치량
            player.sendMessage("경험치를 획득하였습니다.");
        }
    }

    //광부 경험치 획득
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        PlayerData data = PlayerData.get(player);
        if (data.getJob() == PlayerJob.MINER) {
            Material blockType = event.getBlock().getType();
            if (isOre(blockType)) {
                data.addExperience(15);
                player.sendMessage("경험치를 획득하였습니다.");
            }
        }
    }

    //광물인지 확인
    private boolean isOre(Material material) {
        return material == Material.COAL_ORE || material == Material.IRON_ORE || material == Material.DIAMOND_ORE || material == Material.GOLD_ORE;
    }
}

