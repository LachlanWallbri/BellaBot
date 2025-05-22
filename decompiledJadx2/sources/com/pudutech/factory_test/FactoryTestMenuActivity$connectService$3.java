package com.pudutech.factory_test;

import android.content.Context;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.bumblebee.robot.aidl.RobotInterface;
import com.pudutech.mirsdk.hardware.HardwareInterface;
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

/* compiled from: FactoryTestMenuActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.factory_test.FactoryTestMenuActivity$connectService$3", m3970f = "FactoryTestMenuActivity.kt", m3971i = {0, 1}, m3972l = {451, 452}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes.dex */
final class FactoryTestMenuActivity$connectService$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5134p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FactoryTestMenuActivity$connectService$3(Context context, Continuation continuation) {
        super(2, continuation);
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        FactoryTestMenuActivity$connectService$3 factoryTestMenuActivity$connectService$3 = new FactoryTestMenuActivity$connectService$3(this.$context, completion);
        factoryTestMenuActivity$connectService$3.f5134p$ = (CoroutineScope) obj;
        return factoryTestMenuActivity$connectService$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FactoryTestMenuActivity$connectService$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5134p$;
            AIDLConnection<HardwareInterface> hdService = ServiceConnectionKt.getHdService();
            Context context = this.$context;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (AIDLConnection.connect$default(hdService, context, null, this, 2, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        AIDLConnection<RobotInterface> rbService = ServiceConnectionKt.getRbService();
        Context context2 = this.$context;
        this.L$0 = coroutineScope;
        this.label = 2;
        if (AIDLConnection.connect$default(rbService, context2, null, this, 2, null) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
