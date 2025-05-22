package com.pudutech.robot.module.report.protocol;

import com.pudutech.robot.module.report.protocol.bean.DeliveryInfo;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: DeliveryReport.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR*\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0006\"\u0004\b\u0016\u0010\bR\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001a\"\u0004\b\u001f\u0010\u001c¨\u0006 "}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/DeliveryReport;", "Lcom/pudutech/robot/module/report/protocol/BaseV2Report;", "()V", "average", "", "getAverage", "()D", "setAverage", "(D)V", "battery", "getBattery", "setBattery", "info", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/module/report/protocol/bean/DeliveryInfo;", "Lkotlin/collections/ArrayList;", "getInfo", "()Ljava/util/ArrayList;", "setInfo", "(Ljava/util/ArrayList;)V", "mileage", "getMileage", "setMileage", "task_id", "", "getTask_id", "()J", "setTask_id", "(J)V", "total_time", "getTotal_time", "setTotal_time", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class DeliveryReport extends BaseV2Report {
    private double average;
    private double battery;
    private ArrayList<DeliveryInfo> info = new ArrayList<>();
    private double mileage;
    private long task_id;
    private long total_time;

    public DeliveryReport() {
        setType("delivery");
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

    public final ArrayList<DeliveryInfo> getInfo() {
        return this.info;
    }

    public final void setInfo(ArrayList<DeliveryInfo> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.info = arrayList;
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
