package com.pudutech.mirsdk.map;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.aliyun.alink.linksdk.tmp.device.panel.data.InvokeServiceData;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import com.pudutech.mirsdk.aidl.serialize.CameraType;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.aidl.serialize.MapInfo;
import com.pudutech.mirsdk.compat.topo.ConfigJson;
import com.pudutech.mirsdk.compat.topo.FileName;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdk.config.MapFilePathConfig;
import com.pudutech.mirsdk.config.SDKConfig;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
import com.pudutech.mirsdk.locate.LocateHandle;
import com.pudutech.mirsdk.locate.LocateHandleFactory;
import com.pudutech.mirsdk.map.elements.OptemapConfig;
import com.pudutech.mirsdk.map.elements.Source;
import com.pudutech.mirsdk.mapify.util.CamerConfigHelper;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.apache.commons.io.LineIterator;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONObject;
import org.mozilla.javascript.ES6Iterator;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

/* compiled from: Atlas.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010W\u001a\u00020\nH\u0002J\u000e\u0010X\u001a\u00020\n2\u0006\u0010Y\u001a\u00020\u0004J\r\u0010Z\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010[J\u001c\u0010\\\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010]\u001a\u00020\u0004H\u0002J\u0010\u0010^\u001a\u00020\f2\u0006\u0010_\u001a\u00020`H\u0002J\b\u0010a\u001a\u00020\nH\u0002J$\u0010b\u001a\u0004\u0018\u00010\u00042\u001a\u0010c\u001a\u0016\u0012\u0004\u0012\u00020\t\u0018\u00010dj\n\u0012\u0004\u0012\u00020\t\u0018\u0001`eJ\b\u0010f\u001a\u00020\nH\u0002J\b\u0010g\u001a\u00020\nH\u0002J\u0016\u0010h\u001a\u00020\f2\u0006\u0010%\u001a\u00020&2\u0006\u0010i\u001a\u00020jJ\u0010\u0010k\u001a\u00020\n2\u0006\u0010l\u001a\u00020\u0004H\u0002J\u0006\u0010m\u001a\u000202J\u0012\u0010n\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010o\u001a\u00020\u0004J\f\u0010p\u001a\b\u0012\u0004\u0012\u00020?0qJ\u0012\u0010r\u001a\u0004\u0018\u00010?2\b\u0010]\u001a\u0004\u0018\u00010\u0004J\u0006\u0010s\u001a\u00020\fJ\u0010\u0010s\u001a\u0004\u0018\u0001022\u0006\u0010t\u001a\u00020\u0004J\u0012\u0010u\u001a\u00020\u00042\b\b\u0002\u0010Y\u001a\u00020\u0004H\u0002J\u0010\u0010v\u001a\u0004\u0018\u0001022\u0006\u0010w\u001a\u00020\u0004J\b\u0010x\u001a\u00020\nH\u0002J\u0015\u0010y\u001a\u0004\u0018\u00010\t2\u0006\u0010z\u001a\u00020\u0004¢\u0006\u0002\u0010{J\b\u0010|\u001a\u0004\u0018\u00010}J\b\u0010~\u001a\u0004\u0018\u00010\u007fJ\u0007\u0010\u0080\u0001\u001a\u00020\fJ\u0007\u0010\u0081\u0001\u001a\u00020\fJ\t\u0010\u0082\u0001\u001a\u00020\nH\u0002J\u000f\u0010\u0083\u0001\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010qJ\u001c\u0010\u0084\u0001\u001a\u00020\n2\u0007\u0010\u0085\u0001\u001a\u00020\u00042\n\b\u0002\u0010t\u001a\u0004\u0018\u00010\u0004J\t\u0010\u0086\u0001\u001a\u00020\nH\u0002J\u0019\u0010\u0087\u0001\u001a\u00020\f2\u0007\u0010\u0085\u0001\u001a\u00020\u00042\u0007\u0010\u0088\u0001\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R,\u0010\u0005\u001a \u0012\u0004\u0012\u00020\u0007\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\b\u0012\u0004\u0012\u00020\n0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0014\"\u0004\b\u001e\u0010\u0016R\u001a\u0010\u001f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0014\"\u0004\b!\u0010\u0016R\u001a\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020$0#X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010%\u001a\u00020&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001a\u00101\u001a\u000202X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u0014\u00107\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u0014R$\u0010:\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u0004@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u0014\"\u0004\b<\u0010\u0016R\u0014\u0010=\u001a\b\u0012\u0004\u0012\u00020?0>X\u0082\u000e¢\u0006\u0002\n\u0000R,\u0010@\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020A0>0#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u000e\u0010F\u001a\u00020GX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010I\u001a\u00020J8F¢\u0006\u0006\u001a\u0004\bK\u0010LR\u000e\u0010M\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001d\u0010N\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020O0#¢\u0006\b\n\u0000\u001a\u0004\bP\u0010CR\u001d\u0010Q\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020$0R8F¢\u0006\u0006\u001a\u0004\bS\u0010CR\u001a\u0010T\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010\u0014\"\u0004\bV\u0010\u0016¨\u0006\u0089\u0001"}, m3961d2 = {"Lcom/pudutech/mirsdk/map/Atlas;", "", "()V", "TAG", "", "addMapNameToList", "Lkotlin/Function2;", "Lcom/pudutech/mirsdk/map/MapConfig;", "Lkotlin/Pair;", "", "", ConfigJson.AUTOEXP, "", "getAutoexp", "()Z", "setAutoexp", "(Z)V", "backup_path", "defaultFloor", "getDefaultFloor", "()Ljava/lang/String;", "setDefaultFloor", "(Ljava/lang/String;)V", "defaultFloorIndex", "getDefaultFloorIndex", "()I", "setDefaultFloorIndex", "(I)V", "defaultTakingId", "getDefaultTakingId", "setDefaultTakingId", "errorReason", "getErrorReason", "setErrorReason", "floorsTopomapWithPathList", "", "Lcom/pudutech/mirsdk/map/FloorsMapList;", "locateCase", "Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;", "getLocateCase", "()Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;", "setLocateCase", "(Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;)V", "locateHandle", "Lcom/pudutech/mirsdk/locate/LocateHandle;", "getLocateHandle", "()Lcom/pudutech/mirsdk/locate/LocateHandle;", "setLocateHandle", "(Lcom/pudutech/mirsdk/locate/LocateHandle;)V", "mapDecode", "Lcom/pudutech/mirsdk/map/MapDecode;", "getMapDecode", "()Lcom/pudutech/mirsdk/map/MapDecode;", "setMapDecode", "(Lcom/pudutech/mirsdk/map/MapDecode;)V", "mapExtractPath", "getMapExtractPath", ES6Iterator.VALUE_PROPERTY, "mapFileName", "getMapFileName", "setMapFileName", "mapInfoList", "", "Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;", "mapNameList", "Lcom/pudutech/mirsdk/map/MapNameWithFloor;", "getMapNameList", "()Ljava/util/Map;", "setMapNameList", "(Ljava/util/Map;)V", "mapPackConfig", "Lcom/pudutech/mirsdk/map/MapPkgMgrConfig;", "mapPackConfigName", "mapPackageConfig", "Lcom/pudutech/mirsdk/aidl/serialize/MapPackageConfig;", "getMapPackageConfig", "()Lcom/pudutech/mirsdk/aidl/serialize/MapPackageConfig;", "mapRootPath", "markerList", "", "getMarkerList", "multiFloorsTopoMapList", "", "getMultiFloorsTopoMapList", "pdmapFullName", "getPdmapFullName", "setPdmapFullName", "backup", "checkConfigMap", "pdmap", "checkDefaultMap", "()Ljava/lang/Integer;", "checkFloorIDAndIndex", "mapName", "checkMapPackNotLegal", "file", "Ljava/io/File;", "checkMapPackageConfigFile", "checkMarkersFloor", "markers", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "checkScheduleConfig", "cleanOldExtractMaps", "extractMaps", "productMachineType", "Lcom/pudutech/mirsdk/hardware/serialize/ProductMachineType;", "findPereferenceConfig", "config", "getMap", "getMapConfig", "name", "getMapInfoList", "", "getMapVersion", "load", MapElement.Key.MAP, "loadPdmap", "loadSpecifyFloorMap", "topomapFile", "notSavePereferenceConfig", "parseExposureConfig", "newmap", "(Ljava/lang/String;)Ljava/lang/Integer;", "parseOptemapYaml", "Lcom/pudutech/mirsdk/map/elements/OptemapConfig;", "parseScheduleConfig", "Lcom/pudutech/mirsdk/map/ScheduleConfig;", "save", "saveCharge", "saveMapPackConfigPereference", "topoFileList", "updateDefaultMap", "floor", "updateMultiFloorsDefaultConfig", "updateSpecifyDefaultMap", "mapname", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class Atlas {
    private boolean autoexp;
    private int defaultFloorIndex;
    private String defaultTakingId;
    public MapDecode mapDecode;
    private final String TAG = MapElement.Key.MAP;
    private final String mapPackConfigName = "/sdcard/pudu/config/map_pack.cfg";
    private final String mapRootPath = MapFilePathConfig.DEFAULT_MAP_LOCATE_PATH;
    private final String backup_path = "/sdcard/pudu/backup";
    private final String mapExtractPath = this.mapRootPath + "/extract";
    private List<MapInfo> mapInfoList = new ArrayList();
    private String errorReason = "";
    private String defaultFloor = "0";
    private String mapFileName = "";
    private String pdmapFullName = "";
    private Map<String, List<MapNameWithFloor>> mapNameList = new LinkedHashMap();
    private final Map<String, int[]> markerList = new LinkedHashMap();
    private final Map<String, FloorsMapList> floorsTopomapWithPathList = new LinkedHashMap();
    private LocateCase locateCase = LocateCase.Marker;
    private LocateHandle locateHandle = LocateHandleFactory.INSTANCE.create();
    private final MapPkgMgrConfig mapPackConfig = new MapPkgMgrConfig(null, null, null, 4, null);
    private final Function2<MapConfig, Pair<String, Integer>, Unit> addMapNameToList = (Function2) new Function2<MapConfig, Pair<? extends String, ? extends Integer>, Unit>() { // from class: com.pudutech.mirsdk.map.Atlas$addMapNameToList$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(MapConfig mapConfig, Pair<? extends String, ? extends Integer> pair) {
            invoke2(mapConfig, (Pair<String, Integer>) pair);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(MapConfig mapConfig, Pair<String, Integer> floor) {
            Intrinsics.checkParameterIsNotNull(mapConfig, "mapConfig");
            Intrinsics.checkParameterIsNotNull(floor, "floor");
            if (Atlas.this.getMapNameList().containsKey(floor.getFirst())) {
                List<MapNameWithFloor> list = Atlas.this.getMapNameList().get(floor.getFirst());
                if (list != null) {
                    list.add(new MapNameWithFloor(mapConfig.getAlias(), floor.getFirst()));
                    return;
                }
                return;
            }
            Atlas.this.getMapNameList().put(floor.getFirst(), new ArrayList());
            List<MapNameWithFloor> list2 = Atlas.this.getMapNameList().get(floor.getFirst());
            if (list2 != null) {
                list2.add(new MapNameWithFloor(mapConfig.getAlias(), floor.getFirst()));
            }
        }
    };

    private final void checkScheduleConfig() {
    }

    public final String getMapExtractPath() {
        return this.mapExtractPath;
    }

    public final MapDecode getMapDecode() {
        MapDecode mapDecode = this.mapDecode;
        if (mapDecode == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mapDecode");
        }
        return mapDecode;
    }

    public final void setMapDecode(MapDecode mapDecode) {
        Intrinsics.checkParameterIsNotNull(mapDecode, "<set-?>");
        this.mapDecode = mapDecode;
    }

    public final String getErrorReason() {
        return this.errorReason;
    }

    public final void setErrorReason(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.errorReason = str;
    }

    public final String getDefaultFloor() {
        return this.defaultFloor;
    }

    public final void setDefaultFloor(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.defaultFloor = str;
    }

    public final int getDefaultFloorIndex() {
        return this.defaultFloorIndex;
    }

    public final void setDefaultFloorIndex(int i) {
        this.defaultFloorIndex = i;
    }

    public final String getMapFileName() {
        return this.mapFileName;
    }

    public final void setMapFileName(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.mapFileName = value;
        String encodeMapName = StringBase64Kt.encodeMapName(this.mapFileName);
        this.pdmapFullName = ((this.mapRootPath + "/") + encodeMapName) + ".pdmap";
        Pair<String, Integer> checkFloorIDAndIndex = checkFloorIDAndIndex(this.mapFileName);
        this.defaultFloor = checkFloorIDAndIndex.getFirst();
        this.defaultFloorIndex = checkFloorIDAndIndex.getSecond().intValue();
    }

    public final String getPdmapFullName() {
        return this.pdmapFullName;
    }

    public final void setPdmapFullName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.pdmapFullName = str;
    }

    public final Map<String, List<MapNameWithFloor>> getMapNameList() {
        return this.mapNameList;
    }

    public final void setMapNameList(Map<String, List<MapNameWithFloor>> map) {
        Intrinsics.checkParameterIsNotNull(map, "<set-?>");
        this.mapNameList = map;
    }

    public final Map<String, int[]> getMarkerList() {
        return this.markerList;
    }

    public final Map<String, FloorsMapList> getMultiFloorsTopoMapList() {
        return this.floorsTopomapWithPathList;
    }

    public final String getDefaultTakingId() {
        return this.defaultTakingId;
    }

    public final void setDefaultTakingId(String str) {
        this.defaultTakingId = str;
    }

    public final boolean getAutoexp() {
        return this.autoexp;
    }

    public final void setAutoexp(boolean z) {
        this.autoexp = z;
    }

    public final LocateCase getLocateCase() {
        return this.locateCase;
    }

    public final void setLocateCase(LocateCase locateCase) {
        Intrinsics.checkParameterIsNotNull(locateCase, "<set-?>");
        this.locateCase = locateCase;
    }

    public final LocateHandle getLocateHandle() {
        return this.locateHandle;
    }

    public final void setLocateHandle(LocateHandle locateHandle) {
        Intrinsics.checkParameterIsNotNull(locateHandle, "<set-?>");
        this.locateHandle = locateHandle;
    }

    public final com.pudutech.mirsdk.aidl.serialize.MapPackageConfig getMapPackageConfig() {
        Object obj;
        com.pudutech.mirsdk.aidl.serialize.MapPackageConfig mapPackageConfig = new com.pudutech.mirsdk.aidl.serialize.MapPackageConfig(null, null, null);
        mapPackageConfig.setDef_floor(this.mapPackConfig.getDef_floor());
        Iterator<T> it = this.mapPackConfig.getList().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((MapListConfig) obj).getFloor(), this.mapPackConfig.getDef_floor())) {
                break;
            }
        }
        MapListConfig mapListConfig = (MapListConfig) obj;
        mapPackageConfig.setDef_map(mapListConfig != null ? mapListConfig.getDefmap() : null);
        ArrayList arrayList = new ArrayList();
        for (MapListConfig mapListConfig2 : this.mapPackConfig.getList()) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (String str : mapListConfig2.getMaps()) {
                linkedHashMap.put(str, (this.mapRootPath + "/extract/") + StringBase64Kt.encodeMapName(str));
            }
            arrayList.add(new com.pudutech.mirsdk.aidl.serialize.MapListConfig(mapListConfig2.getFloor(), mapListConfig2.getFindex(), mapListConfig2.getDefmap(), linkedHashMap));
        }
        mapPackageConfig.setList(arrayList);
        return mapPackageConfig;
    }

    public final MapDecode getMap() {
        MapDecode mapDecode = this.mapDecode;
        if (mapDecode == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mapDecode");
        }
        return mapDecode;
    }

    public final boolean load() {
        this.errorReason = "";
        Pdlog.m3275i(MapElement.Key.MAP, "map opening " + this.mapFileName);
        try {
            this.mapFileName.equals("");
            String loadPdmap$default = loadPdmap$default(this, null, 1, null);
            if (loadPdmap$default.length() == 0) {
                this.errorReason = "topo map is empty";
                Pdlog.m3277w(this.TAG, String.valueOf(this.errorReason));
                return false;
            }
            this.mapDecode = new MapDecode();
            MapDecode mapDecode = this.mapDecode;
            if (mapDecode == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mapDecode");
            }
            mapDecode.decode(loadPdmap$default);
            if (checkFloorIDAndIndex(this.mapFileName).getSecond().intValue() == 0) {
                checkScheduleConfig();
            }
            if (this.defaultTakingId == null) {
                MapDecode mapDecode2 = this.mapDecode;
                if (mapDecode2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mapDecode");
                }
                Iterator<Source> it = mapDecode2.getSource().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Source next = it.next();
                    if (Intrinsics.areEqual(next.getMode(), "dining_outlet")) {
                        this.defaultTakingId = next.getId();
                        break;
                    }
                }
            }
            Pdlog.m3273d(this.TAG, "default taking id " + this.defaultTakingId);
            return true;
        } catch (Exception e) {
            this.errorReason = "map open fail: " + Log.getStackTraceString(e);
            Pdlog.m3274e(MapElement.Key.MAP, this.errorReason);
            return false;
        }
    }

    public final MapDecode load(String r6) {
        Intrinsics.checkParameterIsNotNull(r6, "map");
        Pdlog.m3275i(MapElement.Key.MAP, "map load " + r6);
        MapDecode mapDecode = null;
        try {
            r6.equals("");
            String loadPdmap = loadPdmap(r6);
            if (loadPdmap.length() == 0) {
                Pdlog.m3277w(this.TAG, "topo map load is empty");
            } else {
                MapDecode mapDecode2 = new MapDecode();
                mapDecode2.decode(loadPdmap);
                mapDecode = mapDecode2;
            }
        } catch (Exception e) {
            Pdlog.m3274e("map load", "map load fail: " + Log.getStackTraceString(e));
        }
        return mapDecode;
    }

    public final boolean save() {
        try {
            backup();
            String str = this.backup_path + '/' + StringBase64Kt.encodeMapName(this.mapFileName) + ".pdmap";
            MapDecode mapDecode = this.mapDecode;
            if (mapDecode == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mapDecode");
            }
            String jsonMap = mapDecode.toJsonMap();
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(this.pdmapFullName));
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(str));
            byte[] bArr = new byte[1024];
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry != null) {
                    zipOutputStream.putNextEntry(new ZipEntry(nextEntry.getName()));
                    if (Intrinsics.areEqual(nextEntry.getName(), "ATLAS_DATA")) {
                        Charset charset = Charsets.UTF_8;
                        if (jsonMap == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        byte[] bytes = jsonMap.getBytes(charset);
                        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                        Charset charset2 = Charsets.UTF_8;
                        if (jsonMap == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        byte[] bytes2 = jsonMap.getBytes(charset2);
                        Intrinsics.checkExpressionValueIsNotNull(bytes2, "(this as java.lang.String).getBytes(charset)");
                        zipOutputStream.write(bytes, 0, bytes2.length);
                    } else if (Intrinsics.areEqual(nextEntry.getName(), "config.json")) {
                        byte[] bArr2 = new byte[1024];
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        while (true) {
                            int read = zipInputStream.read(bArr2);
                            if (read <= 0) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr2, 0, read);
                        }
                        byte[] tmp = byteArrayOutputStream.toByteArray();
                        byteArrayOutputStream.close();
                        Intrinsics.checkExpressionValueIsNotNull(tmp, "tmp");
                        MapConfig mapConfig = (MapConfig) new Gson().fromJson(new String(tmp, 0, tmp.length, Charsets.UTF_8), MapConfig.class);
                        mapConfig.setMap_version(mapConfig.getMap_version() + 1);
                        String pack = new Gson().toJson(mapConfig);
                        Intrinsics.checkExpressionValueIsNotNull(pack, "pack");
                        Charset charset3 = Charsets.UTF_8;
                        if (pack == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        byte[] bytes3 = pack.getBytes(charset3);
                        Intrinsics.checkExpressionValueIsNotNull(bytes3, "(this as java.lang.String).getBytes(charset)");
                        byte[] bytes4 = pack.getBytes(Charsets.UTF_8);
                        Intrinsics.checkExpressionValueIsNotNull(bytes4, "(this as java.lang.String).getBytes(charset)");
                        zipOutputStream.write(bytes3, 0, bytes4.length);
                    } else {
                        while (true) {
                            int read2 = zipInputStream.read(bArr);
                            if (read2 <= 0) {
                                break;
                            }
                            zipOutputStream.write(bArr, 0, read2);
                        }
                    }
                    zipOutputStream.closeEntry();
                } else {
                    zipOutputStream.close();
                    zipInputStream.close();
                    Tools.execCommand(InvokeServiceData.CALL_TYPE_SYNC, true);
                    return true;
                }
            }
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "save error " + Log.getStackTraceString(e));
            return false;
        }
    }

    public final boolean saveCharge() {
        try {
            backup();
            String str = this.backup_path + '/' + StringBase64Kt.encodeMapName(this.mapFileName) + ".pdmap";
            MapDecode mapDecode = this.mapDecode;
            if (mapDecode == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mapDecode");
            }
            String chargePileJsonMap = mapDecode.toChargePileJsonMap();
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(this.pdmapFullName));
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(str));
            byte[] bArr = new byte[1024];
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry != null) {
                    zipOutputStream.putNextEntry(new ZipEntry(nextEntry.getName()));
                    if (Intrinsics.areEqual(nextEntry.getName(), "ATLAS_DATA")) {
                        Charset charset = Charsets.UTF_8;
                        if (chargePileJsonMap == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        byte[] bytes = chargePileJsonMap.getBytes(charset);
                        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                        Charset charset2 = Charsets.UTF_8;
                        if (chargePileJsonMap == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        byte[] bytes2 = chargePileJsonMap.getBytes(charset2);
                        Intrinsics.checkExpressionValueIsNotNull(bytes2, "(this as java.lang.String).getBytes(charset)");
                        zipOutputStream.write(bytes, 0, bytes2.length);
                    } else if (Intrinsics.areEqual(nextEntry.getName(), "config.json")) {
                        byte[] bArr2 = new byte[1024];
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        while (true) {
                            int read = zipInputStream.read(bArr2);
                            if (read <= 0) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr2, 0, read);
                        }
                        byte[] tmp = byteArrayOutputStream.toByteArray();
                        byteArrayOutputStream.close();
                        Intrinsics.checkExpressionValueIsNotNull(tmp, "tmp");
                        MapConfig mapConfig = (MapConfig) new Gson().fromJson(new String(tmp, 0, tmp.length, Charsets.UTF_8), MapConfig.class);
                        mapConfig.setMap_version(mapConfig.getMap_version() + 1);
                        String pack = new Gson().toJson(mapConfig);
                        Intrinsics.checkExpressionValueIsNotNull(pack, "pack");
                        Charset charset3 = Charsets.UTF_8;
                        if (pack == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        byte[] bytes3 = pack.getBytes(charset3);
                        Intrinsics.checkExpressionValueIsNotNull(bytes3, "(this as java.lang.String).getBytes(charset)");
                        byte[] bytes4 = pack.getBytes(Charsets.UTF_8);
                        Intrinsics.checkExpressionValueIsNotNull(bytes4, "(this as java.lang.String).getBytes(charset)");
                        zipOutputStream.write(bytes3, 0, bytes4.length);
                    } else {
                        while (true) {
                            int read2 = zipInputStream.read(bArr);
                            if (read2 <= 0) {
                                break;
                            }
                            zipOutputStream.write(bArr, 0, read2);
                        }
                    }
                    zipOutputStream.closeEntry();
                } else {
                    zipOutputStream.close();
                    zipInputStream.close();
                    Tools.execCommand(InvokeServiceData.CALL_TYPE_SYNC, true);
                    return true;
                }
            }
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "save error " + Log.getStackTraceString(e));
            return false;
        }
    }

    private final void backup() {
        File file = new File(String.valueOf(this.backup_path));
        if (!file.exists()) {
            file.mkdir();
        }
        Tools.execCommand("cp " + this.pdmapFullName + ' ' + this.backup_path + '/', false);
        Tools.execCommand(InvokeServiceData.CALL_TYPE_SYNC, true);
    }

    public final List<MapInfo> getMapInfoList() {
        return this.mapInfoList;
    }

    public final MapInfo getMapVersion(String mapName) {
        ZipInputStream zipInputStream;
        File file = new File(mapName);
        Pdlog.m3273d("pdmaplist", "name is " + file.getName());
        ZipInputStream zipInputStream2 = (ZipInputStream) null;
        try {
            zipInputStream = new ZipInputStream(new FileInputStream(file.getAbsolutePath()));
            try {
                MapInfo mapInfo = (MapInfo) null;
                while (true) {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry != null) {
                        if (Intrinsics.areEqual("config.json", nextEntry.getName())) {
                            byte[] bArr = new byte[1024];
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            while (true) {
                                int read = zipInputStream.read(bArr);
                                if (read <= 0) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, read);
                            }
                            byte[] tmp = byteArrayOutputStream.toByteArray();
                            byteArrayOutputStream.close();
                            Intrinsics.checkExpressionValueIsNotNull(tmp, "tmp");
                            MapConfig mapConfig = (MapConfig) new Gson().fromJson(new String(tmp, 0, tmp.length, Charsets.UTF_8), MapConfig.class);
                            String fileNameNoExtension = FileUtils.getFileNameNoExtension(file.getName());
                            Intrinsics.checkExpressionValueIsNotNull(fileNameNoExtension, "com.pudutech.mirsdk.map.…ameNoExtension(file.name)");
                            String decodeMapName = StringBase64Kt.decodeMapName(fileNameNoExtension);
                            int map_version = mapConfig.getMap_version();
                            byte[] fileMD5 = FileUtils.getFileMD5(file);
                            Intrinsics.checkExpressionValueIsNotNull(fileMD5, "com.pudutech.mirsdk.map.FileUtils.getFileMD5(file)");
                            mapInfo = new MapInfo(map_version, fileMD5, decodeMapName);
                        } else {
                            zipInputStream.closeEntry();
                        }
                    } else {
                        zipInputStream.close();
                        return mapInfo;
                    }
                }
            } catch (Throwable th) {
                th = th;
                if (zipInputStream != null) {
                    zipInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            zipInputStream = zipInputStream2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v16 */
    public final boolean extractMaps(LocateCase locateCase, ProductMachineType productMachineType) {
        File[] fileArr;
        int i;
        int i2;
        byte[] bArr;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        ByteArrayOutputStream byteArrayOutputStream;
        String str;
        ByteArrayOutputStream byteArrayOutputStream2;
        Intrinsics.checkParameterIsNotNull(locateCase, "locateCase");
        Intrinsics.checkParameterIsNotNull(productMachineType, "productMachineType");
        int i3 = 1;
        Pdlog.m3273d(this.TAG, "extractMaps localType : " + locateCase);
        cleanOldExtractMaps();
        try {
            File[] mapFiles = new File(this.mapRootPath).listFiles(new FilenameFilter() { // from class: com.pudutech.mirsdk.map.Atlas$extractMaps$mapFiles$1
                @Override // java.io.FilenameFilter
                public final boolean accept(File file, String str2) {
                    return str2 != null && StringsKt.contains$default((CharSequence) str2, (CharSequence) "pdmap", false, 2, (Object) null);
                }
            });
            byte[] bArr2 = new byte[1024];
            Intrinsics.checkExpressionValueIsNotNull(mapFiles, "mapFiles");
            if (mapFiles.length == 0) {
                this.errorReason = "not find map package";
                Pdlog.m3273d(this.TAG, this.errorReason);
                return false;
            }
            this.mapInfoList.clear();
            int length = mapFiles.length;
            int i4 = 0;
            while (i4 < length) {
                File file = mapFiles[i4];
                Intrinsics.checkExpressionValueIsNotNull(file, "file");
                if (checkMapPackNotLegal(file)) {
                    if (new File(this.backup_path + '/' + file.getName()).exists()) {
                        Tools.execCommand("mv " + this.backup_path + '/' + file.getName() + ' ' + file.getAbsoluteFile(), i3);
                        Tools.execCommand(InvokeServiceData.CALL_TYPE_SYNC, i3);
                        if (checkMapPackNotLegal(file)) {
                        }
                    }
                    fileArr = mapFiles;
                    bArr = bArr2;
                    i = length;
                    i2 = i4;
                    i4 = i2 + 1;
                    mapFiles = fileArr;
                    length = i;
                    bArr2 = bArr;
                    i3 = 1;
                }
                MapInfo mapVersion = getMapVersion(file.getAbsolutePath());
                if (mapVersion != null) {
                    String str2 = this.TAG;
                    Object[] objArr = new Object[i3];
                    objArr[0] = "map info: name " + mapVersion.getMapName() + "  mapVersion: " + mapVersion.getMapVersion() + ' ';
                    Pdlog.m3273d(str2, objArr);
                    this.mapInfoList.add(mapVersion);
                    Pdlog.m3273d(this.TAG, "mapInfoList size is  " + this.mapInfoList.size());
                }
                ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file.getAbsolutePath()));
                int length2 = file.getName().length() - 6;
                String obj = file.getName().subSequence(0, length2).toString();
                String str3 = (this.mapExtractPath + "/") + obj;
                File file2 = new File(str3);
                if (!file2.mkdirs()) {
                    this.errorReason = "fail to create extract path " + str3;
                    return false;
                }
                String str4 = this.TAG;
                fileArr = mapFiles;
                i = length;
                StringBuilder sb = new StringBuilder();
                i2 = i4;
                sb.append("extract map ");
                sb.append(StringBase64Kt.decodeMapName(obj));
                sb.append(" files to ");
                sb.append(file2.getAbsolutePath());
                Pdlog.m3273d(str4, sb.toString());
                boolean z5 = false;
                boolean z6 = false;
                while (true) {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry == null) {
                        bArr = bArr2;
                        z = z5;
                        z2 = z6;
                        break;
                    }
                    if (Intrinsics.areEqual(nextEntry.getName(), "ATLAS_DATA") || Intrinsics.areEqual(nextEntry.getName(), "config.json")) {
                        z3 = z5;
                    } else {
                        z3 = z5;
                        if (!Intrinsics.areEqual(nextEntry.getName(), MapFilePathConfig.MAP_DATA) && !Intrinsics.areEqual(nextEntry.getName(), "optemap.yaml") && !Intrinsics.areEqual(nextEntry.getName(), "optemap.pgm")) {
                            bArr = bArr2;
                            z4 = z6;
                            str = str3;
                            z5 = z3;
                            zipInputStream.closeEntry();
                            z6 = z4;
                            str3 = str;
                            bArr2 = bArr;
                        }
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(str3, nextEntry.getName()));
                    ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
                    z4 = z6;
                    while (true) {
                        int read = zipInputStream.read(bArr2);
                        if (read <= 0) {
                            break;
                        }
                        ByteArrayOutputStream byteArrayOutputStream4 = byteArrayOutputStream3;
                        byte[] bArr3 = bArr2;
                        String str5 = str3;
                        fileOutputStream.write(bArr3, 0, read);
                        if (Intrinsics.areEqual(nextEntry.getName(), "config.json")) {
                            byteArrayOutputStream2 = byteArrayOutputStream4;
                            byteArrayOutputStream2.write(bArr3, 0, read);
                        } else {
                            byteArrayOutputStream2 = byteArrayOutputStream4;
                        }
                        bArr2 = bArr3;
                        byteArrayOutputStream3 = byteArrayOutputStream2;
                        str3 = str5;
                    }
                    fileOutputStream.close();
                    if (Intrinsics.areEqual(nextEntry.getName(), "config.json")) {
                        byte[] tmp = byteArrayOutputStream3.toByteArray();
                        Intrinsics.checkExpressionValueIsNotNull(tmp, "tmp");
                        str = str3;
                        MapConfig mapConfig = (MapConfig) new Gson().fromJson(new String(tmp, 0, tmp.length, Charsets.UTF_8), MapConfig.class);
                        int sensor = mapConfig.getSensor();
                        String str6 = this.TAG;
                        StringBuilder sb2 = new StringBuilder();
                        bArr = bArr2;
                        sb2.append("productMachineType ");
                        sb2.append(productMachineType.getModel());
                        sb2.append(" sensor ");
                        sb2.append(sensor);
                        sb2.append("  alias ");
                        sb2.append(mapConfig.getAlias());
                        Pdlog.m3273d(str6, sb2.toString());
                        Pair<String, Integer> checkFloorIDAndIndex = checkFloorIDAndIndex(mapConfig.getAlias());
                        String str7 = this.TAG;
                        StringBuilder sb3 = new StringBuilder();
                        byteArrayOutputStream = byteArrayOutputStream3;
                        sb3.append("map ");
                        sb3.append(mapConfig.getAlias());
                        sb3.append(" in floor ");
                        sb3.append(checkFloorIDAndIndex.getFirst());
                        Pdlog.m3273d(str7, sb3.toString());
                        z2 = this.locateHandle.proceed(locateCase, productMachineType, sensor);
                        Pdlog.m3273d(this.TAG, "locateCase result=" + z2 + ' ' + file2);
                        if (z2) {
                            Function2<MapConfig, Pair<String, Integer>, Unit> function2 = this.addMapNameToList;
                            Intrinsics.checkExpressionValueIsNotNull(mapConfig, "mapConfig");
                            function2.invoke(mapConfig, checkFloorIDAndIndex);
                            z4 = z2;
                            z5 = true;
                        } else {
                            Pdlog.m3273d(this.TAG, "locateCase not match and delete " + file2);
                            FilesKt.deleteRecursively(file2);
                            z = true;
                            break;
                        }
                    } else {
                        byteArrayOutputStream = byteArrayOutputStream3;
                        bArr = bArr2;
                        str = str3;
                        z5 = z3;
                    }
                    byteArrayOutputStream.close();
                    zipInputStream.closeEntry();
                    z6 = z4;
                    str3 = str;
                    bArr2 = bArr;
                }
                zipInputStream.close();
                if (!z2 && !z) {
                    Pdlog.m3273d(this.TAG, "map name " + StringBase64Kt.decodeMapName("" + file.getName().subSequence(0, length2)));
                    String str8 = "" + file.getName().subSequence(0, length2);
                    String decodeMapName = StringBase64Kt.decodeMapName(str8);
                    if (new File(this.mapRootPath + '/' + str8 + ".pdmap").exists() && !this.mapNameList.containsKey(str8)) {
                        if (this.mapNameList.containsKey("0")) {
                            List<MapNameWithFloor> list = this.mapNameList.get("0");
                            if (list != null) {
                                list.add(new MapNameWithFloor(decodeMapName, "0"));
                            }
                        } else {
                            this.mapNameList.put("0", new ArrayList());
                            List<MapNameWithFloor> list2 = this.mapNameList.get("0");
                            if (list2 != null) {
                                list2.add(new MapNameWithFloor(decodeMapName, "0"));
                            }
                        }
                    }
                }
                i4 = i2 + 1;
                mapFiles = fileArr;
                length = i;
                bArr2 = bArr;
                i3 = 1;
            }
            File file3 = new File(this.mapExtractPath);
            if (file3.exists()) {
                File[] listFiles = file3.listFiles();
                Intrinsics.checkExpressionValueIsNotNull(listFiles, "extractFilesPath.listFiles()");
                if (!(listFiles.length == 0)) {
                    return true;
                }
            }
            this.errorReason = "not find legal map";
            Pdlog.m3277w(this.TAG, this.errorReason + " [machine locate " + locateCase + "] ");
            return false;
        } catch (Exception e) {
            this.errorReason = "load maps fail: " + e.getLocalizedMessage();
            Pdlog.m3274e(this.TAG, this.errorReason);
            return false;
        }
    }

    public final Integer checkDefaultMap() {
        Object obj;
        checkMapPackageConfigFile();
        Iterator<T> it = this.mapPackConfig.getList().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((MapListConfig) obj).getFloor(), this.mapPackConfig.getDef_floor())) {
                break;
            }
        }
        if (obj == null) {
            Intrinsics.throwNpe();
        }
        String defmap = ((MapListConfig) obj).getDefmap();
        saveMapPackConfigPereference();
        setMapFileName(defmap);
        this.pdmapFullName = ((this.mapRootPath + "/") + StringBase64Kt.encodeMapName(defmap)) + ".pdmap";
        checkConfigMap(this.pdmapFullName);
        if (!new File(this.pdmapFullName).exists()) {
            Pdlog.m3273d(this.TAG, "can't find " + this.pdmapFullName);
            File[] listFiles = new File(this.mapRootPath).listFiles();
            int length = listFiles.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                File file = listFiles[i];
                Intrinsics.checkExpressionValueIsNotNull(file, "file");
                String name = file.getName();
                Intrinsics.checkExpressionValueIsNotNull(name, "file.name");
                if (StringsKt.endsWith$default(name, ".pdmap", false, 2, (Object) null)) {
                    String absolutePath = file.getAbsolutePath();
                    Intrinsics.checkExpressionValueIsNotNull(absolutePath, "file.absolutePath");
                    this.pdmapFullName = absolutePath;
                    Pdlog.m3273d(this.TAG, "find another map " + this.pdmapFullName);
                    break;
                }
                i++;
            }
        }
        Pair<String, Integer> checkFloorIDAndIndex = checkFloorIDAndIndex(defmap);
        this.defaultFloor = checkFloorIDAndIndex.getFirst();
        this.defaultFloorIndex = checkFloorIDAndIndex.getSecond().intValue();
        return parseExposureConfig(this.mapFileName);
    }

    public final Integer parseExposureConfig(String newmap) {
        Intrinsics.checkParameterIsNotNull(newmap, "newmap");
        MapConfig mapConfig = getMapConfig(newmap);
        this.autoexp = mapConfig != null ? mapConfig.getAutoexp() : false;
        Tools.execCommand(InvokeServiceData.CALL_TYPE_SYNC, true);
        Tools.execCommand(InvokeServiceData.CALL_TYPE_SYNC, true);
        Tools.execCommand(InvokeServiceData.CALL_TYPE_SYNC, true);
        Integer valueOf = mapConfig != null ? Integer.valueOf(mapConfig.getSensor()) : null;
        if (valueOf != null && valueOf.intValue() == 1) {
            this.locateCase = LocateCase.Marker;
        } else if (valueOf != null && valueOf.intValue() == 2) {
            this.locateCase = LocateCase.Laser;
        } else if (valueOf != null && valueOf.intValue() == 3) {
            this.locateCase = LocateCase.LaserMark;
        } else if (valueOf != null && valueOf.intValue() == 4) {
            this.locateCase = LocateCase.Slamware;
        } else {
            this.locateCase = LocateCase.Marker;
        }
        if (mapConfig == null || mapConfig.getSensor() != 1 || mapConfig.getExposure_time() == 0) {
            return null;
        }
        return Integer.valueOf(mapConfig.getExposure_time());
    }

    public final MapDecode loadSpecifyFloorMap(String topomapFile) {
        Intrinsics.checkParameterIsNotNull(topomapFile, "topomapFile");
        File file = new File(topomapFile);
        if (!file.exists()) {
            return null;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bArr = new byte[1024];
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    byte[] tmp = byteArrayOutputStream.toByteArray();
                    MapDecode mapDecode = new MapDecode();
                    Intrinsics.checkExpressionValueIsNotNull(tmp, "tmp");
                    mapDecode.decode(new String(tmp, 0, tmp.length, Charsets.UTF_8));
                    return mapDecode;
                }
            }
        } catch (Exception e) {
            Pdlog.m3277w(this.TAG, "load map " + topomapFile + " error " + e.getLocalizedMessage());
            return null;
        } finally {
            fileInputStream.close();
        }
    }

    public final List<String> topoFileList() {
        if (this.floorsTopomapWithPathList.size() < 2) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, FloorsMapList> entry : this.floorsTopomapWithPathList.entrySet()) {
            arrayList.add(new FloorsTopomapPath(entry.getKey(), entry.getValue().getIndex(), entry.getValue().getPath()).toJson());
        }
        return arrayList;
    }

    public static /* synthetic */ void updateDefaultMap$default(Atlas atlas, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        atlas.updateDefaultMap(str, str2);
    }

    public final void updateDefaultMap(String floor, String r6) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(floor, "floor");
        Pdlog.m3273d(this.TAG, "update using default map " + floor + ' ' + r6);
        this.mapPackConfig.setDef_floor(floor);
        Object obj2 = null;
        if (r6 != null) {
            Iterator<T> it = this.mapPackConfig.getList().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                if (Intrinsics.areEqual(((MapListConfig) next).getFloor(), floor)) {
                    obj2 = next;
                    break;
                }
            }
            MapListConfig mapListConfig = (MapListConfig) obj2;
            if (mapListConfig != null) {
                mapListConfig.setDefmap(r6);
            }
            this.mapPackConfig.setDefmap(r6);
        } else {
            MapPkgMgrConfig mapPkgMgrConfig = this.mapPackConfig;
            Iterator<T> it2 = mapPkgMgrConfig.getList().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    obj = null;
                    break;
                } else {
                    obj = it2.next();
                    if (Intrinsics.areEqual(((MapListConfig) obj).getFloor(), floor)) {
                        break;
                    }
                }
            }
            MapListConfig mapListConfig2 = (MapListConfig) obj;
            mapPkgMgrConfig.setDefmap(mapListConfig2 != null ? mapListConfig2.getDefmap() : null);
        }
        updateMultiFloorsDefaultConfig();
        saveMapPackConfigPereference();
    }

    public final boolean updateSpecifyDefaultMap(String floor, String mapname) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(floor, "floor");
        Intrinsics.checkParameterIsNotNull(mapname, "mapname");
        Pdlog.m3273d(this.TAG, "update specified default map " + floor + ' ' + mapname);
        Iterator<T> it = this.mapPackConfig.getList().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((MapListConfig) obj).getFloor(), floor)) {
                break;
            }
        }
        MapListConfig mapListConfig = (MapListConfig) obj;
        if (Intrinsics.areEqual(mapname, mapListConfig != null ? mapListConfig.getDefmap() : null)) {
            return false;
        }
        if (mapListConfig != null) {
            mapListConfig.setDefmap(mapname);
        }
        updateMultiFloorsDefaultConfig();
        saveMapPackConfigPereference();
        return true;
    }

    public final String checkMarkersFloor(HashSet<Integer> markers) {
        if (markers == null) {
            return null;
        }
        Iterator<Integer> it = markers.iterator();
        while (it.hasNext()) {
            Integer next = it.next();
            Pdlog.m3273d(this.TAG, "markers: " + next);
        }
        if (markers.size() < 2) {
            return null;
        }
        for (Map.Entry<String, int[]> entry : this.markerList.entrySet()) {
            HashSet hashSet = new HashSet();
            Iterator<Integer> it2 = markers.iterator();
            while (it2.hasNext()) {
                hashSet.add(it2.next());
            }
            hashSet.retainAll(new HashSet(ArraysKt.asList(entry.getValue())));
            Pdlog.m3273d(this.TAG, "after retain set, markers size " + hashSet.size());
            Iterator it3 = hashSet.iterator();
            while (it3.hasNext()) {
                Integer num = (Integer) it3.next();
                Pdlog.m3273d(this.TAG, "after retain set, markers: " + num);
            }
            if (hashSet.size() >= 2) {
                Pdlog.m3273d(this.TAG, "check markers in floor " + entry.getKey());
                return entry.getKey();
            }
        }
        return null;
    }

    private final void checkMapPackageConfigFile() {
        this.mapPackConfig.getList().clear();
        File file = new File(this.mapPackConfigName);
        if (file.isDirectory() || !file.exists()) {
            notSavePereferenceConfig();
        } else {
            LineIterator lineIterator = org.apache.commons.io.FileUtils.lineIterator(file);
            String str = "";
            while (lineIterator.hasNext()) {
                str = lineIterator.nextLine();
                Intrinsics.checkExpressionValueIsNotNull(str, "apacheIOIterator.nextLine()");
            }
            lineIterator.close();
            if (Intrinsics.areEqual(str, "")) {
                notSavePereferenceConfig();
            } else {
                findPereferenceConfig(str);
            }
        }
        updateMultiFloorsDefaultConfig();
    }

    private final void updateMultiFloorsDefaultConfig() {
        for (MapListConfig mapListConfig : this.mapPackConfig.getList()) {
            Pair<String, Integer> checkFloorIDAndIndex = checkFloorIDAndIndex(mapListConfig.getDefmap());
            this.floorsTopomapWithPathList.put(mapListConfig.getFloor(), new FloorsMapList(mapListConfig.getFloor(), checkFloorIDAndIndex.getSecond().intValue(), ((this.mapExtractPath + "/") + StringBase64Kt.encodeMapName(mapListConfig.getDefmap())) + "/ATLAS_DATA"));
        }
    }

    private final Pair<String, Integer> checkFloorIDAndIndex(String mapName) {
        String str = mapName;
        int i = 0;
        String str2 = "0";
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) MqttTopic.MULTI_LEVEL_WILDCARD, false, 2, (Object) null)) {
            int indexOf$default = StringsKt.indexOf$default((CharSequence) str, '#', 0, false, 6, (Object) null);
            int i2 = indexOf$default + 1;
            int indexOf$default2 = StringsKt.indexOf$default((CharSequence) str, '#', i2, false, 4, (Object) null);
            if (indexOf$default2 != -1) {
                try {
                    if (mapName != null) {
                        String substring = mapName.substring(i2, indexOf$default2);
                        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        int parseInt = Integer.parseInt(substring);
                        if (mapName == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        String substring2 = mapName.substring(0, indexOf$default);
                        Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        i = parseInt;
                        str2 = substring2;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                } catch (Exception e) {
                    Pdlog.m3277w(this.TAG, "get floor index from map name " + mapName + " exception: " + e.getLocalizedMessage());
                }
            }
        }
        return new Pair<>(str2, Integer.valueOf(i));
    }

    private final void saveMapPackConfigPereference() {
        String json = new Gson().toJson(this.mapPackConfig);
        Pdlog.m3273d(this.TAG, "map pack config " + json);
        SharedPreferences.Editor edit = SDKConfig.INSTANCE.getPreferences().edit();
        edit.putString("map_package_config", json);
        edit.apply();
        org.apache.commons.io.FileUtils.writeStringToFile(new File(this.mapPackConfigName), json, Charsets.UTF_8);
        Tools.execCommand(InvokeServiceData.CALL_TYPE_SYNC, true);
        Tools.execCommand(InvokeServiceData.CALL_TYPE_SYNC, true);
        Tools.execCommand(InvokeServiceData.CALL_TYPE_SYNC, true);
    }

    private final void notSavePereferenceConfig() {
        String defaultMapFromPrefer;
        File file = new File(MapFilePathConfig.DEAULT_MAP_NAME_FILE);
        if (file.isDirectory()) {
            FilesKt.deleteRecursively(file);
        }
        if (!file.exists() || file.isDirectory()) {
            defaultMapFromPrefer = "";
        } else {
            LineIterator lineIterator = org.apache.commons.io.FileUtils.lineIterator(file);
            defaultMapFromPrefer = "";
            while (lineIterator.hasNext()) {
                defaultMapFromPrefer = lineIterator.nextLine();
                Intrinsics.checkExpressionValueIsNotNull(defaultMapFromPrefer, "apacheIOIterator.nextLine()");
            }
            lineIterator.close();
        }
        if (Intrinsics.areEqual(defaultMapFromPrefer, "")) {
            defaultMapFromPrefer = SDKConfig.INSTANCE.getPreferences().getString("defaultMap", "null");
        }
        if (!Intrinsics.areEqual(defaultMapFromPrefer, "null")) {
            Intrinsics.checkExpressionValueIsNotNull(defaultMapFromPrefer, "defaultMapFromPrefer");
            defaultMapFromPrefer = StringBase64Kt.decodeMapName(defaultMapFromPrefer);
        }
        Pdlog.m3273d(this.TAG, "notSavePereferenceConfig map name list size " + this.mapNameList.size());
        boolean z = false;
        for (Map.Entry<String, List<MapNameWithFloor>> entry : this.mapNameList.entrySet()) {
            List<MapNameWithFloor> value = entry.getValue();
            MapListConfig mapListConfig = new MapListConfig(entry.getKey(), 0, "", null, 8, null);
            ArrayList arrayList = new ArrayList();
            boolean z2 = z;
            boolean z3 = false;
            for (MapNameWithFloor mapNameWithFloor : value) {
                if (Intrinsics.areEqual(defaultMapFromPrefer, mapNameWithFloor.getName())) {
                    this.mapPackConfig.setDef_floor(mapNameWithFloor.getFloor());
                    this.mapPackConfig.setDefmap(mapNameWithFloor.getName());
                    mapListConfig.setDefmap(mapNameWithFloor.getName());
                    z3 = true;
                    z2 = true;
                }
                arrayList.add(mapNameWithFloor.getName());
            }
            if (!z3) {
                mapListConfig.setDefmap(arrayList.get(0));
            }
            mapListConfig.setFindex(checkFloorIDAndIndex(mapListConfig.getDefmap()).getSecond().intValue());
            mapListConfig.setMaps(arrayList);
            Pdlog.m3273d(this.TAG, "add map list config");
            this.mapPackConfig.getList().add(mapListConfig);
            z = z2;
        }
        CollectionsKt.sort(this.mapPackConfig.getList());
        Pdlog.m3273d(this.TAG, "list size " + this.mapPackConfig.getList().size());
        if (z) {
            return;
        }
        MapPkgMgrConfig mapPkgMgrConfig = this.mapPackConfig;
        mapPkgMgrConfig.setDef_floor(mapPkgMgrConfig.getList().get(0).getFloor());
        MapPkgMgrConfig mapPkgMgrConfig2 = this.mapPackConfig;
        mapPkgMgrConfig2.setDefmap(mapPkgMgrConfig2.getList().get(0).getDefmap());
    }

    private final void findPereferenceConfig(String config) {
        Object obj;
        String floor;
        Object obj2;
        String defmap;
        Object obj3;
        Object obj4;
        Object obj5;
        try {
            MapPkgMgrConfig mapPkgMgrConfig = (MapPkgMgrConfig) new Gson().fromJson(config, MapPkgMgrConfig.class);
            Pdlog.m3273d(this.TAG, "findPereferenceConfig map name list size " + this.mapNameList.size());
            boolean z = false;
            for (Map.Entry<String, List<MapNameWithFloor>> entry : this.mapNameList.entrySet()) {
                List<MapNameWithFloor> value = entry.getValue();
                MapListConfig mapListConfig = new MapListConfig(entry.getKey(), 0, "", null, 8, null);
                ArrayList arrayList = new ArrayList();
                Iterator<T> it = mapPkgMgrConfig.getList().iterator();
                while (true) {
                    if (it.hasNext()) {
                        obj4 = it.next();
                        if (Intrinsics.areEqual(((MapListConfig) obj4).getFloor(), entry.getKey())) {
                            break;
                        }
                    } else {
                        obj4 = null;
                        break;
                    }
                }
                MapListConfig mapListConfig2 = (MapListConfig) obj4;
                String defmap2 = mapListConfig2 != null ? mapListConfig2.getDefmap() : null;
                boolean z2 = false;
                for (MapNameWithFloor mapNameWithFloor : value) {
                    if (Intrinsics.areEqual(defmap2, mapNameWithFloor.getName())) {
                        mapListConfig.setDefmap(defmap2);
                        z2 = true;
                    }
                    arrayList.add(mapNameWithFloor.getName());
                }
                if (!z2) {
                    mapListConfig.setDefmap(arrayList.get(0));
                }
                mapListConfig.setFindex(checkFloorIDAndIndex(mapListConfig.getDefmap()).getSecond().intValue());
                mapListConfig.setMaps(arrayList);
                this.mapPackConfig.getList().add(mapListConfig);
                if (mapPkgMgrConfig.getDefmap() != null) {
                    Iterator<T> it2 = entry.getValue().iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            obj5 = it2.next();
                            if (Intrinsics.areEqual(((MapNameWithFloor) obj5).getName(), mapPkgMgrConfig.getDefmap())) {
                                break;
                            }
                        } else {
                            obj5 = null;
                            break;
                        }
                    }
                    if (obj5 != null) {
                        z = true;
                    }
                }
            }
            CollectionsKt.sort(this.mapPackConfig.getList());
            MapPkgMgrConfig mapPkgMgrConfig2 = this.mapPackConfig;
            Iterator<T> it3 = this.mapPackConfig.getList().iterator();
            while (true) {
                if (it3.hasNext()) {
                    obj = it3.next();
                    if (Intrinsics.areEqual(((MapListConfig) obj).getFloor(), mapPkgMgrConfig.getDef_floor())) {
                        break;
                    }
                } else {
                    obj = null;
                    break;
                }
            }
            MapListConfig mapListConfig3 = (MapListConfig) obj;
            if (mapListConfig3 == null || (floor = mapListConfig3.getFloor()) == null) {
                floor = this.mapPackConfig.getList().get(0).getFloor();
            }
            mapPkgMgrConfig2.setDef_floor(floor);
            MapPkgMgrConfig mapPkgMgrConfig3 = this.mapPackConfig;
            if (mapPkgMgrConfig.getDefmap() != null && z) {
                Iterator<T> it4 = this.mapPackConfig.getList().iterator();
                while (true) {
                    if (it4.hasNext()) {
                        obj3 = it4.next();
                        if (Intrinsics.areEqual(((MapListConfig) obj3).getFloor(), this.mapPackConfig.getDef_floor())) {
                            break;
                        }
                    } else {
                        obj3 = null;
                        break;
                    }
                }
                MapListConfig mapListConfig4 = (MapListConfig) obj3;
                if (mapListConfig4 != null) {
                    String defmap3 = mapPkgMgrConfig.getDefmap();
                    if (defmap3 == null) {
                        Intrinsics.throwNpe();
                    }
                    mapListConfig4.setDefmap(defmap3);
                }
                defmap = mapPkgMgrConfig.getDefmap();
                mapPkgMgrConfig3.setDefmap(defmap);
            }
            Iterator<T> it5 = this.mapPackConfig.getList().iterator();
            while (true) {
                if (it5.hasNext()) {
                    obj2 = it5.next();
                    if (Intrinsics.areEqual(((MapListConfig) obj2).getFloor(), mapPkgMgrConfig.getDef_floor())) {
                        break;
                    }
                } else {
                    obj2 = null;
                    break;
                }
            }
            MapListConfig mapListConfig5 = (MapListConfig) obj2;
            if (mapListConfig5 == null || (defmap = mapListConfig5.getDefmap()) == null) {
                defmap = this.mapPackConfig.getList().get(0).getDefmap();
            }
            mapPkgMgrConfig3.setDefmap(defmap);
        } catch (Exception e) {
            Pdlog.m3277w(this.TAG, "findPereferenceConfig happend exception: " + e.getMessage());
            notSavePereferenceConfig();
        }
    }

    public static /* synthetic */ MapConfig getMapConfig$default(Atlas atlas, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        return atlas.getMapConfig(str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00e6, code lost:
    
        if (r0 == null) goto L74;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final MapConfig getMapConfig(String name) {
        ZipInputStream zipInputStream;
        Intrinsics.checkParameterIsNotNull(name, "name");
        MapConfig mapConfig = (MapConfig) null;
        ZipInputStream zipInputStream2 = (ZipInputStream) null;
        try {
            try {
                if (name.length() == 0) {
                    zipInputStream = new ZipInputStream(new FileInputStream(this.pdmapFullName));
                } else {
                    zipInputStream = new ZipInputStream(new FileInputStream(((this.mapRootPath + "/") + StringBase64Kt.encodeMapName(name)) + ".pdmap"));
                }
                zipInputStream2 = zipInputStream;
                byte[] bArr = new byte[1024];
                while (true) {
                    if (zipInputStream2.getNextEntry() == null) {
                        break;
                    }
                    if (!(!Intrinsics.areEqual(r5.getName(), "config.json"))) {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        while (true) {
                            int read = zipInputStream2.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, read);
                        }
                        zipInputStream2.closeEntry();
                        byte[] tmp = byteArrayOutputStream.toByteArray();
                        Intrinsics.checkExpressionValueIsNotNull(tmp, "tmp");
                        mapConfig = (MapConfig) new Gson().fromJson(new String(tmp, 0, tmp.length, Charsets.UTF_8), MapConfig.class);
                    }
                }
            } catch (Exception e) {
                Pdlog.m3274e(this.TAG, "read map " + name + " config exception: " + e.getLocalizedMessage());
            }
            zipInputStream2.close();
            return mapConfig;
        } catch (Throwable th) {
            if (zipInputStream2 != null) {
                zipInputStream2.close();
            }
            throw th;
        }
    }

    private final void cleanOldExtractMaps() {
        this.mapNameList.clear();
        this.mapPackConfig.getList().clear();
        try {
            File file = new File(this.mapExtractPath);
            if (file.exists()) {
                File[] listFiles = file.listFiles();
                Intrinsics.checkExpressionValueIsNotNull(listFiles, "extractFilesPath.listFiles()");
                if (!(listFiles.length == 0)) {
                    if (file.isDirectory()) {
                        FilesKt.deleteRecursively(file);
                    } else {
                        file.delete();
                    }
                }
            }
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "clean old extract maps fail " + e.getLocalizedMessage());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x0139, code lost:
    
        if (r3.isEmpty() == false) goto L134;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean checkMapPackNotLegal(File file) {
        ZipInputStream zipInputStream;
        boolean z;
        boolean z2;
        ZipInputStream zipInputStream2 = (ZipInputStream) null;
        String decodeMapName = StringBase64Kt.decodeMapName(file.getName().subSequence(0, file.getName().length() - 6).toString());
        boolean z3 = true;
        try {
            try {
                zipInputStream = new ZipInputStream(new FileInputStream(file.getAbsolutePath()));
                try {
                    List mutableListOf = CollectionsKt.mutableListOf("optemap.yaml", "optemap.pgm");
                    List mutableListOf2 = CollectionsKt.mutableListOf(MapFilePathConfig.LOCATE_MAP_DATA_NAME);
                    List mutableListOf3 = CollectionsKt.mutableListOf("optemap.yaml", "optemap.pgm", MapFilePathConfig.LOCATE_MAP_DATA_NAME);
                    List mutableListOf4 = CollectionsKt.mutableListOf(MapFilePathConfig.SLAM_MAP_DATA_NAME);
                    int i = 0;
                    while (true) {
                        ZipEntry nextEntry = zipInputStream.getNextEntry();
                        if (nextEntry == null) {
                            z = false;
                            break;
                        }
                        if (mutableListOf.contains(nextEntry.getName())) {
                            mutableListOf.remove(nextEntry.getName());
                        }
                        if (mutableListOf2.contains(nextEntry.getName())) {
                            mutableListOf2.remove(nextEntry.getName());
                        }
                        if (mutableListOf3.contains(nextEntry.getName())) {
                            mutableListOf3.remove(nextEntry.getName());
                        }
                        if (mutableListOf4.contains(nextEntry.getName())) {
                            mutableListOf4.remove(nextEntry.getName());
                        }
                        if (Intrinsics.areEqual("config.json", nextEntry.getName())) {
                            byte[] bArr = new byte[1024];
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            while (true) {
                                int read = zipInputStream.read(bArr);
                                if (read <= 0) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, read);
                            }
                            byte[] tmp = byteArrayOutputStream.toByteArray();
                            byteArrayOutputStream.close();
                            Intrinsics.checkExpressionValueIsNotNull(tmp, "tmp");
                            MapConfig mapConfig = (MapConfig) new Gson().fromJson(new String(tmp, 0, tmp.length, Charsets.UTF_8), MapConfig.class);
                            if (!Intrinsics.areEqual(mapConfig.getAlias(), decodeMapName)) {
                                zipInputStream.closeEntry();
                                z = true;
                                break;
                            }
                            i = mapConfig.getSensor();
                        } else {
                            zipInputStream.closeEntry();
                        }
                    }
                    if (i != 0 && i != 1) {
                        if (i == 2) {
                            if (!mutableListOf.isEmpty()) {
                                z2 = true;
                            }
                            z2 = false;
                        } else if (i != 3) {
                            if (i == 4) {
                                if (!mutableListOf4.isEmpty()) {
                                }
                                z2 = false;
                            } else {
                                Pdlog.m3277w(this.TAG, "error sensor " + i);
                            }
                            z2 = true;
                        } else {
                            if (!mutableListOf3.isEmpty()) {
                                z2 = true;
                            }
                            z2 = false;
                        }
                    }
                    if (!z && !z2) {
                        z3 = false;
                    }
                    zipInputStream.close();
                    return z3;
                } catch (Exception e) {
                    e = e;
                    zipInputStream2 = zipInputStream;
                    Pdlog.m3274e(this.TAG, "check map legal failed: " + e.getMessage());
                    if (zipInputStream2 != null) {
                        zipInputStream2.close();
                    }
                    return true;
                } catch (Throwable th) {
                    th = th;
                    if (zipInputStream != null) {
                        zipInputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                zipInputStream = zipInputStream2;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0170  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void checkConfigMap(String pdmap) {
        Throwable th;
        ZipInputStream zipInputStream;
        Exception e;
        Intrinsics.checkParameterIsNotNull(pdmap, "pdmap");
        File file = new File(pdmap);
        ZipInputStream zipInputStream2 = (ZipInputStream) null;
        try {
            try {
                zipInputStream = new ZipInputStream(new FileInputStream(file.getAbsolutePath()));
                while (true) {
                    try {
                        ZipEntry nextEntry = zipInputStream.getNextEntry();
                        if (nextEntry == null) {
                            break;
                        }
                        if (Intrinsics.areEqual("config.json", nextEntry.getName())) {
                            byte[] bArr = new byte[1024];
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            while (true) {
                                int read = zipInputStream.read(bArr);
                                if (read <= 0) {
                                    break;
                                } else {
                                    byteArrayOutputStream.write(bArr, 0, read);
                                }
                            }
                            byte[] tmp = byteArrayOutputStream.toByteArray();
                            byteArrayOutputStream.close();
                            Intrinsics.checkExpressionValueIsNotNull(tmp, "tmp");
                            JSONObject jSONObject = new JSONObject(new String(tmp, 0, tmp.length, Charsets.UTF_8));
                            if (jSONObject.getString("camera_scheme").equals("1")) {
                                Pdlog.m3273d(this.TAG, "map camera type is FACE_CAMERA");
                                CamerConfigHelper.INSTANCE.setCameraType(CameraType.FACE_CAMERA);
                                CamerConfigHelper.INSTANCE.setCurrentType(CameraType.FACE_CAMERA);
                            } else {
                                Pdlog.m3273d(this.TAG, "map camera type is MARKER_CAMERA");
                                CamerConfigHelper.INSTANCE.setCameraType(CameraType.MARKER_CAMERA);
                                CamerConfigHelper.INSTANCE.setCurrentType(CameraType.MARKER_CAMERA);
                            }
                            try {
                                CamerConfigHelper camerConfigHelper = CamerConfigHelper.INSTANCE;
                                String string = jSONObject.getString("magic_sensor_left");
                                Intrinsics.checkExpressionValueIsNotNull(string, "msgObj.getString(\"magic_sensor_left\")");
                                camerConfigHelper.setMagic_sensor_left(Integer.parseInt(string));
                                CamerConfigHelper camerConfigHelper2 = CamerConfigHelper.INSTANCE;
                                String string2 = jSONObject.getString("magic_sensor_right");
                                Intrinsics.checkExpressionValueIsNotNull(string2, "msgObj.getString(\"magic_sensor_right\")");
                                camerConfigHelper2.setMagic_sensor_right(Integer.parseInt(string2));
                                Pdlog.m3273d(this.TAG, "magic_sensor_left is " + CamerConfigHelper.INSTANCE.getMagic_sensor_left() + " and magic_sensor_right is " + CamerConfigHelper.INSTANCE.getMagic_sensor_right() + ' ');
                            } catch (Exception e2) {
                                Pdlog.m3274e(this.TAG, "check map legal failed: " + e2.getMessage());
                                CamerConfigHelper.INSTANCE.setMagic_sensor_left(0);
                                CamerConfigHelper.INSTANCE.setMagic_sensor_right(0);
                            }
                        } else {
                            zipInputStream.closeEntry();
                        }
                    } catch (Exception e3) {
                        e = e3;
                        Pdlog.m3274e("camera_scheme", "check map legal failed: " + e.getMessage());
                        CamerConfigHelper.INSTANCE.setCameraType(CameraType.MARKER_CAMERA);
                        if (zipInputStream != null) {
                            zipInputStream.closeEntry();
                        }
                        if (zipInputStream == null) {
                            return;
                        }
                        zipInputStream.close();
                    }
                }
                zipInputStream.closeEntry();
            } catch (Exception e4) {
                zipInputStream = zipInputStream2;
                e = e4;
            } catch (Throwable th2) {
                th = th2;
                if (zipInputStream2 != null) {
                }
                if (zipInputStream2 != null) {
                }
                throw th;
            }
            zipInputStream.close();
        } catch (Throwable th3) {
            th = th3;
            if (zipInputStream2 != null) {
                zipInputStream2.closeEntry();
            }
            if (zipInputStream2 != null) {
                zipInputStream2.close();
            }
            throw th;
        }
    }

    static /* synthetic */ String loadPdmap$default(Atlas atlas, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        return atlas.loadPdmap(str);
    }

    private final String loadPdmap(String pdmap) {
        ZipInputStream zipInputStream;
        if (TextUtils.isEmpty(pdmap)) {
            Pdlog.m3273d(this.TAG, "pdmap name " + this.pdmapFullName);
            zipInputStream = new ZipInputStream(new FileInputStream(this.pdmapFullName));
        } else {
            String encodeMapName = StringBase64Kt.encodeMapName(pdmap);
            String str = ((this.mapRootPath + "/") + encodeMapName) + ".pdmap";
            Pdlog.m3273d(this.TAG, "pdmap name " + str);
            zipInputStream = new ZipInputStream(new FileInputStream(str));
        }
        byte[] bArr = new byte[1024];
        String str2 = "";
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry != null) {
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
                    sb.append(str2);
                    Intrinsics.checkExpressionValueIsNotNull(tmp, "tmp");
                    sb.append(new String(tmp, 0, tmp.length, Charsets.UTF_8));
                    str2 = sb.toString();
                }
                zipInputStream.closeEntry();
            } else {
                zipInputStream.close();
                return str2;
            }
        }
    }

    public final OptemapConfig parseOptemapYaml() {
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(this.pdmapFullName));
        byte[] bArr = new byte[1024];
        String str = "";
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                break;
            }
            if (Intrinsics.areEqual(nextEntry.getName(), "optemap.yaml")) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int read = zipInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byte[] tmp = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                Intrinsics.checkExpressionValueIsNotNull(tmp, "tmp");
                sb.append(new String(tmp, 0, tmp.length, Charsets.UTF_8));
                str = sb.toString();
            }
            zipInputStream.closeEntry();
        }
        zipInputStream.close();
        OptemapConfig optemapConfig = (OptemapConfig) null;
        Representer representer = new Representer();
        representer.getPropertyUtils().setSkipMissingProperties(true);
        return Intrinsics.areEqual(str, "") ^ true ? (OptemapConfig) new Yaml(representer).loadAs(str, OptemapConfig.class) : optemapConfig;
    }

    public final ScheduleConfig parseScheduleConfig() {
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(this.pdmapFullName));
        byte[] bArr = new byte[1024];
        String str = "";
        String str2 = str;
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                break;
            }
            if (Intrinsics.areEqual(nextEntry.getName(), FileName.SCH_CONFIG_YAML)) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int read = zipInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byte[] tmp = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                Intrinsics.checkExpressionValueIsNotNull(tmp, "tmp");
                sb.append(new String(tmp, 0, tmp.length, Charsets.UTF_8));
                str2 = sb.toString();
            } else if (Intrinsics.areEqual(nextEntry.getName(), "scheduling_config.json")) {
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                while (true) {
                    int read2 = zipInputStream.read(bArr);
                    if (read2 <= 0) {
                        break;
                    }
                    byteArrayOutputStream2.write(bArr, 0, read2);
                }
                byte[] tmp2 = byteArrayOutputStream2.toByteArray();
                byteArrayOutputStream2.close();
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                Intrinsics.checkExpressionValueIsNotNull(tmp2, "tmp");
                sb2.append(new String(tmp2, 0, tmp2.length, Charsets.UTF_8));
                str = sb2.toString();
            }
            zipInputStream.closeEntry();
        }
        zipInputStream.close();
        ScheduleConfig scheduleConfig = (ScheduleConfig) null;
        if (!Intrinsics.areEqual(str, "")) {
            try {
                scheduleConfig = (ScheduleConfig) new Gson().fromJson(str, ScheduleConfig.class);
            } catch (Exception e) {
                String str3 = this.TAG;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("parse schedule config with gson exception: ");
                sb3.append(e.getLocalizedMessage());
                sb3.append(" : ");
                StackTraceElement[] stackTrace = e.getStackTrace();
                Intrinsics.checkExpressionValueIsNotNull(stackTrace, "e.stackTrace");
                sb3.append(ArraysKt.contentDeepToString(stackTrace));
                Pdlog.m3277w(str3, sb3.toString());
                str = "";
            }
        }
        Representer representer = new Representer();
        representer.getPropertyUtils().setSkipMissingProperties(true);
        return (Intrinsics.areEqual(str, "") && (Intrinsics.areEqual(str2, "") ^ true)) ? (ScheduleConfig) new Yaml(representer).loadAs(str2, ScheduleConfig.class) : scheduleConfig;
    }
}
