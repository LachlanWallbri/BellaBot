package com.pudu.library.loracall.view;

import android.widget.TextView;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import com.pudu.library.loracall.KotlinUtilsKt;
import com.pudu.library.loracall.base.BasePop;
import com.pudu.loracall.library.C3965R;
import com.pudu.loracall.library.databinding.LoraUpdateProgressPopBinding;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LoRaHomeFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "Lcom/pudu/loracall/library/databinding/LoraUpdateProgressPopBinding;", "pop", "Lcom/pudu/library/loracall/base/BasePop;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LoRaHomeFragment$getUpdateProgressPop$2 extends Lambda implements Function2<LoraUpdateProgressPopBinding, BasePop<LoraUpdateProgressPopBinding>, Unit> {
    final /* synthetic */ LoRaHomeFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoRaHomeFragment$getUpdateProgressPop$2(LoRaHomeFragment loRaHomeFragment) {
        super(2);
        this.this$0 = loRaHomeFragment;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(LoraUpdateProgressPopBinding loraUpdateProgressPopBinding, BasePop<LoraUpdateProgressPopBinding> basePop) {
        invoke2(loraUpdateProgressPopBinding, basePop);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(final LoraUpdateProgressPopBinding receiver, final BasePop<LoraUpdateProgressPopBinding> pop) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(pop, "pop");
        this.this$0.getViewModel().getLoraUpdateState().setValue(0);
        this.this$0.getViewModel().getLoraProgress().setValue(0);
        BasePop<LoraUpdateProgressPopBinding> basePop = pop;
        this.this$0.getViewModel().getLoraProgress().observe(basePop, new Observer<Integer>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$getUpdateProgressPop$2.1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Integer num) {
                TextView progressView = LoraUpdateProgressPopBinding.this.progressView;
                Intrinsics.checkExpressionValueIsNotNull(progressView, "progressView");
                progressView.setText(String.valueOf(num.intValue()));
            }
        });
        this.this$0.getViewModel().getLoraUpdateState().observe(basePop, new Observer<Integer>() { // from class: com.pudu.library.loracall.view.LoRaHomeFragment$getUpdateProgressPop$2.2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Integer num) {
                if (num != null && num.intValue() == 5) {
                    String string = LoRaHomeFragment$getUpdateProgressPop$2.this.this$0.getString(C3965R.string.lora_update_completed);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.lora_update_completed)");
                    KotlinUtilsKt.showToast(string);
                    pop.dismiss();
                    BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(LoRaHomeFragment$getUpdateProgressPop$2.this.this$0), null, null, new AnonymousClass1(null), 3, null);
                    return;
                }
                if (num != null && num.intValue() == 6) {
                    String string2 = LoRaHomeFragment$getUpdateProgressPop$2.this.this$0.getString(C3965R.string.lora_update_failed);
                    Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.lora_update_failed)");
                    KotlinUtilsKt.showToast(string2);
                    pop.dismiss();
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: LoRaHomeFragment.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudu.library.loracall.view.LoRaHomeFragment$getUpdateProgressPop$2$2$1", m3970f = "LoRaHomeFragment.kt", m3971i = {0}, m3972l = {424}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
            /* renamed from: com.pudu.library.loracall.view.LoRaHomeFragment$getUpdateProgressPop$2$2$1, reason: invalid class name */
            /* loaded from: classes4.dex */
            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f4385p$;

                AnonymousClass1(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(completion);
                    anonymousClass1.f4385p$ = (CoroutineScope) obj;
                    return anonymousClass1;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.L$0 = this.f4385p$;
                        this.label = 1;
                        if (DelayKt.delay(5000L, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    LoRaHomeFragment$getUpdateProgressPop$2.this.this$0.initLoraData();
                    return Unit.INSTANCE;
                }
            }
        });
    }
}
