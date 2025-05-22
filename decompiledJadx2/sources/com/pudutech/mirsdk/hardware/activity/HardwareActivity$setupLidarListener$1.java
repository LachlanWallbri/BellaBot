package com.pudutech.mirsdk.hardware.activity;

import com.pudutech.mirsdk.hardware.HardwareScopeKt;
import com.pudutech.mirsdk.hardware.ILidarData;
import com.pudutech.mirsdk.hardware.serialize.PolarCoordinates;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J'\u0010\u0002\u001a\u00020\u00032\u0010\u0010\u0004\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0006\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016¢\u0006\u0002\u0010\t¨\u0006\n"}, m3961d2 = {"com/pudutech/mirsdk/hardware/activity/HardwareActivity$setupLidarListener$1", "Lcom/pudutech/mirsdk/hardware/ILidarData$Stub;", "onFrame", "", "p0", "", "Lcom/pudutech/mirsdk/hardware/serialize/PolarCoordinates;", "p1", "", "([Lcom/pudutech/mirsdk/hardware/serialize/PolarCoordinates;J)V", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class HardwareActivity$setupLidarListener$1 extends ILidarData.Stub {
    final /* synthetic */ HardwareActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HardwareActivity$setupLidarListener$1(HardwareActivity hardwareActivity) {
        this.this$0 = hardwareActivity;
    }

    @Override // com.pudutech.mirsdk.hardware.ILidarData
    public void onFrame(PolarCoordinates[] p0, long p1) {
        BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), Dispatchers.getMain(), null, new HardwareActivity$setupLidarListener$1$onFrame$1(this, p1, p0, null), 2, null);
    }
}
