package com.pudutech.mpmodule.database.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.pudutech.base.Pdlog;
import com.pudutech.mpmodule.database.greendao.DaoMaster;
import org.greenrobot.greendao.database.Database;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class CDevOpenHelper extends DaoMaster.OpenHelper {
    public CDevOpenHelper(Context context, String str) {
        super(context, str);
    }

    public CDevOpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
        super(context, str, cursorFactory);
    }

    @Override // org.greenrobot.greendao.database.DatabaseOpenHelper
    public void onUpgrade(Database database, int i, int i2) {
        Pdlog.m3273d("database", "Upgrading schema from version " + i + " to " + i2 + " by dropping all tables.");
        if (i == 2 && i2 == 3) {
            Pdlog.m3273d("database", "onUpgrade: nothin to do");
        } else {
            DaoMaster.dropAllTables(database, true);
            onCreate(database);
        }
    }
}
