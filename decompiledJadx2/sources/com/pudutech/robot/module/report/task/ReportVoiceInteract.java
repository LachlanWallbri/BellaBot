package com.pudutech.robot.module.report.task;

import com.pudutech.robot.module.report.protocol.BaseReport;
import com.pudutech.robot.module.report.protocol.ReportVoiceInteractWrapper;
import com.pudutech.robot.module.report.protocol.VoiceInteractReport;
import com.pudutech.robot.module.report.protocol.VoiceInteractReportWrap;
import kotlin.Metadata;

/* compiled from: ReportVoiceInteract.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000bJ\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000bJ\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/ReportVoiceInteract;", "Lcom/pudutech/robot/module/report/task/BaseReportTask;", "()V", "voiceInteractReport", "Lcom/pudutech/robot/module/report/protocol/VoiceInteractReport;", "checkParams", "", "getReportData", "Lcom/pudutech/robot/module/report/protocol/BaseReport;", "recordCatchEndTime", "l", "", "recordCatchStartTime", "recordInteractEndTime", "t", "recordInteractStartTime", "toString", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ReportVoiceInteract extends BaseReportTask {
    private volatile VoiceInteractReport voiceInteractReport = new VoiceInteractReport();

    public final ReportVoiceInteract recordInteractStartTime(long t) {
        this.voiceInteractReport.setInteract_start_time(t);
        return this;
    }

    public final ReportVoiceInteract recordInteractEndTime(long t) {
        this.voiceInteractReport.setInteract_end_time(t);
        return this;
    }

    public final ReportVoiceInteract recordCatchStartTime(long l) {
        this.voiceInteractReport.setCatch_start_time(l);
        return this;
    }

    public final ReportVoiceInteract recordCatchEndTime(long l) {
        this.voiceInteractReport.setCatch_end_time(l);
        return this;
    }

    public final boolean checkParams() {
        if (this.voiceInteractReport.getCatch_start_time() == 0 || this.voiceInteractReport.getCatch_end_time() == 0 || this.voiceInteractReport.getCatch_start_time() >= this.voiceInteractReport.getCatch_end_time()) {
            return (this.voiceInteractReport.getInteract_end_time() == 0 || this.voiceInteractReport.getInteract_start_time() == 0 || this.voiceInteractReport.getInteract_start_time() >= this.voiceInteractReport.getInteract_end_time()) ? false : true;
        }
        return true;
    }

    public String toString() {
        return "ReportVoiceInteract(catch_start_time:" + this.voiceInteractReport.getCatch_start_time() + ", catch_end_time:" + this.voiceInteractReport.getCatch_end_time() + ",interact_start_time:" + this.voiceInteractReport.getInteract_start_time() + ",interact_end_time " + this.voiceInteractReport.getInteract_end_time() + ')';
    }

    @Override // com.pudutech.robot.module.report.task.BaseReportTask
    public BaseReport getReportData() {
        VoiceInteractReportWrap voiceInteractReportWrap = new VoiceInteractReportWrap();
        if (this.voiceInteractReport.getInteract_end_time() > this.voiceInteractReport.getInteract_start_time()) {
            this.voiceInteractReport.setInteract_duration(this.voiceInteractReport.getInteract_end_time() - this.voiceInteractReport.getInteract_start_time());
        }
        if (this.voiceInteractReport.getCatch_end_time() > this.voiceInteractReport.getCatch_start_time()) {
            this.voiceInteractReport.setStay_duration(this.voiceInteractReport.getCatch_end_time() - this.voiceInteractReport.getCatch_start_time());
        }
        ReportVoiceInteractWrapper reportVoiceInteractWrapper = new ReportVoiceInteractWrapper(0L, 0L, 0L, 0L, 0L, 0L, 63, null);
        reportVoiceInteractWrapper.setInteract_duration(this.voiceInteractReport.getInteract_duration());
        reportVoiceInteractWrapper.setInteract_start_time(this.voiceInteractReport.getInteract_start_time());
        reportVoiceInteractWrapper.setInteract_end_time(this.voiceInteractReport.getInteract_end_time());
        reportVoiceInteractWrapper.setCatch_end_time(this.voiceInteractReport.getCatch_end_time());
        reportVoiceInteractWrapper.setCatch_start_time(this.voiceInteractReport.getCatch_start_time());
        reportVoiceInteractWrapper.setStay_duration(this.voiceInteractReport.getStay_duration());
        voiceInteractReportWrap.setReport(reportVoiceInteractWrapper);
        return voiceInteractReportWrap;
    }
}
