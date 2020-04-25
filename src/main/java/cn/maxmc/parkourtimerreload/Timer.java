package cn.maxmc.parkourtimerreload;

public class Timer {
    private long start;
    private long end;

    /**
     * Reset the time
     */
    public void reset(){
        start = 0;
        end = 0;
    }

    /**
     * Set the start time of player
     * call {@link #end()} to get The time
     */
    public void start(){
        start = System.currentTimeMillis();
    }

    /**
     * @return the total time Player used
     * Set the end time of player
     * Must call after {@link #start()}
     */
    public double end(){
        if(start != 0) {
            end = System.currentTimeMillis();
        }else {
            end = 0;
        }
        return ((double)end)/1000;
    }
}
