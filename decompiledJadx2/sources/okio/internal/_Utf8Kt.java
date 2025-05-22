package okio.internal;

import com.fasterxml.jackson.core.base.GeneratorBase;
import com.pudu.library.loracall.SlipConfig;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.DimensionsKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: -Utf8.kt */
@Metadata(m3959bv = {1, 0, 2}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0002*\u00020\u0001¨\u0006\u0004"}, m3961d2 = {"commonAsUtf8ToByteArray", "", "", "commonToUtf8String", "jvm"}, m3962k = 2, m3963mv = {1, 1, 11})
/* loaded from: classes2.dex */
public final class _Utf8Kt {
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0105, code lost:
    
        if (((r16[r5] & com.pudu.library.loracall.SlipConfig.END) == 128) == false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x008d, code lost:
    
        if (((r16[r5] & com.pudu.library.loracall.SlipConfig.END) == 128) == false) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final String commonToUtf8String(byte[] receiver) {
        int i;
        int i2;
        int i3;
        int i4;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        char[] cArr = new char[receiver.length];
        int length = receiver.length;
        int i5 = 0;
        int i6 = 0;
        while (i5 < length) {
            byte b = receiver[i5];
            if (b >= 0) {
                i = i6 + 1;
                cArr[i6] = (char) b;
                i5++;
                while (i5 < length && receiver[i5] >= 0) {
                    cArr[i] = (char) receiver[i5];
                    i5++;
                    i++;
                }
            } else {
                if ((b >> 5) == -2) {
                    int i7 = i5 + 1;
                    if (length <= i7) {
                        i = i6 + 1;
                        cArr[i6] = (char) 65533;
                    } else {
                        byte b2 = receiver[i5];
                        byte b3 = receiver[i7];
                        if ((b3 & SlipConfig.END) == 128) {
                            int i8 = (b3 ^ 3968) ^ (b2 << 6);
                            if (i8 < 128) {
                                i = i6 + 1;
                                cArr[i6] = (char) 65533;
                            } else {
                                i = i6 + 1;
                                cArr[i6] = (char) i8;
                            }
                            i2 = 2;
                        } else {
                            i = i6 + 1;
                            cArr[i6] = (char) 65533;
                        }
                    }
                    i2 = 1;
                } else if ((b >> 4) == -2) {
                    int i9 = i5 + 2;
                    if (length <= i9) {
                        i = i6 + 1;
                        cArr[i6] = (char) 65533;
                        int i10 = i5 + 1;
                        if (length > i10) {
                        }
                        i2 = 1;
                    } else {
                        byte b4 = receiver[i5];
                        byte b5 = receiver[i5 + 1];
                        if ((b5 & SlipConfig.END) == 128) {
                            byte b6 = receiver[i9];
                            if ((b6 & SlipConfig.END) == 128) {
                                int i11 = ((b6 ^ (-123008)) ^ (b5 << 6)) ^ (b4 << 12);
                                if (i11 < 2048) {
                                    i = i6 + 1;
                                    cArr[i6] = (char) 65533;
                                } else if (55296 <= i11 && 57343 >= i11) {
                                    i = i6 + 1;
                                    cArr[i6] = (char) 65533;
                                } else {
                                    i = i6 + 1;
                                    cArr[i6] = (char) i11;
                                }
                                i2 = 3;
                            } else {
                                i = i6 + 1;
                                cArr[i6] = (char) 65533;
                                i2 = 2;
                            }
                        } else {
                            i = i6 + 1;
                            cArr[i6] = (char) 65533;
                            i2 = 1;
                        }
                    }
                } else {
                    if ((b >> 3) == -2) {
                        int i12 = i5 + 3;
                        if (length <= i12) {
                            i3 = i6 + 1;
                            cArr[i6] = 65533;
                            int i13 = i5 + 1;
                            if (length > i13) {
                                if ((receiver[i13] & SlipConfig.END) == 128) {
                                    int i14 = i5 + 2;
                                    if (length > i14) {
                                    }
                                    i4 = 2;
                                }
                            }
                            i4 = 1;
                        } else {
                            byte b7 = receiver[i5];
                            byte b8 = receiver[i5 + 1];
                            if ((b8 & SlipConfig.END) == 128) {
                                byte b9 = receiver[i5 + 2];
                                if ((b9 & SlipConfig.END) == 128) {
                                    byte b10 = receiver[i12];
                                    if ((b10 & SlipConfig.END) == 128) {
                                        int i15 = (((b10 ^ 3678080) ^ (b9 << 6)) ^ (b8 << 12)) ^ (b7 << 18);
                                        if (i15 > 1114111) {
                                            i3 = i6 + 1;
                                            cArr[i6] = 65533;
                                        } else if (55296 <= i15 && 57343 >= i15) {
                                            i3 = i6 + 1;
                                            cArr[i6] = 65533;
                                        } else if (i15 < 65536) {
                                            i3 = i6 + 1;
                                            cArr[i6] = 65533;
                                        } else if (i15 != 65533) {
                                            int i16 = i6 + 1;
                                            cArr[i6] = (char) ((i15 >>> 10) + 55232);
                                            char c = (char) ((i15 & 1023) + GeneratorBase.SURR2_FIRST);
                                            i3 = i16 + 1;
                                            cArr[i16] = c;
                                        } else {
                                            i3 = i6 + 1;
                                            cArr[i6] = 65533;
                                        }
                                        i4 = 4;
                                    } else {
                                        i3 = i6 + 1;
                                        cArr[i6] = 65533;
                                        i4 = 3;
                                    }
                                } else {
                                    i3 = i6 + 1;
                                    cArr[i6] = 65533;
                                    i4 = 2;
                                }
                            } else {
                                i3 = i6 + 1;
                                cArr[i6] = 65533;
                                i4 = 1;
                            }
                        }
                        i5 += i4;
                    } else {
                        i3 = i6 + 1;
                        cArr[i6] = 65533;
                        i5++;
                    }
                    i6 = i3;
                }
                i5 += i2;
            }
            i6 = i;
        }
        return new String(cArr, 0, i6);
    }

    public static final byte[] commonAsUtf8ToByteArray(String receiver) {
        int i;
        int i2;
        char charAt;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        byte[] bArr = new byte[receiver.length() * 4];
        int length = receiver.length();
        int i3 = 0;
        while (i3 < length) {
            char charAt2 = receiver.charAt(i3);
            if (charAt2 >= 128) {
                int length2 = receiver.length();
                int i4 = i3;
                while (i3 < length2) {
                    char charAt3 = receiver.charAt(i3);
                    if (charAt3 < 128) {
                        int i5 = i4 + 1;
                        bArr[i4] = (byte) charAt3;
                        i3++;
                        while (i3 < length2 && receiver.charAt(i3) < 128) {
                            bArr[i5] = (byte) receiver.charAt(i3);
                            i3++;
                            i5++;
                        }
                        i4 = i5;
                    } else {
                        if (charAt3 < 2048) {
                            int i6 = i4 + 1;
                            bArr[i4] = (byte) ((charAt3 >> 6) | 192);
                            byte b = (byte) ((charAt3 & '?') | 128);
                            i = i6 + 1;
                            bArr[i6] = b;
                        } else if (55296 > charAt3 || 57343 < charAt3) {
                            int i7 = i4 + 1;
                            bArr[i4] = (byte) ((charAt3 >> '\f') | 224);
                            int i8 = i7 + 1;
                            bArr[i7] = (byte) (((charAt3 >> 6) & 63) | 128);
                            byte b2 = (byte) ((charAt3 & '?') | 128);
                            i = i8 + 1;
                            bArr[i8] = b2;
                        } else if (charAt3 > 56319 || length2 <= (i2 = i3 + 1) || 56320 > (charAt = receiver.charAt(i2)) || 57343 < charAt) {
                            i = i4 + 1;
                            bArr[i4] = 63;
                        } else {
                            int charAt4 = ((charAt3 << '\n') + receiver.charAt(i2)) - 56613888;
                            int i9 = i4 + 1;
                            bArr[i4] = (byte) ((charAt4 >> 18) | DimensionsKt.HDPI);
                            int i10 = i9 + 1;
                            bArr[i9] = (byte) (((charAt4 >> 12) & 63) | 128);
                            int i11 = i10 + 1;
                            bArr[i10] = (byte) (((charAt4 >> 6) & 63) | 128);
                            byte b3 = (byte) ((charAt4 & 63) | 128);
                            i = i11 + 1;
                            bArr[i11] = b3;
                            i3 += 2;
                            i4 = i;
                        }
                        i3++;
                        i4 = i;
                    }
                }
                byte[] copyOf = Arrays.copyOf(bArr, i4);
                Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
                return copyOf;
            }
            bArr[i3] = (byte) charAt2;
            i3++;
        }
        byte[] copyOf2 = Arrays.copyOf(bArr, receiver.length());
        Intrinsics.checkExpressionValueIsNotNull(copyOf2, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf2;
    }
}
