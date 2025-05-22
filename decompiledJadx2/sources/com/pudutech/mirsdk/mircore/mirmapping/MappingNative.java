package com.pudutech.mirsdk.mircore.mirmapping;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.PolarCoordinates;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.coreparcel.CruisePath;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import com.pudutech.mirsdk.mircore.coreparcel.TopoPath;
import java.io.FileDescriptor;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: MappingNative.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0014\u001a\u00020\u0015H\u0086 J\u0011\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0005H\u0086 J\u0011\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u000fH\u0086 J\u0017\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0086 J\u0011\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u0000H\u0086 J\t\u0010!\u001a\u00020\u0015H\u0086 J\u0017\u0010\"\u001a\u00020#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0086 J\t\u0010%\u001a\u00020\u0005H\u0086 J\u0006\u0010&\u001a\u00020\u0005J\t\u0010'\u001a\u00020(H\u0086 J\u0011\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u000fH\u0086 J\t\u0010,\u001a\u00020\u000fH\u0086 J\t\u0010-\u001a\u00020\u0005H\u0086 J\t\u0010.\u001a\u00020\u001eH\u0086 J%\u0010/\u001a\u0002002\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\f\u00102\u001a\b\u0012\u0004\u0012\u0002030\u001dH\u0086 J'\u00104\u001a\u00020\u00152\b\u00105\u001a\u0004\u0018\u0001062\b\u00107\u001a\u0004\u0018\u0001062\b\u00108\u001a\u0004\u0018\u000106H\u0086 J#\u00109\u001a\u00020\u00152\b\u0010:\u001a\u0004\u0018\u0001002\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000206H\u0086 J!\u0010;\u001a\u00020\u000f2\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u0002062\u0006\u0010?\u001a\u000206H\u0086 J\t\u0010@\u001a\u00020\u000fH\u0086 J\u0011\u0010A\u001a\u00020\u00152\u0006\u0010B\u001a\u00020\u000fH\u0086 J\u0011\u0010C\u001a\u00020\u00152\u0006\u0010D\u001a\u00020*H\u0086 J\u0011\u0010E\u001a\u00020\u00152\u0006\u0010D\u001a\u00020*H\u0086 J\u0011\u0010F\u001a\u00020\u00152\u0006\u0010D\u001a\u00020*H\u0086 J\u0011\u0010G\u001a\u0002002\u0006\u0010H\u001a\u00020IH\u0086 J\u000e\u0010J\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u0005J)\u0010K\u001a\u00020\u00152\u0006\u0010D\u001a\u00020*2\u0006\u0010L\u001a\u00020\u00052\u0006\u0010M\u001a\u00020\u00052\u0006\u0010N\u001a\u00020OH\u0086 J\u0011\u0010P\u001a\u00020\u00152\u0006\u0010D\u001a\u00020*H\u0086 J9\u0010Q\u001a\u00020\u00152\u0006\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020S2\u0006\u0010U\u001a\u00020S2\u0006\u0010V\u001a\u00020S2\u0006\u0010W\u001a\u00020S2\u0006\u0010X\u001a\u00020SH\u0086 J\u001c\u0010Y\u001a\u00020\u00152\f\u0010Z\u001a\b\u0012\u0004\u0012\u00020\\0[H\u0086 ¢\u0006\u0002\u0010]J1\u0010^\u001a\u00020\u00152\u0006\u0010_\u001a\u00020S2\u0006\u0010`\u001a\u00020S2\u0006\u0010a\u001a\u00020S2\u0006\u0010b\u001a\u00020S2\u0006\u0010c\u001a\u00020SH\u0086 J\t\u0010d\u001a\u00020\u0015H\u0086 R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR7\u0010\n\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006e"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/mirmapping/MappingNative;", "", "()V", "getExposureHandle", "Lkotlin/Function0;", "", "getGetExposureHandle", "()Lkotlin/jvm/functions/Function0;", "setGetExposureHandle", "(Lkotlin/jvm/functions/Function0;)V", "setExposureHandle", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "exposure", "", "getSetExposureHandle", "()Lkotlin/jvm/functions/Function1;", "setSetExposureHandle", "(Lkotlin/jvm/functions/Function1;)V", "cancelMapping", "", "checkMapLimit", "mg", "checkMarkerVisible", "is_end", "checkVirtualWall", "", "walls", "", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "createModule", "mapping", "finishMapping", "getCruisePath", "Lcom/pudutech/mirsdk/mircore/coreparcel/CruisePath;", "cruise_poses", "getDetectMarkerId", "getExposure", "getLoclizationMap", "", "getMapData", "Ljava/io/FileDescriptor;", "is_final", "getMappingOptStatus", "getMatchPreMapStatus", "getRobotPose", "getTopoPath", "Lcom/pudutech/mirsdk/mircore/coreparcel/TopoPath;", "poses", "ele_list", "Lcom/pudutech/mirsdk/mircore/coreparcel/Destination;", "initExtendMapping", "mapBytes", "", "configBytes", "markerBytes", "initGenTopoPath", "old_topo_path", "initModule", "machineType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineModel;", "marker_config", "mapping_config", "isModuleInited", "onswitchAutoExpose", "isauto", "processDownRGBD", "file", "processLeftRGBD", "processRightRGBD", "resetDualPath", "ill_track_id", "", "setExposure", "setFrontCameraData", "rows", "cols", "time", "", "setImageData", "setImuData", "accel_x", "", "accel_y", "accel_z", "gyro_x", "gyro_y", "gyro_z", "setLaserData", "laser_points", "", "Lcom/pudutech/mirsdk/hardware/serialize/PolarCoordinates;", "([Lcom/pudutech/mirsdk/hardware/serialize/PolarCoordinates;)V", "setOdomData", "x", "y", "theta", "line_speed", "angle_speed", "startMapping", "MirMapping_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MappingNative {
    public static final MappingNative INSTANCE = new MappingNative();
    private static Function0<Integer> getExposureHandle;
    private static Function1<? super Integer, Boolean> setExposureHandle;

    public final native void cancelMapping();

    public final native boolean checkMapLimit(int mg);

    public final native boolean checkMarkerVisible(boolean is_end);

    public final native boolean[] checkVirtualWall(List<Vector3d> walls);

    public final native void createModule(MappingNative mapping);

    public final native void finishMapping();

    public final native CruisePath getCruisePath(List<Vector3d> cruise_poses);

    public final native int getDetectMarkerId();

    public final native String getLoclizationMap();

    public final native FileDescriptor getMapData(boolean is_final);

    public final native boolean getMappingOptStatus();

    public final native int getMatchPreMapStatus();

    public final native Vector3d getRobotPose();

    public final native TopoPath getTopoPath(List<Vector3d> poses, List<Destination> ele_list);

    public final native void initExtendMapping(byte[] mapBytes, byte[] configBytes, byte[] markerBytes);

    public final native void initGenTopoPath(TopoPath old_topo_path, byte[] mapBytes, byte[] configBytes);

    public final native boolean initModule(MachineModel machineType, byte[] marker_config, byte[] mapping_config);

    public final native boolean isModuleInited();

    public final native void onswitchAutoExpose(boolean isauto);

    public final native void processDownRGBD(FileDescriptor file);

    public final native void processLeftRGBD(FileDescriptor file);

    public final native void processRightRGBD(FileDescriptor file);

    public final native TopoPath resetDualPath(int[] ill_track_id);

    public final native void setFrontCameraData(FileDescriptor file, int rows, int cols, long time);

    public final native void setImageData(FileDescriptor file);

    public final native void setImuData(double accel_x, double accel_y, double accel_z, double gyro_x, double gyro_y, double gyro_z);

    public final native void setLaserData(PolarCoordinates[] laser_points);

    public final native void setOdomData(double x, double y, double theta, double line_speed, double angle_speed);

    public final native void startMapping();

    static {
        System.loadLibrary("mappingnative");
    }

    private MappingNative() {
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
}
