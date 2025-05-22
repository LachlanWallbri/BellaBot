package com.pudutech.event_tracking.custom;

import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;

/* compiled from: custom.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/event_tracking/custom/StorageEvent;", "Lcom/pudutech/event_tracking/custom/CustomArgs;", "totalInternal", "", "availableInternal", "totalExternal", "availableExternal", "(JJJJ)V", "getAvailableExternal", "()J", "getAvailableInternal", "getTotalExternal", "getTotalInternal", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class StorageEvent extends CustomArgs {
    private final long availableExternal;
    private final long availableInternal;
    private final long totalExternal;
    private final long totalInternal;

    /* renamed from: component1, reason: from getter */
    public final long getTotalInternal() {
        return this.totalInternal;
    }

    /* renamed from: component2, reason: from getter */
    public final long getAvailableInternal() {
        return this.availableInternal;
    }

    /* renamed from: component3, reason: from getter */
    public final long getTotalExternal() {
        return this.totalExternal;
    }

    /* renamed from: component4, reason: from getter */
    public final long getAvailableExternal() {
        return this.availableExternal;
    }

    public final StorageEvent copy(long totalInternal, long availableInternal, long totalExternal, long availableExternal) {
        return new StorageEvent(totalInternal, availableInternal, totalExternal, availableExternal);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StorageEvent)) {
            return false;
        }
        StorageEvent storageEvent = (StorageEvent) other;
        return this.totalInternal == storageEvent.totalInternal && this.availableInternal == storageEvent.availableInternal && this.totalExternal == storageEvent.totalExternal && this.availableExternal == storageEvent.availableExternal;
    }

    public int hashCode() {
        return (((((Long.hashCode(this.totalInternal) * 31) + Long.hashCode(this.availableInternal)) * 31) + Long.hashCode(this.totalExternal)) * 31) + Long.hashCode(this.availableExternal);
    }

    public String toString() {
        return "StorageEvent(totalInternal=" + this.totalInternal + ", availableInternal=" + this.availableInternal + ", totalExternal=" + this.totalExternal + ", availableExternal=" + this.availableExternal + ")";
    }

    public final long getTotalInternal() {
        return this.totalInternal;
    }

    public final long getAvailableInternal() {
        return this.availableInternal;
    }

    public final long getTotalExternal() {
        return this.totalExternal;
    }

    public final long getAvailableExternal() {
        return this.availableExternal;
    }

    public StorageEvent(long j, long j2, long j3, long j4) {
        super(CustomKt.CUSTOM_EVENT_STORAGE, MapsKt.mapOf(TuplesKt.m3968to("total_internal", String.valueOf(j)), TuplesKt.m3968to("available_internal", String.valueOf(j2)), TuplesKt.m3968to("total_external", String.valueOf(j3)), TuplesKt.m3968to("available_external", String.valueOf(j4))), 0);
        this.totalInternal = j;
        this.availableInternal = j2;
        this.totalExternal = j3;
        this.availableExternal = j4;
    }
}
