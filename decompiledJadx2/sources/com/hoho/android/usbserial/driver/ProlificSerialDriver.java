package com.hoho.android.usbserial.driver;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.util.Log;
import com.hoho.android.usbserial.driver.ProlificSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.util.MonotonicClock;
import java.io.IOException;
import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class ProlificSerialDriver implements UsbSerialDriver {
    private static final int[] standardBaudRates = {75, 150, 300, 600, 1200, 1800, 2400, 3600, 4800, 7200, 9600, 14400, 19200, 28800, 38400, 57600, 115200, 128000, 134400, 161280, 201600, 230400, 268800, 403200, 460800, 614400, 806400, 921600, 1228800, 2457600, 3000000, 6000000};
    private final String TAG = ProlificSerialDriver.class.getSimpleName();
    private final UsbDevice mDevice;
    private final UsbSerialPort mPort;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public enum DeviceType {
        DEVICE_TYPE_01,
        DEVICE_TYPE_T,
        DEVICE_TYPE_HX,
        DEVICE_TYPE_HXN
    }

    public ProlificSerialDriver(UsbDevice device) {
        this.mDevice = device;
        this.mPort = new ProlificSerialPort(this.mDevice, 0);
    }

    @Override // com.hoho.android.usbserial.driver.UsbSerialDriver
    public List<UsbSerialPort> getPorts() {
        return Collections.singletonList(this.mPort);
    }

    @Override // com.hoho.android.usbserial.driver.UsbSerialDriver
    public UsbDevice getDevice() {
        return this.mDevice;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class ProlificSerialPort extends CommonUsbSerialPort {
        private static final int CONTROL_DTR = 1;
        private static final int CONTROL_RTS = 2;
        private static final int CTRL_OUT_REQTYPE = 33;
        private static final int FLUSH_RX_REQUEST = 8;
        private static final int FLUSH_TX_REQUEST = 9;
        private static final int GET_CONTROL_FLAG_CD = 2;
        private static final int GET_CONTROL_FLAG_CTS = 8;
        private static final int GET_CONTROL_FLAG_DSR = 4;
        private static final int GET_CONTROL_FLAG_RI = 1;
        private static final int GET_CONTROL_HXN_FLAG_CD = 64;
        private static final int GET_CONTROL_HXN_FLAG_CTS = 8;
        private static final int GET_CONTROL_HXN_FLAG_DSR = 32;
        private static final int GET_CONTROL_HXN_FLAG_RI = 128;
        private static final int GET_CONTROL_HXN_REQUEST = 128;
        private static final int GET_CONTROL_REQUEST = 135;
        private static final int INTERRUPT_ENDPOINT = 129;
        private static final int READ_ENDPOINT = 131;
        private static final int RESET_HXN_REQUEST = 7;
        private static final int RESET_HXN_RX_PIPE = 1;
        private static final int RESET_HXN_TX_PIPE = 2;
        private static final int SEND_BREAK_REQUEST = 35;
        private static final int SET_CONTROL_REQUEST = 34;
        private static final int SET_LINE_REQUEST = 32;
        private static final int STATUS_BUFFER_SIZE = 10;
        private static final int STATUS_BYTE_IDX = 8;
        private static final int STATUS_FLAG_CD = 1;
        private static final int STATUS_FLAG_CTS = 128;
        private static final int STATUS_FLAG_DSR = 2;
        private static final int STATUS_FLAG_RI = 8;
        private static final int STATUS_NOTIFICATION = 161;
        private static final int USB_READ_TIMEOUT_MILLIS = 1000;
        private static final int USB_RECIP_INTERFACE = 1;
        private static final int USB_WRITE_TIMEOUT_MILLIS = 5000;
        private static final int VENDOR_IN_REQTYPE = 192;
        private static final int VENDOR_OUT_REQTYPE = 64;
        private static final int VENDOR_READ_HXN_REQUEST = 129;
        private static final int VENDOR_READ_REQUEST = 1;
        private static final int VENDOR_WRITE_HXN_REQUEST = 128;
        private static final int VENDOR_WRITE_REQUEST = 1;
        private static final int WRITE_ENDPOINT = 2;
        private int mBaudRate;
        private int mControlLinesValue;
        private int mDataBits;
        protected DeviceType mDeviceType;
        private UsbEndpoint mInterruptEndpoint;
        private int mParity;
        private IOException mReadStatusException;
        private volatile Thread mReadStatusThread;
        private final Object mReadStatusThreadLock;
        private int mStatus;
        private int mStopBits;
        private boolean mStopReadStatusThread;

        public ProlificSerialPort(UsbDevice device, int portNumber) {
            super(device, portNumber);
            this.mDeviceType = DeviceType.DEVICE_TYPE_HX;
            this.mControlLinesValue = 0;
            this.mBaudRate = -1;
            this.mDataBits = -1;
            this.mStopBits = -1;
            this.mParity = -1;
            this.mStatus = 0;
            this.mReadStatusThread = null;
            this.mReadStatusThreadLock = new Object();
            this.mStopReadStatusThread = false;
            this.mReadStatusException = null;
        }

        @Override // com.hoho.android.usbserial.driver.UsbSerialPort
        public UsbSerialDriver getDriver() {
            return ProlificSerialDriver.this;
        }

        private byte[] inControlTransfer(int requestType, int request, int value, int index, int length) throws IOException {
            byte[] bArr = new byte[length];
            int controlTransfer = this.mConnection.controlTransfer(requestType, request, value, index, bArr, length, 1000);
            if (controlTransfer == length) {
                return bArr;
            }
            throw new IOException(String.format("ControlTransfer 0x%x failed: %d", Integer.valueOf(value), Integer.valueOf(controlTransfer)));
        }

        private void outControlTransfer(int requestType, int request, int value, int index, byte[] data) throws IOException {
            int length = data == null ? 0 : data.length;
            int controlTransfer = this.mConnection.controlTransfer(requestType, request, value, index, data, length, 5000);
            if (controlTransfer != length) {
                throw new IOException(String.format("ControlTransfer 0x%x failed: %d", Integer.valueOf(value), Integer.valueOf(controlTransfer)));
            }
        }

        private byte[] vendorIn(int value, int index, int length) throws IOException {
            return inControlTransfer(192, this.mDeviceType == DeviceType.DEVICE_TYPE_HXN ? 129 : 1, value, index, length);
        }

        private void vendorOut(int value, int index, byte[] data) throws IOException {
            outControlTransfer(64, this.mDeviceType == DeviceType.DEVICE_TYPE_HXN ? 128 : 1, value, index, data);
        }

        private void resetDevice() throws IOException {
            purgeHwBuffers(true, true);
        }

        private void ctrlOut(int request, int value, int index, byte[] data) throws IOException {
            outControlTransfer(33, request, value, index, data);
        }

        private boolean testHxStatus() {
            try {
                inControlTransfer(192, 1, 32896, 0, 1);
                return true;
            } catch (IOException unused) {
                return false;
            }
        }

        private void doBlackMagic() throws IOException {
            if (this.mDeviceType == DeviceType.DEVICE_TYPE_HXN) {
                return;
            }
            vendorIn(33924, 0, 1);
            vendorOut(1028, 0, null);
            vendorIn(33924, 0, 1);
            vendorIn(33667, 0, 1);
            vendorIn(33924, 0, 1);
            vendorOut(1028, 1, null);
            vendorIn(33924, 0, 1);
            vendorIn(33667, 0, 1);
            vendorOut(0, 1, null);
            vendorOut(1, 0, null);
            vendorOut(2, this.mDeviceType == DeviceType.DEVICE_TYPE_01 ? 36 : 68, null);
        }

        private void setControlLines(int newControlLinesValue) throws IOException {
            ctrlOut(34, newControlLinesValue, 0, null);
            this.mControlLinesValue = newControlLinesValue;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void readStatusThreadFunction() {
            while (!this.mStopReadStatusThread) {
                try {
                    byte[] bArr = new byte[10];
                    long millis = MonotonicClock.millis() + 500;
                    int bulkTransfer = this.mConnection.bulkTransfer(this.mInterruptEndpoint, bArr, 10, 500);
                    if (bulkTransfer == -1 && MonotonicClock.millis() < millis) {
                        testConnection();
                    }
                    if (bulkTransfer > 0) {
                        if (bulkTransfer != 10) {
                            throw new IOException("Invalid status notification, expected 10 bytes, got " + bulkTransfer);
                        }
                        if (bArr[0] != -95) {
                            throw new IOException("Invalid status notification, expected 161 request, got " + ((int) bArr[0]));
                        }
                        this.mStatus = bArr[8] & 255;
                    }
                } catch (IOException e) {
                    this.mReadStatusException = e;
                    return;
                }
            }
        }

        private int getStatus() throws IOException {
            if (this.mReadStatusThread == null && this.mReadStatusException == null) {
                synchronized (this.mReadStatusThreadLock) {
                    if (this.mReadStatusThread == null) {
                        this.mStatus = 0;
                        if (this.mDeviceType == DeviceType.DEVICE_TYPE_HXN) {
                            byte[] vendorIn = vendorIn(128, 0, 1);
                            if ((vendorIn[0] & 8) == 0) {
                                this.mStatus |= 128;
                            }
                            if ((vendorIn[0] & 32) == 0) {
                                this.mStatus |= 2;
                            }
                            if ((vendorIn[0] & 64) == 0) {
                                this.mStatus |= 1;
                            }
                            if ((vendorIn[0] & 128) == 0) {
                                this.mStatus |= 8;
                            }
                        } else {
                            byte[] vendorIn2 = vendorIn(135, 0, 1);
                            if ((vendorIn2[0] & 8) == 0) {
                                this.mStatus |= 128;
                            }
                            if ((vendorIn2[0] & 4) == 0) {
                                this.mStatus |= 2;
                            }
                            if ((vendorIn2[0] & 2) == 0) {
                                this.mStatus |= 1;
                            }
                            if ((vendorIn2[0] & 1) == 0) {
                                this.mStatus |= 8;
                            }
                        }
                        this.mReadStatusThread = new Thread(new Runnable() { // from class: com.hoho.android.usbserial.driver.-$$Lambda$ProlificSerialDriver$ProlificSerialPort$iAg6cDwXTeZRsMxPkjpy46lTnds
                            @Override // java.lang.Runnable
                            public final void run() {
                                ProlificSerialDriver.ProlificSerialPort.this.readStatusThreadFunction();
                            }
                        });
                        this.mReadStatusThread.setDaemon(true);
                        this.mReadStatusThread.start();
                    }
                }
            }
            IOException iOException = this.mReadStatusException;
            if (iOException != null) {
                this.mReadStatusException = null;
                throw new IOException(iOException);
            }
            return this.mStatus;
        }

        private boolean testStatusFlag(int flag) throws IOException {
            return (getStatus() & flag) == flag;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort
        public void openInt(UsbDeviceConnection connection) throws IOException {
            UsbInterface usbInterface = this.mDevice.getInterface(0);
            if (!connection.claimInterface(usbInterface, true)) {
                throw new IOException("Error claiming Prolific interface 0");
            }
            for (int i = 0; i < usbInterface.getEndpointCount(); i++) {
                UsbEndpoint endpoint = usbInterface.getEndpoint(i);
                int address = endpoint.getAddress();
                if (address == 2) {
                    this.mWriteEndpoint = endpoint;
                } else if (address == 129) {
                    this.mInterruptEndpoint = endpoint;
                } else if (address == 131) {
                    this.mReadEndpoint = endpoint;
                }
            }
            byte[] rawDescriptors = connection.getRawDescriptors();
            if (rawDescriptors == null || rawDescriptors.length < 14) {
                throw new IOException("Could not get device descriptors");
            }
            int i2 = (rawDescriptors[3] << 8) + rawDescriptors[2];
            int i3 = (rawDescriptors[13] << 8) + rawDescriptors[12];
            byte b = rawDescriptors[7];
            if (this.mDevice.getDeviceClass() == 2 || b != 64) {
                this.mDeviceType = DeviceType.DEVICE_TYPE_01;
            } else if (i3 == 768 && i2 == 512) {
                this.mDeviceType = DeviceType.DEVICE_TYPE_T;
            } else if (i3 == 1280) {
                this.mDeviceType = DeviceType.DEVICE_TYPE_T;
            } else if (i2 == 512 && !testHxStatus()) {
                this.mDeviceType = DeviceType.DEVICE_TYPE_HXN;
            } else {
                this.mDeviceType = DeviceType.DEVICE_TYPE_HX;
            }
            resetDevice();
            doBlackMagic();
            setControlLines(this.mControlLinesValue);
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort
        public void closeInt() {
            try {
                synchronized (this.mReadStatusThreadLock) {
                    if (this.mReadStatusThread != null) {
                        try {
                            this.mStopReadStatusThread = true;
                            this.mReadStatusThread.join();
                        } catch (Exception e) {
                            Log.w(ProlificSerialDriver.this.TAG, "An error occured while waiting for status read thread", e);
                        }
                        this.mStopReadStatusThread = false;
                        this.mReadStatusThread = null;
                        this.mReadStatusException = null;
                    }
                }
                resetDevice();
            } catch (Exception unused) {
            }
            try {
                this.mConnection.releaseInterface(this.mDevice.getInterface(0));
            } catch (Exception unused2) {
            }
        }

        private int filterBaudRate(int baudRate) {
            int i;
            int i2;
            int i3;
            if (baudRate <= 0) {
                throw new IllegalArgumentException("Invalid baud rate: " + baudRate);
            }
            if (this.mDeviceType == DeviceType.DEVICE_TYPE_HXN) {
                return baudRate;
            }
            for (int i4 : ProlificSerialDriver.standardBaudRates) {
                if (i4 == baudRate) {
                    return baudRate;
                }
            }
            int i5 = 384000000 / baudRate;
            if (i5 == 0) {
                throw new UnsupportedOperationException("Baud rate to high");
            }
            if (this.mDeviceType == DeviceType.DEVICE_TYPE_T) {
                i = 0;
                while (i5 >= 2048) {
                    if (i >= 15) {
                        throw new UnsupportedOperationException("Baud rate to low");
                    }
                    i5 >>= 1;
                    i++;
                }
                i2 = ((((i & (-2)) << 12) + i5) + ((i & 1) << 16)) - 2147483648;
                i3 = (384000000 / i5) >> i;
            } else {
                i = 0;
                while (i5 >= 512) {
                    if (i >= 7) {
                        throw new UnsupportedOperationException("Baud rate to low");
                    }
                    i5 >>= 2;
                    i++;
                }
                i2 = ((i << 9) + i5) - 2147483648;
                i3 = (384000000 / i5) >> (i << 1);
            }
            double abs = Math.abs(1.0d - (i3 / baudRate));
            if (abs >= 0.031d) {
                throw new UnsupportedOperationException(String.format("Baud rate deviation %.1f%% is higher than allowed 3%%", Double.valueOf(abs * 100.0d)));
            }
            Log.d(ProlificSerialDriver.this.TAG, String.format("baud rate=%d, effective=%d, error=%.1f%%, value=0x%08x, mantissa=%d, exponent=%d", Integer.valueOf(baudRate), Integer.valueOf(i3), Double.valueOf(abs * 100.0d), Integer.valueOf(i2), Integer.valueOf(i5), Integer.valueOf(i)));
            return i2;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public void setParameters(int baudRate, int dataBits, int stopBits, int parity) throws IOException {
            int filterBaudRate = filterBaudRate(baudRate);
            if (this.mBaudRate == filterBaudRate && this.mDataBits == dataBits && this.mStopBits == stopBits && this.mParity == parity) {
                return;
            }
            byte[] bArr = new byte[7];
            bArr[0] = (byte) (filterBaudRate & 255);
            bArr[1] = (byte) ((filterBaudRate >> 8) & 255);
            bArr[2] = (byte) ((filterBaudRate >> 16) & 255);
            bArr[3] = (byte) ((filterBaudRate >> 24) & 255);
            if (stopBits == 1) {
                bArr[4] = 0;
            } else if (stopBits == 2) {
                bArr[4] = 2;
            } else {
                if (stopBits != 3) {
                    throw new IllegalArgumentException("Invalid stop bits: " + stopBits);
                }
                bArr[4] = 1;
            }
            if (parity == 0) {
                bArr[5] = 0;
            } else if (parity == 1) {
                bArr[5] = 1;
            } else if (parity == 2) {
                bArr[5] = 2;
            } else if (parity == 3) {
                bArr[5] = 3;
            } else {
                if (parity != 4) {
                    throw new IllegalArgumentException("Invalid parity: " + parity);
                }
                bArr[5] = 4;
            }
            if (dataBits < 5 || dataBits > 8) {
                throw new IllegalArgumentException("Invalid data bits: " + dataBits);
            }
            bArr[6] = (byte) dataBits;
            ctrlOut(32, 0, 0, bArr);
            resetDevice();
            this.mBaudRate = filterBaudRate;
            this.mDataBits = dataBits;
            this.mStopBits = stopBits;
            this.mParity = parity;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public boolean getCD() throws IOException {
            return testStatusFlag(1);
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public boolean getCTS() throws IOException {
            return testStatusFlag(128);
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public boolean getDSR() throws IOException {
            return testStatusFlag(2);
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public boolean getDTR() throws IOException {
            return (this.mControlLinesValue & 1) != 0;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public void setDTR(boolean value) throws IOException {
            int i;
            if (value) {
                i = this.mControlLinesValue | 1;
            } else {
                i = this.mControlLinesValue & (-2);
            }
            setControlLines(i);
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public boolean getRI() throws IOException {
            return testStatusFlag(8);
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public boolean getRTS() throws IOException {
            return (this.mControlLinesValue & 2) != 0;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public void setRTS(boolean value) throws IOException {
            int i;
            if (value) {
                i = this.mControlLinesValue | 2;
            } else {
                i = this.mControlLinesValue & (-3);
            }
            setControlLines(i);
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public EnumSet<UsbSerialPort.ControlLine> getControlLines() throws IOException {
            int status = getStatus();
            EnumSet<UsbSerialPort.ControlLine> noneOf = EnumSet.noneOf(UsbSerialPort.ControlLine.class);
            if ((this.mControlLinesValue & 2) != 0) {
                noneOf.add(UsbSerialPort.ControlLine.RTS);
            }
            if ((status & 128) != 0) {
                noneOf.add(UsbSerialPort.ControlLine.CTS);
            }
            if ((this.mControlLinesValue & 1) != 0) {
                noneOf.add(UsbSerialPort.ControlLine.DTR);
            }
            if ((status & 2) != 0) {
                noneOf.add(UsbSerialPort.ControlLine.DSR);
            }
            if ((status & 1) != 0) {
                noneOf.add(UsbSerialPort.ControlLine.CD);
            }
            if ((status & 8) != 0) {
                noneOf.add(UsbSerialPort.ControlLine.RI);
            }
            return noneOf;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public EnumSet<UsbSerialPort.ControlLine> getSupportedControlLines() throws IOException {
            return EnumSet.allOf(UsbSerialPort.ControlLine.class);
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public void purgeHwBuffers(boolean purgeWriteBuffers, boolean purgeReadBuffers) throws IOException {
            if (this.mDeviceType != DeviceType.DEVICE_TYPE_HXN) {
                if (purgeWriteBuffers) {
                    vendorOut(8, 0, null);
                }
                if (purgeReadBuffers) {
                    vendorOut(9, 0, null);
                    return;
                }
                return;
            }
            int i = purgeWriteBuffers ? 1 : 0;
            if (purgeReadBuffers) {
                i |= 2;
            }
            if (i != 0) {
                vendorOut(7, i, null);
            }
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public void setBreak(boolean value) throws IOException {
            ctrlOut(35, value ? 65535 : 0, 0, null);
        }
    }

    public static Map<Integer, int[]> getSupportedDevices() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(Integer.valueOf(UsbId.VENDOR_PROLIFIC), new int[]{UsbId.PROLIFIC_PL2303, UsbId.PROLIFIC_PL2303GC, UsbId.PROLIFIC_PL2303GB, UsbId.PROLIFIC_PL2303GT, 9187, 9187, UsbId.PROLIFIC_PL2303GS});
        return linkedHashMap;
    }
}
