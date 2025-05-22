package com.pudutech.schedulerlib.connection;

import com.pudutech.easynodelib.EasyNodeDataCatcher;
import com.pudutech.schedulerlib.connection.PeanutEspService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PeanutEspService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, m3961d2 = {"com/pudutech/schedulerlib/connection/PeanutEspService$initEasyNode$1", "Lcom/pudutech/easynodelib/EasyNodeDataCatcher;", "callback", "", "data", "", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class PeanutEspService$initEasyNode$1 implements EasyNodeDataCatcher {
    PeanutEspService$initEasyNode$1() {
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [kotlin.jvm.functions.Function3] */
    @Override // com.pudutech.easynodelib.EasyNodeDataCatcher
    public void callback(byte[] data) {
        PeanutEspService.OnEspListener onEspListener;
        Intrinsics.checkParameterIsNotNull(data, "data");
        if (PeanutEspService.INSTANCE.getPause()) {
            return;
        }
        PeanutEspService peanutEspService = PeanutEspService.INSTANCE;
        peanutEspService.setReceiveIndex(peanutEspService.getReceiveIndex() + 1);
        ?? onTestListener = PeanutEspService.INSTANCE.getOnTestListener();
        if (onTestListener != 0) {
            PeanutEspService peanutEspService2 = PeanutEspService.INSTANCE;
            int receiveIndex = peanutEspService2.getReceiveIndex();
            peanutEspService2.setReceiveIndex(receiveIndex + 1);
        }
        PeanutEspService peanutEspService3 = PeanutEspService.INSTANCE;
        onEspListener = PeanutEspService.mOnEspListener;
        if (onEspListener != null) {
            onEspListener.onEspData(data);
        }
    }
}
