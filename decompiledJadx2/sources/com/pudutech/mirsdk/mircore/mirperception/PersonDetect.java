package com.pudutech.mirsdk.mircore.mirperception;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: PersonDetect.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005JF\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u001326\u0010\u0014\u001a2\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00070\rJ\u0011\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0018H\u0086 J)\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u000eH\u0086 J\t\u0010\u001d\u001a\u00020\u001eH\u0086 J\u0011\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!H\u0086 J\t\u0010\"\u001a\u00020\u0007H\u0082 J\t\u0010#\u001a\u00020\u0007H\u0082 J\t\u0010$\u001a\u00020\u0018H\u0086 J\u000e\u0010%\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010&\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013J\u0019\u0010'\u001a\u00020\u00072\u0006\u0010(\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\u000eH\u0086 J\u0011\u0010*\u001a\u00020\u00072\u0006\u0010+\u001a\u00020,H\u0086 J\t\u0010-\u001a\u00020\u0007H\u0082 J\t\u0010.\u001a\u00020\u0007H\u0082 J\u0010\u0010/\u001a\u00020\u00072\u0006\u00100\u001a\u00020\u0006H\u0002J@\u00101\u001a\u00020\u00072\u0006\u00102\u001a\u00020\u00062\u0006\u00103\u001a\u00020\u00062\u0006\u00104\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u000e2\u0006\u00105\u001a\u00020\u000e2\u0006\u00106\u001a\u00020\u000e2\u0006\u00107\u001a\u00020\u000eH\u0002R,\u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bRP\u0010\f\u001a8\u00124\u00122\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00070\r0\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\t\"\u0004\b\u0010\u0010\u000b¨\u00068"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/mirperception/PersonDetect;", "", "()V", "personCountListener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lkotlin/Function1;", "", "", "getPersonCountListener", "()Lcom/pudutech/base/architecture/ThreadSafeListener;", "setPersonCountListener", "(Lcom/pudutech/base/architecture/ThreadSafeListener;)V", "personListener", "Lkotlin/Function7;", "", "getPersonListener", "setPersonListener", "addPersonCountListener", "name", "", "callback", "addPersonListener", "enablePersonDetect", "enable", "", "enablePersonDetectInRange", "min_angle", "max_angle", "max_dist", "getDetectRegin", "", "initialize", "machineModel", "Lcom/pudutech/mirsdk/hardware/serialize/MachineModel;", "installPersonCountListener", "installPersonListener", "isModuleInited", "removePersonCountListener", "removePersonListener", "setDetectRegin", "dist", "angle", "setInitRobotPose", "pose", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "uninstallPersonCountListener", "uninstallPersonListener", "updatePersonCount", "count", "updatePersonInfo", "flag", "id", "yaw", MapElement.Key.DIR, "vx", "vy", "MirPerception_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class PersonDetect {
    public static final PersonDetect INSTANCE = new PersonDetect();
    private static ThreadSafeListener<Function1<Integer, Unit>> personCountListener;
    private static ThreadSafeListener<Function7<Integer, Integer, Double, Double, Double, Double, Double, Unit>> personListener;

    private final native void installPersonCountListener();

    private final native void installPersonListener();

    private final native void uninstallPersonCountListener();

    private final native void uninstallPersonListener();

    public final native void enablePersonDetect(boolean enable);

    public final native void enablePersonDetectInRange(boolean enable, double min_angle, double max_angle, double max_dist);

    public final native double[] getDetectRegin();

    public final native boolean initialize(MachineModel machineModel);

    public final native boolean isModuleInited();

    public final native void setDetectRegin(double dist, double angle);

    public final native void setInitRobotPose(Vector3d pose);

    static {
        Pdlog.m3273d(PerceptionKt.getTAG(), "load library: person_detect");
        System.loadLibrary("person_detect");
        personListener = new ThreadSafeListener<>();
        personCountListener = new ThreadSafeListener<>();
    }

    private PersonDetect() {
    }

    public final void addPersonListener(String name, Function7<? super Integer, ? super Integer, ? super Double, ? super Double, ? super Double, ? super Double, ? super Double, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(PerceptionKt.getTAG(), "add Person Listener");
        personListener.add(name, callback);
        installPersonListener();
    }

    public final void removePersonListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(PerceptionKt.getTAG(), "remove Person Listener");
        uninstallPersonListener();
        personListener.remove(name);
    }

    public final void addPersonCountListener(String name, Function1<? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(PerceptionKt.getTAG(), "add Person Count Listener");
        personCountListener.add(name, callback);
        installPersonCountListener();
    }

    public final void removePersonCountListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(PerceptionKt.getTAG(), "remove Person Count Listener");
        uninstallPersonCountListener();
        personCountListener.remove(name);
    }

    private final void updatePersonInfo(final int flag, final int id, final double yaw, final double dist, final double dir, final double vx, final double vy) {
        personListener.notify(new Function2<Function7<? super Integer, ? super Integer, ? super Double, ? super Double, ? super Double, ? super Double, ? super Double, ? extends Unit>, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.mirperception.PersonDetect$updatePersonInfo$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Function7<? super Integer, ? super Integer, ? super Double, ? super Double, ? super Double, ? super Double, ? super Double, ? extends Unit> function7, String str) {
                invoke2((Function7<? super Integer, ? super Integer, ? super Double, ? super Double, ? super Double, ? super Double, ? super Double, Unit>) function7, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function7<? super Integer, ? super Integer, ? super Double, ? super Double, ? super Double, ? super Double, ? super Double, Unit> it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.invoke(Integer.valueOf(flag), Integer.valueOf(id), Double.valueOf(yaw), Double.valueOf(dist), Double.valueOf(dir), Double.valueOf(vx), Double.valueOf(vy));
            }
        });
    }

    private final void updatePersonCount(final int count) {
        personCountListener.notify(new Function2<Function1<? super Integer, ? extends Unit>, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.mirperception.PersonDetect$updatePersonCount$1
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
                it.invoke(Integer.valueOf(count));
            }
        });
    }

    public final ThreadSafeListener<Function7<Integer, Integer, Double, Double, Double, Double, Double, Unit>> getPersonListener() {
        return personListener;
    }

    public final void setPersonListener(ThreadSafeListener<Function7<Integer, Integer, Double, Double, Double, Double, Double, Unit>> threadSafeListener) {
        Intrinsics.checkParameterIsNotNull(threadSafeListener, "<set-?>");
        personListener = threadSafeListener;
    }

    public final ThreadSafeListener<Function1<Integer, Unit>> getPersonCountListener() {
        return personCountListener;
    }

    public final void setPersonCountListener(ThreadSafeListener<Function1<Integer, Unit>> threadSafeListener) {
        Intrinsics.checkParameterIsNotNull(threadSafeListener, "<set-?>");
        personCountListener = threadSafeListener;
    }
}
