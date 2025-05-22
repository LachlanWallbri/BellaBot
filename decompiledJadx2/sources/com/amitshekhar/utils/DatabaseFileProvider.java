package com.amitshekhar.utils;

import android.content.Context;
import android.util.Pair;
import java.io.File;
import java.text.MessageFormat;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DatabaseFileProvider {
    private static final String DB_PASSWORD_RESOURCE = "DB_PASSWORD_{0}";

    private DatabaseFileProvider() {
    }

    public static HashMap<String, Pair<File, String>> getDatabaseFiles(Context context) {
        HashMap<String, Pair<File, String>> hashMap = new HashMap<>();
        try {
            for (String str : context.databaseList()) {
                hashMap.put(str, new Pair<>(context.getDatabasePath(str), getDbPasswordFromStringResources(context, str)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    private static String getDbPasswordFromStringResources(Context context, String str) {
        if (str.endsWith(".db")) {
            str = str.substring(0, str.lastIndexOf(46));
        }
        int identifier = context.getResources().getIdentifier(MessageFormat.format(DB_PASSWORD_RESOURCE, str.toUpperCase()), "string", context.getPackageName());
        return identifier != 0 ? context.getString(identifier) : "";
    }
}
