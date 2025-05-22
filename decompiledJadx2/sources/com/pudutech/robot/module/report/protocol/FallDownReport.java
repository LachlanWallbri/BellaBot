package com.pudutech.robot.module.report.protocol;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FallDownReport.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/FallDownReport;", "Lcom/pudutech/robot/module/report/protocol/BaseV4Report;", "()V", "data", "Lcom/pudutech/robot/module/report/protocol/FallDownType;", "getData", "()Lcom/pudutech/robot/module/report/protocol/FallDownType;", "setData", "(Lcom/pudutech/robot/module/report/protocol/FallDownType;)V", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class FallDownReport extends BaseV4Report {
    private FallDownType data;

    public FallDownReport() {
        setType("software");
        setMode("fall_down");
        this.data = new FallDownType();
    }

    public final FallDownType getData() {
        return this.data;
    }

    public final void setData(FallDownType fallDownType) {
        Intrinsics.checkParameterIsNotNull(fallDownType, "<set-?>");
        this.data = fallDownType;
    }
}
