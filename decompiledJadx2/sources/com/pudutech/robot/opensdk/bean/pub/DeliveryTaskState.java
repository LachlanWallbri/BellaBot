package com.pudutech.robot.opensdk.bean.pub;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: DeliveryTaskState.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003JS\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006#"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/pub/DeliveryTaskState;", "", "destination", "", "type", "id", "estimatedTime", "", "spendTime", "status", "completeType", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJLjava/lang/String;Ljava/lang/String;)V", "getCompleteType", "()Ljava/lang/String;", "getDestination", "getEstimatedTime", "()J", "getId", "getSpendTime", "getStatus", "getType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class DeliveryTaskState {
    private final String completeType;
    private final String destination;
    private final long estimatedTime;
    private final String id;
    private final long spendTime;
    private final String status;
    private final String type;

    /* renamed from: component1, reason: from getter */
    public final String getDestination() {
        return this.destination;
    }

    /* renamed from: component2, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component3, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component4, reason: from getter */
    public final long getEstimatedTime() {
        return this.estimatedTime;
    }

    /* renamed from: component5, reason: from getter */
    public final long getSpendTime() {
        return this.spendTime;
    }

    /* renamed from: component6, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* renamed from: component7, reason: from getter */
    public final String getCompleteType() {
        return this.completeType;
    }

    public final DeliveryTaskState copy(String destination, String type, String id, long estimatedTime, long spendTime, String status, String completeType) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(status, "status");
        return new DeliveryTaskState(destination, type, id, estimatedTime, spendTime, status, completeType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeliveryTaskState)) {
            return false;
        }
        DeliveryTaskState deliveryTaskState = (DeliveryTaskState) other;
        return Intrinsics.areEqual(this.destination, deliveryTaskState.destination) && Intrinsics.areEqual(this.type, deliveryTaskState.type) && Intrinsics.areEqual(this.id, deliveryTaskState.id) && this.estimatedTime == deliveryTaskState.estimatedTime && this.spendTime == deliveryTaskState.spendTime && Intrinsics.areEqual(this.status, deliveryTaskState.status) && Intrinsics.areEqual(this.completeType, deliveryTaskState.completeType);
    }

    public int hashCode() {
        String str = this.destination;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.type;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.id;
        int hashCode3 = (((((hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + Long.hashCode(this.estimatedTime)) * 31) + Long.hashCode(this.spendTime)) * 31;
        String str4 = this.status;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.completeType;
        return hashCode4 + (str5 != null ? str5.hashCode() : 0);
    }

    public String toString() {
        return "DeliveryTaskState(destination=" + this.destination + ", type=" + this.type + ", id=" + this.id + ", estimatedTime=" + this.estimatedTime + ", spendTime=" + this.spendTime + ", status=" + this.status + ", completeType=" + this.completeType + ")";
    }

    public DeliveryTaskState(String destination, String type, String str, long j, long j2, String status, String str2) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(status, "status");
        this.destination = destination;
        this.type = type;
        this.id = str;
        this.estimatedTime = j;
        this.spendTime = j2;
        this.status = status;
        this.completeType = str2;
    }

    public final String getDestination() {
        return this.destination;
    }

    public final String getType() {
        return this.type;
    }

    public final String getId() {
        return this.id;
    }

    public final long getEstimatedTime() {
        return this.estimatedTime;
    }

    public final long getSpendTime() {
        return this.spendTime;
    }

    public final String getStatus() {
        return this.status;
    }

    public /* synthetic */ DeliveryTaskState(String str, String str2, String str3, long j, long j2, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, j, j2, str4, (i & 64) != 0 ? (String) null : str5);
    }

    public final String getCompleteType() {
        return this.completeType;
    }
}
