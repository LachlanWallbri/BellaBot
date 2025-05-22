package com.loc;

import android.net.wifi.ScanResult;
import android.text.TextUtils;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import com.loc.C3846bu;
import java.util.ArrayList;
import java.util.zip.CRC32;

/* compiled from: Req.java */
/* renamed from: com.loc.cp */
/* loaded from: classes4.dex */
public final class C3868cp {

    /* renamed from: a */
    public String f3959a = "1";

    /* renamed from: b */
    public short f3960b = 0;

    /* renamed from: c */
    public String f3961c = null;

    /* renamed from: d */
    public String f3962d = null;

    /* renamed from: e */
    public String f3963e = null;

    /* renamed from: f */
    public String f3964f = null;

    /* renamed from: g */
    public String f3965g = null;

    /* renamed from: h */
    public String f3966h = null;

    /* renamed from: i */
    public String f3967i = null;

    /* renamed from: j */
    public String f3968j = null;

    /* renamed from: k */
    public String f3969k = null;

    /* renamed from: l */
    public String f3970l = null;

    /* renamed from: m */
    public String f3971m = null;

    /* renamed from: n */
    public String f3972n = null;

    /* renamed from: o */
    public String f3973o = null;

    /* renamed from: p */
    public String f3974p = null;

    /* renamed from: q */
    public String f3975q = null;

    /* renamed from: r */
    public String f3976r = null;

    /* renamed from: s */
    public String f3977s = null;

    /* renamed from: t */
    public String f3978t = null;

    /* renamed from: u */
    public String f3979u = null;

    /* renamed from: v */
    public String f3980v = null;

    /* renamed from: w */
    public String f3981w = null;

    /* renamed from: x */
    public String f3982x = null;

    /* renamed from: y */
    public String f3983y = null;

    /* renamed from: z */
    public int f3984z = 0;

    /* renamed from: A */
    public String f3946A = null;

    /* renamed from: B */
    public String f3947B = null;

    /* renamed from: C */
    public ArrayList<C3854cb> f3948C = new ArrayList<>();

    /* renamed from: D */
    public String f3949D = null;

    /* renamed from: E */
    public String f3950E = null;

    /* renamed from: F */
    public ArrayList<ScanResult> f3951F = new ArrayList<>();

    /* renamed from: G */
    public ArrayList<C3846bu.a> f3952G = new ArrayList<>();

    /* renamed from: H */
    public String f3953H = null;

    /* renamed from: I */
    public String f3954I = null;

    /* renamed from: J */
    public byte[] f3955J = null;

    /* renamed from: L */
    private byte[] f3957L = null;

    /* renamed from: M */
    private int f3958M = 0;

    /* renamed from: K */
    public String f3956K = null;

    /* renamed from: a */
    private String m2862a(String str, int i) {
        String[] split = this.f3947B.split("\\*")[i].split(",");
        if (str.equals("lac")) {
            return split[0];
        }
        if (str.equals("cellid")) {
            return split[1];
        }
        if (str.equals("signal")) {
            return split[2];
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x000d, code lost:
    
        if (r0.length != 6) goto L6;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private byte[] m2863a(String str) {
        String[] split = str.split(":");
        byte[] bArr = new byte[6];
        if (split != null) {
            try {
            } catch (Throwable th) {
                C3880f.m3097a(th, "Req", "getMacBa " + str);
                return m2863a("00:00:00:00:00:00");
            }
        }
        split = new String[6];
        for (int i = 0; i < split.length; i++) {
            split[i] = "0";
        }
        for (int i2 = 0; i2 < split.length; i2++) {
            if (split[i2].length() > 2) {
                split[i2] = split[i2].substring(0, 2);
            }
            bArr[i2] = (byte) Integer.parseInt(split[i2], 16);
        }
        return bArr;
    }

    /* renamed from: b */
    private String m2864b(String str) {
        if (!this.f3946A.contains(str + ">")) {
            return "0";
        }
        return this.f3946A.substring(this.f3946A.indexOf(str + ">") + str.length() + 1, this.f3946A.indexOf("</" + str));
    }

    /* JADX WARN: Can't wrap try/catch for region: R(21:153|154|(3:155|156|157)|158|(4:160|(1:162)|163|(2:165|166)(4:400|(3:402|(1:408)(2:404|405)|406)|409|410))(1:(4:413|(1:415)|416|166))|167|(15:171|172|173|174|(1:396)(5:177|(1:179)|180|(3:182|(1:214)(4:184|(5:205|(1:207)|208|(1:210)|211)(2:190|(5:192|(1:194)|195|(1:197)|198)(1:204))|199|200)|201)|215)|216|(1:218)(11:376|377|378|379|380|381|382|383|(3:388|(1:390)|386)|385|386)|219|220|(1:222)(8:346|(1:348)(1:375)|349|(1:351)|352|(9:354|355|356|357|358|(1:360)|361|(2:367|368)(1:365)|366)|373|374)|223|224|225|(1:227)|(20:229|230|231|232|233|(1:235)|237|238|239|240|(3:334|335|336)|242|243|(3:245|246|247)(5:260|261|262|(34:264|265|266|267|268|269|(1:271)(1:326)|272|273|274|275|276|277|278|279|280|281|282|283|284|285|286|287|288|(2:310|311)(3:290|291|(2:308|309))|293|294|295|296|297|298|299|301|302)|331)|248|(1:250)(1:257)|251|(1:253)|254|255)(20:343|344|231|232|233|(0)|237|238|239|240|(0)|242|243|(0)(0)|248|(0)(0)|251|(0)|254|255))|399|174|(0)|396|216|(0)(0)|219|220|(0)(0)|223|224|225|(0)|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:345:0x075b, code lost:
    
        r11[r12] = 0;
     */
    /* JADX WARN: Removed duplicated region for block: B:153:0x033f A[Catch: all -> 0x0355, TryCatch #32 {all -> 0x0355, blocks: (B:151:0x0337, B:153:0x033f, B:421:0x0342), top: B:150:0x0337 }] */
    /* JADX WARN: Removed duplicated region for block: B:160:0x039a  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0506 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:218:0x061d  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x069c  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0748  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x074b A[Catch: all -> 0x075b, TryCatch #34 {all -> 0x075b, blocks: (B:225:0x073d, B:229:0x074b, B:343:0x074e), top: B:224:0x073d }] */
    /* JADX WARN: Removed duplicated region for block: B:235:0x076c A[Catch: all -> 0x078d, TRY_LEAVE, TryCatch #27 {all -> 0x078d, blocks: (B:233:0x0764, B:235:0x076c), top: B:232:0x0764 }] */
    /* JADX WARN: Removed duplicated region for block: B:245:0x079d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:250:0x084f  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x085f  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0851  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x07a7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:290:0x0815 A[Catch: all -> 0x0825, TRY_LEAVE, TryCatch #2 {all -> 0x0825, blocks: (B:311:0x0810, B:290:0x0815), top: B:310:0x0810 }] */
    /* JADX WARN: Removed duplicated region for block: B:310:0x0810 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:334:0x077d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:343:0x074e A[Catch: all -> 0x075b, TRY_LEAVE, TryCatch #34 {all -> 0x075b, blocks: (B:225:0x073d, B:229:0x074b, B:343:0x074e), top: B:224:0x073d }] */
    /* JADX WARN: Removed duplicated region for block: B:346:0x06a1  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x0621  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x0452  */
    /* JADX WARN: Removed duplicated region for block: B:421:0x0342 A[Catch: all -> 0x0355, TRY_LEAVE, TryCatch #32 {all -> 0x0355, blocks: (B:151:0x0337, B:153:0x033f, B:421:0x0342), top: B:150:0x0337 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final byte[] m2865a() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        String str;
        int i19;
        int i20;
        int min;
        int length;
        int i21;
        int i22;
        int i23;
        byte[] bArr;
        int i24;
        int i25;
        int min2;
        int i26;
        int i27;
        int length2;
        boolean isEmpty;
        byte[] bytes;
        int i28;
        int length3;
        if (TextUtils.isEmpty(this.f3959a)) {
            this.f3959a = "";
        }
        if (TextUtils.isEmpty(this.f3961c)) {
            this.f3961c = "";
        }
        if (TextUtils.isEmpty(this.f3962d)) {
            this.f3962d = "";
        }
        if (TextUtils.isEmpty(this.f3963e)) {
            this.f3963e = "";
        }
        if (TextUtils.isEmpty(this.f3964f)) {
            this.f3964f = "";
        }
        if (TextUtils.isEmpty(this.f3965g)) {
            this.f3965g = "";
        }
        if (TextUtils.isEmpty(this.f3966h)) {
            this.f3966h = "";
        }
        if (TextUtils.isEmpty(this.f3967i)) {
            this.f3967i = "";
        }
        if (TextUtils.isEmpty(this.f3968j) || (!this.f3968j.equals("1") && !this.f3968j.equals("2"))) {
            this.f3968j = "0";
        }
        if (TextUtils.isEmpty(this.f3969k) || (!this.f3969k.equals("0") && !this.f3969k.equals("1"))) {
            this.f3969k = "0";
        }
        if (TextUtils.isEmpty(this.f3970l)) {
            this.f3970l = "";
        }
        if (TextUtils.isEmpty(this.f3971m)) {
            this.f3971m = "";
        }
        if (TextUtils.isEmpty(this.f3972n)) {
            this.f3972n = "";
        }
        if (TextUtils.isEmpty(this.f3973o)) {
            this.f3973o = "";
        }
        if (TextUtils.isEmpty(this.f3974p)) {
            this.f3974p = "";
        }
        if (TextUtils.isEmpty(this.f3975q)) {
            this.f3975q = "";
        }
        if (TextUtils.isEmpty(this.f3976r)) {
            this.f3976r = "";
        }
        if (TextUtils.isEmpty(this.f3977s)) {
            this.f3977s = "";
        }
        if (TextUtils.isEmpty(this.f3978t)) {
            this.f3978t = "";
        }
        if (TextUtils.isEmpty(this.f3979u)) {
            this.f3979u = "";
        }
        if (TextUtils.isEmpty(this.f3980v)) {
            this.f3980v = "";
        }
        if (TextUtils.isEmpty(this.f3981w)) {
            this.f3981w = "";
        }
        if (TextUtils.isEmpty(this.f3982x)) {
            this.f3982x = "";
        }
        if (TextUtils.isEmpty(this.f3983y) || (!this.f3983y.equals("1") && !this.f3983y.equals("2"))) {
            this.f3983y = "0";
        }
        int i29 = this.f3984z;
        if (!(i29 > 0 && i29 <= 15)) {
            this.f3984z = 0;
        }
        if (TextUtils.isEmpty(this.f3946A)) {
            this.f3946A = "";
        }
        if (TextUtils.isEmpty(this.f3947B)) {
            this.f3947B = "";
        }
        if (TextUtils.isEmpty(this.f3950E)) {
            this.f3950E = "";
        }
        if (TextUtils.isEmpty(this.f3953H)) {
            this.f3953H = "";
        }
        if (TextUtils.isEmpty(this.f3954I)) {
            this.f3954I = "";
        }
        if (TextUtils.isEmpty(this.f3956K)) {
            this.f3956K = "";
        }
        if (this.f3955J == null) {
            this.f3955J = new byte[0];
        }
        byte[] bArr2 = new byte[2];
        byte[] bArr3 = new byte[4];
        byte[] bArr4 = this.f3955J;
        int length4 = bArr4 != null ? bArr4.length + 1 + 4096 : 4096;
        byte[] bArr5 = this.f3957L;
        if (bArr5 == null || length4 > this.f3958M) {
            bArr5 = new byte[length4];
            this.f3957L = bArr5;
            this.f3958M = length4;
        }
        bArr5[0] = Byte.parseByte(this.f3959a);
        byte[] m2980a = C3876cx.m2980a(this.f3960b, (byte[]) null);
        System.arraycopy(m2980a, 0, bArr5, 1, m2980a.length);
        int length5 = m2980a.length + 1;
        try {
            byte[] bytes2 = this.f3961c.getBytes("GBK");
            bArr5[length5] = (byte) bytes2.length;
            length5++;
            System.arraycopy(bytes2, 0, bArr5, length5, bytes2.length);
            i = length5 + bytes2.length;
        } catch (Throwable th) {
            C3880f.m3097a(th, "Req", "buildV4Dot2");
            bArr5[length5] = 0;
            i = length5 + 1;
        }
        try {
            byte[] bytes3 = this.f3962d.getBytes("GBK");
            bArr5[i] = (byte) bytes3.length;
            i++;
            System.arraycopy(bytes3, 0, bArr5, i, bytes3.length);
            i2 = i + bytes3.length;
        } catch (Throwable th2) {
            C3880f.m3097a(th2, "Req", "buildV4Dot21");
            bArr5[i] = 0;
            i2 = i + 1;
        }
        try {
            byte[] bytes4 = this.f3973o.getBytes("GBK");
            bArr5[i2] = (byte) bytes4.length;
            i2++;
            System.arraycopy(bytes4, 0, bArr5, i2, bytes4.length);
            i3 = i2 + bytes4.length;
        } catch (Throwable th3) {
            C3880f.m3097a(th3, "Req", "buildV4Dot22");
            bArr5[i2] = 0;
            i3 = i2 + 1;
        }
        try {
            byte[] bytes5 = this.f3963e.getBytes("GBK");
            bArr5[i3] = (byte) bytes5.length;
            i3++;
            System.arraycopy(bytes5, 0, bArr5, i3, bytes5.length);
            i4 = i3 + bytes5.length;
        } catch (Throwable th4) {
            C3880f.m3097a(th4, "Req", "buildV4Dot23");
            bArr5[i3] = 0;
            i4 = i3 + 1;
        }
        try {
            byte[] bytes6 = this.f3964f.getBytes("GBK");
            bArr5[i4] = (byte) bytes6.length;
            i4++;
            System.arraycopy(bytes6, 0, bArr5, i4, bytes6.length);
            i5 = i4 + bytes6.length;
        } catch (Throwable th5) {
            C3880f.m3097a(th5, "Req", "buildV4Dot24");
            bArr5[i4] = 0;
            i5 = i4 + 1;
        }
        try {
            byte[] bytes7 = this.f3965g.getBytes("GBK");
            bArr5[i5] = (byte) bytes7.length;
            i5++;
            System.arraycopy(bytes7, 0, bArr5, i5, bytes7.length);
            i6 = i5 + bytes7.length;
        } catch (Throwable th6) {
            C3880f.m3097a(th6, "Req", "buildV4Dot25");
            bArr5[i5] = 0;
            i6 = i5 + 1;
        }
        try {
            byte[] bytes8 = this.f3979u.getBytes("GBK");
            bArr5[i6] = (byte) bytes8.length;
            i6++;
            System.arraycopy(bytes8, 0, bArr5, i6, bytes8.length);
            i7 = i6 + bytes8.length;
        } catch (Throwable th7) {
            C3880f.m3097a(th7, "Req", "buildV4Dot26");
            bArr5[i6] = 0;
            i7 = i6 + 1;
        }
        try {
            byte[] bytes9 = this.f3966h.getBytes("GBK");
            bArr5[i7] = (byte) bytes9.length;
            i7++;
            System.arraycopy(bytes9, 0, bArr5, i7, bytes9.length);
            i8 = i7 + bytes9.length;
        } catch (Throwable th8) {
            C3880f.m3097a(th8, "Req", "buildV4Dot27");
            bArr5[i7] = 0;
            i8 = i7 + 1;
        }
        try {
            byte[] bytes10 = this.f3974p.getBytes("GBK");
            bArr5[i8] = (byte) bytes10.length;
            i8++;
            System.arraycopy(bytes10, 0, bArr5, i8, bytes10.length);
            i9 = i8 + bytes10.length;
        } catch (Throwable th9) {
            C3880f.m3097a(th9, "Req", "buildV4Dot28");
            bArr5[i8] = 0;
            i9 = i8 + 1;
        }
        try {
            byte[] bytes11 = this.f3975q.getBytes("GBK");
            bArr5[i9] = (byte) bytes11.length;
            i9++;
            System.arraycopy(bytes11, 0, bArr5, i9, bytes11.length);
            i10 = i9 + bytes11.length;
        } catch (Throwable th10) {
            C3880f.m3097a(th10, "Req", "buildV4Dot29");
            bArr5[i9] = 0;
            i10 = i9 + 1;
        }
        try {
        } catch (Throwable th11) {
            C3880f.m3097a(th11, "Req", "buildV4Dot219");
            bArr5[i10] = 0;
        }
        if (TextUtils.isEmpty(this.f3978t)) {
            bArr5[i10] = 0;
            i11 = i10 + 1;
            try {
                byte[] bytes12 = this.f3980v.getBytes("GBK");
                bArr5[i11] = (byte) bytes12.length;
                i11++;
                System.arraycopy(bytes12, 0, bArr5, i11, bytes12.length);
                i12 = i11 + bytes12.length;
            } catch (Throwable th12) {
                C3880f.m3097a(th12, "Req", "buildV4Dot211");
                bArr5[i11] = 0;
                i12 = i11 + 1;
            }
            try {
                byte[] bytes13 = this.f3981w.getBytes("GBK");
                bArr5[i12] = (byte) bytes13.length;
                i12++;
                System.arraycopy(bytes13, 0, bArr5, i12, bytes13.length);
                i13 = i12 + bytes13.length;
            } catch (Throwable th13) {
                C3880f.m3097a(th13, "Req", "buildV4Dot212");
                bArr5[i12] = 0;
                i13 = i12 + 1;
            }
            try {
            } catch (Throwable th14) {
                C3880f.m3097a(th14, "Req", "buildV4Dot212");
                bArr5[i13] = 0;
            }
            if (TextUtils.isEmpty(this.f3956K)) {
                byte[] bytes14 = this.f3956K.getBytes("GBK");
                bArr5[i13] = (byte) bytes14.length;
                int i30 = i13 + 1;
                System.arraycopy(bytes14, 0, bArr5, i30, bytes14.length);
                i14 = i30 + bytes14.length;
                byte[] bytes15 = this.f3982x.getBytes("GBK");
                bArr5[i14] = (byte) bytes15.length;
                i14++;
                System.arraycopy(bytes15, 0, bArr5, i14, bytes15.length);
                i15 = i14 + bytes15.length;
                bArr5[i15] = Byte.parseByte(this.f3983y);
                int i31 = i15 + 1;
                bArr5[i31] = Byte.parseByte(this.f3968j);
                int i32 = i31 + 1;
                int i33 = this.f3984z;
                i16 = i33 & 3;
                bArr5[i32] = (byte) i33;
                i17 = i32 + 1;
                if (i16 != 1) {
                }
                str = this.f3949D;
                if (str != null) {
                    byte[] bytes16 = str.getBytes("GBK");
                    int min3 = Math.min(bytes16.length, 60);
                    bArr5[i17] = (byte) min3;
                    i17++;
                    System.arraycopy(bytes16, 0, bArr5, i17, min3);
                    i19 = i17 + min3;
                    ArrayList<C3854cb> arrayList = this.f3948C;
                    int size = arrayList.size();
                    int i34 = 3;
                    if ((this.f3984z & 4) == 4) {
                    }
                    bArr5[i19] = 0;
                    i20 = i19 + 1;
                    if (this.f3950E.length() != 0) {
                    }
                    int i35 = i20 + 1;
                    ArrayList<ScanResult> arrayList2 = this.f3951F;
                    min = Math.min(arrayList2.size(), 15);
                    if (min != 0) {
                    }
                    bArr5[length] = 0;
                    int i36 = length + 1;
                    bytes = this.f3954I.getBytes("GBK");
                    if (bytes.length > 127) {
                    }
                    if (bytes == null) {
                    }
                }
                bArr5[i17] = 0;
                i19 = i17 + 1;
                ArrayList<C3854cb> arrayList3 = this.f3948C;
                int size2 = arrayList3.size();
                int i342 = 3;
                if ((this.f3984z & 4) == 4) {
                }
                bArr5[i19] = 0;
                i20 = i19 + 1;
                if (this.f3950E.length() != 0) {
                }
                int i352 = i20 + 1;
                ArrayList<ScanResult> arrayList22 = this.f3951F;
                min = Math.min(arrayList22.size(), 15);
                if (min != 0) {
                }
                bArr5[length] = 0;
                int i362 = length + 1;
                bytes = this.f3954I.getBytes("GBK");
                if (bytes.length > 127) {
                }
                if (bytes == null) {
                }
            } else {
                bArr5[i13] = 0;
                i14 = i13 + 1;
                try {
                    byte[] bytes152 = this.f3982x.getBytes("GBK");
                    bArr5[i14] = (byte) bytes152.length;
                    i14++;
                    System.arraycopy(bytes152, 0, bArr5, i14, bytes152.length);
                    i15 = i14 + bytes152.length;
                } catch (Throwable th15) {
                    C3880f.m3097a(th15, "Req", "buildV4Dot213");
                    bArr5[i14] = 0;
                    i15 = i14 + 1;
                }
                bArr5[i15] = Byte.parseByte(this.f3983y);
                int i312 = i15 + 1;
                bArr5[i312] = Byte.parseByte(this.f3968j);
                int i322 = i312 + 1;
                int i332 = this.f3984z;
                i16 = i332 & 3;
                bArr5[i322] = (byte) i332;
                i17 = i322 + 1;
                if (i16 != 1) {
                    byte[] m3008e = C3876cx.m3008e(m2864b("mcc"));
                    System.arraycopy(m3008e, 0, bArr5, i17, m3008e.length);
                    int length6 = i17 + m3008e.length;
                    byte[] m3008e2 = C3876cx.m3008e(m2864b("mnc"));
                    System.arraycopy(m3008e2, 0, bArr5, length6, m3008e2.length);
                    int length7 = length6 + m3008e2.length;
                    byte[] m3008e3 = C3876cx.m3008e(m2864b("lac"));
                    System.arraycopy(m3008e3, 0, bArr5, length7, m3008e3.length);
                    int length8 = length7 + m3008e3.length;
                    byte[] m3011f = C3876cx.m3011f(m2864b("cellid"));
                    System.arraycopy(m3011f, 0, bArr5, length8, m3011f.length);
                    int length9 = length8 + m3011f.length;
                    int parseInt = Integer.parseInt(m2864b("signal"));
                    if (parseInt > 127 || parseInt < -128) {
                        parseInt = 0;
                    }
                    bArr5[length9] = (byte) parseInt;
                    int i37 = length9 + 1;
                    byte[] m2980a2 = C3876cx.m2980a(0, bArr2);
                    System.arraycopy(m2980a2, 0, bArr5, i37, m2980a2.length);
                    i18 = i37 + 2;
                    if (this.f3947B.length() == 0) {
                        bArr5[i18] = 0;
                        i17 = i18 + 1;
                    } else {
                        int length10 = this.f3947B.split("\\*").length;
                        bArr5[i18] = (byte) length10;
                        int i38 = i18 + 1;
                        for (int i39 = 0; i39 < length10; i39++) {
                            byte[] m3008e4 = C3876cx.m3008e(m2862a("lac", i39));
                            System.arraycopy(m3008e4, 0, bArr5, i38, m3008e4.length);
                            int length11 = i38 + m3008e4.length;
                            byte[] m3011f2 = C3876cx.m3011f(m2862a("cellid", i39));
                            System.arraycopy(m3011f2, 0, bArr5, length11, m3011f2.length);
                            int length12 = length11 + m3011f2.length;
                            int parseInt2 = Integer.parseInt(m2862a("signal", i39));
                            if (parseInt2 > 127 || parseInt2 < -128) {
                                parseInt2 = 0;
                            }
                            bArr5[length12] = (byte) parseInt2;
                            i38 = length12 + 1;
                        }
                        i17 = i38;
                    }
                } else if (i16 == 2) {
                    byte[] m3008e5 = C3876cx.m3008e(m2864b("mcc"));
                    System.arraycopy(m3008e5, 0, bArr5, i17, m3008e5.length);
                    int length13 = i17 + m3008e5.length;
                    byte[] m3008e6 = C3876cx.m3008e(m2864b("sid"));
                    System.arraycopy(m3008e6, 0, bArr5, length13, m3008e6.length);
                    int length14 = length13 + m3008e6.length;
                    byte[] m3008e7 = C3876cx.m3008e(m2864b("nid"));
                    System.arraycopy(m3008e7, 0, bArr5, length14, m3008e7.length);
                    int length15 = length14 + m3008e7.length;
                    byte[] m3008e8 = C3876cx.m3008e(m2864b("bid"));
                    System.arraycopy(m3008e8, 0, bArr5, length15, m3008e8.length);
                    int length16 = length15 + m3008e8.length;
                    byte[] m3011f3 = C3876cx.m3011f(m2864b("lon"));
                    System.arraycopy(m3011f3, 0, bArr5, length16, m3011f3.length);
                    int length17 = length16 + m3011f3.length;
                    byte[] m3011f4 = C3876cx.m3011f(m2864b("lat"));
                    System.arraycopy(m3011f4, 0, bArr5, length17, m3011f4.length);
                    int length18 = length17 + m3011f4.length;
                    int parseInt3 = Integer.parseInt(m2864b("signal"));
                    if (parseInt3 > 127 || parseInt3 < -128) {
                        parseInt3 = 0;
                    }
                    bArr5[length18] = (byte) parseInt3;
                    int i40 = length18 + 1;
                    byte[] m2980a3 = C3876cx.m2980a(0, bArr2);
                    System.arraycopy(m2980a3, 0, bArr5, i40, m2980a3.length);
                    i18 = i40 + 2;
                    bArr5[i18] = 0;
                    i17 = i18 + 1;
                }
                str = this.f3949D;
                if (str != null && (this.f3984z & 8) == 8) {
                    try {
                        byte[] bytes162 = str.getBytes("GBK");
                        int min32 = Math.min(bytes162.length, 60);
                        bArr5[i17] = (byte) min32;
                        i17++;
                        System.arraycopy(bytes162, 0, bArr5, i17, min32);
                        i19 = i17 + min32;
                    } catch (Exception unused) {
                    }
                    ArrayList<C3854cb> arrayList32 = this.f3948C;
                    int size22 = arrayList32.size();
                    int i3422 = 3;
                    if ((this.f3984z & 4) == 4 || size22 <= 0) {
                        bArr5[i19] = 0;
                        i20 = i19 + 1;
                    } else {
                        if (!arrayList32.get(0).f3880o) {
                            size22--;
                        }
                        bArr5[i19] = (byte) size22;
                        i20 = i19 + 1;
                        int i41 = 0;
                        while (i41 < size22) {
                            C3854cb c3854cb = arrayList32.get(i41);
                            if (c3854cb.f3880o) {
                                if (c3854cb.f3876k == 1 || c3854cb.f3876k == i3422 || c3854cb.f3876k == 4) {
                                    byte b = (byte) c3854cb.f3876k;
                                    if (c3854cb.f3879n) {
                                        b = (byte) (b | 8);
                                    }
                                    bArr5[i20] = b;
                                    int i42 = i20 + 1;
                                    byte[] m2980a4 = C3876cx.m2980a(c3854cb.f3866a, bArr2);
                                    System.arraycopy(m2980a4, 0, bArr5, i42, m2980a4.length);
                                    int length19 = i42 + m2980a4.length;
                                    byte[] m2980a5 = C3876cx.m2980a(c3854cb.f3867b, bArr2);
                                    System.arraycopy(m2980a5, 0, bArr5, length19, m2980a5.length);
                                    int length20 = length19 + m2980a5.length;
                                    byte[] m2980a6 = C3876cx.m2980a(c3854cb.f3868c, bArr2);
                                    System.arraycopy(m2980a6, 0, bArr5, length20, m2980a6.length);
                                    int length21 = length20 + m2980a6.length;
                                    byte[] m2980a7 = C3876cx.m2980a(c3854cb.f3869d, bArr3);
                                    System.arraycopy(m2980a7, 0, bArr5, length21, m2980a7.length);
                                    int length22 = length21 + m2980a7.length;
                                    int i43 = c3854cb.f3875j;
                                    if (i43 > 127 || i43 < -128) {
                                        i43 = 99;
                                    }
                                    bArr5[length22] = (byte) i43;
                                    i28 = length22 + 1;
                                    byte[] m2980a8 = C3876cx.m2980a(c3854cb.f3877l, bArr2);
                                    System.arraycopy(m2980a8, 0, bArr5, i28, m2980a8.length);
                                    length3 = m2980a8.length;
                                } else if (c3854cb.f3876k == 2) {
                                    byte b2 = (byte) c3854cb.f3876k;
                                    if (c3854cb.f3879n) {
                                        b2 = (byte) (b2 | 8);
                                    }
                                    bArr5[i20] = b2;
                                    int i44 = i20 + 1;
                                    byte[] m2980a9 = C3876cx.m2980a(c3854cb.f3866a, bArr2);
                                    System.arraycopy(m2980a9, 0, bArr5, i44, m2980a9.length);
                                    int length23 = i44 + m2980a9.length;
                                    byte[] m2980a10 = C3876cx.m2980a(c3854cb.f3872g, bArr2);
                                    System.arraycopy(m2980a10, 0, bArr5, length23, m2980a10.length);
                                    int length24 = length23 + m2980a10.length;
                                    byte[] m2980a11 = C3876cx.m2980a(c3854cb.f3873h, bArr2);
                                    System.arraycopy(m2980a11, 0, bArr5, length24, m2980a11.length);
                                    int length25 = length24 + m2980a11.length;
                                    byte[] m2980a12 = C3876cx.m2980a(c3854cb.f3874i, bArr2);
                                    System.arraycopy(m2980a12, 0, bArr5, length25, m2980a12.length);
                                    int length26 = length25 + m2980a12.length;
                                    byte[] m2994b = C3876cx.m2994b(c3854cb.f3871f, bArr3);
                                    System.arraycopy(m2994b, 0, bArr5, length26, m2994b.length);
                                    int length27 = length26 + m2994b.length;
                                    byte[] m2994b2 = C3876cx.m2994b(c3854cb.f3870e, bArr3);
                                    System.arraycopy(m2994b2, 0, bArr5, length27, m2994b2.length);
                                    int length28 = length27 + m2994b2.length;
                                    int i45 = c3854cb.f3875j;
                                    if (i45 > 127 || i45 < -128) {
                                        i45 = 99;
                                    }
                                    bArr5[length28] = (byte) i45;
                                    i28 = length28 + 1;
                                    byte[] m2980a13 = C3876cx.m2980a(c3854cb.f3877l, bArr2);
                                    System.arraycopy(m2980a13, 0, bArr5, i28, m2980a13.length);
                                    length3 = m2980a13.length;
                                }
                                i20 = i28 + length3;
                            }
                            i41++;
                            i3422 = 3;
                        }
                    }
                    if (this.f3950E.length() != 0) {
                        bArr5[i20] = 0;
                    } else {
                        bArr5[i20] = 1;
                        i20++;
                        try {
                            String[] split = this.f3950E.split(",");
                            byte[] m2863a = m2863a(split[0]);
                            System.arraycopy(m2863a, 0, bArr5, i20, m2863a.length);
                            i20 += m2863a.length;
                            try {
                                byte[] bytes17 = split[2].getBytes("GBK");
                                bArr5[i20] = (byte) bytes17.length;
                                i20++;
                                System.arraycopy(bytes17, 0, bArr5, i20, bytes17.length);
                                i20 += bytes17.length;
                            } catch (Throwable th16) {
                                C3880f.m3097a(th16, "Req", "buildV4Dot214");
                                bArr5[i20] = 0;
                                i20++;
                            }
                            int parseInt4 = Integer.parseInt(split[1]);
                            if (parseInt4 <= 127) {
                                if (parseInt4 < -128) {
                                }
                                bArr5[i20] = Byte.parseByte(String.valueOf(parseInt4));
                            }
                            parseInt4 = 0;
                            bArr5[i20] = Byte.parseByte(String.valueOf(parseInt4));
                        } catch (Throwable th17) {
                            C3880f.m3097a(th17, "Req", "buildV4Dot216");
                            byte[] m2863a2 = m2863a("00:00:00:00:00:00");
                            System.arraycopy(m2863a2, 0, bArr5, i20, m2863a2.length);
                            int length29 = i20 + m2863a2.length;
                            bArr5[length29] = 0;
                            i20 = length29 + 1;
                            bArr5[i20] = Byte.parseByte("0");
                        }
                    }
                    int i3522 = i20 + 1;
                    ArrayList<ScanResult> arrayList222 = this.f3951F;
                    min = Math.min(arrayList222.size(), 15);
                    if (min != 0) {
                        bArr5[i3522] = 0;
                        length = i3522 + 1;
                    } else {
                        bArr5[i3522] = (byte) min;
                        int i46 = i3522 + 1;
                        boolean z = C3876cx.m2996c() >= 17;
                        long m2985b = z ? C3876cx.m2985b() / 1000 : 0L;
                        for (int i47 = 0; i47 < min; i47++) {
                            ScanResult scanResult = arrayList222.get(i47);
                            byte[] m2863a3 = m2863a(scanResult.BSSID);
                            System.arraycopy(m2863a3, 0, bArr5, i46, m2863a3.length);
                            int length30 = i46 + m2863a3.length;
                            try {
                                byte[] bytes18 = scanResult.SSID.getBytes("GBK");
                                bArr5[length30] = (byte) bytes18.length;
                                length30++;
                                System.arraycopy(bytes18, 0, bArr5, length30, bytes18.length);
                                i21 = length30 + bytes18.length;
                            } catch (Exception unused2) {
                                bArr5[length30] = 0;
                                i21 = length30 + 1;
                            }
                            int i48 = scanResult.level;
                            if (i48 > 127 || i48 < -128) {
                                i48 = 0;
                            }
                            bArr5[i21] = Byte.parseByte(String.valueOf(i48));
                            int i49 = i21 + 1;
                            if (!z || (i22 = (int) (((m2985b - scanResult.timestamp) / 1000000) + 1)) < 0) {
                                i22 = 0;
                            }
                            byte[] m2980a14 = C3876cx.m2980a(i22, bArr2);
                            System.arraycopy(m2980a14, 0, bArr5, i49, m2980a14.length);
                            int length31 = i49 + m2980a14.length;
                            byte[] m2980a15 = C3876cx.m2980a(scanResult.frequency, bArr2);
                            System.arraycopy(m2980a15, 0, bArr5, length31, m2980a15.length);
                            i46 = length31 + m2980a15.length;
                        }
                        byte[] m2980a16 = C3876cx.m2980a(Integer.parseInt(this.f3953H), bArr2);
                        System.arraycopy(m2980a16, 0, bArr5, i46, m2980a16.length);
                        length = i46 + m2980a16.length;
                    }
                    bArr5[length] = 0;
                    int i3622 = length + 1;
                    bytes = this.f3954I.getBytes("GBK");
                    if (bytes.length > 127) {
                        bytes = null;
                    }
                    if (bytes == null) {
                        bArr5[i3622] = (byte) bytes.length;
                        int i50 = i3622 + 1;
                        System.arraycopy(bytes, 0, bArr5, i50, bytes.length);
                        i23 = i50 + bytes.length;
                        bArr = new byte[]{0, 0};
                        isEmpty = TextUtils.isEmpty(AMapLocationServer.f1212d);
                        if (!isEmpty) {
                        }
                        System.arraycopy(bArr, 0, bArr5, i23, 2);
                        i25 = i23 + 2;
                        if (!isEmpty) {
                        }
                        i24 = 2;
                        ArrayList<C3846bu.a> arrayList4 = this.f3952G;
                        min2 = Math.min(arrayList4.size(), 65536);
                        if (min2 == 0) {
                        }
                        byte[] bArr6 = this.f3955J;
                        if (bArr6 != null) {
                        }
                        byte[] m2980a17 = C3876cx.m2980a(length2, (byte[]) null);
                        System.arraycopy(m2980a17, 0, bArr5, i26, m2980a17.length);
                        int length32 = i26 + m2980a17.length;
                        if (length2 > 0) {
                        }
                        byte[] bArr7 = new byte[length32];
                        System.arraycopy(bArr5, 0, bArr7, 0, length32);
                        CRC32 crc32 = new CRC32();
                        crc32.update(bArr7);
                        byte[] m2981a = C3876cx.m2981a(crc32.getValue());
                        byte[] bArr8 = new byte[m2981a.length + length32];
                        System.arraycopy(bArr7, 0, bArr8, 0, length32);
                        System.arraycopy(m2981a, 0, bArr8, length32, m2981a.length);
                        return bArr8;
                    }
                    bArr5[i3622] = 0;
                    i23 = i3622 + 1;
                    bArr = new byte[]{0, 0};
                    try {
                        isEmpty = TextUtils.isEmpty(AMapLocationServer.f1212d);
                        if (!isEmpty) {
                            bArr = C3876cx.m2980a(AMapLocationServer.f1212d.length(), bArr2);
                        }
                    } catch (Throwable unused3) {
                        i24 = 2;
                    }
                    try {
                        System.arraycopy(bArr, 0, bArr5, i23, 2);
                        i25 = i23 + 2;
                        if (!isEmpty) {
                            try {
                                byte[] bytes19 = AMapLocationServer.f1212d.getBytes("GBK");
                                System.arraycopy(bytes19, 0, bArr5, i25, bytes19.length);
                                i25 += bytes19.length;
                            } catch (Throwable unused4) {
                            }
                        }
                        i24 = 2;
                    } catch (Throwable unused5) {
                        i24 = 2;
                        i25 = i23 + i24;
                        ArrayList<C3846bu.a> arrayList42 = this.f3952G;
                        min2 = Math.min(arrayList42.size(), 65536);
                        if (min2 == 0) {
                        }
                        byte[] bArr62 = this.f3955J;
                        if (bArr62 != null) {
                        }
                        byte[] m2980a172 = C3876cx.m2980a(length2, (byte[]) null);
                        System.arraycopy(m2980a172, 0, bArr5, i26, m2980a172.length);
                        int length322 = i26 + m2980a172.length;
                        if (length2 > 0) {
                        }
                        byte[] bArr72 = new byte[length322];
                        System.arraycopy(bArr5, 0, bArr72, 0, length322);
                        CRC32 crc322 = new CRC32();
                        crc322.update(bArr72);
                        byte[] m2981a2 = C3876cx.m2981a(crc322.getValue());
                        byte[] bArr82 = new byte[m2981a2.length + length322];
                        System.arraycopy(bArr72, 0, bArr82, 0, length322);
                        System.arraycopy(m2981a2, 0, bArr82, length322, m2981a2.length);
                        return bArr82;
                    }
                    ArrayList<C3846bu.a> arrayList422 = this.f3952G;
                    min2 = Math.min(arrayList422.size(), 65536);
                    if (min2 == 0) {
                        try {
                            System.arraycopy(C3876cx.m2980a(0, bArr2), 0, bArr5, i25, i24);
                        } catch (Throwable unused6) {
                        }
                        i26 = i25 + i24;
                    } else {
                        try {
                            System.arraycopy(C3876cx.m2980a(min2, bArr2), 0, bArr5, i25, i24);
                        } catch (Throwable unused7) {
                        }
                        i26 = i25 + i24;
                        for (int i51 = 0; i51 < min2; i51++) {
                            C3846bu.a aVar = arrayList422.get(i51);
                            try {
                                byte[] bArr9 = aVar.f3799c;
                                System.arraycopy(bArr9, 0, bArr5, i26, bArr9.length);
                                i27 = i26 + bArr9.length;
                            } catch (Throwable unused8) {
                                i27 = i26 + 6;
                            }
                            try {
                                byte[] bytes20 = aVar.f3797a.getBytes("GBK");
                                int i52 = 32;
                                if (bytes20.length <= 32) {
                                    i52 = bytes20.length;
                                }
                                System.arraycopy(bytes20, 0, bArr5, i27, i52);
                            } catch (Throwable unused9) {
                            }
                            int i53 = i27 + 32;
                            try {
                                byte[] bArr10 = {0, 0, 0, 0};
                                byte[] bytes21 = aVar.f3800d.getBytes("GBK");
                                System.arraycopy(bytes21, 0, bArr5, i53, bytes21.length);
                            } catch (Throwable unused10) {
                            }
                            int i54 = i53 + 4;
                            try {
                                byte[] bArr11 = new byte[4];
                                bArr11[0] = 0;
                                bArr11[1] = 0;
                                try {
                                    bArr11[2] = 0;
                                    try {
                                        bArr11[3] = 0;
                                        byte[] bytes22 = aVar.f3801e.getBytes("GBK");
                                        System.arraycopy(bytes22, 0, bArr5, i54, bytes22.length);
                                    } catch (Throwable unused11) {
                                    }
                                } catch (Throwable unused12) {
                                    int i55 = i54 + 4;
                                    if (aVar.f3803g <= 127) {
                                    }
                                    bArr5[i55] = (byte) aVar.f3803g;
                                    int i56 = i55 + 1;
                                    byte[] m2994b3 = C3876cx.m2994b(aVar.f3802f, new byte[4]);
                                    System.arraycopy(m2994b3, 0, bArr5, i56, m2994b3.length);
                                    int i57 = i56 + 4;
                                    byte[] m2994b4 = C3876cx.m2994b(aVar.f3806j, new byte[4]);
                                    System.arraycopy(m2994b4, 0, bArr5, i57, m2994b4.length);
                                    i26 = i57 + 4;
                                }
                            } catch (Throwable unused13) {
                            }
                            int i552 = i54 + 4;
                            try {
                                if (aVar.f3803g <= 127) {
                                    try {
                                        aVar.f3803g = 0;
                                    } catch (Throwable unused14) {
                                        int i562 = i552 + 1;
                                        byte[] m2994b32 = C3876cx.m2994b(aVar.f3802f, new byte[4]);
                                        System.arraycopy(m2994b32, 0, bArr5, i562, m2994b32.length);
                                        int i572 = i562 + 4;
                                        byte[] m2994b42 = C3876cx.m2994b(aVar.f3806j, new byte[4]);
                                        System.arraycopy(m2994b42, 0, bArr5, i572, m2994b42.length);
                                        i26 = i572 + 4;
                                    }
                                } else if (aVar.f3803g < -128) {
                                    try {
                                        aVar.f3803g = 0;
                                    } catch (Throwable unused15) {
                                    }
                                }
                                bArr5[i552] = (byte) aVar.f3803g;
                            } catch (Throwable unused16) {
                            }
                            int i5622 = i552 + 1;
                            try {
                                byte[] m2994b322 = C3876cx.m2994b(aVar.f3802f, new byte[4]);
                                System.arraycopy(m2994b322, 0, bArr5, i5622, m2994b322.length);
                            } catch (Throwable unused17) {
                            }
                            int i5722 = i5622 + 4;
                            try {
                                byte[] m2994b422 = C3876cx.m2994b(aVar.f3806j, new byte[4]);
                                System.arraycopy(m2994b422, 0, bArr5, i5722, m2994b422.length);
                            } catch (Throwable unused18) {
                            }
                            i26 = i5722 + 4;
                        }
                    }
                    byte[] bArr622 = this.f3955J;
                    length2 = bArr622 != null ? bArr622.length : 0;
                    byte[] m2980a1722 = C3876cx.m2980a(length2, (byte[]) null);
                    System.arraycopy(m2980a1722, 0, bArr5, i26, m2980a1722.length);
                    int length3222 = i26 + m2980a1722.length;
                    if (length2 > 0) {
                        byte[] bArr12 = this.f3955J;
                        System.arraycopy(bArr12, 0, bArr5, length3222, bArr12.length);
                        length3222 += this.f3955J.length;
                    }
                    byte[] bArr722 = new byte[length3222];
                    System.arraycopy(bArr5, 0, bArr722, 0, length3222);
                    CRC32 crc3222 = new CRC32();
                    crc3222.update(bArr722);
                    byte[] m2981a22 = C3876cx.m2981a(crc3222.getValue());
                    byte[] bArr822 = new byte[m2981a22.length + length3222];
                    System.arraycopy(bArr722, 0, bArr822, 0, length3222);
                    System.arraycopy(m2981a22, 0, bArr822, length3222, m2981a22.length);
                    return bArr822;
                }
                bArr5[i17] = 0;
                i19 = i17 + 1;
                ArrayList<C3854cb> arrayList322 = this.f3948C;
                int size222 = arrayList322.size();
                int i34222 = 3;
                if ((this.f3984z & 4) == 4) {
                }
                bArr5[i19] = 0;
                i20 = i19 + 1;
                if (this.f3950E.length() != 0) {
                }
                int i35222 = i20 + 1;
                ArrayList<ScanResult> arrayList2222 = this.f3951F;
                min = Math.min(arrayList2222.size(), 15);
                if (min != 0) {
                }
                bArr5[length] = 0;
                int i36222 = length + 1;
                bytes = this.f3954I.getBytes("GBK");
                if (bytes.length > 127) {
                }
                if (bytes == null) {
                }
            }
        } else {
            byte[] m2863a4 = m2863a(this.f3978t);
            bArr5[i10] = (byte) m2863a4.length;
            int i58 = i10 + 1;
            System.arraycopy(m2863a4, 0, bArr5, i58, m2863a4.length);
            i11 = i58 + m2863a4.length;
            byte[] bytes122 = this.f3980v.getBytes("GBK");
            bArr5[i11] = (byte) bytes122.length;
            i11++;
            System.arraycopy(bytes122, 0, bArr5, i11, bytes122.length);
            i12 = i11 + bytes122.length;
            byte[] bytes132 = this.f3981w.getBytes("GBK");
            bArr5[i12] = (byte) bytes132.length;
            i12++;
            System.arraycopy(bytes132, 0, bArr5, i12, bytes132.length);
            i13 = i12 + bytes132.length;
            if (TextUtils.isEmpty(this.f3956K)) {
            }
        }
    }
}
