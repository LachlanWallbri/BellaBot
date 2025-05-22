package com.pudutech.bumblebee.presenter.robot_open_task.bean;

import kotlin.Metadata;

/* compiled from: MqttResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/Info;", "", "task_id", "", "(J)V", "getTask_id", "()J", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class Info {
    private final long task_id;

    public static /* synthetic */ Info copy$default(Info info, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = info.task_id;
        }
        return info.copy(j);
    }

    /* renamed from: component1, reason: from getter */
    public final long getTask_id() {
        return this.task_id;
    }

    public final Info copy(long task_id) {
        return new Info(task_id);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof Info) && this.task_id == ((Info) other).task_id;
        }
        return true;
    }

    public int hashCode() {
        long j = this.task_id;
        return (int) (j ^ (j >>> 32));
    }

    public String toString() {
        return "Info(task_id=" + this.task_id + ")";
    }

    public Info(long j) {
        this.task_id = j;
    }

    public final long getTask_id() {
        return this.task_id;
    }
}
