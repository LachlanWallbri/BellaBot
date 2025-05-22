package com.pudutech.mirsdk.mircore.mirperception;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReflectorDetect.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00132\u001e\u0010\u0014\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0005J\"\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u000eJ\u0011\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0018H\u0086 J\u0011\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0086 J\t\u0010\u001c\u001a\u00020\bH\u0082 J\t\u0010\u001d\u001a\u00020\bH\u0082 J\t\u0010\u001e\u001a\u00020\u0018H\u0086 J\u000e\u0010\u001f\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010 \u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0013J\u0011\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\u0007H\u0086 J\u0019\u0010#\u001a\u00020\b2\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u0006H\u0086 J\t\u0010&\u001a\u00020\bH\u0082 J\t\u0010'\u001a\u00020\bH\u0082 J \u0010(\u001a\u00020\b2\u0006\u0010%\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00072\u0006\u0010)\u001a\u00020\u0007H\u0002J\u0010\u0010*\u001a\u00020\b2\u0006\u0010+\u001a\u00020\u0006H\u0002R8\u0010\u0003\u001a \u0012\u001c\u0012\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR,\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u000e0\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\f¨\u0006,"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/mirperception/ReflectorDetect;", "", "()V", "reflectorDetectListener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lkotlin/Function3;", "", "", "", "getReflectorDetectListener", "()Lcom/pudutech/base/architecture/ThreadSafeListener;", "setReflectorDetectListener", "(Lcom/pudutech/base/architecture/ThreadSafeListener;)V", "roadWidthMeasureListener", "Lkotlin/Function1;", "getRoadWidthMeasureListener", "setRoadWidthMeasureListener", "addReflectorDetectListener", "name", "", "callback", "addRoadWidthMeasureListener", "enableReflectorDetect", "flag", "", "initialize", "machineModel", "Lcom/pudutech/mirsdk/hardware/serialize/MachineModel;", "installReflectorDetectListener", "installRoadWidthMeasureListener", "isModuleInited", "removeReflectorDetectListener", "removeRoadWidthMeasureListener", "setLidarType", "type", "setReflectorLevel", "level", "distance", "uninstallReflectorDetectListener", "uninstallRoadWidthMeasureListener", "updateReflectorInfo", "isInDangrousRegion", "updateRoadWidthInfo", "width", "MirPerception_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ReflectorDetect {
    public static final ReflectorDetect INSTANCE = new ReflectorDetect();
    private static ThreadSafeListener<Function3<Double, Integer, Integer, Unit>> reflectorDetectListener;
    private static ThreadSafeListener<Function1<Double, Unit>> roadWidthMeasureListener;

    private final native void installReflectorDetectListener();

    private final native void installRoadWidthMeasureListener();

    private final native void uninstallReflectorDetectListener();

    private final native void uninstallRoadWidthMeasureListener();

    public final native void enableReflectorDetect(boolean flag);

    public final native boolean initialize(MachineModel machineModel);

    public final native boolean isModuleInited();

    public final native void setLidarType(int type);

    public final native void setReflectorLevel(int level, double distance);

    static {
        Pdlog.m3273d(PerceptionKt.getTAG(), "load library: reflector_detect");
        System.loadLibrary("reflector_detect");
        reflectorDetectListener = new ThreadSafeListener<>();
        roadWidthMeasureListener = new ThreadSafeListener<>();
    }

    private ReflectorDetect() {
    }

    public final ThreadSafeListener<Function3<Double, Integer, Integer, Unit>> getReflectorDetectListener() {
        return reflectorDetectListener;
    }

    public final void setReflectorDetectListener(ThreadSafeListener<Function3<Double, Integer, Integer, Unit>> threadSafeListener) {
        Intrinsics.checkParameterIsNotNull(threadSafeListener, "<set-?>");
        reflectorDetectListener = threadSafeListener;
    }

    public final ThreadSafeListener<Function1<Double, Unit>> getRoadWidthMeasureListener() {
        return roadWidthMeasureListener;
    }

    public final void setRoadWidthMeasureListener(ThreadSafeListener<Function1<Double, Unit>> threadSafeListener) {
        Intrinsics.checkParameterIsNotNull(threadSafeListener, "<set-?>");
        roadWidthMeasureListener = threadSafeListener;
    }

    public final void addReflectorDetectListener(String name, Function3<? super Double, ? super Integer, ? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(PerceptionKt.getTAG(), "add ReflectorListener");
        reflectorDetectListener.add(name, callback);
        installReflectorDetectListener();
    }

    public final void removeReflectorDetectListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(PerceptionKt.getTAG(), "remove ReflectorListener");
        reflectorDetectListener.remove(name);
        if (reflectorDetectListener.counts() == 0) {
            uninstallReflectorDetectListener();
        }
    }

    public final void addRoadWidthMeasureListener(String name, Function1<? super Double, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(PerceptionKt.getTAG(), "add RoadWidthMeasureListener");
        roadWidthMeasureListener.add(name, callback);
        installRoadWidthMeasureListener();
    }

    public final void removeRoadWidthMeasureListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(PerceptionKt.getTAG(), "remove RoadWidthMeasureListener");
        roadWidthMeasureListener.remove(name);
        if (roadWidthMeasureListener.counts() == 0) {
            uninstallRoadWidthMeasureListener();
        }
    }

    private final void updateReflectorInfo(final double distance, final int level, final int isInDangrousRegion) {
        Pdlog.m3273d(PerceptionKt.getTAG(), "call updateReflectorInfo in Costmap.kt");
        reflectorDetectListener.notify(new Function2<Function3<? super Double, ? super Integer, ? super Integer, ? extends Unit>, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.mirperception.ReflectorDetect$updateReflectorInfo$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Function3<? super Double, ? super Integer, ? super Integer, ? extends Unit> function3, String str) {
                invoke2((Function3<? super Double, ? super Integer, ? super Integer, Unit>) function3, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function3<? super Double, ? super Integer, ? super Integer, Unit> it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.invoke(Double.valueOf(distance), Integer.valueOf(level), Integer.valueOf(isInDangrousRegion));
            }
        });
    }

    private final void updateRoadWidthInfo(final double width) {
        Pdlog.m3273d(PerceptionKt.getTAG(), "call updateRoadWidthInfo in Costmap.kt");
        roadWidthMeasureListener.notify(new Function2<Function1<? super Double, ? extends Unit>, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.mirperception.ReflectorDetect$updateRoadWidthInfo$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Function1<? super Double, ? extends Unit> function1, String str) {
                invoke2((Function1<? super Double, Unit>) function1, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function1<? super Double, Unit> it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.invoke(Double.valueOf(width));
            }
        });
    }
}
