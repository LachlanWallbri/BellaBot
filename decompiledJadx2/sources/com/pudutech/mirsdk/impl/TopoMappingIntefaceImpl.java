package com.pudutech.mirsdk.impl;

import com.aliyun.alink.linksdk.tmp.device.panel.data.InvokeServiceData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.freeinstall_ui.utils.Constants;
import com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface;
import com.pudutech.mirsdk.aidl.serialize.CameraType;
import com.pudutech.mirsdk.aidl.serialize.CruiseTracks;
import com.pudutech.mirsdk.aidl.serialize.DockerResult;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdk.config.MapFilePathConfig;
import com.pudutech.mirsdk.config.SDKConfig;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.map.MapConfig;
import com.pudutech.mirsdk.map.StringBase64Kt;
import com.pudutech.mirsdk.mapify.bean.Elements;
import com.pudutech.mirsdk.mapify.bean.Map;
import com.pudutech.mirsdk.mapify.bean.Root;
import com.pudutech.mirsdk.mapify.mapdata.ScheduleConfig;
import com.pudutech.mirsdk.mapify.util.CamerConfigHelper;
import com.pudutech.mirsdk.mapify.util.FileUtils;
import com.pudutech.mirsdk.mapify.util.MapManager;
import com.pudutech.mirsdk.mapify.util.ZipUtils;
import com.pudutech.mirsdk.mircore.MirMappingCoreInterface;
import com.pudutech.mirsdk.mircore.coreparcel.CruisePath;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import com.pudutech.mirsdk.mircore.coreparcel.DockerDetectResult;
import com.pudutech.mirsdk.mircore.coreparcel.Node;
import com.pudutech.mirsdk.mircore.coreparcel.TopoPath;
import com.pudutech.mirsdk.mircore.coreparcel.TopoTrack;
import com.pudutech.schedulerlib.utils.CommandUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;

/* compiled from: TopoMappingIntefaceImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0015\n\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0016\u00103\u001a\b\u0012\u0004\u0012\u00020-0\u000b2\u0006\u0010\u001f\u001a\u00020\u0007H\u0016J\u0016\u00104\u001a\u00020\u001e2\f\u00105\u001a\b\u0012\u0004\u0012\u00020\u00140\u000bH\u0016J\b\u00106\u001a\u000207H\u0016J(\u00108\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u000b2\u000e\u00109\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u000b2\u0006\u0010:\u001a\u00020\u0007H\u0016J$\u0010;\u001a\u0002072\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00140\u000b2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00140\u000bH\u0016J.\u0010>\u001a\b\u0012\u0004\u0012\u00020-0\u000b2\u000e\u0010?\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u000b2\u000e\u0010@\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010\u000bH\u0016J\b\u0010A\u001a\u00020\tH\u0016J\u000e\u0010B\u001a\u0002072\u0006\u0010:\u001a\u00020\u0007J\u000e\u0010C\u001a\b\u0012\u0004\u0012\u00020(0\u000bH\u0016J\u000e\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00180\u000bH\u0016J\u000e\u0010E\u001a\b\u0012\u0004\u0012\u00020F0\u000bH\u0016J\b\u0010G\u001a\u00020HH\u0016J\u0006\u0010I\u001a\u000207J\u0006\u0010J\u001a\u000207J\u0006\u0010K\u001a\u000207J\u000e\u0010L\u001a\b\u0012\u0004\u0012\u00020F0\u000bH\u0016J\u000e\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00140\u000bH\u0016J\u000e\u0010N\u001a\u00020\u00072\u0006\u0010O\u001a\u00020\u0007J\u0018\u0010P\u001a\u0002072\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u000bH\u0016J\b\u0010Q\u001a\u000207H\u0016J\u0018\u0010R\u001a\u0002072\u000e\u0010S\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u000bH\u0016J\u0012\u0010T\u001a\u0002072\b\u0010U\u001a\u0004\u0018\u00010VH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u001a\u001a\n \u001c*\u0004\u0018\u00010\u001b0\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\"\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000f0#j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000f`$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010/\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00100\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00101\u001a\b\u0012\u0004\u0012\u00020\u00140\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00102\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006W"}, m3961d2 = {"Lcom/pudutech/mirsdk/impl/TopoMappingIntefaceImpl;", "Lcom/pudutech/mirsdk/aidl/mapify/TopoMappingInteface$Stub;", "mapCoreService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/mircore/MirMappingCoreInterface;", "(Lcom/pudutech/base/architecture/AIDLConnection;)V", "TAG", "", "cameraType", "Lcom/pudutech/mirsdk/aidl/serialize/CameraType;", "chargesElements", "", "Lcom/pudutech/mirsdk/mapify/bean/Elements;", "circleElements", "cruiseNodes", "Lcom/pudutech/mirsdk/mircore/coreparcel/Node;", "cruisePath", "Lcom/pudutech/mirsdk/mircore/coreparcel/CruisePath;", "cruisePoses", "", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "currentIds", "", "dockerlist", "Lcom/pudutech/mirsdk/aidl/serialize/DockerResult;", "element", "gson", "Lcom/google/gson/Gson;", "kotlin.jvm.PlatformType", "isLegalOfVirtualWall", "", MapElement.Key.MAP, "Lcom/pudutech/mirsdk/mapify/bean/Map;", "nodeElements", "nodeHashMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "oldTopoData", "Lcom/pudutech/mirsdk/mircore/coreparcel/TopoPath;", "oldcruiseTracks", "Lcom/pudutech/mirsdk/aidl/serialize/CruiseTracks;", "root", "Lcom/pudutech/mirsdk/mapify/bean/Root;", "sourceElements", "sourceNodes", "Lcom/pudutech/mirsdk/mircore/coreparcel/Destination;", "topoData", "tracksElements", "virtualWall", "virtualWall3dList", "virtualWallElements", "analyMap", "checkVirtualWall", "wallList", "clearOldData", "", "createCruise", "poseList", "name", "createScheduleConfig", "parking_pose_tmp", "taking_pose_tmp", "createTopomap", "poses", "ele_list", "getCamraType", "getCircleList", "getCruiseTracksList", "getDockerChargeList", "getDoubleRoadTracks", "Lcom/pudutech/mirsdk/mircore/coreparcel/TopoTrack;", "getMapLocateCase", "Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;", "getNodeList", "getSourceList", "getTrackList", "getTracks", "getVirtualWallList", "loadPdmap", "pdmapFullName", "saveChargeDockerList", "saveTopomap", "saveVirtualWall", "p0", "setTwoWayroad", "nodelist", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class TopoMappingIntefaceImpl extends TopoMappingInteface.Stub {
    private final String TAG;
    private CameraType cameraType;
    private List<Elements> chargesElements;
    private List<Elements> circleElements;
    private List<Node> cruiseNodes;
    private CruisePath cruisePath;
    private List<Vector3d> cruisePoses;
    private int currentIds;
    private List<DockerResult> dockerlist;
    private Elements element;
    private Gson gson;
    private boolean[] isLegalOfVirtualWall;
    private Map map;
    private final AIDLConnection<MirMappingCoreInterface> mapCoreService;
    private List<Elements> nodeElements;
    private HashMap<String, Node> nodeHashMap;
    private TopoPath oldTopoData;
    private List<CruiseTracks> oldcruiseTracks;
    private Root root;
    private List<Elements> sourceElements;
    private List<Destination> sourceNodes;
    private TopoPath topoData;
    private List<Elements> tracksElements;
    private List<Vector3d> virtualWall;
    private final List<Vector3d> virtualWall3dList;
    private List<Elements> virtualWallElements;

    public TopoMappingIntefaceImpl(AIDLConnection<MirMappingCoreInterface> mapCoreService) {
        Intrinsics.checkParameterIsNotNull(mapCoreService, "mapCoreService");
        this.mapCoreService = mapCoreService;
        this.TAG = "TopoMappingIntefaceImpl";
        this.circleElements = new ArrayList();
        this.nodeElements = new ArrayList();
        this.sourceElements = new ArrayList();
        this.tracksElements = new ArrayList();
        this.chargesElements = new ArrayList();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.topoData = new TopoPath();
        this.isLegalOfVirtualWall = new boolean[0];
        this.virtualWall = CollectionsKt.emptyList();
        this.virtualWallElements = new ArrayList();
        this.virtualWall3dList = new ArrayList();
        this.cruiseNodes = new ArrayList();
        this.sourceNodes = new ArrayList();
        this.nodeHashMap = new HashMap<>();
        this.cruisePath = new CruisePath();
        this.dockerlist = new ArrayList();
        this.oldcruiseTracks = new ArrayList();
        this.cameraType = CameraType.MARKER_CAMERA;
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
    public void saveTopomap() {
        Pdlog.m3273d(this.TAG, "save the topoMapData to ALTAS_DATA file");
        getTrackList();
        getNodeList();
        getSourceList();
        this.root = new Root();
        this.map = new Map();
        Map map = this.map;
        if (map == null) {
            Intrinsics.throwUninitializedPropertyAccessException(MapElement.Key.MAP);
        }
        map.getElements().addAll(this.tracksElements);
        Map map2 = this.map;
        if (map2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(MapElement.Key.MAP);
        }
        map2.getElements().addAll(this.sourceElements);
        Map map3 = this.map;
        if (map3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(MapElement.Key.MAP);
        }
        map3.getElements().addAll(this.nodeElements);
        Map map4 = this.map;
        if (map4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(MapElement.Key.MAP);
        }
        map4.getElements().addAll(this.circleElements);
        Map map5 = this.map;
        if (map5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(MapElement.Key.MAP);
        }
        map5.getElements().addAll(this.virtualWallElements);
        Map map6 = this.map;
        if (map6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(MapElement.Key.MAP);
        }
        map6.getElements().addAll(this.chargesElements);
        File file = new File((MapFilePathConfig.DEFAULT_MAP_LOCATE_PATH + "/") + StringBase64Kt.encodeMapName(MapFilePathConfig.INSTANCE.getCREATE_MAPNAME()));
        Pdlog.m3273d(this.TAG, "map file " + MapFilePathConfig.INSTANCE.getCREATE_MAPNAME() + " exist? " + file.exists());
        Root root = this.root;
        if (root == null) {
            Intrinsics.throwUninitializedPropertyAccessException("root");
        }
        Map map7 = this.map;
        if (map7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(MapElement.Key.MAP);
        }
        root.setMap(map7);
        File file2 = new File(file.getAbsolutePath(), "ATLAS_DATA");
        Gson gson = this.gson;
        Root root2 = this.root;
        if (root2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("root");
        }
        String json = gson.toJson(root2);
        Intrinsics.checkExpressionValueIsNotNull(json, "gson.toJson(root)");
        FilesKt.writeText$default(file2, json, null, 2, null);
        MapManager.INSTANCE.createmapdata(this.tracksElements, this.circleElements);
        Pdlog.m3273d(this.TAG, "finish create MapData");
        File[] listFiles = file.listFiles();
        Intrinsics.checkExpressionValueIsNotNull(listFiles, "dir.listFiles()");
        ZipUtils.zipFiles(ArraysKt.toList(listFiles), new File(MapFilePathConfig.DEFAULT_MAP_LOCATE_PATH, StringBase64Kt.encodeMapName(MapFilePathConfig.INSTANCE.getCREATE_MAPNAME()) + ".pdmap"));
        CommandUtils.INSTANCE.executeCommand(InvokeServiceData.CALL_TYPE_SYNC);
        this.tracksElements.clear();
        this.sourceElements.clear();
        this.nodeElements.clear();
        this.circleElements.clear();
        this.virtualWallElements.clear();
        this.chargesElements.clear();
        this.nodeHashMap.clear();
        this.currentIds = 0;
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
    public void setTwoWayroad(int[] nodelist) {
        if (nodelist != null) {
            Pdlog.m3273d("topoData", "topoData is setTwoWayRoad");
            MirMappingCoreInterface mirMappingCoreInterface = this.mapCoreService.getInterface();
            TopoPath resetDualPath = mirMappingCoreInterface != null ? mirMappingCoreInterface.resetDualPath(nodelist) : null;
            if (resetDualPath == null) {
                Intrinsics.throwNpe();
            }
            this.topoData = resetDualPath;
            this.nodeHashMap.clear();
            if (this.topoData.getIllegal_service_point() != null) {
                for (Destination destination : this.topoData.getIllegal_service_point()) {
                    this.sourceNodes.remove(destination);
                }
            }
            for (Node node : this.topoData.getNodes()) {
                HashMap<String, Node> hashMap = this.nodeHashMap;
                if (hashMap != null) {
                    hashMap.put(String.valueOf(node.getId()), node);
                }
            }
        }
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
    public List<TopoTrack> getDoubleRoadTracks() {
        ArrayList arrayList = new ArrayList();
        for (TopoTrack topoTrack : this.topoData.getTracks()) {
            if (topoTrack.getDual_width() >= 1.6d) {
                arrayList.add(topoTrack);
            }
        }
        return arrayList;
    }

    public final String loadPdmap(String pdmapFullName) {
        ZipInputStream zipInputStream;
        Intrinsics.checkParameterIsNotNull(pdmapFullName, "pdmapFullName");
        Pdlog.m3273d(this.TAG, "pdmap name " + pdmapFullName);
        ZipInputStream zipInputStream2 = (ZipInputStream) null;
        String str = "";
        byte[] bArr = new byte[1024];
        File file = new File(pdmapFullName);
        ZipUtils.unzipFile(file, new File(file.getParent(), FileUtils.getFileNameNoExtension(file.getName())));
        MapFilePathConfig mapFilePathConfig = MapFilePathConfig.INSTANCE;
        String fileNameNoExtension = FileUtils.getFileNameNoExtension(file.getName());
        Intrinsics.checkExpressionValueIsNotNull(fileNameNoExtension, "getFileNameNoExtension(file.name)");
        mapFilePathConfig.setCREATE_MAPNAME(StringBase64Kt.decodeMapName(fileNameNoExtension));
        File file2 = new File(MapFilePathConfig.DEFAULT_MAP_LOCATE_PATH, FileUtils.getFileNameNoExtension(file.getName()));
        Pdlog.m3273d("analyMap", "FileUtils.getFileNameNoExtension(file.name)  is " + file2 + " and decodname is " + MapFilePathConfig.INSTANCE.getCREATE_MAPNAME());
        File file3 = new File(MapFilePathConfig.TMP_MAP_LOCATE_PATH);
        if (!file3.exists()) {
            file3.mkdir();
        }
        org.apache.commons.io.FileUtils.copyDirectory(file2, new File(MapFilePathConfig.TMP_MAP_LOCATE_PATH));
        try {
            try {
                zipInputStream = new ZipInputStream(new FileInputStream(pdmapFullName));
                while (true) {
                    try {
                        ZipEntry nextEntry = zipInputStream.getNextEntry();
                        if (nextEntry == null) {
                            break;
                        }
                        if (Intrinsics.areEqual(nextEntry.getName(), "ATLAS_DATA")) {
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            while (true) {
                                int read = zipInputStream.read(bArr);
                                if (read <= 0) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, read);
                            }
                            byte[] tmp = byteArrayOutputStream.toByteArray();
                            StringBuilder sb = new StringBuilder();
                            sb.append(str);
                            Intrinsics.checkExpressionValueIsNotNull(tmp, "tmp");
                            sb.append(new String(tmp, 0, tmp.length, Charsets.UTF_8));
                            str = sb.toString();
                        }
                        if (Intrinsics.areEqual(nextEntry.getName(), "config.json")) {
                            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                            while (true) {
                                int read2 = zipInputStream.read(bArr);
                                if (read2 <= 0) {
                                    break;
                                }
                                byteArrayOutputStream2.write(bArr, 0, read2);
                            }
                            byte[] tmp2 = byteArrayOutputStream2.toByteArray();
                            Intrinsics.checkExpressionValueIsNotNull(tmp2, "tmp");
                            MapConfig mapConfig = (MapConfig) new Gson().fromJson(new String(tmp2, 0, tmp2.length, Charsets.UTF_8), MapConfig.class);
                            int camera_scheme = mapConfig.getCamera_scheme();
                            int sensor = mapConfig.getSensor();
                            if (sensor == 0 || sensor == 1) {
                                MapFilePathConfig.INSTANCE.setLocateCase(LocateCase.Marker);
                            } else if (sensor == 2) {
                                MapFilePathConfig.INSTANCE.setLocateCase(LocateCase.Laser);
                            } else if (sensor == 3) {
                                MapFilePathConfig.INSTANCE.setLocateCase(LocateCase.LaserMark);
                            }
                            if (camera_scheme == 1) {
                                CamerConfigHelper.cameraType = CameraType.FACE_CAMERA;
                                this.cameraType = CameraType.FACE_CAMERA;
                            } else {
                                CamerConfigHelper.cameraType = CameraType.MARKER_CAMERA;
                                this.cameraType = CameraType.MARKER_CAMERA;
                            }
                            Pdlog.m3273d(this.TAG, "loadPdmap is  " + camera_scheme + ' ');
                        }
                        zipInputStream.closeEntry();
                    } catch (Exception e) {
                        e = e;
                        zipInputStream2 = zipInputStream;
                        Pdlog.m3274e("loadPdmap", "load map failed: " + e.getMessage());
                        if (zipInputStream2 != null) {
                            zipInputStream2.close();
                        }
                        return str;
                    } catch (Throwable th) {
                        th = th;
                        if (zipInputStream != null) {
                            zipInputStream.close();
                        }
                        throw th;
                    }
                }
                zipInputStream.close();
            } catch (Throwable th2) {
                th = th2;
                zipInputStream = zipInputStream2;
            }
        } catch (Exception e2) {
            e = e2;
        }
        return str;
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
    public List<Destination> analyMap(String map) {
        Double valueOf;
        Intrinsics.checkParameterIsNotNull(map, "map");
        ArrayList arrayList = new ArrayList();
        String loadPdmap = loadPdmap(map);
        if (Intrinsics.areEqual(loadPdmap, "")) {
            Pdlog.m3273d("analyMap", "destinations is null");
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Map map2 = ((Root) new Gson().fromJson(loadPdmap, Root.class)).getMap();
        if (map2 == null) {
            Intrinsics.throwNpe();
        }
        List<Elements> elements = map2.getElements();
        ArrayList arrayList4 = new ArrayList();
        this.dockerlist.clear();
        this.virtualWall3dList.clear();
        Pdlog.m3273d("analyMap", "topoElements size is " + elements.size());
        for (Elements elements2 : elements) {
            Destination destination = new Destination();
            DockerResult dockerResult = new DockerResult();
            String type = elements2.getType();
            if (type == null) {
                Intrinsics.throwNpe();
            }
            if (type.equals("charging_pile")) {
                dockerResult.setName(String.valueOf(elements2.getId()));
                dockerResult.setDocker(new DockerDetectResult());
                DockerDetectResult docker = dockerResult.getDocker();
                double[] vector = elements2.getVector();
                if (vector == null) {
                    Intrinsics.throwNpe();
                }
                docker.setX(vector[0]);
                DockerDetectResult docker2 = dockerResult.getDocker();
                double[] vector2 = elements2.getVector();
                if (vector2 == null) {
                    Intrinsics.throwNpe();
                }
                docker2.setY(vector2[1]);
                DockerDetectResult docker3 = dockerResult.getDocker();
                double[] vector3 = elements2.getVector();
                if (vector3 == null) {
                    Intrinsics.throwNpe();
                }
                docker3.setTheta(vector3[2]);
                this.dockerlist.add(dockerResult);
            } else {
                String type2 = elements2.getType();
                if (type2 == null) {
                    Intrinsics.throwNpe();
                }
                if (type2.equals("track")) {
                    TopoTrack topoTrack = new TopoTrack();
                    String id = elements2.getId();
                    if (id == null) {
                        Intrinsics.throwNpe();
                    }
                    topoTrack.setTopo_id(Integer.parseInt(id));
                    double[] vector4 = elements2.getVector();
                    if (vector4 == null) {
                        Intrinsics.throwNpe();
                    }
                    double d = vector4[0];
                    double[] vector5 = elements2.getVector();
                    if (vector5 == null) {
                        Intrinsics.throwNpe();
                    }
                    topoTrack.setStart_pose(new Vector3d(d, vector5[1], 0.0d));
                    double[] vector6 = elements2.getVector();
                    if (vector6 == null) {
                        Intrinsics.throwNpe();
                    }
                    double d2 = vector6[2];
                    double[] vector7 = elements2.getVector();
                    if (vector7 == null) {
                        Intrinsics.throwNpe();
                    }
                    topoTrack.setEnd_pose(new Vector3d(d2, vector7[3], 0.0d));
                    topoTrack.setPath_width(elements2.getWidth());
                    topoTrack.setDual_width(elements2.getDualWidth());
                    arrayList2.add(topoTrack);
                } else {
                    String type3 = elements2.getType();
                    if (type3 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (type3.equals("node")) {
                        Node node = new Node();
                        String id2 = elements2.getId();
                        if (id2 == null) {
                            Intrinsics.throwNpe();
                        }
                        node.setId(Integer.parseInt(id2));
                        double[] vector8 = elements2.getVector();
                        if (vector8 == null) {
                            Intrinsics.throwNpe();
                        }
                        node.setX(vector8[0]);
                        double[] vector9 = elements2.getVector();
                        if (vector9 == null) {
                            Intrinsics.throwNpe();
                        }
                        node.setY(vector9[1]);
                        arrayList3.add(node);
                    } else {
                        String type4 = elements2.getType();
                        if (type4 == null) {
                            Intrinsics.throwNpe();
                        }
                        if (!type4.equals("trace_circle")) {
                            String type5 = elements2.getType();
                            if (type5 == null) {
                                Intrinsics.throwNpe();
                            }
                            if (!type5.equals("circle")) {
                                String type6 = elements2.getType();
                                if (type6 == null) {
                                    Intrinsics.throwNpe();
                                }
                                if (!type6.equals("danger_area")) {
                                    String type7 = elements2.getType();
                                    if (type7 == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    if (type7.equals("virtual_wall")) {
                                        double[] vector10 = elements2.getVector();
                                        Double valueOf2 = vector10 != null ? Double.valueOf(vector10[0]) : null;
                                        if (valueOf2 == null) {
                                            Intrinsics.throwNpe();
                                        }
                                        double doubleValue = valueOf2.doubleValue();
                                        double[] vector11 = elements2.getVector();
                                        Double valueOf3 = vector11 != null ? Double.valueOf(vector11[1]) : null;
                                        if (valueOf3 == null) {
                                            Intrinsics.throwNpe();
                                        }
                                        Vector3d vector3d = new Vector3d(doubleValue, valueOf3.doubleValue(), 0.0d);
                                        double[] vector12 = elements2.getVector();
                                        Double valueOf4 = vector12 != null ? Double.valueOf(vector12[2]) : null;
                                        if (valueOf4 == null) {
                                            Intrinsics.throwNpe();
                                        }
                                        double doubleValue2 = valueOf4.doubleValue();
                                        double[] vector13 = elements2.getVector();
                                        valueOf = vector13 != null ? Double.valueOf(vector13[3]) : null;
                                        if (valueOf == null) {
                                            Intrinsics.throwNpe();
                                        }
                                        Vector3d vector3d2 = new Vector3d(doubleValue2, valueOf.doubleValue(), 0.0d);
                                        this.virtualWall3dList.add(vector3d);
                                        this.virtualWall3dList.add(vector3d2);
                                    } else {
                                        Pdlog.m3273d("analyCircleElements", "test is " + elements2.getType() + " and name is " + elements2.getName() + " mode is " + elements2.getMode());
                                        destination.setId(String.valueOf(elements2.getId()));
                                        destination.setName(String.valueOf(elements2.getId()));
                                        destination.setGroup(String.valueOf(elements2.getGroup()));
                                        double[] vector14 = elements2.getVector();
                                        Double valueOf5 = vector14 != null ? Double.valueOf(vector14[0]) : null;
                                        if (valueOf5 == null) {
                                            Intrinsics.throwNpe();
                                        }
                                        double doubleValue3 = valueOf5.doubleValue();
                                        double[] vector15 = elements2.getVector();
                                        Double valueOf6 = vector15 != null ? Double.valueOf(vector15[1]) : null;
                                        if (valueOf6 == null) {
                                            Intrinsics.throwNpe();
                                        }
                                        double doubleValue4 = valueOf6.doubleValue();
                                        double[] vector16 = elements2.getVector();
                                        valueOf = vector16 != null ? Double.valueOf(vector16[2]) : null;
                                        if (valueOf == null) {
                                            Intrinsics.throwNpe();
                                        }
                                        destination.setVector(new Vector3d(doubleValue3, doubleValue4, valueOf.doubleValue()));
                                        destination.setDir(elements2.getDir());
                                        destination.setDoubleDir(elements2.getDoubleDir());
                                        destination.setHigh_precision(elements2.getHigh_precision());
                                        destination.setMode(String.valueOf(elements2.getMode()));
                                        arrayList.add(destination);
                                    }
                                }
                            }
                        }
                        arrayList4.add(elements2);
                    }
                }
            }
        }
        this.oldTopoData = new TopoPath();
        TopoPath topoPath = this.oldTopoData;
        if (topoPath != null) {
            Object[] array = arrayList2.toArray(new TopoTrack[0]);
            if (array == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            topoPath.setTracks((TopoTrack[]) array);
        }
        TopoPath topoPath2 = this.oldTopoData;
        if (topoPath2 != null) {
            Object[] array2 = arrayList3.toArray(new Node[0]);
            if (array2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            topoPath2.setNodes((Node[]) array2);
        }
        this.oldcruiseTracks = MapManager.INSTANCE.analyCruiseTrack(arrayList2, arrayList3, arrayList4);
        return arrayList;
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
    public List<DockerResult> getDockerChargeList() {
        return this.dockerlist;
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
    public List<CruiseTracks> getCruiseTracksList() {
        Pdlog.m3273d("oldcruiseTracks", "oldcruiseTracks retrun is " + this.oldcruiseTracks.size());
        return this.oldcruiseTracks;
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
    public List<Vector3d> getVirtualWallList() {
        return this.virtualWall3dList;
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
    public void clearOldData() {
        this.oldTopoData = (TopoPath) null;
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
    /* renamed from: getCamraType, reason: from getter */
    public CameraType getCameraType() {
        return this.cameraType;
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
    public List<TopoTrack> getTracks() {
        return ArraysKt.toMutableList(this.topoData.getTracks());
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
    public LocateCase getMapLocateCase() {
        return MapFilePathConfig.INSTANCE.getLocateCase();
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
    public boolean[] checkVirtualWall(List<Vector3d> wallList) {
        Intrinsics.checkParameterIsNotNull(wallList, "wallList");
        MirMappingCoreInterface mirMappingCoreInterface = this.mapCoreService.getInterface();
        boolean[] checkVirtualWall = mirMappingCoreInterface != null ? mirMappingCoreInterface.checkVirtualWall(wallList) : null;
        if (checkVirtualWall == null) {
            Intrinsics.throwNpe();
        }
        this.isLegalOfVirtualWall = checkVirtualWall;
        Pdlog.m3273d("checkVirtualWall", "checkVirtualWall size is " + this.isLegalOfVirtualWall.length);
        int i = 0;
        for (boolean z : this.isLegalOfVirtualWall) {
            if (z) {
                this.virtualWall = CollectionsKt.plus((Collection<? extends Vector3d>) this.virtualWall, wallList != null ? wallList.get(i) : null);
            }
            i++;
        }
        return this.isLegalOfVirtualWall;
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
    public List<Vector3d> createCruise(List<Vector3d> poseList, String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (poseList != null) {
            this.cruisePoses = poseList;
        }
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("input pose v2 size is ");
        sb.append(poseList != null ? Integer.valueOf(poseList.size()) : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d("cruiseNodes", objArr);
        MirMappingCoreInterface mirMappingCoreInterface = this.mapCoreService.getInterface();
        CruisePath cruisePath = mirMappingCoreInterface != null ? mirMappingCoreInterface.getCruisePath(poseList) : null;
        if (cruisePath == null) {
            Intrinsics.throwNpe();
        }
        this.cruisePath = cruisePath;
        CruisePath cruisePath2 = this.cruisePath;
        Node[] nodes = cruisePath2 != null ? cruisePath2.getNodes() : null;
        if (nodes == null) {
            Intrinsics.throwNpe();
        }
        this.cruiseNodes = ArraysKt.toMutableList(nodes);
        CruisePath cruisePath3 = this.cruisePath;
        Vector3d[] tracks = cruisePath3 != null ? cruisePath3.getTracks() : null;
        if (tracks == null) {
            Intrinsics.throwNpe();
        }
        List<Vector3d> mutableList = ArraysKt.toMutableList(tracks);
        Object[] objArr2 = new Object[1];
        StringBuilder sb2 = new StringBuilder();
        sb2.append("create cruise nodes size ");
        sb2.append(this.cruiseNodes.size());
        sb2.append(" and cruise tracks is ");
        CruisePath cruisePath4 = this.cruisePath;
        if (cruisePath4 == null) {
            Intrinsics.throwNpe();
        }
        sb2.append(cruisePath4.getTracks().length);
        objArr2[0] = sb2.toString();
        Pdlog.m3273d("cruiseNodes", objArr2);
        for (Node node : this.cruiseNodes) {
            HashMap<String, Node> hashMap = this.nodeHashMap;
            if (hashMap != null) {
                hashMap.put(String.valueOf(node.getId()), node);
            }
        }
        if (this.cruiseNodes.size() > 0) {
            getCircleList(name);
        }
        Object[] objArr3 = new Object[1];
        StringBuilder sb3 = new StringBuilder();
        sb3.append("tracks size is ");
        CruisePath cruisePath5 = this.cruisePath;
        if (cruisePath5 == null) {
            Intrinsics.throwNpe();
        }
        sb3.append(cruisePath5.getTracks());
        objArr3[0] = sb3.toString();
        Pdlog.m3273d("cruiseNodes", objArr3);
        return mutableList;
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
    public void saveVirtualWall(List<Vector3d> p0) {
        if (p0 == null) {
            Intrinsics.throwNpe();
        }
        IntProgression step = RangesKt.step(CollectionsKt.getIndices(p0), 2);
        int first = step.getFirst();
        int last = step.getLast();
        int step2 = step.getStep();
        if (step2 >= 0) {
            if (first > last) {
                return;
            }
        } else if (first < last) {
            return;
        }
        while (true) {
            this.element = new Elements();
            Elements elements = this.element;
            if (elements == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            elements.setType("virtual_wall");
            Elements elements2 = this.element;
            if (elements2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            int i = first + 1;
            elements2.setVector(new double[]{p0.get(first).getX(), p0.get(first).getY(), p0.get(i).getX(), p0.get(i).getY()});
            Elements elements3 = this.element;
            if (elements3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            elements3.setId(String.valueOf(first / 2));
            List<Elements> list = this.virtualWallElements;
            Elements elements4 = this.element;
            if (elements4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            list.add(elements4);
            if (first == last) {
                return;
            } else {
                first += step2;
            }
        }
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
    public List<Destination> createTopomap(List<Vector3d> poses, List<Destination> ele_list) {
        TopoPath topoPath;
        TopoTrack[] tracks;
        String string = SDKConfig.INSTANCE.getPreferences().getString("tempMaproot", MapFilePathConfig.TMP_MAP_LOCATE_PATH);
        new File(string, MapFilePathConfig.MERGE_MAP);
        File file = new File(string, "optemap.pgm");
        File file2 = new File(string, "optemap.yaml");
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("create topoData true initGen ");
        TopoPath topoPath2 = this.oldTopoData;
        sb.append((topoPath2 == null || (tracks = topoPath2.getTracks()) == null) ? null : Integer.valueOf(tracks.length));
        objArr[0] = sb.toString();
        Pdlog.m3273d("createTopomap", objArr);
        MirMappingCoreInterface mirMappingCoreInterface = this.mapCoreService.getInterface();
        if (mirMappingCoreInterface != null) {
            mirMappingCoreInterface.initGenTopoPath(this.oldTopoData, FilesKt.readBytes(file), FilesKt.readBytes(file2));
        }
        MirMappingCoreInterface mirMappingCoreInterface2 = this.mapCoreService.getInterface();
        if (mirMappingCoreInterface2 == null || (topoPath = mirMappingCoreInterface2.getTopoPath(poses, ele_list)) == null) {
            topoPath = new TopoPath();
        }
        this.topoData = topoPath;
        Pdlog.m3273d("createTopomap", "create topoData true " + this.topoData);
        List mutableList = ArraysKt.toMutableList(this.topoData.getIllegal_service_point());
        Node[] nodes = this.topoData.getNodes();
        if (ele_list != null) {
            this.sourceNodes = ele_list;
            Iterator it = mutableList.iterator();
            while (it.hasNext()) {
                this.sourceNodes.remove((Destination) it.next());
            }
        }
        Pdlog.m3273d("createTopomap", "create topoData true second  " + nodes.length);
        int length = nodes.length;
        for (int i = 0; i < length; i++) {
            Node node = nodes[i];
            HashMap<String, Node> hashMap = this.nodeHashMap;
            if (hashMap != null) {
                hashMap.put(String.valueOf(node.getId()), node);
            }
        }
        Pdlog.m3273d("createTopomap", "create topoData true third");
        return ArraysKt.toMutableList(this.topoData.getIllegal_service_point());
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0266 A[LOOP:3: B:42:0x0260->B:44:0x0266, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x02a7  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0301 A[LOOP:4: B:57:0x02fb->B:59:0x0301, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x033e  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0354 A[LOOP:5: B:65:0x034e->B:67:0x0354, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x038e  */
    @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void createScheduleConfig(List<Vector3d> parking_pose_tmp, List<Vector3d> taking_pose_tmp) {
        String str;
        String str2;
        ScheduleConfig scheduleConfig;
        Iterator it;
        Iterator it2;
        Iterator it3;
        int size;
        int i;
        Intrinsics.checkParameterIsNotNull(parking_pose_tmp, "parking_pose_tmp");
        Intrinsics.checkParameterIsNotNull(taking_pose_tmp, "taking_pose_tmp");
        char c = 0;
        Pdlog.m3273d(this.TAG, "createScheduleConfig");
        if (parking_pose_tmp.isEmpty()) {
            Pdlog.m3273d(this.TAG, "parking_pose_tmp is null");
        }
        if (taking_pose_tmp.isEmpty()) {
            Pdlog.m3273d(this.TAG, "taking_pose_tmp is null");
        }
        File file = new File(MapFilePathConfig.DEFAULT_MAP_LOCATE_PATH, StringBase64Kt.encodeMapName(MapFilePathConfig.INSTANCE.getCREATE_MAPNAME()));
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Vector3d vector3d : parking_pose_tmp) {
            arrayList2.add(new double[]{vector3d.getX(), vector3d.getY(), vector3d.getZ()});
            arrayList.add(CollectionsKt.mutableListOf(new double[]{vector3d.getX() - 0.5d, vector3d.getY() + 0.5d, vector3d.getZ()}, new double[]{vector3d.getX() + 0.5d, vector3d.getY() - 0.5d, vector3d.getZ()}));
        }
        ArrayList arrayList3 = new ArrayList();
        for (Vector3d vector3d2 : taking_pose_tmp) {
            arrayList3.add(new double[]{vector3d2.getX(), vector3d2.getY(), vector3d2.getZ()});
            arrayList.add(CollectionsKt.mutableListOf(new double[]{vector3d2.getX() - 0.5d, vector3d2.getY() + 0.5d, vector3d2.getZ()}, new double[]{vector3d2.getX() + 0.5d, vector3d2.getY() - 0.5d, vector3d2.getZ()}));
        }
        Pdlog.m3273d(this.TAG, "parkRegion size is " + arrayList.size() + ", parking_pose_table is " + arrayList2.size() + ", taking_pose_table is " + arrayList3.size());
        File file2 = new File(file.getAbsolutePath(), "scheduling_config.json");
        if (file2.isDirectory() || !file2.exists()) {
            Pdlog.m3273d(this.TAG, file2 + " is Directory or not exist");
            ScheduleConfig scheduleConfig2 = new ScheduleConfig(CollectionsKt.mutableListOf(arrayList2), CollectionsKt.mutableListOf(arrayList3));
            scheduleConfig2.setParking_regions(arrayList);
            File file3 = new File(file.getAbsolutePath(), "scheduling_config.json");
            String json = this.gson.toJson(scheduleConfig2);
            Intrinsics.checkExpressionValueIsNotNull(json, "gson.toJson(scheduleFile)");
            FilesKt.writeText$default(file3, json, null, 2, null);
            return;
        }
        Pdlog.m3273d(this.TAG, file2 + " is exist");
        FileInputStream fileInputStream = new FileInputStream(file2);
        String str3 = "";
        byte[] bArr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            try {
                try {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    str = str3;
                    try {
                        byteArrayOutputStream.write(bArr, 0, read);
                        str3 = str;
                    } catch (Exception unused) {
                    }
                } catch (Exception unused2) {
                    str = str3;
                }
                byteArrayOutputStream.close();
                fileInputStream.close();
                str2 = str;
                scheduleConfig = (ScheduleConfig) new Gson().fromJson(str2, ScheduleConfig.class);
                if (scheduleConfig.getParking_pose().isEmpty()) {
                    scheduleConfig.setParking_pose(CollectionsKt.mutableListOf(new ArrayList()));
                }
                if ((!scheduleConfig.getParking_pose().isEmpty()) && scheduleConfig.getParking_pose().size() == 1 && scheduleConfig.getParking_pose().get(0) == null) {
                    scheduleConfig.getParking_pose().clear();
                    scheduleConfig.setParking_pose(CollectionsKt.mutableListOf(new ArrayList()));
                }
                it = arrayList2.iterator();
                while (it.hasNext()) {
                    scheduleConfig.getParking_pose().get(0).add((double[]) it.next());
                }
                Pdlog.m3273d(this.TAG, "parking_pose size is " + scheduleConfig.getParking_pose().size());
                if (scheduleConfig.getTaking_pose().isEmpty()) {
                    scheduleConfig.setTaking_pose(CollectionsKt.mutableListOf(new ArrayList()));
                }
                if ((!scheduleConfig.getTaking_pose().isEmpty()) && scheduleConfig.getTaking_pose().size() == 1 && scheduleConfig.getTaking_pose().get(0) == null) {
                    scheduleConfig.getTaking_pose().clear();
                    scheduleConfig.setTaking_pose(CollectionsKt.mutableListOf(new ArrayList()));
                }
                it2 = arrayList3.iterator();
                while (it2.hasNext()) {
                    scheduleConfig.getTaking_pose().get(0).add((double[]) it2.next());
                }
                Pdlog.m3273d(this.TAG, "taking_pose size is " + scheduleConfig.getTaking_pose().size());
                if (scheduleConfig.getParking_regions() == null) {
                    scheduleConfig.setParking_regions(new ArrayList());
                }
                it3 = arrayList.iterator();
                while (it3.hasNext()) {
                    scheduleConfig.getParking_regions().add((List) it3.next());
                }
                Pdlog.m3273d(this.TAG, "parkRegion size is " + scheduleConfig.getParking_regions().size());
                size = scheduleConfig.getParking_regions().size();
                i = 0;
                while (i < size) {
                    String str4 = this.TAG;
                    Object[] objArr = new Object[1];
                    objArr[c] = "parkRegion index is " + i;
                    Pdlog.m3273d(str4, objArr);
                    for (double[] dArr : scheduleConfig.getParking_regions().get(i)) {
                        Pdlog.m3273d(this.TAG, " region is " + dArr[c] + ", " + dArr[1] + ", " + dArr[2]);
                        scheduleConfig = scheduleConfig;
                        c = (char) 0;
                        file = file;
                    }
                    i++;
                    file = file;
                }
                File file4 = file;
                String str5 = this.TAG;
                Object[] objArr2 = new Object[1];
                objArr2[c] = " region is success 539";
                Pdlog.m3273d(str5, objArr2);
                Pdlog.m3273d(this.TAG, "dir absolutePath is " + file4.getAbsolutePath() + " , MapConfig.SCHEDULING_CONFIG is scheduling_config.json");
                File file5 = new File(file4.getAbsolutePath(), "scheduling_config.json");
                String json2 = this.gson.toJson(scheduleConfig);
                Intrinsics.checkExpressionValueIsNotNull(json2, "gson.toJson(scheduleFile)");
                FilesKt.writeText$default(file5, json2, null, 2, null);
            } finally {
                byteArrayOutputStream.close();
                fileInputStream.close();
            }
        }
        byte[] tmp = byteArrayOutputStream.toByteArray();
        StringBuilder sb = new StringBuilder();
        sb.append(str3);
        Intrinsics.checkExpressionValueIsNotNull(tmp, "tmp");
        str = str3;
        sb.append(new String(tmp, 0, tmp.length, Charsets.UTF_8));
        str2 = sb.toString();
        scheduleConfig = (ScheduleConfig) new Gson().fromJson(str2, ScheduleConfig.class);
        if (scheduleConfig.getParking_pose().isEmpty()) {
        }
        if (!scheduleConfig.getParking_pose().isEmpty()) {
            scheduleConfig.getParking_pose().clear();
            scheduleConfig.setParking_pose(CollectionsKt.mutableListOf(new ArrayList()));
        }
        it = arrayList2.iterator();
        while (it.hasNext()) {
        }
        Pdlog.m3273d(this.TAG, "parking_pose size is " + scheduleConfig.getParking_pose().size());
        if (scheduleConfig.getTaking_pose().isEmpty()) {
        }
        if (!scheduleConfig.getTaking_pose().isEmpty()) {
            scheduleConfig.getTaking_pose().clear();
            scheduleConfig.setTaking_pose(CollectionsKt.mutableListOf(new ArrayList()));
        }
        it2 = arrayList3.iterator();
        while (it2.hasNext()) {
        }
        Pdlog.m3273d(this.TAG, "taking_pose size is " + scheduleConfig.getTaking_pose().size());
        if (scheduleConfig.getParking_regions() == null) {
        }
        it3 = arrayList.iterator();
        while (it3.hasNext()) {
        }
        Pdlog.m3273d(this.TAG, "parkRegion size is " + scheduleConfig.getParking_regions().size());
        size = scheduleConfig.getParking_regions().size();
        i = 0;
        while (i < size) {
        }
        File file42 = file;
        String str52 = this.TAG;
        Object[] objArr22 = new Object[1];
        objArr22[c] = " region is success 539";
        Pdlog.m3273d(str52, objArr22);
        Pdlog.m3273d(this.TAG, "dir absolutePath is " + file42.getAbsolutePath() + " , MapConfig.SCHEDULING_CONFIG is scheduling_config.json");
        File file52 = new File(file42.getAbsolutePath(), "scheduling_config.json");
        String json22 = this.gson.toJson(scheduleConfig);
        Intrinsics.checkExpressionValueIsNotNull(json22, "gson.toJson(scheduleFile)");
        FilesKt.writeText$default(file52, json22, null, 2, null);
    }

    public final void getCircleList(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.element = new Elements();
        Elements elements = this.element;
        if (elements == null) {
            Intrinsics.throwUninitializedPropertyAccessException("element");
        }
        elements.setType("trace_circle");
        Elements elements2 = this.element;
        if (elements2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("element");
        }
        elements2.setVector(new double[this.cruiseNodes.size()]);
        Pdlog.m3273d("cruisesNode size is " + this.cruiseNodes.size(), new Object[0]);
        int size = this.cruiseNodes.size();
        for (int i = 0; i < size; i++) {
            Elements elements3 = this.element;
            if (elements3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            double[] vector = elements3.getVector();
            if (vector == null) {
                Intrinsics.throwNpe();
            }
            vector[i] = this.cruiseNodes.get(i).getId();
        }
        Elements elements4 = this.element;
        if (elements4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("element");
        }
        elements4.setIdList(new ArrayList());
        Elements elements5 = this.element;
        if (elements5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("element");
        }
        elements5.setPoseList(new ArrayList());
        for (Vector3d vector3d : this.cruisePath.getTracks()) {
            List<Double> mutableListOf = CollectionsKt.mutableListOf(Double.valueOf(vector3d.getX()), Double.valueOf(vector3d.getY()), Double.valueOf(vector3d.getZ()));
            Elements elements6 = this.element;
            if (elements6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            List<List<Double>> poseList = elements6.getPoseList();
            if (poseList != null) {
                poseList.add(mutableListOf);
            }
        }
        Elements elements7 = this.element;
        if (elements7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("element");
        }
        elements7.setId(String.valueOf(this.currentIds));
        Elements elements8 = this.element;
        if (elements8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("element");
        }
        elements8.setName(name);
        if (this.cruiseNodes.size() > 0) {
            List<Elements> list = this.circleElements;
            Elements elements9 = this.element;
            if (elements9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            list.add(elements9);
        }
        Pdlog.m3273d("cruiseNodes", "nodeHashMap?.put(node.id.toString(), node  3) cruiseTrackMap");
        this.currentIds++;
    }

    public final void getNodeList() {
        HashMap<String, Node> hashMap = this.nodeHashMap;
        if (hashMap != null) {
            for (Map.Entry<String, Node> entry : hashMap.entrySet()) {
                Pdlog.m3273d("", new Object[0]);
                this.element = new Elements();
                Elements elements = this.element;
                if (elements == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("element");
                }
                elements.setType("node");
                Elements elements2 = this.element;
                if (elements2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("element");
                }
                elements2.setVector(new double[]{entry.getValue().getX(), entry.getValue().getY(), 0.0d});
                Elements elements3 = this.element;
                if (elements3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("element");
                }
                elements3.setId(entry.getKey());
                List<Elements> list = this.nodeElements;
                Elements elements4 = this.element;
                if (elements4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("element");
                }
                list.add(elements4);
            }
        }
    }

    public final void getTrackList() {
        for (TopoTrack topoTrack : this.topoData.getTracks()) {
            this.element = new Elements();
            Elements elements = this.element;
            if (elements == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            elements.setType("track");
            Elements elements2 = this.element;
            if (elements2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            double[] dArr = new double[4];
            Vector3d start_pose = topoTrack.getStart_pose();
            if (start_pose == null) {
                Intrinsics.throwNpe();
            }
            dArr[0] = start_pose.getX();
            Vector3d start_pose2 = topoTrack.getStart_pose();
            if (start_pose2 == null) {
                Intrinsics.throwNpe();
            }
            dArr[1] = start_pose2.getY();
            Vector3d end_pose = topoTrack.getEnd_pose();
            if (end_pose == null) {
                Intrinsics.throwNpe();
            }
            dArr[2] = end_pose.getX();
            Vector3d end_pose2 = topoTrack.getEnd_pose();
            if (end_pose2 == null) {
                Intrinsics.throwNpe();
            }
            dArr[3] = end_pose2.getY();
            elements2.setVector(dArr);
            Elements elements3 = this.element;
            if (elements3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            elements3.setId(String.valueOf(topoTrack.getTopo_id()));
            Elements elements4 = this.element;
            if (elements4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            elements4.setWidth(topoTrack.getPath_width());
            Elements elements5 = this.element;
            if (elements5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            elements5.setDualWidth(topoTrack.getDual_width());
            List<Elements> list = this.tracksElements;
            Elements elements6 = this.element;
            if (elements6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            list.add(elements6);
        }
    }

    public final void getSourceList() {
        for (Destination destination : this.sourceNodes) {
            this.element = new Elements();
            Elements elements = this.element;
            if (elements == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            elements.setType(MapElement.Source.SOURCE);
            Elements elements2 = this.element;
            if (elements2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            elements2.setId(destination.getName());
            Elements elements3 = this.element;
            if (elements3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            elements3.setVector(new double[]{destination.getVector().getX(), destination.getVector().getY(), destination.getVector().getZ()});
            Elements elements4 = this.element;
            if (elements4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            elements4.setDoubleDir(destination.getDoubleDir());
            Elements elements5 = this.element;
            if (elements5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            elements5.setDir(destination.getVector().getZ());
            Elements elements6 = this.element;
            if (elements6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            elements6.setDirMode(2);
            Elements elements7 = this.element;
            if (elements7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            elements7.setGroup(destination.getName());
            if (Constants.POINT_TYPE_TABLE.equals(destination.getMode())) {
                Elements elements8 = this.element;
                if (elements8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("element");
                }
                elements8.setGroup("");
            }
            Elements elements9 = this.element;
            if (elements9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            Elements elements10 = this.element;
            if (elements10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            elements9.setHigh_precision(elements10.getHigh_precision());
            Elements elements11 = this.element;
            if (elements11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            elements11.setSort_weight(Integer.valueOf(destination.getSort_weight()));
            Elements elements12 = this.element;
            if (elements12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            elements12.setName(destination.getName());
            Elements elements13 = this.element;
            if (elements13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            elements13.setMode(destination.getMode());
            List<Elements> list = this.sourceElements;
            Elements elements14 = this.element;
            if (elements14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("element");
            }
            list.add(elements14);
        }
    }

    @Override // com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface
    public void saveChargeDockerList(List<DockerResult> dockerlist) {
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("dockerlist size is ");
        sb.append(dockerlist != null ? Integer.valueOf(dockerlist.size()) : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d("dockerlist", objArr);
        if (dockerlist != null) {
            for (DockerResult dockerResult : dockerlist) {
                this.element = new Elements();
                Elements elements = this.element;
                if (elements == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("element");
                }
                elements.setType("charging_pile");
                Elements elements2 = this.element;
                if (elements2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("element");
                }
                elements2.setId(dockerResult.getName());
                Elements elements3 = this.element;
                if (elements3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("element");
                }
                elements3.setAlign_dist(Double.valueOf(0.8d));
                Elements elements4 = this.element;
                if (elements4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("element");
                }
                elements4.setGroup(dockerResult.getName());
                DockerDetectResult docker = dockerResult.getDocker();
                Elements elements5 = this.element;
                if (elements5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("element");
                }
                elements5.setVector(new double[]{docker.getX(), docker.getY(), docker.getTheta()});
                List<Elements> list = this.chargesElements;
                Elements elements6 = this.element;
                if (elements6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("element");
                }
                list.add(elements6);
            }
        }
    }
}
