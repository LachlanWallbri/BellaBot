package com.pudutech.mirsdk.mircore.mirperception;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.coreparcel.MapData;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: Costmap.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0018\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005J\u001c\u0010\u0017\u001a\u00020\u00072\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0086 ¢\u0006\u0002\u0010\u001bJ\t\u0010\u001c\u001a\u00020\u001dH\u0086 J\u0011\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020 H\u0086 J\u0011\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020\u0015H\u0086 J\t\u0010#\u001a\u00020\u0006H\u0086 J\t\u0010$\u001a\u00020\u0015H\u0086 J\t\u0010%\u001a\u00020\u0007H\u0086 J\u0019\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)H\u0086 J\t\u0010+\u001a\u00020\u0006H\u0086 J\t\u0010,\u001a\u00020'H\u0086 J\b\u0010-\u001a\u00020\u0007H\u0002J\u0011\u0010.\u001a\u00020 2\u0006\u0010/\u001a\u000200H\u0086 J\t\u00101\u001a\u00020\u0007H\u0082 J\t\u00102\u001a\u00020\u0007H\u0082 J\t\u00103\u001a\u00020 H\u0086 J\u001c\u00104\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u0012J\u000e\u00105\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0015J$\u00106\u001a\u00020\u00072\u0006\u00107\u001a\u00020 2\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0086 ¢\u0006\u0002\u00109J\u0011\u0010:\u001a\u00020\u00072\u0006\u0010;\u001a\u00020 H\u0086 J\u0011\u0010<\u001a\u00020\u00072\u0006\u0010;\u001a\u00020 H\u0086 J\u0011\u0010=\u001a\u00020 2\u0006\u0010>\u001a\u00020 H\u0086 J\u0011\u0010?\u001a\u00020\u00072\u0006\u0010;\u001a\u00020 H\u0086 J\t\u0010@\u001a\u00020\u0007H\u0082 J\t\u0010A\u001a\u00020\u0007H\u0082 J\u000e\u0010B\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010C\u001a\u00020\u00072\u0006\u0010D\u001a\u00020\u0006H\u0002J\u0016\u0010E\u001a\u00020\u00072\u0006\u0010F\u001a\u00020\u000e2\u0006\u0010G\u001a\u00020\u000eR,\u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR2\u0010\f\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00070\r0\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\t\"\u0004\b\u0010\u0010\u000bR\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00120\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006H"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/mirperception/Costmap;", "", "()V", "costmapErrorListener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lkotlin/Function1;", "", "", "getCostmapErrorListener", "()Lcom/pudutech/base/architecture/ThreadSafeListener;", "setCostmapErrorListener", "(Lcom/pudutech/base/architecture/ThreadSafeListener;)V", "costmapListener", "Lkotlin/Function2;", "Lcom/pudutech/mirsdk/mircore/coreparcel/MapData;", "getCostmapListener", "setCostmapListener", "updatedListener", "Lkotlin/Function0;", "addCostmapErrorListener", "name", "", "callback", "addVirtualWall", "walls", "", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "([Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "checkNoise", "", "enableCostmap", "enable", "", "enableRGBD", "rgbd_config_file", "getCliffInfo", "getCliffIrImg", "getCostmap", "getCostmapAddress", "", "posex", "", "posey", "getDropDetectType", "getDropInfoAddress", "informMapUpdated", "initialize", "machineModel", "Lcom/pudutech/mirsdk/hardware/serialize/MachineModel;", "installCostmapErrorListener", "installUpdatedListener", "isModuleInited", "registMapUpdatedListener", "removeCostmapErrorListener", "setNoiseDetectSwitch", "need_noise_detect", "rect_vector", "(Z[Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "switchDataRecord", "b", "switchDropDet", "switchFeasibalRegionSeg", "use_cape", "switchUseDropInfo", "uninstallCostmapErrorListener", "uninstallUpdatedListener", "unregistMapUpdatedListener", "updateCostmapErrorInfo", "flag", "updateLocalMap", "localMap", "highPrecMap", "MirPerception_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class Costmap {
    private static ThreadSafeListener<Function2<MapData, MapData, Unit>> costmapListener;
    public static final Costmap INSTANCE = new Costmap();
    private static ThreadSafeListener<Function0<Unit>> updatedListener = new ThreadSafeListener<>();
    private static ThreadSafeListener<Function1<Integer, Unit>> costmapErrorListener = new ThreadSafeListener<>();

    private final native void installCostmapErrorListener();

    private final native void installUpdatedListener();

    private final native void uninstallCostmapErrorListener();

    private final native void uninstallUpdatedListener();

    public final native void addVirtualWall(Vector3d[] walls);

    public final native int[] checkNoise();

    public final native void enableCostmap(boolean enable);

    public final native boolean enableRGBD(String rgbd_config_file);

    public final native int getCliffInfo();

    public final native String getCliffIrImg();

    public final native void getCostmap();

    public final native long[] getCostmapAddress(double posex, double posey);

    public final native int getDropDetectType();

    public final native long[] getDropInfoAddress();

    public final native boolean initialize(MachineModel machineModel);

    public final native boolean isModuleInited();

    public final native void setNoiseDetectSwitch(boolean need_noise_detect, Vector3d[] rect_vector);

    public final native void switchDataRecord(boolean b);

    public final native void switchDropDet(boolean b);

    public final native boolean switchFeasibalRegionSeg(boolean use_cape);

    public final native void switchUseDropInfo(boolean b);

    static {
        Pdlog.m3273d(PerceptionKt.getTAG(), "load library: costmap");
        System.loadLibrary("costmap");
        costmapListener = new ThreadSafeListener<>();
    }

    private Costmap() {
    }

    public final ThreadSafeListener<Function1<Integer, Unit>> getCostmapErrorListener() {
        return costmapErrorListener;
    }

    public final void setCostmapErrorListener(ThreadSafeListener<Function1<Integer, Unit>> threadSafeListener) {
        Intrinsics.checkParameterIsNotNull(threadSafeListener, "<set-?>");
        costmapErrorListener = threadSafeListener;
    }

    public final ThreadSafeListener<Function2<MapData, MapData, Unit>> getCostmapListener() {
        return costmapListener;
    }

    public final void setCostmapListener(ThreadSafeListener<Function2<MapData, MapData, Unit>> threadSafeListener) {
        Intrinsics.checkParameterIsNotNull(threadSafeListener, "<set-?>");
        costmapListener = threadSafeListener;
    }

    public final void updateLocalMap(final MapData localMap, final MapData highPrecMap) {
        Intrinsics.checkParameterIsNotNull(localMap, "localMap");
        Intrinsics.checkParameterIsNotNull(highPrecMap, "highPrecMap");
        costmapListener.notify(new Function2<Function2<? super MapData, ? super MapData, ? extends Unit>, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.mirperception.Costmap$updateLocalMap$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Function2<? super MapData, ? super MapData, ? extends Unit> function2, String str) {
                invoke2((Function2<? super MapData, ? super MapData, Unit>) function2, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function2<? super MapData, ? super MapData, Unit> it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.invoke(MapData.this, highPrecMap);
            }
        });
    }

    public final void registMapUpdatedListener(String name, Function0<Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        if (updatedListener.counts() >= 1) {
            return;
        }
        updatedListener.add(name, callback);
        installUpdatedListener();
    }

    public final void unregistMapUpdatedListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (updatedListener.counts() <= 0) {
            return;
        }
        uninstallUpdatedListener();
        updatedListener.remove(name);
    }

    public final void addCostmapErrorListener(String name, Function1<? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(PerceptionKt.getTAG(), "add CostmapErrorListener");
        costmapErrorListener.add(name, callback);
        installCostmapErrorListener();
    }

    public final void removeCostmapErrorListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(PerceptionKt.getTAG(), "remove CostmapErrorListener");
        uninstallCostmapErrorListener();
        costmapErrorListener.remove(name);
    }

    private final void informMapUpdated() {
        Pdlog.m3273d(PerceptionKt.getTAG(), "call informMapUpdated in Costmap.kt");
        updatedListener.notify(new Function2<Function0<? extends Unit>, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.mirperception.Costmap$informMapUpdated$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Function0<? extends Unit> function0, String str) {
                invoke2((Function0<Unit>) function0, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function0<Unit> it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.invoke();
            }
        });
    }

    private final void updateCostmapErrorInfo(final int flag) {
        Pdlog.m3273d(PerceptionKt.getTAG(), "call updateCostmapErrorInfo in Costmap.kt");
        costmapErrorListener.notify(new Function2<Function1<? super Integer, ? extends Unit>, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.mirperception.Costmap$updateCostmapErrorInfo$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Function1<? super Integer, ? extends Unit> function1, String str) {
                invoke2((Function1<? super Integer, Unit>) function1, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function1<? super Integer, Unit> it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.invoke(Integer.valueOf(flag));
            }
        });
    }
}
