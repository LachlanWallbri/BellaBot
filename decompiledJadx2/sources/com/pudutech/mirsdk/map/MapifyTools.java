package com.pudutech.mirsdk.map;

import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdk.config.MapFilePathConfig;
import com.pudutech.mirsdk.map.elements.Source;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: MapifyTools.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/mirsdk/map/MapifyTools;", "", "()V", "TAG", "", "checkMapPackNotLegal", "", "file", "Ljava/io/File;", "checkNoRedundancyDestination", "floor", MapElement.Key.MAP, "Lcom/pudutech/mirsdk/map/MapDecode;", "isBase64", "str", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MapifyTools {
    public static final MapifyTools INSTANCE = new MapifyTools();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private MapifyTools() {
    }

    public final boolean isBase64(String str) {
        Intrinsics.checkParameterIsNotNull(str, "str");
        return Pattern.matches("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$", str);
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x02f1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean checkMapPackNotLegal(File file) {
        ZipInputStream zipInputStream;
        boolean isEmpty;
        List list;
        List list2;
        Intrinsics.checkParameterIsNotNull(file, "file");
        ZipInputStream zipInputStream2 = (ZipInputStream) null;
        int i = 1;
        try {
            zipInputStream = new ZipInputStream(new FileInputStream(file.getAbsolutePath()));
            try {
                Pdlog.m3273d(TAG, "path is " + file.getAbsolutePath() + "  1 name is " + file.getName());
                List mutableListOf = CollectionsKt.mutableListOf("optemap.yaml", "optemap.pgm", "ATLAS_DATA");
                List mutableListOf2 = CollectionsKt.mutableListOf(MapFilePathConfig.LOCATE_MAP_DATA_NAME, "ATLAS_DATA");
                List mutableListOf3 = CollectionsKt.mutableListOf("optemap.yaml", "optemap.pgm", "ATLAS_DATA");
                List mutableListOf4 = CollectionsKt.mutableListOf(MapFilePathConfig.SLAM_MAP_DATA_NAME);
                int i2 = 0;
                int i3 = 0;
                while (true) {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    String str = TAG;
                    Object[] objArr = new Object[i];
                    objArr[0] = "path is " + file.getAbsolutePath() + " 2 name is " + file.getName();
                    Pdlog.m3273d(str, objArr);
                    if (nextEntry == null) {
                        if (i2 == 0) {
                            Pdlog.m3273d(TAG, "filenumber is Empty " + i2);
                            zipInputStream.close();
                            return false;
                        }
                        if (i3 == 0 || i3 == 1) {
                            String str2 = TAG;
                            Object[] objArr2 = new Object[1];
                            StringBuilder sb = new StringBuilder();
                            sb.append("markerFileNeed.isNotEmpty() ");
                            sb.append(!mutableListOf2.isEmpty());
                            objArr2[0] = sb.toString();
                            Pdlog.m3273d(str2, objArr2);
                            isEmpty = mutableListOf2.isEmpty();
                        } else if (i3 == 2) {
                            String str3 = TAG;
                            Object[] objArr3 = new Object[1];
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("markerFileNeed.isNotEmpty() ");
                            sb2.append(!mutableListOf.isEmpty());
                            objArr3[0] = sb2.toString();
                            Pdlog.m3273d(str3, objArr3);
                            isEmpty = mutableListOf.isEmpty();
                        } else if (i3 == 3) {
                            String str4 = TAG;
                            Object[] objArr4 = new Object[1];
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append("markerFileNeed.isNotEmpty() ");
                            sb3.append(!mutableListOf3.isEmpty());
                            objArr4[0] = sb3.toString();
                            Pdlog.m3273d(str4, objArr4);
                            isEmpty = mutableListOf3.isEmpty();
                        } else if (i3 != 4) {
                            Pdlog.m3277w(TAG, "error sensor " + i3);
                            isEmpty = true;
                        } else {
                            isEmpty = mutableListOf4.isEmpty();
                        }
                        Pdlog.m3273d(TAG, "fileListIsNotEmpty is " + isEmpty);
                        zipInputStream.close();
                        return isEmpty;
                    }
                    i2++;
                    Pdlog.m3273d(TAG, "file number is " + i2);
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
                        i3 = ((MapConfig) new Gson().fromJson(new String(tmp, 0, tmp.length, Charsets.UTF_8), MapConfig.class)).getSensor();
                    } else {
                        if (Intrinsics.areEqual(nextEntry.getName(), "ATLAS_DATA")) {
                            MapDecode mapDecode = new MapDecode();
                            byte[] bArr2 = new byte[1024];
                            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                            while (true) {
                                int read2 = zipInputStream.read(bArr2);
                                if (read2 <= 0) {
                                    break;
                                }
                                List list3 = mutableListOf3;
                                List list4 = mutableListOf4;
                                byteArrayOutputStream2.write(bArr2, 0, read2);
                                mutableListOf3 = list3;
                                mutableListOf4 = list4;
                            }
                            byte[] tmp2 = byteArrayOutputStream2.toByteArray();
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append("");
                            Intrinsics.checkExpressionValueIsNotNull(tmp2, "tmp");
                            list = mutableListOf3;
                            list2 = mutableListOf4;
                            sb4.append(new String(tmp2, 0, tmp2.length, Charsets.UTF_8));
                            mapDecode.decode(sb4.toString());
                            try {
                                if (!checkNoRedundancyDestination("", mapDecode)) {
                                    zipInputStream.close();
                                    return false;
                                }
                            } catch (Exception e) {
                                e = e;
                                zipInputStream2 = zipInputStream;
                                try {
                                    Pdlog.m3274e(TAG, "check map legal failed: " + e.getMessage());
                                    if (zipInputStream2 != null) {
                                        zipInputStream2.close();
                                    }
                                    return false;
                                } catch (Throwable th) {
                                    th = th;
                                    zipInputStream = zipInputStream2;
                                    if (zipInputStream != null) {
                                        zipInputStream.close();
                                    }
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                if (zipInputStream != null) {
                                }
                                throw th;
                            }
                        } else {
                            list = mutableListOf3;
                            list2 = mutableListOf4;
                        }
                        zipInputStream.closeEntry();
                        mutableListOf3 = list;
                        mutableListOf4 = list2;
                    }
                    i = 1;
                }
            } catch (Exception e2) {
                e = e2;
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Exception e3) {
            e = e3;
        } catch (Throwable th4) {
            th = th4;
            zipInputStream = zipInputStream2;
            if (zipInputStream != null) {
            }
            throw th;
        }
    }

    public final boolean checkNoRedundancyDestination(String floor, MapDecode map) {
        Intrinsics.checkParameterIsNotNull(floor, "floor");
        Intrinsics.checkParameterIsNotNull(map, "map");
        ArrayList<Source> source = map.getSource();
        HashSet hashSet = new HashSet();
        Iterator<Source> it = source.iterator();
        while (it.hasNext()) {
            Source next = it.next();
            if (hashSet.contains(next.getId())) {
                Pdlog.m3277w(TAG, "destination " + next.getId() + " has existed in destination list 168");
                return false;
            }
            hashSet.add(next.getId());
        }
        Pdlog.m3273d(TAG, "destinationTable size:" + hashSet.size() + " 193");
        return true;
    }
}
