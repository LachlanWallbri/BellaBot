package com.pudutech.bumblebee.presenter.report_cloud.protocol.custom_call;

import com.pudutech.bumblebee.presenter.report_cloud.protocol.Head;
import com.pudutech.bumblebee.presenter.report_cloud.protocol.delivery.Info;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomCall.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR \u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0006\"\u0004\b\u0015\u0010\bR\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001b¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/report_cloud/protocol/custom_call/CustomCall;", "Lcom/pudutech/bumblebee/presenter/report_cloud/protocol/Head;", "()V", "average", "", "getAverage", "()D", "setAverage", "(D)V", "battery", "getBattery", "setBattery", "info", "", "Lcom/pudutech/bumblebee/presenter/report_cloud/protocol/delivery/Info;", "getInfo", "()Ljava/util/List;", "setInfo", "(Ljava/util/List;)V", "mileage", "getMileage", "setMileage", "task_id", "", "getTask_id", "()J", "setTask_id", "(J)V", "total_time", "getTotal_time", "setTotal_time", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CustomCall extends Head {
    private double average;
    private double battery;
    private List<Info> info = new ArrayList();
    private double mileage;
    private long task_id;
    private long total_time;

    public CustomCall() {
        this.type = "custom_call";
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

    public final List<Info> getInfo() {
        return this.info;
    }

    public final void setInfo(List<Info> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.info = list;
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
