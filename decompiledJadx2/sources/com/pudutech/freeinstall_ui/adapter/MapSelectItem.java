package com.pudutech.freeinstall_ui.adapter;

import com.pudutech.mirsdk.aidl.serialize.MapInfo;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MapSelectAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J+\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/adapter/MapSelectItem;", "", "mapInfo", "Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;", "mapFile", "Ljava/io/File;", "isSelect", "", "(Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;Ljava/io/File;Z)V", "()Z", "setSelect", "(Z)V", "getMapFile", "()Ljava/io/File;", "setMapFile", "(Ljava/io/File;)V", "getMapInfo", "()Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final /* data */ class MapSelectItem {
    private boolean isSelect;
    private File mapFile;
    private final MapInfo mapInfo;

    public static /* synthetic */ MapSelectItem copy$default(MapSelectItem mapSelectItem, MapInfo mapInfo, File file, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            mapInfo = mapSelectItem.mapInfo;
        }
        if ((i & 2) != 0) {
            file = mapSelectItem.mapFile;
        }
        if ((i & 4) != 0) {
            z = mapSelectItem.isSelect;
        }
        return mapSelectItem.copy(mapInfo, file, z);
    }

    /* renamed from: component1, reason: from getter */
    public final MapInfo getMapInfo() {
        return this.mapInfo;
    }

    /* renamed from: component2, reason: from getter */
    public final File getMapFile() {
        return this.mapFile;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    public final MapSelectItem copy(MapInfo mapInfo, File mapFile, boolean isSelect) {
        return new MapSelectItem(mapInfo, mapFile, isSelect);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MapSelectItem)) {
            return false;
        }
        MapSelectItem mapSelectItem = (MapSelectItem) other;
        return Intrinsics.areEqual(this.mapInfo, mapSelectItem.mapInfo) && Intrinsics.areEqual(this.mapFile, mapSelectItem.mapFile) && this.isSelect == mapSelectItem.isSelect;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        MapInfo mapInfo = this.mapInfo;
        int hashCode = (mapInfo != null ? mapInfo.hashCode() : 0) * 31;
        File file = this.mapFile;
        int hashCode2 = (hashCode + (file != null ? file.hashCode() : 0)) * 31;
        boolean z = this.isSelect;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "MapSelectItem(mapInfo=" + this.mapInfo + ", mapFile=" + this.mapFile + ", isSelect=" + this.isSelect + ")";
    }

    public MapSelectItem(MapInfo mapInfo, File file, boolean z) {
        this.mapInfo = mapInfo;
        this.mapFile = file;
        this.isSelect = z;
    }

    public final File getMapFile() {
        return this.mapFile;
    }

    public final MapInfo getMapInfo() {
        return this.mapInfo;
    }

    public final boolean isSelect() {
        return this.isSelect;
    }

    public final void setMapFile(File file) {
        this.mapFile = file;
    }

    public final void setSelect(boolean z) {
        this.isSelect = z;
    }
}
