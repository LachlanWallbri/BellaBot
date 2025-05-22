package com.pudutech.robot.module.report.protocol;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;

/* compiled from: VoiceInteractReportWrap.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016R \u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/VoiceInteractReportWrap;", "Lcom/pudutech/robot/module/report/protocol/BaseApiNoNumReport;", "()V", "report", "Lcom/pudutech/robot/module/report/protocol/ReportVoiceInteractWrapper;", "getReport", "()Lcom/pudutech/robot/module/report/protocol/ReportVoiceInteractWrapper;", "setReport", "(Lcom/pudutech/robot/module/report/protocol/ReportVoiceInteractWrapper;)V", "getUrlPath", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class VoiceInteractReportWrap extends BaseApiNoNumReport {

    @SerializedName("data")
    private ReportVoiceInteractWrapper report;

    @Override // com.pudutech.robot.module.report.protocol.BaseApiNoNumReport, com.pudutech.robot.module.report.protocol.BaseReport
    public String getUrlPath() {
        return "/api/kettybot/voice_interact/collect/v1";
    }

    public final ReportVoiceInteractWrapper getReport() {
        return this.report;
    }

    public final void setReport(ReportVoiceInteractWrapper reportVoiceInteractWrapper) {
        this.report = reportVoiceInteractWrapper;
    }
}
