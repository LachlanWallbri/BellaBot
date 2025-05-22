package com.pudutech.peanut.presenter.general_task;

import com.pudutech.base.Pdlog;
import com.pudutech.peanut.presenter.general_task.GeneralContract;
import com.pudutech.peanut.presenter.mvp_base.BaseOneViewPresenter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: GeneralPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/general_task/GeneralPresenter;", "Lcom/pudutech/peanut/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/peanut/presenter/general_task/GeneralContract$ViewInterface;", "Lcom/pudutech/peanut/presenter/general_task/GeneralContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "actionTouchScreen", "", "viewName", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class GeneralPresenter extends BaseOneViewPresenter<GeneralContract.ViewInterface> implements GeneralContract.PresenterInterface {
    private final String TAG = "GeneralPresenter";

    @Override // com.pudutech.peanut.presenter.mvp_base.BaseOneViewPresenter
    protected String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.peanut.presenter.general_task.GeneralContract.PresenterInterface
    public void actionTouchScreen(String viewName) {
        Pdlog.m3275i("GeneralPresenter", viewName + " touch screen");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.peanut.presenter.general_task.GeneralPresenter$actionTouchScreen$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        });
    }
}
