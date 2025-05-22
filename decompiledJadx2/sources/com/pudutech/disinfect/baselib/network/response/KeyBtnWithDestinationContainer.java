package com.pudutech.disinfect.baselib.network.response;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: iot_call.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/KeyBtnWithDestinationContainer;", "", "call_list", "", "Lcom/pudutech/disinfect/baselib/network/response/KeyBtnWithDestination;", "(Ljava/util/List;)V", "getCall_list", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class KeyBtnWithDestinationContainer {
    private final List<KeyBtnWithDestination> call_list;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ KeyBtnWithDestinationContainer copy$default(KeyBtnWithDestinationContainer keyBtnWithDestinationContainer, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = keyBtnWithDestinationContainer.call_list;
        }
        return keyBtnWithDestinationContainer.copy(list);
    }

    public final List<KeyBtnWithDestination> component1() {
        return this.call_list;
    }

    public final KeyBtnWithDestinationContainer copy(List<KeyBtnWithDestination> call_list) {
        Intrinsics.checkParameterIsNotNull(call_list, "call_list");
        return new KeyBtnWithDestinationContainer(call_list);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof KeyBtnWithDestinationContainer) && Intrinsics.areEqual(this.call_list, ((KeyBtnWithDestinationContainer) other).call_list);
        }
        return true;
    }

    public int hashCode() {
        List<KeyBtnWithDestination> list = this.call_list;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "KeyBtnWithDestinationContainer(call_list=" + this.call_list + ")";
    }

    public KeyBtnWithDestinationContainer(List<KeyBtnWithDestination> call_list) {
        Intrinsics.checkParameterIsNotNull(call_list, "call_list");
        this.call_list = call_list;
    }

    public final List<KeyBtnWithDestination> getCall_list() {
        return this.call_list;
    }
}
