package com.pudutech.disinfect.baselib.base.viewmodel.ext;

import androidx.exifinterface.media.ExifInterface;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.BaseResponse;
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
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BaseViewModelExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m3961d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.disinfect.baselib.base.viewmodel.ext.BaseViewModelExtKt$requestNetworkDataCallMain$2", m3970f = "BaseViewModelExt.kt", m3971i = {0, 0, 1, 1, 1, 2, 2, 2, 3, 3}, m3972l = {293, 298, 306, 313}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$runCatching", "$this$launch", "it", "$this$runCatching", "$this$launch", "it", C3898x.f4338g, "$this$launch", "it"}, m3975s = {"L$0", "L$1", "L$0", "L$2", "L$3", "L$0", "L$2", "L$4", "L$0", "L$2"})
/* loaded from: classes2.dex */
public final class BaseViewModelExtKt$requestNetworkDataCallMain$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $block;
    final /* synthetic */ Function1 $error;
    final /* synthetic */ Function1 $success;
    final /* synthetic */ String $tag;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5029p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseViewModelExtKt$requestNetworkDataCallMain$2(String str, Function1 function1, Function1 function12, Function1 function13, Continuation continuation) {
        super(2, continuation);
        this.$tag = str;
        this.$block = function1;
        this.$success = function12;
        this.$error = function13;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        BaseViewModelExtKt$requestNetworkDataCallMain$2 baseViewModelExtKt$requestNetworkDataCallMain$2 = new BaseViewModelExtKt$requestNetworkDataCallMain$2(this.$tag, this.$block, this.$success, this.$error, completion);
        baseViewModelExtKt$requestNetworkDataCallMain$2.f5029p$ = (CoroutineScope) obj;
        return baseViewModelExtKt$requestNetworkDataCallMain$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseViewModelExtKt$requestNetworkDataCallMain$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00fd  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object m4510constructorimpl;
        CoroutineScope coroutineScope;
        Object obj2;
        BaseResponse baseResponse;
        C4438xfdd0fe8 c4438xfdd0fe8;
        Object m4510constructorimpl2;
        Throwable m4513exceptionOrNullimpl;
        Object obj3;
        CoroutineScope coroutineScope2;
        Throwable m4513exceptionOrNullimpl2;
        CoroutineScope coroutineScope3;
        Object invoke;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
        } catch (Throwable th) {
            Result.Companion companion = Result.INSTANCE;
            m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
        }
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope3 = this.f5029p$;
            Result.Companion companion2 = Result.INSTANCE;
            Pdlog.m3273d(this.$tag, "runCatching请求数据");
            Function1 function1 = this.$block;
            this.L$0 = coroutineScope3;
            this.L$1 = coroutineScope3;
            this.label = 1;
            invoke = function1.invoke(this);
            if (invoke == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i == 4) {
                            Object obj4 = this.L$1;
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    Object obj5 = this.L$3;
                    obj3 = this.L$1;
                    coroutineScope2 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    obj2 = obj3;
                    coroutineScope = coroutineScope2;
                    m4513exceptionOrNullimpl2 = Result.m4513exceptionOrNullimpl(obj2);
                    if (m4513exceptionOrNullimpl2 != null) {
                        String str = this.$tag;
                        Object[] objArr = new Object[1];
                        String message = m4513exceptionOrNullimpl2.getMessage();
                        objArr[0] = message != null ? message : "unknown exception";
                        Pdlog.m3274e(str, objArr);
                        MainCoroutineDispatcher main = Dispatchers.getMain();
                        C4437xce4fe641 c4437xce4fe641 = new C4437xce4fe641(m4513exceptionOrNullimpl2, null, this);
                        this.L$0 = coroutineScope;
                        this.L$1 = obj2;
                        this.L$2 = m4513exceptionOrNullimpl2;
                        this.label = 4;
                        if (BuildersKt.withContext(main, c4437xce4fe641, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
                baseResponse = (BaseResponse) this.L$2;
                obj2 = this.L$1;
                coroutineScope = (CoroutineScope) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    m4510constructorimpl2 = Result.m4510constructorimpl(Unit.INSTANCE);
                } catch (Throwable th2) {
                    th = th2;
                    Result.Companion companion3 = Result.INSTANCE;
                    m4510constructorimpl2 = Result.m4510constructorimpl(ResultKt.createFailure(th));
                    m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl2);
                    if (m4513exceptionOrNullimpl != null) {
                    }
                    m4513exceptionOrNullimpl2 = Result.m4513exceptionOrNullimpl(obj2);
                    if (m4513exceptionOrNullimpl2 != null) {
                    }
                    return Unit.INSTANCE;
                }
                m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl2);
                if (m4513exceptionOrNullimpl != null) {
                    String str2 = this.$tag;
                    Object[] objArr2 = new Object[1];
                    String message2 = m4513exceptionOrNullimpl.getMessage();
                    if (message2 == null) {
                        message2 = "unknown exception";
                    }
                    objArr2[0] = message2;
                    Pdlog.m3274e(str2, objArr2);
                    MainCoroutineDispatcher main2 = Dispatchers.getMain();
                    C4439xfdd0fe9 c4439xfdd0fe9 = new C4439xfdd0fe9(m4513exceptionOrNullimpl, null, this, coroutineScope);
                    this.L$0 = coroutineScope;
                    this.L$1 = obj2;
                    this.L$2 = baseResponse;
                    this.L$3 = m4510constructorimpl2;
                    this.L$4 = m4513exceptionOrNullimpl;
                    this.label = 3;
                    if (BuildersKt.withContext(main2, c4439xfdd0fe9, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    obj3 = obj2;
                    coroutineScope2 = coroutineScope;
                    obj2 = obj3;
                    coroutineScope = coroutineScope2;
                }
                m4513exceptionOrNullimpl2 = Result.m4513exceptionOrNullimpl(obj2);
                if (m4513exceptionOrNullimpl2 != null) {
                }
                return Unit.INSTANCE;
            }
            coroutineScope3 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            invoke = obj;
        }
        BaseResponse baseResponse2 = (BaseResponse) invoke;
        Pdlog.m3273d(this.$tag, "runCatching result = " + baseResponse2.getResponseCode());
        m4510constructorimpl = Result.m4510constructorimpl(baseResponse2);
        coroutineScope = coroutineScope3;
        obj2 = m4510constructorimpl;
        if (Result.m4517isSuccessimpl(obj2)) {
            BaseResponse baseResponse3 = (BaseResponse) obj2;
            try {
                Result.Companion companion4 = Result.INSTANCE;
                c4438xfdd0fe8 = new C4438xfdd0fe8(null, baseResponse3, this, coroutineScope);
                this.L$0 = coroutineScope;
                this.L$1 = obj2;
                this.L$2 = baseResponse3;
                this.L$3 = coroutineScope;
                this.label = 2;
            } catch (Throwable th3) {
                th = th3;
                baseResponse = baseResponse3;
                Result.Companion companion32 = Result.INSTANCE;
                m4510constructorimpl2 = Result.m4510constructorimpl(ResultKt.createFailure(th));
                m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl2);
                if (m4513exceptionOrNullimpl != null) {
                }
                m4513exceptionOrNullimpl2 = Result.m4513exceptionOrNullimpl(obj2);
                if (m4513exceptionOrNullimpl2 != null) {
                }
                return Unit.INSTANCE;
            }
            if (BaseViewModelExtKt.executeResponse(baseResponse3, c4438xfdd0fe8, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            baseResponse = baseResponse3;
            m4510constructorimpl2 = Result.m4510constructorimpl(Unit.INSTANCE);
            m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl2);
            if (m4513exceptionOrNullimpl != null) {
            }
        }
        m4513exceptionOrNullimpl2 = Result.m4513exceptionOrNullimpl(obj2);
        if (m4513exceptionOrNullimpl2 != null) {
        }
        return Unit.INSTANCE;
    }
}
