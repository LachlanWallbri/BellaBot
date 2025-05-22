package com.pudutech.lidar;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public class LidarNode {
    public int angle;
    public double angleInRad;
    public int dist;
    public double distance_m;
    public double ptX;
    public double ptY;
    public int quality;

    public void set(int i, int i2, int i3, double d, double d2) {
        this.quality = i;
        this.angle = i2;
        this.dist = i3;
        this.ptX = d;
        this.ptY = d2;
    }

    public void set(LidarNode lidarNode) {
        this.quality = lidarNode.quality;
        this.angle = lidarNode.angle;
        this.dist = lidarNode.dist;
        this.ptX = lidarNode.ptX;
        this.ptY = lidarNode.ptY;
    }
}
