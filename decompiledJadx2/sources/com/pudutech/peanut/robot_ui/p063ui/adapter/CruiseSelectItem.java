package com.pudutech.peanut.robot_ui.p063ui.adapter;

import com.pudutech.mirsdkwrap.lib.map.MapCruiseLine;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CruiseSelectAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0007\"\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/CruiseSelectItem;", "", "mapModel", "Lcom/pudutech/mirsdkwrap/lib/map/MapCruiseLine;", "isSelect", "", "(Lcom/pudutech/mirsdkwrap/lib/map/MapCruiseLine;Z)V", "()Z", "setSelect", "(Z)V", "getMapModel", "()Lcom/pudutech/mirsdkwrap/lib/map/MapCruiseLine;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class CruiseSelectItem {
    private boolean isSelect;
    private final MapCruiseLine mapModel;

    public static /* synthetic */ CruiseSelectItem copy$default(CruiseSelectItem cruiseSelectItem, MapCruiseLine mapCruiseLine, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            mapCruiseLine = cruiseSelectItem.mapModel;
        }
        if ((i & 2) != 0) {
            z = cruiseSelectItem.isSelect;
        }
        return cruiseSelectItem.copy(mapCruiseLine, z);
    }

    /* renamed from: component1, reason: from getter */
    public final MapCruiseLine getMapModel() {
        return this.mapModel;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    public final CruiseSelectItem copy(MapCruiseLine mapModel, boolean isSelect) {
        Intrinsics.checkParameterIsNotNull(mapModel, "mapModel");
        return new CruiseSelectItem(mapModel, isSelect);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CruiseSelectItem)) {
            return false;
        }
        CruiseSelectItem cruiseSelectItem = (CruiseSelectItem) other;
        return Intrinsics.areEqual(this.mapModel, cruiseSelectItem.mapModel) && this.isSelect == cruiseSelectItem.isSelect;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        MapCruiseLine mapCruiseLine = this.mapModel;
        int hashCode = (mapCruiseLine != null ? mapCruiseLine.hashCode() : 0) * 31;
        boolean z = this.isSelect;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public String toString() {
        return "CruiseSelectItem(mapModel=" + this.mapModel + ", isSelect=" + this.isSelect + ")";
    }

    public CruiseSelectItem(MapCruiseLine mapModel, boolean z) {
        Intrinsics.checkParameterIsNotNull(mapModel, "mapModel");
        this.mapModel = mapModel;
        this.isSelect = z;
    }

    public /* synthetic */ CruiseSelectItem(MapCruiseLine mapCruiseLine, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(mapCruiseLine, (i & 2) != 0 ? false : z);
    }

    public final MapCruiseLine getMapModel() {
        return this.mapModel;
    }

    public final boolean isSelect() {
        return this.isSelect;
    }

    public final void setSelect(boolean z) {
        this.isSelect = z;
    }
}
