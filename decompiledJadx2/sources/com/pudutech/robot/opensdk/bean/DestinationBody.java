package com.pudutech.robot.opensdk.bean;

import com.pudutech.robot.opensdk.interf.IBody;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: DestinationBody.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/DestinationBody;", "Lcom/pudutech/robot/opensdk/interf/IBody;", "destination", "Lcom/pudutech/robot/opensdk/bean/Destination;", "(Lcom/pudutech/robot/opensdk/bean/Destination;)V", "getDestination", "()Lcom/pudutech/robot/opensdk/bean/Destination;", "setDestination", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class DestinationBody implements IBody {
    private Destination destination;

    public static /* synthetic */ DestinationBody copy$default(DestinationBody destinationBody, Destination destination, int i, Object obj) {
        if ((i & 1) != 0) {
            destination = destinationBody.destination;
        }
        return destinationBody.copy(destination);
    }

    /* renamed from: component1, reason: from getter */
    public final Destination getDestination() {
        return this.destination;
    }

    public final DestinationBody copy(Destination destination) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        return new DestinationBody(destination);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof DestinationBody) && Intrinsics.areEqual(this.destination, ((DestinationBody) other).destination);
        }
        return true;
    }

    public int hashCode() {
        Destination destination = this.destination;
        if (destination != null) {
            return destination.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "DestinationBody(destination=" + this.destination + ")";
    }

    public DestinationBody(Destination destination) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        this.destination = destination;
    }

    public final Destination getDestination() {
        return this.destination;
    }

    public final void setDestination(Destination destination) {
        Intrinsics.checkParameterIsNotNull(destination, "<set-?>");
        this.destination = destination;
    }
}
