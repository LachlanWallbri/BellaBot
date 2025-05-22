package com.pudutech.mirsdk.hardware.mcuupdate;

import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: ByteUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u0004\u0018\u00010\u00012\u0006\u0010\r\u001a\u00020\u000eJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eJ\u000e\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0015\u001a\u00020\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/mcuupdate/ByteUtils;", "", "()V", "HEXES", "", "HEX_INDICATOR", "SPACE", "TAG", "availaleSize", "", "getAvailaleSize", "()J", "ByteToObject", "bytes", "", "hexToString", "data", "int2byte", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_RES, "", "toByteArray", "obj", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class ByteUtils {
    public static final ByteUtils INSTANCE = new ByteUtils();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final String HEXES = HEXES;
    private static final String HEXES = HEXES;
    private static final String HEX_INDICATOR = HEX_INDICATOR;
    private static final String HEX_INDICATOR = HEX_INDICATOR;
    private static final String SPACE = SPACE;
    private static final String SPACE = SPACE;

    public final byte[] int2byte(int res) {
        return new byte[]{(byte) (res & 255), (byte) ((res >> 8) & 255), (byte) ((res >> 16) & 255), (byte) (res >>> 24)};
    }

    private ByteUtils() {
    }

    public final long getAvailaleSize() {
        File path = Environment.getExternalStorageDirectory();
        Intrinsics.checkExpressionValueIsNotNull(path, "path");
        StatFs statFs = new StatFs(path.getPath());
        return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
    }

    public final Object ByteToObject(byte[] bytes) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        Object obj = null;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            obj = objectInputStream.readObject();
            byteArrayInputStream.close();
            objectInputStream.close();
            return obj;
        } catch (Exception e) {
            System.out.println((Object) ("translation" + e.getMessage()));
            Pdlog.m3274e(TAG, "exception ", e.getLocalizedMessage(), " :", Log.getStackTraceString(e));
            return obj;
        }
    }

    public final byte[] toByteArray(Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "obj");
        byte[] bArr = (byte[]) null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            bArr = byteArrayOutputStream.toByteArray();
            objectOutputStream.close();
            byteArrayOutputStream.close();
            return bArr;
        } catch (IOException e) {
            e.printStackTrace();
            return bArr;
        }
    }

    public final String hexToString(byte[] data) {
        if (data == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (byte b : data) {
            sb.append(HEX_INDICATOR);
            sb.append(HEXES.charAt((b & 240) >> 4));
            sb.append(HEXES.charAt(b & 15));
            sb.append(SPACE);
        }
        return sb.toString();
    }
}
