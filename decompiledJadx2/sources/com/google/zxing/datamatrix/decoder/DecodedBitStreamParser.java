package com.google.zxing.datamatrix.decoder;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.common.base.Ascii;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.DecoderResult;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.text.Typography;
import org.apache.commons.codec.language.Soundex;
import org.apache.commons.io.FilenameUtils;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import org.jetbrains.anko.DimensionsKt;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
final class DecodedBitStreamParser {
    private static final char[] C40_BASIC_SET_CHARS = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', Matrix.MATRIX_TYPE_RANDOM_LT, 'M', 'N', 'O', 'P', 'Q', Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'S', 'T', Matrix.MATRIX_TYPE_RANDOM_UT, 'V', 'W', 'X', 'Y', Matrix.MATRIX_TYPE_ZERO};
    private static final char[] C40_SHIFT2_SET_CHARS;
    private static final char[] TEXT_BASIC_SET_CHARS;
    private static final char[] TEXT_SHIFT2_SET_CHARS;
    private static final char[] TEXT_SHIFT3_SET_CHARS;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* loaded from: classes.dex */
    public enum Mode {
        PAD_ENCODE,
        ASCII_ENCODE,
        C40_ENCODE,
        TEXT_ENCODE,
        ANSIX12_ENCODE,
        EDIFACT_ENCODE,
        BASE256_ENCODE
    }

    static {
        char[] cArr = {'!', '\"', '#', Typography.dollar, '%', Typography.amp, '\'', '(', ')', '*', '+', ',', Soundex.SILENT_MARKER, FilenameUtils.EXTENSION_SEPARATOR, '/', ':', ';', Typography.less, '=', Typography.greater, '?', '@', '[', '\\', ']', '^', '_'};
        C40_SHIFT2_SET_CHARS = cArr;
        TEXT_BASIC_SET_CHARS = new char[]{'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        TEXT_SHIFT2_SET_CHARS = cArr;
        TEXT_SHIFT3_SET_CHARS = new char[]{'`', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', Matrix.MATRIX_TYPE_RANDOM_LT, 'M', 'N', 'O', 'P', 'Q', Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'S', 'T', Matrix.MATRIX_TYPE_RANDOM_UT, 'V', 'W', 'X', 'Y', Matrix.MATRIX_TYPE_ZERO, '{', '|', '}', '~', Ascii.MAX};
    }

    private DecodedBitStreamParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DecoderResult decode(byte[] bArr) throws FormatException {
        BitSource bitSource = new BitSource(bArr);
        StringBuilder sb = new StringBuilder(100);
        StringBuilder sb2 = new StringBuilder(0);
        ArrayList arrayList = new ArrayList(1);
        Mode mode = Mode.ASCII_ENCODE;
        do {
            if (mode == Mode.ASCII_ENCODE) {
                mode = decodeAsciiSegment(bitSource, sb, sb2);
            } else {
                int i = C35111.f2102xb73eb560[mode.ordinal()];
                if (i == 1) {
                    decodeC40Segment(bitSource, sb);
                } else if (i == 2) {
                    decodeTextSegment(bitSource, sb);
                } else if (i == 3) {
                    decodeAnsiX12Segment(bitSource, sb);
                } else if (i == 4) {
                    decodeEdifactSegment(bitSource, sb);
                } else if (i == 5) {
                    decodeBase256Segment(bitSource, sb, arrayList);
                } else {
                    throw FormatException.getFormatInstance();
                }
                mode = Mode.ASCII_ENCODE;
            }
            if (mode == Mode.PAD_ENCODE) {
                break;
            }
        } while (bitSource.available() > 0);
        if (sb2.length() > 0) {
            sb.append((CharSequence) sb2);
        }
        String sb3 = sb.toString();
        if (arrayList.isEmpty()) {
            arrayList = null;
        }
        return new DecoderResult(bArr, sb3, arrayList, null);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* renamed from: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C35111 {

        /* renamed from: $SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode */
        static final /* synthetic */ int[] f2102xb73eb560;

        static {
            int[] iArr = new int[Mode.values().length];
            f2102xb73eb560 = iArr;
            try {
                iArr[Mode.C40_ENCODE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2102xb73eb560[Mode.TEXT_ENCODE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2102xb73eb560[Mode.ANSIX12_ENCODE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2102xb73eb560[Mode.EDIFACT_ENCODE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2102xb73eb560[Mode.BASE256_ENCODE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:22:0x0037. Please report as an issue. */
    private static Mode decodeAsciiSegment(BitSource bitSource, StringBuilder sb, StringBuilder sb2) throws FormatException {
        boolean z = false;
        do {
            int readBits = bitSource.readBits(8);
            if (readBits == 0) {
                throw FormatException.getFormatInstance();
            }
            if (readBits > 128) {
                if (readBits != 129) {
                    if (readBits <= 229) {
                        int i = readBits - 130;
                        if (i < 10) {
                            sb.append('0');
                        }
                        sb.append(i);
                    } else {
                        switch (readBits) {
                            case 230:
                                return Mode.C40_ENCODE;
                            case 231:
                                return Mode.BASE256_ENCODE;
                            case 232:
                                sb.append((char) 29);
                                break;
                            case 233:
                            case 234:
                            case 241:
                                break;
                            case 235:
                                z = true;
                                break;
                            case 236:
                                sb.append("[)>\u001e05\u001d");
                                sb2.insert(0, "\u001e\u0004");
                                break;
                            case 237:
                                sb.append("[)>\u001e06\u001d");
                                sb2.insert(0, "\u001e\u0004");
                                break;
                            case 238:
                                return Mode.ANSIX12_ENCODE;
                            case 239:
                                return Mode.TEXT_ENCODE;
                            case DimensionsKt.HDPI /* 240 */:
                                return Mode.EDIFACT_ENCODE;
                            default:
                                if (readBits != 254 || bitSource.available() != 0) {
                                    throw FormatException.getFormatInstance();
                                }
                                break;
                        }
                    }
                } else {
                    return Mode.PAD_ENCODE;
                }
            } else {
                if (z) {
                    readBits += 128;
                }
                sb.append((char) (readBits - 1));
                return Mode.ASCII_ENCODE;
            }
        } while (bitSource.available() > 0);
        return Mode.ASCII_ENCODE;
    }

    private static void decodeC40Segment(BitSource bitSource, StringBuilder sb) throws FormatException {
        int readBits;
        int[] iArr = new int[3];
        boolean z = false;
        int i = 0;
        while (bitSource.available() != 8 && (readBits = bitSource.readBits(8)) != 254) {
            parseTwoBytes(readBits, bitSource.readBits(8), iArr);
            for (int i2 = 0; i2 < 3; i2++) {
                int i3 = iArr[i2];
                if (i != 0) {
                    if (i != 1) {
                        if (i == 2) {
                            char[] cArr = C40_SHIFT2_SET_CHARS;
                            if (i3 < cArr.length) {
                                char c = cArr[i3];
                                if (z) {
                                    sb.append((char) (c + 128));
                                    z = false;
                                } else {
                                    sb.append(c);
                                }
                            } else if (i3 == 27) {
                                sb.append((char) 29);
                            } else {
                                if (i3 != 30) {
                                    throw FormatException.getFormatInstance();
                                }
                                z = true;
                            }
                            i = 0;
                        } else {
                            if (i != 3) {
                                throw FormatException.getFormatInstance();
                            }
                            if (z) {
                                sb.append((char) (i3 + 224));
                                z = false;
                                i = 0;
                            } else {
                                sb.append((char) (i3 + 96));
                                i = 0;
                            }
                        }
                    } else if (z) {
                        sb.append((char) (i3 + 128));
                        z = false;
                        i = 0;
                    } else {
                        sb.append((char) i3);
                        i = 0;
                    }
                } else if (i3 < 3) {
                    i = i3 + 1;
                } else {
                    char[] cArr2 = C40_BASIC_SET_CHARS;
                    if (i3 < cArr2.length) {
                        char c2 = cArr2[i3];
                        if (z) {
                            sb.append((char) (c2 + 128));
                            z = false;
                        } else {
                            sb.append(c2);
                        }
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                }
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    private static void decodeTextSegment(BitSource bitSource, StringBuilder sb) throws FormatException {
        int readBits;
        int[] iArr = new int[3];
        boolean z = false;
        int i = 0;
        while (bitSource.available() != 8 && (readBits = bitSource.readBits(8)) != 254) {
            parseTwoBytes(readBits, bitSource.readBits(8), iArr);
            for (int i2 = 0; i2 < 3; i2++) {
                int i3 = iArr[i2];
                if (i != 0) {
                    if (i != 1) {
                        if (i == 2) {
                            char[] cArr = TEXT_SHIFT2_SET_CHARS;
                            if (i3 < cArr.length) {
                                char c = cArr[i3];
                                if (z) {
                                    sb.append((char) (c + 128));
                                    z = false;
                                } else {
                                    sb.append(c);
                                }
                            } else if (i3 == 27) {
                                sb.append((char) 29);
                            } else {
                                if (i3 != 30) {
                                    throw FormatException.getFormatInstance();
                                }
                                z = true;
                            }
                            i = 0;
                        } else if (i == 3) {
                            char[] cArr2 = TEXT_SHIFT3_SET_CHARS;
                            if (i3 < cArr2.length) {
                                char c2 = cArr2[i3];
                                if (z) {
                                    sb.append((char) (c2 + 128));
                                    z = false;
                                    i = 0;
                                } else {
                                    sb.append(c2);
                                    i = 0;
                                }
                            } else {
                                throw FormatException.getFormatInstance();
                            }
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    } else if (z) {
                        sb.append((char) (i3 + 128));
                        z = false;
                        i = 0;
                    } else {
                        sb.append((char) i3);
                        i = 0;
                    }
                } else if (i3 < 3) {
                    i = i3 + 1;
                } else {
                    char[] cArr3 = TEXT_BASIC_SET_CHARS;
                    if (i3 < cArr3.length) {
                        char c3 = cArr3[i3];
                        if (z) {
                            sb.append((char) (c3 + 128));
                            z = false;
                        } else {
                            sb.append(c3);
                        }
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                }
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    private static void decodeAnsiX12Segment(BitSource bitSource, StringBuilder sb) throws FormatException {
        int readBits;
        int[] iArr = new int[3];
        while (bitSource.available() != 8 && (readBits = bitSource.readBits(8)) != 254) {
            parseTwoBytes(readBits, bitSource.readBits(8), iArr);
            for (int i = 0; i < 3; i++) {
                int i2 = iArr[i];
                if (i2 == 0) {
                    sb.append('\r');
                } else if (i2 == 1) {
                    sb.append('*');
                } else if (i2 == 2) {
                    sb.append(Typography.greater);
                } else if (i2 == 3) {
                    sb.append(' ');
                } else if (i2 < 14) {
                    sb.append((char) (i2 + 44));
                } else if (i2 < 40) {
                    sb.append((char) (i2 + 51));
                } else {
                    throw FormatException.getFormatInstance();
                }
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    private static void parseTwoBytes(int i, int i2, int[] iArr) {
        int i3 = ((i << 8) + i2) - 1;
        int i4 = i3 / 1600;
        iArr[0] = i4;
        int i5 = i3 - (i4 * 1600);
        int i6 = i5 / 40;
        iArr[1] = i6;
        iArr[2] = i5 - (i6 * 40);
    }

    private static void decodeEdifactSegment(BitSource bitSource, StringBuilder sb) {
        while (bitSource.available() > 16) {
            for (int i = 0; i < 4; i++) {
                int readBits = bitSource.readBits(6);
                if (readBits == 31) {
                    int bitOffset = 8 - bitSource.getBitOffset();
                    if (bitOffset != 8) {
                        bitSource.readBits(bitOffset);
                        return;
                    }
                    return;
                }
                if ((readBits & 32) == 0) {
                    readBits |= 64;
                }
                sb.append((char) readBits);
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    private static void decodeBase256Segment(BitSource bitSource, StringBuilder sb, Collection<byte[]> collection) throws FormatException {
        int byteOffset = bitSource.getByteOffset() + 1;
        int i = byteOffset + 1;
        int unrandomize255State = unrandomize255State(bitSource.readBits(8), byteOffset);
        if (unrandomize255State == 0) {
            unrandomize255State = bitSource.available() / 8;
        } else if (unrandomize255State >= 250) {
            unrandomize255State = ((unrandomize255State - 249) * ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION) + unrandomize255State(bitSource.readBits(8), i);
            i++;
        }
        if (unrandomize255State < 0) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[unrandomize255State];
        int i2 = 0;
        while (i2 < unrandomize255State) {
            if (bitSource.available() < 8) {
                throw FormatException.getFormatInstance();
            }
            bArr[i2] = (byte) unrandomize255State(bitSource.readBits(8), i);
            i2++;
            i++;
        }
        collection.add(bArr);
        try {
            sb.append(new String(bArr, "ISO8859_1"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Platform does not support required encoding: ".concat(String.valueOf(e)));
        }
    }

    private static int unrandomize255State(int i, int i2) {
        int i3 = i - (((i2 * 149) % 255) + 1);
        return i3 >= 0 ? i3 : i3 + 256;
    }
}
