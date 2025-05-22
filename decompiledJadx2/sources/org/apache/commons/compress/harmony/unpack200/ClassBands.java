package org.apache.commons.compress.harmony.unpack200;

import androidx.exifinterface.media.ExifInterface;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.Attribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.ConstantValueAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.DeprecatedAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.EnclosingMethodAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.ExceptionsAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.LineNumberTableAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.LocalVariableTableAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.LocalVariableTypeTableAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.SignatureAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.SourceFileAttribute;
import org.simpleframework.xml.strategy.Name;

/* loaded from: classes9.dex */
public class ClassBands extends BandSet {
    private final AttributeLayoutMap attrMap;
    private long[] classAccessFlags;
    private ArrayList[] classAttributes;
    private final int classCount;
    private int[] classFieldCount;
    private long[] classFlags;
    private int[][] classInterfacesInts;
    private int[] classMethodCount;
    private int[] classSuperInts;
    private String[] classThis;
    private int[] classThisInts;
    private int[] classVersionMajor;
    private int[] classVersionMinor;
    private List[] codeAttributes;
    private int[][] codeHandlerCatchPO;
    private int[][] codeHandlerClassRCN;
    private int[] codeHandlerCount;
    private int[][] codeHandlerEndPO;
    private int[][] codeHandlerStartP;
    private boolean[] codeHasAttributes;
    private int[] codeMaxNALocals;
    private int[] codeMaxStack;
    private final CpBands cpBands;
    private long[][] fieldAccessFlags;
    private ArrayList[][] fieldAttributes;
    private String[][] fieldDescr;
    private int[][] fieldDescrInts;
    private long[][] fieldFlags;
    private IcTuple[][] icLocal;
    private long[][] methodAccessFlags;
    private int[] methodAttrCalls;
    private ArrayList[][] methodAttributes;
    private String[][] methodDescr;
    private int[][] methodDescrInts;
    private long[][] methodFlags;
    private final SegmentOptions options;

    @Override // org.apache.commons.compress.harmony.unpack200.BandSet
    public void unpack() {
    }

    public ClassBands(Segment segment) {
        super(segment);
        this.attrMap = segment.getAttrDefinitionBands().getAttributeDefinitionMap();
        this.cpBands = segment.getCpBands();
        this.classCount = this.header.getClassCount();
        this.options = this.header.getOptions();
    }

    @Override // org.apache.commons.compress.harmony.unpack200.BandSet
    public void read(InputStream inputStream) throws IOException, Pack200Exception {
        int classCount = this.header.getClassCount();
        this.classThisInts = decodeBandInt("class_this", inputStream, Codec.DELTA5, classCount);
        this.classThis = getReferences(this.classThisInts, this.cpBands.getCpClass());
        this.classSuperInts = decodeBandInt("class_super", inputStream, Codec.DELTA5, classCount);
        this.classInterfacesInts = decodeBandInt("class_interface", inputStream, Codec.DELTA5, decodeBandInt("class_interface_count", inputStream, Codec.DELTA5, classCount));
        this.classFieldCount = decodeBandInt("class_field_count", inputStream, Codec.DELTA5, classCount);
        this.classMethodCount = decodeBandInt("class_method_count", inputStream, Codec.DELTA5, classCount);
        parseFieldBands(inputStream);
        parseMethodBands(inputStream);
        parseClassAttrBands(inputStream);
        parseCodeBands(inputStream);
    }

    private void parseFieldBands(InputStream inputStream) throws IOException, Pack200Exception {
        this.fieldDescrInts = decodeBandInt("field_descr", inputStream, Codec.DELTA5, this.classFieldCount);
        this.fieldDescr = getReferences(this.fieldDescrInts, this.cpBands.getCpDescriptor());
        parseFieldAttrBands(inputStream);
    }

    private void parseFieldAttrBands(InputStream inputStream) throws IOException, Pack200Exception {
        int i;
        int[] iArr;
        int[] iArr2;
        int i2;
        InputStream inputStream2 = inputStream;
        this.fieldFlags = parseFlags("field_flags", inputStream, this.classFieldCount, Codec.UNSIGNED5, this.options.hasFieldFlagsHi());
        int[] decodeBandInt = decodeBandInt("field_attr_calls", inputStream2, Codec.UNSIGNED5, getCallCount(decodeBandInt("field_attr_indexes", inputStream2, Codec.UNSIGNED5, decodeBandInt("field_attr_count", inputStream2, Codec.UNSIGNED5, SegmentUtils.countBit16(this.fieldFlags))), this.fieldFlags, 1));
        this.fieldAttributes = new ArrayList[this.classCount];
        for (int i3 = 0; i3 < this.classCount; i3++) {
            this.fieldAttributes[i3] = new ArrayList[this.fieldFlags[i3].length];
            for (int i4 = 0; i4 < this.fieldFlags[i3].length; i4++) {
                this.fieldAttributes[i3][i4] = new ArrayList();
            }
        }
        AttributeLayout attributeLayout = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_CONSTANT_VALUE, 1);
        int[] decodeBandInt2 = decodeBandInt("field_ConstantValue_KQ", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(this.fieldFlags, attributeLayout));
        AttributeLayout attributeLayout2 = this.attrMap.getAttributeLayout("Signature", 1);
        int[] decodeBandInt3 = decodeBandInt("field_Signature_RS", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(this.fieldFlags, attributeLayout2));
        AttributeLayout attributeLayout3 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_DEPRECATED, 1);
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < this.classCount) {
            int i8 = i7;
            int i9 = i6;
            int i10 = 0;
            while (true) {
                long[][] jArr = this.fieldFlags;
                if (i10 < jArr[i5].length) {
                    long j = jArr[i5][i10];
                    if (attributeLayout3.matches(j)) {
                        this.fieldAttributes[i5][i10].add(new DeprecatedAttribute());
                    }
                    if (attributeLayout.matches(j)) {
                        iArr2 = decodeBandInt3;
                        long j2 = decodeBandInt2[i9];
                        String str = this.fieldDescr[i5][i10];
                        iArr = decodeBandInt2;
                        i2 = 58;
                        String substring = str.substring(str.indexOf(58) + 1);
                        if (substring.equals("B") || substring.equals(ExifInterface.LATITUDE_SOUTH) || substring.equals("C") || substring.equals("Z")) {
                            substring = "I";
                        }
                        this.fieldAttributes[i5][i10].add(new ConstantValueAttribute(attributeLayout.getValue(j2, substring, this.cpBands.getConstantPool())));
                        i9++;
                    } else {
                        iArr = decodeBandInt2;
                        iArr2 = decodeBandInt3;
                        i2 = 58;
                    }
                    if (attributeLayout2.matches(j)) {
                        long j3 = iArr2[i8];
                        String str2 = this.fieldDescr[i5][i10];
                        this.fieldAttributes[i5][i10].add(new SignatureAttribute((CPUTF8) attributeLayout2.getValue(j3, str2.substring(str2.indexOf(i2) + 1), this.cpBands.getConstantPool())));
                        i8++;
                    }
                    i10++;
                    decodeBandInt3 = iArr2;
                    decodeBandInt2 = iArr;
                }
            }
            i5++;
            inputStream2 = inputStream;
            i6 = i9;
            i7 = i8;
        }
        InputStream inputStream3 = inputStream2;
        int parseFieldMetadataBands = parseFieldMetadataBands(inputStream3, decodeBandInt);
        int i11 = this.options.hasFieldFlagsHi() ? 62 : 31;
        int i12 = i11 + 1;
        AttributeLayout[] attributeLayoutArr = new AttributeLayout[i12];
        int[] iArr3 = new int[i12];
        List[] listArr = new List[i12];
        for (int i13 = 0; i13 < i11; i13++) {
            AttributeLayout attributeLayout4 = this.attrMap.getAttributeLayout(i13, 1);
            if (attributeLayout4 != null && !attributeLayout4.isDefaultLayout()) {
                attributeLayoutArr[i13] = attributeLayout4;
                iArr3[i13] = SegmentUtils.countMatches(this.fieldFlags, attributeLayout4);
            }
        }
        int i14 = parseFieldMetadataBands;
        for (int i15 = 0; i15 < iArr3.length; i15++) {
            if (iArr3[i15] > 0) {
                NewAttributeBands attributeBands = this.attrMap.getAttributeBands(attributeLayoutArr[i15]);
                listArr[i15] = attributeBands.parseAttributes(inputStream3, iArr3[i15]);
                int numBackwardsCallables = attributeLayoutArr[i15].numBackwardsCallables();
                if (numBackwardsCallables > 0) {
                    int[] iArr4 = new int[numBackwardsCallables];
                    System.arraycopy(decodeBandInt, i14, iArr4, 0, numBackwardsCallables);
                    attributeBands.setBackwardsCalls(iArr4);
                    i14 += numBackwardsCallables;
                }
            }
        }
        for (int i16 = 0; i16 < this.classCount; i16++) {
            int i17 = 0;
            while (true) {
                long[][] jArr2 = this.fieldFlags;
                if (i17 < jArr2[i16].length) {
                    long j4 = jArr2[i16][i17];
                    int i18 = 0;
                    for (int i19 = 0; i19 < attributeLayoutArr.length; i19++) {
                        if (attributeLayoutArr[i19] != null && attributeLayoutArr[i19].matches(j4)) {
                            if (attributeLayoutArr[i19].getIndex() < 15) {
                                i = 0;
                                this.fieldAttributes[i16][i17].add(i18, listArr[i19].get(0));
                                i18++;
                            } else {
                                i = 0;
                                this.fieldAttributes[i16][i17].add(listArr[i19].get(0));
                            }
                            listArr[i19].remove(i);
                        }
                    }
                    i17++;
                }
            }
        }
    }

    private void parseMethodBands(InputStream inputStream) throws IOException, Pack200Exception {
        this.methodDescrInts = decodeBandInt("method_descr", inputStream, Codec.MDELTA5, this.classMethodCount);
        this.methodDescr = getReferences(this.methodDescrInts, this.cpBands.getCpDescriptor());
        parseMethodAttrBands(inputStream);
    }

    private void parseMethodAttrBands(InputStream inputStream) throws IOException, Pack200Exception {
        int i;
        AttributeLayout attributeLayout;
        int[][] iArr;
        this.methodFlags = parseFlags("method_flags", inputStream, this.classMethodCount, Codec.UNSIGNED5, this.options.hasMethodFlagsHi());
        this.methodAttrCalls = decodeBandInt("method_attr_calls", inputStream, Codec.UNSIGNED5, getCallCount(decodeBandInt("method_attr_indexes", inputStream, Codec.UNSIGNED5, decodeBandInt("method_attr_count", inputStream, Codec.UNSIGNED5, SegmentUtils.countBit16(this.methodFlags))), this.methodFlags, 2));
        this.methodAttributes = new ArrayList[this.classCount];
        for (int i2 = 0; i2 < this.classCount; i2++) {
            this.methodAttributes[i2] = new ArrayList[this.methodFlags[i2].length];
            for (int i3 = 0; i3 < this.methodFlags[i2].length; i3++) {
                this.methodAttributes[i2][i3] = new ArrayList();
            }
        }
        AttributeLayout attributeLayout2 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_EXCEPTIONS, 2);
        int[] decodeBandInt = decodeBandInt("method_Exceptions_n", inputStream, Codec.UNSIGNED5, SegmentUtils.countMatches(this.methodFlags, attributeLayout2));
        int[][] decodeBandInt2 = decodeBandInt("method_Exceptions_RC", inputStream, Codec.UNSIGNED5, decodeBandInt);
        AttributeLayout attributeLayout3 = this.attrMap.getAttributeLayout("Signature", 2);
        int[] decodeBandInt3 = decodeBandInt("method_signature_RS", inputStream, Codec.UNSIGNED5, SegmentUtils.countMatches(this.methodFlags, attributeLayout3));
        AttributeLayout attributeLayout4 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_DEPRECATED, 2);
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < this.methodAttributes.length) {
            int i7 = i6;
            int i8 = i5;
            int i9 = 0;
            while (i9 < this.methodAttributes[i4].length) {
                int[] iArr2 = decodeBandInt;
                long j = this.methodFlags[i4][i9];
                if (attributeLayout2.matches(j)) {
                    int i10 = iArr2[i8];
                    int[] iArr3 = decodeBandInt2[i8];
                    CPClass[] cPClassArr = new CPClass[i10];
                    int i11 = 0;
                    while (i11 < i10) {
                        cPClassArr[i11] = this.cpBands.cpClassValue(iArr3[i11]);
                        i11++;
                        attributeLayout2 = attributeLayout2;
                        decodeBandInt2 = decodeBandInt2;
                    }
                    attributeLayout = attributeLayout2;
                    iArr = decodeBandInt2;
                    this.methodAttributes[i4][i9].add(new ExceptionsAttribute(cPClassArr));
                    i8++;
                } else {
                    attributeLayout = attributeLayout2;
                    iArr = decodeBandInt2;
                }
                if (attributeLayout3.matches(j)) {
                    long j2 = decodeBandInt3[i7];
                    String str = this.methodDescr[i4][i9];
                    String substring = str.substring(str.indexOf(58) + 1);
                    if (substring.equals("B") || substring.equals("H")) {
                        substring = "I";
                    }
                    this.methodAttributes[i4][i9].add(new SignatureAttribute((CPUTF8) attributeLayout3.getValue(j2, substring, this.cpBands.getConstantPool())));
                    i7++;
                }
                if (attributeLayout4.matches(j)) {
                    this.methodAttributes[i4][i9].add(new DeprecatedAttribute());
                }
                i9++;
                decodeBandInt = iArr2;
                attributeLayout2 = attributeLayout;
                decodeBandInt2 = iArr;
            }
            i4++;
            i5 = i8;
            i6 = i7;
        }
        int parseMethodMetadataBands = parseMethodMetadataBands(inputStream, this.methodAttrCalls);
        int i12 = this.options.hasMethodFlagsHi() ? 62 : 31;
        int i13 = i12 + 1;
        AttributeLayout[] attributeLayoutArr = new AttributeLayout[i13];
        int[] iArr4 = new int[i13];
        List[] listArr = new List[i13];
        for (int i14 = 0; i14 < i12; i14++) {
            AttributeLayout attributeLayout5 = this.attrMap.getAttributeLayout(i14, 2);
            if (attributeLayout5 != null && !attributeLayout5.isDefaultLayout()) {
                attributeLayoutArr[i14] = attributeLayout5;
                iArr4[i14] = SegmentUtils.countMatches(this.methodFlags, attributeLayout5);
            }
        }
        int i15 = parseMethodMetadataBands;
        for (int i16 = 0; i16 < iArr4.length; i16++) {
            if (iArr4[i16] > 0) {
                NewAttributeBands attributeBands = this.attrMap.getAttributeBands(attributeLayoutArr[i16]);
                listArr[i16] = attributeBands.parseAttributes(inputStream, iArr4[i16]);
                int numBackwardsCallables = attributeLayoutArr[i16].numBackwardsCallables();
                if (numBackwardsCallables > 0) {
                    int[] iArr5 = new int[numBackwardsCallables];
                    System.arraycopy(this.methodAttrCalls, i15, iArr5, 0, numBackwardsCallables);
                    attributeBands.setBackwardsCalls(iArr5);
                    i15 += numBackwardsCallables;
                }
            }
        }
        for (int i17 = 0; i17 < this.methodAttributes.length; i17++) {
            for (int i18 = 0; i18 < this.methodAttributes[i17].length; i18++) {
                long j3 = this.methodFlags[i17][i18];
                int i19 = 0;
                for (int i20 = 0; i20 < attributeLayoutArr.length; i20++) {
                    if (attributeLayoutArr[i20] != null && attributeLayoutArr[i20].matches(j3)) {
                        if (attributeLayoutArr[i20].getIndex() < 15) {
                            i = 0;
                            this.methodAttributes[i17][i18].add(i19, listArr[i20].get(0));
                            i19++;
                        } else {
                            i = 0;
                            this.methodAttributes[i17][i18].add(listArr[i20].get(0));
                        }
                        listArr[i20].remove(i);
                    }
                }
            }
        }
    }

    private int getCallCount(int[][] iArr, long[][] jArr, int i) throws Pack200Exception {
        int i2 = 0;
        int i3 = 0;
        while (i2 < iArr.length) {
            int i4 = i3;
            for (int i5 = 0; i5 < iArr[i2].length; i5++) {
                i4 += this.attrMap.getAttributeLayout(iArr[i2][i5], i).numBackwardsCallables();
            }
            i2++;
            i3 = i4;
        }
        int i6 = 0;
        int i7 = 0;
        while (i6 < jArr.length) {
            int i8 = i7;
            for (int i9 = 0; i9 < jArr[i6].length; i9++) {
                i8 = (int) (i8 | jArr[i6][i9]);
            }
            i6++;
            i7 = i8;
        }
        for (int i10 = 0; i10 < 26; i10++) {
            if (((1 << i10) & i7) != 0) {
                i3 += this.attrMap.getAttributeLayout(i10, i).numBackwardsCallables();
            }
        }
        return i3;
    }

    private void parseClassAttrBands(InputStream inputStream) throws IOException, Pack200Exception {
        int i;
        AttributeLayout attributeLayout;
        AttributeLayout attributeLayout2;
        int[] iArr;
        int i2;
        AttributeLayout attributeLayout3;
        AttributeLayout attributeLayout4;
        int i3;
        AttributeLayout attributeLayout5;
        AttributeLayout attributeLayout6;
        AttributeLayout[] attributeLayoutArr;
        int i4;
        AttributeLayout attributeLayout7;
        AttributeLayout attributeLayout8;
        int i5;
        String str;
        String str2;
        int i6;
        int i7;
        int i8;
        int[] iArr2;
        AttributeLayout attributeLayout9;
        InputStream inputStream2 = inputStream;
        String[] cpUTF8 = this.cpBands.getCpUTF8();
        String[] cpClass = this.cpBands.getCpClass();
        this.classAttributes = new ArrayList[this.classCount];
        int i9 = 0;
        while (true) {
            i = this.classCount;
            if (i9 >= i) {
                break;
            }
            this.classAttributes[i9] = new ArrayList();
            i9++;
        }
        this.classFlags = parseFlags("class_flags", inputStream, i, Codec.UNSIGNED5, this.options.hasClassFlagsHi());
        int[] decodeBandInt = decodeBandInt("class_attr_calls", inputStream2, Codec.UNSIGNED5, getCallCount(decodeBandInt("class_attr_indexes", inputStream2, Codec.UNSIGNED5, decodeBandInt("class_attr_count", inputStream2, Codec.UNSIGNED5, SegmentUtils.countBit16(this.classFlags))), new long[][]{this.classFlags}, 0));
        AttributeLayout attributeLayout10 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_DEPRECATED, 0);
        AttributeLayout attributeLayout11 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_SOURCE_FILE, 0);
        int[] decodeBandInt2 = decodeBandInt("class_SourceFile_RUN", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(this.classFlags, attributeLayout11));
        AttributeLayout attributeLayout12 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_ENCLOSING_METHOD, 0);
        int countMatches = SegmentUtils.countMatches(this.classFlags, attributeLayout12);
        int[] decodeBandInt3 = decodeBandInt("class_EnclosingMethod_RC", inputStream2, Codec.UNSIGNED5, countMatches);
        int[] decodeBandInt4 = decodeBandInt("class_EnclosingMethod_RDN", inputStream2, Codec.UNSIGNED5, countMatches);
        AttributeLayout attributeLayout13 = this.attrMap.getAttributeLayout("Signature", 0);
        int[] decodeBandInt5 = decodeBandInt("class_Signature_RS", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(this.classFlags, attributeLayout13));
        int parseClassMetadataBands = parseClassMetadataBands(inputStream2, decodeBandInt);
        AttributeLayout attributeLayout14 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_INNER_CLASSES, 0);
        int[] decodeBandInt6 = decodeBandInt("class_InnerClasses_N", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(this.classFlags, attributeLayout14));
        int[][] decodeBandInt7 = decodeBandInt("class_InnerClasses_RC", inputStream2, Codec.UNSIGNED5, decodeBandInt6);
        int[][] decodeBandInt8 = decodeBandInt("class_InnerClasses_F", inputStream2, Codec.UNSIGNED5, decodeBandInt6);
        int i10 = 0;
        int i11 = 0;
        while (i11 < decodeBandInt8.length) {
            AttributeLayout attributeLayout15 = attributeLayout14;
            for (int i12 = 0; i12 < decodeBandInt8[i11].length; i12++) {
                if (decodeBandInt8[i11][i12] != 0) {
                    i10++;
                }
            }
            i11++;
            attributeLayout14 = attributeLayout15;
        }
        AttributeLayout attributeLayout16 = attributeLayout14;
        int[] decodeBandInt9 = decodeBandInt("class_InnerClasses_outer_RCN", inputStream2, Codec.UNSIGNED5, i10);
        int[] decodeBandInt10 = decodeBandInt("class_InnerClasses_name_RUN", inputStream2, Codec.UNSIGNED5, i10);
        AttributeLayout attributeLayout17 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_CLASS_FILE_VERSION, 0);
        int countMatches2 = SegmentUtils.countMatches(this.classFlags, attributeLayout17);
        AttributeLayout attributeLayout18 = attributeLayout17;
        int[] decodeBandInt11 = decodeBandInt("class_file_version_minor_H", inputStream2, Codec.UNSIGNED5, countMatches2);
        int[] decodeBandInt12 = decodeBandInt("class_file_version_major_H", inputStream2, Codec.UNSIGNED5, countMatches2);
        if (countMatches2 > 0) {
            int i13 = this.classCount;
            this.classVersionMajor = new int[i13];
            this.classVersionMinor = new int[i13];
        }
        int defaultClassMajorVersion = this.header.getDefaultClassMajorVersion();
        int defaultClassMinorVersion = this.header.getDefaultClassMinorVersion();
        int i14 = this.options.hasClassFlagsHi() ? 62 : 31;
        int i15 = i14 + 1;
        AttributeLayout[] attributeLayoutArr2 = new AttributeLayout[i15];
        int[] iArr3 = new int[i15];
        List[] listArr = new List[i15];
        int i16 = 0;
        while (i16 < i14) {
            int i17 = i14;
            int[] iArr4 = decodeBandInt5;
            AttributeLayout attributeLayout19 = this.attrMap.getAttributeLayout(i16, 0);
            if (attributeLayout19 != null && !attributeLayout19.isDefaultLayout()) {
                attributeLayoutArr2[i16] = attributeLayout19;
                iArr3[i16] = SegmentUtils.countMatches(this.classFlags, attributeLayout19);
            }
            i16++;
            i14 = i17;
            decodeBandInt5 = iArr4;
        }
        int[] iArr5 = decodeBandInt5;
        int i18 = parseClassMetadataBands;
        int i19 = 0;
        while (i19 < iArr3.length) {
            if (iArr3[i19] > 0) {
                attributeLayout9 = attributeLayout13;
                NewAttributeBands attributeBands = this.attrMap.getAttributeBands(attributeLayoutArr2[i19]);
                listArr[i19] = attributeBands.parseAttributes(inputStream2, iArr3[i19]);
                int numBackwardsCallables = attributeLayoutArr2[i19].numBackwardsCallables();
                if (numBackwardsCallables > 0) {
                    int[] iArr6 = new int[numBackwardsCallables];
                    iArr2 = iArr3;
                    System.arraycopy(decodeBandInt, i18, iArr6, 0, numBackwardsCallables);
                    attributeBands.setBackwardsCalls(iArr6);
                    i18 += numBackwardsCallables;
                } else {
                    iArr2 = iArr3;
                }
            } else {
                iArr2 = iArr3;
                attributeLayout9 = attributeLayout13;
            }
            i19++;
            inputStream2 = inputStream;
            attributeLayout13 = attributeLayout9;
            iArr3 = iArr2;
        }
        AttributeLayout attributeLayout20 = attributeLayout13;
        this.icLocal = new IcTuple[this.classCount];
        int i20 = 0;
        int i21 = 0;
        int i22 = 0;
        int i23 = 0;
        int i24 = 0;
        int i25 = 0;
        int i26 = 0;
        while (i21 < this.classCount) {
            long[] jArr = this.classFlags;
            int i27 = i26;
            AttributeLayout[] attributeLayoutArr3 = attributeLayoutArr2;
            long j = jArr[i21];
            int i28 = i25;
            List[] listArr2 = listArr;
            if (attributeLayout10.matches(jArr[i21])) {
                this.classAttributes[i21].add(new DeprecatedAttribute());
            }
            if (attributeLayout11.matches(j)) {
                iArr = decodeBandInt4;
                ClassFileEntry value = attributeLayout11.getValue(decodeBandInt2[i22], this.cpBands.getConstantPool());
                if (value == null) {
                    String[] strArr = this.classThis;
                    String substring = strArr[i21].substring(strArr[i21].lastIndexOf(47) + 1);
                    String substring2 = substring.substring(substring.lastIndexOf(46) + 1);
                    char[] charArray = substring2.toCharArray();
                    attributeLayout = attributeLayout10;
                    int i29 = 0;
                    while (true) {
                        if (i29 >= charArray.length) {
                            attributeLayout2 = attributeLayout11;
                            i8 = -1;
                            i29 = -1;
                            break;
                        } else {
                            attributeLayout2 = attributeLayout11;
                            if (charArray[i29] <= '-') {
                                i8 = -1;
                                break;
                            } else {
                                i29++;
                                attributeLayout11 = attributeLayout2;
                            }
                        }
                    }
                    if (i29 > i8) {
                        substring2 = substring2.substring(0, i29);
                    }
                    value = this.cpBands.cpUTF8Value(substring2 + ".java", true);
                } else {
                    attributeLayout = attributeLayout10;
                    attributeLayout2 = attributeLayout11;
                }
                this.classAttributes[i21].add(new SourceFileAttribute((CPUTF8) value));
                i22++;
            } else {
                attributeLayout = attributeLayout10;
                attributeLayout2 = attributeLayout11;
                iArr = decodeBandInt4;
            }
            if (attributeLayout12.matches(j)) {
                this.classAttributes[i21].add(new EnclosingMethodAttribute(this.cpBands.cpClassValue(decodeBandInt3[i23]), iArr[i23] != 0 ? this.cpBands.cpNameAndTypeValue(iArr[i23] - 1) : null));
                i23++;
            }
            AttributeLayout attributeLayout21 = attributeLayout20;
            if (attributeLayout21.matches(j)) {
                this.classAttributes[i21].add(new SignatureAttribute((CPUTF8) attributeLayout21.getValue(iArr5[i24], this.cpBands.getConstantPool())));
                i24++;
            }
            AttributeLayout attributeLayout22 = attributeLayout16;
            if (attributeLayout22.matches(j)) {
                this.icLocal[i21] = new IcTuple[decodeBandInt6[i28]];
                i3 = i20;
                int i30 = 0;
                while (i30 < this.icLocal[i21].length) {
                    int i31 = decodeBandInt7[i28][i30];
                    String str3 = cpClass[i31];
                    int i32 = decodeBandInt8[i28][i30];
                    if (i32 != 0) {
                        int i33 = decodeBandInt9[i3];
                        int i34 = decodeBandInt10[i3];
                        String str4 = cpClass[i33];
                        i3++;
                        i4 = i22;
                        attributeLayout7 = attributeLayout21;
                        attributeLayout8 = attributeLayout22;
                        i6 = i33;
                        i7 = i34;
                        str2 = cpUTF8[i34];
                        str = str4;
                        i5 = i32;
                    } else {
                        i4 = i22;
                        IcTuple[] icTuples = this.segment.getIcBands().getIcTuples();
                        attributeLayout7 = attributeLayout21;
                        attributeLayout8 = attributeLayout22;
                        int i35 = 0;
                        while (true) {
                            if (i35 >= icTuples.length) {
                                i5 = i32;
                                str = null;
                                str2 = null;
                                break;
                            } else {
                                if (icTuples[i35].getC().equals(str3)) {
                                    int f = icTuples[i35].getF();
                                    String c2 = icTuples[i35].getC2();
                                    str2 = icTuples[i35].getN();
                                    i5 = f;
                                    str = c2;
                                    break;
                                }
                                i35++;
                            }
                        }
                        i6 = -1;
                        i7 = -1;
                    }
                    this.icLocal[i21][i30] = new IcTuple(str3, i5, str, str2, i31, i6, i7, i30);
                    i30++;
                    i22 = i4;
                    attributeLayout21 = attributeLayout7;
                    attributeLayout22 = attributeLayout8;
                }
                i2 = i22;
                attributeLayout3 = attributeLayout21;
                attributeLayout4 = attributeLayout22;
                i25 = i28 + 1;
                attributeLayout5 = attributeLayout18;
            } else {
                i2 = i22;
                attributeLayout3 = attributeLayout21;
                attributeLayout4 = attributeLayout22;
                i3 = i20;
                attributeLayout5 = attributeLayout18;
                i25 = i28;
            }
            if (attributeLayout5.matches(j)) {
                this.classVersionMajor[i21] = decodeBandInt12[i27];
                this.classVersionMinor[i21] = decodeBandInt11[i27];
                i27++;
            } else {
                int[] iArr7 = this.classVersionMajor;
                if (iArr7 != null) {
                    iArr7[i21] = defaultClassMajorVersion;
                    this.classVersionMinor[i21] = defaultClassMinorVersion;
                }
            }
            AttributeLayout[] attributeLayoutArr4 = attributeLayoutArr3;
            int i36 = 0;
            while (i36 < attributeLayoutArr4.length) {
                if (attributeLayoutArr4[i36] == null || !attributeLayoutArr4[i36].matches(j)) {
                    attributeLayout6 = attributeLayout5;
                    attributeLayoutArr = attributeLayoutArr4;
                } else {
                    attributeLayout6 = attributeLayout5;
                    attributeLayoutArr = attributeLayoutArr4;
                    this.classAttributes[i21].add(listArr2[i36].get(0));
                    listArr2[i36].remove(0);
                }
                i36++;
                attributeLayout5 = attributeLayout6;
                attributeLayoutArr4 = attributeLayoutArr;
            }
            attributeLayout18 = attributeLayout5;
            i21++;
            i20 = i3;
            decodeBandInt4 = iArr;
            attributeLayoutArr2 = attributeLayoutArr4;
            i26 = i27;
            listArr = listArr2;
            attributeLayout10 = attributeLayout;
            attributeLayout11 = attributeLayout2;
            i22 = i2;
            attributeLayout20 = attributeLayout3;
            attributeLayout16 = attributeLayout4;
        }
    }

    private void parseCodeBands(InputStream inputStream) throws Pack200Exception, IOException {
        char c;
        char c2 = 2;
        int countMatches = SegmentUtils.countMatches(this.methodFlags, this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_CODE, 2));
        int[] decodeBandInt = decodeBandInt("code_headers", inputStream, Codec.BYTE1, countMatches);
        boolean hasAllCodeFlags = this.segment.getSegmentHeader().getOptions().hasAllCodeFlags();
        if (!hasAllCodeFlags) {
            this.codeHasAttributes = new boolean[countMatches];
        }
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < countMatches; i3++) {
            if (decodeBandInt[i3] == 0) {
                i2++;
                if (!hasAllCodeFlags) {
                    this.codeHasAttributes[i3] = true;
                }
            }
        }
        int[] decodeBandInt2 = decodeBandInt("code_max_stack", inputStream, Codec.UNSIGNED5, i2);
        int[] decodeBandInt3 = decodeBandInt("code_max_na_locals", inputStream, Codec.UNSIGNED5, i2);
        int[] decodeBandInt4 = decodeBandInt("code_handler_count", inputStream, Codec.UNSIGNED5, i2);
        this.codeMaxStack = new int[countMatches];
        this.codeMaxNALocals = new int[countMatches];
        this.codeHandlerCount = new int[countMatches];
        int i4 = 0;
        int i5 = 0;
        while (i4 < countMatches) {
            int i6 = decodeBandInt[i4] & 255;
            if (i6 < 0) {
                throw new IllegalStateException("Shouldn't get here");
            }
            if (i6 == 0) {
                this.codeMaxStack[i4] = decodeBandInt2[i5];
                this.codeMaxNALocals[i4] = decodeBandInt3[i5];
                this.codeHandlerCount[i4] = decodeBandInt4[i5];
                i5++;
                c = c2;
            } else {
                if (i6 <= 144) {
                    int i7 = i6 - 1;
                    this.codeMaxStack[i4] = i7 % 12;
                    this.codeMaxNALocals[i4] = i7 / 12;
                    this.codeHandlerCount[i4] = 0;
                } else if (i6 <= 208) {
                    int i8 = i6 - 145;
                    this.codeMaxStack[i4] = i8 % 8;
                    this.codeMaxNALocals[i4] = i8 / 8;
                    this.codeHandlerCount[i4] = 1;
                } else if (i6 <= 255) {
                    int i9 = i6 - 209;
                    this.codeMaxStack[i4] = i9 % 7;
                    this.codeMaxNALocals[i4] = i9 / 7;
                    c = 2;
                    this.codeHandlerCount[i4] = 2;
                } else {
                    throw new IllegalStateException("Shouldn't get here either");
                }
                c = 2;
            }
            i4++;
            c2 = c;
        }
        this.codeHandlerStartP = decodeBandInt("code_handler_start_P", inputStream, Codec.BCI5, this.codeHandlerCount);
        this.codeHandlerEndPO = decodeBandInt("code_handler_end_PO", inputStream, Codec.BRANCH5, this.codeHandlerCount);
        this.codeHandlerCatchPO = decodeBandInt("code_handler_catch_PO", inputStream, Codec.BRANCH5, this.codeHandlerCount);
        this.codeHandlerClassRCN = decodeBandInt("code_handler_class_RCN", inputStream, Codec.UNSIGNED5, this.codeHandlerCount);
        if (!hasAllCodeFlags) {
            countMatches = i2;
        }
        this.codeAttributes = new List[countMatches];
        while (true) {
            List[] listArr = this.codeAttributes;
            if (i < listArr.length) {
                listArr[i] = new ArrayList();
                i++;
            } else {
                parseCodeAttrBands(inputStream, countMatches);
                return;
            }
        }
    }

    private void parseCodeAttrBands(InputStream inputStream, int i) throws IOException, Pack200Exception {
        AttributeLayout attributeLayout;
        int[] iArr;
        int[][] iArr2;
        int[] iArr3;
        int[] iArr4;
        InputStream inputStream2 = inputStream;
        long[] parseFlags = parseFlags("code_flags", inputStream, i, Codec.UNSIGNED5, this.segment.getSegmentHeader().getOptions().hasCodeFlagsHi());
        int[][] decodeBandInt = decodeBandInt("code_attr_indexes", inputStream2, Codec.UNSIGNED5, decodeBandInt("code_attr_count", inputStream2, Codec.UNSIGNED5, SegmentUtils.countBit16(parseFlags)));
        int i2 = 0;
        int i3 = 0;
        while (i2 < decodeBandInt.length) {
            int i4 = i3;
            for (int i5 = 0; i5 < decodeBandInt[i2].length; i5++) {
                i4 += this.attrMap.getAttributeLayout(decodeBandInt[i2][i5], 3).numBackwardsCallables();
            }
            i2++;
            i3 = i4;
        }
        int[] decodeBandInt2 = decodeBandInt("code_attr_calls", inputStream2, Codec.UNSIGNED5, i3);
        AttributeLayout attributeLayout2 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_LINE_NUMBER_TABLE, 3);
        int[] decodeBandInt3 = decodeBandInt("code_LineNumberTable_N", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(parseFlags, attributeLayout2));
        int[][] decodeBandInt4 = decodeBandInt("code_LineNumberTable_bci_P", inputStream2, Codec.BCI5, decodeBandInt3);
        int[][] decodeBandInt5 = decodeBandInt("code_LineNumberTable_line", inputStream2, Codec.UNSIGNED5, decodeBandInt3);
        AttributeLayout attributeLayout3 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_LOCAL_VARIABLE_TABLE, 3);
        AttributeLayout attributeLayout4 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_LOCAL_VARIABLE_TYPE_TABLE, 3);
        int[] decodeBandInt6 = decodeBandInt("code_LocalVariableTable_N", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(parseFlags, attributeLayout3));
        int[][] decodeBandInt7 = decodeBandInt("code_LocalVariableTable_bci_P", inputStream2, Codec.BCI5, decodeBandInt6);
        int[][] decodeBandInt8 = decodeBandInt("code_LocalVariableTable_span_O", inputStream2, Codec.BRANCH5, decodeBandInt6);
        CPUTF8[][] parseCPUTF8References = parseCPUTF8References("code_LocalVariableTable_name_RU", inputStream2, Codec.UNSIGNED5, decodeBandInt6);
        CPUTF8[][] parseCPSignatureReferences = parseCPSignatureReferences("code_LocalVariableTable_type_RS", inputStream2, Codec.UNSIGNED5, decodeBandInt6);
        int[][] decodeBandInt9 = decodeBandInt("code_LocalVariableTable_slot", inputStream2, Codec.UNSIGNED5, decodeBandInt6);
        AttributeLayout attributeLayout5 = attributeLayout4;
        int[] decodeBandInt10 = decodeBandInt("code_LocalVariableTypeTable_N", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(parseFlags, attributeLayout4));
        int[][] decodeBandInt11 = decodeBandInt("code_LocalVariableTypeTable_bci_P", inputStream2, Codec.BCI5, decodeBandInt10);
        int[][] decodeBandInt12 = decodeBandInt("code_LocalVariableTypeTable_span_O", inputStream2, Codec.BRANCH5, decodeBandInt10);
        CPUTF8[][] parseCPUTF8References2 = parseCPUTF8References("code_LocalVariableTypeTable_name_RU", inputStream2, Codec.UNSIGNED5, decodeBandInt10);
        CPUTF8[][] parseCPSignatureReferences2 = parseCPSignatureReferences("code_LocalVariableTypeTable_type_RS", inputStream2, Codec.UNSIGNED5, decodeBandInt10);
        int[][] decodeBandInt13 = decodeBandInt("code_LocalVariableTypeTable_slot", inputStream2, Codec.UNSIGNED5, decodeBandInt10);
        int i6 = this.options.hasCodeFlagsHi() ? 62 : 31;
        int i7 = i6 + 1;
        AttributeLayout[] attributeLayoutArr = new AttributeLayout[i7];
        int[] iArr5 = new int[i7];
        List[] listArr = new List[i7];
        int i8 = 0;
        while (i8 < i6) {
            int i9 = i6;
            int[][] iArr6 = decodeBandInt7;
            AttributeLayout attributeLayout6 = this.attrMap.getAttributeLayout(i8, 3);
            if (attributeLayout6 != null && !attributeLayout6.isDefaultLayout()) {
                attributeLayoutArr[i8] = attributeLayout6;
                iArr5[i8] = SegmentUtils.countMatches(parseFlags, attributeLayout6);
            }
            i8++;
            i6 = i9;
            decodeBandInt7 = iArr6;
        }
        int[][] iArr7 = decodeBandInt7;
        int i10 = 0;
        int i11 = 0;
        while (i10 < iArr5.length) {
            if (iArr5[i10] > 0) {
                iArr4 = decodeBandInt6;
                NewAttributeBands attributeBands = this.attrMap.getAttributeBands(attributeLayoutArr[i10]);
                listArr[i10] = attributeBands.parseAttributes(inputStream2, iArr5[i10]);
                int numBackwardsCallables = attributeLayoutArr[i10].numBackwardsCallables();
                iArr3 = iArr5;
                if (numBackwardsCallables > 0) {
                    int[] iArr8 = new int[numBackwardsCallables];
                    System.arraycopy(decodeBandInt2, i11, iArr8, 0, numBackwardsCallables);
                    attributeBands.setBackwardsCalls(iArr8);
                    i11 += numBackwardsCallables;
                }
            } else {
                iArr3 = iArr5;
                iArr4 = decodeBandInt6;
            }
            i10++;
            inputStream2 = inputStream;
            decodeBandInt6 = iArr4;
            iArr5 = iArr3;
        }
        int[] iArr9 = decodeBandInt6;
        int i12 = i;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        while (i13 < i12) {
            if (attributeLayout2.matches(parseFlags[i13])) {
                attributeLayout = attributeLayout2;
                LineNumberTableAttribute lineNumberTableAttribute = new LineNumberTableAttribute(decodeBandInt3[i14], decodeBandInt4[i14], decodeBandInt5[i14]);
                i14++;
                this.codeAttributes[i13].add(lineNumberTableAttribute);
            } else {
                attributeLayout = attributeLayout2;
            }
            if (attributeLayout3.matches(parseFlags[i13])) {
                LocalVariableTableAttribute localVariableTableAttribute = new LocalVariableTableAttribute(iArr9[i15], iArr7[i15], decodeBandInt8[i15], parseCPUTF8References[i15], parseCPSignatureReferences[i15], decodeBandInt9[i15]);
                i15++;
                this.codeAttributes[i13].add(localVariableTableAttribute);
            }
            AttributeLayout attributeLayout7 = attributeLayout5;
            if (attributeLayout7.matches(parseFlags[i13])) {
                LocalVariableTypeTableAttribute localVariableTypeTableAttribute = new LocalVariableTypeTableAttribute(decodeBandInt10[i16], decodeBandInt11[i16], decodeBandInt12[i16], parseCPUTF8References2[i16], parseCPSignatureReferences2[i16], decodeBandInt13[i16]);
                i16++;
                this.codeAttributes[i13].add(localVariableTypeTableAttribute);
            }
            int i17 = 0;
            while (i17 < attributeLayoutArr.length) {
                if (attributeLayoutArr[i17] != null) {
                    iArr = decodeBandInt3;
                    iArr2 = decodeBandInt4;
                    if (attributeLayoutArr[i17].matches(parseFlags[i13])) {
                        this.codeAttributes[i13].add(listArr[i17].get(0));
                        listArr[i17].remove(0);
                        i17++;
                        decodeBandInt3 = iArr;
                        decodeBandInt4 = iArr2;
                    }
                } else {
                    iArr = decodeBandInt3;
                    iArr2 = decodeBandInt4;
                }
                i17++;
                decodeBandInt3 = iArr;
                decodeBandInt4 = iArr2;
            }
            i13++;
            attributeLayout2 = attributeLayout;
            attributeLayout5 = attributeLayout7;
            i12 = i;
        }
    }

    private int parseFieldMetadataBands(InputStream inputStream, int[] iArr) throws Pack200Exception, IOException {
        int i;
        String[] strArr = {"RVA", "RIA"};
        AttributeLayout attributeLayout = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_ANNOTATIONS, 1);
        AttributeLayout attributeLayout2 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_ANNOTATIONS, 1);
        int countMatches = SegmentUtils.countMatches(this.fieldFlags, attributeLayout);
        int countMatches2 = SegmentUtils.countMatches(this.fieldFlags, attributeLayout2);
        int[] iArr2 = {countMatches, countMatches2};
        int[] iArr3 = {0, 0};
        if (countMatches > 0) {
            iArr3[0] = iArr[0];
            if (countMatches2 > 0) {
                iArr3[1] = iArr[1];
                i = 2;
            }
            i = 1;
        } else if (countMatches2 > 0) {
            iArr3[1] = iArr[0];
            i = 1;
        } else {
            i = 0;
        }
        MetadataBandGroup[] parseMetadata = parseMetadata(inputStream, strArr, iArr2, iArr3, "field");
        List attributes = parseMetadata[0].getAttributes();
        List attributes2 = parseMetadata[1].getAttributes();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < this.fieldFlags.length) {
            int i5 = i4;
            int i6 = i3;
            int i7 = 0;
            while (true) {
                long[][] jArr = this.fieldFlags;
                if (i7 < jArr[i2].length) {
                    if (attributeLayout.matches(jArr[i2][i7])) {
                        this.fieldAttributes[i2][i7].add(attributes.get(i6));
                        i6++;
                    }
                    if (attributeLayout2.matches(this.fieldFlags[i2][i7])) {
                        this.fieldAttributes[i2][i7].add(attributes2.get(i5));
                        i5++;
                    }
                    i7++;
                }
            }
            i2++;
            i3 = i6;
            i4 = i5;
        }
        return i;
    }

    private MetadataBandGroup[] parseMetadata(InputStream inputStream, String[] strArr, int[] iArr, int[] iArr2, String str) throws IOException, Pack200Exception {
        int i;
        ClassBands classBands = this;
        InputStream inputStream2 = inputStream;
        String[] strArr2 = strArr;
        MetadataBandGroup[] metadataBandGroupArr = new MetadataBandGroup[strArr2.length];
        int i2 = 0;
        while (i2 < strArr2.length) {
            metadataBandGroupArr[i2] = new MetadataBandGroup(strArr2[i2], classBands.cpBands);
            String str2 = strArr2[i2];
            if (str2.indexOf(80) >= 0) {
                metadataBandGroupArr[i2].param_NB = classBands.decodeBandInt(str + "_" + str2 + "_param_NB", inputStream2, Codec.BYTE1, iArr[i2]);
            }
            if (!str2.equals("AD")) {
                metadataBandGroupArr[i2].anno_N = classBands.decodeBandInt(str + "_" + str2 + "_anno_N", inputStream2, Codec.UNSIGNED5, iArr[i2]);
                metadataBandGroupArr[i2].type_RS = classBands.parseCPSignatureReferences(str + "_" + str2 + "_type_RS", inputStream2, Codec.UNSIGNED5, metadataBandGroupArr[i2].anno_N);
                metadataBandGroupArr[i2].pair_N = classBands.decodeBandInt(str + "_" + str2 + "_pair_N", inputStream2, Codec.UNSIGNED5, metadataBandGroupArr[i2].anno_N);
                int i3 = 0;
                i = 0;
                while (i3 < metadataBandGroupArr[i2].pair_N.length) {
                    int i4 = i;
                    for (int i5 = 0; i5 < metadataBandGroupArr[i2].pair_N[i3].length; i5++) {
                        i4 += metadataBandGroupArr[i2].pair_N[i3][i5];
                    }
                    i3++;
                    i = i4;
                }
                metadataBandGroupArr[i2].name_RU = classBands.parseCPUTF8References(str + "_" + str2 + "_name_RU", inputStream2, Codec.UNSIGNED5, i);
            } else {
                i = iArr[i2];
            }
            metadataBandGroupArr[i2].f8969T = classBands.decodeBandInt(str + "_" + str2 + "_T", inputStream2, Codec.BYTE1, i + iArr2[i2]);
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            int i11 = 0;
            int i12 = 0;
            int i13 = 0;
            int i14 = 0;
            int i15 = 0;
            while (i6 < metadataBandGroupArr[i2].f8969T.length) {
                char c = (char) metadataBandGroupArr[i2].f8969T[i6];
                String str3 = str2;
                if (c == '@') {
                    i12++;
                } else if (c != 'F') {
                    if (c != 'S') {
                        if (c == 'c') {
                            i11++;
                        } else if (c == 'e') {
                            i15++;
                        } else if (c == 's') {
                            i14++;
                        } else if (c != 'I') {
                            if (c == 'J') {
                                i10++;
                            } else if (c != 'Z') {
                                if (c != '[') {
                                    switch (c) {
                                        case 'D':
                                            i8++;
                                            break;
                                    }
                                } else {
                                    i13++;
                                }
                            }
                        }
                    }
                    i7++;
                } else {
                    i9++;
                }
                i6++;
                str2 = str3;
            }
            MetadataBandGroup metadataBandGroup = metadataBandGroupArr[i2];
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("_");
            sb.append(str2);
            int i16 = i12;
            sb.append("_caseI_KI");
            metadataBandGroup.caseI_KI = parseCPIntReferences(sb.toString(), inputStream, Codec.UNSIGNED5, i7);
            metadataBandGroupArr[i2].caseD_KD = parseCPDoubleReferences(str + "_" + str2 + "_caseD_KD", inputStream, Codec.UNSIGNED5, i8);
            metadataBandGroupArr[i2].caseF_KF = parseCPFloatReferences(str + "_" + str2 + "_caseF_KF", inputStream, Codec.UNSIGNED5, i9);
            metadataBandGroupArr[i2].caseJ_KJ = parseCPLongReferences(str + "_" + str2 + "_caseJ_KJ", inputStream, Codec.UNSIGNED5, i10);
            metadataBandGroupArr[i2].casec_RS = parseCPSignatureReferences(str + "_" + str2 + "_casec_RS", inputStream, Codec.UNSIGNED5, i11);
            int i17 = i15;
            metadataBandGroupArr[i2].caseet_RS = parseReferences(str + "_" + str2 + "_caseet_RS", inputStream, Codec.UNSIGNED5, i17, this.cpBands.getCpSignature());
            metadataBandGroupArr[i2].caseec_RU = parseReferences(str + "_" + str2 + "_caseec_RU", inputStream, Codec.UNSIGNED5, i17, this.cpBands.getCpUTF8());
            metadataBandGroupArr[i2].cases_RU = parseCPUTF8References(str + "_" + str2 + "_cases_RU", inputStream, Codec.UNSIGNED5, i14);
            metadataBandGroupArr[i2].casearray_N = decodeBandInt(str + "_" + str2 + "_casearray_N", inputStream, Codec.UNSIGNED5, i13);
            metadataBandGroupArr[i2].nesttype_RS = parseCPUTF8References(str + "_" + str2 + "_nesttype_RS", inputStream, Codec.UNSIGNED5, i16);
            metadataBandGroupArr[i2].nestpair_N = decodeBandInt(str + "_" + str2 + "_nestpair_N", inputStream, Codec.UNSIGNED5, i16);
            int i18 = 0;
            for (int i19 = 0; i19 < metadataBandGroupArr[i2].nestpair_N.length; i19++) {
                i18 += metadataBandGroupArr[i2].nestpair_N[i19];
            }
            metadataBandGroupArr[i2].nestname_RU = parseCPUTF8References(str + "_" + str2 + "_nestname_RU", inputStream, Codec.UNSIGNED5, i18);
            i2++;
            classBands = this;
            inputStream2 = inputStream;
            strArr2 = strArr;
        }
        return metadataBandGroupArr;
    }

    private int parseMethodMetadataBands(InputStream inputStream, int[] iArr) throws Pack200Exception, IOException {
        String[] strArr = {"RVA", "RIA", "RVPA", "RIPA", "AD"};
        int[] iArr2 = {0, 0, 0, 0, 0};
        AttributeLayout[] attributeLayoutArr = {this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_ANNOTATIONS, 2), this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_ANNOTATIONS, 2), this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS, 2), this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS, 2), this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_ANNOTATION_DEFAULT, 2)};
        for (int i = 0; i < attributeLayoutArr.length; i++) {
            iArr2[i] = SegmentUtils.countMatches(this.methodFlags, attributeLayoutArr[i]);
        }
        int[] iArr3 = new int[5];
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < iArr3.length; i4++) {
            if (iArr2[i4] > 0) {
                i2++;
                iArr3[i4] = iArr[i3];
                i3++;
            } else {
                iArr3[i4] = 0;
            }
        }
        MetadataBandGroup[] parseMetadata = parseMetadata(inputStream, strArr, iArr2, iArr3, "method");
        List[] listArr = new List[strArr.length];
        int[] iArr4 = new int[strArr.length];
        for (int i5 = 0; i5 < parseMetadata.length; i5++) {
            listArr[i5] = parseMetadata[i5].getAttributes();
            iArr4[i5] = 0;
        }
        for (int i6 = 0; i6 < this.methodFlags.length; i6++) {
            for (int i7 = 0; i7 < this.methodFlags[i6].length; i7++) {
                for (int i8 = 0; i8 < attributeLayoutArr.length; i8++) {
                    if (attributeLayoutArr[i8].matches(this.methodFlags[i6][i7])) {
                        ArrayList arrayList = this.methodAttributes[i6][i7];
                        List list = listArr[i8];
                        int i9 = iArr4[i8];
                        iArr4[i8] = i9 + 1;
                        arrayList.add(list.get(i9));
                    }
                }
            }
        }
        return i2;
    }

    private int parseClassMetadataBands(InputStream inputStream, int[] iArr) throws Pack200Exception, IOException {
        int i;
        String[] strArr = {"RVA", "RIA"};
        int i2 = 0;
        AttributeLayout attributeLayout = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_ANNOTATIONS, 0);
        AttributeLayout attributeLayout2 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_ANNOTATIONS, 0);
        int countMatches = SegmentUtils.countMatches(this.classFlags, attributeLayout);
        int countMatches2 = SegmentUtils.countMatches(this.classFlags, attributeLayout2);
        int[] iArr2 = {countMatches, countMatches2};
        int[] iArr3 = {0, 0};
        if (countMatches > 0) {
            iArr3[0] = iArr[0];
            if (countMatches2 > 0) {
                iArr3[1] = iArr[1];
                i = 2;
            }
            i = 1;
        } else if (countMatches2 > 0) {
            iArr3[1] = iArr[0];
            i = 1;
        } else {
            i = 0;
        }
        MetadataBandGroup[] parseMetadata = parseMetadata(inputStream, strArr, iArr2, iArr3, Name.LABEL);
        List attributes = parseMetadata[0].getAttributes();
        List attributes2 = parseMetadata[1].getAttributes();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            long[] jArr = this.classFlags;
            if (i2 >= jArr.length) {
                return i;
            }
            if (attributeLayout.matches(jArr[i2])) {
                this.classAttributes[i2].add(attributes.get(i3));
                i3++;
            }
            if (attributeLayout2.matches(this.classFlags[i2])) {
                this.classAttributes[i2].add(attributes2.get(i4));
                i4++;
            }
            i2++;
        }
    }

    public ArrayList[] getClassAttributes() {
        return this.classAttributes;
    }

    public int[] getClassFieldCount() {
        return this.classFieldCount;
    }

    public long[] getRawClassFlags() {
        return this.classFlags;
    }

    public long[] getClassFlags() throws Pack200Exception {
        if (this.classAccessFlags == null) {
            int i = 0;
            long j = 32767;
            for (int i2 = 0; i2 < 16; i2++) {
                AttributeLayout attributeLayout = this.attrMap.getAttributeLayout(i2, 0);
                if (attributeLayout != null && !attributeLayout.isDefaultLayout()) {
                    j &= ~(1 << i2);
                }
            }
            this.classAccessFlags = new long[this.classFlags.length];
            while (true) {
                long[] jArr = this.classFlags;
                if (i >= jArr.length) {
                    break;
                }
                this.classAccessFlags[i] = jArr[i] & j;
                i++;
            }
        }
        return this.classAccessFlags;
    }

    public int[][] getClassInterfacesInts() {
        return this.classInterfacesInts;
    }

    public int[] getClassMethodCount() {
        return this.classMethodCount;
    }

    public int[] getClassSuperInts() {
        return this.classSuperInts;
    }

    public int[] getClassThisInts() {
        return this.classThisInts;
    }

    public int[] getCodeMaxNALocals() {
        return this.codeMaxNALocals;
    }

    public int[] getCodeMaxStack() {
        return this.codeMaxStack;
    }

    public ArrayList[][] getFieldAttributes() {
        return this.fieldAttributes;
    }

    public int[][] getFieldDescrInts() {
        return this.fieldDescrInts;
    }

    public int[][] getMethodDescrInts() {
        return this.methodDescrInts;
    }

    public long[][] getFieldFlags() throws Pack200Exception {
        if (this.fieldAccessFlags == null) {
            long j = 32767;
            for (int i = 0; i < 16; i++) {
                AttributeLayout attributeLayout = this.attrMap.getAttributeLayout(i, 1);
                if (attributeLayout != null && !attributeLayout.isDefaultLayout()) {
                    j &= ~(1 << i);
                }
            }
            this.fieldAccessFlags = new long[this.fieldFlags.length];
            int i2 = 0;
            while (true) {
                long[][] jArr = this.fieldFlags;
                if (i2 >= jArr.length) {
                    break;
                }
                this.fieldAccessFlags[i2] = new long[jArr[i2].length];
                int i3 = 0;
                while (true) {
                    long[][] jArr2 = this.fieldFlags;
                    if (i3 < jArr2[i2].length) {
                        this.fieldAccessFlags[i2][i3] = jArr2[i2][i3] & j;
                        i3++;
                    }
                }
                i2++;
            }
        }
        return this.fieldAccessFlags;
    }

    public ArrayList getOrderedCodeAttributes() {
        ArrayList arrayList = new ArrayList(this.codeAttributes.length);
        int i = 0;
        while (true) {
            List[] listArr = this.codeAttributes;
            if (i >= listArr.length) {
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList(listArr[i].size());
            for (int i2 = 0; i2 < this.codeAttributes[i].size(); i2++) {
                arrayList2.add((Attribute) this.codeAttributes[i].get(i2));
            }
            arrayList.add(arrayList2);
            i++;
        }
    }

    public ArrayList[][] getMethodAttributes() {
        return this.methodAttributes;
    }

    public String[][] getMethodDescr() {
        return this.methodDescr;
    }

    public long[][] getMethodFlags() throws Pack200Exception {
        if (this.methodAccessFlags == null) {
            long j = 32767;
            for (int i = 0; i < 16; i++) {
                AttributeLayout attributeLayout = this.attrMap.getAttributeLayout(i, 2);
                if (attributeLayout != null && !attributeLayout.isDefaultLayout()) {
                    j &= ~(1 << i);
                }
            }
            this.methodAccessFlags = new long[this.methodFlags.length];
            int i2 = 0;
            while (true) {
                long[][] jArr = this.methodFlags;
                if (i2 >= jArr.length) {
                    break;
                }
                this.methodAccessFlags[i2] = new long[jArr[i2].length];
                int i3 = 0;
                while (true) {
                    long[][] jArr2 = this.methodFlags;
                    if (i3 < jArr2[i2].length) {
                        this.methodAccessFlags[i2][i3] = jArr2[i2][i3] & j;
                        i3++;
                    }
                }
                i2++;
            }
        }
        return this.methodAccessFlags;
    }

    public int[] getClassVersionMajor() {
        return this.classVersionMajor;
    }

    public int[] getClassVersionMinor() {
        return this.classVersionMinor;
    }

    public int[] getCodeHandlerCount() {
        return this.codeHandlerCount;
    }

    public int[][] getCodeHandlerCatchPO() {
        return this.codeHandlerCatchPO;
    }

    public int[][] getCodeHandlerClassRCN() {
        return this.codeHandlerClassRCN;
    }

    public int[][] getCodeHandlerEndPO() {
        return this.codeHandlerEndPO;
    }

    public int[][] getCodeHandlerStartP() {
        return this.codeHandlerStartP;
    }

    public IcTuple[][] getIcLocal() {
        return this.icLocal;
    }

    public boolean[] getCodeHasAttributes() {
        return this.codeHasAttributes;
    }
}
