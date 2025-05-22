package com.pudutech.disinfect.baselib.state;

import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import com.loc.C3898x;
import com.pudutech.remotemaintenance.config.IoTConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: AppException.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\r\u0018\u00002\u00060\u0001j\u0002`\u0002B%\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\bB\u0019\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/state/AppException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", AUserTrack.UTKEY_ERROR_CODE, "", "error", "", "errorLog", "(ILjava/lang/String;Ljava/lang/String;)V", "Lcom/pudutech/disinfect/baselib/state/Error;", C3898x.f4338g, "", "(Lcom/pudutech/disinfect/baselib/state/Error;Ljava/lang/Throwable;)V", "getErrorCode", "()I", "setErrorCode", "(I)V", "getErrorLog", "()Ljava/lang/String;", "setErrorLog", "(Ljava/lang/String;)V", IoTConfig.PARAM_ERROR_MSG, "getErrorMsg", "setErrorMsg", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class AppException extends Exception {
    private int errorCode;
    private String errorLog;
    private String errorMsg;

    public final String getErrorMsg() {
        return this.errorMsg;
    }

    public final void setErrorMsg(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.errorMsg = str;
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    public final void setErrorCode(int i) {
        this.errorCode = i;
    }

    public final String getErrorLog() {
        return this.errorLog;
    }

    public final void setErrorLog(String str) {
        this.errorLog = str;
    }

    public AppException(int i, String str, String str2) {
        super(str);
        this.errorMsg = str == null ? "请求失败,请重试" : str;
        this.errorCode = i;
        this.errorLog = str2 == null ? this.errorMsg : str2;
    }

    public /* synthetic */ AppException(int i, String str, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, (i2 & 4) != 0 ? "" : str2);
    }

    public AppException(Error error, Throwable th) {
        Intrinsics.checkParameterIsNotNull(error, "error");
        this.errorCode = error.getCode();
        this.errorMsg = error.getErr();
        this.errorLog = th != null ? th.getMessage() : null;
    }
}
