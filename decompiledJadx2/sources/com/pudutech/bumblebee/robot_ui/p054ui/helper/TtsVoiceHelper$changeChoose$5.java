package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TtsVoiceHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper$changeChoose$5", m3970f = "TtsVoiceHelper.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes3.dex */
public final class TtsVoiceHelper$changeChoose$5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ TtsVoiceHelper.TtsConfigData $item;
    final /* synthetic */ TtsVoiceHelper.TtsVoiceType $ttsVoiceType;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4935p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TtsVoiceHelper$changeChoose$5(TtsVoiceHelper.TtsConfigData ttsConfigData, TtsVoiceHelper.TtsVoiceType ttsVoiceType, Continuation continuation) {
        super(2, continuation);
        this.$item = ttsConfigData;
        this.$ttsVoiceType = ttsVoiceType;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TtsVoiceHelper$changeChoose$5 ttsVoiceHelper$changeChoose$5 = new TtsVoiceHelper$changeChoose$5(this.$item, this.$ttsVoiceType, completion);
        ttsVoiceHelper$changeChoose$5.f4935p$ = (CoroutineScope) obj;
        return ttsVoiceHelper$changeChoose$5;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TtsVoiceHelper$changeChoose$5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f4935p$;
        TtsVoiceFlHelper.INSTANCE.updataVoice(this.$item, this.$ttsVoiceType);
        return Unit.INSTANCE;
    }
}
