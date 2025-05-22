package com.pudutech.robot.module.report.protocol;

import com.pudutech.robot.module.report.protocol.bean.ErrorProcess;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ErrorProcessReport.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R*\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/ErrorProcessReport;", "Lcom/pudutech/robot/module/report/protocol/BaseV4Report;", "()V", "data", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/module/report/protocol/bean/ErrorProcess;", "Lkotlin/collections/ArrayList;", "getData", "()Ljava/util/ArrayList;", "setData", "(Ljava/util/ArrayList;)V", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ErrorProcessReport extends BaseV4Report {
    private ArrayList<ErrorProcess> data = new ArrayList<>();

    public ErrorProcessReport() {
        setType("software");
        setMode("error_process");
    }

    public final ArrayList<ErrorProcess> getData() {
        return this.data;
    }

    public final void setData(ArrayList<ErrorProcess> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.data = arrayList;
    }
}
