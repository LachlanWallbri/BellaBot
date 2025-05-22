package com.pudutech.bumblebee.presenter.delivery_task;

import androidx.lifecycle.LifecycleOwner;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.BusinessContext;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryTaskSettingContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeliveryTaskSettingPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006H\u0016J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\fH\u0014R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryTaskSettingPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryTaskSettingContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryTaskSettingContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "deliveryTaskSettingModel", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryTaskSettingModel;", "actionUpdateVoice", "", "voiceName", "onResume", "owner", "Landroidx/lifecycle/LifecycleOwner;", "onViewAttach", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DeliveryTaskSettingPresenter extends BaseOneViewPresenter<DeliveryTaskSettingContract.ViewInterface> implements DeliveryTaskSettingContract.PresenterInterface {
    private final String TAG = "DeliveryTaskSettingPresenter";
    private final DeliveryTaskSettingModel deliveryTaskSettingModel = new DeliveryTaskSettingModel();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewAttach() {
        super.onViewAttach();
        Pdlog.m3273d(getTAG(), "onViewAttach");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliveryTaskSettingPresenter$onViewAttach$1
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
                DeliveryTaskSettingModel deliveryTaskSettingModel;
                DeliveryTaskSettingModel deliveryTaskSettingModel2;
                deliveryTaskSettingModel = DeliveryTaskSettingPresenter.this.deliveryTaskSettingModel;
                final boolean haveTableGroup = deliveryTaskSettingModel.haveTableGroup();
                deliveryTaskSettingModel2 = DeliveryTaskSettingPresenter.this.deliveryTaskSettingModel;
                final String currentVoiceName = deliveryTaskSettingModel2.getCurrentVoiceName();
                DeliveryTaskSettingPresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliveryTaskSettingPresenter$onViewAttach$1.1
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
                        DeliveryTaskSettingContract.ViewInterface theView;
                        DeliveryTaskSettingContract.ViewInterface theView2;
                        theView = DeliveryTaskSettingPresenter.this.getTheView();
                        if (theView != null) {
                            theView.showTableLayout(haveTableGroup);
                        }
                        theView2 = DeliveryTaskSettingPresenter.this.getTheView();
                        if (theView2 != null) {
                            theView2.updateMutliVoiceTip(currentVoiceName);
                        }
                    }
                });
            }
        });
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onResume(LifecycleOwner owner) {
        Intrinsics.checkParameterIsNotNull(owner, "owner");
        Pdlog.m3273d(getTAG(), "onResume");
        DeliveryTaskSettingContract.ViewInterface theView = getTheView();
        if (theView != null) {
            theView.showMutliVoiceButton(DeliverSettingModel.INSTANCE.getVoiceSwitchState(BusinessContext.INSTANCE.getContext()));
        }
        DeliveryTaskSettingContract.ViewInterface theView2 = getTheView();
        if (theView2 != null) {
            theView2.showPalletTtsVoiceButton();
        }
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DeliveryTaskSettingContract.PresenterInterface
    public void actionUpdateVoice(String voiceName) {
        Intrinsics.checkParameterIsNotNull(voiceName, "voiceName");
        DeliveryTaskSettingContract.ViewInterface theView = getTheView();
        if (theView != null) {
            theView.updateMutliVoiceTip(voiceName);
        }
    }
}
