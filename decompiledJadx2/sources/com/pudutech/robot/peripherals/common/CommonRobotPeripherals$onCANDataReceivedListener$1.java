package com.pudutech.robot.peripherals.common;

import com.pudutech.mirsdk.hardware.ICANData;
import com.pudutech.robot.peripherals.manager.CANConfig;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: CommonRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/robot/peripherals/common/CommonRobotPeripherals$onCANDataReceivedListener$1", "Lcom/pudutech/mirsdk/hardware/ICANData$Stub;", "onData", "", "id", "", "data", "", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CommonRobotPeripherals$onCANDataReceivedListener$1 extends ICANData.Stub {
    final /* synthetic */ CommonRobotPeripherals this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CommonRobotPeripherals$onCANDataReceivedListener$1(CommonRobotPeripherals commonRobotPeripherals) {
        this.this$0 = commonRobotPeripherals;
    }

    @Override // com.pudutech.mirsdk.hardware.ICANData
    public void onData(int id, byte[] data) {
        byte[] bArr;
        Intrinsics.checkParameterIsNotNull(data, "data");
        byte b = (byte) id;
        byte m4528constructorimpl = UByte.m4528constructorimpl(b);
        if (m4528constructorimpl == CANConfig.INSTANCE.getCAN_CMD_HEAD_BO_PROTOCOL_SHUTDOWN_EVENT()) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new CommonRobotPeripherals$onCANDataReceivedListener$1$onData$1(this, null), 3, null);
            return;
        }
        if (m4528constructorimpl == CANConfig.INSTANCE.getCAN_CMD_HEAD_8B_PROTOCOL_CONTROL_HATCH()) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new CommonRobotPeripherals$onCANDataReceivedListener$1$onData$2(null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new CommonRobotPeripherals$onCANDataReceivedListener$1$onData$3(this, id, data, null), 3, null);
        } else {
            bArr = this.this$0.recvCmds;
            if (UByteArray.m4573contains7apg3OU(bArr, UByte.m4528constructorimpl(b))) {
                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new CommonRobotPeripherals$onCANDataReceivedListener$1$onData$4(this, id, data, null), 3, null);
            }
        }
    }
}
