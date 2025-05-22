package com.pudutech.robot.module.report.protocol;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: VoiceInteractReportWrap.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003JE\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020%HÖ\u0001R\u001e\u0010\u0007\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001e\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001e\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\rR\u001e\u0010\b\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000b\"\u0004\b\u0017\u0010\r¨\u0006&"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/ReportVoiceInteractWrapper;", "", "interact_start_time", "", "interact_end_time", "interact_duration", "catch_start_time", "catch_end_time", "stay_duration", "(JJJJJJ)V", "getCatch_end_time", "()J", "setCatch_end_time", "(J)V", "getCatch_start_time", "setCatch_start_time", "getInteract_duration", "setInteract_duration", "getInteract_end_time", "setInteract_end_time", "getInteract_start_time", "setInteract_start_time", "getStay_duration", "setStay_duration", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class ReportVoiceInteractWrapper {

    @SerializedName("catch_end_time")
    private long catch_end_time;

    @SerializedName("catch_start_time")
    private long catch_start_time;

    @SerializedName("interact_duration")
    private long interact_duration;

    @SerializedName("interact_end_time")
    private long interact_end_time;

    @SerializedName("interact_start_time")
    private long interact_start_time;

    @SerializedName("stay_duration")
    private long stay_duration;

    public ReportVoiceInteractWrapper() {
        this(0L, 0L, 0L, 0L, 0L, 0L, 63, null);
    }

    /* renamed from: component1, reason: from getter */
    public final long getInteract_start_time() {
        return this.interact_start_time;
    }

    /* renamed from: component2, reason: from getter */
    public final long getInteract_end_time() {
        return this.interact_end_time;
    }

    /* renamed from: component3, reason: from getter */
    public final long getInteract_duration() {
        return this.interact_duration;
    }

    /* renamed from: component4, reason: from getter */
    public final long getCatch_start_time() {
        return this.catch_start_time;
    }

    /* renamed from: component5, reason: from getter */
    public final long getCatch_end_time() {
        return this.catch_end_time;
    }

    /* renamed from: component6, reason: from getter */
    public final long getStay_duration() {
        return this.stay_duration;
    }

    public final ReportVoiceInteractWrapper copy(long interact_start_time, long interact_end_time, long interact_duration, long catch_start_time, long catch_end_time, long stay_duration) {
        return new ReportVoiceInteractWrapper(interact_start_time, interact_end_time, interact_duration, catch_start_time, catch_end_time, stay_duration);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ReportVoiceInteractWrapper)) {
            return false;
        }
        ReportVoiceInteractWrapper reportVoiceInteractWrapper = (ReportVoiceInteractWrapper) other;
        return this.interact_start_time == reportVoiceInteractWrapper.interact_start_time && this.interact_end_time == reportVoiceInteractWrapper.interact_end_time && this.interact_duration == reportVoiceInteractWrapper.interact_duration && this.catch_start_time == reportVoiceInteractWrapper.catch_start_time && this.catch_end_time == reportVoiceInteractWrapper.catch_end_time && this.stay_duration == reportVoiceInteractWrapper.stay_duration;
    }

    public int hashCode() {
        long j = this.interact_start_time;
        long j2 = this.interact_end_time;
        int i = ((((int) (j ^ (j >>> 32))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        long j3 = this.interact_duration;
        int i2 = (i + ((int) (j3 ^ (j3 >>> 32)))) * 31;
        long j4 = this.catch_start_time;
        int i3 = (i2 + ((int) (j4 ^ (j4 >>> 32)))) * 31;
        long j5 = this.catch_end_time;
        int i4 = (i3 + ((int) (j5 ^ (j5 >>> 32)))) * 31;
        long j6 = this.stay_duration;
        return i4 + ((int) ((j6 >>> 32) ^ j6));
    }

    public String toString() {
        return "ReportVoiceInteractWrapper(interact_start_time=" + this.interact_start_time + ", interact_end_time=" + this.interact_end_time + ", interact_duration=" + this.interact_duration + ", catch_start_time=" + this.catch_start_time + ", catch_end_time=" + this.catch_end_time + ", stay_duration=" + this.stay_duration + ")";
    }

    public ReportVoiceInteractWrapper(long j, long j2, long j3, long j4, long j5, long j6) {
        this.interact_start_time = j;
        this.interact_end_time = j2;
        this.interact_duration = j3;
        this.catch_start_time = j4;
        this.catch_end_time = j5;
        this.stay_duration = j6;
    }

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

    public /* synthetic */ ReportVoiceInteractWrapper(long j, long j2, long j3, long j4, long j5, long j6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? -1L : j, (i & 2) != 0 ? -1L : j2, (i & 4) != 0 ? -1L : j3, (i & 8) != 0 ? -1L : j4, (i & 16) != 0 ? -1L : j5, (i & 32) == 0 ? j6 : -1L);
    }

    public final long getStay_duration() {
        return this.stay_duration;
    }

    public final void setStay_duration(long j) {
        this.stay_duration = j;
    }
}
