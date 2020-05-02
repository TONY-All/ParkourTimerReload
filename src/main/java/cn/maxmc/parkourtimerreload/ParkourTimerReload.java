package cn.maxmc.parkourtimerreload;

import cn.maxmc.parkourtimerreload.datatypes.YamlParkourData;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ParkourTimerReload extends JavaPlugin {
    private static ParkourTimerReload Instance;
    private static ParkourManager pm;

    public static ParkourTimerReload getInstance() {
        return Instance;
    }

    public static ParkourManager getPm() {
        return pm;
    }

    public ParkourTimerReload() {
        Instance = this;
    }

    /**
     * Plugin start up logic.
     */
    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getLogger().info("正在加载玩家数据");
        pm = new ParkourManager();
        registerTypes();
        pm.init();
        Bukkit.getPluginManager().registerEvents(new ParkourListener(),this);
        Bukkit.getPluginCommand("parkour").setExecutor(new ParkourCommand());
    }

    /**
     * Plugin shut down logic.
     */
    @Override
    public void onDisable() {

    }

    /**
     * Register existed Types
     */
    private void registerTypes(){
        YamlParkourData.register();
    }
}
