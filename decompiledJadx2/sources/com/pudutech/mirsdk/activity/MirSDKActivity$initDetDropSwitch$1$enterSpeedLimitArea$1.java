package com.pudutech.mirsdk.activity;

import android.widget.TextView;
import com.pudutech.mirsdk.function.C4946R;
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

/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$initDetDropSwitch$1$enterSpeedLimitArea$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
final class MirSDKActivity$initDetDropSwitch$1$enterSpeedLimitArea$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $p0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5634p$;
    final /* synthetic */ MirSDKActivity$initDetDropSwitch$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MirSDKActivity$initDetDropSwitch$1$enterSpeedLimitArea$1(MirSDKActivity$initDetDropSwitch$1 mirSDKActivity$initDetDropSwitch$1, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mirSDKActivity$initDetDropSwitch$1;
        this.$p0 = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MirSDKActivity$initDetDropSwitch$1$enterSpeedLimitArea$1 mirSDKActivity$initDetDropSwitch$1$enterSpeedLimitArea$1 = new MirSDKActivity$initDetDropSwitch$1$enterSpeedLimitArea$1(this.this$0, this.$p0, completion);
        mirSDKActivity$initDetDropSwitch$1$enterSpeedLimitArea$1.f5634p$ = (CoroutineScope) obj;
        return mirSDKActivity$initDetDropSwitch$1$enterSpeedLimitArea$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MirSDKActivity$initDetDropSwitch$1$enterSpeedLimitArea$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5634p$;
        ((TextView) this.this$0.this$0._$_findCachedViewById(C4946R.id.enter_speed_area)).setText(String.valueOf(this.$p0));
        return Unit.INSTANCE;
    }
}
