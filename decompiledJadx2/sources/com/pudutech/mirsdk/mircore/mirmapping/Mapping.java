package com.pudutech.mirsdk.mircore.mirmapping;

import android.os.ParcelFileDescriptor;
import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.IDownRgbdData;
import com.pudutech.mirsdk.hardware.ILeftRgbdData;
import com.pudutech.mirsdk.hardware.ILidarData;
import com.pudutech.mirsdk.hardware.IMarkerCameraData;
import com.pudutech.mirsdk.hardware.IRightRgbdData;
import com.pudutech.mirsdk.hardware.ISensorData;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.PolarCoordinates;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.coreparcel.CruisePath;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import com.pudutech.mirsdk.mircore.coreparcel.TopoPath;
import java.io.FileDescriptor;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Mapping.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Í\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r*\u0007\u0004\u0007\r\u0010\u0013\u0016\u001b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 J)\u0010\"\u001a\u00020\u001e2!\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u00110!¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020'0#J\u0006\u0010(\u001a\u00020\u001eJ\u000e\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020!J\u000e\u0010+\u001a\u00020'2\u0006\u0010,\u001a\u00020'J\u001a\u0010-\u001a\b\u0012\u0004\u0012\u00020'0.2\f\u0010/\u001a\b\u0012\u0004\u0012\u0002000.J\u0006\u00101\u001a\u00020\u001eJ\u0014\u00102\u001a\u0002032\f\u00104\u001a\b\u0012\u0004\u0012\u0002000.J\u0006\u00105\u001a\u00020!J\u0006\u00106\u001a\u00020\nJ\u000e\u00107\u001a\u0002082\u0006\u00109\u001a\u00020'J\u0006\u0010:\u001a\u00020'J\u0006\u0010;\u001a\u00020!J\u0006\u0010<\u001a\u000200J\"\u0010=\u001a\u00020>2\f\u0010?\u001a\b\u0012\u0004\u0012\u0002000.2\f\u0010@\u001a\b\u0012\u0004\u0012\u00020A0.J$\u0010B\u001a\u00020\u001e2\b\u0010C\u001a\u0004\u0018\u00010D2\b\u0010E\u001a\u0004\u0018\u00010D2\b\u0010F\u001a\u0004\u0018\u00010DJ \u0010G\u001a\u00020\u001e2\b\u0010H\u001a\u0004\u0018\u00010>2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020DJ\u001e\u0010I\u001a\u00020'2\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020D2\u0006\u0010M\u001a\u00020DJ\u0006\u0010N\u001a\u00020'J \u0010O\u001a\u00020'2\u0018\u0010P\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020R\u0012\u0004\u0012\u00020\u001e0QJ \u0010S\u001a\u00020\u001e2\u0018\u0010P\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020T\u0012\u0004\u0012\u00020\u001e0QJ \u0010U\u001a\u00020\u001e2\u0018\u0010P\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020V\u0012\u0004\u0012\u00020\u001e0QJ \u0010W\u001a\u00020\u001e2\u0018\u0010P\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020X\u0012\u0004\u0012\u00020\u001e0QJ \u0010Y\u001a\u00020\u001e2\u0018\u0010P\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020Z\u0012\u0004\u0012\u00020\u001e0QJ \u0010[\u001a\u00020\u001e2\u0018\u0010P\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020Z\u0012\u0004\u0012\u00020\u001e0QJ \u0010\\\u001a\u00020\u001e2\u0018\u0010P\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020]\u0012\u0004\u0012\u00020\u001e0QJ\u0014\u0010^\u001a\u00020>2\f\u0010_\u001a\b\u0012\u0004\u0012\u00020!0.J\u0006\u0010`\u001a\u00020\u001eJ\u000e\u0010a\u001a\u00020\u001e2\u0006\u0010b\u001a\u00020'J\u001a\u0010c\u001a\u00020\u001e2\u0012\u0010P\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001e0#J\u001a\u0010d\u001a\u00020\u001e2\u0012\u0010P\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001e0#J\u001a\u0010e\u001a\u00020'2\u0012\u0010P\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001e0#J\u001a\u0010f\u001a\u00020\u001e2\u0012\u0010P\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001e0#J\u001a\u0010g\u001a\u00020\u001e2\u0012\u0010P\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001e0#J\u001a\u0010h\u001a\u00020\u001e2\u0012\u0010P\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001e0#J\u001a\u0010i\u001a\u00020\u001e2\u0012\u0010P\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001e0#R\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005R\u0010\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u0010\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0014R\u0010\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001c¨\u0006j"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/mirmapping/Mapping;", "", "()V", "MARKERSENSOR", "com/pudutech/mirsdk/mircore/mirmapping/Mapping$MARKERSENSOR$1", "Lcom/pudutech/mirsdk/mircore/mirmapping/Mapping$MARKERSENSOR$1;", "SENSORLISTENOR", "com/pudutech/mirsdk/mircore/mirmapping/Mapping$SENSORLISTENOR$1", "Lcom/pudutech/mirsdk/mircore/mirmapping/Mapping$SENSORLISTENOR$1;", "TAG", "", "kotlin.jvm.PlatformType", "downRGBDListener", "com/pudutech/mirsdk/mircore/mirmapping/Mapping$downRGBDListener$1", "Lcom/pudutech/mirsdk/mircore/mirmapping/Mapping$downRGBDListener$1;", "frontCameraListener", "com/pudutech/mirsdk/mircore/mirmapping/Mapping$frontCameraListener$1", "Lcom/pudutech/mirsdk/mircore/mirmapping/Mapping$frontCameraListener$1;", "leftRGBDListener", "com/pudutech/mirsdk/mircore/mirmapping/Mapping$leftRGBDListener$1", "Lcom/pudutech/mirsdk/mircore/mirmapping/Mapping$leftRGBDListener$1;", "lidarListener", "com/pudutech/mirsdk/mircore/mirmapping/Mapping$lidarListener$1", "Lcom/pudutech/mirsdk/mircore/mirmapping/Mapping$lidarListener$1;", "odometry", "Lcom/pudutech/mirsdk/mircore/mirmapping/OdometryCenter;", "rightRGBDListener", "com/pudutech/mirsdk/mircore/mirmapping/Mapping$rightRGBDListener$1", "Lcom/pudutech/mirsdk/mircore/mirmapping/Mapping$rightRGBDListener$1;", "addGetExposureInterface", "", "func", "Lkotlin/Function0;", "", "addSetExposureInterface", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "exposure", "", "cancelMapping", "checkMapLimit", "mg", "checkMarkerVisible", "is_end", "checkVirtualWall", "", "walls", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "finishMapping", "getCruisePath", "Lcom/pudutech/mirsdk/mircore/coreparcel/CruisePath;", "cruise_poses", "getDetectMarkerId", "getLoclizationMap", "getMapData", "Ljava/io/FileDescriptor;", "is_final", "getMappingOptStatus", "getMatchPreMapStatus", "getRobotPose", "getTopoPath", "Lcom/pudutech/mirsdk/mircore/coreparcel/TopoPath;", "poses", "ele_list", "Lcom/pudutech/mirsdk/mircore/coreparcel/Destination;", "initExtendMapping", "mapBytes", "", "configBytes", "markerBytes", "initGenTopoPath", "old_topo_path", "initModule", "machineType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineModel;", "marker_config", "mapping_config", "isModuleInited", "registSensorListener", "handler", "Lkotlin/Function2;", "Lcom/pudutech/mirsdk/hardware/ISensorData;", "registerDownRGBDListener", "Lcom/pudutech/mirsdk/hardware/IDownRgbdData;", "registerLaserListener", "Lcom/pudutech/mirsdk/hardware/ILidarData;", "registerLeftRGBDListener", "Lcom/pudutech/mirsdk/hardware/ILeftRgbdData;", "registerMarkerFrameListener", "Lcom/pudutech/mirsdk/hardware/IMarkerCameraData;", "registerMonocularCameraListener", "registerRightRGBDListenner", "Lcom/pudutech/mirsdk/hardware/IRightRgbdData;", "resetDualPath", "ill_track_id", "startMapping", "switchAutoExposure", "isAuto", "unregistMarkerFrameListener", "unregistMonocularCameraListener", "unregistSensorListener", "unregisterDownRGBDListener", "unregisterLaserListener", "unregisterLeftRGBDListener", "unregisterRightRGBDListener", "MirMapping_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class Mapping {
    private final String TAG = getClass().getSimpleName();
    private final OdometryCenter odometry = new OdometryCenter();
    private final Mapping$SENSORLISTENOR$1 SENSORLISTENOR = new ISensorData.Stub() { // from class: com.pudutech.mirsdk.mircore.mirmapping.Mapping$SENSORLISTENOR$1
        @Override // com.pudutech.mirsdk.hardware.ISensorData
        public void onEncoder(double left, double right, double interval) {
            OdometryCenter odometryCenter;
            odometryCenter = Mapping.this.odometry;
            odometryCenter.updateEncoder(left, right, interval);
        }

        @Override // com.pudutech.mirsdk.hardware.ISensorData
        public void onIMU(double x, double y, double z, double interval) {
            OdometryCenter odometryCenter;
            odometryCenter = Mapping.this.odometry;
            odometryCenter.updateIMU(x, y, z, interval);
            MappingNative.INSTANCE.setImuData(0.0d, 0.0d, 0.0d, x, y, z);
        }

        @Override // com.pudutech.mirsdk.hardware.ISensorData
        public void onSpeed(double line_speed, double angle_speed, double interval) {
            OdometryCenter odometryCenter;
            odometryCenter = Mapping.this.odometry;
            odometryCenter.updateSpeed(line_speed, angle_speed, interval);
        }
    };
    private final Mapping$lidarListener$1 lidarListener = new ILidarData.Stub() { // from class: com.pudutech.mirsdk.mircore.mirmapping.Mapping$lidarListener$1
        @Override // com.pudutech.mirsdk.hardware.ILidarData
        public void onFrame(PolarCoordinates[] laser_points, long lidar_time_stamp) {
            if (laser_points != null) {
                MappingNative.INSTANCE.setLaserData(laser_points);
            }
        }
    };
    private final Mapping$MARKERSENSOR$1 MARKERSENSOR = new IMarkerCameraData.Stub() { // from class: com.pudutech.mirsdk.mircore.mirmapping.Mapping$MARKERSENSOR$1
        @Override // com.pudutech.mirsdk.hardware.IMarkerCameraData
        public void onFrame(ParcelFileDescriptor p0, int rows, int cols, int p3, int p4, long p5) {
            String str;
            if (p0 == null) {
                return;
            }
            str = Mapping.this.TAG;
            Pdlog.m3276v(str, "on camera frame:" + rows + ' ' + cols + ", transport time " + (SystemClock.elapsedRealtime() - p5) + "ms");
            MappingNative mappingNative = MappingNative.INSTANCE;
            FileDescriptor fileDescriptor = p0.getFileDescriptor();
            Intrinsics.checkExpressionValueIsNotNull(fileDescriptor, "p0.fileDescriptor");
            mappingNative.setImageData(fileDescriptor);
        }
    };
    private final Mapping$frontCameraListener$1 frontCameraListener = new IMarkerCameraData.Stub() { // from class: com.pudutech.mirsdk.mircore.mirmapping.Mapping$frontCameraListener$1
        @Override // com.pudutech.mirsdk.hardware.IMarkerCameraData
        public void onFrame(ParcelFileDescriptor p0, int rows, int cols, int p3, int p4, long p5) {
            String str;
            if (p0 == null) {
                return;
            }
            str = Mapping.this.TAG;
            Pdlog.m3276v(str, "on front camera frame:" + rows + ' ' + cols + ", transport time " + (SystemClock.elapsedRealtime() - p5) + "ms");
            MappingNative mappingNative = MappingNative.INSTANCE;
            FileDescriptor fileDescriptor = p0.getFileDescriptor();
            Intrinsics.checkExpressionValueIsNotNull(fileDescriptor, "p0.fileDescriptor");
            mappingNative.setFrontCameraData(fileDescriptor, rows, cols, p5);
        }
    };
    private final Mapping$leftRGBDListener$1 leftRGBDListener = new ILeftRgbdData.Stub() { // from class: com.pudutech.mirsdk.mircore.mirmapping.Mapping$leftRGBDListener$1
        @Override // com.pudutech.mirsdk.hardware.ILeftRgbdData
        public void onLeftFrameDescriptor(ParcelFileDescriptor p0, int rows, int cols, int memorySize) {
            String str;
            if (p0 == null) {
                return;
            }
            str = Mapping.this.TAG;
            Pdlog.m3273d(str, "call processLeftRGBD in Mapping");
            MappingNative mappingNative = MappingNative.INSTANCE;
            FileDescriptor fileDescriptor = p0.getFileDescriptor();
            Intrinsics.checkExpressionValueIsNotNull(fileDescriptor, "p0.fileDescriptor");
            mappingNative.processLeftRGBD(fileDescriptor);
        }
    };
    private final Mapping$rightRGBDListener$1 rightRGBDListener = new IRightRgbdData.Stub() { // from class: com.pudutech.mirsdk.mircore.mirmapping.Mapping$rightRGBDListener$1
        @Override // com.pudutech.mirsdk.hardware.IRightRgbdData
        public void onRightFrameDescriptor(ParcelFileDescriptor p0, int rows, int cols, int memorySize) {
            String str;
            if (p0 == null) {
                return;
            }
            str = Mapping.this.TAG;
            Pdlog.m3273d(str, "call processRightRGBD in Mapping");
            MappingNative mappingNative = MappingNative.INSTANCE;
            FileDescriptor fileDescriptor = p0.getFileDescriptor();
            Intrinsics.checkExpressionValueIsNotNull(fileDescriptor, "p0.fileDescriptor");
            mappingNative.processRightRGBD(fileDescriptor);
        }
    };
    private final Mapping$downRGBDListener$1 downRGBDListener = new IDownRgbdData.Stub() { // from class: com.pudutech.mirsdk.mircore.mirmapping.Mapping$downRGBDListener$1
        @Override // com.pudutech.mirsdk.hardware.IDownRgbdData
        public void onDownFrameDescriptor(ParcelFileDescriptor p0, int rows, int cols, int memorySize) {
            String str;
            if (p0 == null) {
                return;
            }
            str = Mapping.this.TAG;
            Pdlog.m3273d(str, "call processDownRGBD in Mapping");
            MappingNative mappingNative = MappingNative.INSTANCE;
            FileDescriptor fileDescriptor = p0.getFileDescriptor();
            Intrinsics.checkExpressionValueIsNotNull(fileDescriptor, "p0.fileDescriptor");
            mappingNative.processDownRGBD(fileDescriptor);
        }
    };

    public final boolean initModule(MachineModel machineType, byte[] marker_config, byte[] mapping_config) {
        Intrinsics.checkParameterIsNotNull(machineType, "machineType");
        Intrinsics.checkParameterIsNotNull(marker_config, "marker_config");
        Intrinsics.checkParameterIsNotNull(mapping_config, "mapping_config");
        MappingNative.INSTANCE.createModule(MappingNative.INSTANCE);
        return MappingNative.INSTANCE.initModule(machineType, marker_config, mapping_config);
    }

    public final boolean isModuleInited() {
        return MappingNative.INSTANCE.isModuleInited();
    }

    public final void initExtendMapping(byte[] mapBytes, byte[] configBytes, byte[] markerBytes) {
        MappingNative.INSTANCE.initExtendMapping(mapBytes, configBytes, markerBytes);
    }

    public final boolean registSensorListener(Function2<? super String, ? super ISensorData, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        handler.invoke(TAG, this.SENSORLISTENOR);
        return true;
    }

    public final boolean unregistSensorListener(Function1<? super String, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        handler.invoke(TAG);
        return true;
    }

    public final void registerMarkerFrameListener(Function2<? super String, ? super IMarkerCameraData, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        handler.invoke(TAG, this.MARKERSENSOR);
    }

    public final void unregistMarkerFrameListener(Function1<? super String, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        handler.invoke(TAG);
    }

    public final void registerLaserListener(Function2<? super String, ? super ILidarData, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        handler.invoke(TAG, this.lidarListener);
    }

    public final void unregisterLaserListener(Function1<? super String, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        handler.invoke(TAG);
    }

    public final void registerMonocularCameraListener(Function2<? super String, ? super IMarkerCameraData, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        handler.invoke(TAG, this.frontCameraListener);
    }

    public final void unregistMonocularCameraListener(Function1<? super String, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        handler.invoke(TAG);
    }

    public final void registerLeftRGBDListener(Function2<? super String, ? super ILeftRgbdData, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        Pdlog.m3273d(this.TAG, "register left rgbd listener");
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        handler.invoke(TAG, this.leftRGBDListener);
    }

    public final void unregisterLeftRGBDListener(Function1<? super String, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        Pdlog.m3273d(this.TAG, "unregister left rgbd listener");
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        handler.invoke(TAG);
    }

    public final void registerRightRGBDListenner(Function2<? super String, ? super IRightRgbdData, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        Pdlog.m3273d(this.TAG, "register right rgbd listener");
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        handler.invoke(TAG, this.rightRGBDListener);
    }

    public final void unregisterRightRGBDListener(Function1<? super String, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        Pdlog.m3273d(this.TAG, "unregister right rgbd listener");
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        handler.invoke(TAG);
    }

    public final void registerDownRGBDListener(Function2<? super String, ? super IDownRgbdData, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        Pdlog.m3273d(this.TAG, "register down rgbd listener");
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        handler.invoke(TAG, this.downRGBDListener);
    }

    public final void unregisterDownRGBDListener(Function1<? super String, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        Pdlog.m3273d(this.TAG, "unregister down rgbd listener");
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        handler.invoke(TAG);
    }

    public final void switchAutoExposure(boolean isAuto) {
        MappingNative.INSTANCE.onswitchAutoExpose(isAuto);
    }

    public final void addSetExposureInterface(Function1<? super Integer, Boolean> func) {
        Intrinsics.checkParameterIsNotNull(func, "func");
        MappingNative.INSTANCE.setSetExposureHandle(func);
    }

    public final void addGetExposureInterface(Function0<Integer> func) {
        Intrinsics.checkParameterIsNotNull(func, "func");
        MappingNative.INSTANCE.setGetExposureHandle(func);
    }

    public final boolean checkMarkerVisible(boolean is_end) {
        return MappingNative.INSTANCE.checkMarkerVisible(is_end);
    }

    public final int getDetectMarkerId() {
        return MappingNative.INSTANCE.getDetectMarkerId();
    }

    public final Vector3d getRobotPose() {
        return MappingNative.INSTANCE.getRobotPose();
    }

    public final boolean checkMapLimit(int mg) {
        return MappingNative.INSTANCE.checkMapLimit(mg);
    }

    public final FileDescriptor getMapData(boolean is_final) {
        return MappingNative.INSTANCE.getMapData(is_final);
    }

    public final void startMapping() {
        MappingNative.INSTANCE.startMapping();
    }

    public final int getMatchPreMapStatus() {
        return MappingNative.INSTANCE.getMatchPreMapStatus();
    }

    public final void cancelMapping() {
        MappingNative.INSTANCE.cancelMapping();
    }

    public final void finishMapping() {
        MappingNative.INSTANCE.finishMapping();
    }

    public final boolean getMappingOptStatus() {
        return MappingNative.INSTANCE.getMappingOptStatus();
    }

    public final String getLoclizationMap() {
        return MappingNative.INSTANCE.getLoclizationMap();
    }

    public final void initGenTopoPath(TopoPath old_topo_path, byte[] mapBytes, byte[] configBytes) {
        Intrinsics.checkParameterIsNotNull(mapBytes, "mapBytes");
        Intrinsics.checkParameterIsNotNull(configBytes, "configBytes");
        MappingNative.INSTANCE.initGenTopoPath(old_topo_path, mapBytes, configBytes);
    }

    public final TopoPath getTopoPath(List<Vector3d> poses, List<Destination> ele_list) {
        Intrinsics.checkParameterIsNotNull(poses, "poses");
        Intrinsics.checkParameterIsNotNull(ele_list, "ele_list");
        return MappingNative.INSTANCE.getTopoPath(poses, ele_list);
    }

    public final TopoPath resetDualPath(List<Integer> ill_track_id) {
        Intrinsics.checkParameterIsNotNull(ill_track_id, "ill_track_id");
        return MappingNative.INSTANCE.resetDualPath(CollectionsKt.toIntArray(ill_track_id));
    }

    public final List<Boolean> checkVirtualWall(List<Vector3d> walls) {
        Intrinsics.checkParameterIsNotNull(walls, "walls");
        boolean[] checkVirtualWall = MappingNative.INSTANCE.checkVirtualWall(walls);
        if (checkVirtualWall != null) {
            return ArraysKt.toMutableList(checkVirtualWall);
        }
        return null;
    }

    public final CruisePath getCruisePath(List<Vector3d> cruise_poses) {
        Intrinsics.checkParameterIsNotNull(cruise_poses, "cruise_poses");
        return MappingNative.INSTANCE.getCruisePath(cruise_poses);
    }
}
