package com.pudutech.bumblebee.presenter.initialization_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.presenter.initialization_task.LEDScreenFontUpdateContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.robot.aidl.serialize.UpdateEvent;
import kotlin.Metadata;

/* compiled from: LEDScreenFontUpdatePresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000+\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/initialization_task/LEDScreenFontUpdatePresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/initialization_task/LEDScreenFontUpdateContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/initialization_task/LEDScreenFontUpdateContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "listener", "com/pudutech/bumblebee/presenter/initialization_task/LEDScreenFontUpdatePresenter$listener$1", "Lcom/pudutech/bumblebee/presenter/initialization_task/LEDScreenFontUpdatePresenter$listener$1;", "forceUpdate", "", "tryUpdate", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LEDScreenFontUpdatePresenter extends BaseOneViewPresenter<LEDScreenFontUpdateContract.ViewInterface> implements LEDScreenFontUpdateContract.PresenterInterface {
    private final String TAG = "LEDScreenFontUpdatePresenter";
    private final LEDScreenFontUpdatePresenter$listener$1 listener = new LEDScreenFontUpdatePresenter$listener$1(this);

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[UpdateEvent.values().length];

        static {
            $EnumSwitchMapping$0[UpdateEvent.UPDATING.ordinal()] = 1;
            $EnumSwitchMapping$0[UpdateEvent.SUCCESS.ordinal()] = 2;
            $EnumSwitchMapping$0[UpdateEvent.FAIL.ordinal()] = 3;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.bumblebee.presenter.initialization_task.LEDScreenFontUpdateContract.PresenterInterface
    public void tryUpdate() {
        Pdlog.m3273d(getTAG(), "tryUpdate ");
        Peripherals.INSTANCE.getLedScreen().update(false, this.listener);
    }

    @Override // com.pudutech.bumblebee.presenter.initialization_task.LEDScreenFontUpdateContract.PresenterInterface
    public void forceUpdate() {
        Pdlog.m3273d(getTAG(), "forceUpdate ");
        Peripherals.INSTANCE.getLedScreen().update(true, this.listener);
    }
}
