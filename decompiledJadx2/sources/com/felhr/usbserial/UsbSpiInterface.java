package com.felhr.usbserial;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface UsbSpiInterface {
    public static final int DIVIDER_128 = 128;
    public static final int DIVIDER_16 = 16;
    public static final int DIVIDER_2 = 2;
    public static final int DIVIDER_32 = 32;
    public static final int DIVIDER_4 = 4;
    public static final int DIVIDER_64 = 64;
    public static final int DIVIDER_8 = 8;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public interface UsbMISOCallback {
        int onReceivedData(byte[] bArr);
    }

    void closeSPI();

    boolean connectSPI();

    int getClockDivider();

    int getSelectedSlave();

    void readMISO(int i);

    void selectSlave(int i);

    void setClock(int i);

    void setMISOCallback(UsbMISOCallback usbMISOCallback);

    void writeMOSI(byte[] bArr);

    void writeRead(byte[] bArr, int i);
}
