package com.felhr.usbserial;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbRequest;
import android.util.Log;
import com.felhr.usbserial.UsbSerialInterface;

/* JADX WARN: Classes with same name are omitted:
  
 */
@Deprecated
/* loaded from: classes.dex */
public class BLED112SerialDevice extends UsbSerialDevice {
    private static final int BLED112_DEFAULT_CONTROL_LINE = 3;
    private static final int BLED112_DISCONNECT_CONTROL_LINE = 2;
    private static final int BLED112_GET_LINE_CODING = 33;
    private static final int BLED112_REQTYPE_DEVICE2HOST = 161;
    private static final int BLED112_REQTYPE_HOST2DEVICE = 33;
    private static final int BLED112_SET_CONTROL_LINE_STATE = 34;
    private static final int BLED112_SET_LINE_CODING = 32;
    private UsbEndpoint inEndpoint;
    private UsbInterface mInterface;
    private UsbEndpoint outEndpoint;
    private UsbRequest requestIN;
    private static final String CLASS_ID = BLED112SerialDevice.class.getSimpleName();
    private static final byte[] BLED112_DEFAULT_LINE_CODING = {0, 1, -62, 0, 0, 0, 8};

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

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setFlowControl(int i) {
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

    @Deprecated
    public BLED112SerialDevice(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection) {
        super(usbDevice, usbDeviceConnection);
        this.mInterface = usbDevice.getInterface(1);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public boolean open() {
        restartWorkingThread();
        restartWriteThread();
        if (this.connection.claimInterface(this.mInterface, true)) {
            Log.i(CLASS_ID, "Interface succesfully claimed");
        } else {
            Log.i(CLASS_ID, "Interface could not be claimed");
        }
        int endpointCount = this.mInterface.getEndpointCount();
        for (int i = 0; i <= endpointCount - 1; i++) {
            UsbEndpoint endpoint = this.mInterface.getEndpoint(i);
            if (endpoint.getType() == 2 && endpoint.getDirection() == 128) {
                this.inEndpoint = endpoint;
            } else {
                this.outEndpoint = endpoint;
            }
        }
        setControlCommand(32, 0, BLED112_DEFAULT_LINE_CODING);
        setControlCommand(34, 3, null);
        UsbRequest usbRequest = new UsbRequest();
        this.requestIN = usbRequest;
        usbRequest.initialize(this.connection, this.inEndpoint);
        setThreadsParams(this.requestIN, this.outEndpoint);
        return true;
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void close() {
        setControlCommand(34, 2, null);
        killWorkingThread();
        killWriteThread();
        this.connection.releaseInterface(this.mInterface);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setBaudRate(int i) {
        byte[] lineCoding = getLineCoding();
        lineCoding[3] = (byte) (i & 255);
        lineCoding[2] = (byte) ((i >> 8) & 255);
        lineCoding[1] = (byte) ((i >> 16) & 255);
        lineCoding[0] = (byte) ((i >> 24) & 255);
        setControlCommand(32, 0, lineCoding);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setDataBits(int i) {
        byte[] lineCoding = getLineCoding();
        if (i == 5) {
            lineCoding[6] = 5;
        } else if (i == 6) {
            lineCoding[6] = 6;
        } else if (i == 7) {
            lineCoding[6] = 7;
        } else if (i == 8) {
            lineCoding[6] = 8;
        }
        setControlCommand(32, 0, lineCoding);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setStopBits(int i) {
        byte[] lineCoding = getLineCoding();
        if (i == 1) {
            lineCoding[4] = 0;
        } else if (i == 2) {
            lineCoding[4] = 2;
        } else if (i == 3) {
            lineCoding[4] = 1;
        }
        setControlCommand(32, 0, lineCoding);
    }

    @Override // com.felhr.usbserial.UsbSerialDevice, com.felhr.usbserial.UsbSerialInterface
    public void setParity(int i) {
        byte[] lineCoding = getLineCoding();
        if (i == 0) {
            lineCoding[5] = 0;
        } else if (i == 1) {
            lineCoding[5] = 1;
        } else if (i == 2) {
            lineCoding[5] = 2;
        } else if (i == 3) {
            lineCoding[5] = 3;
        } else if (i == 4) {
            lineCoding[5] = 4;
        }
        setControlCommand(32, 0, lineCoding);
    }

    private int setControlCommand(int i, int i2, byte[] bArr) {
        int controlTransfer = this.connection.controlTransfer(33, i, i2, 0, bArr, bArr != null ? bArr.length : 0, 5000);
        Log.i(CLASS_ID, "Control Transfer Response: " + String.valueOf(controlTransfer));
        return controlTransfer;
    }

    private byte[] getLineCoding() {
        byte[] bArr = new byte[7];
        int controlTransfer = this.connection.controlTransfer(161, 33, 0, 0, bArr, 7, 5000);
        Log.i(CLASS_ID, "Control Transfer Response: " + String.valueOf(controlTransfer));
        return bArr;
    }
}
