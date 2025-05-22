package com.pudutech.mirsdk.hardware;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.ICANData;
import com.pudutech.mirsdk.hardware.can.CANBus;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: CarpetModeConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u000e\u001a\u00020\u000fX\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/CarpetModeConfig;", "", "can", "Lcom/pudutech/mirsdk/hardware/can/CANBus;", "(Lcom/pudutech/mirsdk/hardware/can/CANBus;)V", "getCan", "()Lcom/pudutech/mirsdk/hardware/can/CANBus;", "canListener", "Lcom/pudutech/mirsdk/hardware/can/CANBus$CANListener;", "<set-?>", "", "carpetmode", "getCarpetmode", "()Z", "requestCarpetModeCmd", "Lkotlin/UByteArray;", "[B", "switchMode", "", "mode", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class CarpetModeConfig {
    private final CANBus can;
    private final CANBus.CANListener canListener;
    private boolean carpetmode;
    private final byte[] requestCarpetModeCmd;

    public CarpetModeConfig(CANBus can) {
        Intrinsics.checkParameterIsNotNull(can, "can");
        this.can = can;
        byte b = (byte) 70;
        this.canListener = new CANBus.CANListener(new byte[]{b}, new ICANData.Stub() { // from class: com.pudutech.mirsdk.hardware.CarpetModeConfig$canListener$1
            @Override // com.pudutech.mirsdk.hardware.ICANData
            public void onData(int id, byte[] data) {
                CarpetModeConfig carpetModeConfig = CarpetModeConfig.this;
                boolean z = false;
                if (data != null && data[1] == ((byte) 1)) {
                    z = true;
                }
                carpetModeConfig.carpetmode = z;
            }
        });
        this.requestCarpetModeCmd = new byte[]{UByte.m4528constructorimpl((byte) 0), UByte.m4528constructorimpl(b)};
        this.can.getCanListener().add("Carpet", this.canListener);
        this.can.m4425sendGBYM_sE(this.requestCarpetModeCmd);
    }

    public final CANBus getCan() {
        return this.can;
    }

    public final boolean getCarpetmode() {
        return this.carpetmode;
    }

    public final void switchMode(boolean mode) {
        Pdlog.m3273d("Hardware", "switch carpet mode " + mode);
        byte[] bArr = new byte[2];
        bArr[0] = UByte.m4528constructorimpl((byte) 69);
        bArr[1] = UByte.m4528constructorimpl(mode ? (byte) 1 : (byte) 0);
        this.can.m4425sendGBYM_sE(bArr);
        this.can.m4425sendGBYM_sE(this.requestCarpetModeCmd);
    }
}
