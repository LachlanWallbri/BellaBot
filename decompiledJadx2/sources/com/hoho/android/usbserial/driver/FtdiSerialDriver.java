package com.hoho.android.usbserial.driver;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.util.Log;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.util.MonotonicClock;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.archivers.cpio.CpioConstants;

/* loaded from: classes4.dex */
public class FtdiSerialDriver implements UsbSerialDriver {
    private static final String TAG = FtdiSerialPort.class.getSimpleName();
    private final UsbDevice mDevice;
    private final List<UsbSerialPort> mPorts = new ArrayList();

    public FtdiSerialDriver(UsbDevice device) {
        this.mDevice = device;
        for (int i = 0; i < device.getInterfaceCount(); i++) {
            this.mPorts.add(new FtdiSerialPort(this.mDevice, i));
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
    public class FtdiSerialPort extends CommonUsbSerialPort {
        private static final int GET_LATENCY_TIMER_REQUEST = 10;
        private static final int GET_MODEM_STATUS_REQUEST = 5;
        private static final int MODEM_CONTROL_DTR_DISABLE = 256;
        private static final int MODEM_CONTROL_DTR_ENABLE = 257;
        private static final int MODEM_CONTROL_REQUEST = 1;
        private static final int MODEM_CONTROL_RTS_DISABLE = 512;
        private static final int MODEM_CONTROL_RTS_ENABLE = 514;
        private static final int MODEM_STATUS_CD = 128;
        private static final int MODEM_STATUS_CTS = 16;
        private static final int MODEM_STATUS_DSR = 32;
        private static final int MODEM_STATUS_RI = 64;
        private static final int READ_HEADER_LENGTH = 2;
        private static final int REQTYPE_DEVICE_TO_HOST = 192;
        private static final int REQTYPE_HOST_TO_DEVICE = 64;
        private static final int RESET_ALL = 0;
        private static final int RESET_PURGE_RX = 1;
        private static final int RESET_PURGE_TX = 2;
        private static final int RESET_REQUEST = 0;
        private static final int SET_BAUD_RATE_REQUEST = 3;
        private static final int SET_DATA_REQUEST = 4;
        private static final int SET_LATENCY_TIMER_REQUEST = 9;
        private static final int USB_WRITE_TIMEOUT_MILLIS = 5000;
        private boolean baudRateWithPort;
        private int breakConfig;
        private boolean dtr;
        private boolean rts;

        public FtdiSerialPort(UsbDevice device, int portNumber) {
            super(device, portNumber);
            this.baudRateWithPort = false;
            this.dtr = false;
            this.rts = false;
            this.breakConfig = 0;
        }

        @Override // com.hoho.android.usbserial.driver.UsbSerialPort
        public UsbSerialDriver getDriver() {
            return FtdiSerialDriver.this;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort
        protected void openInt(UsbDeviceConnection connection) throws IOException {
            boolean z = true;
            if (!connection.claimInterface(this.mDevice.getInterface(this.mPortNumber), true)) {
                throw new IOException("Could not claim interface " + this.mPortNumber);
            }
            if (this.mDevice.getInterface(this.mPortNumber).getEndpointCount() < 2) {
                throw new IOException("Not enough endpoints");
            }
            this.mReadEndpoint = this.mDevice.getInterface(this.mPortNumber).getEndpoint(0);
            this.mWriteEndpoint = this.mDevice.getInterface(this.mPortNumber).getEndpoint(1);
            int controlTransfer = this.mConnection.controlTransfer(64, 0, 0, this.mPortNumber + 1, null, 0, 5000);
            if (controlTransfer != 0) {
                throw new IOException("Reset failed: result=" + controlTransfer);
            }
            int controlTransfer2 = this.mConnection.controlTransfer(64, 1, (this.rts ? 514 : 512) | (this.dtr ? 257 : 256), this.mPortNumber + 1, null, 0, 5000);
            if (controlTransfer2 != 0) {
                throw new IOException("Init RTS,DTR failed: result=" + controlTransfer2);
            }
            byte[] rawDescriptors = connection.getRawDescriptors();
            if (rawDescriptors == null || rawDescriptors.length < 14) {
                throw new IOException("Could not get device descriptors");
            }
            byte b = rawDescriptors[13];
            if (b != 7 && b != 8 && b != 9) {
                z = false;
            }
            this.baudRateWithPort = z;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort
        protected void closeInt() {
            try {
                this.mConnection.releaseInterface(this.mDevice.getInterface(this.mPortNumber));
            } catch (Exception unused) {
            }
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public int read(final byte[] dest, final int timeout) throws IOException {
            int read;
            int i;
            if (dest.length <= 2) {
                throw new IllegalArgumentException("Read buffer to small");
            }
            if (timeout != 0) {
                long millis = MonotonicClock.millis() + timeout;
                do {
                    i = super.read(dest, Math.max(1, (int) (millis - MonotonicClock.millis())), false);
                    if (i != 2) {
                        break;
                    }
                } while (MonotonicClock.millis() < millis);
                if (i <= 0 && MonotonicClock.millis() < millis) {
                    testConnection();
                }
                return readFilter(dest, i);
            }
            do {
                read = super.read(dest, timeout, false);
            } while (read == 2);
            i = read;
            return readFilter(dest, i);
        }

        protected int readFilter(byte[] buffer, int totalBytesRead) throws IOException {
            int maxPacketSize = this.mReadEndpoint.getMaxPacketSize();
            int i = 0;
            int i2 = 0;
            while (i < totalBytesRead) {
                int i3 = i + maxPacketSize;
                int i4 = i + 2;
                int min = Math.min(i3, totalBytesRead) - i4;
                if (min < 0) {
                    throw new IOException("Expected at least 2 bytes");
                }
                System.arraycopy(buffer, i4, buffer, i2, min);
                i2 += min;
                i = i3;
            }
            return i2;
        }

        private void setBaudrate(int baudRate) throws IOException {
            int i;
            int i2;
            int i3;
            int i4;
            int i5;
            int i6;
            int i7;
            if (baudRate > 3500000) {
                throw new UnsupportedOperationException("Baud rate to high");
            }
            if (baudRate >= 2500000) {
                i3 = 3000000;
                i2 = 0;
                i = 0;
            } else if (baudRate >= 1750000) {
                i3 = 2000000;
                i = 0;
                i2 = 1;
            } else {
                int i8 = ((48000000 / baudRate) + 1) >> 1;
                i = i8 & 7;
                i2 = i8 >> 3;
                if (i2 > 16383) {
                    throw new UnsupportedOperationException("Baud rate to low");
                }
                i3 = ((48000000 / ((i2 << 3) + i)) + 1) >> 1;
            }
            double abs = Math.abs(1.0d - (i3 / baudRate));
            if (abs >= 0.031d) {
                throw new UnsupportedOperationException(String.format("Baud rate deviation %.1f%% is higher than allowed 3%%", Double.valueOf(abs * 100.0d)));
            }
            switch (i) {
                case 0:
                default:
                    i4 = 0;
                    i5 = i2;
                    break;
                case 1:
                    i6 = i2 | CpioConstants.C_ISSOCK;
                    i5 = i6;
                    i4 = 0;
                    break;
                case 2:
                    i6 = 32768 | i2;
                    i5 = i6;
                    i4 = 0;
                    break;
                case 3:
                    i7 = i2 | 0;
                    i5 = i7;
                    i4 = 1;
                    break;
                case 4:
                    i6 = i2 | 16384;
                    i5 = i6;
                    i4 = 0;
                    break;
                case 5:
                    i7 = i2 | 16384;
                    i5 = i7;
                    i4 = 1;
                    break;
                case 6:
                    i7 = 32768 | i2;
                    i5 = i7;
                    i4 = 1;
                    break;
                case 7:
                    i7 = i2 | CpioConstants.C_ISSOCK;
                    i5 = i7;
                    i4 = 1;
                    break;
            }
            if (this.baudRateWithPort) {
                i4 = (i4 << 8) | (this.mPortNumber + 1);
            }
            int i9 = i4;
            Log.d(FtdiSerialDriver.TAG, String.format("baud rate=%d, effective=%d, error=%.1f%%, value=0x%04x, index=0x%04x, divisor=%d, subdivisor=%d", Integer.valueOf(baudRate), Integer.valueOf(i3), Double.valueOf(abs * 100.0d), Integer.valueOf(i5), Integer.valueOf(i9), Integer.valueOf(i2), Integer.valueOf(i)));
            int controlTransfer = this.mConnection.controlTransfer(64, 3, i5, i9, null, 0, 5000);
            if (controlTransfer == 0) {
                return;
            }
            throw new IOException("Setting baudrate failed: result=" + controlTransfer);
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public void setParameters(int baudRate, int dataBits, int stopBits, int parity) throws IOException {
            if (baudRate <= 0) {
                throw new IllegalArgumentException("Invalid baud rate: " + baudRate);
            }
            setBaudrate(baudRate);
            if (dataBits == 5 || dataBits == 6) {
                throw new UnsupportedOperationException("Unsupported data bits: " + dataBits);
            }
            if (dataBits != 7 && dataBits != 8) {
                throw new IllegalArgumentException("Invalid data bits: " + dataBits);
            }
            int i = dataBits | 0;
            if (parity != 0) {
                if (parity == 1) {
                    i |= 256;
                } else if (parity == 2) {
                    i |= 512;
                } else if (parity == 3) {
                    i |= 768;
                } else {
                    if (parity != 4) {
                        throw new IllegalArgumentException("Invalid parity: " + parity);
                    }
                    i |= 1024;
                }
            }
            if (stopBits != 1) {
                if (stopBits != 2) {
                    if (stopBits == 3) {
                        throw new UnsupportedOperationException("Unsupported stop bits: 1.5");
                    }
                    throw new IllegalArgumentException("Invalid stop bits: " + stopBits);
                }
                i |= 4096;
            }
            int controlTransfer = this.mConnection.controlTransfer(64, 4, i, this.mPortNumber + 1, null, 0, 5000);
            if (controlTransfer != 0) {
                throw new IOException("Setting parameters failed: result=" + controlTransfer);
            }
            this.breakConfig = i;
        }

        private int getStatus() throws IOException {
            byte[] bArr = new byte[2];
            int controlTransfer = this.mConnection.controlTransfer(192, 5, 0, this.mPortNumber + 1, bArr, bArr.length, 5000);
            if (controlTransfer != 2) {
                throw new IOException("Get modem status failed: result=" + controlTransfer);
            }
            return bArr[0];
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public boolean getCD() throws IOException {
            return (getStatus() & 128) != 0;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public boolean getCTS() throws IOException {
            return (getStatus() & 16) != 0;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public boolean getDSR() throws IOException {
            return (getStatus() & 32) != 0;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public boolean getDTR() throws IOException {
            return this.dtr;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public void setDTR(boolean value) throws IOException {
            int controlTransfer = this.mConnection.controlTransfer(64, 1, value ? 257 : 256, this.mPortNumber + 1, null, 0, 5000);
            if (controlTransfer != 0) {
                throw new IOException("Set DTR failed: result=" + controlTransfer);
            }
            this.dtr = value;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public boolean getRI() throws IOException {
            return (getStatus() & 64) != 0;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public boolean getRTS() throws IOException {
            return this.rts;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public void setRTS(boolean value) throws IOException {
            int controlTransfer = this.mConnection.controlTransfer(64, 1, value ? 514 : 512, this.mPortNumber + 1, null, 0, 5000);
            if (controlTransfer != 0) {
                throw new IOException("Set DTR failed: result=" + controlTransfer);
            }
            this.rts = value;
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public EnumSet<UsbSerialPort.ControlLine> getControlLines() throws IOException {
            int status = getStatus();
            EnumSet<UsbSerialPort.ControlLine> noneOf = EnumSet.noneOf(UsbSerialPort.ControlLine.class);
            if (this.rts) {
                noneOf.add(UsbSerialPort.ControlLine.RTS);
            }
            if ((status & 16) != 0) {
                noneOf.add(UsbSerialPort.ControlLine.CTS);
            }
            if (this.dtr) {
                noneOf.add(UsbSerialPort.ControlLine.DTR);
            }
            if ((status & 32) != 0) {
                noneOf.add(UsbSerialPort.ControlLine.DSR);
            }
            if ((status & 128) != 0) {
                noneOf.add(UsbSerialPort.ControlLine.CD);
            }
            if ((status & 64) != 0) {
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
            int controlTransfer;
            int controlTransfer2;
            if (purgeWriteBuffers && (controlTransfer2 = this.mConnection.controlTransfer(64, 0, 1, this.mPortNumber + 1, null, 0, 5000)) != 0) {
                throw new IOException("Purge write buffer failed: result=" + controlTransfer2);
            }
            if (!purgeReadBuffers || (controlTransfer = this.mConnection.controlTransfer(64, 0, 2, this.mPortNumber + 1, null, 0, 5000)) == 0) {
                return;
            }
            throw new IOException("Purge read buffer failed: result=" + controlTransfer);
        }

        @Override // com.hoho.android.usbserial.driver.CommonUsbSerialPort, com.hoho.android.usbserial.driver.UsbSerialPort
        public void setBreak(boolean value) throws IOException {
            int i = this.breakConfig;
            if (value) {
                i |= 16384;
            }
            int controlTransfer = this.mConnection.controlTransfer(64, 4, i, this.mPortNumber + 1, null, 0, 5000);
            if (controlTransfer == 0) {
                return;
            }
            throw new IOException("Setting BREAK failed: result=" + controlTransfer);
        }

        public void setLatencyTimer(int latencyTime) throws IOException {
            int controlTransfer = this.mConnection.controlTransfer(64, 9, latencyTime, this.mPortNumber + 1, null, 0, 5000);
            if (controlTransfer == 0) {
                return;
            }
            throw new IOException("Set latency timer failed: result=" + controlTransfer);
        }

        public int getLatencyTimer() throws IOException {
            byte[] bArr = new byte[1];
            int controlTransfer = this.mConnection.controlTransfer(192, 10, 0, this.mPortNumber + 1, bArr, bArr.length, 5000);
            if (controlTransfer != 1) {
                throw new IOException("Get latency timer failed: result=" + controlTransfer);
            }
            return bArr[0];
        }
    }

    public static Map<Integer, int[]> getSupportedDevices() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(Integer.valueOf(UsbId.VENDOR_FTDI), new int[]{UsbId.FTDI_FT232R, UsbId.FTDI_FT232H, UsbId.FTDI_FT2232H, UsbId.FTDI_FT4232H, UsbId.FTDI_FT231X});
        return linkedHashMap;
    }
}
