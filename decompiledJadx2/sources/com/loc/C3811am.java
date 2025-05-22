package com.loc;

import android.database.sqlite.SQLiteDatabase;
import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;

/* compiled from: LogDBCreator.java */
/* renamed from: com.loc.am */
/* loaded from: classes4.dex */
public class C3811am implements InterfaceC3803ae {
    @Override // com.loc.InterfaceC3803ae
    /* renamed from: a */
    public final String mo2416a() {
        return "logdb.db";
    }

    @Override // com.loc.InterfaceC3803ae
    /* renamed from: a */
    public final void mo2417a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS a (_id integer primary key autoincrement, a1  varchar(20), a2 varchar(10),a3 varchar(50),a4 varchar(100),a5 varchar(20),a6 integer);");
            sQLiteDatabase.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s (_id integer primary key autoincrement,b1 varchar(40), b2 integer,b3  integer,a1  varchar(20));", "b"));
            sQLiteDatabase.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s (_id integer primary key autoincrement,b1 varchar(40), b2 integer,b3  integer,a1  varchar(20));", "c"));
            sQLiteDatabase.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s (_id integer primary key autoincrement,b1 varchar(40), b2 integer,b3  integer,a1  varchar(20));", LinkFormat.DOMAIN));
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS e (_id integer primary key autoincrement,c1 integer,c2 integer,c3 integer);");
        } catch (Throwable th) {
            C3897w.m3249a(th, "DB", "onCreate");
        }
    }
}
