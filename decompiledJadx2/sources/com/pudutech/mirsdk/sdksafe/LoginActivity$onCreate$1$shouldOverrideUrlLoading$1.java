package com.pudutech.mirsdk.sdksafe;

import android.widget.TextView;
import com.iflytek.cloud.SpeechUtility;
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
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: LoginActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", SpeechUtility.TAG_RESOURCE_RESULT, "Lcom/pudutech/mirsdk/sdksafe/LoginActivity$LoginResult;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class LoginActivity$onCreate$1$shouldOverrideUrlLoading$1 extends Lambda implements Function1<LoginActivity.LoginResult, Unit> {
    final /* synthetic */ LoginActivity$onCreate$1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LoginActivity$onCreate$1$shouldOverrideUrlLoading$1(LoginActivity$onCreate$1 loginActivity$onCreate$1) {
        super(1);
        this.this$0 = loginActivity$onCreate$1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(LoginActivity.LoginResult loginResult) {
        invoke2(loginResult);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(LoginActivity.LoginResult result) {
        Intrinsics.checkParameterIsNotNull(result, "result");
        if (LoginActivity.WhenMappings.$EnumSwitchMapping$0[result.ordinal()] == 1) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C53051(null), 2, null);
            return;
        }
        if (result == LoginActivity.LoginResult.AuthFailed) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C53062(null), 2, null);
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C53073(null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: LoginActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.sdksafe.LoginActivity$onCreate$1$shouldOverrideUrlLoading$1$1", m3970f = "LoginActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdk.sdksafe.LoginActivity$onCreate$1$shouldOverrideUrlLoading$1$1 */
    /* loaded from: classes6.dex */
    public static final class C53051 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6431p$;

        C53051(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C53051 c53051 = new C53051(completion);
            c53051.f6431p$ = (CoroutineScope) obj;
            return c53051;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C53051) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6431p$;
            ((ProgressWebView) LoginActivity$onCreate$1$shouldOverrideUrlLoading$1.this.this$0.$webView.element).setVisibility(4);
            TextView tvAuth = (TextView) LoginActivity$onCreate$1$shouldOverrideUrlLoading$1.this.this$0.$tvAuth.element;
            Intrinsics.checkExpressionValueIsNotNull(tvAuth, "tvAuth");
            tvAuth.setVisibility(0);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: LoginActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.sdksafe.LoginActivity$onCreate$1$shouldOverrideUrlLoading$1$2", m3970f = "LoginActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdk.sdksafe.LoginActivity$onCreate$1$shouldOverrideUrlLoading$1$2 */
    /* loaded from: classes6.dex */
    public static final class C53062 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6432p$;

        C53062(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C53062 c53062 = new C53062(completion);
            c53062.f6432p$ = (CoroutineScope) obj;
            return c53062;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C53062) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6432p$;
            LoginActivity loginActivity = LoginActivity$onCreate$1$shouldOverrideUrlLoading$1.this.this$0.this$0;
            String string = LoginActivity$onCreate$1$shouldOverrideUrlLoading$1.this.this$0.this$0.getString(C5309R.string.safe_auth_failed);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.safe_auth_failed)");
            loginActivity.showMessage(string);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: LoginActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.sdksafe.LoginActivity$onCreate$1$shouldOverrideUrlLoading$1$3", m3970f = "LoginActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdk.sdksafe.LoginActivity$onCreate$1$shouldOverrideUrlLoading$1$3 */
    /* loaded from: classes6.dex */
    public static final class C53073 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6433p$;

        C53073(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C53073 c53073 = new C53073(completion);
            c53073.f6433p$ = (CoroutineScope) obj;
            return c53073;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C53073) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6433p$;
            ((ProgressWebView) LoginActivity$onCreate$1$shouldOverrideUrlLoading$1.this.this$0.$webView.element).loadUrl(SDKSafe.INSTANCE.getPage$SDKSafe_release());
            ((ProgressWebView) LoginActivity$onCreate$1$shouldOverrideUrlLoading$1.this.this$0.$webView.element).setVisibility(0);
            TextView tvAuth = (TextView) LoginActivity$onCreate$1$shouldOverrideUrlLoading$1.this.this$0.$tvAuth.element;
            Intrinsics.checkExpressionValueIsNotNull(tvAuth, "tvAuth");
            tvAuth.setVisibility(8);
            return Unit.INSTANCE;
        }
    }
}
