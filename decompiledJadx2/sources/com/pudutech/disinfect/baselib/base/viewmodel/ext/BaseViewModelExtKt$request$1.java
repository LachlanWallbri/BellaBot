package com.pudutech.disinfect.baselib.base.viewmodel.ext;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.MutableLiveData;
import com.pudutech.disinfect.baselib.ext.util.LogExtKt;
import com.pudutech.disinfect.baselib.network.BaseResponse;
import com.pudutech.disinfect.baselib.state.ResultState;
import com.pudutech.disinfect.baselib.state.ResultStateKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: BaseViewModelExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m3961d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.disinfect.baselib.base.viewmodel.ext.BaseViewModelExtKt$request$1", m3970f = "BaseViewModelExt.kt", m3971i = {0, 0}, m3972l = {102}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$runCatching"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes2.dex */
public final class BaseViewModelExtKt$request$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $block;
    final /* synthetic */ boolean $isShowDialog;
    final /* synthetic */ String $loadingMessage;
    final /* synthetic */ MutableLiveData $resultState;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5026p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseViewModelExtKt$request$1(boolean z, MutableLiveData mutableLiveData, String str, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.$isShowDialog = z;
        this.$resultState = mutableLiveData;
        this.$loadingMessage = str;
        this.$block = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        BaseViewModelExtKt$request$1 baseViewModelExtKt$request$1 = new BaseViewModelExtKt$request$1(this.$isShowDialog, this.$resultState, this.$loadingMessage, this.$block, completion);
        baseViewModelExtKt$request$1.f5026p$ = (CoroutineScope) obj;
        return baseViewModelExtKt$request$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseViewModelExtKt$request$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object m4510constructorimpl;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f5026p$;
                Result.Companion companion = Result.INSTANCE;
                if (this.$isShowDialog) {
                    this.$resultState.setValue(ResultState.INSTANCE.onLoading(this.$loadingMessage));
                }
                Function1 function1 = this.$block;
                this.L$0 = coroutineScope;
                this.L$1 = coroutineScope;
                this.label = 1;
                obj = function1.invoke(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            m4510constructorimpl = Result.m4510constructorimpl((BaseResponse) obj);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m4517isSuccessimpl(m4510constructorimpl)) {
            ResultStateKt.parseResult(this.$resultState, (BaseResponse) m4510constructorimpl);
        }
        Throwable m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl);
        if (m4513exceptionOrNullimpl != null) {
            String message = m4513exceptionOrNullimpl.getMessage();
            if (message != null) {
                LogExtKt.logE$default(message, null, 1, null);
            }
            ResultStateKt.parseException(this.$resultState, m4513exceptionOrNullimpl);
        }
        return Unit.INSTANCE;
    }
}
