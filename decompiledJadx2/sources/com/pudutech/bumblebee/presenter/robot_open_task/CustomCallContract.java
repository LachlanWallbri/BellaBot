package com.pudutech.bumblebee.presenter.robot_open_task;

import androidx.core.app.NotificationCompat;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.mvp_base.BaseViewInterface;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallTargetBean;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.ICustomCallBean;
import com.pudutech.robot.opensdk.bean.pub.CustomCallOperationType;
import com.pudutech.robot.opensdk.bean.pub.CustomCallState;
import kotlin.Metadata;

/* compiled from: CustomCallContract.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/CustomCallContract;", "", "CustomCallBodyType", "PresenterInterface", "ViewInterface", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface CustomCallContract {

    /* compiled from: CustomCallContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/CustomCallContract$CustomCallBodyType;", "", "(Ljava/lang/String;I)V", "img", "qrcode", "video", "callConfirm", NotificationCompat.CATEGORY_CALL, "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum CustomCallBodyType {
        img,
        qrcode,
        video,
        callConfirm,
        call
    }

    /* compiled from: CustomCallContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/CustomCallContract$PresenterInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BasePresenterInterface;", "notificationCustomCall", "", "state", "Lcom/pudutech/robot/opensdk/bean/pub/CustomCallState;", "type", "Lcom/pudutech/robot/opensdk/bean/pub/CustomCallOperationType;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface PresenterInterface extends BasePresenterInterface {
        void notificationCustomCall(CustomCallState state, CustomCallOperationType type);
    }

    /* compiled from: CustomCallContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/CustomCallContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseViewInterface;", "onCall", "", "task", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallTargetBean;", "onCancel", "onComplete", "onContent", AIUIConstant.KEY_CONTENT, "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/ICustomCallBean;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface ViewInterface extends BaseViewInterface {
        void onCall(CustomCallTargetBean task);

        void onCancel();

        void onComplete(CustomCallTargetBean task);

        void onContent(ICustomCallBean content);
    }
}
