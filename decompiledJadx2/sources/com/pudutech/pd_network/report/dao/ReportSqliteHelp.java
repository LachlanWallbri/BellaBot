package com.pudutech.pd_network.report.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.pudutech.pd_network.log.NetWorkLog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReportSqliteHelp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J \u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016R\u0014\u0010\u0007\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/pd_network/report/dao/ReportSqliteHelp;", "Landroid/database/sqlite/SQLiteOpenHelper;", "context", "Landroid/content/Context;", "dbName", "", "(Landroid/content/Context;Ljava/lang/String;)V", "TAG", "getTAG", "()Ljava/lang/String;", "onCreate", "", "db", "Landroid/database/sqlite/SQLiteDatabase;", "onUpgrade", "oldVersion", "", "newVersion", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ReportSqliteHelp extends SQLiteOpenHelper {
    private final String TAG;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReportSqliteHelp(Context context, String dbName) {
        super(context, dbName, (SQLiteDatabase.CursorFactory) null, 4);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(dbName, "dbName");
        this.TAG = "ReportSqliteHelp";
    }

    public final String getTAG() {
        return this.TAG;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase db) {
        Intrinsics.checkParameterIsNotNull(db, "db");
        NetWorkLog.INSTANCE.mo3278d(this.TAG, "onCreate");
        db.execSQL(ReportSqliteHelpKt.DB_CREATE);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Intrinsics.checkParameterIsNotNull(db, "db");
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "onUpgrade > db:" + db + " oldVersion:" + oldVersion + " newVersion:" + newVersion + "  ");
        if (newVersion == 4) {
            db.execSQL("drop table if exists t_report");
            onCreate(db);
        }
    }
}
