package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.mircore.ErrorListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, m3961d2 = {"com/pudutech/mirsdk/SDKInterfaceStub$addDEngineErrorListener$1", "Lcom/pudutech/mirsdk/mircore/ErrorListener$Stub;", "report", "", "p0", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SDKInterfaceStub$addDEngineErrorListener$1 extends ErrorListener.Stub {
    SDKInterfaceStub$addDEngineErrorListener$1() {
    }

    @Override // com.pudutech.mirsdk.mircore.ErrorListener
    public void report(final int p0) {
        SDKInterfaceStub.access$getIErrorListener$p(SDKInterfaceStub.INSTANCE).notify(new Function2<com.pudutech.mirsdk.aidl.ErrorListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$addDEngineErrorListener$1$report$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(com.pudutech.mirsdk.aidl.ErrorListener errorListener, String str) {
                invoke2(errorListener, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(com.pudutech.mirsdk.aidl.ErrorListener it, String name) {
                String str;
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(name, "name");
                it.report(p0);
                SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
                str = SDKInterfaceStub.TAG;
                Pdlog.m3273d(str, "addDEngineErrorListener report " + p0);
            }
        });
    }
}
