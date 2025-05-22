package com.pudutech.bumblebee.presenter.clean_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.presenter.clean_task.CleanAlertContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: CleanAlertPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/clean_task/CleanAlertPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/clean_task/CleanAlertContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/clean_task/CleanAlertContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "lastAlertMeters", "", "tag_cleanAlert", "actionCheck", "", "actionClean", "didIt", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CleanAlertPresenter extends BaseOneViewPresenter<CleanAlertContract.ViewInterface> implements CleanAlertContract.PresenterInterface {
    private double lastAlertMeters;
    private final String tag_cleanAlert = "cleanAlert";

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return "CleanAlertPresenter";
    }

    @Override // com.pudutech.bumblebee.presenter.clean_task.CleanAlertContract.PresenterInterface
    public void actionCheck() {
        Pdlog.m3275i(getTAG(), "actionCheck");
        this.lastAlertMeters = CoreDevices.INSTANCE.getMileageTask().getMilestone(this.tag_cleanAlert);
        final double totalMeters = CoreDevices.INSTANCE.getMileageTask().getTotalMeters();
        Pdlog.m3275i(getTAG(), "actionCheck last=" + this.lastAlertMeters + " total=" + totalMeters);
        double d = this.lastAlertMeters;
        if (d > totalMeters || d == 0.0d) {
            this.lastAlertMeters = totalMeters;
            CoreDevices.INSTANCE.getMileageTask().setMilestone(this.tag_cleanAlert, this.lastAlertMeters);
        }
        final boolean z = totalMeters - this.lastAlertMeters > 100000.0d;
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.clean_task.CleanAlertPresenter$actionCheck$1
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
                CleanAlertContract.ViewInterface theView;
                double d2;
                theView = CleanAlertPresenter.this.getTheView();
                if (theView != null) {
                    boolean z2 = z;
                    double d3 = totalMeters;
                    d2 = CleanAlertPresenter.this.lastAlertMeters;
                    theView.showAlert(z2, d3 - d2);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.clean_task.CleanAlertContract.PresenterInterface
    public void actionClean(boolean didIt) {
        Pdlog.m3275i(getTAG(), "actionClean didIt=" + didIt);
        if (didIt) {
            this.lastAlertMeters = CoreDevices.INSTANCE.getMileageTask().getTotalMeters();
            CoreDevices.INSTANCE.getMileageTask().setMilestone(this.tag_cleanAlert, this.lastAlertMeters);
        }
    }
}
