package com.pudutech.robot.module.report.protocol;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: RecycleDishReport.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0002\b\u0014\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R\u001a\u0010\u001b\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R\u001a\u0010\u001e\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0015\"\u0004\b \u0010\u0017R\u001a\u0010!\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0015\"\u0004\b#\u0010\u0017R\u001a\u0010$\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001a\u0010*\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u000f\"\u0004\b,\u0010\u0011R\u001a\u0010-\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0015\"\u0004\b/\u0010\u0017R\u001a\u00100\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u000f\"\u0004\b2\u0010\u0011R\u001a\u00103\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0015\"\u0004\b5\u0010\u0017R\u001a\u00106\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u000f\"\u0004\b8\u0010\u0011¨\u00069"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/RecycleDishReport;", "Lcom/pudutech/robot/module/report/protocol/BaseV2Report;", "()V", "average", "", "getAverage", "()D", "setAverage", "(D)V", "battery", "getBattery", "setBattery", "destination", "", "getDestination", "()I", "setDestination", "(I)V", TypedValues.Transition.S_DURATION, "", "getDuration", "()J", "setDuration", "(J)V", "duration_pause", "getDuration_pause", "setDuration_pause", "duration_recycle_actual", "getDuration_recycle_actual", "setDuration_recycle_actual", "duration_recycle_preset", "getDuration_recycle_preset", "setDuration_recycle_preset", "duration_wait", "getDuration_wait", "setDuration_wait", "goal_id", "", "getGoal_id", "()Ljava/lang/String;", "setGoal_id", "(Ljava/lang/String;)V", "interrupt", "getInterrupt", "setInterrupt", "mileage", "getMileage", "setMileage", "pause_count", "getPause_count", "setPause_count", "task_id", "getTask_id", "setTask_id", "task_mode", "getTask_mode", "setTask_mode", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class RecycleDishReport extends BaseV2Report {
    private double average;
    private double battery;
    private long duration;
    private long duration_pause;
    private long duration_recycle_actual;
    private long duration_recycle_preset;
    private long duration_wait;
    private int interrupt;
    private long mileage;
    private int pause_count;
    private long task_id;
    private int task_mode = 1;
    private int destination = 1;
    private String goal_id = "";

    public RecycleDishReport() {
        setType("back_disc");
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

    public final int getTask_mode() {
        return this.task_mode;
    }

    public final void setTask_mode(int i) {
        this.task_mode = i;
    }

    public final int getDestination() {
        return this.destination;
    }

    public final void setDestination(int i) {
        this.destination = i;
    }

    public final String getGoal_id() {
        return this.goal_id;
    }

    public final void setGoal_id(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.goal_id = str;
    }

    public final int getInterrupt() {
        return this.interrupt;
    }

    public final void setInterrupt(int i) {
        this.interrupt = i;
    }

    public final long getDuration_wait() {
        return this.duration_wait;
    }

    public final void setDuration_wait(long j) {
        this.duration_wait = j;
    }

    public final long getDuration_pause() {
        return this.duration_pause;
    }

    public final void setDuration_pause(long j) {
        this.duration_pause = j;
    }

    public final long getDuration_recycle_preset() {
        return this.duration_recycle_preset;
    }

    public final void setDuration_recycle_preset(long j) {
        this.duration_recycle_preset = j;
    }

    public final long getDuration_recycle_actual() {
        return this.duration_recycle_actual;
    }

    public final void setDuration_recycle_actual(long j) {
        this.duration_recycle_actual = j;
    }

    public final int getPause_count() {
        return this.pause_count;
    }

    public final void setPause_count(int i) {
        this.pause_count = i;
    }

    public final long getMileage() {
        return this.mileage;
    }

    public final void setMileage(long j) {
        this.mileage = j;
    }

    public final double getAverage() {
        return this.average;
    }

    public final void setAverage(double d) {
        this.average = d;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final void setDuration(long j) {
        this.duration = j;
    }
}
