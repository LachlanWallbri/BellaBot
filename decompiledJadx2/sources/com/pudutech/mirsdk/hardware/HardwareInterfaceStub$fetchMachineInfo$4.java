package com.pudutech.mirsdk.hardware;

import com.pudutech.mirsdk.hardware.can.CANBus;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0004\b\u0007\u0010\b"}, m3961d2 = {"<anonymous>", "", "p1", "Lkotlin/UByteArray;", "Lkotlin/ParameterName;", "name", "bytes", "invoke", "([B)V"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final /* synthetic */ class HardwareInterfaceStub$fetchMachineInfo$4 extends FunctionReference implements Function1<UByteArray, Unit> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public HardwareInterfaceStub$fetchMachineInfo$4(CANBus cANBus) {
        super(1, cANBus);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return MqttServiceConstants.SEND_ACTION;
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(CANBus.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "send-GBYM_sE([B)V";
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(UByteArray uByteArray) {
        invoke(uByteArray.getStorage());
        return Unit.INSTANCE;
    }

    public final void invoke(byte[] p1) {
        Intrinsics.checkParameterIsNotNull(p1, "p1");
        ((CANBus) this.receiver).m4425sendGBYM_sE(p1);
    }
}
