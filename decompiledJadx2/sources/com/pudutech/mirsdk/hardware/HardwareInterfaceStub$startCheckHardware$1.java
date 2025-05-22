package com.pudutech.mirsdk.hardware;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.bouncycastle.math.Primes;

/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0082@"}, m3961d2 = {"startCheckHardware", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.HardwareInterfaceStub", m3970f = "HardwareInterfaceStub.kt", m3971i = {0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8, 9, 9, 9, 9, 9}, m3972l = {175, 178, 179, 183, 183, 200, 204, Primes.SMALL_FACTOR_LIMIT, 217, 219}, m3973m = "startCheckHardware", m3974n = {"this", "success", "this", "success", "this", "success", "openHeadUsbResult", "this", "success", "openHeadUsbResult", "this", "success", "openHeadUsbResult", "this", "success", "openHeadUsbResult", "this", "success", "openHeadUsbResult", "this", "success", "openHeadUsbResult", "checkTime", "this", "success", "openHeadUsbResult", "checkTime", "this", "success", "openHeadUsbResult", "checkTime", "sensorList"}, m3975s = {"L$0", "Z$0", "L$0", "Z$0", "L$0", "Z$0", "L$1", "L$0", "Z$0", "L$1", "L$0", "Z$0", "L$1", "L$0", "Z$0", "L$1", "L$0", "Z$0", "L$1", "L$0", "Z$0", "L$1", "J$0", "L$0", "Z$0", "L$1", "J$0", "L$0", "Z$0", "L$1", "J$0", "L$2"})
/* loaded from: classes5.dex */
final class HardwareInterfaceStub$startCheckHardware$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HardwareInterfaceStub this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HardwareInterfaceStub$startCheckHardware$1(HardwareInterfaceStub hardwareInterfaceStub, Continuation continuation) {
        super(continuation);
        this.this$0 = hardwareInterfaceStub;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.startCheckHardware(this);
    }
}
