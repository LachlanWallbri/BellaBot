package com.pudutech.mirsdk.mircore.localization.markerLocateNative;

import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.PuduLocateInterface;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatus;
import java.io.FileDescriptor;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MarkerLocateNative.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0010\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u001c\u001a\u00020\u0007H\u0086 J \u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u0006H\u0002J\u0019\u0010!\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0000H\u0086 J\u0006\u0010%\u001a\u00020\u000eJ\u0006\u0010&\u001a\u00020'J\t\u0010(\u001a\u00020)H\u0086 J\t\u0010*\u001a\u00020\u0006H\u0086 J\t\u0010+\u001a\u00020\u000eH\u0086 J\t\u0010,\u001a\u00020'H\u0086 J\u0011\u0010-\u001a\u00020\u00052\u0006\u0010.\u001a\u00020/H\u0086 J\t\u00100\u001a\u00020\u0005H\u0087 J\t\u00101\u001a\u00020\u0005H\u0086 J\u0011\u00102\u001a\u00020\u00072\u0006\u00103\u001a\u00020\u0005H\u0086 J\u000e\u00104\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u000eJ\u0011\u00105\u001a\u00020\u00072\u0006\u00106\u001a\u000207H\u0086 J9\u00108\u001a\u00020\u00072\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020:2\u0006\u0010<\u001a\u00020:2\u0006\u0010=\u001a\u00020:2\u0006\u0010>\u001a\u00020:2\u0006\u0010?\u001a\u00020:H\u0086 J\u0011\u0010@\u001a\u00020\u00052\u0006\u0010A\u001a\u00020/H\u0086 JA\u0010B\u001a\u00020\u00072\u0006\u0010C\u001a\u00020:2\u0006\u0010D\u001a\u00020:2\u0006\u0010E\u001a\u00020:2\u0006\u0010F\u001a\u00020:2\u0006\u0010G\u001a\u00020:2\u0006\u0010H\u001a\u00020:2\u0006\u0010I\u001a\u00020:H\u0086 R4\u0010\u0003\u001a\u001c\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R7\u0010\u0013\u001a\u001f\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006J"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/localization/markerLocateNative/MarkerLocateNative;", "", "()V", "cameraCalibrationListener", "Lkotlin/Function3;", "", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "", "getCameraCalibrationListener", "()Lkotlin/jvm/functions/Function3;", "setCameraCalibrationListener", "(Lkotlin/jvm/functions/Function3;)V", "getExposureHandle", "Lkotlin/Function0;", "", "getGetExposureHandle", "()Lkotlin/jvm/functions/Function0;", "setGetExposureHandle", "(Lkotlin/jvm/functions/Function0;)V", "setExposureHandle", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "exposure", "getSetExposureHandle", "()Lkotlin/jvm/functions/Function1;", "setSetExposureHandle", "(Lkotlin/jvm/functions/Function1;)V", "calibExStart", "calibrationCameraResult", SpeechUtility.TAG_RESOURCE_RESULT, "rpy", "pose", "createModules", "case", "Lcom/pudutech/mirsdk/mircore/PuduLocateInterface$LocateCase;", "locate", "getExposure", "getFullVersionInfo", "", "getLocalizationStatus", "Lcom/pudutech/mirsdk/mircore/coreparcel/LocalizationStatus;", "getRobotPosition", "getSlamStatus", "getVersionInfo", "initData", "config", "", "initModules", "isLocalizationFinishInitialization", "onswitchAutoExposeNative", "isauto", "setExposure", "setImageData", "file", "Ljava/io/FileDescriptor;", "setImuData", "accel_x", "", "accel_y", "accel_z", "gyro_x", "gyro_y", "gyro_z", "setLocateMap", "locate_map", "setOdomData", "enl", "enr", "x", "y", "theta", "line_speed", "angle_speed", "markerlocatenativelibrary_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MarkerLocateNative {
    public static final MarkerLocateNative INSTANCE = new MarkerLocateNative();
    private static Function3<? super Boolean, ? super Vector3d, ? super Vector3d, Unit> cameraCalibrationListener;
    private static Function0<Integer> getExposureHandle;
    private static Function1<? super Integer, Boolean> setExposureHandle;

    public final native void calibExStart();

    public final native void createModules(PuduLocateInterface.LocateCase r1, MarkerLocateNative locate);

    public final String getFullVersionInfo() {
        return "{\"marker\":\"commit: dbb1899, auth: “wujinlong”<“wujinlong@pudutech.com”>, time: “Sat Apr 2 20:57:19 2022 +0800”\"}";
    }

    public final native LocalizationStatus getLocalizationStatus();

    public final native Vector3d getRobotPosition();

    public final native int getSlamStatus();

    public final native String getVersionInfo();

    public final native boolean initData(byte[] config);

    @Deprecated(message = "旧架构初始化接口")
    public final native boolean initModules();

    public final native boolean isLocalizationFinishInitialization();

    public final native void onswitchAutoExposeNative(boolean isauto);

    public final native void setImageData(FileDescriptor file);

    public final native void setImuData(double accel_x, double accel_y, double accel_z, double gyro_x, double gyro_y, double gyro_z);

    public final native boolean setLocateMap(byte[] locate_map);

    public final native void setOdomData(double enl, double enr, double x, double y, double theta, double line_speed, double angle_speed);

    static {
        System.loadLibrary("markerLocate");
    }

    private MarkerLocateNative() {
    }

    public final Function1<Integer, Boolean> getSetExposureHandle() {
        return setExposureHandle;
    }

    public final void setSetExposureHandle(Function1<? super Integer, Boolean> function1) {
        setExposureHandle = function1;
    }

    public final Function0<Integer> getGetExposureHandle() {
        return getExposureHandle;
    }

    public final void setGetExposureHandle(Function0<Integer> function0) {
        getExposureHandle = function0;
    }

    public final boolean setExposure(int exposure) {
        Boolean invoke;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("setExposureHandle is null ");
        sb.append(setExposureHandle == null);
        objArr[0] = sb.toString();
        Pdlog.m3273d("MarkerJNI", objArr);
        Function1<? super Integer, Boolean> function1 = setExposureHandle;
        if (function1 == null || (invoke = function1.invoke(Integer.valueOf(exposure))) == null) {
            return false;
        }
        return invoke.booleanValue();
    }

    public final int getExposure() {
        Integer invoke;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("getExposureHandle is null ");
        sb.append(getExposureHandle == null);
        objArr[0] = sb.toString();
        Pdlog.m3273d("MarkerJNI", objArr);
        Function0<Integer> function0 = getExposureHandle;
        if (function0 == null || (invoke = function0.invoke()) == null) {
            return 0;
        }
        return invoke.intValue();
    }

    public final Function3<Boolean, Vector3d, Vector3d, Unit> getCameraCalibrationListener() {
        return cameraCalibrationListener;
    }

    public final void setCameraCalibrationListener(Function3<? super Boolean, ? super Vector3d, ? super Vector3d, Unit> function3) {
        cameraCalibrationListener = function3;
    }

    private final void calibrationCameraResult(boolean result, Vector3d rpy, Vector3d pose) {
        Function3<? super Boolean, ? super Vector3d, ? super Vector3d, Unit> function3 = cameraCalibrationListener;
        if (function3 == null) {
            return;
        }
        if (function3 != null) {
            function3.invoke(Boolean.valueOf(result), rpy, pose);
        }
        cameraCalibrationListener = (Function3) null;
    }
}
