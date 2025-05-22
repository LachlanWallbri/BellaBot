package com.acs.smartcard;

import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* renamed from: com.acs.smartcard.a */
/* loaded from: classes.dex */
public final class C0737a extends C0740d {

    /* renamed from: c */
    private int f177c;

    public C0737a(UsbDeviceConnection usbDeviceConnection, UsbEndpoint usbEndpoint, UsbEndpoint usbEndpoint2, UsbEndpoint usbEndpoint3) {
        super(usbDeviceConnection, usbEndpoint, usbEndpoint2, usbEndpoint3);
    }

    /* renamed from: a */
    public final void m53a(int i) {
        this.f177c = i;
    }

    @Override // com.acs.smartcard.C0740d
    /* renamed from: a */
    public final byte[] mo56a(int i, int i2) throws ReaderException {
        byte[] bArr;
        int i3;
        byte[] bArr2;
        byte[] bArr3 = new byte[68];
        if (i == 0) {
            bArr = new byte[5];
            int i4 = this.f177c;
            i3 = (i4 == 0 || i4 == 12 || i4 == 13) ? 0 : 32;
            m65a(new byte[]{1, 7, 0, 1, (byte) i3}, 5, 5000);
            m50b(new byte[4], 4, 2000);
            m65a(new byte[]{1, 2, 0, 1, (byte) this.f177c}, 5, 5000);
            m50b(new byte[4], 4, 2000);
            bArr[0] = 1;
            bArr[1] = Byte.MIN_VALUE;
            bArr[2] = 0;
            bArr[3] = 1;
            bArr[4] = 0;
        } else {
            bArr = new byte[4];
            mo58b(i);
            try {
                Thread.sleep(10L);
            } catch (InterruptedException unused) {
            }
            bArr[0] = 1;
            bArr[1] = -112;
            bArr[2] = 0;
            bArr[3] = 0;
            i3 = 0;
        }
        m65a(bArr, bArr.length, 5000);
        int m50b = m50b(bArr3, 68, 10000) - 4;
        if (m50b > 0) {
            bArr2 = new byte[m50b];
            System.arraycopy(bArr3, 4, bArr2, 0, m50b);
        } else {
            bArr2 = null;
        }
        if (i == 0 && i3 == 32) {
            m65a(new byte[]{1, -96, 0, 6, -1, -92, 0, 0, 1, (byte) this.f177c}, 10, 5000);
            m50b(new byte[44], 44, 2000);
        }
        return bArr2;
    }

    @Override // com.acs.smartcard.C0740d
    /* renamed from: b */
    public final void mo58b(int i) throws ReaderException {
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[4];
        bArr[0] = 1;
        bArr[1] = (byte) (i == 0 ? 129 : 145);
        bArr[2] = 0;
        bArr[3] = 0;
        m65a(bArr, 4, 5000);
        m50b(bArr2, 4, 2000);
    }

    @Override // com.acs.smartcard.C0740d
    /* renamed from: a */
    public final void mo55a(int i, byte[] bArr, int i2, int i3, int i4) throws ReaderException {
        byte[] bArr2 = new byte[8];
        bArr2[0] = 1;
        bArr2[1] = (byte) (i == 0 ? 10 : 12);
        bArr2[2] = 0;
        bArr2[3] = 4;
        System.arraycopy(bArr, 0, bArr2, 4, 4);
        m65a(bArr2, 8, 5000);
    }

    @Override // com.acs.smartcard.C0740d
    /* renamed from: b */
    public final void mo59b(int i, byte[] bArr, int i2, int i3, int i4) throws ReaderException {
        int i5 = i3 + 4;
        byte[] bArr2 = new byte[i5];
        C0744h c0744h = m66a().get(i);
        bArr2[0] = 1;
        if (c0744h.f247d.f191n == 1) {
            bArr2[1] = (byte) (i == 0 ? 160 : 176);
        } else {
            bArr2[1] = (byte) (i == 0 ? 161 : 177);
        }
        bArr2[2] = (byte) (i3 >> 8);
        bArr2[3] = (byte) i3;
        if (bArr != null) {
            System.arraycopy(bArr, i2, bArr2, 4, i3);
        }
        m65a(bArr2, i5, 5000);
    }

    @Override // com.acs.smartcard.C0740d
    /* renamed from: a */
    public final int mo52a(int i, byte[] bArr, int i2, int[] iArr, int i3) throws ReaderException {
        int i4 = i2 + 4;
        byte[] bArr2 = new byte[i4];
        int m50b = m50b(bArr2, i4, 0) - 4;
        if (bArr != null) {
            System.arraycopy(bArr2, 4, bArr, 0, m50b);
        }
        return m50b;
    }

    @Override // com.acs.smartcard.C0740d
    /* renamed from: c */
    public final int mo60c(int i) throws ReaderException {
        byte b;
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[20];
        if (i == 0) {
            bArr[0] = 1;
            bArr[1] = 1;
            bArr[2] = 0;
            bArr[3] = 0;
            m65a(bArr, 4, 5000);
            if (m50b(bArr2, 20, 2000) > 19 && (b = bArr2[19]) != 0) {
                if (b != 1) {
                    if (b == 3) {
                        return 0;
                    }
                }
            }
            return 2;
        }
        return 1;
    }

    @Override // com.acs.smartcard.C0740d
    /* renamed from: a */
    public final void mo54a(int i, C0738b c0738b) throws ReaderException {
        byte[] bArr = new byte[8];
        byte[] bArr2 = new byte[8];
        bArr[0] = 1;
        bArr[1] = (byte) (i == 0 ? 11 : 13);
        bArr[2] = 0;
        bArr[3] = 4;
        bArr[4] = -1;
        bArr[5] = (byte) (c0738b.f191n == 1 ? 16 : 17);
        bArr[6] = (byte) (c0738b.f194q.f233c | (c0738b.f194q.f232b << 4));
        bArr[7] = 0;
        for (int i2 = 4; i2 < 7; i2++) {
            bArr[7] = (byte) (bArr[7] ^ bArr[i2]);
        }
        m65a(bArr, 8, 5000);
        m50b(bArr2, 8, 2000);
    }

    @Override // com.acs.smartcard.C0740d
    /* renamed from: a */
    public final int mo51a(int i, byte[] bArr, int i2, byte[] bArr2, int i3, int i4) throws ReaderException {
        throw new CommunicationErrorException("The command is not supported.");
    }

    @Override // com.acs.smartcard.C0740d
    /* renamed from: b */
    public final int mo57b(int i, byte[] bArr, int i2, byte[] bArr2, int i3, int i4) throws ReaderException {
        throw new CommunicationErrorException("The command is not supported.");
    }

    /* renamed from: b */
    private int m50b(byte[] bArr, int i, int i2) throws ReaderException {
        int maxPacketSize = this.f218b.getMaxPacketSize();
        byte[] bArr2 = new byte[maxPacketSize];
        int i3 = i;
        boolean z = false;
        int i4 = 0;
        boolean z2 = false;
        int i5 = 0;
        while (!z) {
            int bulkTransfer = this.f217a.bulkTransfer(this.f218b, bArr2, maxPacketSize, i2);
            if (bulkTransfer < 0) {
                throw new CommunicationErrorException("USB read error: " + bulkTransfer);
            }
            if (i3 >= bulkTransfer) {
                System.arraycopy(bArr2, 0, bArr, i4, bulkTransfer);
                i3 -= bulkTransfer;
            }
            i4 += bulkTransfer;
            if (!z2 && i4 >= 4 && bArr[0] == 1) {
                i5 = (bArr[3] & 255) | ((bArr[2] & 255) << 8);
                z2 = true;
            }
            if (z2 && i4 >= i5 + 4) {
                int i6 = bArr[1] & 255;
                if (i6 != 0) {
                    if (i6 == 254) {
                        throw new UnresponsiveCardException();
                    }
                    if (i6 == 249) {
                        throw new UnpoweredCardException();
                    }
                    if (i6 == 250) {
                        throw new RemovedCardException();
                    }
                    CommunicationErrorException communicationErrorException = new CommunicationErrorException("ACR38 Error: " + i6);
                    communicationErrorException.setCcidError(true);
                    communicationErrorException.setCcidErrorCode((byte) i6);
                    throw communicationErrorException;
                }
                z = true;
            }
        }
        if (!z || i4 <= i) {
            return i4;
        }
        throw new InsufficientBufferException("Required buffer length: " + i4);
    }
}
