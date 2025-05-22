package com.pudutech.bumblebee.presenter.robot_open_task.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MqttReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\t¢\u0006\u0002\u0010\rJ\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\u0010\u0010'\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000fJ\t\u0010(\u001a\u00020\tHÆ\u0003J\t\u0010)\u001a\u00020\u000bHÆ\u0003J\t\u0010*\u001a\u00020\tHÆ\u0003JL\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\tHÆ\u0001¢\u0006\u0002\u0010,J\u0013\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u00020\u0007HÖ\u0001J\t\u00101\u001a\u00020\u000bHÖ\u0001R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\f\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0014\"\u0004\b \u0010\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$¨\u00062"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/PayloadStatus;", "", "schedule", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/Schedule;", "work", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/Work;", "battery", "", "shop_id", "", "map_name", "", "charging", "(Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/Schedule;Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/Work;Ljava/lang/Integer;JLjava/lang/String;J)V", "getBattery", "()Ljava/lang/Integer;", "setBattery", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getCharging", "()J", "setCharging", "(J)V", "getMap_name", "()Ljava/lang/String;", "setMap_name", "(Ljava/lang/String;)V", "getSchedule", "()Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/Schedule;", "setSchedule", "(Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/Schedule;)V", "getShop_id", "setShop_id", "getWork", "()Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/Work;", "setWork", "(Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/Work;)V", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/Schedule;Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/Work;Ljava/lang/Integer;JLjava/lang/String;J)Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/PayloadStatus;", "equals", "", "other", "hashCode", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class PayloadStatus {
    private Integer battery;
    private long charging;
    private String map_name;
    private Schedule schedule;
    private long shop_id;
    private Work work;

    /* renamed from: component1, reason: from getter */
    public final Schedule getSchedule() {
        return this.schedule;
    }

    /* renamed from: component2, reason: from getter */
    public final Work getWork() {
        return this.work;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getBattery() {
        return this.battery;
    }

    /* renamed from: component4, reason: from getter */
    public final long getShop_id() {
        return this.shop_id;
    }

    /* renamed from: component5, reason: from getter */
    public final String getMap_name() {
        return this.map_name;
    }

    /* renamed from: component6, reason: from getter */
    public final long getCharging() {
        return this.charging;
    }

    public final PayloadStatus copy(Schedule schedule, Work work, Integer battery, long shop_id, String map_name, long charging) {
        Intrinsics.checkParameterIsNotNull(schedule, "schedule");
        Intrinsics.checkParameterIsNotNull(work, "work");
        Intrinsics.checkParameterIsNotNull(map_name, "map_name");
        return new PayloadStatus(schedule, work, battery, shop_id, map_name, charging);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PayloadStatus)) {
            return false;
        }
        PayloadStatus payloadStatus = (PayloadStatus) other;
        return Intrinsics.areEqual(this.schedule, payloadStatus.schedule) && Intrinsics.areEqual(this.work, payloadStatus.work) && Intrinsics.areEqual(this.battery, payloadStatus.battery) && this.shop_id == payloadStatus.shop_id && Intrinsics.areEqual(this.map_name, payloadStatus.map_name) && this.charging == payloadStatus.charging;
    }

    public int hashCode() {
        Schedule schedule = this.schedule;
        int hashCode = (schedule != null ? schedule.hashCode() : 0) * 31;
        Work work = this.work;
        int hashCode2 = (hashCode + (work != null ? work.hashCode() : 0)) * 31;
        Integer num = this.battery;
        int hashCode3 = (hashCode2 + (num != null ? num.hashCode() : 0)) * 31;
        long j = this.shop_id;
        int i = (hashCode3 + ((int) (j ^ (j >>> 32)))) * 31;
        String str = this.map_name;
        int hashCode4 = (i + (str != null ? str.hashCode() : 0)) * 31;
        long j2 = this.charging;
        return hashCode4 + ((int) (j2 ^ (j2 >>> 32)));
    }

    public String toString() {
        return "PayloadStatus(schedule=" + this.schedule + ", work=" + this.work + ", battery=" + this.battery + ", shop_id=" + this.shop_id + ", map_name=" + this.map_name + ", charging=" + this.charging + ")";
    }

    public PayloadStatus(Schedule schedule, Work work, Integer num, long j, String map_name, long j2) {
        Intrinsics.checkParameterIsNotNull(schedule, "schedule");
        Intrinsics.checkParameterIsNotNull(work, "work");
        Intrinsics.checkParameterIsNotNull(map_name, "map_name");
        this.schedule = schedule;
        this.work = work;
        this.battery = num;
        this.shop_id = j;
        this.map_name = map_name;
        this.charging = j2;
    }

    public final Schedule getSchedule() {
        return this.schedule;
    }

    public final void setSchedule(Schedule schedule) {
        Intrinsics.checkParameterIsNotNull(schedule, "<set-?>");
        this.schedule = schedule;
    }

    public final Work getWork() {
        return this.work;
    }

    public final void setWork(Work work) {
        Intrinsics.checkParameterIsNotNull(work, "<set-?>");
        this.work = work;
    }

    public final Integer getBattery() {
        return this.battery;
    }

    public final void setBattery(Integer num) {
        this.battery = num;
    }

    public final long getShop_id() {
        return this.shop_id;
    }

    public final void setShop_id(long j) {
        this.shop_id = j;
    }

    public final String getMap_name() {
        return this.map_name;
    }

    public final void setMap_name(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.map_name = str;
    }

    public final long getCharging() {
        return this.charging;
    }

    public final void setCharging(long j) {
        this.charging = j;
    }
}
