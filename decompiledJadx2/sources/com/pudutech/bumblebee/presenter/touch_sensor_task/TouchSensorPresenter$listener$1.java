package com.pudutech.bumblebee.presenter.touch_sensor_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.TouchEvent;
import com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.TouchSensorListener;
import com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.TouchSensorPlace;
import com.pudutech.bumblebee.presenter.touch_sensor_task.TouchSensorContract;
import com.pudutech.resources.voice.VoiceItem;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: TouchSensorPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorPresenter$listener$1", "Lcom/pudutech/bumblebee/business/peripherals_task/touch_sensor_task/TouchSensorListener;", "onTouchEvent", "", "touchPlace", "Lcom/pudutech/bumblebee/business/peripherals_task/touch_sensor_task/TouchSensorPlace;", "touchEvent", "Lcom/pudutech/bumblebee/business/peripherals_task/touch_sensor_task/TouchEvent;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class TouchSensorPresenter$listener$1 implements TouchSensorListener {
    final /* synthetic */ TouchSensorPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TouchSensorPresenter$listener$1(TouchSensorPresenter touchSensorPresenter) {
        this.this$0 = touchSensorPresenter;
    }

    /* JADX WARN: Type inference failed for: r8v13, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v2, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v20, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v4, types: [T, java.lang.String] */
    @Override // com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.TouchSensorListener
    public void onTouchEvent(TouchSensorPlace touchPlace, TouchEvent touchEvent) {
        Intrinsics.checkParameterIsNotNull(touchPlace, "touchPlace");
        Intrinsics.checkParameterIsNotNull(touchEvent, "touchEvent");
        Pdlog.m3273d(this.this$0.getTAG(), "onTouch " + touchPlace + ' ' + touchEvent);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final TouchSensorContract.Place valueOf = TouchSensorContract.Place.valueOf(touchPlace.name());
        final TouchSensorContract.Event valueOf2 = TouchSensorContract.Event.valueOf(touchEvent.name());
        if (valueOf2.getValue() != touchEvent.getValue()) {
            Pdlog.m3274e(this.this$0.getTAG(), "enum class trans fail. place=" + valueOf + " event=" + valueOf2);
            return;
        }
        if (valueOf2 == TouchSensorContract.Event.HAPPY || valueOf2 == TouchSensorContract.Event.HAPPY_LEVEL2 || valueOf2 == TouchSensorContract.Event.HAPPY_LEVEL3) {
            if (valueOf == TouchSensorContract.Place.LEFT_EAR || valueOf == TouchSensorContract.Place.RIGHT_EAR) {
                objectRef.element = "voice23_" + touchEvent.getValue();
            } else {
                objectRef.element = "voice24_" + touchEvent.getValue();
            }
        } else {
            if (valueOf2 == TouchSensorContract.Event.ANGER) {
                objectRef.element = "voice25_" + (Peripherals.INSTANCE.getTouchSensor().getAngryCnt() <= 3 ? Peripherals.INSTANCE.getTouchSensor().getAngryCnt() : 3);
            } else if (valueOf2 == TouchSensorContract.Event.ANGER_LEVEL2) {
                objectRef.element = "voice26_" + ((Peripherals.INSTANCE.getTouchSensor().getAngryCnt() <= 5 ? Peripherals.INSTANCE.getTouchSensor().getAngryCnt() : 5) - 3);
            }
        }
        this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.touch_sensor_task.TouchSensorPresenter$listener$1$onTouchEvent$1
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

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                TouchSensorContract.ViewInterface theView;
                theView = TouchSensorPresenter$listener$1.this.this$0.getTheView();
                if (theView != null) {
                    theView.sayTouched(VoiceItem.valueOf((String) objectRef.element));
                }
            }
        });
        this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.touch_sensor_task.TouchSensorPresenter$listener$1$onTouchEvent$2
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
                TouchSensorContract.ViewInterface theView;
                theView = TouchSensorPresenter$listener$1.this.this$0.getTheView();
                if (theView != null) {
                    theView.showTouched(valueOf, valueOf2);
                }
            }
        });
    }
}
