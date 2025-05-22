package com.pudutech.bumblebee.presenter.robot_open_task.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MqttResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/ActionPayLoad;", "", "action", "", "info", "(Ljava/lang/String;Ljava/lang/String;)V", "getAction", "()Ljava/lang/String;", "setAction", "(Ljava/lang/String;)V", "getInfo", "setInfo", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class ActionPayLoad {
    private String action;
    private String info;

    public static /* synthetic */ ActionPayLoad copy$default(ActionPayLoad actionPayLoad, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = actionPayLoad.action;
        }
        if ((i & 2) != 0) {
            str2 = actionPayLoad.info;
        }
        return actionPayLoad.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAction() {
        return this.action;
    }

    /* renamed from: component2, reason: from getter */
    public final String getInfo() {
        return this.info;
    }

    public final ActionPayLoad copy(String action, String info) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        Intrinsics.checkParameterIsNotNull(info, "info");
        return new ActionPayLoad(action, info);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActionPayLoad)) {
            return false;
        }
        ActionPayLoad actionPayLoad = (ActionPayLoad) other;
        return Intrinsics.areEqual(this.action, actionPayLoad.action) && Intrinsics.areEqual(this.info, actionPayLoad.info);
    }

    public int hashCode() {
        String str = this.action;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.info;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "ActionPayLoad(action=" + this.action + ", info=" + this.info + ")";
    }

    public ActionPayLoad(String action, String info) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        Intrinsics.checkParameterIsNotNull(info, "info");
        this.action = action;
        this.info = info;
    }

    public final String getAction() {
        return this.action;
    }

    public final void setAction(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.action = str;
    }

    public final String getInfo() {
        return this.info;
    }

    public final void setInfo(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.info = str;
    }
}
