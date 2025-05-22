package com.pudutech.robot.module.report.protocol;

import com.pudutech.robot.module.report.PuduReportManager;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BaseV2Report.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\f\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0004H\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\b¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/BaseV2Report;", "Lcom/pudutech/robot/module/report/protocol/BaseReport;", "()V", "hardver", "", "getHardver", "()Ljava/lang/String;", "setHardver", "(Ljava/lang/String;)V", "report_number", "", "getReport_number", "()I", "setReport_number", "(I)V", "softver", "getSoftver", "setSoftver", "type", "getType", "setType", "getUrlPath", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public class BaseV2Report extends BaseReport {
    private int report_number;
    private String softver = "";
    private String hardver = "";
    private String type = "";

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

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        this.type = str;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x000a. Please report as an issue. */
    @Override // com.pudutech.robot.module.report.protocol.BaseReport
    public String getUrlPath() {
        String str = this.type;
        if (str != null) {
            switch (str.hashCode()) {
                case -1361632588:
                    if (str.equals("charge")) {
                        return "/api/common/charge/collect/v2";
                    }
                    break;
                case -1351809835:
                    if (str.equals("cruise")) {
                        return "/api/common/cruise/collect/v1";
                    }
                    break;
                case -231171556:
                    if (str.equals("upgrade")) {
                        return "/api/common/upgrade/collect/v1";
                    }
                    break;
                case 113291:
                    if (str.equals("run")) {
                        return "/api/common/run/collect/v1";
                    }
                    break;
                case 3029746:
                    if (str.equals("boot")) {
                        return "/api/common/boot/collect/v2";
                    }
                    break;
                case 49166883:
                    if (str.equals("disinfect")) {
                        return "/api/common/disinfect/collect/v1";
                    }
                    break;
                case 96784904:
                    if (str.equals("error")) {
                        return "/api/common/error/collect/v2";
                    }
                    break;
                case 450534586:
                    if (str.equals("support_ability")) {
                        return "/api/common/abilities/update/v1";
                    }
                    break;
                case 823466996:
                    if (str.equals("delivery")) {
                        return "/api/common/delivery/collect/v1";
                    }
                    break;
                case 904576122:
                    if (str.equals("nfc_clock")) {
                        return "/api/common/nfc_clock/collect/v1";
                    }
                    break;
                case 1334688237:
                    if (str.equals("back_disc")) {
                        return "/api/common/back_disc/collect/v1";
                    }
                    break;
            }
        }
        return PuduReportManager.API_V2;
    }
}
