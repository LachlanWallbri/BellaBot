package com.slamtec.slamware.robot;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class MoveOption {
    private boolean appending;
    private boolean keyPoints;
    private boolean milestone;
    private boolean noSmooth;
    private boolean precise;
    private boolean returnUnreachableDirectly;
    private Double speedRatio;
    private boolean trackWithOA;
    private boolean withYaw;

    public MoveOption() {
        this.appending = false;
        this.milestone = false;
        this.noSmooth = false;
        this.keyPoints = false;
        this.precise = false;
        this.withYaw = false;
        this.returnUnreachableDirectly = false;
        this.trackWithOA = false;
        this.speedRatio = null;
    }

    public MoveOption(Double d) {
        this.appending = false;
        this.milestone = false;
        this.noSmooth = false;
        this.keyPoints = false;
        this.precise = false;
        this.withYaw = false;
        this.returnUnreachableDirectly = false;
        this.trackWithOA = false;
        this.speedRatio = null;
        this.speedRatio = d;
    }

    public boolean isAppending() {
        return this.appending;
    }

    public void setAppending(boolean z) {
        this.appending = z;
    }

    public boolean isMilestone() {
        return this.milestone;
    }

    public void setMilestone(boolean z) {
        this.milestone = z;
    }

    public boolean isNoSmooth() {
        return this.noSmooth;
    }

    public void setNoSmooth(boolean z) {
        this.noSmooth = z;
    }

    public boolean isKeyPoints() {
        return this.keyPoints;
    }

    public void setKeyPoints(boolean z) {
        this.keyPoints = z;
    }

    public boolean isPrecise() {
        return this.precise;
    }

    public void setPrecise(boolean z) {
        this.precise = z;
    }

    public boolean isWithYaw() {
        return this.withYaw;
    }

    public void setWithYaw(boolean z) {
        this.withYaw = z;
    }

    public boolean isReturnUnreachableDirectly() {
        return this.returnUnreachableDirectly;
    }

    public void setReturnUnreachableDirectly(boolean z) {
        this.returnUnreachableDirectly = z;
    }

    public boolean isTrackWithOA() {
        return this.trackWithOA;
    }

    public void setTrackWithOA(boolean z) {
        this.trackWithOA = z;
    }

    public Double getSpeedRatio() {
        return this.speedRatio;
    }

    public void setSpeedRatio(Double d) {
        this.speedRatio = d;
    }
}
