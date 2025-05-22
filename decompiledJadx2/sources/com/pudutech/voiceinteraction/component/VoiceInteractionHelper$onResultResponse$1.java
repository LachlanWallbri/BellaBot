package com.pudutech.voiceinteraction.component;

import com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: VoiceInteractionHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.voiceinteraction.component.VoiceInteractionHelper$onResultResponse$1", m3970f = "VoiceInteractionHelper.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
final class VoiceInteractionHelper$onResultResponse$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $data;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7549p$;
    final /* synthetic */ VoiceInteractionHelper this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VoiceInteractionHelper$onResultResponse$1(VoiceInteractionHelper voiceInteractionHelper, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = voiceInteractionHelper;
        this.$data = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        VoiceInteractionHelper$onResultResponse$1 voiceInteractionHelper$onResultResponse$1 = new VoiceInteractionHelper$onResultResponse$1(this.this$0, this.$data, completion);
        voiceInteractionHelper$onResultResponse$1.f7549p$ = (CoroutineScope) obj;
        return voiceInteractionHelper$onResultResponse$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VoiceInteractionHelper$onResultResponse$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7549p$;
        IVoiceReponseTextListener access$getIVoiceReponseTextListener$p = VoiceInteractionHelper.access$getIVoiceReponseTextListener$p(this.this$0);
        if (access$getIVoiceReponseTextListener$p != null) {
            access$getIVoiceReponseTextListener$p.responseText(this.$data);
        }
        IVoiceReponseTextListener access$getIVoiceReponseTextListener$p2 = VoiceInteractionHelper.access$getIVoiceReponseTextListener$p(this.this$0);
        if (access$getIVoiceReponseTextListener$p2 != null) {
            access$getIVoiceReponseTextListener$p2.reWakeup(true);
        }
        IVoiceReponseTextListener access$getIVoiceReponseTextListener$p3 = VoiceInteractionHelper.access$getIVoiceReponseTextListener$p(this.this$0);
        if (access$getIVoiceReponseTextListener$p3 != null) {
            access$getIVoiceReponseTextListener$p3.loading(false);
        }
        IVoiceReponseTextListener access$getIVoiceReponseTextListener$p4 = VoiceInteractionHelper.access$getIVoiceReponseTextListener$p(this.this$0);
        if (access$getIVoiceReponseTextListener$p4 != null) {
            access$getIVoiceReponseTextListener$p4.volumeChange(false);
        }
        return Unit.INSTANCE;
    }
}
