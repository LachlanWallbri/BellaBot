package com.pudutech.mirsdkwrap.lib.map;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.config.MapFilePathConfig;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MapDrawPathLoader.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0016\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fJ\u0013\u0010\u001a\u001a\u00020\u001bH\u0080@ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ \u0010\u001e\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\f2\u0006\u0010\u001f\u001a\u00020 H\u0002R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\bR\u001e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u001e\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u001e\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/map/MapDrawPathLoader;", "", "mapName", "", "mapDataPath", "(Ljava/lang/String;Ljava/lang/String;)V", "TAG", "getMapDataPath$module_robot_mirsdk_wrapper_release", "()Ljava/lang/String;", "mapLines", "Ljava/util/ArrayList;", "Lcom/pudutech/mirsdkwrap/lib/map/MapLine;", "Lkotlin/collections/ArrayList;", "getMapName", "<set-?>", "", "maxX", "getMaxX", "()I", "maxY", "getMaxY", "minX", "getMinX", "minY", "getMinY", "getMapLines", "loadMap", "", "loadMap$module_robot_mirsdk_wrapper_release", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "parseLines", "allLines", "Lorg/json/JSONArray;", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MapDrawPathLoader {
    private final String TAG;
    private final String mapDataPath;
    private ArrayList<MapLine> mapLines;
    private final String mapName;
    private int maxX;
    private int maxY;
    private int minX;
    private int minY;

    public MapDrawPathLoader(String mapName, String mapDataPath) {
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        Intrinsics.checkParameterIsNotNull(mapDataPath, "mapDataPath");
        this.mapName = mapName;
        this.mapDataPath = mapDataPath;
        this.TAG = "MapDrawPathLoader";
        this.maxX = 5;
        this.minX = -5;
        this.maxY = 5;
        this.minY = -5;
        this.mapLines = new ArrayList<>();
    }

    /* renamed from: getMapDataPath$module_robot_mirsdk_wrapper_release, reason: from getter */
    public final String getMapDataPath() {
        return this.mapDataPath;
    }

    public final String getMapName() {
        return this.mapName;
    }

    public final int getMaxX() {
        return this.maxX;
    }

    public final int getMinX() {
        return this.minX;
    }

    public final int getMaxY() {
        return this.maxY;
    }

    public final int getMinY() {
        return this.minY;
    }

    public final ArrayList<MapLine> getMapLines() {
        ArrayList<MapLine> arrayList = this.mapLines;
        ArrayList<MapLine> arrayList2 = new ArrayList<>(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator<T> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(MapLine.copy$default((MapLine) it.next(), 0, 0, 0, 0, 15, null));
        }
        return arrayList2;
    }

    public final Object loadMap$module_robot_mirsdk_wrapper_release(Continuation<? super Unit> continuation) {
        File file = new File(this.mapDataPath, MapFilePathConfig.MAP_DATA);
        if (!file.exists()) {
            Pdlog.m3274e(this.TAG, "loadMap : " + this.mapName + " not exists : " + this.mapDataPath + ' ');
            throw new IOException("path is wrong");
        }
        String readText$default = FilesKt.readText$default(file, null, 1, null);
        String str = readText$default;
        if (str == null || str.length() == 0) {
            Pdlog.m3277w(this.TAG, this.mapDataPath + " is empty");
        }
        JSONObject jSONObject = new JSONObject(readText$default);
        JSONArray allLines = jSONObject.getJSONArray("AllLines");
        jSONObject.getJSONArray("MapModules");
        JSONObject jSONObject2 = jSONObject.getJSONObject("XY");
        this.mapLines.clear();
        try {
            ArrayList<MapLine> arrayList = this.mapLines;
            Intrinsics.checkExpressionValueIsNotNull(allLines, "allLines");
            arrayList.addAll(parseLines(allLines));
            this.maxX = jSONObject2.getInt("maxX");
            this.minX = jSONObject2.getInt("minX");
            this.maxY = jSONObject2.getInt("maxY");
            this.minY = jSONObject2.getInt("minY");
            return Unit.INSTANCE;
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, ' ' + this.mapName + " : parse fail! " + e);
            throw new IOException("parse map lines failed");
        }
    }

    private final ArrayList<MapLine> parseLines(JSONArray allLines) {
        ArrayList<MapLine> arrayList = new ArrayList<>();
        int length = allLines.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = allLines.getJSONObject(i);
            JSONObject jSONObject2 = jSONObject.getJSONObject("start");
            JSONObject jSONObject3 = jSONObject.getJSONObject("end");
            arrayList.add(new MapLine(jSONObject2.getInt("x"), jSONObject2.getInt("y"), jSONObject3.getInt("x"), jSONObject3.getInt("y")));
        }
        return arrayList;
    }
}
