package com.amitshekhar;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import androidx.sqlite.p003db.SupportSQLiteDatabase;
import com.amitshekhar.server.ClientServer;
import com.amitshekhar.sqlite.DBFactory;
import com.amitshekhar.utils.NetworkUtils;
import java.io.File;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DebugDB {
    private static final int DEFAULT_PORT = 8080;
    private static final String TAG = DebugDB.class.getSimpleName();
    private static String addressLog = "not available";
    private static ClientServer clientServer;

    private DebugDB() {
    }

    public static void initialize(Context context, DBFactory dBFactory) {
        int i;
        try {
            i = Integer.valueOf(context.getString(C1326R.string.PORT_NUMBER)).intValue();
        } catch (NumberFormatException e) {
            Log.e(TAG, "PORT_NUMBER should be integer", e);
            i = DEFAULT_PORT;
            Log.i(TAG, "Using Default port : 8080");
        }
        clientServer = new ClientServer(context, i, dBFactory);
        clientServer.start();
        addressLog = NetworkUtils.getAddressLog(context, i);
        Log.d(TAG, addressLog);
    }

    public static String getAddressLog() {
        Log.d(TAG, addressLog);
        return addressLog;
    }

    public static void shutDown() {
        ClientServer clientServer2 = clientServer;
        if (clientServer2 != null) {
            clientServer2.stop();
            clientServer = null;
        }
    }

    public static void setCustomDatabaseFiles(HashMap<String, Pair<File, String>> hashMap) {
        ClientServer clientServer2 = clientServer;
        if (clientServer2 != null) {
            clientServer2.setCustomDatabaseFiles(hashMap);
        }
    }

    public static void setInMemoryRoomDatabases(HashMap<String, SupportSQLiteDatabase> hashMap) {
        ClientServer clientServer2 = clientServer;
        if (clientServer2 != null) {
            clientServer2.setInMemoryRoomDatabases(hashMap);
        }
    }

    public static boolean isServerRunning() {
        ClientServer clientServer2 = clientServer;
        return clientServer2 != null && clientServer2.isRunning();
    }
}
