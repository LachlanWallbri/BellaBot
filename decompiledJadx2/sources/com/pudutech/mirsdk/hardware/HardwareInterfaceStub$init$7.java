package com.pudutech.mirsdk.hardware;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0018\u00010\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u00052\u0017\u0010\u0006\u001a\u0013\u0018\u00010\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b2\u0017\u0010\t\u001a\u0013\u0018\u00010\n¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u000b¢\u0006\u0002\b\f"}, m3961d2 = {"<anonymous>", "", "p1", "", "Lkotlin/ParameterName;", "name", "p2", "", "idList", "p3", "Lcom/pudutech/mirsdk/hardware/ICANData;", "listener", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
final /* synthetic */ class HardwareInterfaceStub$init$7 extends FunctionReference implements Function3<String, byte[], ICANData, Unit> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public HardwareInterfaceStub$init$7(HardwareInterfaceStub hardwareInterfaceStub) {
        super(3, hardwareInterfaceStub);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "addCANDataListener";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(HardwareInterfaceStub.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "addCANDataListener(Ljava/lang/String;[BLcom/pudutech/mirsdk/hardware/ICANData;)V";
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(String str, byte[] bArr, ICANData iCANData) {
        invoke2(str, bArr, iCANData);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(String str, byte[] bArr, ICANData iCANData) {
        ((HardwareInterfaceStub) this.receiver).addCANDataListener(str, bArr, iCANData);
    }
}
