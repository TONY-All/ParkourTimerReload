package cn.maxmc.parkourtimerreload;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;
import java.util.Map;


/**
 * @author tony_all xiyanxinnian
 * 玩家跑酷管理类
 */
public class ParkourManager {
    private Map<String,ParkourData> dataTypes;
    private Map<Player,Timer> timer;
    private List<ParkourData> data;

    public ParkourManager() {
        //数据存储类型判断
        String datatype = ParkourTimerReload.getInstance().getConfig().getString("storge");
        if(dataTypes.containsKey(datatype)){
            File f = new File(ParkourTimerReload.getInstance().getDataFolder(),"data");
            for (File file : f.listFiles()) {
                if(file.getName().contains("data_")){
                    String area = file.getName().replace("data_","");
                    area = area.replace(".yml","");
                    data.add(dataTypes.get(datatype).getNewInstance(area));
                }
            }
        }else {
            Bukkit.getLogger().warning("未设置数据类型或数据类型不存在，服务器即将关闭");
            Bukkit.shutdown();
        }
    }

    public void setupPlayer(Player p){

    }

    public void startParkour(Player p){
        if(!timer.containsKey(p)){
            timer.put(p,new Timer());
        }
        timer.get(p).start();
    }

    public double endParkour(Player p){
        //If player don't have a Timer(no start),return 0
        if(!timer.containsKey(p)){
            return 0;
        }
        return timer.get(p).end();
    }

    public void addDataType(String typeName,ParkourData type){
        dataTypes.put(typeName,type);
    }
}
