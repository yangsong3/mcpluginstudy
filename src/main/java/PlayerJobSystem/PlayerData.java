package PlayerJobSystem;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerData {
    private static Map<Player, PlayerData> playerDataMap = new HashMap<>();

    private PlayerJob job;
    private int level;
    private int experience;

    public PlayerData(PlayerJob job) {
        this.job = job;
        this.level = 1;
        this.experience = 0;
    }

    public static PlayerData get(Player player) {
        return playerDataMap.computeIfAbsent(player, p -> new PlayerData(PlayerJob.HUNTER));
    }

    public PlayerJob getJob() {
        return job;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public void addExperience(int amount) {
        experience += amount;
        if (experience >= level * 100) {
            experience = 0;
            level ++;
        }
    }
}
