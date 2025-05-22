package com.pudutech.disinfect.baselib.base.viewmodel;

import androidx.exifinterface.media.ExifInterface;
import com.pudutech.disinfect.baselib.network.BaseResponse;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: BaseViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, m3961d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;", "t", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel$request$2$2$1$1", "com/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel$request$2$$special$$inlined$runCatching$lambda$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel$request$2$invokeSuspend$$inlined$onSuccess$lambda$1 */
/* loaded from: classes2.dex */
public final class C4432x3db2ba79<T> extends SuspendLambda implements Function3<CoroutineScope, T, Continuation<? super Unit>, Object> {
    final /* synthetic */ BaseResponse $it$inlined;
    final /* synthetic */ CoroutineScope $this_launch$inlined;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5009p$;
    private Object p$0;
    final /* synthetic */ BaseViewModel$request$2 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C4432x3db2ba79(Continuation continuation, BaseResponse baseResponse, BaseViewModel$request$2 baseViewModel$request$2, CoroutineScope coroutineScope) {
        super(3, continuation);
        this.$it$inlined = baseResponse;
        this.this$0 = baseViewModel$request$2;
        this.$this_launch$inlined = coroutineScope;
    }

    public final Continuation<Unit> create(CoroutineScope create, T t, Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(create, "$this$create");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        C4432x3db2ba79 c4432x3db2ba79 = new C4432x3db2ba79(continuation, this.$it$inlined, this.this$0, this.$this_launch$inlined);
        c4432x3db2ba79.f5009p$ = create;
        c4432x3db2ba79.p$0 = t;
        return c4432x3db2ba79;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, Object obj, Continuation<? super Unit> continuation) {
        return ((C4432x3db2ba79) create(coroutineScope, obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5009p$;
        this.this$0.$success.invoke(this.p$0);
        return Unit.INSTANCE;
    }
}
