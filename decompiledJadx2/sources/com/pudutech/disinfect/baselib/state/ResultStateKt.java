package com.pudutech.disinfect.baselib.state;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.MutableLiveData;
import com.iflytek.cloud.SpeechUtility;
import com.loc.C3898x;
import com.pudutech.disinfect.baselib.network.BaseResponse;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: ResultState.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006\u001a)\u0010\u0007\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u00032\u0006\u0010\b\u001a\u0002H\u0002¢\u0006\u0002\u0010\t\u001a*\u0010\u0007\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\n¨\u0006\u000b"}, m3961d2 = {"parseException", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/MutableLiveData;", "Lcom/pudutech/disinfect/baselib/state/ResultState;", C3898x.f4338g, "", "parseResult", SpeechUtility.TAG_RESOURCE_RESULT, "(Landroidx/lifecycle/MutableLiveData;Ljava/lang/Object;)V", "Lcom/pudutech/disinfect/baselib/network/BaseResponse;", "module_baselib_robot_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ResultStateKt {
    public static final <T> void parseResult(MutableLiveData<ResultState<T>> parseResult, BaseResponse<T> result) {
        Intrinsics.checkParameterIsNotNull(parseResult, "$this$parseResult");
        Intrinsics.checkParameterIsNotNull(result, "result");
        parseResult.setValue(result.isSuccess() ? ResultState.INSTANCE.onSuccess(result.getResponseData()) : ResultState.INSTANCE.onError(new AppException(result.getResponseCode(), result.getResponseMsg(), null, 4, null)));
    }

    public static final <T> void parseResult(MutableLiveData<ResultState<T>> parseResult, T t) {
        Intrinsics.checkParameterIsNotNull(parseResult, "$this$parseResult");
        parseResult.setValue(ResultState.INSTANCE.onSuccess(t));
    }

    public static final <T> void parseException(MutableLiveData<ResultState<T>> parseException, Throwable e) {
        Intrinsics.checkParameterIsNotNull(parseException, "$this$parseException");
        Intrinsics.checkParameterIsNotNull(e, "e");
        parseException.setValue(ResultState.INSTANCE.onError(ExceptionHandle.INSTANCE.handleException(e)));
    }
}
