package com.pudutech.mirsdk.mapify.mapdata;

/* renamed from: com.pudutech.mirsdk.mapify.mapdata.XY */
/* loaded from: classes4.dex */
public class C5206XY {
    private int maxX;
    private int maxY;
    private int minX;
    private int minY;

    public C5206XY(int i, int i2, int i3, int i4) {
        this.minX = i;
        this.maxX = i2;
        this.minY = i3;
        this.maxY = i4;
    }

    public void setMinX(int i) {
        this.minX = i;
    }

    public int getMinX() {
        return this.minX;
    }

    public void setMaxX(int i) {
        this.maxX = i;
    }

    public int getMaxX() {
        return this.maxX;
    }

    public void setMinY(int i) {
        this.minY = i;
    }

    public int getMinY() {
        return this.minY;
    }

    public void setMaxY(int i) {
        this.maxY = i;
    }

    public int getMaxY() {
        return this.maxY;
    }
}
