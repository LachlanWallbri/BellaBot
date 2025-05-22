package com.felhr.deviceids;

import com.hoho.android.usbserial.driver.UsbId;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public class CH34xIds {
    private static final ConcreteDevice[] ch34xDevices = {new ConcreteDevice(17224, UsbId.QINHENG_CH341A), new ConcreteDevice(UsbId.VENDOR_QINHENG, UsbId.QINHENG_CH340), new ConcreteDevice(UsbId.VENDOR_QINHENG, UsbId.QINHENG_CH341A), new ConcreteDevice(UsbId.VENDOR_QINHENG, 1093)};

    private CH34xIds() {
    }

    public static boolean isDeviceSupported(int i, int i2) {
        int i3 = 0;
        while (true) {
            ConcreteDevice[] concreteDeviceArr = ch34xDevices;
            if (i3 > concreteDeviceArr.length - 1) {
                return false;
            }
            if (concreteDeviceArr[i3].vendorId == i && ch34xDevices[i3].productId == i2) {
                return true;
            }
            i3++;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    private static class ConcreteDevice {
        public int productId;
        public int vendorId;

        public ConcreteDevice(int i, int i2) {
            this.vendorId = i;
            this.productId = i2;
        }
    }
}
