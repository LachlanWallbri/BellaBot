package com.pudutech.bumblebee.presenter.motion_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.core_devices_task.motion_task.MotionEvent;
import com.pudutech.bumblebee.business.core_devices_task.motion_task.MotionListener;
import com.pudutech.bumblebee.presenter.motion_task.MotionContract;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MotionPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, m3961d2 = {"com/pudutech/bumblebee/presenter/motion_task/MotionPresenter$listener$1", "Lcom/pudutech/bumblebee/business/core_devices_task/motion_task/MotionListener;", "onMotion", "", "event", "Lcom/pudutech/bumblebee/business/core_devices_task/motion_task/MotionEvent;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MotionPresenter$listener$1 implements MotionListener {
    final /* synthetic */ MotionPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MotionPresenter$listener$1(MotionPresenter motionPresenter) {
        this.this$0 = motionPresenter;
    }

    @Override // com.pudutech.bumblebee.business.core_devices_task.motion_task.MotionListener
    public void onMotion(MotionEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Pdlog.m3273d(this.this$0.getTAG(), "onMotion event=" + event);
        try {
            final MotionContract.Event valueOf = MotionContract.Event.valueOf(event.name());
            this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.motion_task.MotionPresenter$listener$1$onMotion$1
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
                    MotionContract.ViewInterface theView;
                    theView = MotionPresenter$listener$1.this.this$0.getTheView();
                    if (theView != null) {
                        theView.showMotionEvent(valueOf);
                    }
                }
            });
        } catch (Exception unused) {
            Pdlog.m3274e(this.this$0.getTAG(), "Event not match " + event.name());
        }
    }
}
