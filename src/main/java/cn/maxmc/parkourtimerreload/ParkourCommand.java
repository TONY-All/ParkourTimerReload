package cn.maxmc.parkourtimerreload;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ParkourCommand implements CommandExecutor {
    private ParkourManager pm = ParkourTimerReload.getPm();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //Only Admin Perms can issue these cmds
        if(sender.hasPermission(Permissions.PARKOURADMIN.perm())) {

            /**
             * @args 0 start
             * @args 1 {@Inject player}
             */
            if (args[0].equals("start")) {
                if (!(args.length == 2)) {
                    sender.sendMessage("参数有误,请检查");
                    return true;
                }
                Player p = Bukkit.getPlayer(args[1]);
                if(p == null){
                    sender.sendMessage("玩家不存在");
                    return true;
                }
                pm.startTimer(p);
                return true;
            }

            /**
             * @args 0 end
             * @args 1 {@Inject player}
             * @args 2 {@Inject area}
             */
            if(args[0].equals("end")){
                if(!(args.length == 3)){
                    sender.sendMessage("参数有误,请检查");
                    return true;
                }
                Player p = Bukkit.getPlayer(args[1]);
                String area = args[2];
                if(p == null){
                    sender.sendMessage("玩家不存在");
                    return true;
                }
                if(!pm.isAreaExist(area)){
                    pm.addArea(area);
                }
                double time = pm.endTimer(p);
                if(pm.getTime(p,area) == 0d){
                    pm.savePlayer(p,area,time);
                }else {
                    if(pm.getTime(p,area) > time){
                        pm.savePlayer(p,area,time);
                    }
                }
                return true;
            }


        }
        if(sender.hasPermission(Permissions.PARKOURCANCEL.perm())){
            if(args[0].equals("cancel")){
                if(!(sender instanceof Player)) {
                    sender.sendMessage("该指令只能由玩家执行");
                    return true;
                }
                sender.sendMessage("");
            }
        }
        return true;
    }
}
