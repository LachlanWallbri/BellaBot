package com.pudutech.peanut.robot_ui.bean;

import com.iflytek.aiui.AIUIConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TaskModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/bean/TaskBean;", "", "isReturn", "", AIUIConstant.KEY_CONTENT, "", "(ILjava/lang/String;)V", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "()I", "setReturn", "(I)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class TaskBean {
    private String content;
    private int isReturn;

    public static /* synthetic */ TaskBean copy$default(TaskBean taskBean, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = taskBean.isReturn;
        }
        if ((i2 & 2) != 0) {
            str = taskBean.content;
        }
        return taskBean.copy(i, str);
    }

    /* renamed from: component1, reason: from getter */
    public final int getIsReturn() {
        return this.isReturn;
    }

    /* renamed from: component2, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    public final TaskBean copy(int isReturn, String content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        return new TaskBean(isReturn, content);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TaskBean)) {
            return false;
        }
        TaskBean taskBean = (TaskBean) other;
        return this.isReturn == taskBean.isReturn && Intrinsics.areEqual(this.content, taskBean.content);
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.isReturn) * 31;
        String str = this.content;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "TaskBean(isReturn=" + this.isReturn + ", content=" + this.content + ")";
    }

    public TaskBean(int i, String content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        this.isReturn = i;
        this.content = content;
    }

    public final int isReturn() {
        return this.isReturn;
    }

    public final void setReturn(int i) {
        this.isReturn = i;
    }

    public final String getContent() {
        return this.content;
    }

    public final void setContent(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.content = str;
    }
}
