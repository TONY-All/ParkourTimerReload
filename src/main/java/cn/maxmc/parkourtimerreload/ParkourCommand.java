package cn.maxmc.parkourtimerreload;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ParkourCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //Only Admin Perms can issue these cmds
        if(sender.hasPermission(Permissions.PARKOURADMIN.perm())) {

            if (args[0].equals("start")) {
                if (!(args.length == 3)) {
                    sender.sendMessage("参数有误,请检查");
                }

                return true;
            }


        }
        if(sender.hasPermission(Permissions.PARKOURCANCEL.perm())){
            if(args[0].equals("cancel")){

            }
        }
        return true;
    }
}
