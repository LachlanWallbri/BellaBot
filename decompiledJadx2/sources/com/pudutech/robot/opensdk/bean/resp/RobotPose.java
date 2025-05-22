package com.pudutech.robot.opensdk.bean.resp;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: RespRobotStateBody.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/resp/RobotPose;", "", "x", "", "y", "angle", "(DDD)V", "getAngle", "()D", "getX", "getY", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class RobotPose {
    private final double angle;
    private final double x;
    private final double y;

    public static /* synthetic */ RobotPose copy$default(RobotPose robotPose, double d, double d2, double d3, int i, Object obj) {
        if ((i & 1) != 0) {
            d = robotPose.x;
        }
        double d4 = d;
        if ((i & 2) != 0) {
            d2 = robotPose.y;
        }
        double d5 = d2;
        if ((i & 4) != 0) {
            d3 = robotPose.angle;
        }
        return robotPose.copy(d4, d5, d3);
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
    public final double getAngle() {
        return this.angle;
    }

    public final RobotPose copy(double x, double y, double angle) {
        return new RobotPose(x, y, angle);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RobotPose)) {
            return false;
        }
        RobotPose robotPose = (RobotPose) other;
        return Double.compare(this.x, robotPose.x) == 0 && Double.compare(this.y, robotPose.y) == 0 && Double.compare(this.angle, robotPose.angle) == 0;
    }

    public int hashCode() {
        return (((Double.hashCode(this.x) * 31) + Double.hashCode(this.y)) * 31) + Double.hashCode(this.angle);
    }

    public String toString() {
        return "RobotPose(x=" + this.x + ", y=" + this.y + ", angle=" + this.angle + ")";
    }

    public RobotPose(double d, double d2, double d3) {
        this.x = d;
        this.y = d2;
        this.angle = d3;
    }

    public final double getAngle() {
        return this.angle;
    }

    public final double getX() {
        return this.x;
    }

    public final double getY() {
        return this.y;
    }
}
