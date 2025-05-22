package com.pudutech.bumblebee.robot_ui.track.task;

import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.business.peripherals_task.pallet_task.PalletTask;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.delivery_task.TrayModel;
import com.pudutech.bumblebee.presenter.performance.MovePerformance;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CallFromType;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.TableTaskManager;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.PalletCountHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.bumblebee.robot_ui.track.TrackScene;
import com.pudutech.bumblebee.robot_ui.util.RobotSpeedUtil;
import com.pudutech.mpmodule.utils.AppCommonUtil;
import com.pudutech.robot.module.report.TrackingReportManager;
import com.pudutech.robot.module.report.track2.BaseMoveTrackTask;
import com.pudutech.robot.module.report.track2.DeliverKt;
import com.pudutech.robot.module.report.track2.DeliverTask;
import com.pudutech.robot.module.report.track2.TrackKey;
import com.pudutech.robot.module.report.track2.TrackType;
import com.pudutech.robot.module.report.track2.TrayState;
import com.pudutech.robot.module.report.track2.WakeUpType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeliveryTrack.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004J \u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0014\u001a\u00020\rH\u0016J\u0016\u0010\u0015\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0017J\b\u0010\u0018\u001a\u00020\rH\u0016J\b\u0010\u0019\u001a\u00020\rH\u0016J\b\u0010\u001a\u001a\u00020\rH\u0016J\u000e\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u001cJ\b\u0010\u001d\u001a\u00020\rH\u0016J\u0018\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u0011H\u0016J\u0018\u0010!\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u0011H\u0016J\b\u0010$\u001a\u00020\rH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/track/task/DeliveryTrack;", "Lcom/pudutech/bumblebee/robot_ui/track/task/ISportTrack;", "()V", "TAG", "", "pageKey", "<set-?>", "sessionId", "getSessionId", "()Ljava/lang/String;", "task", "Lcom/pudutech/robot/module/report/track2/DeliverTask;", "onArrive", "", "destination", "onCreateTask", "mode", "", "type", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CallFromType;", "onEmergencyStop", "onFinishOne", "cause", "Lcom/pudutech/robot/module/report/track2/BaseMoveTrackTask$FinishOneCause;", "onMove", "onPause", "onStartScheduling", "onStop", "Lcom/pudutech/robot/module/report/track2/BaseMoveTrackTask$StopCause;", "onStopScheduling", "onTouch", TrackKey.TOUCH_TYPE, TrackKey.TOUCH_STATUS, "onTray", TrackKey.TRAY_SENSOR_COUNT, TrackKey.TRAY_SENSOR_STATUS, "onWakeup", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class DeliveryTrack implements ISportTrack {
    private static DeliverTask task;
    public static final DeliveryTrack INSTANCE = new DeliveryTrack();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static String sessionId = "";
    private static String pageKey = "";

    private DeliveryTrack() {
    }

    public final String getSessionId() {
        return sessionId;
    }

    public static /* synthetic */ void onCreateTask$default(DeliveryTrack deliveryTrack, int i, String str, CallFromType callFromType, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            callFromType = CallFromType.DEFAULT;
        }
        deliveryTrack.onCreateTask(i, str, callFromType);
    }

    public final void onCreateTask(int mode, String pageKey2, CallFromType type) {
        int i;
        Intrinsics.checkParameterIsNotNull(pageKey2, "pageKey");
        Intrinsics.checkParameterIsNotNull(type, "type");
        sessionId = TrackScene.INSTANCE.createOrGetSession(TrackType.DELIVERY, true);
        ArrayList arrayList = new ArrayList();
        int count = PalletCountHelper.INSTANCE.getCount();
        for (int i2 = 0; i2 < count; i2++) {
            arrayList.add(new TrayState(i2, PalletCountHelper.INSTANCE.isPalletEnable(i2)));
        }
        TrackingReportManager trackingReportManager = TrackingReportManager.INSTANCE;
        int allDestinations = TableTaskManager.INSTANCE.getAllDestinations();
        ArrayList<TrayModel> allTask = TableTaskManager.INSTANCE.getAllTask();
        if ((allTask instanceof Collection) && allTask.isEmpty()) {
            i = 0;
        } else {
            Iterator<T> it = allTask.iterator();
            i = 0;
            while (it.hasNext()) {
                if ((!((TrayModel) it.next()).getAllDestinations().isEmpty()) && (i = i + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        task = DeliverKt.newDeliverTask(trackingReportManager, new DeliverTask.BaseDeliverConfig.BellaDeliverConfig(mode, allDestinations, i, TableTaskManager.INSTANCE.getTaskInfo(), TableTaskManager.INSTANCE.getMovePerformance() == MovePerformance.STEADY, AppCommonUtil.isOpenMusicSwitch(), AppCommonUtil.isOpenMusicSwitch(), TtsVoiceHelper.INSTANCE.checkTtsOpenType(RobotContext.INSTANCE.getContext(), mode == 3 ? TtsVoiceHelper.TtsVoiceType.SPECIAL_MODE_ARRIVE : TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE) == TtsVoiceHelper.TtsVoiceOpenType.OPEN, Constans.INSTANCE.getDeliverFaceSwitch(), Float.parseFloat(RobotSpeedUtil.INSTANCE.getDeliverSpeedLevel(RobotContext.INSTANCE.getContext())), BusinessSetting.INSTANCE.getDelayAutoFinishSwitch(), BusinessSetting.INSTANCE.getArrivalLoopVoiceDelayTime_ms(), BusinessSetting.INSTANCE.getCallFreezeTime_ms(), !PalletTask.INSTANCE.isMute(), Constans.INSTANCE.getPalletAlarmSwitch(), PalletCountHelper.INSTANCE.getCount(), arrayList, SpUtils.get(RobotContext.INSTANCE.getContext(), "key_interaction_switch", false), Constans.INSTANCE.getDeliverExitSwitch(), SpUtils.get(RobotContext.INSTANCE.getContext(), "key_spaces_mode_select_music", "").length() > 0, SpUtils.get(RobotContext.INSTANCE.getContext(), "key_spaces_mode_select_arrive_voice", "").length() > 0, pageKey2, type.getType()), sessionId);
        pageKey = pageKey2;
        VoiceInteractionTrack.INSTANCE.onCreateTask(pageKey2, sessionId);
        DeliverTask deliverTask = task;
        if (deliverTask != null) {
            deliverTask.onStart();
        }
    }

    public final void onArrive(String destination) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        DeliverTask deliverTask = task;
        if (deliverTask != null) {
            deliverTask.onArrive(destination);
        }
    }

    public final void onFinishOne(String destination, BaseMoveTrackTask.FinishOneCause cause) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(cause, "cause");
        DeliverTask deliverTask = task;
        if (deliverTask != null) {
            deliverTask.onFinishOne(destination, cause);
        }
    }

    public final void onStop(BaseMoveTrackTask.StopCause cause) {
        Intrinsics.checkParameterIsNotNull(cause, "cause");
        DeliverTask deliverTask = task;
        if (deliverTask != null) {
            deliverTask.onStop(cause);
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.track.task.ISportTrack
    public void onPause() {
        DeliverTask deliverTask = task;
        if (deliverTask != null) {
            deliverTask.onPause();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.track.task.ISportTrack
    public void onMove() {
        DeliverTask deliverTask = task;
        if (deliverTask != null) {
            deliverTask.onMove();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.track.task.ISportTrack
    public void onStartScheduling() {
        Pdlog.m3273d(TAG, "onStartScheduling: 调度状态不需要上传");
    }

    @Override // com.pudutech.bumblebee.robot_ui.track.task.ISportTrack
    public void onStopScheduling() {
        Pdlog.m3273d(TAG, "onStopScheduling: 调度状态不需要上传");
    }

    @Override // com.pudutech.bumblebee.robot_ui.track.task.ISportTrack
    public void onEmergencyStop() {
        PeripheralsTrack.INSTANCE.onEmergencyStopBtnPeripherals(0, pageKey, sessionId);
    }

    @Override // com.pudutech.bumblebee.robot_ui.track.task.ISportTrack
    public void onTouch(int touch_type, int touch_status) {
        PeripheralsTrack.INSTANCE.onTouchPeripherals(touch_type, touch_status, pageKey, sessionId);
    }

    @Override // com.pudutech.bumblebee.robot_ui.track.task.ISportTrack
    public void onTray(int tray_sensor_count, int tray_sensor_status) {
        PeripheralsTrack.INSTANCE.onTrayPeripherals(tray_sensor_count, tray_sensor_status, pageKey, sessionId);
    }

    @Override // com.pudutech.bumblebee.robot_ui.track.task.ISportTrack
    public void onWakeup() {
        VoiceInteractionTrack.INSTANCE.onWakeup(WakeUpType.VOICE);
    }
}
