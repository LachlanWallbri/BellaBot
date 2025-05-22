package org.apache.commons.compress.harmony.unpack200;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPDouble;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPFieldRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPFloat;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInteger;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInterfaceMethodRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPLong;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPMethodRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPNameAndType;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPString;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;

/* loaded from: classes9.dex */
public class CpBands extends BandSet {
    private int classOffset;
    private String[] cpClass;
    private int[] cpClassInts;
    private String[] cpDescriptor;
    private int[] cpDescriptorNameInts;
    private int[] cpDescriptorTypeInts;
    private double[] cpDouble;
    private String[] cpFieldClass;
    private int[] cpFieldClassInts;
    private String[] cpFieldDescriptor;
    private int[] cpFieldDescriptorInts;
    private float[] cpFloat;
    private String[] cpIMethodClass;
    private int[] cpIMethodClassInts;
    private String[] cpIMethodDescriptor;
    private int[] cpIMethodDescriptorInts;
    private int[] cpInt;
    private long[] cpLong;
    private String[] cpMethodClass;
    private int[] cpMethodClassInts;
    private String[] cpMethodDescriptor;
    private int[] cpMethodDescriptorInts;
    private String[] cpSignature;
    private int[] cpSignatureInts;
    private String[] cpString;
    private int[] cpStringInts;
    private String[] cpUTF8;
    private int descrOffset;
    private final Map descriptorsToCPNameAndTypes;
    private int doubleOffset;
    private final Map doublesToCPDoubles;
    private int fieldOffset;
    private int floatOffset;
    private final Map floatsToCPFloats;
    private int imethodOffset;
    private int intOffset;
    private final Map integersToCPIntegers;
    private int longOffset;
    private final Map longsToCPLongs;
    private Map mapClass;
    private Map mapDescriptor;
    private Map mapSignature;
    private Map mapUTF8;
    private int methodOffset;
    private final SegmentConstantPool pool;
    private int signatureOffset;
    private int stringOffset;
    private final Map stringsToCPClass;
    private final Map stringsToCPStrings;
    private final Map stringsToCPUTF8;

    @Override // org.apache.commons.compress.harmony.unpack200.BandSet
    public void unpack() {
    }

    public SegmentConstantPool getConstantPool() {
        return this.pool;
    }

    public CpBands(Segment segment) {
        super(segment);
        this.pool = new SegmentConstantPool(this);
        this.stringsToCPUTF8 = new HashMap();
        this.stringsToCPStrings = new HashMap();
        this.longsToCPLongs = new HashMap();
        this.integersToCPIntegers = new HashMap();
        this.floatsToCPFloats = new HashMap();
        this.stringsToCPClass = new HashMap();
        this.doublesToCPDoubles = new HashMap();
        this.descriptorsToCPNameAndTypes = new HashMap();
    }

    @Override // org.apache.commons.compress.harmony.unpack200.BandSet
    public void read(InputStream inputStream) throws IOException, Pack200Exception {
        parseCpUtf8(inputStream);
        parseCpInt(inputStream);
        parseCpFloat(inputStream);
        parseCpLong(inputStream);
        parseCpDouble(inputStream);
        parseCpString(inputStream);
        parseCpClass(inputStream);
        parseCpSignature(inputStream);
        parseCpDescriptor(inputStream);
        parseCpField(inputStream);
        parseCpMethod(inputStream);
        parseCpIMethod(inputStream);
        this.intOffset = this.cpUTF8.length;
        this.floatOffset = this.intOffset + this.cpInt.length;
        this.longOffset = this.floatOffset + this.cpFloat.length;
        this.doubleOffset = this.longOffset + this.cpLong.length;
        this.stringOffset = this.doubleOffset + this.cpDouble.length;
        this.classOffset = this.stringOffset + this.cpString.length;
        this.signatureOffset = this.classOffset + this.cpClass.length;
        this.descrOffset = this.signatureOffset + this.cpSignature.length;
        this.fieldOffset = this.descrOffset + this.cpDescriptor.length;
        this.methodOffset = this.fieldOffset + this.cpFieldClass.length;
        this.imethodOffset = this.methodOffset + this.cpMethodClass.length;
    }

    private void parseCpClass(InputStream inputStream) throws IOException, Pack200Exception {
        int cpClassCount = this.header.getCpClassCount();
        this.cpClassInts = decodeBandInt("cp_Class", inputStream, Codec.UDELTA5, cpClassCount);
        this.cpClass = new String[cpClassCount];
        this.mapClass = new HashMap(cpClassCount);
        for (int i = 0; i < cpClassCount; i++) {
            String[] strArr = this.cpClass;
            strArr[i] = this.cpUTF8[this.cpClassInts[i]];
            this.mapClass.put(strArr[i], Integer.valueOf(i));
        }
    }

    private void parseCpDescriptor(InputStream inputStream) throws IOException, Pack200Exception {
        int cpDescriptorCount = this.header.getCpDescriptorCount();
        this.cpDescriptorNameInts = decodeBandInt("cp_Descr_name", inputStream, Codec.DELTA5, cpDescriptorCount);
        this.cpDescriptorTypeInts = decodeBandInt("cp_Descr_type", inputStream, Codec.UDELTA5, cpDescriptorCount);
        String[] references = getReferences(this.cpDescriptorNameInts, this.cpUTF8);
        String[] references2 = getReferences(this.cpDescriptorTypeInts, this.cpSignature);
        this.cpDescriptor = new String[cpDescriptorCount];
        this.mapDescriptor = new HashMap(cpDescriptorCount);
        for (int i = 0; i < cpDescriptorCount; i++) {
            this.cpDescriptor[i] = references[i] + ":" + references2[i];
            this.mapDescriptor.put(this.cpDescriptor[i], Integer.valueOf(i));
        }
    }

    private void parseCpDouble(InputStream inputStream) throws IOException, Pack200Exception {
        long[] parseFlags = parseFlags("cp_Double", inputStream, this.header.getCpDoubleCount(), Codec.UDELTA5, Codec.DELTA5);
        this.cpDouble = new double[parseFlags.length];
        for (int i = 0; i < parseFlags.length; i++) {
            this.cpDouble[i] = Double.longBitsToDouble(parseFlags[i]);
        }
    }

    private void parseCpField(InputStream inputStream) throws IOException, Pack200Exception {
        int cpFieldCount = this.header.getCpFieldCount();
        this.cpFieldClassInts = decodeBandInt("cp_Field_class", inputStream, Codec.DELTA5, cpFieldCount);
        this.cpFieldDescriptorInts = decodeBandInt("cp_Field_desc", inputStream, Codec.UDELTA5, cpFieldCount);
        this.cpFieldClass = new String[cpFieldCount];
        this.cpFieldDescriptor = new String[cpFieldCount];
        for (int i = 0; i < cpFieldCount; i++) {
            this.cpFieldClass[i] = this.cpClass[this.cpFieldClassInts[i]];
            this.cpFieldDescriptor[i] = this.cpDescriptor[this.cpFieldDescriptorInts[i]];
        }
    }

    private void parseCpFloat(InputStream inputStream) throws IOException, Pack200Exception {
        int cpFloatCount = this.header.getCpFloatCount();
        this.cpFloat = new float[cpFloatCount];
        int[] decodeBandInt = decodeBandInt("cp_Float", inputStream, Codec.UDELTA5, cpFloatCount);
        for (int i = 0; i < cpFloatCount; i++) {
            this.cpFloat[i] = Float.intBitsToFloat(decodeBandInt[i]);
        }
    }

    private void parseCpIMethod(InputStream inputStream) throws IOException, Pack200Exception {
        int cpIMethodCount = this.header.getCpIMethodCount();
        this.cpIMethodClassInts = decodeBandInt("cp_Imethod_class", inputStream, Codec.DELTA5, cpIMethodCount);
        this.cpIMethodDescriptorInts = decodeBandInt("cp_Imethod_desc", inputStream, Codec.UDELTA5, cpIMethodCount);
        this.cpIMethodClass = new String[cpIMethodCount];
        this.cpIMethodDescriptor = new String[cpIMethodCount];
        for (int i = 0; i < cpIMethodCount; i++) {
            this.cpIMethodClass[i] = this.cpClass[this.cpIMethodClassInts[i]];
            this.cpIMethodDescriptor[i] = this.cpDescriptor[this.cpIMethodDescriptorInts[i]];
        }
    }

    private void parseCpInt(InputStream inputStream) throws IOException, Pack200Exception {
        this.cpInt = decodeBandInt("cpInt", inputStream, Codec.UDELTA5, this.header.getCpIntCount());
    }

    private void parseCpLong(InputStream inputStream) throws IOException, Pack200Exception {
        this.cpLong = parseFlags("cp_Long", inputStream, this.header.getCpLongCount(), Codec.UDELTA5, Codec.DELTA5);
    }

    private void parseCpMethod(InputStream inputStream) throws IOException, Pack200Exception {
        int cpMethodCount = this.header.getCpMethodCount();
        this.cpMethodClassInts = decodeBandInt("cp_Method_class", inputStream, Codec.DELTA5, cpMethodCount);
        this.cpMethodDescriptorInts = decodeBandInt("cp_Method_desc", inputStream, Codec.UDELTA5, cpMethodCount);
        this.cpMethodClass = new String[cpMethodCount];
        this.cpMethodDescriptor = new String[cpMethodCount];
        for (int i = 0; i < cpMethodCount; i++) {
            this.cpMethodClass[i] = this.cpClass[this.cpMethodClassInts[i]];
            this.cpMethodDescriptor[i] = this.cpDescriptor[this.cpMethodDescriptorInts[i]];
        }
    }

    private void parseCpSignature(InputStream inputStream) throws IOException, Pack200Exception {
        int cpSignatureCount = this.header.getCpSignatureCount();
        this.cpSignatureInts = decodeBandInt("cp_Signature_form", inputStream, Codec.DELTA5, cpSignatureCount);
        String[] references = getReferences(this.cpSignatureInts, this.cpUTF8);
        this.cpSignature = new String[cpSignatureCount];
        this.mapSignature = new HashMap();
        int i = 0;
        for (int i2 = 0; i2 < cpSignatureCount; i2++) {
            for (char c : references[i2].toCharArray()) {
                if (c == 'L') {
                    this.cpSignatureInts[i2] = -1;
                    i++;
                }
            }
        }
        String[] parseReferences = parseReferences("cp_Signature_classes", inputStream, Codec.UDELTA5, i, this.cpClass);
        int i3 = 0;
        int i4 = 0;
        while (i3 < cpSignatureCount) {
            String str = references[i3];
            int length = str.length();
            StringBuffer stringBuffer = new StringBuffer(64);
            ArrayList arrayList = new ArrayList();
            int i5 = i4;
            for (int i6 = 0; i6 < length; i6++) {
                char charAt = str.charAt(i6);
                stringBuffer.append(charAt);
                if (charAt == 'L') {
                    String str2 = parseReferences[i5];
                    arrayList.add(str2);
                    stringBuffer.append(str2);
                    i5++;
                }
            }
            this.cpSignature[i3] = stringBuffer.toString();
            this.mapSignature.put(stringBuffer.toString(), Integer.valueOf(i3));
            i3++;
            i4 = i5;
        }
    }

    private void parseCpString(InputStream inputStream) throws IOException, Pack200Exception {
        int cpStringCount = this.header.getCpStringCount();
        this.cpStringInts = decodeBandInt("cp_String", inputStream, Codec.UDELTA5, cpStringCount);
        this.cpString = new String[cpStringCount];
        for (int i = 0; i < cpStringCount; i++) {
            this.cpString[i] = this.cpUTF8[this.cpStringInts[i]];
        }
    }

    private void parseCpUtf8(InputStream inputStream) throws IOException, Pack200Exception {
        int cpUTF8Count = this.header.getCpUTF8Count();
        this.cpUTF8 = new String[cpUTF8Count];
        this.mapUTF8 = new HashMap(cpUTF8Count + 1);
        this.cpUTF8[0] = "";
        this.mapUTF8.put("", 0);
        int[] decodeBandInt = decodeBandInt("cpUTF8Prefix", inputStream, Codec.DELTA5, cpUTF8Count - 2);
        int[] decodeBandInt2 = decodeBandInt("cpUTF8Suffix", inputStream, Codec.UNSIGNED5, cpUTF8Count - 1);
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < decodeBandInt2.length; i3++) {
            if (decodeBandInt2[i3] == 0) {
                i2++;
            } else {
                i += decodeBandInt2[i3];
            }
        }
        char[] cArr = new char[i];
        int[] decodeBandInt3 = decodeBandInt("cp_Utf8_chars", inputStream, Codec.CHAR3, i);
        for (int i4 = 0; i4 < cArr.length; i4++) {
            cArr[i4] = (char) decodeBandInt3[i4];
        }
        int[] decodeBandInt4 = decodeBandInt("cp_Utf8_big_suffix", inputStream, Codec.DELTA5, i2);
        int[][] iArr = new int[i2];
        for (int i5 = 0; i5 < iArr.length; i5++) {
            iArr[i5] = decodeBandInt("cp_Utf8_big_chars " + i5, inputStream, Codec.DELTA5, decodeBandInt4[i5]);
        }
        char[][] cArr2 = new char[i2];
        for (int i6 = 0; i6 < iArr.length; i6++) {
            cArr2[i6] = new char[iArr[i6].length];
            for (int i7 = 0; i7 < iArr[i6].length; i7++) {
                cArr2[i6][i7] = (char) iArr[i6][i7];
            }
        }
        int i8 = 0;
        int i9 = 0;
        int i10 = 1;
        while (i10 < cpUTF8Count) {
            String[] strArr = this.cpUTF8;
            int i11 = i10 - 1;
            String str = strArr[i11];
            if (decodeBandInt2[i11] == 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(str.substring(0, i10 > 1 ? decodeBandInt[i10 - 2] : 0));
                sb.append(new String(cArr2[i9]));
                strArr[i10] = sb.toString();
                this.mapUTF8.put(this.cpUTF8[i10], Integer.valueOf(i10));
                i9++;
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str.substring(0, i10 > 1 ? decodeBandInt[i10 - 2] : 0));
                sb2.append(new String(cArr, i8, decodeBandInt2[i11]));
                strArr[i10] = sb2.toString();
                i8 += decodeBandInt2[i11];
                this.mapUTF8.put(this.cpUTF8[i10], Integer.valueOf(i10));
            }
            i10++;
        }
    }

    public String[] getCpClass() {
        return this.cpClass;
    }

    public String[] getCpDescriptor() {
        return this.cpDescriptor;
    }

    public String[] getCpFieldClass() {
        return this.cpFieldClass;
    }

    public String[] getCpIMethodClass() {
        return this.cpIMethodClass;
    }

    public int[] getCpInt() {
        return this.cpInt;
    }

    public long[] getCpLong() {
        return this.cpLong;
    }

    public String[] getCpMethodClass() {
        return this.cpMethodClass;
    }

    public String[] getCpMethodDescriptor() {
        return this.cpMethodDescriptor;
    }

    public String[] getCpSignature() {
        return this.cpSignature;
    }

    public String[] getCpUTF8() {
        return this.cpUTF8;
    }

    public CPUTF8 cpUTF8Value(int i) {
        String str = this.cpUTF8[i];
        CPUTF8 cputf8 = (CPUTF8) this.stringsToCPUTF8.get(str);
        if (cputf8 == null) {
            CPUTF8 cputf82 = new CPUTF8(str, i);
            this.stringsToCPUTF8.put(str, cputf82);
            return cputf82;
        }
        if (cputf8.getGlobalIndex() <= i) {
            return cputf8;
        }
        cputf8.setGlobalIndex(i);
        return cputf8;
    }

    public CPUTF8 cpUTF8Value(String str) {
        return cpUTF8Value(str, true);
    }

    public CPUTF8 cpUTF8Value(String str, boolean z) {
        CPUTF8 cputf8 = (CPUTF8) this.stringsToCPUTF8.get(str);
        if (cputf8 != null) {
            return cputf8;
        }
        Integer num = z ? (Integer) this.mapUTF8.get(str) : null;
        if (num != null) {
            return cpUTF8Value(num.intValue());
        }
        if (z) {
            num = (Integer) this.mapSignature.get(str);
        }
        if (num != null) {
            return cpSignatureValue(num.intValue());
        }
        CPUTF8 cputf82 = new CPUTF8(str, -1);
        this.stringsToCPUTF8.put(str, cputf82);
        return cputf82;
    }

    public CPString cpStringValue(int i) {
        String str = this.cpString[i];
        int i2 = this.cpStringInts[i];
        int i3 = this.stringOffset + i;
        CPString cPString = (CPString) this.stringsToCPStrings.get(str);
        if (cPString != null) {
            return cPString;
        }
        CPString cPString2 = new CPString(cpUTF8Value(i2), i3);
        this.stringsToCPStrings.put(str, cPString2);
        return cPString2;
    }

    public CPLong cpLongValue(int i) {
        Long valueOf = Long.valueOf(this.cpLong[i]);
        CPLong cPLong = (CPLong) this.longsToCPLongs.get(valueOf);
        if (cPLong != null) {
            return cPLong;
        }
        CPLong cPLong2 = new CPLong(valueOf, i + this.longOffset);
        this.longsToCPLongs.put(valueOf, cPLong2);
        return cPLong2;
    }

    public CPInteger cpIntegerValue(int i) {
        Integer valueOf = Integer.valueOf(this.cpInt[i]);
        CPInteger cPInteger = (CPInteger) this.integersToCPIntegers.get(valueOf);
        if (cPInteger != null) {
            return cPInteger;
        }
        CPInteger cPInteger2 = new CPInteger(valueOf, i + this.intOffset);
        this.integersToCPIntegers.put(valueOf, cPInteger2);
        return cPInteger2;
    }

    public CPFloat cpFloatValue(int i) {
        Float valueOf = Float.valueOf(this.cpFloat[i]);
        CPFloat cPFloat = (CPFloat) this.floatsToCPFloats.get(valueOf);
        if (cPFloat != null) {
            return cPFloat;
        }
        CPFloat cPFloat2 = new CPFloat(valueOf, i + this.floatOffset);
        this.floatsToCPFloats.put(valueOf, cPFloat2);
        return cPFloat2;
    }

    public CPClass cpClassValue(int i) {
        String str = this.cpClass[i];
        int i2 = this.cpClassInts[i];
        int i3 = this.classOffset + i;
        CPClass cPClass = (CPClass) this.stringsToCPClass.get(str);
        if (cPClass != null) {
            return cPClass;
        }
        CPClass cPClass2 = new CPClass(cpUTF8Value(i2), i3);
        this.stringsToCPClass.put(str, cPClass2);
        return cPClass2;
    }

    public CPClass cpClassValue(String str) {
        CPClass cPClass = (CPClass) this.stringsToCPClass.get(str);
        if (cPClass != null) {
            return cPClass;
        }
        Integer num = (Integer) this.mapClass.get(str);
        if (num != null) {
            return cpClassValue(num.intValue());
        }
        CPClass cPClass2 = new CPClass(cpUTF8Value(str, false), -1);
        this.stringsToCPClass.put(str, cPClass2);
        return cPClass2;
    }

    public CPDouble cpDoubleValue(int i) {
        Double valueOf = Double.valueOf(this.cpDouble[i]);
        CPDouble cPDouble = (CPDouble) this.doublesToCPDoubles.get(valueOf);
        if (cPDouble != null) {
            return cPDouble;
        }
        CPDouble cPDouble2 = new CPDouble(valueOf, i + this.doubleOffset);
        this.doublesToCPDoubles.put(valueOf, cPDouble2);
        return cPDouble2;
    }

    public CPNameAndType cpNameAndTypeValue(int i) {
        String str = this.cpDescriptor[i];
        CPNameAndType cPNameAndType = (CPNameAndType) this.descriptorsToCPNameAndTypes.get(str);
        if (cPNameAndType != null) {
            return cPNameAndType;
        }
        CPNameAndType cPNameAndType2 = new CPNameAndType(cpUTF8Value(this.cpDescriptorNameInts[i]), cpSignatureValue(this.cpDescriptorTypeInts[i]), i + this.descrOffset);
        this.descriptorsToCPNameAndTypes.put(str, cPNameAndType2);
        return cPNameAndType2;
    }

    public CPInterfaceMethodRef cpIMethodValue(int i) {
        return new CPInterfaceMethodRef(cpClassValue(this.cpIMethodClassInts[i]), cpNameAndTypeValue(this.cpIMethodDescriptorInts[i]), i + this.imethodOffset);
    }

    public CPMethodRef cpMethodValue(int i) {
        return new CPMethodRef(cpClassValue(this.cpMethodClassInts[i]), cpNameAndTypeValue(this.cpMethodDescriptorInts[i]), i + this.methodOffset);
    }

    public CPFieldRef cpFieldValue(int i) {
        return new CPFieldRef(cpClassValue(this.cpFieldClassInts[i]), cpNameAndTypeValue(this.cpFieldDescriptorInts[i]), i + this.fieldOffset);
    }

    public CPUTF8 cpSignatureValue(int i) {
        int i2;
        int[] iArr = this.cpSignatureInts;
        if (iArr[i] != -1) {
            i2 = iArr[i];
        } else {
            i2 = this.signatureOffset + i;
        }
        String str = this.cpSignature[i];
        CPUTF8 cputf8 = (CPUTF8) this.stringsToCPUTF8.get(str);
        if (cputf8 != null) {
            return cputf8;
        }
        CPUTF8 cputf82 = new CPUTF8(str, i2);
        this.stringsToCPUTF8.put(str, cputf82);
        return cputf82;
    }

    public CPNameAndType cpNameAndTypeValue(String str) {
        CPNameAndType cPNameAndType = (CPNameAndType) this.descriptorsToCPNameAndTypes.get(str);
        if (cPNameAndType != null) {
            return cPNameAndType;
        }
        Integer num = (Integer) this.mapDescriptor.get(str);
        if (num != null) {
            return cpNameAndTypeValue(num.intValue());
        }
        int indexOf = str.indexOf(58);
        CPNameAndType cPNameAndType2 = new CPNameAndType(cpUTF8Value(str.substring(0, indexOf), true), cpUTF8Value(str.substring(indexOf + 1), true), this.descrOffset - 1);
        this.descriptorsToCPNameAndTypes.put(str, cPNameAndType2);
        return cPNameAndType2;
    }

    public int[] getCpDescriptorNameInts() {
        return this.cpDescriptorNameInts;
    }

    public int[] getCpDescriptorTypeInts() {
        return this.cpDescriptorTypeInts;
    }
}
