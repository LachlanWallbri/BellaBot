package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.pdf417.PDF417ResultMetadata;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
final class DecodedBitStreamParser {

    /* renamed from: AL */
    private static final int f2113AL = 28;

    /* renamed from: AS */
    private static final int f2114AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final BigInteger[] EXP900;

    /* renamed from: LL */
    private static final int f2115LL = 27;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_ADDRESSEE = 4;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_CHECKSUM = 6;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_FILE_NAME = 0;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_FILE_SIZE = 5;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_SEGMENT_COUNT = 1;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_SENDER = 3;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_TIME_STAMP = 2;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;

    /* renamed from: ML */
    private static final int f2116ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMBER_OF_SEQUENCE_CODEWORDS = 2;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;

    /* renamed from: PL */
    private static final int f2117PL = 25;

    /* renamed from: PS */
    private static final int f2118PS = 29;
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;
    private static final char[] PUNCT_CHARS = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();
    private static final char[] MIXED_CHARS = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* loaded from: classes.dex */
    public enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        EXP900 = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger valueOf = BigInteger.valueOf(900L);
        EXP900[1] = valueOf;
        int i = 2;
        while (true) {
            BigInteger[] bigIntegerArr2 = EXP900;
            if (i >= bigIntegerArr2.length) {
                return;
            }
            bigIntegerArr2[i] = bigIntegerArr2[i - 1].multiply(valueOf);
            i++;
        }
    }

    private DecodedBitStreamParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:6:0x001b. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static DecoderResult decode(int[] iArr, String str) throws FormatException {
        int i;
        StringBuilder sb = new StringBuilder(iArr.length << 1);
        Charset charset = StandardCharsets.ISO_8859_1;
        int i2 = iArr[1];
        PDF417ResultMetadata pDF417ResultMetadata = new PDF417ResultMetadata();
        int i3 = 2;
        while (i3 < iArr[0]) {
            if (i2 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                switch (i2) {
                    case 900:
                        i = textCompaction(iArr, i3, sb);
                        break;
                    case 901:
                        i = byteCompaction(i2, iArr, charset, i3, sb);
                        break;
                    case 902:
                        i = numericCompaction(iArr, i3, sb);
                        break;
                    default:
                        switch (i2) {
                            case MACRO_PDF417_TERMINATOR /* 922 */:
                            case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /* 923 */:
                                throw FormatException.getFormatInstance();
                            case BYTE_COMPACTION_MODE_LATCH_6 /* 924 */:
                                break;
                            case ECI_USER_DEFINED /* 925 */:
                                i = i3 + 1;
                                break;
                            case ECI_GENERAL_PURPOSE /* 926 */:
                                i = i3 + 2;
                                break;
                            case ECI_CHARSET /* 927 */:
                                i = i3 + 1;
                                charset = Charset.forName(CharacterSetECI.getCharacterSetECIByValue(iArr[i3]).name());
                                break;
                            case 928:
                                i = decodeMacroBlock(iArr, i3, pDF417ResultMetadata);
                                break;
                            default:
                                i = textCompaction(iArr, i3 - 1, sb);
                                break;
                        }
                }
            } else {
                i = i3 + 1;
                sb.append((char) iArr[i3]);
            }
            if (i < iArr.length) {
                i3 = i + 1;
                i2 = iArr[i];
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (sb.length() == 0) {
            throw FormatException.getFormatInstance();
        }
        DecoderResult decoderResult = new DecoderResult(null, sb.toString(), null, str);
        decoderResult.setOther(pDF417ResultMetadata);
        return decoderResult;
    }

    static int decodeMacroBlock(int[] iArr, int i, PDF417ResultMetadata pDF417ResultMetadata) throws FormatException {
        if (i + 2 > iArr[0]) {
            throw FormatException.getFormatInstance();
        }
        int[] iArr2 = new int[2];
        int i2 = 0;
        while (i2 < 2) {
            iArr2[i2] = iArr[i];
            i2++;
            i++;
        }
        pDF417ResultMetadata.setSegmentIndex(Integer.parseInt(decodeBase900toBase10(iArr2, 2)));
        StringBuilder sb = new StringBuilder();
        int textCompaction = textCompaction(iArr, i, sb);
        pDF417ResultMetadata.setFileId(sb.toString());
        int i3 = iArr[textCompaction] == BEGIN_MACRO_PDF417_OPTIONAL_FIELD ? textCompaction + 1 : -1;
        while (textCompaction < iArr[0]) {
            int i4 = iArr[textCompaction];
            if (i4 == MACRO_PDF417_TERMINATOR) {
                textCompaction++;
                pDF417ResultMetadata.setLastSegment(true);
            } else if (i4 == BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
                int i5 = textCompaction + 1;
                switch (iArr[i5]) {
                    case 0:
                        StringBuilder sb2 = new StringBuilder();
                        textCompaction = textCompaction(iArr, i5 + 1, sb2);
                        pDF417ResultMetadata.setFileName(sb2.toString());
                        break;
                    case 1:
                        StringBuilder sb3 = new StringBuilder();
                        textCompaction = numericCompaction(iArr, i5 + 1, sb3);
                        pDF417ResultMetadata.setSegmentCount(Integer.parseInt(sb3.toString()));
                        break;
                    case 2:
                        StringBuilder sb4 = new StringBuilder();
                        textCompaction = numericCompaction(iArr, i5 + 1, sb4);
                        pDF417ResultMetadata.setTimestamp(Long.parseLong(sb4.toString()));
                        break;
                    case 3:
                        StringBuilder sb5 = new StringBuilder();
                        textCompaction = textCompaction(iArr, i5 + 1, sb5);
                        pDF417ResultMetadata.setSender(sb5.toString());
                        break;
                    case 4:
                        StringBuilder sb6 = new StringBuilder();
                        textCompaction = textCompaction(iArr, i5 + 1, sb6);
                        pDF417ResultMetadata.setAddressee(sb6.toString());
                        break;
                    case 5:
                        StringBuilder sb7 = new StringBuilder();
                        textCompaction = numericCompaction(iArr, i5 + 1, sb7);
                        pDF417ResultMetadata.setFileSize(Long.parseLong(sb7.toString()));
                        break;
                    case 6:
                        StringBuilder sb8 = new StringBuilder();
                        textCompaction = numericCompaction(iArr, i5 + 1, sb8);
                        pDF417ResultMetadata.setChecksum(Integer.parseInt(sb8.toString()));
                        break;
                    default:
                        throw FormatException.getFormatInstance();
                }
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (i3 != -1) {
            int i6 = textCompaction - i3;
            if (pDF417ResultMetadata.isLastSegment()) {
                i6--;
            }
            pDF417ResultMetadata.setOptionalData(Arrays.copyOfRange(iArr, i3, i6 + i3));
        }
        return textCompaction;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:16:0x0034. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:17:0x0037. Please report as an issue. */
    private static int textCompaction(int[] iArr, int i, StringBuilder sb) {
        int[] iArr2 = new int[(iArr[0] - i) << 1];
        int[] iArr3 = new int[(iArr[0] - i) << 1];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i4 < 900) {
                iArr2[i2] = i4 / 30;
                iArr2[i2 + 1] = i4 % 30;
                i2 += 2;
            } else if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                if (i4 != 928) {
                    switch (i4) {
                        case 900:
                            iArr2[i2] = 900;
                            i2++;
                            break;
                        case 901:
                        case 902:
                            break;
                        default:
                            switch (i4) {
                            }
                    }
                }
                i = i3 - 1;
                z = true;
            } else {
                iArr2[i2] = MODE_SHIFT_TO_BYTE_COMPACTION_MODE;
                i = i3 + 1;
                iArr3[i2] = iArr[i3];
                i2++;
            }
            i = i3;
        }
        decodeTextCompaction(iArr2, iArr3, i2, sb);
        return i;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:38:0x007f. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x001c. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:51:0x00a5. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:61:0x00c6. Please report as an issue. */
    private static void decodeTextCompaction(int[] iArr, int[] iArr2, int i, StringBuilder sb) {
        Mode mode;
        int i2;
        Mode mode2 = Mode.ALPHA;
        Mode mode3 = Mode.ALPHA;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = iArr[i3];
            char c = ' ';
            switch (C35161.f2119x45bba1d[mode2.ordinal()]) {
                case 1:
                    if (i4 < 26) {
                        i2 = i4 + 65;
                        c = (char) i2;
                        break;
                    } else {
                        if (i4 == 900) {
                            mode2 = Mode.ALPHA;
                        } else if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                            switch (i4) {
                                case 27:
                                    mode2 = Mode.LOWER;
                                    break;
                                case 28:
                                    mode2 = Mode.MIXED;
                                    break;
                                case 29:
                                    mode = Mode.PUNCT_SHIFT;
                                    c = 0;
                                    Mode mode4 = mode;
                                    mode3 = mode2;
                                    mode2 = mode4;
                                    break;
                            }
                        } else {
                            sb.append((char) iArr2[i3]);
                        }
                        c = 0;
                        break;
                    }
                case 2:
                    if (i4 < 26) {
                        i2 = i4 + 97;
                        c = (char) i2;
                        break;
                    } else {
                        if (i4 == 900) {
                            mode2 = Mode.ALPHA;
                        } else if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                            switch (i4) {
                                case 27:
                                    mode = Mode.ALPHA_SHIFT;
                                    c = 0;
                                    Mode mode42 = mode;
                                    mode3 = mode2;
                                    mode2 = mode42;
                                    break;
                                case 28:
                                    mode2 = Mode.MIXED;
                                    break;
                                case 29:
                                    mode = Mode.PUNCT_SHIFT;
                                    c = 0;
                                    Mode mode422 = mode;
                                    mode3 = mode2;
                                    mode2 = mode422;
                                    break;
                            }
                        } else {
                            sb.append((char) iArr2[i3]);
                        }
                        c = 0;
                        break;
                    }
                case 3:
                    if (i4 < 25) {
                        c = MIXED_CHARS[i4];
                        break;
                    } else {
                        if (i4 == 900) {
                            mode2 = Mode.ALPHA;
                        } else if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                            switch (i4) {
                                case 25:
                                    mode2 = Mode.PUNCT;
                                    break;
                                case 27:
                                    mode2 = Mode.LOWER;
                                    break;
                                case 28:
                                    mode2 = Mode.ALPHA;
                                    break;
                                case 29:
                                    mode = Mode.PUNCT_SHIFT;
                                    c = 0;
                                    Mode mode4222 = mode;
                                    mode3 = mode2;
                                    mode2 = mode4222;
                                    break;
                            }
                        } else {
                            sb.append((char) iArr2[i3]);
                        }
                        c = 0;
                        break;
                    }
                case 4:
                    if (i4 < 29) {
                        c = PUNCT_CHARS[i4];
                        break;
                    } else {
                        if (i4 == 29) {
                            mode2 = Mode.ALPHA;
                        } else if (i4 == 900) {
                            mode2 = Mode.ALPHA;
                        } else if (i4 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                            sb.append((char) iArr2[i3]);
                        }
                        c = 0;
                        break;
                    }
                case 5:
                    if (i4 < 26) {
                        c = (char) (i4 + 65);
                    } else if (i4 != 26) {
                        if (i4 == 900) {
                            mode2 = Mode.ALPHA;
                            c = 0;
                            break;
                        }
                        mode2 = mode3;
                        c = 0;
                    }
                    mode2 = mode3;
                    break;
                case 6:
                    if (i4 < 29) {
                        c = PUNCT_CHARS[i4];
                        mode2 = mode3;
                        break;
                    } else {
                        if (i4 == 29) {
                            mode2 = Mode.ALPHA;
                        } else if (i4 != 900) {
                            if (i4 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                sb.append((char) iArr2[i3]);
                            }
                            mode2 = mode3;
                        } else {
                            mode2 = Mode.ALPHA;
                        }
                        c = 0;
                        break;
                    }
                default:
                    c = 0;
                    break;
            }
            if (c != 0) {
                sb.append(c);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* renamed from: com.google.zxing.pdf417.decoder.DecodedBitStreamParser$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C35161 {

        /* renamed from: $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode */
        static final /* synthetic */ int[] f2119x45bba1d;

        static {
            int[] iArr = new int[Mode.values().length];
            f2119x45bba1d = iArr;
            try {
                iArr[Mode.ALPHA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2119x45bba1d[Mode.LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2119x45bba1d[Mode.MIXED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2119x45bba1d[Mode.PUNCT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2119x45bba1d[Mode.ALPHA_SHIFT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2119x45bba1d[Mode.PUNCT_SHIFT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:33:0x0036. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:45:0x0077. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:46:0x007a. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0046 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0021 A[ADDED_TO_REGION, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00a9 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static int byteCompaction(int i, int[] iArr, Charset charset, int i2, StringBuilder sb) {
        int i3;
        int i4;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        long j = 900;
        int i5 = 6;
        if (i == 901) {
            int[] iArr2 = new int[6];
            int i6 = i2 + 1;
            int i7 = iArr[i2];
            boolean z = false;
            while (true) {
                i3 = 0;
                long j2 = 0;
                while (i6 < iArr[0] && !z) {
                    int i8 = i3 + 1;
                    iArr2[i3] = i7;
                    j2 = (j2 * j) + i7;
                    int i9 = i6 + 1;
                    i7 = iArr[i6];
                    if (i7 == 928) {
                        switch (i7) {
                            case 900:
                            case 901:
                            case 902:
                                break;
                            default:
                                switch (i7) {
                                    case MACRO_PDF417_TERMINATOR /* 922 */:
                                    case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /* 923 */:
                                    case BYTE_COMPACTION_MODE_LATCH_6 /* 924 */:
                                        break;
                                    default:
                                        if (i8 % 5 != 0 || i8 <= 0) {
                                            i7 = i7;
                                            i6 = i9;
                                            i3 = i8;
                                            j = 900;
                                            i5 = 6;
                                        } else {
                                            int i10 = 0;
                                            while (i10 < i5) {
                                                byteArrayOutputStream.write((byte) (j2 >> ((5 - i10) * 8)));
                                                i10++;
                                                i7 = i7;
                                                i5 = 6;
                                            }
                                            i6 = i9;
                                            j = 900;
                                        }
                                }
                        }
                        while (i6 < iArr[0]) {
                            int i82 = i3 + 1;
                            iArr2[i3] = i7;
                            j2 = (j2 * j) + i7;
                            int i92 = i6 + 1;
                            i7 = iArr[i6];
                            if (i7 == 928) {
                            }
                        }
                    }
                    i6 = i92 - 1;
                    i7 = i7;
                    i3 = i82;
                    j = 900;
                    i5 = 6;
                    z = true;
                }
            }
            if (i6 == iArr[0] && i7 < 900) {
                iArr2[i3] = i7;
                i3++;
            }
            for (int i11 = 0; i11 < i3; i11++) {
                byteArrayOutputStream.write((byte) iArr2[i11]);
            }
            i4 = i6;
        } else if (i != BYTE_COMPACTION_MODE_LATCH_6) {
            i4 = i2;
        } else {
            i4 = i2;
            boolean z2 = false;
            while (true) {
                int i12 = 0;
                long j3 = 0;
                while (i4 < iArr[0] && !z2) {
                    int i13 = i4 + 1;
                    int i14 = iArr[i4];
                    if (i14 < 900) {
                        i12++;
                        j3 = (j3 * 900) + i14;
                    } else {
                        if (i14 != 928) {
                            switch (i14) {
                                default:
                                    switch (i14) {
                                    }
                                case 900:
                                case 901:
                                case 902:
                                    i4 = i13 - 1;
                                    z2 = true;
                                    break;
                            }
                            if (i12 % 5 == 0 && i12 > 0) {
                                for (int i15 = 0; i15 < 6; i15++) {
                                    byteArrayOutputStream.write((byte) (j3 >> ((5 - i15) * 8)));
                                }
                            }
                        }
                        i4 = i13 - 1;
                        z2 = true;
                        if (i12 % 5 == 0) {
                        }
                    }
                    i4 = i13;
                    if (i12 % 5 == 0) {
                    }
                }
            }
        }
        sb.append(new String(byteArrayOutputStream.toByteArray(), charset));
        return i4;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:27:0x002a. Please report as an issue. */
    private static int numericCompaction(int[] iArr, int i, StringBuilder sb) throws FormatException {
        int[] iArr2 = new int[15];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i3 == iArr[0]) {
                z = true;
            }
            if (i4 < 900) {
                iArr2[i2] = i4;
                i2++;
            } else {
                if (i4 != 900 && i4 != 901 && i4 != 928) {
                    switch (i4) {
                    }
                }
                i3--;
                z = true;
            }
            if ((i2 % 15 == 0 || i4 == 902 || z) && i2 > 0) {
                sb.append(decodeBase900toBase10(iArr2, i2));
                i2 = 0;
            }
            i = i3;
        }
        return i;
    }

    private static String decodeBase900toBase10(int[] iArr, int i) throws FormatException {
        BigInteger bigInteger = BigInteger.ZERO;
        for (int i2 = 0; i2 < i; i2++) {
            bigInteger = bigInteger.add(EXP900[(i - i2) - 1].multiply(BigInteger.valueOf(iArr[i2])));
        }
        String bigInteger2 = bigInteger.toString();
        if (bigInteger2.charAt(0) != '1') {
            throw FormatException.getFormatInstance();
        }
        return bigInteger2.substring(1);
    }
}
