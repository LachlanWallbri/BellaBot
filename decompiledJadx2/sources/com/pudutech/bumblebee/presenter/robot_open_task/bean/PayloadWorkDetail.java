package com.pudutech.bumblebee.presenter.robot_open_task.bean;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MqttReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/PayloadWorkDetail;", "", "info", "", "status", "", TypedValues.Attributes.S_TARGET, "(Ljava/lang/String;ILjava/lang/String;)V", "getInfo", "()Ljava/lang/String;", "setInfo", "(Ljava/lang/String;)V", "getStatus", "()I", "setStatus", "(I)V", "getTarget", "setTarget", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class PayloadWorkDetail {
    private String info;
    private int status;
    private String target;

    public static /* synthetic */ PayloadWorkDetail copy$default(PayloadWorkDetail payloadWorkDetail, String str, int i, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = payloadWorkDetail.info;
        }
        if ((i2 & 2) != 0) {
            i = payloadWorkDetail.status;
        }
        if ((i2 & 4) != 0) {
            str2 = payloadWorkDetail.target;
        }
        return payloadWorkDetail.copy(str, i, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getInfo() {
        return this.info;
    }

    /* renamed from: component2, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    /* renamed from: component3, reason: from getter */
    public final String getTarget() {
        return this.target;
    }

    public final PayloadWorkDetail copy(String info, int status, String target) {
        Intrinsics.checkParameterIsNotNull(info, "info");
        Intrinsics.checkParameterIsNotNull(target, "target");
        return new PayloadWorkDetail(info, status, target);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PayloadWorkDetail)) {
            return false;
        }
        PayloadWorkDetail payloadWorkDetail = (PayloadWorkDetail) other;
        return Intrinsics.areEqual(this.info, payloadWorkDetail.info) && this.status == payloadWorkDetail.status && Intrinsics.areEqual(this.target, payloadWorkDetail.target);
    }

    public int hashCode() {
        String str = this.info;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + this.status) * 31;
        String str2 = this.target;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "PayloadWorkDetail(info=" + this.info + ", status=" + this.status + ", target=" + this.target + ")";
    }

    public PayloadWorkDetail(String info, int i, String target) {
        Intrinsics.checkParameterIsNotNull(info, "info");
        Intrinsics.checkParameterIsNotNull(target, "target");
        this.info = info;
        this.status = i;
        this.target = target;
    }

    public final String getInfo() {
        return this.info;
    }

    public final void setInfo(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.info = str;
    }

    public final int getStatus() {
        return this.status;
    }

    public final void setStatus(int i) {
        this.status = i;
    }

    public final String getTarget() {
        return this.target;
    }

    public final void setTarget(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.target = str;
    }
}
