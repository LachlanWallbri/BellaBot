package com.pudutech.mirsdk.mircore.localization.laserLocateNative;

import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.PolarCoordinates;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.PuduLocateInterface;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatus;
import java.io.FileDescriptor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: LaserLocateNative.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0017\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u001f\u001a\u00020\u0007H\u0086 J \u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u0006H\u0002J!\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00002\u0006\u0010(\u001a\u00020)H\u0086 J\u0006\u0010*\u001a\u00020\u000eJ\u0006\u0010+\u001a\u00020,J\t\u0010-\u001a\u00020.H\u0086 J\t\u0010/\u001a\u00020\u0006H\u0086 J\t\u00100\u001a\u000201H\u0086 J\t\u00102\u001a\u00020\u000eH\u0086 J\t\u00103\u001a\u00020,H\u0086 J\u0011\u00104\u001a\u00020\u00052\u0006\u00105\u001a\u000206H\u0086 J\u0011\u00107\u001a\u00020\u00052\u0006\u00105\u001a\u000206H\u0086 J\t\u00108\u001a\u00020\u0005H\u0086 J\u0011\u00109\u001a\u00020\u00072\u0006\u0010:\u001a\u00020\u0005H\u0086 J\t\u0010;\u001a\u00020\u0007H\u0086 J.\u0010<\u001a\u00020\u00072\u000e\u0010=\u001a\n\u0012\u0004\u0012\u00020,\u0018\u00010>2\u000e\u0010?\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010>H\u0086 ¢\u0006\u0002\u0010@J&\u0010A\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u00062\u000e\u0010B\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010>H\u0086 ¢\u0006\u0002\u0010CJ\t\u0010D\u001a\u00020\u0005H\u0086 J\u0006\u0010E\u001a\u00020\u0005J\u000e\u0010F\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u000eJ\u0019\u0010G\u001a\u00020\u00072\u0006\u0010H\u001a\u00020\u00062\u0006\u0010I\u001a\u00020\u0006H\u0086 J)\u0010J\u001a\u00020\u00072\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020\u000e2\u0006\u0010N\u001a\u00020\u000e2\u0006\u0010O\u001a\u00020PH\u0086 J\u0011\u0010Q\u001a\u00020\u00072\u0006\u0010K\u001a\u00020LH\u0086 J9\u0010R\u001a\u00020\u00072\u0006\u0010S\u001a\u0002012\u0006\u0010T\u001a\u0002012\u0006\u0010U\u001a\u0002012\u0006\u0010V\u001a\u0002012\u0006\u0010W\u001a\u0002012\u0006\u0010X\u001a\u000201H\u0086 J\t\u0010Y\u001a\u00020\u0007H\u0086 J\t\u0010Z\u001a\u00020\u0007H\u0086 J\u001c\u0010[\u001a\u00020\u00072\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020]0>H\u0086 ¢\u0006\u0002\u0010^J\u0019\u0010_\u001a\u00020\u00052\u0006\u0010`\u001a\u0002062\u0006\u0010a\u001a\u000206H\u0086 J\u0019\u0010b\u001a\u00020\u00052\u0006\u0010`\u001a\u0002062\u0006\u0010c\u001a\u00020,H\u0086 J\u0013\u0010d\u001a\u00020\u00072\b\u0010e\u001a\u0004\u0018\u00010,H\u0086 J\u0011\u0010f\u001a\u00020\u00052\u0006\u0010g\u001a\u000206H\u0086 JA\u0010h\u001a\u00020\u00072\u0006\u0010i\u001a\u0002012\u0006\u0010j\u001a\u0002012\u0006\u0010k\u001a\u0002012\u0006\u0010l\u001a\u0002012\u0006\u0010m\u001a\u0002012\u0006\u0010n\u001a\u0002012\u0006\u0010o\u001a\u000201H\u0086 J\u0011\u0010p\u001a\u00020\u00072\u0006\u0010q\u001a\u00020\u0005H\u0086 J\u0011\u0010r\u001a\u00020\u00072\u0006\u0010s\u001a\u00020\u0005H\u0086 R4\u0010\u0003\u001a\u001c\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R7\u0010\u0016\u001a\u001f\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006t"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/localization/laserLocateNative/LaserLocateNative;", "", "()V", "cameraCalibrationListener", "Lkotlin/Function3;", "", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "", "getCameraCalibrationListener", "()Lkotlin/jvm/functions/Function3;", "setCameraCalibrationListener", "(Lkotlin/jvm/functions/Function3;)V", "getExposureHandle", "Lkotlin/Function0;", "", "getGetExposureHandle", "()Lkotlin/jvm/functions/Function0;", "setGetExposureHandle", "(Lkotlin/jvm/functions/Function0;)V", "savePdMapHandle", "getSavePdMapHandle", "setSavePdMapHandle", "setExposureHandle", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "exposure", "getSetExposureHandle", "()Lkotlin/jvm/functions/Function1;", "setSetExposureHandle", "(Lkotlin/jvm/functions/Function1;)V", "calibExStart", "calibrationCameraResult", SpeechUtility.TAG_RESOURCE_RESULT, "rpy", "pose", "createModules", "case", "Lcom/pudutech/mirsdk/mircore/PuduLocateInterface$LocateCase;", "locate", "machineType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineModel;", "getExposure", "getFullVersionInfo", "", "getLocalizationStatus", "Lcom/pudutech/mirsdk/mircore/coreparcel/LocalizationStatus;", "getRobotPosition", "getRotatePlanning", "", "getSlamStatus", "getVersionInfo", "initData", "config", "", "initMarkerConfig", "isLocalizationFinishInitialization", "onswitchAutoExposeNative", "isauto", "relocalization", "relocalizationByPoints", "ids", "", "poses", "([Ljava/lang/String;[Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "relocalizationPointIsValid", "vaild_poses", "(Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;[Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)Z", "saveMarkerMap", "savePdMap", "setExposure", "setFloorElevatorPoint", "pose1", "pose2", "setFrontCameraData", "file", "Ljava/io/FileDescriptor;", "rows", "cols", "time", "", "setImageData", "setImuData", "accel_x", "accel_y", "accel_z", "gyro_x", "gyro_y", "gyro_z", "setInMapingMode", "setInRemapingMode", "setLaserData", "laser_points", "Lcom/pudutech/mirsdk/hardware/serialize/PolarCoordinates;", "([Lcom/pudutech/mirsdk/hardware/serialize/PolarCoordinates;)V", "setLidarLocateMap", "map_config", "map_pgm", "setLidarLocateMapByPath", "map_path", "setLidarSerialNumber", "lidar_sn", "setMarkerLocateMap", "locate_map", "setOdomData", "enl", "enr", "x", "y", "theta", "line_speed", "angle_speed", "setRotateEnd", "end_s", "setTaskStatus", "task_s", "laserlocatenativelibrary_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class LaserLocateNative {
    public static final LaserLocateNative INSTANCE = new LaserLocateNative();
    private static Function3<? super Boolean, ? super Vector3d, ? super Vector3d, Unit> cameraCalibrationListener;
    private static Function0<Integer> getExposureHandle;
    private static Function0<Boolean> savePdMapHandle;
    private static Function1<? super Integer, Boolean> setExposureHandle;

    public final native void calibExStart();

    public final native void createModules(PuduLocateInterface.LocateCase r1, LaserLocateNative locate, MachineModel machineType);

    public final String getFullVersionInfo() {
        return "{\"laser\":\"commit: af2f96b, auth: “wujinlong”<“wujinlong@pudutech.com”>, time: “Thu Dec 8 20:00:55 2022 +0800”\"}";
    }

    public final native LocalizationStatus getLocalizationStatus();

    public final native Vector3d getRobotPosition();

    public final native double getRotatePlanning();

    public final native int getSlamStatus();

    public final native String getVersionInfo();

    public final native boolean initData(byte[] config);

    public final native boolean initMarkerConfig(byte[] config);

    public final native boolean isLocalizationFinishInitialization();

    public final native void onswitchAutoExposeNative(boolean isauto);

    public final native void relocalization();

    public final native void relocalizationByPoints(String[] ids, Vector3d[] poses);

    public final native boolean relocalizationPointIsValid(Vector3d pose, Vector3d[] vaild_poses);

    public final native boolean saveMarkerMap();

    public final native void setFloorElevatorPoint(Vector3d pose1, Vector3d pose2);

    public final native void setFrontCameraData(FileDescriptor file, int rows, int cols, long time);

    public final native void setImageData(FileDescriptor file);

    public final native void setImuData(double accel_x, double accel_y, double accel_z, double gyro_x, double gyro_y, double gyro_z);

    public final native void setInMapingMode();

    public final native void setInRemapingMode();

    public final native void setLaserData(PolarCoordinates[] laser_points);

    public final native boolean setLidarLocateMap(byte[] map_config, byte[] map_pgm);

    public final native boolean setLidarLocateMapByPath(byte[] map_config, String map_path);

    public final native void setLidarSerialNumber(String lidar_sn);

    public final native boolean setMarkerLocateMap(byte[] locate_map);

    public final native void setOdomData(double enl, double enr, double x, double y, double theta, double line_speed, double angle_speed);

    public final native void setRotateEnd(boolean end_s);

    public final native void setTaskStatus(boolean task_s);

    static {
        System.loadLibrary("laserLocate");
    }

    private LaserLocateNative() {
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

    public final Function0<Boolean> getSavePdMapHandle() {
        return savePdMapHandle;
    }

    public final void setSavePdMapHandle(Function0<Boolean> function0) {
        savePdMapHandle = function0;
    }

    public final boolean setExposure(int exposure) {
        Boolean invoke;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("setExposureHandle is null ");
        sb.append(setExposureHandle == null);
        objArr[0] = sb.toString();
        Pdlog.m3273d("LocalJNI", objArr);
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
        Pdlog.m3273d("LocalJNI", objArr);
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

    public final boolean savePdMap() {
        Boolean invoke;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("savePdMapHandle is null ");
        sb.append(savePdMapHandle == null);
        objArr[0] = sb.toString();
        Pdlog.m3273d("LocalJNI", objArr);
        Function0<Boolean> function0 = savePdMapHandle;
        if (function0 == null || (invoke = function0.invoke()) == null) {
            return false;
        }
        return invoke.booleanValue();
    }
}
