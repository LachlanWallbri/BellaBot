package com.pudutech.mirsdk.sdksafe;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes6.dex
 */
/* compiled from: SDKSafeRequestInterface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J-\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\u0007\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\tJ-\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0003\u0010\u0007\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ-\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u00112\b\b\u0003\u0010\u0007\u001a\u00020\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/mirsdk/sdksafe/SDKSafeRequestInterface;", "", "chkNode", "Lcom/pudutech/mirsdk/sdksafe/ResponseMsg;", "Lcom/pudutech/mirsdk/sdksafe/ChkNodeResponse;", "request", "Lcom/pudutech/mirsdk/sdksafe/ChkNodeRequest;", "url", "", "(Lcom/pudutech/mirsdk/sdksafe/ChkNodeRequest;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loginPlat", "Lcom/pudutech/mirsdk/sdksafe/LoginResponse;", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "Lcom/pudutech/mirsdk/sdksafe/LoginRequest;", "(Lcom/pudutech/mirsdk/sdksafe/LoginRequest;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "token", "Lcom/pudutech/mirsdk/sdksafe/TokenResponse;", "Lcom/pudutech/mirsdk/sdksafe/TokenRequest;", "(Lcom/pudutech/mirsdk/sdksafe/TokenRequest;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sdksafe_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public interface SDKSafeRequestInterface {
    @POST
    Object chkNode(@Body ChkNodeRequest chkNodeRequest, @Url String str, Continuation<? super ResponseMsg<ChkNodeResponse>> continuation);

    @POST
    Object loginPlat(@Body LoginRequest loginRequest, @Url String str, Continuation<? super ResponseMsg<LoginResponse>> continuation);

    @POST
    Object token(@Body TokenRequest tokenRequest, @Url String str, Continuation<? super ResponseMsg<TokenResponse>> continuation);

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes6.dex
     */
    /* compiled from: SDKSafeRequestInterface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ Object loginPlat$default(SDKSafeRequestInterface sDKSafeRequestInterface, LoginRequest loginRequest, String str, Continuation continuation, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: loginPlat");
            }
            if ((i & 2) != 0) {
                str = "https://oauth2-test.pudutech.com/api/login/app";
            }
            return sDKSafeRequestInterface.loginPlat(loginRequest, str, continuation);
        }

        public static /* synthetic */ Object token$default(SDKSafeRequestInterface sDKSafeRequestInterface, TokenRequest tokenRequest, String str, Continuation continuation, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: token");
            }
            if ((i & 2) != 0) {
                str = "https://oauth2-test.pudutech.com/api/get_token/v1";
            }
            return sDKSafeRequestInterface.token(tokenRequest, str, continuation);
        }

        public static /* synthetic */ Object chkNode$default(SDKSafeRequestInterface sDKSafeRequestInterface, ChkNodeRequest chkNodeRequest, String str, Continuation continuation, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: chkNode");
            }
            if ((i & 2) != 0) {
                str = "https://auth-test.pudutech.com/api/inner/check_node/v1";
            }
            return sDKSafeRequestInterface.chkNode(chkNodeRequest, str, continuation);
        }
    }
}
