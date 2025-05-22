package com.pudutech.gatecontrollerlib;

import android.bluetooth.BluetoothGatt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: GateController.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/gatecontrollerlib/GateController$mGattCallback$1$onConnectionStateChange$1$2"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.gatecontrollerlib.GateController$mGattCallback$1$onConnectionStateChange$$inlined$apply$lambda$2 */
/* loaded from: classes5.dex */
final class C4595xf9857f20 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ BluetoothGatt $gatt$inlined;
    final /* synthetic */ Ref.BooleanRef $isAllowNotify$inlined;
    final /* synthetic */ String $mac$inlined;
    final /* synthetic */ int $newState$inlined;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5307p$;
    final /* synthetic */ GateController$mGattCallback$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C4595xf9857f20(Continuation continuation, GateController$mGattCallback$1 gateController$mGattCallback$1, int i, String str, Ref.BooleanRef booleanRef, BluetoothGatt bluetoothGatt) {
        super(2, continuation);
        this.this$0 = gateController$mGattCallback$1;
        this.$newState$inlined = i;
        this.$mac$inlined = str;
        this.$isAllowNotify$inlined = booleanRef;
        this.$gatt$inlined = bluetoothGatt;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C4595xf9857f20 c4595xf9857f20 = new C4595xf9857f20(completion, this.this$0, this.$newState$inlined, this.$mac$inlined, this.$isAllowNotify$inlined, this.$gatt$inlined);
        c4595xf9857f20.f5307p$ = (CoroutineScope) obj;
        return c4595xf9857f20;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C4595xf9857f20) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int i;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5307p$;
        BluetoothGatt bluetoothGatt = this.$gatt$inlined;
        i = this.this$0.this$0.MTU_SIZE;
        bluetoothGatt.requestMtu(i);
        return Unit.INSTANCE;
    }
}
