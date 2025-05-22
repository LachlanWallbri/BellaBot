package org.apache.commons.compress.harmony.pack200;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.compress.harmony.pack200.AttributeDefinitionBands;
import org.apache.commons.compress.harmony.pack200.IcBands;
import org.objectweb.asm.Label;

/* loaded from: classes9.dex */
public class ClassBands extends BandSet {
    private boolean anySyntheticClasses;
    private boolean anySyntheticFields;
    private boolean anySyntheticMethods;
    private final AttributeDefinitionBands attrBands;
    private final List classAttributeBands;
    private final List classEnclosingMethodClass;
    private final List classEnclosingMethodDesc;
    private final IntList classFileVersionMajor;
    private final IntList classFileVersionMinor;
    private List classInnerClassesNameRUN;
    private List classInnerClassesOuterRCN;
    private final Map classReferencesInnerClass;
    private final List classSignature;
    private final List classSourceFile;
    private int[] class_InnerClasses_F;
    private int[] class_InnerClasses_N;
    private CPClass[] class_InnerClasses_RC;
    private final MetadataBandGroup class_RIA_bands;
    private final MetadataBandGroup class_RVA_bands;
    private int[] class_attr_calls;
    private final int[] class_field_count;
    private final long[] class_flags;
    private final CPClass[][] class_interface;
    private final int[] class_interface_count;
    private final int[] class_method_count;
    private final CPClass[] class_super;
    private final CPClass[] class_this;
    private final List codeAttributeBands;
    private final List codeFlags;
    private final List codeHandlerCatchPO;
    private final List codeHandlerClass;
    private final IntList codeHandlerCount;
    private final List codeHandlerEndPO;
    private final List codeHandlerStartP;
    private int[] codeHeaders;
    private final List codeLineNumberTableBciP;
    private final IntList codeLineNumberTableLine;
    private final IntList codeLineNumberTableN;
    private final List codeLocalVariableTableBciP;
    private final IntList codeLocalVariableTableN;
    private final List codeLocalVariableTableNameRU;
    private final IntList codeLocalVariableTableSlot;
    private final List codeLocalVariableTableSpanO;
    private final List codeLocalVariableTableTypeRS;
    private final List codeLocalVariableTypeTableBciP;
    private final IntList codeLocalVariableTypeTableN;
    private final List codeLocalVariableTypeTableNameRU;
    private final IntList codeLocalVariableTypeTableSlot;
    private final List codeLocalVariableTypeTableSpanO;
    private final List codeLocalVariableTypeTableTypeRS;
    private final IntList codeMaxLocals;
    private final IntList codeMaxStack;
    private int[] code_attr_calls;
    private final CpBands cpBands;
    private final List fieldAttributeBands;
    private final List fieldConstantValueKQ;
    private final List fieldSignature;
    private final MetadataBandGroup field_RIA_bands;
    private final MetadataBandGroup field_RVA_bands;
    private int[] field_attr_calls;
    private final CPNameAndType[][] field_descr;
    private final long[][] field_flags;
    private int index;
    private final int[] major_versions;
    private final List methodAttributeBands;
    private final List methodExceptionClasses;
    private final IntList methodExceptionNumber;
    private final List methodSignature;
    private final MetadataBandGroup method_AD_bands;
    private final MetadataBandGroup method_RIA_bands;
    private final MetadataBandGroup method_RIPA_bands;
    private final MetadataBandGroup method_RVA_bands;
    private final MetadataBandGroup method_RVPA_bands;
    private int[] method_attr_calls;
    private final CPNameAndType[][] method_descr;
    private final long[][] method_flags;
    private int numMethodArgs;
    private final Segment segment;
    private final boolean stripDebug;
    private final List tempFieldDesc;
    private final List tempFieldFlags;
    private final List tempMethodDesc;
    private final List tempMethodFlags;
    private TempParamAnnotation tempMethodRIPA;
    private TempParamAnnotation tempMethodRVPA;

    public ClassBands(Segment segment, int i, int i2, boolean z) throws IOException {
        super(i2, segment.getSegmentHeader());
        this.classSourceFile = new ArrayList();
        this.classEnclosingMethodClass = new ArrayList();
        this.classEnclosingMethodDesc = new ArrayList();
        this.classSignature = new ArrayList();
        this.classFileVersionMinor = new IntList();
        this.classFileVersionMajor = new IntList();
        this.fieldConstantValueKQ = new ArrayList();
        this.fieldSignature = new ArrayList();
        this.methodSignature = new ArrayList();
        this.methodExceptionNumber = new IntList();
        this.methodExceptionClasses = new ArrayList();
        this.codeMaxStack = new IntList();
        this.codeMaxLocals = new IntList();
        this.codeHandlerCount = new IntList();
        this.codeHandlerStartP = new ArrayList();
        this.codeHandlerEndPO = new ArrayList();
        this.codeHandlerCatchPO = new ArrayList();
        this.codeHandlerClass = new ArrayList();
        this.codeFlags = new ArrayList();
        this.codeLineNumberTableN = new IntList();
        this.codeLineNumberTableBciP = new ArrayList();
        this.codeLineNumberTableLine = new IntList();
        this.codeLocalVariableTableN = new IntList();
        this.codeLocalVariableTableBciP = new ArrayList();
        this.codeLocalVariableTableSpanO = new ArrayList();
        this.codeLocalVariableTableNameRU = new ArrayList();
        this.codeLocalVariableTableTypeRS = new ArrayList();
        this.codeLocalVariableTableSlot = new IntList();
        this.codeLocalVariableTypeTableN = new IntList();
        this.codeLocalVariableTypeTableBciP = new ArrayList();
        this.codeLocalVariableTypeTableSpanO = new ArrayList();
        this.codeLocalVariableTypeTableNameRU = new ArrayList();
        this.codeLocalVariableTypeTableTypeRS = new ArrayList();
        this.codeLocalVariableTypeTableSlot = new IntList();
        this.classAttributeBands = new ArrayList();
        this.methodAttributeBands = new ArrayList();
        this.fieldAttributeBands = new ArrayList();
        this.codeAttributeBands = new ArrayList();
        this.tempFieldFlags = new ArrayList();
        this.tempFieldDesc = new ArrayList();
        this.tempMethodFlags = new ArrayList();
        this.tempMethodDesc = new ArrayList();
        this.anySyntheticClasses = false;
        this.anySyntheticFields = false;
        this.anySyntheticMethods = false;
        this.classReferencesInnerClass = new HashMap();
        this.index = 0;
        this.numMethodArgs = 0;
        this.stripDebug = z;
        this.segment = segment;
        this.cpBands = segment.getCpBands();
        this.attrBands = segment.getAttrBands();
        this.class_this = new CPClass[i];
        this.class_super = new CPClass[i];
        this.class_interface_count = new int[i];
        this.class_interface = new CPClass[i];
        this.class_field_count = new int[i];
        this.class_method_count = new int[i];
        this.field_descr = new CPNameAndType[i];
        this.field_flags = new long[i];
        this.method_descr = new CPNameAndType[i];
        this.method_flags = new long[i];
        for (int i3 = 0; i3 < i; i3++) {
            this.field_flags[i3] = new long[0];
            this.method_flags[i3] = new long[0];
        }
        this.major_versions = new int[i];
        this.class_flags = new long[i];
        this.class_RVA_bands = new MetadataBandGroup("RVA", 0, this.cpBands, this.segmentHeader, i2);
        this.class_RIA_bands = new MetadataBandGroup("RIA", 0, this.cpBands, this.segmentHeader, i2);
        this.field_RVA_bands = new MetadataBandGroup("RVA", 1, this.cpBands, this.segmentHeader, i2);
        this.field_RIA_bands = new MetadataBandGroup("RIA", 1, this.cpBands, this.segmentHeader, i2);
        this.method_RVA_bands = new MetadataBandGroup("RVA", 2, this.cpBands, this.segmentHeader, i2);
        this.method_RIA_bands = new MetadataBandGroup("RIA", 2, this.cpBands, this.segmentHeader, i2);
        this.method_RVPA_bands = new MetadataBandGroup("RVPA", 2, this.cpBands, this.segmentHeader, i2);
        this.method_RIPA_bands = new MetadataBandGroup("RIPA", 2, this.cpBands, this.segmentHeader, i2);
        this.method_AD_bands = new MetadataBandGroup("AD", 2, this.cpBands, this.segmentHeader, i2);
        createNewAttributeBands();
    }

    private void createNewAttributeBands() throws IOException {
        Iterator it = this.attrBands.getClassAttributeLayouts().iterator();
        while (it.hasNext()) {
            this.classAttributeBands.add(new NewAttributeBands(this.effort, this.cpBands, this.segment.getSegmentHeader(), (AttributeDefinitionBands.AttributeDefinition) it.next()));
        }
        Iterator it2 = this.attrBands.getMethodAttributeLayouts().iterator();
        while (it2.hasNext()) {
            this.methodAttributeBands.add(new NewAttributeBands(this.effort, this.cpBands, this.segment.getSegmentHeader(), (AttributeDefinitionBands.AttributeDefinition) it2.next()));
        }
        Iterator it3 = this.attrBands.getFieldAttributeLayouts().iterator();
        while (it3.hasNext()) {
            this.fieldAttributeBands.add(new NewAttributeBands(this.effort, this.cpBands, this.segment.getSegmentHeader(), (AttributeDefinitionBands.AttributeDefinition) it3.next()));
        }
        Iterator it4 = this.attrBands.getCodeAttributeLayouts().iterator();
        while (it4.hasNext()) {
            this.codeAttributeBands.add(new NewAttributeBands(this.effort, this.cpBands, this.segment.getSegmentHeader(), (AttributeDefinitionBands.AttributeDefinition) it4.next()));
        }
    }

    public void addClass(int i, int i2, String str, String str2, String str3, String[] strArr) {
        this.class_this[this.index] = this.cpBands.getCPClass(str);
        this.class_super[this.index] = this.cpBands.getCPClass(str3);
        int[] iArr = this.class_interface_count;
        int i3 = this.index;
        iArr[i3] = strArr.length;
        this.class_interface[i3] = new CPClass[strArr.length];
        for (int i4 = 0; i4 < strArr.length; i4++) {
            this.class_interface[this.index][i4] = this.cpBands.getCPClass(strArr[i4]);
        }
        int[] iArr2 = this.major_versions;
        int i5 = this.index;
        iArr2[i5] = i;
        this.class_flags[i5] = i2;
        if (!this.anySyntheticClasses && (i2 & 4096) != 0 && this.segment.getCurrentClassReader().hasSyntheticAttributes()) {
            this.cpBands.addCPUtf8("Synthetic");
            this.anySyntheticClasses = true;
        }
        int i6 = 131072 & i2;
        if (str2 != null) {
            long[] jArr = this.class_flags;
            int i7 = this.index;
            jArr[i7] = jArr[i7] | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
            this.classSignature.add(this.cpBands.getCPSignature(str2));
        }
    }

    public void currentClassReferencesInnerClass(CPClass cPClass) {
        CPClass cPClass2;
        int i = this.index;
        CPClass[] cPClassArr = this.class_this;
        if (i >= cPClassArr.length || (cPClass2 = cPClassArr[i]) == null || cPClass2.equals(cPClass) || isInnerClassOf(cPClass2.toString(), cPClass)) {
            return;
        }
        Set set = (Set) this.classReferencesInnerClass.get(cPClass2);
        if (set == null) {
            set = new HashSet();
            this.classReferencesInnerClass.put(cPClass2, set);
        }
        set.add(cPClass);
    }

    private boolean isInnerClassOf(String str, CPClass cPClass) {
        if (!isInnerClass(str)) {
            return false;
        }
        String substring = str.substring(0, str.lastIndexOf(36));
        if (substring.equals(cPClass.toString())) {
            return true;
        }
        return isInnerClassOf(substring, cPClass);
    }

    private boolean isInnerClass(String str) {
        return str.indexOf(36) != -1;
    }

    public void addField(int i, String str, String str2, String str3, Object obj) {
        int i2 = i & 65535;
        this.tempFieldDesc.add(this.cpBands.getCPNameAndType(str, str2));
        if (str3 != null) {
            this.fieldSignature.add(this.cpBands.getCPSignature(str3));
            i2 |= 524288;
        }
        if ((i2 & 131072) != 0) {
            i2 = (i2 & (-131073)) | 1048576;
        }
        if (obj != null) {
            this.fieldConstantValueKQ.add(this.cpBands.getConstant(obj));
            i2 |= 131072;
        }
        if (!this.anySyntheticFields && (i2 & 4096) != 0 && this.segment.getCurrentClassReader().hasSyntheticAttributes()) {
            this.cpBands.addCPUtf8("Synthetic");
            this.anySyntheticFields = true;
        }
        this.tempFieldFlags.add(Long.valueOf(i2));
    }

    public void finaliseBands() {
        int i;
        int defaultMajorVersion = this.segmentHeader.getDefaultMajorVersion();
        int i2 = 0;
        while (true) {
            long[] jArr = this.class_flags;
            if (i2 >= jArr.length) {
                break;
            }
            int i3 = this.major_versions[i2];
            if (i3 != defaultMajorVersion) {
                jArr[i2] = jArr[i2] | 16777216;
                this.classFileVersionMajor.add(i3);
                this.classFileVersionMinor.add(0);
            }
            i2++;
        }
        this.codeHeaders = new int[this.codeHandlerCount.size()];
        int i4 = 0;
        for (int i5 = 0; i5 < this.codeHeaders.length; i5++) {
            int i6 = i5 - i4;
            int i7 = this.codeHandlerCount.get(i6);
            int i8 = this.codeMaxLocals.get(i6);
            int i9 = this.codeMaxStack.get(i6);
            if (i7 == 0) {
                int i10 = (i8 * 12) + i9 + 1;
                if (i10 < 145 && i9 < 12) {
                    this.codeHeaders[i5] = i10;
                }
            } else if (i7 == 1) {
                int i11 = (i8 * 8) + i9 + 145;
                if (i11 < 209 && i9 < 8) {
                    this.codeHeaders[i5] = i11;
                }
            } else if (i7 == 2 && (i = (i8 * 7) + i9 + 209) < 256 && i9 < 7) {
                this.codeHeaders[i5] = i;
            }
            if (this.codeHeaders[i5] != 0) {
                this.codeHandlerCount.remove(i6);
                this.codeMaxLocals.remove(i6);
                this.codeMaxStack.remove(i6);
                i4++;
            } else if (!this.segment.getSegmentHeader().have_all_code_flags()) {
                this.codeFlags.add(0L);
            }
        }
        IntList intList = new IntList();
        ArrayList arrayList = new ArrayList();
        int i12 = 0;
        while (true) {
            CPClass[] cPClassArr = this.class_this;
            if (i12 >= cPClassArr.length) {
                break;
            }
            CPClass cPClass = cPClassArr[i12];
            Set set = (Set) this.classReferencesInnerClass.get(cPClass);
            if (set != null) {
                List innerClassesForOuter = this.segment.getIcBands().getInnerClassesForOuter(cPClass.toString());
                if (innerClassesForOuter != null) {
                    Iterator it = innerClassesForOuter.iterator();
                    while (it.hasNext()) {
                        set.remove(((IcBands.IcTuple) it.next()).f8955C);
                    }
                }
                Iterator it2 = set.iterator();
                int i13 = 0;
                while (it2.hasNext()) {
                    IcBands.IcTuple icTuple = this.segment.getIcBands().getIcTuple((CPClass) it2.next());
                    if (icTuple != null && !icTuple.isAnonymous()) {
                        arrayList.add(icTuple);
                        i13++;
                    }
                }
                if (i13 != 0) {
                    intList.add(i13);
                    long[] jArr2 = this.class_flags;
                    jArr2[i12] = jArr2[i12] | 8388608;
                }
            }
            i12++;
        }
        this.class_InnerClasses_N = intList.toArray();
        this.class_InnerClasses_RC = new CPClass[arrayList.size()];
        this.class_InnerClasses_F = new int[arrayList.size()];
        this.classInnerClassesOuterRCN = new ArrayList();
        this.classInnerClassesNameRUN = new ArrayList();
        for (int i14 = 0; i14 < this.class_InnerClasses_RC.length; i14++) {
            IcBands.IcTuple icTuple2 = (IcBands.IcTuple) arrayList.get(i14);
            this.class_InnerClasses_RC[i14] = icTuple2.f8955C;
            if (icTuple2.f8956C2 == null && icTuple2.f8958N == null) {
                this.class_InnerClasses_F[i14] = 0;
            } else {
                if (icTuple2.f8957F == 0) {
                    this.class_InnerClasses_F[i14] = 65536;
                } else {
                    this.class_InnerClasses_F[i14] = icTuple2.f8957F;
                }
                this.classInnerClassesOuterRCN.add(icTuple2.f8956C2);
                this.classInnerClassesNameRUN.add(icTuple2.f8958N);
            }
        }
        IntList intList2 = new IntList();
        IntList intList3 = new IntList();
        IntList intList4 = new IntList();
        IntList intList5 = new IntList();
        if (this.class_RVA_bands.hasContent()) {
            intList2.add(this.class_RVA_bands.numBackwardsCalls());
        }
        if (this.class_RIA_bands.hasContent()) {
            intList2.add(this.class_RIA_bands.numBackwardsCalls());
        }
        if (this.field_RVA_bands.hasContent()) {
            intList3.add(this.field_RVA_bands.numBackwardsCalls());
        }
        if (this.field_RIA_bands.hasContent()) {
            intList3.add(this.field_RIA_bands.numBackwardsCalls());
        }
        if (this.method_RVA_bands.hasContent()) {
            intList4.add(this.method_RVA_bands.numBackwardsCalls());
        }
        if (this.method_RIA_bands.hasContent()) {
            intList4.add(this.method_RIA_bands.numBackwardsCalls());
        }
        if (this.method_RVPA_bands.hasContent()) {
            intList4.add(this.method_RVPA_bands.numBackwardsCalls());
        }
        if (this.method_RIPA_bands.hasContent()) {
            intList4.add(this.method_RIPA_bands.numBackwardsCalls());
        }
        if (this.method_AD_bands.hasContent()) {
            intList4.add(this.method_AD_bands.numBackwardsCalls());
        }
        $$Lambda$ClassBands$a6QuEZcVwz2R9VAJwYlKvNODiyo __lambda_classbands_a6quezcvwz2r9vajwylkvnodiyo = new Comparator() { // from class: org.apache.commons.compress.harmony.pack200.-$$Lambda$ClassBands$a6QuEZcVwz2R9VAJwYlKvNODiyo
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ClassBands.lambda$finaliseBands$0(obj, obj2);
            }
        };
        Collections.sort(this.classAttributeBands, __lambda_classbands_a6quezcvwz2r9vajwylkvnodiyo);
        Collections.sort(this.methodAttributeBands, __lambda_classbands_a6quezcvwz2r9vajwylkvnodiyo);
        Collections.sort(this.fieldAttributeBands, __lambda_classbands_a6quezcvwz2r9vajwylkvnodiyo);
        Collections.sort(this.codeAttributeBands, __lambda_classbands_a6quezcvwz2r9vajwylkvnodiyo);
        for (NewAttributeBands newAttributeBands : this.classAttributeBands) {
            if (newAttributeBands.isUsedAtLeastOnce()) {
                for (int i15 : newAttributeBands.numBackwardsCalls()) {
                    intList2.add(i15);
                }
            }
        }
        for (NewAttributeBands newAttributeBands2 : this.methodAttributeBands) {
            if (newAttributeBands2.isUsedAtLeastOnce()) {
                for (int i16 : newAttributeBands2.numBackwardsCalls()) {
                    intList4.add(i16);
                }
            }
        }
        for (NewAttributeBands newAttributeBands3 : this.fieldAttributeBands) {
            if (newAttributeBands3.isUsedAtLeastOnce()) {
                for (int i17 : newAttributeBands3.numBackwardsCalls()) {
                    intList3.add(i17);
                }
            }
        }
        for (NewAttributeBands newAttributeBands4 : this.codeAttributeBands) {
            if (newAttributeBands4.isUsedAtLeastOnce()) {
                for (int i18 : newAttributeBands4.numBackwardsCalls()) {
                    intList5.add(i18);
                }
            }
        }
        this.class_attr_calls = intList2.toArray();
        this.field_attr_calls = intList3.toArray();
        this.method_attr_calls = intList4.toArray();
        this.code_attr_calls = intList5.toArray();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$finaliseBands$0(Object obj, Object obj2) {
        return ((NewAttributeBands) obj).getFlagIndex() - ((NewAttributeBands) obj2).getFlagIndex();
    }

    @Override // org.apache.commons.compress.harmony.pack200.BandSet
    public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
        PackingUtils.log("Writing class bands...");
        byte[] encodeBandInt = encodeBandInt("class_this", getInts(this.class_this), Codec.DELTA5);
        outputStream.write(encodeBandInt);
        PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from class_this[" + this.class_this.length + "]");
        byte[] encodeBandInt2 = encodeBandInt("class_super", getInts(this.class_super), Codec.DELTA5);
        outputStream.write(encodeBandInt2);
        PackingUtils.log("Wrote " + encodeBandInt2.length + " bytes from class_super[" + this.class_super.length + "]");
        byte[] encodeBandInt3 = encodeBandInt("class_interface_count", this.class_interface_count, Codec.DELTA5);
        outputStream.write(encodeBandInt3);
        PackingUtils.log("Wrote " + encodeBandInt3.length + " bytes from class_interface_count[" + this.class_interface_count.length + "]");
        int[] iArr = new int[sum(this.class_interface_count)];
        int i = 0;
        int i2 = 0;
        while (true) {
            CPClass[][] cPClassArr = this.class_interface;
            if (i >= cPClassArr.length) {
                break;
            }
            if (cPClassArr[i] != null) {
                int i3 = i2;
                int i4 = 0;
                while (true) {
                    CPClass[][] cPClassArr2 = this.class_interface;
                    if (i4 >= cPClassArr2[i].length) {
                        break;
                    }
                    iArr[i3] = cPClassArr2[i][i4].getIndex();
                    i3++;
                    i4++;
                }
                i2 = i3;
            }
            i++;
        }
        byte[] encodeBandInt4 = encodeBandInt("class_interface", iArr, Codec.DELTA5);
        outputStream.write(encodeBandInt4);
        PackingUtils.log("Wrote " + encodeBandInt4.length + " bytes from class_interface[" + iArr.length + "]");
        byte[] encodeBandInt5 = encodeBandInt("class_field_count", this.class_field_count, Codec.DELTA5);
        outputStream.write(encodeBandInt5);
        PackingUtils.log("Wrote " + encodeBandInt5.length + " bytes from class_field_count[" + this.class_field_count.length + "]");
        byte[] encodeBandInt6 = encodeBandInt("class_method_count", this.class_method_count, Codec.DELTA5);
        outputStream.write(encodeBandInt6);
        PackingUtils.log("Wrote " + encodeBandInt6.length + " bytes from class_method_count[" + this.class_method_count.length + "]");
        int[] iArr2 = new int[sum(this.class_field_count)];
        int i5 = 0;
        int i6 = 0;
        while (i5 < this.index) {
            int i7 = i6;
            int i8 = 0;
            while (true) {
                CPNameAndType[][] cPNameAndTypeArr = this.field_descr;
                if (i8 < cPNameAndTypeArr[i5].length) {
                    iArr2[i7] = cPNameAndTypeArr[i5][i8].getIndex();
                    i7++;
                    i8++;
                }
            }
            i5++;
            i6 = i7;
        }
        byte[] encodeBandInt7 = encodeBandInt("field_descr", iArr2, Codec.DELTA5);
        outputStream.write(encodeBandInt7);
        PackingUtils.log("Wrote " + encodeBandInt7.length + " bytes from field_descr[" + iArr2.length + "]");
        writeFieldAttributeBands(outputStream);
        int[] iArr3 = new int[sum(this.class_method_count)];
        int i9 = 0;
        int i10 = 0;
        while (i9 < this.index) {
            int i11 = i10;
            int i12 = 0;
            while (true) {
                CPNameAndType[][] cPNameAndTypeArr2 = this.method_descr;
                if (i12 < cPNameAndTypeArr2[i9].length) {
                    iArr3[i11] = cPNameAndTypeArr2[i9][i12].getIndex();
                    i11++;
                    i12++;
                }
            }
            i9++;
            i10 = i11;
        }
        byte[] encodeBandInt8 = encodeBandInt("method_descr", iArr3, Codec.MDELTA5);
        outputStream.write(encodeBandInt8);
        PackingUtils.log("Wrote " + encodeBandInt8.length + " bytes from method_descr[" + iArr3.length + "]");
        writeMethodAttributeBands(outputStream);
        writeClassAttributeBands(outputStream);
        writeCodeBands(outputStream);
    }

    private int sum(int[] iArr) {
        int i = 0;
        for (int i2 : iArr) {
            i += i2;
        }
        return i;
    }

    private void writeFieldAttributeBands(OutputStream outputStream) throws IOException, Pack200Exception {
        byte[] encodeFlags = encodeFlags("field_flags", this.field_flags, Codec.UNSIGNED5, Codec.UNSIGNED5, this.segmentHeader.have_field_flags_hi());
        outputStream.write(encodeFlags);
        PackingUtils.log("Wrote " + encodeFlags.length + " bytes from field_flags[" + this.field_flags.length + "]");
        byte[] encodeBandInt = encodeBandInt("field_attr_calls", this.field_attr_calls, Codec.UNSIGNED5);
        outputStream.write(encodeBandInt);
        PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from field_attr_calls[" + this.field_attr_calls.length + "]");
        byte[] encodeBandInt2 = encodeBandInt("fieldConstantValueKQ", cpEntryListToArray(this.fieldConstantValueKQ), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt2);
        PackingUtils.log("Wrote " + encodeBandInt2.length + " bytes from fieldConstantValueKQ[" + this.fieldConstantValueKQ.size() + "]");
        byte[] encodeBandInt3 = encodeBandInt("fieldSignature", cpEntryListToArray(this.fieldSignature), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt3);
        PackingUtils.log("Wrote " + encodeBandInt3.length + " bytes from fieldSignature[" + this.fieldSignature.size() + "]");
        this.field_RVA_bands.pack(outputStream);
        this.field_RIA_bands.pack(outputStream);
        Iterator it = this.fieldAttributeBands.iterator();
        while (it.hasNext()) {
            ((NewAttributeBands) it.next()).pack(outputStream);
        }
    }

    private void writeMethodAttributeBands(OutputStream outputStream) throws IOException, Pack200Exception {
        byte[] encodeFlags = encodeFlags("method_flags", this.method_flags, Codec.UNSIGNED5, Codec.UNSIGNED5, this.segmentHeader.have_method_flags_hi());
        outputStream.write(encodeFlags);
        PackingUtils.log("Wrote " + encodeFlags.length + " bytes from method_flags[" + this.method_flags.length + "]");
        byte[] encodeBandInt = encodeBandInt("method_attr_calls", this.method_attr_calls, Codec.UNSIGNED5);
        outputStream.write(encodeBandInt);
        PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from method_attr_calls[" + this.method_attr_calls.length + "]");
        byte[] encodeBandInt2 = encodeBandInt("methodExceptionNumber", this.methodExceptionNumber.toArray(), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt2);
        PackingUtils.log("Wrote " + encodeBandInt2.length + " bytes from methodExceptionNumber[" + this.methodExceptionNumber.size() + "]");
        byte[] encodeBandInt3 = encodeBandInt("methodExceptionClasses", cpEntryListToArray(this.methodExceptionClasses), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt3);
        PackingUtils.log("Wrote " + encodeBandInt3.length + " bytes from methodExceptionClasses[" + this.methodExceptionClasses.size() + "]");
        byte[] encodeBandInt4 = encodeBandInt("methodSignature", cpEntryListToArray(this.methodSignature), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt4);
        PackingUtils.log("Wrote " + encodeBandInt4.length + " bytes from methodSignature[" + this.methodSignature.size() + "]");
        this.method_RVA_bands.pack(outputStream);
        this.method_RIA_bands.pack(outputStream);
        this.method_RVPA_bands.pack(outputStream);
        this.method_RIPA_bands.pack(outputStream);
        this.method_AD_bands.pack(outputStream);
        Iterator it = this.methodAttributeBands.iterator();
        while (it.hasNext()) {
            ((NewAttributeBands) it.next()).pack(outputStream);
        }
    }

    private void writeClassAttributeBands(OutputStream outputStream) throws IOException, Pack200Exception {
        byte[] encodeFlags = encodeFlags("class_flags", this.class_flags, Codec.UNSIGNED5, Codec.UNSIGNED5, this.segmentHeader.have_class_flags_hi());
        outputStream.write(encodeFlags);
        PackingUtils.log("Wrote " + encodeFlags.length + " bytes from class_flags[" + this.class_flags.length + "]");
        byte[] encodeBandInt = encodeBandInt("class_attr_calls", this.class_attr_calls, Codec.UNSIGNED5);
        outputStream.write(encodeBandInt);
        PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from class_attr_calls[" + this.class_attr_calls.length + "]");
        byte[] encodeBandInt2 = encodeBandInt("classSourceFile", cpEntryOrNullListToArray(this.classSourceFile), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt2);
        PackingUtils.log("Wrote " + encodeBandInt2.length + " bytes from classSourceFile[" + this.classSourceFile.size() + "]");
        byte[] encodeBandInt3 = encodeBandInt("class_enclosing_method_RC", cpEntryListToArray(this.classEnclosingMethodClass), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt3);
        PackingUtils.log("Wrote " + encodeBandInt3.length + " bytes from class_enclosing_method_RC[" + this.classEnclosingMethodClass.size() + "]");
        byte[] encodeBandInt4 = encodeBandInt("class_EnclosingMethod_RDN", cpEntryOrNullListToArray(this.classEnclosingMethodDesc), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt4);
        PackingUtils.log("Wrote " + encodeBandInt4.length + " bytes from class_EnclosingMethod_RDN[" + this.classEnclosingMethodDesc.size() + "]");
        byte[] encodeBandInt5 = encodeBandInt("class_Signature_RS", cpEntryListToArray(this.classSignature), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt5);
        PackingUtils.log("Wrote " + encodeBandInt5.length + " bytes from class_Signature_RS[" + this.classSignature.size() + "]");
        this.class_RVA_bands.pack(outputStream);
        this.class_RIA_bands.pack(outputStream);
        byte[] encodeBandInt6 = encodeBandInt("class_InnerClasses_N", this.class_InnerClasses_N, Codec.UNSIGNED5);
        outputStream.write(encodeBandInt6);
        PackingUtils.log("Wrote " + encodeBandInt6.length + " bytes from class_InnerClasses_N[" + this.class_InnerClasses_N.length + "]");
        byte[] encodeBandInt7 = encodeBandInt("class_InnerClasses_RC", getInts(this.class_InnerClasses_RC), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt7);
        PackingUtils.log("Wrote " + encodeBandInt7.length + " bytes from class_InnerClasses_RC[" + this.class_InnerClasses_RC.length + "]");
        byte[] encodeBandInt8 = encodeBandInt("class_InnerClasses_F", this.class_InnerClasses_F, Codec.UNSIGNED5);
        outputStream.write(encodeBandInt8);
        PackingUtils.log("Wrote " + encodeBandInt8.length + " bytes from class_InnerClasses_F[" + this.class_InnerClasses_F.length + "]");
        byte[] encodeBandInt9 = encodeBandInt("class_InnerClasses_outer_RCN", cpEntryOrNullListToArray(this.classInnerClassesOuterRCN), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt9);
        PackingUtils.log("Wrote " + encodeBandInt9.length + " bytes from class_InnerClasses_outer_RCN[" + this.classInnerClassesOuterRCN.size() + "]");
        byte[] encodeBandInt10 = encodeBandInt("class_InnerClasses_name_RUN", cpEntryOrNullListToArray(this.classInnerClassesNameRUN), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt10);
        PackingUtils.log("Wrote " + encodeBandInt10.length + " bytes from class_InnerClasses_name_RUN[" + this.classInnerClassesNameRUN.size() + "]");
        byte[] encodeBandInt11 = encodeBandInt("classFileVersionMinor", this.classFileVersionMinor.toArray(), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt11);
        PackingUtils.log("Wrote " + encodeBandInt11.length + " bytes from classFileVersionMinor[" + this.classFileVersionMinor.size() + "]");
        byte[] encodeBandInt12 = encodeBandInt("classFileVersionMajor", this.classFileVersionMajor.toArray(), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt12);
        PackingUtils.log("Wrote " + encodeBandInt12.length + " bytes from classFileVersionMajor[" + this.classFileVersionMajor.size() + "]");
        Iterator it = this.classAttributeBands.iterator();
        while (it.hasNext()) {
            ((NewAttributeBands) it.next()).pack(outputStream);
        }
    }

    private int[] getInts(CPClass[] cPClassArr) {
        int[] iArr = new int[cPClassArr.length];
        for (int i = 0; i < iArr.length; i++) {
            if (cPClassArr[i] != null) {
                iArr[i] = cPClassArr[i].getIndex();
            }
        }
        return iArr;
    }

    private void writeCodeBands(OutputStream outputStream) throws IOException, Pack200Exception {
        byte[] encodeBandInt = encodeBandInt("codeHeaders", this.codeHeaders, Codec.BYTE1);
        outputStream.write(encodeBandInt);
        PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from codeHeaders[" + this.codeHeaders.length + "]");
        byte[] encodeBandInt2 = encodeBandInt("codeMaxStack", this.codeMaxStack.toArray(), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt2);
        PackingUtils.log("Wrote " + encodeBandInt2.length + " bytes from codeMaxStack[" + this.codeMaxStack.size() + "]");
        byte[] encodeBandInt3 = encodeBandInt("codeMaxLocals", this.codeMaxLocals.toArray(), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt3);
        PackingUtils.log("Wrote " + encodeBandInt3.length + " bytes from codeMaxLocals[" + this.codeMaxLocals.size() + "]");
        byte[] encodeBandInt4 = encodeBandInt("codeHandlerCount", this.codeHandlerCount.toArray(), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt4);
        PackingUtils.log("Wrote " + encodeBandInt4.length + " bytes from codeHandlerCount[" + this.codeHandlerCount.size() + "]");
        byte[] encodeBandInt5 = encodeBandInt("codeHandlerStartP", integerListToArray(this.codeHandlerStartP), Codec.BCI5);
        outputStream.write(encodeBandInt5);
        PackingUtils.log("Wrote " + encodeBandInt5.length + " bytes from codeHandlerStartP[" + this.codeHandlerStartP.size() + "]");
        byte[] encodeBandInt6 = encodeBandInt("codeHandlerEndPO", integerListToArray(this.codeHandlerEndPO), Codec.BRANCH5);
        outputStream.write(encodeBandInt6);
        PackingUtils.log("Wrote " + encodeBandInt6.length + " bytes from codeHandlerEndPO[" + this.codeHandlerEndPO.size() + "]");
        byte[] encodeBandInt7 = encodeBandInt("codeHandlerCatchPO", integerListToArray(this.codeHandlerCatchPO), Codec.BRANCH5);
        outputStream.write(encodeBandInt7);
        PackingUtils.log("Wrote " + encodeBandInt7.length + " bytes from codeHandlerCatchPO[" + this.codeHandlerCatchPO.size() + "]");
        byte[] encodeBandInt8 = encodeBandInt("codeHandlerClass", cpEntryOrNullListToArray(this.codeHandlerClass), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt8);
        PackingUtils.log("Wrote " + encodeBandInt8.length + " bytes from codeHandlerClass[" + this.codeHandlerClass.size() + "]");
        writeCodeAttributeBands(outputStream);
    }

    private void writeCodeAttributeBands(OutputStream outputStream) throws IOException, Pack200Exception {
        byte[] encodeFlags = encodeFlags("codeFlags", longListToArray(this.codeFlags), Codec.UNSIGNED5, Codec.UNSIGNED5, this.segmentHeader.have_code_flags_hi());
        outputStream.write(encodeFlags);
        PackingUtils.log("Wrote " + encodeFlags.length + " bytes from codeFlags[" + this.codeFlags.size() + "]");
        byte[] encodeBandInt = encodeBandInt("code_attr_calls", this.code_attr_calls, Codec.UNSIGNED5);
        outputStream.write(encodeBandInt);
        PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from code_attr_calls[" + this.code_attr_calls.length + "]");
        byte[] encodeBandInt2 = encodeBandInt("code_LineNumberTable_N", this.codeLineNumberTableN.toArray(), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt2);
        PackingUtils.log("Wrote " + encodeBandInt2.length + " bytes from code_LineNumberTable_N[" + this.codeLineNumberTableN.size() + "]");
        byte[] encodeBandInt3 = encodeBandInt("code_LineNumberTable_bci_P", integerListToArray(this.codeLineNumberTableBciP), Codec.BCI5);
        outputStream.write(encodeBandInt3);
        PackingUtils.log("Wrote " + encodeBandInt3.length + " bytes from code_LineNumberTable_bci_P[" + this.codeLineNumberTableBciP.size() + "]");
        byte[] encodeBandInt4 = encodeBandInt("code_LineNumberTable_line", this.codeLineNumberTableLine.toArray(), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt4);
        PackingUtils.log("Wrote " + encodeBandInt4.length + " bytes from code_LineNumberTable_line[" + this.codeLineNumberTableLine.size() + "]");
        byte[] encodeBandInt5 = encodeBandInt("code_LocalVariableTable_N", this.codeLocalVariableTableN.toArray(), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt5);
        PackingUtils.log("Wrote " + encodeBandInt5.length + " bytes from code_LocalVariableTable_N[" + this.codeLocalVariableTableN.size() + "]");
        byte[] encodeBandInt6 = encodeBandInt("code_LocalVariableTable_bci_P", integerListToArray(this.codeLocalVariableTableBciP), Codec.BCI5);
        outputStream.write(encodeBandInt6);
        PackingUtils.log("Wrote " + encodeBandInt6.length + " bytes from code_LocalVariableTable_bci_P[" + this.codeLocalVariableTableBciP.size() + "]");
        byte[] encodeBandInt7 = encodeBandInt("code_LocalVariableTable_span_O", integerListToArray(this.codeLocalVariableTableSpanO), Codec.BRANCH5);
        outputStream.write(encodeBandInt7);
        PackingUtils.log("Wrote " + encodeBandInt7.length + " bytes from code_LocalVariableTable_span_O[" + this.codeLocalVariableTableSpanO.size() + "]");
        byte[] encodeBandInt8 = encodeBandInt("code_LocalVariableTable_name_RU", cpEntryListToArray(this.codeLocalVariableTableNameRU), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt8);
        PackingUtils.log("Wrote " + encodeBandInt8.length + " bytes from code_LocalVariableTable_name_RU[" + this.codeLocalVariableTableNameRU.size() + "]");
        byte[] encodeBandInt9 = encodeBandInt("code_LocalVariableTable_type_RS", cpEntryListToArray(this.codeLocalVariableTableTypeRS), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt9);
        PackingUtils.log("Wrote " + encodeBandInt9.length + " bytes from code_LocalVariableTable_type_RS[" + this.codeLocalVariableTableTypeRS.size() + "]");
        byte[] encodeBandInt10 = encodeBandInt("code_LocalVariableTable_slot", this.codeLocalVariableTableSlot.toArray(), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt10);
        PackingUtils.log("Wrote " + encodeBandInt10.length + " bytes from code_LocalVariableTable_slot[" + this.codeLocalVariableTableSlot.size() + "]");
        byte[] encodeBandInt11 = encodeBandInt("code_LocalVariableTypeTable_N", this.codeLocalVariableTypeTableN.toArray(), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt11);
        PackingUtils.log("Wrote " + encodeBandInt11.length + " bytes from code_LocalVariableTypeTable_N[" + this.codeLocalVariableTypeTableN.size() + "]");
        byte[] encodeBandInt12 = encodeBandInt("code_LocalVariableTypeTable_bci_P", integerListToArray(this.codeLocalVariableTypeTableBciP), Codec.BCI5);
        outputStream.write(encodeBandInt12);
        PackingUtils.log("Wrote " + encodeBandInt12.length + " bytes from code_LocalVariableTypeTable_bci_P[" + this.codeLocalVariableTypeTableBciP.size() + "]");
        byte[] encodeBandInt13 = encodeBandInt("code_LocalVariableTypeTable_span_O", integerListToArray(this.codeLocalVariableTypeTableSpanO), Codec.BRANCH5);
        outputStream.write(encodeBandInt13);
        PackingUtils.log("Wrote " + encodeBandInt13.length + " bytes from code_LocalVariableTypeTable_span_O[" + this.codeLocalVariableTypeTableSpanO.size() + "]");
        byte[] encodeBandInt14 = encodeBandInt("code_LocalVariableTypeTable_name_RU", cpEntryListToArray(this.codeLocalVariableTypeTableNameRU), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt14);
        PackingUtils.log("Wrote " + encodeBandInt14.length + " bytes from code_LocalVariableTypeTable_name_RU[" + this.codeLocalVariableTypeTableNameRU.size() + "]");
        byte[] encodeBandInt15 = encodeBandInt("code_LocalVariableTypeTable_type_RS", cpEntryListToArray(this.codeLocalVariableTypeTableTypeRS), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt15);
        PackingUtils.log("Wrote " + encodeBandInt15.length + " bytes from code_LocalVariableTypeTable_type_RS[" + this.codeLocalVariableTypeTableTypeRS.size() + "]");
        byte[] encodeBandInt16 = encodeBandInt("code_LocalVariableTypeTable_slot", this.codeLocalVariableTypeTableSlot.toArray(), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt16);
        PackingUtils.log("Wrote " + encodeBandInt16.length + " bytes from code_LocalVariableTypeTable_slot[" + this.codeLocalVariableTypeTableSlot.size() + "]");
        Iterator it = this.codeAttributeBands.iterator();
        while (it.hasNext()) {
            ((NewAttributeBands) it.next()).pack(outputStream);
        }
    }

    public void addMethod(int i, String str, String str2, String str3, String[] strArr) {
        this.tempMethodDesc.add(this.cpBands.getCPNameAndType(str, str2));
        if (str3 != null) {
            this.methodSignature.add(this.cpBands.getCPSignature(str3));
            i |= 524288;
        }
        if (strArr != null) {
            this.methodExceptionNumber.add(strArr.length);
            for (String str4 : strArr) {
                this.methodExceptionClasses.add(this.cpBands.getCPClass(str4));
            }
            i |= 262144;
        }
        if ((131072 & i) != 0) {
            i = (i & (-131073)) | 1048576;
        }
        this.tempMethodFlags.add(Long.valueOf(i));
        this.numMethodArgs = countArgs(str2);
        if (this.anySyntheticMethods || (i & 4096) == 0 || !this.segment.getCurrentClassReader().hasSyntheticAttributes()) {
            return;
        }
        this.cpBands.addCPUtf8("Synthetic");
        this.anySyntheticMethods = true;
    }

    public void endOfMethod() {
        TempParamAnnotation tempParamAnnotation = this.tempMethodRVPA;
        if (tempParamAnnotation != null) {
            this.method_RVPA_bands.addParameterAnnotation(tempParamAnnotation.numParams, this.tempMethodRVPA.annoN, this.tempMethodRVPA.pairN, this.tempMethodRVPA.typeRS, this.tempMethodRVPA.nameRU, this.tempMethodRVPA.f8954t, this.tempMethodRVPA.values, this.tempMethodRVPA.caseArrayN, this.tempMethodRVPA.nestTypeRS, this.tempMethodRVPA.nestNameRU, this.tempMethodRVPA.nestPairN);
            this.tempMethodRVPA = null;
        }
        TempParamAnnotation tempParamAnnotation2 = this.tempMethodRIPA;
        if (tempParamAnnotation2 != null) {
            this.method_RIPA_bands.addParameterAnnotation(tempParamAnnotation2.numParams, this.tempMethodRIPA.annoN, this.tempMethodRIPA.pairN, this.tempMethodRIPA.typeRS, this.tempMethodRIPA.nameRU, this.tempMethodRIPA.f8954t, this.tempMethodRIPA.values, this.tempMethodRIPA.caseArrayN, this.tempMethodRIPA.nestTypeRS, this.tempMethodRIPA.nestNameRU, this.tempMethodRIPA.nestPairN);
            this.tempMethodRIPA = null;
        }
        if (this.codeFlags.size() > 0) {
            long longValue = ((Long) this.codeFlags.get(r0.size() - 1)).longValue();
            int i = this.codeLocalVariableTableN.get(r2.size() - 1);
            if (longValue == 4 && i == 0) {
                this.codeLocalVariableTableN.remove(r0.size() - 1);
                this.codeFlags.remove(r0.size() - 1);
                this.codeFlags.add(0);
            }
        }
    }

    protected static int countArgs(String str) {
        int indexOf = str.indexOf(40);
        int indexOf2 = str.indexOf(41);
        if (indexOf == -1 || indexOf2 == -1 || indexOf2 < indexOf) {
            throw new IllegalArgumentException("No arguments");
        }
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        for (int i2 = indexOf + 1; i2 < indexOf2; i2++) {
            char charAt = str.charAt(i2);
            if (z && charAt == ';') {
                z = false;
                z2 = false;
            } else if (!z && charAt == 'L') {
                i++;
                z = true;
            } else if (charAt == '[') {
                z2 = true;
            } else if (!z) {
                if (z2) {
                    i++;
                    z2 = false;
                } else {
                    i = (charAt == 'D' || charAt == 'J') ? i + 2 : i + 1;
                }
            }
        }
        return i;
    }

    public void endOfClass() {
        int size = this.tempFieldDesc.size();
        int[] iArr = this.class_field_count;
        int i = this.index;
        iArr[i] = size;
        this.field_descr[i] = new CPNameAndType[size];
        this.field_flags[i] = new long[size];
        for (int i2 = 0; i2 < size; i2++) {
            this.field_descr[this.index][i2] = (CPNameAndType) this.tempFieldDesc.get(i2);
            this.field_flags[this.index][i2] = ((Long) this.tempFieldFlags.get(i2)).longValue();
        }
        int size2 = this.tempMethodDesc.size();
        int[] iArr2 = this.class_method_count;
        int i3 = this.index;
        iArr2[i3] = size2;
        this.method_descr[i3] = new CPNameAndType[size2];
        this.method_flags[i3] = new long[size2];
        for (int i4 = 0; i4 < size2; i4++) {
            this.method_descr[this.index][i4] = (CPNameAndType) this.tempMethodDesc.get(i4);
            this.method_flags[this.index][i4] = ((Long) this.tempMethodFlags.get(i4)).longValue();
        }
        this.tempFieldDesc.clear();
        this.tempFieldFlags.clear();
        this.tempMethodDesc.clear();
        this.tempMethodFlags.clear();
        this.index++;
    }

    public void addSourceFile(String str) {
        String cPClass = this.class_this[this.index].toString();
        if (cPClass.indexOf(36) != -1) {
            cPClass = cPClass.substring(0, cPClass.indexOf(36));
        }
        if (str.equals(cPClass.substring(cPClass.lastIndexOf(47) + 1) + ".java")) {
            this.classSourceFile.add(null);
        } else {
            this.classSourceFile.add(this.cpBands.getCPUtf8(str));
        }
        long[] jArr = this.class_flags;
        int i = this.index;
        jArr[i] = jArr[i] | PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
    }

    public void addEnclosingMethod(String str, String str2, String str3) {
        long[] jArr = this.class_flags;
        int i = this.index;
        jArr[i] = jArr[i] | PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
        this.classEnclosingMethodClass.add(this.cpBands.getCPClass(str));
        this.classEnclosingMethodDesc.add(str2 == null ? null : this.cpBands.getCPNameAndType(str2, str3));
    }

    public void addClassAttribute(NewAttribute newAttribute) {
        String str = newAttribute.type;
        for (NewAttributeBands newAttributeBands : this.classAttributeBands) {
            if (newAttributeBands.getAttributeName().equals(str)) {
                newAttributeBands.addAttribute(newAttribute);
                int flagIndex = newAttributeBands.getFlagIndex();
                long[] jArr = this.class_flags;
                int i = this.index;
                jArr[i] = jArr[i] | (1 << flagIndex);
                return;
            }
        }
        throw new RuntimeException("No suitable definition for " + str);
    }

    public void addFieldAttribute(NewAttribute newAttribute) {
        String str = newAttribute.type;
        for (NewAttributeBands newAttributeBands : this.fieldAttributeBands) {
            if (newAttributeBands.getAttributeName().equals(str)) {
                newAttributeBands.addAttribute(newAttribute);
                int flagIndex = newAttributeBands.getFlagIndex();
                List list = this.tempFieldFlags;
                this.tempFieldFlags.add(Long.valueOf(((Long) list.remove(list.size() - 1)).longValue() | (1 << flagIndex)));
                return;
            }
        }
        throw new RuntimeException("No suitable definition for " + str);
    }

    public void addMethodAttribute(NewAttribute newAttribute) {
        String str = newAttribute.type;
        for (NewAttributeBands newAttributeBands : this.methodAttributeBands) {
            if (newAttributeBands.getAttributeName().equals(str)) {
                newAttributeBands.addAttribute(newAttribute);
                int flagIndex = newAttributeBands.getFlagIndex();
                List list = this.tempMethodFlags;
                this.tempMethodFlags.add(Long.valueOf(((Long) list.remove(list.size() - 1)).longValue() | (1 << flagIndex)));
                return;
            }
        }
        throw new RuntimeException("No suitable definition for " + str);
    }

    public void addCodeAttribute(NewAttribute newAttribute) {
        String str = newAttribute.type;
        for (NewAttributeBands newAttributeBands : this.codeAttributeBands) {
            if (newAttributeBands.getAttributeName().equals(str)) {
                newAttributeBands.addAttribute(newAttribute);
                int flagIndex = newAttributeBands.getFlagIndex();
                List list = this.codeFlags;
                this.codeFlags.add(Long.valueOf(((Long) list.remove(list.size() - 1)).longValue() | (1 << flagIndex)));
                return;
            }
        }
        throw new RuntimeException("No suitable definition for " + str);
    }

    public void addMaxStack(int i, int i2) {
        List list = this.tempMethodFlags;
        Long valueOf = Long.valueOf(((Long) list.remove(list.size() - 1)).intValue() | 131072);
        this.tempMethodFlags.add(valueOf);
        this.codeMaxStack.add(i);
        if ((valueOf.longValue() & 8) == 0) {
            i2--;
        }
        this.codeMaxLocals.add(i2 - this.numMethodArgs);
    }

    public void addCode() {
        this.codeHandlerCount.add(0);
        if (this.stripDebug) {
            return;
        }
        this.codeFlags.add(4L);
        this.codeLocalVariableTableN.add(0);
    }

    public void addHandler(Label label, Label label2, Label label3, String str) {
        this.codeHandlerCount.add(this.codeHandlerCount.remove(r0.size() - 1) + 1);
        this.codeHandlerStartP.add(label);
        this.codeHandlerEndPO.add(label2);
        this.codeHandlerCatchPO.add(label3);
        this.codeHandlerClass.add(str == null ? null : this.cpBands.getCPClass(str));
    }

    public void addLineNumber(int i, Label label) {
        List list = this.codeFlags;
        if ((((Long) list.get(list.size() - 1)).intValue() & 2) == 0) {
            List list2 = this.codeFlags;
            list2.remove(list2.size() - 1);
            this.codeFlags.add(Long.valueOf(r0.intValue() | 2));
            this.codeLineNumberTableN.add(1);
        } else {
            IntList intList = this.codeLineNumberTableN;
            intList.increment(intList.size() - 1);
        }
        this.codeLineNumberTableLine.add(i);
        this.codeLineNumberTableBciP.add(label);
    }

    public void addLocalVariable(String str, String str2, String str3, Label label, Label label2, int i) {
        if (str3 != null) {
            List list = this.codeFlags;
            if ((((Long) list.get(list.size() - 1)).intValue() & 8) == 0) {
                List list2 = this.codeFlags;
                list2.remove(list2.size() - 1);
                this.codeFlags.add(Long.valueOf(r1.intValue() | 8));
                this.codeLocalVariableTypeTableN.add(1);
            } else {
                IntList intList = this.codeLocalVariableTypeTableN;
                intList.increment(intList.size() - 1);
            }
            this.codeLocalVariableTypeTableBciP.add(label);
            this.codeLocalVariableTypeTableSpanO.add(label2);
            this.codeLocalVariableTypeTableNameRU.add(this.cpBands.getCPUtf8(str));
            this.codeLocalVariableTypeTableTypeRS.add(this.cpBands.getCPSignature(str3));
            this.codeLocalVariableTypeTableSlot.add(i);
        }
        IntList intList2 = this.codeLocalVariableTableN;
        intList2.increment(intList2.size() - 1);
        this.codeLocalVariableTableBciP.add(label);
        this.codeLocalVariableTableSpanO.add(label2);
        this.codeLocalVariableTableNameRU.add(this.cpBands.getCPUtf8(str));
        this.codeLocalVariableTableTypeRS.add(this.cpBands.getCPSignature(str2));
        this.codeLocalVariableTableSlot.add(i);
    }

    public void doBciRenumbering(IntList intList, Map map) {
        renumberBci(this.codeLineNumberTableBciP, intList, map);
        renumberBci(this.codeLocalVariableTableBciP, intList, map);
        renumberOffsetBci(this.codeLocalVariableTableBciP, this.codeLocalVariableTableSpanO, intList, map);
        renumberBci(this.codeLocalVariableTypeTableBciP, intList, map);
        renumberOffsetBci(this.codeLocalVariableTypeTableBciP, this.codeLocalVariableTypeTableSpanO, intList, map);
        renumberBci(this.codeHandlerStartP, intList, map);
        renumberOffsetBci(this.codeHandlerStartP, this.codeHandlerEndPO, intList, map);
        renumberDoubleOffsetBci(this.codeHandlerStartP, this.codeHandlerEndPO, this.codeHandlerCatchPO, intList, map);
        Iterator it = this.classAttributeBands.iterator();
        while (it.hasNext()) {
            ((NewAttributeBands) it.next()).renumberBci(intList, map);
        }
        Iterator it2 = this.methodAttributeBands.iterator();
        while (it2.hasNext()) {
            ((NewAttributeBands) it2.next()).renumberBci(intList, map);
        }
        Iterator it3 = this.fieldAttributeBands.iterator();
        while (it3.hasNext()) {
            ((NewAttributeBands) it3.next()).renumberBci(intList, map);
        }
        Iterator it4 = this.codeAttributeBands.iterator();
        while (it4.hasNext()) {
            ((NewAttributeBands) it4.next()).renumberBci(intList, map);
        }
    }

    private void renumberBci(List list, IntList intList, Map map) {
        for (int size = list.size() - 1; size >= 0; size--) {
            Object obj = list.get(size);
            if (obj instanceof Integer) {
                return;
            }
            if (obj instanceof Label) {
                list.remove(size);
                list.add(size, Integer.valueOf(intList.get(((Integer) map.get(obj)).intValue())));
            }
        }
    }

    private void renumberOffsetBci(List list, List list2, IntList intList, Map map) {
        for (int size = list2.size() - 1; size >= 0; size--) {
            Object obj = list2.get(size);
            if (obj instanceof Integer) {
                return;
            }
            if (obj instanceof Label) {
                list2.remove(size);
                list2.add(size, Integer.valueOf(intList.get(((Integer) map.get(obj)).intValue()) - ((Integer) list.get(size)).intValue()));
            }
        }
    }

    private void renumberDoubleOffsetBci(List list, List list2, List list3, IntList intList, Map map) {
        for (int size = list3.size() - 1; size >= 0; size--) {
            Object obj = list3.get(size);
            if (obj instanceof Integer) {
                return;
            }
            if (obj instanceof Label) {
                list3.remove(size);
                list3.add(size, Integer.valueOf((intList.get(((Integer) map.get(obj)).intValue()) - ((Integer) list.get(size)).intValue()) - ((Integer) list2.get(size)).intValue()));
            }
        }
    }

    public boolean isAnySyntheticClasses() {
        return this.anySyntheticClasses;
    }

    public boolean isAnySyntheticFields() {
        return this.anySyntheticFields;
    }

    public boolean isAnySyntheticMethods() {
        return this.anySyntheticMethods;
    }

    public void addParameterAnnotation(int i, String str, boolean z, List list, List list2, List list3, List list4, List list5, List list6, List list7) {
        if (z) {
            if (this.tempMethodRVPA == null) {
                this.tempMethodRVPA = new TempParamAnnotation(this.numMethodArgs);
                this.tempMethodRVPA.addParameterAnnotation(i, str, list, list2, list3, list4, list5, list6, list7);
            }
            this.tempMethodFlags.add(Long.valueOf(((Long) this.tempMethodFlags.remove(r1.size() - 1)).longValue() | 8388608));
            return;
        }
        if (this.tempMethodRIPA == null) {
            this.tempMethodRIPA = new TempParamAnnotation(this.numMethodArgs);
            this.tempMethodRIPA.addParameterAnnotation(i, str, list, list2, list3, list4, list5, list6, list7);
        }
        this.tempMethodFlags.add(Long.valueOf(((Long) this.tempMethodFlags.remove(r1.size() - 1)).longValue() | 16777216));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class TempParamAnnotation {
        int[] annoN;
        int numParams;
        IntList pairN = new IntList();
        List typeRS = new ArrayList();
        List nameRU = new ArrayList();

        /* renamed from: t */
        List f8954t = new ArrayList();
        List values = new ArrayList();
        List caseArrayN = new ArrayList();
        List nestTypeRS = new ArrayList();
        List nestNameRU = new ArrayList();
        List nestPairN = new ArrayList();

        public TempParamAnnotation(int i) {
            this.numParams = i;
            this.annoN = new int[i];
        }

        public void addParameterAnnotation(int i, String str, List list, List list2, List list3, List list4, List list5, List list6, List list7) {
            int[] iArr = this.annoN;
            iArr[i] = iArr[i] + 1;
            this.typeRS.add(str);
            this.pairN.add(list.size());
            this.nameRU.addAll(list);
            this.f8954t.addAll(list2);
            this.values.addAll(list3);
            this.caseArrayN.addAll(list4);
            this.nestTypeRS.addAll(list5);
            this.nestNameRU.addAll(list6);
            this.nestPairN.addAll(list7);
        }
    }

    public void addAnnotation(int i, String str, boolean z, List list, List list2, List list3, List list4, List list5, List list6, List list7) {
        if (i == 0) {
            if (z) {
                this.class_RVA_bands.addAnnotation(str, list, list2, list3, list4, list5, list6, list7);
                if ((this.class_flags[this.index] & 2097152) != 0) {
                    this.class_RVA_bands.incrementAnnoN();
                    return;
                }
                this.class_RVA_bands.newEntryInAnnoN();
                long[] jArr = this.class_flags;
                int i2 = this.index;
                jArr[i2] = jArr[i2] | 2097152;
                return;
            }
            this.class_RIA_bands.addAnnotation(str, list, list2, list3, list4, list5, list6, list7);
            if ((this.class_flags[this.index] & 4194304) != 0) {
                this.class_RIA_bands.incrementAnnoN();
                return;
            }
            this.class_RIA_bands.newEntryInAnnoN();
            long[] jArr2 = this.class_flags;
            int i3 = this.index;
            jArr2[i3] = jArr2[i3] | 4194304;
            return;
        }
        if (i == 1) {
            if (z) {
                this.field_RVA_bands.addAnnotation(str, list, list2, list3, list4, list5, list6, list7);
                List list8 = this.tempFieldFlags;
                if ((((Long) list8.remove(list8.size() - 1)).intValue() & 2097152) != 0) {
                    this.field_RVA_bands.incrementAnnoN();
                } else {
                    this.field_RVA_bands.newEntryInAnnoN();
                }
                this.tempFieldFlags.add(Long.valueOf(r1.intValue() | 2097152));
                return;
            }
            this.field_RIA_bands.addAnnotation(str, list, list2, list3, list4, list5, list6, list7);
            List list9 = this.tempFieldFlags;
            if ((((Long) list9.remove(list9.size() - 1)).intValue() & 4194304) != 0) {
                this.field_RIA_bands.incrementAnnoN();
            } else {
                this.field_RIA_bands.newEntryInAnnoN();
            }
            this.tempFieldFlags.add(Long.valueOf(r1.intValue() | 4194304));
            return;
        }
        if (i != 2) {
            return;
        }
        if (z) {
            this.method_RVA_bands.addAnnotation(str, list, list2, list3, list4, list5, list6, list7);
            List list10 = this.tempMethodFlags;
            if ((((Long) list10.remove(list10.size() - 1)).intValue() & 2097152) != 0) {
                this.method_RVA_bands.incrementAnnoN();
            } else {
                this.method_RVA_bands.newEntryInAnnoN();
            }
            this.tempMethodFlags.add(Long.valueOf(r1.intValue() | 2097152));
            return;
        }
        this.method_RIA_bands.addAnnotation(str, list, list2, list3, list4, list5, list6, list7);
        List list11 = this.tempMethodFlags;
        if ((((Long) list11.remove(list11.size() - 1)).intValue() & 4194304) != 0) {
            this.method_RIA_bands.incrementAnnoN();
        } else {
            this.method_RIA_bands.newEntryInAnnoN();
        }
        this.tempMethodFlags.add(Long.valueOf(r1.intValue() | 4194304));
    }

    public void addAnnotationDefault(List list, List list2, List list3, List list4, List list5, List list6, List list7) {
        this.method_AD_bands.addAnnotation(null, list, list2, list3, list4, list5, list6, list7);
        this.tempMethodFlags.add(Long.valueOf(((Long) this.tempMethodFlags.remove(r1.size() - 1)).longValue() | 33554432));
    }

    public void removeCurrentClass() {
        long j = this.class_flags[this.index];
        long j2 = PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
        if ((j & PlaybackStateCompat.ACTION_PREPARE_FROM_URI) != 0) {
            this.classSourceFile.remove(r1.size() - 1);
        }
        if ((this.class_flags[this.index] & PlaybackStateCompat.ACTION_SET_REPEAT_MODE) != 0) {
            this.classEnclosingMethodClass.remove(r1.size() - 1);
            this.classEnclosingMethodDesc.remove(r1.size() - 1);
        }
        long j3 = this.class_flags[this.index];
        long j4 = PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
        if ((j3 & PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED) != 0) {
            this.classSignature.remove(r1.size() - 1);
        }
        if ((this.class_flags[this.index] & 2097152) != 0) {
            this.class_RVA_bands.removeLatest();
        }
        if ((this.class_flags[this.index] & 4194304) != 0) {
            this.class_RIA_bands.removeLatest();
        }
        Iterator it = this.tempFieldFlags.iterator();
        while (it.hasNext()) {
            long longValue = ((Long) it.next()).longValue();
            if ((longValue & PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED) != 0) {
                this.fieldSignature.remove(r8.size() - 1);
            }
            if ((longValue & PlaybackStateCompat.ACTION_PREPARE_FROM_URI) != 0) {
                this.fieldConstantValueKQ.remove(r8.size() - 1);
            }
            if ((longValue & 2097152) != 0) {
                this.field_RVA_bands.removeLatest();
            }
            if ((longValue & 4194304) != 0) {
                this.field_RIA_bands.removeLatest();
            }
        }
        Iterator it2 = this.tempMethodFlags.iterator();
        while (it2.hasNext()) {
            long longValue2 = ((Long) it2.next()).longValue();
            if ((longValue2 & j4) != 0) {
                this.methodSignature.remove(r2.size() - 1);
            }
            if ((longValue2 & PlaybackStateCompat.ACTION_SET_REPEAT_MODE) != 0) {
                int remove = this.methodExceptionNumber.remove(r2.size() - 1);
                for (int i = 0; i < remove; i++) {
                    this.methodExceptionClasses.remove(r11.size() - 1);
                }
            }
            if ((longValue2 & j2) != 0) {
                this.codeMaxLocals.remove(r2.size() - 1);
                this.codeMaxStack.remove(r2.size() - 1);
                int remove2 = this.codeHandlerCount.remove(r2.size() - 1);
                for (int i2 = 0; i2 < remove2; i2++) {
                    int size = this.codeHandlerStartP.size() - 1;
                    this.codeHandlerStartP.remove(size);
                    this.codeHandlerEndPO.remove(size);
                    this.codeHandlerCatchPO.remove(size);
                    this.codeHandlerClass.remove(size);
                }
                if (!this.stripDebug) {
                    long longValue3 = ((Long) this.codeFlags.remove(r2.size() - 1)).longValue();
                    int remove3 = this.codeLocalVariableTableN.remove(r10.size() - 1);
                    for (int i3 = 0; i3 < remove3; i3++) {
                        int size2 = this.codeLocalVariableTableBciP.size() - 1;
                        this.codeLocalVariableTableBciP.remove(size2);
                        this.codeLocalVariableTableSpanO.remove(size2);
                        this.codeLocalVariableTableNameRU.remove(size2);
                        this.codeLocalVariableTableTypeRS.remove(size2);
                        this.codeLocalVariableTableSlot.remove(size2);
                    }
                    if ((8 & longValue3) != 0) {
                        int remove4 = this.codeLocalVariableTypeTableN.remove(r4.size() - 1);
                        for (int i4 = 0; i4 < remove4; i4++) {
                            int size3 = this.codeLocalVariableTypeTableBciP.size() - 1;
                            this.codeLocalVariableTypeTableBciP.remove(size3);
                            this.codeLocalVariableTypeTableSpanO.remove(size3);
                            this.codeLocalVariableTypeTableNameRU.remove(size3);
                            this.codeLocalVariableTypeTableTypeRS.remove(size3);
                            this.codeLocalVariableTypeTableSlot.remove(size3);
                        }
                    }
                    if ((longValue3 & 2) != 0) {
                        int remove5 = this.codeLineNumberTableN.remove(r2.size() - 1);
                        for (int i5 = 0; i5 < remove5; i5++) {
                            int size4 = this.codeLineNumberTableBciP.size() - 1;
                            this.codeLineNumberTableBciP.remove(size4);
                            this.codeLineNumberTableLine.remove(size4);
                        }
                    }
                }
            }
            if ((longValue2 & 2097152) != 0) {
                this.method_RVA_bands.removeLatest();
            }
            if ((longValue2 & 4194304) != 0) {
                this.method_RIA_bands.removeLatest();
            }
            if ((8388608 & longValue2) != 0) {
                this.method_RVPA_bands.removeLatest();
            }
            if ((16777216 & longValue2) != 0) {
                this.method_RIPA_bands.removeLatest();
            }
            if ((33554432 & longValue2) != 0) {
                this.method_AD_bands.removeLatest();
            }
            j2 = PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
            j4 = PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
        }
        CPClass[] cPClassArr = this.class_this;
        int i6 = this.index;
        cPClassArr[i6] = null;
        this.class_super[i6] = null;
        this.class_interface_count[i6] = 0;
        this.class_interface[i6] = null;
        this.major_versions[i6] = 0;
        this.class_flags[i6] = 0;
        this.tempFieldDesc.clear();
        this.tempFieldFlags.clear();
        this.tempMethodDesc.clear();
        this.tempMethodFlags.clear();
        int i7 = this.index;
        if (i7 > 0) {
            this.index = i7 - 1;
        }
    }

    public int numClassesProcessed() {
        return this.index;
    }
}
