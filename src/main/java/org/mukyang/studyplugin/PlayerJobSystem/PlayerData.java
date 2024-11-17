package org.mukyang.studyplugin.PlayerJobSystem;

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
            level++;
        }
    }

    public void setJob(PlayerJob job) {
        this.job = job;
        this.level = 1;
        this.experience = 0;
    }

    //레벨별 능력치 보너스 부여
    //사냥꾼
    public int getAttackBonus() {
        return job == PlayerJob.HUNTER ? level * 2 : 0;
    }

    //농부
    public int getHarvestBonus() {
        return job == PlayerJob.FARMER ? level : 0;
    }

    //광부
    public double getMiningSpeedBonus() {
        return job == PlayerJob.MINER ? 1.0 + (level * 0.1) : 1.0;
    }

    public int getMiningAmountBonus() {
        return job == PlayerJob.MINER ? level : 0;
    }
}
