package com.acs.smartcard;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TlvProperties {
    public static final int PROPERTY_bEntryValidationCondition = 2;
    public static final int PROPERTY_bMaxPINSize = 7;
    public static final int PROPERTY_bMinPINSize = 6;
    public static final int PROPERTY_bPPDUSupport = 9;
    public static final int PROPERTY_bTimeOut2 = 3;
    public static final int PROPERTY_dwMaxAPDUDataSize = 10;
    public static final int PROPERTY_sFirmwareID = 8;
    public static final int PROPERTY_wIdProduct = 12;
    public static final int PROPERTY_wIdVendor = 11;
    public static final int PROPERTY_wLcdLayout = 1;
    public static final int PROPERTY_wLcdMaxCharacters = 4;
    public static final int PROPERTY_wLcdMaxLines = 5;

    /* renamed from: a */
    private Object[] f176a;

    /* renamed from: a */
    private static boolean m49a(int i) {
        return i > 0 && i <= 12;
    }

    public TlvProperties() {
        this.f176a = new Object[13];
    }

    public TlvProperties(byte[] bArr, int i) {
        this.f176a = new Object[13];
        fromByteArray(bArr, i);
    }

    public void clear() {
        Arrays.fill(this.f176a, (Object) null);
    }

    public Object getProperty(int i) {
        if (m49a(i)) {
            return this.f176a[i];
        }
        return null;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:13:0x0025. Please report as an issue. */
    public void fromByteArray(byte[] bArr, int i) {
        int i2;
        if (bArr == null) {
            throw new IllegalArgumentException("The buffer is null.");
        }
        if (i <= 0) {
            throw new IllegalArgumentException("The buffer length is less than or equal to zero.");
        }
        if (i > bArr.length) {
            throw new IllegalArgumentException("The buffer length is greater than the buffer size.");
        }
        clear();
        int i3 = 0;
        while (i3 < i) {
            int i4 = bArr[i3] & 255;
            if (m49a(i4) && (i2 = i3 + 1) < i) {
                int i5 = bArr[i2] & 255;
                if (i2 + i5 < i) {
                    switch (i4) {
                        case 1:
                        case 4:
                        case 5:
                        case 11:
                        case 12:
                            if (i5 == 2) {
                                this.f176a[i4] = Integer.valueOf((bArr[i3 + 2] & 255) | ((bArr[i3 + 3] & 255) << 8));
                                break;
                            }
                            break;
                        case 2:
                        case 3:
                        case 6:
                        case 7:
                        case 9:
                            if (i5 == 1) {
                                this.f176a[i4] = Integer.valueOf(bArr[i3 + 2] & 255);
                                break;
                            }
                            break;
                        case 8:
                            if (i5 > 0) {
                                try {
                                    this.f176a[i4] = new String(bArr, i3 + 2, i5, "UTF-8");
                                    break;
                                } catch (UnsupportedEncodingException unused) {
                                    break;
                                }
                            }
                            break;
                        case 10:
                            if (i5 == 4) {
                                this.f176a[i4] = Integer.valueOf((bArr[i3 + 2] & 255) | ((bArr[i3 + 3] & 255) << 8) | ((bArr[i3 + 4] & 255) << 16) | ((bArr[i3 + 5] & 255) << 24));
                                break;
                            }
                            break;
                    }
                }
            }
            int i6 = i3 + 1;
            if (i6 >= i) {
                return;
            } else {
                i3 += (bArr[i6] & 255) + 2;
            }
        }
    }
}
