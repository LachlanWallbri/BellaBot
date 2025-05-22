package com.pudutech.mirsdk.sdksafe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.sdksafe.LoginActivity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes6.dex
 */
/* compiled from: LoginActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000K\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J&\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00032\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J&\u0010\u000b\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J.\u0010\u000b\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u0016J \u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000e\u001a\u00020\u0017H\u0016J\u001c\u0010\u0018\u001a\u00020\u00192\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0003H\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m3961d2 = {"com/pudutech/mirsdk/sdksafe/LoginActivity$onCreate$1", "Landroid/webkit/WebViewClient;", "overrideUrl", "", "onPageStarted", "", "view", "Landroid/webkit/WebView;", "url", "favicon", "Landroid/graphics/Bitmap;", "onReceivedError", "request", "Landroid/webkit/WebResourceRequest;", "error", "Landroid/webkit/WebResourceError;", AUserTrack.UTKEY_ERROR_CODE, "", "description", "failingUrl", "onReceivedSslError", "handler", "Landroid/webkit/SslErrorHandler;", "Landroid/net/http/SslError;", "shouldOverrideUrlLoading", "", "sdksafe_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class LoginActivity$onCreate$1 extends WebViewClient {
    private String overrideUrl;
    final /* synthetic */ LoginActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LoginActivity$onCreate$1(LoginActivity loginActivity) {
        this.this$0 = loginActivity;
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        String str;
        if (url != null) {
            SDKSafe sDKSafe = SDKSafe.INSTANCE;
            str = this.this$0.node;
            sDKSafe.checkUrl$sdksafe_release(url, str, new Function1<LoginActivity.LoginResult, Unit>() { // from class: com.pudutech.mirsdk.sdksafe.LoginActivity$onCreate$1$onPageStarted$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(LoginActivity.LoginResult loginResult) {
                    invoke2(loginResult);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: LoginActivity.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                @DebugMetadata(m3969c = "com.pudutech.mirsdk.sdksafe.LoginActivity$onCreate$1$onPageStarted$1$1", m3970f = "LoginActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                /* renamed from: com.pudutech.mirsdk.sdksafe.LoginActivity$onCreate$1$onPageStarted$1$1 */
                /* loaded from: classes2.dex */
                public static final class C53031 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f6429p$;

                    C53031(Continuation continuation) {
                        super(2, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        C53031 c53031 = new C53031(completion);
                        c53031.f6429p$ = (CoroutineScope) obj;
                        return c53031;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C53031) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        LoginActivity$onCreate$1.this.this$0.getWebView$sdksafe_release().setVisibility(4);
                        return Unit.INSTANCE;
                    }
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LoginActivity.LoginResult result) {
                    Intrinsics.checkParameterIsNotNull(result, "result");
                    if (LoginActivity.WhenMappings.$EnumSwitchMapping$0[result.ordinal()] != 1) {
                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C53042(null), 2, null);
                    } else {
                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C53031(null), 2, null);
                    }
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: LoginActivity.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                @DebugMetadata(m3969c = "com.pudutech.mirsdk.sdksafe.LoginActivity$onCreate$1$onPageStarted$1$2", m3970f = "LoginActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                /* renamed from: com.pudutech.mirsdk.sdksafe.LoginActivity$onCreate$1$onPageStarted$1$2 */
                /* loaded from: classes2.dex */
                public static final class C53042 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f6430p$;

                    C53042(Continuation continuation) {
                        super(2, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        C53042 c53042 = new C53042(completion);
                        c53042.f6430p$ = (CoroutineScope) obj;
                        return c53042;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C53042) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        LoginActivity$onCreate$1.this.this$0.getWebView$sdksafe_release().loadUrl(SDKSafe.INSTANCE.getPage$sdksafe_release());
                        LoginActivity$onCreate$1.this.this$0.getWebView$sdksafe_release().setVisibility(0);
                        return Unit.INSTANCE;
                    }
                }
            });
        }
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        this.overrideUrl = url;
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        Intrinsics.checkParameterIsNotNull(error, "error");
        handler.proceed();
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        String str;
        int i;
        CharSequence description;
        CharSequence description2;
        str = this.this$0.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("received error: ");
        sb.append((error == null || (description2 = error.getDescription()) == null) ? null : description2.toString());
        objArr[0] = sb.toString();
        Pdlog.m3277w(str, objArr);
        this.this$0.getWebView$sdksafe_release().setVisibility(4);
        if (Intrinsics.areEqual((error == null || (description = error.getDescription()) == null) ? null : description.toString(), "net::ERR_INTERNET_DISCONNECTED")) {
            LoginActivity loginActivity = this.this$0;
            i = loginActivity.loginTime;
            loginActivity.loginTime = i + 1;
            this.this$0.startActivity(new Intent(this.this$0, (Class<?>) WifiSetActivity.class));
            return;
        }
        String str2 = this.overrideUrl;
        if (str2 == null || !StringsKt.contains$default((CharSequence) str2, (CharSequence) "https://rbotctrl.pudutech.com/login", false, 2, (Object) null)) {
            TextView textView = (TextView) this.this$0.findViewById(C5309R.id.error_msg);
            Intrinsics.checkExpressionValueIsNotNull(textView, "textView");
            textView.setText(this.this$0.getString(C5309R.string.error_info));
            textView.setVisibility(0);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3277w(str, "error: " + description + ", fail url: " + failingUrl);
    }
}
