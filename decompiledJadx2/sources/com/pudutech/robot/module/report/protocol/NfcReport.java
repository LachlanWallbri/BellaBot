package com.pudutech.robot.module.report.protocol;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: NfcReport.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/NfcReport;", "Lcom/pudutech/robot/module/report/protocol/BaseV2Report;", "()V", "clock_timestamp", "", "getClock_timestamp", "()J", "setClock_timestamp", "(J)V", "nfc_id", "", "getNfc_id", "()Ljava/lang/String;", "setNfc_id", "(Ljava/lang/String;)V", "source_type", "", "getSource_type", "()I", "setSource_type", "(I)V", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class NfcReport extends BaseV2Report {
    private long clock_timestamp;
    private String nfc_id = "";
    private int source_type = 2;

    public NfcReport() {
        setType("nfc_clock");
    }

    public final long getClock_timestamp() {
        return this.clock_timestamp;
    }

    public final void setClock_timestamp(long j) {
        this.clock_timestamp = j;
    }

    public final String getNfc_id() {
        return this.nfc_id;
    }

    public final void setNfc_id(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.nfc_id = str;
    }

    public final int getSource_type() {
        return this.source_type;
    }

    public final void setSource_type(int i) {
        this.source_type = i;
    }
}
