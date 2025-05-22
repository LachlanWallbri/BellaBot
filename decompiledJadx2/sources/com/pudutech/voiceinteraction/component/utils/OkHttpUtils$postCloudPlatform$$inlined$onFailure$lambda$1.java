package com.pudutech.voiceinteraction.component.utils;

import com.pudutech.base.Pdlog;
import com.pudutech.voiceinteraction.component.utils.OkHttpUtils;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: OkHttpUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/voiceinteraction/component/utils/OkHttpUtils$postCloudPlatform$4$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
final class OkHttpUtils$postCloudPlatform$$inlined$onFailure$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ OkHttpUtils.HttpCallbackInterface $callback$inlined;
    final /* synthetic */ Continuation $continuation$inlined;
    final /* synthetic */ Throwable $it;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7581p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OkHttpUtils$postCloudPlatform$$inlined$onFailure$lambda$1(Throwable th, Continuation continuation, Continuation continuation2, OkHttpUtils.HttpCallbackInterface httpCallbackInterface) {
        super(2, continuation);
        this.$it = th;
        this.$continuation$inlined = continuation2;
        this.$callback$inlined = httpCallbackInterface;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        OkHttpUtils$postCloudPlatform$$inlined$onFailure$lambda$1 okHttpUtils$postCloudPlatform$$inlined$onFailure$lambda$1 = new OkHttpUtils$postCloudPlatform$$inlined$onFailure$lambda$1(this.$it, completion, this.$continuation$inlined, this.$callback$inlined);
        okHttpUtils$postCloudPlatform$$inlined$onFailure$lambda$1.f7581p$ = (CoroutineScope) obj;
        return okHttpUtils$postCloudPlatform$$inlined$onFailure$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OkHttpUtils$postCloudPlatform$$inlined$onFailure$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7581p$;
        this.$callback$inlined.onErrorCallback(this.$it.toString());
        String tag = OkHttpUtils.INSTANCE.getTAG();
        StringBuilder sb = new StringBuilder();
        sb.append("postCloudPlatform onFailure ");
        sb.append(String.valueOf(this.$it.getMessage()));
        sb.append(" ThreadName=");
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
        sb.append(currentThread.getName());
        Pdlog.m3273d(tag, sb.toString());
        return Unit.INSTANCE;
    }
}
