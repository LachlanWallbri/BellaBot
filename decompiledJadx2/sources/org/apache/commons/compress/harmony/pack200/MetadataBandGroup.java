package org.apache.commons.compress.harmony.pack200;

import androidx.exifinterface.media.ExifInterface;
import com.loc.C3898x;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class MetadataBandGroup extends BandSet {
    public static final int CONTEXT_CLASS = 0;
    public static final int CONTEXT_FIELD = 1;
    public static final int CONTEXT_METHOD = 2;

    /* renamed from: T */
    public List f8959T;
    public IntList anno_N;
    public List caseD_KD;
    public List caseF_KF;
    public List caseI_KI;
    public List caseJ_KJ;
    public IntList casearray_N;
    public List casec_RS;
    public List caseec_RU;
    public List caseet_RS;
    public List cases_RU;
    private final int context;
    private final CpBands cpBands;
    public List name_RU;
    public List nestname_RU;
    public IntList nestpair_N;
    public List nesttype_RS;
    private int numBackwardsCalls;
    public IntList pair_N;
    public IntList param_NB;
    private final String type;
    public List type_RS;

    public MetadataBandGroup(String str, int i, CpBands cpBands, SegmentHeader segmentHeader, int i2) {
        super(i2, segmentHeader);
        this.numBackwardsCalls = 0;
        this.param_NB = new IntList();
        this.anno_N = new IntList();
        this.type_RS = new ArrayList();
        this.pair_N = new IntList();
        this.name_RU = new ArrayList();
        this.f8959T = new ArrayList();
        this.caseI_KI = new ArrayList();
        this.caseD_KD = new ArrayList();
        this.caseF_KF = new ArrayList();
        this.caseJ_KJ = new ArrayList();
        this.casec_RS = new ArrayList();
        this.caseet_RS = new ArrayList();
        this.caseec_RU = new ArrayList();
        this.cases_RU = new ArrayList();
        this.casearray_N = new IntList();
        this.nesttype_RS = new ArrayList();
        this.nestpair_N = new IntList();
        this.nestname_RU = new ArrayList();
        this.type = str;
        this.cpBands = cpBands;
        this.context = i;
    }

    @Override // org.apache.commons.compress.harmony.pack200.BandSet
    public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
        PackingUtils.log("Writing metadata band group...");
        if (hasContent()) {
            int i = this.context;
            String str = i == 0 ? "Class" : i == 1 ? "Field" : "Method";
            if (!this.type.equals("AD")) {
                if (this.type.indexOf(80) != -1) {
                    byte[] encodeBandInt = encodeBandInt(str + "_" + this.type + " param_NB", this.param_NB.toArray(), Codec.BYTE1);
                    outputStream.write(encodeBandInt);
                    PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from " + str + "_" + this.type + " anno_N[" + this.param_NB.size() + "]");
                }
                byte[] encodeBandInt2 = encodeBandInt(str + "_" + this.type + " anno_N", this.anno_N.toArray(), Codec.UNSIGNED5);
                outputStream.write(encodeBandInt2);
                PackingUtils.log("Wrote " + encodeBandInt2.length + " bytes from " + str + "_" + this.type + " anno_N[" + this.anno_N.size() + "]");
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append("_");
                sb.append(this.type);
                sb.append(" type_RS");
                byte[] encodeBandInt3 = encodeBandInt(sb.toString(), cpEntryListToArray(this.type_RS), Codec.UNSIGNED5);
                outputStream.write(encodeBandInt3);
                PackingUtils.log("Wrote " + encodeBandInt3.length + " bytes from " + str + "_" + this.type + " type_RS[" + this.type_RS.size() + "]");
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append("_");
                sb2.append(this.type);
                sb2.append(" pair_N");
                byte[] encodeBandInt4 = encodeBandInt(sb2.toString(), this.pair_N.toArray(), Codec.UNSIGNED5);
                outputStream.write(encodeBandInt4);
                PackingUtils.log("Wrote " + encodeBandInt4.length + " bytes from " + str + "_" + this.type + " pair_N[" + this.pair_N.size() + "]");
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append("_");
                sb3.append(this.type);
                sb3.append(" name_RU");
                byte[] encodeBandInt5 = encodeBandInt(sb3.toString(), cpEntryListToArray(this.name_RU), Codec.UNSIGNED5);
                outputStream.write(encodeBandInt5);
                PackingUtils.log("Wrote " + encodeBandInt5.length + " bytes from " + str + "_" + this.type + " name_RU[" + this.name_RU.size() + "]");
            }
            byte[] encodeBandInt6 = encodeBandInt(str + "_" + this.type + " T", tagListToArray(this.f8959T), Codec.BYTE1);
            outputStream.write(encodeBandInt6);
            PackingUtils.log("Wrote " + encodeBandInt6.length + " bytes from " + str + "_" + this.type + " T[" + this.f8959T.size() + "]");
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str);
            sb4.append("_");
            sb4.append(this.type);
            sb4.append(" caseI_KI");
            byte[] encodeBandInt7 = encodeBandInt(sb4.toString(), cpEntryListToArray(this.caseI_KI), Codec.UNSIGNED5);
            outputStream.write(encodeBandInt7);
            PackingUtils.log("Wrote " + encodeBandInt7.length + " bytes from " + str + "_" + this.type + " caseI_KI[" + this.caseI_KI.size() + "]");
            StringBuilder sb5 = new StringBuilder();
            sb5.append(str);
            sb5.append("_");
            sb5.append(this.type);
            sb5.append(" caseD_KD");
            byte[] encodeBandInt8 = encodeBandInt(sb5.toString(), cpEntryListToArray(this.caseD_KD), Codec.UNSIGNED5);
            outputStream.write(encodeBandInt8);
            PackingUtils.log("Wrote " + encodeBandInt8.length + " bytes from " + str + "_" + this.type + " caseD_KD[" + this.caseD_KD.size() + "]");
            StringBuilder sb6 = new StringBuilder();
            sb6.append(str);
            sb6.append("_");
            sb6.append(this.type);
            sb6.append(" caseF_KF");
            byte[] encodeBandInt9 = encodeBandInt(sb6.toString(), cpEntryListToArray(this.caseF_KF), Codec.UNSIGNED5);
            outputStream.write(encodeBandInt9);
            PackingUtils.log("Wrote " + encodeBandInt9.length + " bytes from " + str + "_" + this.type + " caseF_KF[" + this.caseF_KF.size() + "]");
            StringBuilder sb7 = new StringBuilder();
            sb7.append(str);
            sb7.append("_");
            sb7.append(this.type);
            sb7.append(" caseJ_KJ");
            byte[] encodeBandInt10 = encodeBandInt(sb7.toString(), cpEntryListToArray(this.caseJ_KJ), Codec.UNSIGNED5);
            outputStream.write(encodeBandInt10);
            PackingUtils.log("Wrote " + encodeBandInt10.length + " bytes from " + str + "_" + this.type + " caseJ_KJ[" + this.caseJ_KJ.size() + "]");
            StringBuilder sb8 = new StringBuilder();
            sb8.append(str);
            sb8.append("_");
            sb8.append(this.type);
            sb8.append(" casec_RS");
            byte[] encodeBandInt11 = encodeBandInt(sb8.toString(), cpEntryListToArray(this.casec_RS), Codec.UNSIGNED5);
            outputStream.write(encodeBandInt11);
            PackingUtils.log("Wrote " + encodeBandInt11.length + " bytes from " + str + "_" + this.type + " casec_RS[" + this.casec_RS.size() + "]");
            StringBuilder sb9 = new StringBuilder();
            sb9.append(str);
            sb9.append("_");
            sb9.append(this.type);
            sb9.append(" caseet_RS");
            byte[] encodeBandInt12 = encodeBandInt(sb9.toString(), cpEntryListToArray(this.caseet_RS), Codec.UNSIGNED5);
            outputStream.write(encodeBandInt12);
            PackingUtils.log("Wrote " + encodeBandInt12.length + " bytes from " + str + "_" + this.type + " caseet_RS[" + this.caseet_RS.size() + "]");
            StringBuilder sb10 = new StringBuilder();
            sb10.append(str);
            sb10.append("_");
            sb10.append(this.type);
            sb10.append(" caseec_RU");
            byte[] encodeBandInt13 = encodeBandInt(sb10.toString(), cpEntryListToArray(this.caseec_RU), Codec.UNSIGNED5);
            outputStream.write(encodeBandInt13);
            PackingUtils.log("Wrote " + encodeBandInt13.length + " bytes from " + str + "_" + this.type + " caseec_RU[" + this.caseec_RU.size() + "]");
            StringBuilder sb11 = new StringBuilder();
            sb11.append(str);
            sb11.append("_");
            sb11.append(this.type);
            sb11.append(" cases_RU");
            byte[] encodeBandInt14 = encodeBandInt(sb11.toString(), cpEntryListToArray(this.cases_RU), Codec.UNSIGNED5);
            outputStream.write(encodeBandInt14);
            PackingUtils.log("Wrote " + encodeBandInt14.length + " bytes from " + str + "_" + this.type + " cases_RU[" + this.cases_RU.size() + "]");
            StringBuilder sb12 = new StringBuilder();
            sb12.append(str);
            sb12.append("_");
            sb12.append(this.type);
            sb12.append(" casearray_N");
            byte[] encodeBandInt15 = encodeBandInt(sb12.toString(), this.casearray_N.toArray(), Codec.UNSIGNED5);
            outputStream.write(encodeBandInt15);
            PackingUtils.log("Wrote " + encodeBandInt15.length + " bytes from " + str + "_" + this.type + " casearray_N[" + this.casearray_N.size() + "]");
            StringBuilder sb13 = new StringBuilder();
            sb13.append(str);
            sb13.append("_");
            sb13.append(this.type);
            sb13.append(" nesttype_RS");
            byte[] encodeBandInt16 = encodeBandInt(sb13.toString(), cpEntryListToArray(this.nesttype_RS), Codec.UNSIGNED5);
            outputStream.write(encodeBandInt16);
            PackingUtils.log("Wrote " + encodeBandInt16.length + " bytes from " + str + "_" + this.type + " nesttype_RS[" + this.nesttype_RS.size() + "]");
            StringBuilder sb14 = new StringBuilder();
            sb14.append(str);
            sb14.append("_");
            sb14.append(this.type);
            sb14.append(" nestpair_N");
            byte[] encodeBandInt17 = encodeBandInt(sb14.toString(), this.nestpair_N.toArray(), Codec.UNSIGNED5);
            outputStream.write(encodeBandInt17);
            PackingUtils.log("Wrote " + encodeBandInt17.length + " bytes from " + str + "_" + this.type + " nestpair_N[" + this.nestpair_N.size() + "]");
            StringBuilder sb15 = new StringBuilder();
            sb15.append(str);
            sb15.append("_");
            sb15.append(this.type);
            sb15.append(" nestname_RU");
            byte[] encodeBandInt18 = encodeBandInt(sb15.toString(), cpEntryListToArray(this.nestname_RU), Codec.UNSIGNED5);
            outputStream.write(encodeBandInt18);
            PackingUtils.log("Wrote " + encodeBandInt18.length + " bytes from " + str + "_" + this.type + " nestname_RU[" + this.nestname_RU.size() + "]");
        }
    }

    private int[] tagListToArray(List list) {
        int[] iArr = new int[list.size()];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = ((String) list.get(i)).charAt(0);
        }
        return iArr;
    }

    public void addParameterAnnotation(int i, int[] iArr, IntList intList, List list, List list2, List list3, List list4, List list5, List list6, List list7, List list8) {
        this.param_NB.add(i);
        for (int i2 : iArr) {
            this.anno_N.add(i2);
        }
        this.pair_N.addAll(intList);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            this.type_RS.add(this.cpBands.getCPSignature((String) it.next()));
        }
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            this.name_RU.add(this.cpBands.getCPUtf8((String) it2.next()));
        }
        Iterator it3 = list4.iterator();
        Iterator it4 = list3.iterator();
        while (it4.hasNext()) {
            String str = (String) it4.next();
            this.f8959T.add(str);
            if (str.equals("B") || str.equals("C") || str.equals("I") || str.equals(ExifInterface.LATITUDE_SOUTH) || str.equals("Z")) {
                this.caseI_KI.add(this.cpBands.getConstant((Integer) it3.next()));
            } else if (str.equals("D")) {
                this.caseD_KD.add(this.cpBands.getConstant((Double) it3.next()));
            } else if (str.equals("F")) {
                this.caseF_KF.add(this.cpBands.getConstant((Float) it3.next()));
            } else if (str.equals("J")) {
                this.caseJ_KJ.add(this.cpBands.getConstant((Long) it3.next()));
            } else if (str.equals("c")) {
                this.casec_RS.add(this.cpBands.getCPSignature((String) it3.next()));
            } else if (str.equals(C3898x.f4338g)) {
                String str2 = (String) it3.next();
                String str3 = (String) it3.next();
                this.caseet_RS.add(this.cpBands.getCPSignature(str2));
                this.caseec_RU.add(this.cpBands.getCPUtf8(str3));
            } else if (str.equals("s")) {
                this.cases_RU.add(this.cpBands.getCPUtf8((String) it3.next()));
            }
        }
        Iterator it5 = list5.iterator();
        while (it5.hasNext()) {
            int intValue = ((Integer) it5.next()).intValue();
            this.casearray_N.add(intValue);
            this.numBackwardsCalls += intValue;
        }
        Iterator it6 = list6.iterator();
        while (it6.hasNext()) {
            this.nesttype_RS.add(this.cpBands.getCPSignature((String) it6.next()));
        }
        Iterator it7 = list7.iterator();
        while (it7.hasNext()) {
            this.nestname_RU.add(this.cpBands.getCPUtf8((String) it7.next()));
        }
        Iterator it8 = list8.iterator();
        while (it8.hasNext()) {
            Integer num = (Integer) it8.next();
            this.nestpair_N.add(num.intValue());
            this.numBackwardsCalls += num.intValue();
        }
    }

    public void addAnnotation(String str, List list, List list2, List list3, List list4, List list5, List list6, List list7) {
        this.type_RS.add(this.cpBands.getCPSignature(str));
        this.pair_N.add(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            this.name_RU.add(this.cpBands.getCPUtf8((String) it.next()));
        }
        Iterator it2 = list3.iterator();
        Iterator it3 = list2.iterator();
        while (it3.hasNext()) {
            String str2 = (String) it3.next();
            this.f8959T.add(str2);
            if (str2.equals("B") || str2.equals("C") || str2.equals("I") || str2.equals(ExifInterface.LATITUDE_SOUTH) || str2.equals("Z")) {
                this.caseI_KI.add(this.cpBands.getConstant((Integer) it2.next()));
            } else if (str2.equals("D")) {
                this.caseD_KD.add(this.cpBands.getConstant((Double) it2.next()));
            } else if (str2.equals("F")) {
                this.caseF_KF.add(this.cpBands.getConstant((Float) it2.next()));
            } else if (str2.equals("J")) {
                this.caseJ_KJ.add(this.cpBands.getConstant((Long) it2.next()));
            } else if (str2.equals("c")) {
                this.casec_RS.add(this.cpBands.getCPSignature((String) it2.next()));
            } else if (str2.equals(C3898x.f4338g)) {
                String str3 = (String) it2.next();
                String str4 = (String) it2.next();
                this.caseet_RS.add(this.cpBands.getCPSignature(str3));
                this.caseec_RU.add(this.cpBands.getCPUtf8(str4));
            } else if (str2.equals("s")) {
                this.cases_RU.add(this.cpBands.getCPUtf8((String) it2.next()));
            }
        }
        Iterator it4 = list4.iterator();
        while (it4.hasNext()) {
            int intValue = ((Integer) it4.next()).intValue();
            this.casearray_N.add(intValue);
            this.numBackwardsCalls += intValue;
        }
        Iterator it5 = list5.iterator();
        while (it5.hasNext()) {
            this.nesttype_RS.add(this.cpBands.getCPSignature((String) it5.next()));
        }
        Iterator it6 = list6.iterator();
        while (it6.hasNext()) {
            this.nestname_RU.add(this.cpBands.getCPUtf8((String) it6.next()));
        }
        Iterator it7 = list7.iterator();
        while (it7.hasNext()) {
            Integer num = (Integer) it7.next();
            this.nestpair_N.add(num.intValue());
            this.numBackwardsCalls += num.intValue();
        }
    }

    public boolean hasContent() {
        return this.type_RS.size() > 0;
    }

    public int numBackwardsCalls() {
        return this.numBackwardsCalls;
    }

    public void incrementAnnoN() {
        this.anno_N.increment(r0.size() - 1);
    }

    public void newEntryInAnnoN() {
        this.anno_N.add(1);
    }

    public void removeLatest() {
        int remove = this.anno_N.remove(r0.size() - 1);
        for (int i = 0; i < remove; i++) {
            this.type_RS.remove(r3.size() - 1);
            int remove2 = this.pair_N.remove(r3.size() - 1);
            for (int i2 = 0; i2 < remove2; i2++) {
                removeOnePair();
            }
        }
    }

    private void removeOnePair() {
        String str = (String) this.f8959T.remove(r0.size() - 1);
        if (str.equals("B") || str.equals("C") || str.equals("I") || str.equals(ExifInterface.LATITUDE_SOUTH) || str.equals("Z")) {
            this.caseI_KI.remove(r0.size() - 1);
            return;
        }
        if (str.equals("D")) {
            this.caseD_KD.remove(r0.size() - 1);
            return;
        }
        if (str.equals("F")) {
            this.caseF_KF.remove(r0.size() - 1);
            return;
        }
        if (str.equals("J")) {
            this.caseJ_KJ.remove(r0.size() - 1);
            return;
        }
        if (str.equals("C")) {
            this.casec_RS.remove(r0.size() - 1);
            return;
        }
        if (str.equals(C3898x.f4338g)) {
            this.caseet_RS.remove(r0.size() - 1);
            this.caseec_RU.remove(this.caseet_RS.size() - 1);
            return;
        }
        if (str.equals("s")) {
            this.cases_RU.remove(r0.size() - 1);
            return;
        }
        int i = 0;
        if (str.equals("[")) {
            int remove = this.casearray_N.remove(r0.size() - 1);
            this.numBackwardsCalls -= remove;
            while (i < remove) {
                removeOnePair();
                i++;
            }
            return;
        }
        if (str.equals("@")) {
            this.nesttype_RS.remove(r0.size() - 1);
            int remove2 = this.nestpair_N.remove(r0.size() - 1);
            this.numBackwardsCalls -= remove2;
            while (i < remove2) {
                removeOnePair();
                i++;
            }
        }
    }
}
