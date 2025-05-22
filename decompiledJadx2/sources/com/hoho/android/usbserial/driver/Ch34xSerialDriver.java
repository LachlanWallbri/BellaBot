package com.hoho.android.usbserial.driver;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.support.v4.media.session.PlaybackStateCompat;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import java.io.IOException;
import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class Ch34xSerialDriver implements UsbSerialDriver {
    private static final int GCL_CD = 8;
    private static final int GCL_CTS = 1;
    private static final int GCL_DSR = 2;
    private static final int GCL_RI = 4;
    private static final int LCR_CS5 = 0;
    private static final int LCR_CS6 = 1;
    private static final int LCR_CS7 = 2;
    private static final int LCR_CS8 = 3;
    private static final int LCR_ENABLE_PAR = 8;
    private static final int LCR_ENABLE_RX = 128;
    private static final int LCR_ENABLE_TX = 64;
    private static final int LCR_MARK_SPACE = 32;
    private static final int LCR_PAR_EVEN = 16;
    private static final int LCR_STOP_BITS_2 = 4;
    private static final int SCL_DTR = 32;
    private static final int SCL_RTS = 64;
    private static final String TAG = Ch34xSerialDriver.class.getSimpleName();
    private final UsbDevice mDevice;
    private final UsbSerialPort mPort;

    public Ch34xSerialDriver(UsbDevice device) {
        this.mDevice = device;
        this.mPort = new Ch340SerialPort(this.mDevice, 0);
    }

    @Override // com.hoho.android.usbserial.driver.UsbSerialDriver
    public UsbDevice getDevice() {
        return this.mDevice;
    }

    @Override // com.hoho.android.usbserial.driver.UsbSerialDriver
    public List<UsbSerialPort> getPorts() {
        return Collections.singletonList(this.mPort);
    }

    /* loaded from: classes4.dex */
    public class Ch340SerialPort extends CommonUsbSerialPort {
        private static final int USB_TIMEOUT_MILLIS = 5000;
        private final int DEFAULT_BAUD_RATE;
        private boolean dtr;
        private boolean rts;

        public Ch340SerialPort(UsbDevice device, int portNumber) {
            super(device, portNumber);
            this.DEFAULT_BAUD_RATE = 9600;
            this.dtr = false;
            this.rts = false;
        }

        @Override // com.hoho.android.usbserial.driver.UsbSerialPort
        public UsbSerialDriver getDriver() {
            return Ch34xSerialDriver.this;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort
        protected void openInt(UsbDeviceConnection connection) throws IOException {
            for (int i = 0; i < this.mDevice.getInterfaceCount(); i++) {
                if (!this.mConnection.claimInterface(this.mDevice.getInterface(i), true)) {
                    throw new IOException("Could not claim data interface");
                }
            }
            UsbInterface usbInterface = this.mDevice.getInterface(this.mDevice.getInterfaceCount() - 1);
            for (int i2 = 0; i2 < usbInterface.getEndpointCount(); i2++) {
                UsbEndpoint endpoint = usbInterface.getEndpoint(i2);
                if (endpoint.getType() == 2) {
                    if (endpoint.getDirection() == 128) {
                        this.mReadEndpoint = endpoint;
                    } else {
                        this.mWriteEndpoint = endpoint;
                    }
                }
            }
            initialize();
            setBaudRate(9600);
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort
        protected void closeInt() {
            for (int i = 0; i < this.mDevice.getInterfaceCount(); i++) {
                try {
                    this.mConnection.releaseInterface(this.mDevice.getInterface(i));
                } catch (Exception unused) {
                    return;
                }
            }
        }

        private int controlOut(int request, int value, int index) {
            return this.mConnection.controlTransfer(64, request, value, index, null, 0, 5000);
        }

        private int controlIn(int request, int value, int index, byte[] buffer) {
            return this.mConnection.controlTransfer(192, request, value, index, buffer, buffer.length, 5000);
        }

        private void checkState(String msg, int request, int value, int[] expected) throws IOException {
            int i;
            byte[] bArr = new byte[expected.length];
            int controlIn = controlIn(request, value, 0, bArr);
            if (controlIn < 0) {
                throw new IOException("Failed send cmd [" + msg + "]");
            }
            if (controlIn != expected.length) {
                throw new IOException("Expected " + expected.length + " bytes, but get " + controlIn + " [" + msg + "]");
            }
            for (int i2 = 0; i2 < expected.length; i2++) {
                if (expected[i2] != -1 && expected[i2] != (i = bArr[i2] & 255)) {
                    throw new IOException("Expected 0x" + Integer.toHexString(expected[i2]) + " byte, but get 0x" + Integer.toHexString(i) + " [" + msg + "]");
                }
            }
        }

        private void setControlLines() throws IOException {
            if (controlOut(164, ~((this.dtr ? 32 : 0) | (this.rts ? 64 : 0)), 0) < 0) {
                throw new IOException("Failed to set control lines");
            }
        }

        private byte getStatus() throws IOException {
            byte[] bArr = new byte[2];
            if (controlIn(149, 1798, 0, bArr) < 0) {
                throw new IOException("Error getting control lines");
            }
            return bArr[0];
        }

        private void initialize() throws IOException {
            checkState("init #1", 95, 0, new int[]{-1, 0});
            if (controlOut(161, 0, 0) < 0) {
                throw new IOException("Init failed: #2");
            }
            setBaudRate(9600);
            checkState("init #4", 149, 9496, new int[]{-1, 0});
            if (controlOut(154, 9496, 195) < 0) {
                throw new IOException("Init failed: #5");
            }
            checkState("init #6", 149, 1798, new int[]{-1, -1});
            if (controlOut(161, 20511, 55562) < 0) {
                throw new IOException("Init failed: #7");
            }
            setBaudRate(9600);
            setControlLines();
            checkState("init #10", 149, 1798, new int[]{-1, -1});
        }

        private void setBaudRate(int baudRate) throws IOException {
            long j = 1532620800 / baudRate;
            int i = 3;
            while (j > 65520 && i > 0) {
                j >>= 3;
                i--;
            }
            if (j > 65520) {
                throw new UnsupportedOperationException("Unsupported baud rate: " + baudRate);
            }
            long j2 = PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH - j;
            if (controlOut(154, 4882, (int) ((65280 & j2) | i | 128)) < 0) {
                throw new IOException("Error setting baud rate: #1)");
            }
            if (controlOut(154, 3884, (int) (255 & j2)) < 0) {
                throw new IOException("Error setting baud rate: #2");
            }
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public void setParameters(int baudRate, int dataBits, int stopBits, int parity) throws IOException {
            int i;
            if (baudRate <= 0) {
                throw new IllegalArgumentException("Invalid baud rate: " + baudRate);
            }
            setBaudRate(baudRate);
            if (dataBits == 5) {
                i = 192;
            } else if (dataBits == 6) {
                i = 193;
            } else if (dataBits == 7) {
                i = 194;
            } else {
                if (dataBits != 8) {
                    throw new IllegalArgumentException("Invalid data bits: " + dataBits);
                }
                i = 195;
            }
            if (parity != 0) {
                if (parity == 1) {
                    i |= 8;
                } else if (parity == 2) {
                    i |= 24;
                } else if (parity == 3) {
                    i |= 40;
                } else {
                    if (parity != 4) {
                        throw new IllegalArgumentException("Invalid parity: " + parity);
                    }
                    i |= 56;
                }
            }
            if (stopBits != 1) {
                if (stopBits != 2) {
                    if (stopBits == 3) {
                        throw new UnsupportedOperationException("Unsupported stop bits: 1.5");
                    }
                    throw new IllegalArgumentException("Invalid stop bits: " + stopBits);
                }
                i |= 4;
            }
            if (controlOut(154, 9496, i) < 0) {
                throw new IOException("Error setting control byte");
            }
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public boolean getCD() throws IOException {
            return (getStatus() & 8) == 0;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public boolean getCTS() throws IOException {
            return (getStatus() & 1) == 0;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public boolean getDSR() throws IOException {
            return (getStatus() & 2) == 0;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public boolean getDTR() throws IOException {
            return this.dtr;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public void setDTR(boolean value) throws IOException {
            this.dtr = value;
            setControlLines();
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public boolean getRI() throws IOException {
            return (getStatus() & 4) == 0;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public boolean getRTS() throws IOException {
            return this.rts;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public void setRTS(boolean value) throws IOException {
            this.rts = value;
            setControlLines();
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public EnumSet<UsbSerialPort.ControlLine> getControlLines() throws IOException {
            byte status = getStatus();
            EnumSet<UsbSerialPort.ControlLine> noneOf = EnumSet.noneOf(UsbSerialPort.ControlLine.class);
            if (this.rts) {
                noneOf.add(UsbSerialPort.ControlLine.RTS);
            }
            if ((status & 1) == 0) {
                noneOf.add(UsbSerialPort.ControlLine.CTS);
            }
            if (this.dtr) {
                noneOf.add(UsbSerialPort.ControlLine.DTR);
            }
            if ((status & 2) == 0) {
                noneOf.add(UsbSerialPort.ControlLine.DSR);
            }
            if ((status & 8) == 0) {
                noneOf.add(UsbSerialPort.ControlLine.CD);
            }
            if ((status & 4) == 0) {
                noneOf.add(UsbSerialPort.ControlLine.RI);
            }
            return noneOf;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public EnumSet<UsbSerialPort.ControlLine> getSupportedControlLines() throws IOException {
            return EnumSet.allOf(UsbSerialPort.ControlLine.class);
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public void setBreak(boolean value) throws IOException {
            byte[] bArr = new byte[2];
            if (controlIn(149, 6149, 0, bArr) < 0) {
                throw new IOException("Error getting BREAK condition");
            }
            if (value) {
                bArr[0] = (byte) (bArr[0] & (-2));
                bArr[1] = (byte) (bArr[1] & ByteSourceJsonBootstrapper.UTF8_BOM_3);
            } else {
                bArr[0] = (byte) (bArr[0] | 1);
                bArr[1] = (byte) (bArr[1] | 64);
            }
            if (controlOut(154, 6149, ((bArr[1] & 255) << 8) | (bArr[0] & 255)) < 0) {
                throw new IOException("Error setting BREAK condition");
            }
        }
    }

    public static Map<Integer, int[]> getSupportedDevices() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(Integer.valueOf(UsbId.VENDOR_QINHENG), new int[]{UsbId.QINHENG_CH340, UsbId.QINHENG_CH341A});
        return linkedHashMap;
    }
}
