package com.pudutech.bumblebee.robot_ui.track.task;

import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.presenter.cruise_task.CruiseConfig;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.bumblebee.robot_ui.track.TrackScene;
import com.pudutech.bumblebee.robot_ui.util.RobotSpeedUtil;
import com.pudutech.mpmodule.utils.AppCommonUtil;
import com.pudutech.robot.module.report.TrackingReportManager;
import com.pudutech.robot.module.report.track2.BaseCruiseConfig;
import com.pudutech.robot.module.report.track2.BaseMoveTrackTask;
import com.pudutech.robot.module.report.track2.CruiseKt;
import com.pudutech.robot.module.report.track2.CruiseTask2;
import com.pudutech.robot.module.report.track2.TrackKey;
import com.pudutech.robot.module.report.track2.TrackType;
import com.pudutech.robot.module.report.track2.WakeUpType;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CruiseTrack.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004J,\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u0004J\b\u0010\u0016\u001a\u00020\rH\u0016J\u0016\u0010\u0017\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0019J\b\u0010\u001a\u001a\u00020\rH\u0016J\b\u0010\u001b\u001a\u00020\rH\u0016J\b\u0010\u001c\u001a\u00020\rH\u0016J\u000e\u0010\u001d\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u001eJ\b\u0010\u001f\u001a\u00020\rH\u0016J\u0018\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u0011H\u0016J\u0018\u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020\u00112\u0006\u0010%\u001a\u00020\u0011H\u0016J\b\u0010&\u001a\u00020\rH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/track/task/CruiseTrack;", "Lcom/pudutech/bumblebee/robot_ui/track/task/ISportTrack;", "()V", "TAG", "", "pageKey", "<set-?>", "sessionId", "getSessionId", "()Ljava/lang/String;", "task", "Lcom/pudutech/robot/module/report/track2/CruiseTask2;", "onArrive", "", "destination", "onCreateTask", "cruisePathId", "", "stayPointList", "", "isSteady", "", "onEmergencyStop", "onFinishOne", "cause", "Lcom/pudutech/robot/module/report/track2/BaseMoveTrackTask$FinishOneCause;", "onMove", "onPause", "onStartScheduling", "onStop", "Lcom/pudutech/robot/module/report/track2/BaseMoveTrackTask$StopCause;", "onStopScheduling", "onTouch", TrackKey.TOUCH_TYPE, TrackKey.TOUCH_STATUS, "onTray", TrackKey.TRAY_SENSOR_COUNT, TrackKey.TRAY_SENSOR_STATUS, "onWakeup", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CruiseTrack implements ISportTrack {
    private static CruiseTask2 task;
    public static final CruiseTrack INSTANCE = new CruiseTrack();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static String sessionId = "";
    private static String pageKey = "";

    private CruiseTrack() {
    }

    public final String getSessionId() {
        return sessionId;
    }

    public final void onCreateTask(int cruisePathId, List<String> stayPointList, boolean isSteady, String pageKey2) {
        Intrinsics.checkParameterIsNotNull(stayPointList, "stayPointList");
        Intrinsics.checkParameterIsNotNull(pageKey2, "pageKey");
        sessionId = TrackScene.INSTANCE.createOrGetSession(TrackType.CRUISE, true);
        task = CruiseKt.newCruiseTask2(TrackingReportManager.INSTANCE, new BaseCruiseConfig.BellCruiseConfig(AppCommonUtil.isOpenMusicSwitch(), Constans.INSTANCE.isOpenCruiseFace(), Float.parseFloat(RobotSpeedUtil.INSTANCE.getCruiseSpeedLevel(RobotContext.INSTANCE.getContext())), SpUtils.get(RobotContext.INSTANCE.getContext(), Constans.KEY_CRUISE_STAY_COPY_WRITE_TYPE, "").length() > 0, CruiseConfig.INSTANCE.getDelayAutoFinish_ms(), TtsVoiceHelper.INSTANCE.checkTtsOpenType(RobotContext.INSTANCE.getContext(), TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE) == TtsVoiceHelper.TtsVoiceOpenType.OPEN, TtsVoiceHelper.INSTANCE.getTimeInterval(TtsVoiceHelper.TtsVoiceType.CRUISE_STAY_TYPE) * 1000, cruisePathId, stayPointList, isSteady, Constans.INSTANCE.getCruiseExitSwitch(), pageKey2), sessionId);
        pageKey = pageKey2;
        VoiceInteractionTrack.INSTANCE.onCreateTask(pageKey2, sessionId);
        CruiseTask2 cruiseTask2 = task;
        if (cruiseTask2 != null) {
            cruiseTask2.onStart();
        }
    }

    public final void onArrive(String destination) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        CruiseTask2 cruiseTask2 = task;
        if (cruiseTask2 != null) {
            cruiseTask2.onArrive(destination);
        }
    }

    public final void onFinishOne(String destination, BaseMoveTrackTask.FinishOneCause cause) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(cause, "cause");
        CruiseTask2 cruiseTask2 = task;
        if (cruiseTask2 != null) {
            cruiseTask2.onFinishOne(destination, cause);
        }
    }

    public final void onStop(BaseMoveTrackTask.StopCause cause) {
        Intrinsics.checkParameterIsNotNull(cause, "cause");
        CruiseTask2 cruiseTask2 = task;
        if (cruiseTask2 != null) {
            cruiseTask2.onStop(cause);
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.track.task.ISportTrack
    public void onPause() {
        CruiseTask2 cruiseTask2 = task;
        if (cruiseTask2 != null) {
            cruiseTask2.onPause();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.track.task.ISportTrack
    public void onMove() {
        CruiseTask2 cruiseTask2 = task;
        if (cruiseTask2 != null) {
            cruiseTask2.onMove();
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
