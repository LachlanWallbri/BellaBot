package com.slamtec.slamware.robot;

import java.util.Iterator;
import java.util.Vector;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class LaserScan {
    private Vector<LaserPoint> laserPoints;
    private Pose pose;

    public LaserScan() {
        this.laserPoints = new Vector<>();
        this.pose = null;
    }

    public LaserScan(Vector<LaserPoint> vector) {
        this.laserPoints = new Vector<>();
        this.pose = null;
        copyPoint(vector);
    }

    public LaserScan(Vector<LaserPoint> vector, Pose pose) {
        this.laserPoints = new Vector<>();
        this.pose = pose;
        copyPoint(vector);
    }

    public LaserScan(LaserScan laserScan) {
        this.laserPoints = new Vector<>();
        this.pose = new Pose(laserScan.pose);
        copyPoint(laserScan.laserPoints);
    }

    private void copyPoint(Vector<LaserPoint> vector) {
        Iterator<LaserPoint> it = vector.iterator();
        while (it.hasNext()) {
            this.laserPoints.add(new LaserPoint(it.next()));
        }
    }

    public Vector<LaserPoint> getLaserPoints() {
        return this.laserPoints;
    }

    public void setLaserPoints(Vector<LaserPoint> vector) {
        this.laserPoints = new Vector<>();
        copyPoint(vector);
    }

    public Pose getPose() {
        return this.pose;
    }

    public void setPose(Pose pose) {
        this.pose = new Pose(pose);
    }
}
