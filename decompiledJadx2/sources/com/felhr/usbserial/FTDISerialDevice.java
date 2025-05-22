package com.felhr.usbserial;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbRequest;
import android.util.Log;
import com.aliyun.alink.dm.api.DMErrorCode;
import com.felhr.usbserial.UsbSerialInterface;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public class FTDISerialDevice extends UsbSerialDevice {
    private static final String CLASS_ID = FTDISerialDevice.class.getSimpleName();
    public static final int FTDI_BAUDRATE_115200 = 26;
    public static final int FTDI_BAUDRATE_1200 = 2500;
    public static final int FTDI_BAUDRATE_19200 = 32924;
    public static final int FTDI_BAUDRATE_230400 = 13;
    public static final int FTDI_BAUDRATE_2400 = 1250;
    public static final int FTDI_BAUDRATE_300 = 10000;
    public static final int FTDI_BAUDRATE_38400 = 49230;
    public static final int FTDI_BAUDRATE_460800 = 16390;
    public static final int FTDI_BAUDRATE_4800 = 625;
    public static final int FTDI_BAUDRATE_57600 = 52;
    public static final int FTDI_BAUDRATE_600 = 5000;
    public static final int FTDI_BAUDRATE_921600 = 32771;
    public static final int FTDI_BAUDRATE_9600 = 16696;
    private static final int FTDI_REQTYPE_HOST2DEVICE = 64;
    private static final int FTDI_SET_DATA_DEFAULT = 8;
    private static final int FTDI_SET_FLOW_CTRL_DEFAULT = 0;
    private static final int FTDI_SET_MODEM_CTRL_DEFAULT1 = 257;
    private static final int FTDI_SET_MODEM_CTRL_DEFAULT2 = 514;
    private static final int FTDI_SET_MODEM_CTRL_DEFAULT3 = 256;
    private static final int FTDI_SET_MODEM_CTRL_DEFAULT4 = 512;
    private static final int FTDI_SIO_MODEM_CTRL = 1;
    private static final int FTDI_SIO_RESET = 0;
    private static final int FTDI_SIO_SET_BAUD_RATE = 3;
    private static final int FTDI_SIO_SET_DATA = 4;
    private static final int FTDI_SIO_SET_DTR_HIGH = 257;
    private static final int FTDI_SIO_SET_DTR_LOW = 256;
    private static final int FTDI_SIO_SET_DTR_MASK = 1;
    private static final int FTDI_SIO_SET_FLOW_CTRL = 2;
    private static final int FTDI_SIO_SET_RTS_HIGH = 514;
    private static final int FTDI_SIO_SET_RTS_LOW = 512;
    private static final int FTDI_SIO_SET_RTS_MASK = 2;
    private UsbSerialInterface.UsbBreakCallback breakCallback;
    private UsbSerialInterface.UsbCTSCallback ctsCallback;
    private boolean ctsState;
    private int currentSioSetData;
    private UsbSerialInterface.UsbDSRCallback dsrCallback;
    private boolean dsrState;
    private boolean dtrDsrEnabled;
    private boolean firstTime;
    private UsbSerialInterface.UsbFrameCallback frameCallback;
    public FTDIUtilities ftdiUtilities;
    private UsbEndpoint inEndpoint;
    private UsbInterface mInterface;
    private UsbEndpoint outEndpoint;
    private UsbSerialInterface.UsbOverrunCallback overrunCallback;
    private UsbSerialInterface.UsbParityCallback parityCallback;
    private UsbRequest requestIN;
    private boolean rtsCtsEnabled;

    public FTDISerialDevice(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection) {
        this(usbDevice, usbDeviceConnection, -1);
    }

    public FTDISerialDevice(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection, int i) {
        super(usbDevice, usbDeviceConnection);
        this.currentSioSetData = 0;
        this.ftdiUtilities = new FTDIUtilities();
        this.rtsCtsEnabled = false;
        this.dtrDsrEnabled = false;
        this.ctsState = true;
        this.dsrState = true;
        this.firstTime = true;
        this.mInterface = usbDevice.getInterface(i < 0 ? 0 : i);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public boolean open() {
        if (!openFTDI()) {
            return false;
        }
        UsbRequest usbRequest = new UsbRequest();
        this.requestIN = usbRequest;
        usbRequest.initialize(this.connection, this.inEndpoint);
        restartWorkingThread();
        restartWriteThread();
        setThreadsParams(this.requestIN, this.outEndpoint);
        this.asyncMode = true;
        return true;
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void close() {
        setControlCommand(1, 256, 0, null);
        setControlCommand(1, 512, 0, null);
        this.currentSioSetData = 0;
        killWorkingThread();
        killWriteThread();
        this.connection.releaseInterface(this.mInterface);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public boolean syncOpen() {
        if (!openFTDI()) {
            return false;
        }
        setSyncParams(this.inEndpoint, this.outEndpoint);
        this.asyncMode = false;
        return true;
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void syncClose() {
        setControlCommand(1, 256, 0, null);
        setControlCommand(1, 512, 0, null);
        this.currentSioSetData = 0;
        this.connection.releaseInterface(this.mInterface);
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x0081, code lost:
    
        if (r5 <= 921600) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0084, code lost:
    
        if (r5 > 921600) goto L55;
     */
    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void setBaudRate(int i) {
        int i2 = FTDI_BAUDRATE_921600;
        if (i >= 0 && i <= 300) {
            i2 = 10000;
        } else if (i > 300 && i <= 600) {
            i2 = 5000;
        } else if (i > 600 && i <= 1200) {
            i2 = FTDI_BAUDRATE_1200;
        } else if (i > 1200 && i <= 2400) {
            i2 = FTDI_BAUDRATE_2400;
        } else if (i <= 2400 || i > 4800) {
            if (i <= 4800 || i > 9600) {
                if (i > 9600 && i <= 19200) {
                    i2 = FTDI_BAUDRATE_19200;
                } else if (i > 19200 && i <= 38400) {
                    i2 = FTDI_BAUDRATE_38400;
                } else if (i > 19200 && i <= 57600) {
                    i2 = 52;
                } else if (i > 57600 && i <= 115200) {
                    i2 = 26;
                } else if (i > 115200 && i <= 230400) {
                    i2 = 13;
                } else if (i > 230400 && i <= 460800) {
                    i2 = FTDI_BAUDRATE_460800;
                } else if (i > 460800) {
                }
            }
            i2 = FTDI_BAUDRATE_9600;
        } else {
            i2 = FTDI_BAUDRATE_4800;
        }
        setControlCommand(3, i2, 0, null);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setDataBits(int i) {
        if (i == 5) {
            int i2 = this.currentSioSetData | 1;
            this.currentSioSetData = i2;
            int i3 = i2 & (-3);
            this.currentSioSetData = i3;
            int i4 = i3 | 4;
            this.currentSioSetData = i4;
            int i5 = i4 & (-9);
            this.currentSioSetData = i5;
            setControlCommand(4, i5, 0, null);
            return;
        }
        if (i == 6) {
            int i6 = this.currentSioSetData & (-2);
            this.currentSioSetData = i6;
            int i7 = i6 | 2;
            this.currentSioSetData = i7;
            int i8 = i7 | 4;
            this.currentSioSetData = i8;
            int i9 = i8 & (-9);
            this.currentSioSetData = i9;
            setControlCommand(4, i9, 0, null);
            return;
        }
        if (i == 7) {
            int i10 = this.currentSioSetData | 1;
            this.currentSioSetData = i10;
            int i11 = i10 | 2;
            this.currentSioSetData = i11;
            int i12 = i11 | 4;
            this.currentSioSetData = i12;
            int i13 = i12 & (-9);
            this.currentSioSetData = i13;
            setControlCommand(4, i13, 0, null);
            return;
        }
        if (i == 8) {
            int i14 = this.currentSioSetData & (-2);
            this.currentSioSetData = i14;
            int i15 = i14 & (-3);
            this.currentSioSetData = i15;
            int i16 = i15 & (-5);
            this.currentSioSetData = i16;
            int i17 = i16 | 8;
            this.currentSioSetData = i17;
            setControlCommand(4, i17, 0, null);
            return;
        }
        int i18 = this.currentSioSetData & (-2);
        this.currentSioSetData = i18;
        int i19 = i18 & (-3);
        this.currentSioSetData = i19;
        int i20 = i19 & (-5);
        this.currentSioSetData = i20;
        int i21 = i20 | 8;
        this.currentSioSetData = i21;
        setControlCommand(4, i21, 0, null);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setStopBits(int i) {
        if (i == 1) {
            int i2 = this.currentSioSetData & (-2049);
            this.currentSioSetData = i2;
            int i3 = i2 & (-4097);
            this.currentSioSetData = i3;
            int i4 = i3 & (-8193);
            this.currentSioSetData = i4;
            setControlCommand(4, i4, 0, null);
            return;
        }
        if (i == 2) {
            int i5 = this.currentSioSetData & (-2049);
            this.currentSioSetData = i5;
            int i6 = i5 | 4096;
            this.currentSioSetData = i6;
            int i7 = i6 & (-8193);
            this.currentSioSetData = i7;
            setControlCommand(4, i7, 0, null);
            return;
        }
        if (i == 3) {
            int i8 = this.currentSioSetData | 2048;
            this.currentSioSetData = i8;
            int i9 = i8 & (-4097);
            this.currentSioSetData = i9;
            int i10 = i9 & (-8193);
            this.currentSioSetData = i10;
            setControlCommand(4, i10, 0, null);
            return;
        }
        int i11 = this.currentSioSetData & (-2049);
        this.currentSioSetData = i11;
        int i12 = i11 & (-4097);
        this.currentSioSetData = i12;
        int i13 = i12 & (-8193);
        this.currentSioSetData = i13;
        setControlCommand(4, i13, 0, null);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setParity(int i) {
        if (i == 0) {
            int i2 = this.currentSioSetData & (-257);
            this.currentSioSetData = i2;
            int i3 = i2 & (-513);
            this.currentSioSetData = i3;
            int i4 = i3 & (-1025);
            this.currentSioSetData = i4;
            setControlCommand(4, i4, 0, null);
            return;
        }
        if (i == 1) {
            int i5 = this.currentSioSetData | 256;
            this.currentSioSetData = i5;
            int i6 = i5 & (-513);
            this.currentSioSetData = i6;
            int i7 = i6 & (-1025);
            this.currentSioSetData = i7;
            setControlCommand(4, i7, 0, null);
            return;
        }
        if (i == 2) {
            int i8 = this.currentSioSetData & (-257);
            this.currentSioSetData = i8;
            int i9 = i8 | 512;
            this.currentSioSetData = i9;
            int i10 = i9 & (-1025);
            this.currentSioSetData = i10;
            setControlCommand(4, i10, 0, null);
            return;
        }
        if (i == 3) {
            int i11 = this.currentSioSetData | 256;
            this.currentSioSetData = i11;
            int i12 = i11 | 512;
            this.currentSioSetData = i12;
            int i13 = i12 & (-1025);
            this.currentSioSetData = i13;
            setControlCommand(4, i13, 0, null);
            return;
        }
        if (i == 4) {
            int i14 = this.currentSioSetData & (-257);
            this.currentSioSetData = i14;
            int i15 = i14 & (-513);
            this.currentSioSetData = i15;
            int i16 = i15 | 1024;
            this.currentSioSetData = i16;
            setControlCommand(4, i16, 0, null);
            return;
        }
        int i17 = this.currentSioSetData & (-257);
        this.currentSioSetData = i17;
        int i18 = i17 & (-513);
        this.currentSioSetData = i18;
        int i19 = i18 & (-1025);
        this.currentSioSetData = i19;
        setControlCommand(4, i19, 0, null);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setFlowControl(int i) {
        if (i == 0) {
            setControlCommand(2, 0, 0, null);
            this.rtsCtsEnabled = false;
            this.dtrDsrEnabled = false;
        } else if (i == 1) {
            this.rtsCtsEnabled = true;
            this.dtrDsrEnabled = false;
            setControlCommand(2, 0, 1, null);
        } else if (i == 2) {
            this.dtrDsrEnabled = true;
            this.rtsCtsEnabled = false;
            setControlCommand(2, 0, 2, null);
        } else if (i == 3) {
            setControlCommand(2, 4881, 4, null);
        } else {
            setControlCommand(2, 0, 0, null);
        }
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void setRTS(boolean z) {
        if (z) {
            setControlCommand(1, DMErrorCode.ERROR_CMP_REGISTER_CONNECT_ERROR_EXIST, 0, null);
        } else {
            setControlCommand(1, 512, 0, null);
        }
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void setDTR(boolean z) {
        if (z) {
            setControlCommand(1, 257, 0, null);
        } else {
            setControlCommand(1, 256, 0, null);
        }
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getCTS(UsbSerialInterface.UsbCTSCallback usbCTSCallback) {
        this.ctsCallback = usbCTSCallback;
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getDSR(UsbSerialInterface.UsbDSRCallback usbDSRCallback) {
        this.dsrCallback = usbDSRCallback;
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getBreak(UsbSerialInterface.UsbBreakCallback usbBreakCallback) {
        this.breakCallback = usbBreakCallback;
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getFrame(UsbSerialInterface.UsbFrameCallback usbFrameCallback) {
        this.frameCallback = usbFrameCallback;
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getOverrun(UsbSerialInterface.UsbOverrunCallback usbOverrunCallback) {
        this.overrunCallback = usbOverrunCallback;
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getParity(UsbSerialInterface.UsbParityCallback usbParityCallback) {
        this.parityCallback = usbParityCallback;
    }

    private boolean openFTDI() {
        if (this.connection.claimInterface(this.mInterface, true)) {
            Log.i(CLASS_ID, "Interface succesfully claimed");
            int endpointCount = this.mInterface.getEndpointCount();
            for (int i = 0; i <= endpointCount - 1; i++) {
                UsbEndpoint endpoint = this.mInterface.getEndpoint(i);
                if (endpoint.getType() == 2 && endpoint.getDirection() == 128) {
                    this.inEndpoint = endpoint;
                } else {
                    this.outEndpoint = endpoint;
                }
            }
            this.firstTime = true;
            if (setControlCommand(0, 0, 0, null) < 0 || setControlCommand(4, 8, 0, null) < 0) {
                return false;
            }
            this.currentSioSetData = 8;
            if (setControlCommand(1, 257, 0, null) < 0 || setControlCommand(1, DMErrorCode.ERROR_CMP_REGISTER_CONNECT_ERROR_EXIST, 0, null) < 0 || setControlCommand(2, 0, 0, null) < 0 || setControlCommand(3, FTDI_BAUDRATE_9600, 0, null) < 0) {
                return false;
            }
            this.rtsCtsEnabled = false;
            this.dtrDsrEnabled = false;
            return true;
        }
        Log.i(CLASS_ID, "Interface could not be claimed");
        return false;
    }

    private int setControlCommand(int i, int i2, int i3, byte[] bArr) {
        int controlTransfer = this.connection.controlTransfer(64, i, i2, this.mInterface.getId() + 1 + i3, bArr, bArr != null ? bArr.length : 0, 5000);
        Log.i(CLASS_ID, "Control Transfer Response: " + String.valueOf(controlTransfer));
        return controlTransfer;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes.dex */
    public class FTDIUtilities {
        public FTDIUtilities() {
        }

        public byte[] adaptArray(byte[] bArr) {
            int length = bArr.length;
            int i = 64;
            if (length > 64) {
                int i2 = 1;
                while (i < length) {
                    i2++;
                    i = i2 * 64;
                }
                byte[] bArr2 = new byte[length - (i2 * 2)];
                copyData(bArr, bArr2);
                return bArr2;
            }
            return Arrays.copyOfRange(bArr, 2, length);
        }

        public void checkModemStatus(byte[] bArr) {
            if (bArr.length == 0) {
                return;
            }
            boolean z = (bArr[0] & 16) == 16;
            boolean z2 = (bArr[0] & 32) == 32;
            if (FTDISerialDevice.this.firstTime) {
                FTDISerialDevice.this.ctsState = z;
                FTDISerialDevice.this.dsrState = z2;
                if (FTDISerialDevice.this.rtsCtsEnabled && FTDISerialDevice.this.ctsCallback != null) {
                    FTDISerialDevice.this.ctsCallback.onCTSChanged(FTDISerialDevice.this.ctsState);
                }
                if (FTDISerialDevice.this.dtrDsrEnabled && FTDISerialDevice.this.dsrCallback != null) {
                    FTDISerialDevice.this.dsrCallback.onDSRChanged(FTDISerialDevice.this.dsrState);
                }
                FTDISerialDevice.this.firstTime = false;
                return;
            }
            if (FTDISerialDevice.this.rtsCtsEnabled && z != FTDISerialDevice.this.ctsState && FTDISerialDevice.this.ctsCallback != null) {
                FTDISerialDevice.this.ctsState = !r0.ctsState;
                FTDISerialDevice.this.ctsCallback.onCTSChanged(FTDISerialDevice.this.ctsState);
            }
            if (FTDISerialDevice.this.dtrDsrEnabled && z2 != FTDISerialDevice.this.dsrState && FTDISerialDevice.this.dsrCallback != null) {
                FTDISerialDevice.this.dsrState = !r0.dsrState;
                FTDISerialDevice.this.dsrCallback.onDSRChanged(FTDISerialDevice.this.dsrState);
            }
            if (FTDISerialDevice.this.parityCallback != null && (bArr[1] & 4) == 4) {
                FTDISerialDevice.this.parityCallback.onParityError();
            }
            if (FTDISerialDevice.this.frameCallback != null && (bArr[1] & 8) == 8) {
                FTDISerialDevice.this.frameCallback.onFramingError();
            }
            if (FTDISerialDevice.this.overrunCallback != null && (bArr[1] & 2) == 2) {
                FTDISerialDevice.this.overrunCallback.onOverrunError();
            }
            if (FTDISerialDevice.this.breakCallback == null || (bArr[1] & 16) != 16) {
                return;
            }
            FTDISerialDevice.this.breakCallback.onBreakInterrupt();
        }

        private void copyData(byte[] bArr, byte[] bArr2) {
            int i = 0;
            int i2 = 0;
            while (i <= bArr.length - 1) {
                if (i == 0 || i == 1) {
                    i++;
                } else if (i % 64 != 0 || i < 64) {
                    bArr2[i2] = bArr[i];
                    i++;
                    i2++;
                } else {
                    i += 2;
                }
            }
        }
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public int syncRead(byte[] bArr, int i) {
        int i2;
        long currentTimeMillis = System.currentTimeMillis() + i;
        if (this.asyncMode) {
            return -1;
        }
        if (bArr == null) {
            return 0;
        }
        int length = bArr.length / 62;
        if (bArr.length % 62 != 0) {
            length++;
        }
        int length2 = bArr.length + (length * 2);
        byte[] bArr2 = new byte[length2];
        int i3 = 0;
        do {
            if (i > 0) {
                i2 = (int) (currentTimeMillis - System.currentTimeMillis());
                if (i2 <= 0) {
                    break;
                }
            } else {
                i2 = 0;
            }
            int bulkTransfer = this.connection.bulkTransfer(this.inEndpoint, bArr2, length2, i2);
            if (bulkTransfer > 2) {
                System.arraycopy(this.ftdiUtilities.adaptArray(bArr2), 0, bArr, 0, bArr.length);
                int i4 = bulkTransfer / 64;
                if (bulkTransfer % 64 != 0) {
                    i4++;
                }
                i3 = bulkTransfer - (i4 * 2);
            }
        } while (i3 <= 0);
        return i3;
    }
}
