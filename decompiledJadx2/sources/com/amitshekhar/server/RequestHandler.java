package com.amitshekhar.server;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import androidx.sqlite.p003db.SupportSQLiteDatabase;
import com.amitshekhar.model.Response;
import com.amitshekhar.model.RowDataRequest;
import com.amitshekhar.model.TableDataResponse;
import com.amitshekhar.model.UpdateRowResponse;
import com.amitshekhar.sqlite.DBFactory;
import com.amitshekhar.sqlite.InMemoryDebugSQLiteDB;
import com.amitshekhar.sqlite.SQLiteDB;
import com.amitshekhar.utils.Constants;
import com.amitshekhar.utils.DatabaseFileProvider;
import com.amitshekhar.utils.DatabaseHelper;
import com.amitshekhar.utils.PrefHelper;
import com.amitshekhar.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.iflytek.speech.VoiceWakeuperAidl;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class RequestHandler {
    private boolean isDbOpened;
    private final AssetManager mAssets;
    private final Context mContext;
    private HashMap<String, Pair<File, String>> mCustomDatabaseFiles;
    private HashMap<String, Pair<File, String>> mDatabaseFiles;
    private final DBFactory mDbFactory;
    private SQLiteDB sqLiteDB;
    private String mSelectedDatabase = null;
    private HashMap<String, SupportSQLiteDatabase> mRoomInMemoryDatabases = new HashMap<>();
    private final Gson mGson = new GsonBuilder().serializeNulls().create();

    public RequestHandler(Context context, DBFactory dBFactory) {
        this.mContext = context;
        this.mAssets = context.getResources().getAssets();
        this.mDbFactory = dBFactory;
    }

    /* JADX WARN: Code restructure failed: missing block: B:67:0x0044, code lost:
    
        if (r2.isEmpty() != false) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void handle(Socket socket) throws IOException {
        BufferedReader bufferedReader;
        PrintStream printStream;
        String str;
        byte[] loadContent;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (TextUtils.isEmpty(readLine)) {
                        str = null;
                        break;
                    } else if (readLine.startsWith("GET /")) {
                        int indexOf = readLine.indexOf(47) + 1;
                        str = readLine.substring(indexOf, readLine.indexOf(32, indexOf));
                        break;
                    }
                } catch (Throwable th) {
                    th = th;
                    printStream = null;
                }
            }
            printStream = new PrintStream(socket.getOutputStream());
            if (str != null) {
                try {
                } catch (Throwable th2) {
                    th = th2;
                    if (printStream != null) {
                        try {
                            printStream.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw th;
                        }
                    }
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th;
                }
            }
            str = "index.html";
            if (str.startsWith("getDbList")) {
                loadContent = getDBListResponse().getBytes();
            } else if (str.startsWith("getAllDataFromTheTable")) {
                loadContent = getAllDataFromTheTableResponse(str).getBytes();
            } else if (str.startsWith("getTableList")) {
                loadContent = getTableListResponse(str).getBytes();
            } else if (str.startsWith("addTableData")) {
                loadContent = addTableDataAndGetResponse(str).getBytes();
            } else if (str.startsWith("updateTableData")) {
                loadContent = updateTableDataAndGetResponse(str).getBytes();
            } else if (str.startsWith("deleteTableData")) {
                loadContent = deleteTableDataAndGetResponse(str).getBytes();
            } else if (str.startsWith("query")) {
                loadContent = executeQueryAndGetResponse(str).getBytes();
            } else if (str.startsWith("deleteDb")) {
                loadContent = deleteSelectedDatabaseAndGetResponse().getBytes();
            } else if (str.startsWith("downloadDb")) {
                loadContent = Utils.getDatabase(this.mSelectedDatabase, this.mDatabaseFiles);
            } else {
                loadContent = Utils.loadContent(str, this.mAssets);
            }
            if (loadContent == null) {
                writeServerError(printStream);
                try {
                    printStream.close();
                    bufferedReader.close();
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            printStream.println("HTTP/1.0 200 OK");
            printStream.println("Content-Type: " + Utils.detectMimeType(str));
            if (str.startsWith("downloadDb")) {
                printStream.println("Content-Disposition: attachment; filename=" + this.mSelectedDatabase);
            } else {
                printStream.println("Content-Length: " + loadContent.length);
            }
            printStream.println();
            printStream.write(loadContent);
            printStream.flush();
            try {
                printStream.close();
                bufferedReader.close();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            printStream = null;
        }
    }

    public void setCustomDatabaseFiles(HashMap<String, Pair<File, String>> hashMap) {
        this.mCustomDatabaseFiles = hashMap;
    }

    public void setInMemoryRoomDatabases(HashMap<String, SupportSQLiteDatabase> hashMap) {
        this.mRoomInMemoryDatabases = hashMap;
    }

    private void writeServerError(PrintStream printStream) {
        printStream.println("HTTP/1.0 500 Internal Server Error");
        printStream.flush();
    }

    private void openDatabase(String str) {
        closeDatabase();
        if (this.mRoomInMemoryDatabases.containsKey(str)) {
            this.sqLiteDB = new InMemoryDebugSQLiteDB(this.mRoomInMemoryDatabases.get(str));
        } else {
            File file = (File) this.mDatabaseFiles.get(str).first;
            this.sqLiteDB = this.mDbFactory.create(this.mContext, file.getAbsolutePath(), (String) this.mDatabaseFiles.get(str).second);
        }
        this.isDbOpened = true;
    }

    private void closeDatabase() {
        SQLiteDB sQLiteDB = this.sqLiteDB;
        if (sQLiteDB != null && sQLiteDB.isOpen()) {
            this.sqLiteDB.close();
        }
        this.sqLiteDB = null;
        this.isDbOpened = false;
    }

    private String getDBListResponse() {
        this.mDatabaseFiles = DatabaseFileProvider.getDatabaseFiles(this.mContext);
        HashMap<String, Pair<File, String>> hashMap = this.mCustomDatabaseFiles;
        if (hashMap != null) {
            this.mDatabaseFiles.putAll(hashMap);
        }
        Response response = new Response();
        HashMap<String, Pair<File, String>> hashMap2 = this.mDatabaseFiles;
        if (hashMap2 != null) {
            for (Map.Entry<String, Pair<File, String>> entry : hashMap2.entrySet()) {
                String[] strArr = new String[3];
                strArr[0] = entry.getKey();
                strArr[1] = !((String) entry.getValue().second).equals("") ? "true" : "false";
                strArr[2] = "true";
                response.rows.add(strArr);
            }
        }
        HashMap<String, SupportSQLiteDatabase> hashMap3 = this.mRoomInMemoryDatabases;
        if (hashMap3 != null) {
            Iterator<Map.Entry<String, SupportSQLiteDatabase>> it = hashMap3.entrySet().iterator();
            while (it.hasNext()) {
                response.rows.add(new String[]{it.next().getKey(), "false", "false"});
            }
        }
        response.rows.add(new String[]{Constants.APP_SHARED_PREFERENCES, "false", "false"});
        response.isSuccessful = true;
        return this.mGson.toJson(response);
    }

    private String getAllDataFromTheTableResponse(String str) {
        TableDataResponse allPrefData;
        String substring = str.contains("?tableName=") ? str.substring(str.indexOf("=") + 1, str.length()) : null;
        if (this.isDbOpened) {
            allPrefData = DatabaseHelper.getTableData(this.sqLiteDB, "SELECT * FROM " + substring, substring);
        } else {
            allPrefData = PrefHelper.getAllPrefData(this.mContext, substring);
        }
        return this.mGson.toJson(allPrefData);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private String executeQueryAndGetResponse(String str) {
        String str2;
        try {
            String substring = str.contains("?query=") ? str.substring(str.indexOf("=") + 1, str.length()) : null;
            try {
                substring = URLDecoder.decode(substring, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (substring != null) {
                str2 = null;
                for (String str3 : substring.split(VoiceWakeuperAidl.PARAMS_SEPARATE)) {
                    try {
                        String trim = str3.trim();
                        String lowerCase = trim.split(" ")[0].toLowerCase();
                        if (!lowerCase.equals("select") && !lowerCase.equals("pragma")) {
                            TableDataResponse exec = DatabaseHelper.exec(this.sqLiteDB, trim);
                            str2 = this.mGson.toJson(exec);
                            if (!exec.isSuccessful) {
                                break;
                            }
                        }
                        TableDataResponse tableData = DatabaseHelper.getTableData(this.sqLiteDB, trim, null);
                        str2 = this.mGson.toJson(tableData);
                        if (!tableData.isSuccessful) {
                            break;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                        if (str2 == null) {
                        }
                    }
                }
            } else {
                str2 = null;
            }
        } catch (Exception e3) {
            e = e3;
            str2 = null;
        }
        if (str2 == null) {
            return str2;
        }
        Response response = new Response();
        response.isSuccessful = false;
        return this.mGson.toJson(response);
    }

    private String getTableListResponse(String str) {
        Response response;
        String substring = str.contains("?database=") ? str.substring(str.indexOf("=") + 1, str.length()) : null;
        if (Constants.APP_SHARED_PREFERENCES.equals(substring)) {
            response = PrefHelper.getAllPrefTableName(this.mContext);
            closeDatabase();
            this.mSelectedDatabase = Constants.APP_SHARED_PREFERENCES;
        } else {
            openDatabase(substring);
            Response allTableName = DatabaseHelper.getAllTableName(this.sqLiteDB);
            this.mSelectedDatabase = substring;
            response = allTableName;
        }
        return this.mGson.toJson(response);
    }

    private String addTableDataAndGetResponse(String str) {
        UpdateRowResponse addRow;
        try {
            Uri parse = Uri.parse(URLDecoder.decode(str, "UTF-8"));
            String queryParameter = parse.getQueryParameter("tableName");
            List list = (List) this.mGson.fromJson(parse.getQueryParameter("addData"), new TypeToken<List<RowDataRequest>>() { // from class: com.amitshekhar.server.RequestHandler.1
            }.getType());
            if (Constants.APP_SHARED_PREFERENCES.equals(this.mSelectedDatabase)) {
                addRow = PrefHelper.addOrUpdateRow(this.mContext, queryParameter, list);
            } else {
                addRow = DatabaseHelper.addRow(this.sqLiteDB, queryParameter, list);
            }
            return this.mGson.toJson(addRow);
        } catch (Exception e) {
            e.printStackTrace();
            UpdateRowResponse updateRowResponse = new UpdateRowResponse();
            updateRowResponse.isSuccessful = false;
            return this.mGson.toJson(updateRowResponse);
        }
    }

    private String updateTableDataAndGetResponse(String str) {
        UpdateRowResponse updateRow;
        try {
            Uri parse = Uri.parse(URLDecoder.decode(str, "UTF-8"));
            String queryParameter = parse.getQueryParameter("tableName");
            List list = (List) this.mGson.fromJson(parse.getQueryParameter("updatedData"), new TypeToken<List<RowDataRequest>>() { // from class: com.amitshekhar.server.RequestHandler.2
            }.getType());
            if (Constants.APP_SHARED_PREFERENCES.equals(this.mSelectedDatabase)) {
                updateRow = PrefHelper.addOrUpdateRow(this.mContext, queryParameter, list);
            } else {
                updateRow = DatabaseHelper.updateRow(this.sqLiteDB, queryParameter, list);
            }
            return this.mGson.toJson(updateRow);
        } catch (Exception e) {
            e.printStackTrace();
            UpdateRowResponse updateRowResponse = new UpdateRowResponse();
            updateRowResponse.isSuccessful = false;
            return this.mGson.toJson(updateRowResponse);
        }
    }

    private String deleteTableDataAndGetResponse(String str) {
        UpdateRowResponse deleteRow;
        try {
            Uri parse = Uri.parse(URLDecoder.decode(str, "UTF-8"));
            String queryParameter = parse.getQueryParameter("tableName");
            List list = (List) this.mGson.fromJson(parse.getQueryParameter("deleteData"), new TypeToken<List<RowDataRequest>>() { // from class: com.amitshekhar.server.RequestHandler.3
            }.getType());
            if (Constants.APP_SHARED_PREFERENCES.equals(this.mSelectedDatabase)) {
                deleteRow = PrefHelper.deleteRow(this.mContext, queryParameter, list);
            } else {
                deleteRow = DatabaseHelper.deleteRow(this.sqLiteDB, queryParameter, list);
            }
            return this.mGson.toJson(deleteRow);
        } catch (Exception e) {
            e.printStackTrace();
            UpdateRowResponse updateRowResponse = new UpdateRowResponse();
            updateRowResponse.isSuccessful = false;
            return this.mGson.toJson(updateRowResponse);
        }
    }

    private String deleteSelectedDatabaseAndGetResponse() {
        UpdateRowResponse updateRowResponse = new UpdateRowResponse();
        String str = this.mSelectedDatabase;
        if (str == null || !this.mDatabaseFiles.containsKey(str)) {
            updateRowResponse.isSuccessful = false;
            return this.mGson.toJson(updateRowResponse);
        }
        try {
            closeDatabase();
            updateRowResponse.isSuccessful = ((File) this.mDatabaseFiles.get(this.mSelectedDatabase).first).delete();
            if (updateRowResponse.isSuccessful) {
                this.mDatabaseFiles.remove(this.mSelectedDatabase);
                this.mCustomDatabaseFiles.remove(this.mSelectedDatabase);
            }
            return this.mGson.toJson(updateRowResponse);
        } catch (Exception e) {
            e.printStackTrace();
            updateRowResponse.isSuccessful = false;
            return this.mGson.toJson(updateRowResponse);
        }
    }
}
