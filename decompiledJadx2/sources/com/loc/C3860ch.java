package com.loc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.iflytek.speech.VoiceWakeuperAidl;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONObject;

/* compiled from: DB.java */
/* renamed from: com.loc.ch */
/* loaded from: classes4.dex */
public final class C3860ch {

    /* renamed from: a */
    private static C3860ch f3910a;

    /* renamed from: b */
    private String f3911b = "2.0.201501131131".replace(".", "");

    /* renamed from: c */
    private String f3912c = null;

    /* renamed from: a */
    public static synchronized C3860ch m2824a() {
        C3860ch c3860ch;
        synchronized (C3860ch.class) {
            if (f3910a == null) {
                f3910a = new C3860ch();
            }
            c3860ch = f3910a;
        }
        return c3860ch;
    }

    /* renamed from: a */
    private boolean m2825a(SQLiteDatabase sQLiteDatabase, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Cursor cursor = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT count(*) as c FROM sqlite_master WHERE type = 'table' AND name = '");
            sb.append(str.trim());
            sb.append(this.f3911b);
            sb.append("' ");
            cursor = sQLiteDatabase.rawQuery(sb.toString(), null);
            boolean z = cursor != null && cursor.moveToFirst() && cursor.getInt(0) > 0;
            sb.delete(0, sb.length());
            if (cursor == null) {
                return z;
            }
            cursor.close();
            return z;
        } catch (Throwable unused) {
            if (cursor != null) {
                cursor.close();
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final String m2826a(String str, StringBuilder sb, Context context) {
        if (context == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.f3912c == null) {
                this.f3912c = C3857ce.m2796a(MessageDigestAlgorithms.MD5, context.getPackageName());
            }
            if (str.contains("&")) {
                str = str.substring(0, str.indexOf("&"));
            }
            String substring = str.substring(str.lastIndexOf(MqttTopic.MULTI_LEVEL_WILDCARD) + 1);
            if (substring.equals("cgi")) {
                jSONObject.put("cgi", str.substring(0, str.length() - 12));
            } else if (!TextUtils.isEmpty(sb) && sb.indexOf("access") != -1) {
                jSONObject.put("cgi", str.substring(0, str.length() - (substring.length() + 9)));
                String[] split = sb.toString().split(",access");
                jSONObject.put("mmac", split[0].contains(MqttTopic.MULTI_LEVEL_WILDCARD) ? split[0].substring(split[0].lastIndexOf(MqttTopic.MULTI_LEVEL_WILDCARD) + 1) : split[0]);
            }
            return C3889o.m3178a(C3857ce.m2804c(jSONObject.toString().getBytes("UTF-8"), this.f3912c));
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    public final synchronized void m2827a(Context context) throws Exception {
        if (context == null) {
            return;
        }
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                sQLiteDatabase = context.openOrCreateDatabase("hmdb", 0, null);
                if (!m2825a(sQLiteDatabase, "hist")) {
                    if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.close();
                    }
                    return;
                }
                try {
                    sQLiteDatabase.delete("hist" + this.f3911b, "time<?", new String[]{String.valueOf(C3876cx.m2969a() - 86400000)});
                } catch (Throwable th) {
                    C3880f.m3097a(th, "DB", "clearHist");
                    String message = th.getMessage();
                    if (!TextUtils.isEmpty(message)) {
                        message.contains("no such table");
                    }
                }
                if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.close();
                }
            } catch (Throwable th2) {
                throw th2;
            }
        } catch (Throwable th3) {
            try {
                C3880f.m3097a(th3, "DB", "clearHist p2");
                if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
                    return;
                }
                sQLiteDatabase.close();
            } catch (Throwable th4) {
                if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.close();
                }
                throw th4;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a1, code lost:
    
        if (r0.moveToFirst() != false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00af, code lost:
    
        if (r0.getString(0).startsWith("{") == false) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00b1, code lost:
    
        r4 = new org.json.JSONObject(r0.getString(0));
        r12.delete(0, r12.length());
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00c9, code lost:
    
        if (android.text.TextUtils.isEmpty(r0.getString(1)) != false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00cb, code lost:
    
        r6 = r0.getString(1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00cf, code lost:
    
        r12.append(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00ec, code lost:
    
        r6 = new org.json.JSONObject(r0.getString(2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00fb, code lost:
    
        if (com.loc.C3876cx.m2979a(r6, "type") == false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00fd, code lost:
    
        r6.put("type", "new");
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x018f, code lost:
    
        r7 = new com.autonavi.aps.amapapi.model.AMapLocationServer("");
        r7.m548b(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x019f, code lost:
    
        if (com.loc.C3876cx.m2979a(r4, "mmac") == false) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x01a7, code lost:
    
        if (com.loc.C3876cx.m2979a(r4, "cgi") == false) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x01a9, code lost:
    
        r5 = (r4.getString("cgi") + org.eclipse.paho.client.mqttv3.MqttTopic.MULTI_LEVEL_WILDCARD) + "network#";
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x01dd, code lost:
    
        if (r4.getString("cgi").contains(org.eclipse.paho.client.mqttv3.MqttTopic.MULTI_LEVEL_WILDCARD) == false) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x01df, code lost:
    
        r4 = new java.lang.StringBuilder();
        r4.append(r5);
        r4.append("cgiwifi");
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x024b, code lost:
    
        com.loc.C3858cf.m2807a().m2816a(r4.toString() + "&" + r7.isOffset() + "&" + r7.m558h(), r12, r7, r11, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x027d, code lost:
    
        if (r0.moveToNext() != false) goto L114;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x027f, code lost:
    
        r12.delete(0, r12.length());
        r3.delete(0, r3.length());
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x01f1, code lost:
    
        r4 = new java.lang.StringBuilder();
        r4.append(r5);
        r4.append("wifi");
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0205, code lost:
    
        if (com.loc.C3876cx.m2979a(r4, "cgi") == false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0207, code lost:
    
        r5 = (r4.getString("cgi") + org.eclipse.paho.client.mqttv3.MqttTopic.MULTI_LEVEL_WILDCARD) + "network#";
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x023b, code lost:
    
        if (r4.getString("cgi").contains(org.eclipse.paho.client.mqttv3.MqttTopic.MULTI_LEVEL_WILDCARD) == false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x023d, code lost:
    
        r4 = new java.lang.StringBuilder();
        r4.append(r5);
        r4.append("cgi");
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00d9, code lost:
    
        if (com.loc.C3876cx.m2979a(r4, "mmac") == false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00db, code lost:
    
        r12.append(org.eclipse.paho.client.mqttv3.MqttTopic.MULTI_LEVEL_WILDCARD);
        r12.append(r4.getString("mmac"));
        r6 = ",access";
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0106, code lost:
    
        r7 = new org.json.JSONObject(new java.lang.String(com.loc.C3857ce.m2805d(com.loc.C3889o.m3182b(r0.getString(0)), r10.f3912c), "UTF-8"));
        r12.delete(0, r12.length());
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x012f, code lost:
    
        if (android.text.TextUtils.isEmpty(r0.getString(1)) != false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0131, code lost:
    
        r12.append(new java.lang.String(com.loc.C3857ce.m2805d(com.loc.C3889o.m3182b(r0.getString(1)), r10.f3912c), "UTF-8"));
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0165, code lost:
    
        r6 = new org.json.JSONObject(new java.lang.String(com.loc.C3857ce.m2805d(com.loc.C3889o.m3182b(r0.getString(2)), r10.f3912c), "UTF-8"));
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0185, code lost:
    
        if (com.loc.C3876cx.m2979a(r6, "type") == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0187, code lost:
    
        r6.put("type", "new");
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x018e, code lost:
    
        r4 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0150, code lost:
    
        if (com.loc.C3876cx.m2979a(r7, "mmac") == false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0152, code lost:
    
        r12.append(org.eclipse.paho.client.mqttv3.MqttTopic.MULTI_LEVEL_WILDCARD);
        r12.append(r7.getString("mmac"));
        r12.append(",access");
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final synchronized void m2828a(Context context, String str) throws Exception {
        SQLiteDatabase sQLiteDatabase;
        if (context == null) {
            return;
        }
        Cursor cursor = null;
        try {
            try {
                sQLiteDatabase = context.openOrCreateDatabase("hmdb", 0, null);
                try {
                    if (!m2825a(sQLiteDatabase, "hist")) {
                        if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                            sQLiteDatabase.close();
                        }
                        return;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("SELECT feature, nb, loc FROM ");
                    sb.append("hist");
                    sb.append(this.f3911b);
                    long m2969a = C3876cx.m2969a() - 86400000;
                    sb.append(" WHERE time > ");
                    sb.append(m2969a);
                    if (str != null) {
                        sb.append(" and feature = '");
                        sb.append(str + "'");
                    }
                    sb.append(" ORDER BY time ASC;");
                    try {
                        cursor = sQLiteDatabase.rawQuery(sb.toString(), null);
                    } catch (Throwable th) {
                        C3880f.m3097a(th, "DB", "fetchHist");
                        String message = th.getMessage();
                        if (!TextUtils.isEmpty(message)) {
                            message.contains("no such table");
                        }
                    }
                    StringBuilder sb2 = new StringBuilder();
                    if (this.f3912c == null) {
                        this.f3912c = C3857ce.m2796a(MessageDigestAlgorithms.MD5, context.getPackageName());
                    }
                    if (cursor != null) {
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        C3880f.m3097a(th, "DB", "fetchHist p2");
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                            sQLiteDatabase.close();
                        }
                    } catch (Throwable th3) {
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                            sQLiteDatabase.close();
                        }
                        throw th3;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                sQLiteDatabase = null;
            }
        } catch (Throwable th5) {
            throw th5;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0089 A[Catch: all -> 0x0082, TryCatch #1 {all -> 0x0082, blocks: (B:56:0x0077, B:58:0x007d, B:19:0x0089, B:21:0x0106, B:22:0x0109, B:54:0x00cd), top: B:55:0x0077 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0106 A[Catch: all -> 0x0082, TryCatch #1 {all -> 0x0082, blocks: (B:56:0x0077, B:58:0x007d, B:19:0x0089, B:21:0x0106, B:22:0x0109, B:54:0x00cd), top: B:55:0x0077 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0112 A[Catch: all -> 0x0155, DONT_GENERATE, TRY_ENTER, TryCatch #4 {, blocks: (B:3:0x0001, B:7:0x000b, B:24:0x0112, B:26:0x0117, B:28:0x011d, B:38:0x0131, B:40:0x0136, B:42:0x013c, B:48:0x0144, B:50:0x0149, B:52:0x014f, B:53:0x0152, B:36:0x0128), top: B:2:0x0001, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0117 A[Catch: all -> 0x0155, DONT_GENERATE, TryCatch #4 {, blocks: (B:3:0x0001, B:7:0x000b, B:24:0x0112, B:26:0x0117, B:28:0x011d, B:38:0x0131, B:40:0x0136, B:42:0x013c, B:48:0x0144, B:50:0x0149, B:52:0x014f, B:53:0x0152, B:36:0x0128), top: B:2:0x0001, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00cd A[Catch: all -> 0x0082, TryCatch #1 {all -> 0x0082, blocks: (B:56:0x0077, B:58:0x007d, B:19:0x0089, B:21:0x0106, B:22:0x0109, B:54:0x00cd), top: B:55:0x0077 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final synchronized void m2829a(Context context, String str, String str2, long j) throws Exception {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        int i;
        if (!TextUtils.isEmpty(str) && context != null) {
            String m2998c = C3876cx.m2998c(str);
            String m2998c2 = C3876cx.m2998c(str2);
            try {
                StringBuilder sb = new StringBuilder();
                sQLiteDatabase = context.openOrCreateDatabase("hmdb", 0, null);
                try {
                    sb.append("CREATE TABLE IF NOT EXISTS hm");
                    sb.append(this.f3911b);
                    sb.append(" (hash VARCHAR PRIMARY KEY, num INTEGER, extra VARCHAR, time VARCHAR);");
                    sQLiteDatabase.execSQL(sb.toString());
                    sb.delete(0, sb.length());
                    sb.append("SELECT num FROM hm");
                    sb.append(this.f3911b);
                    sb.append(" WHERE hash = '");
                    sb.append(m2998c);
                    sb.append("';");
                    try {
                        cursor = sQLiteDatabase.rawQuery(sb.toString(), null);
                    } catch (Throwable th) {
                        C3880f.m3097a(th, "DB", "updateHm");
                        String message = th.getMessage();
                        if (!TextUtils.isEmpty(message)) {
                            message.contains("no such table");
                        }
                        cursor = null;
                    }
                    if (cursor != null) {
                        try {
                            if (cursor.moveToNext()) {
                                i = cursor.getInt(0);
                                if (i <= 0) {
                                    ContentValues contentValues = new ContentValues();
                                    contentValues.put("num", Integer.valueOf(i + 1));
                                    contentValues.put("extra", m2998c2);
                                    contentValues.put("time", Long.valueOf(j));
                                    sQLiteDatabase.update("hm" + this.f3911b, contentValues, "hash = '" + m2998c + "'", null);
                                } else {
                                    sb.delete(0, sb.length());
                                    sb.append("REPLACE INTO ");
                                    sb.append("hm");
                                    sb.append(this.f3911b);
                                    sb.append(" VALUES (?, ?, ?, ?)");
                                    sQLiteDatabase.execSQL(sb.toString(), new Object[]{m2998c, 1, m2998c2, Long.valueOf(j)});
                                }
                                if (cursor != null) {
                                    cursor.close();
                                }
                                sb.delete(0, sb.length());
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                C3880f.m3097a(th, "DB", "updateHm p2");
                                if (cursor != null) {
                                    cursor.close();
                                }
                                if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                                    sQLiteDatabase.close();
                                }
                                return;
                            } finally {
                                if (cursor != null) {
                                    cursor.close();
                                }
                                if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                                    sQLiteDatabase.close();
                                }
                            }
                        }
                    }
                    i = 0;
                    if (i <= 0) {
                    }
                    if (cursor != null) {
                    }
                    sb.delete(0, sb.length());
                } catch (Throwable th3) {
                    th = th3;
                    cursor = null;
                }
            } catch (Throwable th4) {
                th = th4;
                sQLiteDatabase = null;
                cursor = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final void m2830a(String str, AMapLocation aMapLocation, StringBuilder sb, Context context) throws Exception {
        SQLiteDatabase sQLiteDatabase;
        if (context == null) {
            return;
        }
        if (this.f3912c == null) {
            this.f3912c = C3857ce.m2796a(MessageDigestAlgorithms.MD5, context.getPackageName());
        }
        String m2826a = m2826a(str, sb, context);
        StringBuilder sb2 = new StringBuilder();
        Cursor cursor = null;
        try {
            sQLiteDatabase = context.openOrCreateDatabase("hmdb", 0, null);
        } catch (Throwable th) {
            th = th;
            sQLiteDatabase = null;
        }
        try {
            sb2.append("CREATE TABLE IF NOT EXISTS hist");
            sb2.append(this.f3911b);
            sb2.append(" (feature VARCHAR PRIMARY KEY, nb VARCHAR, loc VARCHAR, time VARCHAR);");
            sQLiteDatabase.execSQL(sb2.toString());
            sb2.delete(0, sb2.length());
            sb2.append("REPLACE INTO ");
            sb2.append("hist");
            sb2.append(this.f3911b);
            sb2.append(" VALUES (?, ?, ?, ?)");
            Object[] objArr = new Object[4];
            objArr[0] = m2826a;
            objArr[1] = C3857ce.m2804c(sb.toString().getBytes("UTF-8"), this.f3912c);
            objArr[2] = C3857ce.m2804c(aMapLocation.toStr().getBytes("UTF-8"), this.f3912c);
            objArr[3] = Long.valueOf(aMapLocation.getTime());
            for (int i = 1; i < objArr.length - 1; i++) {
                objArr[i] = C3889o.m3178a((byte[]) objArr[i]);
            }
            sQLiteDatabase.execSQL(sb2.toString(), objArr);
            sb2.delete(0, sb2.length());
            sb2.append("SELECT COUNT(*) AS total FROM ");
            sb2.append("hist");
            sb2.append(this.f3911b);
            sb2.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
            cursor = sQLiteDatabase.rawQuery(sb2.toString(), null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
        } catch (Throwable th2) {
            th = th2;
            try {
                C3880f.m3097a(th, "DB", "updateHist");
                if (cursor != null) {
                    cursor.close();
                }
                sb2.delete(0, sb2.length());
                if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
                    return;
                }
                sQLiteDatabase.close();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
                sb2.delete(0, sb2.length());
                if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.close();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0086, code lost:
    
        if (r0.moveToFirst() != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0088, code lost:
    
        r3 = r0.getString(0);
        r9 = r0.getInt(1);
        r4 = r0.getString(2);
        r10 = r0.getLong(3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a1, code lost:
    
        if (r4.startsWith("{") != false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00a3, code lost:
    
        r3 = com.loc.C3876cx.m3003d(r3);
        r4 = com.loc.C3876cx.m3003d(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00ab, code lost:
    
        com.loc.C3861ci.m2832a().m2835a(r14, r3, r4, r9, r10, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00ba, code lost:
    
        if (r0.moveToNext() != false) goto L76;
     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final synchronized void m2831b(Context context) throws Exception {
        SQLiteDatabase sQLiteDatabase;
        if (!C3848bw.f3831a || context == null) {
            return;
        }
        Cursor cursor = null;
        try {
            sQLiteDatabase = context.openOrCreateDatabase("hmdb", 0, null);
            try {
                if (!m2825a(sQLiteDatabase, "hm")) {
                    if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.close();
                    }
                    return;
                }
                long m2969a = C3876cx.m2969a() - 1209600000;
                StringBuilder sb = new StringBuilder();
                sb.append("SELECT hash, num, extra, time FROM ");
                sb.append("hm");
                sb.append(this.f3911b);
                sb.append(" WHERE time > ");
                sb.append(m2969a);
                sb.append(" ORDER BY num DESC LIMIT 0,");
                sb.append(500);
                sb.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                try {
                    cursor = sQLiteDatabase.rawQuery(sb.toString(), null);
                } catch (Throwable th) {
                    C3880f.m3097a(th, "DB", "fetchHm");
                    String message = th.getMessage();
                    if (!TextUtils.isEmpty(message)) {
                        message.contains("no such table");
                    }
                }
                sb.delete(0, sb.length());
                if (cursor != null) {
                }
            } catch (Throwable th2) {
                th = th2;
                try {
                    C3880f.m3097a(th, "DB", "fetchHm p2");
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.close();
                    }
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.close();
                    }
                }
            }
        } catch (Throwable th3) {
            th = th3;
            sQLiteDatabase = null;
        }
    }
}
