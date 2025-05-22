package com.loc;

import android.database.sqlite.SQLiteDatabase;

/* compiled from: SdCardDBCreator.java */
/* renamed from: com.loc.ck */
/* loaded from: classes4.dex */
public class C3863ck implements InterfaceC3803ae {
    @Override // com.loc.InterfaceC3803ae
    /* renamed from: a */
    public final String mo2416a() {
        return "alsn.db";
    }

    @Override // com.loc.InterfaceC3803ae
    /* renamed from: a */
    public final void mo2417a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS c (_id integer primary key autoincrement, a2 varchar(100), a4 varchar(2000), a3 LONG );");
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS b (_id integer primary key autoincrement, b1 integer );");
        } catch (Throwable th) {
            C3880f.m3097a(th, "SdCardDBCreator", "onCreate");
        }
    }
}
