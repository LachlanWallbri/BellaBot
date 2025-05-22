package com.pudutech.mirsdk.mircore.mirperception;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: Vision.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0006\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J:\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000f2*\u0010\u0010\u001a&\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0005J\u0011\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0086 J\u0011\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0086 J\t\u0010\u0017\u001a\u00020\bH\u0082 J\t\u0010\u0018\u001a\u00020\u0013H\u0086 J\u000e\u0010\u0019\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u001a\u001a\u00020\bH\u0082 J0\u0010\u001b\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u0007H\u0002RD\u0010\u0003\u001a,\u0012(\u0012&\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006 "}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/mirperception/Vision;", "", "()V", "visionListener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lkotlin/Function5;", "", "", "", "getVisionListener", "()Lcom/pudutech/base/architecture/ThreadSafeListener;", "setVisionListener", "(Lcom/pudutech/base/architecture/ThreadSafeListener;)V", "addVisionListener", "name", "", "callback", "enableVision", "flag", "", "initialize", "machineModel", "Lcom/pudutech/mirsdk/hardware/serialize/MachineModel;", "installFacePropertyListener", "isModuleInited", "removeVisionListener", "uninstallFacePropertyListener", "updateFaceInfo", "yaw", "pitch", "dist", "angle", "MirPerception_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class Vision {
    public static final Vision INSTANCE = new Vision();
    private static ThreadSafeListener<Function5<Integer, Double, Double, Double, Double, Unit>> visionListener;

    private final native void installFacePropertyListener();

    private final native void uninstallFacePropertyListener();

    public final native void enableVision(boolean flag);

    public final native boolean initialize(MachineModel machineModel);

    public final native boolean isModuleInited();

    static {
        Pdlog.m3273d(PerceptionKt.getTAG(), "load library: vision");
        System.loadLibrary("vision");
        visionListener = new ThreadSafeListener<>();
    }

    private Vision() {
    }

    public final void addVisionListener(String name, Function5<? super Integer, ? super Double, ? super Double, ? super Double, ? super Double, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(PerceptionKt.getTAG(), "add VisionListener");
        visionListener.add(name, callback);
        installFacePropertyListener();
    }

    public final void removeVisionListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(PerceptionKt.getTAG(), "remove VisionListener");
        uninstallFacePropertyListener();
        visionListener.remove(name);
    }

    private final void updateFaceInfo(final int flag, final double yaw, final double pitch, final double dist, final double angle) {
        visionListener.notify(new Function2<Function5<? super Integer, ? super Double, ? super Double, ? super Double, ? super Double, ? extends Unit>, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.mirperception.Vision$updateFaceInfo$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Function5<? super Integer, ? super Double, ? super Double, ? super Double, ? super Double, ? extends Unit> function5, String str) {
                invoke2((Function5<? super Integer, ? super Double, ? super Double, ? super Double, ? super Double, Unit>) function5, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function5<? super Integer, ? super Double, ? super Double, ? super Double, ? super Double, Unit> it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.invoke(Integer.valueOf(flag), Double.valueOf(yaw), Double.valueOf(pitch), Double.valueOf(dist), Double.valueOf(angle));
            }
        });
    }

    public final ThreadSafeListener<Function5<Integer, Double, Double, Double, Double, Unit>> getVisionListener() {
        return visionListener;
    }

    public final void setVisionListener(ThreadSafeListener<Function5<Integer, Double, Double, Double, Double, Unit>> threadSafeListener) {
        Intrinsics.checkParameterIsNotNull(threadSafeListener, "<set-?>");
        visionListener = threadSafeListener;
    }
}
