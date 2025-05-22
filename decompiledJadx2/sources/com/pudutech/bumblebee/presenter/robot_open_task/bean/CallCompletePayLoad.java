package com.pudutech.bumblebee.presenter.robot_open_task.bean;

import kotlin.Metadata;

/* compiled from: MqttResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CallCompletePayLoad;", "", "request_id", "", "code", "(JJ)V", "getCode", "()J", "setCode", "(J)V", "getRequest_id", "setRequest_id", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class CallCompletePayLoad {
    private long code;
    private long request_id;

    public static /* synthetic */ CallCompletePayLoad copy$default(CallCompletePayLoad callCompletePayLoad, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = callCompletePayLoad.request_id;
        }
        if ((i & 2) != 0) {
            j2 = callCompletePayLoad.code;
        }
        return callCompletePayLoad.copy(j, j2);
    }

    /* renamed from: component1, reason: from getter */
    public final long getRequest_id() {
        return this.request_id;
    }

    /* renamed from: component2, reason: from getter */
    public final long getCode() {
        return this.code;
    }

    public final CallCompletePayLoad copy(long request_id, long code) {
        return new CallCompletePayLoad(request_id, code);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CallCompletePayLoad)) {
            return false;
        }
        CallCompletePayLoad callCompletePayLoad = (CallCompletePayLoad) other;
        return this.request_id == callCompletePayLoad.request_id && this.code == callCompletePayLoad.code;
    }

    public int hashCode() {
        long j = this.request_id;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        long j2 = this.code;
        return i + ((int) ((j2 >>> 32) ^ j2));
    }

    public String toString() {
        return "CallCompletePayLoad(request_id=" + this.request_id + ", code=" + this.code + ")";
    }

    public CallCompletePayLoad(long j, long j2) {
        this.request_id = j;
        this.code = j2;
    }

    public final long getRequest_id() {
        return this.request_id;
    }

    public final void setRequest_id(long j) {
        this.request_id = j;
    }

    public final long getCode() {
        return this.code;
    }

    public final void setCode(long j) {
        this.code = j;
    }
}
