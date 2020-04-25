package cn.maxmc.parkourtimerreload.datatypes;

import cn.maxmc.parkourtimerreload.ParkourData;
import cn.maxmc.parkourtimerreload.ParkourManager;
import cn.maxmc.parkourtimerreload.ParkourTimerReload;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class YamlParkourData implements ParkourData {
    private String area;
    private YamlConfiguration dataCfg;
    private File fileCfg;

    private YamlParkourData(){
    }

    public static void register(){
        ParkourTimerReload.getPm().addDataType("yml",new YamlParkourData());
    }

    public YamlParkourData(String area) {
        this.area = area;

        fileCfg = new File(ParkourTimerReload.getInstance().getDataFolder()+File.separator+"data","data_"+area+".yml");
        if(!fileCfg.exists()){
            fileCfg.getParentFile().mkdirs();
            try {
                fileCfg.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        dataCfg = YamlConfiguration.loadConfiguration(fileCfg);
    }

    @Override
    public Map<UUID, Double> getDataMap() {
        if(dataCfg.getKeys(true).size() == 0){
            return new HashMap<>();
        }
        Map<UUID, Double> data = new HashMap<>();
        for (String key : dataCfg.getKeys(true)) {
            data.put(UUID.fromString(key),dataCfg.getDouble(key));
        }
        return data;
    }

    @Override
    public double getTime(UUID uid) {
        if(dataCfg.contains(uid.toString())) {
            return dataCfg.getDouble(uid.toString());
        }
        return 0;
    }

    @Override
    public void setTime(UUID uid,double time) {
        dataCfg.set(uid.toString(),time);
        try {
            dataCfg.save(fileCfg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getArea() {
        return area;
    }

    @Override
    public ParkourData getNewInstance(String area) {
        return new YamlParkourData(area);
    }
}
