package com.pudutech.bumblebee.presenter.initialization_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.peripherals_task.led_screen_task.LEDScreenUpdateFontListener;
import com.pudutech.bumblebee.presenter.initialization_task.LEDScreenFontUpdateContract;
import com.pudutech.bumblebee.presenter.initialization_task.LEDScreenFontUpdatePresenter;
import com.pudutech.bumblebee.robot.aidl.serialize.UpdateEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LEDScreenFontUpdatePresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, m3961d2 = {"com/pudutech/bumblebee/presenter/initialization_task/LEDScreenFontUpdatePresenter$listener$1", "Lcom/pudutech/bumblebee/business/peripherals_task/led_screen_task/LEDScreenUpdateFontListener;", "onUpdateEvent", "", "p1", "Lcom/pudutech/bumblebee/robot/aidl/serialize/UpdateEvent;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LEDScreenFontUpdatePresenter$listener$1 implements LEDScreenUpdateFontListener {
    final /* synthetic */ LEDScreenFontUpdatePresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LEDScreenFontUpdatePresenter$listener$1(LEDScreenFontUpdatePresenter lEDScreenFontUpdatePresenter) {
        this.this$0 = lEDScreenFontUpdatePresenter;
    }

    @Override // com.pudutech.bumblebee.business.peripherals_task.led_screen_task.LEDScreenUpdateFontListener
    public void onUpdateEvent(UpdateEvent p1) {
        Intrinsics.checkParameterIsNotNull(p1, "p1");
        Pdlog.m3273d(this.this$0.getTAG(), "onUpdateEvent p1=" + p1);
        int i = LEDScreenFontUpdatePresenter.WhenMappings.$EnumSwitchMapping$0[p1.ordinal()];
        if (i == 1) {
            this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.initialization_task.LEDScreenFontUpdatePresenter$listener$1$onUpdateEvent$1
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
                    LEDScreenFontUpdateContract.ViewInterface theView;
                    theView = LEDScreenFontUpdatePresenter$listener$1.this.this$0.getTheView();
                    if (theView != null) {
                        theView.onUpdateEvent(LEDScreenFontUpdateContract.UpdateEvent.UPDATING);
                    }
                }
            });
        } else if (i == 2 || i == 3) {
            this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.initialization_task.LEDScreenFontUpdatePresenter$listener$1$onUpdateEvent$2
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
                    LEDScreenFontUpdateContract.ViewInterface theView;
                    theView = LEDScreenFontUpdatePresenter$listener$1.this.this$0.getTheView();
                    if (theView != null) {
                        theView.onUpdateEvent(LEDScreenFontUpdateContract.UpdateEvent.DONE);
                    }
                }
            });
        } else {
            this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.initialization_task.LEDScreenFontUpdatePresenter$listener$1$onUpdateEvent$3
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
                    LEDScreenFontUpdateContract.ViewInterface theView;
                    theView = LEDScreenFontUpdatePresenter$listener$1.this.this$0.getTheView();
                    if (theView != null) {
                        theView.onUpdateEvent(LEDScreenFontUpdateContract.UpdateEvent.UNNECESSARY_UPDATE);
                    }
                }
            });
        }
    }
}
