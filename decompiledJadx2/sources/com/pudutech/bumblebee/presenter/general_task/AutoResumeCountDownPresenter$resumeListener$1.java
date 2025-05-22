package com.pudutech.bumblebee.presenter.general_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.behavior.DelayResumeListener;
import com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownContract;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: AutoResumeCountDownPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, m3961d2 = {"com/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownPresenter$resumeListener$1", "Lcom/pudutech/bumblebee/business/behavior/DelayResumeListener;", "onCountDownTimeChange", "", "left_ms", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class AutoResumeCountDownPresenter$resumeListener$1 implements DelayResumeListener {
    final /* synthetic */ AutoResumeCountDownPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoResumeCountDownPresenter$resumeListener$1(AutoResumeCountDownPresenter autoResumeCountDownPresenter) {
        this.this$0 = autoResumeCountDownPresenter;
    }

    @Override // com.pudutech.bumblebee.business.behavior.DelayResumeListener
    public void onCountDownTimeChange(long left_ms) {
        long j;
        AutoResumeCountDownContract.ViewInterface theView;
        final long ceil = (long) Math.ceil(left_ms / 1000.0d);
        j = this.this$0.mTimeLeft_s;
        if (j != ceil) {
            String tag = this.this$0.getTAG();
            StringBuilder sb = new StringBuilder();
            sb.append("on time change. left_s=");
            sb.append(ceil);
            sb.append(" mView=");
            theView = this.this$0.getTheView();
            sb.append(theView);
            Pdlog.m3273d(tag, sb.toString());
            this.this$0.mTimeLeft_s = ceil;
            this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownPresenter$resumeListener$1$onCountDownTimeChange$1
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
                    AutoResumeCountDownContract.ViewInterface theView2;
                    theView2 = AutoResumeCountDownPresenter$resumeListener$1.this.this$0.getTheView();
                    if (theView2 != null) {
                        theView2.showTimeLeft(ceil, AutoResumeCountDownContract.Unit.SECOND);
                    }
                }
            });
        }
    }
}
