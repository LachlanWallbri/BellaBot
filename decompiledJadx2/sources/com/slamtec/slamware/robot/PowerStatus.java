package com.slamtec.slamware.robot;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class PowerStatus {
    private int batteryPercentage;
    private DockingStatus dockingStatus;
    private boolean isCharging;
    private boolean isDCConnected;
    private SleepMode sleepMode;

    public PowerStatus(boolean z, DockingStatus dockingStatus, boolean z2, int i, SleepMode sleepMode) {
        this.isDCConnected = z;
        this.dockingStatus = dockingStatus;
        this.isCharging = z2;
        this.batteryPercentage = i;
        this.sleepMode = sleepMode;
    }

    public boolean isDCConnected() {
        return this.isDCConnected;
    }

    public DockingStatus getDockingStatus() {
        return this.dockingStatus;
    }

    public boolean isCharging() {
        return this.isCharging;
    }

    public int getBatteryPercentage() {
        return this.batteryPercentage;
    }

    public SleepMode getSleepMode() {
        return this.sleepMode;
    }
}
