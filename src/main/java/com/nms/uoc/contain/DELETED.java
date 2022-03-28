package com.nms.uoc.contain;

public enum DELETED{
    FALSE(0),
    TRUE (1);

    public final int value;
    DELETED(int value){
        this.value= value;
    }

    public int getValue() {
        return value;
    }
}
