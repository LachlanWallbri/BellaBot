package com.pudutech.mirsdk.sdksafe;

import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes6.dex
 */
/* compiled from: SDKSafeRequest.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\b¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/mirsdk/sdksafe/LoginRequest;", "", "()V", "account", "", "getAccount", "()Ljava/lang/String;", "setAccount", "(Ljava/lang/String;)V", "appid", "getAppid", "setAppid", CognitoUserPoolsSignInProvider.AttributeKeys.PASSWORD, "getPassword", "setPassword", "sdksafe_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class LoginRequest {
    private String appid = "";
    private String account = "";
    private String password = "";

    public final String getAppid() {
        return this.appid;
    }

    public final void setAppid(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.appid = str;
    }

    public final String getAccount() {
        return this.account;
    }

    public final void setAccount(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.account = str;
    }

    public final String getPassword() {
        return this.password;
    }

    public final void setPassword(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.password = str;
    }
}
