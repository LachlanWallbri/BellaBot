package com.pudutech.bumblebee.presenter.robot_open_task.bean;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MqttReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/Work;", "", "status", "", "last_status", NotificationCompat.CATEGORY_MESSAGE, "", "(IILjava/lang/String;)V", "getLast_status", "()I", "setLast_status", "(I)V", "getMsg", "()Ljava/lang/String;", "setMsg", "(Ljava/lang/String;)V", "getStatus", "setStatus", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class Work {
    private int last_status;
    private String msg;
    private int status;

    public static /* synthetic */ Work copy$default(Work work, int i, int i2, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = work.status;
        }
        if ((i3 & 2) != 0) {
            i2 = work.last_status;
        }
        if ((i3 & 4) != 0) {
            str = work.msg;
        }
        return work.copy(i, i2, str);
    }

    /* renamed from: component1, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    /* renamed from: component2, reason: from getter */
    public final int getLast_status() {
        return this.last_status;
    }

    /* renamed from: component3, reason: from getter */
    public final String getMsg() {
        return this.msg;
    }

    public final Work copy(int status, int last_status, String msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        return new Work(status, last_status, msg);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Work)) {
            return false;
        }
        Work work = (Work) other;
        return this.status == work.status && this.last_status == work.last_status && Intrinsics.areEqual(this.msg, work.msg);
    }

    public int hashCode() {
        int i = ((this.status * 31) + this.last_status) * 31;
        String str = this.msg;
        return i + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "Work(status=" + this.status + ", last_status=" + this.last_status + ", msg=" + this.msg + ")";
    }

    public Work(int i, int i2, String msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        this.status = i;
        this.last_status = i2;
        this.msg = msg;
    }

    public final int getLast_status() {
        return this.last_status;
    }

    public final String getMsg() {
        return this.msg;
    }

    public final int getStatus() {
        return this.status;
    }

    public final void setLast_status(int i) {
        this.last_status = i;
    }

    public final void setMsg(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.msg = str;
    }

    public final void setStatus(int i) {
        this.status = i;
    }
}
