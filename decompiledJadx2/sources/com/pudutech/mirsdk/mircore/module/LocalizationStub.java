package com.pudutech.mirsdk.mircore.module;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.ILidarData;
import com.pudutech.mirsdk.hardware.IMarkerCameraData;
import com.pudutech.mirsdk.hardware.ISensorData;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.Vector2d;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.LocalizationInterface;
import com.pudutech.mirsdk.mircore.LocalizationListener;
import com.pudutech.mirsdk.mircore.MarkerCameraCalibResultListener;
import com.pudutech.mirsdk.mircore.PuduLocateInterface;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatus;
import com.pudutech.mirsdk.mircore.coreparcel.SlamStatus;
import com.pudutech.mirsdk.mircore.localization.Localization;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: LocalizationStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ$\u0010\r\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\t0\u0010J*\u0010\u0012\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\t0\u0013J\u0014\u0010\u0014\u001a\u00020\t2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000bJ\u001a\u0010\u0017\u001a\u00020\t2\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00160\u0010J$\u0010\u0019\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\t0\u0010J\u0012\u0010\u001b\u001a\u00020\t2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\u0006\u0010 \u001a\u00020!J\b\u0010\"\u001a\u00020#H\u0016J\u0006\u0010$\u001a\u00020\u0004J\u000e\u0010%\u001a\u00020\u00162\u0006\u0010&\u001a\u00020'J\u000e\u0010(\u001a\u00020\u00162\u0006\u0010&\u001a\u00020'J\b\u0010)\u001a\u00020\u0016H\u0016J \u0010*\u001a\u00020\t2\u0018\u0010+\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020\t0\u0013J \u0010-\u001a\u00020\t2\u0018\u0010+\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\t0\u0013J \u0010/\u001a\u00020\t2\u0018\u0010+\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\t0\u0013J \u00101\u001a\u00020\t2\u0018\u0010+\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\t0\u0013J\b\u00102\u001a\u00020\tH\u0016J(\u00103\u001a\u00020\t2\u000e\u00104\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001052\u000e\u00106\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u000105H\u0016J \u00107\u001a\u00020\u00162\u0006\u00108\u001a\u00020\u00112\u000e\u00109\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u000105H\u0016J\u0012\u0010:\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u0010;\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004J\u0010\u0010<\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004J\u0010\u0010=\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004J\b\u0010>\u001a\u00020\u0016H\u0016J\u0018\u0010?\u001a\u00020\t2\u0006\u0010@\u001a\u00020\u00112\u0006\u0010A\u001a\u00020\u0011H\u0016J\b\u0010B\u001a\u00020\tH\u0016J\b\u0010C\u001a\u00020\tH\u0016J\u0012\u0010D\u001a\u00020\t2\b\u0010E\u001a\u0004\u0018\u00010\u0004H\u0016J\u001c\u0010F\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010GH\u0016J\u000e\u0010H\u001a\u00020\u00162\u0006\u0010I\u001a\u00020'J\u0016\u0010H\u001a\u00020\u00162\u0006\u0010J\u001a\u00020'2\u0006\u0010K\u001a\u00020'J\u0016\u0010H\u001a\u00020\u00162\u0006\u0010J\u001a\u00020'2\u0006\u0010L\u001a\u00020\u0004J\u000e\u0010M\u001a\u00020\t2\u0006\u0010N\u001a\u00020\u0016J\u000e\u0010O\u001a\u00020\t2\u0006\u0010P\u001a\u00020\u0016J\u000e\u0010Q\u001a\u00020\t2\u0006\u0010R\u001a\u00020\u0016J\u001e\u0010S\u001a\u00020\t2\u0006\u0010T\u001a\u00020U2\u0006\u0010V\u001a\u00020W2\u0006\u0010X\u001a\u00020YJ\u001a\u0010Z\u001a\u00020\t2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\u0010J\u001a\u0010[\u001a\u00020\t2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\u0010J\u001a\u0010\\\u001a\u00020\t2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\u0010J\u001a\u0010]\u001a\u00020\t2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\u0010R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006^"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/module/LocalizationStub;", "Lcom/pudutech/mirsdk/mircore/LocalizationInterface$Stub;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "localImpl", "Lcom/pudutech/mirsdk/mircore/PuduLocateInterface;", "addGetExposureInterface", "", "getter", "Lkotlin/Function0;", "", "addOdomListener", "name", "listener", "Lkotlin/Function1;", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "addPoseListener", "Lkotlin/Function2;", "addSaveMapInterface", "func", "", "addSetExposureInterface", "setter", "addSpeedListener", "Lcom/pudutech/mirsdk/hardware/serialize/Vector2d;", "calibrationMarkerCamera", "p0", "Lcom/pudutech/mirsdk/mircore/MarkerCameraCalibResultListener;", "getLocalizationStatus", "Lcom/pudutech/mirsdk/mircore/coreparcel/LocalizationStatus;", "getRotatePlanning", "", "getSlamStatus", "Lcom/pudutech/mirsdk/mircore/coreparcel/SlamStatus;", "getVersionInfo", "initConfig", "config", "", "initMarkerConfig", "isLocalizationFinishInitialization", "registSensorListener", "handler", "Lcom/pudutech/mirsdk/hardware/ISensorData;", "registerLaserListener", "Lcom/pudutech/mirsdk/hardware/ILidarData;", "registerMarkerFrameListener", "Lcom/pudutech/mirsdk/hardware/IMarkerCameraData;", "registerMonocularCameraListener", "reloadLocalization", "relocalizationByPoints", "ids", "", "poses", "relocalizationPointIsValid", "pose", "vaild_poses", "removeLocateListener", "removeOdomListener", "removePoseListener", "removeSpeedListener", "saveMarkerMap", "setFloorElevatorPoint", "pose1", "pose2", "setInMapingMode", "setInRemapingMode", "setLidarSerialNumber", "sn", "setLocateListener", "Lcom/pudutech/mirsdk/mircore/LocalizationListener;", "setLocateMap", "locate_map", "map_config", "map_pgm", "map_path", "setRotateEnd", "end_s", "setTaskStatus", "task_s", "switchAutoExposure", "switch_req", "switchLocateCase", "context", "Landroid/content/Context;", "case", "Lcom/pudutech/mirsdk/mircore/PuduLocateInterface$LocateCase;", "machineType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineModel;", "unregistMarkerFrameListener", "unregistMonocularCameraListener", "unregistSensorListener", "unregisterLaserListener", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class LocalizationStub extends LocalizationInterface.Stub {
    private final String TAG = getClass().getSimpleName();
    private final PuduLocateInterface localImpl = new Localization();

    @Override // com.pudutech.mirsdk.mircore.LocalizationInterface
    public boolean isLocalizationFinishInitialization() {
        boolean checkFinishInitLocalization = this.localImpl.checkFinishInitLocalization();
        Pdlog.m3273d(this.TAG, "localization stub init localization status is " + checkFinishInitLocalization);
        return checkFinishInitLocalization;
    }

    @Override // com.pudutech.mirsdk.mircore.LocalizationInterface
    public void setLocateListener(String name, LocalizationListener listener) {
        if (name == null || listener == null) {
            return;
        }
        this.localImpl.setLocateListener(name, listener);
    }

    @Override // com.pudutech.mirsdk.mircore.LocalizationInterface
    public void removeLocateListener(String name) {
        if (name != null) {
            this.localImpl.removeLocateListener(name);
        }
    }

    @Override // com.pudutech.mirsdk.mircore.LocalizationInterface
    public SlamStatus getSlamStatus() {
        return this.localImpl.getSlamStatus();
    }

    @Override // com.pudutech.mirsdk.mircore.LocalizationInterface
    public LocalizationStatus getLocalizationStatus() {
        return this.localImpl.getLocalizationStatus();
    }

    @Override // com.pudutech.mirsdk.mircore.LocalizationInterface
    public void reloadLocalization() {
        this.localImpl.relocalization();
    }

    @Override // com.pudutech.mirsdk.mircore.LocalizationInterface
    public void relocalizationByPoints(List<String> ids, List<Vector3d> poses) {
        String[] strArr;
        PuduLocateInterface puduLocateInterface = this.localImpl;
        Vector3d[] vector3dArr = null;
        if (ids != null) {
            Object[] array = ids.toArray(new String[0]);
            if (array == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            strArr = (String[]) array;
        } else {
            strArr = null;
        }
        if (poses != null) {
            Object[] array2 = poses.toArray(new Vector3d[0]);
            if (array2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            vector3dArr = (Vector3d[]) array2;
        }
        puduLocateInterface.relocalizationByPoints(strArr, vector3dArr);
    }

    @Override // com.pudutech.mirsdk.mircore.LocalizationInterface
    public boolean relocalizationPointIsValid(Vector3d pose, List<Vector3d> vaild_poses) {
        Vector3d[] vector3dArr;
        Intrinsics.checkParameterIsNotNull(pose, "pose");
        PuduLocateInterface puduLocateInterface = this.localImpl;
        if (vaild_poses != null) {
            Object[] array = vaild_poses.toArray(new Vector3d[0]);
            if (array == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            vector3dArr = (Vector3d[]) array;
        } else {
            vector3dArr = null;
        }
        return puduLocateInterface.relocalizationPointIsValid(pose, vector3dArr);
    }

    @Override // com.pudutech.mirsdk.mircore.LocalizationInterface
    public void setFloorElevatorPoint(Vector3d pose1, Vector3d pose2) {
        Intrinsics.checkParameterIsNotNull(pose1, "pose1");
        Intrinsics.checkParameterIsNotNull(pose2, "pose2");
        this.localImpl.setFloorElevatorPoint(pose1, pose2);
    }

    @Override // com.pudutech.mirsdk.mircore.LocalizationInterface
    public void calibrationMarkerCamera(final MarkerCameraCalibResultListener p0) {
        if (p0 == null) {
            return;
        }
        this.localImpl.calibrationMarkerCamera(new Function3<Boolean, Vector3d, Vector3d, Unit>() { // from class: com.pudutech.mirsdk.mircore.module.LocalizationStub$calibrationMarkerCamera$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Vector3d vector3d, Vector3d vector3d2) {
                invoke(bool.booleanValue(), vector3d, vector3d2);
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z, Vector3d rpy, Vector3d pose) {
                Intrinsics.checkParameterIsNotNull(rpy, "rpy");
                Intrinsics.checkParameterIsNotNull(pose, "pose");
                MarkerCameraCalibResultListener.this.calibResult(z, rpy, pose);
            }
        });
    }

    @Override // com.pudutech.mirsdk.mircore.LocalizationInterface
    public void setInMapingMode() {
        this.localImpl.setInMapingMode();
    }

    @Override // com.pudutech.mirsdk.mircore.LocalizationInterface
    public void setInRemapingMode() {
        this.localImpl.setInRemapingMode();
    }

    @Override // com.pudutech.mirsdk.mircore.LocalizationInterface
    public boolean saveMarkerMap() {
        return this.localImpl.saveMarkerMap();
    }

    public final boolean initConfig(byte[] config) {
        Intrinsics.checkParameterIsNotNull(config, "config");
        return this.localImpl.initConfig(config);
    }

    public final boolean initMarkerConfig(byte[] config) {
        Intrinsics.checkParameterIsNotNull(config, "config");
        return this.localImpl.initMarkerConfig(config);
    }

    public final void switchLocateCase(Context context, PuduLocateInterface.LocateCase r3, MachineModel machineType) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(r3, "case");
        Intrinsics.checkParameterIsNotNull(machineType, "machineType");
        this.localImpl.createModules(context, r3, machineType);
    }

    public final boolean setLocateMap(byte[] locate_map) {
        Intrinsics.checkParameterIsNotNull(locate_map, "locate_map");
        return this.localImpl.setLocateMap(locate_map);
    }

    public final boolean setLocateMap(byte[] map_config, byte[] map_pgm) {
        Intrinsics.checkParameterIsNotNull(map_config, "map_config");
        Intrinsics.checkParameterIsNotNull(map_pgm, "map_pgm");
        return this.localImpl.setLocateMap(map_config, map_pgm);
    }

    public final boolean setLocateMap(byte[] map_config, String map_path) {
        Intrinsics.checkParameterIsNotNull(map_config, "map_config");
        Intrinsics.checkParameterIsNotNull(map_path, "map_path");
        return this.localImpl.setLocateMap(map_config, map_path);
    }

    public final void registSensorListener(Function2<? super String, ? super ISensorData, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        this.localImpl.registSensorListener(handler);
    }

    public final void unregistSensorListener(Function1<? super String, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        this.localImpl.unregistSensorListener(handler);
    }

    public final void registerMarkerFrameListener(Function2<? super String, ? super IMarkerCameraData, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        this.localImpl.registerMarkerFrameListener(handler);
    }

    public final void unregistMarkerFrameListener(Function1<? super String, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        this.localImpl.unregistMarkerFrameListener(handler);
    }

    public final void registerLaserListener(Function2<? super String, ? super ILidarData, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        this.localImpl.registerLaserListener(handler);
    }

    public final void unregisterLaserListener(Function1<? super String, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        this.localImpl.unregisterLaserListener(handler);
    }

    public final void registerMonocularCameraListener(Function2<? super String, ? super IMarkerCameraData, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        this.localImpl.registerMonocularCameraListener(handler);
    }

    public final void unregistMonocularCameraListener(Function1<? super String, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        this.localImpl.unregistMonocularCameraListener(handler);
    }

    public final String getVersionInfo() {
        return this.localImpl.getVersionInfo();
    }

    public final void addPoseListener(String name, Function2<? super Vector3d, ? super Vector3d, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (name != null) {
            this.localImpl.addPoseListener(name, listener);
        }
    }

    public final void removePoseListener(String name) {
        if (name != null) {
            this.localImpl.removePoseListener(name);
        }
    }

    public final void addSpeedListener(String name, Function1<? super Vector2d, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (name != null) {
            this.localImpl.addSpeedListener(name, listener);
        }
    }

    public final void removeSpeedListener(String name) {
        if (name != null) {
            this.localImpl.removeSpeedListener(name);
        }
    }

    public final void switchAutoExposure(boolean switch_req) {
        this.localImpl.onSwitchAutoExposeNative(switch_req);
    }

    public final void addSetExposureInterface(Function1<? super Integer, Boolean> setter) {
        Intrinsics.checkParameterIsNotNull(setter, "setter");
        this.localImpl.addSetExposureInterface(setter);
    }

    public final void addGetExposureInterface(Function0<Integer> getter) {
        Intrinsics.checkParameterIsNotNull(getter, "getter");
        this.localImpl.addGetExposureInterface(getter);
    }

    public final void addSaveMapInterface(Function0<Boolean> func) {
        Intrinsics.checkParameterIsNotNull(func, "func");
        this.localImpl.addSaveMapInterface(func);
    }

    public final void addOdomListener(String name, Function1<? super Vector3d, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (name != null) {
            this.localImpl.addOdomListener(name, listener);
        }
    }

    public final void removeOdomListener(String name) {
        if (name != null) {
            this.localImpl.removeOdomListener(name);
        }
    }

    @Override // com.pudutech.mirsdk.mircore.LocalizationInterface
    public void setLidarSerialNumber(String sn) {
        this.localImpl.setLidarSerialNumber(sn);
    }

    public final void setTaskStatus(boolean task_s) {
        this.localImpl.setTaskStatus(task_s);
    }

    public final void setRotateEnd(boolean end_s) {
        this.localImpl.setRotateEnd(end_s);
    }

    public final double getRotatePlanning() {
        return this.localImpl.getRotatePlanning();
    }
}
