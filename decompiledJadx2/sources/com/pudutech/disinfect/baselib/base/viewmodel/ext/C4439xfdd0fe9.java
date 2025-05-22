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
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, m3961d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/disinfect/baselib/base/viewmodel/ext/BaseViewModelExtKt$requestNetworkDataCallMain$2$2$2$1", "com/pudutech/disinfect/baselib/base/viewmodel/ext/BaseViewModelExtKt$requestNetworkDataCallMain$2$$special$$inlined$onFailure$lambda$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.disinfect.baselib.base.viewmodel.ext.BaseViewModelExtKt$requestNetworkDataCallMain$2$invokeSuspend$$inlined$onSuccess$lambda$2 */
/* loaded from: classes2.dex */
final class C4439xfdd0fe9 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: $e */
    final /* synthetic */ Throwable f5022$e;
    final /* synthetic */ CoroutineScope $this_launch$inlined;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5023p$;
    final /* synthetic */ BaseViewModelExtKt$requestNetworkDataCallMain$2 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C4439xfdd0fe9(Throwable th, Continuation continuation, BaseViewModelExtKt$requestNetworkDataCallMain$2 baseViewModelExtKt$requestNetworkDataCallMain$2, CoroutineScope coroutineScope) {
        super(2, continuation);
        this.f5022$e = th;
        this.this$0 = baseViewModelExtKt$requestNetworkDataCallMain$2;
        this.$this_launch$inlined = coroutineScope;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C4439xfdd0fe9 c4439xfdd0fe9 = new C4439xfdd0fe9(this.f5022$e, completion, this.this$0, this.$this_launch$inlined);
        c4439xfdd0fe9.f5023p$ = (CoroutineScope) obj;
        return c4439xfdd0fe9;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C4439xfdd0fe9) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5023p$;
        this.this$0.$error.invoke(ExceptionHandle.INSTANCE.handleException(this.f5022$e));
        return Unit.INSTANCE;
    }
}
