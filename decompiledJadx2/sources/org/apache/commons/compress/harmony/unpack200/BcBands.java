package org.apache.commons.compress.harmony.unpack200;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.Attribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.CodeAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.ExceptionTableEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.NewAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;
import org.apache.http.HttpStatus;
import org.bouncycastle.math.Primes;
import org.jetbrains.anko.DimensionsKt;

/* loaded from: classes9.dex */
public class BcBands extends BandSet {
    private int[] bcByte;
    private int[] bcCaseCount;
    private int[] bcCaseValue;
    private int[] bcClassRef;
    private int[] bcDoubleRef;
    private int[][] bcEscByte;
    private int[] bcEscRef;
    private int[] bcEscRefSize;
    private int[] bcEscSize;
    private int[] bcFieldRef;
    private int[] bcFloatRef;
    private int[] bcIMethodRef;
    private int[] bcInitRef;
    private int[] bcIntRef;
    private int[] bcLabel;
    private int[] bcLocal;
    private int[] bcLongRef;
    private int[] bcMethodRef;
    private int[] bcShort;
    private int[] bcStringRef;
    private int[] bcSuperField;
    private int[] bcSuperMethod;
    private int[] bcThisField;
    private int[] bcThisMethod;
    private byte[][][] methodByteCodePacked;
    private List wideByteCodes;

    private boolean endsWithLoad(int i) {
        return i >= 21 && i <= 25;
    }

    private boolean endsWithStore(int i) {
        return i >= 54 && i <= 58;
    }

    private boolean startsWithIf(int i) {
        return (i >= 153 && i <= 166) || i == 198 || i == 199;
    }

    public BcBands(Segment segment) {
        super(segment);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0114, code lost:
    
        if (startsWithIf(r8) != false) goto L66;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:36:0x00f4. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:37:0x00f7. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:38:0x00fa. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:39:0x00fd. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:40:0x0100. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:63:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0131  */
    @Override // org.apache.commons.compress.harmony.unpack200.BandSet
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void read(InputStream inputStream) throws IOException, Pack200Exception {
        int i;
        AttributeLayoutMap attributeDefinitionMap = this.segment.getAttrDefinitionBands().getAttributeDefinitionMap();
        int classCount = this.header.getClassCount();
        long[][] methodFlags = this.segment.getClassBands().getMethodFlags();
        AttributeLayout attributeLayout = attributeDefinitionMap.getAttributeLayout(AttributeLayout.ACC_ABSTRACT, 2);
        AttributeLayout attributeLayout2 = attributeDefinitionMap.getAttributeLayout(AttributeLayout.ACC_NATIVE, 2);
        this.methodByteCodePacked = new byte[classCount][];
        ArrayList arrayList = new ArrayList();
        this.wideByteCodes = new ArrayList();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
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
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        int i21 = 0;
        int i22 = 0;
        int i23 = 0;
        while (i2 < classCount) {
            int length = methodFlags[i2].length;
            int i24 = classCount;
            this.methodByteCodePacked[i2] = new byte[length];
            int i25 = 0;
            while (i25 < length) {
                long[][] jArr = methodFlags;
                int i26 = length;
                long j = methodFlags[i2][i25];
                if (!attributeLayout.matches(j) && !attributeLayout2.matches(j)) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    while (true) {
                        byte read = (byte) (inputStream.read() & 255);
                        if (read != -1) {
                            byteArrayOutputStream.write(read);
                        } else {
                            this.methodByteCodePacked[i2][i25] = byteArrayOutputStream.toByteArray();
                            byte[][][] bArr = this.methodByteCodePacked;
                            int length2 = bArr[i2][i25].length;
                            int[] iArr = new int[bArr[i2][i25].length];
                            for (int i27 = 0; i27 < iArr.length; i27++) {
                                iArr[i27] = this.methodByteCodePacked[i2][i25][i27] & 255;
                            }
                            int i28 = 0;
                            while (true) {
                                byte[][][] bArr2 = this.methodByteCodePacked;
                                if (i28 < bArr2[i2][i25].length) {
                                    int i29 = bArr2[i2][i25][i28] & 255;
                                    AttributeLayout attributeLayout3 = attributeLayout2;
                                    if (i29 != 132) {
                                        if (i29 != 192 && i29 != 193) {
                                            if (i29 == 196) {
                                                int i30 = i28 + 1;
                                                int i31 = bArr2[i2][i25][i30] & 255;
                                                this.wideByteCodes.add(Integer.valueOf(i31));
                                                if (i31 == 132) {
                                                    i9++;
                                                    i8++;
                                                    i = i30;
                                                } else if (endsWithLoad(i31) || endsWithStore(i31) || i31 == 169) {
                                                    i = i30;
                                                    i9++;
                                                } else {
                                                    Segment segment = this.segment;
                                                    StringBuilder sb = new StringBuilder();
                                                    i = i30;
                                                    sb.append("Found unhandled ");
                                                    sb.append(ByteCode.getByteCode(i31));
                                                    segment.log(2, sb.toString());
                                                }
                                                i28 = i;
                                            } else if (i29 == 197) {
                                                i7++;
                                            } else if (i29 == 253) {
                                                i6++;
                                            } else if (i29 != 254) {
                                                switch (i29) {
                                                    case 16:
                                                        i7++;
                                                        break;
                                                    case 17:
                                                        i8++;
                                                        break;
                                                    case 18:
                                                    case 19:
                                                        i15++;
                                                        break;
                                                    case 20:
                                                        i13++;
                                                        break;
                                                    default:
                                                        switch (i29) {
                                                            case 167:
                                                            case 168:
                                                                i10++;
                                                                break;
                                                            case 169:
                                                                i9++;
                                                                break;
                                                            case 170:
                                                                arrayList.add(true);
                                                                i3++;
                                                                i10++;
                                                                break;
                                                            case 171:
                                                                arrayList.add(false);
                                                                i3++;
                                                                i10++;
                                                                break;
                                                            default:
                                                                switch (i29) {
                                                                    case 178:
                                                                    case 179:
                                                                    case 180:
                                                                    case 181:
                                                                        i17++;
                                                                        break;
                                                                    case 182:
                                                                    case 183:
                                                                    case 184:
                                                                        i18++;
                                                                        break;
                                                                    case 185:
                                                                        i19++;
                                                                        break;
                                                                    default:
                                                                        switch (i29) {
                                                                            case 187:
                                                                            case 189:
                                                                                break;
                                                                            case 188:
                                                                                break;
                                                                            default:
                                                                                switch (i29) {
                                                                                    case 200:
                                                                                    case 201:
                                                                                        break;
                                                                                    case 202:
                                                                                    case HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION /* 203 */:
                                                                                    case 204:
                                                                                    case HttpStatus.SC_RESET_CONTENT /* 205 */:
                                                                                    case 209:
                                                                                    case 210:
                                                                                    case Primes.SMALL_FACTOR_LIMIT /* 211 */:
                                                                                    case 212:
                                                                                        i20++;
                                                                                        break;
                                                                                    case HttpStatus.SC_PARTIAL_CONTENT /* 206 */:
                                                                                    case HttpStatus.SC_MULTI_STATUS /* 207 */:
                                                                                    case 208:
                                                                                    case DimensionsKt.TVDPI /* 213 */:
                                                                                    case 214:
                                                                                    case 215:
                                                                                        i22++;
                                                                                        break;
                                                                                    case 216:
                                                                                    case 217:
                                                                                    case 218:
                                                                                    case 219:
                                                                                    case 223:
                                                                                    case 224:
                                                                                    case 225:
                                                                                    case 226:
                                                                                        i21++;
                                                                                        break;
                                                                                    case 220:
                                                                                    case 221:
                                                                                    case 222:
                                                                                    case 227:
                                                                                    case 228:
                                                                                    case 229:
                                                                                        i23++;
                                                                                        break;
                                                                                    case 230:
                                                                                    case 231:
                                                                                    case 232:
                                                                                        i4++;
                                                                                        break;
                                                                                    case 233:
                                                                                    case 236:
                                                                                        break;
                                                                                    case 234:
                                                                                    case 237:
                                                                                        i11++;
                                                                                        break;
                                                                                    case 235:
                                                                                    case 238:
                                                                                        i12++;
                                                                                        break;
                                                                                    case 239:
                                                                                        i14++;
                                                                                        break;
                                                                                    default:
                                                                                        if (!endsWithLoad(i29) && !endsWithStore(i29)) {
                                                                                            break;
                                                                                        }
                                                                                        i9++;
                                                                                        break;
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                            } else {
                                                i5++;
                                            }
                                        }
                                        i16++;
                                    } else {
                                        i9++;
                                        i7++;
                                    }
                                    i28++;
                                    attributeLayout2 = attributeLayout3;
                                }
                            }
                        }
                    }
                }
                i25++;
                methodFlags = jArr;
                length = i26;
                attributeLayout2 = attributeLayout2;
            }
            i2++;
            classCount = i24;
        }
        this.bcCaseCount = decodeBandInt("bc_case_count", inputStream, Codec.UNSIGNED5, i3);
        int i32 = 0;
        for (int i33 = 0; i33 < this.bcCaseCount.length; i33++) {
            i32 = ((Boolean) arrayList.get(i33)).booleanValue() ? i32 + 1 : i32 + this.bcCaseCount[i33];
        }
        this.bcCaseValue = decodeBandInt("bc_case_value", inputStream, Codec.DELTA5, i32);
        int i34 = i10;
        for (int i35 = 0; i35 < i3; i35++) {
            i34 += this.bcCaseCount[i35];
        }
        this.bcByte = decodeBandInt("bc_byte", inputStream, Codec.BYTE1, i7);
        this.bcShort = decodeBandInt("bc_short", inputStream, Codec.DELTA5, i8);
        this.bcLocal = decodeBandInt("bc_local", inputStream, Codec.UNSIGNED5, i9);
        this.bcLabel = decodeBandInt("bc_label", inputStream, Codec.BRANCH5, i34);
        this.bcIntRef = decodeBandInt("bc_intref", inputStream, Codec.DELTA5, i11);
        this.bcFloatRef = decodeBandInt("bc_floatref", inputStream, Codec.DELTA5, i12);
        this.bcLongRef = decodeBandInt("bc_longref", inputStream, Codec.DELTA5, i13);
        this.bcDoubleRef = decodeBandInt("bc_doubleref", inputStream, Codec.DELTA5, i14);
        this.bcStringRef = decodeBandInt("bc_stringref", inputStream, Codec.DELTA5, i15);
        this.bcClassRef = decodeBandInt("bc_classref", inputStream, Codec.UNSIGNED5, i16);
        this.bcFieldRef = decodeBandInt("bc_fieldref", inputStream, Codec.DELTA5, i17);
        this.bcMethodRef = decodeBandInt("bc_methodref", inputStream, Codec.UNSIGNED5, i18);
        this.bcIMethodRef = decodeBandInt("bc_imethodref", inputStream, Codec.DELTA5, i19);
        this.bcThisField = decodeBandInt("bc_thisfield", inputStream, Codec.UNSIGNED5, i20);
        this.bcSuperField = decodeBandInt("bc_superfield", inputStream, Codec.UNSIGNED5, i21);
        this.bcThisMethod = decodeBandInt("bc_thismethod", inputStream, Codec.UNSIGNED5, i22);
        this.bcSuperMethod = decodeBandInt("bc_supermethod", inputStream, Codec.UNSIGNED5, i23);
        this.bcInitRef = decodeBandInt("bc_initref", inputStream, Codec.UNSIGNED5, i4);
        this.bcEscRef = decodeBandInt("bc_escref", inputStream, Codec.UNSIGNED5, i6);
        this.bcEscRefSize = decodeBandInt("bc_escrefsize", inputStream, Codec.UNSIGNED5, i6);
        this.bcEscSize = decodeBandInt("bc_escsize", inputStream, Codec.UNSIGNED5, i5);
        this.bcEscByte = decodeBandInt("bc_escbyte", inputStream, Codec.BYTE1, this.bcEscSize);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.BandSet
    public void unpack() throws Pack200Exception {
        int[] iArr;
        int[] iArr2;
        AttributeLayout attributeLayout;
        int[] iArr3;
        AttributeLayout attributeLayout2;
        AttributeLayout attributeLayout3;
        int i;
        int i2;
        int i3;
        int i4;
        List list;
        int classCount = this.header.getClassCount();
        long[][] methodFlags = this.segment.getClassBands().getMethodFlags();
        int[] codeMaxNALocals = this.segment.getClassBands().getCodeMaxNALocals();
        int[] codeMaxStack = this.segment.getClassBands().getCodeMaxStack();
        ArrayList[][] methodAttributes = this.segment.getClassBands().getMethodAttributes();
        String[][] methodDescr = this.segment.getClassBands().getMethodDescr();
        AttributeLayoutMap attributeDefinitionMap = this.segment.getAttrDefinitionBands().getAttributeDefinitionMap();
        AttributeLayout attributeLayout4 = attributeDefinitionMap.getAttributeLayout(AttributeLayout.ACC_ABSTRACT, 2);
        AttributeLayout attributeLayout5 = attributeDefinitionMap.getAttributeLayout(AttributeLayout.ACC_NATIVE, 2);
        AttributeLayout attributeLayout6 = attributeDefinitionMap.getAttributeLayout(AttributeLayout.ACC_STATIC, 2);
        int[] iArr4 = new int[this.wideByteCodes.size()];
        for (int i5 = 0; i5 < iArr4.length; i5++) {
            iArr4[i5] = ((Integer) this.wideByteCodes.get(i5)).intValue();
        }
        OperandManager operandManager = new OperandManager(this.bcCaseCount, this.bcCaseValue, this.bcByte, this.bcShort, this.bcLocal, this.bcLabel, this.bcIntRef, this.bcFloatRef, this.bcLongRef, this.bcDoubleRef, this.bcStringRef, this.bcClassRef, this.bcFieldRef, this.bcMethodRef, this.bcIMethodRef, this.bcThisField, this.bcSuperField, this.bcThisMethod, this.bcSuperMethod, this.bcInitRef, iArr4);
        operandManager.setSegment(this.segment);
        ArrayList orderedCodeAttributes = this.segment.getClassBands().getOrderedCodeAttributes();
        int[] codeHandlerCount = this.segment.getClassBands().getCodeHandlerCount();
        int[][] codeHandlerStartP = this.segment.getClassBands().getCodeHandlerStartP();
        int[][] codeHandlerEndPO = this.segment.getClassBands().getCodeHandlerEndPO();
        int[][] codeHandlerCatchPO = this.segment.getClassBands().getCodeHandlerCatchPO();
        int[][] codeHandlerClassRCN = this.segment.getClassBands().getCodeHandlerClassRCN();
        boolean hasAllCodeFlags = this.segment.getSegmentHeader().getOptions().hasAllCodeFlags();
        boolean[] codeHasAttributes = this.segment.getClassBands().getCodeHasAttributes();
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i8 < classCount) {
            int length = methodFlags[i8].length;
            int i9 = classCount;
            int i10 = i7;
            int i11 = 0;
            while (i11 < length) {
                int i12 = i10;
                long[][] jArr = methodFlags;
                long j = methodFlags[i8][i11];
                if (attributeLayout4.matches(j) || attributeLayout5.matches(j)) {
                    iArr = codeMaxNALocals;
                    iArr2 = codeMaxStack;
                    attributeLayout = attributeLayout6;
                    iArr3 = codeHandlerCount;
                    attributeLayout2 = attributeLayout4;
                    attributeLayout3 = attributeLayout5;
                    i = i11;
                    i2 = length;
                    i3 = i8;
                    i6 = i6;
                    i4 = i12;
                } else {
                    int i13 = codeMaxStack[i6];
                    int i14 = codeMaxNALocals[i6];
                    if (!attributeLayout6.matches(j)) {
                        i14++;
                    }
                    int countInvokeInterfaceArgs = i14 + SegmentUtils.countInvokeInterfaceArgs(methodDescr[i8][i11]);
                    String[] cpClass = this.segment.getCpBands().getCpClass();
                    iArr = codeMaxNALocals;
                    operandManager.setCurrentClass(cpClass[this.segment.getClassBands().getClassThisInts()[i8]]);
                    operandManager.setSuperClass(cpClass[this.segment.getClassBands().getClassSuperInts()[i8]]);
                    ArrayList arrayList = new ArrayList();
                    iArr2 = codeMaxStack;
                    if (codeHandlerCount != null) {
                        int i15 = 0;
                        while (i15 < codeHandlerCount[i6]) {
                            int i16 = codeHandlerClassRCN[i6][i15] - 1;
                            CPClass cPClass = null;
                            AttributeLayout attributeLayout7 = attributeLayout6;
                            if (i16 != -1) {
                                cPClass = this.segment.getCpBands().cpClassValue(i16);
                            }
                            arrayList.add(new ExceptionTableEntry(codeHandlerStartP[i6][i15], codeHandlerEndPO[i6][i15], codeHandlerCatchPO[i6][i15], cPClass));
                            i15++;
                            attributeLayout6 = attributeLayout7;
                            codeHandlerCount = codeHandlerCount;
                            attributeLayout4 = attributeLayout4;
                            attributeLayout5 = attributeLayout5;
                        }
                    }
                    attributeLayout = attributeLayout6;
                    iArr3 = codeHandlerCount;
                    attributeLayout2 = attributeLayout4;
                    attributeLayout3 = attributeLayout5;
                    int i17 = i6;
                    i = i11;
                    i2 = length;
                    i3 = i8;
                    CodeAttribute codeAttribute = new CodeAttribute(i13, countInvokeInterfaceArgs, this.methodByteCodePacked[i8][i11], this.segment, operandManager, arrayList);
                    ArrayList arrayList2 = methodAttributes[i3][i];
                    int i18 = 0;
                    for (int i19 = 0; i19 < arrayList2.size(); i19++) {
                        Attribute attribute = (Attribute) arrayList2.get(i19);
                        if (!(attribute instanceof NewAttribute) || ((NewAttribute) attribute).getLayoutIndex() >= 15) {
                            break;
                        }
                        i18++;
                    }
                    arrayList2.add(i18, codeAttribute);
                    codeAttribute.renumber(codeAttribute.byteCodeOffsets);
                    if (hasAllCodeFlags) {
                        list = (List) orderedCodeAttributes.get(i17);
                        i4 = i12;
                    } else if (codeHasAttributes[i17]) {
                        list = (List) orderedCodeAttributes.get(i12);
                        i4 = i12 + 1;
                    } else {
                        list = Collections.EMPTY_LIST;
                        i4 = i12;
                    }
                    for (int i20 = 0; i20 < list.size(); i20++) {
                        Attribute attribute2 = (Attribute) list.get(i20);
                        codeAttribute.addAttribute(attribute2);
                        if (attribute2.hasBCIRenumbering()) {
                            ((BCIRenumberedAttribute) attribute2).renumber(codeAttribute.byteCodeOffsets);
                        }
                    }
                    i6 = i17 + 1;
                }
                i11 = i + 1;
                i8 = i3;
                i10 = i4;
                length = i2;
                methodFlags = jArr;
                codeMaxNALocals = iArr;
                codeMaxStack = iArr2;
                attributeLayout6 = attributeLayout;
                codeHandlerCount = iArr3;
                attributeLayout4 = attributeLayout2;
                attributeLayout5 = attributeLayout3;
            }
            i7 = i10;
            i8++;
            classCount = i9;
            codeHandlerCount = codeHandlerCount;
        }
    }

    public byte[][][] getMethodByteCodePacked() {
        return this.methodByteCodePacked;
    }

    public int[] getBcCaseCount() {
        return this.bcCaseCount;
    }

    public int[] getBcCaseValue() {
        return this.bcCaseValue;
    }

    public int[] getBcByte() {
        return this.bcByte;
    }

    public int[] getBcClassRef() {
        return this.bcClassRef;
    }

    public int[] getBcDoubleRef() {
        return this.bcDoubleRef;
    }

    public int[] getBcFieldRef() {
        return this.bcFieldRef;
    }

    public int[] getBcFloatRef() {
        return this.bcFloatRef;
    }

    public int[] getBcIMethodRef() {
        return this.bcIMethodRef;
    }

    public int[] getBcInitRef() {
        return this.bcInitRef;
    }

    public int[] getBcIntRef() {
        return this.bcIntRef;
    }

    public int[] getBcLabel() {
        return this.bcLabel;
    }

    public int[] getBcLocal() {
        return this.bcLocal;
    }

    public int[] getBcLongRef() {
        return this.bcLongRef;
    }

    public int[] getBcMethodRef() {
        return this.bcMethodRef;
    }

    public int[] getBcShort() {
        return this.bcShort;
    }

    public int[] getBcStringRef() {
        return this.bcStringRef;
    }

    public int[] getBcSuperField() {
        return this.bcSuperField;
    }

    public int[] getBcSuperMethod() {
        return this.bcSuperMethod;
    }

    public int[] getBcThisField() {
        return this.bcThisField;
    }

    public int[] getBcThisMethod() {
        return this.bcThisMethod;
    }
}
