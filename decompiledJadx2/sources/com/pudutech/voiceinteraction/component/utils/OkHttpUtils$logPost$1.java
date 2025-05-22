package com.pudutech.voiceinteraction.component.utils;

import com.pudutech.base.Pdlog;
import com.pudutech.voiceinteraction.component.utils.OkHttpUtils;
import kotlin.Metadata;
import kotlin.Result;
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
import okhttp3.ResponseBody;

/* compiled from: OkHttpUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.voiceinteraction.component.utils.OkHttpUtils$logPost$1", m3970f = "OkHttpUtils.kt", m3971i = {0, 0}, m3972l = {339}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$runCatching"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes7.dex */
final class OkHttpUtils$logPost$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef $aiVoiceService;
    final /* synthetic */ OkHttpUtils.LogData $log;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7585p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OkHttpUtils$logPost$1(OkHttpUtils.LogData logData, Ref.ObjectRef objectRef, Continuation continuation) {
        super(2, continuation);
        this.$log = logData;
        this.$aiVoiceService = objectRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        OkHttpUtils$logPost$1 okHttpUtils$logPost$1 = new OkHttpUtils$logPost$1(this.$log, this.$aiVoiceService, completion);
        okHttpUtils$logPost$1.f7585p$ = (CoroutineScope) obj;
        return okHttpUtils$logPost$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OkHttpUtils$logPost$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object m4510constructorimpl;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f7585p$;
                Result.Companion companion = Result.INSTANCE;
                Pdlog.m3273d(OkHttpUtils.INSTANCE.getTAG(), " logPostLog:" + this.$log);
                OkHttpUtils.AiVoiceService aiVoiceService = (OkHttpUtils.AiVoiceService) this.$aiVoiceService.element;
                OkHttpUtils.LogData logData = this.$log;
                this.L$0 = coroutineScope;
                this.L$1 = coroutineScope;
                this.label = 1;
                obj = aiVoiceService.logPost(logData, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            m4510constructorimpl = Result.m4510constructorimpl((ResponseBody) obj);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m4517isSuccessimpl(m4510constructorimpl)) {
            Pdlog.m3273d(OkHttpUtils.INSTANCE.getTAG(), " logPost Response:" + ((ResponseBody) m4510constructorimpl));
        }
        Throwable m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl);
        if (m4513exceptionOrNullimpl != null) {
            Pdlog.m3273d(OkHttpUtils.INSTANCE.getTAG(), "logPost onFailure " + m4513exceptionOrNullimpl);
        }
        return Unit.INSTANCE;
    }
}
