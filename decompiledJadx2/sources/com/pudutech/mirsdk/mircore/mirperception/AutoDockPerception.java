package com.pudutech.mirsdk.mircore.mirperception;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: AutoDockPerception.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0006\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J4\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000f2$\u0010\u0010\u001a \u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0005J\t\u0010\u0011\u001a\u00020\u0012H\u0086 J\t\u0010\u0013\u001a\u00020\u0012H\u0086 J\u0011\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0016H\u0086 J\t\u0010\u0017\u001a\u00020\bH\u0082 J\t\u0010\u0018\u001a\u00020\u0006H\u0086 J\u000e\u0010\u0019\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u001a\u001a\u00020\bH\u0086 J\u0011\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u0006H\u0086 J\u0019\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u0007H\u0086 J\t\u0010 \u001a\u00020\bH\u0082 J(\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u0007H\u0002R>\u0010\u0003\u001a&\u0012\"\u0012 \u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006&"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/mirperception/AutoDockPerception;", "", "()V", "AutoDockListener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lkotlin/Function4;", "", "", "", "getAutoDockListener", "()Lcom/pudutech/base/architecture/ThreadSafeListener;", "setAutoDockListener", "(Lcom/pudutech/base/architecture/ThreadSafeListener;)V", "addAutoDockListener", "name", "", "callback", "getDockerEstimateTransform", "", "identifyDocker", "initialize", "machineModel", "Lcom/pudutech/mirsdk/hardware/serialize/MachineModel;", "installDockerEstimateTransformListener", "isModuleInited", "removeAutoDockListener", "resetAutoDockPerception", "setAutoDockSwitch", "need_autodock", "setDockerPose", "docker_pose_x", "docker_pose_y", "uninstallDockerEstimateTransformListener", "updateAutoDockInfo", "is_track", "x", "y", "yaw", "MirPerception_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AutoDockPerception {
    private static ThreadSafeListener<Function4<Boolean, Double, Double, Double, Unit>> AutoDockListener;
    public static final AutoDockPerception INSTANCE = new AutoDockPerception();

    private final native void installDockerEstimateTransformListener();

    private final native void uninstallDockerEstimateTransformListener();

    public final native double[] getDockerEstimateTransform();

    public final native double[] identifyDocker();

    public final native boolean initialize(MachineModel machineModel);

    public final native boolean isModuleInited();

    public final native void resetAutoDockPerception();

    public final native void setAutoDockSwitch(boolean need_autodock);

    public final native void setDockerPose(double docker_pose_x, double docker_pose_y);

    static {
        Pdlog.m3273d(PerceptionKt.getTAG(), "load library: auto_dock_perception");
        System.loadLibrary("auto_dock_perception");
        AutoDockListener = new ThreadSafeListener<>();
    }

    private AutoDockPerception() {
    }

    public final void addAutoDockListener(String name, Function4<? super Boolean, ? super Double, ? super Double, ? super Double, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(PerceptionKt.getTAG(), "add AutoDockListener");
        AutoDockListener.add(name, callback);
        installDockerEstimateTransformListener();
    }

    public final void removeAutoDockListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(PerceptionKt.getTAG(), "remove AutoDockListener");
        uninstallDockerEstimateTransformListener();
        AutoDockListener.remove(name);
    }

    private final void updateAutoDockInfo(final boolean is_track, final double x, final double y, final double yaw) {
        AutoDockListener.notify(new Function2<Function4<? super Boolean, ? super Double, ? super Double, ? super Double, ? extends Unit>, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.mirperception.AutoDockPerception$updateAutoDockInfo$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Function4<? super Boolean, ? super Double, ? super Double, ? super Double, ? extends Unit> function4, String str) {
                invoke2((Function4<? super Boolean, ? super Double, ? super Double, ? super Double, Unit>) function4, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function4<? super Boolean, ? super Double, ? super Double, ? super Double, Unit> it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.invoke(Boolean.valueOf(is_track), Double.valueOf(x), Double.valueOf(y), Double.valueOf(yaw));
            }
        });
    }

    public final ThreadSafeListener<Function4<Boolean, Double, Double, Double, Unit>> getAutoDockListener() {
        return AutoDockListener;
    }

    public final void setAutoDockListener(ThreadSafeListener<Function4<Boolean, Double, Double, Double, Unit>> threadSafeListener) {
        Intrinsics.checkParameterIsNotNull(threadSafeListener, "<set-?>");
        AutoDockListener = threadSafeListener;
    }
}
