package com.pudutech.bumblebee.robot_ui.track.task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.Constant;
import com.pudutech.bumblebee.presenter.delivery_task.TrayModel;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.TableTaskManager;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.bumblebee.robot_ui.track.TrackScene;
import com.pudutech.bumblebee.robot_ui.util.RobotSpeedUtil;
import com.pudutech.robot.module.report.TrackingReportManager;
import com.pudutech.robot.module.report.track2.BaseMoveTrackTask;
import com.pudutech.robot.module.report.track2.BaseRecyclerTaskConfig;
import com.pudutech.robot.module.report.track2.RecyclerKt;
import com.pudutech.robot.module.report.track2.RecyclerTask;
import com.pudutech.robot.module.report.track2.TrackKey;
import com.pudutech.robot.module.report.track2.TrackType;
import com.pudutech.robot.module.report.track2.WakeUpType;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecycleTrack.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004J\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0004J\b\u0010\u0010\u001a\u00020\rH\u0016J\u0016\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0014\u001a\u00020\rH\u0016J\b\u0010\u0015\u001a\u00020\rH\u0016J\b\u0010\u0016\u001a\u00020\rH\u0016J\u000e\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0018J\b\u0010\u0019\u001a\u00020\rH\u0016J\u0018\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cH\u0016J\u0018\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u001cH\u0016J\b\u0010!\u001a\u00020\rH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/track/task/RecycleTrack;", "Lcom/pudutech/bumblebee/robot_ui/track/task/ISportTrack;", "()V", "TAG", "", "pageKey", "<set-?>", "sessionId", "getSessionId", "()Ljava/lang/String;", "task", "Lcom/pudutech/robot/module/report/track2/RecyclerTask;", "onArrive", "", "destination", "onCreateTask", "onEmergencyStop", "onFinishOne", "cause", "Lcom/pudutech/robot/module/report/track2/BaseMoveTrackTask$FinishOneCause;", "onMove", "onPause", "onStartScheduling", "onStop", "Lcom/pudutech/robot/module/report/track2/BaseMoveTrackTask$StopCause;", "onStopScheduling", "onTouch", TrackKey.TOUCH_TYPE, "", TrackKey.TOUCH_STATUS, "onTray", TrackKey.TRAY_SENSOR_COUNT, TrackKey.TRAY_SENSOR_STATUS, "onWakeup", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class RecycleTrack implements ISportTrack {
    private static RecyclerTask task;
    public static final RecycleTrack INSTANCE = new RecycleTrack();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static String sessionId = "";
    private static String pageKey = "";

    private RecycleTrack() {
    }

    public final String getSessionId() {
        return sessionId;
    }

    public final void onCreateTask(String pageKey2) {
        Intrinsics.checkParameterIsNotNull(pageKey2, "pageKey");
        sessionId = TrackScene.INSTANCE.createOrGetSession(TrackType.RECYCLER, true);
        TrackingReportManager trackingReportManager = TrackingReportManager.INSTANCE;
        String str = sessionId;
        float parseFloat = Float.parseFloat(RobotSpeedUtil.INSTANCE.getRecyclingSpeedLevel(RobotContext.INSTANCE.getContext()));
        boolean isOrderRecycle = Constans.INSTANCE.isOrderRecycle();
        boolean isRepeatLastRecycle = Constans.INSTANCE.isRepeatLastRecycle();
        boolean z = TtsVoiceHelper.INSTANCE.checkTtsOpenType(RobotContext.INSTANCE.getContext(), TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_ARRIVE) == TtsVoiceHelper.TtsVoiceOpenType.OPEN;
        boolean z2 = TtsVoiceHelper.INSTANCE.checkTtsOpenType(RobotContext.INSTANCE.getContext(), TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_LEAVE) == TtsVoiceHelper.TtsVoiceOpenType.OPEN;
        boolean z3 = TtsVoiceHelper.INSTANCE.checkTtsOpenType(RobotContext.INSTANCE.getContext(), TtsVoiceHelper.TtsVoiceType.RECYCLE_POINT_ARRIVE) == TtsVoiceHelper.TtsVoiceOpenType.OPEN;
        boolean isRecycleAutoComplete = Constant.INSTANCE.isRecycleAutoComplete();
        long recycleCompleteTime = Constant.INSTANCE.getRecycleCompleteTime();
        long recyclePauseTime = Constant.INSTANCE.getRecyclePauseTime();
        ArrayList<TrayModel> allTask = TableTaskManager.INSTANCE.getAllTask();
        ArrayList arrayList = new ArrayList();
        Iterator it = allTask.iterator();
        while (it.hasNext()) {
            TrayModel trayModel = (TrayModel) it.next();
            Iterator it2 = it;
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(trayModel.getAllDestinations().get(0).getDestination());
            CollectionsKt.addAll(arrayList, arrayList2);
            it = it2;
        }
        task = RecyclerKt.newRecyclerTask(trackingReportManager, str, new BaseRecyclerTaskConfig.BellaRecyclerTaskConfig(parseFloat, isOrderRecycle, isRepeatLastRecycle, z, z2, z3, isRecycleAutoComplete, recycleCompleteTime, recyclePauseTime, arrayList, Constans.INSTANCE.getRecycleExitSwitch(), Constans.INSTANCE.isRecyclePlateFace(), pageKey2));
        pageKey = pageKey2;
        VoiceInteractionTrack.INSTANCE.onCreateTask(pageKey2, sessionId);
        RecyclerTask recyclerTask = task;
        if (recyclerTask != null) {
            recyclerTask.onStart();
        }
    }

    public final void onArrive(String destination) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        RecyclerTask recyclerTask = task;
        if (recyclerTask != null) {
            recyclerTask.onArrive(destination);
        }
    }

    public final void onStop(BaseMoveTrackTask.StopCause cause) {
        Intrinsics.checkParameterIsNotNull(cause, "cause");
        RecyclerTask recyclerTask = task;
        if (recyclerTask != null) {
            recyclerTask.onStop(cause);
        }
    }

    public final void onFinishOne(String destination, BaseMoveTrackTask.FinishOneCause cause) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(cause, "cause");
        RecyclerTask recyclerTask = task;
        if (recyclerTask != null) {
            recyclerTask.onFinishOne(destination, cause);
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.track.task.ISportTrack
    public void onPause() {
        RecyclerTask recyclerTask = task;
        if (recyclerTask != null) {
            recyclerTask.onPause();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.track.task.ISportTrack
    public void onMove() {
        RecyclerTask recyclerTask = task;
        if (recyclerTask != null) {
            recyclerTask.onMove();
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
