package com.pudutech.robot.opensdk.aliyun.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: AliyunThingProData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u0003HÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/bean/AliyunThingSys;", "", "ack", "", "(I)V", "getAck", "()I", "setAck", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class AliyunThingSys {
    private int ack;

    public AliyunThingSys() {
        this(0, 1, null);
    }

    public static /* synthetic */ AliyunThingSys copy$default(AliyunThingSys aliyunThingSys, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = aliyunThingSys.ack;
        }
        return aliyunThingSys.copy(i);
    }

    /* renamed from: component1, reason: from getter */
    public final int getAck() {
        return this.ack;
    }

    public final AliyunThingSys copy(int ack) {
        return new AliyunThingSys(ack);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof AliyunThingSys) && this.ack == ((AliyunThingSys) other).ack;
        }
        return true;
    }

    public int hashCode() {
        return Integer.hashCode(this.ack);
    }

    public String toString() {
        return "AliyunThingSys(ack=" + this.ack + ")";
    }

    public AliyunThingSys(int i) {
        this.ack = i;
    }

    public /* synthetic */ AliyunThingSys(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i);
    }

    public final int getAck() {
        return this.ack;
    }

    public final void setAck(int i) {
        this.ack = i;
    }
}
