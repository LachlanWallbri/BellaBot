package org.apache.commons.compress.harmony.pack200;

import com.pudutech.gatecontrollerlib.GateControllerMsg;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.anko.DimensionsKt;

/* loaded from: classes9.dex */
public class CodecEncoding {
    private static final BHSDCodec[] canonicalCodec = {null, new BHSDCodec(1, 256), new BHSDCodec(1, 256, 1), new BHSDCodec(1, 256, 0, 1), new BHSDCodec(1, 256, 1, 1), new BHSDCodec(2, 256), new BHSDCodec(2, 256, 1), new BHSDCodec(2, 256, 0, 1), new BHSDCodec(2, 256, 1, 1), new BHSDCodec(3, 256), new BHSDCodec(3, 256, 1), new BHSDCodec(3, 256, 0, 1), new BHSDCodec(3, 256, 1, 1), new BHSDCodec(4, 256), new BHSDCodec(4, 256, 1), new BHSDCodec(4, 256, 0, 1), new BHSDCodec(4, 256, 1, 1), new BHSDCodec(5, 4), new BHSDCodec(5, 4, 1), new BHSDCodec(5, 4, 2), new BHSDCodec(5, 16), new BHSDCodec(5, 16, 1), new BHSDCodec(5, 16, 2), new BHSDCodec(5, 32), new BHSDCodec(5, 32, 1), new BHSDCodec(5, 32, 2), new BHSDCodec(5, 64), new BHSDCodec(5, 64, 1), new BHSDCodec(5, 64, 2), new BHSDCodec(5, 128), new BHSDCodec(5, 128, 1), new BHSDCodec(5, 128, 2), new BHSDCodec(5, 4, 0, 1), new BHSDCodec(5, 4, 1, 1), new BHSDCodec(5, 4, 2, 1), new BHSDCodec(5, 16, 0, 1), new BHSDCodec(5, 16, 1, 1), new BHSDCodec(5, 16, 2, 1), new BHSDCodec(5, 32, 0, 1), new BHSDCodec(5, 32, 1, 1), new BHSDCodec(5, 32, 2, 1), new BHSDCodec(5, 64, 0, 1), new BHSDCodec(5, 64, 1, 1), new BHSDCodec(5, 64, 2, 1), new BHSDCodec(5, 128, 0, 1), new BHSDCodec(5, 128, 1, 1), new BHSDCodec(5, 128, 2, 1), new BHSDCodec(2, 192), new BHSDCodec(2, 224), new BHSDCodec(2, DimensionsKt.HDPI), new BHSDCodec(2, GateControllerMsg.ControlCode.Error), new BHSDCodec(2, 252), new BHSDCodec(2, 8, 0, 1), new BHSDCodec(2, 8, 1, 1), new BHSDCodec(2, 16, 0, 1), new BHSDCodec(2, 16, 1, 1), new BHSDCodec(2, 32, 0, 1), new BHSDCodec(2, 32, 1, 1), new BHSDCodec(2, 64, 0, 1), new BHSDCodec(2, 64, 1, 1), new BHSDCodec(2, 128, 0, 1), new BHSDCodec(2, 128, 1, 1), new BHSDCodec(2, 192, 0, 1), new BHSDCodec(2, 192, 1, 1), new BHSDCodec(2, 224, 0, 1), new BHSDCodec(2, 224, 1, 1), new BHSDCodec(2, DimensionsKt.HDPI, 0, 1), new BHSDCodec(2, DimensionsKt.HDPI, 1, 1), new BHSDCodec(2, GateControllerMsg.ControlCode.Error, 0, 1), new BHSDCodec(2, GateControllerMsg.ControlCode.Error, 1, 1), new BHSDCodec(3, 192), new BHSDCodec(3, 224), new BHSDCodec(3, DimensionsKt.HDPI), new BHSDCodec(3, GateControllerMsg.ControlCode.Error), new BHSDCodec(3, 252), new BHSDCodec(3, 8, 0, 1), new BHSDCodec(3, 8, 1, 1), new BHSDCodec(3, 16, 0, 1), new BHSDCodec(3, 16, 1, 1), new BHSDCodec(3, 32, 0, 1), new BHSDCodec(3, 32, 1, 1), new BHSDCodec(3, 64, 0, 1), new BHSDCodec(3, 64, 1, 1), new BHSDCodec(3, 128, 0, 1), new BHSDCodec(3, 128, 1, 1), new BHSDCodec(3, 192, 0, 1), new BHSDCodec(3, 192, 1, 1), new BHSDCodec(3, 224, 0, 1), new BHSDCodec(3, 224, 1, 1), new BHSDCodec(3, DimensionsKt.HDPI, 0, 1), new BHSDCodec(3, DimensionsKt.HDPI, 1, 1), new BHSDCodec(3, GateControllerMsg.ControlCode.Error, 0, 1), new BHSDCodec(3, GateControllerMsg.ControlCode.Error, 1, 1), new BHSDCodec(4, 192), new BHSDCodec(4, 224), new BHSDCodec(4, DimensionsKt.HDPI), new BHSDCodec(4, GateControllerMsg.ControlCode.Error), new BHSDCodec(4, 252), new BHSDCodec(4, 8, 0, 1), new BHSDCodec(4, 8, 1, 1), new BHSDCodec(4, 16, 0, 1), new BHSDCodec(4, 16, 1, 1), new BHSDCodec(4, 32, 0, 1), new BHSDCodec(4, 32, 1, 1), new BHSDCodec(4, 64, 0, 1), new BHSDCodec(4, 64, 1, 1), new BHSDCodec(4, 128, 0, 1), new BHSDCodec(4, 128, 1, 1), new BHSDCodec(4, 192, 0, 1), new BHSDCodec(4, 192, 1, 1), new BHSDCodec(4, 224, 0, 1), new BHSDCodec(4, 224, 1, 1), new BHSDCodec(4, DimensionsKt.HDPI, 0, 1), new BHSDCodec(4, DimensionsKt.HDPI, 1, 1), new BHSDCodec(4, GateControllerMsg.ControlCode.Error, 0, 1), new BHSDCodec(4, GateControllerMsg.ControlCode.Error, 1, 1)};
    private static Map canonicalCodecsToSpecifiers;

    public static Codec getCodec(int i, InputStream inputStream, Codec codec) throws IOException, Pack200Exception {
        BHSDCodec[] bHSDCodecArr = canonicalCodec;
        if (bHSDCodecArr.length != 116) {
            throw new Error("Canonical encodings have been incorrectly modified");
        }
        if (i < 0) {
            throw new IllegalArgumentException("Encoding cannot be less than zero");
        }
        if (i == 0) {
            return codec;
        }
        if (i <= 115) {
            return bHSDCodecArr[i];
        }
        if (i == 116) {
            int read = inputStream.read();
            if (read == -1) {
                throw new EOFException("End of buffer read whilst trying to decode codec");
            }
            int i2 = read & 1;
            int i3 = (read >> 1) & 3;
            int i4 = ((read >> 3) & 7) + 1;
            int read2 = inputStream.read();
            if (read2 == -1) {
                throw new EOFException("End of buffer read whilst trying to decode codec");
            }
            return new BHSDCodec(i4, read2 + 1, i3, i2);
        }
        if (i >= 117 && i <= 140) {
            int i5 = i - 117;
            int i6 = i5 & 3;
            boolean z = ((i5 >> 2) & 1) == 1;
            boolean z2 = ((i5 >> 3) & 1) == 1;
            boolean z3 = ((i5 >> 4) & 1) == 1;
            if (z2 && z3) {
                throw new Pack200Exception("ADef and BDef should never both be true");
            }
            int read3 = ((z ? inputStream.read() : 3) + 1) * ((int) Math.pow(16.0d, i6));
            Codec codec2 = z2 ? codec : getCodec(inputStream.read(), inputStream, codec);
            if (!z3) {
                codec = getCodec(inputStream.read(), inputStream, codec);
            }
            return new RunCodec(read3, codec2, codec);
        }
        if (i < 141 || i > 188) {
            throw new Pack200Exception("Invalid codec encoding byte (" + i + ") found");
        }
        int i7 = i - 141;
        boolean z4 = (i7 & 1) == 1;
        boolean z5 = ((i7 >> 1) & 1) == 1;
        int i8 = i7 >> 2;
        boolean z6 = i8 != 0;
        int i9 = new int[]{0, 4, 8, 16, 32, 64, 128, 192, 224, DimensionsKt.HDPI, GateControllerMsg.ControlCode.Error, 252}[i8];
        if (z6) {
            Codec codec3 = z4 ? codec : getCodec(inputStream.read(), inputStream, codec);
            if (!z5) {
                codec = getCodec(inputStream.read(), inputStream, codec);
            }
            return new PopulationCodec(codec3, i9, codec);
        }
        Codec codec4 = z4 ? codec : getCodec(inputStream.read(), inputStream, codec);
        Codec codec5 = getCodec(inputStream.read(), inputStream, codec);
        if (!z5) {
            codec = getCodec(inputStream.read(), inputStream, codec);
        }
        return new PopulationCodec(codec4, codec5, codec);
    }

    public static int getSpecifierForDefaultCodec(BHSDCodec bHSDCodec) {
        return getSpecifier(bHSDCodec, null)[0];
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x0136, code lost:
    
        if (r9 != (-1)) goto L77;
     */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x016c A[LOOP:3: B:80:0x0169->B:82:0x016c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0179 A[LOOP:4: B:85:0x0176->B:87:0x0179, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0185 A[LOOP:5: B:89:0x0182->B:91:0x0185, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0147  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int[] getSpecifier(Codec codec, Codec codec2) {
        int i;
        int[] specifier;
        int[] specifier2;
        int[] specifier3;
        int i2;
        int i3;
        int i4;
        int i5 = 0;
        if (canonicalCodecsToSpecifiers == null) {
            HashMap hashMap = new HashMap(canonicalCodec.length);
            int i6 = 0;
            while (true) {
                BHSDCodec[] bHSDCodecArr = canonicalCodec;
                if (i6 >= bHSDCodecArr.length) {
                    break;
                }
                hashMap.put(bHSDCodecArr[i6], Integer.valueOf(i6));
                i6++;
            }
            canonicalCodecsToSpecifiers = hashMap;
        }
        if (canonicalCodecsToSpecifiers.containsKey(codec)) {
            return new int[]{((Integer) canonicalCodecsToSpecifiers.get(codec)).intValue()};
        }
        int i7 = 2;
        if (codec instanceof BHSDCodec) {
            BHSDCodec bHSDCodec = (BHSDCodec) codec;
            return new int[]{116, (bHSDCodec.isDelta() ? 1 : 0) + (bHSDCodec.getS() * 2) + ((bHSDCodec.getB() - 1) * 8), bHSDCodec.getH() - 1};
        }
        if (codec instanceof RunCodec) {
            RunCodec runCodec = (RunCodec) codec;
            int k = runCodec.getK();
            if (k <= 256) {
                i2 = k - 1;
                i3 = 0;
            } else if (k <= 4096) {
                i2 = (k / 16) - 1;
                i3 = 1;
            } else if (k <= 65536) {
                i2 = (k / 256) - 1;
                i3 = 2;
            } else {
                i2 = (k / 4096) - 1;
                i3 = 3;
            }
            Codec aCodec = runCodec.getACodec();
            Codec bCodec = runCodec.getBCodec();
            if (aCodec.equals(codec2)) {
                i4 = 1;
            } else {
                i4 = bCodec.equals(codec2) ? 2 : 0;
            }
            int i8 = i3 + 117 + (i2 == 3 ? 0 : 4) + (i4 * 8);
            int[] specifier4 = i4 == 1 ? new int[0] : getSpecifier(aCodec, codec2);
            int[] specifier5 = i4 == 2 ? new int[0] : getSpecifier(bCodec, codec2);
            int[] iArr = new int[(i2 == 3 ? 0 : 1) + 1 + specifier4.length + specifier5.length];
            iArr[0] = i8;
            if (i2 != 3) {
                iArr[1] = i2;
            } else {
                i7 = 1;
            }
            for (int i9 : specifier4) {
                iArr[i7] = i9;
                i7++;
            }
            while (i5 < specifier5.length) {
                iArr[i7] = specifier5[i5];
                i7++;
                i5++;
            }
            return iArr;
        }
        if (!(codec instanceof PopulationCodec)) {
            return null;
        }
        PopulationCodec populationCodec = (PopulationCodec) codec;
        Codec tokenCodec = populationCodec.getTokenCodec();
        Codec favouredCodec = populationCodec.getFavouredCodec();
        Codec unfavouredCodec = populationCodec.getUnfavouredCodec();
        boolean equals = favouredCodec.equals(codec2);
        boolean equals2 = unfavouredCodec.equals(codec2);
        int[] favoured = populationCodec.getFavoured();
        if (favoured != null) {
            int length = favoured.length;
            if (tokenCodec == Codec.BYTE1) {
                i = 1;
            } else if (tokenCodec instanceof BHSDCodec) {
                BHSDCodec bHSDCodec2 = (BHSDCodec) tokenCodec;
                if (bHSDCodec2.getS() == 0) {
                    i = Arrays.binarySearch(new int[]{4, 8, 16, 32, 64, 128, 192, 224, DimensionsKt.HDPI, GateControllerMsg.ControlCode.Error, 252}, 256 - bHSDCodec2.getH());
                }
            }
            int i10 = (equals ? 1 : 0) + 141 + ((equals2 ? 1 : 0) * 2) + (i * 4);
            specifier = !equals ? new int[0] : getSpecifier(favouredCodec, codec2);
            specifier2 = i == 0 ? new int[0] : getSpecifier(tokenCodec, codec2);
            specifier3 = !equals2 ? new int[0] : getSpecifier(unfavouredCodec, codec2);
            int[] iArr2 = new int[specifier.length + 1 + specifier3.length + specifier2.length];
            iArr2[0] = i10;
            int i11 = 1;
            for (int i12 : specifier) {
                iArr2[i11] = i12;
                i11++;
            }
            for (int i13 : specifier2) {
                iArr2[i11] = i13;
                i11++;
            }
            while (i5 < specifier3.length) {
                iArr2[i11] = specifier3[i5];
                i11++;
                i5++;
            }
            return iArr2;
        }
        i = 0;
        int i102 = (equals ? 1 : 0) + 141 + ((equals2 ? 1 : 0) * 2) + (i * 4);
        if (!equals) {
        }
        if (i == 0) {
        }
        if (!equals2) {
        }
        int[] iArr22 = new int[specifier.length + 1 + specifier3.length + specifier2.length];
        iArr22[0] = i102;
        int i112 = 1;
        while (r4 < specifier.length) {
        }
        while (r3 < specifier2.length) {
        }
        while (i5 < specifier3.length) {
        }
        return iArr22;
    }

    public static BHSDCodec getCanonicalCodec(int i) {
        return canonicalCodec[i];
    }
}
