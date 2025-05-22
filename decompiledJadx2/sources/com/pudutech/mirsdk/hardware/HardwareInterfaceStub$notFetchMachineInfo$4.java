package com.pudutech.mirsdk.hardware;

import com.pudutech.mirsdk.hardware.can.CANParserManager;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0004\b\u0007\u0010\b"}, m3961d2 = {"<anonymous>", "", "p1", "Lkotlin/UByte;", "Lkotlin/ParameterName;", "name", "protocol", "invoke", "(B)V"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final /* synthetic */ class HardwareInterfaceStub$notFetchMachineInfo$4 extends FunctionReference implements Function1<UByte, Unit> {
    HardwareInterfaceStub$notFetchMachineInfo$4(CANParserManager cANParserManager) {
        super(1, cANParserManager);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "unRegCanParser";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(CANParserManager.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "unRegCanParser-7apg3OU(B)V";
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(UByte uByte) {
        invoke(uByte.getData());
        return Unit.INSTANCE;
    }

    public final void invoke(byte b) {
        ((CANParserManager) this.receiver).m4429unRegCanParser7apg3OU(b);
    }
}
