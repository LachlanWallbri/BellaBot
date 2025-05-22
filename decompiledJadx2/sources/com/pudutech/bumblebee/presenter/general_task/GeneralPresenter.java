package com.pudutech.bumblebee.presenter.general_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.presenter.general_task.GeneralContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: GeneralPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0006H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/general_task/GeneralPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/general_task/GeneralContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/general_task/GeneralContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "isNoNeedLcdClick", "", "()Z", "setNoNeedLcdClick", "(Z)V", "actionTouchScreen", "", "viewName", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class GeneralPresenter extends BaseOneViewPresenter<GeneralContract.ViewInterface> implements GeneralContract.PresenterInterface {
    private final String TAG = "GeneralPresenter";
    private boolean isNoNeedLcdClick;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    /* renamed from: isNoNeedLcdClick, reason: from getter */
    public final boolean getIsNoNeedLcdClick() {
        return this.isNoNeedLcdClick;
    }

    public final void setNoNeedLcdClick(boolean z) {
        this.isNoNeedLcdClick = z;
    }

    @Override // com.pudutech.bumblebee.presenter.general_task.GeneralContract.PresenterInterface
    public void actionTouchScreen(final String viewName) {
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.general_task.GeneralPresenter$actionTouchScreen$1
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
                if (GeneralPresenter.this.getIsNoNeedLcdClick()) {
                    return;
                }
                Peripherals.INSTANCE.getLcd().touchScreen();
                Pdlog.m3275i("GeneralPresenter", viewName + " touch screen isNoNeedLcdClick =" + GeneralPresenter.this.getIsNoNeedLcdClick());
            }
        });
    }
}
