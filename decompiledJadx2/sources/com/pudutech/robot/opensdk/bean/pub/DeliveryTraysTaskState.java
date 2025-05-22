package com.pudutech.robot.opensdk.bean.pub;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: DeliveryTaskState.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\u0002\u0010\u0006J\u0019\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0003J#\u0010\n\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/pub/DeliveryTraysTaskState;", "", "destinations", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/opensdk/bean/pub/DeliveryTaskState;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;)V", "getDestinations", "()Ljava/util/ArrayList;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class DeliveryTraysTaskState {
    private final ArrayList<DeliveryTaskState> destinations;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DeliveryTraysTaskState copy$default(DeliveryTraysTaskState deliveryTraysTaskState, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = deliveryTraysTaskState.destinations;
        }
        return deliveryTraysTaskState.copy(arrayList);
    }

    public final ArrayList<DeliveryTaskState> component1() {
        return this.destinations;
    }

    public final DeliveryTraysTaskState copy(ArrayList<DeliveryTaskState> destinations) {
        Intrinsics.checkParameterIsNotNull(destinations, "destinations");
        return new DeliveryTraysTaskState(destinations);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof DeliveryTraysTaskState) && Intrinsics.areEqual(this.destinations, ((DeliveryTraysTaskState) other).destinations);
        }
        return true;
    }

    public int hashCode() {
        ArrayList<DeliveryTaskState> arrayList = this.destinations;
        if (arrayList != null) {
            return arrayList.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "DeliveryTraysTaskState(destinations=" + this.destinations + ")";
    }

    public DeliveryTraysTaskState(ArrayList<DeliveryTaskState> destinations) {
        Intrinsics.checkParameterIsNotNull(destinations, "destinations");
        this.destinations = destinations;
    }

    public final ArrayList<DeliveryTaskState> getDestinations() {
        return this.destinations;
    }
}
