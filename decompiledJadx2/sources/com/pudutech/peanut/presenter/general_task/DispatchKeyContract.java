package com.pudutech.peanut.presenter.general_task;

import androidx.core.app.NotificationCompat;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.kotlinmvp.mvp_base.BasePresenterInterface;
import com.pudutech.peanut.presenter.mvp_base.BaseViewInterface;
import kotlin.Metadata;

/* compiled from: DispatchKeyContract.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/general_task/DispatchKeyContract;", "", "KEY", "PresenterInterface", "ViewInterface", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public interface DispatchKeyContract {

    /* compiled from: DispatchKeyContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/general_task/DispatchKeyContract$KEY;", "", "(Ljava/lang/String;I)V", "QR_EMPLOYEES", "REMOTE_START", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum KEY {
        QR_EMPLOYEES,
        REMOTE_START
    }

    /* compiled from: DispatchKeyContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/general_task/DispatchKeyContract$PresenterInterface;", "Lcom/pudutech/kotlinmvp/mvp_base/BasePresenterInterface;", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public interface PresenterInterface extends BasePresenterInterface {
    }

    /* compiled from: DispatchKeyContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/general_task/DispatchKeyContract$ViewInterface;", "Lcom/pudutech/peanut/presenter/mvp_base/BaseViewInterface;", "onDispatchKey", "", TransferTable.COLUMN_KEY, "Lcom/pudutech/peanut/presenter/general_task/DispatchKeyContract$KEY;", NotificationCompat.CATEGORY_MESSAGE, "", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public interface ViewInterface extends BaseViewInterface {
        boolean onDispatchKey(KEY key, String msg);
    }
}
