package com.pudutech.mirsdk.charge;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.mirsdk.bluetooth.BluetoothBleHelper;
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
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ChargeFinder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.charge.ChargeFinder$doBluetoothChargeState$2", m3970f = "ChargeFinder.kt", m3971i = {0}, m3972l = {TypedValues.Attributes.TYPE_EASING}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
public final class ChargeFinder$doBluetoothChargeState$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $mac;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5732p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChargeFinder$doBluetoothChargeState$2(String str, Continuation continuation) {
        super(2, continuation);
        this.$mac = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ChargeFinder$doBluetoothChargeState$2 chargeFinder$doBluetoothChargeState$2 = new ChargeFinder$doBluetoothChargeState$2(this.$mac, completion);
        chargeFinder$doBluetoothChargeState$2.f5732p$ = (CoroutineScope) obj;
        return chargeFinder$doBluetoothChargeState$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ChargeFinder$doBluetoothChargeState$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        boolean z;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f5732p$;
            ChargeFinder chargeFinder = ChargeFinder.INSTANCE;
            ChargeFinder.isRecStatusReply = false;
            coroutineScope = coroutineScope2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (CoroutineScopeKt.isActive(coroutineScope) && BluetoothBleHelper.INSTANCE.getConnectState(this.$mac) == 2) {
            ChargeMessageCenter.INSTANCE.sendStateReq(this.$mac);
            ChargeFinder chargeFinder2 = ChargeFinder.INSTANCE;
            z = ChargeFinder.isRecStatusReply;
            long j = z ? 10000L : 500L;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(j, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
