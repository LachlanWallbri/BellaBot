package com.pudutech.mirsdk.compat.topo;

import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdk.config.MapFilePathConfig;
import com.pudutech.mirsdk.map.StringBase64Kt;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: CompatMap.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013J\u0016\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0018J\u0010\u0010\u0019\u001a\u00020\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004J\u0006\u0010\u001a\u001a\u00020\u0013J\u001e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\bJ\u001e\u0010!\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\bJ\u001a\u0010\"\u001a\u00020\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u00042\b\u0010#\u001a\u0004\u0018\u00010\u0004J\u000e\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020&J\u0010\u0010'\u001a\u00020\b2\b\u0010(\u001a\u0004\u0018\u00010&J\u0010\u0010)\u001a\u0004\u0018\u00010\u00042\u0006\u0010*\u001a\u00020\u001cJ\u000e\u0010+\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020\u001cJ\u000e\u0010-\u001a\u00020\b2\u0006\u0010%\u001a\u00020&J\u000e\u0010.\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u001cJ\u000e\u0010/\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u001cJ\u000e\u00100\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u001cJ\u000e\u00101\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u001cJ\u000e\u00102\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u001cJ$\u00103\u001a\u00020\u00132\u0006\u00104\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\b2\f\u00105\u001a\b\u0012\u0004\u0012\u00020\u001c06J$\u00107\u001a\u00020\u00132\u0006\u00104\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\b2\f\u00105\u001a\b\u0012\u0004\u0012\u00020\u001c06J\u0012\u00108\u001a\u00020\u00132\n\u00109\u001a\u00060:j\u0002`;J\u0010\u0010<\u001a\u00020\u001c2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004J\u0010\u0010=\u001a\u00020\u001c2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004J\u001e\u0010>\u001a\u00020\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u00042\f\u00105\u001a\b\u0012\u0004\u0012\u00020\u001c0?J\u0018\u0010@\u001a\u00020\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0011\u001a\u00020\u001cJ\"\u0010A\u001a\u00020\u00132\u0006\u0010B\u001a\u00020C2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00042\b\u0010D\u001a\u0004\u0018\u00010\u0004J\"\u0010E\u001a\u00020\u00042\u0006\u0010B\u001a\u00020C2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00042\b\u0010D\u001a\u0004\u0018\u00010\u0004J\u001a\u0010F\u001a\u0004\u0018\u00010\u001c2\u0010\u0010G\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010HJ\u001a\u0010I\u001a\u00020\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006J"}, m3961d2 = {"Lcom/pudutech/mirsdk/compat/topo/CompatMap;", "", "()V", "TAG", "", "backup_path", "compatMapPath", "isDebug", "", "mapExtractPath", "mapPackConfigName", "mapRootPath", "oldMapExtractPath", "oldMapPath", "robotConfigJsonFilePath", "checkAtlasData", "", "json", "cleanOldExtractMaps", "", "compat", "compatMap1", "path", "hasParing", "", "compatMap2", "copyPuduRobotMap", "creatdSoureForParkingPose", "Lorg/json/JSONObject;", "index", "vector3d", "Lorg/json/JSONArray;", "b", "creatdSoureForTakingPose", "createConfigJsonFile", "fn", "createExtractFiles", "file", "Ljava/io/File;", "deleteDir", MapElement.Key.DIR, "getElementId", "jObj", "getMapElements", "root", "isBase64Valid", "isDiningOutlet", "isDiningOutletOrParking", "isGroup", "isParking", "isSource", "parseParkingPose", "jsonObject", "list", "", "parseTakingPose", "printStackTrace", C3898x.f4338g, "Ljava/lang/Exception;", "Lkotlin/Exception;", "readAtlas", "readSchConfig", "saveToAtlasFile", "", "saveToSchConfigJsonFile", "unZipEntry", "zipIs", "Ljava/util/zip/ZipInputStream;", "name", "unZipEntryAndReadString", "yamlToJson", MapElement.Key.MAP, "Ljava/util/LinkedHashMap;", "zipFile", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CompatMap {
    private final boolean isDebug;
    private final String TAG = "CompatMap";
    private final String mapPackConfigName = "/sdcard/pudu/config/map_pack.cfg";
    private final String mapRootPath = MapFilePathConfig.DEFAULT_MAP_LOCATE_PATH;
    private final String backup_path = "/sdcard/pudu/compat_backup";
    private final String mapExtractPath = "/sdcard/pudu/map/extract";
    private final String compatMapPath = "/sdcard/pudu/compatmap";
    private final String robotConfigJsonFilePath = MapFilePathConfig.CAMERA_CONFIG_PATH;
    private String oldMapPath = "/sdcard/PuduRobotMap";
    private final String oldMapExtractPath = "/sdcard/PuduRobotMap/extract";

    public final void compat() {
        Pdlog.m3274e(this.TAG, "CompatMap beging.........");
        cleanOldExtractMaps();
        try {
            File file = new File(this.mapRootPath);
            if (!file.exists()) {
                Pdlog.m3274e(this.TAG, "!file.exists()........." + this.mapRootPath);
                file.mkdirs();
            }
            copyPuduRobotMap();
            File[] listFiles = file.listFiles(new FilenameFilter() { // from class: com.pudutech.mirsdk.compat.topo.CompatMap$compat$lists$1
                @Override // java.io.FilenameFilter
                public final boolean accept(File file2, String name) {
                    Intrinsics.checkExpressionValueIsNotNull(name, "name");
                    return StringsKt.contains$default((CharSequence) name, (CharSequence) ".pdmap", false, 2, (Object) null);
                }
            });
            for (File f : listFiles) {
                if (!f.exists()) {
                    String str = this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append(" File f : lists !f.exists().........");
                    Intrinsics.checkExpressionValueIsNotNull(f, "f");
                    sb.append(f.getAbsolutePath());
                    Pdlog.m3274e(str, sb.toString());
                    return;
                }
                Intrinsics.checkExpressionValueIsNotNull(f, "f");
                if (!isBase64Valid(f)) {
                    Pdlog.m3275i(this.TAG, "base64 名称解析异常 " + f.getAbsolutePath() + " " + listFiles.length + " ---。");
                } else {
                    createExtractFiles(f);
                }
            }
        } catch (Exception e) {
            printStackTrace(e);
        }
        cleanOldExtractMaps();
        Pdlog.m3274e(this.TAG, "CompatMap end.........");
    }

    public final void copyPuduRobotMap() {
        File file = new File(this.oldMapPath, "pdrobot.pdmap");
        if (file.exists()) {
            String str = StringBase64Kt.encodeMapName(file.getName().subSequence(0, file.getName().length() - 6).toString()) + ".pdmap";
            if (new File(this.mapRootPath, str).exists()) {
                Pdlog.m3274e(this.TAG, "copyPuduRobotMap: 目标目录已存在同名地图，放弃copyPuduRobotMap兼容");
                return;
            }
            String str2 = this.oldMapPath;
            String name = file.getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "file.name");
            FileTools.copyZip(str2, name, this.mapRootPath, str, true);
        }
    }

    public final boolean createExtractFiles(File file) {
        ZipInputStream zipInputStream;
        Intrinsics.checkParameterIsNotNull(file, "file");
        String obj = file.getName().subSequence(0, file.getName().length() - 6).toString();
        String str = this.mapExtractPath + '/' + obj;
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        ZipInputStream zipInputStream2 = (ZipInputStream) null;
        try {
            try {
                zipInputStream = new ZipInputStream(new FileInputStream(file));
                try {
                    try {
                        ZipEntry zipEntry = (ZipEntry) null;
                        int[] iArr = (int[]) null;
                        boolean z = false;
                        while (true) {
                            try {
                                zipEntry = zipInputStream.getNextEntry();
                            } catch (Exception unused) {
                            }
                            if (zipEntry == null) {
                                break;
                            }
                            Pdlog.m3275i(this.TAG, "pdmap--->" + zipEntry.getName());
                            if (Intrinsics.areEqual(zipEntry.getName(), "ATLAS_DATA")) {
                                String unZipEntryAndReadString = unZipEntryAndReadString(zipInputStream, str, zipEntry.getName());
                                Pdlog.m3275i(this.TAG, "\n");
                                Pdlog.m3275i(this.TAG, "解压地图文件----》" + StringBase64Kt.decodeMapName(obj) + " files to " + file2.getAbsolutePath());
                                iArr = checkAtlasData(unZipEntryAndReadString);
                            } else {
                                if (Intrinsics.areEqual("config.json", zipEntry.getName())) {
                                    z = true;
                                }
                                unZipEntry(zipInputStream, str, zipEntry.getName());
                            }
                            zipInputStream.closeEntry();
                        }
                        if (iArr != null) {
                            if (!z) {
                                createConfigJsonFile(str, obj);
                            }
                            Pdlog.m3275i(this.TAG, "----地图检测结果  --->" + iArr[0] + " " + iArr[1] + " " + iArr[2]);
                            if (iArr[0] == 0) {
                                compatMap1(str, iArr[2]);
                            } else if (iArr[1] == 1) {
                                compatMap2(str);
                            }
                        } else {
                            Pdlog.m3274e(this.TAG, "----ATLAS_DATA 文件不存在  --->extract map " + StringBase64Kt.decodeMapName(obj) + " files to " + file2.getAbsolutePath() + " " + StringBase64Kt.encodeMapName(obj));
                        }
                        try {
                            zipInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return true;
                    } catch (Throwable th) {
                        th = th;
                        if (zipInputStream != null) {
                            try {
                                zipInputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Exception e3) {
                    e = e3;
                    zipInputStream2 = zipInputStream;
                    Pdlog.m3274e(this.TAG, e.toString());
                    printStackTrace(e);
                    if (zipInputStream2 == null) {
                        return false;
                    }
                    try {
                        zipInputStream2.close();
                        return false;
                    } catch (IOException e4) {
                        e4.printStackTrace();
                        return false;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                zipInputStream = zipInputStream2;
            }
        } catch (Exception e5) {
            e = e5;
        }
    }

    public final int[] checkAtlasData(String json) throws Exception {
        JSONArray mapElements = getMapElements(new JSONObject(json));
        int[] iArr = new int[3];
        int length = mapElements.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = mapElements.getJSONObject(i);
            Intrinsics.checkExpressionValueIsNotNull(jSONObject, "elements.getJSONObject(i)");
            if (isSource(jSONObject)) {
                if (isDiningOutlet(jSONObject)) {
                    iArr[0] = 1;
                    if (isGroup(jSONObject)) {
                        iArr[1] = 1;
                    }
                }
                if (isParking(jSONObject)) {
                    iArr[2] = 1;
                }
            }
            if (iArr[0] == 1 && iArr[1] == 1 && iArr[2] == 1) {
                break;
            }
        }
        return iArr;
    }

    public final boolean isSource(JSONObject jObj) {
        Intrinsics.checkParameterIsNotNull(jObj, "jObj");
        try {
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Intrinsics.areEqual(MapElement.Source.SOURCE, jObj.getString("type"));
    }

    public final boolean isDiningOutlet(JSONObject jObj) {
        Intrinsics.checkParameterIsNotNull(jObj, "jObj");
        try {
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Intrinsics.areEqual("dining_outlet", jObj.getString("mode"));
    }

    public final boolean isParking(JSONObject jObj) {
        Intrinsics.checkParameterIsNotNull(jObj, "jObj");
        try {
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Intrinsics.areEqual("parking", jObj.getString("mode"));
    }

    public final boolean isDiningOutletOrParking(JSONObject jObj) {
        String string;
        Intrinsics.checkParameterIsNotNull(jObj, "jObj");
        try {
            string = jObj.getString("mode");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (!Intrinsics.areEqual("dining_outlet", string)) {
            if (!Intrinsics.areEqual("parking", string)) {
                return false;
            }
        }
        return true;
    }

    public final boolean isGroup(JSONObject jObj) {
        Intrinsics.checkParameterIsNotNull(jObj, "jObj");
        try {
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Intrinsics.areEqual("", jObj.getString(MapElement.Key.GROUP));
    }

    public final String getElementId(JSONObject jObj) {
        Intrinsics.checkParameterIsNotNull(jObj, "jObj");
        String str = (String) null;
        try {
            return jObj.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
            return str;
        }
    }

    public final JSONObject readSchConfig(String path) throws Exception {
        boolean z;
        File file = new File(path, "scheduling_config.json");
        if (file.exists()) {
            z = false;
        } else {
            file = new File(path, FileName.SCH_CONFIG_YAML);
            if (!file.exists()) {
                throw new CustomException("sch_config not exists");
            }
            z = true;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bArr = new byte[fileInputStream.available()];
        if (z) {
            Yaml yaml = new Yaml();
            Pdlog.m3274e(this.TAG, "configFile -->yaml---> " + file.getAbsolutePath());
            Object load = yaml.load(fileInputStream);
            if (load == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.util.LinkedHashMap<*, *>");
            }
            fileInputStream.close();
            JSONObject yamlToJson = yamlToJson((LinkedHashMap) load);
            if (yamlToJson != null) {
                saveToSchConfigJsonFile(path, yamlToJson);
            } else {
                yamlToJson = new JSONObject();
            }
            return yamlToJson;
        }
        fileInputStream.read(bArr);
        fileInputStream.close();
        return new JSONObject(new String(bArr, Charsets.UTF_8));
    }

    public final JSONObject yamlToJson(LinkedHashMap<?, ?> map) {
        try {
            return new JSONObject(map);
        } catch (Exception e) {
            printStackTrace(e);
            return null;
        }
    }

    public final void createConfigJsonFile(String path, String fn) {
        File file = new File(this.robotConfigJsonFilePath);
        if (!file.exists()) {
            Pdlog.m3275i(this.TAG, this.robotConfigJsonFilePath + " not exits >>>>");
        }
        File file2 = new File(path, "config.json");
        if (!file2.getParentFile().exists()) {
            file2.getParentFile().mkdirs();
        }
        Pdlog.m3275i(this.TAG, "createConfigJsonFile--->" + file2.getAbsolutePath());
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            JSONObject jSONObject = new JSONObject(new String(bArr, Charsets.UTF_8));
            JSONObject jSONObject2 = new JSONObject();
            if (fn == null) {
                Intrinsics.throwNpe();
            }
            jSONObject2.put(ConfigJson.ALIAS, StringBase64Kt.decodeMapName(fn));
            jSONObject2.put(ConfigJson.AUTOEXP, false);
            jSONObject2.put("exposure_time", jSONObject.getInt("exposure_time"));
            jSONObject2.put(ConfigJson.IMAGE_FPS, jSONObject.getInt(ConfigJson.IMAGE_FPS));
            jSONObject2.put(ConfigJson.IMAGE_HEIGHT, jSONObject.getInt(ConfigJson.IMAGE_HEIGHT));
            jSONObject2.put(ConfigJson.IMAGE_WIDTH, jSONObject.getInt(ConfigJson.IMAGE_WIDTH));
            jSONObject2.put(ConfigJson.MAP_VERSION, 0);
            jSONObject2.put(ConfigJson.SENSOR, 1);
            FileUtils.writeStringToFile(file2, jSONObject2.toString(), Charsets.UTF_8);
        } catch (Exception e) {
            printStackTrace(e);
        }
    }

    public final void saveToSchConfigJsonFile(String path, JSONObject json) {
        Intrinsics.checkParameterIsNotNull(json, "json");
        try {
            File file = new File(path, "scheduling_config.json");
            FileUtils.writeStringToFile(file, json.toString(), Charsets.UTF_8);
            Pdlog.m3275i(this.TAG, "saveToSchConfigJsonFile ---> =" + file.getAbsolutePath());
        } catch (Exception e) {
            printStackTrace(e);
        }
    }

    public final void compatMap1(String path, int hasParing) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        try {
            Pdlog.m3275i(this.TAG, "-----兼容：地图文件没有停靠点时，从调度配置文件中读取停靠点------begin");
            JSONObject readSchConfig = readSchConfig(path);
            ArrayList arrayList = new ArrayList();
            if (readSchConfig.has(SchedulingConfigKey.USE_PARKING_FIND)) {
                int i = readSchConfig.getInt(SchedulingConfigKey.USE_PARKING_FIND);
                Pdlog.m3275i(this.TAG, "use_parking_find =" + i + " from path=" + path);
                if (i == 0) {
                    if (readSchConfig.has(SchedulingConfigKey.TAKING_POSE)) {
                        parseTakingPose(readSchConfig, false, arrayList);
                    }
                    if (hasParing == 0 && readSchConfig.has(SchedulingConfigKey.PARKING_POSE)) {
                        parseParkingPose(readSchConfig, false, arrayList);
                    }
                } else {
                    if (readSchConfig.has(SchedulingConfigKey.TAKING_POSE)) {
                        parseTakingPose(readSchConfig, true, arrayList);
                    }
                    if (hasParing == 0 && readSchConfig.has(SchedulingConfigKey.PARKING_POSE)) {
                        parseParkingPose(readSchConfig, true, arrayList);
                    }
                }
            }
            if (arrayList.size() > 0) {
                Pdlog.m3275i(this.TAG, "-----sch_config have data  FileName.ATLAS_DATA to atlas------");
                saveToAtlasFile(path, arrayList);
            }
            Pdlog.m3275i(this.TAG, "-----compatMap------end");
        } catch (Exception e) {
            printStackTrace(e);
        }
    }

    public final void compatMap2(String path) {
        Pdlog.m3275i(this.TAG, "-----兼容： 地图有停靠点但 group id 为空，根据调度配置文件解析停靠点------begin");
        try {
            JSONObject readSchConfig = readSchConfig(path);
            if (readSchConfig.has(SchedulingConfigKey.USE_PARKING_FIND)) {
                int i = readSchConfig.getInt(SchedulingConfigKey.USE_PARKING_FIND);
                JSONObject readAtlas = readAtlas(path);
                JSONArray mapElements = getMapElements(readAtlas);
                int length = mapElements.length();
                boolean z = false;
                for (int i2 = 0; i2 < length; i2++) {
                    JSONObject jSONObject = mapElements.getJSONObject(i2);
                    Intrinsics.checkExpressionValueIsNotNull(jSONObject, "elements.getJSONObject(i)");
                    if (isSource(jSONObject) && isDiningOutletOrParking(jSONObject)) {
                        if (i == 0) {
                            String elementId = getElementId(jSONObject);
                            if (elementId != null) {
                                jSONObject.put(MapElement.Key.GROUP, elementId);
                            }
                        } else {
                            jSONObject.put(MapElement.Key.GROUP, "default");
                        }
                        z = true;
                    }
                }
                String jSONObject2 = readAtlas.toString();
                Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "root.toString()");
                if (z) {
                    zipFile(path, jSONObject2);
                }
            }
        } catch (Exception e) {
            printStackTrace(e);
        }
    }

    public final void parseParkingPose(JSONObject jsonObject, boolean b, List<JSONObject> list) {
        Intrinsics.checkParameterIsNotNull(jsonObject, "jsonObject");
        Intrinsics.checkParameterIsNotNull(list, "list");
        Pdlog.m3275i(this.TAG, "------parseParkingPose------begin");
        try {
            Object obj = jsonObject.get(SchedulingConfigKey.PARKING_POSE);
            if (obj == null) {
                throw new TypeCastException("null cannot be cast to non-null type org.json.JSONArray");
            }
            JSONArray jSONArray = ((JSONArray) obj).getJSONArray(0);
            int length = jSONArray.length();
            if (length > 0) {
                for (int i = 0; i < length; i++) {
                    JSONArray jSONArray2 = jSONArray.getJSONArray(i);
                    Intrinsics.checkExpressionValueIsNotNull(jSONArray2, "obj.getJSONArray(index)");
                    list.add(creatdSoureForParkingPose(i, jSONArray2, b));
                }
            }
            Pdlog.m3275i(this.TAG, "------parseParkingPose------end  ___data len = " + length);
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "存在有taking_pose 但是[null]" + e);
        }
    }

    public final void parseTakingPose(JSONObject jsonObject, boolean b, List<JSONObject> list) {
        Intrinsics.checkParameterIsNotNull(jsonObject, "jsonObject");
        Intrinsics.checkParameterIsNotNull(list, "list");
        Pdlog.m3275i(this.TAG, "------parseTakingPose------begin");
        try {
            Object obj = jsonObject.get(SchedulingConfigKey.TAKING_POSE);
            if (obj == null) {
                throw new TypeCastException("null cannot be cast to non-null type org.json.JSONArray");
            }
            JSONArray jSONArray = ((JSONArray) obj).getJSONArray(0);
            int length = jSONArray.length();
            if (length > 0) {
                for (int i = 0; i < length; i++) {
                    JSONArray jSONArray2 = jSONArray.getJSONArray(i);
                    Intrinsics.checkExpressionValueIsNotNull(jSONArray2, "obj.getJSONArray(index)");
                    list.add(creatdSoureForTakingPose(i, jSONArray2, b));
                }
            }
            Pdlog.m3275i(this.TAG, "------parseTakingPose------end  ___data len = " + length);
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "存在有parseTakingPose 但是[null]" + e);
        }
    }

    public final JSONArray getMapElements(JSONObject root) throws Exception {
        Intrinsics.checkParameterIsNotNull(root, "root");
        JSONArray jSONArray = root.getJSONObject(MapElement.Key.MAP).getJSONArray(MapElement.Key.ELEMENTS);
        Intrinsics.checkExpressionValueIsNotNull(jSONArray, "map.getJSONArray(MapElement.Key.ELEMENTS)");
        return jSONArray;
    }

    public final JSONObject readAtlas(String path) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File(path, "ATLAS_DATA"));
        byte[] bArr = new byte[fileInputStream.available()];
        fileInputStream.read(bArr);
        fileInputStream.close();
        return new JSONObject(new String(bArr, Charsets.UTF_8));
    }

    public final void saveToAtlasFile(String path, List<? extends JSONObject> list) throws Exception {
        Intrinsics.checkParameterIsNotNull(list, "list");
        JSONObject readAtlas = readAtlas(path);
        JSONArray mapElements = getMapElements(readAtlas);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            mapElements.put(list.get(i));
        }
        String jSONObject = readAtlas.toString();
        Intrinsics.checkExpressionValueIsNotNull(jSONObject, "root.toString()");
        Pdlog.m3274e(this.TAG, "new altas---json = ------->" + jSONObject);
        zipFile(path, jSONObject);
        Pdlog.m3275i(this.TAG, "new altas---fileToZip ret = ------->  --->" + this.compatMapPath);
    }

    public final void zipFile(String path, String json) throws Exception {
        File file = new File(path, "ATLAS_DATA");
        FileUtils.writeStringToFile(file, json, Charsets.UTF_8);
        StringBuilder sb = new StringBuilder();
        File parentFile = file.getParentFile();
        Intrinsics.checkExpressionValueIsNotNull(parentFile, "newAtlasFile.parentFile");
        sb.append(parentFile.getName());
        sb.append(".pdmap");
        String sb2 = sb.toString();
        if (path == null) {
            Intrinsics.throwNpe();
        }
        boolean fileToZip = FileTools.fileToZip(path, this.compatMapPath, sb2);
        Pdlog.m3275i(this.TAG, "地图配置修改后重新压缩结果 ret=------> " + fileToZip + "    " + file.getAbsolutePath() + "--->count= ");
        if (!fileToZip || this.isDebug) {
            return;
        }
        boolean copyZip = FileTools.copyZip(this.mapRootPath, sb2, this.backup_path, sb2, true);
        Pdlog.m3275i(this.TAG, "先将原始文件拷贝到备份区  ret=------> " + copyZip + "    ");
        boolean copyZip2 = FileTools.copyZip(this.compatMapPath, sb2, this.mapRootPath, sb2, true);
        Pdlog.m3275i(this.TAG, "再将修改后的文件拷贝到map目录  ret=------> " + copyZip2 + "    ");
    }

    public final JSONObject creatdSoureForTakingPose(int index, JSONArray vector3d, boolean b) throws JSONException {
        Intrinsics.checkParameterIsNotNull(vector3d, "vector3d");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", MapElement.Source.SOURCE);
        jSONObject.put(MapElement.Key.VECTOR, vector3d);
        jSONObject.put(MapElement.Key.DIR, vector3d.get(2));
        jSONObject.put(MapElement.Key.DIR_MODE, 2);
        jSONObject.put("mode", "dining_outlet");
        if (b) {
            jSONObject.put(MapElement.Key.GROUP, "default");
        } else {
            jSONObject.put(MapElement.Key.GROUP, "dining_outlet" + index);
        }
        jSONObject.put("id", "dining_outlet" + index);
        return jSONObject;
    }

    public final JSONObject creatdSoureForParkingPose(int index, JSONArray vector3d, boolean b) throws JSONException {
        Intrinsics.checkParameterIsNotNull(vector3d, "vector3d");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", MapElement.Source.SOURCE);
        jSONObject.put(MapElement.Key.VECTOR, vector3d);
        jSONObject.put(MapElement.Key.DIR, vector3d.get(2));
        jSONObject.put(MapElement.Key.DIR_MODE, 2);
        jSONObject.put("mode", "parking");
        if (b) {
            jSONObject.put(MapElement.Key.GROUP, "default");
        } else {
            jSONObject.put(MapElement.Key.GROUP, MapElement.Source.PARKING_OUTLET + index);
        }
        jSONObject.put("id", MapElement.Source.PARKING_OUTLET + index);
        return jSONObject;
    }

    public final void unZipEntry(ZipInputStream zipIs, String path, String name) throws IOException {
        Intrinsics.checkParameterIsNotNull(zipIs, "zipIs");
        FileOutputStream fileOutputStream = new FileOutputStream(new File(path, name));
        byte[] bArr = new byte[1024];
        Ref.IntRef intRef = new Ref.IntRef();
        while (true) {
            int read = zipIs.read(bArr);
            intRef.element = read;
            if (read > 0) {
                fileOutputStream.write(bArr, 0, intRef.element);
            } else {
                fileOutputStream.close();
                return;
            }
        }
    }

    public final String unZipEntryAndReadString(ZipInputStream zipIs, String path, String name) throws IOException {
        Intrinsics.checkParameterIsNotNull(zipIs, "zipIs");
        FileOutputStream fileOutputStream = new FileOutputStream(new File(path, name));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        Ref.IntRef intRef = new Ref.IntRef();
        while (true) {
            int read = zipIs.read(bArr);
            intRef.element = read;
            if (read > 0) {
                fileOutputStream.write(bArr, 0, intRef.element);
                byteArrayOutputStream.write(bArr, 0, intRef.element);
            } else {
                byte[] tmp = byteArrayOutputStream.toByteArray();
                Intrinsics.checkExpressionValueIsNotNull(tmp, "tmp");
                String str = new String(tmp, Charsets.UTF_8);
                fileOutputStream.close();
                byteArrayOutputStream.close();
                return str;
            }
        }
    }

    public final boolean isBase64Valid(File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        return !Intrinsics.areEqual("", StringBase64Kt.decodeMapName(file.getName().subSequence(0, file.getName().length() - 6).toString()));
    }

    public final void cleanOldExtractMaps() {
        deleteDir(new File(this.mapExtractPath));
        deleteDir(new File(this.oldMapExtractPath));
    }

    public final boolean deleteDir(File dir) {
        if (dir == null) {
            return false;
        }
        if (!dir.exists()) {
            return true;
        }
        if (!dir.isDirectory()) {
            return false;
        }
        File[] listFiles = dir.listFiles();
        if (listFiles != null && listFiles.length != 0) {
            for (File file : listFiles) {
                Intrinsics.checkExpressionValueIsNotNull(file, "file");
                if (file.isFile()) {
                    if (!file.delete()) {
                        return false;
                    }
                } else if (file.isDirectory() && !deleteDir(file)) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public final void printStackTrace(Exception e) {
        Intrinsics.checkParameterIsNotNull(e, "e");
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            Pdlog.m3275i(this.TAG, "" + stackTraceElement.toString());
        }
    }
}
