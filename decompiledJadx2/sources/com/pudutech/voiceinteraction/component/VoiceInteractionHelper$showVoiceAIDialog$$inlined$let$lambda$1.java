package com.pudutech.voiceinteraction.component;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.pudutech.voiceinteraction.component.p069ui.VoiceInteractionDialog;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: VoiceInteractionHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/voiceinteraction/component/VoiceInteractionHelper$showVoiceAIDialog$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class VoiceInteractionHelper$showVoiceAIDialog$$inlined$let$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AppCompatActivity $act;
    final /* synthetic */ int $random$inlined;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7539p$;
    final /* synthetic */ VoiceInteractionHelper this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VoiceInteractionHelper$showVoiceAIDialog$$inlined$let$lambda$1(AppCompatActivity appCompatActivity, Continuation continuation, VoiceInteractionHelper voiceInteractionHelper, int i) {
        super(2, continuation);
        this.$act = appCompatActivity;
        this.this$0 = voiceInteractionHelper;
        this.$random$inlined = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        VoiceInteractionHelper$showVoiceAIDialog$$inlined$let$lambda$1 voiceInteractionHelper$showVoiceAIDialog$$inlined$let$lambda$1 = new VoiceInteractionHelper$showVoiceAIDialog$$inlined$let$lambda$1(this.$act, completion, this.this$0, this.$random$inlined);
        voiceInteractionHelper$showVoiceAIDialog$$inlined$let$lambda$1.f7539p$ = (CoroutineScope) obj;
        return voiceInteractionHelper$showVoiceAIDialog$$inlined$let$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VoiceInteractionHelper$showVoiceAIDialog$$inlined$let$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7539p$;
        if (VoiceInteractionHelper.access$getVoiceInteractionDialog$p(this.this$0) == null) {
            VoiceInteractionHelper.access$setVoiceInteractionDialog$p(this.this$0, new VoiceInteractionDialog(this.$act, this.$random$inlined));
            VoiceInteractionDialog access$getVoiceInteractionDialog$p = VoiceInteractionHelper.access$getVoiceInteractionDialog$p(this.this$0);
            if (access$getVoiceInteractionDialog$p != null) {
                access$getVoiceInteractionDialog$p.setOnCloseBtnClickListener(new VoiceInteractionDialog.OnCloseBtnClickListener() { // from class: com.pudutech.voiceinteraction.component.VoiceInteractionHelper$showVoiceAIDialog$$inlined$let$lambda$1.1
                    @Override // com.pudutech.voiceinteraction.component.ui.VoiceInteractionDialog.OnCloseBtnClickListener
                    public void onCloseBtnClick(View v) {
                        Intrinsics.checkParameterIsNotNull(v, "v");
                        VoiceInteractionHelper$showVoiceAIDialog$$inlined$let$lambda$1.this.this$0.dismissVoiceInteractionDialog();
                        VoiceInteractionHelper$showVoiceAIDialog$$inlined$let$lambda$1.this.this$0.resetCAE();
                    }
                });
            }
        }
        VoiceInteractionDialog access$getVoiceInteractionDialog$p2 = VoiceInteractionHelper.access$getVoiceInteractionDialog$p(this.this$0);
        if (access$getVoiceInteractionDialog$p2 != null) {
            access$getVoiceInteractionDialog$p2.show();
            Function1 access$getOnDialogShowListener$p = VoiceInteractionHelper.access$getOnDialogShowListener$p(this.this$0);
            if (access$getOnDialogShowListener$p != null) {
            }
        }
        return Unit.INSTANCE;
    }
}
