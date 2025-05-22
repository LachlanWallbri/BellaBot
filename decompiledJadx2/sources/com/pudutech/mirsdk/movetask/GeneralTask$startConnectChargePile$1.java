package com.pudutech.mirsdk.movetask;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.bluetooth.BluetoothBleHelper;
import com.pudutech.mirsdk.bluetooth.BluetoothBleListener;
import com.pudutech.mirsdk.charge.ChargeFinder;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: GeneralTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.GeneralTask$startConnectChargePile$1", m3970f = "GeneralTask.kt", m3971i = {0, 0}, m3972l = {718}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$apply"}, m3975s = {"L$0", "L$2"})
/* loaded from: classes6.dex */
public final class GeneralTask$startConnectChargePile$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $mac;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6422p$;
    final /* synthetic */ GeneralTask this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GeneralTask$startConnectChargePile$1(GeneralTask generalTask, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = generalTask;
        this.$mac = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        GeneralTask$startConnectChargePile$1 generalTask$startConnectChargePile$1 = new GeneralTask$startConnectChargePile$1(this.this$0, this.$mac, completion);
        generalTask$startConnectChargePile$1.f6422p$ = (CoroutineScope) obj;
        return generalTask$startConnectChargePile$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GeneralTask$startConnectChargePile$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x00a8, code lost:
    
        if (r1 == null) goto L22;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        BluetoothBleListener bluetoothBleListener;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6422p$;
            this.this$0.isRecStatusReply = false;
            BluetoothBleHelper.scanBleDevice$default(BluetoothBleHelper.INSTANCE, false, 0L, null, 6, null);
            this.this$0.initCallBack(this.$mac);
            ChargeFinder.INSTANCE.cancelConnectDevice();
            bluetoothBleListener = this.this$0.callback;
            if (bluetoothBleListener != null) {
                if (!Intrinsics.areEqual(bluetoothBleListener.getMac(), this.$mac)) {
                    BluetoothBleHelper.INSTANCE.disconnectDevice(bluetoothBleListener.getMac());
                    this.L$0 = coroutineScope;
                    this.L$1 = bluetoothBleListener;
                    this.L$2 = bluetoothBleListener;
                    this.label = 1;
                    if (DelayKt.delay(500L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (BluetoothBleHelper.INSTANCE.getConnectState(this.$mac) == 2) {
                    Pdlog.m3273d(this.this$0.TAG, "device already connected");
                    this.this$0.doBluetoothChargeState(bluetoothBleListener.getMac());
                } else {
                    BluetoothBleHelper.INSTANCE.connectDevice(this.$mac);
                }
            }
            BluetoothBleHelper.INSTANCE.connectDevice(this.$mac);
            Unit unit = Unit.INSTANCE;
            return Unit.INSTANCE;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        BluetoothBleListener bluetoothBleListener2 = (BluetoothBleListener) this.L$1;
        ResultKt.throwOnFailure(obj);
        bluetoothBleListener = bluetoothBleListener2;
        BluetoothBleHelper.INSTANCE.connectDevice(this.$mac);
    }
}
