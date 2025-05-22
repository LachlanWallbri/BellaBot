package com.pudutech.robot.opensdk.bean;

import com.pudutech.robot.opensdk.interf.IBody;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: DeliveryTaskBody.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J\u0019\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nHÆ\u0003JA\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0018\b\u0002\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00062\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR!\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\r¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/DeliveryTaskBody;", "Lcom/pudutech/robot/opensdk/interf/IBody;", "type", "", "deliverySort", "executeTask", "", "trays", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/opensdk/bean/TrayDestinationTasks;", "Lkotlin/collections/ArrayList;", "(Ljava/lang/String;Ljava/lang/String;ZLjava/util/ArrayList;)V", "getDeliverySort", "()Ljava/lang/String;", "getExecuteTask", "()Z", "getTrays", "()Ljava/util/ArrayList;", "getType", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class DeliveryTaskBody implements IBody {
    private final String deliverySort;
    private final boolean executeTask;
    private final ArrayList<TrayDestinationTasks> trays;
    private final String type;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DeliveryTaskBody copy$default(DeliveryTaskBody deliveryTaskBody, String str, String str2, boolean z, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            str = deliveryTaskBody.type;
        }
        if ((i & 2) != 0) {
            str2 = deliveryTaskBody.deliverySort;
        }
        if ((i & 4) != 0) {
            z = deliveryTaskBody.executeTask;
        }
        if ((i & 8) != 0) {
            arrayList = deliveryTaskBody.trays;
        }
        return deliveryTaskBody.copy(str, str2, z, arrayList);
    }

    /* renamed from: component1, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDeliverySort() {
        return this.deliverySort;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getExecuteTask() {
        return this.executeTask;
    }

    public final ArrayList<TrayDestinationTasks> component4() {
        return this.trays;
    }

    public final DeliveryTaskBody copy(String type, String deliverySort, boolean executeTask, ArrayList<TrayDestinationTasks> trays) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(deliverySort, "deliverySort");
        Intrinsics.checkParameterIsNotNull(trays, "trays");
        return new DeliveryTaskBody(type, deliverySort, executeTask, trays);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeliveryTaskBody)) {
            return false;
        }
        DeliveryTaskBody deliveryTaskBody = (DeliveryTaskBody) other;
        return Intrinsics.areEqual(this.type, deliveryTaskBody.type) && Intrinsics.areEqual(this.deliverySort, deliveryTaskBody.deliverySort) && this.executeTask == deliveryTaskBody.executeTask && Intrinsics.areEqual(this.trays, deliveryTaskBody.trays);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.type;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.deliverySort;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        boolean z = this.executeTask;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode2 + i) * 31;
        ArrayList<TrayDestinationTasks> arrayList = this.trays;
        return i2 + (arrayList != null ? arrayList.hashCode() : 0);
    }

    public String toString() {
        return "DeliveryTaskBody(type=" + this.type + ", deliverySort=" + this.deliverySort + ", executeTask=" + this.executeTask + ", trays=" + this.trays + ")";
    }

    public DeliveryTaskBody(String type, String deliverySort, boolean z, ArrayList<TrayDestinationTasks> trays) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(deliverySort, "deliverySort");
        Intrinsics.checkParameterIsNotNull(trays, "trays");
        this.type = type;
        this.deliverySort = deliverySort;
        this.executeTask = z;
        this.trays = trays;
    }

    public final String getType() {
        return this.type;
    }

    public final String getDeliverySort() {
        return this.deliverySort;
    }

    public final boolean getExecuteTask() {
        return this.executeTask;
    }

    public final ArrayList<TrayDestinationTasks> getTrays() {
        return this.trays;
    }
}
