package com.pudutech.bumblebee.presenter.rotation_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.PoseListener;
import com.pudutech.bumblebee.presenter.rotation_task.RotationContract;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: RotationPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/bumblebee/presenter/rotation_task/RotationPresenter$listener$1", "Lcom/pudutech/bumblebee/business/robotsdk/sdk_listeners/PoseListener;", "onPose", "", "p0", "", "p1", "p2", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RotationPresenter$listener$1 implements PoseListener {
    final /* synthetic */ RotationPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RotationPresenter$listener$1(RotationPresenter rotationPresenter) {
        this.this$0 = rotationPresenter;
    }

    @Override // com.pudutech.bumblebee.business.robotsdk.sdk_listeners.PoseListener
    public void onPose(double p0, double p1, double p2) {
        double within2PI;
        double d;
        double within2PI2;
        boolean z;
        double d2;
        double d3;
        boolean z2;
        double d4;
        double d5;
        within2PI = this.this$0.toWithin2PI(p2);
        RotationPresenter rotationPresenter = this.this$0;
        d = rotationPresenter.target;
        within2PI2 = rotationPresenter.toWithin2PI(d);
        z = this.this$0.isClockwise;
        if (z) {
            if (within2PI2 > within2PI) {
                d5 = this.this$0.M_2PI;
                d3 = d5 - (within2PI2 - within2PI);
            } else {
                d3 = within2PI - within2PI2;
            }
        } else if (within2PI2 > within2PI) {
            d3 = within2PI2 - within2PI;
        } else {
            d2 = this.this$0.M_2PI;
            d3 = (d2 + within2PI2) - within2PI;
        }
        String tag = this.this$0.getTAG();
        StringBuilder sb = new StringBuilder();
        sb.append("onPose now=");
        sb.append(within2PI);
        sb.append(" want=");
        sb.append(within2PI2);
        sb.append(" isClockwise=");
        z2 = this.this$0.isClockwise;
        sb.append(z2);
        sb.append(" diff=");
        sb.append(d3);
        Pdlog.m3276v(tag, sb.toString());
        d4 = this.this$0.FINISH_THRESHOLD;
        if (d3 < d4) {
            this.this$0.endListenerAndTimer();
            this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.rotation_task.RotationPresenter$listener$1$onPose$1
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
                    RotationContract.ViewInterface theView;
                    theView = RotationPresenter$listener$1.this.this$0.getTheView();
                    if (theView != null) {
                        theView.showRotateEvent(RotationContract.ViewEvent.FINISH);
                    }
                }
            });
        }
    }
}
