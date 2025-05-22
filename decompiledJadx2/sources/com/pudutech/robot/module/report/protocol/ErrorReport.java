package com.pudutech.robot.module.report.protocol;

import com.pudutech.robot.module.report.protocol.bean.ErrorString;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ErrorReport.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\"\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/ErrorReport;", "Lcom/pudutech/robot/module/report/protocol/BaseV2Report;", "()V", "battery", "", "getBattery", "()D", "setBattery", "(D)V", "errors", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/module/report/protocol/bean/ErrorString;", "getErrors", "()Ljava/util/ArrayList;", "setErrors", "(Ljava/util/ArrayList;)V", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ErrorReport extends BaseV2Report {
    private double battery;
    private ArrayList<ErrorString> errors = new ArrayList<>();

    public ErrorReport() {
        setType("error");
    }

    public final double getBattery() {
        return this.battery;
    }

    public final void setBattery(double d) {
        this.battery = d;
    }

    public final ArrayList<ErrorString> getErrors() {
        return this.errors;
    }

    public final void setErrors(ArrayList<ErrorString> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.errors = arrayList;
    }
}
