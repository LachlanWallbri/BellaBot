package com.pudutech.bumblebee.robot_ui.manager;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.InitStepListener;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.mirsdk.aidl.serialize.StepState;
import com.pudutech.mirsdkwrap.lib.MirSdkManager;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mirsdkwrap.lib.robot.DevicesControlHelper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SafeModeManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0003\u0006\t\f\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J-\u0010\u000e\u001a\u00020\u000f2%\u0010\u0010\u001a!\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u000f0\u0011j\u0002`\u0016J\u0006\u0010\u0017\u001a\u00020\u000fJ-\u0010\u0018\u001a\u00020\u000f2%\u0010\u0010\u001a!\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u000f0\u0011j\u0002`\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0010\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u0010\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\r¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/SafeModeManager;", "", "()V", "TAG", "", "emergencyListener", "com/pudutech/bumblebee/robot_ui/manager/SafeModeManager$emergencyListener$1", "Lcom/pudutech/bumblebee/robot_ui/manager/SafeModeManager$emergencyListener$1;", "initStepListener", "com/pudutech/bumblebee/robot_ui/manager/SafeModeManager$initStepListener$1", "Lcom/pudutech/bumblebee/robot_ui/manager/SafeModeManager$initStepListener$1;", "securityFeedBackListener", "com/pudutech/bumblebee/robot_ui/manager/SafeModeManager$securityFeedBackListener$1", "Lcom/pudutech/bumblebee/robot_ui/manager/SafeModeManager$securityFeedBackListener$1;", "addSafeStateListener", "", "listener", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "isOpen", "Lcom/pudutech/mirsdkwrap/lib/interf/SecurityFeedBackListener;", "init", "removeSafeStateListener", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SafeModeManager {
    public static final String TAG = "SafeModeManager";
    public static final SafeModeManager INSTANCE = new SafeModeManager();
    private static final SafeModeManager$initStepListener$1 initStepListener = new InitStepListener() { // from class: com.pudutech.bumblebee.robot_ui.manager.SafeModeManager$initStepListener$1
        @Override // com.pudutech.bumblebee.business.robotsdk.sdk_listeners.InitStepListener
        public void onInitStep(InitStep p0, StepState p1, String p2) {
            SafeModeManager$emergencyListener$1 safeModeManager$emergencyListener$1;
            if (p0 == InitStep.Finish) {
                DevicesControlHelper devicesControlHelper = RobotMoveManager.INSTANCE.getDevicesControlHelper();
                if (devicesControlHelper != null) {
                    SafeModeManager safeModeManager = SafeModeManager.INSTANCE;
                    safeModeManager$emergencyListener$1 = SafeModeManager.emergencyListener;
                    devicesControlHelper.addOnEmergencyKeyPressedListener(safeModeManager$emergencyListener$1);
                }
                MirSdkManager.INSTANCE.switchSecurity(Constans.INSTANCE.isSafeMode(), Constans.INSTANCE.isSafeMode());
                Pdlog.m3273d(SafeModeManager.TAG, "onInitStep  isSafeMode = " + Constans.INSTANCE.isSafeMode() + " isSafeMode = " + Constans.INSTANCE.isSafeMode());
            }
        }
    };
    private static final SafeModeManager$securityFeedBackListener$1 securityFeedBackListener = new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.SafeModeManager$securityFeedBackListener$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public void invoke(boolean isOpen) {
            Pdlog.m3273d(SafeModeManager.TAG, "safe mode onStateChanged " + isOpen);
            Constans.INSTANCE.setSafeMode(isOpen);
        }
    };
    private static final SafeModeManager$emergencyListener$1 emergencyListener = new DevicesControlHelper.OnEmergencyKeyPressedListener() { // from class: com.pudutech.bumblebee.robot_ui.manager.SafeModeManager$emergencyListener$1
        @Override // com.pudutech.mirsdkwrap.lib.robot.DevicesControlHelper.OnEmergencyKeyPressedListener
        public void onEmergencyKeyPressed(boolean isPressed) {
            if (isPressed) {
                return;
            }
            MirSdkManager.INSTANCE.switchSecurity(Constans.INSTANCE.isSafeMode(), Constans.INSTANCE.isSafeMode());
            Pdlog.m3273d(SafeModeManager.TAG, "onEmergencyKeyPressed isPressed = " + isPressed + " isSafeMode = " + Constans.INSTANCE.isSafeMode() + " isSafeMode = " + Constans.INSTANCE.isSafeMode());
        }
    };

    private SafeModeManager() {
    }

    public final void init() {
        SDK.INSTANCE.getInitStepListeners().addListener(initStepListener);
        RobotMoveManager.INSTANCE.addOnSecurityFeedBackListener(securityFeedBackListener);
    }

    public final void addSafeStateListener(Function1<? super Boolean, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        RobotMoveManager.INSTANCE.addOnSecurityFeedBackListener(listener);
    }

    public final void removeSafeStateListener(Function1<? super Boolean, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        RobotMoveManager.INSTANCE.removeOnSecurityFeedBackListener(listener);
    }
}
