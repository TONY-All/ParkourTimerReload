package cn.maxmc.parkourtimerreload;

import java.util.Map;
import java.util.UUID;


/**
 * @implNote Must have Constructor with arg "String Area"
 * @author tony_all xiyanxinnian
 */
public interface ParkourData {

    /**
     * @return All Player Data
     */
    Map<UUID, Double> getDataMap();

    /**
     * @param uid Player UUID
     * @return the best time of Player in parkour
     */
    double getTime(UUID uid);

    /**
     * @param uid Player UUID
     * @param time Time
     * Set the parkour time of player
     */
    void setTime(UUID uid,double time);

    /**
     * @return Area Name
     * Must be initialization in Constructor
     */
    String getArea();

    /**
     * @param area Area name
     * @return a instance of this
     * @implNote Must have a private noArg Constructor
     */
    ParkourData getNewInstance(String area);
}
