package com.loc;

import android.content.Context;
import android.database.Cursor;
import android.net.Proxy;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.iflytek.cloud.msc.util.NetworkUtil;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.util.List;
import java.util.Locale;

/* compiled from: ProxyUtil.java */
/* renamed from: com.loc.q */
/* loaded from: classes4.dex */
public final class C3891q {
    /* renamed from: a */
    private static String m3193a() {
        String str;
        try {
            str = Proxy.getDefaultHost();
        } catch (Throwable th) {
            C3897w.m3249a(th, "ProxyUtil", "getDefHost");
            str = null;
        }
        return str == null ? "null" : str;
    }

    /* renamed from: a */
    public static java.net.Proxy m3194a(Context context) {
        try {
            return Build.VERSION.SDK_INT >= 11 ? m3195a(context, new URI("http://restapi.amap.com")) : m3197b(context);
        } catch (Throwable th) {
            C3897w.m3249a(th, "ProxyUtil", "getProxy");
            return null;
        }
    }

    /* renamed from: a */
    private static java.net.Proxy m3195a(Context context, URI uri) {
        java.net.Proxy proxy;
        if (m3198c(context)) {
            try {
                List<java.net.Proxy> select = ProxySelector.getDefault().select(uri);
                if (select == null || select.isEmpty() || (proxy = select.get(0)) == null) {
                    return null;
                }
                if (proxy.type() == Proxy.Type.DIRECT) {
                    return null;
                }
                return proxy;
            } catch (Throwable th) {
                C3897w.m3249a(th, "ProxyUtil", "getProxySelectorCfg");
            }
        }
        return null;
    }

    /* renamed from: b */
    private static int m3196b() {
        try {
            return android.net.Proxy.getDefaultPort();
        } catch (Throwable th) {
            C3897w.m3249a(th, "ProxyUtil", "getDefPort");
            return -1;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x00eb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0177 A[Catch: all -> 0x0173, TRY_LEAVE, TryCatch #10 {all -> 0x0173, blocks: (B:20:0x0168, B:12:0x0177), top: B:19:0x0168 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0168 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00cc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0101 A[Catch: all -> 0x018a, TryCatch #4 {all -> 0x018a, blocks: (B:109:0x00e1, B:73:0x00f6, B:75:0x0101, B:77:0x0115, B:79:0x011b, B:83:0x0129, B:95:0x0135, B:97:0x013b, B:99:0x0141, B:103:0x014f), top: B:4:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x015d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v20 */
    /* JADX WARN: Type inference failed for: r10v21 */
    /* JADX WARN: Type inference failed for: r9v0, types: [android.content.ContentResolver, android.database.Cursor] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static java.net.Proxy m3197b(Context context) {
        String str;
        Cursor cursor;
        int i;
        ?? r10;
        Throwable th;
        String m3166o;
        boolean z;
        boolean z2;
        String str2;
        boolean z3;
        String str3;
        boolean z4;
        if (m3198c(context)) {
            Uri parse = Uri.parse("content://telephony/carriers/preferapn");
            String str4 = null;
            boolean z5 = false;
            try {
                try {
                    cursor = context.getContentResolver().query(parse, null, null, null, null);
                } catch (SecurityException e) {
                    e = e;
                    cursor = null;
                    str4 = null;
                } catch (Throwable th2) {
                    th = th2;
                    str = null;
                    cursor = null;
                }
                if (cursor != null) {
                    try {
                    } catch (SecurityException e2) {
                        e = e2;
                        str4 = null;
                        r10 = -1;
                        C3897w.m3249a(e, "ProxyUtil", "getHostProxy");
                        m3166o = C3888n.m3166o(context);
                        if (m3166o != null) {
                            String lowerCase = m3166o.toLowerCase(Locale.US);
                            String m3193a = m3193a();
                            int m3196b = m3196b();
                            if (lowerCase.indexOf(NetworkUtil.NET_CTWAP) != -1) {
                                if (TextUtils.isEmpty(m3193a) || m3193a.equals("null")) {
                                    m3193a = str4;
                                    z2 = false;
                                } else {
                                    z2 = true;
                                }
                                if (!z2) {
                                    m3193a = C3894t.m3237c("QMTAuMC4wLjIwMA==");
                                }
                                if (m3196b != -1) {
                                    str = m3193a;
                                    i = m3196b;
                                }
                                str = m3193a;
                                i = 80;
                            } else if (lowerCase.indexOf("wap") != -1) {
                                if (TextUtils.isEmpty(m3193a) || m3193a.equals("null")) {
                                    m3193a = str4;
                                    z = false;
                                } else {
                                    z = true;
                                }
                                if (!z) {
                                    m3193a = C3894t.m3237c("QMTAuMC4wLjE3Mg==");
                                }
                                str = m3193a;
                                i = 80;
                            } else {
                                str = str4;
                                i = m3196b;
                            }
                        } else {
                            i = r10;
                            str = str4;
                        }
                        if (cursor != null) {
                            try {
                                cursor.close();
                            } catch (Throwable th3) {
                                th = th3;
                                C3897w.m3249a(th, "ProxyUtil", "getHostProxy2");
                                th.printStackTrace();
                                int i2 = i;
                                if (str != null) {
                                }
                                if (z5) {
                                }
                                return null;
                            }
                        }
                        int i22 = i;
                        if (str != null) {
                        }
                        if (z5) {
                        }
                        return null;
                    } catch (Throwable th4) {
                        th = th4;
                        str = null;
                        i = -1;
                        C3897w.m3249a(th, "ProxyUtil", "getHostProxy1");
                        th.printStackTrace();
                        if (cursor != null) {
                            try {
                                cursor.close();
                            } catch (Throwable th5) {
                                th = th5;
                                C3897w.m3249a(th, "ProxyUtil", "getHostProxy2");
                                th.printStackTrace();
                                int i222 = i;
                                if (str != null) {
                                }
                                if (z5) {
                                }
                                return null;
                            }
                        }
                        int i2222 = i;
                        if (str != null) {
                        }
                        if (z5) {
                        }
                        return null;
                    }
                    if (cursor.moveToFirst()) {
                        String string = cursor.getString(cursor.getColumnIndex("apn"));
                        String str5 = string;
                        r10 = parse;
                        if (string != null) {
                            Locale locale = Locale.US;
                            str5 = string.toLowerCase(locale);
                            r10 = locale;
                        }
                        try {
                            try {
                                if (str5 != null && str5.contains(NetworkUtil.NET_CTWAP)) {
                                    String m3193a2 = m3193a();
                                    int m3196b2 = m3196b();
                                    if (TextUtils.isEmpty(m3193a2) || m3193a2.equals("null")) {
                                        str3 = null;
                                        z4 = false;
                                    } else {
                                        str3 = m3193a2;
                                        z4 = true;
                                    }
                                    String m3237c = !z4 ? C3894t.m3237c("QMTAuMC4wLjIwMA==") : str3;
                                    i = m3196b2 == -1 ? 80 : m3196b2;
                                    str = m3237c;
                                } else if (str5 != null && str5.contains("wap")) {
                                    String m3193a3 = m3193a();
                                    int m3196b3 = m3196b();
                                    if (TextUtils.isEmpty(m3193a3) || m3193a3.equals("null")) {
                                        str2 = null;
                                        z3 = false;
                                    } else {
                                        str2 = m3193a3;
                                        z3 = true;
                                    }
                                    str = !z3 ? C3894t.m3237c("QMTAuMC4wLjE3Mg==") : str2;
                                    i = m3196b3 == -1 ? 80 : m3196b3;
                                }
                                if (cursor != null) {
                                    try {
                                        cursor.close();
                                    } catch (Throwable th6) {
                                        th = th6;
                                        C3897w.m3249a(th, "ProxyUtil", "getHostProxy2");
                                        th.printStackTrace();
                                        int i22222 = i;
                                        if (str != null) {
                                        }
                                        if (z5) {
                                        }
                                        return null;
                                    }
                                }
                            } catch (SecurityException e3) {
                                e = e3;
                                C3897w.m3249a(e, "ProxyUtil", "getHostProxy");
                                m3166o = C3888n.m3166o(context);
                                if (m3166o != null) {
                                }
                                if (cursor != null) {
                                }
                                int i222222 = i;
                                if (str != null) {
                                }
                                if (z5) {
                                }
                                return null;
                            } catch (Throwable th7) {
                                th = th7;
                                i = r10 == true ? 1 : 0;
                                str = null;
                                C3897w.m3249a(th, "ProxyUtil", "getHostProxy1");
                                th.printStackTrace();
                                if (cursor != null) {
                                }
                                int i2222222 = i;
                                if (str != null) {
                                }
                                if (z5) {
                                }
                                return null;
                            }
                        } catch (SecurityException e4) {
                            e = e4;
                            str4 = null;
                            C3897w.m3249a(e, "ProxyUtil", "getHostProxy");
                            m3166o = C3888n.m3166o(context);
                            if (m3166o != null) {
                            }
                            if (cursor != null) {
                            }
                            int i22222222 = i;
                            if (str != null) {
                            }
                            if (z5) {
                            }
                            return null;
                        } catch (Throwable th8) {
                            th = th8;
                            str = null;
                            i = r10 == true ? 1 : 0;
                            C3897w.m3249a(th, "ProxyUtil", "getHostProxy1");
                            th.printStackTrace();
                            if (cursor != null) {
                            }
                            int i222222222 = i;
                            if (str != null) {
                            }
                            if (z5) {
                            }
                            return null;
                        }
                        int i2222222222 = i;
                        if (str != null) {
                            try {
                                if (str.length() > 0 && i2222222222 != -1) {
                                    z5 = true;
                                }
                            } catch (Throwable th9) {
                                C3897w.m3249a(th9, "ProxyUtil", "getHostProxy2");
                                th9.printStackTrace();
                            }
                        }
                        if (z5) {
                            return new java.net.Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(str, i2222222222));
                        }
                    }
                }
                str = null;
                i = -1;
                if (cursor != null) {
                }
                int i22222222222 = i;
                if (str != null) {
                }
                if (z5) {
                }
            } finally {
            }
        }
        return null;
    }

    /* renamed from: c */
    private static boolean m3198c(Context context) {
        return C3888n.m3164m(context) == 0;
    }
}
