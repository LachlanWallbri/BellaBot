package com.pudutech.schedulerlib.connection;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: Timer.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004¸\u0006\u0000"}, m3961d2 = {"kotlin/concurrent/TimersKt$timerTask$1", "Ljava/util/TimerTask;", "run", "", "kotlin-stdlib"}, m3962k = 1, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.schedulerlib.connection.PeanutEspService$initEsp$1$invokeSuspend$$inlined$fixedRateTimer$1 */
/* loaded from: classes7.dex */
public final class C5729x2b574326 extends TimerTask {
    final /* synthetic */ PeanutEspService$initEsp$1 this$0;

    public C5729x2b574326(PeanutEspService$initEsp$1 peanutEspService$initEsp$1) {
        this.this$0 = peanutEspService$initEsp$1;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        if (PeanutEspService.INSTANCE.getHeartbeatTime() == 0 || SystemClock.elapsedRealtime() - PeanutEspService.INSTANCE.getHeartbeatTime() <= 10000) {
            return;
        }
        Pdlog.m3274e("PeanutEspService", "esp heartbeat no data for more than 10s");
        PeanutEspService.INSTANCE.setHeartbeatTime(SystemClock.elapsedRealtime());
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C5730x997a0652(null, this), 3, null);
    }
}
