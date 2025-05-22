package com.pudutech.robot.opensdk.bean.resp;

import com.pudutech.robot.opensdk.interf.IBody;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: RespCustomCallCompleteResultBody.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\nJ\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0007HÆ\u0003J0\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\u00032\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006!"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/resp/RespCustomCallCompleteResultBody;", "Lcom/pudutech/robot/opensdk/interf/IBody;", "success", "", "code", "", "nextTaskResult", "Lcom/pudutech/robot/opensdk/bean/resp/RespResultBody;", "(ZLjava/lang/Integer;Lcom/pudutech/robot/opensdk/bean/resp/RespResultBody;)V", "getCode", "()Ljava/lang/Integer;", "setCode", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getNextTaskResult", "()Lcom/pudutech/robot/opensdk/bean/resp/RespResultBody;", "setNextTaskResult", "(Lcom/pudutech/robot/opensdk/bean/resp/RespResultBody;)V", "getSuccess", "()Z", "setSuccess", "(Z)V", "component1", "component2", "component3", "copy", "(ZLjava/lang/Integer;Lcom/pudutech/robot/opensdk/bean/resp/RespResultBody;)Lcom/pudutech/robot/opensdk/bean/resp/RespCustomCallCompleteResultBody;", "equals", "other", "", "hashCode", "toString", "", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class RespCustomCallCompleteResultBody implements IBody {
    private Integer code;
    private RespResultBody nextTaskResult;
    private boolean success;

    public static /* synthetic */ RespCustomCallCompleteResultBody copy$default(RespCustomCallCompleteResultBody respCustomCallCompleteResultBody, boolean z, Integer num, RespResultBody respResultBody, int i, Object obj) {
        if ((i & 1) != 0) {
            z = respCustomCallCompleteResultBody.success;
        }
        if ((i & 2) != 0) {
            num = respCustomCallCompleteResultBody.code;
        }
        if ((i & 4) != 0) {
            respResultBody = respCustomCallCompleteResultBody.nextTaskResult;
        }
        return respCustomCallCompleteResultBody.copy(z, num, respResultBody);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getSuccess() {
        return this.success;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getCode() {
        return this.code;
    }

    /* renamed from: component3, reason: from getter */
    public final RespResultBody getNextTaskResult() {
        return this.nextTaskResult;
    }

    public final RespCustomCallCompleteResultBody copy(boolean success, Integer code, RespResultBody nextTaskResult) {
        return new RespCustomCallCompleteResultBody(success, code, nextTaskResult);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RespCustomCallCompleteResultBody)) {
            return false;
        }
        RespCustomCallCompleteResultBody respCustomCallCompleteResultBody = (RespCustomCallCompleteResultBody) other;
        return this.success == respCustomCallCompleteResultBody.success && Intrinsics.areEqual(this.code, respCustomCallCompleteResultBody.code) && Intrinsics.areEqual(this.nextTaskResult, respCustomCallCompleteResultBody.nextTaskResult);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    public int hashCode() {
        boolean z = this.success;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        Integer num = this.code;
        int hashCode = (i + (num != null ? num.hashCode() : 0)) * 31;
        RespResultBody respResultBody = this.nextTaskResult;
        return hashCode + (respResultBody != null ? respResultBody.hashCode() : 0);
    }

    public String toString() {
        return "RespCustomCallCompleteResultBody(success=" + this.success + ", code=" + this.code + ", nextTaskResult=" + this.nextTaskResult + ")";
    }

    public RespCustomCallCompleteResultBody(boolean z, Integer num, RespResultBody respResultBody) {
        this.success = z;
        this.code = num;
        this.nextTaskResult = respResultBody;
    }

    public final boolean getSuccess() {
        return this.success;
    }

    public final void setSuccess(boolean z) {
        this.success = z;
    }

    public /* synthetic */ RespCustomCallCompleteResultBody(boolean z, Integer num, RespResultBody respResultBody, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i & 2) != 0 ? (Integer) null : num, (i & 4) != 0 ? (RespResultBody) null : respResultBody);
    }

    public final Integer getCode() {
        return this.code;
    }

    public final void setCode(Integer num) {
        this.code = num;
    }

    public final RespResultBody getNextTaskResult() {
        return this.nextTaskResult;
    }

    public final void setNextTaskResult(RespResultBody respResultBody) {
        this.nextTaskResult = respResultBody;
    }
}
