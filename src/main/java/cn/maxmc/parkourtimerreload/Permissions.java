package cn.maxmc.parkourtimerreload;

public enum Permissions {
    PARKOURADMIN("parkour.admin"),
    PARKOURCANCEL("parkour.cancel")
    ;

    private String permStr;
    Permissions(String permStr) {
        this.permStr = permStr;
    }

    public String perm(){
        return permStr;
    }
}
