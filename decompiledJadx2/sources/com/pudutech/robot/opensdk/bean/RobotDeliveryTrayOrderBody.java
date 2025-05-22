package com.pudutech.robot.opensdk.bean;

import com.pudutech.robot.opensdk.interf.IBody;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: RobotDeliveryTrayOrderBody.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0019\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0007HÆ\u0003J-\u0010\u000f\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/RobotDeliveryTrayOrderBody;", "Lcom/pudutech/robot/opensdk/interf/IBody;", "orders", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/opensdk/bean/DeliveryOrder;", "Lkotlin/collections/ArrayList;", "trayIndex", "", "(Ljava/util/ArrayList;I)V", "getOrders", "()Ljava/util/ArrayList;", "getTrayIndex", "()I", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class RobotDeliveryTrayOrderBody implements IBody {
    private final ArrayList<DeliveryOrder> orders;
    private final int trayIndex;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ RobotDeliveryTrayOrderBody copy$default(RobotDeliveryTrayOrderBody robotDeliveryTrayOrderBody, ArrayList arrayList, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            arrayList = robotDeliveryTrayOrderBody.orders;
        }
        if ((i2 & 2) != 0) {
            i = robotDeliveryTrayOrderBody.trayIndex;
        }
        return robotDeliveryTrayOrderBody.copy(arrayList, i);
    }

    public final ArrayList<DeliveryOrder> component1() {
        return this.orders;
    }

    /* renamed from: component2, reason: from getter */
    public final int getTrayIndex() {
        return this.trayIndex;
    }

    public final RobotDeliveryTrayOrderBody copy(ArrayList<DeliveryOrder> orders, int trayIndex) {
        Intrinsics.checkParameterIsNotNull(orders, "orders");
        return new RobotDeliveryTrayOrderBody(orders, trayIndex);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RobotDeliveryTrayOrderBody)) {
            return false;
        }
        RobotDeliveryTrayOrderBody robotDeliveryTrayOrderBody = (RobotDeliveryTrayOrderBody) other;
        return Intrinsics.areEqual(this.orders, robotDeliveryTrayOrderBody.orders) && this.trayIndex == robotDeliveryTrayOrderBody.trayIndex;
    }

    public int hashCode() {
        ArrayList<DeliveryOrder> arrayList = this.orders;
        return ((arrayList != null ? arrayList.hashCode() : 0) * 31) + Integer.hashCode(this.trayIndex);
    }

    public String toString() {
        return "RobotDeliveryTrayOrderBody(orders=" + this.orders + ", trayIndex=" + this.trayIndex + ")";
    }

    public RobotDeliveryTrayOrderBody(ArrayList<DeliveryOrder> orders, int i) {
        Intrinsics.checkParameterIsNotNull(orders, "orders");
        this.orders = orders;
        this.trayIndex = i;
    }

    public final ArrayList<DeliveryOrder> getOrders() {
        return this.orders;
    }

    public final int getTrayIndex() {
        return this.trayIndex;
    }
}
