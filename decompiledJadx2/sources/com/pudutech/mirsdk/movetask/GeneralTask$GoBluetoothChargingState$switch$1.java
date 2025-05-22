package com.pudutech.mirsdk.movetask;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.MoveAction;
import com.pudutech.mirsdk.map.elements.ChargingPile;
import com.pudutech.mirsdk.mircore.coreparcel.PathSegments;
import com.pudutech.mirsdk.movetask.GeneralTask;
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

/* compiled from: GeneralTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.GeneralTask$GoBluetoothChargingState$switch$1", m3970f = "GeneralTask.kt", m3971i = {0}, m3972l = {914}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes6.dex */
final class GeneralTask$GoBluetoothChargingState$switch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6399p$;
    final /* synthetic */ GeneralTask.GoBluetoothChargingState this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GeneralTask$GoBluetoothChargingState$switch$1(GeneralTask.GoBluetoothChargingState goBluetoothChargingState, Continuation continuation) {
        super(2, continuation);
        this.this$0 = goBluetoothChargingState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        GeneralTask$GoBluetoothChargingState$switch$1 generalTask$GoBluetoothChargingState$switch$1 = new GeneralTask$GoBluetoothChargingState$switch$1(this.this$0, completion);
        generalTask$GoBluetoothChargingState$switch$1.f6399p$ = (CoroutineScope) obj;
        return generalTask$GoBluetoothChargingState$switch$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GeneralTask$GoBluetoothChargingState$switch$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ChargingPile chargingPile;
        ChargingPile chargingPile2;
        ChargingPile chargingPile3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.L$0 = this.f6399p$;
            this.label = 1;
            if (DelayKt.delay(300L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        Pdlog.m3273d(GeneralTask.this.TAG, "GoBluetoothChargingState try charge again");
        chargingPile = this.this$0.recentPile;
        if (chargingPile != null) {
            String str = GeneralTask.this.TAG;
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("GoBluetoothChargingState try charge id:");
            PathSegments pathSegments = GeneralTask.this.getPathSegments();
            sb.append(pathSegments != null ? pathSegments.getFinalGoal() : null);
            sb.append(" group:");
            chargingPile2 = this.this$0.recentPile;
            sb.append(chargingPile2 != null ? chargingPile2.getGroup() : null);
            objArr[0] = sb.toString();
            Pdlog.m3273d(str, objArr);
            MoveAction action = GeneralTask.this.getAction();
            chargingPile3 = this.this$0.recentPile;
            action.realGoCharging(chargingPile3 != null ? chargingPile3.getGroup() : null);
        } else {
            Pdlog.m3273d(GeneralTask.this.TAG, "GoBluetoothChargingState try charge not find pile");
        }
        return Unit.INSTANCE;
    }
}
