package com.pudutech.bumblebee.robot.disinfection_device;

import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.bumblebee.robot.aidl.IDisinfectionRobotListener;
import com.pudutech.bumblebee.robot.aidl.serialize.OpenState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: DisinfectionModuleManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "st", "Lcom/pudutech/bumblebee/robot/aidl/serialize/OpenState;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
final class DisinfectionModuleManager$init$10 extends Lambda implements Function1<OpenState, Unit> {
    public static final DisinfectionModuleManager$init$10 INSTANCE = new DisinfectionModuleManager$init$10();

    DisinfectionModuleManager$init$10() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(OpenState openState) {
        invoke2(openState);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(final OpenState st) {
        ThreadSafeListener threadSafeListener;
        Intrinsics.checkParameterIsNotNull(st, "st");
        DisinfectionModuleManager disinfectionModuleManager = DisinfectionModuleManager.INSTANCE;
        threadSafeListener = DisinfectionModuleManager.listeners;
        threadSafeListener.notify(new Function2<IDisinfectionRobotListener, String, Unit>() { // from class: com.pudutech.bumblebee.robot.disinfection_device.DisinfectionModuleManager$init$10.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(IDisinfectionRobotListener iDisinfectionRobotListener, String str) {
                invoke2(iDisinfectionRobotListener, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IDisinfectionRobotListener it, String name) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(name, "name");
                it.onUvLampPlateOpenState(OpenState.this);
            }
        });
    }
}
