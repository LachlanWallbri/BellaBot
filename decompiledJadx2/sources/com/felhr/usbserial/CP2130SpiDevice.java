package com.felhr.usbserial;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbRequest;
import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CP2130SpiDevice extends UsbSpiDevice {
    private static final int BM_REQ_DEVICE_2_HOST = 192;
    private static final int BM_REQ_HOST_2_DEVICE = 64;
    private static String CLASS_ID = CP2130SpiDevice.class.getSimpleName();
    public static final int CLOCK_12MHz = 0;
    public static final int CLOCK_187_5KHz = 6;
    public static final int CLOCK_1_5MHz = 3;
    public static final int CLOCK_375KHz = 5;
    public static final int CLOCK_3MHz = 2;
    public static final int CLOCK_6MHz = 1;
    public static final int CLOCK_750KHz = 4;
    public static final int CLOCK_93_75KHz = 7;
    private static final int GET_SPI_WORD = 48;
    private static final int SET_GPIO_CHIP_SELECT = 37;
    private static final int SET_SPI_WORD = 49;
    private int currentChannel;
    private UsbEndpoint inEndpoint;
    private UsbInterface mInterface;
    private UsbEndpoint outEndpoint;
    private UsbRequest requestIN;

    public CP2130SpiDevice(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection) {
        this(usbDevice, usbDeviceConnection, -1);
    }

    public CP2130SpiDevice(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection, int i) {
        super(usbDevice, usbDeviceConnection);
        this.mInterface = usbDevice.getInterface(i < 0 ? 0 : i);
        this.currentChannel = 0;
    }

    @Override // com.felhr.usbserial.UsbSpiDevice, com.felhr.usbserial.UsbSpiInterface
    public boolean connectSPI() {
        if (!openCP2130()) {
            return false;
        }
        restartWorkingThread();
        restartWriteThread();
        setThreadsParams(this.inEndpoint, this.outEndpoint);
        return true;
    }

    @Override // com.felhr.usbserial.UsbSpiDevice, com.felhr.usbserial.UsbSpiInterface
    public int getSelectedSlave() {
        return this.currentChannel;
    }

    @Override // com.felhr.usbserial.UsbSpiDevice, com.felhr.usbserial.UsbSpiInterface
    public void writeMOSI(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 8];
        bArr2[0] = 0;
        bArr2[1] = 0;
        bArr2[2] = 1;
        bArr2[3] = Byte.MIN_VALUE;
        bArr2[4] = (byte) (bArr.length & 255);
        bArr2[5] = (byte) ((bArr.length >> 8) & 255);
        bArr2[6] = (byte) ((bArr.length >> 16) & 255);
        bArr2[7] = (byte) ((bArr.length >> 24) & 255);
        System.arraycopy(bArr, 0, bArr2, 8, bArr.length);
        this.serialBuffer.putWriteBuffer(bArr2);
    }

    @Override // com.felhr.usbserial.UsbSpiDevice, com.felhr.usbserial.UsbSpiInterface
    public void setClock(int i) {
        switch (i) {
            case 0:
                setSetSpiWord(this.currentChannel, 0);
                return;
            case 1:
                setSetSpiWord(this.currentChannel, 1);
                return;
            case 2:
                setSetSpiWord(this.currentChannel, 2);
                return;
            case 3:
                setSetSpiWord(this.currentChannel, 3);
                return;
            case 4:
                setSetSpiWord(this.currentChannel, 4);
                return;
            case 5:
                setSetSpiWord(this.currentChannel, 5);
                return;
            case 6:
                setSetSpiWord(this.currentChannel, 6);
                return;
            case 7:
                setSetSpiWord(this.currentChannel, 7);
                return;
            default:
                return;
        }
    }

    @Override // com.felhr.usbserial.UsbSpiDevice, com.felhr.usbserial.UsbSpiInterface
    public void readMISO(int i) {
        this.serialBuffer.putWriteBuffer(new byte[]{0, 0, 0, Byte.MIN_VALUE, (byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)});
    }

    @Override // com.felhr.usbserial.UsbSpiDevice, com.felhr.usbserial.UsbSpiInterface
    public void writeRead(byte[] bArr, int i) {
        byte[] bArr2 = new byte[bArr.length + 8];
        bArr2[0] = 0;
        bArr2[1] = 0;
        bArr2[2] = 2;
        bArr2[3] = Byte.MIN_VALUE;
        bArr2[4] = (byte) (i & 255);
        bArr2[5] = (byte) ((i >> 8) & 255);
        bArr2[6] = (byte) ((i >> 16) & 255);
        bArr2[7] = (byte) ((i >> 24) & 255);
        System.arraycopy(bArr, 0, bArr2, 8, bArr.length);
        this.serialBuffer.putWriteBuffer(bArr2);
    }

    @Override // com.felhr.usbserial.UsbSpiDevice, com.felhr.usbserial.UsbSpiInterface
    public void selectSlave(int i) {
        if (i > 10 || i < 0) {
            Log.i(CLASS_ID, "selected slave must be in 0-10 range");
        } else {
            setGpioChipSelect(i, true);
        }
    }

    @Override // com.felhr.usbserial.UsbSpiDevice, com.felhr.usbserial.UsbSpiInterface
    public int getClockDivider() {
        return getSpiWord()[this.currentChannel] & 7;
    }

    @Override // com.felhr.usbserial.UsbSpiDevice, com.felhr.usbserial.UsbSpiInterface
    public void closeSPI() {
        killWorkingThread();
        killWriteThread();
        this.connection.releaseInterface(this.mInterface);
    }

    private boolean openCP2130() {
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
            return true;
        }
        Log.i(CLASS_ID, "Interface could not be claimed");
        return false;
    }

    private void setSetSpiWord(int i, int i2) {
        byte[] bArr = new byte[2];
        if (i < 0 || i > 10) {
            Log.i(CLASS_ID, "Channel not valid");
            return;
        }
        bArr[0] = (byte) i;
        bArr[1] = (byte) i2;
        bArr[1] = (byte) (bArr[1] | 8);
        setControlCommandOut(49, 0, 0, bArr);
    }

    private void setGpioChipSelect(int i, boolean z) {
        byte[] bArr = new byte[2];
        if (i < 0 || i > 10) {
            Log.i(CLASS_ID, "Channel not valid");
            return;
        }
        bArr[0] = (byte) i;
        bArr[1] = z ? (byte) 2 : (byte) 1;
        if (setControlCommandOut(37, 0, 0, bArr) != -1) {
            this.currentChannel = i;
        }
    }

    private byte[] getSpiWord() {
        return setControlCommandIn(48, 0, 0, 2);
    }

    private int setControlCommandOut(int i, int i2, int i3, byte[] bArr) {
        int controlTransfer = this.connection.controlTransfer(64, i, i2, this.mInterface.getId(), bArr, bArr != null ? bArr.length : 0, 5000);
        Log.i(CLASS_ID, "Control Transfer Response: " + String.valueOf(controlTransfer));
        return controlTransfer;
    }

    private byte[] setControlCommandIn(int i, int i2, int i3, int i4) {
        byte[] bArr = new byte[i4];
        int controlTransfer = this.connection.controlTransfer(192, i, i2, this.mInterface.getId(), bArr, i4, 5000);
        Log.i(CLASS_ID, "Control Transfer Response: " + String.valueOf(controlTransfer));
        return bArr;
    }
}
