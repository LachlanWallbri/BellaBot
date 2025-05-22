package com.pudutech.bumblebee.presenter.general_task;

import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.business.peripherals_task.qr_scan_task.QrScanEvent;
import com.pudutech.bumblebee.business.peripherals_task.qr_scan_task.QrScanTaskListener;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.general_task.DispatchKeyContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager;
import com.pudutech.bumblebee.presenter.robot_open_task.listener.RemoteCommandListener;
import com.pudutech.bumblebee.presenter.utils.SoundPoolVoiceUtil;
import com.pudutech.robot.opensdk.bean.ActionCommand;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DispatchKeyPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0007H\u0016J\b\u0010\u0013\u001a\u00020\u000fH\u0014J\b\u0010\u0014\u001a\u00020\u000fH\u0014R\u0014\u0010\u0006\u001a\u00020\u0007X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/general_task/DispatchKeyPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/general_task/DispatchKeyContract$ViewInterface;", "Lcom/pudutech/bumblebee/business/peripherals_task/qr_scan_task/QrScanTaskListener;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/listener/RemoteCommandListener;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "onAction", "", "action", "Lcom/pudutech/robot/opensdk/bean/ActionCommand;", "onQrScanEvent", "", "event", "Lcom/pudutech/bumblebee/business/peripherals_task/qr_scan_task/QrScanEvent;", NotificationCompat.CATEGORY_MESSAGE, "onViewAttach", "onViewRemoved", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DispatchKeyPresenter extends BaseOneViewPresenter<DispatchKeyContract.ViewInterface> implements QrScanTaskListener, RemoteCommandListener {
    private final String TAG = "DispatchKeyPresenter";

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ActionCommand.values().length];

        static {
            $EnumSwitchMapping$0[ActionCommand.START.ordinal()] = 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewAttach() {
        super.onViewAttach();
        DispatchKeyPresenter dispatchKeyPresenter = this;
        Peripherals.INSTANCE.getQrScanTask().addListener(dispatchKeyPresenter);
        RobotOpenManager.INSTANCE.getRemoteCommandListener().addListener(dispatchKeyPresenter);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewRemoved() {
        super.onViewRemoved();
        DispatchKeyPresenter dispatchKeyPresenter = this;
        Peripherals.INSTANCE.getQrScanTask().removeListener(dispatchKeyPresenter);
        RobotOpenManager.INSTANCE.getRemoteCommandListener().removeListener(dispatchKeyPresenter);
    }

    @Override // com.pudutech.bumblebee.business.peripherals_task.qr_scan_task.QrScanTaskListener
    public void onQrScanEvent(QrScanEvent event, final String msg) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (QrScanEvent.EMPLOYEES_NO != event) {
            return;
        }
        Pdlog.m3273d(getTAG(), "onQrScanEvent : event = " + event + "; msg = " + msg + "; ");
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.general_task.DispatchKeyPresenter$onQrScanEvent$1
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
                DispatchKeyContract.ViewInterface theView;
                theView = DispatchKeyPresenter.this.getTheView();
                Boolean valueOf = theView != null ? Boolean.valueOf(theView.onDispatchKey(DispatchKeyContract.KEY.QR_EMPLOYEES, msg)) : null;
                if (Intrinsics.areEqual((Object) valueOf, (Object) true)) {
                    SoundPoolVoiceUtil.INSTANCE.play(SoundPoolVoiceUtil.Voice.SCAN, BusinessSetting.INSTANCE.getBtnVoice() / 100.0f);
                } else if (Intrinsics.areEqual((Object) valueOf, (Object) false)) {
                    SoundPoolVoiceUtil.INSTANCE.play(SoundPoolVoiceUtil.Voice.SCAN_FAILED, BusinessSetting.INSTANCE.getBtnVoice() / 100.0f);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.robot_open_task.listener.RemoteCommandListener
    public boolean onAction(ActionCommand action) {
        DispatchKeyContract.ViewInterface theView;
        Intrinsics.checkParameterIsNotNull(action, "action");
        Pdlog.m3273d(getTAG(), "onAction action=" + action);
        DispatchKeyContract.KEY key = WhenMappings.$EnumSwitchMapping$0[action.ordinal()] != 1 ? null : DispatchKeyContract.KEY.REMOTE_START;
        if (key == null || (theView = getTheView()) == null) {
            return false;
        }
        return theView.onDispatchKey(key, "");
    }
}
