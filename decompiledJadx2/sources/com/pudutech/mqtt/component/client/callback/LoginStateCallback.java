package com.pudutech.mqtt.component.client.callback;

import com.pudutech.mqtt.component.client.config.LoginState;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: LoginStateCallback.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mqtt/component/client/callback/LoginStateCallback;", "", "callbackLoginState", "", "state", "Lcom/pudutech/mqtt/component/client/config/LoginState;", "errMsg", "", "component_mqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface LoginStateCallback {
    void callbackLoginState(LoginState state, String errMsg);

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* compiled from: LoginStateCallback.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void callbackLoginState$default(LoginStateCallback loginStateCallback, LoginState loginState, String str, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: callbackLoginState");
            }
            if ((i & 2) != 0) {
                str = (String) null;
            }
            loginStateCallback.callbackLoginState(loginState, str);
        }
    }
}
