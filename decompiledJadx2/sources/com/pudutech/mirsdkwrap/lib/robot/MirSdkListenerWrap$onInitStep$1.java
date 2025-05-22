package com.pudutech.mirsdkwrap.lib.robot;

import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.mirsdk.aidl.serialize.StepState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirSdkListenerWrap.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap$onInitStep$1", m3970f = "MirSdkListenerWrap.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class MirSdkListenerWrap$onInitStep$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ InitStep $p0;
    final /* synthetic */ StepState $p1;
    final /* synthetic */ String $p2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6630p$;
    final /* synthetic */ MirSdkListenerWrap this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MirSdkListenerWrap$onInitStep$1(MirSdkListenerWrap mirSdkListenerWrap, InitStep initStep, StepState stepState, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mirSdkListenerWrap;
        this.$p0 = initStep;
        this.$p1 = stepState;
        this.$p2 = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MirSdkListenerWrap$onInitStep$1 mirSdkListenerWrap$onInitStep$1 = new MirSdkListenerWrap$onInitStep$1(this.this$0, this.$p0, this.$p1, this.$p2, completion);
        mirSdkListenerWrap$onInitStep$1.f6630p$ = (CoroutineScope) obj;
        return mirSdkListenerWrap$onInitStep$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MirSdkListenerWrap$onInitStep$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6630p$;
        this.this$0.getInitStepListeners().forEach(new Function1<Function3<? super InitStep, ? super StepState, ? super String, ? extends Unit>, Unit>() { // from class: com.pudutech.mirsdkwrap.lib.robot.MirSdkListenerWrap$onInitStep$1.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Function3<? super InitStep, ? super StepState, ? super String, ? extends Unit> function3) {
                invoke2((Function3<? super InitStep, ? super StepState, ? super String, Unit>) function3);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Function3<? super InitStep, ? super StepState, ? super String, Unit> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.invoke(MirSdkListenerWrap$onInitStep$1.this.$p0, MirSdkListenerWrap$onInitStep$1.this.$p1, MirSdkListenerWrap$onInitStep$1.this.$p2);
            }
        });
        if (this.$p0 == InitStep.Finish && (this.$p1 == StepState.Success || this.$p1 == StepState.Fail)) {
            this.this$0.getInitStepListeners().clear$module_robot_mirsdk_wrapper_release();
        }
        return Unit.INSTANCE;
    }
}
