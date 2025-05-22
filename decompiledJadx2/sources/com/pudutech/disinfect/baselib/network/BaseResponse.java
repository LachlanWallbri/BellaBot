package com.pudutech.disinfect.baselib.network;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: BaseResponse.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\r\u0010\u0006\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/BaseResponse;", ExifInterface.GPS_DIRECTION_TRUE, "", "()V", "getResponseCode", "", "getResponseData", "()Ljava/lang/Object;", "getResponseMsg", "", "isSuccess", "", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public abstract class BaseResponse<T> {
    public abstract int getResponseCode();

    public abstract T getResponseData();

    public abstract String getResponseMsg();

    public abstract boolean isSuccess();
}
