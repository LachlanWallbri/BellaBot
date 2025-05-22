package com.pudutech.bumblebee.presenter.net.resp;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HttpResult.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B'\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\bJ\t\u0010\u0016\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\u000eJ6\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00018\u0000HÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u001e\u001a\u00020\u0004HÖ\u0001J\t\u0010\u001f\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001e\u0010\u0007\u001a\u0004\u0018\u00018\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006 "}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/net/resp/HttpResult;", ExifInterface.GPS_DIRECTION_TRUE, "", "code", "", NotificationCompat.CATEGORY_MESSAGE, "", "data", "(ILjava/lang/String;Ljava/lang/Object;)V", "getCode", "()I", "setCode", "(I)V", "getData", "()Ljava/lang/Object;", "setData", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "getMsg", "()Ljava/lang/String;", "setMsg", "(Ljava/lang/String;)V", "component1", "component2", "component3", "copy", "(ILjava/lang/String;Ljava/lang/Object;)Lcom/pudutech/bumblebee/presenter/net/resp/HttpResult;", "equals", "", "other", "hashCode", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class HttpResult<T> {
    private int code;
    private T data;
    private String msg;

    public HttpResult() {
        this(0, null, null, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HttpResult copy$default(HttpResult httpResult, int i, String str, Object obj, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            i = httpResult.code;
        }
        if ((i2 & 2) != 0) {
            str = httpResult.msg;
        }
        if ((i2 & 4) != 0) {
            obj = httpResult.data;
        }
        return httpResult.copy(i, str, obj);
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

    public final HttpResult<T> copy(int code, String msg, T data) {
        return new HttpResult<>(code, msg, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HttpResult)) {
            return false;
        }
        HttpResult httpResult = (HttpResult) other;
        return this.code == httpResult.code && Intrinsics.areEqual(this.msg, httpResult.msg) && Intrinsics.areEqual(this.data, httpResult.data);
    }

    public int hashCode() {
        int i = this.code * 31;
        String str = this.msg;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        T t = this.data;
        return hashCode + (t != null ? t.hashCode() : 0);
    }

    public String toString() {
        return "HttpResult(code=" + this.code + ", msg=" + this.msg + ", data=" + this.data + ")";
    }

    public HttpResult(int i, String str, T t) {
        this.code = i;
        this.msg = str;
        this.data = t;
    }

    public /* synthetic */ HttpResult(int i, String str, Object obj, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? (String) null : str, (i2 & 4) != 0 ? null : obj);
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
        this.msg = str;
    }
}
