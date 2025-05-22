package com.pudutech.freeinstall_ui.adapter;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EditMapAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001e\b\u0086\b\u0018\u0000 $2\u00020\u0001:\u0001$B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007¢\u0006\u0002\u0010\nJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J;\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u0007HÆ\u0001J\u0013\u0010 \u001a\u00020\u00072\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020\u0005HÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\t\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000b\"\u0004\b\u0017\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0013\"\u0004\b\u0019\u0010\u0015¨\u0006%"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/adapter/MapPointItem;", "", "name", "", "type", "", "isSetting", "", "pointType", "titleMust", "(Ljava/lang/String;IZIZ)V", "()Z", "setSetting", "(Z)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getPointType", "()I", "setPointType", "(I)V", "getTitleMust", "setTitleMust", "getType", "setType", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "Companion", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final /* data */ class MapPointItem {
    public static final int TYPE_CHARGE_DILL = 6;
    public static final int TYPE_CREATE_MAP_EXPAND = 7;
    public static final int TYPE_CRUISE = 4;
    public static final int TYPE_DOOR = 2;
    public static final int TYPE_MEAL = 1;
    public static final int TYPE_STATION = 5;
    public static final int TYPE_TABLE = 3;
    private boolean isSetting;
    private String name;
    private int pointType;
    private boolean titleMust;
    private int type;

    public static /* synthetic */ MapPointItem copy$default(MapPointItem mapPointItem, String str, int i, boolean z, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = mapPointItem.name;
        }
        if ((i3 & 2) != 0) {
            i = mapPointItem.type;
        }
        int i4 = i;
        if ((i3 & 4) != 0) {
            z = mapPointItem.isSetting;
        }
        boolean z3 = z;
        if ((i3 & 8) != 0) {
            i2 = mapPointItem.pointType;
        }
        int i5 = i2;
        if ((i3 & 16) != 0) {
            z2 = mapPointItem.titleMust;
        }
        return mapPointItem.copy(str, i4, z3, i5, z2);
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
    public final boolean getIsSetting() {
        return this.isSetting;
    }

    /* renamed from: component4, reason: from getter */
    public final int getPointType() {
        return this.pointType;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getTitleMust() {
        return this.titleMust;
    }

    public final MapPointItem copy(String name, int type, boolean isSetting, int pointType, boolean titleMust) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new MapPointItem(name, type, isSetting, pointType, titleMust);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MapPointItem)) {
            return false;
        }
        MapPointItem mapPointItem = (MapPointItem) other;
        return Intrinsics.areEqual(this.name, mapPointItem.name) && this.type == mapPointItem.type && this.isSetting == mapPointItem.isSetting && this.pointType == mapPointItem.pointType && this.titleMust == mapPointItem.titleMust;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.name;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + Integer.hashCode(this.type)) * 31;
        boolean z = this.isSetting;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int hashCode2 = (((hashCode + i) * 31) + Integer.hashCode(this.pointType)) * 31;
        boolean z2 = this.titleMust;
        int i2 = z2;
        if (z2 != 0) {
            i2 = 1;
        }
        return hashCode2 + i2;
    }

    public String toString() {
        return "MapPointItem(name=" + this.name + ", type=" + this.type + ", isSetting=" + this.isSetting + ", pointType=" + this.pointType + ", titleMust=" + this.titleMust + ")";
    }

    public MapPointItem(String name, int i, boolean z, int i2, boolean z2) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.name = name;
        this.type = i;
        this.isSetting = z;
        this.pointType = i2;
        this.titleMust = z2;
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

    public final boolean isSetting() {
        return this.isSetting;
    }

    public final void setSetting(boolean z) {
        this.isSetting = z;
    }

    public final int getPointType() {
        return this.pointType;
    }

    public final void setPointType(int i) {
        this.pointType = i;
    }

    public /* synthetic */ MapPointItem(String str, int i, boolean z, int i2, boolean z2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, (i3 & 4) != 0 ? false : z, (i3 & 8) != 0 ? 0 : i2, (i3 & 16) != 0 ? false : z2);
    }

    public final boolean getTitleMust() {
        return this.titleMust;
    }

    public final void setTitleMust(boolean z) {
        this.titleMust = z;
    }
}
