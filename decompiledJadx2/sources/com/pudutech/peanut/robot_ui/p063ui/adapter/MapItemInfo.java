package com.pudutech.peanut.robot_ui.p063ui.adapter;

import com.pudutech.mirsdkwrap.lib.map.DestinationType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MapListAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b!\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rJ\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010%\u001a\u00020\tHÆ\u0003J\t\u0010&\u001a\u00020\tHÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\fHÆ\u0003JI\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001J\u0013\u0010)\u001a\u00020\t2\b\u0010*\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010+\u001a\u00020\u0005HÖ\u0001J\t\u0010,\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\n\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0012\"\u0004\b\u0015\u0010\u0014R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u0006-"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/MapItemInfo;", "", "name", "", "type", "", "destinationType", "Lcom/pudutech/mirsdkwrap/lib/map/DestinationType;", "isCheck", "", "isExpand", "mapData", "Lcom/pudutech/peanut/robot_ui/ui/adapter/MapViewData;", "(Ljava/lang/String;ILcom/pudutech/mirsdkwrap/lib/map/DestinationType;ZZLcom/pudutech/peanut/robot_ui/ui/adapter/MapViewData;)V", "getDestinationType", "()Lcom/pudutech/mirsdkwrap/lib/map/DestinationType;", "setDestinationType", "(Lcom/pudutech/mirsdkwrap/lib/map/DestinationType;)V", "()Z", "setCheck", "(Z)V", "setExpand", "getMapData", "()Lcom/pudutech/peanut/robot_ui/ui/adapter/MapViewData;", "setMapData", "(Lcom/pudutech/peanut/robot_ui/ui/adapter/MapViewData;)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getType", "()I", "setType", "(I)V", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class MapItemInfo {
    private DestinationType destinationType;
    private boolean isCheck;
    private boolean isExpand;
    private MapViewData mapData;
    private String name;
    private int type;

    public static /* synthetic */ MapItemInfo copy$default(MapItemInfo mapItemInfo, String str, int i, DestinationType destinationType, boolean z, boolean z2, MapViewData mapViewData, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = mapItemInfo.name;
        }
        if ((i2 & 2) != 0) {
            i = mapItemInfo.type;
        }
        int i3 = i;
        if ((i2 & 4) != 0) {
            destinationType = mapItemInfo.destinationType;
        }
        DestinationType destinationType2 = destinationType;
        if ((i2 & 8) != 0) {
            z = mapItemInfo.isCheck;
        }
        boolean z3 = z;
        if ((i2 & 16) != 0) {
            z2 = mapItemInfo.isExpand;
        }
        boolean z4 = z2;
        if ((i2 & 32) != 0) {
            mapViewData = mapItemInfo.mapData;
        }
        return mapItemInfo.copy(str, i3, destinationType2, z3, z4, mapViewData);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* renamed from: component3, reason: from getter */
    public final DestinationType getDestinationType() {
        return this.destinationType;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getIsCheck() {
        return this.isCheck;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getIsExpand() {
        return this.isExpand;
    }

    /* renamed from: component6, reason: from getter */
    public final MapViewData getMapData() {
        return this.mapData;
    }

    public final MapItemInfo copy(String name, int type, DestinationType destinationType, boolean isCheck, boolean isExpand, MapViewData mapData) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new MapItemInfo(name, type, destinationType, isCheck, isExpand, mapData);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MapItemInfo)) {
            return false;
        }
        MapItemInfo mapItemInfo = (MapItemInfo) other;
        return Intrinsics.areEqual(this.name, mapItemInfo.name) && this.type == mapItemInfo.type && Intrinsics.areEqual(this.destinationType, mapItemInfo.destinationType) && this.isCheck == mapItemInfo.isCheck && this.isExpand == mapItemInfo.isExpand && Intrinsics.areEqual(this.mapData, mapItemInfo.mapData);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.name;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + Integer.hashCode(this.type)) * 31;
        DestinationType destinationType = this.destinationType;
        int hashCode2 = (hashCode + (destinationType != null ? destinationType.hashCode() : 0)) * 31;
        boolean z = this.isCheck;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode2 + i) * 31;
        boolean z2 = this.isExpand;
        int i3 = z2;
        if (z2 != 0) {
            i3 = 1;
        }
        int i4 = (i2 + i3) * 31;
        MapViewData mapViewData = this.mapData;
        return i4 + (mapViewData != null ? mapViewData.hashCode() : 0);
    }

    public String toString() {
        return "MapItemInfo(name=" + this.name + ", type=" + this.type + ", destinationType=" + this.destinationType + ", isCheck=" + this.isCheck + ", isExpand=" + this.isExpand + ", mapData=" + this.mapData + ")";
    }

    public MapItemInfo(String name, int i, DestinationType destinationType, boolean z, boolean z2, MapViewData mapViewData) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.name = name;
        this.type = i;
        this.destinationType = destinationType;
        this.isCheck = z;
        this.isExpand = z2;
        this.mapData = mapViewData;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    public final int getType() {
        return this.type;
    }

    public final void setType(int i) {
        this.type = i;
    }

    public /* synthetic */ MapItemInfo(String str, int i, DestinationType destinationType, boolean z, boolean z2, MapViewData mapViewData, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, (i2 & 4) != 0 ? (DestinationType) null : destinationType, (i2 & 8) != 0 ? false : z, (i2 & 16) != 0 ? false : z2, (i2 & 32) != 0 ? (MapViewData) null : mapViewData);
    }

    public final DestinationType getDestinationType() {
        return this.destinationType;
    }

    public final void setDestinationType(DestinationType destinationType) {
        this.destinationType = destinationType;
    }

    public final boolean isCheck() {
        return this.isCheck;
    }

    public final void setCheck(boolean z) {
        this.isCheck = z;
    }

    public final boolean isExpand() {
        return this.isExpand;
    }

    public final void setExpand(boolean z) {
        this.isExpand = z;
    }

    public final MapViewData getMapData() {
        return this.mapData;
    }

    public final void setMapData(MapViewData mapViewData) {
        this.mapData = mapViewData;
    }
}
