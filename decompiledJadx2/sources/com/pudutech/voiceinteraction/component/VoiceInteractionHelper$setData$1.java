package com.pudutech.voiceinteraction.component;

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
@DebugMetadata(m3969c = "com.pudutech.voiceinteraction.component.VoiceInteractionHelper$setData$1", m3970f = "VoiceInteractionHelper.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes7.dex */
final class VoiceInteractionHelper$setData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $configId;
    final /* synthetic */ String $map_name;
    final /* synthetic */ String $shopId;
    final /* synthetic */ int $state;
    final /* synthetic */ String $workMode;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7559p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VoiceInteractionHelper$setData$1(String str, String str2, String str3, String str4, int i, Continuation continuation) {
        super(2, continuation);
        this.$map_name = str;
        this.$configId = str2;
        this.$workMode = str3;
        this.$shopId = str4;
        this.$state = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        VoiceInteractionHelper$setData$1 voiceInteractionHelper$setData$1 = new VoiceInteractionHelper$setData$1(this.$map_name, this.$configId, this.$workMode, this.$shopId, this.$state, completion);
        voiceInteractionHelper$setData$1.f7559p$ = (CoroutineScope) obj;
        return voiceInteractionHelper$setData$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VoiceInteractionHelper$setData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7559p$;
        VoiceInteractionKit.INSTANCE.getINSTANCE().setData(this.$map_name, this.$configId, this.$workMode, this.$shopId, this.$state);
        return Unit.INSTANCE;
    }
}
