package com.pudutech.disinfect.baselib.base.viewmodel.ext;

import androidx.exifinterface.media.ExifInterface;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.disinfect.baselib.ext.util.LogExtKt;
import com.pudutech.disinfect.baselib.network.BaseResponse;
import com.pudutech.disinfect.baselib.state.ExceptionHandle;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
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
@DebugMetadata(m3969c = "com.pudutech.disinfect.baselib.base.viewmodel.ext.BaseViewModelExtKt$request$3", m3970f = "BaseViewModelExt.kt", m3971i = {0, 0, 1, 1, 1}, m3972l = {134, 140}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$runCatching", "$this$launch", "it", "$this$runCatching"}, m3975s = {"L$0", "L$1", "L$0", "L$2", "L$3"})
/* loaded from: classes2.dex */
public final class BaseViewModelExtKt$request$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $block;
    final /* synthetic */ Function1 $error;
    final /* synthetic */ boolean $isShowDialog;
    final /* synthetic */ String $loadingMessage;
    final /* synthetic */ Function1 $success;
    final /* synthetic */ BaseViewModel $this_request;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5027p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseViewModelExtKt$request$3(BaseViewModel baseViewModel, boolean z, String str, Function1 function1, Function1 function12, Function1 function13, Continuation continuation) {
        super(2, continuation);
        this.$this_request = baseViewModel;
        this.$isShowDialog = z;
        this.$loadingMessage = str;
        this.$block = function1;
        this.$success = function12;
        this.$error = function13;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        BaseViewModelExtKt$request$3 baseViewModelExtKt$request$3 = new BaseViewModelExtKt$request$3(this.$this_request, this.$isShowDialog, this.$loadingMessage, this.$block, this.$success, this.$error, completion);
        baseViewModelExtKt$request$3.f5027p$ = (CoroutineScope) obj;
        return baseViewModelExtKt$request$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseViewModelExtKt$request$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00fa  */
    /* JADX WARN: Type inference failed for: r1v11, types: [java.lang.Object, kotlinx.coroutines.CoroutineScope] */
    /* JADX WARN: Type inference failed for: r1v16, types: [kotlinx.coroutines.CoroutineScope] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Object, kotlinx.coroutines.CoroutineScope] */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object m4510constructorimpl;
        ?? r1;
        BaseViewModel baseViewModel;
        C4435xdb4fab3e c4435xdb4fab3e;
        Object obj2;
        Object obj3;
        Throwable m4513exceptionOrNullimpl;
        Throwable m4513exceptionOrNullimpl2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
        } catch (Throwable th) {
            Result.Companion companion = Result.INSTANCE;
            m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
            r1 = i;
        }
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ?? r12 = this.f5027p$;
            Result.Companion companion2 = Result.INSTANCE;
            if (this.$isShowDialog) {
                this.$this_request.getLoadingChange().getShowDialog().postValue(this.$loadingMessage);
            }
            Function1 function1 = this.$block;
            this.L$0 = r12;
            this.L$1 = r12;
            this.label = 1;
            obj = function1.invoke(this);
            i = r12;
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    obj2 = this.L$1;
                    try {
                        ResultKt.throwOnFailure(obj);
                        obj3 = Result.m4510constructorimpl(Unit.INSTANCE);
                    } catch (Throwable th2) {
                        Object obj4 = obj2;
                        th = th2;
                        m4510constructorimpl = obj4;
                        Result.Companion companion3 = Result.INSTANCE;
                        Object m4510constructorimpl2 = Result.m4510constructorimpl(ResultKt.createFailure(th));
                        obj2 = m4510constructorimpl;
                        obj3 = m4510constructorimpl2;
                        m4513exceptionOrNullimpl2 = Result.m4513exceptionOrNullimpl(obj3);
                        if (m4513exceptionOrNullimpl2 != null) {
                        }
                        m4510constructorimpl = obj2;
                        m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl);
                        if (m4513exceptionOrNullimpl != null) {
                        }
                        return Unit.INSTANCE;
                    }
                    m4513exceptionOrNullimpl2 = Result.m4513exceptionOrNullimpl(obj3);
                    if (m4513exceptionOrNullimpl2 != null) {
                        Pdlog.m3274e("BaseViewModel", "request fail " + m4513exceptionOrNullimpl2.getMessage());
                        this.$error.invoke(ExceptionHandle.INSTANCE.handleException(m4513exceptionOrNullimpl2));
                    }
                    m4510constructorimpl = obj2;
                    m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl);
                    if (m4513exceptionOrNullimpl != null) {
                        this.$this_request.getLoadingChange().getDismissDialog().postValue(Boxing.boxBoolean(false));
                        String message = m4513exceptionOrNullimpl.getMessage();
                        if (message != null) {
                            LogExtKt.logE$default(message, null, 1, null);
                        }
                        Pdlog.m3274e("BaseViewModel", "request fail " + m4513exceptionOrNullimpl.getMessage());
                        this.$error.invoke(ExceptionHandle.INSTANCE.handleException(m4513exceptionOrNullimpl));
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ?? r13 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            i = r13;
        }
        m4510constructorimpl = Result.m4510constructorimpl((BaseResponse) obj);
        r1 = i;
        if (Result.m4517isSuccessimpl(m4510constructorimpl)) {
            BaseResponse baseResponse = (BaseResponse) m4510constructorimpl;
            this.$this_request.getLoadingChange().getDismissDialog().postValue(Boxing.boxBoolean(false));
            try {
                Result.Companion companion4 = Result.INSTANCE;
                baseViewModel = this.$this_request;
                c4435xdb4fab3e = new C4435xdb4fab3e(null, baseResponse, this, r1);
                this.L$0 = r1;
                this.L$1 = m4510constructorimpl;
                this.L$2 = baseResponse;
                this.L$3 = r1;
                this.label = 2;
            } catch (Throwable th3) {
                th = th3;
                Result.Companion companion32 = Result.INSTANCE;
                Object m4510constructorimpl22 = Result.m4510constructorimpl(ResultKt.createFailure(th));
                obj2 = m4510constructorimpl;
                obj3 = m4510constructorimpl22;
                m4513exceptionOrNullimpl2 = Result.m4513exceptionOrNullimpl(obj3);
                if (m4513exceptionOrNullimpl2 != null) {
                }
                m4510constructorimpl = obj2;
                m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl);
                if (m4513exceptionOrNullimpl != null) {
                }
                return Unit.INSTANCE;
            }
            if (baseViewModel.executeResponse(baseResponse, c4435xdb4fab3e, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj2 = m4510constructorimpl;
            obj3 = Result.m4510constructorimpl(Unit.INSTANCE);
            m4513exceptionOrNullimpl2 = Result.m4513exceptionOrNullimpl(obj3);
            if (m4513exceptionOrNullimpl2 != null) {
            }
            m4510constructorimpl = obj2;
        }
        m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl);
        if (m4513exceptionOrNullimpl != null) {
        }
        return Unit.INSTANCE;
    }
}
