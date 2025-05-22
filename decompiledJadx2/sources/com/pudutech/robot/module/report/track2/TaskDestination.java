package com.pudutech.robot.module.report.track2;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TrackConstant.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/TaskDestination;", "", "destination", "", "food_info", "(Ljava/lang/String;Ljava/lang/String;)V", "getDestination", "()Ljava/lang/String;", "getFood_info", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class TaskDestination {
    private final String destination;
    private final String food_info;

    public static /* synthetic */ TaskDestination copy$default(TaskDestination taskDestination, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = taskDestination.destination;
        }
        if ((i & 2) != 0) {
            str2 = taskDestination.food_info;
        }
        return taskDestination.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDestination() {
        return this.destination;
    }

    /* renamed from: component2, reason: from getter */
    public final String getFood_info() {
        return this.food_info;
    }

    public final TaskDestination copy(String destination, String food_info) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(food_info, "food_info");
        return new TaskDestination(destination, food_info);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TaskDestination)) {
            return false;
        }
        TaskDestination taskDestination = (TaskDestination) other;
        return Intrinsics.areEqual(this.destination, taskDestination.destination) && Intrinsics.areEqual(this.food_info, taskDestination.food_info);
    }

    public int hashCode() {
        String str = this.destination;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.food_info;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "TaskDestination(destination=" + this.destination + ", food_info=" + this.food_info + ")";
    }

    public TaskDestination(String destination, String food_info) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(food_info, "food_info");
        this.destination = destination;
        this.food_info = food_info;
    }

    public final String getDestination() {
        return this.destination;
    }

    public /* synthetic */ TaskDestination(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? "" : str2);
    }

    public final String getFood_info() {
        return this.food_info;
    }
}
