package com.pudutech.robot.opensdk.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: DeliveryOrder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/DeliveryOrder;", "", "tableNo", "", "tableName", "name", "amount", "", "id", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DLjava/lang/String;)V", "getAmount", "()D", "getId", "()Ljava/lang/String;", "getName", "getTableName", "getTableNo", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class DeliveryOrder {
    private final double amount;
    private final String id;
    private final String name;
    private final String tableName;
    private final String tableNo;

    public static /* synthetic */ DeliveryOrder copy$default(DeliveryOrder deliveryOrder, String str, String str2, String str3, double d, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = deliveryOrder.tableNo;
        }
        if ((i & 2) != 0) {
            str2 = deliveryOrder.tableName;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = deliveryOrder.name;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            d = deliveryOrder.amount;
        }
        double d2 = d;
        if ((i & 16) != 0) {
            str4 = deliveryOrder.id;
        }
        return deliveryOrder.copy(str, str5, str6, d2, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTableNo() {
        return this.tableNo;
    }

    /* renamed from: component2, reason: from getter */
    public final String getTableName() {
        return this.tableName;
    }

    /* renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component4, reason: from getter */
    public final double getAmount() {
        return this.amount;
    }

    /* renamed from: component5, reason: from getter */
    public final String getId() {
        return this.id;
    }

    public final DeliveryOrder copy(String tableNo, String tableName, String name, double amount, String id) {
        Intrinsics.checkParameterIsNotNull(tableNo, "tableNo");
        Intrinsics.checkParameterIsNotNull(tableName, "tableName");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(id, "id");
        return new DeliveryOrder(tableNo, tableName, name, amount, id);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeliveryOrder)) {
            return false;
        }
        DeliveryOrder deliveryOrder = (DeliveryOrder) other;
        return Intrinsics.areEqual(this.tableNo, deliveryOrder.tableNo) && Intrinsics.areEqual(this.tableName, deliveryOrder.tableName) && Intrinsics.areEqual(this.name, deliveryOrder.name) && Double.compare(this.amount, deliveryOrder.amount) == 0 && Intrinsics.areEqual(this.id, deliveryOrder.id);
    }

    public int hashCode() {
        String str = this.tableNo;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.tableName;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.name;
        int hashCode3 = (((hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + Double.hashCode(this.amount)) * 31;
        String str4 = this.id;
        return hashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "DeliveryOrder(tableNo=" + this.tableNo + ", tableName=" + this.tableName + ", name=" + this.name + ", amount=" + this.amount + ", id=" + this.id + ")";
    }

    public DeliveryOrder(String tableNo, String tableName, String name, double d, String id) {
        Intrinsics.checkParameterIsNotNull(tableNo, "tableNo");
        Intrinsics.checkParameterIsNotNull(tableName, "tableName");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(id, "id");
        this.tableNo = tableNo;
        this.tableName = tableName;
        this.name = name;
        this.amount = d;
        this.id = id;
    }

    public final String getTableNo() {
        return this.tableNo;
    }

    public final String getTableName() {
        return this.tableName;
    }

    public final String getName() {
        return this.name;
    }

    public final double getAmount() {
        return this.amount;
    }

    public final String getId() {
        return this.id;
    }
}
