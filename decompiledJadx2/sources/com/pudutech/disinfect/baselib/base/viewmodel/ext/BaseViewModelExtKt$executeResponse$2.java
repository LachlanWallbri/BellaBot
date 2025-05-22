package com.pudutech.disinfect.baselib.base.viewmodel.ext;

import androidx.exifinterface.media.ExifInterface;
import com.pudutech.disinfect.baselib.network.BaseResponse;
import com.pudutech.disinfect.baselib.state.AppException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: BaseViewModelExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m3961d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.disinfect.baselib.base.viewmodel.ext.BaseViewModelExtKt$executeResponse$2", m3970f = "BaseViewModelExt.kt", m3971i = {0}, m3972l = {204}, m3973m = "invokeSuspend", m3974n = {"$this$coroutineScope"}, m3975s = {"L$0"})
/* loaded from: classes2.dex */
final class BaseViewModelExtKt$executeResponse$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ BaseResponse $response;
    final /* synthetic */ Function3 $success;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5024p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseViewModelExtKt$executeResponse$2(BaseResponse baseResponse, Function3 function3, Continuation continuation) {
        super(2, continuation);
        this.$response = baseResponse;
        this.$success = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        BaseViewModelExtKt$executeResponse$2 baseViewModelExtKt$executeResponse$2 = new BaseViewModelExtKt$executeResponse$2(this.$response, this.$success, completion);
        baseViewModelExtKt$executeResponse$2.f5024p$ = (CoroutineScope) obj;
        return baseViewModelExtKt$executeResponse$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseViewModelExtKt$executeResponse$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5024p$;
            if (!this.$response.isSuccess()) {
                throw new AppException(this.$response.getResponseCode(), this.$response.getResponseMsg(), this.$response.getResponseMsg());
            }
            Function3 function3 = this.$success;
            Object responseData = this.$response.getResponseData();
            this.L$0 = coroutineScope;
            this.label = 1;
            if (function3.invoke(coroutineScope, responseData, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
