package com.felhr.deviceids;

import androidx.core.view.InputDeviceCompat;
import com.aliyun.alink.dm.api.DMErrorCode;
import com.hoho.android.usbserial.driver.UsbId;
import com.slamtec.slamware.robot.HealthInfo;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public class CP210xIds {
    private static final ConcreteDevice[] cp210xDevices = {new ConcreteDevice(1115, 83), new ConcreteDevice(1137, 1642), new ConcreteDevice(1161, 57344), new ConcreteDevice(1161, 57347), new ConcreteDevice(1861, 4096), new ConcreteDevice(2118, 4352), new ConcreteDevice(2278, 21761), new ConcreteDevice(2301, 10), new ConcreteDevice(3053, 4352), new ConcreteDevice(3053, 4353), new ConcreteDevice(4047, 4099), new ConcreteDevice(4047, HealthInfo.BaseError.BaseComponentErrorTypeMotionOdometryDown), new ConcreteDevice(4047, 4102), new ConcreteDevice(4062, 51717), new ConcreteDevice(4262, 43558), new ConcreteDevice(4267, 4293), new ConcreteDevice(4277, 44144), new ConcreteDevice(UsbId.VENDOR_SILABS, 3985), new ConcreteDevice(UsbId.VENDOR_SILABS, 4353), new ConcreteDevice(UsbId.VENDOR_SILABS, 5633), new ConcreteDevice(UsbId.VENDOR_SILABS, 32778), new ConcreteDevice(UsbId.VENDOR_SILABS, 32827), new ConcreteDevice(UsbId.VENDOR_SILABS, 32836), new ConcreteDevice(UsbId.VENDOR_SILABS, 32846), new ConcreteDevice(UsbId.VENDOR_SILABS, 32851), new ConcreteDevice(UsbId.VENDOR_SILABS, 32852), new ConcreteDevice(UsbId.VENDOR_SILABS, 32870), new ConcreteDevice(UsbId.VENDOR_SILABS, 32879), new ConcreteDevice(UsbId.VENDOR_SILABS, 32890), new ConcreteDevice(UsbId.VENDOR_SILABS, 32964), new ConcreteDevice(UsbId.VENDOR_SILABS, 32970), new ConcreteDevice(UsbId.VENDOR_SILABS, 32989), new ConcreteDevice(UsbId.VENDOR_SILABS, 33014), new ConcreteDevice(UsbId.VENDOR_SILABS, 33045), new ConcreteDevice(UsbId.VENDOR_SILABS, 33085), new ConcreteDevice(UsbId.VENDOR_SILABS, 33087), new ConcreteDevice(UsbId.VENDOR_SILABS, 33098), new ConcreteDevice(UsbId.VENDOR_SILABS, 33099), new ConcreteDevice(9221, 3), new ConcreteDevice(UsbId.VENDOR_SILABS, 33110), new ConcreteDevice(UsbId.VENDOR_SILABS, 33118), new ConcreteDevice(UsbId.VENDOR_SILABS, 33119), new ConcreteDevice(UsbId.VENDOR_SILABS, 33163), new ConcreteDevice(UsbId.VENDOR_SILABS, 33183), new ConcreteDevice(UsbId.VENDOR_SILABS, 33190), new ConcreteDevice(UsbId.VENDOR_SILABS, 33193), new ConcreteDevice(UsbId.VENDOR_SILABS, 33196), new ConcreteDevice(UsbId.VENDOR_SILABS, 33197), new ConcreteDevice(UsbId.VENDOR_SILABS, 33224), new ConcreteDevice(UsbId.VENDOR_SILABS, 33250), new ConcreteDevice(UsbId.VENDOR_SILABS, 33255), new ConcreteDevice(UsbId.VENDOR_SILABS, 33256), new ConcreteDevice(UsbId.VENDOR_SILABS, 33266), new ConcreteDevice(UsbId.VENDOR_SILABS, 33304), new ConcreteDevice(UsbId.VENDOR_SILABS, 33323), new ConcreteDevice(UsbId.VENDOR_SILABS, 33387), new ConcreteDevice(UsbId.VENDOR_SILABS, 33409), new ConcreteDevice(UsbId.VENDOR_SILABS, 33427), new ConcreteDevice(UsbId.VENDOR_SILABS, 33529), new ConcreteDevice(UsbId.VENDOR_SILABS, 33601), new ConcreteDevice(UsbId.VENDOR_SILABS, 33666), new ConcreteDevice(UsbId.VENDOR_SILABS, 33704), new ConcreteDevice(UsbId.VENDOR_SILABS, 33752), new ConcreteDevice(UsbId.VENDOR_SILABS, 33809), new ConcreteDevice(UsbId.VENDOR_SILABS, 33816), new ConcreteDevice(UsbId.VENDOR_SILABS, 33902), new ConcreteDevice(UsbId.VENDOR_SILABS, 33911), new ConcreteDevice(UsbId.VENDOR_SILABS, 34282), new ConcreteDevice(UsbId.VENDOR_SILABS, 34283), new ConcreteDevice(UsbId.VENDOR_SILABS, 34296), new ConcreteDevice(UsbId.VENDOR_SILABS, 34404), new ConcreteDevice(UsbId.VENDOR_SILABS, 34405), new ConcreteDevice(UsbId.VENDOR_SILABS, 34980), new ConcreteDevice(UsbId.VENDOR_SILABS, 34981), new ConcreteDevice(UsbId.VENDOR_SILABS, 60000), new ConcreteDevice(UsbId.VENDOR_SILABS, 60001), new ConcreteDevice(UsbId.VENDOR_SILABS, UsbId.SILABS_CP2105), new ConcreteDevice(UsbId.VENDOR_SILABS, 60032), new ConcreteDevice(UsbId.VENDOR_SILABS, UsbId.SILABS_CP2108), new ConcreteDevice(UsbId.VENDOR_SILABS, 61441), new ConcreteDevice(UsbId.VENDOR_SILABS, 61442), new ConcreteDevice(UsbId.VENDOR_SILABS, 61443), new ConcreteDevice(UsbId.VENDOR_SILABS, 61444), new ConcreteDevice(4293, 60001), new ConcreteDevice(4302, 60010), new ConcreteDevice(5037, 39321), new ConcreteDevice(5461, 4), new ConcreteDevice(5738, InputDeviceCompat.SOURCE_DPAD), new ConcreteDevice(5738, 769), new ConcreteDevice(5738, 771), new ConcreteDevice(5738, 772), new ConcreteDevice(5738, 773), new ConcreteDevice(5738, InputDeviceCompat.SOURCE_GAMEPAD), new ConcreteDevice(5738, 257), new ConcreteDevice(5846, 1), new ConcreteDevice(5852, 16), new ConcreteDevice(5852, 17), new ConcreteDevice(5852, 18), new ConcreteDevice(5852, 21), new ConcreteDevice(6056, 1), new ConcreteDevice(6056, 5), new ConcreteDevice(6132, 43690), new ConcreteDevice(6211, 512), new ConcreteDevice(6383, 57359), new ConcreteDevice(6875, 1), new ConcreteDevice(7139, 1958), new ConcreteDevice(7721, 258), new ConcreteDevice(7721, 1281), new ConcreteDevice(8121, 256), new ConcreteDevice(8121, 512), new ConcreteDevice(8121, InputDeviceCompat.SOURCE_DPAD), new ConcreteDevice(8121, DMErrorCode.ERROR_CMP_REGISTER_CONNECT_ERROR_EXIST), new ConcreteDevice(8121, 515), new ConcreteDevice(8121, 768), new ConcreteDevice(8121, 769), new ConcreteDevice(8121, 770), new ConcreteDevice(8121, 771), new ConcreteDevice(8121, 1024), new ConcreteDevice(8121, InputDeviceCompat.SOURCE_GAMEPAD), new ConcreteDevice(8121, 1026), new ConcreteDevice(8121, UsbId.VENDOR_FTDI), new ConcreteDevice(8121, 1028), new ConcreteDevice(8121, 1536), new ConcreteDevice(8121, 1537), new ConcreteDevice(8121, 1538), new ConcreteDevice(8121, 1792), new ConcreteDevice(8121, 1793), new ConcreteDevice(12693, 61840), new ConcreteDevice(12693, 62080), new ConcreteDevice(12693, 62081), new ConcreteDevice(16700, 38144)};

    public static boolean isDeviceSupported(int i, int i2) {
        int i3 = 0;
        while (true) {
            ConcreteDevice[] concreteDeviceArr = cp210xDevices;
            if (i3 > concreteDeviceArr.length - 1) {
                return false;
            }
            if (concreteDeviceArr[i3].vendorId == i && cp210xDevices[i3].productId == i2) {
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
