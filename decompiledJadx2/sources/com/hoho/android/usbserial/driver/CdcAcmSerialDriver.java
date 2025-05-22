package com.hoho.android.usbserial.driver;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.util.Log;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class CdcAcmSerialDriver implements UsbSerialDriver {
    private final UsbDevice mDevice;
    private final String TAG = CdcAcmSerialDriver.class.getSimpleName();
    private final List<UsbSerialPort> mPorts = new ArrayList();

    public CdcAcmSerialDriver(UsbDevice device) {
        this.mDevice = device;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < device.getInterfaceCount(); i3++) {
            i = device.getInterface(i3).getInterfaceClass() == 2 ? i + 1 : i;
            if (device.getInterface(i3).getInterfaceClass() == 10) {
                i2++;
            }
        }
        for (int i4 = 0; i4 < Math.min(i, i2); i4++) {
            this.mPorts.add(new CdcAcmSerialPort(this.mDevice, i4));
        }
        if (this.mPorts.size() == 0) {
            this.mPorts.add(new CdcAcmSerialPort(this.mDevice, -1));
        }
    }

    @Override // com.hoho.android.usbserial.driver.UsbSerialDriver
    public UsbDevice getDevice() {
        return this.mDevice;
    }

    @Override // com.hoho.android.usbserial.driver.UsbSerialDriver
    public List<UsbSerialPort> getPorts() {
        return this.mPorts;
    }

    /* loaded from: classes4.dex */
    public class CdcAcmSerialPort extends CommonUsbSerialPort {
        private static final int GET_LINE_CODING = 33;
        private static final int SEND_BREAK = 35;
        private static final int SET_CONTROL_LINE_STATE = 34;
        private static final int SET_LINE_CODING = 32;
        private static final int USB_RECIP_INTERFACE = 1;
        private static final int USB_RT_ACM = 33;
        private UsbEndpoint mControlEndpoint;
        private int mControlIndex;
        private UsbInterface mControlInterface;
        private UsbInterface mDataInterface;
        private boolean mDtr;
        private boolean mRts;

        public CdcAcmSerialPort(UsbDevice device, int portNumber) {
            super(device, portNumber);
            this.mRts = false;
            this.mDtr = false;
        }

        @Override // com.hoho.android.usbserial.driver.UsbSerialPort
        public UsbSerialDriver getDriver() {
            return CdcAcmSerialDriver.this;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort
        protected void openInt(UsbDeviceConnection connection) throws IOException {
            if (this.mPortNumber == -1) {
                Log.d(CdcAcmSerialDriver.this.TAG, "device might be castrated ACM device, trying single interface logic");
                openSingleInterface();
            } else {
                Log.d(CdcAcmSerialDriver.this.TAG, "trying default interface logic");
                openInterface();
            }
        }

        private void openSingleInterface() throws IOException {
            this.mControlIndex = 0;
            this.mControlInterface = this.mDevice.getInterface(0);
            this.mDataInterface = this.mDevice.getInterface(0);
            if (!this.mConnection.claimInterface(this.mControlInterface, true)) {
                throw new IOException("Could not claim shared control/data interface");
            }
            for (int i = 0; i < this.mControlInterface.getEndpointCount(); i++) {
                UsbEndpoint endpoint = this.mControlInterface.getEndpoint(i);
                if (endpoint.getDirection() == 128 && endpoint.getType() == 3) {
                    this.mControlEndpoint = endpoint;
                } else if (endpoint.getDirection() == 128 && endpoint.getType() == 2) {
                    this.mReadEndpoint = endpoint;
                } else if (endpoint.getDirection() == 0 && endpoint.getType() == 2) {
                    this.mWriteEndpoint = endpoint;
                }
            }
            if (this.mControlEndpoint == null) {
                throw new IOException("No control endpoint");
            }
        }

        private void openInterface() throws IOException {
            Log.d(CdcAcmSerialDriver.this.TAG, "claiming interfaces, count=" + this.mDevice.getInterfaceCount());
            this.mControlInterface = null;
            this.mDataInterface = null;
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < this.mDevice.getInterfaceCount(); i3++) {
                UsbInterface usbInterface = this.mDevice.getInterface(i3);
                if (usbInterface.getInterfaceClass() == 2) {
                    if (i == this.mPortNumber) {
                        this.mControlIndex = i3;
                        this.mControlInterface = usbInterface;
                    }
                    i++;
                }
                if (usbInterface.getInterfaceClass() == 10) {
                    if (i2 == this.mPortNumber) {
                        this.mDataInterface = usbInterface;
                    }
                    i2++;
                }
            }
            if (this.mControlInterface != null) {
                Log.d(CdcAcmSerialDriver.this.TAG, "Control iface=" + this.mControlInterface);
                if (!this.mConnection.claimInterface(this.mControlInterface, true)) {
                    throw new IOException("Could not claim control interface");
                }
                this.mControlEndpoint = this.mControlInterface.getEndpoint(0);
                if (this.mControlEndpoint.getDirection() != 128 || this.mControlEndpoint.getType() != 3) {
                    throw new IOException("Invalid control endpoint");
                }
                if (this.mDataInterface != null) {
                    Log.d(CdcAcmSerialDriver.this.TAG, "data iface=" + this.mDataInterface);
                    if (!this.mConnection.claimInterface(this.mDataInterface, true)) {
                        throw new IOException("Could not claim data interface");
                    }
                    for (int i4 = 0; i4 < this.mDataInterface.getEndpointCount(); i4++) {
                        UsbEndpoint endpoint = this.mDataInterface.getEndpoint(i4);
                        if (endpoint.getDirection() == 128 && endpoint.getType() == 2) {
                            this.mReadEndpoint = endpoint;
                        }
                        if (endpoint.getDirection() == 0 && endpoint.getType() == 2) {
                            this.mWriteEndpoint = endpoint;
                        }
                    }
                    return;
                }
                throw new IOException("No data interface");
            }
            throw new IOException("No control interface");
        }

        private int sendAcmControlMessage(int request, int value, byte[] buf) throws IOException {
            int controlTransfer = this.mConnection.controlTransfer(33, request, value, this.mControlIndex, buf, buf != null ? buf.length : 0, 5000);
            if (controlTransfer >= 0) {
                return controlTransfer;
            }
            throw new IOException("controlTransfer failed");
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort
        protected void closeInt() {
            try {
                this.mConnection.releaseInterface(this.mControlInterface);
                this.mConnection.releaseInterface(this.mDataInterface);
            } catch (Exception unused) {
            }
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public void setParameters(int baudRate, int dataBits, int stopBits, int parity) throws IOException {
            byte b;
            byte b2;
            if (baudRate <= 0) {
                throw new IllegalArgumentException("Invalid baud rate: " + baudRate);
            }
            if (dataBits < 5 || dataBits > 8) {
                throw new IllegalArgumentException("Invalid data bits: " + dataBits);
            }
            if (stopBits == 1) {
                b = 0;
            } else if (stopBits == 2) {
                b = 2;
            } else {
                if (stopBits != 3) {
                    throw new IllegalArgumentException("Invalid stop bits: " + stopBits);
                }
                b = 1;
            }
            if (parity == 0) {
                b2 = 0;
            } else if (parity == 1) {
                b2 = 1;
            } else if (parity == 2) {
                b2 = 2;
            } else if (parity == 3) {
                b2 = 3;
            } else {
                if (parity != 4) {
                    throw new IllegalArgumentException("Invalid parity: " + parity);
                }
                b2 = 4;
            }
            sendAcmControlMessage(32, 0, new byte[]{(byte) (baudRate & 255), (byte) ((baudRate >> 8) & 255), (byte) ((baudRate >> 16) & 255), (byte) ((baudRate >> 24) & 255), b, b2, (byte) dataBits});
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public boolean getDTR() throws IOException {
            return this.mDtr;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public void setDTR(boolean value) throws IOException {
            this.mDtr = value;
            setDtrRts();
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public boolean getRTS() throws IOException {
            return this.mRts;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public void setRTS(boolean value) throws IOException {
            this.mRts = value;
            setDtrRts();
        }

        private void setDtrRts() throws IOException {
            sendAcmControlMessage(34, (this.mRts ? 2 : 0) | (this.mDtr ? 1 : 0), null);
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public EnumSet<UsbSerialPort.ControlLine> getControlLines() throws IOException {
            EnumSet<UsbSerialPort.ControlLine> noneOf = EnumSet.noneOf(UsbSerialPort.ControlLine.class);
            if (this.mRts) {
                noneOf.add(UsbSerialPort.ControlLine.RTS);
            }
            if (this.mDtr) {
                noneOf.add(UsbSerialPort.ControlLine.DTR);
            }
            return noneOf;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public EnumSet<UsbSerialPort.ControlLine> getSupportedControlLines() throws IOException {
            return EnumSet.of(UsbSerialPort.ControlLine.RTS, UsbSerialPort.ControlLine.DTR);
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public void setBreak(boolean value) throws IOException {
            sendAcmControlMessage(35, value ? 65535 : 0, null);
        }
    }

    public static Map<Integer, int[]> getSupportedDevices() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(Integer.valueOf(UsbId.VENDOR_ARDUINO), new int[]{1, 67, 16, 66, 59, 68, 63, 68, UsbId.ARDUINO_LEONARDO, UsbId.ARDUINO_MICRO});
        linkedHashMap.put(Integer.valueOf(UsbId.VENDOR_VAN_OOIJEN_TECH), new int[]{1155});
        linkedHashMap.put(1003, new int[]{UsbId.ATMEL_LUFA_CDC_DEMO_APP});
        linkedHashMap.put(Integer.valueOf(UsbId.VENDOR_LEAFLABS), new int[]{4});
        linkedHashMap.put(Integer.valueOf(UsbId.VENDOR_ARM), new int[]{UsbId.ARM_MBED});
        linkedHashMap.put(1155, new int[]{UsbId.ST_CDC});
        return linkedHashMap;
    }
}
