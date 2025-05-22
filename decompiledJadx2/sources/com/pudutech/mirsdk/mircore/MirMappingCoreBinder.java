package com.pudutech.mirsdk.mircore;

import android.os.ParcelFileDescriptor;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.MirMappingCoreInterface;
import com.pudutech.mirsdk.mircore.coreparcel.CruisePath;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import com.pudutech.mirsdk.mircore.coreparcel.TopoPath;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;

/* compiled from: MirMappingCoreBinder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0015\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016J\b\u0010\u000f\u001a\u00020\u0007H\u0016J\b\u0010\u0010\u001a\u00020\u0007H\u0016J\u0010\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0004H\u0016J\u0010\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0016\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0016J\b\u0010\u001b\u001a\u00020\rH\u0016J\u0016\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0016J\b\u0010\u001f\u001a\u00020\u0015H\u0016J\b\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020\u0004H\u0016J\b\u0010#\u001a\u00020!H\u0016J\b\u0010$\u001a\u00020\u0007H\u0016J\b\u0010%\u001a\u00020\u001aH\u0016J$\u0010&\u001a\u00020'2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\f\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u0019H\u0016J.\u0010+\u001a\u00020\r2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u0010.\u001a\u0004\u0018\u00010-2\b\u0010/\u001a\u0004\u0018\u00010-2\u0006\u00100\u001a\u000201H\u0016J\"\u00102\u001a\u00020\r2\b\u00103\u001a\u0004\u0018\u00010'2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020-H\u0016J\u0010\u00104\u001a\u00020\r2\u0006\u00100\u001a\u000201H\u0016J\u0010\u00105\u001a\u00020\r2\u0006\u00100\u001a\u000201H\u0016J\b\u00106\u001a\u00020\rH\u0016J\u0010\u00107\u001a\u00020'2\u0006\u00108\u001a\u000209H\u0016J\b\u0010:\u001a\u00020\rH\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006;"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/MirMappingCoreBinder;", "Lcom/pudutech/mirsdk/mircore/MirMappingCoreInterface$Stub;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "hasInit", "", "initExJob", "Lkotlinx/coroutines/Job;", "initJob", "reInitJob", "addMappingSensorListener", "", "cancelMapping", "checkBeginMappingMarkerVisible", "checkFinishMappingMarkerVisible", "checkMapLegal", "map_path", "checkMapLimit", "mg", "", "checkVirtualWall", "", "virtual_wall", "", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "finishMapping", "getCruisePath", "Lcom/pudutech/mirsdk/mircore/coreparcel/CruisePath;", "cruise_poses", "getDetectMarkerId", "getFinalMapDataUI", "Landroid/os/ParcelFileDescriptor;", "getLoclizationMap", "getMapDataUI", "getMappingOptStatus", "getRobotPose", "getTopoPath", "Lcom/pudutech/mirsdk/mircore/coreparcel/TopoPath;", "poses", "ele_list", "Lcom/pudutech/mirsdk/mircore/coreparcel/Destination;", "initExtendMapping", "laser_map", "", "laser_yaml", "marker_data", "listener", "Lcom/pudutech/mirsdk/mircore/InitMappingServiceListener;", "initGenTopoPath", "old_topo_path", "initModules", "reInitModules", "removeMappingSensorListener", "resetDualPath", "ill_track_id", "", "startMapping", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MirMappingCoreBinder extends MirMappingCoreInterface.Stub {
    public static final MirMappingCoreBinder INSTANCE;
    private static final String TAG;
    private static boolean hasInit;
    private static Job initExJob;
    private static Job initJob;
    private static Job reInitJob;

    static {
        MirMappingCoreBinder mirMappingCoreBinder = new MirMappingCoreBinder();
        INSTANCE = mirMappingCoreBinder;
        TAG = mirMappingCoreBinder.getClass().getSimpleName();
    }

    private MirMappingCoreBinder() {
    }

    @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
    public synchronized void initModules(InitMappingServiceListener listener) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (hasInit) {
            Pdlog.m3273d(TAG, "initModules hasInit");
        } else {
            if (initJob != null) {
                Pdlog.m3273d(TAG, "initModules initJob != null");
                return;
            }
            hasInit = true;
            launch$default = BuildersKt__Builders_commonKt.launch$default(MirCoreScopeKt.getMirCoreScope(), null, null, new MirMappingCoreBinder$initModules$1(listener, null), 3, null);
            initJob = launch$default;
        }
    }

    @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
    public synchronized void reInitModules(InitMappingServiceListener listener) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (reInitJob == null) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(MirCoreScopeKt.getMirCoreScope(), null, null, new MirMappingCoreBinder$reInitModules$1(listener, null), 3, null);
            reInitJob = launch$default;
        } else {
            Pdlog.m3273d(TAG, "reInitModules reInitJob != null");
        }
    }

    @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
    public synchronized void initExtendMapping(byte[] laser_map, byte[] laser_yaml, byte[] marker_data, InitMappingServiceListener listener) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (initExJob == null) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(MirCoreScopeKt.getMirCoreScope(), null, null, new MirMappingCoreBinder$initExtendMapping$1(laser_map, laser_yaml, marker_data, listener, null), 3, null);
            initExJob = launch$default;
        } else {
            Pdlog.m3273d(TAG, "initExtendMapping initExJob != null");
        }
    }

    @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
    public void addMappingSensorListener() {
        MirMappingCoreImpl.INSTANCE.addMappingSensorListener();
    }

    @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
    public void removeMappingSensorListener() {
        MirMappingCoreImpl.INSTANCE.removeMappingSensorListener();
    }

    @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
    public boolean checkMapLegal(String map_path) {
        Intrinsics.checkParameterIsNotNull(map_path, "map_path");
        return MirMappingCoreImpl.INSTANCE.checkMapLegal(map_path);
    }

    @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
    public boolean checkBeginMappingMarkerVisible() {
        return MirMappingCoreImpl.INSTANCE.checkBeginMappingMarkerVisible();
    }

    @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
    public void startMapping() {
        MirMappingCoreImpl.INSTANCE.startMapping();
    }

    @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
    public int getDetectMarkerId() {
        return MirMappingCoreImpl.INSTANCE.getDetectMarkerId();
    }

    @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
    public Vector3d getRobotPose() {
        return MirMappingCoreImpl.INSTANCE.getRobotPose();
    }

    @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
    public boolean checkMapLimit(int mg) {
        return MirMappingCoreImpl.INSTANCE.checkMapLimit(mg);
    }

    @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
    public ParcelFileDescriptor getMapDataUI() {
        return MirMappingCoreImpl.INSTANCE.getMapDataUI();
    }

    @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
    public boolean checkFinishMappingMarkerVisible() {
        return MirMappingCoreImpl.INSTANCE.checkFinishMappingMarkerVisible();
    }

    @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
    public void cancelMapping() {
        MirMappingCoreImpl.INSTANCE.cancelMapping();
    }

    @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
    public void finishMapping() {
        MirMappingCoreImpl.INSTANCE.finishMapping();
    }

    @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
    public boolean getMappingOptStatus() {
        return MirMappingCoreImpl.INSTANCE.getMappingOptStatus();
    }

    @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
    public ParcelFileDescriptor getFinalMapDataUI() {
        return MirMappingCoreImpl.INSTANCE.getFinalMapDataUI();
    }

    @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
    public String getLoclizationMap() {
        return MirMappingCoreImpl.INSTANCE.getLoclizationMap();
    }

    @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
    public void initGenTopoPath(TopoPath old_topo_path, byte[] laser_map, byte[] laser_yaml) {
        Intrinsics.checkParameterIsNotNull(laser_map, "laser_map");
        Intrinsics.checkParameterIsNotNull(laser_yaml, "laser_yaml");
        MirMappingCoreImpl.INSTANCE.initGenTopoPath(old_topo_path, laser_map, laser_yaml);
    }

    @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
    public TopoPath getTopoPath(List<Vector3d> poses, List<Destination> ele_list) {
        Intrinsics.checkParameterIsNotNull(poses, "poses");
        Intrinsics.checkParameterIsNotNull(ele_list, "ele_list");
        return MirMappingCoreImpl.INSTANCE.getTopoPath(poses, ele_list);
    }

    @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
    public TopoPath resetDualPath(int[] ill_track_id) {
        Intrinsics.checkParameterIsNotNull(ill_track_id, "ill_track_id");
        return MirMappingCoreImpl.INSTANCE.resetDualPath(ill_track_id);
    }

    @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
    public boolean[] checkVirtualWall(List<Vector3d> virtual_wall) {
        Intrinsics.checkParameterIsNotNull(virtual_wall, "virtual_wall");
        return MirMappingCoreImpl.INSTANCE.checkVirtualWall(virtual_wall);
    }

    @Override // com.pudutech.mirsdk.mircore.MirMappingCoreInterface
    public CruisePath getCruisePath(List<Vector3d> cruise_poses) {
        Intrinsics.checkParameterIsNotNull(cruise_poses, "cruise_poses");
        return MirMappingCoreImpl.INSTANCE.getCruisePath(cruise_poses);
    }
}
