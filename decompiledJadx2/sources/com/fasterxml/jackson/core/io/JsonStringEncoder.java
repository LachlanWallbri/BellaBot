package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.util.Arrays;
import org.jetbrains.anko.DimensionsKt;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public final class JsonStringEncoder {
    private static final int INITIAL_BYTE_BUFFER_SIZE = 200;
    private static final int INITIAL_CHAR_BUFFER_SIZE = 120;
    private static final int SURR1_FIRST = 55296;
    private static final int SURR1_LAST = 56319;
    private static final int SURR2_FIRST = 56320;
    private static final int SURR2_LAST = 57343;

    /* renamed from: HC */
    private static final char[] f1255HC = CharTypes.copyHexChars();

    /* renamed from: HB */
    private static final byte[] f1254HB = CharTypes.copyHexBytes();
    private static final JsonStringEncoder instance = new JsonStringEncoder();

    private char[] _qbuf() {
        return new char[]{'\\', 0, '0', '0'};
    }

    public static JsonStringEncoder getInstance() {
        return instance;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0026, code lost:
    
        r9 = r0 + 1;
        r0 = r13.charAt(r0);
        r10 = r1[r0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x002e, code lost:
    
        if (r10 >= 0) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0030, code lost:
    
        r0 = _appendNumeric(r0, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0039, code lost:
    
        r10 = r6 + r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x003c, code lost:
    
        if (r10 <= r4.length) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003e, code lost:
    
        r10 = r4.length - r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0040, code lost:
    
        if (r10 <= 0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0042, code lost:
    
        java.lang.System.arraycopy(r8, 0, r4, r6, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0045, code lost:
    
        if (r7 != null) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0047, code lost:
    
        r7 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004b, code lost:
    
        r4 = r7.finishCurrentSegment();
        r0 = r0 - r10;
        java.lang.System.arraycopy(r8, r10, r4, 0, r0);
        r6 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0055, code lost:
    
        java.lang.System.arraycopy(r8, 0, r4, r6, r0);
        r6 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0035, code lost:
    
        r0 = _appendNamed(r10, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0020, code lost:
    
        if (r8 != null) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0022, code lost:
    
        r8 = _qbuf();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public char[] quoteAsString(String str) {
        int i;
        int[] iArr = CharTypes.get7BitOutputEscapes();
        int length = iArr.length;
        int length2 = str.length();
        TextBuffer textBuffer = null;
        char[] cArr = null;
        int i2 = 0;
        char[] cArr2 = new char[120];
        int i3 = 0;
        loop0: while (true) {
            if (i3 >= length2) {
                break;
            }
            while (true) {
                char charAt = str.charAt(i3);
                if (charAt < length && iArr[charAt] != 0) {
                    break;
                }
                if (i2 >= cArr2.length) {
                    if (textBuffer == null) {
                        textBuffer = TextBuffer.fromInitial(cArr2);
                    }
                    cArr2 = textBuffer.finishCurrentSegment();
                    i2 = 0;
                }
                int i4 = i2 + 1;
                cArr2[i2] = charAt;
                i3++;
                if (i3 >= length2) {
                    i2 = i4;
                    break loop0;
                }
                i2 = i4;
            }
            i3 = i;
        }
        if (textBuffer == null) {
            return Arrays.copyOfRange(cArr2, 0, i2);
        }
        textBuffer.setCurrentLength(i2);
        return textBuffer.contentsAsArray();
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002a, code lost:
    
        if (r8 != null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002c, code lost:
    
        r8 = _qbuf();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0030, code lost:
    
        r9 = r0 + 1;
        r0 = r13.charAt(r0);
        r10 = r1[r0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0038, code lost:
    
        if (r10 >= 0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003a, code lost:
    
        r0 = _appendNumeric(r0, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0043, code lost:
    
        r10 = r7 + r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0046, code lost:
    
        if (r10 <= r6.length) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0048, code lost:
    
        r10 = r6.length - r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004a, code lost:
    
        if (r10 <= 0) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004c, code lost:
    
        java.lang.System.arraycopy(r8, 0, r6, r7, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004f, code lost:
    
        if (r4 != null) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0051, code lost:
    
        r4 = com.fasterxml.jackson.core.util.TextBuffer.fromInitial(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0055, code lost:
    
        r6 = r4.finishCurrentSegment();
        r0 = r0 - r10;
        java.lang.System.arraycopy(r8, r10, r6, 0, r0);
        r7 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005f, code lost:
    
        java.lang.System.arraycopy(r8, 0, r6, r7, r0);
        r7 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x003f, code lost:
    
        r0 = _appendNamed(r10, r8);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public char[] quoteAsString(CharSequence charSequence) {
        int i;
        if (charSequence instanceof String) {
            return quoteAsString((String) charSequence);
        }
        int[] iArr = CharTypes.get7BitOutputEscapes();
        int length = iArr.length;
        int length2 = charSequence.length();
        TextBuffer textBuffer = null;
        char[] cArr = new char[120];
        char[] cArr2 = null;
        int i2 = 0;
        int i3 = 0;
        loop0: while (true) {
            if (i2 >= length2) {
                break;
            }
            while (true) {
                char charAt = charSequence.charAt(i2);
                if (charAt < length && iArr[charAt] != 0) {
                    break;
                }
                if (i3 >= cArr.length) {
                    if (textBuffer == null) {
                        textBuffer = TextBuffer.fromInitial(cArr);
                    }
                    cArr = textBuffer.finishCurrentSegment();
                    i3 = 0;
                }
                int i4 = i3 + 1;
                cArr[i3] = charAt;
                i2++;
                if (i2 >= length2) {
                    i3 = i4;
                    break loop0;
                }
                i3 = i4;
            }
            i2 = i;
        }
        if (textBuffer == null) {
            return Arrays.copyOfRange(cArr, 0, i3);
        }
        textBuffer.setCurrentLength(i3);
        return textBuffer.contentsAsArray();
    }

    public void quoteAsString(CharSequence charSequence, StringBuilder sb) {
        int _appendNamed;
        int[] iArr = CharTypes.get7BitOutputEscapes();
        int length = iArr.length;
        int length2 = charSequence.length();
        char[] cArr = null;
        int i = 0;
        while (i < length2) {
            do {
                char charAt = charSequence.charAt(i);
                if (charAt >= length || iArr[charAt] == 0) {
                    sb.append(charAt);
                    i++;
                } else {
                    if (cArr == null) {
                        cArr = _qbuf();
                    }
                    int i2 = i + 1;
                    char charAt2 = charSequence.charAt(i);
                    int i3 = iArr[charAt2];
                    if (i3 < 0) {
                        _appendNamed = _appendNumeric(charAt2, cArr);
                    } else {
                        _appendNamed = _appendNamed(i3, cArr);
                    }
                    sb.append(cArr, 0, _appendNamed);
                    i = i2;
                }
            } while (i < length2);
            return;
        }
    }

    public byte[] quoteAsUTF8(String str) {
        int i;
        int i2;
        int length = str.length();
        int i3 = 0;
        ByteArrayBuilder byteArrayBuilder = null;
        byte[] bArr = new byte[200];
        int i4 = 0;
        loop0: while (true) {
            if (i4 >= length) {
                break;
            }
            int[] iArr = CharTypes.get7BitOutputEscapes();
            while (true) {
                char charAt = str.charAt(i4);
                if (charAt > 127 || iArr[charAt] != 0) {
                    break;
                }
                if (i3 >= bArr.length) {
                    if (byteArrayBuilder == null) {
                        byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr, i3);
                    }
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    i3 = 0;
                }
                int i5 = i3 + 1;
                bArr[i3] = (byte) charAt;
                i4++;
                if (i4 >= length) {
                    i3 = i5;
                    break loop0;
                }
                i3 = i5;
            }
            if (byteArrayBuilder == null) {
                byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr, i3);
            }
            if (i3 >= bArr.length) {
                bArr = byteArrayBuilder.finishCurrentSegment();
                i3 = 0;
            }
            int i6 = i4 + 1;
            char charAt2 = str.charAt(i4);
            if (charAt2 <= 127) {
                i3 = _appendByte(charAt2, iArr[charAt2], byteArrayBuilder, i3);
                bArr = byteArrayBuilder.getCurrentSegment();
            } else {
                if (charAt2 <= 2047) {
                    bArr[i3] = (byte) ((charAt2 >> 6) | 192);
                    i2 = (charAt2 & '?') | 128;
                    i = i3 + 1;
                } else if (charAt2 < 55296 || charAt2 > 57343) {
                    int i7 = i3 + 1;
                    bArr[i3] = (byte) ((charAt2 >> '\f') | 224);
                    if (i7 >= bArr.length) {
                        bArr = byteArrayBuilder.finishCurrentSegment();
                        i7 = 0;
                    }
                    i = i7 + 1;
                    bArr[i7] = (byte) (((charAt2 >> 6) & 63) | 128);
                    i2 = (charAt2 & '?') | 128;
                } else {
                    if (charAt2 > 56319) {
                        _illegal(charAt2);
                    }
                    if (i6 >= length) {
                        _illegal(charAt2);
                    }
                    int i8 = i6 + 1;
                    int _convert = _convert(charAt2, str.charAt(i6));
                    if (_convert > 1114111) {
                        _illegal(_convert);
                    }
                    int i9 = i3 + 1;
                    bArr[i3] = (byte) ((_convert >> 18) | DimensionsKt.HDPI);
                    if (i9 >= bArr.length) {
                        bArr = byteArrayBuilder.finishCurrentSegment();
                        i9 = 0;
                    }
                    int i10 = i9 + 1;
                    bArr[i9] = (byte) (((_convert >> 12) & 63) | 128);
                    if (i10 >= bArr.length) {
                        bArr = byteArrayBuilder.finishCurrentSegment();
                        i10 = 0;
                    }
                    bArr[i10] = (byte) (((_convert >> 6) & 63) | 128);
                    i2 = (_convert & 63) | 128;
                    i = i10 + 1;
                    i6 = i8;
                }
                if (i >= bArr.length) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    i = 0;
                }
                bArr[i] = (byte) i2;
                i3 = i + 1;
            }
            i4 = i6;
        }
        if (byteArrayBuilder == null) {
            return Arrays.copyOfRange(bArr, 0, i3);
        }
        return byteArrayBuilder.completeAndCoalesce(i3);
    }

    public byte[] encodeAsUTF8(String str) {
        int i;
        int length = str.length();
        byte[] bArr = new byte[200];
        ByteArrayBuilder byteArrayBuilder = null;
        int length2 = bArr.length;
        int i2 = 0;
        byte[] bArr2 = bArr;
        int i3 = 0;
        loop0: while (true) {
            if (i3 >= length) {
                break;
            }
            int i4 = i3 + 1;
            int charAt = str.charAt(i3);
            while (charAt <= 127) {
                if (i2 >= length2) {
                    if (byteArrayBuilder == null) {
                        byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr2, i2);
                    }
                    bArr2 = byteArrayBuilder.finishCurrentSegment();
                    length2 = bArr2.length;
                    i2 = 0;
                }
                int i5 = i2 + 1;
                bArr2[i2] = (byte) charAt;
                if (i4 >= length) {
                    i2 = i5;
                    break loop0;
                }
                char charAt2 = str.charAt(i4);
                i4++;
                charAt = charAt2;
                i2 = i5;
            }
            if (byteArrayBuilder == null) {
                byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr2, i2);
            }
            if (i2 >= length2) {
                bArr2 = byteArrayBuilder.finishCurrentSegment();
                length2 = bArr2.length;
                i2 = 0;
            }
            if (charAt < 2048) {
                bArr2[i2] = (byte) ((charAt >> 6) | 192);
                i = i2 + 1;
            } else if (charAt < 55296 || charAt > 57343) {
                int i6 = i2 + 1;
                bArr2[i2] = (byte) ((charAt >> 12) | 224);
                if (i6 >= length2) {
                    bArr2 = byteArrayBuilder.finishCurrentSegment();
                    length2 = bArr2.length;
                    i6 = 0;
                }
                i = i6 + 1;
                bArr2[i6] = (byte) (((charAt >> 6) & 63) | 128);
            } else {
                if (charAt > 56319) {
                    _illegal(charAt);
                }
                if (i4 >= length) {
                    _illegal(charAt);
                }
                int i7 = i4 + 1;
                charAt = _convert(charAt, str.charAt(i4));
                if (charAt > 1114111) {
                    _illegal(charAt);
                }
                int i8 = i2 + 1;
                bArr2[i2] = (byte) ((charAt >> 18) | DimensionsKt.HDPI);
                if (i8 >= length2) {
                    bArr2 = byteArrayBuilder.finishCurrentSegment();
                    length2 = bArr2.length;
                    i8 = 0;
                }
                int i9 = i8 + 1;
                bArr2[i8] = (byte) (((charAt >> 12) & 63) | 128);
                if (i9 >= length2) {
                    bArr2 = byteArrayBuilder.finishCurrentSegment();
                    length2 = bArr2.length;
                    i9 = 0;
                }
                bArr2[i9] = (byte) (((charAt >> 6) & 63) | 128);
                i = i9 + 1;
                i4 = i7;
            }
            if (i >= length2) {
                bArr2 = byteArrayBuilder.finishCurrentSegment();
                length2 = bArr2.length;
                i = 0;
            }
            bArr2[i] = (byte) ((charAt & 63) | 128);
            i3 = i4;
            i2 = i + 1;
        }
        if (byteArrayBuilder == null) {
            return Arrays.copyOfRange(bArr2, 0, i2);
        }
        return byteArrayBuilder.completeAndCoalesce(i2);
    }

    public byte[] encodeAsUTF8(CharSequence charSequence) {
        int i;
        int length = charSequence.length();
        byte[] bArr = new byte[200];
        ByteArrayBuilder byteArrayBuilder = null;
        int length2 = bArr.length;
        int i2 = 0;
        byte[] bArr2 = bArr;
        int i3 = 0;
        loop0: while (true) {
            if (i3 >= length) {
                break;
            }
            int i4 = i3 + 1;
            int charAt = charSequence.charAt(i3);
            while (charAt <= 127) {
                if (i2 >= length2) {
                    if (byteArrayBuilder == null) {
                        byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr2, i2);
                    }
                    bArr2 = byteArrayBuilder.finishCurrentSegment();
                    length2 = bArr2.length;
                    i2 = 0;
                }
                int i5 = i2 + 1;
                bArr2[i2] = (byte) charAt;
                if (i4 >= length) {
                    i2 = i5;
                    break loop0;
                }
                char charAt2 = charSequence.charAt(i4);
                i4++;
                charAt = charAt2;
                i2 = i5;
            }
            if (byteArrayBuilder == null) {
                byteArrayBuilder = ByteArrayBuilder.fromInitial(bArr2, i2);
            }
            if (i2 >= length2) {
                bArr2 = byteArrayBuilder.finishCurrentSegment();
                length2 = bArr2.length;
                i2 = 0;
            }
            if (charAt < 2048) {
                bArr2[i2] = (byte) ((charAt >> 6) | 192);
                i = i2 + 1;
            } else if (charAt < 55296 || charAt > 57343) {
                int i6 = i2 + 1;
                bArr2[i2] = (byte) ((charAt >> 12) | 224);
                if (i6 >= length2) {
                    bArr2 = byteArrayBuilder.finishCurrentSegment();
                    length2 = bArr2.length;
                    i6 = 0;
                }
                i = i6 + 1;
                bArr2[i6] = (byte) (((charAt >> 6) & 63) | 128);
            } else {
                if (charAt > 56319) {
                    _illegal(charAt);
                }
                if (i4 >= length) {
                    _illegal(charAt);
                }
                int i7 = i4 + 1;
                charAt = _convert(charAt, charSequence.charAt(i4));
                if (charAt > 1114111) {
                    _illegal(charAt);
                }
                int i8 = i2 + 1;
                bArr2[i2] = (byte) ((charAt >> 18) | DimensionsKt.HDPI);
                if (i8 >= length2) {
                    bArr2 = byteArrayBuilder.finishCurrentSegment();
                    length2 = bArr2.length;
                    i8 = 0;
                }
                int i9 = i8 + 1;
                bArr2[i8] = (byte) (((charAt >> 12) & 63) | 128);
                if (i9 >= length2) {
                    bArr2 = byteArrayBuilder.finishCurrentSegment();
                    length2 = bArr2.length;
                    i9 = 0;
                }
                bArr2[i9] = (byte) (((charAt >> 6) & 63) | 128);
                i = i9 + 1;
                i4 = i7;
            }
            if (i >= length2) {
                bArr2 = byteArrayBuilder.finishCurrentSegment();
                length2 = bArr2.length;
                i = 0;
            }
            bArr2[i] = (byte) ((charAt & 63) | 128);
            i3 = i4;
            i2 = i + 1;
        }
        if (byteArrayBuilder == null) {
            return Arrays.copyOfRange(bArr2, 0, i2);
        }
        return byteArrayBuilder.completeAndCoalesce(i2);
    }

    private int _appendNumeric(int i, char[] cArr) {
        cArr[1] = 'u';
        char[] cArr2 = f1255HC;
        cArr[4] = cArr2[i >> 4];
        cArr[5] = cArr2[i & 15];
        return 6;
    }

    private int _appendNamed(int i, char[] cArr) {
        cArr[1] = (char) i;
        return 2;
    }

    private int _appendByte(int i, int i2, ByteArrayBuilder byteArrayBuilder, int i3) {
        byteArrayBuilder.setCurrentSegmentLength(i3);
        byteArrayBuilder.append(92);
        if (i2 < 0) {
            byteArrayBuilder.append(117);
            if (i > 255) {
                int i4 = i >> 8;
                byteArrayBuilder.append(f1254HB[i4 >> 4]);
                byteArrayBuilder.append(f1254HB[i4 & 15]);
                i &= 255;
            } else {
                byteArrayBuilder.append(48);
                byteArrayBuilder.append(48);
            }
            byteArrayBuilder.append(f1254HB[i >> 4]);
            byteArrayBuilder.append(f1254HB[i & 15]);
        } else {
            byteArrayBuilder.append((byte) i2);
        }
        return byteArrayBuilder.getCurrentSegmentLength();
    }

    private static int _convert(int i, int i2) {
        if (i2 >= 56320 && i2 <= 57343) {
            return ((i - 55296) << 10) + 65536 + (i2 - 56320);
        }
        throw new IllegalArgumentException("Broken surrogate pair: first char 0x" + Integer.toHexString(i) + ", second 0x" + Integer.toHexString(i2) + "; illegal combination");
    }

    private static void _illegal(int i) {
        throw new IllegalArgumentException(UTF8Writer.illegalSurrogateDesc(i));
    }
}
