package com.pudutech.bumblebee.business.peripherals_task.led_screen_task;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.TimerThread;
import com.pudutech.bumblebee.robot.aidl.RobotInterface;
import com.pudutech.bumblebee.robot.aidl.serialize.LEDScreenMode;
import com.pudutech.bumblebee.robot.aidl.serialize.UpdateEvent;
import com.pudutech.bumblebee.robot.aidl.serialize.UpdateObject;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdkwrap.lib.robot.MachineInfoHelper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LEDScreenTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0014J\u001a\u0010\u0015\u001a\u00020\u00102\b\b\u0002\u0010\u0016\u001a\u00020\f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/peripherals_task/led_screen_task/LEDScreenTask;", "", "()V", "TAG", "", "controller", "Lcom/pudutech/bumblebee/robot/aidl/RobotInterface;", "getController$module_bumblebee_business_robotRelease", "()Lcom/pudutech/bumblebee/robot/aidl/RobotInterface;", "setController$module_bumblebee_business_robotRelease", "(Lcom/pudutech/bumblebee/robot/aidl/RobotInterface;)V", "isUpdating", "", "last", "Lcom/pudutech/bumblebee/robot/aidl/serialize/LEDScreenMode;", "control", "", "mode", "string", TypedValues.Custom.S_COLOR, "", "update", "isForce", "listener", "Lcom/pudutech/bumblebee/business/peripherals_task/led_screen_task/LEDScreenUpdateFontListener;", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LEDScreenTask {
    private final String TAG = "LEDScreenTask";
    private RobotInterface controller;
    private boolean isUpdating;
    private LEDScreenMode last;

    /* renamed from: getController$module_bumblebee_business_robotRelease, reason: from getter */
    public final RobotInterface getController() {
        return this.controller;
    }

    public final void setController$module_bumblebee_business_robotRelease(RobotInterface robotInterface) {
        this.controller = robotInterface;
    }

    public final void control(final LEDScreenMode mode) {
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        if (this.isUpdating) {
            return;
        }
        TimerThread.INSTANCE.post(new Function0<Unit>() { // from class: com.pudutech.bumblebee.business.peripherals_task.led_screen_task.LEDScreenTask$control$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                String str;
                LEDScreenMode lEDScreenMode;
                LEDScreenMode lEDScreenMode2;
                RobotInterface controller;
                str = LEDScreenTask.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("control mode=");
                sb.append(mode);
                sb.append(" when control=");
                sb.append(LEDScreenTask.this.getController());
                sb.append(" last=");
                lEDScreenMode = LEDScreenTask.this.last;
                sb.append(lEDScreenMode);
                Pdlog.m3273d(str, sb.toString());
                lEDScreenMode2 = LEDScreenTask.this.last;
                if (lEDScreenMode2 == mode || (controller = LEDScreenTask.this.getController()) == null) {
                    return;
                }
                controller.controlLEDScreen(mode);
                LEDScreenTask.this.last = mode;
            }
        });
    }

    public final void control(final String string, final int color) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        if (this.isUpdating) {
            return;
        }
        TimerThread.INSTANCE.post(new Function0<Unit>() { // from class: com.pudutech.bumblebee.business.peripherals_task.led_screen_task.LEDScreenTask$control$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                String str;
                str = LEDScreenTask.this.TAG;
                Pdlog.m3273d(str, "control.set " + string + ' ' + color);
                LEDScreenTask.this.last = (LEDScreenMode) null;
                RobotInterface controller = LEDScreenTask.this.getController();
                if (controller != null) {
                    controller.setupLEDScreenContent(string, color);
                }
            }
        });
    }

    public static /* synthetic */ void update$default(LEDScreenTask lEDScreenTask, boolean z, LEDScreenUpdateFontListener lEDScreenUpdateFontListener, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        lEDScreenTask.update(z, lEDScreenUpdateFontListener);
    }

    public final void update(boolean isForce, LEDScreenUpdateFontListener listener) {
        Integer robotMinorVersion;
        MachineModel robotType = MachineInfoHelper.INSTANCE.getRobotType();
        Pdlog.m3273d(this.TAG, "update isForce=" + isForce + "  robot_type=" + robotType + " listener=" + listener + " controller=" + this.controller);
        if (robotType == null) {
            if (listener != null) {
                listener.onUpdateEvent(UpdateEvent.UNNECESSARY);
            }
        } else if (robotType != MachineModel.BellaBot || (robotMinorVersion = MachineInfoHelper.INSTANCE.getRobotMinorVersion()) == null || robotMinorVersion.intValue() != 3) {
            if (listener != null) {
                listener.onUpdateEvent(UpdateEvent.UNNECESSARY);
            }
        } else {
            LEDScreenTask$update$l$1 lEDScreenTask$update$l$1 = new LEDScreenTask$update$l$1(this, listener);
            RobotInterface robotInterface = this.controller;
            if (robotInterface != null) {
                robotInterface.update(UpdateObject.LED_SCREEN_FONT_LIB, lEDScreenTask$update$l$1, isForce);
            }
        }
    }
}
