package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.tencent.bugly.AbstractC5864a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.p */
/* loaded from: classes7.dex */
public final class C5932p {

    /* renamed from: a */
    private static C5932p f8185a = null;

    /* renamed from: b */
    private static C5933q f8186b = null;

    /* renamed from: c */
    private static boolean f8187c = false;

    private C5932p(Context context, List<AbstractC5864a> list) {
        f8186b = new C5933q(context, list);
    }

    /* renamed from: a */
    public static synchronized C5932p m3741a(Context context, List<AbstractC5864a> list) {
        C5932p c5932p;
        synchronized (C5932p.class) {
            if (f8185a == null) {
                f8185a = new C5932p(context, list);
            }
            c5932p = f8185a;
        }
        return c5932p;
    }

    /* renamed from: a */
    public static synchronized C5932p m3740a() {
        C5932p c5932p;
        synchronized (C5932p.class) {
            c5932p = f8185a;
        }
        return c5932p;
    }

    /* renamed from: a */
    public final long m3755a(String str, ContentValues contentValues, InterfaceC5931o interfaceC5931o, boolean z) {
        return m3737a(str, contentValues, (InterfaceC5931o) null);
    }

    /* renamed from: a */
    public final Cursor m3756a(String str, String[] strArr, String str2, String[] strArr2, InterfaceC5931o interfaceC5931o, boolean z) {
        return m3739a(false, str, strArr, str2, null, null, null, null, null, null);
    }

    /* renamed from: a */
    public final int m3754a(String str, String str2, String[] strArr, InterfaceC5931o interfaceC5931o, boolean z) {
        return m3735a(str, str2, (String[]) null, (InterfaceC5931o) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x003f, code lost:
    
        if (r9 != null) goto L13;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized long m3737a(String str, ContentValues contentValues, InterfaceC5931o interfaceC5931o) {
        long j;
        j = 0;
        try {
            SQLiteDatabase writableDatabase = f8186b.getWritableDatabase();
            if (writableDatabase != null && contentValues != null) {
                long replace = writableDatabase.replace(str, TransferTable.COLUMN_ID, contentValues);
                if (replace >= 0) {
                    C5940x.m3823c("[Database] insert %s success.", str);
                } else {
                    C5940x.m3824d("[Database] replace %s error.", str);
                }
                j = replace;
            }
        } catch (Throwable th) {
            try {
                if (!C5940x.m3819a(th)) {
                    th.printStackTrace();
                }
            } finally {
                if (interfaceC5931o != null) {
                    Long.valueOf(0L);
                }
            }
        }
        return j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public synchronized Cursor m3739a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, InterfaceC5931o interfaceC5931o) {
        Cursor cursor;
        cursor = null;
        try {
            SQLiteDatabase writableDatabase = f8186b.getWritableDatabase();
            if (writableDatabase != null) {
                cursor = writableDatabase.query(z, str, strArr, str2, strArr2, str3, str4, str5, str6);
            }
        } finally {
            try {
                return cursor;
            } finally {
            }
        }
        return cursor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0020, code lost:
    
        if (r6 != null) goto L8;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized int m3735a(String str, String str2, String[] strArr, InterfaceC5931o interfaceC5931o) {
        int i;
        try {
            SQLiteDatabase writableDatabase = f8186b.getWritableDatabase();
            i = writableDatabase != null ? writableDatabase.delete(str, str2, strArr) : 0;
        } catch (Throwable th) {
            try {
                if (!C5940x.m3819a(th)) {
                    th.printStackTrace();
                }
            } finally {
                if (interfaceC5931o != null) {
                    Integer.valueOf(0);
                }
            }
        }
        return i;
    }

    /* renamed from: a */
    public final boolean m3761a(int i, String str, byte[] bArr, InterfaceC5931o interfaceC5931o, boolean z) {
        if (!z) {
            a aVar = new a(4, null);
            aVar.m3764a(i, str, bArr);
            C5939w.m3810a().m3812a(aVar);
            return true;
        }
        return m3746a(i, str, bArr, (InterfaceC5931o) null);
    }

    /* renamed from: a */
    public final Map<String, byte[]> m3758a(int i, InterfaceC5931o interfaceC5931o, boolean z) {
        return m3743a(i, (InterfaceC5931o) null);
    }

    /* renamed from: a */
    public final boolean m3760a(int i, String str, InterfaceC5931o interfaceC5931o, boolean z) {
        return m3745a(555, str, (InterfaceC5931o) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0027, code lost:
    
        if (r8 != null) goto L5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x002a, code lost:
    
        return r0;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean m3746a(int i, String str, byte[] bArr, InterfaceC5931o interfaceC5931o) {
        boolean z = false;
        try {
            C5934r c5934r = new C5934r();
            c5934r.f8210a = i;
            c5934r.f8215f = str;
            c5934r.f8214e = System.currentTimeMillis();
            c5934r.f8216g = bArr;
            z = m3750b(c5934r);
        } catch (Throwable th) {
            try {
                if (!C5940x.m3819a(th)) {
                    th.printStackTrace();
                }
            } finally {
                if (interfaceC5931o != null) {
                    Boolean.valueOf(false);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public Map<String, byte[]> m3743a(int i, InterfaceC5931o interfaceC5931o) {
        HashMap hashMap = null;
        try {
            List<C5934r> m3752c = m3752c(i);
            if (m3752c == null) {
                return null;
            }
            HashMap hashMap2 = new HashMap();
            try {
                for (C5934r c5934r : m3752c) {
                    byte[] bArr = c5934r.f8216g;
                    if (bArr != null) {
                        hashMap2.put(c5934r.f8215f, bArr);
                    }
                }
                return hashMap2;
            } catch (Throwable th) {
                th = th;
                hashMap = hashMap2;
                if (C5940x.m3819a(th)) {
                    return hashMap;
                }
                th.printStackTrace();
                return hashMap;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* renamed from: a */
    public final synchronized boolean m3762a(C5934r c5934r) {
        ContentValues m3751c;
        if (c5934r == null) {
            return false;
        }
        try {
            SQLiteDatabase writableDatabase = f8186b.getWritableDatabase();
            if (writableDatabase == null || (m3751c = m3751c(c5934r)) == null) {
                return false;
            }
            long replace = writableDatabase.replace("t_lr", TransferTable.COLUMN_ID, m3751c);
            if (replace < 0) {
                return false;
            }
            C5940x.m3823c("[Database] insert %s success.", "t_lr");
            c5934r.f8210a = replace;
            return true;
        } catch (Throwable th) {
            try {
                if (!C5940x.m3819a(th)) {
                    th.printStackTrace();
                }
                return false;
            } finally {
            }
        }
    }

    /* renamed from: b */
    private synchronized boolean m3750b(C5934r c5934r) {
        ContentValues m3753d;
        if (c5934r == null) {
            return false;
        }
        try {
            SQLiteDatabase writableDatabase = f8186b.getWritableDatabase();
            if (writableDatabase == null || (m3753d = m3753d(c5934r)) == null) {
                return false;
            }
            long replace = writableDatabase.replace("t_pf", TransferTable.COLUMN_ID, m3753d);
            if (replace < 0) {
                return false;
            }
            C5940x.m3823c("[Database] insert %s success.", "t_pf");
            c5934r.f8210a = replace;
            return true;
        } catch (Throwable th) {
            try {
                if (!C5940x.m3819a(th)) {
                    th.printStackTrace();
                }
                return false;
            } finally {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00a8 A[Catch: all -> 0x00b1, TRY_LEAVE, TryCatch #0 {all -> 0x00b1, blocks: (B:42:0x00a2, B:44:0x00a8), top: B:41:0x00a2, outer: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ad A[Catch: all -> 0x00ba, TRY_ENTER, TryCatch #3 {, blocks: (B:3:0x0001, B:11:0x0032, B:37:0x009c, B:47:0x00ad, B:50:0x00b4, B:51:0x00b7, B:42:0x00a2, B:44:0x00a8), top: B:2:0x0001, inners: #0 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final synchronized List<C5934r> m3757a(int i) {
        String str;
        Throwable th;
        Cursor cursor;
        SQLiteDatabase writableDatabase = f8186b.getWritableDatabase();
        if (writableDatabase != null) {
            if (i >= 0) {
                try {
                    str = "_tp = " + i;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = null;
                    try {
                        if (!C5940x.m3819a(th)) {
                        }
                        if (cursor != null) {
                        }
                        return null;
                    } finally {
                        if (cursor != null) {
                            cursor.close();
                        }
                    }
                }
            } else {
                str = null;
            }
            cursor = writableDatabase.query("t_lr", null, str, null, null, null, null);
            if (cursor == null) {
                return null;
            }
            try {
                StringBuilder sb = new StringBuilder();
                ArrayList arrayList = new ArrayList();
                while (cursor.moveToNext()) {
                    C5934r m3742a = m3742a(cursor);
                    if (m3742a != null) {
                        arrayList.add(m3742a);
                    } else {
                        try {
                            long j = cursor.getLong(cursor.getColumnIndex(TransferTable.COLUMN_ID));
                            sb.append(" or _id");
                            sb.append(" = ");
                            sb.append(j);
                        } catch (Throwable unused) {
                            C5940x.m3824d("[Database] unknown id.", new Object[0]);
                        }
                    }
                }
                String sb2 = sb.toString();
                if (sb2.length() > 0) {
                    C5940x.m3824d("[Database] deleted %s illegal data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", sb2.substring(4), null)));
                }
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            } catch (Throwable th3) {
                th = th3;
                if (!C5940x.m3819a(th)) {
                    th.printStackTrace();
                }
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            }
        }
        return null;
    }

    /* renamed from: a */
    public final synchronized void m3759a(List<C5934r> list) {
        if (list != null) {
            if (list.size() != 0) {
                SQLiteDatabase writableDatabase = f8186b.getWritableDatabase();
                if (writableDatabase != null) {
                    StringBuilder sb = new StringBuilder();
                    for (C5934r c5934r : list) {
                        sb.append(" or _id");
                        sb.append(" = ");
                        sb.append(c5934r.f8210a);
                    }
                    String sb2 = sb.toString();
                    if (sb2.length() > 0) {
                        sb2 = sb2.substring(4);
                    }
                    sb.setLength(0);
                    try {
                        C5940x.m3823c("[Database] deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", sb2, null)));
                    } catch (Throwable th) {
                        if (C5940x.m3819a(th)) {
                            return;
                        }
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    /* renamed from: b */
    public final synchronized void m3763b(int i) {
        String str;
        SQLiteDatabase writableDatabase = f8186b.getWritableDatabase();
        if (writableDatabase != null) {
            if (i >= 0) {
                try {
                    str = "_tp = " + i;
                } catch (Throwable th) {
                    if (C5940x.m3819a(th)) {
                        return;
                    }
                    th.printStackTrace();
                    return;
                }
            } else {
                str = null;
            }
            C5940x.m3823c("[Database] deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", str, null)));
        }
    }

    /* renamed from: c */
    private static ContentValues m3751c(C5934r c5934r) {
        if (c5934r == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (c5934r.f8210a > 0) {
                contentValues.put(TransferTable.COLUMN_ID, Long.valueOf(c5934r.f8210a));
            }
            contentValues.put("_tp", Integer.valueOf(c5934r.f8211b));
            contentValues.put("_pc", c5934r.f8212c);
            contentValues.put("_th", c5934r.f8213d);
            contentValues.put("_tm", Long.valueOf(c5934r.f8214e));
            if (c5934r.f8216g != null) {
                contentValues.put("_dt", c5934r.f8216g);
            }
            return contentValues;
        } catch (Throwable th) {
            if (!C5940x.m3819a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    private static C5934r m3742a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            C5934r c5934r = new C5934r();
            c5934r.f8210a = cursor.getLong(cursor.getColumnIndex(TransferTable.COLUMN_ID));
            c5934r.f8211b = cursor.getInt(cursor.getColumnIndex("_tp"));
            c5934r.f8212c = cursor.getString(cursor.getColumnIndex("_pc"));
            c5934r.f8213d = cursor.getString(cursor.getColumnIndex("_th"));
            c5934r.f8214e = cursor.getLong(cursor.getColumnIndex("_tm"));
            c5934r.f8216g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return c5934r;
        } catch (Throwable th) {
            if (!C5940x.m3819a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: c */
    private synchronized List<C5934r> m3752c(int i) {
        Cursor cursor;
        try {
            SQLiteDatabase writableDatabase = f8186b.getWritableDatabase();
            if (writableDatabase != null) {
                String str = "_id = " + i;
                cursor = writableDatabase.query("t_pf", null, str, null, null, null, null);
                if (cursor == null) {
                    return null;
                }
                try {
                    StringBuilder sb = new StringBuilder();
                    ArrayList arrayList = new ArrayList();
                    while (cursor.moveToNext()) {
                        C5934r m3749b = m3749b(cursor);
                        if (m3749b != null) {
                            arrayList.add(m3749b);
                        } else {
                            try {
                                String string = cursor.getString(cursor.getColumnIndex("_tp"));
                                sb.append(" or _tp");
                                sb.append(" = ");
                                sb.append(string);
                            } catch (Throwable unused) {
                                C5940x.m3824d("[Database] unknown id.", new Object[0]);
                            }
                        }
                    }
                    if (sb.length() > 0) {
                        sb.append(" and _id");
                        sb.append(" = ");
                        sb.append(i);
                        C5940x.m3824d("[Database] deleted %s illegal data %d.", "t_pf", Integer.valueOf(writableDatabase.delete("t_pf", str.substring(4), null)));
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    try {
                        if (!C5940x.m3819a(th)) {
                            th.printStackTrace();
                        }
                        if (cursor != null) {
                            cursor.close();
                        }
                        return null;
                    } finally {
                        if (cursor != null) {
                            cursor.close();
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x006e, code lost:
    
        if (r7 != null) goto L14;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized boolean m3745a(int i, String str, InterfaceC5931o interfaceC5931o) {
        boolean z;
        String str2;
        z = false;
        try {
            SQLiteDatabase writableDatabase = f8186b.getWritableDatabase();
            if (writableDatabase != null) {
                if (C5942z.m3868a(str)) {
                    str2 = "_id = " + i;
                } else {
                    str2 = "_id = " + i + " and _tp = \"" + str + "\"";
                }
                int delete = writableDatabase.delete("t_pf", str2, null);
                C5940x.m3823c("[Database] deleted %s data %d", "t_pf", Integer.valueOf(delete));
                if (delete > 0) {
                    z = true;
                }
            }
        } catch (Throwable th) {
            try {
                if (!C5940x.m3819a(th)) {
                    th.printStackTrace();
                }
            } finally {
                if (interfaceC5931o != null) {
                    Boolean.valueOf(false);
                }
            }
        }
        return z;
    }

    /* renamed from: d */
    private static ContentValues m3753d(C5934r c5934r) {
        if (c5934r != null && !C5942z.m3868a(c5934r.f8215f)) {
            try {
                ContentValues contentValues = new ContentValues();
                if (c5934r.f8210a > 0) {
                    contentValues.put(TransferTable.COLUMN_ID, Long.valueOf(c5934r.f8210a));
                }
                contentValues.put("_tp", c5934r.f8215f);
                contentValues.put("_tm", Long.valueOf(c5934r.f8214e));
                if (c5934r.f8216g != null) {
                    contentValues.put("_dt", c5934r.f8216g);
                }
                return contentValues;
            } catch (Throwable th) {
                if (!C5940x.m3819a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    /* renamed from: b */
    private static C5934r m3749b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            C5934r c5934r = new C5934r();
            c5934r.f8210a = cursor.getLong(cursor.getColumnIndex(TransferTable.COLUMN_ID));
            c5934r.f8214e = cursor.getLong(cursor.getColumnIndex("_tm"));
            c5934r.f8215f = cursor.getString(cursor.getColumnIndex("_tp"));
            c5934r.f8216g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return c5934r;
        } catch (Throwable th) {
            if (!C5940x.m3819a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.p$a */
    /* loaded from: classes7.dex */
    public class a extends Thread {

        /* renamed from: a */
        private int f8188a;

        /* renamed from: b */
        private InterfaceC5931o f8189b;

        /* renamed from: c */
        private String f8190c;

        /* renamed from: d */
        private ContentValues f8191d;

        /* renamed from: e */
        private boolean f8192e;

        /* renamed from: f */
        private String[] f8193f;

        /* renamed from: g */
        private String f8194g;

        /* renamed from: h */
        private String[] f8195h;

        /* renamed from: i */
        private String f8196i;

        /* renamed from: j */
        private String f8197j;

        /* renamed from: k */
        private String f8198k;

        /* renamed from: l */
        private String f8199l;

        /* renamed from: m */
        private String f8200m;

        /* renamed from: n */
        private String[] f8201n;

        /* renamed from: o */
        private int f8202o;

        /* renamed from: p */
        private String f8203p;

        /* renamed from: q */
        private byte[] f8204q;

        public a(int i, InterfaceC5931o interfaceC5931o) {
            this.f8188a = i;
            this.f8189b = interfaceC5931o;
        }

        /* renamed from: a */
        public final void m3765a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
            this.f8192e = z;
            this.f8190c = str;
            this.f8193f = strArr;
            this.f8194g = str2;
            this.f8195h = strArr2;
            this.f8196i = str3;
            this.f8197j = str4;
            this.f8198k = str5;
            this.f8199l = str6;
        }

        /* renamed from: a */
        public final void m3764a(int i, String str, byte[] bArr) {
            this.f8202o = i;
            this.f8203p = str;
            this.f8204q = bArr;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            switch (this.f8188a) {
                case 1:
                    C5932p.this.m3737a(this.f8190c, this.f8191d, this.f8189b);
                    return;
                case 2:
                    C5932p.this.m3735a(this.f8190c, this.f8200m, this.f8201n, this.f8189b);
                    return;
                case 3:
                    Cursor m3739a = C5932p.this.m3739a(this.f8192e, this.f8190c, this.f8193f, this.f8194g, this.f8195h, this.f8196i, this.f8197j, this.f8198k, this.f8199l, this.f8189b);
                    if (m3739a != null) {
                        m3739a.close();
                        return;
                    }
                    return;
                case 4:
                    C5932p.this.m3746a(this.f8202o, this.f8203p, this.f8204q, this.f8189b);
                    return;
                case 5:
                    C5932p.this.m3743a(this.f8202o, this.f8189b);
                    return;
                case 6:
                    C5932p.this.m3745a(this.f8202o, this.f8203p, this.f8189b);
                    return;
                default:
                    return;
            }
        }
    }
}
