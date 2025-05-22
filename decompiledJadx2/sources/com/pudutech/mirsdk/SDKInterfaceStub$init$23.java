package com.pudutech.mirsdk;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "errors", "Lkotlin/Pair;", "", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class SDKInterfaceStub$init$23 extends Lambda implements Function1<Pair<? extends Boolean, ? extends String>, Unit> {
    public static final SDKInterfaceStub$init$23 INSTANCE = new SDKInterfaceStub$init$23();

    SDKInterfaceStub$init$23() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends Boolean, ? extends String> pair) {
        invoke2((Pair<Boolean, String>) pair);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Pair<Boolean, String> errors) {
        Intrinsics.checkParameterIsNotNull(errors, "errors");
        SDKInterfaceStub.access$getDanceService$p(SDKInterfaceStub.INSTANCE).suspendWarningWelfunction(errors);
    }
}
