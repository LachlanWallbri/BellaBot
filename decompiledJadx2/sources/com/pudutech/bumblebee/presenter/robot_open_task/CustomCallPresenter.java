package com.pudutech.bumblebee.presenter.robot_open_task;

import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.robot_open_task.CustomCallContract;
import com.pudutech.bumblebee.presenter.robot_open_task.CustomCallPresenter;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallImgBean;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallNotificationBean;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallQrcodeBean;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallTargetBean;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallVideoBean;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.ICustomCallBean;
import com.pudutech.bumblebee.presenter.robot_open_task.listener.CustomCallAction;
import com.pudutech.bumblebee.presenter.robot_open_task.listener.CustomCallListener;
import com.pudutech.robot.opensdk.bean.CustomCallBody;
import com.pudutech.robot.opensdk.bean.CustomCallCompleteBody;
import com.pudutech.robot.opensdk.bean.CustomCallContentBody;
import com.pudutech.robot.opensdk.bean.CustomCallContentData;
import com.pudutech.robot.opensdk.bean.pub.CustomCallOperationType;
import com.pudutech.robot.opensdk.bean.pub.CustomCallState;
import com.pudutech.robot.opensdk.interf.IBody;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomCallPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000=\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0010H\u0014J\b\u0010\u0016\u001a\u00020\u0010H\u0014R\u0010\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\"\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\b\u001a\u0004\u0018\u00010\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/CustomCallPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/CustomCallContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/CustomCallContract$PresenterInterface;", "()V", "customCallListener", "com/pudutech/bumblebee/presenter/robot_open_task/CustomCallPresenter$customCallListener$1", "Lcom/pudutech/bumblebee/presenter/robot_open_task/CustomCallPresenter$customCallListener$1;", "<set-?>", "", "destination", "getDestination", "()Ljava/lang/String;", "notificationBean", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallNotificationBean;", "notificationCustomCall", "", "state", "Lcom/pudutech/robot/opensdk/bean/pub/CustomCallState;", "type", "Lcom/pudutech/robot/opensdk/bean/pub/CustomCallOperationType;", "onViewAttach", "onViewRemoved", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CustomCallPresenter extends BaseOneViewPresenter<CustomCallContract.ViewInterface> implements CustomCallContract.PresenterInterface {
    private final CustomCallPresenter$customCallListener$1 customCallListener = new CustomCallListener() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.CustomCallPresenter$customCallListener$1
        @Override // com.pudutech.bumblebee.presenter.robot_open_task.listener.CustomCallListener
        public void onAction(CustomCallAction ation, IBody content, Function1<? super Boolean, Unit> callback) {
            CustomCallContract.ViewInterface theView;
            CustomCallContract.ViewInterface theView2;
            CustomCallNotificationBean customCallNotificationBean;
            CustomCallNotificationBean customCallNotificationBean2;
            CustomCallQrcodeBean customCallQrcodeBean;
            String destination;
            CustomCallContract.ViewInterface theView3;
            CustomCallNotificationBean customCallNotificationBean3;
            CustomCallContract.ViewInterface theView4;
            CustomCallContract.ViewInterface theView5;
            Intrinsics.checkParameterIsNotNull(ation, "ation");
            Intrinsics.checkParameterIsNotNull(content, "content");
            int i = CustomCallPresenter.WhenMappings.$EnumSwitchMapping$0[ation.ordinal()];
            int i2 = 0;
            if (i == 1) {
                CustomCallBody customCallBody = (CustomCallBody) content;
                CustomCallVideoBean customCallVideoBean = (ICustomCallBean) null;
                CustomCallContentData contentData = customCallBody.getContentData();
                CustomCallPresenter.this.destination = customCallBody.getDestination().getName();
                String contentType = customCallBody.getContentType();
                if (Intrinsics.areEqual(contentType, CustomCallContract.CustomCallBodyType.img.name())) {
                    customCallVideoBean = new CustomCallImgBean(contentData != null ? contentData.getUrls() : null, contentData != null ? contentData.getSwitchTime() : null, contentData != null ? contentData.getCancelBtnTime() : null, contentData != null ? contentData.getShowTimeout() : null);
                } else if (Intrinsics.areEqual(contentType, CustomCallContract.CustomCallBodyType.qrcode.name())) {
                    customCallVideoBean = new CustomCallQrcodeBean(customCallBody.getDestination().getName(), contentData != null ? contentData.getQrcode() : null, contentData != null ? contentData.getText() : null, null);
                } else if (Intrinsics.areEqual(contentType, CustomCallContract.CustomCallBodyType.video.name())) {
                    customCallVideoBean = new CustomCallVideoBean(contentData != null ? contentData.getUrls() : null, contentData != null ? contentData.getPlayCount() : null, contentData != null ? contentData.getCancelBtnTime() : null);
                } else if (Intrinsics.areEqual(contentType, CustomCallContract.CustomCallBodyType.callConfirm.name())) {
                    i2 = 2;
                } else if (Intrinsics.areEqual(contentType, CustomCallContract.CustomCallBodyType.call.name())) {
                    i2 = 1;
                }
                CustomCallTargetBean customCallTargetBean = new CustomCallTargetBean(customCallBody.getDestination().getName(), i2, customCallVideoBean);
                theView = CustomCallPresenter.this.getTheView();
                if (theView != null) {
                    theView.onCall(customCallTargetBean);
                    return;
                }
                return;
            }
            if (i == 2) {
                theView2 = CustomCallPresenter.this.getTheView();
                if (theView2 != null) {
                    theView2.onCancel();
                    return;
                }
                return;
            }
            if (i == 3) {
                customCallNotificationBean = CustomCallPresenter.this.notificationBean;
                if ((customCallNotificationBean != null ? customCallNotificationBean.getState() : null) != CustomCallState.Arrived) {
                    if (callback != null) {
                        callback.invoke(false);
                        return;
                    }
                    return;
                }
                CustomCallContentBody customCallContentBody = (CustomCallContentBody) content;
                CustomCallContentData contentData2 = customCallContentBody.getContentData();
                CustomCallVideoBean customCallVideoBean2 = (ICustomCallBean) null;
                String contentType2 = customCallContentBody.getContentType();
                if (Intrinsics.areEqual(contentType2, CustomCallContract.CustomCallBodyType.img.name())) {
                    customCallVideoBean2 = new CustomCallImgBean(contentData2 != null ? contentData2.getUrls() : null, contentData2 != null ? contentData2.getSwitchTime() : null, contentData2 != null ? contentData2.getCancelBtnTime() : null, contentData2 != null ? contentData2.getShowTimeout() : null);
                } else if (Intrinsics.areEqual(contentType2, CustomCallContract.CustomCallBodyType.qrcode.name())) {
                    customCallNotificationBean2 = CustomCallPresenter.this.notificationBean;
                    if (customCallNotificationBean2 == null || (destination = customCallNotificationBean2.getDestination()) == null) {
                        customCallQrcodeBean = null;
                    } else {
                        customCallQrcodeBean = new CustomCallQrcodeBean(destination, contentData2 != null ? contentData2.getQrcode() : null, contentData2 != null ? contentData2.getText() : null, null);
                    }
                    customCallVideoBean2 = customCallQrcodeBean;
                } else if (Intrinsics.areEqual(contentType2, CustomCallContract.CustomCallBodyType.video.name())) {
                    customCallVideoBean2 = new CustomCallVideoBean(contentData2 != null ? contentData2.getUrls() : null, contentData2 != null ? contentData2.getPlayCount() : null, contentData2 != null ? contentData2.getCancelBtnTime() : null);
                }
                if (customCallVideoBean2 == null) {
                    if (callback != null) {
                        callback.invoke(false);
                        return;
                    }
                    return;
                } else {
                    theView3 = CustomCallPresenter.this.getTheView();
                    if (theView3 != null) {
                        theView3.onContent(customCallVideoBean2);
                    }
                    if (callback != null) {
                        callback.invoke(true);
                        return;
                    }
                    return;
                }
            }
            if (i != 4) {
                return;
            }
            customCallNotificationBean3 = CustomCallPresenter.this.notificationBean;
            if ((customCallNotificationBean3 != null ? customCallNotificationBean3.getState() : null) != CustomCallState.Arrived) {
                if (callback != null) {
                    callback.invoke(false);
                    return;
                }
                return;
            }
            CustomCallBody nextCallTask = ((CustomCallCompleteBody) content).getNextCallTask();
            if (nextCallTask == null) {
                theView5 = CustomCallPresenter.this.getTheView();
                if (theView5 != null) {
                    theView5.onComplete(null);
                }
            } else {
                CustomCallVideoBean customCallVideoBean3 = (ICustomCallBean) null;
                CustomCallContentData contentData3 = nextCallTask.getContentData();
                CustomCallPresenter.this.destination = nextCallTask.getDestination().getName();
                String contentType3 = nextCallTask.getContentType();
                if (Intrinsics.areEqual(contentType3, CustomCallContract.CustomCallBodyType.img.name())) {
                    customCallVideoBean3 = new CustomCallImgBean(contentData3 != null ? contentData3.getUrls() : null, contentData3 != null ? contentData3.getSwitchTime() : null, contentData3 != null ? contentData3.getCancelBtnTime() : null, contentData3 != null ? contentData3.getShowTimeout() : null);
                } else if (Intrinsics.areEqual(contentType3, CustomCallContract.CustomCallBodyType.qrcode.name())) {
                    customCallVideoBean3 = new CustomCallQrcodeBean(nextCallTask.getDestination().getName(), contentData3 != null ? contentData3.getQrcode() : null, contentData3 != null ? contentData3.getText() : null, null);
                } else if (Intrinsics.areEqual(contentType3, CustomCallContract.CustomCallBodyType.video.name())) {
                    customCallVideoBean3 = new CustomCallVideoBean(contentData3 != null ? contentData3.getUrls() : null, contentData3 != null ? contentData3.getPlayCount() : null, contentData3 != null ? contentData3.getCancelBtnTime() : null);
                } else if (Intrinsics.areEqual(contentType3, CustomCallContract.CustomCallBodyType.callConfirm.name())) {
                    i2 = 2;
                } else if (Intrinsics.areEqual(contentType3, CustomCallContract.CustomCallBodyType.call.name())) {
                    i2 = 1;
                }
                CustomCallTargetBean customCallTargetBean2 = new CustomCallTargetBean(nextCallTask.getDestination().getName(), i2, customCallVideoBean3);
                theView4 = CustomCallPresenter.this.getTheView();
                if (theView4 != null) {
                    theView4.onComplete(customCallTargetBean2);
                }
            }
            if (callback != null) {
                callback.invoke(true);
            }
        }
    };
    private String destination;
    private CustomCallNotificationBean notificationBean;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[CustomCallAction.values().length];

        static {
            $EnumSwitchMapping$0[CustomCallAction.CALL.ordinal()] = 1;
            $EnumSwitchMapping$0[CustomCallAction.CANCEL.ordinal()] = 2;
            $EnumSwitchMapping$0[CustomCallAction.CONTENT.ordinal()] = 3;
            $EnumSwitchMapping$0[CustomCallAction.COMPLETE.ordinal()] = 4;
        }
    }

    public final String getDestination() {
        return this.destination;
    }

    @Override // com.pudutech.bumblebee.presenter.robot_open_task.CustomCallContract.PresenterInterface
    public void notificationCustomCall(CustomCallState state, CustomCallOperationType type) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(type, "type");
        String str = this.destination;
        if (str != null) {
            this.notificationBean = new CustomCallNotificationBean(str, state, type);
            CustomCallNotificationBean customCallNotificationBean = this.notificationBean;
            if (customCallNotificationBean != null) {
                RobotOpenManager.INSTANCE.notifyCustomCall$module_bumblebee_presenter_robotRelease(customCallNotificationBean);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewAttach() {
        super.onViewAttach();
        RobotOpenManager.INSTANCE.getCustomCallListener().addListener(this.customCallListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewRemoved() {
        super.onViewRemoved();
        RobotOpenManager.INSTANCE.getCustomCallListener().removeListener(this.customCallListener);
    }
}
