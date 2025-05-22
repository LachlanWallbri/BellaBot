package com.pudutech.bumblebee.business.peripherals_task.led_screen_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.TimerThread;
import com.pudutech.bumblebee.robot.aidl.IUpdateListener;
import com.pudutech.bumblebee.robot.aidl.RobotInterface;
import com.pudutech.bumblebee.robot.aidl.serialize.LEDScreenMode;
import com.pudutech.bumblebee.robot.aidl.serialize.UpdateEvent;
import com.pudutech.bumblebee.robot.aidl.serialize.UpdateObject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LEDScreenTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/bumblebee/business/peripherals_task/led_screen_task/LEDScreenTask$update$l$1", "Lcom/pudutech/bumblebee/robot/aidl/IUpdateListener$Stub;", "onUpdateEvent", "", "p0", "Lcom/pudutech/bumblebee/robot/aidl/serialize/UpdateObject;", "p1", "Lcom/pudutech/bumblebee/robot/aidl/serialize/UpdateEvent;", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LEDScreenTask$update$l$1 extends IUpdateListener.Stub {
    final /* synthetic */ LEDScreenUpdateFontListener $listener;
    final /* synthetic */ LEDScreenTask this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LEDScreenTask$update$l$1(LEDScreenTask lEDScreenTask, LEDScreenUpdateFontListener lEDScreenUpdateFontListener) {
        this.this$0 = lEDScreenTask;
        this.$listener = lEDScreenUpdateFontListener;
    }

    @Override // com.pudutech.bumblebee.robot.aidl.IUpdateListener
    public void onUpdateEvent(UpdateObject p0, UpdateEvent p1) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onUpdateEvent p0=" + p0 + "  p1=" + p1);
        if (p1 == UpdateEvent.UPDATING) {
            this.this$0.isUpdating = true;
            TimerThread.INSTANCE.post(new Function0<Unit>() { // from class: com.pudutech.bumblebee.business.peripherals_task.led_screen_task.LEDScreenTask$update$l$1$onUpdateEvent$1
                /* JADX INFO: Access modifiers changed from: package-private */
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
                    RobotInterface controller = LEDScreenTask$update$l$1.this.this$0.getController();
                    if (controller != null) {
                        controller.controlLEDScreen(LEDScreenMode.IDLE);
                    }
                }
            });
        } else {
            this.this$0.isUpdating = false;
        }
        LEDScreenUpdateFontListener lEDScreenUpdateFontListener = this.$listener;
        if (lEDScreenUpdateFontListener != null) {
            if (p1 == null) {
                Intrinsics.throwNpe();
            }
            lEDScreenUpdateFontListener.onUpdateEvent(p1);
        }
    }
}
