package com.pudutech.bumblebee.presenter.robot_open_task.bean;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MqttResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003JE\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020&HÖ\u0001J\t\u0010'\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\f\"\u0004\b\u0016\u0010\u000eR\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0012\"\u0004\b\u0018\u0010\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u0014¨\u0006("}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CallTaskDownPayLoad;", "", "point", "", "type", "", "shop_id", "map_name", "task_id", TypedValues.Attributes.S_TARGET, "(Ljava/lang/String;JJLjava/lang/String;JLjava/lang/String;)V", "getMap_name", "()Ljava/lang/String;", "setMap_name", "(Ljava/lang/String;)V", "getPoint", "setPoint", "getShop_id", "()J", "setShop_id", "(J)V", "getTarget", "setTarget", "getTask_id", "setTask_id", "getType", "setType", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class CallTaskDownPayLoad {
    private String map_name;
    private String point;
    private long shop_id;
    private String target;
    private long task_id;
    private long type;

    /* renamed from: component1, reason: from getter */
    public final String getPoint() {
        return this.point;
    }

    /* renamed from: component2, reason: from getter */
    public final long getType() {
        return this.type;
    }

    /* renamed from: component3, reason: from getter */
    public final long getShop_id() {
        return this.shop_id;
    }

    /* renamed from: component4, reason: from getter */
    public final String getMap_name() {
        return this.map_name;
    }

    /* renamed from: component5, reason: from getter */
    public final long getTask_id() {
        return this.task_id;
    }

    /* renamed from: component6, reason: from getter */
    public final String getTarget() {
        return this.target;
    }

    public final CallTaskDownPayLoad copy(String point, long type, long shop_id, String map_name, long task_id, String target) {
        Intrinsics.checkParameterIsNotNull(point, "point");
        Intrinsics.checkParameterIsNotNull(map_name, "map_name");
        Intrinsics.checkParameterIsNotNull(target, "target");
        return new CallTaskDownPayLoad(point, type, shop_id, map_name, task_id, target);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CallTaskDownPayLoad)) {
            return false;
        }
        CallTaskDownPayLoad callTaskDownPayLoad = (CallTaskDownPayLoad) other;
        return Intrinsics.areEqual(this.point, callTaskDownPayLoad.point) && this.type == callTaskDownPayLoad.type && this.shop_id == callTaskDownPayLoad.shop_id && Intrinsics.areEqual(this.map_name, callTaskDownPayLoad.map_name) && this.task_id == callTaskDownPayLoad.task_id && Intrinsics.areEqual(this.target, callTaskDownPayLoad.target);
    }

    public int hashCode() {
        String str = this.point;
        int hashCode = str != null ? str.hashCode() : 0;
        long j = this.type;
        int i = ((hashCode * 31) + ((int) (j ^ (j >>> 32)))) * 31;
        long j2 = this.shop_id;
        int i2 = (i + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        String str2 = this.map_name;
        int hashCode2 = (i2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        long j3 = this.task_id;
        int i3 = (hashCode2 + ((int) (j3 ^ (j3 >>> 32)))) * 31;
        String str3 = this.target;
        return i3 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "CallTaskDownPayLoad(point=" + this.point + ", type=" + this.type + ", shop_id=" + this.shop_id + ", map_name=" + this.map_name + ", task_id=" + this.task_id + ", target=" + this.target + ")";
    }

    public CallTaskDownPayLoad(String point, long j, long j2, String map_name, long j3, String target) {
        Intrinsics.checkParameterIsNotNull(point, "point");
        Intrinsics.checkParameterIsNotNull(map_name, "map_name");
        Intrinsics.checkParameterIsNotNull(target, "target");
        this.point = point;
        this.type = j;
        this.shop_id = j2;
        this.map_name = map_name;
        this.task_id = j3;
        this.target = target;
    }

    public final String getPoint() {
        return this.point;
    }

    public final void setPoint(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.point = str;
    }

    public final long getType() {
        return this.type;
    }

    public final void setType(long j) {
        this.type = j;
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

    public final long getTask_id() {
        return this.task_id;
    }

    public final void setTask_id(long j) {
        this.task_id = j;
    }

    public final String getTarget() {
        return this.target;
    }

    public final void setTarget(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.target = str;
    }
}
