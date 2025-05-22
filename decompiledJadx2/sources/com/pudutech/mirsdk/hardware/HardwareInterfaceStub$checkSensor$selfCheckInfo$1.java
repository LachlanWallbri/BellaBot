package com.pudutech.mirsdk.hardware;

import com.iflytek.cloud.SpeechUtility;
import com.pudutech.mirsdk.compat.topo.ConfigJson;
import com.pudutech.mirsdk.hardware.HardwareInterfaceStub;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/mirsdk/hardware/HardwareInterfaceStub$checkSensor$selfCheckInfo$1", "Lcom/pudutech/mirsdk/hardware/HardwareInterfaceStub$SelfCheckSensorInfo;", "onState", "", ConfigJson.SENSOR, "Lcom/pudutech/mirsdk/hardware/HardwareInterfaceStub$SelfCheckSensor;", SpeechUtility.TAG_RESOURCE_RESULT, "", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class HardwareInterfaceStub$checkSensor$selfCheckInfo$1 implements HardwareInterfaceStub.SelfCheckSensorInfo {
    final /* synthetic */ List $sensorList;

    HardwareInterfaceStub$checkSensor$selfCheckInfo$1(List list) {
        this.$sensorList = list;
    }

    @Override // com.pudutech.mirsdk.hardware.HardwareInterfaceStub.SelfCheckSensorInfo
    public void onState(HardwareInterfaceStub.SelfCheckSensor sensor, boolean result) {
        AtomicBoolean atomicBoolean;
        AtomicBoolean atomicBoolean2;
        Intrinsics.checkParameterIsNotNull(sensor, "sensor");
        synchronized (this.$sensorList) {
            if (this.$sensorList.contains(sensor)) {
                if (!result) {
                    HardwareInterfaceStub hardwareInterfaceStub = HardwareInterfaceStub.INSTANCE;
                    atomicBoolean2 = HardwareInterfaceStub.waitSelfCheck;
                    atomicBoolean2.set(false);
                } else {
                    this.$sensorList.remove(sensor);
                }
                if (this.$sensorList.isEmpty()) {
                    HardwareInterfaceStub hardwareInterfaceStub2 = HardwareInterfaceStub.INSTANCE;
                    atomicBoolean = HardwareInterfaceStub.waitSelfCheck;
                    atomicBoolean.set(false);
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }
}
