package com.pudutech.disinfect.baselib.network.response;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: RobotActiveStatusResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/TableBean;", "", "queue_id", "", "waiting_number", "(Ljava/lang/String;Ljava/lang/String;)V", "getQueue_id", "()Ljava/lang/String;", "setQueue_id", "(Ljava/lang/String;)V", "getWaiting_number", "setWaiting_number", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class TableBean {
    private String queue_id;
    private String waiting_number;

    public static /* synthetic */ TableBean copy$default(TableBean tableBean, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tableBean.queue_id;
        }
        if ((i & 2) != 0) {
            str2 = tableBean.waiting_number;
        }
        return tableBean.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getQueue_id() {
        return this.queue_id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getWaiting_number() {
        return this.waiting_number;
    }

    public final TableBean copy(String queue_id, String waiting_number) {
        Intrinsics.checkParameterIsNotNull(queue_id, "queue_id");
        Intrinsics.checkParameterIsNotNull(waiting_number, "waiting_number");
        return new TableBean(queue_id, waiting_number);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TableBean)) {
            return false;
        }
        TableBean tableBean = (TableBean) other;
        return Intrinsics.areEqual(this.queue_id, tableBean.queue_id) && Intrinsics.areEqual(this.waiting_number, tableBean.waiting_number);
    }

    public int hashCode() {
        String str = this.queue_id;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.waiting_number;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "TableBean(queue_id=" + this.queue_id + ", waiting_number=" + this.waiting_number + ")";
    }

    public TableBean(String queue_id, String waiting_number) {
        Intrinsics.checkParameterIsNotNull(queue_id, "queue_id");
        Intrinsics.checkParameterIsNotNull(waiting_number, "waiting_number");
        this.queue_id = queue_id;
        this.waiting_number = waiting_number;
    }

    public final String getQueue_id() {
        return this.queue_id;
    }

    public final String getWaiting_number() {
        return this.waiting_number;
    }

    public final void setQueue_id(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.queue_id = str;
    }

    public final void setWaiting_number(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.waiting_number = str;
    }
}
