package com.pudutech.base;

import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class StringUtils {
    public static final String DEFAULT_SAVE_FILE_PATH = Environment.getExternalStorageDirectory() + File.separator + "Robot" + File.separator + "download" + File.separator;
    private static String savePatchFileName;
    private String saveFileName = DEFAULT_SAVE_FILE_PATH;

    public static String convertToQuotedString(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int length = str.length() - 1;
        if (length < 0) {
            return str;
        }
        if (str.charAt(0) == '\"' && str.charAt(length) == '\"') {
            return str;
        }
        return "\"" + str + "\"";
    }

    public static boolean isEmpty(String str) {
        if (str != null && !"".equals(str)) {
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (charAt != ' ' && charAt != '\t' && charAt != '\r' && charAt != '\n') {
                    return false;
                }
            }
        }
        return true;
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(DEFAULT_SAVE_FILE_PATH);
        sb.append("patch");
        sb.append(File.separator);
        savePatchFileName = sb.toString();
    }

    public static FileInputStream[] getMcuUpdateFile(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        FileInputStream[] fileInputStreamArr = new FileInputStream[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            try {
                fileInputStreamArr[i] = new FileInputStream(new File(savePatchFileName + strArr[i]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return fileInputStreamArr;
    }
}
