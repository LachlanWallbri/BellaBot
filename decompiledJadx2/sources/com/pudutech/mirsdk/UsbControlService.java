package com.pudutech.mirsdk;

import com.pudutech.mirsdk.aidl.USBCtrlService;
import com.pudutech.mirsdk.hardware.UsbControlInterface;
import kotlin.Metadata;

/* compiled from: UsbControlService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0004J\u0012\u0010\n\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/mirsdk/UsbControlService;", "Lcom/pudutech/mirsdk/aidl/USBCtrlService$Stub;", "()V", "mUsbInterface", "Lcom/pudutech/mirsdk/hardware/UsbControlInterface;", "getBacklight", "", "init", "", "usbInterface", "sendUsbData", "data", "", "setBacklight", "brightness", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class UsbControlService extends USBCtrlService.Stub {
    private UsbControlInterface mUsbInterface;

    public final void init(UsbControlInterface usbInterface) {
        this.mUsbInterface = usbInterface;
    }

    @Override // com.pudutech.mirsdk.aidl.USBCtrlService
    public int setBacklight(int brightness) {
        UsbControlInterface usbControlInterface = this.mUsbInterface;
        if (usbControlInterface != null) {
            return usbControlInterface.setBacklight(brightness);
        }
        return -1;
    }

    @Override // com.pudutech.mirsdk.aidl.USBCtrlService
    public int sendUsbData(byte[] data) {
        UsbControlInterface usbControlInterface = this.mUsbInterface;
        if (usbControlInterface != null) {
            return usbControlInterface.sendUsbData(data);
        }
        return -1;
    }

    @Override // com.pudutech.mirsdk.aidl.USBCtrlService
    public int getBacklight() {
        UsbControlInterface usbControlInterface = this.mUsbInterface;
        if (usbControlInterface != null) {
            return usbControlInterface.getBacklight();
        }
        return 100;
    }
}
