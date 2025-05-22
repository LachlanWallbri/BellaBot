package com.pudutech.robot.opensdk.aliyun.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: AliyunThingProData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/bean/AliyunThingValue;", "", ES6Iterator.VALUE_PROPERTY, "", "time", "", "(Ljava/lang/String;J)V", "getTime", "()J", "setTime", "(J)V", "getValue", "()Ljava/lang/String;", "setValue", "(Ljava/lang/String;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class AliyunThingValue {
    private long time;
    private String value;

    public static /* synthetic */ AliyunThingValue copy$default(AliyunThingValue aliyunThingValue, String str, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = aliyunThingValue.value;
        }
        if ((i & 2) != 0) {
            j = aliyunThingValue.time;
        }
        return aliyunThingValue.copy(str, j);
    }

    /* renamed from: component1, reason: from getter */
    public final String getValue() {
        return this.value;
    }

    /* renamed from: component2, reason: from getter */
    public final long getTime() {
        return this.time;
    }

    public final AliyunThingValue copy(String value, long time) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        return new AliyunThingValue(value, time);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AliyunThingValue)) {
            return false;
        }
        AliyunThingValue aliyunThingValue = (AliyunThingValue) other;
        return Intrinsics.areEqual(this.value, aliyunThingValue.value) && this.time == aliyunThingValue.time;
    }

    public int hashCode() {
        String str = this.value;
        return ((str != null ? str.hashCode() : 0) * 31) + Long.hashCode(this.time);
    }

    public String toString() {
        return "AliyunThingValue(value=" + this.value + ", time=" + this.time + ")";
    }

    public AliyunThingValue(String value, long j) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.value = value;
        this.time = j;
    }

    public final String getValue() {
        return this.value;
    }

    public final void setValue(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.value = str;
    }

    public final long getTime() {
        return this.time;
    }

    public final void setTime(long j) {
        this.time = j;
    }
}
