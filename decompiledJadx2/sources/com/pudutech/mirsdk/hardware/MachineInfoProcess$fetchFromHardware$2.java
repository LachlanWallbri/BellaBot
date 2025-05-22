package com.pudutech.mirsdk.hardware;

import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: MachineInfoProcess.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u00062\u0015\u0010\u0007\u001a\u00110\b¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t¢\u0006\u0004\b\n\u0010\u000b"}, m3961d2 = {"<anonymous>", "", "p1", "", "Lkotlin/ParameterName;", "name", "id", "p2", "Lkotlin/UByteArray;", "byteArray", "invoke", "(I[B)V"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final /* synthetic */ class MachineInfoProcess$fetchFromHardware$2 extends FunctionReference implements Function2<Integer, UByteArray, Unit> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public MachineInfoProcess$fetchFromHardware$2(MachineInfoProcess machineInfoProcess) {
        super(2, machineInfoProcess);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "parseMachineInfoCAN";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(MachineInfoProcess.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "parseMachineInfoCAN-eUNIVaU(I[B)V";
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
        invoke(num.intValue(), uByteArray.getStorage());
        return Unit.INSTANCE;
    }

    public final void invoke(int i, byte[] p2) {
        Intrinsics.checkParameterIsNotNull(p2, "p2");
        ((MachineInfoProcess) this.receiver).m4416parseMachineInfoCANeUNIVaU(i, p2);
    }
}
