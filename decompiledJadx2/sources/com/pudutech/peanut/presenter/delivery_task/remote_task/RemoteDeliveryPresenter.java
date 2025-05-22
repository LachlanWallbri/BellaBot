package com.pudutech.peanut.presenter.delivery_task.remote_task;

import com.pudutech.peanut.presenter.delivery_task.remote_task.RemoteDeliveryContract;
import com.pudutech.peanut.presenter.mvp_base.BaseOneViewPresenter;
import kotlin.Metadata;

/* compiled from: RemoteDeliveryPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u00020\nH\u0014J\b\u0010\u000b\u001a\u00020\nH\u0014R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/delivery_task/remote_task/RemoteDeliveryPresenter;", "Lcom/pudutech/peanut/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/peanut/presenter/delivery_task/remote_task/RemoteDeliveryContract$ViewInterface;", "Lcom/pudutech/peanut/presenter/delivery_task/remote_task/RemoteDeliveryContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "onViewAttach", "", "onViewRemoved", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class RemoteDeliveryPresenter extends BaseOneViewPresenter<RemoteDeliveryContract.ViewInterface> implements RemoteDeliveryContract.PresenterInterface {
    private final String TAG = "RemoteDeliveryPresenter";

    @Override // com.pudutech.peanut.presenter.mvp_base.BaseOneViewPresenter
    protected String getTAG() {
        return this.TAG;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.presenter.mvp_base.BaseOneViewPresenter
    public void onViewAttach() {
        super.onViewAttach();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.presenter.mvp_base.BaseOneViewPresenter
    public void onViewRemoved() {
        super.onViewRemoved();
    }
}
