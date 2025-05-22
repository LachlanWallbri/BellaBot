package com.pudutech.mirsdk.map;

import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.compat.topo.FileName;
import com.pudutech.mirsdk.config.MapFilePathConfig;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MapCompatibale.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0012\n\u0000\u001a\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\u0007\u001a\b\u0010\u0005\u001a\u00020\u0003H\u0002\u001a\b\u0010\u0006\u001a\u00020\u0003H\u0002\u001a\u0006\u0010\u0007\u001a\u00020\b\u001a\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082D¢\u0006\u0002\n\u0000¨\u0006\r"}, m3961d2 = {"TAG", "", "checkNewMapDistribute", "", "map_root_path", "collectionMapFiles", "createScheduleConfigFile", "getStarterFromPuduConfig", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "readLocateMapBytes", "", "bytes", "", "MirFunction_packRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MapCompatibaleKt {
    private static final String TAG = "map";

    public static final int[] readLocateMapBytes(byte[] bytes) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        ByteBuffer musSize = ByteBuffer.wrap(bytes, 0, 16).order(ByteOrder.LITTLE_ENDIAN);
        Intrinsics.checkExpressionValueIsNotNull(musSize, "musSize");
        int i = (((int) musSize.getLong()) * ((int) musSize.getLong()) * 8) + 16;
        ByteBuffer musSize2 = ByteBuffer.wrap(bytes, i, 16).order(ByteOrder.LITTLE_ENDIAN);
        Intrinsics.checkExpressionValueIsNotNull(musSize2, "musSize");
        int i2 = i + 16 + (((int) musSize2.getLong()) * ((int) musSize2.getLong()) * 8);
        ByteBuffer musSize3 = ByteBuffer.wrap(bytes, i2, 4).order(ByteOrder.LITTLE_ENDIAN);
        Intrinsics.checkExpressionValueIsNotNull(musSize3, "musSize");
        int i3 = musSize3.getInt();
        IntBuffer asIntBuffer = ByteBuffer.wrap(bytes, i2 + 4, i3 * 4).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
        int[] iArr = new int[i3];
        asIntBuffer.get(iArr);
        ArraysKt.sort(iArr);
        return iArr;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0029, code lost:
    
        if ((r1.length == 0) != false) goto L13;
     */
    @Deprecated(message = "not need compatible")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void checkNewMapDistribute(String map_root_path) {
        File file;
        Intrinsics.checkParameterIsNotNull(map_root_path, "map_root_path");
        File file2 = (File) null;
        try {
            file = new File(map_root_path);
        } catch (Throwable th) {
            th = th;
            file = file2;
        }
        try {
            if (file.exists()) {
                if (file.exists()) {
                    String[] list = file.list();
                    Intrinsics.checkExpressionValueIsNotNull(list, "mapRootDir.list()");
                }
                file.mkdirs();
            }
            collectionMapFiles();
            File file3 = new File("/sdcard/PuduRobotMap/pdrobot.pdmap");
            if (file3.exists()) {
                file.mkdirs();
                File file4 = new File(map_root_path + '/' + StringBase64Kt.encodeMapName("pdrobot") + ".pdmap");
                FileChannel fileChannel = (FileChannel) null;
                FileChannel fileChannel2 = (FileChannel) null;
                try {
                    fileChannel = new FileInputStream(file3).getChannel();
                    fileChannel2 = new FileOutputStream(file4).getChannel();
                    if (fileChannel2 == null) {
                        Intrinsics.throwNpe();
                    }
                    FileChannel fileChannel3 = fileChannel;
                    if (fileChannel == null) {
                        Intrinsics.throwNpe();
                    }
                    fileChannel2.transferFrom(fileChannel3, 0L, fileChannel.size());
                    fileChannel.close();
                    fileChannel2.close();
                } catch (Throwable th2) {
                    if (fileChannel == null) {
                        Intrinsics.throwNpe();
                    }
                    fileChannel.close();
                    if (fileChannel2 == null) {
                        Intrinsics.throwNpe();
                    }
                    fileChannel2.close();
                    throw th2;
                }
            }
            file.mkdirs();
        } catch (Throwable th3) {
            th = th3;
            if (file != null) {
                file.mkdirs();
            }
            throw th;
        }
    }

    public static final Vector3d getStarterFromPuduConfig() {
        Vector3d vector3d = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
        File file = new File(MapFilePathConfig.DEFAULT_LOCATE_PATH);
        File file2 = null;
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            Intrinsics.checkExpressionValueIsNotNull(listFiles, "it.listFiles()");
            int length = listFiles.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                File file3 = listFiles[i];
                Intrinsics.checkExpressionValueIsNotNull(file3, "file");
                if (Intrinsics.areEqual(file3.getName(), "pudu_config.json")) {
                    file2 = file3;
                    break;
                }
                i++;
            }
        }
        if (file2 != null) {
            try {
                PuduConfigure puduConfigure = (PuduConfigure) new Gson().fromJson((Reader) new FileReader("/sdcard/PuduRobotMap/pudu_config.json"), PuduConfigure.class);
                vector3d.setX(puduConfigure.DELIVER_START_X);
                vector3d.setY(puduConfigure.DELIVER_START_Y);
                vector3d.setZ(puduConfigure.DELIVER_START_THETA);
            } catch (Exception e) {
                Pdlog.m3274e(TAG, "read pudu config file exception " + e.getLocalizedMessage());
            }
        }
        return vector3d;
    }

    private static final void collectionMapFiles() {
        File file;
        File it;
        File it2;
        File it3;
        FileInputStream fileInputStream;
        File file2 = new File(MapFilePathConfig.DEFAULT_LOCATE_PATH);
        if (file2.exists() && file2.isDirectory()) {
            File[] files = file2.listFiles();
            Intrinsics.checkExpressionValueIsNotNull(files, "files");
            int length = files.length;
            int i = 0;
            while (true) {
                file = null;
                if (i >= length) {
                    it = null;
                    break;
                }
                it = files[i];
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (it.getName().equals("ATLAS_DATA")) {
                    break;
                } else {
                    i++;
                }
            }
            int length2 = files.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length2) {
                    it2 = null;
                    break;
                }
                it2 = files[i2];
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                if (it2.getName().equals(MapFilePathConfig.MAP_DATA)) {
                    break;
                } else {
                    i2++;
                }
            }
            int length3 = files.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length3) {
                    it3 = null;
                    break;
                }
                it3 = files[i3];
                Intrinsics.checkExpressionValueIsNotNull(it3, "it");
                if (it3.getName().equals(MapFilePathConfig.LOCATE_MAP_DATA_NAME)) {
                    break;
                } else {
                    i3++;
                }
            }
            if (it == null || it2 == null || it3 == null) {
                return;
            }
            int length4 = files.length;
            int i4 = 0;
            while (true) {
                if (i4 >= length4) {
                    break;
                }
                File it4 = files[i4];
                Intrinsics.checkExpressionValueIsNotNull(it4, "it");
                String name = it4.getName();
                Intrinsics.checkExpressionValueIsNotNull(name, "it.name");
                if (StringsKt.contains$default((CharSequence) name, (CharSequence) "scheduling_config", false, 2, (Object) null)) {
                    file = it4;
                    break;
                }
                i4++;
            }
            if (file == null) {
                createScheduleConfigFile();
            }
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(MapFilePathConfig.DEFAULT_LOCATE_PATH + "pdrobot.pdmap"));
            byte[] bArr = new byte[1024];
            for (String str : CollectionsKt.mutableListOf("ATLAS_DATA", MapFilePathConfig.MAP_DATA, MapFilePathConfig.LOCATE_MAP_DATA_NAME, "scheduling_config.json", FileName.SCH_CONFIG_YAML)) {
                Pdlog.m3273d(TAG, "zip compress file " + str);
                if (new File(MapFilePathConfig.DEFAULT_LOCATE_PATH + str).exists()) {
                    zipOutputStream.putNextEntry(new ZipEntry(str));
                    fileInputStream = new FileInputStream(MapFilePathConfig.DEFAULT_LOCATE_PATH + str);
                    while (true) {
                        try {
                            try {
                                int read = fileInputStream.read(bArr);
                                if (read <= 0) {
                                    break;
                                } else {
                                    zipOutputStream.write(bArr, 0, read);
                                }
                            } catch (Exception e) {
                                Pdlog.m3274e(TAG, "zip compress pdmap exception: " + e.getLocalizedMessage());
                            }
                        } finally {
                        }
                    }
                    fileInputStream.close();
                    zipOutputStream.closeEntry();
                }
            }
            for (String str2 : CollectionsKt.mutableListOf("optemap.yaml", "optemap.pgm")) {
                Pdlog.m3273d(TAG, "zip compress file " + str2);
                if (new File(MapFilePathConfig.DEFAULT_EKF_PATH + str2).exists()) {
                    zipOutputStream.putNextEntry(new ZipEntry(str2));
                    fileInputStream = new FileInputStream(MapFilePathConfig.DEFAULT_EKF_PATH + str2);
                    while (true) {
                        try {
                            try {
                                int read2 = fileInputStream.read(bArr);
                                if (read2 <= 0) {
                                    break;
                                } else {
                                    zipOutputStream.write(bArr, 0, read2);
                                }
                            } catch (Exception e2) {
                                Pdlog.m3274e(TAG, "zip compress pdmap exception: " + e2.getLocalizedMessage());
                            }
                        } finally {
                        }
                    }
                    zipOutputStream.closeEntry();
                }
            }
            zipOutputStream.close();
        }
    }

    private static final void createScheduleConfigFile() {
        Vector3d starterFromPuduConfig = getStarterFromPuduConfig();
        ScheduleConfig scheduleConfig = new ScheduleConfig();
        double[][][] dArr = new double[1][];
        for (int i = 0; i < 1; i++) {
            double[][] dArr2 = new double[1];
            for (int i2 = 0; i2 < 1; i2++) {
                double[] dArr3 = new double[3];
                dArr3[0] = starterFromPuduConfig.getX();
                dArr3[1] = starterFromPuduConfig.getY();
                dArr3[2] = starterFromPuduConfig.getZ();
                dArr2[i2] = dArr3;
            }
            dArr[i] = dArr2;
        }
        scheduleConfig.setTaking_pose(dArr);
        Vector3d vector3d = new Vector3d(starterFromPuduConfig.getX() - 0.5d, starterFromPuduConfig.getY() + 0.5d, 0.0d);
        Vector3d vector3d2 = new Vector3d(starterFromPuduConfig.getX() + 0.5d, starterFromPuduConfig.getY() - 0.5d, 0.0d);
        double[][][] dArr4 = new double[1][];
        for (int i3 = 0; i3 < 1; i3++) {
            double[][] dArr5 = new double[2];
            double[] dArr6 = new double[3];
            dArr6[0] = vector3d.getX();
            dArr6[1] = vector3d.getY();
            dArr6[2] = vector3d.getZ();
            dArr5[0] = dArr6;
            double[] dArr7 = new double[3];
            dArr7[0] = vector3d2.getX();
            dArr7[1] = vector3d2.getY();
            dArr7[2] = vector3d2.getZ();
            dArr5[1] = dArr7;
            dArr4[i3] = dArr5;
        }
        scheduleConfig.setParking_regions(dArr4);
        byte[] bArr = new byte[256];
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/sdcard/PuduRobotMap/scheduling_config.json");
            String schConfig = new Gson().toJson(scheduleConfig);
            Intrinsics.checkExpressionValueIsNotNull(schConfig, "schConfig");
            Charset charset = Charsets.UTF_8;
            if (schConfig == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            byte[] bytes = schConfig.getBytes(charset);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            while (true) {
                int read = byteArrayInputStream.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    byteArrayInputStream.close();
                    fileOutputStream.close();
                    return;
                }
            }
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "create schedule config file exception: " + e.getLocalizedMessage());
        }
    }
}
