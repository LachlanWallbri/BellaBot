package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.SDKInterfaceStub;
import com.pudutech.mirsdk.mircore.InitServiceListener;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitState;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitStep;
import kotlin.Metadata;
import kotlin.jvm.internal.Ref;

/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n"}, m3961d2 = {"com/pudutech/mirsdk/SDKInterfaceStub$initCore$4", "Lcom/pudutech/mirsdk/mircore/InitServiceListener$Stub;", "initCoreServiceState", "", "p0", "Lcom/pudutech/mirsdk/mircore/coreparcel/CoreInitStep;", "p1", "Lcom/pudutech/mirsdk/mircore/coreparcel/CoreInitState;", "p2", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SDKInterfaceStub$initCore$4 extends InitServiceListener.Stub {
    final /* synthetic */ Ref.ObjectRef $result;
    final /* synthetic */ Ref.ObjectRef $state;
    final /* synthetic */ Ref.ObjectRef $step;

    SDKInterfaceStub$initCore$4(Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, Ref.ObjectRef objectRef3) {
        this.$state = objectRef;
        this.$result = objectRef2;
        this.$step = objectRef3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1, types: [T, java.lang.String] */
    @Override // com.pudutech.mirsdk.mircore.InitServiceListener
    public void initCoreServiceState(CoreInitStep p0, CoreInitState p1, String p2) {
        String str;
        if (p0 != 0 && SDKInterfaceStub.WhenMappings.$EnumSwitchMapping$8[p0.ordinal()] == 1) {
            if (p1 == CoreInitState.Success) {
                this.$state.element = p1;
                SDKInterfaceStub.access$initCameraScheme(SDKInterfaceStub.INSTANCE);
            }
            SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
            str = SDKInterfaceStub.TAG;
            Pdlog.m3273d(str, "coreService.initModules CoreInitState.Success state is " + ((CoreInitState) this.$state.element));
        } else if (p1 == CoreInitState.Fail) {
            this.$state.element = p1;
            Ref.ObjectRef objectRef = this.$result;
            objectRef.element = ((String) objectRef.element) + p2;
        }
        this.$step.element = p0;
    }
}
