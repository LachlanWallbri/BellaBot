package com.acs.smartcard;

import com.google.common.base.Ascii;
import com.pudu.library.loracall.SlipConfig;
import com.slamtec.slamware.robot.HealthInfo;
import java.util.Arrays;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.jetbrains.anko.DimensionsKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* renamed from: com.acs.smartcard.h */
/* loaded from: classes.dex */
public final class C0744h {

    /* renamed from: m */
    private static int[] f242m = {372, 372, 558, 744, 1116, 1488, 1860, 0, 0, 512, 768, 1024, 1536, 2048};

    /* renamed from: n */
    private static int[] f243n = {0, 1, 2, 4, 8, 16, 32, 64, 12, 20};

    /* renamed from: a */
    public int f244a;

    /* renamed from: b */
    public boolean f245b;

    /* renamed from: e */
    public C0740d f248e;

    /* renamed from: f */
    public C0739c f249f;

    /* renamed from: h */
    public int f251h;

    /* renamed from: j */
    public int f253j;

    /* renamed from: c */
    public C0743g f246c = new C0743g();

    /* renamed from: d */
    public C0738b f247d = new C0738b();

    /* renamed from: g */
    public byte[] f250g = new byte[288];

    /* renamed from: i */
    public byte[] f252i = new byte[288];

    /* renamed from: k */
    public C0741e f254k = new C0741e();

    /* renamed from: o */
    private C0746j f256o = new C0746j();

    /* renamed from: l */
    public C0749m f255l = new C0749m();

    /* renamed from: b */
    private static int m75b(int i) {
        int i2 = 1;
        while (i > 0) {
            i2 <<= 1;
            i--;
        }
        return i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:139:0x0216, code lost:
    
        if (com.acs.smartcard.C0744h.f242m[r21.f247d.f194q.f232b] != 0) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x0220, code lost:
    
        if (r21.f247d.f194q.f231a != 1) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x0222, code lost:
    
        r21.f247d.f194q.f233c = r21.f247d.f185h;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x0232, code lost:
    
        if (r21.f247d.f194q.f233c < 0) goto L192;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x023a, code lost:
    
        if (r21.f247d.f194q.f233c > r8) goto L193;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0246, code lost:
    
        if (com.acs.smartcard.C0744h.f243n[r21.f247d.f194q.f233c] != 0) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x024a, code lost:
    
        r21.f247d.f194q.f235e = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x0256, code lost:
    
        if (r21.f247d.f194q.f233c > 1) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x0263, code lost:
    
        if (com.acs.smartcard.C0744h.f242m[r21.f247d.f194q.f232b] == 0) goto L198;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x026f, code lost:
    
        if (com.acs.smartcard.C0744h.f243n[r21.f247d.f194q.f233c] == 0) goto L199;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0271, code lost:
    
        r1 = ((r21.f247d.f194q.f234d * 1000) * com.acs.smartcard.C0744h.f243n[r21.f247d.f194q.f233c]) / com.acs.smartcard.C0744h.f242m[r21.f247d.f194q.f232b];
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x0294, code lost:
    
        if (r1 > r21.f246c.f240d) goto L200;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x0296, code lost:
    
        r21.f247d.f194q.f235e = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x02a2, code lost:
    
        if (r21.f247d.f194q.f232b != 1) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x02aa, code lost:
    
        if (r21.f247d.f194q.f233c != 1) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x02ac, code lost:
    
        r21.f247d.f194q.f235e = r21.f246c.f239c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x02bd, code lost:
    
        if (r21.f247d.f194q.f235e != 0) goto L194;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x02bf, code lost:
    
        r21.f247d.f194q.f232b--;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x02ce, code lost:
    
        if (r21.f247d.f194q.f235e == 0) goto L195;
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x0575, code lost:
    
        r21.f247d.f194q.f233c--;
        r8 = 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00f1, code lost:
    
        if (r13 == 0) goto L43;
     */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0111  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int m76a() {
        int i;
        int i2;
        byte[] bArr = this.f247d.f180c;
        int i3 = this.f247d.f181d;
        int i4 = 4;
        byte[] bArr2 = new byte[4];
        byte[] bArr3 = new byte[4];
        byte[] bArr4 = new byte[4];
        byte[] bArr5 = new byte[4];
        if (i3 < 2) {
            return -1073741804;
        }
        int i5 = 0;
        if (bArr[0] != 59 && bArr[0] != 63 && bArr[0] != 3) {
            StringBuilder sb = new StringBuilder("initial character ");
            sb.append(Integer.toHexString(bArr[0] & 255));
            sb.append(" of ATR is invalid");
            return -1073741804;
        }
        if (bArr[0] == 3) {
            this.f247d.f178a = true;
            m73a(this.f247d.f180c, 0, this.f247d.f181d);
        } else {
            this.f247d.f178a = false;
        }
        int i6 = i3 - 1;
        int i7 = 0;
        byte b = 0;
        while (i7 < i6) {
            i7++;
            b = (byte) (bArr[i7] ^ b);
            i4 = 4;
        }
        this.f247d.f190m = 0;
        Arrays.fill(bArr2, (byte) 0);
        Arrays.fill(bArr3, (byte) 0);
        Arrays.fill(bArr4, (byte) 0);
        Arrays.fill(bArr5, (byte) 0);
        bArr2[0] = 17;
        bArr3[0] = 37;
        bArr4[1] = 10;
        byte b2 = 15;
        this.f247d.f183f = bArr[1] & 15;
        byte b3 = (byte) (bArr[1] & 240);
        int i8 = i6 - 1;
        int i9 = 0;
        int i10 = 0;
        int i11 = 2;
        int i12 = 0;
        boolean z = false;
        while (i9 < i4) {
            if ((b3 & 16) != 0) {
                if (i9 == 1) {
                    z = true;
                }
                bArr2[i9] = bArr[i11];
                i11++;
                i8--;
            }
            if ((b3 & 32) != 0) {
                bArr3[i9] = bArr[i11];
                i11++;
                i8--;
            }
            if ((b3 & 64) != 0) {
                bArr4[i9] = bArr[i11];
                i11++;
                i8--;
            }
            if ((b3 & 128) == 0) {
                break;
            }
            b3 = (byte) (bArr[i11] & 240);
            bArr5[i9] = (byte) (bArr[i11] & 15);
            i11++;
            i8--;
            if (((1 << bArr5[i9]) & i12) == 0) {
                i10++;
            }
            i12 |= 1 << bArr5[i9];
            i9++;
            i4 = 4;
        }
        if ((i12 & (-2)) != 0) {
            i8--;
        }
        if (i8 >= 0 && i8 == this.f247d.f183f) {
            i = 0;
            if (i == 0) {
                if (this.f247d.f178a) {
                    m73a(bArr, 0, i8);
                    this.f247d.f178a = false;
                }
                return i;
            }
            System.arraycopy(bArr, i11, this.f247d.f182e, 0, this.f247d.f183f);
            this.f247d.f184g = (bArr2[0] & 240) >> 4;
            this.f247d.f185h = bArr2[0] & 15;
            this.f247d.f186i = (bArr3[0] & SlipConfig.END) >> 6;
            this.f247d.f187j = (bArr3[1] & 255) != 0 ? bArr3[1] & 255 : (bArr3[0] & Ascii.f1926US) * 10;
            this.f247d.f188k = bArr4[0] & 255;
            if (f242m[this.f247d.f184g] == 0 || f243n[this.f247d.f185h] == 0) {
                StringBuilder sb2 = new StringBuilder("Fl = ");
                sb2.append(Integer.toHexString(this.f247d.f184g));
                sb2.append(" or Dl = ");
                sb2.append(Integer.toHexString(this.f247d.f185h));
                sb2.append(" invalid");
                return -1073741804;
            }
            StringBuilder sb3 = new StringBuilder("Card parameters from ATR: Fl = ");
            sb3.append(Integer.toHexString(this.f247d.f184g));
            sb3.append(", Dl = ");
            sb3.append(Integer.toHexString(this.f247d.f185h));
            sb3.append(", I = ");
            sb3.append(Integer.toHexString(this.f247d.f186i));
            sb3.append(", P = ");
            sb3.append(Integer.toHexString(this.f247d.f187j));
            sb3.append(", N = ");
            sb3.append(Integer.toHexString(this.f247d.f188k));
            this.f247d.f194q.f234d = this.f246c.f238b;
            if (this.f247d.f194q.f231a == 0) {
                this.f247d.f194q.f232b = 1;
                this.f247d.f194q.f233c = 1;
                this.f247d.f194q.f235e = this.f246c.f239c;
            } else {
                if (this.f247d.f194q.f231a == 1) {
                    this.f247d.f194q.f232b = this.f247d.f184g;
                }
                if (this.f247d.f194q.f232b >= 0) {
                    if (this.f247d.f194q.f232b <= 15) {
                    }
                }
                return -1073741811;
            }
            C0738b c0738b = this.f247d;
            c0738b.f179b = ((f242m[c0738b.f194q.f232b] * 1000000) / ((f243n[this.f247d.f194q.f233c] * this.f247d.f194q.f234d) * 1000)) + 1;
            this.f247d.f189l = 0;
            if (this.f247d.f188k == 0) {
                this.f247d.f194q.f236f = 2;
            } else if (this.f247d.f188k == 255) {
                this.f247d.f194q.f236f = 1;
            } else {
                C0738b c0738b2 = this.f247d;
                c0738b2.f189l = c0738b2.f188k * this.f247d.f179b;
            }
            StringBuilder sb4 = new StringBuilder("PTS parameters: Fl = ");
            sb4.append(Integer.toHexString(this.f247d.f194q.f232b));
            sb4.append(" (");
            sb4.append(this.f247d.f194q.f234d);
            sb4.append(" KHz), Dl = ");
            sb4.append(Integer.toHexString(this.f247d.f194q.f233c));
            sb4.append(" (");
            sb4.append(this.f247d.f194q.f235e);
            sb4.append(" bps, ");
            sb4.append(this.f247d.f194q.f236f);
            sb4.append(" stop bits)");
            StringBuilder sb5 = new StringBuilder("Calculated timing values: Work etu = ");
            sb5.append(this.f247d.f179b);
            sb5.append(" micro sec, Guard time = ");
            sb5.append(this.f247d.f189l);
            sb5.append(" micro sec");
            if (z || (i10 <= 1 && this.f247d.f184g == 1 && this.f247d.f185h == 1)) {
                this.f246c.f237a = 6;
            } else {
                this.f246c.f237a = 5;
            }
            if (bArr5[0] == 0) {
                this.f247d.f190m |= 1;
                this.f247d.f192o.f257a = bArr4[1];
                if (this.f247d.f194q.f233c > 0 && this.f247d.f194q.f233c < 6) {
                    this.f247d.f192o.f258b = (this.f247d.f192o.f257a * 960 * this.f247d.f179b * m75b(this.f247d.f194q.f233c - 1)) + 1;
                } else {
                    this.f247d.f192o.f258b = (((this.f247d.f192o.f257a * 960) * this.f247d.f179b) / m75b(this.f247d.f194q.f233c - 1)) + 1;
                }
                new StringBuilder("T=0 Values from ATR: WI = ").append(this.f247d.f192o.f257a);
                StringBuilder sb6 = new StringBuilder("T=0 Timing from ATR: WT = ");
                sb6.append(this.f247d.f192o.f258b / 1000);
                sb6.append(" ms");
            }
            if ((i12 & 2) != 0) {
                while (true) {
                    if (bArr5[i5] == 1) {
                        i2 = 4;
                        break;
                    }
                    i2 = 4;
                    if (i5 >= 4) {
                        break;
                    }
                    i5++;
                }
                while (bArr5[i5] == 1 && i5 < i2) {
                    i5++;
                }
                if (i5 == i2) {
                    return -1073741804;
                }
                this.f247d.f190m |= 2;
                this.f247d.f193p.f260a = (bArr2[i5] & 255) != 0 ? bArr2[i5] & 255 : 32;
                this.f247d.f193p.f261b = (bArr3[i5] & b2) != 0 ? bArr3[i5] & b2 : 13;
                this.f247d.f193p.f262c = ((bArr3[i5] & 240) >> 4) != 0 ? (bArr3[i5] & 240) >> 4 : 4;
                this.f247d.f193p.f263d = bArr4[i5] & 1;
                this.f247d.f193p.f264e = ((m75b(this.f247d.f193p.f261b) + 11) * this.f247d.f179b) + 1;
                this.f247d.f193p.f265f = ((m75b(this.f247d.f193p.f262c) * 1000000) / 10) + 1 + (this.f247d.f179b * 11);
                StringBuilder sb7 = new StringBuilder("T=1 Values from ATR: IFSC = ");
                sb7.append(this.f247d.f193p.f260a);
                sb7.append(", CWI = ");
                sb7.append(this.f247d.f193p.f261b);
                sb7.append(", BWI = ");
                sb7.append(this.f247d.f193p.f262c);
                sb7.append(", EDC = ");
                sb7.append(this.f247d.f193p.f263d);
                StringBuilder sb8 = new StringBuilder("T=1 Timing from ATR: CWT = ");
                sb8.append(this.f247d.f193p.f264e / 1000);
                sb8.append(" ms, BWT = ");
                sb8.append(this.f247d.f193p.f265f / 1000);
                sb8.append(" ms");
            }
            if (this.f246c.f237a == 6) {
                if (z) {
                    this.f247d.f191n = 1 << (bArr2[1] & b2);
                } else {
                    C0738b c0738b3 = this.f247d;
                    c0738b3.f191n = c0738b3.f190m;
                }
                new StringBuilder("Mode: Specific").append(z ? "set by TA(2)" : "");
            }
            this.f247d.f190m |= 65536;
            return i;
        }
        i = -1073741804;
        if (i == 0) {
        }
    }

    /* renamed from: b */
    public final int m77b() {
        if (this.f254k.f228c > 288) {
            return -2147483643;
        }
        if (this.f254k.f228c < 4) {
            StringBuilder sb = new StringBuilder("t0Request: TPDU is too short (");
            sb.append(this.f254k.f228c);
            sb.append("). Must be at least 4 bytes.");
            return -1073741811;
        }
        System.arraycopy(this.f254k.f227b, 0, this.f250g, this.f251h, this.f254k.f228c);
        int i = this.f251h;
        this.f251h = this.f254k.f228c + i;
        if (this.f254k.f228c <= 5) {
            this.f256o.f259a = 0;
            if (this.f254k.f228c == 4) {
                this.f250g[i + 4] = 0;
                this.f251h++;
            } else {
                int i2 = this.f250g[i + 4] & 255;
            }
        } else {
            this.f256o.f259a = this.f250g[i + 4] & 255;
            if (this.f256o.f259a != this.f254k.f228c - 5) {
                StringBuilder sb2 = new StringBuilder("t0Request: Lc (");
                sb2.append(this.f256o.f259a);
                sb2.append(") in TPDU does not match the number of bytes to send (");
                sb2.append(this.f254k.f228c - 5);
                sb2.append(")");
                return -1073741811;
            }
        }
        if (this.f247d.f178a) {
            m73a(this.f250g, i, this.f251h - i);
        }
        return 0;
    }

    /* renamed from: c */
    public final int m78c() {
        if (this.f253j < 2) {
            return -1073741434;
        }
        if (this.f254k.f230e < this.f253j) {
            return -1073741789;
        }
        if (this.f247d.f178a) {
            m73a(this.f252i, 0, this.f253j);
        }
        System.arraycopy(this.f252i, 0, this.f254k.f229d, 0, this.f253j);
        this.f254k.f226a = this.f253j;
        return 0;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x002a. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:18:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x011a  */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int m79d() {
        C0748l c0748l = new C0748l();
        if (this.f255l.f286q) {
            this.f255l.f283n = 0;
        }
        this.f255l.f286q = true;
        int i = this.f255l.f283n;
        if (i == 0) {
            this.f255l.f283n = 193;
        } else if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        switch (i) {
                            case 192:
                                c0748l.f266a = 0;
                                c0748l.f267b = 192;
                                c0748l.f268c = 0;
                                this.f255l.f282m = 0;
                                break;
                            case 193:
                                if (this.f255l.f283n == 193) {
                                    this.f255l.f283n = 193;
                                    c0748l.f266a = 0;
                                    c0748l.f267b = 193;
                                    c0748l.f268c = 1;
                                    c0748l.f269d[0] = (byte) this.f255l.f271b;
                                    break;
                                } else {
                                    this.f255l.f283n = 2;
                                    break;
                                }
                            case 194:
                                c0748l.f266a = 0;
                                c0748l.f267b = 194;
                                c0748l.f268c = 0;
                                break;
                            default:
                                switch (i) {
                                    case 225:
                                        if (this.f255l.f278i == 0) {
                                            C0749m c0749m = this.f255l;
                                            c0749m.f283n = c0749m.f278i;
                                        }
                                        c0748l.f266a = 0;
                                        c0748l.f267b = 225;
                                        c0748l.f268c = 1;
                                        c0748l.f269d[0] = (byte) this.f255l.f270a;
                                        break;
                                    case 226:
                                        this.f255l.f283n = 1;
                                        c0748l.f266a = 0;
                                        c0748l.f267b = 226;
                                        c0748l.f268c = 0;
                                        break;
                                    case 227:
                                        C0749m c0749m2 = this.f255l;
                                        c0749m2.f283n = c0749m2.f278i;
                                        c0748l.f266a = 0;
                                        c0748l.f267b = 227;
                                        c0748l.f268c = 1;
                                        c0748l.f269d[0] = (byte) this.f255l.f284o;
                                        break;
                                    default:
                                        new StringBuilder("t1Request: Invalid state: ").append(this.f255l.f283n);
                                        return -1073741436;
                                }
                        }
                    }
                    new StringBuilder("t1Request: T1_").append(this.f255l.f283n != 1 ? "START" : "RESTART");
                    this.f255l.f274e = this.f254k.f228c;
                    this.f255l.f273d = 0;
                    this.f255l.f272c = 0;
                    this.f255l.f270a = this.f247d.f193p.f260a;
                    this.f255l.f279j = 0;
                    this.f255l.f278i = 0;
                    this.f255l.f276g = false;
                    if (this.f255l.f283n == 193) {
                    }
                } else {
                    StringBuilder sb = new StringBuilder("t1Request: R(");
                    sb.append(this.f255l.f281l);
                    sb.append(") ->");
                    c0748l.f266a = 0;
                    c0748l.f267b = (this.f255l.f281l << 4) | 128 | this.f255l.f275f;
                    if (this.f255l.f275f != 0) {
                        this.f255l.f275f = 0;
                        if (this.f255l.f278i == 0) {
                            this.f255l.f283n = 1;
                            return -1073741595;
                        }
                        C0749m c0749m3 = this.f255l;
                        c0749m3.f283n = c0749m3.f278i;
                    }
                    c0748l.f268c = 0;
                }
                this.f250g[this.f251h] = (byte) c0748l.f266a;
                this.f250g[this.f251h + 1] = (byte) c0748l.f267b;
                this.f250g[this.f251h + 2] = (byte) c0748l.f268c;
                if (c0748l.f268c > 0) {
                    System.arraycopy(c0748l.f269d, 0, this.f250g, this.f251h + 3, c0748l.f268c);
                }
                m74a(this.f250g, this.f251h, this.f247d.f193p.f263d, false);
                if (this.f247d.f178a) {
                    m73a(this.f250g, this.f251h, ((this.f247d.f193p.f263d & 1) != 0 ? 5 : 4) + c0748l.f268c);
                }
                this.f251h += ((1 & this.f247d.f193p.f263d) != 0 ? 5 : 4) + c0748l.f268c;
                return 0;
            }
            this.f255l.f283n = 2;
            C0749m c0749m4 = this.f255l;
            c0749m4.f287r = c0749m4.f270a;
            if (this.f255l.f287r > this.f255l.f271b) {
                C0749m c0749m5 = this.f255l;
                c0749m5.f287r = c0749m5.f271b;
            }
            if (this.f255l.f274e > this.f255l.f287r) {
                this.f255l.f276g = true;
                c0748l.f268c = this.f255l.f287r;
            } else {
                this.f255l.f276g = false;
                c0748l.f268c = this.f255l.f274e;
            }
            c0748l.f266a = 0;
            c0748l.f267b = (this.f255l.f282m << 6) | (this.f255l.f276g ? 32 : 0);
            System.arraycopy(this.f254k.f227b, this.f255l.f273d, c0748l.f269d, 0, c0748l.f268c);
            StringBuilder sb2 = new StringBuilder("t1Request: I(");
            sb2.append(this.f255l.f282m);
            sb2.append(".");
            sb2.append(this.f255l.f276g ? 1 : 0);
            sb2.append(") ->");
            this.f250g[this.f251h] = (byte) c0748l.f266a;
            this.f250g[this.f251h + 1] = (byte) c0748l.f267b;
            this.f250g[this.f251h + 2] = (byte) c0748l.f268c;
            if (c0748l.f268c > 0) {
            }
            m74a(this.f250g, this.f251h, this.f247d.f193p.f263d, false);
            if (this.f247d.f178a) {
            }
            this.f251h += ((1 & this.f247d.f193p.f263d) != 0 ? 5 : 4) + c0748l.f268c;
            return 0;
        }
        this.f255l.f280k = 0;
        if (this.f255l.f285p != null) {
            this.f255l.f285p = null;
        }
        if (this.f254k.f230e < 2) {
            return -1073741789;
        }
        this.f255l.f285p = new byte[this.f254k.f230e];
        new StringBuilder("t1Request: T1_").append(this.f255l.f283n != 1 ? "START" : "RESTART");
        this.f255l.f274e = this.f254k.f228c;
        this.f255l.f273d = 0;
        this.f255l.f272c = 0;
        this.f255l.f270a = this.f247d.f193p.f260a;
        this.f255l.f279j = 0;
        this.f255l.f278i = 0;
        this.f255l.f276g = false;
        if (this.f255l.f283n == 193) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x028a, code lost:
    
        if (r4 == 2) goto L99;
     */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0290  */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int m80e() {
        boolean z;
        boolean z2;
        int i;
        C0748l c0748l = new C0748l();
        this.f255l.f286q = false;
        if (this.f247d.f178a) {
            m73a(this.f252i, 0, this.f253j);
        }
        this.f255l.f284o = 0;
        int i2 = (this.f252i[2] & 255) + 3 + ((this.f247d.f193p.f263d & 1) != 0 ? 2 : 1);
        int i3 = this.f253j;
        if (i3 < 4 || i3 != i2) {
            z = true;
            z2 = false;
        } else {
            z = m74a(this.f252i, 0, this.f247d.f193p.f263d, true);
            z2 = true;
        }
        int i4 = -1073741434;
        if (!z2 || !z) {
            this.f255l.f275f = z ? 2 : 1;
            if (this.f255l.f278i == 0) {
                C0749m c0749m = this.f255l;
                c0749m.f278i = c0749m.f283n;
            }
            C0749m c0749m2 = this.f255l;
            int i5 = c0749m2.f279j;
            c0749m2.f279j = i5 + 1;
            if (i5 == 2) {
                this.f255l.f279j = 0;
                this.f255l.f283n = 192;
            } else if (this.f255l.f283n != 192) {
                this.f255l.f283n = 3;
            }
        } else {
            this.f255l.f275f = 0;
            c0748l.f266a = this.f252i[0] & 255;
            c0748l.f267b = this.f252i[1] & 255;
            c0748l.f268c = this.f252i[2] & 255;
            System.arraycopy(this.f252i, 3, c0748l.f269d, 0, c0748l.f268c);
            if (this.f255l.f283n == 193) {
                if (c0748l.f267b == 225) {
                    this.f255l.f283n = 2;
                } else {
                    if ((c0748l.f267b & 130) == 130) {
                        this.f255l.f283n = 2;
                    }
                    i = -1073741434;
                }
            } else if (this.f255l.f283n == 192) {
                if (c0748l.f267b == 224) {
                    this.f255l.f279j = 0;
                    this.f255l.f281l = 0;
                    this.f255l.f282m = 0;
                    this.f255l.f283n = 4;
                }
                i = -1073741434;
            } else if ((c0748l.f267b & 128) == 0) {
                StringBuilder sb = new StringBuilder("t1Reply: I(");
                sb.append((c0748l.f267b & 64) >> 6);
                sb.append(".");
                sb.append((c0748l.f267b & 32) >> 5);
                sb.append(") <-");
                if (((c0748l.f267b & 64) >> 6) == this.f255l.f281l) {
                    this.f255l.f279j = 0;
                    this.f255l.f278i = 0;
                    this.f255l.f281l ^= 1;
                    if (this.f255l.f283n == 2) {
                        this.f255l.f282m ^= 1;
                    }
                    if (this.f254k.f230e < this.f255l.f272c + c0748l.f268c) {
                        i = -1073741789;
                    } else {
                        System.arraycopy(c0748l.f269d, 0, this.f255l.f285p, this.f255l.f272c, c0748l.f268c);
                        this.f255l.f272c += c0748l.f268c;
                        if ((c0748l.f267b & 32) != 0) {
                            this.f255l.f283n = 3;
                        } else {
                            this.f254k.f226a = this.f255l.f272c;
                            System.arraycopy(this.f255l.f285p, 0, this.f254k.f229d, 0, this.f255l.f272c);
                            i = 0;
                        }
                    }
                } else {
                    this.f255l.f275f = 2;
                    if (this.f255l.f278i == 0) {
                        C0749m c0749m3 = this.f255l;
                        c0749m3.f278i = c0749m3.f283n;
                    }
                    C0749m c0749m4 = this.f255l;
                    int i6 = c0749m4.f279j;
                    c0749m4.f279j = i6 + 1;
                    if (i6 == 2) {
                        this.f255l.f279j = 0;
                        this.f255l.f283n = 192;
                    } else {
                        this.f255l.f283n = 3;
                    }
                }
            } else if ((c0748l.f267b & 192) == 128) {
                int i7 = (c0748l.f267b & 16) >> 4;
                StringBuilder sb2 = new StringBuilder("t1Reply: R(");
                sb2.append(i7);
                sb2.append(") <-");
                if (i7 != this.f255l.f282m && this.f255l.f276g) {
                    this.f255l.f279j = 0;
                    this.f255l.f273d += this.f255l.f287r;
                    this.f255l.f274e -= this.f255l.f287r;
                    this.f255l.f282m ^= 1;
                    this.f255l.f283n = 2;
                } else if ((c0748l.f267b & 2) != 0 && this.f255l.f283n == 193) {
                    this.f255l.f283n = 2;
                } else {
                    C0749m c0749m5 = this.f255l;
                    int i8 = c0749m5.f279j;
                    c0749m5.f279j = i8 + 1;
                    if (i8 == 2) {
                        this.f255l.f279j = 0;
                        if (this.f255l.f278i == 0) {
                            C0749m c0749m6 = this.f255l;
                            c0749m6.f278i = c0749m6.f283n;
                        }
                        this.f255l.f283n = 192;
                    }
                }
            } else {
                int i9 = c0748l.f267b;
                if (i9 != 228) {
                    switch (i9) {
                        case 193:
                            this.f255l.f270a = this.f252i[3] & 255;
                            C0749m c0749m7 = this.f255l;
                            c0749m7.f278i = c0749m7.f283n;
                            this.f255l.f283n = 225;
                            break;
                        case 194:
                            this.f255l.f283n = 226;
                            break;
                        case 195:
                            this.f255l.f284o = this.f252i[3] & 255;
                            C0749m c0749m8 = this.f255l;
                            c0749m8.f278i = c0749m8.f283n;
                            this.f255l.f283n = 227;
                            break;
                        default:
                            i = -1073741434;
                            break;
                    }
                } else {
                    i = -1073741666;
                }
            }
            if (this.f255l.f283n == 192) {
                C0749m c0749m9 = this.f255l;
                int i10 = c0749m9.f280k;
                c0749m9.f280k = i10 + 1;
            }
            i4 = i;
            if (i4 != -1073741802) {
                if (this.f255l.f278i == 193) {
                    this.f255l.f283n = 193;
                } else {
                    this.f255l.f283n = 1;
                }
                if (this.f255l.f285p != null) {
                    this.f255l.f285p = null;
                }
                this.f255l.f278i = 0;
                this.f255l.f277h = 0;
            }
            return i4;
        }
        i = -1073741802;
        if (this.f255l.f283n == 192) {
        }
        i4 = i;
        if (i4 != -1073741802) {
        }
        return i4;
    }

    /* renamed from: a */
    public static void m72a(int i) throws ReaderException {
        switch (i) {
            case -2147483643:
                throw new BufferOverflowException();
            case -1073741811:
                throw new IllegalArgumentException();
            case -1073741804:
                throw new UnsupportedCardException();
            case -1073741789:
                throw new InsufficientBufferException();
            case -1073741666:
                throw new DevicePowerFailureException();
            case -1073741595:
                throw new InternalErrorException();
            case -1073741436:
                throw new InvalidDeviceStateException();
            case -1073741434:
                throw new DeviceProtocolErrorException();
            default:
                return;
        }
    }

    /* renamed from: a */
    private static void m73a(byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            byte b = 0;
            for (int i4 = 0; i4 < 8; i4++) {
                if ((bArr[i + i3] & (1 << i4)) != 0) {
                    b = (byte) (b | (1 << (7 - i4)));
                }
            }
            bArr[i + i3] = (byte) (b ^ 255);
        }
    }

    /* renamed from: a */
    private static boolean m74a(byte[] bArr, int i, int i2, boolean z) {
        int i3 = (bArr[i + 2] & 255) + 3;
        int[] iArr = {0, 49345, 49537, DimensionsKt.XHDPI, 49921, 960, DimensionsKt.XXXHDPI, 49729, 50689, 1728, 1920, 51009, 1280, 50625, 50305, 1088};
        int[] iArr2 = {0, 52225, 55297, HealthInfo.BaseError.BaseComponentErrorTypeSensorNone, 61441, 15360, TarConstants.DEFAULT_BLKSIZE, 58369, 40961, 27648, 30720, 46081, 20480, 39937, 34817, 17920};
        if ((i2 & 1) != 0) {
            int i4 = 0;
            for (int i5 = 0; i5 < i3; i5++) {
                int i6 = (bArr[i + i5] & 255) ^ i4;
                i4 = ((i4 >> 8) ^ iArr[i6 & 15]) ^ iArr2[i6 >> 4];
            }
            if (z) {
                int i7 = i + i3;
                if (i4 == (((bArr[i7] & 255) << 8) | (bArr[i7 + 1] & 255))) {
                    return true;
                }
            } else {
                int i8 = i + i3;
                bArr[i8] = (byte) (i4 >> 8);
                bArr[i8 + 1] = (byte) i4;
                return true;
            }
        } else {
            int i9 = bArr[i] & 255;
            for (int i10 = 1; i10 < i3; i10++) {
                i9 ^= bArr[i + i10] & 255;
            }
            if (z) {
                if (i9 == (bArr[i + i3] & 255)) {
                    return true;
                }
            } else {
                bArr[i + i3] = (byte) i9;
                return true;
            }
        }
        return false;
    }
}
