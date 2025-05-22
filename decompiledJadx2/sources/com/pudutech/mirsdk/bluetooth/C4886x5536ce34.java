package com.pudutech.mirsdk.bluetooth;

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

/* compiled from: BluetoothBleHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/mirsdk/bluetooth/BluetoothBleHelper$mGattCallback$1$onConnectionStateChange$1$2"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.mirsdk.bluetooth.BluetoothBleHelper$mGattCallback$1$onConnectionStateChange$$inlined$apply$lambda$2 */
/* loaded from: classes5.dex */
final class C4886x5536ce34 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ BluetoothGatt $gatt$inlined;
    final /* synthetic */ Ref.BooleanRef $isAllowNotify$inlined;
    final /* synthetic */ String $mac$inlined;
    final /* synthetic */ int $newState$inlined;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5719p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C4886x5536ce34(Continuation continuation, int i, String str, Ref.BooleanRef booleanRef, BluetoothGatt bluetoothGatt) {
        super(2, continuation);
        this.$newState$inlined = i;
        this.$mac$inlined = str;
        this.$isAllowNotify$inlined = booleanRef;
        this.$gatt$inlined = bluetoothGatt;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C4886x5536ce34 c4886x5536ce34 = new C4886x5536ce34(completion, this.$newState$inlined, this.$mac$inlined, this.$isAllowNotify$inlined, this.$gatt$inlined);
        c4886x5536ce34.f5719p$ = (CoroutineScope) obj;
        return c4886x5536ce34;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C4886x5536ce34) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5719p$;
        this.$gatt$inlined.requestMtu(128);
        return Unit.INSTANCE;
    }
}
