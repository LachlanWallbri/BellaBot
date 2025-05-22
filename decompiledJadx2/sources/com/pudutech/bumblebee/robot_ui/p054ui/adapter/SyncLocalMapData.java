package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import com.pudutech.bumblebee.robot_ui.enums.MapSyncStatus;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SyncLocalMapAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J'\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SyncLocalMapData;", "", "mapName", "", "mapStatus", "Lcom/pudutech/bumblebee/robot_ui/enums/MapSyncStatus;", "mapVersion", "", "(Ljava/lang/String;Lcom/pudutech/bumblebee/robot_ui/enums/MapSyncStatus;I)V", "getMapName", "()Ljava/lang/String;", "getMapStatus", "()Lcom/pudutech/bumblebee/robot_ui/enums/MapSyncStatus;", "setMapStatus", "(Lcom/pudutech/bumblebee/robot_ui/enums/MapSyncStatus;)V", "getMapVersion", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final /* data */ class SyncLocalMapData {
    private final String mapName;
    private MapSyncStatus mapStatus;
    private final int mapVersion;

    public static /* synthetic */ SyncLocalMapData copy$default(SyncLocalMapData syncLocalMapData, String str, MapSyncStatus mapSyncStatus, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = syncLocalMapData.mapName;
        }
        if ((i2 & 2) != 0) {
            mapSyncStatus = syncLocalMapData.mapStatus;
        }
        if ((i2 & 4) != 0) {
            i = syncLocalMapData.mapVersion;
        }
        return syncLocalMapData.copy(str, mapSyncStatus, i);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMapName() {
        return this.mapName;
    }

    /* renamed from: component2, reason: from getter */
    public final MapSyncStatus getMapStatus() {
        return this.mapStatus;
    }

    /* renamed from: component3, reason: from getter */
    public final int getMapVersion() {
        return this.mapVersion;
    }

    public final SyncLocalMapData copy(String mapName, MapSyncStatus mapStatus, int mapVersion) {
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        Intrinsics.checkParameterIsNotNull(mapStatus, "mapStatus");
        return new SyncLocalMapData(mapName, mapStatus, mapVersion);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SyncLocalMapData)) {
            return false;
        }
        SyncLocalMapData syncLocalMapData = (SyncLocalMapData) other;
        return Intrinsics.areEqual(this.mapName, syncLocalMapData.mapName) && Intrinsics.areEqual(this.mapStatus, syncLocalMapData.mapStatus) && this.mapVersion == syncLocalMapData.mapVersion;
    }

    public int hashCode() {
        String str = this.mapName;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        MapSyncStatus mapSyncStatus = this.mapStatus;
        return ((hashCode + (mapSyncStatus != null ? mapSyncStatus.hashCode() : 0)) * 31) + Integer.hashCode(this.mapVersion);
    }

    public String toString() {
        return "SyncLocalMapData(mapName=" + this.mapName + ", mapStatus=" + this.mapStatus + ", mapVersion=" + this.mapVersion + ")";
    }

    public SyncLocalMapData(String mapName, MapSyncStatus mapStatus, int i) {
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        Intrinsics.checkParameterIsNotNull(mapStatus, "mapStatus");
        this.mapName = mapName;
        this.mapStatus = mapStatus;
        this.mapVersion = i;
    }

    public final String getMapName() {
        return this.mapName;
    }

    public final MapSyncStatus getMapStatus() {
        return this.mapStatus;
    }

    public final int getMapVersion() {
        return this.mapVersion;
    }

    public final void setMapStatus(MapSyncStatus mapSyncStatus) {
        Intrinsics.checkParameterIsNotNull(mapSyncStatus, "<set-?>");
        this.mapStatus = mapSyncStatus;
    }
}
