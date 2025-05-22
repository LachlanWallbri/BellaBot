package com.pudutech.bumblebee.presenter.delivery_task;

import androidx.lifecycle.LifecycleOwner;
import com.pudutech.bumblebee.presenter.BusinessContext;
import com.pudutech.bumblebee.presenter.delivery_task.DeliverSettingContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeliverSettingPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eH\u0016¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/delivery_task/DeliverSettingPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliverSettingContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliverSettingContract$PresenterInterface;", "()V", "actionSaveOutletSelectSwitchState", "", "state", "", "actionSaveVoiceSwitchState", "getTableColumn", "", "onCreate", "owner", "Landroidx/lifecycle/LifecycleOwner;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DeliverSettingPresenter extends BaseOneViewPresenter<DeliverSettingContract.ViewInterface> implements DeliverSettingContract.PresenterInterface {
    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onCreate(LifecycleOwner owner) {
        Intrinsics.checkParameterIsNotNull(owner, "owner");
        DeliverSettingContract.ViewInterface theView = getTheView();
        if (theView != null) {
            theView.showVoiceSwitch(DeliverSettingModel.INSTANCE.getVoiceSwitchState(BusinessContext.INSTANCE.getContext()));
        }
        DeliverSettingContract.ViewInterface theView2 = getTheView();
        if (theView2 != null) {
            theView2.showOutletSelectSwitch(DeliverSettingModel.INSTANCE.getOutletSelectSwitchState(BusinessContext.INSTANCE.getContext()));
        }
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DeliverSettingContract.PresenterInterface
    public void actionSaveVoiceSwitchState(boolean state) {
        DeliverSettingModel.INSTANCE.saveVoiceSwitchState(BusinessContext.INSTANCE.getContext(), state);
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DeliverSettingContract.PresenterInterface
    public int getTableColumn() {
        return DeliverSettingModel.INSTANCE.getTableColumn(BusinessContext.INSTANCE.getContext());
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DeliverSettingContract.PresenterInterface
    public void actionSaveOutletSelectSwitchState(boolean state) {
        DeliverSettingModel.INSTANCE.saveOutletSelectSwitchState(BusinessContext.INSTANCE.getContext(), state);
    }
}
