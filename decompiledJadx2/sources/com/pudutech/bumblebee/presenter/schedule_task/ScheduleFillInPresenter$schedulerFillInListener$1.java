package com.pudutech.bumblebee.presenter.schedule_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.ScheduleFillInListener;
import com.pudutech.bumblebee.presenter.schedule_task.ScheduleFillInContract;
import com.pudutech.mirsdk.aidl.serialize.Destination;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: ScheduleFillInPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/bumblebee/presenter/schedule_task/ScheduleFillInPresenter$schedulerFillInListener$1", "Lcom/pudutech/bumblebee/business/robotsdk/sdk_listeners/ScheduleFillInListener;", "onScheduleFillIn", "", "p1", "Lcom/pudutech/mirsdk/aidl/serialize/Destination;", "fillIn", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ScheduleFillInPresenter$schedulerFillInListener$1 implements ScheduleFillInListener {
    final /* synthetic */ ScheduleFillInPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ScheduleFillInPresenter$schedulerFillInListener$1(ScheduleFillInPresenter scheduleFillInPresenter) {
        this.this$0 = scheduleFillInPresenter;
    }

    @Override // com.pudutech.bumblebee.business.robotsdk.sdk_listeners.ScheduleFillInListener
    public void onScheduleFillIn(final Destination p1, final boolean fillIn) {
        String tag;
        tag = this.this$0.getTAG();
        Pdlog.m3275i(tag, "onScheduleFillIn " + p1 + ' ');
        this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.schedule_task.ScheduleFillInPresenter$schedulerFillInListener$1$onScheduleFillIn$1
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
                ScheduleFillInContract.ViewInterface theView;
                theView = ScheduleFillInPresenter$schedulerFillInListener$1.this.this$0.getTheView();
                if (theView != null) {
                    Destination destination = p1;
                    theView.onFillIn(destination != null ? destination.getName() : null, fillIn);
                }
            }
        });
    }
}
