package com.pudutech.robot.opensdk.bean;

import com.pudutech.robot.opensdk.interf.IBody;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: CustomCallCompleteBody.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/CustomCallCompleteBody;", "Lcom/pudutech/robot/opensdk/interf/IBody;", "nextCallTask", "Lcom/pudutech/robot/opensdk/bean/CustomCallBody;", "taskId", "", "(Lcom/pudutech/robot/opensdk/bean/CustomCallBody;Ljava/lang/String;)V", "getNextCallTask", "()Lcom/pudutech/robot/opensdk/bean/CustomCallBody;", "setNextCallTask", "(Lcom/pudutech/robot/opensdk/bean/CustomCallBody;)V", "getTaskId", "()Ljava/lang/String;", "setTaskId", "(Ljava/lang/String;)V", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class CustomCallCompleteBody implements IBody {
    private CustomCallBody nextCallTask;
    private String taskId;

    public static /* synthetic */ CustomCallCompleteBody copy$default(CustomCallCompleteBody customCallCompleteBody, CustomCallBody customCallBody, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            customCallBody = customCallCompleteBody.nextCallTask;
        }
        if ((i & 2) != 0) {
            str = customCallCompleteBody.taskId;
        }
        return customCallCompleteBody.copy(customCallBody, str);
    }

    /* renamed from: component1, reason: from getter */
    public final CustomCallBody getNextCallTask() {
        return this.nextCallTask;
    }

    /* renamed from: component2, reason: from getter */
    public final String getTaskId() {
        return this.taskId;
    }

    public final CustomCallCompleteBody copy(CustomCallBody nextCallTask, String taskId) {
        return new CustomCallCompleteBody(nextCallTask, taskId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomCallCompleteBody)) {
            return false;
        }
        CustomCallCompleteBody customCallCompleteBody = (CustomCallCompleteBody) other;
        return Intrinsics.areEqual(this.nextCallTask, customCallCompleteBody.nextCallTask) && Intrinsics.areEqual(this.taskId, customCallCompleteBody.taskId);
    }

    public int hashCode() {
        CustomCallBody customCallBody = this.nextCallTask;
        int hashCode = (customCallBody != null ? customCallBody.hashCode() : 0) * 31;
        String str = this.taskId;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "CustomCallCompleteBody(nextCallTask=" + this.nextCallTask + ", taskId=" + this.taskId + ")";
    }

    public CustomCallCompleteBody(CustomCallBody customCallBody, String str) {
        this.nextCallTask = customCallBody;
        this.taskId = str;
    }

    public /* synthetic */ CustomCallCompleteBody(CustomCallBody customCallBody, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (CustomCallBody) null : customCallBody, str);
    }

    public final CustomCallBody getNextCallTask() {
        return this.nextCallTask;
    }

    public final void setNextCallTask(CustomCallBody customCallBody) {
        this.nextCallTask = customCallBody;
    }

    public final String getTaskId() {
        return this.taskId;
    }

    public final void setTaskId(String str) {
        this.taskId = str;
    }
}
