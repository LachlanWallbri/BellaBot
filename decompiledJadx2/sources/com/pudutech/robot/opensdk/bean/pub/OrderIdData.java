package com.pudutech.robot.opensdk.bean.pub;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: OrderIdData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/pub/OrderIdData;", "", "id", "", "spendTime", "", "(Ljava/lang/String;J)V", "getId", "()Ljava/lang/String;", "getSpendTime", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class OrderIdData {
    private final String id;
    private final long spendTime;

    public static /* synthetic */ OrderIdData copy$default(OrderIdData orderIdData, String str, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = orderIdData.id;
        }
        if ((i & 2) != 0) {
            j = orderIdData.spendTime;
        }
        return orderIdData.copy(str, j);
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final long getSpendTime() {
        return this.spendTime;
    }

    public final OrderIdData copy(String id, long spendTime) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        return new OrderIdData(id, spendTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OrderIdData)) {
            return false;
        }
        OrderIdData orderIdData = (OrderIdData) other;
        return Intrinsics.areEqual(this.id, orderIdData.id) && this.spendTime == orderIdData.spendTime;
    }

    public int hashCode() {
        String str = this.id;
        return ((str != null ? str.hashCode() : 0) * 31) + Long.hashCode(this.spendTime);
    }

    public String toString() {
        return "OrderIdData(id=" + this.id + ", spendTime=" + this.spendTime + ")";
    }

    public OrderIdData(String id, long j) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        this.id = id;
        this.spendTime = j;
    }

    public final String getId() {
        return this.id;
    }

    public final long getSpendTime() {
        return this.spendTime;
    }
}
