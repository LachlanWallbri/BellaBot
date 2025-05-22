package com.pudutech.mirsdk.mirhardware;

import android.os.SystemClock;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.hardware.ISensorData;
import kotlin.Metadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: RobotHardware.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\n*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0016J(\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0016J \u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m3961d2 = {"com/pudutech/mirsdk/mirhardware/RobotHardware$onOpenSuccess$1", "Lcom/pudutech/mirsdk/hardware/ISensorData$Stub;", "lastSpeedSend", "", "onEncoder", "", "left", "", "right", "interval", "onIMU", "x", "y", CompressorStreamFactory.f8930Z, "onSpeed", "line", "angular", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RobotHardware$onOpenSuccess$1 extends ISensorData.Stub {
    private long lastSpeedSend;
    final /* synthetic */ RobotHardware this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RobotHardware$onOpenSuccess$1(RobotHardware robotHardware) {
        this.this$0 = robotHardware;
    }

    @Override // com.pudutech.mirsdk.hardware.ISensorData
    public void onSpeed(double line, double angular, double interval) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.this$0.getRobotStatus().getSpeed().getValue().setLine(line);
        this.this$0.getRobotStatus().getSpeed().getValue().setAngular(angular);
        if (elapsedRealtime - this.lastSpeedSend > 100) {
            this.lastSpeedSend = elapsedRealtime;
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, FunctionScope.INSTANCE.getListenerWorker(), null, new RobotHardware$onOpenSuccess$1$onSpeed$1(this, null), 2, null);
        }
    }

    @Override // com.pudutech.mirsdk.hardware.ISensorData
    public void onIMU(double x, double y, double z, double interval) {
        this.this$0.getWatchDog().tick("IMU");
        this.this$0.getWatchDog().tick("CAN");
    }

    @Override // com.pudutech.mirsdk.hardware.ISensorData
    public void onEncoder(double left, double right, double interval) {
        this.this$0.getWatchDog().tick("Encoder");
        this.this$0.getWatchDog().tick("CAN");
    }
}
