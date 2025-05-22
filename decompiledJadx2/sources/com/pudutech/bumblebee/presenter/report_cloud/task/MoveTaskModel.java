package com.pudutech.bumblebee.presenter.report_cloud.task;

import kotlin.Metadata;

/* compiled from: MoveTaskModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0017\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u000f\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0015\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR\u001a\u0010!\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001b\"\u0004\b#\u0010\u001dR\u001a\u0010$\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0006\"\u0004\b&\u0010\bR\u001a\u0010'\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0006\"\u0004\b)\u0010\bR\u001a\u0010*\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0006\"\u0004\b,\u0010\bR\u001a\u0010-\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u001b\"\u0004\b/\u0010\u001d¨\u00060"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/report_cloud/task/MoveTaskModel;", "", "()V", "activeTimestamp_ms", "", "getActiveTimestamp_ms", "()J", "setActiveTimestamp_ms", "(J)V", "durationPause_ms", "getDurationPause_ms", "setDurationPause_ms", "endTimestamp_ms", "getEndTimestamp_ms", "setEndTimestamp_ms", "isActive", "", "()Ljava/lang/Boolean;", "setActive", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "lastSpeedTimestap", "getLastSpeedTimestap", "setLastSpeedTimestap", "mileages_m", "", "getMileages_m", "()D", "setMileages_m", "(D)V", "pauseTimestamp_ms", "getPauseTimestamp_ms", "setPauseTimestamp_ms", "speed", "getSpeed", "setSpeed", "startTimestamp_ms", "getStartTimestamp_ms", "setStartTimestamp_ms", "takingEndTimestamp_ms", "getTakingEndTimestamp_ms", "setTakingEndTimestamp_ms", "takingStartTimestamp_ms", "getTakingStartTimestamp_ms", "setTakingStartTimestamp_ms", "validSpeedTime_ms", "getValidSpeedTime_ms", "setValidSpeedTime_ms", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MoveTaskModel {
    private long activeTimestamp_ms;
    private long durationPause_ms;
    private long endTimestamp_ms;
    private Boolean isActive;
    private long lastSpeedTimestap;
    private double mileages_m;
    private long pauseTimestamp_ms;
    private double speed;
    private long startTimestamp_ms;
    private long takingEndTimestamp_ms;
    private long takingStartTimestamp_ms;
    private double validSpeedTime_ms;

    public final long getStartTimestamp_ms() {
        return this.startTimestamp_ms;
    }

    public final void setStartTimestamp_ms(long j) {
        this.startTimestamp_ms = j;
    }

    public final long getEndTimestamp_ms() {
        return this.endTimestamp_ms;
    }

    public final void setEndTimestamp_ms(long j) {
        this.endTimestamp_ms = j;
    }

    public final double getMileages_m() {
        return this.mileages_m;
    }

    public final void setMileages_m(double d) {
        this.mileages_m = d;
    }

    public final long getDurationPause_ms() {
        return this.durationPause_ms;
    }

    public final void setDurationPause_ms(long j) {
        this.durationPause_ms = j;
    }

    public final long getActiveTimestamp_ms() {
        return this.activeTimestamp_ms;
    }

    public final void setActiveTimestamp_ms(long j) {
        this.activeTimestamp_ms = j;
    }

    public final long getPauseTimestamp_ms() {
        return this.pauseTimestamp_ms;
    }

    public final void setPauseTimestamp_ms(long j) {
        this.pauseTimestamp_ms = j;
    }

    public final long getTakingStartTimestamp_ms() {
        return this.takingStartTimestamp_ms;
    }

    public final void setTakingStartTimestamp_ms(long j) {
        this.takingStartTimestamp_ms = j;
    }

    public final long getTakingEndTimestamp_ms() {
        return this.takingEndTimestamp_ms;
    }

    public final void setTakingEndTimestamp_ms(long j) {
        this.takingEndTimestamp_ms = j;
    }

    /* renamed from: isActive, reason: from getter */
    public final Boolean getIsActive() {
        return this.isActive;
    }

    public final void setActive(Boolean bool) {
        this.isActive = bool;
    }

    public final double getSpeed() {
        return this.speed;
    }

    public final void setSpeed(double d) {
        this.speed = d;
    }

    public final long getLastSpeedTimestap() {
        return this.lastSpeedTimestap;
    }

    public final void setLastSpeedTimestap(long j) {
        this.lastSpeedTimestap = j;
    }

    public final double getValidSpeedTime_ms() {
        return this.validSpeedTime_ms;
    }

    public final void setValidSpeedTime_ms(double d) {
        this.validSpeedTime_ms = d;
    }
}
