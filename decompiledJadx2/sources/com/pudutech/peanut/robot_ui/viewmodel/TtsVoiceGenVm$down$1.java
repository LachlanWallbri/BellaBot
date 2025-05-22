package com.pudutech.peanut.robot_ui.viewmodel;

import com.pudutech.peanut.robot_ui.ttsvoices.TtsPkManager;
import com.pudutech.peanut.robot_ui.ttsvoices.bean.TtsDownInfo;
import kotlin.Metadata;
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
/* compiled from: TtsVoiceGenVm.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.viewmodel.TtsVoiceGenVm$down$1", m3970f = "TtsVoiceGenVm.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
public final class TtsVoiceGenVm$down$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7071p$;
    final /* synthetic */ TtsVoiceGenVm this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TtsVoiceGenVm$down$1(TtsVoiceGenVm ttsVoiceGenVm, Continuation continuation) {
        super(2, continuation);
        this.this$0 = ttsVoiceGenVm;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TtsVoiceGenVm$down$1 ttsVoiceGenVm$down$1 = new TtsVoiceGenVm$down$1(this.this$0, completion);
        ttsVoiceGenVm$down$1.f7071p$ = (CoroutineScope) obj;
        return ttsVoiceGenVm$down$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TtsVoiceGenVm$down$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7071p$;
        TtsPkManager.INSTANCE.downTtsVoice(new Function1<TtsDownInfo, Unit>() { // from class: com.pudutech.peanut.robot_ui.viewmodel.TtsVoiceGenVm$down$1.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TtsDownInfo ttsDownInfo) {
                invoke2(ttsDownInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TtsDownInfo it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                TtsVoiceGenVm$down$1.this.this$0.saveDownInfo(TtsVoiceGenVm.access$getDownInfoModel$p(TtsVoiceGenVm$down$1.this.this$0).getDownInfo(), it);
                TtsVoiceGenVm$down$1.this.this$0.sendMessage(TtsVoiceGenVm.access$getDownInfoModel$p(TtsVoiceGenVm$down$1.this.this$0).getDownInfo());
                if (TtsVoiceGenVm.access$getDownInfoModel$p(TtsVoiceGenVm$down$1.this.this$0).getDownInfo().getLeft() != TtsVoiceGenVm.access$getDownInfoModel$p(TtsVoiceGenVm$down$1.this.this$0).getDownInfo().getAll() || TtsVoiceGenVm.access$getDownInfoModel$p(TtsVoiceGenVm$down$1.this.this$0).getCustomDownInfo().getLeft() == TtsVoiceGenVm.access$getDownInfoModel$p(TtsVoiceGenVm$down$1.this.this$0).getCustomDownInfo().getAll()) {
                    return;
                }
                TtsVoiceGenVm$down$1.this.this$0.downCustom();
            }
        });
        return Unit.INSTANCE;
    }
}
