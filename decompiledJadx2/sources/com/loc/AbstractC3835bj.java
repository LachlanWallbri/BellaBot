package com.loc;

import android.content.Context;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BinaryRequest.java */
/* renamed from: com.loc.bj */
/* loaded from: classes4.dex */
public abstract class AbstractC3835bj extends AbstractC3839bn {

    /* renamed from: a */
    protected Context f3679a;

    /* renamed from: b */
    protected C3893s f3680b;

    public AbstractC3835bj(Context context, C3893s c3893s) {
        if (context != null) {
            this.f3679a = context.getApplicationContext();
        }
        this.f3680b = c3893s;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static byte[] m2604a(byte[] bArr) {
        int length = bArr.length;
        return new byte[]{(byte) (length / 256), (byte) (length % 256)};
    }

    /* renamed from: h */
    private static byte[] m2605h() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(C3894t.m3232a("PANDORA$"));
            byteArrayOutputStream.write(new byte[]{1});
            byteArrayOutputStream.write(new byte[]{0});
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            try {
                C3897w.m3249a(th, "BinaryRequest", "getBinaryHead");
                try {
                    byteArrayOutputStream.close();
                    return null;
                } catch (Throwable th2) {
                    C3897w.m3249a(th2, "BinaryRequest", "getBinaryHead");
                    return null;
                }
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    C3897w.m3249a(th3, "BinaryRequest", "getBinaryHead");
                }
            }
        }
    }

    /* renamed from: i */
    private byte[] m2606i() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(new byte[]{3});
            if (mo2613f()) {
                byte[] m3139a = C3887m.m3139a(this.f3679a);
                byteArrayOutputStream.write(m2604a(m3139a));
                byteArrayOutputStream.write(m3139a);
            } else {
                byteArrayOutputStream.write(new byte[]{0, 0});
            }
            byte[] m3232a = C3894t.m3232a(mo2612e());
            if (m3232a == null || m3232a.length <= 0) {
                byteArrayOutputStream.write(new byte[]{0, 0});
            } else {
                byteArrayOutputStream.write(m2604a(m3232a));
                byteArrayOutputStream.write(m3232a);
            }
            byte[] m3232a2 = C3894t.m3232a(mo2614g());
            if (m3232a2 == null || m3232a2.length <= 0) {
                byteArrayOutputStream.write(new byte[]{0, 0});
            } else {
                byteArrayOutputStream.write(m2604a(m3232a2));
                byteArrayOutputStream.write(m3232a2);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            try {
                C3897w.m3249a(th, "BinaryRequest", "getPublicData");
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th2) {
                    C3897w.m3249a(th2, "BinaryRequest", "getRequestEncryptData");
                }
                return new byte[]{0};
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    C3897w.m3249a(th3, "BinaryRequest", "getRequestEncryptData");
                }
            }
        }
    }

    /* renamed from: j */
    private byte[] m2607j() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] mo2609a_ = mo2609a_();
            if (mo2609a_ != null && mo2609a_.length != 0) {
                byteArrayOutputStream.write(new byte[]{1});
                byteArrayOutputStream.write(m2604a(mo2609a_));
                byteArrayOutputStream.write(mo2609a_);
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(new byte[]{0});
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                C3897w.m3249a(th, "BinaryRequest", "getRequestRawData");
            }
            return byteArray;
        } catch (Throwable th2) {
            try {
                C3897w.m3249a(th2, "BinaryRequest", "getRequestRawData");
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    C3897w.m3249a(th3, "BinaryRequest", "getRequestRawData");
                }
                return new byte[]{0};
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th4) {
                    C3897w.m3249a(th4, "BinaryRequest", "getRequestRawData");
                }
            }
        }
    }

    /* renamed from: k */
    private byte[] m2608k() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] mo2610b_ = mo2610b_();
            if (mo2610b_ != null && mo2610b_.length != 0) {
                byteArrayOutputStream.write(new byte[]{1});
                Context context = this.f3679a;
                byte[] m3141a = C3887m.m3141a(mo2610b_);
                byteArrayOutputStream.write(m2604a(m3141a));
                byteArrayOutputStream.write(m3141a);
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(new byte[]{0});
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                C3897w.m3249a(th, "BinaryRequest", "getRequestEncryptData");
            }
            return byteArray;
        } catch (Throwable th2) {
            try {
                C3897w.m3249a(th2, "BinaryRequest", "getRequestEncryptData");
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    C3897w.m3249a(th3, "BinaryRequest", "getRequestEncryptData");
                }
                return new byte[]{0};
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th4) {
                    C3897w.m3249a(th4, "BinaryRequest", "getRequestEncryptData");
                }
            }
        }
    }

    /* renamed from: a_ */
    public abstract byte[] mo2609a_();

    /* renamed from: b_ */
    public abstract byte[] mo2610b_();

    @Override // com.loc.AbstractC3839bn
    /* renamed from: c */
    public Map<String, String> mo2489c() {
        String m3127f = C3885k.m3127f(this.f3679a);
        String m3136a = C3887m.m3136a();
        String m3137a = C3887m.m3137a(this.f3679a, m3136a, "key=" + m3127f);
        HashMap hashMap = new HashMap();
        hashMap.put("ts", m3136a);
        hashMap.put(TransferTable.COLUMN_KEY, m3127f);
        hashMap.put("scode", m3137a);
        return hashMap;
    }

    @Override // com.loc.AbstractC3839bn
    /* renamed from: d */
    public final byte[] mo2611d() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(m2605h());
            byteArrayOutputStream.write(m2606i());
            byteArrayOutputStream.write(m2607j());
            byteArrayOutputStream.write(m2608k());
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            try {
                C3897w.m3249a(th, "BinaryRequest", "getEntityBytes");
                try {
                    byteArrayOutputStream.close();
                    return null;
                } catch (Throwable th2) {
                    C3897w.m3249a(th2, "BinaryRequest", "getEntityBytes");
                    return null;
                }
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    C3897w.m3249a(th3, "BinaryRequest", "getEntityBytes");
                }
            }
        }
    }

    /* renamed from: e */
    protected String mo2612e() {
        return "2.1";
    }

    /* renamed from: f */
    public boolean mo2613f() {
        return true;
    }

    /* renamed from: g */
    public String mo2614g() {
        return String.format("platform=Android&sdkversion=%s&product=%s", this.f3680b.m3209c(), this.f3680b.m3206a());
    }
}
