package com.pudutech.peanut.presenter.net.resp;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ShangLongBaseHttpResult.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B#\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\bJ\t\u0010\u0012\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\nJ6\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00018\u0000HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0006HÖ\u0001R\u0015\u0010\u0007\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/net/resp/ShangLongBaseHttpResult;", ExifInterface.GPS_DIRECTION_TRUE, "", "success", "", NotificationCompat.CATEGORY_MESSAGE, "", "data", "(ZLjava/lang/String;Ljava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getMsg", "()Ljava/lang/String;", "getSuccess", "()Z", "setSuccess", "(Z)V", "component1", "component2", "component3", "copy", "(ZLjava/lang/String;Ljava/lang/Object;)Lcom/pudutech/peanut/presenter/net/resp/ShangLongBaseHttpResult;", "equals", "other", "hashCode", "", "toString", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class ShangLongBaseHttpResult<T> {
    private final T data;
    private final String msg;
    private boolean success;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ShangLongBaseHttpResult copy$default(ShangLongBaseHttpResult shangLongBaseHttpResult, boolean z, String str, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            z = shangLongBaseHttpResult.success;
        }
        if ((i & 2) != 0) {
            str = shangLongBaseHttpResult.msg;
        }
        if ((i & 4) != 0) {
            obj = shangLongBaseHttpResult.data;
        }
        return shangLongBaseHttpResult.copy(z, str, obj);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getSuccess() {
        return this.success;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMsg() {
        return this.msg;
    }

    public final T component3() {
        return this.data;
    }

    public final ShangLongBaseHttpResult<T> copy(boolean success, String msg, T data) {
        return new ShangLongBaseHttpResult<>(success, msg, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ShangLongBaseHttpResult)) {
            return false;
        }
        ShangLongBaseHttpResult shangLongBaseHttpResult = (ShangLongBaseHttpResult) other;
        return this.success == shangLongBaseHttpResult.success && Intrinsics.areEqual(this.msg, shangLongBaseHttpResult.msg) && Intrinsics.areEqual(this.data, shangLongBaseHttpResult.data);
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
        String str = this.msg;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        T t = this.data;
        return hashCode + (t != null ? t.hashCode() : 0);
    }

    public String toString() {
        return "ShangLongBaseHttpResult(success=" + this.success + ", msg=" + this.msg + ", data=" + this.data + ")";
    }

    public ShangLongBaseHttpResult(boolean z, String str, T t) {
        this.success = z;
        this.msg = str;
        this.data = t;
    }

    public /* synthetic */ ShangLongBaseHttpResult(boolean z, String str, Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, str, obj);
    }

    public final T getData() {
        return this.data;
    }

    public final String getMsg() {
        return this.msg;
    }

    public final boolean getSuccess() {
        return this.success;
    }

    public final void setSuccess(boolean z) {
        this.success = z;
    }
}
