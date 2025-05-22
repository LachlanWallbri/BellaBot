package com.pudutech.disinfect.baselib.oss;

import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: OssDropUpload.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.disinfect.baselib.oss.OssDropUpload$upLoadCliffIrImg$1", m3970f = "OssDropUpload.kt", m3971i = {0}, m3972l = {102}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
final class OssDropUpload$upLoadCliffIrImg$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ byte[] $buf;
    final /* synthetic */ Ref.ObjectRef $objectKey;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5059p$;
    final /* synthetic */ OssDropUpload this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OssDropUpload$upLoadCliffIrImg$1(OssDropUpload ossDropUpload, Ref.ObjectRef objectRef, byte[] bArr, Continuation continuation) {
        super(2, continuation);
        this.this$0 = ossDropUpload;
        this.$objectKey = objectRef;
        this.$buf = bArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        OssDropUpload$upLoadCliffIrImg$1 ossDropUpload$upLoadCliffIrImg$1 = new OssDropUpload$upLoadCliffIrImg$1(this.this$0, this.$objectKey, this.$buf, completion);
        ossDropUpload$upLoadCliffIrImg$1.f5059p$ = (CoroutineScope) obj;
        return ossDropUpload$upLoadCliffIrImg$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OssDropUpload$upLoadCliffIrImg$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5059p$;
            Pdlog.m3273d(this.this$0.getTAG(), "CoroutineScope coming");
            OssDropUpload ossDropUpload = this.this$0;
            String str = (String) this.$objectKey.element;
            byte[] bArr = this.$buf;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (ossDropUpload.updata(str, bArr, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        Pdlog.m3273d(this.this$0.getTAG(), "CoroutineScope end");
        return Unit.INSTANCE;
    }
}
