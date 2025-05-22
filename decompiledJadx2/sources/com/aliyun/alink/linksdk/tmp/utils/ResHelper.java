package com.aliyun.alink.linksdk.tmp.utils;

import android.content.Context;
import com.aliyun.alink.linksdk.tools.ALog;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ResHelper {
    protected static final String TAG = "[Tmp]ResHelper";

    public static String getRawDeviceModel(Context context, int i) {
        String str;
        InputStream inputStream = null;
        try {
            try {
                inputStream = context.getResources().openRawResource(i);
                int available = inputStream.available();
                byte[] bArr = new byte[available];
                inputStream.read(bArr);
                str = new String(bArr, 0, available, "UTF-8");
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                str = "";
            }
            return str;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0070 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String getDataFileData(Context context, String str) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        if (context == null) {
            ALog.m479d(TAG, "getDataFileData context empty");
            return null;
        }
        File externalCacheDir = context.getExternalCacheDir();
        if (externalCacheDir == null) {
            ALog.m479d(TAG, "getDataFileData cacheDir empty");
            return null;
        }
        ALog.m479d(TAG, "getDataFileData cacheDir:" + externalCacheDir.getAbsolutePath());
        try {
            fileInputStream = new FileInputStream(new File(externalCacheDir.getAbsolutePath(), str));
        } catch (Exception e) {
            e = e;
            fileInputStream = null;
        } catch (Throwable th) {
            th = th;
            if (fileInputStream2 != null) {
            }
            throw th;
        }
        try {
            try {
                int available = fileInputStream.available();
                byte[] bArr = new byte[available];
                fileInputStream.read(bArr, 0, available);
                String str2 = new String(bArr, 0, available, "UTF-8");
                try {
                    fileInputStream.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                return str2;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            e.printStackTrace();
            if (fileInputStream == null) {
                return null;
            }
            try {
                fileInputStream.close();
                return null;
            } catch (Exception e5) {
                e5.printStackTrace();
                return null;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r0v7 */
    public static byte[] getFileByte(File file) {
        byte[] bArr;
        ?? r0 = 0;
        byte[] bArr2 = null;
        InputStream inputStream = null;
        try {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    int available = fileInputStream.available();
                    bArr2 = new byte[available];
                    fileInputStream.read(bArr2, 0, available);
                    try {
                        fileInputStream.close();
                        r0 = bArr2;
                    } catch (Exception e) {
                        e.printStackTrace();
                        r0 = bArr2;
                    }
                } catch (Exception e2) {
                    e = e2;
                    bArr = bArr2;
                    inputStream = fileInputStream;
                    e.printStackTrace();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                    r0 = bArr;
                    return r0;
                } catch (Throwable th) {
                    th = th;
                    r0 = fileInputStream;
                    if (r0 != 0) {
                        try {
                            r0.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
                bArr = null;
            }
            return r0;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String getFileStr(File file) {
        try {
            return new String(getFileByte(file), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
