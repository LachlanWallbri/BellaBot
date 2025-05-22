package com.pudutech.mirsdk.sdksafe;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes6.dex
 */
/* compiled from: NetResponse.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00028\u0000¢\u0006\u0002\u0010\bJ\t\u0010\u0016\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\u000e\u0010\u0018\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u000eJ2\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u001e\u001a\u00020\u0004HÖ\u0001J\t\u0010\u001f\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\u0007\u001a\u00028\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006 "}, m3961d2 = {"Lcom/pudutech/mirsdk/sdksafe/ResponseMsg;", ExifInterface.GPS_DIRECTION_TRUE, "", "code", "", NotificationCompat.CATEGORY_MESSAGE, "", "data", "(ILjava/lang/String;Ljava/lang/Object;)V", "getCode", "()I", "setCode", "(I)V", "getData", "()Ljava/lang/Object;", "setData", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "getMsg", "()Ljava/lang/String;", "setMsg", "(Ljava/lang/String;)V", "component1", "component2", "component3", "copy", "(ILjava/lang/String;Ljava/lang/Object;)Lcom/pudutech/mirsdk/sdksafe/ResponseMsg;", "equals", "", "other", "hashCode", "toString", "sdksafe_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final /* data */ class ResponseMsg<T> {
    private int code;
    private T data;
    private String msg;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ResponseMsg copy$default(ResponseMsg responseMsg, int i, String str, Object obj, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            i = responseMsg.code;
        }
        if ((i2 & 2) != 0) {
            str = responseMsg.msg;
        }
        if ((i2 & 4) != 0) {
            obj = responseMsg.data;
        }
        return responseMsg.copy(i, str, obj);
    }

    /* renamed from: component1, reason: from getter */
    public final int getCode() {
        return this.code;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMsg() {
        return this.msg;
    }

    public final T component3() {
        return this.data;
    }

    public final ResponseMsg<T> copy(int code, String msg, T data) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        return new ResponseMsg<>(code, msg, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ResponseMsg)) {
            return false;
        }
        ResponseMsg responseMsg = (ResponseMsg) other;
        return this.code == responseMsg.code && Intrinsics.areEqual(this.msg, responseMsg.msg) && Intrinsics.areEqual(this.data, responseMsg.data);
    }

    public int hashCode() {
        int i = this.code * 31;
        String str = this.msg;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        T t = this.data;
        return hashCode + (t != null ? t.hashCode() : 0);
    }

    public String toString() {
        return "ResponseMsg(code=" + this.code + ", msg=" + this.msg + ", data=" + this.data + ")";
    }

    public ResponseMsg(int i, String msg, T t) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        this.code = i;
        this.msg = msg;
        this.data = t;
    }

    public final int getCode() {
        return this.code;
    }

    public final T getData() {
        return this.data;
    }

    public final String getMsg() {
        return this.msg;
    }

    public final void setCode(int i) {
        this.code = i;
    }

    public final void setData(T t) {
        this.data = t;
    }

    public final void setMsg(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.msg = str;
    }
}
