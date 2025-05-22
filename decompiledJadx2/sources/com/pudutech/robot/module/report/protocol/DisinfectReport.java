package com.pudutech.robot.module.report.protocol;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.robot.module.report.protocol.bean.DisinfectTaskPoint;
import com.pudutech.robot.module.report.track2.TrackKey;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: DisinfectReport.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u001d\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010@\u001a\u000201H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001a\u0010\u0015\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR\u001a\u0010\u0018\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000eR\u001a\u0010\u001b\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\f\"\u0004\b\u001d\u0010\u000eR\u001a\u0010\u001e\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR\u001a\u0010!\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0006\"\u0004\b#\u0010\bR\u001a\u0010$\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\f\"\u0004\b&\u0010\u000eR\u001a\u0010'\u001a\u00020(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010-\u001a\u00020(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010*\"\u0004\b/\u0010,R\u001a\u00100\u001a\u000201X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001a\u00106\u001a\u00020(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010*\"\u0004\b8\u0010,R\"\u00109\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010;0:X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?¨\u0006A"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/DisinfectReport;", "Lcom/pudutech/robot/module/report/protocol/BaseV2Report;", "()V", "average", "", "getAverage", "()D", "setAverage", "(D)V", TypedValues.Transition.S_DURATION, "", "getDuration", "()J", "setDuration", "(J)V", "duration_default", "getDuration_default", "setDuration_default", "duration_move", "getDuration_move", "setDuration_move", "duration_pause", "getDuration_pause", "setDuration_pause", "duration_ray", "getDuration_ray", "setDuration_ray", "duration_spray", "getDuration_spray", "setDuration_spray", "mileage", "getMileage", "setMileage", "spray_rate", "getSpray_rate", "setSpray_rate", "task_id", "getTask_id", "setTask_id", "task_method", "", "getTask_method", "()I", "setTask_method", "(I)V", "task_mode", "getTask_mode", "setTask_mode", "task_name", "", "getTask_name", "()Ljava/lang/String;", "setTask_name", "(Ljava/lang/String;)V", TrackKey.TASK_NUMBER, "getTask_number", "setTask_number", "task_point_list", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/module/report/protocol/bean/DisinfectTaskPoint;", "getTask_point_list", "()Ljava/util/ArrayList;", "setTask_point_list", "(Ljava/util/ArrayList;)V", "toString", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class DisinfectReport extends BaseV2Report {
    private double average;

    /* renamed from: duration, reason: from kotlin metadata and from toString */
    private long work_time;

    /* renamed from: duration_default, reason: from kotlin metadata and from toString */
    private long total_time;

    /* renamed from: duration_move, reason: from kotlin metadata and from toString */
    private long move_time;

    /* renamed from: duration_pause, reason: from kotlin metadata and from toString */
    private long total_pause_time;

    /* renamed from: duration_ray, reason: from kotlin metadata and from toString */
    private long ray_time;

    /* renamed from: duration_spray, reason: from kotlin metadata and from toString */
    private long spray_time;
    private double mileage;
    private double spray_rate;
    private int task_method;
    private int task_mode;
    private int task_number;
    private long task_id = System.currentTimeMillis() / 1000;
    private String task_name = "";
    private ArrayList<DisinfectTaskPoint> task_point_list = new ArrayList<>();

    public DisinfectReport() {
        setType("disinfect");
    }

    public final long getTask_id() {
        return this.task_id;
    }

    public final void setTask_id(long j) {
        this.task_id = j;
    }

    public final int getTask_mode() {
        return this.task_mode;
    }

    public final void setTask_mode(int i) {
        this.task_mode = i;
    }

    public final int getTask_method() {
        return this.task_method;
    }

    public final void setTask_method(int i) {
        this.task_method = i;
    }

    public final int getTask_number() {
        return this.task_number;
    }

    public final void setTask_number(int i) {
        this.task_number = i;
    }

    public final String getTask_name() {
        return this.task_name;
    }

    public final void setTask_name(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.task_name = str;
    }

    public final ArrayList<DisinfectTaskPoint> getTask_point_list() {
        return this.task_point_list;
    }

    public final void setTask_point_list(ArrayList<DisinfectTaskPoint> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.task_point_list = arrayList;
    }

    /* renamed from: getDuration_default, reason: from getter */
    public final long getTotal_time() {
        return this.total_time;
    }

    public final void setDuration_default(long j) {
        this.total_time = j;
    }

    /* renamed from: getDuration, reason: from getter */
    public final long getWork_time() {
        return this.work_time;
    }

    public final void setDuration(long j) {
        this.work_time = j;
    }

    /* renamed from: getDuration_spray, reason: from getter */
    public final long getSpray_time() {
        return this.spray_time;
    }

    public final void setDuration_spray(long j) {
        this.spray_time = j;
    }

    public final double getSpray_rate() {
        return this.spray_rate;
    }

    public final void setSpray_rate(double d) {
        this.spray_rate = d;
    }

    /* renamed from: getDuration_ray, reason: from getter */
    public final long getRay_time() {
        return this.ray_time;
    }

    public final void setDuration_ray(long j) {
        this.ray_time = j;
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

    /* renamed from: getDuration_pause, reason: from getter */
    public final long getTotal_pause_time() {
        return this.total_pause_time;
    }

    public final void setDuration_pause(long j) {
        this.total_pause_time = j;
    }

    /* renamed from: getDuration_move, reason: from getter */
    public final long getMove_time() {
        return this.move_time;
    }

    public final void setDuration_move(long j) {
        this.move_time = j;
    }

    public String toString() {
        return "DisinfectReport(task_mode=" + this.task_mode + ", task_method=" + this.task_method + ", task_number=" + this.task_number + ", task_name='" + this.task_name + "', task_point_list=" + this.task_point_list + ", total_time=" + this.total_time + ", work_time=" + this.work_time + ", spray_time=" + this.spray_time + ", spray_rate=" + this.spray_rate + ", ray_time=" + this.ray_time + ", mileage=" + this.mileage + ", average=" + this.average + ", total_pause_time=" + this.total_pause_time + ", move_time=" + this.move_time + ')';
    }
}
