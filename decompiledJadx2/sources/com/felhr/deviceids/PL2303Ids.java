package com.felhr.deviceids;

import androidx.core.view.PointerIconCompat;
import com.hoho.android.usbserial.driver.UsbId;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public class PL2303Ids {
    private static final ConcreteDevice[] pl2303Devices = {new ConcreteDevice(1189, 16423), new ConcreteDevice(UsbId.VENDOR_PROLIFIC, UsbId.PROLIFIC_PL2303), new ConcreteDevice(UsbId.VENDOR_PROLIFIC, 1211), new ConcreteDevice(UsbId.VENDOR_PROLIFIC, 4660), new ConcreteDevice(UsbId.VENDOR_PROLIFIC, 43680), new ConcreteDevice(UsbId.VENDOR_PROLIFIC, 43682), new ConcreteDevice(UsbId.VENDOR_PROLIFIC, 1553), new ConcreteDevice(UsbId.VENDOR_PROLIFIC, 1554), new ConcreteDevice(UsbId.VENDOR_PROLIFIC, 1545), new ConcreteDevice(UsbId.VENDOR_PROLIFIC, 13082), new ConcreteDevice(UsbId.VENDOR_PROLIFIC, 775), new ConcreteDevice(UsbId.VENDOR_PROLIFIC, 1123), new ConcreteDevice(1367, 8200), new ConcreteDevice(1351, 8200), new ConcreteDevice(1211, 2563), new ConcreteDevice(1211, 2574), new ConcreteDevice(1390, 20483), new ConcreteDevice(1390, 20484), new ConcreteDevice(3770, 4224), new ConcreteDevice(3770, 8320), new ConcreteDevice(3575, 1568), new ConcreteDevice(1412, 45056), new ConcreteDevice(9336, 8200), new ConcreteDevice(5203, 16422), new ConcreteDevice(1841, 1320), new ConcreteDevice(24969, 8296), new ConcreteDevice(4599, 735), new ConcreteDevice(1256, 32769), new ConcreteDevice(4597, 1), new ConcreteDevice(4597, 3), new ConcreteDevice(4597, 4), new ConcreteDevice(4597, 5), new ConcreteDevice(1861, 1), new ConcreteDevice(1931, 4660), new ConcreteDevice(4277, 44144), new ConcreteDevice(1947, 39), new ConcreteDevice(1043, 8449), new ConcreteDevice(3669, 4363), new ConcreteDevice(1841, 8195), new ConcreteDevice(1293, 599), new ConcreteDevice(1423, 38688), new ConcreteDevice(4598, 8193), new ConcreteDevice(1962, 42), new ConcreteDevice(1453, 4026), new ConcreteDevice(21362, UsbId.PROLIFIC_PL2303), new ConcreteDevice(PointerIconCompat.TYPE_TEXT, 2873), new ConcreteDevice(PointerIconCompat.TYPE_TEXT, 12601), new ConcreteDevice(PointerIconCompat.TYPE_TEXT, 12857), new ConcreteDevice(PointerIconCompat.TYPE_TEXT, 13604), new ConcreteDevice(1208, 1313), new ConcreteDevice(1208, 1314), new ConcreteDevice(1356, 1079), new ConcreteDevice(4525, 1), new ConcreteDevice(2915, 25904), new ConcreteDevice(2956, UsbId.PROLIFIC_PL2303), new ConcreteDevice(4362, 4432), new ConcreteDevice(1367, 8200)};

    private PL2303Ids() {
    }

    public static boolean isDeviceSupported(int i, int i2) {
        int i3 = 0;
        while (true) {
            ConcreteDevice[] concreteDeviceArr = pl2303Devices;
            if (i3 > concreteDeviceArr.length - 1) {
                return false;
            }
            if (concreteDeviceArr[i3].vendorId == i && pl2303Devices[i3].productId == i2) {
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
