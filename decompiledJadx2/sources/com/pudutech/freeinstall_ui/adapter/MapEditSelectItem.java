package com.pudutech.freeinstall_ui.adapter;

import com.pudutech.mirsdk.aidl.serialize.MapInfo;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MapEditSelectAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/adapter/MapEditSelectItem;", "", "mapInfo", "Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;", "mapFile", "Ljava/io/File;", "(Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;Ljava/io/File;)V", "getMapFile", "()Ljava/io/File;", "setMapFile", "(Ljava/io/File;)V", "getMapInfo", "()Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;", "setMapInfo", "(Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final /* data */ class MapEditSelectItem {
    private File mapFile;
    private MapInfo mapInfo;

    public static /* synthetic */ MapEditSelectItem copy$default(MapEditSelectItem mapEditSelectItem, MapInfo mapInfo, File file, int i, Object obj) {
        if ((i & 1) != 0) {
            mapInfo = mapEditSelectItem.mapInfo;
        }
        if ((i & 2) != 0) {
            file = mapEditSelectItem.mapFile;
        }
        return mapEditSelectItem.copy(mapInfo, file);
    }

    /* renamed from: component1, reason: from getter */
    public final MapInfo getMapInfo() {
        return this.mapInfo;
    }

    /* renamed from: component2, reason: from getter */
    public final File getMapFile() {
        return this.mapFile;
    }

    public final MapEditSelectItem copy(MapInfo mapInfo, File mapFile) {
        return new MapEditSelectItem(mapInfo, mapFile);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MapEditSelectItem)) {
            return false;
        }
        MapEditSelectItem mapEditSelectItem = (MapEditSelectItem) other;
        return Intrinsics.areEqual(this.mapInfo, mapEditSelectItem.mapInfo) && Intrinsics.areEqual(this.mapFile, mapEditSelectItem.mapFile);
    }

    public int hashCode() {
        MapInfo mapInfo = this.mapInfo;
        int hashCode = (mapInfo != null ? mapInfo.hashCode() : 0) * 31;
        File file = this.mapFile;
        return hashCode + (file != null ? file.hashCode() : 0);
    }

    public String toString() {
        return "MapEditSelectItem(mapInfo=" + this.mapInfo + ", mapFile=" + this.mapFile + ")";
    }

    public MapEditSelectItem(MapInfo mapInfo, File file) {
        this.mapInfo = mapInfo;
        this.mapFile = file;
    }

    public final File getMapFile() {
        return this.mapFile;
    }

    public final MapInfo getMapInfo() {
        return this.mapInfo;
    }

    public final void setMapFile(File file) {
        this.mapFile = file;
    }

    public final void setMapInfo(MapInfo mapInfo) {
        this.mapInfo = mapInfo;
    }
}
