package com.pudutech.mirsdkwrap.lib.map;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdk.config.MapFilePathConfig;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MapCruisePathLoader.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0016\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fJ\u0016\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u00100\nj\b\u0012\u0004\u0012\u00020\u0010`\fJ\r\u0010\u001e\u001a\u00020\u001fH\u0000¢\u0006\u0002\b J \u0010!\u001a\u0012\u0012\u0004\u0012\u00020\u00100\nj\b\u0012\u0004\u0012\u00020\u0010`\f2\u0006\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020\u001fH\u0003J\u0010\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&H\u0002J\u0014\u0010(\u001a\u00020)*\u00020&2\u0006\u0010*\u001a\u00020&H\u0002J \u0010+\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020)0,*\u00020&2\u0006\u0010-\u001a\u00020\u0010H\u0002J\u001e\u0010.\u001a\u0004\u0018\u00010/*\u00020&2\u0006\u00100\u001a\u00020\u00032\u0006\u00101\u001a\u00020\u000bH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082D¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00100\nj\b\u0012\u0004\u0012\u00020\u0010`\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u001e\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u001e\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u001e\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015¨\u00062"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/map/MapCruisePathLoader;", "", "mapName", "", "dataPath", "(Ljava/lang/String;Ljava/lang/String;)V", "SCALE", "", "TAG", "cruiseLines", "Ljava/util/ArrayList;", "Lcom/pudutech/mirsdkwrap/lib/map/MapCruiseLine;", "Lkotlin/collections/ArrayList;", "getDataPath", "()Ljava/lang/String;", "mapLines", "Lcom/pudutech/mirsdkwrap/lib/map/MapLine;", "getMapName", "<set-?>", "maxX", "getMaxX", "()I", "maxY", "getMaxY", "minX", "getMinX", "minY", "getMinY", "getCruiseLines", "getMapLines", "load", "", "load$module_robot_mirsdk_wrapper_release", "parseLines", "allLines", "Lorg/json/JSONArray;", "parseStayPoint", "sketchMapScale", "", "doubleArray", "distance2D", "", "point", "getClosestPoint", "Lkotlin/Pair;", "line", "getClosestStopDot", "Lcom/pudutech/mirsdkwrap/lib/map/StayPoint;", "name", MapElement.Key.MAP, "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MapCruisePathLoader {
    private final int SCALE;
    private final String TAG;
    private final ArrayList<MapCruiseLine> cruiseLines;
    private final String dataPath;
    private ArrayList<MapLine> mapLines;
    private final String mapName;
    private int maxX;
    private int maxY;
    private int minX;
    private int minY;

    public MapCruisePathLoader(String mapName, String dataPath) {
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        Intrinsics.checkParameterIsNotNull(dataPath, "dataPath");
        this.mapName = mapName;
        this.dataPath = dataPath;
        this.TAG = "MapCruisePathLoader";
        this.SCALE = 50;
        this.maxX = 5;
        this.minX = -5;
        this.maxY = 5;
        this.minY = -5;
        this.cruiseLines = new ArrayList<>();
        this.mapLines = new ArrayList<>();
    }

    public final String getMapName() {
        return this.mapName;
    }

    public final String getDataPath() {
        return this.dataPath;
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

    public final ArrayList<MapCruiseLine> getCruiseLines() {
        ArrayList<MapCruiseLine> arrayList = this.cruiseLines;
        ArrayList<MapCruiseLine> arrayList2 = new ArrayList<>(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator<T> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(MapCruiseLine.copy$default((MapCruiseLine) it.next(), null, null, null, null, 15, null));
        }
        return arrayList2;
    }

    public final void load$module_robot_mirsdk_wrapper_release() {
        String str;
        File file = new File(this.dataPath, MapFilePathConfig.MAP_DATA);
        if (!file.exists()) {
            Pdlog.m3274e(this.TAG, this.mapName + " not exists : " + this.dataPath);
            throw new IOException("path is wrong");
        }
        Pdlog.m3273d(this.TAG, "parse file. " + file.getAbsolutePath());
        String readText$default = FilesKt.readText$default(file, null, 1, null);
        if (readText$default.length() == 0) {
            Pdlog.m3277w(this.TAG, file + " is empty");
        }
        JSONObject jSONObject = new JSONObject(readText$default);
        JSONArray allLines = jSONObject.getJSONArray("AllLines");
        JSONArray jSONArray = jSONObject.getJSONArray("MapModules");
        JSONObject jSONObject2 = jSONObject.getJSONObject("XY");
        Pdlog.m3273d(this.TAG, "load jsonMap = " + jSONObject);
        Pdlog.m3273d(this.TAG, "load jsonMapModule = " + jSONArray);
        Pdlog.m3273d(this.TAG, "load jsonXY = " + jSONObject2);
        this.cruiseLines.clear();
        Pdlog.m3273d(this.TAG, "load parseStayPoint");
        int length = jSONArray.length();
        int i = 0;
        while (i < length) {
            JSONArray array = jSONArray.getJSONObject(i).getJSONArray("Map");
            String valueOf = String.valueOf(i);
            try {
                str = jSONArray.getJSONObject(i).getString("id");
                Intrinsics.checkExpressionValueIsNotNull(str, "jsonMapModule.getJSONObject(i).getString(\"id\")");
                try {
                    Pdlog.m3273d(this.TAG, "id = " + str);
                } catch (Exception e) {
                    e = e;
                    Pdlog.m3274e(this.TAG, "no id. " + e);
                    String str2 = str;
                    ArrayList arrayList = new ArrayList();
                    Intrinsics.checkExpressionValueIsNotNull(array, "array");
                    arrayList.addAll(parseLines(array));
                    i++;
                    this.cruiseLines.add(new MapCruiseLine(String.valueOf(i), arrayList, str2, null, 8, null));
                }
            } catch (Exception e2) {
                e = e2;
                str = valueOf;
            }
            String str22 = str;
            ArrayList arrayList2 = new ArrayList();
            try {
                Intrinsics.checkExpressionValueIsNotNull(array, "array");
                arrayList2.addAll(parseLines(array));
            } catch (Exception e3) {
                Pdlog.m3274e(this.TAG, "parse fail! " + e3);
            }
            i++;
            this.cruiseLines.add(new MapCruiseLine(String.valueOf(i), arrayList2, str22, null, 8, null));
        }
        ArrayList<MapCruiseLine> arrayList3 = this.cruiseLines;
        if (arrayList3.size() > 1) {
            CollectionsKt.sortWith(arrayList3, new Comparator<T>() { // from class: com.pudutech.mirsdkwrap.lib.map.MapCruisePathLoader$load$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(((MapCruiseLine) t).getId(), ((MapCruiseLine) t2).getId());
                }
            });
        }
        Pdlog.m3273d(this.TAG, "load cruiseLines = " + this.cruiseLines);
        this.maxX = jSONObject2.getInt("maxX");
        this.minX = jSONObject2.getInt("minX");
        this.maxY = jSONObject2.getInt("maxY");
        this.minY = jSONObject2.getInt("minY");
        this.mapLines.clear();
        try {
            ArrayList<MapLine> arrayList4 = this.mapLines;
            Intrinsics.checkExpressionValueIsNotNull(allLines, "allLines");
            arrayList4.addAll(parseLines(allLines));
            parseStayPoint();
        } catch (Exception e4) {
            Pdlog.m3274e(this.TAG, ' ' + this.mapName + " : parse fail! " + e4);
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

    private final void parseStayPoint() {
        Pdlog.m3273d(this.TAG, "parseTable ");
        final HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        File file = new File(this.dataPath, "ATLAS_DATA");
        if (!file.exists()) {
            Pdlog.m3277w(this.TAG, file + " not exists");
            return;
        }
        Pdlog.m3275i(this.TAG, "parse file. " + file.getAbsolutePath());
        String readText = TextStreamsKt.readText(new FileReader(file));
        String str = readText;
        if (str == null || str.length() == 0) {
            Pdlog.m3277w(this.TAG, file + " is empty");
        }
        Pdlog.m3273d(this.TAG, "parseStayPoint = " + readText);
        JSONArray jSONArray = new JSONObject(readText).getJSONObject(MapElement.Key.MAP).getJSONArray(MapElement.Key.ELEMENTS);
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String id = jSONObject.getString("id");
                String string = jSONObject.getString("type");
                if (string != null) {
                    int hashCode = string.hashCode();
                    if (hashCode != -1360216880) {
                        if (hashCode == -896505829 && string.equals(MapElement.Source.SOURCE)) {
                            JSONArray jSONArray2 = jSONObject.getJSONArray(MapElement.Key.VECTOR);
                            int length2 = jSONArray2.length();
                            double[] dArr = new double[length2];
                            for (int i2 = 0; i2 < length2; i2++) {
                                dArr[i2] = jSONArray2.getDouble(i2);
                            }
                            Intrinsics.checkExpressionValueIsNotNull(id, "id");
                            hashMap.put(id, dArr);
                        }
                    } else if (string.equals("circle")) {
                        JSONArray jSONArray3 = jSONObject.getJSONArray("idList");
                        int length3 = jSONArray3.length();
                        String[] strArr = new String[length3];
                        for (int i3 = 0; i3 < length3; i3++) {
                            String string2 = jSONArray3.getString(i3);
                            Intrinsics.checkExpressionValueIsNotNull(string2, "it.getString(i)");
                            strArr[i3] = string2;
                        }
                        Intrinsics.checkExpressionValueIsNotNull(id, "id");
                        hashMap2.put(Integer.valueOf(Integer.parseInt(id)), strArr);
                        Pdlog.m3273d(this.TAG, "parse stops. " + id + " stops.size=" + strArr.length);
                    }
                }
            } catch (Exception e) {
                Pdlog.m3277w(this.TAG, String.valueOf(e));
            }
        }
        Pdlog.m3273d(this.TAG, "parse result tables.size=" + hashMap.size());
        hashMap2.forEach(new BiConsumer<Integer, String[]>() { // from class: com.pudutech.mirsdkwrap.lib.map.MapCruisePathLoader$parseStayPoint$3
            @Override // java.util.function.BiConsumer
            public final void accept(Integer cruiseId, String[] idList) {
                StayPoint stayPoint;
                ArrayList arrayList;
                double[] sketchMapScale;
                Intrinsics.checkParameterIsNotNull(cruiseId, "cruiseId");
                Intrinsics.checkParameterIsNotNull(idList, "idList");
                int length4 = idList.length;
                StayPoint[] stayPointArr = new StayPoint[length4];
                MapCruiseLine mapCruiseLine = (MapCruiseLine) null;
                for (int i4 = 0; i4 < length4; i4++) {
                    String str2 = idList[i4];
                    double[] it = (double[]) hashMap.get(str2);
                    if (it != null && it.length > 2) {
                        arrayList = MapCruisePathLoader.this.cruiseLines;
                        ArrayList arrayList2 = new ArrayList();
                        for (T t : arrayList) {
                            if (Intrinsics.areEqual(((MapCruiseLine) t).getId(), String.valueOf(cruiseId.intValue()))) {
                                arrayList2.add(t);
                            }
                        }
                        ArrayList arrayList3 = arrayList2;
                        if (!arrayList3.isEmpty()) {
                            mapCruiseLine = (MapCruiseLine) arrayList3.get(0);
                            MapCruisePathLoader mapCruisePathLoader = MapCruisePathLoader.this;
                            Intrinsics.checkExpressionValueIsNotNull(it, "it");
                            sketchMapScale = mapCruisePathLoader.sketchMapScale(it);
                            stayPoint = mapCruisePathLoader.getClosestStopDot(sketchMapScale, str2, (MapCruiseLine) arrayList3.get(0));
                            stayPointArr[i4] = stayPoint;
                        }
                    }
                    stayPoint = null;
                    stayPointArr[i4] = stayPoint;
                }
                if (mapCruiseLine != null) {
                    List mutableList = CollectionsKt.toMutableList((Collection) ArraysKt.filterNotNull(stayPointArr));
                    if (mutableList == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.ArrayList<com.pudutech.mirsdkwrap.lib.map.StayPoint> /* = java.util.ArrayList<com.pudutech.mirsdkwrap.lib.map.StayPoint> */");
                    }
                    mapCruiseLine.setStayPoint((ArrayList) mutableList);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final StayPoint getClosestStopDot(double[] dArr, String str, MapCruiseLine mapCruiseLine) {
        double max_value = DoubleCompanionObject.INSTANCE.getMAX_VALUE();
        StayPoint stayPoint = (StayPoint) null;
        Iterator<T> it = mapCruiseLine.getLines().iterator();
        while (it.hasNext()) {
            Pair<double[], Double> closestPoint = getClosestPoint(dArr, (MapLine) it.next());
            if (closestPoint.getSecond().doubleValue() < max_value) {
                stayPoint = new StayPoint(str, closestPoint.getFirst()[0], closestPoint.getFirst()[1]);
                max_value = closestPoint.getSecond().doubleValue();
            }
        }
        return stayPoint;
    }

    private final Pair<double[], Double> getClosestPoint(double[] dArr, MapLine mapLine) {
        double[] dArr2 = {mapLine.getStartX(), mapLine.getStartY()};
        double[] dArr3 = {mapLine.getEndX(), mapLine.getEndY()};
        int endY = mapLine.getEndY() - mapLine.getStartY();
        int startX = mapLine.getStartX() - mapLine.getEndX();
        int endX = (mapLine.getEndX() * mapLine.getStartY()) - (mapLine.getStartX() * mapLine.getEndY());
        if (endY == 0 && startX == 0) {
            Pdlog.m3277w(this.TAG, "isn't a line. " + mapLine);
            return new Pair<>(dArr2, Double.valueOf(distance2D(dArr, dArr2)));
        }
        double d = dArr[0];
        double d2 = dArr[1];
        int i = endY * endY;
        int i2 = startX * startX;
        double d3 = endY * startX;
        double d4 = i + i2;
        double[] dArr4 = {(((i2 * d) - (d3 * d2)) - (endY * endX)) / d4, (((i * d2) - (d3 * d)) - (startX * endX)) / d4};
        double distance2D = distance2D(dArr2, dArr3);
        if (distance2D(dArr4, dArr2) > distance2D || distance2D(dArr4, dArr3) > distance2D) {
            double distance2D2 = distance2D(dArr, dArr2);
            double distance2D3 = distance2D(dArr, dArr3);
            if (distance2D2 > distance2D2) {
                return new Pair<>(dArr2, Double.valueOf(distance2D2));
            }
            return new Pair<>(dArr3, Double.valueOf(distance2D3));
        }
        return new Pair<>(dArr4, Double.valueOf(distance2D(dArr, dArr4)));
    }

    private final double distance2D(double[] dArr, double[] dArr2) {
        return Math.sqrt(((dArr2[0] - dArr[0]) * (dArr2[0] - dArr[0])) + ((dArr2[1] - dArr[1]) * (dArr2[1] - dArr[1])));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double[] sketchMapScale(double[] doubleArray) {
        int length = doubleArray.length;
        double[] dArr = new double[length];
        for (int i = 0; i < length; i++) {
            dArr[i] = (doubleArray[i] * 100) / this.SCALE;
        }
        return dArr;
    }
}
