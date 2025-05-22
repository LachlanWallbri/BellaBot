package com.pudutech.voiceinteraction.component;

import com.pudutech.voiceinteraction.component.config.Language;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: VoiceInteractionHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.voiceinteraction.component.VoiceInteractionHelper$switchLanguage$1", m3970f = "VoiceInteractionHelper.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes7.dex */
public final class VoiceInteractionHelper$switchLanguage$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Language $language;
    final /* synthetic */ Function0 $onSwitchLanguageFailedListener;
    final /* synthetic */ Function0 $onSwitchLanguageSucceedListener;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7572p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VoiceInteractionHelper$switchLanguage$1(Language language, Function0 function0, Function0 function02, Continuation continuation) {
        super(2, continuation);
        this.$language = language;
        this.$onSwitchLanguageSucceedListener = function0;
        this.$onSwitchLanguageFailedListener = function02;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        VoiceInteractionHelper$switchLanguage$1 voiceInteractionHelper$switchLanguage$1 = new VoiceInteractionHelper$switchLanguage$1(this.$language, this.$onSwitchLanguageSucceedListener, this.$onSwitchLanguageFailedListener, completion);
        voiceInteractionHelper$switchLanguage$1.f7572p$ = (CoroutineScope) obj;
        return voiceInteractionHelper$switchLanguage$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VoiceInteractionHelper$switchLanguage$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7572p$;
        if (VoiceInteractionKit.INSTANCE.getINSTANCE().switchLanguage(this.$language)) {
            Function0 function0 = this.$onSwitchLanguageSucceedListener;
            if (function0 != null) {
            }
        } else {
            Function0 function02 = this.$onSwitchLanguageFailedListener;
            if (function02 != null) {
            }
        }
        return Unit.INSTANCE;
    }
}
