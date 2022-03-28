package com.nms.uoc.contain;

public enum STATUS {
    ACTIVE(0, "active"),
    DE_ACTIVE(1,"de-active"),
    PENDING(2,"pending");

    public final int status;
    public final String display;

    STATUS(int status, String display) {
        this.status = status;
        this.display = display;
    }

    public int getStatus() {
        return status;
    }

    public String getDisplay() {
        return display;
    }
}
