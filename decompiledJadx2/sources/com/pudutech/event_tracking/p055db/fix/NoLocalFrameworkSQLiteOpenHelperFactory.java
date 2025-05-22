package com.pudutech.event_tracking.p055db.fix;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;
import androidx.sqlite.p003db.SupportSQLiteOpenHelper;
import com.pudutech.event_tracking.p055db.fix.NoLocalSQLiteOpenHelper;
import com.pudutech.pd_network.log.NetWorkLog;
import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NoLocalFrameworkSQLiteOpenHelperFactory.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/event_tracking/db/fix/NoLocalFrameworkSQLiteOpenHelperFactory;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;", "()V", "TAG", "", "create", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "configuration", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Configuration;", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class NoLocalFrameworkSQLiteOpenHelperFactory implements SupportSQLiteOpenHelper.Factory {
    private final String TAG = "NoLocalFrameworkSQLiteOpenHelperFactory";

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Factory
    public SupportSQLiteOpenHelper create(SupportSQLiteOpenHelper.Configuration configuration) {
        Intrinsics.checkParameterIsNotNull(configuration, "configuration");
        NoLocalSQLiteOpenHelper noLocalSQLiteOpenHelper = new NoLocalSQLiteOpenHelper(configuration.context, configuration.name, configuration.callback);
        try {
            if (Build.VERSION.SDK_INT >= 27) {
                NoLocalSQLiteOpenHelper.OpenHelper openHelper = noLocalSQLiteOpenHelper.mDelegate;
                Class<? super Object> superclass = openHelper.getClass().getSuperclass();
                if (superclass == null) {
                    Intrinsics.throwNpe();
                }
                Field f = superclass.getDeclaredField("mOpenParamsBuilder");
                Intrinsics.checkExpressionValueIsNotNull(f, "f");
                f.setAccessible(true);
                Object obj = f.get(openHelper);
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.database.sqlite.SQLiteDatabase.OpenParams.Builder");
                }
                ((SQLiteDatabase.OpenParams.Builder) obj).addOpenFlags(16);
            }
        } catch (Exception e) {
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            String str = this.TAG;
            String stackTraceString = Log.getStackTraceString(e);
            Intrinsics.checkExpressionValueIsNotNull(stackTraceString, "Log.getStackTraceString(e)");
            netWorkLog.mo3279e(str, stackTraceString);
        }
        return noLocalSQLiteOpenHelper;
    }
}
