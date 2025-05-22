package com.pudutech.mirsdk.mircore.module.cycleparam;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.mircore.coreparcel.NavigationMode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: CycleParamUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0013\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007J\u001c\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u0012\u0010\f\u001a\u0004\u0018\u00010\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000bJ,\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/module/cycleparam/CycleParamUtils;", "", "()V", "TAG", "", "buidlPassPossibleFallingAreaStr", "pass_possible_falling_area", "", "buildAutoChargeMessageStr", "auto_charge", "track_status", "", "buildMarkerPoseStr", "marker_pose", "buildParamStr", "interaction_mode", "", "navigation_mode", "Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationMode;", "alignGoal", "stick_to_pos", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CycleParamUtils {
    public static final CycleParamUtils INSTANCE = new CycleParamUtils();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private CycleParamUtils() {
    }

    public static /* synthetic */ String buildParamStr$default(CycleParamUtils cycleParamUtils, int i, NavigationMode navigationMode, boolean z, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            navigationMode = NavigationMode.Normal;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        if ((i2 & 8) != 0) {
            z2 = false;
        }
        return cycleParamUtils.buildParamStr(i, navigationMode, z, z2);
    }

    public final String buildParamStr(int interaction_mode, NavigationMode navigation_mode, boolean alignGoal, boolean stick_to_pos) {
        Intrinsics.checkParameterIsNotNull(navigation_mode, "navigation_mode");
        StringBuilder sb = new StringBuilder();
        sb.append("{\"interaction_mode\":" + interaction_mode);
        sb.append(",\"navigation_mode\":\"" + navigation_mode + '\"');
        StringBuilder sb2 = new StringBuilder();
        sb2.append(",\"stick_to_pos\":");
        sb2.append(stick_to_pos);
        sb.append(sb2.toString());
        sb.append(",\"align_goal\":" + alignGoal + '}');
        Pdlog.m3275i(TAG, "cycle_param " + ((Object) sb));
        String sb3 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb3, "cycle_param.toString()");
        return sb3;
    }

    public final String buildMarkerPoseStr(double[] marker_pose) {
        StringBuilder sb = new StringBuilder();
        if (marker_pose == null || marker_pose[6] <= 0) {
            return null;
        }
        sb.append("{\"marker_pose\":{\"x\":" + marker_pose[0] + ",\"y\":" + marker_pose[1] + ",\"z\":" + marker_pose[2] + "},\"dock_pose\":{\"x\":" + marker_pose[3] + ",\"y\":" + marker_pose[4] + ",\"z\":" + marker_pose[5] + "}}");
        return sb.toString();
    }

    public static /* synthetic */ String buildAutoChargeMessageStr$default(CycleParamUtils cycleParamUtils, boolean z, double[] dArr, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            dArr = (double[]) null;
        }
        return cycleParamUtils.buildAutoChargeMessageStr(z, dArr);
    }

    public final String buildAutoChargeMessageStr(boolean auto_charge, double[] track_status) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"auto_charge\":" + auto_charge);
        if (track_status != null && track_status[0] > 0) {
            sb.append(",\"charge_pos\":{\"x\":" + track_status[1] + ",\"y\":" + track_status[2] + ",\"z\":" + track_status[3] + "}}");
        } else {
            sb.append("}");
        }
        Pdlog.m3275i(TAG, "charge_message " + ((Object) sb));
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "charge_message.toString()");
        return sb2;
    }

    public final String buidlPassPossibleFallingAreaStr(boolean pass_possible_falling_area) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"pass_possible_falling_area\":" + pass_possible_falling_area + '}');
        Pdlog.m3275i(TAG, "pass_possible_falling_area " + pass_possible_falling_area);
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "follow_line.toString()");
        return sb2;
    }
}
