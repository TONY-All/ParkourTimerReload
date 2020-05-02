package cn.maxmc.parkourtimerreload;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
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
    private String datatype = ParkourTimerReload.getInstance().getConfig().getString("storge");

    public ParkourManager() {
        dataTypes = new HashMap<>();
        data = new ArrayList<>();
        timer = new HashMap<>();
    }

    public void init(){
        //数据存储类型判断
        String datatype = ParkourTimerReload.getInstance().getConfig().getString("storge");
        if(dataTypes.containsKey(datatype)){
            dataTypes.get(datatype).setup();
        }else {
            Bukkit.getLogger().warning("未设置数据类型或数据类型不存在，服务器即将关闭");
            Bukkit.shutdown();
        }
    }

    public void setupPlayer(Player p){
        if(!timer.containsKey(p)){
            timer.put(p,new Timer());
        }
        timer.get(p).reset();
    }

    public void resetPlayer(Player p){
        timer.get(p).reset();
    }

    public void removePlayer(OfflinePlayer p){
        timer.remove(p);
    }

    public void startTimer(Player p){
        if(!timer.containsKey(p)){
            timer.put(p,new Timer());
        }
        timer.get(p).start();
    }

    public double endTimer(Player p){
        //If player don't have a Timer(no start),return 0
        if(!timer.containsKey(p)){
            return 0;
        }
        return timer.get(p).end();
    }

    public void savePlayer(Player p,String area,double time){
        for (ParkourData datum : data) {
            if(datum.getArea().equals(area)){
                datum.setTime(p.getUniqueId(),time);
            }
        }
    }

    public boolean isAreaExist(String area){
        for (ParkourData datum : data) {
            if(datum.equals(area))
                return true;
        }
        return false;
    }

    public void addDataType(String typeName,ParkourData type){
        dataTypes.put(typeName,type);
    }

    public void addData(ParkourData sdata){
        data.add(sdata);
    }

    public void addArea(String area){
        addData(dataTypes.get(datatype).getNewInstance(area));
    }

    public double getTime(Player p,String area){
        for (ParkourData datum : data) {
            if(datum.getArea().equals(area)) {
                return datum.getTime(p.getUniqueId());
            }
        }
        return 0d;
    }
}
