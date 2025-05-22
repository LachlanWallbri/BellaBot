package com.pudutech.robot.module.report.protocol;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: CruiseReport.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000f\"\u0004\b\u001a\u0010\u0011R\u001a\u0010\u001b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\bR\u001a\u0010\u001e\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u000f\"\u0004\b&\u0010\u0011R\u001a\u0010'\u001a\u00020(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010-\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u000f\"\u0004\b/\u0010\u0011¨\u00060"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/CruiseReport;", "Lcom/pudutech/robot/module/report/protocol/BaseV2Report;", "()V", "average", "", "getAverage", "()D", "setAverage", "(D)V", "battery", "getBattery", "setBattery", TypedValues.Transition.S_DURATION, "", "getDuration", "()J", "setDuration", "(J)V", "duration_count", "", "getDuration_count", "()I", "setDuration_count", "(I)V", "duration_pause", "getDuration_pause", "setDuration_pause", "mileage", "getMileage", "setMileage", "status", "", "getStatus", "()Z", "setStatus", "(Z)V", "task_id", "getTask_id", "setTask_id", "theme", "", "getTheme", "()Ljava/lang/String;", "setTheme", "(Ljava/lang/String;)V", "total_time", "getTotal_time", "setTotal_time", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class CruiseReport extends BaseV2Report {
    private double average;
    private double battery;
    private long duration;
    private int duration_count;
    private long duration_pause;
    private double mileage;
    private boolean status;
    private long task_id;
    private String theme = "";
    private long total_time;

    public CruiseReport() {
        setType("cruise");
    }

    public final long getTask_id() {
        return this.task_id;
    }

    public final void setTask_id(long j) {
        this.task_id = j;
    }

    public final double getBattery() {
        return this.battery;
    }

    public final void setBattery(double d) {
        this.battery = d;
    }

    public final boolean getStatus() {
        return this.status;
    }

    public final void setStatus(boolean z) {
        this.status = z;
    }

    public final String getTheme() {
        return this.theme;
    }

    public final void setTheme(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.theme = str;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final void setDuration(long j) {
        this.duration = j;
    }

    public final long getDuration_pause() {
        return this.duration_pause;
    }

    public final void setDuration_pause(long j) {
        this.duration_pause = j;
    }

    public final int getDuration_count() {
        return this.duration_count;
    }

    public final void setDuration_count(int i) {
        this.duration_count = i;
    }

    public final double getMileage() {
        return this.mileage;
    }

    public final void setMileage(double d) {
        this.mileage = d;
    }

    public final double getAverage() {
        return this.average;
    }

    public final void setAverage(double d) {
        this.average = d;
    }

    public final long getTotal_time() {
        return this.total_time;
    }

    public final void setTotal_time(long j) {
        this.total_time = j;
    }
}
