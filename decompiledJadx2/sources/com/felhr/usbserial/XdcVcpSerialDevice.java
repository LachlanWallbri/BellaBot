package com.felhr.usbserial;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbRequest;
import android.util.Log;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.felhr.usbserial.UsbSerialInterface;

/* JADX WARN: Classes with same name are omitted:
  
 */
@Deprecated
/* loaded from: classes.dex */
public class XdcVcpSerialDevice extends UsbSerialDevice {
    private static final String CLASS_ID = XdcVcpSerialDevice.class.getSimpleName();
    private static final int DEFAULT_BAUDRATE = 115200;
    private static final int XDCVCP_GET_LINE_CTL = 4;
    private static final int XDCVCP_IFC_ENABLE = 0;
    private static final int XDCVCP_LINE_CTL_DEFAULT = 2048;
    private static final int XDCVCP_MHS_ALL = 17;
    private static final int XDCVCP_MHS_DEFAULT = 0;
    private static final int XDCVCP_MHS_DTR = 1;
    private static final int XDCVCP_MHS_RTS = 16;
    private static final int XDCVCP_REQTYPE_DEVICE2HOST = 193;
    private static final int XDCVCP_REQTYPE_HOST2DEVICE = 65;
    private static final int XDCVCP_SET_BAUDDIV = 1;
    private static final int XDCVCP_SET_BAUDRATE = 30;
    private static final int XDCVCP_SET_CHARS = 25;
    private static final int XDCVCP_SET_FLOW = 19;
    private static final int XDCVCP_SET_LINE_CTL = 3;
    private static final int XDCVCP_SET_MHS = 7;
    private static final int XDCVCP_SET_XOFF = 10;
    private static final int XDCVCP_SET_XON = 9;
    private static final int XDCVCP_UART_DISABLE = 0;
    private static final int XDCVCP_UART_ENABLE = 1;
    private static final int XDCVCP_XOFF = 0;
    private static final int XDCVCP_XON = 0;
    private UsbEndpoint inEndpoint;
    private UsbInterface mInterface;
    private UsbEndpoint outEndpoint;
    private UsbRequest requestIN;

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getBreak(UsbSerialInterface.UsbBreakCallback usbBreakCallback) {
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getCTS(UsbSerialInterface.UsbCTSCallback usbCTSCallback) {
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getDSR(UsbSerialInterface.UsbDSRCallback usbDSRCallback) {
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getFrame(UsbSerialInterface.UsbFrameCallback usbFrameCallback) {
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getOverrun(UsbSerialInterface.UsbOverrunCallback usbOverrunCallback) {
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void getParity(UsbSerialInterface.UsbParityCallback usbParityCallback) {
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void setDTR(boolean z) {
    }

    @Override // com.felhr.usbserial.UsbSerialInterface
    public void setRTS(boolean z) {
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void syncClose() {
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public boolean syncOpen() {
        return false;
    }

    public XdcVcpSerialDevice(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection) {
        this(usbDevice, usbDeviceConnection, -1);
    }

    public XdcVcpSerialDevice(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection, int i) {
        super(usbDevice, usbDeviceConnection);
        this.mInterface = usbDevice.getInterface(i < 0 ? 0 : i);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public boolean open() {
        restartWorkingThread();
        restartWriteThread();
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
            if (setControlCommand(0, 1, null) < 0) {
                return false;
            }
            setBaudRate(DEFAULT_BAUDRATE);
            if (setControlCommand(3, 2048, null) < 0) {
                return false;
            }
            setFlowControl(0);
            if (setControlCommand(7, 0, null) < 0) {
                return false;
            }
            UsbRequest usbRequest = new UsbRequest();
            this.requestIN = usbRequest;
            usbRequest.initialize(this.connection, this.inEndpoint);
            setThreadsParams(this.requestIN, this.outEndpoint);
            return true;
        }
        Log.i(CLASS_ID, "Interface could not be claimed");
        return false;
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void close() {
        setControlCommand(0, 0, null);
        killWorkingThread();
        killWriteThread();
        this.connection.releaseInterface(this.mInterface);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setBaudRate(int i) {
        setControlCommand(30, 0, new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)});
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setDataBits(int i) {
        byte[] ctl = getCTL();
        if (i == 5) {
            ctl[1] = 5;
        } else if (i == 6) {
            ctl[1] = 6;
        } else if (i == 7) {
            ctl[1] = 7;
        } else if (i != 8) {
            return;
        } else {
            ctl[1] = 8;
        }
        setControlCommand(3, (byte) ((ctl[1] << 8) | (ctl[0] & 255)), null);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setStopBits(int i) {
        byte[] ctl = getCTL();
        if (i == 1) {
            ctl[0] = (byte) (ctl[0] & (-2));
            ctl[0] = (byte) (ctl[0] & (-3));
        } else if (i == 2) {
            ctl[0] = (byte) (ctl[0] & (-2));
            ctl[0] = (byte) (ctl[0] | 2);
        } else {
            if (i != 3) {
                return;
            }
            ctl[0] = (byte) (ctl[0] | 1);
            ctl[0] = (byte) (ctl[0] & (-3));
        }
        setControlCommand(3, (byte) ((ctl[1] << 8) | (ctl[0] & 255)), null);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setParity(int i) {
        byte[] ctl = getCTL();
        if (i == 0) {
            ctl[0] = (byte) (ctl[0] & ByteSourceJsonBootstrapper.UTF8_BOM_1);
            ctl[0] = (byte) (ctl[0] & (-33));
            ctl[0] = (byte) (ctl[0] & ByteSourceJsonBootstrapper.UTF8_BOM_3);
            ctl[0] = (byte) (ctl[0] & (-129));
        } else if (i == 1) {
            ctl[0] = (byte) (ctl[0] | 16);
            ctl[0] = (byte) (ctl[0] & (-33));
            ctl[0] = (byte) (ctl[0] & ByteSourceJsonBootstrapper.UTF8_BOM_3);
            ctl[0] = (byte) (ctl[0] & (-129));
        } else if (i == 2) {
            ctl[0] = (byte) (ctl[0] & ByteSourceJsonBootstrapper.UTF8_BOM_1);
            ctl[0] = (byte) (ctl[0] | 32);
            ctl[0] = (byte) (ctl[0] & ByteSourceJsonBootstrapper.UTF8_BOM_3);
            ctl[0] = (byte) (ctl[0] & (-129));
        } else if (i == 3) {
            ctl[0] = (byte) (ctl[0] | 16);
            ctl[0] = (byte) (ctl[0] | 32);
            ctl[0] = (byte) (ctl[0] & ByteSourceJsonBootstrapper.UTF8_BOM_3);
            ctl[0] = (byte) (ctl[0] & (-129));
        } else {
            if (i != 4) {
                return;
            }
            ctl[0] = (byte) (ctl[0] & ByteSourceJsonBootstrapper.UTF8_BOM_1);
            ctl[0] = (byte) (ctl[0] & (-33));
            ctl[0] = (byte) (ctl[0] | 64);
            ctl[0] = (byte) (ctl[0] & (-129));
        }
        setControlCommand(3, (byte) ((ctl[1] << 8) | (ctl[0] & 255)), null);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setFlowControl(int i) {
        if (i == 0) {
            setControlCommand(19, 0, new byte[]{1, 0, 0, 0, 64, 0, 0, 0, 0, Byte.MIN_VALUE, 0, 0, 0, 32, 0, 0});
            return;
        }
        if (i == 1) {
            setControlCommand(19, 0, new byte[]{9, 0, 0, 0, Byte.MIN_VALUE, 0, 0, 0, 0, Byte.MIN_VALUE, 0, 0, 0, 32, 0, 0});
            return;
        }
        if (i == 2) {
            setControlCommand(19, 0, new byte[]{18, 0, 0, 0, 64, 0, 0, 0, 0, Byte.MIN_VALUE, 0, 0, 0, 32, 0, 0});
        } else {
            if (i != 3) {
                return;
            }
            setControlCommand(25, 0, new byte[]{0, 0, 0, 0, 17, 19});
            setControlCommand(19, 0, new byte[]{1, 0, 0, 0, 67, 0, 0, 0, 0, Byte.MIN_VALUE, 0, 0, 0, 32, 0, 0});
        }
    }

    private int setControlCommand(int i, int i2, byte[] bArr) {
        int controlTransfer = this.connection.controlTransfer(65, i, i2, this.mInterface.getId(), bArr, bArr != null ? bArr.length : 0, 5000);
        Log.i(CLASS_ID, "Control Transfer Response: " + String.valueOf(controlTransfer));
        return controlTransfer;
    }

    private byte[] getCTL() {
        byte[] bArr = new byte[2];
        int controlTransfer = this.connection.controlTransfer(193, 4, 0, this.mInterface.getId(), bArr, 2, 5000);
        Log.i(CLASS_ID, "Control Transfer Response: " + String.valueOf(controlTransfer));
        return bArr;
    }
}
