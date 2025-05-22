package com.acs.smartcard;

import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class Features {
    public static final int FEATURE_ABORT = 11;
    public static final int FEATURE_CCID_ESC_COMMAND = 19;
    public static final int FEATURE_GET_KEY = 16;
    public static final int FEATURE_GET_KEY_PRESSED = 5;
    public static final int FEATURE_GET_TLV_PROPERTIES = 18;
    public static final int FEATURE_IFD_DISPLAY_PROPERTIES = 17;
    public static final int FEATURE_IFD_PIN_PROPERTIES = 10;
    public static final int FEATURE_MCT_READER_DIRECT = 8;
    public static final int FEATURE_MCT_UNIVERSAL = 9;
    public static final int FEATURE_MODIFY_PIN_DIRECT = 7;
    public static final int FEATURE_MODIFY_PIN_DIRECT_APP_ID = 14;
    public static final int FEATURE_MODIFY_PIN_FINISH = 4;
    public static final int FEATURE_MODIFY_PIN_START = 3;
    public static final int FEATURE_SET_SPE_MESSAGE = 12;
    public static final int FEATURE_VERIFY_PIN_DIRECT = 6;
    public static final int FEATURE_VERIFY_PIN_DIRECT_APP_ID = 13;
    public static final int FEATURE_VERIFY_PIN_FINISH = 2;
    public static final int FEATURE_VERIFY_PIN_START = 1;
    public static final int FEATURE_WRITE_DISPLAY = 15;

    /* renamed from: a */
    private int[] f149a;

    /* renamed from: a */
    private static boolean m24a(int i) {
        return i > 0 && i <= 19;
    }

    public Features() {
        this.f149a = new int[20];
    }

    public Features(byte[] bArr, int i) {
        this.f149a = new int[20];
        fromByteArray(bArr, i);
    }

    public void clear() {
        Arrays.fill(this.f149a, -1);
    }

    public int getControlCode(int i) {
        if (m24a(i)) {
            return this.f149a[i];
        }
        return -1;
    }

    public void fromByteArray(byte[] bArr, int i) {
        int i2;
        int i3;
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
        int i4 = 0;
        while (i4 < i) {
            int i5 = bArr[i4] & 255;
            if (m24a(i5) && (i2 = i4 + 1) < i && (i3 = bArr[i2] & 255) == 4 && i2 + i3 < i) {
                this.f149a[i5] = ((bArr[i4 + 2] & 255) << 24) | ((bArr[i4 + 3] & 255) << 16) | ((bArr[i4 + 4] & 255) << 8) | (bArr[i4 + 5] & 255);
            }
            int i6 = i4 + 1;
            if (i6 >= i) {
                return;
            } else {
                i4 += (bArr[i6] & 255) + 2;
            }
        }
    }
}
