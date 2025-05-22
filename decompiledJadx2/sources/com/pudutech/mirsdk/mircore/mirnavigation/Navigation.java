package com.pudutech.mirsdk.mircore.mirnavigation;

import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.mircore.coreparcel.NavigationResult;
import com.pudutech.mirsdk.mircore.coreparcel.RotateResult;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: Navigation.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JI\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086 J!\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u000fH\u0086 J\u0011\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0018H\u0086 J\t\u0010\u0019\u001a\u00020\u0011H\u0086 J\u0011\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001cH\u0086 J\u0006\u0010\u001d\u001a\u00020\u001eJ\u0011\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u001cH\u0086 J\t\u0010!\u001a\u00020\u001eH\u0086 J\t\u0010\"\u001a\u00020\u001eH\u0086 J\t\u0010#\u001a\u00020\u001eH\u0086 J\t\u0010$\u001a\u00020\u0006H\u0086 J\t\u0010%\u001a\u00020\u0011H\u0086 JQ\u0010&\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u001c2\u0006\u0010'\u001a\u00020\u001c2\u0006\u0010(\u001a\u00020\b2\u0006\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\b2\u0006\u0010+\u001a\u00020\u00182\u0006\u0010,\u001a\u00020\u00182\u0006\u0010-\u001a\u00020\u00182\u0006\u0010.\u001a\u00020\u001cH\u0086 JI\u0010/\u001a\u0002002\u0006\u0010 \u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u00101\u001a\u00020\b2\u0006\u00102\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH\u0086 J\u0011\u00103\u001a\u00020\u001e2\u0006\u00104\u001a\u00020\u001eH\u0086 J\u0011\u00105\u001a\u00020\u00182\u0006\u00104\u001a\u00020\u001eH\u0086 J\u0011\u00106\u001a\u00020\u00182\u0006\u00104\u001a\u00020\u001eH\u0086 J\u0011\u00107\u001a\u00020\u00112\u0006\u00108\u001a\u00020\u0018H\u0086 J\u0011\u00109\u001a\u00020\u00112\u0006\u0010:\u001a\u00020\u0018H\u0086 ¨\u0006;"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/mirnavigation/Navigation;", "", "()V", "callLinearAngularVel", "Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationResult;", "perception_address", "", "posex", "", "posey", "dirx", "diry", "linear_speed", "angular_speed", "mode", "", "createModule", "", "machineType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineModel;", "wheel_space", "rgbd_type", "disengageChargingPile", "is_request_leave_charge", "", "fetchDynamicConfig", "getDangerousRegionResult", "dangerous_info_address", "", "getGitHash", "", "getMapAreaInfo", "local_map_address", "getPlannerParams", "getSteadyParams", "getVersionInfo", "navigationSelfTest", "resetNavigationFlag", "resetPath", "path_address", "goalx", "goaly", "goalz", "is_reverse", "is_deliver_start", "is_virtual_goal", "global_track_address", "rotate", "Lcom/pudutech/mirsdk/mircore/coreparcel/RotateResult;", "target_dirx", "target_diry", "specialProcess", "config", "updateConfig", "updateCycleParam", "updateDynamicConfig", "status", "updateFootPrint", "use_cover_around", "MirNavigation_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class Navigation {
    public static final Navigation INSTANCE = new Navigation();

    public final native NavigationResult callLinearAngularVel(long[] perception_address, double posex, double posey, double dirx, double diry, double linear_speed, double angular_speed, int mode);

    public final native void createModule(MachineModel machineType, double wheel_space, int rgbd_type);

    public final native void disengageChargingPile(boolean is_request_leave_charge);

    public final native void fetchDynamicConfig();

    public final native void getDangerousRegionResult(long dangerous_info_address);

    public final String getGitHash() {
        return "{\"navigation\":\"commit: f2ef0e0, auth: “chenjunwei”<“chenjunwei@pudutech.com”>, time: “Fri May 12 15:39:03 2023 +0800”\"}";
    }

    public final native void getMapAreaInfo(long local_map_address);

    public final native String getPlannerParams();

    public final native String getSteadyParams();

    public final native String getVersionInfo();

    public final native long[] navigationSelfTest();

    public final native void resetNavigationFlag();

    public final native void resetPath(long local_map_address, long path_address, double goalx, double goaly, double goalz, boolean is_reverse, boolean is_deliver_start, boolean is_virtual_goal, long global_track_address);

    public final native RotateResult rotate(long local_map_address, double posex, double posey, double dirx, double diry, double target_dirx, double target_diry, double angular_speed);

    public final native String specialProcess(String config);

    public final native boolean updateConfig(String config);

    public final native boolean updateCycleParam(String config);

    public final native void updateDynamicConfig(boolean status);

    public final native void updateFootPrint(boolean use_cover_around);

    static {
        System.loadLibrary("mirnavigation");
    }

    private Navigation() {
    }
}
