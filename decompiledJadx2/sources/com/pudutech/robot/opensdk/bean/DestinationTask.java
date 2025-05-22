package com.pudutech.robot.opensdk.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: DeliveryTaskBody.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/DestinationTask;", "", "destination", "", "id", "(Ljava/lang/String;Ljava/lang/String;)V", "getDestination", "()Ljava/lang/String;", "getId", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class DestinationTask {
    private final String destination;
    private final String id;

    public static /* synthetic */ DestinationTask copy$default(DestinationTask destinationTask, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = destinationTask.destination;
        }
        if ((i & 2) != 0) {
            str2 = destinationTask.id;
        }
        return destinationTask.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDestination() {
        return this.destination;
    }

    /* renamed from: component2, reason: from getter */
    public final String getId() {
        return this.id;
    }

    public final DestinationTask copy(String destination, String id) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        return new DestinationTask(destination, id);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DestinationTask)) {
            return false;
        }
        DestinationTask destinationTask = (DestinationTask) other;
        return Intrinsics.areEqual(this.destination, destinationTask.destination) && Intrinsics.areEqual(this.id, destinationTask.id);
    }

    public int hashCode() {
        String str = this.destination;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.id;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "DestinationTask(destination=" + this.destination + ", id=" + this.id + ")";
    }

    public DestinationTask(String destination, String str) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        this.destination = destination;
        this.id = str;
    }

    public final String getDestination() {
        return this.destination;
    }

    public final String getId() {
        return this.id;
    }
}
