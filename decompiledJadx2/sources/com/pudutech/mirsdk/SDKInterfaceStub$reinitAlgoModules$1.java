package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.SDKInterfaceStub;
import com.pudutech.mirsdk.aidl.mapify.LoadCoreListener;
import com.pudutech.mirsdk.aidl.serialize.CoreStepType;
import com.pudutech.mirsdk.mircore.InitServiceListener;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitState;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitStep;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n"}, m3961d2 = {"com/pudutech/mirsdk/SDKInterfaceStub$reinitAlgoModules$1", "Lcom/pudutech/mirsdk/mircore/InitServiceListener$Stub;", "initCoreServiceState", "", "p0", "Lcom/pudutech/mirsdk/mircore/coreparcel/CoreInitStep;", "p1", "Lcom/pudutech/mirsdk/mircore/coreparcel/CoreInitState;", "p2", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SDKInterfaceStub$reinitAlgoModules$1 extends InitServiceListener.Stub {
    SDKInterfaceStub$reinitAlgoModules$1() {
    }

    @Override // com.pudutech.mirsdk.mircore.InitServiceListener
    public void initCoreServiceState(CoreInitStep p0, CoreInitState p1, String p2) {
        ThreadSafeListener threadSafeListener;
        ThreadSafeListener threadSafeListener2;
        if (p0 != null && SDKInterfaceStub.WhenMappings.$EnumSwitchMapping$5[p0.ordinal()] == 1) {
            if (p1 == CoreInitState.Success) {
                SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
                threadSafeListener2 = SDKInterfaceStub.loadCoreListener;
                threadSafeListener2.notify(new Function2<LoadCoreListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$reinitAlgoModules$1$initCoreServiceState$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(LoadCoreListener loadCoreListener, String str) {
                        invoke2(loadCoreListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(LoadCoreListener it, String name) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(name, "name");
                        it.coreInitStepResult(CoreStepType.ReinitAlgoModules, CoreInitState.Success);
                    }
                });
            }
            Pdlog.m3276v("newAlgroim", "coreService.connecting  3 ");
            return;
        }
        if (p1 == CoreInitState.Fail) {
            SDKInterfaceStub sDKInterfaceStub2 = SDKInterfaceStub.INSTANCE;
            threadSafeListener = SDKInterfaceStub.loadCoreListener;
            threadSafeListener.notify(new Function2<LoadCoreListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$reinitAlgoModules$1$initCoreServiceState$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(LoadCoreListener loadCoreListener, String str) {
                    invoke2(loadCoreListener, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(LoadCoreListener it, String name) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(name, "name");
                    it.coreInitStepResult(CoreStepType.ReinitAlgoModules, CoreInitState.Fail);
                }
            });
            Pdlog.m3276v("newAlgroim", "coreService.connecting  3 fail");
        }
    }
}
