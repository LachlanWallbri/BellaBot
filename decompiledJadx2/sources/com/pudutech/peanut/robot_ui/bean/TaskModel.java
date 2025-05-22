package com.pudutech.peanut.robot_ui.bean;

import com.iflytek.aiui.AIUIConstant;
import com.pudutech.peanut.presenter.delivery_task.TrayModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TaskModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J'\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/bean/TaskModel;", "", "isReturn", "", AIUIConstant.KEY_CONTENT, "", "trayModel", "Lcom/pudutech/peanut/presenter/delivery_task/TrayModel;", "(ILjava/lang/String;Lcom/pudutech/peanut/presenter/delivery_task/TrayModel;)V", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "()I", "setReturn", "(I)V", "getTrayModel", "()Lcom/pudutech/peanut/presenter/delivery_task/TrayModel;", "setTrayModel", "(Lcom/pudutech/peanut/presenter/delivery_task/TrayModel;)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class TaskModel {
    private String content;
    private int isReturn;
    private TrayModel trayModel;

    public static /* synthetic */ TaskModel copy$default(TaskModel taskModel, int i, String str, TrayModel trayModel, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = taskModel.isReturn;
        }
        if ((i2 & 2) != 0) {
            str = taskModel.content;
        }
        if ((i2 & 4) != 0) {
            trayModel = taskModel.trayModel;
        }
        return taskModel.copy(i, str, trayModel);
    }

    /* renamed from: component1, reason: from getter */
    public final int getIsReturn() {
        return this.isReturn;
    }

    /* renamed from: component2, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* renamed from: component3, reason: from getter */
    public final TrayModel getTrayModel() {
        return this.trayModel;
    }

    public final TaskModel copy(int isReturn, String content, TrayModel trayModel) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        Intrinsics.checkParameterIsNotNull(trayModel, "trayModel");
        return new TaskModel(isReturn, content, trayModel);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TaskModel)) {
            return false;
        }
        TaskModel taskModel = (TaskModel) other;
        return this.isReturn == taskModel.isReturn && Intrinsics.areEqual(this.content, taskModel.content) && Intrinsics.areEqual(this.trayModel, taskModel.trayModel);
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.isReturn) * 31;
        String str = this.content;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        TrayModel trayModel = this.trayModel;
        return hashCode2 + (trayModel != null ? trayModel.hashCode() : 0);
    }

    public String toString() {
        return "TaskModel(isReturn=" + this.isReturn + ", content=" + this.content + ", trayModel=" + this.trayModel + ")";
    }

    public TaskModel(int i, String content, TrayModel trayModel) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        Intrinsics.checkParameterIsNotNull(trayModel, "trayModel");
        this.isReturn = i;
        this.content = content;
        this.trayModel = trayModel;
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

    public final TrayModel getTrayModel() {
        return this.trayModel;
    }

    public final void setTrayModel(TrayModel trayModel) {
        Intrinsics.checkParameterIsNotNull(trayModel, "<set-?>");
        this.trayModel = trayModel;
    }
}
