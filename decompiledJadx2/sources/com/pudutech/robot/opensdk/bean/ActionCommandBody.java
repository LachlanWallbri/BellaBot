package com.pudutech.robot.opensdk.bean;

import com.pudutech.robot.opensdk.interf.IBody;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ActionCommandBody.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/ActionCommandBody;", "Lcom/pudutech/robot/opensdk/interf/IBody;", "action", "", "(Ljava/lang/String;)V", "getAction", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class ActionCommandBody implements IBody {
    private final String action;

    public static /* synthetic */ ActionCommandBody copy$default(ActionCommandBody actionCommandBody, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = actionCommandBody.action;
        }
        return actionCommandBody.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAction() {
        return this.action;
    }

    public final ActionCommandBody copy(String action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        return new ActionCommandBody(action);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof ActionCommandBody) && Intrinsics.areEqual(this.action, ((ActionCommandBody) other).action);
        }
        return true;
    }

    public int hashCode() {
        String str = this.action;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "ActionCommandBody(action=" + this.action + ")";
    }

    public ActionCommandBody(String action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        this.action = action;
    }

    public final String getAction() {
        return this.action;
    }
}
