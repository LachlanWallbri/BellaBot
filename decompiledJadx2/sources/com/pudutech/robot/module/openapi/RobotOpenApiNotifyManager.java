package com.pudutech.robot.module.openapi;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.robot.opensdk.RobotOpenSdk;
import com.pudutech.robot.opensdk.bean.pub.DeliveryTraysTaskState;
import com.pudutech.robot.opensdk.bean.pub.PubDeliveryMode;
import com.pudutech.robot.opensdk.bean.pub.PubDeliveryTaskStateData;
import com.pudutech.robot.opensdk.bean.pub.PubRobotMoveStateData;
import com.pudutech.robot.opensdk.bean.pub.PubRobotPoseDate;
import com.pudutech.robot.opensdk.bean.pub.PubRobotPowerData;
import com.pudutech.robot.opensdk.bean.pub.RobotError;
import com.pudutech.robot.opensdk.interf.IBody;
import com.pudutech.robot.opensdk.interf.ICallback;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RobotOpenApiNotifyManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0016\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0013j\b\u0012\u0004\u0012\u00020\u0014`\u0015J*\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00072\u001a\u0010\u0018\u001a\u0016\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0013j\n\u0012\u0004\u0012\u00020\u0019\u0018\u0001`\u0015J\u001e\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\nJ\u0016\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, m3961d2 = {"Lcom/pudutech/robot/module/openapi/RobotOpenApiNotifyManager;", "", "()V", "PoseNotifyMax", "", "PoseNotifyMin", "TAG", "", "lastMoveState", "lastPostA", "", "lastPostX", "lastPostY", "poseNotifyTime", "notifyDeliveryTask", "", "mode", "Lcom/pudutech/robot/opensdk/bean/pub/PubDeliveryMode;", "trays", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/opensdk/bean/pub/DeliveryTraysTaskState;", "Lkotlin/collections/ArrayList;", "notifyMoveState", "state", "error", "Lcom/pudutech/robot/opensdk/bean/pub/RobotError;", "notifyPose", "x", "y", "angle", "notifyPowerChange", "power", "", "chargerState", "module_robot_open_api_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RobotOpenApiNotifyManager {
    private static long poseNotifyTime;
    public static final RobotOpenApiNotifyManager INSTANCE = new RobotOpenApiNotifyManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final long PoseNotifyMin = PoseNotifyMin;
    private static final long PoseNotifyMin = PoseNotifyMin;
    private static final long PoseNotifyMax = 30000;
    private static double lastPostX = -1.0d;
    private static double lastPostY = -1.0d;
    private static double lastPostA = -1.0d;
    private static String lastMoveState = "";

    private RobotOpenApiNotifyManager() {
    }

    public final void notifyPose(double x, double y, double angle) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if ((elapsedRealtime - poseNotifyTime <= PoseNotifyMin || x == lastPostX || y == lastPostY || lastPostA == angle) && elapsedRealtime - poseNotifyTime <= PoseNotifyMax) {
            return;
        }
        RobotOpenSdk.INSTANCE.publishMsg(new PubRobotPoseDate(x, y, angle), new ICallback() { // from class: com.pudutech.robot.module.openapi.RobotOpenApiNotifyManager$notifyPose$1
            @Override // com.pudutech.robot.opensdk.interf.ICallback
            public void onFailed(Exception e) {
                Intrinsics.checkParameterIsNotNull(e, "e");
            }

            @Override // com.pudutech.robot.opensdk.interf.ICallback
            public void onSuccess(IBody result) {
            }
        });
        poseNotifyTime = elapsedRealtime;
        lastPostA = angle;
        lastPostX = x;
        lastPostY = y;
    }

    public final void notifyDeliveryTask(PubDeliveryMode mode, ArrayList<DeliveryTraysTaskState> trays) {
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Intrinsics.checkParameterIsNotNull(trays, "trays");
        Pdlog.m3273d(TAG, mode);
        Pdlog.json(TAG, trays.toString());
        RobotOpenSdk.INSTANCE.publishMsg(new PubDeliveryTaskStateData(trays, mode.getId()), new ICallback() { // from class: com.pudutech.robot.module.openapi.RobotOpenApiNotifyManager$notifyDeliveryTask$1
            @Override // com.pudutech.robot.opensdk.interf.ICallback
            public void onFailed(Exception e) {
                Intrinsics.checkParameterIsNotNull(e, "e");
            }

            @Override // com.pudutech.robot.opensdk.interf.ICallback
            public void onSuccess(IBody result) {
            }
        });
    }

    public final void notifyMoveState(String state, ArrayList<RobotError> error) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        if (Intrinsics.areEqual(lastMoveState, state)) {
            return;
        }
        Pdlog.m3273d(TAG, "notifyMoveState: state=" + state + "  error=" + error);
        RobotOpenSdk.INSTANCE.publishMsg(new PubRobotMoveStateData(state, error), new ICallback() { // from class: com.pudutech.robot.module.openapi.RobotOpenApiNotifyManager$notifyMoveState$1
            @Override // com.pudutech.robot.opensdk.interf.ICallback
            public void onSuccess(IBody result) {
            }

            @Override // com.pudutech.robot.opensdk.interf.ICallback
            public void onFailed(Exception e) {
                Intrinsics.checkParameterIsNotNull(e, "e");
                RobotOpenApiNotifyManager robotOpenApiNotifyManager = RobotOpenApiNotifyManager.INSTANCE;
                RobotOpenApiNotifyManager.lastMoveState = "";
            }
        });
        lastMoveState = state;
    }

    public final void notifyPowerChange(int power, String chargerState) {
        Intrinsics.checkParameterIsNotNull(chargerState, "chargerState");
        Pdlog.m3273d(TAG, "notifyPowerChange: power=" + power + "  chargerState=" + chargerState);
        RobotOpenSdk.INSTANCE.publishMsg(new PubRobotPowerData(power, chargerState), new ICallback() { // from class: com.pudutech.robot.module.openapi.RobotOpenApiNotifyManager$notifyPowerChange$1
            @Override // com.pudutech.robot.opensdk.interf.ICallback
            public void onFailed(Exception e) {
                Intrinsics.checkParameterIsNotNull(e, "e");
            }

            @Override // com.pudutech.robot.opensdk.interf.ICallback
            public void onSuccess(IBody result) {
            }
        });
    }
}
