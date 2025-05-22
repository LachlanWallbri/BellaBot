package com.pudutech.bumblebee.presenter.clean_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.presenter.clean_task.RgbdCleanContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: RgbdCleanPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0005\u001a\u00020\u00068TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/clean_task/RgbdCleanPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/clean_task/RgbdCleanContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/clean_task/RgbdCleanContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "actionCheck", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RgbdCleanPresenter extends BaseOneViewPresenter<RgbdCleanContract.ViewInterface> implements RgbdCleanContract.PresenterInterface {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return "RgbdCleanPresenter";
    }

    @Override // com.pudutech.bumblebee.presenter.clean_task.RgbdCleanContract.PresenterInterface
    public void actionCheck() {
        Pdlog.m3275i(getTAG(), "actionCheck");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.clean_task.RgbdCleanPresenter$actionCheck$1
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
                boolean checkClean = CoreDevices.INSTANCE.getRgbd().checkClean();
                Pdlog.m3275i(RgbdCleanPresenter.this.getTAG(), "CoreDevices.rgbd.checkCleand " + checkClean);
                RgbdCleanPresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.clean_task.RgbdCleanPresenter$actionCheck$1.1
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
                        RgbdCleanContract.ViewInterface theView;
                        theView = RgbdCleanPresenter.this.getTheView();
                        if (theView != null) {
                            theView.showRgbdAlert(false);
                        }
                    }
                });
            }
        });
    }
}
