package com.pudutech.voiceinteraction.component;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.voiceinteraction.component.VoiceInteractionHelper;
import com.pudutech.voiceinteraction.component.config.VoiceInteractionState;
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
import kotlinx.coroutines.DelayKt;

/* compiled from: VoiceInteractionHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.voiceinteraction.component.VoiceInteractionHelper$onStatusChanged$1", m3970f = "VoiceInteractionHelper.kt", m3971i = {0}, m3972l = {BaseQuickAdapter.LOADING_VIEW}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes6.dex */
final class VoiceInteractionHelper$onStatusChanged$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ VoiceInteractionState $state;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7550p$;
    final /* synthetic */ VoiceInteractionHelper this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VoiceInteractionHelper$onStatusChanged$1(VoiceInteractionHelper voiceInteractionHelper, VoiceInteractionState voiceInteractionState, Continuation continuation) {
        super(2, continuation);
        this.this$0 = voiceInteractionHelper;
        this.$state = voiceInteractionState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        VoiceInteractionHelper$onStatusChanged$1 voiceInteractionHelper$onStatusChanged$1 = new VoiceInteractionHelper$onStatusChanged$1(this.this$0, this.$state, completion);
        voiceInteractionHelper$onStatusChanged$1.f7550p$ = (CoroutineScope) obj;
        return voiceInteractionHelper$onStatusChanged$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VoiceInteractionHelper$onStatusChanged$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:21:0x0031. Please report as an issue. */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7550p$;
            switch (VoiceInteractionHelper.WhenMappings.$EnumSwitchMapping$1[this.$state.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    int i2 = VoiceInteractionHelper.WhenMappings.$EnumSwitchMapping$0[this.$state.ordinal()];
                    if (i2 == 1 || i2 == 2) {
                        IVoiceReponseTextListener access$getIVoiceReponseTextListener$p = VoiceInteractionHelper.access$getIVoiceReponseTextListener$p(this.this$0);
                        if (access$getIVoiceReponseTextListener$p != null) {
                            access$getIVoiceReponseTextListener$p.palyCompelete(2);
                        }
                        VoiceInteractionHelper.access$getHandler$p(this.this$0).obtainMessage(102).sendToTarget();
                        VoiceInteractionHelper.access$getHandler$p(this.this$0).obtainMessage(104).sendToTarget();
                        VoiceInteractionHelper.access$showReWakeupTipsAndStartCountdown(this.this$0);
                    } else if (i2 == 3) {
                        IVoiceReponseTextListener access$getIVoiceReponseTextListener$p2 = VoiceInteractionHelper.access$getIVoiceReponseTextListener$p(this.this$0);
                        if (access$getIVoiceReponseTextListener$p2 != null) {
                            access$getIVoiceReponseTextListener$p2.loading(true);
                        }
                        VoiceInteractionHelper.access$getHandler$p(this.this$0).obtainMessage(101).sendToTarget();
                    } else if (i2 == 4) {
                        IVoiceReponseTextListener access$getIVoiceReponseTextListener$p3 = VoiceInteractionHelper.access$getIVoiceReponseTextListener$p(this.this$0);
                        if (access$getIVoiceReponseTextListener$p3 != null) {
                            access$getIVoiceReponseTextListener$p3.palyCompelete(1);
                        }
                        VoiceInteractionHelper.access$getHandler$p(this.this$0).obtainMessage(102).sendToTarget();
                        VoiceInteractionHelper.access$getHandler$p(this.this$0).obtainMessage(104).sendToTarget();
                        VoiceInteractionHelper.access$showReWakeupTipsAndStartCountdown(this.this$0);
                    }
                    return Unit.INSTANCE;
                case 8:
                case 9:
                    if (!VoiceInteractionHelper.access$isNetworkUnavailableCountingDown$p(this.this$0)) {
                        VoiceInteractionHelper.access$getHandler$p(this.this$0).obtainMessage(102).sendToTarget();
                        VoiceInteractionHelper.access$getHandler$p(this.this$0).obtainMessage(104).sendToTarget();
                        this.L$0 = coroutineScope;
                        this.label = 1;
                        if (DelayKt.delay(SolicitService.CAMERA_OPEN_TIME_OUT, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                case 10:
                    VoiceInteractionHelper.access$getHandler$p(this.this$0).obtainMessage(102).sendToTarget();
                    VoiceInteractionHelper.access$getHandler$p(this.this$0).obtainMessage(104).sendToTarget();
                    VoiceInteractionHelper.access$showReWakeupTipsAndStartCountdown(this.this$0);
                    IVoiceReponseTextListener access$getIVoiceReponseTextListener$p4 = VoiceInteractionHelper.access$getIVoiceReponseTextListener$p(this.this$0);
                    if (access$getIVoiceReponseTextListener$p4 != null) {
                        access$getIVoiceReponseTextListener$p4.palyCompelete(5);
                    }
                    IVoiceReponseTextListener access$getIVoiceReponseTextListener$p5 = VoiceInteractionHelper.access$getIVoiceReponseTextListener$p(this.this$0);
                    if (access$getIVoiceReponseTextListener$p5 != null) {
                        access$getIVoiceReponseTextListener$p5.loading(false);
                    }
                    return Unit.INSTANCE;
                default:
                    VoiceInteractionHelper.access$getHandler$p(this.this$0).obtainMessage(102).sendToTarget();
                    VoiceInteractionHelper.access$getHandler$p(this.this$0).obtainMessage(104).sendToTarget();
                    VoiceInteractionHelper.access$showReWakeupTipsAndStartCountdown(this.this$0);
                    IVoiceReponseTextListener access$getIVoiceReponseTextListener$p6 = VoiceInteractionHelper.access$getIVoiceReponseTextListener$p(this.this$0);
                    if (access$getIVoiceReponseTextListener$p6 != null) {
                        access$getIVoiceReponseTextListener$p6.responseText(VoiceInteractionHelper.access$getContext$p(this.this$0).getString(C5767R.string.pdStr14_8));
                    }
                    IVoiceReponseTextListener access$getIVoiceReponseTextListener$p7 = VoiceInteractionHelper.access$getIVoiceReponseTextListener$p(this.this$0);
                    if (access$getIVoiceReponseTextListener$p7 != null) {
                        access$getIVoiceReponseTextListener$p7.loading(false);
                    }
                    return Unit.INSTANCE;
            }
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        VoiceInteractionHelper.access$showReWakeupTipsAndStartCountdown(this.this$0);
        IVoiceReponseTextListener access$getIVoiceReponseTextListener$p8 = VoiceInteractionHelper.access$getIVoiceReponseTextListener$p(this.this$0);
        if (access$getIVoiceReponseTextListener$p8 != null) {
            access$getIVoiceReponseTextListener$p8.palyCompelete(5);
        }
        IVoiceReponseTextListener access$getIVoiceReponseTextListener$p9 = VoiceInteractionHelper.access$getIVoiceReponseTextListener$p(this.this$0);
        if (access$getIVoiceReponseTextListener$p9 != null) {
            access$getIVoiceReponseTextListener$p9.responseText(VoiceInteractionHelper.access$getContext$p(this.this$0).getString(C5767R.string.pdStr14_7));
        }
        IVoiceReponseTextListener access$getIVoiceReponseTextListener$p10 = VoiceInteractionHelper.access$getIVoiceReponseTextListener$p(this.this$0);
        if (access$getIVoiceReponseTextListener$p10 != null) {
            access$getIVoiceReponseTextListener$p10.loading(false);
        }
        VoiceInteractionHelper.access$setNetworkUnavailableCountingDown$p(this.this$0, true);
        return Unit.INSTANCE;
    }
}
