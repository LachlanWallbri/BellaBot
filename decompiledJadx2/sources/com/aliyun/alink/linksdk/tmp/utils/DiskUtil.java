package com.aliyun.alink.linksdk.tmp.utils;

import android.content.Context;
import android.os.Environment;
import com.aliyun.alink.linksdk.tools.ALog;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DiskUtil {
    private static final String TAG = "[Tmp]DiskUtil";

    public static File getDiskCacheDirWithAppend(Context context, String str) {
        return new File(getDiskCacheDir(context) + File.separator + str);
    }

    public static String getDiskCacheDir(Context context) {
        if ("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            return context.getExternalCacheDir().getPath();
        }
        return context.getCacheDir().getPath();
    }

    public static String readFully(Reader reader) throws IOException {
        try {
            StringWriter stringWriter = new StringWriter();
            char[] cArr = new char[1024];
            while (true) {
                int read = reader.read(cArr);
                if (read != -1) {
                    stringWriter.write(cArr, 0, read);
                } else {
                    return stringWriter.toString();
                }
            }
        } finally {
            reader.close();
        }
    }

    public static String readFully(InputStream inputStream) {
        try {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                String byteArrayOutputStream2 = byteArrayOutputStream.toString("UTF-8");
                try {
                    inputStream.close();
                } catch (Exception e) {
                    ALog.m480e(TAG, "readFully close:" + e.toString());
                }
                return byteArrayOutputStream2;
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (Exception e2) {
                    ALog.m480e(TAG, "readFully close:" + e2.toString());
                }
                throw th;
            }
        } catch (Exception e3) {
            ALog.m480e(TAG, "readFully InputStream:" + e3.toString());
            try {
                inputStream.close();
                return null;
            } catch (Exception e4) {
                ALog.m480e(TAG, "readFully close:" + e4.toString());
                return null;
            }
        }
    }
}
