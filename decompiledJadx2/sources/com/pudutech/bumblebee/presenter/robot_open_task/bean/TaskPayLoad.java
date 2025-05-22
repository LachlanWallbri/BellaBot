package com.pudutech.bumblebee.presenter.robot_open_task.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MqttResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\bHÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J?\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\bHÖ\u0001J\t\u0010%\u001a\u00020\u0005HÖ\u0001R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0014R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006&"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/TaskPayLoad;", "", "shopId", "", "point", "", "info", "type", "", "taskId", "(JLjava/lang/String;Ljava/lang/String;IJ)V", "getInfo", "()Ljava/lang/String;", "setInfo", "(Ljava/lang/String;)V", "getPoint", "setPoint", "getShopId", "()J", "setShopId", "(J)V", "getTaskId", "setTaskId", "getType", "()I", "setType", "(I)V", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class TaskPayLoad {
    private String info;
    private String point;
    private long shopId;
    private long taskId;
    private int type;

    /* renamed from: component1, reason: from getter */
    public final long getShopId() {
        return this.shopId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPoint() {
        return this.point;
    }

    /* renamed from: component3, reason: from getter */
    public final String getInfo() {
        return this.info;
    }

    /* renamed from: component4, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* renamed from: component5, reason: from getter */
    public final long getTaskId() {
        return this.taskId;
    }

    public final TaskPayLoad copy(long shopId, String point, String info, int type, long taskId) {
        return new TaskPayLoad(shopId, point, info, type, taskId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TaskPayLoad)) {
            return false;
        }
        TaskPayLoad taskPayLoad = (TaskPayLoad) other;
        return this.shopId == taskPayLoad.shopId && Intrinsics.areEqual(this.point, taskPayLoad.point) && Intrinsics.areEqual(this.info, taskPayLoad.info) && this.type == taskPayLoad.type && this.taskId == taskPayLoad.taskId;
    }

    public int hashCode() {
        long j = this.shopId;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        String str = this.point;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.info;
        int hashCode2 = (((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.type) * 31;
        long j2 = this.taskId;
        return hashCode2 + ((int) ((j2 >>> 32) ^ j2));
    }

    public String toString() {
        return "TaskPayLoad(shopId=" + this.shopId + ", point=" + this.point + ", info=" + this.info + ", type=" + this.type + ", taskId=" + this.taskId + ")";
    }

    public TaskPayLoad(long j, String str, String str2, int i, long j2) {
        this.shopId = j;
        this.point = str;
        this.info = str2;
        this.type = i;
        this.taskId = j2;
    }

    public final long getShopId() {
        return this.shopId;
    }

    public final void setShopId(long j) {
        this.shopId = j;
    }

    public final String getPoint() {
        return this.point;
    }

    public final void setPoint(String str) {
        this.point = str;
    }

    public final String getInfo() {
        return this.info;
    }

    public final void setInfo(String str) {
        this.info = str;
    }

    public final int getType() {
        return this.type;
    }

    public final void setType(int i) {
        this.type = i;
    }

    public /* synthetic */ TaskPayLoad(long j, String str, String str2, int i, long j2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, str, str2, i, (i2 & 16) != 0 ? 0L : j2);
    }

    public final long getTaskId() {
        return this.taskId;
    }

    public final void setTaskId(long j) {
        this.taskId = j;
    }
}
