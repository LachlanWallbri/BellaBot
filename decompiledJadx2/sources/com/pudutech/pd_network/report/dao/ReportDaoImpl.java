package com.pudutech.pd_network.report.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.pd_network.PdNetConfig;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.report.bean.ReportBean;
import com.pudutech.pd_network.utils.ExtKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReportDao.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u0000 .2\u00020\u0001:\u0001.B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0014H\u0016J\u0010\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0016H\u0016J\u0016\u0010\u0018\u001a\u00020\u00142\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00160\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u001e\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u001b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u0016H\u0016J2\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u001b2\b\u0010#\u001a\u0004\u0018\u00010\u00062\b\u0010$\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u0016H\u0016J\u0016\u0010%\u001a\b\u0012\u0004\u0012\u00020!0\u001b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010&\u001a\u00020\u0016H\u0016J\b\u0010'\u001a\u00020\u0016H\u0016J\u0012\u0010(\u001a\u0004\u0018\u00010!2\u0006\u0010\u0019\u001a\u00020\u0016H\u0016J\u0015\u0010)\u001a\u00020\u00162\u0006\u0010*\u001a\u00020!H\u0016¢\u0006\u0002\u0010+J\u0016\u0010,\u001a\u00020\u00142\f\u0010*\u001a\b\u0012\u0004\u0012\u00020!0\u001bH\u0016J\u0010\u0010-\u001a\u00020\u00142\u0006\u0010*\u001a\u00020!H\u0016J\u001e\u0010-\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00160\u001bH\u0016R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, m3961d2 = {"Lcom/pudutech/pd_network/report/dao/ReportDaoImpl;", "Lcom/pudutech/pd_network/report/dao/IReportDao;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "kotlin.jvm.PlatformType", "getContext", "()Landroid/content/Context;", "db", "Landroid/database/sqlite/SQLiteDatabase;", "getDb", "()Landroid/database/sqlite/SQLiteDatabase;", "setDb", "(Landroid/database/sqlite/SQLiteDatabase;)V", "dbName", "mCursor", "Landroid/database/Cursor;", RequestParameters.SUBRESOURCE_DELETE, "", "count", "", "deleteAll", "deleteById", "id", "ids", "", "deleteByUpload", "upload", "", "getCount", "getList", "Lcom/pudutech/pd_network/report/bean/ReportBean;", "timeStamp", "env", "cluster", "getListByUpload", "getMaxId", "getMinId", "getReportById", "insert", "bean", "(Lcom/pudutech/pd_network/report/bean/ReportBean;)Ljava/lang/Long;", "insertList", "updateUploadState", "Companion", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ReportDaoImpl implements IReportDao {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static IReportDao instance;
    private final String TAG;
    private final Context context;
    private SQLiteDatabase db;
    private final String dbName;
    private Cursor mCursor;

    private ReportDaoImpl(Context context) {
        this.context = context;
        this.TAG = getClass().getSimpleName();
        boolean isMainProcess = PdNetConfig.INSTANCE.isMainProcess();
        String str = ReportSqliteHelpKt.DB_NAME;
        if (!isMainProcess) {
            StringBuilder sb = new StringBuilder();
            String md5 = ExtKt.md5(PdNetConfig.INSTANCE.getProcessName());
            sb.append(md5 == null ? "" : md5);
            sb.append("_");
            sb.append(ReportSqliteHelpKt.DB_NAME);
            str = sb.toString();
        }
        this.dbName = str;
        SQLiteDatabase writableDatabase = new ReportSqliteHelp(this.context, this.dbName).getWritableDatabase();
        Intrinsics.checkExpressionValueIsNotNull(writableDatabase, "ReportSqliteHelp(context, dbName).writableDatabase");
        this.db = writableDatabase;
    }

    public /* synthetic */ ReportDaoImpl(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final Context getContext() {
        return this.context;
    }

    public final SQLiteDatabase getDb() {
        return this.db;
    }

    public final void setDb(SQLiteDatabase sQLiteDatabase) {
        Intrinsics.checkParameterIsNotNull(sQLiteDatabase, "<set-?>");
        this.db = sQLiteDatabase;
    }

    @Override // com.pudutech.pd_network.report.dao.IReportDao
    public Long insert(ReportBean bean) {
        Intrinsics.checkParameterIsNotNull(bean, "bean");
        ContentValues contentValues = new ContentValues();
        contentValues.put("timeStamp", Long.valueOf(bean.getTimeStamp()));
        contentValues.put("data", bean.getData());
        contentValues.put("host", bean.getHost());
        contentValues.put("api", bean.getApi());
        contentValues.put("env", bean.getEnv());
        contentValues.put("cluster", bean.getCluster());
        contentValues.put("upload", Integer.valueOf(bean.getUpload()));
        return Long.valueOf(this.db.insert(ReportSqliteHelpKt.DB_TABLE, "reportId", contentValues));
    }

    @Override // com.pudutech.pd_network.report.dao.IReportDao
    public void insertList(List<ReportBean> bean) {
        Intrinsics.checkParameterIsNotNull(bean, "bean");
        SQLiteDatabase sQLiteDatabase = this.db;
        sQLiteDatabase.beginTransaction();
        try {
            Iterator<T> it = bean.iterator();
            while (it.hasNext()) {
                insert((ReportBean) it.next());
            }
            Unit unit = Unit.INSTANCE;
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    @Override // com.pudutech.pd_network.report.dao.IReportDao
    public void delete(long count) {
        SQLiteDatabase sQLiteDatabase = this.db;
        sQLiteDatabase.beginTransaction();
        try {
            this.db.execSQL("delete from t_report where reportId < (select reportId from t_report order by reportId asc limit " + count + ", 1)");
            Unit unit = Unit.INSTANCE;
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    @Override // com.pudutech.pd_network.report.dao.IReportDao
    public void deleteById(long id) {
        this.db.execSQL("delete from t_report where reportId=?", new Long[]{Long.valueOf(id)});
    }

    @Override // com.pudutech.pd_network.report.dao.IReportDao
    public void deleteById(List<Long> ids) {
        Intrinsics.checkParameterIsNotNull(ids, "ids");
        this.db.execSQL("delete from t_report where reportId in (" + CollectionsKt.joinToString$default(ids, null, null, null, 0, null, null, 63, null) + ')', new Object[0]);
    }

    @Override // com.pudutech.pd_network.report.dao.IReportDao
    public void deleteByUpload(int upload) {
        this.db.execSQL("delete from t_report where upload=?", new Integer[]{Integer.valueOf(upload)});
    }

    @Override // com.pudutech.pd_network.report.dao.IReportDao
    public void deleteAll() {
        this.db.execSQL("delete from t_report where 1=1", new Object[0]);
    }

    @Override // com.pudutech.pd_network.report.dao.IReportDao
    public void updateUploadState(ReportBean bean) {
        Intrinsics.checkParameterIsNotNull(bean, "bean");
        this.db.execSQL("update t_report set upload=? where reportId=?", new Object[]{Integer.valueOf(bean.getUpload()), Long.valueOf(bean.getReportId())});
    }

    @Override // com.pudutech.pd_network.report.dao.IReportDao
    public void updateUploadState(int upload, List<Long> ids) {
        Intrinsics.checkParameterIsNotNull(ids, "ids");
        if (ids.isEmpty()) {
            return;
        }
        this.db.execSQL("update t_report set upload=" + upload + " where reportId in (" + CollectionsKt.joinToString$default(ids, null, null, null, 0, null, null, 63, null) + ')', new Object[0]);
    }

    @Override // com.pudutech.pd_network.report.dao.IReportDao
    public List<ReportBean> getListByUpload(int upload) {
        return getList(upload, 0L);
    }

    @Override // com.pudutech.pd_network.report.dao.IReportDao
    public List<ReportBean> getList(int upload, long timeStamp) {
        return getList(null, null, upload, timeStamp);
    }

    @Override // com.pudutech.pd_network.report.dao.IReportDao
    public List<ReportBean> getList(String env, String cluster, int upload, long timeStamp) {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("select * from t_report where ");
        sb.append(" timeStamp > ");
        sb.append(timeStamp);
        sb.append(' ');
        sb.append(" and ");
        sb.append("upload=");
        sb.append(upload);
        sb.append(' ');
        sb.append(' ');
        String str2 = env;
        boolean z = true;
        String str3 = "";
        if (str2 == null || str2.length() == 0) {
            str = "";
        } else {
            str = "and env='" + env + '\'';
        }
        sb.append(str);
        sb.append(' ');
        String str4 = cluster;
        if (str4 != null && str4.length() != 0) {
            z = false;
        }
        if (!z) {
            str3 = " and cluster='" + cluster + '\'';
        }
        sb.append(str3);
        sb.append(" order by reportId desc limit 20");
        String sb2 = sb.toString();
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        netWorkLog.mo3280i(TAG, sb2);
        ArrayList arrayList = new ArrayList();
        try {
            try {
                Cursor cursor = this.mCursor;
                if (cursor != null) {
                    cursor.close();
                }
                this.mCursor = (Cursor) null;
                Cursor it = this.db.rawQuery(sb2, new String[0]);
                this.mCursor = it;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (it.getCount() > 0) {
                    int columnIndex = it.getColumnIndex("reportId");
                    int columnIndex2 = it.getColumnIndex("timeStamp");
                    int columnIndex3 = it.getColumnIndex("data");
                    int columnIndex4 = it.getColumnIndex("api");
                    int columnIndex5 = it.getColumnIndex("env");
                    int columnIndex6 = it.getColumnIndex("cluster");
                    int columnIndex7 = it.getColumnIndex("host");
                    int columnIndex8 = it.getColumnIndex("upload");
                    while (it.moveToNext()) {
                        long j = it.getLong(columnIndex);
                        long j2 = it.getLong(columnIndex2);
                        String string = it.getString(columnIndex3);
                        Intrinsics.checkExpressionValueIsNotNull(string, "it.getString(dataIndex)");
                        String string2 = it.getString(columnIndex4);
                        Intrinsics.checkExpressionValueIsNotNull(string2, "it.getString(apiIndex)");
                        String string3 = it.getString(columnIndex7);
                        int i = columnIndex;
                        Intrinsics.checkExpressionValueIsNotNull(string3, "it.getString(hostIndex)");
                        String string4 = it.getString(columnIndex5);
                        int i2 = columnIndex2;
                        Intrinsics.checkExpressionValueIsNotNull(string4, "it.getString(envIndex)");
                        String string5 = it.getString(columnIndex6);
                        int i3 = columnIndex3;
                        Intrinsics.checkExpressionValueIsNotNull(string5, "it.getString(clusterIndex)");
                        arrayList.add(new ReportBean(j, j2, string, string3, string2, string4, string5, it.getInt(columnIndex8)));
                        columnIndex = i;
                        columnIndex2 = i2;
                        columnIndex3 = i3;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
                String TAG2 = this.TAG;
                Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
                netWorkLog2.mo3280i(TAG2, "getList.error " + e + ' ' + e.getMessage());
                Cursor cursor2 = this.mCursor;
                if (cursor2 != null) {
                    cursor2.close();
                }
            }
            return arrayList;
        } finally {
            Cursor cursor3 = this.mCursor;
            if (cursor3 != null) {
                cursor3.close();
            }
            this.mCursor = null;
        }
    }

    @Override // com.pudutech.pd_network.report.dao.IReportDao
    public long getCount(int upload) {
        Cursor rawQuery = this.db.rawQuery("select count(*) from t_report where upload=?", new String[]{String.valueOf(upload)});
        rawQuery.moveToFirst();
        long j = rawQuery.getLong(0);
        rawQuery.close();
        return j;
    }

    @Override // com.pudutech.pd_network.report.dao.IReportDao
    public long getMinId() {
        Cursor rawQuery = this.db.rawQuery("select reportId from t_report order by reportId asc limit 1", new String[0]);
        rawQuery.moveToFirst();
        long j = rawQuery.getLong(0);
        rawQuery.close();
        return j;
    }

    @Override // com.pudutech.pd_network.report.dao.IReportDao
    public long getMaxId() {
        Cursor rawQuery = this.db.rawQuery("select reportId from t_report order by reportId desc limit 1", new String[0]);
        rawQuery.moveToFirst();
        long j = rawQuery.getLong(0);
        rawQuery.close();
        return j;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x00f7, code lost:
    
        if (r3 == null) goto L24;
     */
    @Override // com.pudutech.pd_network.report.dao.IReportDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ReportBean getReportById(long id) {
        Cursor cursor = (Cursor) null;
        ReportBean reportBean = (ReportBean) null;
        try {
            try {
                cursor = this.db.rawQuery("select * from t_report where reportId=?", new String[]{String.valueOf(id)});
                Intrinsics.checkExpressionValueIsNotNull(cursor, "cursor");
                if (cursor.getCount() > 0) {
                    int columnIndex = cursor.getColumnIndex("reportId");
                    int columnIndex2 = cursor.getColumnIndex("timeStamp");
                    int columnIndex3 = cursor.getColumnIndex("data");
                    int columnIndex4 = cursor.getColumnIndex("api");
                    int columnIndex5 = cursor.getColumnIndex("env");
                    int columnIndex6 = cursor.getColumnIndex("cluster");
                    int columnIndex7 = cursor.getColumnIndex("host");
                    int columnIndex8 = cursor.getColumnIndex("upload");
                    while (cursor.moveToNext()) {
                        try {
                            long j = cursor.getLong(columnIndex);
                            long j2 = cursor.getLong(columnIndex2);
                            String string = cursor.getString(columnIndex3);
                            int i = columnIndex;
                            Intrinsics.checkExpressionValueIsNotNull(string, "cursor.getString(dataIndex)");
                            String string2 = cursor.getString(columnIndex4);
                            ReportBean reportBean2 = reportBean;
                            try {
                                Intrinsics.checkExpressionValueIsNotNull(string2, "cursor.getString(apiIndex)");
                                String string3 = cursor.getString(columnIndex7);
                                int i2 = columnIndex2;
                                Intrinsics.checkExpressionValueIsNotNull(string3, "cursor.getString(hostIndex)");
                                String string4 = cursor.getString(columnIndex5);
                                int i3 = columnIndex3;
                                Intrinsics.checkExpressionValueIsNotNull(string4, "cursor.getString(envIndex)");
                                String string5 = cursor.getString(columnIndex6);
                                int i4 = columnIndex4;
                                Intrinsics.checkExpressionValueIsNotNull(string5, "cursor.getString(clusterIndex)");
                                ReportBean reportBean3 = new ReportBean(j, j2, string, string3, string2, string4, string5, cursor.getInt(columnIndex8));
                                columnIndex = i;
                                reportBean = reportBean3;
                                columnIndex2 = i2;
                                columnIndex3 = i3;
                                columnIndex4 = i4;
                            } catch (Exception e) {
                                e = e;
                                reportBean = reportBean2;
                                e.printStackTrace();
                                NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                                String TAG = this.TAG;
                                Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
                                netWorkLog.mo3280i(TAG, "getList.getReportById " + e + ' ' + e.getMessage());
                            }
                        } catch (Exception e2) {
                            e = e2;
                        }
                    }
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
        }
        cursor.close();
        return reportBean;
    }

    /* compiled from: ReportDao.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\bR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/pd_network/report/dao/ReportDaoImpl$Companion;", "", "()V", "instance", "Lcom/pudutech/pd_network/report/dao/IReportDao;", "getDb", "context", "Landroid/content/Context;", "getDb$pd_network_release", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final IReportDao getDb$pd_network_release(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            if (ReportDaoImpl.instance == null) {
                ReportDaoImpl.instance = new ReportDaoImpl(context, null);
            }
            IReportDao iReportDao = ReportDaoImpl.instance;
            if (iReportDao == null) {
                Intrinsics.throwNpe();
            }
            return iReportDao;
        }
    }
}
