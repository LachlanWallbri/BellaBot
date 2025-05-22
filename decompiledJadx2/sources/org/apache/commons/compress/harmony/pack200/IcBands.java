package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import kotlin.text.Typography;

/* loaded from: classes9.dex */
public class IcBands extends BandSet {
    private int bit16Count;
    private final CpBands cpBands;
    private final Set innerClasses;
    private final Map outerToInner;

    public IcBands(SegmentHeader segmentHeader, CpBands cpBands, int i) {
        super(i, segmentHeader);
        this.innerClasses = new TreeSet();
        this.bit16Count = 0;
        this.outerToInner = new HashMap();
        this.cpBands = cpBands;
    }

    public void finaliseBands() {
        this.segmentHeader.setIc_count(this.innerClasses.size());
    }

    @Override // org.apache.commons.compress.harmony.pack200.BandSet
    public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
        PackingUtils.log("Writing internal class bands...");
        int[] iArr = new int[this.innerClasses.size()];
        int[] iArr2 = new int[this.innerClasses.size()];
        int i = this.bit16Count;
        int[] iArr3 = new int[i];
        int[] iArr4 = new int[i];
        ArrayList arrayList = new ArrayList(this.innerClasses);
        int i2 = 0;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            IcTuple icTuple = (IcTuple) arrayList.get(i3);
            iArr[i3] = icTuple.f8955C.getIndex();
            iArr2[i3] = icTuple.f8957F;
            if ((icTuple.f8957F & 65536) != 0) {
                iArr3[i2] = icTuple.f8956C2 == null ? 0 : icTuple.f8956C2.getIndex() + 1;
                iArr4[i2] = icTuple.f8958N == null ? 0 : icTuple.f8958N.getIndex() + 1;
                i2++;
            }
        }
        byte[] encodeBandInt = encodeBandInt("ic_this_class", iArr, Codec.UDELTA5);
        outputStream.write(encodeBandInt);
        PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from ic_this_class[" + iArr.length + "]");
        byte[] encodeBandInt2 = encodeBandInt("ic_flags", iArr2, Codec.UNSIGNED5);
        outputStream.write(encodeBandInt2);
        PackingUtils.log("Wrote " + encodeBandInt2.length + " bytes from ic_flags[" + iArr2.length + "]");
        byte[] encodeBandInt3 = encodeBandInt("ic_outer_class", iArr3, Codec.DELTA5);
        outputStream.write(encodeBandInt3);
        PackingUtils.log("Wrote " + encodeBandInt3.length + " bytes from ic_outer_class[" + iArr3.length + "]");
        byte[] encodeBandInt4 = encodeBandInt("ic_name", iArr4, Codec.DELTA5);
        outputStream.write(encodeBandInt4);
        PackingUtils.log("Wrote " + encodeBandInt4.length + " bytes from ic_name[" + iArr4.length + "]");
    }

    public void addInnerClass(String str, String str2, String str3, int i) {
        if (str2 != null || str3 != null) {
            if (namesArePredictable(str, str2, str3)) {
                IcTuple icTuple = new IcTuple(this.cpBands.getCPClass(str), i, null, null);
                addToMap(str2, icTuple);
                this.innerClasses.add(icTuple);
                return;
            } else {
                IcTuple icTuple2 = new IcTuple(this.cpBands.getCPClass(str), i | 65536, this.cpBands.getCPClass(str2), this.cpBands.getCPUtf8(str3));
                if (this.innerClasses.add(icTuple2)) {
                    this.bit16Count++;
                    addToMap(str2, icTuple2);
                    return;
                }
                return;
            }
        }
        IcTuple icTuple3 = new IcTuple(this.cpBands.getCPClass(str), i, null, null);
        addToMap(getOuter(str), icTuple3);
        this.innerClasses.add(icTuple3);
    }

    public List getInnerClassesForOuter(String str) {
        return (List) this.outerToInner.get(str);
    }

    private String getOuter(String str) {
        return str.substring(0, str.lastIndexOf(36));
    }

    private void addToMap(String str, IcTuple icTuple) {
        List list = (List) this.outerToInner.get(str);
        if (list == null) {
            ArrayList arrayList = new ArrayList();
            this.outerToInner.put(str, arrayList);
            arrayList.add(icTuple);
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (icTuple.equals((IcTuple) it.next())) {
                    return;
                }
            }
            list.add(icTuple);
        }
    }

    private boolean namesArePredictable(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(Typography.dollar);
        sb.append(str3);
        return str.equals(sb.toString()) && str3.indexOf(36) == -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public class IcTuple implements Comparable {

        /* renamed from: C */
        protected CPClass f8955C;

        /* renamed from: C2 */
        protected CPClass f8956C2;

        /* renamed from: F */
        protected int f8957F;

        /* renamed from: N */
        protected CPUTF8 f8958N;

        public IcTuple(CPClass cPClass, int i, CPClass cPClass2, CPUTF8 cputf8) {
            this.f8955C = cPClass;
            this.f8957F = i;
            this.f8956C2 = cPClass2;
            this.f8958N = cputf8;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof IcTuple)) {
                return false;
            }
            IcTuple icTuple = (IcTuple) obj;
            if (!this.f8955C.equals(icTuple.f8955C) || this.f8957F != icTuple.f8957F) {
                return false;
            }
            CPClass cPClass = this.f8956C2;
            if (cPClass != null) {
                if (!cPClass.equals(icTuple.f8956C2)) {
                    return false;
                }
            } else if (icTuple.f8956C2 != null) {
                return false;
            }
            CPUTF8 cputf8 = this.f8958N;
            if (cputf8 != null) {
                if (!cputf8.equals(icTuple.f8958N)) {
                    return false;
                }
            } else if (icTuple.f8958N != null) {
                return false;
            }
            return true;
        }

        public String toString() {
            return this.f8955C.toString();
        }

        @Override // java.lang.Comparable
        public int compareTo(Object obj) {
            return this.f8955C.compareTo(((IcTuple) obj).f8955C);
        }

        public boolean isAnonymous() {
            String cPClass = this.f8955C.toString();
            return Character.isDigit(cPClass.substring(cPClass.lastIndexOf(36) + 1).charAt(0));
        }
    }

    public IcTuple getIcTuple(CPClass cPClass) {
        for (IcTuple icTuple : this.innerClasses) {
            if (icTuple.f8955C.equals(cPClass)) {
                return icTuple;
            }
        }
        return null;
    }
}
