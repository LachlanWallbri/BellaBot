package com.pudutech.bumblebee.robot_ui.track.task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.track.TrackScene;
import com.pudutech.bumblebee.robot_ui.util.RobotSpeedUtil;
import com.pudutech.robot.module.report.TrackingReportManager;
import com.pudutech.robot.module.report.track2.BaseGoDishTaskConfig;
import com.pudutech.robot.module.report.track2.BaseMoveTrackTask;
import com.pudutech.robot.module.report.track2.GoDishwashingRoomTask;
import com.pudutech.robot.module.report.track2.Recycler_go_dish_washing_roomKt;
import com.pudutech.robot.module.report.track2.TrackKey;
import com.pudutech.robot.module.report.track2.TrackType;
import com.pudutech.robot.module.report.track2.WakeUpType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GoDishWashTrack.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\rJ\u0018\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0004J\b\u0010\u000f\u001a\u00020\rH\u0016J\b\u0010\u0010\u001a\u00020\rH\u0016J\b\u0010\u0011\u001a\u00020\rH\u0016J\b\u0010\u0012\u001a\u00020\rH\u0016J\u000e\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u00020\rH\u0016J\u0018\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0016J\u0018\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u0019H\u0016J\b\u0010\u001e\u001a\u00020\rH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/track/task/GoDishWashTrack;", "Lcom/pudutech/bumblebee/robot_ui/track/task/ISportTrack;", "()V", "TAG", "", "pageKey", "<set-?>", "sessionId", "getSessionId", "()Ljava/lang/String;", "task", "Lcom/pudutech/robot/module/report/track2/GoDishwashingRoomTask;", "onArrive", "", "onCreateTask", "onEmergencyStop", "onMove", "onPause", "onStartScheduling", "onStop", "cause", "Lcom/pudutech/robot/module/report/track2/BaseMoveTrackTask$StopCause;", "onStopScheduling", "onTouch", TrackKey.TOUCH_TYPE, "", TrackKey.TOUCH_STATUS, "onTray", TrackKey.TRAY_SENSOR_COUNT, TrackKey.TRAY_SENSOR_STATUS, "onWakeup", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class GoDishWashTrack implements ISportTrack {
    private static GoDishwashingRoomTask task;
    public static final GoDishWashTrack INSTANCE = new GoDishWashTrack();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static String sessionId = "";
    private static String pageKey = "";

    private GoDishWashTrack() {
    }

    public final String getSessionId() {
        return sessionId;
    }

    public static /* synthetic */ void onCreateTask$default(GoDishWashTrack goDishWashTrack, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        goDishWashTrack.onCreateTask(str, str2);
    }

    public final void onCreateTask(String pageKey2, String sessionId2) {
        Intrinsics.checkParameterIsNotNull(pageKey2, "pageKey");
        Intrinsics.checkParameterIsNotNull(sessionId2, "sessionId");
        if (sessionId2.length() == 0) {
            sessionId = TrackScene.INSTANCE.createOrGetSession(TrackType.RECYCLER_GO_DISHWASHING_ROOM, true);
        } else {
            sessionId = sessionId2;
        }
        task = Recycler_go_dish_washing_roomKt.newGoDishwashingRoomTask(TrackingReportManager.INSTANCE, sessionId2, new BaseGoDishTaskConfig.BellaGoDishTaskConfig(Float.parseFloat(RobotSpeedUtil.INSTANCE.getRecyclingSpeedLevel(RobotContext.INSTANCE.getContext())), Constans.INSTANCE.getRecycleExitSwitch(), Constans.INSTANCE.isRecyclePlateFace(), pageKey2));
        pageKey = pageKey2;
        VoiceInteractionTrack.INSTANCE.onCreateTask(pageKey2, sessionId2);
        GoDishwashingRoomTask goDishwashingRoomTask = task;
        if (goDishwashingRoomTask != null) {
            goDishwashingRoomTask.onStart();
        }
    }

    public final void onArrive() {
        GoDishwashingRoomTask goDishwashingRoomTask = task;
        if (goDishwashingRoomTask != null) {
            goDishwashingRoomTask.onArrive();
        }
    }

    public final void onStop(BaseMoveTrackTask.StopCause cause) {
        Intrinsics.checkParameterIsNotNull(cause, "cause");
        GoDishwashingRoomTask goDishwashingRoomTask = task;
        if (goDishwashingRoomTask != null) {
            goDishwashingRoomTask.onStop(cause);
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.track.task.ISportTrack
    public void onPause() {
        GoDishwashingRoomTask goDishwashingRoomTask = task;
        if (goDishwashingRoomTask != null) {
            goDishwashingRoomTask.onPause();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.track.task.ISportTrack
    public void onMove() {
        GoDishwashingRoomTask goDishwashingRoomTask = task;
        if (goDishwashingRoomTask != null) {
            goDishwashingRoomTask.onMove();
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
