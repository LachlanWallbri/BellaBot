package com.pudutech.robot.module.report.protocol;

import kotlin.Metadata;

/* compiled from: VoiceInteractReport.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0014\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\b¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/VoiceInteractReport;", "", "()V", "catch_end_time", "", "getCatch_end_time", "()J", "setCatch_end_time", "(J)V", "catch_start_time", "getCatch_start_time", "setCatch_start_time", "interact_duration", "getInteract_duration", "setInteract_duration", "interact_end_time", "getInteract_end_time", "setInteract_end_time", "interact_start_time", "getInteract_start_time", "setInteract_start_time", "stay_duration", "getStay_duration", "setStay_duration", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class VoiceInteractReport {
    private volatile long catch_end_time;
    private volatile long catch_start_time;
    private volatile long interact_duration;
    private volatile long interact_end_time;
    private volatile long interact_start_time;
    private volatile long stay_duration;

    public final long getInteract_start_time() {
        return this.interact_start_time;
    }

    public final void setInteract_start_time(long j) {
        this.interact_start_time = j;
    }

    public final long getInteract_end_time() {
        return this.interact_end_time;
    }

    public final void setInteract_end_time(long j) {
        this.interact_end_time = j;
    }

    public final long getInteract_duration() {
        return this.interact_duration;
    }

    public final void setInteract_duration(long j) {
        this.interact_duration = j;
    }

    public final long getCatch_start_time() {
        return this.catch_start_time;
    }

    public final void setCatch_start_time(long j) {
        this.catch_start_time = j;
    }

    public final long getCatch_end_time() {
        return this.catch_end_time;
    }

    public final void setCatch_end_time(long j) {
        this.catch_end_time = j;
    }

    public final long getStay_duration() {
        return this.stay_duration;
    }

    public final void setStay_duration(long j) {
        this.stay_duration = j;
    }
}
