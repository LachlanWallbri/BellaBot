package com.pudutech.robot.module.report.protocol;

import kotlin.Metadata;

/* compiled from: BaseApiReport.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0004H\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\b¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/BaseApiReport;", "Lcom/pudutech/robot/module/report/protocol/BaseReport;", "()V", "hardver", "", "getHardver", "()Ljava/lang/String;", "setHardver", "(Ljava/lang/String;)V", "report_number", "", "getReport_number", "()I", "setReport_number", "(I)V", "softver", "getSoftver", "setSoftver", "getUrlPath", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public abstract class BaseApiReport extends BaseReport {
    private int report_number;
    private String softver = "";
    private String hardver = "";

    @Override // com.pudutech.robot.module.report.protocol.BaseReport
    public String getUrlPath() {
        return "";
    }

    public final int getReport_number() {
        return this.report_number;
    }

    public final void setReport_number(int i) {
        this.report_number = i;
    }

    public final String getSoftver() {
        return this.softver;
    }

    public final void setSoftver(String str) {
        this.softver = str;
    }

    public final String getHardver() {
        return this.hardver;
    }

    public final void setHardver(String str) {
        this.hardver = str;
    }
}
