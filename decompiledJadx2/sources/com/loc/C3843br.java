package com.loc;

import android.content.Context;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* compiled from: StatisticsEntity.java */
/* renamed from: com.loc.br */
/* loaded from: classes4.dex */
public final class C3843br {

    /* renamed from: a */
    private Context f3710a;

    /* renamed from: b */
    private String f3711b;

    /* renamed from: c */
    private String f3712c;

    /* renamed from: d */
    private String f3713d;

    /* renamed from: e */
    private String f3714e;

    public C3843br(Context context, String str, String str2, String str3) throws C3884j {
        if (TextUtils.isEmpty(str3) || str3.length() > 256) {
            throw new C3884j("无效的参数 - IllegalArgumentException");
        }
        this.f3710a = context.getApplicationContext();
        this.f3712c = str;
        this.f3713d = str2;
        this.f3711b = str3;
    }

    /* renamed from: a */
    public final void m2638a(String str) throws C3884j {
        if (TextUtils.isEmpty(str) || str.length() > 65536) {
            throw new C3884j("无效的参数 - IllegalArgumentException");
        }
        this.f3714e = str;
    }

    /* renamed from: a */
    public final byte[] m2639a() {
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] bArr;
        byte[] bArr2 = new byte[0];
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Throwable th) {
                th = th;
                byteArrayOutputStream = null;
            }
            try {
                C3894t.m3229a(byteArrayOutputStream, this.f3712c);
                C3894t.m3229a(byteArrayOutputStream, this.f3713d);
                C3894t.m3229a(byteArrayOutputStream, this.f3711b);
                C3894t.m3229a(byteArrayOutputStream, String.valueOf(C3888n.m3164m(this.f3710a)));
                new SimpleDateFormat("SSS").format(new Date());
                int i = Calendar.getInstance().get(14);
                byteArrayOutputStream.write(new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)});
                String str = this.f3714e;
                if (TextUtils.isEmpty(str)) {
                    bArr = new byte[]{0, 0};
                } else {
                    int length = str.length();
                    bArr = new byte[]{(byte) (length / 256), (byte) (length % 256)};
                }
                byteArrayOutputStream.write(bArr);
                byteArrayOutputStream.write(C3894t.m3232a(this.f3714e));
                bArr2 = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th = th2;
                try {
                    C3897w.m3249a(th, "StatisticsEntity", "toDatas");
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    return bArr2;
                } catch (Throwable th3) {
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th4) {
                            th4.printStackTrace();
                        }
                    }
                    throw th3;
                }
            }
        } catch (Throwable th5) {
            th5.printStackTrace();
        }
        return bArr2;
    }
}
