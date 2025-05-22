package com.pudutech.lidar.base;

import com.felhr.utils.HexData;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarAdapterCallback;
import com.pudutech.lidar.port.SerialUSB;
import com.pudutech.lidar.port.SerialUSBListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: SerialUSBLidarAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u001a\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, m3961d2 = {"com/pudutech/lidar/base/SerialUSBLidarAdapter$startLidarService$1", "Lcom/pudutech/lidar/port/SerialUSBListener;", "onDeviceDisConnect", "", "onDeviceOpen", "isSuccess", "", "description", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class SerialUSBLidarAdapter$startLidarService$1 implements SerialUSBListener {
    final /* synthetic */ SerialUSBLidarAdapter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SerialUSBLidarAdapter$startLidarService$1(SerialUSBLidarAdapter serialUSBLidarAdapter) {
        this.this$0 = serialUSBLidarAdapter;
    }

    @Override // com.pudutech.lidar.port.SerialUSBListener
    public void onDeviceOpen(boolean isSuccess, String description) {
        Pdlog.m3275i(this.this$0.getTAG(), "onDeviceOpen isSuccess=" + isSuccess + "  description=" + description);
        if (isSuccess) {
            this.this$0.isLidarConnect = true;
            this.this$0.getLidar().setLidarInterface(new SerialLidarInterface() { // from class: com.pudutech.lidar.base.SerialUSBLidarAdapter$startLidarService$1$onDeviceOpen$1
                @Override // com.pudutech.lidar.base.SerialLidarInterface
                public void send(byte[] data) {
                    SerialUSB serialUSB;
                    Intrinsics.checkParameterIsNotNull(data, "data");
                    Pdlog.m3273d(SerialUSBLidarAdapter$startLidarService$1.this.this$0.getTAG(), "USB write data is " + HexData.hexToString(data));
                    serialUSB = SerialUSBLidarAdapter$startLidarService$1.this.this$0.serialUSB;
                    if (serialUSB != null) {
                        serialUSB.write(data);
                    }
                }

                @Override // com.pudutech.lidar.base.SerialLidarInterface
                public void setDTR(boolean onOrOff) {
                    SerialUSB serialUSB;
                    Pdlog.m3273d(SerialUSBLidarAdapter$startLidarService$1.this.this$0.getTAG(), "set DTR " + onOrOff);
                    serialUSB = SerialUSBLidarAdapter$startLidarService$1.this.this$0.serialUSB;
                    if (serialUSB != null) {
                        serialUSB.setDTR(onOrOff);
                    }
                }
            });
            this.this$0.startScan();
        } else {
            LidarAdapterCallback lidarAdapterCallback = this.this$0.getLidarAdapterCallback();
            LidarErrorType lidarErrorType = LidarErrorType.OPEN_FAIL;
            if (description == null) {
                description = "";
            }
            lidarAdapterCallback.onError(new LidarError(lidarErrorType, description));
        }
    }

    @Override // com.pudutech.lidar.port.SerialUSBListener
    public void onDeviceDisConnect() {
        long j;
        this.this$0.isLidarConnect = false;
        j = this.this$0.lastStartTimestamp;
        if (j != 0) {
            Pdlog.m3274e(this.this$0.getTAG(), "onDeviceDisConnect");
            this.this$0.getLidarAdapterCallback().onError(new LidarError(LidarErrorType.DISCONNECTED, "Lidar USB Disconnected"));
        }
    }
}
