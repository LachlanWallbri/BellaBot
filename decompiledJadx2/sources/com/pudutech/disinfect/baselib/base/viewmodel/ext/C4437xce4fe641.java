package com.pudutech.disinfect.baselib.base.viewmodel.ext;

import androidx.exifinterface.media.ExifInterface;
import com.pudutech.disinfect.baselib.state.ExceptionHandle;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: BaseViewModelExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, m3961d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/disinfect/baselib/base/viewmodel/ext/BaseViewModelExtKt$requestNetworkDataCallMain$2$3$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.disinfect.baselib.base.viewmodel.ext.BaseViewModelExtKt$requestNetworkDataCallMain$2$invokeSuspend$$inlined$onFailure$lambda$1 */
/* loaded from: classes2.dex */
final class C4437xce4fe641 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Throwable $it;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5018p$;
    final /* synthetic */ BaseViewModelExtKt$requestNetworkDataCallMain$2 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C4437xce4fe641(Throwable th, Continuation continuation, BaseViewModelExtKt$requestNetworkDataCallMain$2 baseViewModelExtKt$requestNetworkDataCallMain$2) {
        super(2, continuation);
        this.$it = th;
        this.this$0 = baseViewModelExtKt$requestNetworkDataCallMain$2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C4437xce4fe641 c4437xce4fe641 = new C4437xce4fe641(this.$it, completion, this.this$0);
        c4437xce4fe641.f5018p$ = (CoroutineScope) obj;
        return c4437xce4fe641;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C4437xce4fe641) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5018p$;
        this.this$0.$error.invoke(ExceptionHandle.INSTANCE.handleException(this.$it));
        return Unit.INSTANCE;
    }
}
