package com.loc;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.io.IOException;

/* compiled from: DB.java */
/* renamed from: com.loc.ak */
/* loaded from: classes4.dex */
public final class C3809ak extends SQLiteOpenHelper {

    /* renamed from: a */
    private InterfaceC3803ae f3565a;

    /* compiled from: DB.java */
    /* renamed from: com.loc.ak$a */
    /* loaded from: classes4.dex */
    public static class a extends ContextWrapper {

        /* renamed from: a */
        private String f3566a;

        /* renamed from: b */
        private Context f3567b;

        public a(Context context, String str) {
            super(context);
            this.f3566a = str;
            this.f3567b = context;
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public final File getDatabasePath(String str) {
            try {
                String str2 = this.f3566a + "/" + str;
                File file = new File(this.f3566a);
                if (!file.exists()) {
                    file.mkdirs();
                }
                boolean z = false;
                File file2 = new File(str2);
                if (file2.exists()) {
                    z = true;
                } else {
                    try {
                        z = file2.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (z) {
                    return file2;
                }
                return null;
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public final SQLiteDatabase openOrCreateDatabase(String str, int i, SQLiteDatabase.CursorFactory cursorFactory) {
            SQLiteDatabase sQLiteDatabase;
            if (getDatabasePath(str) != null) {
                try {
                    sQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(str), (SQLiteDatabase.CursorFactory) null);
                } catch (Throwable th) {
                    th.printStackTrace();
                    sQLiteDatabase = null;
                }
                if (sQLiteDatabase != null) {
                    return sQLiteDatabase;
                }
            }
            return SQLiteDatabase.openOrCreateDatabase(this.f3567b.getDatabasePath(str), (SQLiteDatabase.CursorFactory) null);
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public final SQLiteDatabase openOrCreateDatabase(String str, int i, SQLiteDatabase.CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler) {
            SQLiteDatabase sQLiteDatabase;
            if (getDatabasePath(str) != null) {
                try {
                    sQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(str), (SQLiteDatabase.CursorFactory) null);
                } catch (Throwable th) {
                    th.printStackTrace();
                    sQLiteDatabase = null;
                }
                if (sQLiteDatabase != null) {
                    return sQLiteDatabase;
                }
            }
            return SQLiteDatabase.openOrCreateDatabase(this.f3567b.getDatabasePath(str), (SQLiteDatabase.CursorFactory) null);
        }
    }

    public C3809ak(Context context, String str, InterfaceC3803ae interfaceC3803ae) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        this.f3565a = interfaceC3803ae;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.f3565a.mo2417a(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        InterfaceC3803ae interfaceC3803ae = this.f3565a;
    }
}
