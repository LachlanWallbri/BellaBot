package com.pudutech.mirsdk.activity;

import android.widget.Switch;
import com.pudutech.mirsdk.function.C4946R;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$initSlipControlSwitch$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
final class MirSDKActivity$initSlipControlSwitch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef $switchSlipflag;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5643p$;
    final /* synthetic */ MirSDKActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MirSDKActivity$initSlipControlSwitch$1(MirSDKActivity mirSDKActivity, Ref.ObjectRef objectRef, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mirSDKActivity;
        this.$switchSlipflag = objectRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MirSDKActivity$initSlipControlSwitch$1 mirSDKActivity$initSlipControlSwitch$1 = new MirSDKActivity$initSlipControlSwitch$1(this.this$0, this.$switchSlipflag, completion);
        mirSDKActivity$initSlipControlSwitch$1.f5643p$ = (CoroutineScope) obj;
        return mirSDKActivity$initSlipControlSwitch$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MirSDKActivity$initSlipControlSwitch$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5643p$;
        Switch slipControlSwitch = (Switch) this.this$0._$_findCachedViewById(C4946R.id.slipControlSwitch);
        Intrinsics.checkExpressionValueIsNotNull(slipControlSwitch, "slipControlSwitch");
        slipControlSwitch.setChecked(((Boolean) this.$switchSlipflag.element) != null && Intrinsics.areEqual((Boolean) this.$switchSlipflag.element, Boxing.boxBoolean(true)));
        return Unit.INSTANCE;
    }
}
