package com.pudutech.mirsdk.mircore;

import android.content.Context;
import com.pudutech.mirsdk.hardware.ILidarData;
import com.pudutech.mirsdk.hardware.IMarkerCameraData;
import com.pudutech.mirsdk.hardware.ISensorData;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.Vector2d;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatus;
import com.pudutech.mirsdk.mircore.coreparcel.SlamStatus;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: PuduLocateInterface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u000f\bf\u0018\u00002\u00020\u0001:\u0001[J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J&\u0010\u0007\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00030\u000bH&J,\u0010\r\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00030\u000eH&J\u0016\u0010\u000f\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005H&J+\u0010\u0011\u001a\u00020\u00032!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0012\u0012\b\b\b\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00100\u000bH&J&\u0010\u0014\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00030\u000bH&J(\u0010\u0016\u001a\u00020\u00032\u001e\u0010\n\u001a\u001a\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00030\u0017H&J\b\u0010\u0018\u001a\u00020\u0010H&J \u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH&J\b\u0010 \u001a\u00020!H&J\b\u0010\"\u001a\u00020#H&J\b\u0010$\u001a\u00020%H&J\b\u0010&\u001a\u00020\tH&J\u0010\u0010'\u001a\u00020\u00102\u0006\u0010(\u001a\u00020)H&J\b\u0010*\u001a\u00020\u0010H&J\u0010\u0010+\u001a\u00020\u00102\u0006\u0010(\u001a\u00020)H&J\u0010\u0010,\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u0010H&J\"\u0010.\u001a\u00020\u00102\u0018\u0010/\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u00030\u000eH&J\"\u00101\u001a\u00020\u00032\u0018\u0010/\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020\u00030\u000eH&J\"\u00103\u001a\u00020\u00032\u0018\u0010/\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020\u00030\u000eH&J\"\u00105\u001a\u00020\u00032\u0018\u0010/\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020\u00030\u000eH&J\b\u00106\u001a\u00020\u0003H&J-\u00107\u001a\u00020\u00032\u000e\u00108\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u0001092\u000e\u0010:\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u000109H&¢\u0006\u0002\u0010;J%\u0010<\u001a\u00020\u00102\u0006\u0010=\u001a\u00020\f2\u000e\u0010>\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u000109H&¢\u0006\u0002\u0010?J\u0012\u0010@\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\tH&J\u0012\u0010A\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\tH&J\u0012\u0010B\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\tH&J\u0012\u0010C\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\tH&J\b\u0010D\u001a\u00020\u0010H&J\u0018\u0010E\u001a\u00020\u00032\u0006\u0010F\u001a\u00020\f2\u0006\u0010G\u001a\u00020\fH&J\b\u0010H\u001a\u00020\u0003H&J\b\u0010I\u001a\u00020\u0003H&J\u0012\u0010J\u001a\u00020\u00032\b\u0010K\u001a\u0004\u0018\u00010\tH&J\u001c\u0010L\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010MH&J\u0010\u0010N\u001a\u00020\u00102\u0006\u0010O\u001a\u00020)H&J\u0018\u0010N\u001a\u00020\u00102\u0006\u0010P\u001a\u00020)2\u0006\u0010Q\u001a\u00020)H&J\u0018\u0010N\u001a\u00020\u00102\u0006\u0010P\u001a\u00020)2\u0006\u0010R\u001a\u00020\tH&J\u0010\u0010S\u001a\u00020\u00032\u0006\u0010T\u001a\u00020\u0010H&J\u0010\u0010U\u001a\u00020\u00032\u0006\u0010V\u001a\u00020\u0010H&J\u001c\u0010W\u001a\u00020\u00032\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u000bH&J\u001c\u0010X\u001a\u00020\u00032\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u000bH&J\u001c\u0010Y\u001a\u00020\u00102\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u000bH&J\u001c\u0010Z\u001a\u00020\u00032\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u000bH&¨\u0006\\"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/PuduLocateInterface;", "", "addGetExposureInterface", "", "func", "Lkotlin/Function0;", "", "addOdomListener", "name", "", "listener", "Lkotlin/Function1;", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "addPoseListener", "Lkotlin/Function2;", "addSaveMapInterface", "", "addSetExposureInterface", "Lkotlin/ParameterName;", "exposure", "addSpeedListener", "Lcom/pudutech/mirsdk/hardware/serialize/Vector2d;", "calibrationMarkerCamera", "Lkotlin/Function3;", "checkFinishInitLocalization", "createModules", "context", "Landroid/content/Context;", "case", "Lcom/pudutech/mirsdk/mircore/PuduLocateInterface$LocateCase;", "machineType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineModel;", "getLocalizationStatus", "Lcom/pudutech/mirsdk/mircore/coreparcel/LocalizationStatus;", "getRotatePlanning", "", "getSlamStatus", "Lcom/pudutech/mirsdk/mircore/coreparcel/SlamStatus;", "getVersionInfo", "initConfig", "config", "", "initLocalization", "initMarkerConfig", "onSwitchAutoExposeNative", "isAuto", "registSensorListener", "handler", "Lcom/pudutech/mirsdk/hardware/ISensorData;", "registerLaserListener", "Lcom/pudutech/mirsdk/hardware/ILidarData;", "registerMarkerFrameListener", "Lcom/pudutech/mirsdk/hardware/IMarkerCameraData;", "registerMonocularCameraListener", "relocalization", "relocalizationByPoints", "ids", "", "poses", "([Ljava/lang/String;[Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "relocalizationPointIsValid", "pose", "vaild_poses", "(Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;[Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)Z", "removeLocateListener", "removeOdomListener", "removePoseListener", "removeSpeedListener", "saveMarkerMap", "setFloorElevatorPoint", "pose1", "pose2", "setInMapingMode", "setInRemapingMode", "setLidarSerialNumber", "sn", "setLocateListener", "Lcom/pudutech/mirsdk/mircore/LocalizationListener;", "setLocateMap", "locate_map", "map_config", "map_pgm", "map_path", "setRotateEnd", "end_s", "setTaskStatus", "task_s", "unregistMarkerFrameListener", "unregistMonocularCameraListener", "unregistSensorListener", "unregisterLaserListener", "LocateCase", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface PuduLocateInterface {
    void addGetExposureInterface(Function0<Integer> func);

    void addOdomListener(String name, Function1<? super Vector3d, Unit> listener);

    void addPoseListener(String name, Function2<? super Vector3d, ? super Vector3d, Unit> listener);

    void addSaveMapInterface(Function0<Boolean> func);

    void addSetExposureInterface(Function1<? super Integer, Boolean> func);

    void addSpeedListener(String name, Function1<? super Vector2d, Unit> listener);

    void calibrationMarkerCamera(Function3<? super Boolean, ? super Vector3d, ? super Vector3d, Unit> listener);

    boolean checkFinishInitLocalization();

    void createModules(Context context, LocateCase r2, MachineModel machineType);

    LocalizationStatus getLocalizationStatus();

    double getRotatePlanning();

    SlamStatus getSlamStatus();

    String getVersionInfo();

    boolean initConfig(byte[] config);

    boolean initLocalization();

    boolean initMarkerConfig(byte[] config);

    void onSwitchAutoExposeNative(boolean isAuto);

    boolean registSensorListener(Function2<? super String, ? super ISensorData, Unit> handler);

    void registerLaserListener(Function2<? super String, ? super ILidarData, Unit> handler);

    void registerMarkerFrameListener(Function2<? super String, ? super IMarkerCameraData, Unit> handler);

    void registerMonocularCameraListener(Function2<? super String, ? super IMarkerCameraData, Unit> handler);

    void relocalization();

    void relocalizationByPoints(String[] ids, Vector3d[] poses);

    boolean relocalizationPointIsValid(Vector3d pose, Vector3d[] vaild_poses);

    void removeLocateListener(String name);

    void removeOdomListener(String name);

    void removePoseListener(String name);

    void removeSpeedListener(String name);

    boolean saveMarkerMap();

    void setFloorElevatorPoint(Vector3d pose1, Vector3d pose2);

    void setInMapingMode();

    void setInRemapingMode();

    void setLidarSerialNumber(String sn);

    void setLocateListener(String name, LocalizationListener listener);

    boolean setLocateMap(byte[] locate_map);

    boolean setLocateMap(byte[] map_config, String map_path);

    boolean setLocateMap(byte[] map_config, byte[] map_pgm);

    void setRotateEnd(boolean end_s);

    void setTaskStatus(boolean task_s);

    void unregistMarkerFrameListener(Function1<? super String, Unit> handler);

    void unregistMonocularCameraListener(Function1<? super String, Unit> handler);

    boolean unregistSensorListener(Function1<? super String, Unit> handler);

    void unregisterLaserListener(Function1<? super String, Unit> handler);

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: PuduLocateInterface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/PuduLocateInterface$LocateCase;", "", "id", "", "(Ljava/lang/String;II)V", "getId", "()I", "Marker", "Laser", "LaserMark", "Slamware", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum LocateCase {
        Marker(0),
        Laser(1),
        LaserMark(2),
        Slamware(3);

        private final int id;

        LocateCase(int i) {
            this.id = i;
        }

        public final int getId() {
            return this.id;
        }
    }
}
