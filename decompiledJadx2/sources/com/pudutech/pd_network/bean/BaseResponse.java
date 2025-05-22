package com.pudutech.pd_network.bean;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* compiled from: BaseResponse.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u0017J\b\u0010\u0019\u001a\u00020\u0011H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001e\u0010\n\u001a\u0004\u0018\u00018\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/BaseResponse;", ExifInterface.GPS_DIRECTION_TRUE, "", "()V", "code", "", "getCode", "()J", "setCode", "(J)V", "data", "getData", "()Ljava/lang/Object;", "setData", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "message", "", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "isSuccess", "", "isTokenInvalid", "toString", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public class BaseResponse<T> {
    private T data;
    private String message = "BaseResponse Init Error";
    private long code = -1;

    public final String getMessage() {
        return this.message;
    }

    public final void setMessage(String str) {
        this.message = str;
    }

    public final long getCode() {
        return this.code;
    }

    public final void setCode(long j) {
        this.code = j;
    }

    public final T getData() {
        return this.data;
    }

    public final void setData(T t) {
        this.data = t;
    }

    public String toString() {
        return "BaseResponse(message=" + this.message + ", code=" + this.code + ", data=" + this.data + ')';
    }

    public final boolean isTokenInvalid() {
        long j = this.code;
        return j == 1011 || j == 1012 || j == 100100011101L || j == 100100011102L || j == 108101911102L || j == 108101911101L;
    }

    public final boolean isSuccess() {
        long j = this.code;
        return j == 0 || j == 200;
    }
}
