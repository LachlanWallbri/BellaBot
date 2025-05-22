package com.pudutech.disinfect.baselib.network.response;

import androidx.exifinterface.media.ExifInterface;
import com.pudutech.disinfect.baselib.network.BaseResponse;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: LocalResponse.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0002\u0010\u0004J\u000e\u0010\t\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u0006J\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u000bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\r\u0010\u0012\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0006J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\t\u0010\u0015\u001a\u00020\u0011HÖ\u0001J\b\u0010\u0016\u001a\u00020\rH\u0016J\t\u0010\u0017\u001a\u00020\u0014HÖ\u0001R\u001c\u0010\u0003\u001a\u00028\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\b\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/LocalResponse;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/pudutech/disinfect/baselib/network/BaseResponse;", "data", "(Ljava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "setData", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lcom/pudutech/disinfect/baselib/network/response/LocalResponse;", "equals", "", "other", "", "getResponseCode", "", "getResponseData", "getResponseMsg", "", "hashCode", "isSuccess", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class LocalResponse<T> extends BaseResponse<T> {
    private T data;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LocalResponse copy$default(LocalResponse localResponse, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = localResponse.data;
        }
        return localResponse.copy(obj);
    }

    public final T component1() {
        return this.data;
    }

    public final LocalResponse<T> copy(T data) {
        return new LocalResponse<>(data);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof LocalResponse) && Intrinsics.areEqual(this.data, ((LocalResponse) other).data);
        }
        return true;
    }

    @Override // com.pudutech.disinfect.baselib.network.BaseResponse
    public int getResponseCode() {
        return 0;
    }

    @Override // com.pudutech.disinfect.baselib.network.BaseResponse
    public String getResponseMsg() {
        return "";
    }

    public int hashCode() {
        T t = this.data;
        if (t != null) {
            return t.hashCode();
        }
        return 0;
    }

    @Override // com.pudutech.disinfect.baselib.network.BaseResponse
    public boolean isSuccess() {
        return false;
    }

    public String toString() {
        return "LocalResponse(data=" + this.data + ")";
    }

    public LocalResponse(T t) {
        this.data = t;
    }

    public final T getData() {
        return this.data;
    }

    public final void setData(T t) {
        this.data = t;
    }

    @Override // com.pudutech.disinfect.baselib.network.BaseResponse
    public T getResponseData() {
        return this.data;
    }
}
