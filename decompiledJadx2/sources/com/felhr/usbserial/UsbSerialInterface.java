package com.felhr.usbserial;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public interface UsbSerialInterface {
    public static final int DATA_BITS_5 = 5;
    public static final int DATA_BITS_6 = 6;
    public static final int DATA_BITS_7 = 7;
    public static final int DATA_BITS_8 = 8;
    public static final int FLOW_CONTROL_DSR_DTR = 2;
    public static final int FLOW_CONTROL_OFF = 0;
    public static final int FLOW_CONTROL_RTS_CTS = 1;
    public static final int FLOW_CONTROL_XON_XOFF = 3;
    public static final int PARITY_EVEN = 2;
    public static final int PARITY_MARK = 3;
    public static final int PARITY_NONE = 0;
    public static final int PARITY_ODD = 1;
    public static final int PARITY_SPACE = 4;
    public static final int STOP_BITS_1 = 1;
    public static final int STOP_BITS_15 = 3;
    public static final int STOP_BITS_2 = 2;

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes.dex */
    public interface UsbBreakCallback {
        void onBreakInterrupt();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes.dex */
    public interface UsbCTSCallback {
        void onCTSChanged(boolean z);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes.dex */
    public interface UsbDSRCallback {
        void onDSRChanged(boolean z);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes.dex */
    public interface UsbFrameCallback {
        void onFramingError();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes.dex */
    public interface UsbOverrunCallback {
        void onOverrunError();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes.dex */
    public interface UsbParityCallback {
        void onParityError();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* loaded from: classes.dex */
    public interface UsbReadCallback {
        void onReceivedData(byte[] bArr);
    }

    void close();

    void getBreak(UsbBreakCallback usbBreakCallback);

    void getCTS(UsbCTSCallback usbCTSCallback);

    void getDSR(UsbDSRCallback usbDSRCallback);

    void getFrame(UsbFrameCallback usbFrameCallback);

    void getOverrun(UsbOverrunCallback usbOverrunCallback);

    void getParity(UsbParityCallback usbParityCallback);

    boolean open();

    int read(UsbReadCallback usbReadCallback);

    void setBaudRate(int i);

    void setDTR(boolean z);

    void setDataBits(int i);

    void setFlowControl(int i);

    void setParity(int i);

    void setRTS(boolean z);

    void setStopBits(int i);

    void syncClose();

    boolean syncOpen();

    int syncRead(byte[] bArr, int i);

    int syncWrite(byte[] bArr, int i);

    void write(byte[] bArr);
}
