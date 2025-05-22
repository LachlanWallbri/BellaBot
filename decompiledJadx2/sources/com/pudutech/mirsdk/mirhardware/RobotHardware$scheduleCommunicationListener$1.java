package com.pudutech.mirsdk.mirhardware;

import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.hardware.SchCommunicateInfoListener;
import com.pudutech.mirsdk.hardware.serialize.ScheduleCommunicateInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: RobotHardware.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\f"}, m3961d2 = {"com/pudutech/mirsdk/mirhardware/RobotHardware$scheduleCommunicationListener$1", "Lcom/pudutech/mirsdk/hardware/SchCommunicateInfoListener$Stub;", "count", "", "getCount", "()I", "setCount", "(I)V", "scheduleCommunicateInfo", "", "p0", "Lcom/pudutech/mirsdk/hardware/serialize/ScheduleCommunicateInfo;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RobotHardware$scheduleCommunicationListener$1 extends SchCommunicateInfoListener.Stub {
    final /* synthetic */ ThreadSafeListener $listener;
    private int count;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RobotHardware$scheduleCommunicationListener$1(ThreadSafeListener threadSafeListener) {
        this.$listener = threadSafeListener;
    }

    public final int getCount() {
        return this.count;
    }

    public final void setCount(int i) {
        this.count = i;
    }

    @Override // com.pudutech.mirsdk.hardware.SchCommunicateInfoListener
    public void scheduleCommunicateInfo(ScheduleCommunicateInfo p0) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        this.count = p0.getEsp_count() >= p0.getUdp_count() ? p0.getEsp_count() : p0.getUdp_count();
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, FunctionScope.INSTANCE.getListenerWorker(), null, new C5276xb8d53a21(this, null), 2, null);
    }
}
