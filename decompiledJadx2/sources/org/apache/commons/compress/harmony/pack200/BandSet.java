package org.apache.commons.compress.harmony.pack200;

import com.pudutech.gatecontrollerlib.GateControllerMsg;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jetbrains.anko.DimensionsKt;

/* loaded from: classes9.dex */
public abstract class BandSet {
    private static final int[] effortThresholds = {0, 0, 1000, 500, 100, 100, 100, 100, 100, 0};
    private long[] canonicalLargest;
    private long[] canonicalSmallest;
    final int effort;
    protected final SegmentHeader segmentHeader;

    public abstract void pack(OutputStream outputStream) throws IOException, Pack200Exception;

    public BandSet(int i, SegmentHeader segmentHeader) {
        this.effort = i;
        this.segmentHeader = segmentHeader;
    }

    public byte[] encodeScalar(int[] iArr, BHSDCodec bHSDCodec) throws Pack200Exception {
        return bHSDCodec.encode(iArr);
    }

    public byte[] encodeScalar(int i, BHSDCodec bHSDCodec) throws Pack200Exception {
        return bHSDCodec.encode(i);
    }

    public byte[] encodeBandInt(String str, int[] iArr, BHSDCodec bHSDCodec) throws Pack200Exception {
        byte[] bArr;
        int i = this.effort;
        if (i <= 1 || iArr.length < effortThresholds[i]) {
            bArr = null;
        } else {
            BandAnalysisResults analyseBand = analyseBand(str, iArr, bHSDCodec);
            Codec codec = analyseBand.betterCodec;
            bArr = analyseBand.encodedBand;
            if (codec != null) {
                if (codec instanceof BHSDCodec) {
                    int[] specifier = CodecEncoding.getSpecifier(codec, bHSDCodec);
                    int i2 = specifier[0];
                    if (specifier.length > 1) {
                        for (int i3 = 1; i3 < specifier.length; i3++) {
                            this.segmentHeader.appendBandCodingSpecifier(specifier[i3]);
                        }
                    }
                    byte[] encode = bHSDCodec.encode(new int[]{bHSDCodec.isSigned() ? (-1) - i2 : i2 + bHSDCodec.getL()});
                    byte[] bArr2 = new byte[encode.length + bArr.length];
                    System.arraycopy(encode, 0, bArr2, 0, encode.length);
                    System.arraycopy(bArr, 0, bArr2, encode.length, bArr.length);
                    return bArr2;
                }
                if (!(codec instanceof PopulationCodec)) {
                    boolean z = codec instanceof RunCodec;
                } else {
                    for (int i4 : analyseBand.extraMetadata) {
                        this.segmentHeader.appendBandCodingSpecifier(i4);
                    }
                    return bArr;
                }
            }
        }
        if (iArr.length <= 0) {
            return new byte[0];
        }
        if (bArr == null) {
            bArr = bHSDCodec.encode(iArr);
        }
        int i5 = iArr[0];
        if (bHSDCodec.getB() != 1) {
            if (bHSDCodec.isSigned() && i5 >= -256 && i5 <= -1) {
                byte[] encode2 = bHSDCodec.encode(new int[]{(-1) - CodecEncoding.getSpecifierForDefaultCodec(bHSDCodec)});
                byte[] bArr3 = new byte[encode2.length + bArr.length];
                System.arraycopy(encode2, 0, bArr3, 0, encode2.length);
                System.arraycopy(bArr, 0, bArr3, encode2.length, bArr.length);
                return bArr3;
            }
            if (!bHSDCodec.isSigned() && i5 >= bHSDCodec.getL() && i5 <= bHSDCodec.getL() + 255) {
                byte[] encode3 = bHSDCodec.encode(new int[]{CodecEncoding.getSpecifierForDefaultCodec(bHSDCodec) + bHSDCodec.getL()});
                byte[] bArr4 = new byte[encode3.length + bArr.length];
                System.arraycopy(encode3, 0, bArr4, 0, encode3.length);
                System.arraycopy(bArr, 0, bArr4, encode3.length, bArr.length);
                return bArr4;
            }
        }
        return bArr;
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x009d, code lost:
    
        if (r0 >= 0.04d) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private BandAnalysisResults analyseBand(String str, int[] iArr, BHSDCodec bHSDCodec) throws Pack200Exception {
        BandAnalysisResults bandAnalysisResults = new BandAnalysisResults();
        if (this.canonicalLargest == null) {
            this.canonicalLargest = new long[116];
            this.canonicalSmallest = new long[116];
            int i = 1;
            while (true) {
                long[] jArr = this.canonicalLargest;
                if (i >= jArr.length) {
                    break;
                }
                jArr[i] = CodecEncoding.getCanonicalCodec(i).largest();
                this.canonicalSmallest[i] = CodecEncoding.getCanonicalCodec(i).smallest();
                i++;
            }
        }
        BandData bandData = new BandData(iArr);
        byte[] encode = bHSDCodec.encode(iArr);
        bandAnalysisResults.encodedBand = encode;
        if (encode.length <= (iArr.length + 23) - (this.effort * 2)) {
            return bandAnalysisResults;
        }
        if (bandData.anyNegatives() || bandData.largest > Codec.BYTE1.largest()) {
            if (this.effort > 3 && !str.equals("POPULATION")) {
                int numDistinctValues = bandData.numDistinctValues();
                float length = numDistinctValues / iArr.length;
                if (numDistinctValues >= 100) {
                    double d = length;
                    if (d >= 0.02d) {
                        if (this.effort > 6) {
                        }
                    }
                }
                encodeWithPopulationCodec(str, iArr, bHSDCodec, bandData, bandAnalysisResults);
                if (timeToStop(bandAnalysisResults)) {
                    return bandAnalysisResults;
                }
            }
            ArrayList arrayList = new ArrayList();
            if (bandData.mainlyPositiveDeltas() && bandData.mainlySmallDeltas()) {
                arrayList.add(CanonicalCodecFamilies.deltaUnsignedCodecs2);
            }
            if (bandData.wellCorrelated()) {
                if (bandData.mainlyPositiveDeltas()) {
                    arrayList.add(CanonicalCodecFamilies.deltaUnsignedCodecs1);
                    arrayList.add(CanonicalCodecFamilies.deltaUnsignedCodecs3);
                    arrayList.add(CanonicalCodecFamilies.deltaUnsignedCodecs4);
                    arrayList.add(CanonicalCodecFamilies.deltaUnsignedCodecs5);
                    arrayList.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs1);
                    arrayList.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs3);
                    arrayList.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs4);
                    arrayList.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs5);
                    arrayList.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs2);
                } else {
                    arrayList.add(CanonicalCodecFamilies.deltaSignedCodecs1);
                    arrayList.add(CanonicalCodecFamilies.deltaSignedCodecs3);
                    arrayList.add(CanonicalCodecFamilies.deltaSignedCodecs2);
                    arrayList.add(CanonicalCodecFamilies.deltaSignedCodecs4);
                    arrayList.add(CanonicalCodecFamilies.deltaSignedCodecs5);
                    arrayList.add(CanonicalCodecFamilies.nonDeltaSignedCodecs1);
                    arrayList.add(CanonicalCodecFamilies.nonDeltaSignedCodecs2);
                }
            } else if (bandData.anyNegatives()) {
                arrayList.add(CanonicalCodecFamilies.nonDeltaSignedCodecs1);
                arrayList.add(CanonicalCodecFamilies.nonDeltaSignedCodecs2);
                arrayList.add(CanonicalCodecFamilies.deltaSignedCodecs1);
                arrayList.add(CanonicalCodecFamilies.deltaSignedCodecs2);
                arrayList.add(CanonicalCodecFamilies.deltaSignedCodecs3);
                arrayList.add(CanonicalCodecFamilies.deltaSignedCodecs4);
                arrayList.add(CanonicalCodecFamilies.deltaSignedCodecs5);
            } else {
                arrayList.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs1);
                arrayList.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs3);
                arrayList.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs4);
                arrayList.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs5);
                arrayList.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs2);
                arrayList.add(CanonicalCodecFamilies.deltaUnsignedCodecs1);
                arrayList.add(CanonicalCodecFamilies.deltaUnsignedCodecs3);
                arrayList.add(CanonicalCodecFamilies.deltaUnsignedCodecs4);
                arrayList.add(CanonicalCodecFamilies.deltaUnsignedCodecs5);
            }
            if (str.equalsIgnoreCase("cpint")) {
                System.out.print("");
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                tryCodecs(str, iArr, bHSDCodec, bandData, bandAnalysisResults, encode, (BHSDCodec[]) it.next());
                if (timeToStop(bandAnalysisResults)) {
                    break;
                }
            }
            return bandAnalysisResults;
        }
        bandAnalysisResults.encodedBand = Codec.BYTE1.encode(iArr);
        bandAnalysisResults.betterCodec = Codec.BYTE1;
        return bandAnalysisResults;
    }

    private boolean timeToStop(BandAnalysisResults bandAnalysisResults) {
        return this.effort > 6 ? bandAnalysisResults.numCodecsTried >= this.effort * 2 : bandAnalysisResults.numCodecsTried >= this.effort;
    }

    private void tryCodecs(String str, int[] iArr, BHSDCodec bHSDCodec, BandData bandData, BandAnalysisResults bandAnalysisResults, byte[] bArr, BHSDCodec[] bHSDCodecArr) throws Pack200Exception {
        for (BHSDCodec bHSDCodec2 : bHSDCodecArr) {
            if (bHSDCodec2.equals(bHSDCodec)) {
                return;
            }
            if (bHSDCodec2.isDelta()) {
                if (bHSDCodec2.largest() >= bandData.largestDelta && bHSDCodec2.smallest() <= bandData.smallestDelta && bHSDCodec2.largest() >= bandData.largest && bHSDCodec2.smallest() <= bandData.smallest) {
                    byte[] encode = bHSDCodec2.encode(iArr);
                    BandAnalysisResults.access$408(bandAnalysisResults);
                    int length = (bArr.length - encode.length) - bHSDCodec.encode(CodecEncoding.getSpecifier(bHSDCodec2, null)).length;
                    if (length > bandAnalysisResults.saved) {
                        bandAnalysisResults.betterCodec = bHSDCodec2;
                        bandAnalysisResults.encodedBand = encode;
                        bandAnalysisResults.saved = length;
                    }
                }
            } else if (bHSDCodec2.largest() >= bandData.largest && bHSDCodec2.smallest() <= bandData.smallest) {
                byte[] encode2 = bHSDCodec2.encode(iArr);
                BandAnalysisResults.access$408(bandAnalysisResults);
                int length2 = (bArr.length - encode2.length) - bHSDCodec.encode(CodecEncoding.getSpecifier(bHSDCodec2, null)).length;
                if (length2 > bandAnalysisResults.saved) {
                    bandAnalysisResults.betterCodec = bHSDCodec2;
                    bandAnalysisResults.encodedBand = encode2;
                    bandAnalysisResults.saved = length2;
                }
            }
            if (timeToStop(bandAnalysisResults)) {
                return;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:89:0x0117  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void encodeWithPopulationCodec(String str, int[] iArr, BHSDCodec bHSDCodec, BandData bandData, BandAnalysisResults bandAnalysisResults) throws Pack200Exception {
        int l;
        Codec codec;
        byte[] bArr;
        int i;
        Codec codec2;
        boolean z;
        bandAnalysisResults.numCodecsTried += 3;
        final Map map = bandData.distinctValues;
        ArrayList arrayList = new ArrayList();
        for (Integer num : map.keySet()) {
            if (((Integer) map.get(num)).intValue() > 2 || map.size() < 256) {
                arrayList.add(num);
            }
        }
        if (map.size() > 255) {
            Collections.sort(arrayList, new Comparator() { // from class: org.apache.commons.compress.harmony.pack200.-$$Lambda$BandSet$pM-AW4EGODGMT2i3bipCim4wCMU
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    int compareTo;
                    compareTo = ((Integer) r0.get(obj2)).compareTo((Integer) map.get(obj));
                    return compareTo;
                }
            });
        }
        IntList intList = new IntList();
        HashMap hashMap = new HashMap();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            hashMap.put((Integer) arrayList.get(i2), Integer.valueOf(i2));
        }
        int[] iArr2 = new int[iArr.length];
        for (int i3 = 0; i3 < iArr.length; i3++) {
            Integer num2 = (Integer) hashMap.get(Integer.valueOf(iArr[i3]));
            if (num2 == null) {
                iArr2[i3] = 0;
                intList.add(iArr[i3]);
            } else {
                iArr2[i3] = num2.intValue() + 1;
            }
        }
        arrayList.add(arrayList.get(arrayList.size() - 1));
        int[] integerListToArray = integerListToArray(arrayList);
        int[] array = intList.toArray();
        BandAnalysisResults analyseBand = analyseBand("POPULATION", integerListToArray, bHSDCodec);
        BandAnalysisResults analyseBand2 = analyseBand("POPULATION", array, bHSDCodec);
        int size = arrayList.size() - 1;
        if (size < 256) {
            l = 0;
            codec2 = null;
            bArr = Codec.BYTE1.encode(iArr2);
            i = 1;
        } else {
            BandAnalysisResults analyseBand3 = analyseBand("POPULATION", iArr2, bHSDCodec);
            Codec codec3 = analyseBand3.betterCodec;
            byte[] bArr2 = analyseBand3.encodedBand;
            if (codec3 == null) {
                codec3 = bHSDCodec;
            }
            BHSDCodec bHSDCodec2 = (BHSDCodec) codec3;
            l = bHSDCodec2.getL();
            int h = bHSDCodec2.getH();
            int s = bHSDCodec2.getS();
            int b = bHSDCodec2.getB();
            boolean isDelta = bHSDCodec2.isDelta();
            if (s != 0 || isDelta) {
                codec = codec3;
            } else {
                if (b > 1) {
                    codec = codec3;
                    if (new BHSDCodec(b - 1, h).largest() >= size) {
                        z = false;
                        if (z) {
                            switch (l) {
                                case 4:
                                    bArr = bArr2;
                                    i = 1;
                                    break;
                                case 8:
                                    bArr = bArr2;
                                    i = 2;
                                    break;
                                case 16:
                                    i = 3;
                                    bArr = bArr2;
                                    break;
                                case 32:
                                    i = 4;
                                    bArr = bArr2;
                                    break;
                                case 64:
                                    i = 5;
                                    bArr = bArr2;
                                    break;
                                case 128:
                                    i = 6;
                                    bArr = bArr2;
                                    break;
                                case 192:
                                    i = 7;
                                    bArr = bArr2;
                                    break;
                                case 224:
                                    i = 8;
                                    bArr = bArr2;
                                    break;
                                case DimensionsKt.HDPI /* 240 */:
                                    i = 9;
                                    bArr = bArr2;
                                    break;
                                case GateControllerMsg.ControlCode.Error /* 248 */:
                                    i = 10;
                                    bArr = bArr2;
                                    break;
                                case 252:
                                    i = 11;
                                    bArr = bArr2;
                                    break;
                            }
                            codec2 = codec;
                        }
                    }
                } else {
                    codec = codec3;
                }
                z = true;
                if (z) {
                }
            }
            bArr = bArr2;
            i = 0;
            codec2 = codec;
        }
        byte[] bArr3 = analyseBand.encodedBand;
        byte[] bArr4 = analyseBand2.encodedBand;
        Codec codec4 = analyseBand.betterCodec;
        Codec codec5 = analyseBand2.betterCodec;
        int i4 = (codec4 == null ? 1 : 0) + 141 + (i * 4) + (codec5 == null ? 2 : 0);
        IntList intList2 = new IntList(3);
        if (codec4 != null) {
            for (int i5 : CodecEncoding.getSpecifier(codec4, null)) {
                intList2.add(i5);
            }
        }
        if (i == 0) {
            for (int i6 : CodecEncoding.getSpecifier(codec2, null)) {
                intList2.add(i6);
            }
        }
        if (codec5 != null) {
            for (int i7 : CodecEncoding.getSpecifier(codec5, null)) {
                intList2.add(i7);
            }
        }
        int[] array2 = intList2.toArray();
        byte[] encode = Codec.UNSIGNED5.encode(array2);
        byte[] encode2 = bHSDCodec.encode(new int[]{bHSDCodec.isSigned() ? (-1) - i4 : i4 + bHSDCodec.getL()});
        int length = encode2.length + bArr3.length + bArr.length + bArr4.length;
        if (encode.length + length < bandAnalysisResults.encodedBand.length) {
            bandAnalysisResults.saved += bandAnalysisResults.encodedBand.length - (encode.length + length);
            byte[] bArr5 = new byte[length];
            System.arraycopy(encode2, 0, bArr5, 0, encode2.length);
            System.arraycopy(bArr3, 0, bArr5, encode2.length, bArr3.length);
            System.arraycopy(bArr, 0, bArr5, encode2.length + bArr3.length, bArr.length);
            System.arraycopy(bArr4, 0, bArr5, encode2.length + bArr3.length + bArr.length, bArr4.length);
            bandAnalysisResults.encodedBand = bArr5;
            bandAnalysisResults.extraMetadata = array2;
            if (l != 0) {
                bandAnalysisResults.betterCodec = new PopulationCodec(codec4, l, codec5);
            } else {
                bandAnalysisResults.betterCodec = new PopulationCodec(codec4, codec2, codec5);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] encodeFlags(String str, long[] jArr, BHSDCodec bHSDCodec, BHSDCodec bHSDCodec2, boolean z) throws Pack200Exception {
        if (!z) {
            int[] iArr = new int[jArr.length];
            for (int i = 0; i < jArr.length; i++) {
                iArr[i] = (int) jArr[i];
            }
            return encodeBandInt(str, iArr, bHSDCodec);
        }
        int[] iArr2 = new int[jArr.length];
        int[] iArr3 = new int[jArr.length];
        for (int i2 = 0; i2 < jArr.length; i2++) {
            long j = jArr[i2];
            iArr2[i2] = (int) (j >> 32);
            iArr3[i2] = (int) j;
        }
        byte[] encodeBandInt = encodeBandInt(str, iArr2, bHSDCodec2);
        byte[] encodeBandInt2 = encodeBandInt(str, iArr3, bHSDCodec);
        byte[] bArr = new byte[encodeBandInt.length + encodeBandInt2.length];
        System.arraycopy(encodeBandInt, 0, bArr, 0, encodeBandInt.length);
        System.arraycopy(encodeBandInt2, 0, bArr, encodeBandInt.length + 1, encodeBandInt2.length);
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int[] integerListToArray(List list) {
        int[] iArr = new int[list.size()];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = ((Integer) list.get(i)).intValue();
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long[] longListToArray(List list) {
        long[] jArr = new long[list.size()];
        for (int i = 0; i < jArr.length; i++) {
            jArr[i] = ((Long) list.get(i)).longValue();
        }
        return jArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int[] cpEntryListToArray(List list) {
        int[] iArr = new int[list.size()];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = ((ConstantPoolEntry) list.get(i)).getIndex();
            if (iArr[i] < 0) {
                throw new RuntimeException("Index should be > 0");
            }
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int[] cpEntryOrNullListToArray(List list) {
        int[] iArr = new int[list.size()];
        for (int i = 0; i < iArr.length; i++) {
            ConstantPoolEntry constantPoolEntry = (ConstantPoolEntry) list.get(i);
            iArr[i] = constantPoolEntry == null ? 0 : constantPoolEntry.getIndex() + 1;
            if (constantPoolEntry != null && constantPoolEntry.getIndex() < 0) {
                throw new RuntimeException("Index should be > 0");
            }
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] encodeFlags(String str, long[][] jArr, BHSDCodec bHSDCodec, BHSDCodec bHSDCodec2, boolean z) throws Pack200Exception {
        return encodeFlags(str, flatten(jArr), bHSDCodec, bHSDCodec2, z);
    }

    private long[] flatten(long[][] jArr) {
        int i = 0;
        for (long[] jArr2 : jArr) {
            i += jArr2.length;
        }
        long[] jArr3 = new long[i];
        int i2 = 0;
        int i3 = 0;
        while (i2 < jArr.length) {
            int i4 = i3;
            for (int i5 = 0; i5 < jArr[i2].length; i5++) {
                jArr3[i4] = jArr[i2][i5];
                i4++;
            }
            i2++;
            i3 = i4;
        }
        return jArr3;
    }

    /* loaded from: classes9.dex */
    public class BandData {
        private final int[] band;
        private Map distinctValues;
        private int largest;
        private int largestDelta;
        private int smallest;
        private int smallestDelta;
        private int deltaIsAscending = 0;
        private int smallDeltaCount = 0;
        private double averageAbsoluteDelta = 0.0d;
        private double averageAbsoluteValue = 0.0d;

        public BandData(int[] iArr) {
            Integer valueOf;
            this.smallest = Integer.MAX_VALUE;
            this.largest = Integer.MIN_VALUE;
            this.band = iArr;
            for (int i = 0; i < iArr.length; i++) {
                if (iArr[i] < this.smallest) {
                    this.smallest = iArr[i];
                }
                if (iArr[i] > this.largest) {
                    this.largest = iArr[i];
                }
                if (i != 0) {
                    int i2 = iArr[i] - iArr[i - 1];
                    if (i2 < this.smallestDelta) {
                        this.smallestDelta = i2;
                    }
                    if (i2 > this.largestDelta) {
                        this.largestDelta = i2;
                    }
                    if (i2 >= 0) {
                        this.deltaIsAscending++;
                    }
                    this.averageAbsoluteDelta += Math.abs(i2) / (iArr.length - 1);
                    if (Math.abs(i2) < 256) {
                        this.smallDeltaCount++;
                    }
                } else {
                    this.smallestDelta = iArr[0];
                    this.largestDelta = iArr[0];
                }
                this.averageAbsoluteValue += Math.abs(iArr[i]) / iArr.length;
                if (BandSet.this.effort > 3) {
                    if (this.distinctValues == null) {
                        this.distinctValues = new HashMap();
                    }
                    Integer valueOf2 = Integer.valueOf(iArr[i]);
                    Integer num = (Integer) this.distinctValues.get(valueOf2);
                    if (num == null) {
                        valueOf = 1;
                    } else {
                        valueOf = Integer.valueOf(num.intValue() + 1);
                    }
                    this.distinctValues.put(valueOf2, valueOf);
                }
            }
        }

        public boolean mainlySmallDeltas() {
            return ((float) this.smallDeltaCount) / ((float) this.band.length) > 0.7f;
        }

        public boolean wellCorrelated() {
            return this.averageAbsoluteDelta * 3.1d < this.averageAbsoluteValue;
        }

        public boolean mainlyPositiveDeltas() {
            return ((float) this.deltaIsAscending) / ((float) this.band.length) > 0.95f;
        }

        public boolean anyNegatives() {
            return this.smallest < 0;
        }

        public int numDistinctValues() {
            Map map = this.distinctValues;
            if (map == null) {
                return this.band.length;
            }
            return map.size();
        }
    }

    /* loaded from: classes9.dex */
    public class BandAnalysisResults {
        private Codec betterCodec;
        private byte[] encodedBand;
        private int[] extraMetadata;
        private int numCodecsTried = 0;
        private int saved = 0;

        public BandAnalysisResults() {
        }

        static /* synthetic */ int access$408(BandAnalysisResults bandAnalysisResults) {
            int i = bandAnalysisResults.numCodecsTried;
            bandAnalysisResults.numCodecsTried = i + 1;
            return i;
        }
    }
}
