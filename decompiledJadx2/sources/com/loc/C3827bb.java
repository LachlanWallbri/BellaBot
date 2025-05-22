package com.loc;

import android.database.sqlite.SQLiteDatabase;

/* compiled from: DynamicFileDBCreator.java */
/* renamed from: com.loc.bb */
/* loaded from: classes4.dex */
public final class C3827bb implements InterfaceC3803ae {

    /* renamed from: a */
    private static C3827bb f3619a;

    private C3827bb() {
    }

    /* renamed from: b */
    public static synchronized C3827bb m2516b() {
        C3827bb c3827bb;
        synchronized (C3827bb.class) {
            if (f3619a == null) {
                f3619a = new C3827bb();
            }
            c3827bb = f3619a;
        }
        return c3827bb;
    }

    @Override // com.loc.InterfaceC3803ae
    /* renamed from: a */
    public final String mo2416a() {
        return "dafile.db";
    }

    @Override // com.loc.InterfaceC3803ae
    /* renamed from: a */
    public final void mo2417a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS file (_id integer primary key autoincrement, sname  varchar(20), fname varchar(100),md varchar(20),version varchar(20),dversion varchar(20),status varchar(20),reservedfield varchar(20));");
        } catch (Throwable th) {
            C3897w.m3249a(th, "DynamicFileDBCreator", "onCreate");
        }
    }
}
