package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.objectweb.asm.Label;

/* loaded from: classes9.dex */
public class BcBands extends BandSet {
    private static final int ALOAD_0 = 42;
    private static final int IINC = 132;
    private static final int INVOKEINTERFACE = 185;
    private static final int LOOKUPSWITCH = 171;
    private static final int MULTIANEWARRAY = 197;
    private static final int TABLESWITCH = 170;
    private static final int WIDE = 196;
    private static final int endMarker = 255;
    private final IntList bcByte;
    private final IntList bcCaseCount;
    private final IntList bcCaseValue;
    private final List bcClassRef;
    private final IntList bcCodes;
    private final List bcDoubleRef;
    private final List bcFieldRef;
    private final List bcFloatRef;
    private final List bcIMethodRef;
    private List bcInitRef;
    private final List bcIntref;
    private final List bcLabel;
    private final IntList bcLabelRelativeOffsets;
    private final IntList bcLocal;
    private final List bcLongRef;
    private final List bcMethodRef;
    private final IntList bcShort;
    private final List bcStringRef;
    private final List bcSuperField;
    private List bcSuperMethod;
    private List bcThisField;
    private List bcThisMethod;
    private final IntList bciRenumbering;
    private int byteCodeOffset;
    private final CpBands cpBands;
    private String currentClass;
    private String currentNewClass;
    private final Map labelsToOffsets;
    private int renumberedOffset;
    private final Segment segment;
    private String superClass;

    public BcBands(CpBands cpBands, Segment segment, int i) {
        super(i, segment.getSegmentHeader());
        this.bcCodes = new IntList();
        this.bcCaseCount = new IntList();
        this.bcCaseValue = new IntList();
        this.bcByte = new IntList();
        this.bcShort = new IntList();
        this.bcLocal = new IntList();
        this.bcLabel = new ArrayList();
        this.bcIntref = new ArrayList();
        this.bcFloatRef = new ArrayList();
        this.bcLongRef = new ArrayList();
        this.bcDoubleRef = new ArrayList();
        this.bcStringRef = new ArrayList();
        this.bcClassRef = new ArrayList();
        this.bcFieldRef = new ArrayList();
        this.bcMethodRef = new ArrayList();
        this.bcIMethodRef = new ArrayList();
        this.bcThisField = new ArrayList();
        this.bcSuperField = new ArrayList();
        this.bcThisMethod = new ArrayList();
        this.bcSuperMethod = new ArrayList();
        this.bcInitRef = new ArrayList();
        this.bciRenumbering = new IntList();
        this.labelsToOffsets = new HashMap();
        this.bcLabelRelativeOffsets = new IntList();
        this.cpBands = cpBands;
        this.segment = segment;
    }

    public void setCurrentClass(String str, String str2) {
        this.currentClass = str;
        this.superClass = str2;
    }

    public void finaliseBands() {
        this.bcThisField = getIndexInClass(this.bcThisField);
        this.bcThisMethod = getIndexInClass(this.bcThisMethod);
        this.bcSuperMethod = getIndexInClass(this.bcSuperMethod);
        this.bcInitRef = getIndexInClassForConstructor(this.bcInitRef);
    }

    @Override // org.apache.commons.compress.harmony.pack200.BandSet
    public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
        PackingUtils.log("Writing byte code bands...");
        byte[] encodeBandInt = encodeBandInt("bcCodes", this.bcCodes.toArray(), Codec.BYTE1);
        outputStream.write(encodeBandInt);
        PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from bcCodes[" + this.bcCodes.size() + "]");
        byte[] encodeBandInt2 = encodeBandInt("bcCaseCount", this.bcCaseCount.toArray(), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt2);
        PackingUtils.log("Wrote " + encodeBandInt2.length + " bytes from bcCaseCount[" + this.bcCaseCount.size() + "]");
        byte[] encodeBandInt3 = encodeBandInt("bcCaseValue", this.bcCaseValue.toArray(), Codec.DELTA5);
        outputStream.write(encodeBandInt3);
        PackingUtils.log("Wrote " + encodeBandInt3.length + " bytes from bcCaseValue[" + this.bcCaseValue.size() + "]");
        byte[] encodeBandInt4 = encodeBandInt("bcByte", this.bcByte.toArray(), Codec.BYTE1);
        outputStream.write(encodeBandInt4);
        PackingUtils.log("Wrote " + encodeBandInt4.length + " bytes from bcByte[" + this.bcByte.size() + "]");
        byte[] encodeBandInt5 = encodeBandInt("bcShort", this.bcShort.toArray(), Codec.DELTA5);
        outputStream.write(encodeBandInt5);
        PackingUtils.log("Wrote " + encodeBandInt5.length + " bytes from bcShort[" + this.bcShort.size() + "]");
        byte[] encodeBandInt6 = encodeBandInt("bcLocal", this.bcLocal.toArray(), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt6);
        PackingUtils.log("Wrote " + encodeBandInt6.length + " bytes from bcLocal[" + this.bcLocal.size() + "]");
        byte[] encodeBandInt7 = encodeBandInt("bcLabel", integerListToArray(this.bcLabel), Codec.BRANCH5);
        outputStream.write(encodeBandInt7);
        PackingUtils.log("Wrote " + encodeBandInt7.length + " bytes from bcLabel[" + this.bcLabel.size() + "]");
        byte[] encodeBandInt8 = encodeBandInt("bcIntref", cpEntryListToArray(this.bcIntref), Codec.DELTA5);
        outputStream.write(encodeBandInt8);
        PackingUtils.log("Wrote " + encodeBandInt8.length + " bytes from bcIntref[" + this.bcIntref.size() + "]");
        byte[] encodeBandInt9 = encodeBandInt("bcFloatRef", cpEntryListToArray(this.bcFloatRef), Codec.DELTA5);
        outputStream.write(encodeBandInt9);
        PackingUtils.log("Wrote " + encodeBandInt9.length + " bytes from bcFloatRef[" + this.bcFloatRef.size() + "]");
        byte[] encodeBandInt10 = encodeBandInt("bcLongRef", cpEntryListToArray(this.bcLongRef), Codec.DELTA5);
        outputStream.write(encodeBandInt10);
        PackingUtils.log("Wrote " + encodeBandInt10.length + " bytes from bcLongRef[" + this.bcLongRef.size() + "]");
        byte[] encodeBandInt11 = encodeBandInt("bcDoubleRef", cpEntryListToArray(this.bcDoubleRef), Codec.DELTA5);
        outputStream.write(encodeBandInt11);
        PackingUtils.log("Wrote " + encodeBandInt11.length + " bytes from bcDoubleRef[" + this.bcDoubleRef.size() + "]");
        byte[] encodeBandInt12 = encodeBandInt("bcStringRef", cpEntryListToArray(this.bcStringRef), Codec.DELTA5);
        outputStream.write(encodeBandInt12);
        PackingUtils.log("Wrote " + encodeBandInt12.length + " bytes from bcStringRef[" + this.bcStringRef.size() + "]");
        byte[] encodeBandInt13 = encodeBandInt("bcClassRef", cpEntryOrNullListToArray(this.bcClassRef), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt13);
        PackingUtils.log("Wrote " + encodeBandInt13.length + " bytes from bcClassRef[" + this.bcClassRef.size() + "]");
        byte[] encodeBandInt14 = encodeBandInt("bcFieldRef", cpEntryListToArray(this.bcFieldRef), Codec.DELTA5);
        outputStream.write(encodeBandInt14);
        PackingUtils.log("Wrote " + encodeBandInt14.length + " bytes from bcFieldRef[" + this.bcFieldRef.size() + "]");
        byte[] encodeBandInt15 = encodeBandInt("bcMethodRef", cpEntryListToArray(this.bcMethodRef), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt15);
        PackingUtils.log("Wrote " + encodeBandInt15.length + " bytes from bcMethodRef[" + this.bcMethodRef.size() + "]");
        byte[] encodeBandInt16 = encodeBandInt("bcIMethodRef", cpEntryListToArray(this.bcIMethodRef), Codec.DELTA5);
        outputStream.write(encodeBandInt16);
        PackingUtils.log("Wrote " + encodeBandInt16.length + " bytes from bcIMethodRef[" + this.bcIMethodRef.size() + "]");
        byte[] encodeBandInt17 = encodeBandInt("bcThisField", integerListToArray(this.bcThisField), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt17);
        PackingUtils.log("Wrote " + encodeBandInt17.length + " bytes from bcThisField[" + this.bcThisField.size() + "]");
        byte[] encodeBandInt18 = encodeBandInt("bcSuperField", integerListToArray(this.bcSuperField), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt18);
        PackingUtils.log("Wrote " + encodeBandInt18.length + " bytes from bcSuperField[" + this.bcSuperField.size() + "]");
        byte[] encodeBandInt19 = encodeBandInt("bcThisMethod", integerListToArray(this.bcThisMethod), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt19);
        PackingUtils.log("Wrote " + encodeBandInt19.length + " bytes from bcThisMethod[" + this.bcThisMethod.size() + "]");
        byte[] encodeBandInt20 = encodeBandInt("bcSuperMethod", integerListToArray(this.bcSuperMethod), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt20);
        PackingUtils.log("Wrote " + encodeBandInt20.length + " bytes from bcSuperMethod[" + this.bcSuperMethod.size() + "]");
        byte[] encodeBandInt21 = encodeBandInt("bcInitRef", integerListToArray(this.bcInitRef), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt21);
        PackingUtils.log("Wrote " + encodeBandInt21.length + " bytes from bcInitRef[" + this.bcInitRef.size() + "]");
    }

    private List getIndexInClass(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(Integer.valueOf(((CPMethodOrField) list.get(i)).getIndexInClass()));
        }
        return arrayList;
    }

    private List getIndexInClassForConstructor(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(Integer.valueOf(((CPMethodOrField) list.get(i)).getIndexInClassForConstructor()));
        }
        return arrayList;
    }

    public void visitEnd() {
        for (int i = 0; i < this.bciRenumbering.size(); i++) {
            if (this.bciRenumbering.get(i) == -1) {
                this.bciRenumbering.remove(i);
                IntList intList = this.bciRenumbering;
                int i2 = this.renumberedOffset + 1;
                this.renumberedOffset = i2;
                intList.add(i, i2);
            }
        }
        int i3 = this.renumberedOffset;
        if (i3 != 0) {
            if (i3 + 1 != this.bciRenumbering.size()) {
                throw new RuntimeException("Mistake made with renumbering");
            }
            for (int size = this.bcLabel.size() - 1; size >= 0; size--) {
                Object obj = this.bcLabel.get(size);
                if (obj instanceof Integer) {
                    break;
                }
                if (obj instanceof Label) {
                    this.bcLabel.remove(size);
                    this.bcLabel.add(size, Integer.valueOf(this.bciRenumbering.get(((Integer) this.labelsToOffsets.get(obj)).intValue()) - this.bciRenumbering.get(this.bcLabelRelativeOffsets.get(size))));
                }
            }
            this.bcCodes.add(255);
            this.segment.getClassBands().doBciRenumbering(this.bciRenumbering, this.labelsToOffsets);
            this.bciRenumbering.clear();
            this.labelsToOffsets.clear();
            this.byteCodeOffset = 0;
            this.renumberedOffset = 0;
        }
    }

    public void visitLabel(Label label) {
        this.labelsToOffsets.put(label, Integer.valueOf(this.byteCodeOffset));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void visitFieldInsn(int i, String str, String str2, String str3) {
        this.byteCodeOffset += 3;
        updateRenumbering();
        boolean z = true;
        if (this.bcCodes.size() > 0) {
            IntList intList = this.bcCodes;
            if (intList.get(intList.size() - 1) == 42) {
                IntList intList2 = this.bcCodes;
                intList2.remove(intList2.size() - 1);
                CPMethodOrField cPField = this.cpBands.getCPField(str, str2, str3);
                if (z) {
                    i += 7;
                }
                if (!str.equals(this.currentClass)) {
                    i += 24;
                    this.bcThisField.add(cPField);
                } else {
                    if (z) {
                        i -= 7;
                        this.bcCodes.add(42);
                    }
                    this.bcFieldRef.add(cPField);
                }
                this.bcCodes.add(i);
            }
        }
        z = false;
        CPMethodOrField cPField2 = this.cpBands.getCPField(str, str2, str3);
        if (z) {
        }
        if (!str.equals(this.currentClass)) {
        }
        this.bcCodes.add(i);
    }

    private void updateRenumbering() {
        if (this.bciRenumbering.isEmpty()) {
            this.bciRenumbering.add(0);
        }
        this.renumberedOffset++;
        for (int size = this.bciRenumbering.size(); size < this.byteCodeOffset; size++) {
            this.bciRenumbering.add(-1);
        }
        this.bciRenumbering.add(this.renumberedOffset);
    }

    public void visitIincInsn(int i, int i2) {
        if (i > 255 || i2 > 255) {
            this.byteCodeOffset += 6;
            this.bcCodes.add(196);
            this.bcCodes.add(132);
            this.bcLocal.add(i);
            this.bcShort.add(i2);
        } else {
            this.byteCodeOffset += 3;
            this.bcCodes.add(132);
            this.bcLocal.add(i);
            this.bcByte.add(i2 & 255);
        }
        updateRenumbering();
    }

    public void visitInsn(int i) {
        if (i >= 202) {
            throw new RuntimeException("Non-standard bytecode instructions not supported");
        }
        this.bcCodes.add(i);
        this.byteCodeOffset++;
        updateRenumbering();
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x000a, code lost:
    
        if (r2 != 188) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void visitIntInsn(int i, int i2) {
        if (i != 16) {
            if (i == 17) {
                this.bcCodes.add(i);
                this.bcShort.add(i2);
                this.byteCodeOffset += 3;
            }
            updateRenumbering();
        }
        this.bcCodes.add(i);
        this.bcByte.add(i2 & 255);
        this.byteCodeOffset += 2;
        updateRenumbering();
    }

    public void visitJumpInsn(int i, Label label) {
        this.bcCodes.add(i);
        this.bcLabel.add(label);
        this.bcLabelRelativeOffsets.add(this.byteCodeOffset);
        this.byteCodeOffset += 3;
        updateRenumbering();
    }

    public void visitLdcInsn(Object obj) {
        CPConstant constant = this.cpBands.getConstant(obj);
        if (this.segment.lastConstantHadWideIndex() || (constant instanceof CPLong) || (constant instanceof CPDouble)) {
            this.byteCodeOffset += 3;
            if (constant instanceof CPInt) {
                this.bcCodes.add(237);
                this.bcIntref.add(constant);
            } else if (constant instanceof CPFloat) {
                this.bcCodes.add(238);
                this.bcFloatRef.add(constant);
            } else if (constant instanceof CPLong) {
                this.bcCodes.add(20);
                this.bcLongRef.add(constant);
            } else if (constant instanceof CPDouble) {
                this.bcCodes.add(239);
                this.bcDoubleRef.add(constant);
            } else if (constant instanceof CPString) {
                this.bcCodes.add(19);
                this.bcStringRef.add(constant);
            } else if (constant instanceof CPClass) {
                this.bcCodes.add(236);
                this.bcClassRef.add(constant);
            } else {
                throw new RuntimeException("Constant should not be null");
            }
        } else {
            this.byteCodeOffset += 2;
            if (constant instanceof CPInt) {
                this.bcCodes.add(234);
                this.bcIntref.add(constant);
            } else if (constant instanceof CPFloat) {
                this.bcCodes.add(235);
                this.bcFloatRef.add(constant);
            } else if (constant instanceof CPString) {
                this.bcCodes.add(18);
                this.bcStringRef.add(constant);
            } else if (constant instanceof CPClass) {
                this.bcCodes.add(233);
                this.bcClassRef.add(constant);
            }
        }
        updateRenumbering();
    }

    public void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
        this.bcCodes.add(171);
        this.bcLabel.add(label);
        this.bcLabelRelativeOffsets.add(this.byteCodeOffset);
        this.bcCaseCount.add(iArr.length);
        for (int i = 0; i < labelArr.length; i++) {
            this.bcCaseValue.add(iArr[i]);
            this.bcLabel.add(labelArr[i]);
            this.bcLabelRelativeOffsets.add(this.byteCodeOffset);
        }
        int i2 = this.byteCodeOffset;
        this.byteCodeOffset += ((i2 + 1) % 4 != 0 ? 4 - ((i2 + 1) % 4) : 0) + 1 + 8 + (iArr.length * 8);
        updateRenumbering();
    }

    public void visitMethodInsn(int i, String str, String str2, String str3) {
        this.byteCodeOffset += 3;
        switch (i) {
            case 182:
            case 183:
            case 184:
                boolean z = false;
                if (this.bcCodes.size() > 0) {
                    IntList intList = this.bcCodes;
                    if (intList.get(intList.size() - 1) == 42) {
                        IntList intList2 = this.bcCodes;
                        intList2.remove(intList2.size() - 1);
                        i += 7;
                        z = true;
                    }
                }
                if (str.equals(this.currentClass)) {
                    i += 24;
                    if (str2.equals("<init>") && i == 207) {
                        i = 230;
                        this.bcInitRef.add(this.cpBands.getCPMethod(str, str2, str3));
                    } else {
                        this.bcThisMethod.add(this.cpBands.getCPMethod(str, str2, str3));
                    }
                } else if (str.equals(this.superClass)) {
                    i += 38;
                    if (str2.equals("<init>") && i == 221) {
                        i = 231;
                        this.bcInitRef.add(this.cpBands.getCPMethod(str, str2, str3));
                    } else {
                        this.bcSuperMethod.add(this.cpBands.getCPMethod(str, str2, str3));
                    }
                } else {
                    if (z) {
                        i -= 7;
                        this.bcCodes.add(42);
                    }
                    if (str2.equals("<init>") && i == 183 && str.equals(this.currentNewClass)) {
                        i = 232;
                        this.bcInitRef.add(this.cpBands.getCPMethod(str, str2, str3));
                    } else {
                        this.bcMethodRef.add(this.cpBands.getCPMethod(str, str2, str3));
                    }
                }
                this.bcCodes.add(i);
                break;
            case 185:
                this.byteCodeOffset += 2;
                this.bcIMethodRef.add(this.cpBands.getCPIMethod(str, str2, str3));
                this.bcCodes.add(185);
                break;
        }
        updateRenumbering();
    }

    public void visitMultiANewArrayInsn(String str, int i) {
        this.byteCodeOffset += 4;
        updateRenumbering();
        this.bcCodes.add(197);
        this.bcClassRef.add(this.cpBands.getCPClass(str));
        this.bcByte.add(i & 255);
    }

    public void visitTableSwitchInsn(int i, int i2, Label label, Label[] labelArr) {
        this.bcCodes.add(170);
        this.bcLabel.add(label);
        this.bcLabelRelativeOffsets.add(this.byteCodeOffset);
        this.bcCaseValue.add(i);
        this.bcCaseCount.add(labelArr.length);
        for (Label label2 : labelArr) {
            this.bcLabel.add(label2);
            this.bcLabelRelativeOffsets.add(this.byteCodeOffset);
        }
        int i3 = this.byteCodeOffset;
        this.byteCodeOffset += (i3 % 4 != 0 ? 4 - (i3 % 4) : 0) + 12 + (labelArr.length * 4);
        updateRenumbering();
    }

    public void visitTypeInsn(int i, String str) {
        this.byteCodeOffset += 3;
        updateRenumbering();
        this.bcCodes.add(i);
        this.bcClassRef.add(this.cpBands.getCPClass(str));
        if (i == 187) {
            this.currentNewClass = str;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void visitVarInsn(int i, int i2) {
        if (i2 > 255) {
            this.byteCodeOffset += 4;
            this.bcCodes.add(196);
            this.bcCodes.add(i);
            this.bcLocal.add(i2);
        } else if (i2 > 3 || i == 169) {
            this.byteCodeOffset += 2;
            this.bcCodes.add(i);
            this.bcLocal.add(i2);
        } else {
            this.byteCodeOffset++;
            switch (i) {
                case 21:
                    this.bcCodes.add(i + 5 + i2);
                    break;
                case 22:
                    this.bcCodes.add(i + 8 + i2);
                    break;
                case 23:
                    this.bcCodes.add(i + 11 + i2);
                    break;
                case 24:
                    this.bcCodes.add(i + 14 + i2);
                    break;
                case 25:
                    this.bcCodes.add(i + 17 + i2);
                    break;
                default:
                    switch (i) {
                    }
            }
        }
        updateRenumbering();
    }
}
