package com.pudutech.mirsdk;

import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.mirsdk.aidl.serialize.StepState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u00062\u0015\u0010\u0007\u001a\u00110\b¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t2\u0015\u0010\n\u001a\u00110\u000b¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\f¢\u0006\u0002\b\r"}, m3961d2 = {"<anonymous>", "", "p1", "Lcom/pudutech/mirsdk/aidl/serialize/InitStep;", "Lkotlin/ParameterName;", "name", "step", "p2", "Lcom/pudutech/mirsdk/aidl/serialize/StepState;", "stepState", "p3", "", "description", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final /* synthetic */ class SDKInterfaceStub$robotHardware$1 extends FunctionReference implements Function3<InitStep, StepState, String, Unit> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SDKInterfaceStub$robotHardware$1(SDKInterfaceStub sDKInterfaceStub) {
        super(3, sDKInterfaceStub);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "initStepNotify";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(SDKInterfaceStub.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "initStepNotify(Lcom/pudutech/mirsdk/aidl/serialize/InitStep;Lcom/pudutech/mirsdk/aidl/serialize/StepState;Ljava/lang/String;)V";
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(InitStep initStep, StepState stepState, String str) {
        invoke2(initStep, stepState, str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(InitStep p1, StepState p2, String p3) {
        Intrinsics.checkParameterIsNotNull(p1, "p1");
        Intrinsics.checkParameterIsNotNull(p2, "p2");
        Intrinsics.checkParameterIsNotNull(p3, "p3");
        ((SDKInterfaceStub) this.receiver).initStepNotify(p1, p2, p3);
    }
}
