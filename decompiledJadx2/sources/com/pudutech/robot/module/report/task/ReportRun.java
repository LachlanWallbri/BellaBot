package com.pudutech.robot.module.report.task;

import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.robot.module.report.PuduReportManager;
import com.pudutech.robot.module.report.protocol.BaseReport;
import com.pudutech.robot.module.report.protocol.RunReport;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ReportRun.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000C\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u000f\u0018\u00002\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0006H\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u000e\u001a\u00020\u000f8\u0002X\u0083\u0004¢\u0006\n\n\u0002\u0010\u0011\u0012\u0004\b\u0010\u0010\u0002¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/ReportRun;", "Lcom/pudutech/robot/module/report/task/BaseReportTask;", "()V", "TAG", "", ES6Iterator.VALUE_PROPERTY, "Lcom/pudutech/robot/module/report/task/ReportRun$Status;", "status", "setStatus", "(Lcom/pudutech/robot/module/report/task/ReportRun$Status;)V", "taskSet", "", "timer", "Ljava/util/Timer;", "timerTask", "com/pudutech/robot/module/report/task/ReportRun$timerTask$1", "timerTask$annotations", "Lcom/pudutech/robot/module/report/task/ReportRun$timerTask$1;", "getReportData", "Lcom/pudutech/robot/module/report/protocol/BaseReport;", "getRunTime", "", "getStatus", "startTask", "", "Status", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ReportRun extends BaseReportTask {
    private boolean taskSet;
    private final String TAG = "ReportRun";
    private final Timer timer = new Timer();
    private final ReportRun$timerTask$1 timerTask = new TimerTask() { // from class: com.pudutech.robot.module.report.task.ReportRun$timerTask$1
        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            String str;
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            str = ReportRun.this.TAG;
            netWorkLog.mo3278d(str, "report Robot status");
            ReportRun.this.report();
        }
    };
    private Status status = Status.IDLE;

    private static /* synthetic */ void timerTask$annotations() {
    }

    public final void startTask() {
        long runTime = getRunTime();
        NetWorkLog.INSTANCE.mo3278d(this.TAG, "startTask time is " + runTime);
        if (this.taskSet) {
            return;
        }
        this.taskSet = true;
        this.timer.schedule(this.timerTask, 0L, runTime);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0070 A[Catch: Exception -> 0x0077, TryCatch #3 {Exception -> 0x0077, blocks: (B:12:0x002e, B:14:0x0039, B:16:0x0046, B:18:0x004f, B:20:0x0054, B:26:0x006a, B:28:0x0070, B:29:0x0073, B:32:0x0062), top: B:11:0x002e }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0062 A[Catch: Exception -> 0x0077, all -> 0x0088, TRY_LEAVE, TryCatch #2 {all -> 0x0088, blocks: (B:10:0x0024, B:12:0x002e, B:14:0x0039, B:16:0x0046, B:18:0x004f, B:20:0x0054, B:32:0x0062, B:36:0x0078), top: B:9:0x0024 }] */
    /* JADX WARN: Type inference failed for: r0v9, types: [T, java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v5, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v1, types: [T, java.io.BufferedReader] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final long getRunTime() {
        FileReader fileReader;
        boolean z;
        BufferedReader bufferedReader;
        File file = new File("/sdcard/pudu/config/run_time");
        if (!file.exists()) {
            return 600000L;
        }
        FileReader fileReader2 = (FileReader) null;
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (BufferedReader) 0;
        try {
            try {
                fileReader = new FileReader(file);
                try {
                    try {
                        objectRef.element = new BufferedReader(fileReader);
                        try {
                            final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                            objectRef2.element = (String) 0;
                            String str = "";
                            while (new Function0<String>() { // from class: com.pudutech.robot.module.report.task.ReportRun$getRunTime$1
                                /* JADX INFO: Access modifiers changed from: package-private */
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* JADX WARN: Multi-variable type inference failed */
                                /* JADX WARN: Type inference failed for: r1v3, types: [T, java.lang.String] */
                                @Override // kotlin.jvm.functions.Function0
                                public final String invoke() {
                                    Ref.ObjectRef.this.element = ((BufferedReader) objectRef.element).readLine();
                                    return (String) Ref.ObjectRef.this.element;
                                }
                            }.invoke() != null) {
                                str = Intrinsics.stringPlus(str, (String) objectRef2.element);
                            }
                            String str2 = str;
                            if (str2 != null && str2.length() != 0) {
                                z = false;
                                long parseInt = !z ? 600000L : Integer.parseInt(str) * 1000;
                                bufferedReader = (BufferedReader) objectRef.element;
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                                fileReader.close();
                                return parseInt;
                            }
                            z = true;
                            if (!z) {
                            }
                            bufferedReader = (BufferedReader) objectRef.element;
                            if (bufferedReader != null) {
                            }
                            fileReader.close();
                            return parseInt;
                        } catch (Exception e) {
                            e.printStackTrace();
                            BufferedReader bufferedReader2 = (BufferedReader) objectRef.element;
                            if (bufferedReader2 != null) {
                                bufferedReader2.close();
                            }
                            fileReader.close();
                            return 600000L;
                        }
                    } catch (Throwable th) {
                        th = th;
                        BufferedReader bufferedReader3 = (BufferedReader) objectRef.element;
                        if (bufferedReader3 != null) {
                            bufferedReader3.close();
                        }
                        if (fileReader != null) {
                            fileReader.close();
                        }
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                    fileReader2 = fileReader;
                    e.printStackTrace();
                    BufferedReader bufferedReader4 = (BufferedReader) objectRef.element;
                    if (bufferedReader4 != null) {
                        bufferedReader4.close();
                    }
                    if (fileReader2 != null) {
                        fileReader2.close();
                    }
                    return 600000L;
                }
            } catch (Throwable th2) {
                th = th2;
                fileReader = fileReader2;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: ReportRun.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/ReportRun$Status;", "", ES6Iterator.VALUE_PROPERTY, "", "(Ljava/lang/String;II)V", "getValue", "()I", "OFF_LINE", "WORKING", "IDLE", "CHARGING", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public enum Status {
        OFF_LINE(0),
        WORKING(1),
        IDLE(2),
        CHARGING(3);

        private final int value;

        Status(int i) {
            this.value = i;
        }

        public final int getValue() {
            return this.value;
        }
    }

    private final void setStatus(Status status) {
        this.status = status;
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "set status=" + status);
    }

    @Override // com.pudutech.robot.module.report.task.BaseReportTask
    public BaseReport getReportData() {
        RunReport runReport = new RunReport();
        PuduReportManager.IReportDataProvide iReportDataProvide$module_robot_report_release = PuduReportManager.INSTANCE.getIReportDataProvide$module_robot_report_release();
        runReport.setBattery(iReportDataProvide$module_robot_report_release != null ? iReportDataProvide$module_robot_report_release.getPowerPercent() : 0.0d);
        runReport.setStatus(getStatus().getValue());
        PuduReportManager.IRobotPositionProvider iRobotPositionProvider$module_robot_report_release = PuduReportManager.INSTANCE.getIRobotPositionProvider$module_robot_report_release();
        runReport.setPosition(iRobotPositionProvider$module_robot_report_release != null ? iRobotPositionProvider$module_robot_report_release.getPositionInfo() : null);
        return runReport;
    }

    private final Status getStatus() {
        PuduReportManager.IReportDataProvide iReportDataProvide$module_robot_report_release = PuduReportManager.INSTANCE.getIReportDataProvide$module_robot_report_release();
        if (iReportDataProvide$module_robot_report_release != null && iReportDataProvide$module_robot_report_release.isCharging()) {
            return Status.CHARGING;
        }
        PuduReportManager.IReportDataProvide iReportDataProvide$module_robot_report_release2 = PuduReportManager.INSTANCE.getIReportDataProvide$module_robot_report_release();
        if (iReportDataProvide$module_robot_report_release2 != null && iReportDataProvide$module_robot_report_release2.isWorking()) {
            return Status.WORKING;
        }
        return Status.IDLE;
    }
}
