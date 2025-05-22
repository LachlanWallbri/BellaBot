package com.pudutech.robot.module.report.protocol;

import com.pudutech.robot.module.report.PuduReportManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BaseV4Report.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\u0004H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/BaseV4Report;", "Lcom/pudutech/robot/module/report/protocol/BaseReport;", "()V", "mode", "", "getMode", "()Ljava/lang/String;", "setMode", "(Ljava/lang/String;)V", "type", "getType", "setType", "getUrlPath", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public class BaseV4Report extends BaseReport {
    private String mode = "";
    private String type = "";

    public final String getMode() {
        return this.mode;
    }

    public final void setMode(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mode = str;
    }

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        this.type = str;
    }

    @Override // com.pudutech.robot.module.report.protocol.BaseReport
    public String getUrlPath() {
        String str = this.mode;
        int hashCode = str.hashCode();
        if (hashCode != -331239923) {
            if (hashCode == 1091768120) {
                if (str.equals("error_process")) {
                    return "/api/common/error_process/collect/v1";
                }
            } else if (hashCode == 2121038566 && str.equals("fall_down")) {
                return "/api/common/fall_down/collect/v1";
            }
        } else if (str.equals("battery")) {
            return "/api/common/battery/collect/v1";
        }
        return PuduReportManager.API_V4;
    }
}
