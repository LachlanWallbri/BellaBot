package com.pudutech.bumblebee.presenter.motion_task;

import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.presenter.motion_task.MotionContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MotionPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000+\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\rH\u0014J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/motion_task/MotionPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/motion_task/MotionContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/motion_task/MotionContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "listener", "com/pudutech/bumblebee/presenter/motion_task/MotionPresenter$listener$1", "Lcom/pudutech/bumblebee/presenter/motion_task/MotionPresenter$listener$1;", "onViewRemoved", "", "replaceView", "view", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MotionPresenter extends BaseOneViewPresenter<MotionContract.ViewInterface> implements MotionContract.PresenterInterface {
    private final String TAG = "MotionPresenter";
    private final MotionPresenter$listener$1 listener = new MotionPresenter$listener$1(this);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void replaceView(MotionContract.ViewInterface view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.replaceView((MotionPresenter) view);
        CoreDevices.INSTANCE.getMotionTask().addListener(this.listener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewRemoved() {
        super.onViewRemoved();
        CoreDevices.INSTANCE.getMotionTask().removeListener(this.listener);
    }
}
