package org.apache.commons.compress.harmony.unpack200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.compress.harmony.unpack200.bytecode.AnnotationDefaultAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.AnnotationsAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.Attribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPDouble;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPFloat;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInteger;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPLong;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;
import org.apache.commons.compress.harmony.unpack200.bytecode.RuntimeVisibleorInvisibleAnnotationsAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.RuntimeVisibleorInvisibleParameterAnnotationsAttribute;

/* loaded from: classes9.dex */
public class MetadataBandGroup {
    private static CPUTF8 riaUTF8;
    private static CPUTF8 ripaUTF8;
    private static CPUTF8 rvaUTF8;
    private static CPUTF8 rvpaUTF8;

    /* renamed from: T */
    public int[] f8969T;
    private int T_index;
    public int[] anno_N;
    private int anno_N_Index;
    private List attributes;
    public CPDouble[] caseD_KD;
    private int caseD_KD_Index;
    public CPFloat[] caseF_KF;
    private int caseF_KF_Index;
    public CPInteger[] caseI_KI;
    private int caseI_KI_Index;
    public CPLong[] caseJ_KJ;
    private int caseJ_KJ_Index;
    public int[] casearray_N;
    private int casearray_N_Index;
    public CPUTF8[] casec_RS;
    private int casec_RS_Index;
    public String[] caseec_RU;
    private int caseec_RU_Index;
    public String[] caseet_RS;
    private int caseet_RS_Index;
    public CPUTF8[] cases_RU;
    private int cases_RU_Index;
    private final CpBands cpBands;
    public CPUTF8[] name_RU;
    public CPUTF8[] nestname_RU;
    private Iterator nestname_RU_Iterator;
    public int[] nestpair_N;
    private int nestpair_N_Index;
    public CPUTF8[] nesttype_RS;
    private int nesttype_RS_Index;
    public int[][] pair_N;
    private int pair_N_Index;
    public int[] param_NB;
    private final String type;
    public CPUTF8[][] type_RS;

    public static void setRvaAttributeName(CPUTF8 cputf8) {
        rvaUTF8 = cputf8;
    }

    public static void setRiaAttributeName(CPUTF8 cputf8) {
        riaUTF8 = cputf8;
    }

    public static void setRvpaAttributeName(CPUTF8 cputf8) {
        rvpaUTF8 = cputf8;
    }

    public static void setRipaAttributeName(CPUTF8 cputf8) {
        ripaUTF8 = cputf8;
    }

    public MetadataBandGroup(String str, CpBands cpBands) {
        this.type = str;
        this.cpBands = cpBands;
    }

    public List getAttributes() {
        if (this.attributes == null) {
            this.attributes = new ArrayList();
            CPUTF8[] cputf8Arr = this.name_RU;
            int i = 0;
            if (cputf8Arr != null) {
                Iterator it = Arrays.asList(cputf8Arr).iterator();
                if (!this.type.equals("AD")) {
                    this.T_index = 0;
                }
                this.caseI_KI_Index = 0;
                this.caseD_KD_Index = 0;
                this.caseF_KF_Index = 0;
                this.caseJ_KJ_Index = 0;
                this.casec_RS_Index = 0;
                this.caseet_RS_Index = 0;
                this.caseec_RU_Index = 0;
                this.cases_RU_Index = 0;
                this.casearray_N_Index = 0;
                this.nesttype_RS_Index = 0;
                this.nestpair_N_Index = 0;
                this.nestname_RU_Iterator = Arrays.asList(this.nestname_RU).iterator();
                if (this.type.equals("RVA") || this.type.equals("RIA")) {
                    while (true) {
                        int[] iArr = this.anno_N;
                        if (i >= iArr.length) {
                            break;
                        }
                        this.attributes.add(getAttribute(iArr[i], this.type_RS[i], this.pair_N[i], it));
                        i++;
                    }
                } else if (this.type.equals("RVPA") || this.type.equals("RIPA")) {
                    this.anno_N_Index = 0;
                    this.pair_N_Index = 0;
                    while (true) {
                        int[] iArr2 = this.param_NB;
                        if (i >= iArr2.length) {
                            break;
                        }
                        this.attributes.add(getParameterAttribute(iArr2[i], it));
                        i++;
                    }
                }
            } else if (this.type.equals("AD")) {
                while (true) {
                    int[] iArr3 = this.f8969T;
                    if (i >= iArr3.length) {
                        break;
                    }
                    this.attributes.add(new AnnotationDefaultAttribute(new AnnotationsAttribute.ElementValue(iArr3[i], getNextValue(iArr3[i]))));
                    i++;
                }
            }
        }
        return this.attributes;
    }

    private Attribute getAttribute(int i, CPUTF8[] cputf8Arr, int[] iArr, Iterator it) {
        AnnotationsAttribute.Annotation[] annotationArr = new AnnotationsAttribute.Annotation[i];
        for (int i2 = 0; i2 < i; i2++) {
            annotationArr[i2] = getAnnotation(cputf8Arr[i2], iArr[i2], it);
        }
        return new RuntimeVisibleorInvisibleAnnotationsAttribute(this.type.equals("RVA") ? rvaUTF8 : riaUTF8, annotationArr);
    }

    private Attribute getParameterAttribute(int i, Iterator it) {
        RuntimeVisibleorInvisibleParameterAnnotationsAttribute.ParameterAnnotation[] parameterAnnotationArr = new RuntimeVisibleorInvisibleParameterAnnotationsAttribute.ParameterAnnotation[i];
        for (int i2 = 0; i2 < i; i2++) {
            int[] iArr = this.anno_N;
            int i3 = this.anno_N_Index;
            this.anno_N_Index = i3 + 1;
            int i4 = iArr[i3];
            int[][] iArr2 = this.pair_N;
            int i5 = this.pair_N_Index;
            this.pair_N_Index = i5 + 1;
            int[] iArr3 = iArr2[i5];
            AnnotationsAttribute.Annotation[] annotationArr = new AnnotationsAttribute.Annotation[i4];
            for (int i6 = 0; i6 < annotationArr.length; i6++) {
                annotationArr[i6] = getAnnotation(this.type_RS[this.anno_N_Index - 1][i6], iArr3[i6], it);
            }
            parameterAnnotationArr[i2] = new RuntimeVisibleorInvisibleParameterAnnotationsAttribute.ParameterAnnotation(annotationArr);
        }
        return new RuntimeVisibleorInvisibleParameterAnnotationsAttribute(this.type.equals("RVPA") ? rvpaUTF8 : ripaUTF8, parameterAnnotationArr);
    }

    private AnnotationsAttribute.Annotation getAnnotation(CPUTF8 cputf8, int i, Iterator it) {
        CPUTF8[] cputf8Arr = new CPUTF8[i];
        AnnotationsAttribute.ElementValue[] elementValueArr = new AnnotationsAttribute.ElementValue[i];
        for (int i2 = 0; i2 < cputf8Arr.length; i2++) {
            cputf8Arr[i2] = (CPUTF8) it.next();
            int[] iArr = this.f8969T;
            int i3 = this.T_index;
            this.T_index = i3 + 1;
            int i4 = iArr[i3];
            elementValueArr[i2] = new AnnotationsAttribute.ElementValue(i4, getNextValue(i4));
        }
        return new AnnotationsAttribute.Annotation(i, cputf8, cputf8Arr, elementValueArr);
    }

    private Object getNextValue(int i) {
        if (i == 64) {
            CPUTF8[] cputf8Arr = this.nesttype_RS;
            int i2 = this.nesttype_RS_Index;
            this.nesttype_RS_Index = i2 + 1;
            CPUTF8 cputf8 = cputf8Arr[i2];
            int[] iArr = this.nestpair_N;
            int i3 = this.nestpair_N_Index;
            this.nestpair_N_Index = i3 + 1;
            return getAnnotation(cputf8, iArr[i3], this.nestname_RU_Iterator);
        }
        if (i != 70) {
            if (i != 83) {
                if (i == 99) {
                    CPUTF8[] cputf8Arr2 = this.casec_RS;
                    int i4 = this.casec_RS_Index;
                    this.casec_RS_Index = i4 + 1;
                    return cputf8Arr2[i4];
                }
                if (i == 101) {
                    StringBuilder sb = new StringBuilder();
                    String[] strArr = this.caseet_RS;
                    int i5 = this.caseet_RS_Index;
                    this.caseet_RS_Index = i5 + 1;
                    sb.append(strArr[i5]);
                    sb.append(":");
                    String[] strArr2 = this.caseec_RU;
                    int i6 = this.caseec_RU_Index;
                    this.caseec_RU_Index = i6 + 1;
                    sb.append(strArr2[i6]);
                    return this.cpBands.cpNameAndTypeValue(sb.toString());
                }
                if (i == 115) {
                    CPUTF8[] cputf8Arr3 = this.cases_RU;
                    int i7 = this.cases_RU_Index;
                    this.cases_RU_Index = i7 + 1;
                    return cputf8Arr3[i7];
                }
                if (i != 73) {
                    if (i == 74) {
                        CPLong[] cPLongArr = this.caseJ_KJ;
                        int i8 = this.caseJ_KJ_Index;
                        this.caseJ_KJ_Index = i8 + 1;
                        return cPLongArr[i8];
                    }
                    if (i != 90) {
                        if (i != 91) {
                            switch (i) {
                                case 66:
                                case 67:
                                    break;
                                case 68:
                                    CPDouble[] cPDoubleArr = this.caseD_KD;
                                    int i9 = this.caseD_KD_Index;
                                    this.caseD_KD_Index = i9 + 1;
                                    return cPDoubleArr[i9];
                                default:
                                    return null;
                            }
                        } else {
                            int[] iArr2 = this.casearray_N;
                            int i10 = this.casearray_N_Index;
                            this.casearray_N_Index = i10 + 1;
                            int i11 = iArr2[i10];
                            AnnotationsAttribute.ElementValue[] elementValueArr = new AnnotationsAttribute.ElementValue[i11];
                            for (int i12 = 0; i12 < i11; i12++) {
                                int[] iArr3 = this.f8969T;
                                int i13 = this.T_index;
                                this.T_index = i13 + 1;
                                int i14 = iArr3[i13];
                                elementValueArr[i12] = new AnnotationsAttribute.ElementValue(i14, getNextValue(i14));
                            }
                            return elementValueArr;
                        }
                    }
                }
            }
            CPInteger[] cPIntegerArr = this.caseI_KI;
            int i15 = this.caseI_KI_Index;
            this.caseI_KI_Index = i15 + 1;
            return cPIntegerArr[i15];
        }
        CPFloat[] cPFloatArr = this.caseF_KF;
        int i16 = this.caseF_KF_Index;
        this.caseF_KF_Index = i16 + 1;
        return cPFloatArr[i16];
    }
}
