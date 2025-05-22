package com.pudutech.bumblebee.presenter.touch_sensor_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.touch_sensor_task.TouchSensorContract;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TouchSensorPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005*\u0001\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\rH\u0016J\b\u0010\u0011\u001a\u00020\rH\u0014J\u0010\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "listener", "com/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorPresenter$listener$1", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorPresenter$listener$1;", "actionIgnoreWhenSaying", "", "boolean", "", "actionResetCount", "onViewRemoved", "replaceView", "view", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class TouchSensorPresenter extends BaseOneViewPresenter<TouchSensorContract.ViewInterface> implements TouchSensorContract.PresenterInterface {
    private final String TAG = "TouchSensorPresenter";
    private final TouchSensorPresenter$listener$1 listener = new TouchSensorPresenter$listener$1(this);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.bumblebee.presenter.touch_sensor_task.TouchSensorContract.PresenterInterface
    public void actionIgnoreWhenSaying(boolean r5) {
        Peripherals.INSTANCE.getTouchSensor().setIgnore(r5);
        Pdlog.m3273d(getTAG(), "action ignore=" + r5 + " when saying");
    }

    @Override // com.pudutech.bumblebee.presenter.touch_sensor_task.TouchSensorContract.PresenterInterface
    public void actionResetCount() {
        Peripherals.INSTANCE.getTouchSensor().reset();
    }

    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void replaceView(TouchSensorContract.ViewInterface view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.replaceView((TouchSensorPresenter) view);
        Peripherals.INSTANCE.getTouchSensor().addListener(this.listener);
        Peripherals.INSTANCE.getTouchSensor().setIgnore(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewRemoved() {
        super.onViewRemoved();
        Peripherals.INSTANCE.getTouchSensor().removeListener(this.listener);
    }
}
