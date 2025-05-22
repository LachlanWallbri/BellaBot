package com.amitshekhar.utils;

import android.content.res.AssetManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class Utils {
    private static final String TAG = "Utils";

    private Utils() {
    }

    public static String detectMimeType(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.endsWith(".html") ? "text/html" : str.endsWith(".js") ? FastJsonJsonView.DEFAULT_JSONP_CONTENT_TYPE : str.endsWith(".css") ? "text/css" : "application/octet-stream";
    }

    public static byte[] loadContent(String str, AssetManager assetManager) throws IOException {
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            inputStream = assetManager.open(str);
        } catch (FileNotFoundException unused) {
            inputStream = null;
        } catch (Throwable th) {
            th = th;
            inputStream = null;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (-1 == read) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byteArrayOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return byteArray;
        } catch (FileNotFoundException unused2) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static byte[] getDatabase(String str, HashMap<String, Pair<File, String>> hashMap) {
        File file;
        byte[] bArr = null;
        if (TextUtils.isEmpty(str) || !hashMap.containsKey(str)) {
            return null;
        }
        byte[] bArr2 = new byte[0];
        try {
            file = (File) hashMap.get(str).first;
        } catch (Exception e) {
            e = e;
            bArr = bArr2;
        }
        try {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr3 = new byte[(int) file.length()];
                while (true) {
                    int read = fileInputStream.read(bArr3);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr3, 0, read);
                    } else {
                        return byteArrayOutputStream.toByteArray();
                    }
                }
            } catch (IOException e2) {
                Log.e(TAG, "getDatabase: ", e2);
                return null;
            }
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            return bArr;
        }
    }
}
