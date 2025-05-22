package com.amitshekhar.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.amitshekhar.model.Response;
import com.amitshekhar.model.RowDataRequest;
import com.amitshekhar.model.TableDataResponse;
import com.amitshekhar.model.UpdateRowResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class PrefHelper {
    private static final String PREFS_SUFFIX = ".xml";

    private PrefHelper() {
    }

    public static List<String> getSharedPreferenceTags(Context context) {
        ArrayList arrayList = new ArrayList();
        File file = new File(context.getApplicationInfo().dataDir + "/shared_prefs");
        if (file.exists()) {
            for (File file2 : file.listFiles()) {
                String name = file2.getName();
                if (name.endsWith(PREFS_SUFFIX)) {
                    arrayList.add(name.substring(0, name.length() - 4));
                }
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public static Response getAllPrefTableName(Context context) {
        Response response = new Response();
        Iterator<String> it = getSharedPreferenceTags(context).iterator();
        while (it.hasNext()) {
            response.rows.add(it.next());
        }
        response.isSuccessful = true;
        return response;
    }

    public static TableDataResponse getAllPrefData(Context context, String str) {
        TableDataResponse tableDataResponse = new TableDataResponse();
        tableDataResponse.isEditable = true;
        tableDataResponse.isSuccessful = true;
        tableDataResponse.isSelectQuery = true;
        TableDataResponse.TableInfo tableInfo = new TableDataResponse.TableInfo();
        tableInfo.isPrimary = true;
        tableInfo.title = "Key";
        TableDataResponse.TableInfo tableInfo2 = new TableDataResponse.TableInfo();
        tableInfo2.isPrimary = false;
        tableInfo2.title = "Value";
        tableDataResponse.tableInfos = new ArrayList();
        tableDataResponse.tableInfos.add(tableInfo);
        tableDataResponse.tableInfos.add(tableInfo2);
        tableDataResponse.rows = new ArrayList();
        for (Map.Entry<String, ?> entry : context.getSharedPreferences(str, 0).getAll().entrySet()) {
            ArrayList arrayList = new ArrayList();
            TableDataResponse.ColumnData columnData = new TableDataResponse.ColumnData();
            columnData.dataType = "text";
            columnData.value = entry.getKey();
            arrayList.add(columnData);
            TableDataResponse.ColumnData columnData2 = new TableDataResponse.ColumnData();
            columnData2.value = entry.getValue().toString();
            if (entry.getValue() != null) {
                if (entry.getValue() instanceof String) {
                    columnData2.dataType = "text";
                } else if (entry.getValue() instanceof Integer) {
                    columnData2.dataType = "integer";
                } else if (entry.getValue() instanceof Long) {
                    columnData2.dataType = DataType.LONG;
                } else if (entry.getValue() instanceof Float) {
                    columnData2.dataType = "float";
                } else if (entry.getValue() instanceof Boolean) {
                    columnData2.dataType = "boolean";
                } else if (entry.getValue() instanceof Set) {
                    columnData2.dataType = DataType.STRING_SET;
                }
            } else {
                columnData2.dataType = "text";
            }
            arrayList.add(columnData2);
            tableDataResponse.rows.add(arrayList);
        }
        return tableDataResponse;
    }

    public static UpdateRowResponse addOrUpdateRow(Context context, String str, List<RowDataRequest> list) {
        UpdateRowResponse updateRowResponse = new UpdateRowResponse();
        if (str == null) {
            return updateRowResponse;
        }
        RowDataRequest rowDataRequest = list.get(0);
        RowDataRequest rowDataRequest2 = list.get(1);
        String str2 = rowDataRequest.value;
        String str3 = rowDataRequest2.value;
        String str4 = rowDataRequest2.dataType;
        if ("null".equals(str3)) {
            str3 = null;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
        char c = 65535;
        try {
            switch (str4.hashCode()) {
                case -1572742348:
                    if (str4.equals(DataType.STRING_SET)) {
                        c = 5;
                        break;
                    }
                    break;
                case 3327612:
                    if (str4.equals(DataType.LONG)) {
                        c = 2;
                        break;
                    }
                    break;
                case 3556653:
                    if (str4.equals("text")) {
                        c = 0;
                        break;
                    }
                    break;
                case 64711720:
                    if (str4.equals("boolean")) {
                        c = 4;
                        break;
                    }
                    break;
                case 97526364:
                    if (str4.equals("float")) {
                        c = 3;
                        break;
                    }
                    break;
                case 1958052158:
                    if (str4.equals("integer")) {
                        c = 1;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                sharedPreferences.edit().putString(str2, str3).apply();
                updateRowResponse.isSuccessful = true;
            } else if (c == 1) {
                sharedPreferences.edit().putInt(str2, Integer.valueOf(str3).intValue()).apply();
                updateRowResponse.isSuccessful = true;
            } else if (c == 2) {
                sharedPreferences.edit().putLong(str2, Long.valueOf(str3).longValue()).apply();
                updateRowResponse.isSuccessful = true;
            } else if (c == 3) {
                sharedPreferences.edit().putFloat(str2, Float.valueOf(str3).floatValue()).apply();
                updateRowResponse.isSuccessful = true;
            } else if (c == 4) {
                sharedPreferences.edit().putBoolean(str2, Boolean.valueOf(str3).booleanValue()).apply();
                updateRowResponse.isSuccessful = true;
            } else if (c == 5) {
                JSONArray jSONArray = new JSONArray(str3);
                HashSet hashSet = new HashSet();
                for (int i = 0; i < jSONArray.length(); i++) {
                    hashSet.add(jSONArray.getString(i));
                }
                sharedPreferences.edit().putStringSet(str2, hashSet).apply();
                updateRowResponse.isSuccessful = true;
            } else {
                sharedPreferences.edit().putString(str2, str3).apply();
                updateRowResponse.isSuccessful = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateRowResponse;
    }

    public static UpdateRowResponse deleteRow(Context context, String str, List<RowDataRequest> list) {
        UpdateRowResponse updateRowResponse = new UpdateRowResponse();
        if (str == null) {
            return updateRowResponse;
        }
        try {
            context.getSharedPreferences(str, 0).edit().remove(list.get(0).value).apply();
            updateRowResponse.isSuccessful = true;
        } catch (Exception unused) {
            updateRowResponse.isSuccessful = false;
        }
        return updateRowResponse;
    }
}
