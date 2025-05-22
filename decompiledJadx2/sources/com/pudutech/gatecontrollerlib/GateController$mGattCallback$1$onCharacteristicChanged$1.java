package com.pudutech.gatecontrollerlib;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import com.pudutech.base.architecture.ThreadSafeListener;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: GateController.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.gatecontrollerlib.GateController$mGattCallback$1$onCharacteristicChanged$1", m3970f = "GateController.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
public final class GateController$mGattCallback$1$onCharacteristicChanged$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ byte[] $data;
    final /* synthetic */ BluetoothGatt $gatt;
    final /* synthetic */ GateControllerMsg $msg;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5308p$;
    final /* synthetic */ GateController$mGattCallback$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GateController$mGattCallback$1$onCharacteristicChanged$1(GateController$mGattCallback$1 gateController$mGattCallback$1, BluetoothGatt bluetoothGatt, byte[] bArr, GateControllerMsg gateControllerMsg, Continuation continuation) {
        super(2, continuation);
        this.this$0 = gateController$mGattCallback$1;
        this.$gatt = bluetoothGatt;
        this.$data = bArr;
        this.$msg = gateControllerMsg;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        GateController$mGattCallback$1$onCharacteristicChanged$1 gateController$mGattCallback$1$onCharacteristicChanged$1 = new GateController$mGattCallback$1$onCharacteristicChanged$1(this.this$0, this.$gatt, this.$data, this.$msg, completion);
        gateController$mGattCallback$1$onCharacteristicChanged$1.f5308p$ = (CoroutineScope) obj;
        return gateController$mGattCallback$1$onCharacteristicChanged$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GateController$mGattCallback$1$onCharacteristicChanged$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ThreadSafeListener threadSafeListener;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5308p$;
        threadSafeListener = this.this$0.this$0.listeners;
        threadSafeListener.notify(new Function2<GateControllerListener, String, Unit>() { // from class: com.pudutech.gatecontrollerlib.GateController$mGattCallback$1$onCharacteristicChanged$1.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(GateControllerListener gateControllerListener, String str) {
                invoke2(gateControllerListener, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GateControllerListener it, String name) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(name, "name");
                BluetoothDevice device = GateController$mGattCallback$1$onCharacteristicChanged$1.this.$gatt.getDevice();
                String address = device != null ? device.getAddress() : null;
                if (address == null) {
                    Intrinsics.throwNpe();
                }
                it.onData(address, GateController$mGattCallback$1$onCharacteristicChanged$1.this.$data);
                BluetoothDevice device2 = GateController$mGattCallback$1$onCharacteristicChanged$1.this.$gatt.getDevice();
                String address2 = device2 != null ? device2.getAddress() : null;
                if (address2 == null) {
                    Intrinsics.throwNpe();
                }
                it.onCommandResult(address2, GateController$mGattCallback$1$onCharacteristicChanged$1.this.$msg);
            }
        });
        return Unit.INSTANCE;
    }
}
