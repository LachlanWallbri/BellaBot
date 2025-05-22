package com.pudutech.robot.opensdk.bean;

import com.pudutech.robot.opensdk.interf.IBody;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: CustomCallCancelBody.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/CustomCallCancelBody;", "Lcom/pudutech/robot/opensdk/interf/IBody;", "taskId", "", "(Ljava/lang/String;)V", "getTaskId", "()Ljava/lang/String;", "setTaskId", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class CustomCallCancelBody implements IBody {
    private String taskId;

    public static /* synthetic */ CustomCallCancelBody copy$default(CustomCallCancelBody customCallCancelBody, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = customCallCancelBody.taskId;
        }
        return customCallCancelBody.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTaskId() {
        return this.taskId;
    }

    public final CustomCallCancelBody copy(String taskId) {
        return new CustomCallCancelBody(taskId);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof CustomCallCancelBody) && Intrinsics.areEqual(this.taskId, ((CustomCallCancelBody) other).taskId);
        }
        return true;
    }

    public int hashCode() {
        String str = this.taskId;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "CustomCallCancelBody(taskId=" + this.taskId + ")";
    }

    public CustomCallCancelBody(String str) {
        this.taskId = str;
    }

    public final String getTaskId() {
        return this.taskId;
    }

    public final void setTaskId(String str) {
        this.taskId = str;
    }
}
