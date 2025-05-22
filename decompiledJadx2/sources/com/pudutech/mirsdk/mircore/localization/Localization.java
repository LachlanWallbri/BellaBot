package com.pudutech.mirsdk.mircore.localization;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import android.os.SystemClock;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudu.slamware_localization.SlamwareLocalization;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.ILidarData;
import com.pudutech.mirsdk.hardware.IMarkerCameraData;
import com.pudutech.mirsdk.hardware.ISensorData;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.PolarCoordinates;
import com.pudutech.mirsdk.hardware.serialize.Vector2d;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.LocalizationListener;
import com.pudutech.mirsdk.mircore.PuduLocateInterface;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatus;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatusInfo;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatusLevel;
import com.pudutech.mirsdk.mircore.coreparcel.SlamStatus;
import com.pudutech.mirsdk.mircore.localization.Localization;
import com.pudutech.mirsdk.mircore.localization.laserLocateNative.LaserLocateNative;
import com.pudutech.mirsdk.mircore.localization.markerLocateNative.MarkerLocateNative;
import java.io.FileDescriptor;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: Localization.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000ã\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b%*\u0005\u0004\u0007\n\u0015\u001d\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u00105\u001a\u00020(2\f\u00106\u001a\b\u0012\u0004\u0012\u00020807H\u0016J&\u00109\u001a\u00020(2\b\u0010:\u001a\u0004\u0018\u00010\r2\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020(0'H\u0016J,\u0010<\u001a\u00020(2\b\u0010:\u001a\u0004\u0018\u00010\r2\u0018\u0010;\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020(0-H\u0016J\u0016\u0010=\u001a\u00020(2\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u001807H\u0016J+\u0010>\u001a\u00020(2!\u00106\u001a\u001d\u0012\u0013\u0012\u001108¢\u0006\f\b?\u0012\b\b:\u0012\u0004\b\b(@\u0012\u0004\u0012\u00020\u00180'H\u0016J&\u0010A\u001a\u00020(2\b\u0010:\u001a\u0004\u0018\u00010\r2\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020(0'H\u0016J(\u0010B\u001a\u00020(2\u001e\u0010;\u001a\u001a\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020(0CH\u0016J\b\u0010D\u001a\u00020\u0018H\u0016J \u0010E\u001a\u00020(2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020#2\u0006\u0010I\u001a\u00020JH\u0016J\b\u0010K\u001a\u00020LH\u0016J\b\u0010M\u001a\u00020\u0013H\u0016J\b\u0010N\u001a\u00020OH\u0016J\u0006\u0010P\u001a\u00020LJ\b\u0010Q\u001a\u00020\rH\u0016J\u0010\u0010R\u001a\u00020\u00182\u0006\u0010S\u001a\u00020TH\u0016J\b\u0010U\u001a\u00020\u0018H\u0016J\u0010\u0010V\u001a\u00020\u00182\u0006\u0010S\u001a\u00020TH\u0016J\b\u0010W\u001a\u00020\u0018H\u0007J\u0010\u0010X\u001a\u00020(2\u0006\u0010Y\u001a\u00020\u0018H\u0016J\"\u0010Z\u001a\u00020\u00182\u0018\u0010[\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\\\u0012\u0004\u0012\u00020(0-H\u0016J\"\u0010]\u001a\u00020(2\u0018\u0010[\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020^\u0012\u0004\u0012\u00020(0-H\u0016J\"\u0010_\u001a\u00020(2\u0018\u0010[\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020`\u0012\u0004\u0012\u00020(0-H\u0016J\"\u0010a\u001a\u00020(2\u0018\u0010[\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020`\u0012\u0004\u0012\u00020(0-H\u0016J\b\u0010b\u001a\u00020(H\u0016J-\u0010c\u001a\u00020(2\u000e\u0010d\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010e2\u000e\u0010f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010eH\u0016¢\u0006\u0002\u0010gJ%\u0010h\u001a\u00020\u00182\u0006\u0010i\u001a\u00020\u00102\u000e\u0010j\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010eH\u0016¢\u0006\u0002\u0010kJ\u0012\u0010l\u001a\u00020(2\b\u0010:\u001a\u0004\u0018\u00010\rH\u0016J\u0012\u0010m\u001a\u00020(2\b\u0010:\u001a\u0004\u0018\u00010\rH\u0016J\u0012\u0010n\u001a\u00020(2\b\u0010:\u001a\u0004\u0018\u00010\rH\u0016J\u0012\u0010o\u001a\u00020(2\b\u0010:\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010p\u001a\u00020\u0018H\u0016J\u0018\u0010q\u001a\u00020(2\u0006\u0010r\u001a\u00020\u00102\u0006\u0010s\u001a\u00020\u0010H\u0016J\b\u0010t\u001a\u00020(H\u0016J\b\u0010u\u001a\u00020(H\u0016J\u0012\u0010v\u001a\u00020(2\b\u0010w\u001a\u0004\u0018\u00010\rH\u0016J\u001c\u0010x\u001a\u00020(2\b\u0010:\u001a\u0004\u0018\u00010\r2\b\u0010;\u001a\u0004\u0018\u00010!H\u0016J\u0010\u0010y\u001a\u00020\u00182\u0006\u0010z\u001a\u00020TH\u0016J\u0018\u0010y\u001a\u00020\u00182\u0006\u0010{\u001a\u00020T2\u0006\u0010|\u001a\u00020TH\u0016J\u0018\u0010y\u001a\u00020\u00182\u0006\u0010{\u001a\u00020T2\u0006\u0010}\u001a\u00020\rH\u0016J\u0010\u0010~\u001a\u00020(2\u0006\u0010\u007f\u001a\u00020\u0018H\u0016J\u0012\u0010\u0080\u0001\u001a\u00020(2\u0007\u0010\u0081\u0001\u001a\u00020\u0018H\u0016J\u001d\u0010\u0082\u0001\u001a\u00020(2\u0012\u0010[\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020(0'H\u0016J\u001d\u0010\u0083\u0001\u001a\u00020(2\u0012\u0010[\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020(0'H\u0016J\u001d\u0010\u0084\u0001\u001a\u00020\u00182\u0012\u0010[\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020(0'H\u0016J\u001d\u0010\u0085\u0001\u001a\u00020(2\u0012\u0010[\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020(0'H\u0016J$\u0010\u0086\u0001\u001a\u00020(2\u0007\u0010\u0087\u0001\u001a\u00020\u00132\u0007\u0010\u0088\u0001\u001a\u00020\u00132\u0007\u0010\u0089\u0001\u001a\u00020\u0013H\u0002R\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005R\u0010\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR\u0010\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0016\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001eR\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010&\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020(0'0 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010,\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020(0-0 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u000201X\u0082\u0004¢\u0006\u0002\n\u0000R \u00102\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020(0'0 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000203X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u008a\u0001"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/localization/Localization;", "Lcom/pudutech/mirsdk/mircore/PuduLocateInterface;", "()V", "LocalizationScope", "com/pudutech/mirsdk/mircore/localization/Localization$LocalizationScope$1", "Lcom/pudutech/mirsdk/mircore/localization/Localization$LocalizationScope$1;", "MARKERSENSOR", "com/pudutech/mirsdk/mircore/localization/Localization$MARKERSENSOR$1", "Lcom/pudutech/mirsdk/mircore/localization/Localization$MARKERSENSOR$1;", "SENSORLISTENOR", "com/pudutech/mirsdk/mircore/localization/Localization$SENSORLISTENOR$1", "Lcom/pudutech/mirsdk/mircore/localization/Localization$SENSORLISTENOR$1;", "TAG", "", "kotlin.jvm.PlatformType", "cur_odom", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "dir_broad", "disLocateLose", "", "frontCameraListener", "com/pudutech/mirsdk/mircore/localization/Localization$frontCameraListener$1", "Lcom/pudutech/mirsdk/mircore/localization/Localization$frontCameraListener$1;", "initialzed", "", "lastBroadTime", "", "last_odom", "lidarListener", "com/pudutech/mirsdk/mircore/localization/Localization$lidarListener$1", "Lcom/pudutech/mirsdk/mircore/localization/Localization$lidarListener$1;", "localizationListeners", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/mircore/LocalizationListener;", "locateCase", "Lcom/pudutech/mirsdk/mircore/PuduLocateInterface$LocateCase;", "lostflag", "odom", "odomListeners", "Lkotlin/Function1;", "", "odometry", "Lcom/pudutech/mirsdk/mircore/localization/OdometryCenter;", "orientation", "poseListeners", "Lkotlin/Function2;", "pose_broad", RequestParameters.POSITION, "slamwareLocation", "Lcom/pudu/slamware_localization/SlamwareLocalization;", "speedListeners", "Lcom/pudutech/mirsdk/hardware/serialize/Vector2d;", "speeds", "addGetExposureInterface", "func", "Lkotlin/Function0;", "", "addOdomListener", "name", "listener", "addPoseListener", "addSaveMapInterface", "addSetExposureInterface", "Lkotlin/ParameterName;", "exposure", "addSpeedListener", "calibrationMarkerCamera", "Lkotlin/Function3;", "checkFinishInitLocalization", "createModules", "context", "Landroid/content/Context;", "case", "machineType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineModel;", "getLocalizationStatus", "Lcom/pudutech/mirsdk/mircore/coreparcel/LocalizationStatus;", "getRotatePlanning", "getSlamStatus", "Lcom/pudutech/mirsdk/mircore/coreparcel/SlamStatus;", "getSlamwareLocalizationStatus", "getVersionInfo", "initConfig", "config", "", "initLocalization", "initMarkerConfig", "initModules", "onSwitchAutoExposeNative", "isAuto", "registSensorListener", "handler", "Lcom/pudutech/mirsdk/hardware/ISensorData;", "registerLaserListener", "Lcom/pudutech/mirsdk/hardware/ILidarData;", "registerMarkerFrameListener", "Lcom/pudutech/mirsdk/hardware/IMarkerCameraData;", "registerMonocularCameraListener", "relocalization", "relocalizationByPoints", "ids", "", "poses", "([Ljava/lang/String;[Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "relocalizationPointIsValid", "pose", "vaild_poses", "(Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;[Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)Z", "removeLocateListener", "removeOdomListener", "removePoseListener", "removeSpeedListener", "saveMarkerMap", "setFloorElevatorPoint", "pose1", "pose2", "setInMapingMode", "setInRemapingMode", "setLidarSerialNumber", "sn", "setLocateListener", "setLocateMap", "locate_map", "map_config", "map_pgm", "map_path", "setRotateEnd", "end_s", "setTaskStatus", "task_s", "unregistMarkerFrameListener", "unregistMonocularCameraListener", "unregistSensorListener", "unregisterLaserListener", "updateOdom", "line_speed", "angle_speed", "interval", "MirLocalization_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class Localization implements PuduLocateInterface {
    private double disLocateLose;
    private boolean initialzed;
    private long lastBroadTime;
    private PuduLocateInterface.LocateCase locateCase;
    private boolean lostflag;
    private final String TAG = getClass().getSimpleName();
    private final OdometryCenter odometry = new OdometryCenter();
    private Vector3d position = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
    private Vector3d odom = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
    private Vector3d orientation = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
    private Vector2d speeds = new Vector2d(0.0d, 0.0d, 3, null);
    private final ThreadSafeListener<LocalizationListener> localizationListeners = new ThreadSafeListener<>();
    private final ThreadSafeListener<Function2<Vector3d, Vector3d, Unit>> poseListeners = new ThreadSafeListener<>();
    private final ThreadSafeListener<Function1<Vector3d, Unit>> odomListeners = new ThreadSafeListener<>();
    private final ThreadSafeListener<Function1<Vector2d, Unit>> speedListeners = new ThreadSafeListener<>();
    private final Localization$LocalizationScope$1 LocalizationScope = new Localization$LocalizationScope$1();
    private final Vector3d pose_broad = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
    private final Vector3d dir_broad = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
    private final SlamwareLocalization slamwareLocation = new SlamwareLocalization();
    private Vector3d cur_odom = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
    private Vector3d last_odom = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
    private final Localization$lidarListener$1 lidarListener = new ILidarData.Stub() { // from class: com.pudutech.mirsdk.mircore.localization.Localization$lidarListener$1
        @Override // com.pudutech.mirsdk.hardware.ILidarData
        public void onFrame(PolarCoordinates[] laser_points, long lidar_time_stamp) {
            if (laser_points != null) {
                LaserLocateNative.INSTANCE.setLaserData(laser_points);
            }
        }
    };
    private final Localization$SENSORLISTENOR$1 SENSORLISTENOR = new ISensorData.Stub() { // from class: com.pudutech.mirsdk.mircore.localization.Localization$SENSORLISTENOR$1
        @Override // com.pudutech.mirsdk.hardware.ISensorData
        public void onEncoder(double left, double right, double interval) {
            OdometryCenter odometryCenter;
            odometryCenter = Localization.this.odometry;
            odometryCenter.updateEncoder(left, right, interval);
        }

        @Override // com.pudutech.mirsdk.hardware.ISensorData
        public void onIMU(double x, double y, double z, double interval) {
            OdometryCenter odometryCenter;
            odometryCenter = Localization.this.odometry;
            odometryCenter.updateIMU(x, y, z, interval);
            int i = Localization.WhenMappings.$EnumSwitchMapping$29[Localization.access$getLocateCase$p(Localization.this).ordinal()];
            if (i == 1) {
                MarkerLocateNative.INSTANCE.setImuData(0.0d, 0.0d, 0.0d, x, y, z);
            } else if (i == 2 || i == 3) {
                LaserLocateNative.INSTANCE.setImuData(0.0d, 0.0d, 0.0d, x, y, z);
            }
        }

        @Override // com.pudutech.mirsdk.hardware.ISensorData
        public void onSpeed(double line_speed, double angle_speed, double interval) {
            Localization.this.updateOdom(line_speed, angle_speed, interval);
        }
    };
    private final Localization$MARKERSENSOR$1 MARKERSENSOR = new IMarkerCameraData.Stub() { // from class: com.pudutech.mirsdk.mircore.localization.Localization$MARKERSENSOR$1
        @Override // com.pudutech.mirsdk.hardware.IMarkerCameraData
        public void onFrame(ParcelFileDescriptor p0, int rows, int cols, int p3, int p4, long p5) {
            String str;
            if (p0 == null) {
                return;
            }
            str = Localization.this.TAG;
            Pdlog.m3276v(str, "on camera frame:" + rows + ' ' + cols + ", transport time " + (SystemClock.elapsedRealtime() - p5) + "ms");
            int i = Localization.WhenMappings.$EnumSwitchMapping$30[Localization.access$getLocateCase$p(Localization.this).ordinal()];
            if (i == 1) {
                MarkerLocateNative markerLocateNative = MarkerLocateNative.INSTANCE;
                FileDescriptor fileDescriptor = p0.getFileDescriptor();
                Intrinsics.checkExpressionValueIsNotNull(fileDescriptor, "p0.fileDescriptor");
                markerLocateNative.setImageData(fileDescriptor);
                return;
            }
            if (i != 2) {
                return;
            }
            LaserLocateNative laserLocateNative = LaserLocateNative.INSTANCE;
            FileDescriptor fileDescriptor2 = p0.getFileDescriptor();
            Intrinsics.checkExpressionValueIsNotNull(fileDescriptor2, "p0.fileDescriptor");
            laserLocateNative.setImageData(fileDescriptor2);
        }
    };
    private final Localization$frontCameraListener$1 frontCameraListener = new IMarkerCameraData.Stub() { // from class: com.pudutech.mirsdk.mircore.localization.Localization$frontCameraListener$1
        @Override // com.pudutech.mirsdk.hardware.IMarkerCameraData
        public void onFrame(ParcelFileDescriptor p0, int rows, int cols, int p3, int p4, long p5) {
            String str;
            if (p0 == null) {
                return;
            }
            str = Localization.this.TAG;
            Pdlog.m3276v(str, "on front camera frame:" + rows + ' ' + cols + ", transport time " + (SystemClock.elapsedRealtime() - p5) + "ms");
            if (Localization.WhenMappings.$EnumSwitchMapping$31[Localization.access$getLocateCase$p(Localization.this).ordinal()] != 1) {
                return;
            }
            LaserLocateNative laserLocateNative = LaserLocateNative.INSTANCE;
            FileDescriptor fileDescriptor = p0.getFileDescriptor();
            Intrinsics.checkExpressionValueIsNotNull(fileDescriptor, "p0.fileDescriptor");
            laserLocateNative.setFrontCameraData(fileDescriptor, rows, cols, p5);
        }
    };

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[PuduLocateInterface.LocateCase.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$10;
        public static final /* synthetic */ int[] $EnumSwitchMapping$11;
        public static final /* synthetic */ int[] $EnumSwitchMapping$12;
        public static final /* synthetic */ int[] $EnumSwitchMapping$13;
        public static final /* synthetic */ int[] $EnumSwitchMapping$14;
        public static final /* synthetic */ int[] $EnumSwitchMapping$15;
        public static final /* synthetic */ int[] $EnumSwitchMapping$16;
        public static final /* synthetic */ int[] $EnumSwitchMapping$17;
        public static final /* synthetic */ int[] $EnumSwitchMapping$18;
        public static final /* synthetic */ int[] $EnumSwitchMapping$19;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$20;
        public static final /* synthetic */ int[] $EnumSwitchMapping$21;
        public static final /* synthetic */ int[] $EnumSwitchMapping$22;
        public static final /* synthetic */ int[] $EnumSwitchMapping$23;
        public static final /* synthetic */ int[] $EnumSwitchMapping$24;
        public static final /* synthetic */ int[] $EnumSwitchMapping$25;
        public static final /* synthetic */ int[] $EnumSwitchMapping$26;
        public static final /* synthetic */ int[] $EnumSwitchMapping$27;
        public static final /* synthetic */ int[] $EnumSwitchMapping$28;
        public static final /* synthetic */ int[] $EnumSwitchMapping$29;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$30;
        public static final /* synthetic */ int[] $EnumSwitchMapping$31;
        public static final /* synthetic */ int[] $EnumSwitchMapping$32;
        public static final /* synthetic */ int[] $EnumSwitchMapping$33;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;
        public static final /* synthetic */ int[] $EnumSwitchMapping$5;
        public static final /* synthetic */ int[] $EnumSwitchMapping$6;
        public static final /* synthetic */ int[] $EnumSwitchMapping$7;
        public static final /* synthetic */ int[] $EnumSwitchMapping$8;
        public static final /* synthetic */ int[] $EnumSwitchMapping$9;

        static {
            $EnumSwitchMapping$0[PuduLocateInterface.LocateCase.Marker.ordinal()] = 1;
            $EnumSwitchMapping$0[PuduLocateInterface.LocateCase.Laser.ordinal()] = 2;
            $EnumSwitchMapping$0[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 3;
            $EnumSwitchMapping$0[PuduLocateInterface.LocateCase.Slamware.ordinal()] = 4;
            $EnumSwitchMapping$1 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$1[PuduLocateInterface.LocateCase.Marker.ordinal()] = 1;
            $EnumSwitchMapping$1[PuduLocateInterface.LocateCase.Laser.ordinal()] = 2;
            $EnumSwitchMapping$1[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 3;
            $EnumSwitchMapping$1[PuduLocateInterface.LocateCase.Slamware.ordinal()] = 4;
            $EnumSwitchMapping$2 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$2[PuduLocateInterface.LocateCase.Marker.ordinal()] = 1;
            $EnumSwitchMapping$2[PuduLocateInterface.LocateCase.Laser.ordinal()] = 2;
            $EnumSwitchMapping$2[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 3;
            $EnumSwitchMapping$3 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$3[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 1;
            $EnumSwitchMapping$4 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$4[PuduLocateInterface.LocateCase.Marker.ordinal()] = 1;
            $EnumSwitchMapping$4[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 2;
            $EnumSwitchMapping$5 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$5[PuduLocateInterface.LocateCase.Laser.ordinal()] = 1;
            $EnumSwitchMapping$5[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 2;
            $EnumSwitchMapping$5[PuduLocateInterface.LocateCase.Slamware.ordinal()] = 3;
            $EnumSwitchMapping$6 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$6[PuduLocateInterface.LocateCase.Laser.ordinal()] = 1;
            $EnumSwitchMapping$6[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 2;
            $EnumSwitchMapping$7 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$7[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 1;
            $EnumSwitchMapping$8 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$8[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 1;
            $EnumSwitchMapping$9 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$9[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 1;
            $EnumSwitchMapping$10 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$10[PuduLocateInterface.LocateCase.Laser.ordinal()] = 1;
            $EnumSwitchMapping$10[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 2;
            $EnumSwitchMapping$10[PuduLocateInterface.LocateCase.Slamware.ordinal()] = 3;
            $EnumSwitchMapping$11 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$11[PuduLocateInterface.LocateCase.Laser.ordinal()] = 1;
            $EnumSwitchMapping$11[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 2;
            $EnumSwitchMapping$11[PuduLocateInterface.LocateCase.Slamware.ordinal()] = 3;
            $EnumSwitchMapping$12 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$12[PuduLocateInterface.LocateCase.Laser.ordinal()] = 1;
            $EnumSwitchMapping$12[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 2;
            $EnumSwitchMapping$13 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$13[PuduLocateInterface.LocateCase.Laser.ordinal()] = 1;
            $EnumSwitchMapping$13[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 2;
            $EnumSwitchMapping$14 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$14[PuduLocateInterface.LocateCase.Marker.ordinal()] = 1;
            $EnumSwitchMapping$14[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 2;
            $EnumSwitchMapping$15 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$15[PuduLocateInterface.LocateCase.Slamware.ordinal()] = 1;
            $EnumSwitchMapping$15[PuduLocateInterface.LocateCase.Marker.ordinal()] = 2;
            $EnumSwitchMapping$15[PuduLocateInterface.LocateCase.Laser.ordinal()] = 3;
            $EnumSwitchMapping$15[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 4;
            $EnumSwitchMapping$16 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$16[PuduLocateInterface.LocateCase.Slamware.ordinal()] = 1;
            $EnumSwitchMapping$16[PuduLocateInterface.LocateCase.Marker.ordinal()] = 2;
            $EnumSwitchMapping$16[PuduLocateInterface.LocateCase.Laser.ordinal()] = 3;
            $EnumSwitchMapping$16[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 4;
            $EnumSwitchMapping$17 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$17[PuduLocateInterface.LocateCase.Slamware.ordinal()] = 1;
            $EnumSwitchMapping$17[PuduLocateInterface.LocateCase.Marker.ordinal()] = 2;
            $EnumSwitchMapping$17[PuduLocateInterface.LocateCase.Laser.ordinal()] = 3;
            $EnumSwitchMapping$17[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 4;
            $EnumSwitchMapping$18 = new int[SlamStatus.values().length];
            $EnumSwitchMapping$18[SlamStatus.LaserSuccess.ordinal()] = 1;
            $EnumSwitchMapping$18[SlamStatus.LaserLocateLose.ordinal()] = 2;
            $EnumSwitchMapping$19 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$19[PuduLocateInterface.LocateCase.Laser.ordinal()] = 1;
            $EnumSwitchMapping$19[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 2;
            $EnumSwitchMapping$20 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$20[PuduLocateInterface.LocateCase.Laser.ordinal()] = 1;
            $EnumSwitchMapping$20[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 2;
            $EnumSwitchMapping$21 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$21[PuduLocateInterface.LocateCase.Laser.ordinal()] = 1;
            $EnumSwitchMapping$21[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 2;
            $EnumSwitchMapping$22 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$22[PuduLocateInterface.LocateCase.Marker.ordinal()] = 1;
            $EnumSwitchMapping$22[PuduLocateInterface.LocateCase.Laser.ordinal()] = 2;
            $EnumSwitchMapping$22[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 3;
            $EnumSwitchMapping$23 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$23[PuduLocateInterface.LocateCase.Marker.ordinal()] = 1;
            $EnumSwitchMapping$23[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 2;
            $EnumSwitchMapping$24 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$24[PuduLocateInterface.LocateCase.Laser.ordinal()] = 1;
            $EnumSwitchMapping$24[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 2;
            $EnumSwitchMapping$25 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$25[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 1;
            $EnumSwitchMapping$26 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$26[PuduLocateInterface.LocateCase.Marker.ordinal()] = 1;
            $EnumSwitchMapping$26[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 2;
            $EnumSwitchMapping$27 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$27[PuduLocateInterface.LocateCase.Marker.ordinal()] = 1;
            $EnumSwitchMapping$27[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 2;
            $EnumSwitchMapping$28 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$28[PuduLocateInterface.LocateCase.Laser.ordinal()] = 1;
            $EnumSwitchMapping$28[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 2;
            $EnumSwitchMapping$29 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$29[PuduLocateInterface.LocateCase.Marker.ordinal()] = 1;
            $EnumSwitchMapping$29[PuduLocateInterface.LocateCase.Laser.ordinal()] = 2;
            $EnumSwitchMapping$29[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 3;
            $EnumSwitchMapping$30 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$30[PuduLocateInterface.LocateCase.Marker.ordinal()] = 1;
            $EnumSwitchMapping$30[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 2;
            $EnumSwitchMapping$31 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$31[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 1;
            $EnumSwitchMapping$32 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$32[PuduLocateInterface.LocateCase.Slamware.ordinal()] = 1;
            $EnumSwitchMapping$32[PuduLocateInterface.LocateCase.Marker.ordinal()] = 2;
            $EnumSwitchMapping$32[PuduLocateInterface.LocateCase.Laser.ordinal()] = 3;
            $EnumSwitchMapping$32[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 4;
            $EnumSwitchMapping$33 = new int[PuduLocateInterface.LocateCase.values().length];
            $EnumSwitchMapping$33[PuduLocateInterface.LocateCase.Laser.ordinal()] = 1;
            $EnumSwitchMapping$33[PuduLocateInterface.LocateCase.LaserMark.ordinal()] = 2;
        }
    }

    public static final /* synthetic */ PuduLocateInterface.LocateCase access$getLocateCase$p(Localization localization) {
        PuduLocateInterface.LocateCase locateCase = localization.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        return locateCase;
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void createModules(Context context, PuduLocateInterface.LocateCase r8, MachineModel machineType) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(r8, "case");
        Intrinsics.checkParameterIsNotNull(machineType, "machineType");
        Pdlog.m3273d(this.TAG, "input locate case " + r8);
        this.initialzed = false;
        if (this.locateCase == null) {
            this.locateCase = r8;
            this.odometry.setLocateCase(r8);
            PuduLocateInterface.LocateCase locateCase = this.locateCase;
            if (locateCase == null) {
                Intrinsics.throwUninitializedPropertyAccessException("locateCase");
            }
            int i = WhenMappings.$EnumSwitchMapping$0[locateCase.ordinal()];
            if (i == 1) {
                MarkerLocateNative.INSTANCE.createModules(r8, MarkerLocateNative.INSTANCE);
                return;
            }
            if (i == 2 || i == 3) {
                LaserLocateNative.INSTANCE.createModules(r8, LaserLocateNative.INSTANCE, machineType);
                return;
            } else {
                if (i != 4) {
                    return;
                }
                this.slamwareLocation.init(context);
                return;
            }
        }
        PuduLocateInterface.LocateCase locateCase2 = this.locateCase;
        if (locateCase2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        if (locateCase2 != r8) {
            this.locateCase = r8;
            this.odometry.setLocateCase(r8);
            PuduLocateInterface.LocateCase locateCase3 = this.locateCase;
            if (locateCase3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("locateCase");
            }
            int i2 = WhenMappings.$EnumSwitchMapping$1[locateCase3.ordinal()];
            if (i2 == 1) {
                MarkerLocateNative.INSTANCE.createModules(r8, MarkerLocateNative.INSTANCE);
                return;
            }
            if (i2 == 2 || i2 == 3) {
                LaserLocateNative.INSTANCE.createModules(r8, LaserLocateNative.INSTANCE, machineType);
            } else {
                if (i2 != 4) {
                    return;
                }
                this.slamwareLocation.init(context);
            }
        }
    }

    @Deprecated(message = "旧架构使用此接口", replaceWith = @ReplaceWith(expression = "initConfig", imports = {"setLocateMap"}))
    public final boolean initModules() {
        this.initialzed = false;
        if (this.locateCase == null) {
            this.initialzed = MarkerLocateNative.INSTANCE.initModules();
        } else {
            PuduLocateInterface.LocateCase locateCase = this.locateCase;
            if (locateCase == null) {
                Intrinsics.throwUninitializedPropertyAccessException("locateCase");
            }
            if (locateCase == PuduLocateInterface.LocateCase.Marker) {
                this.initialzed = MarkerLocateNative.INSTANCE.initModules();
            }
        }
        return this.initialzed;
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public boolean initConfig(byte[] config) {
        Intrinsics.checkParameterIsNotNull(config, "config");
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("init config when locate case in ");
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        sb.append(locateCase);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        PuduLocateInterface.LocateCase locateCase2 = this.locateCase;
        if (locateCase2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        int i = WhenMappings.$EnumSwitchMapping$2[locateCase2.ordinal()];
        if (i == 1) {
            return MarkerLocateNative.INSTANCE.initData(config);
        }
        if (i == 2 || i == 3) {
            return LaserLocateNative.INSTANCE.initData(config);
        }
        return true;
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public boolean initMarkerConfig(byte[] config) {
        Intrinsics.checkParameterIsNotNull(config, "config");
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("init marker config when locate case in ");
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        sb.append(locateCase);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        PuduLocateInterface.LocateCase locateCase2 = this.locateCase;
        if (locateCase2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        if (WhenMappings.$EnumSwitchMapping$3[locateCase2.ordinal()] != 1) {
            return false;
        }
        return LaserLocateNative.INSTANCE.initMarkerConfig(config);
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public boolean setLocateMap(byte[] locate_map) {
        Intrinsics.checkParameterIsNotNull(locate_map, "locate_map");
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        int i = WhenMappings.$EnumSwitchMapping$4[locateCase.ordinal()];
        if (i == 1) {
            this.initialzed = MarkerLocateNative.INSTANCE.setLocateMap(locate_map);
            return this.initialzed;
        }
        if (i != 2) {
            return true;
        }
        this.initialzed = LaserLocateNative.INSTANCE.setMarkerLocateMap(locate_map);
        return this.initialzed;
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public boolean setLocateMap(byte[] map_config, byte[] map_pgm) {
        Intrinsics.checkParameterIsNotNull(map_config, "map_config");
        Intrinsics.checkParameterIsNotNull(map_pgm, "map_pgm");
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        int i = WhenMappings.$EnumSwitchMapping$5[locateCase.ordinal()];
        if (i == 1 || i == 2) {
            this.initialzed = LaserLocateNative.INSTANCE.setLidarLocateMap(map_config, map_pgm);
        } else if (i == 3) {
            this.slamwareLocation.setMap(new String(map_pgm, Charsets.UTF_8));
            this.initialzed = true;
        }
        return this.initialzed;
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public boolean setLocateMap(byte[] map_config, String map_path) {
        Intrinsics.checkParameterIsNotNull(map_config, "map_config");
        Intrinsics.checkParameterIsNotNull(map_path, "map_path");
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        int i = WhenMappings.$EnumSwitchMapping$6[locateCase.ordinal()];
        if (i == 1 || i == 2) {
            this.initialzed = LaserLocateNative.INSTANCE.setLidarLocateMapByPath(map_config, map_path);
        }
        return this.initialzed;
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void setInMapingMode() {
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        if (WhenMappings.$EnumSwitchMapping$7[locateCase.ordinal()] != 1) {
            return;
        }
        LaserLocateNative.INSTANCE.setInMapingMode();
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void setInRemapingMode() {
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        if (WhenMappings.$EnumSwitchMapping$8[locateCase.ordinal()] != 1) {
            return;
        }
        LaserLocateNative.INSTANCE.setInRemapingMode();
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public boolean saveMarkerMap() {
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        if (WhenMappings.$EnumSwitchMapping$9[locateCase.ordinal()] != 1) {
            return false;
        }
        return LaserLocateNative.INSTANCE.saveMarkerMap();
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void relocalization() {
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        int i = WhenMappings.$EnumSwitchMapping$10[locateCase.ordinal()];
        if (i == 1 || i == 2) {
            LaserLocateNative.INSTANCE.relocalization();
        } else {
            if (i != 3) {
                return;
            }
            this.slamwareLocation.setPose(0.0f, 0.0f, 0.0f);
        }
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void relocalizationByPoints(String[] ids, Vector3d[] poses) {
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        int i = WhenMappings.$EnumSwitchMapping$11[locateCase.ordinal()];
        if (i == 1 || i == 2) {
            LaserLocateNative.INSTANCE.relocalizationByPoints(ids, poses);
        } else {
            if (i != 3) {
                return;
            }
            this.slamwareLocation.setPose(0.0f, 0.0f, 0.0f);
        }
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public boolean relocalizationPointIsValid(Vector3d pose, Vector3d[] vaild_poses) {
        Intrinsics.checkParameterIsNotNull(pose, "pose");
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        int i = WhenMappings.$EnumSwitchMapping$12[locateCase.ordinal()];
        if (i == 1 || i == 2) {
            return LaserLocateNative.INSTANCE.relocalizationPointIsValid(pose, vaild_poses);
        }
        return false;
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void setFloorElevatorPoint(Vector3d pose1, Vector3d pose2) {
        Intrinsics.checkParameterIsNotNull(pose1, "pose1");
        Intrinsics.checkParameterIsNotNull(pose2, "pose2");
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        int i = WhenMappings.$EnumSwitchMapping$13[locateCase.ordinal()];
        if (i == 1 || i == 2) {
            LaserLocateNative.INSTANCE.setFloorElevatorPoint(pose1, pose2);
        }
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    /* renamed from: initLocalization, reason: from getter */
    public boolean getInitialzed() {
        return this.initialzed;
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void onSwitchAutoExposeNative(boolean isAuto) {
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        int i = WhenMappings.$EnumSwitchMapping$14[locateCase.ordinal()];
        if (i == 1) {
            MarkerLocateNative.INSTANCE.onswitchAutoExposeNative(isAuto);
        } else {
            if (i != 2) {
                return;
            }
            LaserLocateNative.INSTANCE.onswitchAutoExposeNative(isAuto);
        }
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public boolean checkFinishInitLocalization() {
        boolean checkInitFinish;
        if (this.locateCase == null) {
            return false;
        }
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        int i = WhenMappings.$EnumSwitchMapping$15[locateCase.ordinal()];
        if (i == 1) {
            checkInitFinish = this.slamwareLocation.checkInitFinish();
        } else if (i == 2) {
            checkInitFinish = MarkerLocateNative.INSTANCE.isLocalizationFinishInitialization();
        } else {
            checkInitFinish = (i == 3 || i == 4) ? LaserLocateNative.INSTANCE.isLocalizationFinishInitialization() : false;
        }
        Pdlog.m3273d(this.TAG, "checkFinishInitLocalization " + checkInitFinish);
        return checkInitFinish;
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public SlamStatus getSlamStatus() {
        SlamStatus slamStatus;
        SlamStatus slamStatus2;
        if (this.locateCase == null) {
            Pdlog.m3273d(this.TAG, "getSlamStatus: NoInit");
            return SlamStatus.NoInit;
        }
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        int i = WhenMappings.$EnumSwitchMapping$16[locateCase.ordinal()];
        if (i == 1) {
            return this.slamwareLocation.getSlamStatus();
        }
        if (i == 2) {
            SlamStatus[] values = SlamStatus.values();
            int length = values.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    slamStatus = null;
                    break;
                }
                SlamStatus slamStatus3 = values[i2];
                if (slamStatus3.getValue() == ((short) MarkerLocateNative.INSTANCE.getSlamStatus())) {
                    slamStatus = slamStatus3;
                    break;
                }
                i2++;
            }
            return slamStatus != null ? slamStatus : SlamStatus.Success;
        }
        if (i == 3 || i == 4) {
            SlamStatus[] values2 = SlamStatus.values();
            int length2 = values2.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length2) {
                    slamStatus2 = null;
                    break;
                }
                SlamStatus slamStatus4 = values2[i3];
                if (slamStatus4.getValue() == ((short) LaserLocateNative.INSTANCE.getSlamStatus())) {
                    slamStatus2 = slamStatus4;
                    break;
                }
                i3++;
            }
            return slamStatus2 != null ? slamStatus2 : SlamStatus.Success;
        }
        return SlamStatus.Success;
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public LocalizationStatus getLocalizationStatus() {
        LocalizationStatus localizationStatus = new LocalizationStatus(null, null, null, 7, null);
        localizationStatus.setStatus_level(LocalizationStatusLevel.Error);
        localizationStatus.setStatus_info(LocalizationStatusInfo.NoInit);
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("locate case ");
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        sb.append(locateCase.name());
        sb.append(", get locate status");
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        if (this.locateCase == null || !this.initialzed) {
            Pdlog.m3273d(this.TAG, "locate case or initialzed not init, return");
            return localizationStatus;
        }
        PuduLocateInterface.LocateCase locateCase2 = this.locateCase;
        if (locateCase2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        int i = WhenMappings.$EnumSwitchMapping$17[locateCase2.ordinal()];
        if (i == 1) {
            localizationStatus = getSlamwareLocalizationStatus();
        } else if (i == 2) {
            localizationStatus = MarkerLocateNative.INSTANCE.getLocalizationStatus();
        } else if (i == 3 || i == 4) {
            localizationStatus = LaserLocateNative.INSTANCE.getLocalizationStatus();
        }
        Pdlog.m3273d(this.TAG, "Localization Status Level: " + localizationStatus.getStatus_level() + ", Info: " + localizationStatus.getStatus_info() + ", description: " + localizationStatus.getStatus_description());
        return localizationStatus;
    }

    public final LocalizationStatus getSlamwareLocalizationStatus() {
        SlamStatus slamStatus = this.slamwareLocation.getSlamStatus();
        LocalizationStatus localizationStatus = new LocalizationStatus(null, null, null, 7, null);
        int i = WhenMappings.$EnumSwitchMapping$18[slamStatus.ordinal()];
        if (i == 1) {
            this.disLocateLose = 0.0d;
            this.lostflag = false;
            localizationStatus.setStatus_level(LocalizationStatusLevel.Normal);
            localizationStatus.setStatus_info(LocalizationStatusInfo.Normal);
        } else if (i == 2) {
            this.cur_odom = this.odometry.getOdom_data();
            if (!this.lostflag) {
                this.last_odom.setX(this.cur_odom.getX());
                this.last_odom.setY(this.cur_odom.getY());
                this.last_odom.setZ(this.cur_odom.getZ());
                this.lostflag = true;
            }
            this.disLocateLose += Math.sqrt(((this.cur_odom.getX() - this.last_odom.getX()) * (this.cur_odom.getX() - this.last_odom.getX())) + ((this.cur_odom.getY() - this.last_odom.getY()) * (this.cur_odom.getY() - this.last_odom.getY())));
            if (this.disLocateLose > 6.0d) {
                localizationStatus.setStatus_level(LocalizationStatusLevel.Error);
                localizationStatus.setStatus_info(LocalizationStatusInfo.LaserLocateLose);
                localizationStatus.setStatus_description("Laser Locate Lose! distance: " + String.valueOf(this.disLocateLose));
            }
            this.last_odom.setX(this.cur_odom.getX());
            this.last_odom.setY(this.cur_odom.getY());
            this.last_odom.setZ(this.cur_odom.getZ());
        } else {
            this.disLocateLose = 0.0d;
            this.lostflag = false;
            localizationStatus.setStatus_level(LocalizationStatusLevel.Normal);
            localizationStatus.setStatus_info(LocalizationStatusInfo.Normal);
        }
        return localizationStatus;
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void setTaskStatus(boolean task_s) {
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        int i = WhenMappings.$EnumSwitchMapping$19[locateCase.ordinal()];
        if (i == 1 || i == 2) {
            LaserLocateNative.INSTANCE.setTaskStatus(task_s);
        }
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void setRotateEnd(boolean end_s) {
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        int i = WhenMappings.$EnumSwitchMapping$20[locateCase.ordinal()];
        if (i == 1 || i == 2) {
            LaserLocateNative.INSTANCE.setRotateEnd(end_s);
        }
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public double getRotatePlanning() {
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        int i = WhenMappings.$EnumSwitchMapping$21[locateCase.ordinal()];
        if (i == 1 || i == 2) {
            return LaserLocateNative.INSTANCE.getRotatePlanning();
        }
        return 0.0d;
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public String getVersionInfo() {
        String fullVersionInfo = MarkerLocateNative.INSTANCE.getFullVersionInfo();
        String fullVersionInfo2 = LaserLocateNative.INSTANCE.getFullVersionInfo();
        Pdlog.m3273d(this.TAG, "localization commit: " + fullVersionInfo + ", " + fullVersionInfo2);
        return "{\"localization\":\"commit: dfee3dd, auth: “wujinlong”<“wujinlong@pudutech.com”>, time: “Thu Dec 8 10:22:56 2022 +0800”\"}, " + fullVersionInfo + ", " + fullVersionInfo2;
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void calibrationMarkerCamera(Function3<? super Boolean, ? super Vector3d, ? super Vector3d, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("locate case ");
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        sb.append(locateCase.name());
        sb.append(", calib start!");
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        PuduLocateInterface.LocateCase locateCase2 = this.locateCase;
        if (locateCase2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        int i = WhenMappings.$EnumSwitchMapping$22[locateCase2.ordinal()];
        if (i == 1) {
            Pdlog.m3273d(this.TAG, "start camera calib");
            MarkerLocateNative.INSTANCE.setCameraCalibrationListener(listener);
            MarkerLocateNative.INSTANCE.calibExStart();
        } else if (i == 2 || i == 3) {
            Pdlog.m3273d(this.TAG, "start lidar and odom calib");
            LaserLocateNative.INSTANCE.setCameraCalibrationListener(listener);
            LaserLocateNative.INSTANCE.calibExStart();
        }
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public boolean registSensorListener(Function2<? super String, ? super ISensorData, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        handler.invoke(TAG, this.SENSORLISTENOR);
        this.lastBroadTime = SystemClock.elapsedRealtime();
        return true;
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public boolean unregistSensorListener(Function1<? super String, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        handler.invoke(TAG);
        return true;
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void registerMarkerFrameListener(Function2<? super String, ? super IMarkerCameraData, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        int i = WhenMappings.$EnumSwitchMapping$23[locateCase.ordinal()];
        if (i == 1 || i == 2) {
            String TAG = this.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
            handler.invoke(TAG, this.MARKERSENSOR);
        }
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void unregistMarkerFrameListener(Function1<? super String, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        handler.invoke(TAG);
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void registerLaserListener(Function2<? super String, ? super ILidarData, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        int i = WhenMappings.$EnumSwitchMapping$24[locateCase.ordinal()];
        if (i == 1 || i == 2) {
            String TAG = this.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
            handler.invoke(TAG, this.lidarListener);
        }
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void unregisterLaserListener(Function1<? super String, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        handler.invoke(TAG);
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void registerMonocularCameraListener(Function2<? super String, ? super IMarkerCameraData, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        if (WhenMappings.$EnumSwitchMapping$25[locateCase.ordinal()] != 1) {
            return;
        }
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        handler.invoke(TAG, this.frontCameraListener);
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void unregistMonocularCameraListener(Function1<? super String, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        handler.invoke(TAG);
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void addSetExposureInterface(Function1<? super Integer, Boolean> func) {
        Intrinsics.checkParameterIsNotNull(func, "func");
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        int i = WhenMappings.$EnumSwitchMapping$26[locateCase.ordinal()];
        if (i == 1) {
            MarkerLocateNative.INSTANCE.setSetExposureHandle(func);
        } else {
            if (i != 2) {
                return;
            }
            LaserLocateNative.INSTANCE.setSetExposureHandle(func);
        }
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void addGetExposureInterface(Function0<Integer> func) {
        Intrinsics.checkParameterIsNotNull(func, "func");
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        int i = WhenMappings.$EnumSwitchMapping$27[locateCase.ordinal()];
        if (i == 1) {
            MarkerLocateNative.INSTANCE.setGetExposureHandle(func);
        } else {
            if (i != 2) {
                return;
            }
            LaserLocateNative.INSTANCE.setGetExposureHandle(func);
        }
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void addSaveMapInterface(Function0<Boolean> func) {
        Intrinsics.checkParameterIsNotNull(func, "func");
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        int i = WhenMappings.$EnumSwitchMapping$28[locateCase.ordinal()];
        if (i == 1 || i == 2) {
            LaserLocateNative.INSTANCE.setSavePdMapHandle(func);
        }
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void setLocateListener(String name, LocalizationListener listener) {
        if (name == null || listener == null) {
            return;
        }
        this.localizationListeners.add(name, listener);
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void removeLocateListener(String name) {
        if (name != null) {
            this.localizationListeners.remove(name);
        }
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void addPoseListener(String name, Function2<? super Vector3d, ? super Vector3d, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (name != null) {
            this.poseListeners.add(name, listener);
        }
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void removePoseListener(String name) {
        if (name != null) {
            this.poseListeners.remove(name);
        }
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void addOdomListener(String name, Function1<? super Vector3d, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (name != null) {
            this.odomListeners.add(name, listener);
        }
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void removeOdomListener(String name) {
        if (name != null) {
            this.odomListeners.remove(name);
        }
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void addSpeedListener(String name, Function1<? super Vector2d, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (name != null) {
            this.speedListeners.add(name, listener);
        }
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void removeSpeedListener(String name) {
        if (name != null) {
            this.speedListeners.remove(name);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateOdom(double line_speed, double angle_speed, double interval) {
        BuildersKt__Builders_commonKt.launch$default(this.LocalizationScope, null, null, new Localization$updateOdom$1(this, line_speed, angle_speed, interval, null), 3, null);
    }

    @Override // com.pudutech.mirsdk.mircore.PuduLocateInterface
    public void setLidarSerialNumber(String sn) {
        if (sn != null && sn.hashCode() == 0 && sn.equals("")) {
            Pdlog.m3273d(this.TAG, "lidar serial number: unkwon");
        } else {
            Pdlog.m3273d(this.TAG, "lidar serial number: " + sn);
        }
        PuduLocateInterface.LocateCase locateCase = this.locateCase;
        if (locateCase == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locateCase");
        }
        int i = WhenMappings.$EnumSwitchMapping$33[locateCase.ordinal()];
        if (i == 1 || i == 2) {
            LaserLocateNative.INSTANCE.setLidarSerialNumber(sn);
        }
    }
}
