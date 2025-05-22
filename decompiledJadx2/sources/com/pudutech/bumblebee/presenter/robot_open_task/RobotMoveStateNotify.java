package com.pudutech.bumblebee.presenter.robot_open_task;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.behavior.Behavior;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.core_devices_task.monitor_task.MonitorEvent;
import com.pudutech.bumblebee.business.core_devices_task.monitor_task.MonitorListener;
import com.pudutech.bumblebee.business.core_devices_task.monitor_task.protocol.Error;
import com.pudutech.bumblebee.business.core_devices_task.monitor_task.protocol.Errors;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.business.peripherals_task.qr_scan_task.QrScanEvent;
import com.pudutech.bumblebee.business.peripherals_task.qr_scan_task.QrScanTaskListener;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.utils.SoundPoolVoiceUtil;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveInterfaceDecorator;
import com.pudutech.robot.opensdk.RobotOpenSdk;
import com.pudutech.robot.opensdk.bean.pub.DoorControlCommand;
import com.pudutech.robot.opensdk.bean.pub.PubQrCodeContentDate;
import com.pudutech.robot.opensdk.bean.pub.PubRobotMoveStateData;
import com.pudutech.robot.opensdk.bean.pub.RobotError;
import com.pudutech.robot.opensdk.interf.IBody;
import com.pudutech.robot.opensdk.interf.ICallback;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RobotMoveStateNotify.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032:\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0004j\u0002`\fB\u0005¢\u0006\u0002\u0010\rJ\u0006\u0010\u001b\u001a\u00020\u000bJ\u0006\u0010\u001c\u001a\u00020\u000bJ\u001d\u0010\u001d\u001a\u00020\u000b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u00052\b\u0010\u001f\u001a\u0004\u0018\u00010\tH\u0096\u0002J\u001a\u0010 \u001a\u00020\u000b2\u0010\b\u0002\u0010!\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\"H\u0002J\u0010\u0010$\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020&H\u0016J\u0010\u0010'\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020\u0010H\u0016J\u0018\u0010)\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020*2\u0006\u0010+\u001a\u00020\tH\u0016J\u001a\u0010,\u001a\u00020\u000b2\u0006\u0010-\u001a\u00020\u00102\b\u0010.\u001a\u0004\u0018\u00010\tH\u0002R\u000e\u0010\u000e\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006/"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/RobotMoveStateNotify;", "Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/MonitorListener;", "Lcom/pudutech/bumblebee/business/behavior/Behavior$OnIdleListener;", "Lcom/pudutech/bumblebee/business/peripherals_task/qr_scan_task/QrScanTaskListener;", "Lkotlin/Function2;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "Lkotlin/ParameterName;", "name", "p0", "", "p1", "", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotStateListener;", "()V", "TAG", "enable", "", "getEnable", "()Z", "setEnable", "(Z)V", "isInit", "robotState", "getRobotState", "()Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "setRobotState", "(Lcom/pudutech/mirsdk/aidl/serialize/RobotState;)V", "destroy", "init", "invoke", "state", "description", "notifyRobotState", "errors", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/protocol/Error;", "onEvent", "event", "Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/MonitorEvent;", "onIdle", "boolean", "onQrScanEvent", "Lcom/pudutech/bumblebee/business/peripherals_task/qr_scan_task/QrScanEvent;", NotificationCompat.CATEGORY_MESSAGE, "pudAccessControlRequest", "needOpen", "ext", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RobotMoveStateNotify implements MonitorListener, Behavior.OnIdleListener, QrScanTaskListener, Function2<RobotState, String, Unit> {
    private boolean isInit;
    private final String TAG = "RobotMoveStateNotify";
    private RobotState robotState = RobotState.Idle;
    private boolean enable = true;

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(RobotState robotState, String str) {
        invoke2(robotState, str);
        return Unit.INSTANCE;
    }

    public final RobotState getRobotState() {
        return this.robotState;
    }

    public final void setRobotState(RobotState robotState) {
        Intrinsics.checkParameterIsNotNull(robotState, "<set-?>");
        this.robotState = robotState;
    }

    public final boolean getEnable() {
        return this.enable;
    }

    public final void setEnable(boolean z) {
        this.enable = z;
    }

    private final void pudAccessControlRequest(boolean needOpen, String ext) {
        Pdlog.m3273d(this.TAG, "pudAccessControlRequest needOpen=" + needOpen);
        if (needOpen) {
            RobotOpenManager.INSTANCE.pudDoorControl(DoorControlCommand.OPEN, null, ext);
        } else {
            RobotOpenManager.INSTANCE.pudDoorControl(DoorControlCommand.CLOSE, null, ext);
        }
    }

    public final void init() {
        Pdlog.m3275i(this.TAG, "init=" + this.isInit + " moveAction=" + CoreDevices.INSTANCE.getMoveAction());
        if (this.isInit) {
            return;
        }
        this.isInit = true;
        RobotMoveStateNotify robotMoveStateNotify = this;
        CoreDevices.INSTANCE.getMonitorTask().addListener(robotMoveStateNotify);
        RobotMoveInterfaceDecorator moveAction = CoreDevices.INSTANCE.getMoveAction();
        if (moveAction != null) {
            moveAction.addOnStateChangeListeners(this);
        }
        Behavior.INSTANCE.getOnIdleListeners().add(this);
        Peripherals.INSTANCE.getQrScanTask().addListener(robotMoveStateNotify);
    }

    public final void destroy() {
        RobotMoveStateNotify robotMoveStateNotify = this;
        CoreDevices.INSTANCE.getMonitorTask().removeListener(robotMoveStateNotify);
        RobotMoveInterfaceDecorator moveAction = CoreDevices.INSTANCE.getMoveAction();
        if (moveAction != null) {
            moveAction.removeOnStateChangeListeners(this);
        }
        Behavior.INSTANCE.getOnIdleListeners().remove(this);
        Peripherals.INSTANCE.getQrScanTask().removeListener(robotMoveStateNotify);
        this.isInit = false;
    }

    @Override // com.pudutech.bumblebee.business.core_devices_task.monitor_task.MonitorListener
    public void onEvent(MonitorEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Pdlog.m3273d(this.TAG, "onEvent : event = " + event + "; ");
        if (event.getIsError()) {
            this.robotState = RobotState.Error;
            Errors errors = event.getErrors();
            notifyRobotState(errors != null ? errors.list : null);
        }
    }

    @Override // com.pudutech.bumblebee.business.behavior.Behavior.OnIdleListener
    public void onIdle(boolean r7) {
        if (!r7 || this.robotState == RobotState.Idle) {
            return;
        }
        Pdlog.m3273d(this.TAG, "onIdle : boolean = " + r7 + "; ");
        this.robotState = RobotState.Idle;
        notifyRobotState$default(this, null, 1, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void notifyRobotState$default(RobotMoveStateNotify robotMoveStateNotify, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = (ArrayList) null;
        }
        robotMoveStateNotify.notifyRobotState(arrayList);
    }

    private final void notifyRobotState(ArrayList<Error> errors) {
        if (this.enable) {
            ArrayList arrayList = (ArrayList) null;
            if (errors != null) {
                arrayList = new ArrayList();
                for (Error error : errors) {
                    String str = error.error_type;
                    Intrinsics.checkExpressionValueIsNotNull(str, "it.error_type");
                    String str2 = error.level;
                    Intrinsics.checkExpressionValueIsNotNull(str2, "it.level");
                    String str3 = error.detail;
                    Intrinsics.checkExpressionValueIsNotNull(str3, "it.detail");
                    arrayList.add(new RobotError(str, str2, str3));
                }
            }
            RobotOpenSdk.INSTANCE.publishMsg(new PubRobotMoveStateData(this.robotState.name(), arrayList), new ICallback() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotMoveStateNotify$notifyRobotState$2
                @Override // com.pudutech.robot.opensdk.interf.ICallback
                public void onFailed(Exception e) {
                    String str4;
                    Intrinsics.checkParameterIsNotNull(e, "e");
                    str4 = RobotMoveStateNotify.this.TAG;
                    Pdlog.m3274e(str4, "notifyRobotState onFailed : " + Log.getStackTraceString(e));
                }

                @Override // com.pudutech.robot.opensdk.interf.ICallback
                public void onSuccess(IBody result) {
                    String str4;
                    str4 = RobotMoveStateNotify.this.TAG;
                    Pdlog.m3273d(str4, "notifyRobotState onSuccess : result = " + result + "; ");
                }
            });
        }
    }

    @Override // com.pudutech.bumblebee.business.peripherals_task.qr_scan_task.QrScanTaskListener
    public void onQrScanEvent(QrScanEvent event, String msg) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (event == QrScanEvent.OPEN_MSG) {
            Pdlog.m3273d(this.TAG, "onQrScanEvent : event = " + event + "; msg = " + msg + "; ");
            SoundPoolVoiceUtil.INSTANCE.play(SoundPoolVoiceUtil.Voice.SCAN, ((float) BusinessSetting.INSTANCE.getBtnVoice()) / 100.0f);
            RobotOpenSdk.INSTANCE.publishMsg(new PubQrCodeContentDate(msg), new ICallback() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotMoveStateNotify$onQrScanEvent$1
                @Override // com.pudutech.robot.opensdk.interf.ICallback
                public void onFailed(Exception e) {
                    String str;
                    Intrinsics.checkParameterIsNotNull(e, "e");
                    str = RobotMoveStateNotify.this.TAG;
                    Pdlog.m3274e(str, "onQrScanEvent onFailed : " + Log.getStackTraceString(e));
                }

                @Override // com.pudutech.robot.opensdk.interf.ICallback
                public void onSuccess(IBody result) {
                    String str;
                    str = RobotMoveStateNotify.this.TAG;
                    Pdlog.m3273d(str, "onQrScanEvent onSuccess : result = " + result + "; ");
                }
            });
        }
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public void invoke2(RobotState state, String description) {
        RobotState robotState;
        Pdlog.m3273d(this.TAG, "onStateChange : state = " + state + "; description = " + description + "; ");
        if (state == RobotState.Error || state == RobotState.Resume || state == (robotState = this.robotState)) {
            return;
        }
        if (robotState == RobotState.Idle && state == RobotState.Pause) {
            return;
        }
        if (state == null) {
            state = RobotState.Idle;
        }
        this.robotState = state;
        notifyRobotState$default(this, null, 1, null);
    }
}
