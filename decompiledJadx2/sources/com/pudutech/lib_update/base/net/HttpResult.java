package com.pudutech.lib_update.base.net;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HttpResult.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0017\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0002\u0010\u0006J\t\u0010\u000e\u001a\u00020\u0004HÆ\u0003J\u000e\u0010\u000f\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\fJ(\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u0011J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0013\u0010\u0005\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/lib_update/base/net/HttpResult;", ExifInterface.GPS_DIRECTION_TRUE, "", "code", "", "data", "(ILjava/lang/Object;)V", "getCode", "()I", "setCode", "(I)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "copy", "(ILjava/lang/Object;)Lcom/pudutech/lib_update/base/net/HttpResult;", "equals", "", "other", "hashCode", "toString", "", "lib_update_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class HttpResult<T> {
    private int code;
    private final T data;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HttpResult copy$default(HttpResult httpResult, int i, Object obj, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            i = httpResult.code;
        }
        if ((i2 & 2) != 0) {
            obj = httpResult.data;
        }
        return httpResult.copy(i, obj);
    }

    /* renamed from: component1, reason: from getter */
    public final int getCode() {
        return this.code;
    }

    public final T component2() {
        return this.data;
    }

    public final HttpResult<T> copy(int code, T data) {
        return new HttpResult<>(code, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HttpResult)) {
            return false;
        }
        HttpResult httpResult = (HttpResult) other;
        return this.code == httpResult.code && Intrinsics.areEqual(this.data, httpResult.data);
    }

    public int hashCode() {
        int i = this.code * 31;
        T t = this.data;
        return i + (t != null ? t.hashCode() : 0);
    }

    public String toString() {
        return "HttpResult(code=" + this.code + ", data=" + this.data + ")";
    }

    public HttpResult(int i, T t) {
        this.code = i;
        this.data = t;
    }

    public /* synthetic */ HttpResult(int i, Object obj, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, obj);
    }

    public final int getCode() {
        return this.code;
    }

    public final T getData() {
        return this.data;
    }

    public final void setCode(int i) {
        this.code = i;
    }
}
