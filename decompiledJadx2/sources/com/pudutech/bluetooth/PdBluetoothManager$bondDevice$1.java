package com.pudutech.bluetooth;

import com.iflytek.speech.UtilityConfig;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PdBluetoothManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0083@"}, m3961d2 = {"bondDevice", "", UtilityConfig.KEY_DEVICE_INFO, "Landroid/bluetooth/BluetoothDevice;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bluetooth.PdBluetoothManager", m3970f = "PdBluetoothManager.kt", m3971i = {0, 0}, m3972l = {153}, m3973m = "bondDevice", m3974n = {"this", UtilityConfig.KEY_DEVICE_INFO}, m3975s = {"L$0", "L$1"})
/* loaded from: classes4.dex */
public final class PdBluetoothManager$bondDevice$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PdBluetoothManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PdBluetoothManager$bondDevice$1(PdBluetoothManager pdBluetoothManager, Continuation continuation) {
        super(continuation);
        this.this$0 = pdBluetoothManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.bondDevice(null, this);
    }
}
