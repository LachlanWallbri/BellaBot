package com.pudutech.bumblebee.robot_ui.track.task;

import com.pudutech.robot.module.report.TrackingReportManager;
import com.pudutech.robot.module.report.track2.BasePeripheralsStatus;
import com.pudutech.robot.module.report.track2.PeripheralsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PeripheralsTrack.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bJ(\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bJ(\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/track/task/PeripheralsTrack;", "", "()V", "onEmergencyStopBtnPeripherals", "", "emergencyStatus", "", "pageKey", "", "sessionId", "onTouchPeripherals", "touchType", "touchStatus", "onTrayPeripherals", "traySensorCount", "traySensorStatus", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class PeripheralsTrack {
    public static final PeripheralsTrack INSTANCE = new PeripheralsTrack();

    private PeripheralsTrack() {
    }

    public static /* synthetic */ void onTouchPeripherals$default(PeripheralsTrack peripheralsTrack, int i, int i2, String str, String str2, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            str2 = "";
        }
        peripheralsTrack.onTouchPeripherals(i, i2, str, str2);
    }

    public final void onTouchPeripherals(int touchType, int touchStatus, String pageKey, String sessionId) {
        Intrinsics.checkParameterIsNotNull(pageKey, "pageKey");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        PeripheralsKt.onPeripheralsStatus(TrackingReportManager.INSTANCE, new BasePeripheralsStatus.BellaTouchPeripheralsStatus(pageKey, touchType, touchStatus), sessionId);
    }

    public static /* synthetic */ void onTrayPeripherals$default(PeripheralsTrack peripheralsTrack, int i, int i2, String str, String str2, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            str2 = "";
        }
        peripheralsTrack.onTrayPeripherals(i, i2, str, str2);
    }

    public final void onTrayPeripherals(int traySensorCount, int traySensorStatus, String pageKey, String sessionId) {
        Intrinsics.checkParameterIsNotNull(pageKey, "pageKey");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        PeripheralsKt.onPeripheralsStatus(TrackingReportManager.INSTANCE, new BasePeripheralsStatus.BellaTrayPeripheralsStatus(pageKey, traySensorCount, traySensorStatus), sessionId);
    }

    public static /* synthetic */ void onEmergencyStopBtnPeripherals$default(PeripheralsTrack peripheralsTrack, int i, String str, String str2, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str2 = "";
        }
        peripheralsTrack.onEmergencyStopBtnPeripherals(i, str, str2);
    }

    public final void onEmergencyStopBtnPeripherals(int emergencyStatus, String pageKey, String sessionId) {
        Intrinsics.checkParameterIsNotNull(pageKey, "pageKey");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        PeripheralsKt.onPeripheralsStatus(TrackingReportManager.INSTANCE, new BasePeripheralsStatus.BellaEmergencyStopBtnStatus(pageKey, emergencyStatus), sessionId);
    }
}
