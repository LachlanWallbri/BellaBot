package com.pudutech.mirsdk.mircore.mirschedulemaster;

import com.pudutech.mirsdk.hardware.serialize.RobotScheduleInfo;
import com.pudutech.mirsdk.hardware.serialize.SchedulingMode;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.coreparcel.BusinessType;
import com.pudutech.mirsdk.mircore.coreparcel.DestinationWithAccRange;
import com.pudutech.mirsdk.mircore.coreparcel.NextNavigationBehavior;
import com.pudutech.mirsdk.mircore.coreparcel.PathSegments;
import java.util.List;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ScheduleMaster.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0018\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010 \n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000f\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086 J\t\u0010\u0007\u001a\u00020\bH\u0086 J4\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\rH\u0086 ¢\u0006\u0002\u0010\u0011J\u0011\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\bH\u0086 J!\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\rH\u0086 J\u0011\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0006H\u0086 J\u0011\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0000H\u0086 J\u0011\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086 J\t\u0010 \u001a\u00020!H\u0086 J\u0011\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u0006H\u0086 J\u0011\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u000bH\u0086 J\t\u0010'\u001a\u00020(H\u0086 J\t\u0010)\u001a\u00020\u000bH\u0086 J\u0006\u0010*\u001a\u00020\u0006J\t\u0010+\u001a\u00020,H\u0086 J\t\u0010-\u001a\u00020\bH\u0086 J\u0019\u0010.\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\r2\u0006\u00100\u001a\u00020\rH\u0086 J\t\u00101\u001a\u00020,H\u0086 J\t\u00102\u001a\u00020\u0006H\u0086 J\u0014\u00103\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0086 ¢\u0006\u0002\u00104J)\u00105\u001a\u00020\r2\u0006\u00106\u001a\u00020\r2\u0006\u00107\u001a\u00020\r2\u0006\u00108\u001a\u00020\r2\u0006\u00109\u001a\u00020\rH\u0086 J\t\u0010:\u001a\u00020\u001aH\u0086 J\t\u0010;\u001a\u00020\u001aH\u0086 J%\u0010<\u001a\b\u0012\u0004\u0012\u00020!0=2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00060=2\u0006\u0010?\u001a\u00020\u001aH\u0086 J\u0019\u0010@\u001a\u00020\u001a2\u0006\u0010A\u001a\u00020\u00062\u0006\u0010B\u001a\u00020\u001aH\u0086 J\u0019\u0010C\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010D\u001a\u00020\u001aH\u0086 J\u0011\u0010E\u001a\u00020\u001a2\u0006\u0010F\u001a\u00020\u0006H\u0086 J!\u0010G\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\bH\u0086 J1\u0010H\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010I\u001a\u00020\r2\u0006\u0010J\u001a\u00020\rH\u0086 J\t\u0010K\u001a\u00020\u001dH\u0086 J\u0011\u0010L\u001a\u00020\u001d2\u0006\u0010\u0014\u001a\u00020MH\u0086 J\u0011\u0010N\u001a\u00020\u001d2\u0006\u0010O\u001a\u00020\bH\u0086 J\u001f\u0010P\u001a\u00020\u001d2\u0006\u0010O\u001a\u00020\b2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00060=H\u0086 J\u0011\u0010R\u001a\u00020\u001a2\u0006\u0010S\u001a\u00020\bH\u0086 J\u0011\u0010T\u001a\u00020\u001d2\u0006\u0010U\u001a\u00020\bH\u0086 J\u0019\u0010V\u001a\u00020\u001a2\u0006\u0010W\u001a\u00020\u00062\u0006\u0010X\u001a\u00020YH\u0086 J\u0011\u0010Z\u001a\u00020\u001d2\u0006\u0010[\u001a\u00020\u0006H\u0086 J\u0011\u0010\\\u001a\u00020\u001a2\u0006\u0010]\u001a\u00020\u0006H\u0086 J\u0011\u0010^\u001a\u00020\u001a2\u0006\u0010_\u001a\u00020\u001aH\u0086 J\u0011\u0010`\u001a\u00020\u001d2\u0006\u0010a\u001a\u00020\u0006H\u0086 J\u0019\u0010b\u001a\u00020\u001d2\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\rH\u0086 J\u0011\u0010c\u001a\u00020\u001d2\u0006\u0010\u0014\u001a\u00020MH\u0086 J!\u0010d\u001a\u00020\u001d2\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\rH\u0086 J)\u0010e\u001a\u00020\u001d2\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\bH\u0086 J\u0011\u0010f\u001a\u00020\u001d2\u0006\u0010g\u001a\u00020(H\u0086 ¨\u0006h"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/mirschedulemaster/ScheduleMaster;", "", "()V", "acrossFloorPathSegment", "Lcom/pudutech/mirsdk/mircore/coreparcel/PathSegments;", "destination", "", "avoidScheduleInitStatus", "", "calCruiseRouteForUI", "", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "from_x", "", "from_y", "to_x", "to_y", "(DDDD)[Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "checkScheduleForNavigation", "Lcom/pudutech/mirsdk/mircore/coreparcel/NextNavigationBehavior;", "mode", "computeFrontPathMode", "pose_x", "pose_y", "pose_z", "configFrontPathInfo", "", "config", "createModule", "", "schedule", "currentFloorPathSegment", "findGateOnCurrentPath", "Lcom/pudutech/mirsdk/mircore/coreparcel/DestinationWithAccRange;", "getBehaviorTreeParam", "paramName", "getChangeTakeInfo", "", "goal", "getCurrentRobotInfo", "Lcom/pudutech/mirsdk/hardware/serialize/RobotScheduleInfo;", "getFinalGoal", "getGitHash", "getGlobalPathAddress", "", "getInteractionMode", "getParkingMarkerID", "px", "py", "getPathPointAddress", "getVersionInfo", "getVirtualWalls", "()[Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "globalPlanWeight", "start_pose_x", "start_pose_y", "end_pose_x", "end_pose_y", "isArriveCirclePath", "isModuleReady", "orderDestinationWithRange", "", "destinations", "order", "parseMultiTopoMap", "floor_map", "reset", "parseScheduleConfig", "use_json", "parseTopoMap", "topo_map", "preScheduleCheckReplan", "preScheduleCheckReplanVel", "vel_v", "vel_a", "quitFillIn", "resetScheduleMode", "Lcom/pudutech/mirsdk/hardware/serialize/SchedulingMode;", "setCirclePath", "path_idx", "setCirclePathWithWaiterPoints", "waiter_points", "setCurrentFloorIndex", "cur_floor_index", "setCurrentTime", "tnow", "setGroupIDAndGoHome", "group_id", "business_type", "Lcom/pudutech/mirsdk/mircore/coreparcel/BusinessType;", "setGroupTaskParam", "json_param", "setPlannerParam", "param", "setRoadblockInFront", "set_roadblock", "setRobotId", "robot_id", "setRobotPose", "triggerCruiseWaiter", "updateFinalGoal", "updateFinalGoalWithMode", "updateOtherRobotInfo", "other_info", "MirScheduleMaster_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ScheduleMaster {
    public static final ScheduleMaster INSTANCE = new ScheduleMaster();

    public final native PathSegments acrossFloorPathSegment(String destination);

    public final native int avoidScheduleInitStatus();

    public final native Vector3d[] calCruiseRouteForUI(double from_x, double from_y, double to_x, double to_y);

    public final native NextNavigationBehavior checkScheduleForNavigation(int mode);

    public final native int computeFrontPathMode(double pose_x, double pose_y, double pose_z);

    public final native boolean configFrontPathInfo(String config);

    public final native void createModule(ScheduleMaster schedule);

    public final native PathSegments currentFloorPathSegment(String destination);

    public final native DestinationWithAccRange findGateOnCurrentPath();

    public final native String getBehaviorTreeParam(String paramName);

    public final native boolean[] getChangeTakeInfo(Vector3d goal);

    public final native RobotScheduleInfo getCurrentRobotInfo();

    public final native Vector3d getFinalGoal();

    public final String getGitHash() {
        return "{\"ScheduleAlg\":\"commit: d93ab3fb, auth: “Chengsi Shang”<“shangchengsi@pudutech.com”>, time: “Wed May 17 11:54:10 2023 +0800”\"}";
    }

    public final native long getGlobalPathAddress();

    public final native int getInteractionMode();

    public final native String getParkingMarkerID(double px, double py);

    public final native long getPathPointAddress();

    public final native String getVersionInfo();

    public final native Vector3d[] getVirtualWalls();

    public final native double globalPlanWeight(double start_pose_x, double start_pose_y, double end_pose_x, double end_pose_y);

    public final native boolean isArriveCirclePath();

    public final native boolean isModuleReady();

    public final native List<DestinationWithAccRange> orderDestinationWithRange(List<String> destinations, boolean order);

    public final native boolean parseMultiTopoMap(String floor_map, boolean reset);

    public final native boolean parseScheduleConfig(String config, boolean use_json);

    public final native boolean parseTopoMap(String topo_map);

    public final native boolean preScheduleCheckReplan(double pose_x, double pose_y, int mode);

    public final native boolean preScheduleCheckReplanVel(double pose_x, double pose_y, int mode, double vel_v, double vel_a);

    public final native void quitFillIn();

    public final native void resetScheduleMode(SchedulingMode mode);

    public final native void setCirclePath(int path_idx);

    public final native void setCirclePathWithWaiterPoints(int path_idx, List<String> waiter_points);

    public final native boolean setCurrentFloorIndex(int cur_floor_index);

    public final native void setCurrentTime(int tnow);

    public final native boolean setGroupIDAndGoHome(String group_id, BusinessType business_type);

    public final native void setGroupTaskParam(String json_param);

    public final native boolean setPlannerParam(String param);

    public final native boolean setRoadblockInFront(boolean set_roadblock);

    public final native void setRobotId(String robot_id);

    public final native void setRobotPose(double pose_x, double pose_y);

    public final native void triggerCruiseWaiter(SchedulingMode mode);

    public final native void updateFinalGoal(double pose_x, double pose_y, double pose_z);

    public final native void updateFinalGoalWithMode(double pose_x, double pose_y, double pose_z, int mode);

    public final native void updateOtherRobotInfo(RobotScheduleInfo other_info);

    static {
        System.loadLibrary("schedulemaster");
    }

    private ScheduleMaster() {
    }
}
