package PlayerJobSystem;

public class PlayerJobBonus {
    public int getAttackBonus() {
        PlayerData data = PlayerData.get(player);
        return job == PlayerJob.HUNTER ? level * 2 : 0;
    }
}
