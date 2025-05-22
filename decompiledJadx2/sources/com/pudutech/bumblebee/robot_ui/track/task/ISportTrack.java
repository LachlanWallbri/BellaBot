package com.pudutech.bumblebee.robot_ui.track.task;

import com.pudutech.robot.module.report.track2.TrackKey;
import kotlin.Metadata;

/* compiled from: ISportTrack.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH&J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\nH&J\b\u0010\u000f\u001a\u00020\u0003H&¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/track/task/ISportTrack;", "", "onEmergencyStop", "", "onMove", "onPause", "onStartScheduling", "onStopScheduling", "onTouch", TrackKey.TOUCH_TYPE, "", TrackKey.TOUCH_STATUS, "onTray", TrackKey.TRAY_SENSOR_COUNT, TrackKey.TRAY_SENSOR_STATUS, "onWakeup", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public interface ISportTrack {
    void onEmergencyStop();

    void onMove();

    void onPause();

    void onStartScheduling();

    void onStopScheduling();

    void onTouch(int touch_type, int touch_status);

    void onTray(int tray_sensor_count, int tray_sensor_status);

    void onWakeup();
}
