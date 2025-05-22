package com.pudutech.schedulerlib.connection;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.easynodelib.EasyNodeDataCatcher;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PeanutEspService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, m3961d2 = {"com/pudutech/schedulerlib/connection/PeanutEspService$initEasyNode$2", "Lcom/pudutech/easynodelib/EasyNodeDataCatcher;", "callback", "", "data", "", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class PeanutEspService$initEasyNode$2 implements EasyNodeDataCatcher {
    PeanutEspService$initEasyNode$2() {
    }

    @Override // com.pudutech.easynodelib.EasyNodeDataCatcher
    public void callback(byte[] data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        Pdlog.m3273d("PeanutEspService", "esp heartbeat data:" + PeanutEspServiceKt.toHexString(data));
        PeanutEspService.INSTANCE.setHeartbeatTime(SystemClock.elapsedRealtime());
    }
}
