package com.pudutech.tts_sdk.tts;

import com.pudutech.disklru.DiskLruCacheManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: GoogleTtsTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/tts_sdk/tts/GoogleTtsTask$genTtsFile$1$1$3"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
final class GoogleTtsTask$genTtsFile$1$invokeSuspend$$inlined$let$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7511p$;
    final /* synthetic */ GoogleTtsTask$genTtsFile$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GoogleTtsTask$genTtsFile$1$invokeSuspend$$inlined$let$lambda$1(Continuation continuation, GoogleTtsTask$genTtsFile$1 googleTtsTask$genTtsFile$1) {
        super(2, continuation);
        this.this$0 = googleTtsTask$genTtsFile$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        GoogleTtsTask$genTtsFile$1$invokeSuspend$$inlined$let$lambda$1 googleTtsTask$genTtsFile$1$invokeSuspend$$inlined$let$lambda$1 = new GoogleTtsTask$genTtsFile$1$invokeSuspend$$inlined$let$lambda$1(completion, this.this$0);
        googleTtsTask$genTtsFile$1$invokeSuspend$$inlined$let$lambda$1.f7511p$ = (CoroutineScope) obj;
        return googleTtsTask$genTtsFile$1$invokeSuspend$$inlined$let$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GoogleTtsTask$genTtsFile$1$invokeSuspend$$inlined$let$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7511p$;
        DiskLruCacheManager.INSTANCE.saveDiskCache(this.this$0.$filePath, DiskLruCacheManager.INSTANCE.genDiskLruKey(this.this$0.$filePath, this.this$0.$text, this.this$0.$extraParam));
        this.this$0.$onTtsListener.onComplete(this.this$0.$filePath);
        return Unit.INSTANCE;
    }
}
