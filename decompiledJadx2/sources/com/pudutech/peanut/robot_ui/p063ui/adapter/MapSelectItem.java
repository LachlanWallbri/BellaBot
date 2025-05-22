package com.pudutech.peanut.robot_ui.p063ui.adapter;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MapSelectAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0007\"\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/MapSelectItem;", "", "mapName", "", "isSelect", "", "(Ljava/lang/String;Z)V", "()Z", "setSelect", "(Z)V", "getMapName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class MapSelectItem {
    private boolean isSelect;
    private final String mapName;

    public static /* synthetic */ MapSelectItem copy$default(MapSelectItem mapSelectItem, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = mapSelectItem.mapName;
        }
        if ((i & 2) != 0) {
            z = mapSelectItem.isSelect;
        }
        return mapSelectItem.copy(str, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMapName() {
        return this.mapName;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    public final MapSelectItem copy(String mapName, boolean isSelect) {
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        return new MapSelectItem(mapName, isSelect);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MapSelectItem)) {
            return false;
        }
        MapSelectItem mapSelectItem = (MapSelectItem) other;
        return Intrinsics.areEqual(this.mapName, mapSelectItem.mapName) && this.isSelect == mapSelectItem.isSelect;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.mapName;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        boolean z = this.isSelect;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public String toString() {
        return "MapSelectItem(mapName=" + this.mapName + ", isSelect=" + this.isSelect + ")";
    }

    public MapSelectItem(String mapName, boolean z) {
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        this.mapName = mapName;
        this.isSelect = z;
    }

    public /* synthetic */ MapSelectItem(String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? false : z);
    }

    public final String getMapName() {
        return this.mapName;
    }

    public final boolean isSelect() {
        return this.isSelect;
    }

    public final void setSelect(boolean z) {
        this.isSelect = z;
    }
}
