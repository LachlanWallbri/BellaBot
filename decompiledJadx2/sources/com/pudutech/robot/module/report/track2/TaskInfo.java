package com.pudutech.robot.module.report.track2;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TrackConstant.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/TaskInfo;", "", "index", "", TrackKey.TASK_DESTINATION, "", "Lcom/pudutech/robot/module/report/track2/TaskDestination;", "(ILjava/util/List;)V", "getIndex", "()I", "getTask_destination", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class TaskInfo {
    private final int index;
    private final List<TaskDestination> task_destination;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TaskInfo copy$default(TaskInfo taskInfo, int i, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = taskInfo.index;
        }
        if ((i2 & 2) != 0) {
            list = taskInfo.task_destination;
        }
        return taskInfo.copy(i, list);
    }

    /* renamed from: component1, reason: from getter */
    public final int getIndex() {
        return this.index;
    }

    public final List<TaskDestination> component2() {
        return this.task_destination;
    }

    public final TaskInfo copy(int index, List<TaskDestination> task_destination) {
        Intrinsics.checkParameterIsNotNull(task_destination, "task_destination");
        return new TaskInfo(index, task_destination);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TaskInfo)) {
            return false;
        }
        TaskInfo taskInfo = (TaskInfo) other;
        return this.index == taskInfo.index && Intrinsics.areEqual(this.task_destination, taskInfo.task_destination);
    }

    public int hashCode() {
        int i = this.index * 31;
        List<TaskDestination> list = this.task_destination;
        return i + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "TaskInfo(index=" + this.index + ", task_destination=" + this.task_destination + ")";
    }

    public TaskInfo(int i, List<TaskDestination> task_destination) {
        Intrinsics.checkParameterIsNotNull(task_destination, "task_destination");
        this.index = i;
        this.task_destination = task_destination;
    }

    public final int getIndex() {
        return this.index;
    }

    public final List<TaskDestination> getTask_destination() {
        return this.task_destination;
    }
}
