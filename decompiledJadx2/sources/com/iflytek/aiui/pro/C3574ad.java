package com.iflytek.aiui.pro;

import android.content.Context;
import com.iflytek.aiui.pro.C3644v;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.ad */
/* loaded from: classes.dex */
public class C3574ad {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m955a(Context context, String str) {
        return context.getFilesDir().getAbsolutePath() + "/al/" + str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static byte[] m956a(C3644v c3644v, String str, boolean z) {
        C3644v.b bVar;
        byte[] bArr = new byte[0];
        InputStream inputStream = null;
        try {
            try {
                bVar = c3644v.m1574a(str);
                if (bVar != null) {
                    try {
                        InputStream m1586a = bVar.m1586a(0);
                        if (m1586a == null) {
                            if (m1586a != null) {
                                try {
                                    m1586a.close();
                                } catch (Throwable th) {
                                    th.printStackTrace();
                                }
                            }
                            if (bVar != null) {
                                bVar.close();
                                return bArr;
                            }
                        } else {
                            bArr = new byte[m1586a.available()];
                            m1586a.read(bArr);
                            if (z) {
                                c3644v.m1579c(str);
                            }
                            if (m1586a != null) {
                                try {
                                    m1586a.close();
                                } catch (Throwable th2) {
                                    th2.printStackTrace();
                                }
                            }
                            if (bVar != null) {
                                bVar.close();
                                return bArr;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        try {
                            th.printStackTrace();
                            if (0 != 0) {
                                try {
                                    inputStream.close();
                                } catch (Throwable th4) {
                                    th4.printStackTrace();
                                }
                            }
                            if (bVar != null) {
                                bVar.close();
                                return bArr;
                            }
                            return bArr;
                        } finally {
                        }
                    }
                } else if (bVar != null) {
                    bVar.close();
                }
            } catch (Throwable th5) {
                th = th5;
                bVar = null;
            }
        } catch (Throwable th6) {
            th6.printStackTrace();
        }
        return bArr;
    }
}
