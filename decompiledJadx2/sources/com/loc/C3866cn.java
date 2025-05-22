package com.loc;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/* compiled from: LocationRequest.java */
/* renamed from: com.loc.cn */
/* loaded from: classes4.dex */
public final class C3866cn extends AbstractC3835bj {

    /* renamed from: f */
    Map<String, String> f3938f;

    /* renamed from: g */
    String f3939g;

    /* renamed from: h */
    byte[] f3940h;

    /* renamed from: i */
    byte[] f3941i;

    /* renamed from: j */
    boolean f3942j;

    /* renamed from: k */
    String f3943k;

    /* renamed from: l */
    Map<String, String> f3944l;

    public C3866cn(Context context, C3893s c3893s) {
        super(context, c3893s);
        this.f3938f = null;
        this.f3939g = "";
        this.f3940h = null;
        this.f3941i = null;
        this.f3942j = false;
        this.f3943k = null;
        this.f3944l = null;
    }

    @Override // com.loc.AbstractC3839bn
    /* renamed from: a */
    public final Map<String, String> mo2487a() {
        return this.f3938f;
    }

    @Override // com.loc.AbstractC3835bj
    /* renamed from: a_ */
    public final byte[] mo2609a_() {
        return this.f3940h;
    }

    @Override // com.loc.AbstractC3839bn
    /* renamed from: b */
    public final String mo2488b() {
        return this.f3939g;
    }

    /* renamed from: b */
    public final void m2857b(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            if (bArr != null) {
                try {
                    try {
                        byteArrayOutputStream.write(m2604a(bArr));
                        byteArrayOutputStream.write(bArr);
                    } catch (IOException unused) {
                    }
                } catch (Throwable th) {
                    th = th;
                    try {
                        th.printStackTrace();
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                                return;
                            } catch (IOException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        return;
                    } catch (Throwable th2) {
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th2;
                    }
                }
            }
            this.f3941i = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
        }
    }

    @Override // com.loc.AbstractC3835bj
    /* renamed from: b_ */
    public final byte[] mo2610b_() {
        return this.f3941i;
    }

    @Override // com.loc.AbstractC3835bj, com.loc.AbstractC3839bn
    /* renamed from: c */
    public final Map<String, String> mo2489c() {
        return this.f3944l;
    }

    @Override // com.loc.AbstractC3835bj
    /* renamed from: f */
    public final boolean mo2613f() {
        return this.f3942j;
    }

    @Override // com.loc.AbstractC3835bj
    /* renamed from: g */
    public final String mo2614g() {
        return this.f3943k;
    }
}
