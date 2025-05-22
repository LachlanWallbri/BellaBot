package com.pudutech.mirsdk.mircore;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.config.MapFilePathConfig;
import com.pudutech.mirsdk.hardware.CameraInterface;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.IDownRgbdData;
import com.pudutech.mirsdk.hardware.ILeftRgbdData;
import com.pudutech.mirsdk.hardware.ILidarData;
import com.pudutech.mirsdk.hardware.IMarkerCameraData;
import com.pudutech.mirsdk.hardware.IRightRgbdData;
import com.pudutech.mirsdk.hardware.ISensorData;
import com.pudutech.mirsdk.hardware.LidarInterface;
import com.pudutech.mirsdk.hardware.RGBDInterface;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.coreparcel.CruisePath;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import com.pudutech.mirsdk.mircore.coreparcel.MappingCoreInitState;
import com.pudutech.mirsdk.mircore.coreparcel.MappingCoreInitStep;
import com.pudutech.mirsdk.mircore.coreparcel.TopoPath;
import com.pudutech.mirsdk.mircore.mirmapping.Mapping;
import com.pudutech.mirsdk.mircore.module.HardwareLinker;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.TimeoutKt;

/* compiled from: MirMappingCoreImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0015\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0012J\u000e\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0004J\u000e\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018J\u0014\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cJ\u0006\u0010\u001e\u001a\u00020\u000fJ\u0014\u0010\u001f\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cJ\u0006\u0010\"\u001a\u00020\u0018J\u0006\u0010#\u001a\u00020$J\u0006\u0010%\u001a\u00020\u0004J\u0006\u0010&\u001a\u00020$J\u0006\u0010'\u001a\u00020\u0012J\u0006\u0010(\u001a\u00020\u001dJ\"\u0010)\u001a\u00020*2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\u001cJ7\u0010.\u001a\u00020\u000f2\b\u0010/\u001a\u0004\u0018\u0001002\b\u00101\u001a\u0004\u0018\u0001002\b\u00102\u001a\u0004\u0018\u0001002\u0006\u00103\u001a\u000204H\u0086@ø\u0001\u0000¢\u0006\u0002\u00105J \u00106\u001a\u00020\u000f2\b\u00107\u001a\u0004\u0018\u00010*2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000200J\u0019\u00108\u001a\u00020\u000f2\u0006\u00109\u001a\u000204H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010:J\b\u0010;\u001a\u00020\u000fH\u0002J\u0010\u0010<\u001a\u00020\u00122\u0006\u00109\u001a\u000204H\u0002J\u0019\u0010=\u001a\u00020\u000f2\u0006\u00109\u001a\u000204H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010:J\u0006\u0010>\u001a\u00020\u000fJ\u000e\u0010?\u001a\u00020*2\u0006\u0010@\u001a\u00020AJ\u0006\u0010B\u001a\u00020\u000fJ\b\u0010C\u001a\u00020\u000fH\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006D"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/MirMappingCoreImpl;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "hardwareLinker", "Lcom/pudutech/mirsdk/mircore/module/HardwareLinker;", "machineType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineModel;", "mapping", "Lcom/pudutech/mirsdk/mircore/mirmapping/Mapping;", "getMapping", "()Lcom/pudutech/mirsdk/mircore/mirmapping/Mapping;", "addMappingSensorListener", "", "cancelMapping", "checkBeginMappingMarkerVisible", "", "checkFinishMappingMarkerVisible", "checkMapLegal", "map_path", "checkMapLimit", "mg", "", "checkVirtualWall", "", "virtual_wall", "", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "finishMapping", "getCruisePath", "Lcom/pudutech/mirsdk/mircore/coreparcel/CruisePath;", "cruise_poses", "getDetectMarkerId", "getFinalMapDataUI", "Landroid/os/ParcelFileDescriptor;", "getLoclizationMap", "getMapDataUI", "getMappingOptStatus", "getRobotPose", "getTopoPath", "Lcom/pudutech/mirsdk/mircore/coreparcel/TopoPath;", "poses", "ele_list", "Lcom/pudutech/mirsdk/mircore/coreparcel/Destination;", "initExtendMapping", "laser_map", "", "laser_yaml", "marker_data", "listener", "Lcom/pudutech/mirsdk/mircore/InitMappingServiceListener;", "([B[B[BLcom/pudutech/mirsdk/mircore/InitMappingServiceListener;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initGenTopoPath", "old_topo_path", "initializationMappingCore", "initListener", "(Lcom/pudutech/mirsdk/mircore/InitMappingServiceListener;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initializer", "loadConfigAndInitSubmodules", "reInitModules", "removeMappingSensorListener", "resetDualPath", "ill_track_id", "", "startMapping", "unregistListeners", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MirMappingCoreImpl {
    public static final MirMappingCoreImpl INSTANCE;
    private static final String TAG;
    private static final HardwareLinker hardwareLinker;
    private static MachineModel machineType;
    private static final Mapping mapping;

    static {
        MirMappingCoreImpl mirMappingCoreImpl = new MirMappingCoreImpl();
        INSTANCE = mirMappingCoreImpl;
        TAG = mirMappingCoreImpl.getClass().getSimpleName();
        hardwareLinker = new HardwareLinker();
        mapping = new Mapping();
        machineType = MachineModel.Hls;
    }

    private MirMappingCoreImpl() {
    }

    public final Mapping getMapping() {
        return mapping;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00a0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object initializationMappingCore(InitMappingServiceListener initMappingServiceListener, Continuation<? super Unit> continuation) {
        MirMappingCoreImpl$initializationMappingCore$1 mirMappingCoreImpl$initializationMappingCore$1;
        Object coroutine_suspended;
        int i;
        MirMappingCoreImpl mirMappingCoreImpl;
        int i2;
        Object withTimeoutOrNull;
        int i3;
        MirMappingCoreImpl mirMappingCoreImpl2;
        MachineModel machineModel;
        MachineInfo machineInfo;
        ProductMachineType productType;
        if (continuation instanceof MirMappingCoreImpl$initializationMappingCore$1) {
            mirMappingCoreImpl$initializationMappingCore$1 = (MirMappingCoreImpl$initializationMappingCore$1) continuation;
            if ((mirMappingCoreImpl$initializationMappingCore$1.label & Integer.MIN_VALUE) != 0) {
                mirMappingCoreImpl$initializationMappingCore$1.label -= Integer.MIN_VALUE;
                Object obj = mirMappingCoreImpl$initializationMappingCore$1.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = mirMappingCoreImpl$initializationMappingCore$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    initMappingServiceListener.initMappingCoreServiceState(MappingCoreInitStep.LinkHardware, MappingCoreInitState.Running, "");
                    HardwareLinker hardwareLinker2 = hardwareLinker;
                    Context context = MirMappingCoreService.INSTANCE.getContext().get();
                    if (context == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(context, "MirMappingCoreService.context.get()!!");
                    mirMappingCoreImpl$initializationMappingCore$1.L$0 = this;
                    mirMappingCoreImpl$initializationMappingCore$1.L$1 = initMappingServiceListener;
                    mirMappingCoreImpl$initializationMappingCore$1.I$0 = 0;
                    mirMappingCoreImpl$initializationMappingCore$1.label = 1;
                    if (hardwareLinker2.createLinker(context, mirMappingCoreImpl$initializationMappingCore$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    mirMappingCoreImpl = this;
                    i2 = 0;
                } else {
                    if (i != 1) {
                        if (i == 2) {
                            int i4 = mirMappingCoreImpl$initializationMappingCore$1.I$0;
                            InitMappingServiceListener initMappingServiceListener2 = (InitMappingServiceListener) mirMappingCoreImpl$initializationMappingCore$1.L$1;
                            mirMappingCoreImpl2 = (MirMappingCoreImpl) mirMappingCoreImpl$initializationMappingCore$1.L$0;
                            ResultKt.throwOnFailure(obj);
                            i3 = i4;
                            initMappingServiceListener = initMappingServiceListener2;
                            if (!(!Intrinsics.areEqual((Boolean) obj, Boxing.boxBoolean(true)))) {
                                Pdlog.m3273d(TAG, "mapping core link to hardware failed");
                                initMappingServiceListener.initMappingCoreServiceState(MappingCoreInitStep.LinkHardware, MappingCoreInitState.Fail, "link hardware failed");
                            } else {
                                initMappingServiceListener.initMappingCoreServiceState(MappingCoreInitStep.LinkHardware, MappingCoreInitState.Success, "");
                                Pdlog.m3273d(TAG, "mapping core link to hardware success");
                                mirMappingCoreImpl2.unregistListeners();
                                HardwareInterface hardwareService = hardwareLinker.getHardwareService();
                                if (hardwareService == null || (machineInfo = hardwareService.getMachineInfo()) == null || (productType = machineInfo.getProductType()) == null || (machineModel = productType.getModel()) == null) {
                                    machineModel = MachineModel.Hls;
                                }
                                machineType = machineModel;
                                Pdlog.m3273d(TAG, "machineType is " + machineType);
                                initMappingServiceListener.initMappingCoreServiceState(MappingCoreInitStep.CoreConfig, MappingCoreInitState.Running, "");
                                if (mirMappingCoreImpl2.loadConfigAndInitSubmodules(initMappingServiceListener)) {
                                    Pdlog.m3273d(TAG, "finish create mapping core modules");
                                    initMappingServiceListener.initMappingCoreServiceState(MappingCoreInitStep.CoreConfig, MappingCoreInitState.Success, "");
                                    i3 = 1;
                                }
                            }
                            initMappingServiceListener.initMappingCoreServiceState(MappingCoreInitStep.Finish, i3 == 0 ? MappingCoreInitState.Success : MappingCoreInitState.Fail, i3 == 0 ? "Core Init Failed" : "");
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    int i5 = mirMappingCoreImpl$initializationMappingCore$1.I$0;
                    InitMappingServiceListener initMappingServiceListener3 = (InitMappingServiceListener) mirMappingCoreImpl$initializationMappingCore$1.L$1;
                    mirMappingCoreImpl = (MirMappingCoreImpl) mirMappingCoreImpl$initializationMappingCore$1.L$0;
                    ResultKt.throwOnFailure(obj);
                    i2 = i5;
                    initMappingServiceListener = initMappingServiceListener3;
                }
                MirMappingCoreImpl$initializationMappingCore$result$1 mirMappingCoreImpl$initializationMappingCore$result$1 = new MirMappingCoreImpl$initializationMappingCore$result$1(null);
                mirMappingCoreImpl$initializationMappingCore$1.L$0 = mirMappingCoreImpl;
                mirMappingCoreImpl$initializationMappingCore$1.L$1 = initMappingServiceListener;
                mirMappingCoreImpl$initializationMappingCore$1.I$0 = i2;
                mirMappingCoreImpl$initializationMappingCore$1.label = 2;
                withTimeoutOrNull = TimeoutKt.withTimeoutOrNull(2000L, mirMappingCoreImpl$initializationMappingCore$result$1, mirMappingCoreImpl$initializationMappingCore$1);
                if (withTimeoutOrNull != coroutine_suspended) {
                    return coroutine_suspended;
                }
                i3 = i2;
                obj = withTimeoutOrNull;
                mirMappingCoreImpl2 = mirMappingCoreImpl;
                if (!(!Intrinsics.areEqual((Boolean) obj, Boxing.boxBoolean(true)))) {
                }
                initMappingServiceListener.initMappingCoreServiceState(MappingCoreInitStep.Finish, i3 == 0 ? MappingCoreInitState.Success : MappingCoreInitState.Fail, i3 == 0 ? "Core Init Failed" : "");
                return Unit.INSTANCE;
            }
        }
        mirMappingCoreImpl$initializationMappingCore$1 = new MirMappingCoreImpl$initializationMappingCore$1(this, continuation);
        Object obj2 = mirMappingCoreImpl$initializationMappingCore$1.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = mirMappingCoreImpl$initializationMappingCore$1.label;
        if (i != 0) {
        }
        MirMappingCoreImpl$initializationMappingCore$result$1 mirMappingCoreImpl$initializationMappingCore$result$12 = new MirMappingCoreImpl$initializationMappingCore$result$1(null);
        mirMappingCoreImpl$initializationMappingCore$1.L$0 = mirMappingCoreImpl;
        mirMappingCoreImpl$initializationMappingCore$1.L$1 = initMappingServiceListener;
        mirMappingCoreImpl$initializationMappingCore$1.I$0 = i2;
        mirMappingCoreImpl$initializationMappingCore$1.label = 2;
        withTimeoutOrNull = TimeoutKt.withTimeoutOrNull(2000L, mirMappingCoreImpl$initializationMappingCore$result$12, mirMappingCoreImpl$initializationMappingCore$1);
        if (withTimeoutOrNull != coroutine_suspended) {
        }
    }

    public final Object reInitModules(InitMappingServiceListener initMappingServiceListener, Continuation<? super Unit> continuation) {
        boolean z = true;
        Pdlog.m3273d(TAG, "reInitModules ");
        initMappingServiceListener.initMappingCoreServiceState(MappingCoreInitStep.CoreConfig, MappingCoreInitState.Running, "");
        unregistListeners();
        if (loadConfigAndInitSubmodules(initMappingServiceListener)) {
            initMappingServiceListener.initMappingCoreServiceState(MappingCoreInitStep.CoreConfig, MappingCoreInitState.Success, "");
            Pdlog.m3273d(TAG, "reInitModules success ");
        } else {
            z = false;
        }
        initMappingServiceListener.initMappingCoreServiceState(MappingCoreInitStep.Finish, z ? MappingCoreInitState.Success : MappingCoreInitState.Fail, z ? "" : "Core Init Failed");
        return Unit.INSTANCE;
    }

    public final Object initExtendMapping(byte[] bArr, byte[] bArr2, byte[] bArr3, InitMappingServiceListener initMappingServiceListener, Continuation<? super Unit> continuation) {
        Pdlog.m3273d(TAG, "initExtendMapping Begin");
        initMappingServiceListener.initMappingCoreServiceState(MappingCoreInitStep.InitExtendMapping, MappingCoreInitState.Running, "");
        mapping.initExtendMapping(bArr, bArr2, bArr3);
        initMappingServiceListener.initMappingCoreServiceState(MappingCoreInitStep.InitExtendMapping, MappingCoreInitState.Success, "");
        initMappingServiceListener.initMappingCoreServiceState(MappingCoreInitStep.Finish, MappingCoreInitState.Success, "");
        Pdlog.m3273d(TAG, "initExtendMapping Success");
        return Unit.INSTANCE;
    }

    public final void addMappingSensorListener() {
        Pdlog.m3273d(TAG, "addMappingSensorListener ");
        initializer();
        Pdlog.m3273d(TAG, "addMappingSensorListener finish ");
    }

    public final void removeMappingSensorListener() {
        Pdlog.m3273d(TAG, "removeMappingSensorListener ");
        unregistListeners();
        Pdlog.m3273d(TAG, "removeMappingSensorListener finish ");
    }

    public final boolean checkMapLegal(String map_path) {
        Intrinsics.checkParameterIsNotNull(map_path, "map_path");
        Pdlog.m3273d(TAG, "checkMapLegal ");
        return true;
    }

    public final boolean checkBeginMappingMarkerVisible() {
        Pdlog.m3273d(TAG, "checkBeginMappingMarkerVisible ");
        return mapping.checkMarkerVisible(false);
    }

    public final void startMapping() {
        Pdlog.m3273d(TAG, "startMapping ");
        mapping.startMapping();
    }

    public final int getDetectMarkerId() {
        Pdlog.m3273d(TAG, "getDetectMarkerId ");
        return mapping.getDetectMarkerId();
    }

    public final Vector3d getRobotPose() {
        Pdlog.m3273d(TAG, "getRobotPose ");
        return mapping.getRobotPose();
    }

    public final boolean checkMapLimit(int mg) {
        Pdlog.m3273d(TAG, "checkMapLimit ");
        return mapping.checkMapLimit(mg);
    }

    public final ParcelFileDescriptor getMapDataUI() {
        Pdlog.m3273d(TAG, "getMapDataUI mapdata");
        ParcelFileDescriptor parcelFileDescriptor = ParcelFileDescriptor.dup(mapping.getMapData(false));
        Intrinsics.checkExpressionValueIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
        return parcelFileDescriptor;
    }

    public final boolean checkFinishMappingMarkerVisible() {
        Pdlog.m3273d(TAG, "checkFinishMappingMarkerVisible ");
        return mapping.checkMarkerVisible(true);
    }

    public final void cancelMapping() {
        Pdlog.m3273d(TAG, "cancelMapping ");
        unregistListeners();
        mapping.cancelMapping();
    }

    public final void finishMapping() {
        Pdlog.m3273d(TAG, "finishMapping ");
        unregistListeners();
        mapping.finishMapping();
    }

    public final boolean getMappingOptStatus() {
        Pdlog.m3273d(TAG, "getMappingOptStatus ");
        return mapping.getMappingOptStatus();
    }

    public final ParcelFileDescriptor getFinalMapDataUI() {
        Pdlog.m3273d(TAG, "getFinalMapDataUI mapdata");
        ParcelFileDescriptor parcelFileDescriptor = ParcelFileDescriptor.dup(mapping.getMapData(true));
        Intrinsics.checkExpressionValueIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
        return parcelFileDescriptor;
    }

    public final String getLoclizationMap() {
        String loclizationMap = mapping.getLoclizationMap();
        Pdlog.m3273d(TAG, "getLoclizationMap, path is ", loclizationMap);
        return loclizationMap;
    }

    public final void initGenTopoPath(TopoPath old_topo_path, byte[] laser_map, byte[] laser_yaml) {
        Intrinsics.checkParameterIsNotNull(laser_map, "laser_map");
        Intrinsics.checkParameterIsNotNull(laser_yaml, "laser_yaml");
        Pdlog.m3273d(TAG, "initGenTopoPath ");
        mapping.initGenTopoPath(old_topo_path, laser_map, laser_yaml);
    }

    public final TopoPath getTopoPath(List<Vector3d> poses, List<Destination> ele_list) {
        Intrinsics.checkParameterIsNotNull(poses, "poses");
        Intrinsics.checkParameterIsNotNull(ele_list, "ele_list");
        Pdlog.m3273d(TAG, "getTopoPath ");
        return mapping.getTopoPath(poses, ele_list);
    }

    public final TopoPath resetDualPath(int[] ill_track_id) {
        Intrinsics.checkParameterIsNotNull(ill_track_id, "ill_track_id");
        Pdlog.m3273d(TAG, "resetDualPath ");
        return mapping.resetDualPath(ArraysKt.toMutableList(ill_track_id));
    }

    public final boolean[] checkVirtualWall(List<Vector3d> virtual_wall) {
        Intrinsics.checkParameterIsNotNull(virtual_wall, "virtual_wall");
        Pdlog.m3273d(TAG, "getTopoPath ");
        return CollectionsKt.toBooleanArray(mapping.checkVirtualWall(virtual_wall));
    }

    public final CruisePath getCruisePath(List<Vector3d> cruise_poses) {
        Intrinsics.checkParameterIsNotNull(cruise_poses, "cruise_poses");
        Pdlog.m3273d(TAG, "getCruisePath ");
        return mapping.getCruisePath(cruise_poses);
    }

    private final void initializer() {
        mapping.switchAutoExposure(true);
        mapping.registerLaserListener(new Function2<String, ILidarData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirMappingCoreImpl$initializer$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, ILidarData iLidarData) {
                invoke2(str, iLidarData);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name, ILidarData iLidarData) {
                HardwareLinker hardwareLinker2;
                LidarInterface lidarInterface;
                Intrinsics.checkParameterIsNotNull(name, "name");
                Intrinsics.checkParameterIsNotNull(iLidarData, "iLidarData");
                MirMappingCoreImpl mirMappingCoreImpl = MirMappingCoreImpl.INSTANCE;
                hardwareLinker2 = MirMappingCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService == null || (lidarInterface = hardwareService.getLidarInterface()) == null) {
                    return;
                }
                lidarInterface.addDataListener(name, iLidarData);
            }
        });
        mapping.registSensorListener(new Function2<String, ISensorData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirMappingCoreImpl$initializer$2
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, ISensorData iSensorData) {
                invoke2(str, iSensorData);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name, ISensorData sensor) {
                HardwareLinker hardwareLinker2;
                Intrinsics.checkParameterIsNotNull(name, "name");
                Intrinsics.checkParameterIsNotNull(sensor, "sensor");
                MirMappingCoreImpl mirMappingCoreImpl = MirMappingCoreImpl.INSTANCE;
                hardwareLinker2 = MirMappingCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService != null) {
                    hardwareService.addSensorListener(name, sensor);
                }
            }
        });
        mapping.registerMarkerFrameListener(new Function2<String, IMarkerCameraData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirMappingCoreImpl$initializer$3
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, IMarkerCameraData iMarkerCameraData) {
                invoke2(str, iMarkerCameraData);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name, IMarkerCameraData marker) {
                HardwareLinker hardwareLinker2;
                CameraInterface camera;
                Intrinsics.checkParameterIsNotNull(name, "name");
                Intrinsics.checkParameterIsNotNull(marker, "marker");
                MirMappingCoreImpl mirMappingCoreImpl = MirMappingCoreImpl.INSTANCE;
                hardwareLinker2 = MirMappingCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService == null || (camera = hardwareService.getCamera()) == null) {
                    return;
                }
                camera.addMarkerCameraListener(name, marker);
            }
        });
        mapping.registerMonocularCameraListener(new Function2<String, IMarkerCameraData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirMappingCoreImpl$initializer$4
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, IMarkerCameraData iMarkerCameraData) {
                invoke2(str, iMarkerCameraData);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name, IMarkerCameraData iFaceCameraData) {
                HardwareLinker hardwareLinker2;
                CameraInterface camera;
                Intrinsics.checkParameterIsNotNull(name, "name");
                Intrinsics.checkParameterIsNotNull(iFaceCameraData, "iFaceCameraData");
                MirMappingCoreImpl mirMappingCoreImpl = MirMappingCoreImpl.INSTANCE;
                hardwareLinker2 = MirMappingCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService == null || (camera = hardwareService.getCamera()) == null) {
                    return;
                }
                camera.addMonocularCameraListener(name, iFaceCameraData);
            }
        });
        mapping.registerDownRGBDListener(new Function2<String, IDownRgbdData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirMappingCoreImpl$initializer$5
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, IDownRgbdData iDownRgbdData) {
                invoke2(str, iDownRgbdData);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name, IDownRgbdData iDownRgbdData) {
                HardwareLinker hardwareLinker2;
                RGBDInterface rGBDInterface;
                Intrinsics.checkParameterIsNotNull(name, "name");
                Intrinsics.checkParameterIsNotNull(iDownRgbdData, "iDownRgbdData");
                MirMappingCoreImpl mirMappingCoreImpl = MirMappingCoreImpl.INSTANCE;
                hardwareLinker2 = MirMappingCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService == null || (rGBDInterface = hardwareService.getRGBDInterface()) == null) {
                    return;
                }
                rGBDInterface.addDownRgbdListener(name, iDownRgbdData);
            }
        });
        mapping.registerLeftRGBDListener(new Function2<String, ILeftRgbdData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirMappingCoreImpl$initializer$6
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, ILeftRgbdData iLeftRgbdData) {
                invoke2(str, iLeftRgbdData);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name, ILeftRgbdData iLeftRgbdData) {
                HardwareLinker hardwareLinker2;
                RGBDInterface rGBDInterface;
                Intrinsics.checkParameterIsNotNull(name, "name");
                Intrinsics.checkParameterIsNotNull(iLeftRgbdData, "iLeftRgbdData");
                MirMappingCoreImpl mirMappingCoreImpl = MirMappingCoreImpl.INSTANCE;
                hardwareLinker2 = MirMappingCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService == null || (rGBDInterface = hardwareService.getRGBDInterface()) == null) {
                    return;
                }
                rGBDInterface.addLeftRgbdListener(name, iLeftRgbdData);
            }
        });
        mapping.registerRightRGBDListenner(new Function2<String, IRightRgbdData, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirMappingCoreImpl$initializer$7
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, IRightRgbdData iRightRgbdData) {
                invoke2(str, iRightRgbdData);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name, IRightRgbdData iRightRgbdData) {
                HardwareLinker hardwareLinker2;
                RGBDInterface rGBDInterface;
                Intrinsics.checkParameterIsNotNull(name, "name");
                Intrinsics.checkParameterIsNotNull(iRightRgbdData, "iRightRgbdData");
                MirMappingCoreImpl mirMappingCoreImpl = MirMappingCoreImpl.INSTANCE;
                hardwareLinker2 = MirMappingCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService == null || (rGBDInterface = hardwareService.getRGBDInterface()) == null) {
                    return;
                }
                rGBDInterface.addRightRgbdListener(name, iRightRgbdData);
            }
        });
        mapping.addSetExposureInterface(new Function1<Integer, Boolean>() { // from class: com.pudutech.mirsdk.mircore.MirMappingCoreImpl$initializer$8
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(Integer num) {
                return Boolean.valueOf(invoke(num.intValue()));
            }

            public final boolean invoke(int i) {
                HardwareLinker hardwareLinker2;
                MirMappingCoreImpl mirMappingCoreImpl = MirMappingCoreImpl.INSTANCE;
                hardwareLinker2 = MirMappingCoreImpl.hardwareLinker;
                return hardwareLinker2.setExposure(i);
            }
        });
        mapping.addGetExposureInterface(new Function0<Integer>() { // from class: com.pudutech.mirsdk.mircore.MirMappingCoreImpl$initializer$9
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Integer invoke() {
                return Integer.valueOf(invoke2());
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final int invoke2() {
                HardwareLinker hardwareLinker2;
                MirMappingCoreImpl mirMappingCoreImpl = MirMappingCoreImpl.INSTANCE;
                hardwareLinker2 = MirMappingCoreImpl.hardwareLinker;
                return hardwareLinker2.getExposure();
            }
        });
    }

    private final void unregistListeners() {
        mapping.unregisterLaserListener(new Function1<String, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirMappingCoreImpl$unregistListeners$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name) {
                HardwareLinker hardwareLinker2;
                LidarInterface lidarInterface;
                Intrinsics.checkParameterIsNotNull(name, "name");
                MirMappingCoreImpl mirMappingCoreImpl = MirMappingCoreImpl.INSTANCE;
                hardwareLinker2 = MirMappingCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService == null || (lidarInterface = hardwareService.getLidarInterface()) == null) {
                    return;
                }
                lidarInterface.removeDataListener(name);
            }
        });
        mapping.unregistSensorListener(new Function1<String, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirMappingCoreImpl$unregistListeners$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name) {
                HardwareLinker hardwareLinker2;
                Intrinsics.checkParameterIsNotNull(name, "name");
                MirMappingCoreImpl mirMappingCoreImpl = MirMappingCoreImpl.INSTANCE;
                hardwareLinker2 = MirMappingCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService != null) {
                    hardwareService.removeSensorListener(name);
                }
            }
        });
        mapping.unregistMarkerFrameListener(new Function1<String, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirMappingCoreImpl$unregistListeners$3
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name) {
                HardwareLinker hardwareLinker2;
                CameraInterface camera;
                Intrinsics.checkParameterIsNotNull(name, "name");
                MirMappingCoreImpl mirMappingCoreImpl = MirMappingCoreImpl.INSTANCE;
                hardwareLinker2 = MirMappingCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService == null || (camera = hardwareService.getCamera()) == null) {
                    return;
                }
                camera.removeMarkerCameraListener(name);
            }
        });
        mapping.unregistMonocularCameraListener(new Function1<String, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirMappingCoreImpl$unregistListeners$4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name) {
                HardwareLinker hardwareLinker2;
                CameraInterface camera;
                Intrinsics.checkParameterIsNotNull(name, "name");
                MirMappingCoreImpl mirMappingCoreImpl = MirMappingCoreImpl.INSTANCE;
                hardwareLinker2 = MirMappingCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService == null || (camera = hardwareService.getCamera()) == null) {
                    return;
                }
                camera.removeMonocularCameraListener(name);
            }
        });
        mapping.unregisterLeftRGBDListener(new Function1<String, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirMappingCoreImpl$unregistListeners$5
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name) {
                HardwareLinker hardwareLinker2;
                RGBDInterface rGBDInterface;
                Intrinsics.checkParameterIsNotNull(name, "name");
                MirMappingCoreImpl mirMappingCoreImpl = MirMappingCoreImpl.INSTANCE;
                hardwareLinker2 = MirMappingCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService == null || (rGBDInterface = hardwareService.getRGBDInterface()) == null) {
                    return;
                }
                rGBDInterface.removeLeftRgbdListener(name);
            }
        });
        mapping.unregisterRightRGBDListener(new Function1<String, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirMappingCoreImpl$unregistListeners$6
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name) {
                HardwareLinker hardwareLinker2;
                RGBDInterface rGBDInterface;
                Intrinsics.checkParameterIsNotNull(name, "name");
                MirMappingCoreImpl mirMappingCoreImpl = MirMappingCoreImpl.INSTANCE;
                hardwareLinker2 = MirMappingCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService == null || (rGBDInterface = hardwareService.getRGBDInterface()) == null) {
                    return;
                }
                rGBDInterface.removeRightRgbdListener(name);
            }
        });
        mapping.unregisterDownRGBDListener(new Function1<String, Unit>() { // from class: com.pudutech.mirsdk.mircore.MirMappingCoreImpl$unregistListeners$7
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String name) {
                HardwareLinker hardwareLinker2;
                RGBDInterface rGBDInterface;
                Intrinsics.checkParameterIsNotNull(name, "name");
                MirMappingCoreImpl mirMappingCoreImpl = MirMappingCoreImpl.INSTANCE;
                hardwareLinker2 = MirMappingCoreImpl.hardwareLinker;
                HardwareInterface hardwareService = hardwareLinker2.getHardwareService();
                if (hardwareService == null || (rGBDInterface = hardwareService.getRGBDInterface()) == null) {
                    return;
                }
                rGBDInterface.removeDownRgbdListener(name);
            }
        });
    }

    private final boolean loadConfigAndInitSubmodules(InitMappingServiceListener initListener) {
        try {
            if (new File(MapFilePathConfig.CAMERA_CONFIG_PATH).exists() && new File(MapFilePathConfig.MAPPING_JSON_PATH).exists()) {
                FileInputStream fileInputStream = new FileInputStream(MapFilePathConfig.CAMERA_CONFIG_PATH);
                FileInputStream fileInputStream2 = new FileInputStream(MapFilePathConfig.MAPPING_JSON_PATH);
                byte[] bArr = new byte[1024];
                byte[] bArr2 = new byte[1024];
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    Pdlog.m3273d(TAG, "camera config length : ", Integer.valueOf(read));
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                while (true) {
                    int read2 = fileInputStream2.read(bArr2);
                    if (read2 <= 0) {
                        break;
                    }
                    Pdlog.m3273d(TAG, "mapping config length : ", Integer.valueOf(read2));
                    byteArrayOutputStream2.write(bArr2, 0, read2);
                }
                byte[] tmp_camera = byteArrayOutputStream.toByteArray();
                byte[] tmp_mapping = byteArrayOutputStream2.toByteArray();
                byteArrayOutputStream.close();
                byteArrayOutputStream2.close();
                Mapping mapping2 = mapping;
                MachineModel machineModel = machineType;
                Intrinsics.checkExpressionValueIsNotNull(tmp_camera, "tmp_camera");
                Intrinsics.checkExpressionValueIsNotNull(tmp_mapping, "tmp_mapping");
                if (mapping2.initModule(machineModel, tmp_camera, tmp_mapping)) {
                    return true;
                }
                Pdlog.m3274e(TAG, "mapping module init failed");
                initListener.initMappingCoreServiceState(MappingCoreInitStep.CoreConfig, MappingCoreInitState.Fail, "mapping module init failed");
                return false;
            }
            Pdlog.m3273d(TAG, "config not exit : ");
            initListener.initMappingCoreServiceState(MappingCoreInitStep.CoreConfig, MappingCoreInitState.Fail, "config not exit");
            return false;
        } catch (Exception unused) {
            Pdlog.m3274e(TAG, "load camera and mapping config file error");
            initListener.initMappingCoreServiceState(MappingCoreInitStep.CoreConfig, MappingCoreInitState.Fail, "load camera and mapping config file error");
            return false;
        }
    }
}
