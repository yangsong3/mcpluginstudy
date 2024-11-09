package PlayerJobSystem;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

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

    //잡보너스 적용
    //사냥꾼 데미지 보너스
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            PlayerData data = PlayerData.get(player);
            if (data.getJob() == PlayerJob.HUNTER) {
                int attackBonus = data.getAttackBonus();
                event.setDamage(event.getDamage() + attackBonus);
                player.sendMessage("레벨에 따른 보너스 데미지: " + attackBonus);
            }
        }
    }

    //농부 작물 수확량 보너스
    @EventHandler
    public void onHarvestBonus(PlayerHarvestBlockEvent event) {
        Player player = event.getPlayer();
        PlayerData data = PlayerData.get(player);
        if (data.getJob() == PlayerJob.FARMER) {
            int harvestBonus = data.getHarvestBonus();

            //수확량 증가 로직 구현 ( 작물 드롭 추가 )
            event.getItemsHarvested().forEach(item -> item.setAmount(item.getAmount() + harvestBonus));

            player.sendMessage("보너스에 따른 추가 수확량: " + harvestBonus);
        }
    }

    @EventHandler
    public void onBlockBreakBonus(BlockBreakEvent event) {
        Player player = event.getPlayer();
        PlayerData data = PlayerData.get(player);
        if (data.getJob() == PlayerJob.MINER) {
            Material blockType = event.getBlock().getType();
            if (isOre(blockType)) {
                int amountBonus = data.getMiningAmountBonus();
                double speedBonus = data.getMiningSpeedBonus();

                //아이템 드롭 보너스
                event.setDropItems(false);
                Collection<ItemStack> drops = event.getBlock().getDrops();
                for (ItemStack drop : drops) {
                    drop.setAmount(drop.getAmount() + amountBonus);
                    player.getWorld().dropItemNaturally(event.getBlock().getLocation(), drop);
                }
                player.sendMessage("광부 보너스로 추가 획득량: " + amountBonus);
            }
        }
    }
}

