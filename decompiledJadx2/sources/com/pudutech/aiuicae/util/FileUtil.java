package com.pudutech.aiuicae.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes.dex */
public class FileUtil {
    public static boolean writeFile2End(String str, String str2, String str3) {
        String sDPath = getSDPath();
        if (sDPath == null) {
            return false;
        }
        String str4 = str3 + IOUtils.LINE_SEPARATOR_WINDOWS;
        FileOutputStream fileOutputStream = null;
        try {
            try {
                String str5 = sDPath + '/' + str;
                File file = new File(str5);
                if (!file.exists()) {
                    file.mkdirs();
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(str5 + "/" + str2, true);
                try {
                    fileOutputStream2.write(str4.getBytes());
                    fileOutputStream2.flush();
                    try {
                        fileOutputStream2.close();
                    } catch (IOException unused) {
                    }
                    return true;
                } catch (Exception e) {
                    e = e;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException unused2) {
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException unused3) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public static String getSDPath() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return Environment.getExternalStorageDirectory().getPath();
        }
        return null;
    }

    public static Bitmap getBimmapFromSD(String str) {
        try {
            return BitmapFactory.decodeStream(new FileInputStream(str));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.FileInputStream, java.io.InputStream] */
    public static byte[] getBytesFromFile(String str) {
        Object obj;
        ?? fileInputStream;
        ?? r0 = 0;
        r0 = 0;
        InputStream inputStream = null;
        try {
            try {
                fileInputStream = new FileInputStream(str);
                try {
                    r0 = new byte[fileInputStream.available()];
                    fileInputStream.read(r0);
                } catch (IOException e) {
                    e = e;
                    obj = r0;
                    inputStream = fileInputStream;
                    e.printStackTrace();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException unused) {
                        }
                    }
                    r0 = obj;
                    return r0;
                } catch (Throwable th) {
                    th = th;
                    r0 = fileInputStream;
                    if (r0 != 0) {
                        try {
                            r0.close();
                        } catch (IOException unused2) {
                        }
                    }
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                obj = null;
            }
            try {
                fileInputStream.close();
                r0 = r0;
            } catch (IOException unused3) {
            }
            return r0;
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
