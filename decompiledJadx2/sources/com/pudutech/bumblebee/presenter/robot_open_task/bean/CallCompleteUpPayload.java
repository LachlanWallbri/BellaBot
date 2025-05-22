package com.pudutech.bumblebee.presenter.robot_open_task.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MqttReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000f¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CallCompleteUpPayload;", "", "task_id", "", "status", "message", "", "(JJLjava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "getStatus", "()J", "setStatus", "(J)V", "getTask_id", "setTask_id", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class CallCompleteUpPayload {
    private String message;
    private long status;
    private long task_id;

    public static /* synthetic */ CallCompleteUpPayload copy$default(CallCompleteUpPayload callCompleteUpPayload, long j, long j2, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            j = callCompleteUpPayload.task_id;
        }
        long j3 = j;
        if ((i & 2) != 0) {
            j2 = callCompleteUpPayload.status;
        }
        long j4 = j2;
        if ((i & 4) != 0) {
            str = callCompleteUpPayload.message;
        }
        return callCompleteUpPayload.copy(j3, j4, str);
    }

    /* renamed from: component1, reason: from getter */
    public final long getTask_id() {
        return this.task_id;
    }

    /* renamed from: component2, reason: from getter */
    public final long getStatus() {
        return this.status;
    }

    /* renamed from: component3, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    public final CallCompleteUpPayload copy(long task_id, long status, String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        return new CallCompleteUpPayload(task_id, status, message);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CallCompleteUpPayload)) {
            return false;
        }
        CallCompleteUpPayload callCompleteUpPayload = (CallCompleteUpPayload) other;
        return this.task_id == callCompleteUpPayload.task_id && this.status == callCompleteUpPayload.status && Intrinsics.areEqual(this.message, callCompleteUpPayload.message);
    }

    public int hashCode() {
        long j = this.task_id;
        long j2 = this.status;
        int i = ((((int) (j ^ (j >>> 32))) * 31) + ((int) ((j2 >>> 32) ^ j2))) * 31;
        String str = this.message;
        return i + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "CallCompleteUpPayload(task_id=" + this.task_id + ", status=" + this.status + ", message=" + this.message + ")";
    }

    public CallCompleteUpPayload(long j, long j2, String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        this.task_id = j;
        this.status = j2;
        this.message = message;
    }

    public final String getMessage() {
        return this.message;
    }

    public final long getStatus() {
        return this.status;
    }

    public final long getTask_id() {
        return this.task_id;
    }

    public final void setMessage(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.message = str;
    }

    public final void setStatus(long j) {
        this.status = j;
    }

    public final void setTask_id(long j) {
        this.task_id = j;
    }
}
