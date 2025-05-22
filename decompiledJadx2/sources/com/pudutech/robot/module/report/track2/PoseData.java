package com.pudutech.robot.module.report.track2;

import kotlin.Metadata;

/* compiled from: MileageHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/PoseData;", "", "x", "", "y", "yaw", "(DDD)V", "getX", "()D", "setX", "(D)V", "getY", "setY", "getYaw", "setYaw", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class PoseData {
    private double x;
    private double y;
    private double yaw;

    public static /* synthetic */ PoseData copy$default(PoseData poseData, double d, double d2, double d3, int i, Object obj) {
        if ((i & 1) != 0) {
            d = poseData.x;
        }
        double d4 = d;
        if ((i & 2) != 0) {
            d2 = poseData.y;
        }
        double d5 = d2;
        if ((i & 4) != 0) {
            d3 = poseData.yaw;
        }
        return poseData.copy(d4, d5, d3);
    }

    /* renamed from: component1, reason: from getter */
    public final double getX() {
        return this.x;
    }

    /* renamed from: component2, reason: from getter */
    public final double getY() {
        return this.y;
    }

    /* renamed from: component3, reason: from getter */
    public final double getYaw() {
        return this.yaw;
    }

    public final PoseData copy(double x, double y, double yaw) {
        return new PoseData(x, y, yaw);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PoseData)) {
            return false;
        }
        PoseData poseData = (PoseData) other;
        return Double.compare(this.x, poseData.x) == 0 && Double.compare(this.y, poseData.y) == 0 && Double.compare(this.yaw, poseData.yaw) == 0;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.x);
        long doubleToLongBits2 = Double.doubleToLongBits(this.y);
        int i = ((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31;
        long doubleToLongBits3 = Double.doubleToLongBits(this.yaw);
        return i + ((int) ((doubleToLongBits3 >>> 32) ^ doubleToLongBits3));
    }

    public String toString() {
        return "PoseData(x=" + this.x + ", y=" + this.y + ", yaw=" + this.yaw + ")";
    }

    public PoseData(double d, double d2, double d3) {
        this.x = d;
        this.y = d2;
        this.yaw = d3;
    }

    public final double getX() {
        return this.x;
    }

    public final double getY() {
        return this.y;
    }

    public final double getYaw() {
        return this.yaw;
    }

    public final void setX(double d) {
        this.x = d;
    }

    public final void setY(double d) {
        this.y = d;
    }

    public final void setYaw(double d) {
        this.yaw = d;
    }
}
