package com.acs.smartcard;

import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbRequest;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* renamed from: com.acs.smartcard.d */
/* loaded from: classes.dex */
public class C0740d {

    /* renamed from: a */
    protected UsbDeviceConnection f217a;

    /* renamed from: b */
    protected UsbEndpoint f218b;

    /* renamed from: c */
    private UsbEndpoint f219c;

    /* renamed from: d */
    private UsbEndpoint f220d;

    /* renamed from: e */
    private UsbRequest f221e;

    /* renamed from: f */
    private ByteBuffer f222f;

    /* renamed from: g */
    private int f223g;

    /* renamed from: h */
    private int f224h;

    /* renamed from: i */
    private List<C0744h> f225i = new ArrayList();

    public C0740d(UsbDeviceConnection usbDeviceConnection, UsbEndpoint usbEndpoint, UsbEndpoint usbEndpoint2, UsbEndpoint usbEndpoint3) {
        this.f217a = usbDeviceConnection;
        this.f219c = usbEndpoint;
        this.f218b = usbEndpoint2;
        this.f220d = usbEndpoint3;
        usbEndpoint3.getMaxPacketSize();
        UsbRequest usbRequest = new UsbRequest();
        this.f221e = usbRequest;
        usbRequest.initialize(this.f217a, this.f220d);
        this.f221e.setClientData(this);
        this.f222f = ByteBuffer.allocate(this.f220d.getMaxPacketSize());
    }

    /* renamed from: d */
    public final void m70d(int i) {
        this.f224h = 10;
    }

    /* renamed from: a */
    public final List<C0744h> m66a() {
        return this.f225i;
    }

    /* renamed from: b */
    public final void m67b() {
        UsbRequest usbRequest = this.f221e;
        ByteBuffer byteBuffer = this.f222f;
        usbRequest.queue(byteBuffer, byteBuffer.capacity());
    }

    /* renamed from: c */
    public final byte[] m68c() {
        return (byte[]) this.f222f.array().clone();
    }

    /* renamed from: d */
    public final void m69d() {
        this.f221e.cancel();
    }

    /* renamed from: e */
    public final void m71e() {
        this.f221e.close();
    }

    /* renamed from: a */
    public byte[] mo56a(int i, int i2) throws ReaderException {
        byte[] bArr = new byte[74];
        byte[] bArr2 = {98, 0, 0, 0, 0, (byte) i, (byte) this.f223g, 0, 0, 0};
        m63f();
        m65a(bArr2, 10, 5000);
        int m62b = m62b(bArr, 74, 10000);
        m61a(i, bArr, m62b, 128);
        int i3 = m62b - 10;
        if (i3 <= 0) {
            return null;
        }
        byte[] bArr3 = new byte[i3];
        System.arraycopy(bArr, 10, bArr3, 0, i3);
        return bArr3;
    }

    /* renamed from: b */
    public void mo58b(int i) throws ReaderException {
        byte[] bArr = new byte[10];
        byte[] bArr2 = {99, 0, 0, 0, 0, (byte) i, (byte) this.f223g, 0, 0, 0};
        m63f();
        m65a(bArr2, 10, 5000);
        m61a(i, bArr, m62b(bArr, 10, 2000), 129);
    }

    /* renamed from: a */
    public void mo55a(int i, byte[] bArr, int i2, int i3, int i4) throws ReaderException {
        mo59b(i, bArr, 0, 4, 0);
    }

    /* renamed from: b */
    public void mo59b(int i, byte[] bArr, int i2, int i3, int i4) throws ReaderException {
        int i5 = i3 + 10;
        byte[] bArr2 = new byte[i5];
        bArr2[0] = 111;
        bArr2[1] = (byte) i3;
        bArr2[2] = (byte) (i3 >> 8);
        bArr2[3] = (byte) (i3 >> 16);
        bArr2[4] = (byte) (i3 >>> 24);
        bArr2[5] = (byte) i;
        bArr2[6] = (byte) this.f223g;
        bArr2[7] = 0;
        bArr2[8] = (byte) i4;
        bArr2[9] = (byte) (i4 >> 8);
        if (bArr != null) {
            System.arraycopy(bArr, i2, bArr2, 10, i3);
        }
        m63f();
        m65a(bArr2, i5, 5000);
    }

    /* renamed from: a */
    public int mo52a(int i, byte[] bArr, int i2, int[] iArr, int i3) throws ReaderException {
        return m64a(i, bArr, 0, i2, (int[]) null, 0);
    }

    /* renamed from: a */
    public final int m64a(int i, byte[] bArr, int i2, int i3, int[] iArr, int i4) throws ReaderException {
        int i5 = i3 + 10;
        byte[] bArr2 = new byte[i5];
        int m62b = m62b(bArr2, i5, i4);
        m61a(i, bArr2, m62b, 128);
        if (bArr != null) {
            System.arraycopy(bArr2, 10, bArr, i2, m62b - 10);
        }
        if (iArr != null) {
            iArr[0] = bArr2[9] & 255;
        }
        return m62b - 10;
    }

    /* renamed from: c */
    public int mo60c(int i) throws ReaderException {
        byte[] bArr = new byte[10];
        byte[] bArr2 = {101, 0, 0, 0, 0, (byte) i, (byte) this.f223g, 0, 0, 0};
        m63f();
        m65a(bArr2, 10, 5000);
        m61a(i, bArr, m62b(bArr, 10, 2000), 129);
        return bArr[7] & 3;
    }

    /* renamed from: a */
    public void mo54a(int i, C0738b c0738b) throws ReaderException {
        int i2;
        byte[] bArr = new byte[17];
        byte[] bArr2 = {97, 0, 0, 0, 0, (byte) i, (byte) this.f223g, 0, 0, 0, (byte) ((c0738b.f194q.f232b << 4) | c0738b.f194q.f233c)};
        if (c0738b.f191n == 1) {
            bArr2[1] = 5;
            bArr2[7] = 0;
            bArr2[11] = (byte) (c0738b.f178a ? 2 : 0);
            bArr2[12] = (byte) c0738b.f188k;
            bArr2[13] = (byte) c0738b.f192o.f257a;
            bArr2[14] = 0;
            i2 = 15;
        } else if (c0738b.f191n == 2) {
            bArr2[1] = 7;
            bArr2[7] = 1;
            bArr2[11] = (byte) ((c0738b.f178a ? 18 : 16) | c0738b.f193p.f263d);
            bArr2[12] = (byte) c0738b.f188k;
            bArr2[13] = (byte) ((c0738b.f193p.f262c << 4) | c0738b.f193p.f261b);
            bArr2[14] = 0;
            bArr2[15] = (byte) ((c0738b.f193p.f260a == 0 || c0738b.f193p.f260a == 255) ? 32 : c0738b.f193p.f260a);
            bArr2[16] = 0;
            i2 = 17;
        } else {
            throw new IllegalArgumentException("invalid selected protocol");
        }
        m63f();
        m65a(bArr2, i2, 5000);
        m61a(i, bArr, m62b(bArr, 17, 2000), 130);
    }

    /* renamed from: a */
    public int mo51a(int i, byte[] bArr, int i2, byte[] bArr2, int i3, int i4) throws ReaderException {
        int i5 = i2 + 10;
        byte[] bArr3 = new byte[i5];
        int i6 = i3 + 10;
        byte[] bArr4 = new byte[i6];
        bArr3[0] = 107;
        bArr3[1] = (byte) i2;
        bArr3[2] = (byte) (i2 >> 8);
        bArr3[3] = (byte) (i2 >> 16);
        bArr3[4] = (byte) (i2 >>> 24);
        bArr3[5] = (byte) i;
        bArr3[6] = (byte) this.f223g;
        bArr3[7] = 0;
        bArr3[8] = 0;
        bArr3[9] = 0;
        System.arraycopy(bArr, 0, bArr3, 10, i2);
        m63f();
        m65a(bArr3, i5, 5000);
        int m62b = m62b(bArr4, i6, i4);
        m61a(i, bArr4, m62b, 131);
        int i7 = m62b - 10;
        System.arraycopy(bArr4, 10, bArr2, 0, i7);
        return i7;
    }

    /* renamed from: b */
    public int mo57b(int i, byte[] bArr, int i2, byte[] bArr2, int i3, int i4) throws ReaderException {
        int i5 = i2 + 10;
        byte[] bArr3 = new byte[i5];
        int i6 = i3 + 10;
        byte[] bArr4 = new byte[i6];
        bArr3[0] = 105;
        bArr3[1] = (byte) i2;
        bArr3[2] = (byte) (i2 >> 8);
        bArr3[3] = (byte) (i2 >> 16);
        bArr3[4] = (byte) (i2 >>> 24);
        bArr3[5] = (byte) i;
        bArr3[6] = (byte) this.f223g;
        bArr3[7] = 0;
        bArr3[8] = 0;
        bArr3[9] = 0;
        System.arraycopy(bArr, 0, bArr3, 10, i2);
        m63f();
        m65a(bArr3, i5, 5000);
        int m62b = m62b(bArr4, i6, 0);
        try {
            m61a(i, bArr4, m62b, 128);
            i3 = m62b - 10;
            System.arraycopy(bArr4, 10, bArr2, 0, i3);
        } catch (CommunicationErrorException e) {
            if (e.isCcidError()) {
                byte ccidErrorCode = e.getCcidErrorCode();
                if (ccidErrorCode == -124) {
                    if (i3 < 2) {
                        throw new InsufficientBufferException("Required buffer length: 2");
                    }
                    bArr2[0] = 100;
                    bArr2[1] = 2;
                    return 2;
                }
                if (ccidErrorCode == -1) {
                    if (i3 < 2) {
                        throw new InsufficientBufferException("Required buffer length: 2");
                    }
                    bArr2[0] = 107;
                    bArr2[1] = Byte.MIN_VALUE;
                    return 2;
                }
                if (ccidErrorCode == -17) {
                    if (i3 < 2) {
                        throw new InsufficientBufferException("Required buffer length: 2");
                    }
                    bArr2[0] = 100;
                    bArr2[1] = 1;
                    return 2;
                }
                if (ccidErrorCode != -16) {
                    throw e;
                }
                if (i3 < 2) {
                    throw new InsufficientBufferException("Required buffer length: 2");
                }
                bArr2[0] = 100;
                bArr2[1] = 0;
                return 2;
            }
        }
        return i3;
    }

    /* renamed from: f */
    private void m63f() {
        int i = this.f223g + 1;
        this.f223g = i;
        if (i > 255) {
            this.f223g = 0;
        }
    }

    /* renamed from: b */
    private int m62b(byte[] bArr, int i, int i2) throws ReaderException {
        int maxPacketSize = this.f218b.getMaxPacketSize();
        byte[] bArr2 = new byte[maxPacketSize];
        int i3 = i;
        boolean z = false;
        while (true) {
            int i4 = 0;
            boolean z2 = false;
            int i5 = 0;
            while (!z) {
                int bulkTransfer = this.f217a.bulkTransfer(this.f218b, bArr2, maxPacketSize, i2);
                int i6 = 10;
                while (bulkTransfer < 0 && i6 > 0) {
                    i6--;
                    bulkTransfer = this.f217a.bulkTransfer(this.f218b, bArr2, maxPacketSize, 1000);
                }
                if (bulkTransfer < 0) {
                    throw new CommunicationErrorException("USB read error: " + bulkTransfer);
                }
                if (i3 >= bulkTransfer) {
                    System.arraycopy(bArr2, 0, bArr, i4, bulkTransfer);
                    i3 -= bulkTransfer;
                } else {
                    i3 = 0;
                }
                i4 += bulkTransfer;
                if (!z2 && i4 >= 10) {
                    i5 = ((bArr[4] & 255) << 24) | (bArr[1] & 255) | ((bArr[2] & 255) << 8) | ((bArr[3] & 255) << 16);
                    z2 = true;
                }
                if (z2 && i4 >= i5 + 10) {
                    if ((3 & (bArr[7] >> 6)) == 2) {
                        break;
                    }
                    z = true;
                }
            }
            if (!z || i4 <= i) {
                return i4;
            }
            throw new InsufficientBufferException("Required buffer length: " + i4);
            i3 = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final int m65a(byte[] bArr, int i, int i2) throws ReaderException {
        int maxPacketSize = this.f219c.getMaxPacketSize();
        byte[] bArr2 = new byte[maxPacketSize];
        int i3 = 0;
        while (i > 0) {
            int i4 = i > maxPacketSize ? maxPacketSize : i;
            System.arraycopy(bArr, i3, bArr2, 0, i4);
            int bulkTransfer = this.f217a.bulkTransfer(this.f219c, bArr2, i4, 5000);
            if (bulkTransfer < 0) {
                throw new CommunicationErrorException("USB write error: " + bulkTransfer);
            }
            int i5 = this.f224h;
            if (i5 > 0 && i > maxPacketSize) {
                try {
                    Thread.sleep(i5);
                } catch (InterruptedException unused) {
                }
            }
            i3 += i4;
            i -= i4;
        }
        return i3;
    }

    /* renamed from: a */
    private void m61a(int i, byte[] bArr, int i2, int i3) throws ReaderException {
        if (i2 < 10) {
            throw new CommunicationErrorException("The response length (" + i2 + ") is invalid.");
        }
        if ((bArr[0] & 255) != i3) {
            throw new CommunicationErrorException("The message type (0x" + Integer.toHexString(bArr[0] & 255) + ") is invalid.");
        }
        if ((bArr[5] & 255) != i) {
            throw new CommunicationErrorException("The slot number (" + (bArr[5] & 255) + ") is invalid.");
        }
        int i4 = (bArr[6] & 255) + 1;
        if (i4 > 255) {
            i4 = 0;
        }
        if (i4 != this.f223g) {
            throw new CommunicationErrorException("The sequence number (" + (bArr[6] & 255) + ") is invalid.");
        }
        int i5 = bArr[7] & 3;
        if (((bArr[7] >> 6) & 3) == 1) {
            byte b = bArr[8];
            if (i3 != 130 || b < 0 || b > Byte.MAX_VALUE) {
                if (b == -2) {
                    if (i5 == 2) {
                        throw new RemovedCardException();
                    }
                    throw new UnresponsiveCardException();
                }
                CommunicationErrorException communicationErrorException = new CommunicationErrorException("CCID Error: " + ((int) bArr[8]));
                communicationErrorException.setCcidError(true);
                communicationErrorException.setCcidErrorCode(bArr[8]);
                throw communicationErrorException;
            }
        }
    }
}
