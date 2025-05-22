package com.pudutech.bumblebee.presenter.report_cloud.task;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.SpeedListener;
import com.pudutech.bumblebee.presenter.report_cloud.protocol.cruise.Cruise;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReportCruise.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\f\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0006\u0010\u0012\u001a\u00020\u000fJ\u0006\u0010\u0013\u001a\u00020\u000fJ\u000e\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0016J\b\u0010\u0017\u001a\u00020\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\r¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/report_cloud/task/ReportCruise;", "", "()V", "TAG", "", "cruise", "Lcom/pudutech/bumblebee/presenter/report_cloud/protocol/cruise/Cruise;", "mReportCruise", "Lcom/pudutech/robot/module/report/task/ReportCruise;", "moveTaskModel", "Lcom/pudutech/bumblebee/presenter/report_cloud/task/MoveTaskModel;", "spdListener", "com/pudutech/bumblebee/presenter/report_cloud/task/ReportCruise$spdListener$1", "Lcom/pudutech/bumblebee/presenter/report_cloud/task/ReportCruise$spdListener$1;", "addMeters", "", "speed", "", "createNewTask", "finish", "setAutoMove", "isActive", "", "toCloud", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ReportCruise {
    private static Cruise cruise;
    private static com.pudutech.robot.module.report.task.ReportCruise mReportCruise;
    private static MoveTaskModel moveTaskModel;
    public static final ReportCruise INSTANCE = new ReportCruise();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final ReportCruise$spdListener$1 spdListener = new SpeedListener() { // from class: com.pudutech.bumblebee.presenter.report_cloud.task.ReportCruise$spdListener$1
        @Override // com.pudutech.bumblebee.business.robotsdk.sdk_listeners.SpeedListener
        public void onSpeed(double p0, double p1) {
            ReportCruise.INSTANCE.addMeters(p0);
        }
    };

    private ReportCruise() {
    }

    private final void toCloud() {
        Pdlog.m3275i(TAG, "to cloud. cruise=" + cruise);
        MoveTaskModel moveTaskModel2 = moveTaskModel;
        if (moveTaskModel2 != null) {
            moveTaskModel2.setEndTimestamp_ms(SystemClock.elapsedRealtime());
            Cruise cruise2 = cruise;
            if (cruise2 != null) {
                cruise2.mileage = moveTaskModel2.getMileages_m();
            }
            Cruise cruise3 = cruise;
            if (cruise3 != null) {
                cruise3.duration_pause = moveTaskModel2.getDurationPause_ms() / 1000;
            }
            Cruise cruise4 = cruise;
            if (cruise4 != null) {
                cruise4.total_time = (moveTaskModel2.getEndTimestamp_ms() - moveTaskModel2.getStartTimestamp_ms()) / 1000;
            }
            Cruise cruise5 = cruise;
            if (cruise5 != null) {
                cruise5.duration = cruise5.total_time - cruise5.duration_pause;
            }
        }
        Cruise cruise6 = cruise;
        if (cruise6 != null) {
            if (cruise6.total_time > 0) {
                cruise6.average = cruise6.mileage / cruise6.total_time;
            }
            cruise6.battery = CoreDevices.INSTANCE.getBattery().getPowerPercent();
        }
        com.pudutech.robot.module.report.task.ReportCruise reportCruise = mReportCruise;
        if (reportCruise != null) {
            Cruise cruise7 = cruise;
            if (cruise7 != null) {
                reportCruise.setAverage(cruise7.average);
                reportCruise.setDuration(cruise7.duration);
                reportCruise.setMileage(cruise7.mileage);
                reportCruise.setPauseCount(cruise7.duration_count);
                reportCruise.setPauseDuration(cruise7.duration_pause);
                Boolean status = cruise7.status;
                Intrinsics.checkExpressionValueIsNotNull(status, "status");
                reportCruise.setStatus(status.booleanValue());
                reportCruise.setTotalTime(cruise7.total_time);
            }
            reportCruise.report();
            Pdlog.m3275i(TAG, "to cloud. cruise=" + cruise);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addMeters(double speed) {
        MoveTaskModel moveTaskModel2 = moveTaskModel;
        if (moveTaskModel2 != null) {
            moveTaskModel2.setMileages_m(moveTaskModel2.getMileages_m() + (((SystemClock.elapsedRealtime() - moveTaskModel2.getLastSpeedTimestap()) / 1000.0d) * moveTaskModel2.getSpeed()));
            moveTaskModel2.setLastSpeedTimestap(SystemClock.elapsedRealtime());
            moveTaskModel2.setSpeed(speed);
        }
    }

    public final void createNewTask() {
        Pdlog.m3275i(TAG, "create new task");
        cruise = new Cruise();
        mReportCruise = new com.pudutech.robot.module.report.task.ReportCruise();
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        Cruise cruise2 = cruise;
        if (cruise2 != null) {
            cruise2.task_id = currentTimeMillis;
        }
        com.pudutech.robot.module.report.task.ReportCruise reportCruise = mReportCruise;
        if (reportCruise != null) {
            reportCruise.getReportData();
        }
        com.pudutech.robot.module.report.task.ReportCruise reportCruise2 = mReportCruise;
        if (reportCruise2 != null) {
            reportCruise2.setTaskId(currentTimeMillis);
        }
        moveTaskModel = new MoveTaskModel();
        MoveTaskModel moveTaskModel2 = moveTaskModel;
        if (moveTaskModel2 != null) {
            moveTaskModel2.setStartTimestamp_ms(SystemClock.elapsedRealtime());
        }
        SDK.INSTANCE.getSpeedListeners().addListener(spdListener);
    }

    public final void setAutoMove(boolean isActive) {
        Pdlog.m3275i(TAG, "set auto move=" + isActive + ' ');
        String str = TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("moveTask isActive=");
        MoveTaskModel moveTaskModel2 = moveTaskModel;
        sb.append(moveTaskModel2 != null ? moveTaskModel2.getIsActive() : null);
        sb.append(" activeTimestamp_ms=");
        MoveTaskModel moveTaskModel3 = moveTaskModel;
        sb.append(moveTaskModel3 != null ? Long.valueOf(moveTaskModel3.getActiveTimestamp_ms()) : null);
        sb.append(" pauseTimestamp_ms=");
        MoveTaskModel moveTaskModel4 = moveTaskModel;
        sb.append(moveTaskModel4 != null ? Long.valueOf(moveTaskModel4.getPauseTimestamp_ms()) : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        Pdlog.m3273d(TAG, "");
        MoveTaskModel moveTaskModel5 = moveTaskModel;
        if (Intrinsics.areEqual(moveTaskModel5 != null ? moveTaskModel5.getIsActive() : null, Boolean.valueOf(isActive))) {
            return;
        }
        MoveTaskModel moveTaskModel6 = moveTaskModel;
        if (moveTaskModel6 != null) {
            if (isActive) {
                moveTaskModel6.setActiveTimestamp_ms(SystemClock.elapsedRealtime());
                if (moveTaskModel6.getPauseTimestamp_ms() != 0) {
                    moveTaskModel6.setDurationPause_ms(moveTaskModel6.getDurationPause_ms() + (moveTaskModel6.getActiveTimestamp_ms() - moveTaskModel6.getPauseTimestamp_ms()));
                }
            } else {
                moveTaskModel6.setPauseTimestamp_ms(SystemClock.elapsedRealtime());
            }
            moveTaskModel6.setActive(Boolean.valueOf(isActive));
        }
        if (isActive) {
            return;
        }
        Cruise cruise2 = cruise;
        if (cruise2 != null) {
            cruise2.duration_count++;
        }
        toCloud();
    }

    public final void finish() {
        Pdlog.m3275i(TAG, "finish");
        SDK.INSTANCE.getSpeedListeners().removeListener(spdListener);
        toCloud();
        cruise = (Cruise) null;
        mReportCruise = (com.pudutech.robot.module.report.task.ReportCruise) null;
    }
}
