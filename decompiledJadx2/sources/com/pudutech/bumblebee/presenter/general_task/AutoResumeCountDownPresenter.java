package com.pudutech.bumblebee.presenter.general_task;

import com.pudutech.bumblebee.business.behavior.DelayResumeActive;
import com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import kotlin.Metadata;

/* compiled from: AutoResumeCountDownPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000)\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\b\u0006*\u0001\f\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u0014\u0010\u000e\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "mTimeLeft_s", "", "resumeListener", "com/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownPresenter$resumeListener$1", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownPresenter$resumeListener$1;", "timeLeft_s", "getTimeLeft_s", "()J", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class AutoResumeCountDownPresenter extends BaseOneViewPresenter<AutoResumeCountDownContract.ViewInterface> implements AutoResumeCountDownContract.PresenterInterface {
    private long mTimeLeft_s;
    private final String TAG = "AutoResumeCountDownPresenter";
    private final AutoResumeCountDownPresenter$resumeListener$1 resumeListener = new AutoResumeCountDownPresenter$resumeListener$1(this);

    public AutoResumeCountDownPresenter() {
        DelayResumeActive.INSTANCE.addListener(this.resumeListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownContract.PresenterInterface
    /* renamed from: getTimeLeft_s, reason: from getter */
    public long getMTimeLeft_s() {
        return this.mTimeLeft_s;
    }
}
