package com.pudutech.mirsdk;

import com.pudutech.mirsdk.aidl.mapify.LoadCoreListener;
import com.pudutech.mirsdk.aidl.serialize.CoreStepType;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "it", "Lcom/pudutech/mirsdk/aidl/mapify/LoadCoreListener;", "name", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class SDKInterfaceStub$initCore$2$initCoreServiceState$2 extends Lambda implements Function2<LoadCoreListener, String, Unit> {
    public static final SDKInterfaceStub$initCore$2$initCoreServiceState$2 INSTANCE = new SDKInterfaceStub$initCore$2$initCoreServiceState$2();

    SDKInterfaceStub$initCore$2$initCoreServiceState$2() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(LoadCoreListener loadCoreListener, String str) {
        invoke2(loadCoreListener, str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(LoadCoreListener it, String name) {
        Intrinsics.checkParameterIsNotNull(it, "it");
        Intrinsics.checkParameterIsNotNull(name, "name");
        it.coreInitStepResult(CoreStepType.InitAlgoModules, CoreInitState.Fail);
    }
}
