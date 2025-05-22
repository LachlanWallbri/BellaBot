package com.iflytek.aiui.pro;

import android.content.Context;
import com.iflytek.aiui.pro.C3644v;
import java.io.InputStream;

/* renamed from: com.iflytek.aiui.pro.d0 */
/* loaded from: classes4.dex */
public class C3609d0 {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m1262a(Context context, String str) {
        return context.getFilesDir().getAbsolutePath() + "/al/" + str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static byte[] m1263b(C3644v c3644v, String str, boolean z) {
        C3644v.b bVar;
        byte[] bArr = new byte[0];
        InputStream inputStream = null;
        try {
            bVar = c3644v.m1579c(str);
            if (bVar == null) {
                if (bVar != null) {
                    try {
                        bVar.close();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
                return bArr;
            }
            try {
                InputStream m1586a = bVar.m1586a(0);
                if (m1586a == null) {
                    if (m1586a != null) {
                        try {
                            m1586a.close();
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                        }
                    }
                    if (bVar != null) {
                        try {
                            bVar.close();
                        } catch (Throwable th3) {
                            th3.printStackTrace();
                        }
                    }
                    return bArr;
                }
                try {
                    bArr = new byte[m1586a.available()];
                    m1586a.read(bArr);
                    if (z) {
                        c3644v.q(str);
                    }
                    if (m1586a != null) {
                        try {
                            m1586a.close();
                        } catch (Throwable th4) {
                            th4.printStackTrace();
                        }
                    }
                    if (bVar != null) {
                        try {
                            bVar.close();
                        } catch (Throwable th5) {
                            th5.printStackTrace();
                        }
                    }
                    return bArr;
                } catch (Throwable th6) {
                    th = th6;
                    inputStream = m1586a;
                    try {
                        th.printStackTrace();
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Throwable th7) {
                                th7.printStackTrace();
                            }
                        }
                        if (bVar != null) {
                            try {
                                bVar.close();
                            } catch (Throwable th8) {
                                th8.printStackTrace();
                            }
                        }
                        return bArr;
                    } finally {
                    }
                }
            } catch (Throwable th9) {
                th = th9;
            }
        } catch (Throwable th10) {
            th = th10;
            bVar = null;
        }
    }
}
