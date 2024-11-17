package org.mukyang.studyplugin.command;

import org.mukyang.studyplugin.PlayerJobSystem.PlayerData;
import org.mukyang.studyplugin.PlayerJobSystem.PlayerJob;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JobCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("setjob")) {
            return handleSetJobCommand(sender, args);
        } else if (command.getName().equalsIgnoreCase("checkstatus")) {
            return handleCheckStatusCommand(sender, args);
        }
        return false;
    }

    private boolean handleSetJobCommand(CommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.sendMessage("사용법: /setjob <플레이어> <직업>");
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage("플레이어를 찾을 수 없습니다.");
            return true;
        }

        PlayerJob job;
        try {
            job = PlayerJob.valueOf(args[1].toUpperCase());
        } catch (IllegalArgumentException e) {
            sender.sendMessage("직업 이름이 잘못되었습니다.");
            return true;
        }

        PlayerData data = PlayerData.get(target);
        data.setJob(job);
        target.sendMessage("직업이 " + job.name() + "(으)로 설정되었습니다.");
        sender.sendMessage(target.getName() + "의 직업이 " + job.name() + "(으)로 설정되었습니다.");
        return true;
    }

    private boolean handleCheckStatusCommand(CommandSender sender, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("사용법: /chechstatus <플레이어>");
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null){
            sender.sendMessage("플레이어를 찾을 수 없습니다.");
            return true;
        }

        PlayerData data = PlayerData.get(target);
        sender.sendMessage(target.getName() + "의 상태");
        sender.sendMessage("직업: "+data.getJob().name());
        sender.sendMessage("레벨: " + data.getLevel());
        sender.sendMessage("경험치: " + data.getExperience());
        return true;
    }
}