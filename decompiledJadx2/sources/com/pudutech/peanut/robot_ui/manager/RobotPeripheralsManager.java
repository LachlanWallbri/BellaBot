package com.pudutech.peanut.robot_ui.manager;

import com.pudutech.base.Pdlog;
import com.pudutech.module_robot_selfcheck.RobotInitProcessor;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.extend.RobotPeripheralsFactoryExtKt;
import com.pudutech.robot.peripherals.RobotPeripheralsFactory;
import com.pudutech.robot.peripherals.common.RobotShutDownManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RobotPeripheralsManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0006\u0010\b\u001a\u00020\u0003J\u0011\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0002H\u0096\u0002R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/manager/RobotPeripheralsManager;", "Lkotlin/Function1;", "Lcom/pudutech/module_robot_selfcheck/RobotInitProcessor$InitStatus;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "init", "invoke", "it", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class RobotPeripheralsManager implements Function1<RobotInitProcessor.InitStatus, Unit> {
    public static final RobotPeripheralsManager INSTANCE = new RobotPeripheralsManager();
    private static final String TAG = RobotPeripheralsManager.class.getSimpleName();

    private RobotPeripheralsManager() {
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(RobotInitProcessor.InitStatus initStatus) {
        invoke2(initStatus);
        return Unit.INSTANCE;
    }

    public final void init() {
        RobotInitProcessor.INSTANCE.getINSTANCE().addOnInitStateListener(this);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public void invoke2(RobotInitProcessor.InitStatus it) {
        Intrinsics.checkParameterIsNotNull(it, "it");
        Pdlog.m3274e(TAG, String.valueOf(it));
        if (it == RobotInitProcessor.InitStatus.CHECKCAN) {
            if (Intrinsics.areEqual("peanut", "mock")) {
                RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).init(RobotContext.INSTANCE.getContext(), true);
            } else {
                RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).init(RobotContext.INSTANCE.getContext(), false);
            }
            RobotShutDownManager.INSTANCE.addShutdownEventListener();
            RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).addHardWareConnectListener(new Function1<Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.manager.RobotPeripheralsManager$invoke$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    RobotPeripheralsFactoryExtKt.get(RobotPeripheralsFactory.INSTANCE).removeHardWareConnectListener();
                    LightPlayManager.INSTANCE.playInit();
                }
            });
        }
    }
}
