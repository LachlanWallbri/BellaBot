package com.pudutech.mirsdk.hardware;

import com.pudutech.mirsdk.hardware.can.CANParserManager;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u00062E\u0010\u0007\u001aA\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00010\b¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\r¢\u0006\u0004\b\u000e\u0010\u000f"}, m3961d2 = {"<anonymous>", "", "p1", "Lkotlin/UByte;", "Lkotlin/ParameterName;", "name", "protocol", "p2", "Lkotlin/Function2;", "", "id", "Lkotlin/UByteArray;", "byteArray", "parser", "invoke", "(BLkotlin/jvm/functions/Function2;)V"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final /* synthetic */ class HardwareInterfaceStub$notFetchMachineInfo$3 extends FunctionReference implements Function2<UByte, Function2<? super Integer, ? super UByteArray, ? extends Unit>, Unit> {
    HardwareInterfaceStub$notFetchMachineInfo$3(CANParserManager cANParserManager) {
        super(2, cANParserManager);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "regCanParser";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(CANParserManager.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "regCanParser-eLRuwBU(BLkotlin/jvm/functions/Function2;)V";
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(UByte uByte, Function2<? super Integer, ? super UByteArray, ? extends Unit> function2) {
        invoke(uByte.getData(), (Function2<? super Integer, ? super UByteArray, Unit>) function2);
        return Unit.INSTANCE;
    }

    public final void invoke(byte b, Function2<? super Integer, ? super UByteArray, Unit> p2) {
        Intrinsics.checkParameterIsNotNull(p2, "p2");
        ((CANParserManager) this.receiver).m4428regCanParsereLRuwBU(b, p2);
    }
}
