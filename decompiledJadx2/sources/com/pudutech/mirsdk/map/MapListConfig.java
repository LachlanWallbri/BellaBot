package com.pudutech.mirsdk.map;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MapListConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0012\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\u0002\u0010\tJ\u0011\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0000H\u0096\u0002R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/mirsdk/map/MapListConfig;", "", "floor", "", "findex", "", "defmap", "maps", "", "(Ljava/lang/String;ILjava/lang/String;Ljava/util/List;)V", "getDefmap", "()Ljava/lang/String;", "setDefmap", "(Ljava/lang/String;)V", "getFindex", "()I", "setFindex", "(I)V", "getFloor", "setFloor", "getMaps", "()Ljava/util/List;", "setMaps", "(Ljava/util/List;)V", "compareTo", "other", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MapListConfig implements Comparable<MapListConfig> {
    private String defmap;
    private int findex;
    private String floor;
    private List<String> maps;

    public MapListConfig(String floor, int i, String defmap, List<String> maps) {
        Intrinsics.checkParameterIsNotNull(floor, "floor");
        Intrinsics.checkParameterIsNotNull(defmap, "defmap");
        Intrinsics.checkParameterIsNotNull(maps, "maps");
        this.floor = floor;
        this.findex = i;
        this.defmap = defmap;
        this.maps = maps;
    }

    public /* synthetic */ MapListConfig(String str, int i, String str2, ArrayList arrayList, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, str2, (i2 & 8) != 0 ? new ArrayList() : arrayList);
    }

    public final String getDefmap() {
        return this.defmap;
    }

    public final int getFindex() {
        return this.findex;
    }

    public final String getFloor() {
        return this.floor;
    }

    public final List<String> getMaps() {
        return this.maps;
    }

    public final void setDefmap(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.defmap = str;
    }

    public final void setFindex(int i) {
        this.findex = i;
    }

    public final void setFloor(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.floor = str;
    }

    public final void setMaps(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.maps = list;
    }

    @Override // java.lang.Comparable
    public int compareTo(MapListConfig other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return this.findex - other.findex;
    }
}
