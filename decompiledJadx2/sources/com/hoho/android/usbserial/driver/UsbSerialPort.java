package com.hoho.android.usbserial.driver;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import java.io.Closeable;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.EnumSet;

/* loaded from: classes4.dex */
public interface UsbSerialPort extends Closeable {
    public static final int DATABITS_5 = 5;
    public static final int DATABITS_6 = 6;
    public static final int DATABITS_7 = 7;
    public static final int DATABITS_8 = 8;
    public static final int PARITY_EVEN = 2;
    public static final int PARITY_MARK = 3;
    public static final int PARITY_NONE = 0;
    public static final int PARITY_ODD = 1;
    public static final int PARITY_SPACE = 4;
    public static final int STOPBITS_1 = 1;
    public static final int STOPBITS_1_5 = 3;
    public static final int STOPBITS_2 = 2;

    /* loaded from: classes4.dex */
    public enum ControlLine {
        RTS,
        CTS,
        DTR,
        DSR,
        CD,
        RI
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface Parity {
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    boolean getCD() throws IOException;

    boolean getCTS() throws IOException;

    EnumSet<ControlLine> getControlLines() throws IOException;

    boolean getDSR() throws IOException;

    boolean getDTR() throws IOException;

    UsbDevice getDevice();

    UsbSerialDriver getDriver();

    int getPortNumber();

    boolean getRI() throws IOException;

    boolean getRTS() throws IOException;

    UsbEndpoint getReadEndpoint();

    String getSerial();

    EnumSet<ControlLine> getSupportedControlLines() throws IOException;

    UsbEndpoint getWriteEndpoint();

    boolean isOpen();

    void open(UsbDeviceConnection connection) throws IOException;

    void purgeHwBuffers(boolean purgeWriteBuffers, boolean purgeReadBuffers) throws IOException;

    int read(final byte[] dest, final int timeout) throws IOException;

    void setBreak(boolean value) throws IOException;

    void setDTR(boolean value) throws IOException;

    void setParameters(int baudRate, int dataBits, int stopBits, int parity) throws IOException;

    void setRTS(boolean value) throws IOException;

    void write(final byte[] src, final int timeout) throws IOException;
}
