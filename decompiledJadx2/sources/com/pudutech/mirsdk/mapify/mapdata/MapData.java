package com.pudutech.mirsdk.mapify.mapdata;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MapData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005B\u0007\b\u0016¢\u0006\u0002\u0010\u0006R&\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0005R&\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\n\"\u0004\b\r\u0010\u0005R&\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\u0005R$\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\n\"\u0004\b\u0014\u0010\u0005R \u0010\u0015\u001a\u0004\u0018\u00010\u00168\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR \u0010\u001b\u001a\u0004\u0018\u00010\u001c8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006!"}, m3961d2 = {"Lcom/pudutech/mirsdk/mapify/mapdata/MapData;", "", "background", "", "", "(Ljava/util/List;)V", "()V", "allLines", "Lcom/pudutech/mirsdk/mapify/mapdata/AllLines;", "getAllLines", "()Ljava/util/List;", "setAllLines", "getBackground", "setBackground", "dotLines", "getDotLines", "setDotLines", "mapModules", "Lcom/pudutech/mirsdk/mapify/mapdata/MapModules;", "getMapModules", "setMapModules", "mapinfo", "Lcom/pudutech/mirsdk/mapify/mapdata/Mapinfo;", "getMapinfo", "()Lcom/pudutech/mirsdk/mapify/mapdata/Mapinfo;", "setMapinfo", "(Lcom/pudutech/mirsdk/mapify/mapdata/Mapinfo;)V", "xy", "Lcom/pudutech/mirsdk/mapify/mapdata/XY;", "getXy", "()Lcom/pudutech/mirsdk/mapify/mapdata/XY;", "setXy", "(Lcom/pudutech/mirsdk/mapify/mapdata/XY;)V", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MapData {

    @SerializedName("AllLines")
    private List<AllLines> allLines;

    @SerializedName("Background")
    private List<String> background;

    @SerializedName("DotLines")
    private List<String> dotLines;

    @SerializedName("MapModules")
    private List<MapModules> mapModules = new ArrayList();

    @SerializedName("Mapinfo")
    private Mapinfo mapinfo;

    @SerializedName("XY")
    private C5206XY xy;

    public final List<String> getBackground() {
        return this.background;
    }

    public final void setBackground(List<String> list) {
        this.background = list;
    }

    public final List<MapModules> getMapModules() {
        return this.mapModules;
    }

    public final void setMapModules(List<MapModules> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.mapModules = list;
    }

    public final List<AllLines> getAllLines() {
        return this.allLines;
    }

    public final void setAllLines(List<AllLines> list) {
        this.allLines = list;
    }

    public final List<String> getDotLines() {
        return this.dotLines;
    }

    public final void setDotLines(List<String> list) {
        this.dotLines = list;
    }

    public final Mapinfo getMapinfo() {
        return this.mapinfo;
    }

    public final void setMapinfo(Mapinfo mapinfo) {
        this.mapinfo = mapinfo;
    }

    public final C5206XY getXy() {
        return this.xy;
    }

    public final void setXy(C5206XY c5206xy) {
        this.xy = c5206xy;
    }

    public MapData(List<String> list) {
        this.background = list;
    }

    public MapData() {
    }
}
