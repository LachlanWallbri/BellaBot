package com.pudutech.robot.opensdk.aliyun.bean;

import com.pudutech.mirsdk.compat.topo.MapElement;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: UnBindData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/bean/UnBindData;", "", "action", "", MapElement.Source.SOURCE, "(Ljava/lang/String;Ljava/lang/String;)V", "getAction", "()Ljava/lang/String;", "getSource", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class UnBindData {
    private final String action;
    private final String source;

    public static /* synthetic */ UnBindData copy$default(UnBindData unBindData, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = unBindData.action;
        }
        if ((i & 2) != 0) {
            str2 = unBindData.source;
        }
        return unBindData.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAction() {
        return this.action;
    }

    /* renamed from: component2, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    public final UnBindData copy(String action, String source) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        Intrinsics.checkParameterIsNotNull(source, "source");
        return new UnBindData(action, source);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UnBindData)) {
            return false;
        }
        UnBindData unBindData = (UnBindData) other;
        return Intrinsics.areEqual(this.action, unBindData.action) && Intrinsics.areEqual(this.source, unBindData.source);
    }

    public int hashCode() {
        String str = this.action;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.source;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "UnBindData(action=" + this.action + ", source=" + this.source + ")";
    }

    public UnBindData(String action, String source) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        Intrinsics.checkParameterIsNotNull(source, "source");
        this.action = action;
        this.source = source;
    }

    public final String getAction() {
        return this.action;
    }

    public final String getSource() {
        return this.source;
    }
}
