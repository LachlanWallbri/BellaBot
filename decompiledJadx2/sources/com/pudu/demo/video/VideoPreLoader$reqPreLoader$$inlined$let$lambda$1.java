package com.pudu.demo.video;

import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: VideoPreLoader.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0006"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudu/demo/video/VideoPreLoader$reqPreLoader$1$1$1", "com/pudu/demo/video/VideoPreLoader$$special$$inlined$forEach$lambda$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class VideoPreLoader$reqPreLoader$$inlined$let$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef $proxyUrl;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4368p$;
    final /* synthetic */ VideoPreLoader this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoPreLoader$reqPreLoader$$inlined$let$lambda$1(Ref.ObjectRef objectRef, Continuation continuation, VideoPreLoader videoPreLoader) {
        super(2, continuation);
        this.$proxyUrl = objectRef;
        this.this$0 = videoPreLoader;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        VideoPreLoader$reqPreLoader$$inlined$let$lambda$1 videoPreLoader$reqPreLoader$$inlined$let$lambda$1 = new VideoPreLoader$reqPreLoader$$inlined$let$lambda$1(this.$proxyUrl, completion, this.this$0);
        videoPreLoader$reqPreLoader$$inlined$let$lambda$1.f4368p$ = (CoroutineScope) obj;
        return videoPreLoader$reqPreLoader$$inlined$let$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoPreLoader$reqPreLoader$$inlined$let$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f4368p$;
        this.this$0.realDownLoad((String) this.$proxyUrl.element);
        Pdlog.m3273d(this.this$0.getTAG(), "reqPreLoader--开始加载：" + ((String) this.$proxyUrl.element));
        return Unit.INSTANCE;
    }
}
