package com.pudutech.mirsdk.mapify.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.CruiseTracks;
import com.pudutech.mirsdk.config.MapFilePathConfig;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.map.StringBase64Kt;
import com.pudutech.mirsdk.mapify.bean.Elements;
import com.pudutech.mirsdk.mapify.mapdata.AllLines;
import com.pudutech.mirsdk.mapify.mapdata.C5206XY;
import com.pudutech.mirsdk.mapify.mapdata.Lines;
import com.pudutech.mirsdk.mapify.mapdata.Map;
import com.pudutech.mirsdk.mapify.mapdata.MapData;
import com.pudutech.mirsdk.mapify.mapdata.MapModules;
import com.pudutech.mirsdk.mapify.mapdata.Mapinfo;
import com.pudutech.mirsdk.mapify.mapdata.Vertex;
import com.pudutech.mirsdk.mircore.coreparcel.Node;
import com.pudutech.mirsdk.mircore.coreparcel.TopoTrack;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: MapManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001(B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0007H\u0002J\u0010\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u0007H\u0002J6\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00062\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00062\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00062\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0006J\"\u0010\"\u001a\u00020\u00152\f\u0010#\u001a\b\u0012\u0004\u0012\u00020!0\u00062\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0006J(\u0010$\u001a\u00020\u00152\b\u0010%\u001a\u0004\u0018\u00010\u00072\u0006\u0010&\u001a\u00020\u00072\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, m3961d2 = {"Lcom/pudutech/mirsdk/mapify/util/MapManager;", "", "()V", "TAG", "", "allVertex", "", "Lcom/pudutech/mirsdk/mapify/mapdata/Vertex;", "alllines", "Lcom/pudutech/mirsdk/mapify/util/MapManager$Line;", "gson", "Lcom/google/gson/Gson;", "kotlin.jvm.PlatformType", "linesAll", "mapbean", "Lcom/pudutech/mirsdk/mapify/mapdata/Map;", "mapdataFile", "Lcom/pudutech/mirsdk/mapify/mapdata/MapData;", "scale", "", "addLinkedVertex", "", "left", "right", "addVertex", "v", "analyCruiseTrack", "Lcom/pudutech/mirsdk/aidl/serialize/CruiseTracks;", "tracks", "Lcom/pudutech/mirsdk/mircore/coreparcel/TopoTrack;", "nodes", "Lcom/pudutech/mirsdk/mircore/coreparcel/Node;", "circleElements", "Lcom/pudutech/mirsdk/mapify/bean/Elements;", "createmapdata", "tracksElements", "goNext", "previous", "last", "lines", "Line", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MapManager {
    private static final String TAG = "MapManager";
    public static final MapManager INSTANCE = new MapManager();
    private static List<Vertex> allVertex = new ArrayList();
    private static List<List<Vertex>> linesAll = CollectionsKt.mutableListOf(CollectionsKt.mutableListOf(new Vertex(0.0d, 0.0d, 3, null)));
    private static Map mapbean = new Map();
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static List<Line> alllines = new ArrayList();
    private static int scale = 50;
    private static MapData mapdataFile = new MapData();

    private MapManager() {
    }

    private final void addVertex(Vertex v) {
        for (Vertex vertex : allVertex) {
            if (vertex.getX() == v.getX() && vertex.getY() == v.getY()) {
                return;
            }
        }
        v.setId(allVertex.size());
        allVertex.add(v);
    }

    private final void goNext(Vertex previous, Vertex last, List<Vertex> lines) {
        Pdlog.m3273d(TAG, "crateMapModules 729");
        for (Vertex vertex : last.getLinkedVertex()) {
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = lines.iterator();
            while (it.hasNext()) {
                arrayList.add((Vertex) it.next());
            }
            if (previous == null || vertex.getX() != previous.getX() || vertex.getY() != previous.getY()) {
                arrayList.add(vertex);
                if (vertex.getIsNode()) {
                    linesAll.add(arrayList);
                } else {
                    INSTANCE.goNext(last, vertex, arrayList);
                }
            }
        }
    }

    private final void addLinkedVertex(Vertex left, Vertex right) {
        int i = 0;
        for (Vertex vertex : allVertex) {
            if (vertex.getX() == left.getX() && vertex.getY() == left.getY()) {
                break;
            } else {
                i++;
            }
        }
        Pdlog.m3273d(TAG, "indexLeft is " + i);
        int i2 = 0;
        for (Vertex vertex2 : allVertex) {
            if (vertex2.getX() == right.getX() && vertex2.getY() == right.getY()) {
                break;
            } else {
                i2++;
            }
        }
        Pdlog.m3273d(TAG, "indexLeft is " + i2);
        for (Vertex vertex3 : left.getLinkedVertex()) {
            if (vertex3.getX() == right.getX() && vertex3.getY() == right.getY()) {
                return;
            }
        }
        Pdlog.m3273d(TAG, "crateMapModules 782");
        allVertex.get(i).getLinkedVertex().add(allVertex.get(i2));
        Pdlog.m3273d(TAG, "crateMapModules 784");
    }

    public final List<CruiseTracks> analyCruiseTrack(List<TopoTrack> tracks, List<Node> nodes, List<Elements> circleElements) {
        List<Elements> circleElements2 = circleElements;
        Intrinsics.checkParameterIsNotNull(tracks, "tracks");
        Intrinsics.checkParameterIsNotNull(nodes, "nodes");
        Intrinsics.checkParameterIsNotNull(circleElements2, "circleElements");
        allVertex.clear();
        linesAll.clear();
        ArrayList arrayList = new ArrayList();
        int i = 1;
        int i2 = 0;
        Pdlog.m3273d(TAG, "crateMapModules");
        ArrayList arrayList2 = new ArrayList();
        for (TopoTrack topoTrack : tracks) {
            Vector3d start_pose = topoTrack.getStart_pose();
            if (start_pose == null) {
                Intrinsics.throwNpe();
            }
            double x = start_pose.getX();
            Vector3d start_pose2 = topoTrack.getStart_pose();
            if (start_pose2 == null) {
                Intrinsics.throwNpe();
            }
            Vertex vertex = new Vertex(x, start_pose2.getY());
            Vector3d end_pose = topoTrack.getEnd_pose();
            if (end_pose == null) {
                Intrinsics.throwNpe();
            }
            double x2 = end_pose.getX();
            Vector3d end_pose2 = topoTrack.getEnd_pose();
            if (end_pose2 == null) {
                Intrinsics.throwNpe();
            }
            arrayList2.add(new Lines(vertex, new Vertex(x2, end_pose2.getY())));
        }
        Pdlog.m3273d(TAG, "tracksTable size is " + arrayList2.size() + " 965");
        if (arrayList2.isEmpty()) {
            Pdlog.m3273d(TAG, "crateMapModules 800");
            return arrayList;
        }
        ArrayList<Vertex> arrayList3 = new ArrayList();
        Pdlog.m3273d(TAG, "topoDatanodes size is " + nodes.size() + "  973");
        for (Node node : nodes) {
            Vertex vertex2 = new Vertex(node.getX(), node.getY());
            vertex2.setNodeId(node.getId());
            arrayList3.add(vertex2);
        }
        Pdlog.m3273d(TAG, "nodesTable size is " + arrayList3.size() + " 979");
        if (arrayList3.isEmpty()) {
            Pdlog.m3273d(TAG, "crateMapModules 981");
            return arrayList;
        }
        ArrayList<Lines> arrayList4 = arrayList2;
        for (Lines lines : arrayList4) {
            INSTANCE.addVertex(lines.getLeft());
            INSTANCE.addVertex(lines.getRight());
        }
        Pdlog.m3273d(TAG, "allVertex size is " + allVertex.size() + " 990");
        if (allVertex.isEmpty()) {
            Pdlog.m3273d(TAG, "crateMapModules 992");
            return arrayList;
        }
        int size = allVertex.size();
        for (int i3 = 0; i3 < size; i3++) {
            for (Vertex vertex3 : arrayList3) {
                if (allVertex.get(i3).getX() == vertex3.getX() && allVertex.get(i3).getY() == vertex3.getY()) {
                    allVertex.get(i3).setNode(true);
                    allVertex.get(i3).setNodeId(vertex3.getNodeId());
                }
            }
        }
        Pdlog.m3273d(TAG, "is node 1005");
        for (Lines lines2 : arrayList4) {
            INSTANCE.addLinkedVertex(lines2.getLeft(), lines2.getRight());
            INSTANCE.addLinkedVertex(lines2.getRight(), lines2.getLeft());
        }
        Pdlog.m3273d(TAG, "is node 1013");
        linesAll.clear();
        for (Vertex vertex4 : allVertex) {
            if (vertex4.getIsNode()) {
                ArrayList arrayList5 = new ArrayList();
                arrayList5.add(vertex4);
                INSTANCE.goNext(null, vertex4, arrayList5);
            }
        }
        ArrayList arrayList6 = new ArrayList();
        Pdlog.m3273d(TAG, "circleElements size is " + circleElements.size() + " 1028");
        for (Elements elements : circleElements2) {
            ArrayList arrayList7 = new ArrayList();
            double[] vector = elements.getVector();
            if (vector != null) {
                for (double d : vector) {
                    arrayList7.add(Integer.valueOf((int) d));
                }
                Unit unit = Unit.INSTANCE;
            }
            arrayList6.add(arrayList7);
        }
        Pdlog.m3273d(TAG, "ciecleT size is " + arrayList6.size() + " 1036");
        if (arrayList6.isEmpty()) {
            Pdlog.m3273d(TAG, "is node 1038");
            return arrayList;
        }
        int size2 = arrayList6.size();
        for (int i4 = 0; i4 < size2; i4++) {
            Iterator it = ((Iterable) arrayList6.get(i4)).iterator();
            while (it.hasNext()) {
                Pdlog.m3273d(TAG, "index is1044 " + i4 + ", int is " + ((Number) it.next()).intValue() + ' ');
            }
        }
        Iterator it2 = arrayList6.iterator();
        int i5 = 0;
        while (it2.hasNext()) {
            List list = (List) it2.next();
            ArrayList<List> arrayList8 = new ArrayList();
            arrayList8.clear();
            CruiseTracks cruiseTracks = new CruiseTracks();
            if (circleElements2.get(i5).getName() == null) {
                cruiseTracks.setName("路线" + i5);
            } else {
                String name = circleElements2.get(i5).getName();
                if (name == null) {
                    Intrinsics.throwNpe();
                }
                cruiseTracks.setName(name);
            }
            int size3 = list.size() - i;
            int i6 = i2;
            while (i6 < size3) {
                int intValue = ((Number) list.get(i6)).intValue();
                i6++;
                int intValue2 = ((Number) list.get(i6)).intValue();
                MapManager mapManager = INSTANCE;
                Iterator<T> it3 = linesAll.iterator();
                while (true) {
                    if (it3.hasNext()) {
                        List list2 = (List) it3.next();
                        int size4 = list2.size();
                        if (((Vertex) list2.get(i2)).getNodeId() == intValue && ((Vertex) list2.get(size4 - 1)).getNodeId() == intValue2) {
                            arrayList8.add(list2);
                            break;
                        }
                        i2 = 0;
                    }
                }
                Unit unit2 = Unit.INSTANCE;
                i2 = 0;
            }
            Object[] objArr = new Object[i];
            objArr[0] = "cruisePath size is " + arrayList8.size() + " 1074";
            Pdlog.m3273d(TAG, objArr);
            if (arrayList8.isEmpty()) {
                Object[] objArr2 = new Object[i];
                objArr2[0] = "is node 1076";
                Pdlog.m3273d(TAG, objArr2);
                return arrayList;
            }
            ArrayList<java.util.Map> arrayList9 = new ArrayList();
            Object[] objArr3 = new Object[i];
            objArr3[0] = "cruiseLinessize is " + arrayList9.size() + " 1082";
            Pdlog.m3273d(TAG, objArr3);
            Object[] objArr4 = new Object[i];
            objArr4[0] = "crateMapModules 875";
            Pdlog.m3273d(TAG, objArr4);
            for (List list3 : arrayList8) {
                int size5 = list3.size() - i;
                int i7 = 0;
                while (i7 < size5) {
                    Object[] objArr5 = new Object[i];
                    StringBuilder sb = new StringBuilder();
                    sb.append("cruiseLinessize is ");
                    Iterator it4 = it2;
                    sb.append((Vertex) list3.get(i7));
                    sb.append(" 1086 ");
                    int i8 = i7 + 1;
                    sb.append((Vertex) list3.get(i8));
                    objArr5[0] = sb.toString();
                    Pdlog.m3273d(TAG, objArr5);
                    Pdlog.m3273d(TAG, "cruiseLinessize is " + i7 + " 1087");
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    linkedHashMap.put("start", list3.get(i7));
                    linkedHashMap.put("end", list3.get(i8));
                    arrayList9.add(linkedHashMap);
                    i7 = i8;
                    i = 1;
                    it2 = it4;
                }
            }
            Iterator it5 = it2;
            Object[] objArr6 = new Object[i];
            objArr6[0] = "cruiseLines size is " + arrayList9.size();
            Pdlog.m3273d(TAG, objArr6);
            if (arrayList9.isEmpty()) {
                Pdlog.m3273d(TAG, "is node 1097");
                return arrayList;
            }
            ArrayList arrayList10 = new ArrayList();
            for (java.util.Map map : arrayList9) {
                mapbean = new Map();
                Object obj = map.get("start");
                if (obj == null) {
                    Intrinsics.throwNpe();
                }
                double x3 = ((Vertex) obj).getX();
                Object obj2 = map.get("start");
                if (obj2 == null) {
                    Intrinsics.throwNpe();
                }
                Vector3d vector3d = new Vector3d(x3, ((Vertex) obj2).getY(), 0.0d);
                Object obj3 = map.get("end");
                if (obj3 == null) {
                    Intrinsics.throwNpe();
                }
                double x4 = ((Vertex) obj3).getX();
                Object obj4 = map.get("end");
                if (obj4 == null) {
                    Intrinsics.throwNpe();
                }
                Vector3d vector3d2 = new Vector3d(x4, ((Vertex) obj4).getY(), 0.0d);
                arrayList10.add(vector3d);
                arrayList10.add(vector3d2);
            }
            cruiseTracks.setTracks(arrayList10);
            arrayList.add(cruiseTracks);
            i5++;
            it2 = it5;
            circleElements2 = circleElements;
            i = 1;
            i2 = 0;
        }
        Object[] objArr7 = new Object[i];
        objArr7[0] = "cruiseTracks is " + arrayList.size() + " 1113";
        Pdlog.m3273d(TAG, objArr7);
        return arrayList;
    }

    public final void createmapdata(List<Elements> tracksElements, List<Elements> circleElements) {
        Intrinsics.checkParameterIsNotNull(tracksElements, "tracksElements");
        Intrinsics.checkParameterIsNotNull(circleElements, "circleElements");
        mapdataFile.setAllLines(new ArrayList());
        mapdataFile.setDotLines(new ArrayList());
        mapdataFile.setBackground(new ArrayList());
        char c = 0;
        mapdataFile.setMapinfo(new Mapinfo(50, 50, 0, 0));
        char c2 = 1;
        Pdlog.m3273d("createMapDataFile", "create file is true 4");
        Iterator<Elements> it = tracksElements.iterator();
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MIN_VALUE;
        int i4 = Integer.MAX_VALUE;
        while (true) {
            int i5 = 2;
            double d = 0.5d;
            if (it.hasNext()) {
                Elements next = it.next();
                double[] vector = next.getVector();
                if (vector == null) {
                    Intrinsics.throwNpe();
                }
                double d2 = vector[c];
                double[] vector2 = next.getVector();
                if (vector2 == null) {
                    Intrinsics.throwNpe();
                }
                Vector3d vector3d = new Vector3d(d2, vector2[c2], 0.0d, 4, null);
                double[] vector3 = next.getVector();
                if (vector3 == null) {
                    Intrinsics.throwNpe();
                }
                double d3 = vector3[2];
                double[] vector4 = next.getVector();
                if (vector4 == null) {
                    Intrinsics.throwNpe();
                }
                Line line = new Line(vector3d, new Vector3d(d3, vector4[3], 0.0d, 4, null));
                alllines.add(line);
                if (i2 > line.getStart().getX()) {
                    i2 = (int) line.getStart().getX();
                }
                if (i2 > line.getEnd().getX()) {
                    i2 = (int) line.getEnd().getX();
                }
                if (i4 > line.getStart().getY()) {
                    i4 = (int) line.getStart().getY();
                }
                if (i4 > line.getEnd().getY()) {
                    i4 = (int) line.getEnd().getY();
                }
                if (i < line.getStart().getX()) {
                    i = (int) line.getStart().getX();
                }
                if (i < line.getEnd().getX()) {
                    i = (int) line.getEnd().getX();
                }
                if (i3 < line.getStart().getY()) {
                    i3 = (int) line.getStart().getY();
                }
                if (i3 < line.getEnd().getY()) {
                    i3 = (int) line.getEnd().getY();
                }
                AllLines allLines = new AllLines();
                int i6 = i2;
                double d4 = 100;
                Iterator<Elements> it2 = it;
                allLines.getEnd().setX((int) (((line.getEnd().getX() * d4) / scale) + 0.5d));
                allLines.getEnd().setY((int) (((line.getEnd().getY() * d4) / scale) + 0.5d));
                allLines.getStart().setX((int) (((line.getStart().getX() * d4) / scale) + 0.5d));
                allLines.getStart().setY((int) (((line.getStart().getY() * d4) / scale) + 0.5d));
                List<AllLines> allLines2 = mapdataFile.getAllLines();
                if (allLines2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableList<com.pudutech.mirsdk.mapify.mapdata.AllLines>");
                }
                TypeIntrinsics.asMutableList(allLines2).add(allLines);
                i2 = i6;
                it = it2;
                c2 = 1;
                c = 0;
            } else {
                int i7 = scale;
                double d5 = 1;
                mapdataFile.setXy(new C5206XY((int) ((((i2 * 100) / i7) + 0.5d) - d5), (int) (((i * 100) / i7) + 0.5d + d5), (int) ((((i4 * 100) / i7) + 0.5d) - d5), (int) (((i3 * 100) / i7) + 0.5d + d5)));
                Pdlog.m3273d("createMapDataFile", "create file is true 6  test " + circleElements.size());
                mapdataFile.setMapModules(new ArrayList());
                for (Elements elements : circleElements) {
                    MapModules mapModules = new MapModules();
                    mapModules.setMap(new ArrayList());
                    int i8 = 1;
                    Pdlog.m3273d("createMapDataFile", "create file is true mapmodule");
                    List<List<Double>> poseList = elements.getPoseList();
                    if (poseList == null) {
                        Intrinsics.throwNpe();
                    }
                    int size = poseList.size() - 1;
                    int i9 = 0;
                    while (i9 < size) {
                        Object[] objArr = new Object[i8];
                        StringBuilder sb = new StringBuilder();
                        sb.append("create file is true 7 ");
                        double[] vector5 = elements.getVector();
                        if (vector5 == null) {
                            Intrinsics.throwNpe();
                        }
                        sb.append(vector5.length);
                        objArr[0] = sb.toString();
                        Pdlog.m3273d("createMapDataFile", objArr);
                        List<List<Double>> poseList2 = elements.getPoseList();
                        if (poseList2 == null) {
                            Intrinsics.throwNpe();
                        }
                        List<Double> list = poseList2.get(i9);
                        List<List<Double>> poseList3 = elements.getPoseList();
                        if (poseList3 == null) {
                            Intrinsics.throwNpe();
                        }
                        int i10 = i9 + 1;
                        List<Double> list2 = poseList3.get(i10);
                        mapbean = new Map();
                        double d6 = i5;
                        double doubleValue = (list.get(0).doubleValue() * d6) + d;
                        double doubleValue2 = (list.get(1).doubleValue() * d6) + d;
                        int i11 = size;
                        double doubleValue3 = (list2.get(0).doubleValue() * d6) + d;
                        double doubleValue4 = (list2.get(1).doubleValue() * d6) + 0.5d;
                        mapbean.getStart().setX((int) doubleValue);
                        mapbean.getStart().setY((int) doubleValue2);
                        mapbean.getEnd().setX((int) doubleValue3);
                        mapbean.getEnd().setY((int) doubleValue4);
                        Pdlog.m3273d("createMapDataFile", "create file is true 10");
                        List<Map> map = mapModules.getMap();
                        if (map == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableList<com.pudutech.mirsdk.mapify.mapdata.Map>");
                        }
                        TypeIntrinsics.asMutableList(map).add(mapbean);
                        Pdlog.m3273d("createMapDataFile", "create file is true 8");
                        size = i11;
                        i9 = i10;
                        d = 0.5d;
                        i8 = 1;
                        i5 = 2;
                    }
                    double d7 = d;
                    mapModules.setId(String.valueOf(elements.getId()));
                    mapModules.setName(elements.getName());
                    Pdlog.m3273d(TAG, "crateMapModules 968 " + elements.getName());
                    Pdlog.m3273d(TAG, "cruiseId is " + elements.getId() + ' ');
                    List<MapModules> mapModules2 = mapdataFile.getMapModules();
                    if (mapModules2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableList<com.pudutech.mirsdk.mapify.mapdata.MapModules>");
                    }
                    TypeIntrinsics.asMutableList(mapModules2).add(mapModules);
                    Pdlog.m3273d("createMapDataFile", "create file is true 11");
                    d = d7;
                    i5 = 2;
                }
                File file = new File(new File(MapFilePathConfig.DEFAULT_MAP_LOCATE_PATH, StringBase64Kt.encodeMapName(MapFilePathConfig.INSTANCE.getCREATE_MAPNAME())).getAbsolutePath(), MapFilePathConfig.MAP_DATA);
                String json = gson.toJson(mapdataFile);
                Intrinsics.checkExpressionValueIsNotNull(json, "gson.toJson(mapdataFile)");
                FilesKt.writeText$default(file, json, null, 2, null);
                Pdlog.m3273d("createMapDataFile", "create file is true 5");
                return;
            }
        }
    }

    /* compiled from: MapManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/mirsdk/mapify/util/MapManager$Line;", "", "start", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "end", "(Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "getEnd", "()Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "setEnd", "(Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "getStart", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final /* data */ class Line {
        private Vector3d end;
        private final Vector3d start;

        public static /* synthetic */ Line copy$default(Line line, Vector3d vector3d, Vector3d vector3d2, int i, Object obj) {
            if ((i & 1) != 0) {
                vector3d = line.start;
            }
            if ((i & 2) != 0) {
                vector3d2 = line.end;
            }
            return line.copy(vector3d, vector3d2);
        }

        /* renamed from: component1, reason: from getter */
        public final Vector3d getStart() {
            return this.start;
        }

        /* renamed from: component2, reason: from getter */
        public final Vector3d getEnd() {
            return this.end;
        }

        public final Line copy(Vector3d start, Vector3d end) {
            Intrinsics.checkParameterIsNotNull(start, "start");
            Intrinsics.checkParameterIsNotNull(end, "end");
            return new Line(start, end);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Line)) {
                return false;
            }
            Line line = (Line) other;
            return Intrinsics.areEqual(this.start, line.start) && Intrinsics.areEqual(this.end, line.end);
        }

        public int hashCode() {
            Vector3d vector3d = this.start;
            int hashCode = (vector3d != null ? vector3d.hashCode() : 0) * 31;
            Vector3d vector3d2 = this.end;
            return hashCode + (vector3d2 != null ? vector3d2.hashCode() : 0);
        }

        public String toString() {
            return "Line(start=" + this.start + ", end=" + this.end + ")";
        }

        public Line(Vector3d start, Vector3d end) {
            Intrinsics.checkParameterIsNotNull(start, "start");
            Intrinsics.checkParameterIsNotNull(end, "end");
            this.start = start;
            this.end = end;
        }

        public final Vector3d getEnd() {
            return this.end;
        }

        public final Vector3d getStart() {
            return this.start;
        }

        public final void setEnd(Vector3d vector3d) {
            Intrinsics.checkParameterIsNotNull(vector3d, "<set-?>");
            this.end = vector3d;
        }
    }
}
