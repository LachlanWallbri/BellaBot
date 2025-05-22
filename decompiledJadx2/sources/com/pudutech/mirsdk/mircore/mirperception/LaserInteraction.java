package com.pudutech.mirsdk.mircore.mirperception;

import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: LaserInteraction.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005J\u0011\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0086 J\t\u0010\u0014\u001a\u00020\u0007H\u0082 J\t\u0010\u0015\u001a\u00020\u0011H\u0086 J\u000e\u0010\u0016\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eJ\u0011\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0011H\u0086 J\t\u0010\u0019\u001a\u00020\u0007H\u0082 J\u0010\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0006H\u0002R,\u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/mirperception/LaserInteraction;", "", "()V", "interactionListener", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lkotlin/Function1;", "", "", "getInteractionListener", "()Lcom/pudutech/base/architecture/ThreadSafeListener;", "setInteractionListener", "(Lcom/pudutech/base/architecture/ThreadSafeListener;)V", "addInteractionListener", "name", "", "callback", "initialize", "", "machineModel", "Lcom/pudutech/mirsdk/hardware/serialize/MachineModel;", "installInteractionListener", "isModuleInited", "removeInteractionListener", "setInteractionSwitch", "need_interaction", "uninstallInteractionListener", "updateIteractionInfo", SpeechUtility.TAG_RESOURCE_RESULT, "MirPerception_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class LaserInteraction {
    public static final LaserInteraction INSTANCE = new LaserInteraction();
    private static ThreadSafeListener<Function1<byte[], Unit>> interactionListener;

    private final native void installInteractionListener();

    private final native void uninstallInteractionListener();

    public final native boolean initialize(MachineModel machineModel);

    public final native boolean isModuleInited();

    public final native void setInteractionSwitch(boolean need_interaction);

    static {
        Pdlog.m3273d(PerceptionKt.getTAG(), "load library: laser_interaction");
        System.loadLibrary("laser_interaction");
        interactionListener = new ThreadSafeListener<>();
    }

    private LaserInteraction() {
    }

    public final void addInteractionListener(String name, Function1<? super byte[], Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(PerceptionKt.getTAG(), "addInteractionListener");
        interactionListener.add(name, callback);
        installInteractionListener();
    }

    public final void removeInteractionListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Pdlog.m3273d(PerceptionKt.getTAG(), "removeInteractionListener");
        uninstallInteractionListener();
        interactionListener.remove(name);
    }

    private final void updateIteractionInfo(final byte[] result) {
        interactionListener.notify(new Function2<Function1<? super byte[], ? extends Unit>, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.mirperception.LaserInteraction$updateIteractionInfo$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Function1<? super byte[], ? extends Unit> function1, String str) {
                invoke2((Function1<? super byte[], Unit>) function1, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function1<? super byte[], Unit> it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.invoke(result);
            }
        });
    }

    public final ThreadSafeListener<Function1<byte[], Unit>> getInteractionListener() {
        return interactionListener;
    }

    public final void setInteractionListener(ThreadSafeListener<Function1<byte[], Unit>> threadSafeListener) {
        Intrinsics.checkParameterIsNotNull(threadSafeListener, "<set-?>");
        interactionListener = threadSafeListener;
    }
}
