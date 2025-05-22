package com.felhr.deviceids;

import com.hoho.android.usbserial.driver.UsbId;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CP2130Ids {
    private static final ConcreteDevice[] cp2130Devices = {new ConcreteDevice(UsbId.VENDOR_SILABS, 34720)};

    public static boolean isDeviceSupported(int i, int i2) {
        int i3 = 0;
        while (true) {
            ConcreteDevice[] concreteDeviceArr = cp2130Devices;
            if (i3 > concreteDeviceArr.length - 1) {
                return false;
            }
            if (concreteDeviceArr[i3].vendorId == i && cp2130Devices[i3].productId == i2) {
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
