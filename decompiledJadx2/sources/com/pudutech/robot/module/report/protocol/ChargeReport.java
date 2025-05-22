package com.pudutech.robot.module.report.protocol;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ChargeReport.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000e¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/ChargeReport;", "Lcom/pudutech/robot/module/report/protocol/BaseV2Report;", "()V", "end_battery", "", "getEnd_battery", "()D", "setEnd_battery", "(D)V", "end_timestamp", "", "getEnd_timestamp", "()J", "setEnd_timestamp", "(J)V", "start_battery", "getStart_battery", "setStart_battery", "start_timestamp", "getStart_timestamp", "setStart_timestamp", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ChargeReport extends BaseV2Report {
    private double end_battery;
    private long end_timestamp;
    private double start_battery;
    private long start_timestamp;

    public ChargeReport() {
        setType("charge");
    }

    public final double getStart_battery() {
        return this.start_battery;
    }

    public final void setStart_battery(double d) {
        this.start_battery = d;
    }

    public final double getEnd_battery() {
        return this.end_battery;
    }

    public final void setEnd_battery(double d) {
        this.end_battery = d;
    }

    public final long getStart_timestamp() {
        return this.start_timestamp;
    }

    public final void setStart_timestamp(long j) {
        this.start_timestamp = j;
    }

    public final long getEnd_timestamp() {
        return this.end_timestamp;
    }

    public final void setEnd_timestamp(long j) {
        this.end_timestamp = j;
    }
}
