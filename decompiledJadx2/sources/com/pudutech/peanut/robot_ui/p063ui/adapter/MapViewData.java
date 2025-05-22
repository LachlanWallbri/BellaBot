package com.pudutech.peanut.robot_ui.p063ui.adapter;

import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdkwrap.lib.map.MapCruiseLine;
import com.pudutech.mirsdkwrap.lib.map.MapLine;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MapListAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n\u0012\u001c\b\u0002\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\f\u0018\u00010\bj\n\u0012\u0004\u0012\u00020\f\u0018\u0001`\n¢\u0006\u0002\u0010\rJ\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\u0019\u0010\"\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nHÆ\u0003J\u001d\u0010#\u001a\u0016\u0012\u0004\u0012\u00020\f\u0018\u00010\bj\n\u0012\u0004\u0012\u00020\f\u0018\u0001`\nHÆ\u0003Ji\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u0018\b\u0002\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n2\u001c\b\u0002\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\f\u0018\u00010\bj\n\u0012\u0004\u0012\u00020\f\u0018\u0001`\nHÆ\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020\u0003HÖ\u0001J\t\u0010)\u001a\u00020*HÖ\u0001R.\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\f\u0018\u00010\bj\n\u0012\u0004\u0012\u00020\f\u0018\u0001`\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R*\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u0017R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0015\"\u0004\b\u001b\u0010\u0017R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017¨\u0006+"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/MapViewData;", "", "maxX", "", "maxY", "minX", "minY", MapElement.Key.MAP, "Ljava/util/ArrayList;", "Lcom/pudutech/mirsdkwrap/lib/map/MapLine;", "Lkotlin/collections/ArrayList;", "lines", "Lcom/pudutech/mirsdkwrap/lib/map/MapCruiseLine;", "(IIIILjava/util/ArrayList;Ljava/util/ArrayList;)V", "getLines", "()Ljava/util/ArrayList;", "setLines", "(Ljava/util/ArrayList;)V", "getMap", "setMap", "getMaxX", "()I", "setMaxX", "(I)V", "getMaxY", "setMaxY", "getMinX", "setMinX", "getMinY", "setMinY", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class MapViewData {
    private ArrayList<MapCruiseLine> lines;
    private ArrayList<MapLine> map;
    private int maxX;
    private int maxY;
    private int minX;
    private int minY;

    public static /* synthetic */ MapViewData copy$default(MapViewData mapViewData, int i, int i2, int i3, int i4, ArrayList arrayList, ArrayList arrayList2, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = mapViewData.maxX;
        }
        if ((i5 & 2) != 0) {
            i2 = mapViewData.maxY;
        }
        int i6 = i2;
        if ((i5 & 4) != 0) {
            i3 = mapViewData.minX;
        }
        int i7 = i3;
        if ((i5 & 8) != 0) {
            i4 = mapViewData.minY;
        }
        int i8 = i4;
        if ((i5 & 16) != 0) {
            arrayList = mapViewData.map;
        }
        ArrayList arrayList3 = arrayList;
        if ((i5 & 32) != 0) {
            arrayList2 = mapViewData.lines;
        }
        return mapViewData.copy(i, i6, i7, i8, arrayList3, arrayList2);
    }

    /* renamed from: component1, reason: from getter */
    public final int getMaxX() {
        return this.maxX;
    }

    /* renamed from: component2, reason: from getter */
    public final int getMaxY() {
        return this.maxY;
    }

    /* renamed from: component3, reason: from getter */
    public final int getMinX() {
        return this.minX;
    }

    /* renamed from: component4, reason: from getter */
    public final int getMinY() {
        return this.minY;
    }

    public final ArrayList<MapLine> component5() {
        return this.map;
    }

    public final ArrayList<MapCruiseLine> component6() {
        return this.lines;
    }

    public final MapViewData copy(int maxX, int maxY, int minX, int minY, ArrayList<MapLine> map, ArrayList<MapCruiseLine> lines) {
        Intrinsics.checkParameterIsNotNull(map, "map");
        return new MapViewData(maxX, maxY, minX, minY, map, lines);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MapViewData)) {
            return false;
        }
        MapViewData mapViewData = (MapViewData) other;
        return this.maxX == mapViewData.maxX && this.maxY == mapViewData.maxY && this.minX == mapViewData.minX && this.minY == mapViewData.minY && Intrinsics.areEqual(this.map, mapViewData.map) && Intrinsics.areEqual(this.lines, mapViewData.lines);
    }

    public int hashCode() {
        int hashCode = ((((((Integer.hashCode(this.maxX) * 31) + Integer.hashCode(this.maxY)) * 31) + Integer.hashCode(this.minX)) * 31) + Integer.hashCode(this.minY)) * 31;
        ArrayList<MapLine> arrayList = this.map;
        int hashCode2 = (hashCode + (arrayList != null ? arrayList.hashCode() : 0)) * 31;
        ArrayList<MapCruiseLine> arrayList2 = this.lines;
        return hashCode2 + (arrayList2 != null ? arrayList2.hashCode() : 0);
    }

    public String toString() {
        return "MapViewData(maxX=" + this.maxX + ", maxY=" + this.maxY + ", minX=" + this.minX + ", minY=" + this.minY + ", map=" + this.map + ", lines=" + this.lines + ")";
    }

    public MapViewData(int i, int i2, int i3, int i4, ArrayList<MapLine> map, ArrayList<MapCruiseLine> arrayList) {
        Intrinsics.checkParameterIsNotNull(map, "map");
        this.maxX = i;
        this.maxY = i2;
        this.minX = i3;
        this.minY = i4;
        this.map = map;
        this.lines = arrayList;
    }

    public final int getMaxX() {
        return this.maxX;
    }

    public final void setMaxX(int i) {
        this.maxX = i;
    }

    public final int getMaxY() {
        return this.maxY;
    }

    public final void setMaxY(int i) {
        this.maxY = i;
    }

    public final int getMinX() {
        return this.minX;
    }

    public final void setMinX(int i) {
        this.minX = i;
    }

    public final int getMinY() {
        return this.minY;
    }

    public final void setMinY(int i) {
        this.minY = i;
    }

    public final ArrayList<MapLine> getMap() {
        return this.map;
    }

    public final void setMap(ArrayList<MapLine> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.map = arrayList;
    }

    public /* synthetic */ MapViewData(int i, int i2, int i3, int i4, ArrayList arrayList, ArrayList arrayList2, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, i3, i4, arrayList, (i5 & 32) != 0 ? (ArrayList) null : arrayList2);
    }

    public final ArrayList<MapCruiseLine> getLines() {
        return this.lines;
    }

    public final void setLines(ArrayList<MapCruiseLine> arrayList) {
        this.lines = arrayList;
    }
}
