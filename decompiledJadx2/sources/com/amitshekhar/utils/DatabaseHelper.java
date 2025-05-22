package com.amitshekhar.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.amitshekhar.model.Response;
import com.amitshekhar.model.RowDataRequest;
import com.amitshekhar.model.TableDataResponse;
import com.amitshekhar.model.UpdateRowResponse;
import com.amitshekhar.sqlite.SQLiteDB;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DatabaseHelper {
    private DatabaseHelper() {
    }

    public static Response getAllTableName(SQLiteDB sQLiteDB) {
        Response response = new Response();
        Cursor rawQuery = sQLiteDB.rawQuery("SELECT name FROM sqlite_master WHERE type='table' OR type='view' ORDER BY name COLLATE NOCASE", null);
        if (rawQuery.moveToFirst()) {
            while (!rawQuery.isAfterLast()) {
                response.rows.add(rawQuery.getString(0));
                rawQuery.moveToNext();
            }
        }
        rawQuery.close();
        response.isSuccessful = true;
        try {
            response.dbVersion = sQLiteDB.getVersion();
        } catch (Exception unused) {
        }
        return response;
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:0x00f8, code lost:
    
        if (r9.getCount() > 0) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00fa, code lost:
    
        r10 = new java.util.ArrayList();
        r11 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0104, code lost:
    
        if (r11 >= r9.getColumnCount()) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0106, code lost:
    
        r2 = new com.amitshekhar.model.TableDataResponse.ColumnData();
        r3 = r9.getType(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x010f, code lost:
    
        if (r3 == 1) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0112, code lost:
    
        if (r3 == 2) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0117, code lost:
    
        if (r3 == 3) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x011a, code lost:
    
        if (r3 == 4) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x011c, code lost:
    
        r2.dataType = "text";
        r2.value = r9.getString(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0158, code lost:
    
        r10.add(r2);
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0125, code lost:
    
        r2.dataType = "text";
        r2.value = com.amitshekhar.utils.ConverterUtils.blobToString(r9.getBlob(r11));
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0132, code lost:
    
        r2.dataType = "text";
        r2.value = r9.getString(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x013b, code lost:
    
        r2.dataType = com.amitshekhar.utils.DataType.REAL;
        r2.value = java.lang.Double.valueOf(r9.getDouble(r11));
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x014a, code lost:
    
        r2.dataType = "integer";
        r2.value = java.lang.Long.valueOf(r9.getLong(r11));
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x015e, code lost:
    
        r0.rows.add(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0167, code lost:
    
        if (r9.moveToNext() != false) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0169, code lost:
    
        r9.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x016c, code lost:
    
        return r0;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x016d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static TableDataResponse getTableData(SQLiteDB sQLiteDB, String str, String str2) {
        Cursor cursor;
        boolean z;
        Cursor rawQuery;
        TableDataResponse tableDataResponse = new TableDataResponse();
        tableDataResponse.isSelectQuery = true;
        if (str2 == null) {
            str2 = getTableName(str);
        }
        String quotedTableName = getQuotedTableName(str2);
        if (str2 != null) {
            tableDataResponse.tableInfos = getTableInfo(sQLiteDB, "PRAGMA table_info(" + quotedTableName + ")");
        }
        Cursor cursor2 = null;
        try {
            try {
                cursor = sQLiteDB.rawQuery("SELECT type FROM sqlite_master WHERE name=?", new String[]{quotedTableName});
            } catch (Exception e) {
                e = e;
                cursor = null;
            } catch (Throwable th) {
                th = th;
                if (0 != 0) {
                }
                throw th;
            }
            try {
                z = cursor.moveToFirst() ? "view".equalsIgnoreCase(cursor.getString(0)) : false;
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                if (cursor != null) {
                    cursor.close();
                }
                z = false;
                tableDataResponse.isEditable = (str2 != null || tableDataResponse.tableInfos == null || z) ? false : true;
                if (!TextUtils.isEmpty(str2)) {
                }
                rawQuery = sQLiteDB.rawQuery(str, null);
                if (rawQuery == null) {
                }
            }
            tableDataResponse.isEditable = (str2 != null || tableDataResponse.tableInfos == null || z) ? false : true;
            if (!TextUtils.isEmpty(str2)) {
                str = str.replace(str2, quotedTableName);
            }
            try {
                rawQuery = sQLiteDB.rawQuery(str, null);
                if (rawQuery == null) {
                    rawQuery.moveToFirst();
                    if (tableDataResponse.tableInfos == null) {
                        tableDataResponse.tableInfos = new ArrayList();
                        for (int i = 0; i < rawQuery.getColumnCount(); i++) {
                            TableDataResponse.TableInfo tableInfo = new TableDataResponse.TableInfo();
                            tableInfo.title = rawQuery.getColumnName(i);
                            tableInfo.isPrimary = true;
                            tableDataResponse.tableInfos.add(tableInfo);
                        }
                    }
                    tableDataResponse.isSuccessful = true;
                    tableDataResponse.rows = new ArrayList();
                    String[] columnNames = rawQuery.getColumnNames();
                    ArrayList arrayList = new ArrayList();
                    for (String str3 : columnNames) {
                        Iterator<TableDataResponse.TableInfo> it = tableDataResponse.tableInfos.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                TableDataResponse.TableInfo next = it.next();
                                if (str3.equals(next.title)) {
                                    arrayList.add(next);
                                    break;
                                }
                            }
                        }
                    }
                    if (tableDataResponse.tableInfos.size() != arrayList.size()) {
                        tableDataResponse.tableInfos = arrayList;
                        tableDataResponse.isEditable = false;
                    }
                } else {
                    tableDataResponse.isSuccessful = false;
                    tableDataResponse.errorMessage = "Cursor is null";
                    return tableDataResponse;
                }
            } catch (Exception e3) {
                e3.printStackTrace();
                tableDataResponse.isSuccessful = false;
                tableDataResponse.errorMessage = e3.getMessage();
                return tableDataResponse;
            }
        } catch (Throwable th2) {
            th = th2;
            if (0 != 0) {
                cursor2.close();
            }
            throw th;
        }
    }

    private static String getQuotedTableName(String str) {
        return String.format("[%s]", str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0022, code lost:
    
        r3 = r8.getColumnName(r2);
        r4 = 65535;
        r5 = r3.hashCode();
        r7 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x002e, code lost:
    
        if (r5 == 3579) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0033, code lost:
    
        if (r5 == 3373707) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003c, code lost:
    
        if (r3.equals("name") == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003e, code lost:
    
        r4 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0049, code lost:
    
        if (r4 == 0) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004b, code lost:
    
        if (r4 == 1) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x004e, code lost:
    
        r0.title = r8.getString(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x005f, code lost:
    
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0059, code lost:
    
        if (r8.getInt(r2) != 1) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005c, code lost:
    
        r7 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005d, code lost:
    
        r0.isPrimary = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0046, code lost:
    
        if (r3.equals(com.amitshekhar.utils.Constants.f1200PK) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0048, code lost:
    
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0062, code lost:
    
        r9.add(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0069, code lost:
    
        if (r8.moveToNext() != false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x006b, code lost:
    
        r8.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x006e, code lost:
    
        return r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0013, code lost:
    
        if (r8.getCount() > 0) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0015, code lost:
    
        r0 = new com.amitshekhar.model.TableDataResponse.TableInfo();
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
    
        if (r2 >= r8.getColumnCount()) goto L43;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static List<TableDataResponse.TableInfo> getTableInfo(SQLiteDB sQLiteDB, String str) {
        try {
            Cursor rawQuery = sQLiteDB.rawQuery(str, null);
            if (rawQuery == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            rawQuery.moveToFirst();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static UpdateRowResponse addRow(SQLiteDB sQLiteDB, String str, List<RowDataRequest> list) {
        UpdateRowResponse updateRowResponse = new UpdateRowResponse();
        if (list == null || str == null) {
            updateRowResponse.isSuccessful = false;
            return updateRowResponse;
        }
        String quotedTableName = getQuotedTableName(str);
        ContentValues contentValues = new ContentValues();
        for (RowDataRequest rowDataRequest : list) {
            if ("null".equals(rowDataRequest.value)) {
                rowDataRequest.value = null;
            }
            String str2 = rowDataRequest.dataType;
            char c = 65535;
            int hashCode = str2.hashCode();
            if (hashCode != 3496350) {
                if (hashCode != 3556653) {
                    if (hashCode == 1958052158 && str2.equals("integer")) {
                        c = 0;
                    }
                } else if (str2.equals("text")) {
                    c = 2;
                }
            } else if (str2.equals(DataType.REAL)) {
                c = 1;
            }
            if (c == 0) {
                contentValues.put(rowDataRequest.title, Long.valueOf(rowDataRequest.value));
            } else if (c == 1) {
                contentValues.put(rowDataRequest.title, Double.valueOf(rowDataRequest.value));
            } else if (c == 2) {
                contentValues.put(rowDataRequest.title, rowDataRequest.value);
            } else {
                contentValues.put(rowDataRequest.title, rowDataRequest.value);
            }
        }
        updateRowResponse.isSuccessful = sQLiteDB.insert(quotedTableName, null, contentValues) > 0;
        return updateRowResponse;
    }

    public static UpdateRowResponse updateRow(SQLiteDB sQLiteDB, String str, List<RowDataRequest> list) {
        UpdateRowResponse updateRowResponse = new UpdateRowResponse();
        if (list == null || str == null) {
            updateRowResponse.isSuccessful = false;
            return updateRowResponse;
        }
        String quotedTableName = getQuotedTableName(str);
        ContentValues contentValues = new ContentValues();
        ArrayList arrayList = new ArrayList();
        String str2 = null;
        for (RowDataRequest rowDataRequest : list) {
            if ("null".equals(rowDataRequest.value)) {
                rowDataRequest.value = null;
            }
            if (rowDataRequest.isPrimary) {
                str2 = str2 == null ? rowDataRequest.title + "=? " : str2 + "and " + rowDataRequest.title + "=? ";
                arrayList.add(rowDataRequest.value);
            } else {
                String str3 = rowDataRequest.dataType;
                char c = 65535;
                int hashCode = str3.hashCode();
                if (hashCode != 3496350) {
                    if (hashCode != 3556653) {
                        if (hashCode == 1958052158 && str3.equals("integer")) {
                            c = 0;
                        }
                    } else if (str3.equals("text")) {
                        c = 2;
                    }
                } else if (str3.equals(DataType.REAL)) {
                    c = 1;
                }
                if (c == 0) {
                    contentValues.put(rowDataRequest.title, Long.valueOf(rowDataRequest.value));
                } else if (c == 1) {
                    contentValues.put(rowDataRequest.title, Double.valueOf(rowDataRequest.value));
                } else if (c == 2) {
                    contentValues.put(rowDataRequest.title, rowDataRequest.value);
                }
            }
        }
        String[] strArr = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            strArr[i] = (String) arrayList.get(i);
        }
        sQLiteDB.update(quotedTableName, contentValues, str2, strArr);
        updateRowResponse.isSuccessful = true;
        return updateRowResponse;
    }

    public static UpdateRowResponse deleteRow(SQLiteDB sQLiteDB, String str, List<RowDataRequest> list) {
        UpdateRowResponse updateRowResponse = new UpdateRowResponse();
        if (list == null || str == null) {
            updateRowResponse.isSuccessful = false;
            return updateRowResponse;
        }
        String quotedTableName = getQuotedTableName(str);
        ArrayList arrayList = new ArrayList();
        String str2 = null;
        for (RowDataRequest rowDataRequest : list) {
            if ("null".equals(rowDataRequest.value)) {
                rowDataRequest.value = null;
            }
            if (rowDataRequest.isPrimary) {
                str2 = str2 == null ? rowDataRequest.title + "=? " : str2 + "and " + rowDataRequest.title + "=? ";
                arrayList.add(rowDataRequest.value);
            }
        }
        if (arrayList.size() == 0) {
            updateRowResponse.isSuccessful = true;
            return updateRowResponse;
        }
        String[] strArr = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            strArr[i] = (String) arrayList.get(i);
        }
        sQLiteDB.delete(quotedTableName, str2, strArr);
        updateRowResponse.isSuccessful = true;
        return updateRowResponse;
    }

    public static TableDataResponse exec(SQLiteDB sQLiteDB, String str) {
        TableDataResponse tableDataResponse = new TableDataResponse();
        tableDataResponse.isSelectQuery = false;
        try {
            String tableName = getTableName(str);
            if (!TextUtils.isEmpty(tableName)) {
                str = str.replace(tableName, getQuotedTableName(tableName));
            }
            sQLiteDB.execSQL(str);
            tableDataResponse.isSuccessful = true;
            return tableDataResponse;
        } catch (Exception e) {
            e.printStackTrace();
            tableDataResponse.isSuccessful = false;
            tableDataResponse.errorMessage = e.getMessage();
            return tableDataResponse;
        }
    }

    private static String getTableName(String str) {
        Iterator it = ((HashSet) new TableNameParser(str).tables()).iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (!TextUtils.isEmpty(str2)) {
                return str2;
            }
        }
        return null;
    }
}
