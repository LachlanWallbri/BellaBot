package com.pudutech.freeinstall_wrapper;

import android.os.ParcelFileDescriptor;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.mapify.HardwareExceptListener;
import com.pudutech.mirsdk.aidl.mapify.LocalizationListener;
import com.pudutech.mirsdk.aidl.mapify.LocateMappingInterface;
import com.pudutech.mirsdk.aidl.mapify.MapingModuleListener;
import com.pudutech.mirsdk.aidl.mapify.MappingFunctionInterface;
import com.pudutech.mirsdk.aidl.mapify.TopoMappingInteface;
import com.pudutech.mirsdk.aidl.serialize.CameraType;
import com.pudutech.mirsdk.aidl.serialize.DockerResult;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdk.compat.topo.SchedulingConfigKey;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import com.pudutech.mirsdk.mircore.coreparcel.TopoTrack;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: LocateMappingManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\bJ\u0006\u0010\u0014\u001a\u00020\bJ\r\u0010\u0015\u001a\u0004\u0018\u00010\u0016¢\u0006\u0002\u0010\u0017J\r\u0010\u0018\u001a\u0004\u0018\u00010\u0016¢\u0006\u0002\u0010\u0017J\r\u0010\u0019\u001a\u0004\u0018\u00010\u0016¢\u0006\u0002\u0010\u0017J\u0015\u0010\u001a\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u001b\u001a\u00020\u001c¢\u0006\u0002\u0010\u001dJ\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!J\u0006\u0010#\u001a\u00020\bJ$\u0010$\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010!2\u0006\u0010\t\u001a\u00020\u00042\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\"0!J\"\u0010&\u001a\u00020\b2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\"0!2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\"0!J*\u0010)\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010!2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\"0!2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020*0!J\u000e\u0010,\u001a\u00020\b2\u0006\u0010-\u001a\u00020\u0004J\u0006\u0010.\u001a\u00020\bJ\b\u0010/\u001a\u0004\u0018\u000100J\u000e\u00101\u001a\n\u0012\u0004\u0012\u000203\u0018\u000102J\u0010\u00104\u001a\u0004\u0018\u00010*2\u0006\u0010\t\u001a\u00020\u0004J\b\u00105\u001a\u0004\u0018\u000106J\b\u00107\u001a\u0004\u0018\u000108J\b\u00109\u001a\u0004\u0018\u000108J\b\u0010:\u001a\u0004\u0018\u000108J\b\u0010;\u001a\u0004\u0018\u00010\u0004J\u000e\u0010<\u001a\n\u0012\u0004\u0012\u000203\u0018\u000102J\u000e\u0010=\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u000102J\u0015\u0010>\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b?J\u0006\u0010@\u001a\u00020\bJ\u0006\u0010A\u001a\u00020\bJ\u0006\u0010B\u001a\u00020\bJ\u000e\u0010C\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004J\u000e\u0010D\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0004J\u0006\u0010E\u001a\u00020\bJ\u000e\u0010F\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004J\u0014\u0010G\u001a\u00020\b2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020H0!J\u0016\u0010I\u001a\u00020\b2\u0006\u0010-\u001a\u00020\u00042\u0006\u0010J\u001a\u00020*J\u0006\u0010K\u001a\u00020\bJ\u0014\u0010L\u001a\u00020\b2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!J\u0014\u0010M\u001a\u00020\b2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001c0!J\u0006\u0010N\u001a\u00020\bJ\u0016\u0010O\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010P\u001a\u00020\u001cR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Q"}, m3961d2 = {"Lcom/pudutech/freeinstall_wrapper/LocateMappingManager;", "", "()V", "TAG", "", "mapServiceSdk", "Lcom/pudutech/mirsdk/aidl/mapify/MappingFunctionInterface;", "addHardWareListener", "", "name", "hardwareExceptListener", "Lcom/pudutech/mirsdk/aidl/mapify/HardwareExceptListener;", "addLocalizationListener", "listenerName", "localizationListener", "Lcom/pudutech/mirsdk/aidl/mapify/LocalizationListener;", "addMapingModuleListener", "listener", "Lcom/pudutech/mirsdk/aidl/mapify/MapingModuleListener;", "addMappingSensorListener", "cancelMapping", "checkBeginMappingMarkerVisible", "", "()Ljava/lang/Boolean;", "checkFinishMappingMarkerVisible", "checkMappingOpt", "checkMemoryLimit", "size", "", "(I)Ljava/lang/Boolean;", "checkVirtualWall", "", "list", "", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "clearOldData", "createCruise", "var1", "createScheduleConfig", SchedulingConfigKey.PARKING_POSE, SchedulingConfigKey.TAKING_POSE, "createTopomap", "Lcom/pudutech/mirsdk/mircore/coreparcel/Destination;", "vector3ds", "deleteSavedMap", "mapName", "finishMapping", "getCameraType", "Lcom/pudutech/mirsdk/aidl/serialize/CameraType;", "getDoubleRoadTracks", "", "Lcom/pudutech/mirsdk/mircore/coreparcel/TopoTrack;", "getMapInitPoint", "getMapType", "Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;", "getOptimizedMap", "Landroid/os/ParcelFileDescriptor;", "getOptimizedMapData", "getRealMapData", "getStaticMap", "getTopPath", "getVirtualWallList", "init", "init$module_freeinstall_mirsdk_wrapper_release", "initExtendDrawing", "initExtendFlag", "reInitModules", "removeHardWareListener", "removeLocalizationListener", "removeMappingSensorListener", "removeReinitModuleListener", "saveChargeDockerList", "Lcom/pudutech/mirsdk/aidl/serialize/DockerResult;", "saveOptimizedMap", "destination", "saveTopMap", "saveVirtualWall", "setTwoWayroad", "startMapping", "updateMapVersion", "version", "module_freeinstall_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LocateMappingManager {
    private static MappingFunctionInterface mapServiceSdk;
    public static final LocateMappingManager INSTANCE = new LocateMappingManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private LocateMappingManager() {
    }

    public final void init$module_freeinstall_mirsdk_wrapper_release(MappingFunctionInterface mapServiceSdk2) {
        Intrinsics.checkParameterIsNotNull(mapServiceSdk2, "mapServiceSdk");
        mapServiceSdk = mapServiceSdk2;
    }

    public final void reInitModules() {
        LocateMappingInterface locatMappingInterface;
        Pdlog.m3273d(TAG, "reInitModules");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return;
        }
        locatMappingInterface.reInitModules();
    }

    public final Boolean checkBeginMappingMarkerVisible() {
        LocateMappingInterface locatMappingInterface;
        Pdlog.m3273d(TAG, "checkBeginMappingMarkerVisible");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return null;
        }
        return Boolean.valueOf(locatMappingInterface.checkBeginMappingMarkerVisible());
    }

    public final Boolean checkFinishMappingMarkerVisible() {
        LocateMappingInterface locatMappingInterface;
        Pdlog.m3273d(TAG, "checkFinishMappingMarkerVisible");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return null;
        }
        return Boolean.valueOf(locatMappingInterface.checkFinishMappingMarkerVisible());
    }

    public final void cancelMapping() {
        LocateMappingInterface locatMappingInterface;
        Pdlog.m3273d(TAG, "cancelMapping");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return;
        }
        locatMappingInterface.cancelMapping();
    }

    public final void startMapping() {
        Pdlog.m3273d(TAG, "startMapping");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new LocateMappingManager$startMapping$1(null), 3, null);
    }

    public final void finishMapping() {
        LocateMappingInterface locatMappingInterface;
        Pdlog.m3273d(TAG, "finishMapping");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return;
        }
        locatMappingInterface.finishMapping();
    }

    public final Boolean checkMappingOpt() {
        LocateMappingInterface locatMappingInterface;
        Pdlog.m3273d(TAG, "checkMappingOpt");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return null;
        }
        return Boolean.valueOf(locatMappingInterface.checkMappingOpt());
    }

    public final ParcelFileDescriptor getOptimizedMap() {
        LocateMappingInterface locatMappingInterface;
        Pdlog.m3273d(TAG, "getOptimizedMap");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return null;
        }
        return locatMappingInterface.getOptimizedMap();
    }

    public final void saveOptimizedMap(String mapName, Destination destination) {
        LocateMappingInterface locatMappingInterface;
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Pdlog.m3273d(TAG, "saveOptimizedMap---" + mapName + " destination---" + destination.getVector().getX());
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return;
        }
        locatMappingInterface.saveOptimizedMap(mapName, destination);
    }

    public final ParcelFileDescriptor getOptimizedMapData() {
        LocateMappingInterface locatMappingInterface;
        Pdlog.m3273d(TAG, "getOptimizedMapData");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return null;
        }
        return locatMappingInterface.getOptimizedMap();
    }

    public final ParcelFileDescriptor getRealMapData() {
        LocateMappingInterface locatMappingInterface;
        Pdlog.m3273d(TAG, "getRealMapData");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return null;
        }
        return locatMappingInterface.getRealMapData();
    }

    public final void addLocalizationListener(String listenerName, LocalizationListener localizationListener) {
        LocateMappingInterface locatMappingInterface;
        Intrinsics.checkParameterIsNotNull(listenerName, "listenerName");
        Intrinsics.checkParameterIsNotNull(localizationListener, "localizationListener");
        Pdlog.m3273d(TAG, "addLocalizationListener " + listenerName);
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return;
        }
        locatMappingInterface.addLocalizationListener(listenerName, localizationListener);
    }

    public final void removeLocalizationListener(String listenerName) {
        LocateMappingInterface locatMappingInterface;
        Intrinsics.checkParameterIsNotNull(listenerName, "listenerName");
        Pdlog.m3273d(TAG, "removeLocalizationListener " + listenerName);
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return;
        }
        locatMappingInterface.removeLocalizationListener(listenerName);
    }

    public final void initExtendFlag() {
        LocateMappingInterface locatMappingInterface;
        Pdlog.m3273d(TAG, "initExtendFlag");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return;
        }
        locatMappingInterface.initExtendFlag();
    }

    public final List<Destination> createTopomap(List<Vector3d> vector3ds, List<Destination> list) {
        TopoMappingInteface topoMappingInteface;
        Intrinsics.checkParameterIsNotNull(vector3ds, "vector3ds");
        Intrinsics.checkParameterIsNotNull(list, "list");
        Pdlog.m3273d(TAG, "createTopomap " + list);
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (topoMappingInteface = mappingFunctionInterface.getTopoMappingInteface()) == null) {
            return null;
        }
        return topoMappingInteface.createTopomap(vector3ds, list);
    }

    public final List<Vector3d> createCruise(String name, List<Vector3d> var1) {
        TopoMappingInteface topoMappingInteface;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(var1, "var1");
        Pdlog.m3273d(TAG, "createCruise " + var1);
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (topoMappingInteface = mappingFunctionInterface.getTopoMappingInteface()) == null) {
            return null;
        }
        return topoMappingInteface.createCruise(var1, name);
    }

    public final List<TopoTrack> getTopPath() {
        TopoMappingInteface topoMappingInteface;
        Pdlog.m3273d(TAG, "getTopPath");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (topoMappingInteface = mappingFunctionInterface.getTopoMappingInteface()) == null) {
            return null;
        }
        return topoMappingInteface.getTracks();
    }

    public final void saveTopMap() {
        TopoMappingInteface topoMappingInteface;
        Pdlog.m3273d(TAG, "saveTopMap");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (topoMappingInteface = mappingFunctionInterface.getTopoMappingInteface()) == null) {
            return;
        }
        topoMappingInteface.saveTopomap();
    }

    public final void deleteSavedMap(String mapName) {
        LocateMappingInterface locatMappingInterface;
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        Pdlog.m3273d(TAG, "deleteSavedMap");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return;
        }
        locatMappingInterface.deleteSavedMap(mapName);
    }

    public final String getStaticMap() {
        LocateMappingInterface locatMappingInterface;
        Pdlog.m3273d(TAG, "getStaticMap");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return null;
        }
        return locatMappingInterface.getTempMapRoot();
    }

    public final void initExtendDrawing() {
        Pdlog.m3273d(TAG, "initExtendDrawing");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new LocateMappingManager$initExtendDrawing$1(null), 3, null);
    }

    public final void saveChargeDockerList(List<DockerResult> list) {
        TopoMappingInteface topoMappingInteface;
        Intrinsics.checkParameterIsNotNull(list, "list");
        Pdlog.m3273d(TAG, "saveChargeDockerList");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (topoMappingInteface = mappingFunctionInterface.getTopoMappingInteface()) == null) {
            return;
        }
        topoMappingInteface.saveChargeDockerList(list);
    }

    public final List<TopoTrack> getDoubleRoadTracks() {
        TopoMappingInteface topoMappingInteface;
        Pdlog.m3273d(TAG, "getDoubleRoadTracks");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (topoMappingInteface = mappingFunctionInterface.getTopoMappingInteface()) == null) {
            return null;
        }
        return topoMappingInteface.getDoubleRoadTracks();
    }

    public final void setTwoWayroad(List<Integer> list) {
        TopoMappingInteface topoMappingInteface;
        Intrinsics.checkParameterIsNotNull(list, "list");
        Pdlog.m3273d(TAG, "setTwoWayroad");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (topoMappingInteface = mappingFunctionInterface.getTopoMappingInteface()) == null) {
            return;
        }
        topoMappingInteface.setTwoWayroad(CollectionsKt.toIntArray(list));
    }

    public final boolean[] checkVirtualWall(List<Vector3d> list) {
        TopoMappingInteface topoMappingInteface;
        Intrinsics.checkParameterIsNotNull(list, "list");
        Pdlog.m3273d(TAG, "checkVirtualWall");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (topoMappingInteface = mappingFunctionInterface.getTopoMappingInteface()) == null) {
            return null;
        }
        return topoMappingInteface.checkVirtualWall(list);
    }

    public final void saveVirtualWall(List<Vector3d> list) {
        TopoMappingInteface topoMappingInteface;
        Intrinsics.checkParameterIsNotNull(list, "list");
        Pdlog.m3273d(TAG, "saveVirtualWall");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (topoMappingInteface = mappingFunctionInterface.getTopoMappingInteface()) == null) {
            return;
        }
        topoMappingInteface.saveVirtualWall(list);
    }

    public final void addMapingModuleListener(String name, MapingModuleListener listener) {
        LocateMappingInterface locatMappingInterface;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Pdlog.m3273d(TAG, "addReinitModuleListener");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return;
        }
        locatMappingInterface.addMapingModuleListener(name, listener);
    }

    public final void removeReinitModuleListener(String name) {
        LocateMappingInterface locatMappingInterface;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(TAG, "removeReinitModuleListener");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return;
        }
        locatMappingInterface.removeMapingModuleListener(name);
    }

    public final void createScheduleConfig(List<Vector3d> parking_pose, List<Vector3d> taking_pose) {
        TopoMappingInteface topoMappingInteface;
        Intrinsics.checkParameterIsNotNull(parking_pose, "parking_pose");
        Intrinsics.checkParameterIsNotNull(taking_pose, "taking_pose");
        Pdlog.m3273d(TAG, "createScheduleConfig parking_pose:" + parking_pose + " taking_pose" + taking_pose);
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (topoMappingInteface = mappingFunctionInterface.getTopoMappingInteface()) == null) {
            return;
        }
        topoMappingInteface.createScheduleConfig(parking_pose, taking_pose);
    }

    public final void addHardWareListener(String name, HardwareExceptListener hardwareExceptListener) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(hardwareExceptListener, "hardwareExceptListener");
        Pdlog.m3273d(TAG, "addHardWareListener " + mapServiceSdk);
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface != null) {
            mappingFunctionInterface.addHardWareListener(name, hardwareExceptListener);
        }
    }

    public final void removeHardWareListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(TAG, "removeHardWareListener " + mapServiceSdk);
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface != null) {
            mappingFunctionInterface.removeHardwareListener(name);
        }
    }

    public final void updateMapVersion(String name, int version) {
        LocateMappingInterface locatMappingInterface;
        Intrinsics.checkParameterIsNotNull(name, "name");
        String str = TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("updateMapVersion ");
        sb.append(mapServiceSdk);
        sb.append(' ');
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        sb.append(mappingFunctionInterface != null ? mappingFunctionInterface.getLocatMappingInterface() : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        MappingFunctionInterface mappingFunctionInterface2 = mapServiceSdk;
        if (mappingFunctionInterface2 == null || (locatMappingInterface = mappingFunctionInterface2.getLocatMappingInterface()) == null) {
            return;
        }
        locatMappingInterface.updateMapVersion(name, version);
    }

    public final CameraType getCameraType() {
        TopoMappingInteface topoMappingInteface;
        Pdlog.m3273d(TAG, "getCameraType");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (topoMappingInteface = mappingFunctionInterface.getTopoMappingInteface()) == null) {
            return null;
        }
        return topoMappingInteface.getCameraType();
    }

    public final Boolean checkMemoryLimit(int size) {
        LocateMappingInterface locatMappingInterface;
        Pdlog.m3273d(TAG, "checkMemoryLimit");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return null;
        }
        return Boolean.valueOf(locatMappingInterface.checkMemoryLimit(size));
    }

    public final LocateCase getMapType() {
        TopoMappingInteface topoMappingInteface;
        Pdlog.m3273d(TAG, "getMapType");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (topoMappingInteface = mappingFunctionInterface.getTopoMappingInteface()) == null) {
            return null;
        }
        return topoMappingInteface.getMapLocateCase();
    }

    public final void clearOldData() {
        Pdlog.m3273d(TAG, "clearOldData");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new LocateMappingManager$clearOldData$1(null), 3, null);
    }

    public final void addMappingSensorListener() {
        Pdlog.m3273d(TAG, "addMappingSensorListener " + mapServiceSdk);
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface != null) {
            mappingFunctionInterface.addMappingSensorListener();
        }
    }

    public final void removeMappingSensorListener() {
        Pdlog.m3273d(TAG, "removeMappingSensorListener " + mapServiceSdk);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new LocateMappingManager$removeMappingSensorListener$1(null), 3, null);
    }

    public final Destination getMapInitPoint(String name) {
        LocateMappingInterface locatMappingInterface;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(TAG, "getMapInitPoint---" + name);
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (locatMappingInterface = mappingFunctionInterface.getLocatMappingInterface()) == null) {
            return null;
        }
        return locatMappingInterface.getMapInitPoint(name);
    }

    public final List<Vector3d> getVirtualWallList() {
        TopoMappingInteface topoMappingInteface;
        Pdlog.m3273d(TAG, "getVirtualWallList");
        MappingFunctionInterface mappingFunctionInterface = mapServiceSdk;
        if (mappingFunctionInterface == null || (topoMappingInteface = mappingFunctionInterface.getTopoMappingInteface()) == null) {
            return null;
        }
        return topoMappingInteface.getVirtualWallList();
    }
}
