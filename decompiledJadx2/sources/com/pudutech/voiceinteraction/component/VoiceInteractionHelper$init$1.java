package com.pudutech.voiceinteraction.component;

import android.content.Context;
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
@DebugMetadata(m3969c = "com.pudutech.voiceinteraction.component.VoiceInteractionHelper$init$1", m3970f = "VoiceInteractionHelper.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes7.dex */
public final class VoiceInteractionHelper$init$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ Boolean $ifOnlyWake;
    final /* synthetic */ Language $language;
    final /* synthetic */ Function0 $onInitFailedListener;
    final /* synthetic */ Function0 $onInitSucceedListener;
    final /* synthetic */ String $pid;
    final /* synthetic */ String $vid;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7545p$;
    final /* synthetic */ VoiceInteractionHelper this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VoiceInteractionHelper$init$1(VoiceInteractionHelper voiceInteractionHelper, Context context, Language language, String str, String str2, Boolean bool, Function0 function0, Function0 function02, Continuation continuation) {
        super(2, continuation);
        this.this$0 = voiceInteractionHelper;
        this.$context = context;
        this.$language = language;
        this.$pid = str;
        this.$vid = str2;
        this.$ifOnlyWake = bool;
        this.$onInitSucceedListener = function0;
        this.$onInitFailedListener = function02;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        VoiceInteractionHelper$init$1 voiceInteractionHelper$init$1 = new VoiceInteractionHelper$init$1(this.this$0, this.$context, this.$language, this.$pid, this.$vid, this.$ifOnlyWake, this.$onInitSucceedListener, this.$onInitFailedListener, completion);
        voiceInteractionHelper$init$1.f7545p$ = (CoroutineScope) obj;
        return voiceInteractionHelper$init$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VoiceInteractionHelper$init$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        VoiceInteractionHelper$mainListener$1 voiceInteractionHelper$mainListener$1;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7545p$;
        VoiceInteractionKit instance = VoiceInteractionKit.INSTANCE.getINSTANCE();
        Context context = this.$context;
        Language language = this.$language;
        voiceInteractionHelper$mainListener$1 = this.this$0.mainListener;
        VoiceInteractionHelper$mainListener$1 voiceInteractionHelper$mainListener$12 = voiceInteractionHelper$mainListener$1;
        String str = this.$pid;
        String str2 = this.$vid;
        Boolean bool = this.$ifOnlyWake;
        if (bool == null) {
            Intrinsics.throwNpe();
        }
        if (instance.init(context, language, voiceInteractionHelper$mainListener$12, str, str2, bool.booleanValue())) {
            Function0 function0 = this.$onInitSucceedListener;
            if (function0 != null) {
            }
        } else {
            Function0 function02 = this.$onInitFailedListener;
            if (function02 != null) {
            }
        }
        return Unit.INSTANCE;
    }
}
