package com.iflytek.cloud.msc.util;

import android.content.Context;
import android.os.Environment;
import android.os.MemoryFile;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.record.C3684d;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes3.dex */
public class FileUtil {
    private static final String CHILD_FOLDER_NAME = "res";
    private static final String FOLDER_NAME = "msc";
    private static final String FORMAT_PCM = "pcm";
    private static final String FORMAT_WAV = "wav";
    public static final String RES_SUFFIX = ".jet";
    private static String userPath = "";

    public static String getUserPath(Context context) {
        if (!TextUtils.isEmpty(userPath)) {
            return userPath;
        }
        String absolutePath = context.getFilesDir().getAbsolutePath();
        if (!absolutePath.endsWith("/")) {
            absolutePath = absolutePath + "/";
        }
        String str = absolutePath + "msclib/";
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        userPath = str;
        return userPath;
    }

    public static String getResFilePath(Context context, String str) {
        String absolutePath;
        if (TextUtils.isEmpty(str)) {
            str = System.currentTimeMillis() + "";
        }
        if ("mounted".equals(Environment.getExternalStorageState())) {
            absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            absolutePath = context.getFilesDir().getAbsolutePath();
        }
        if (!absolutePath.endsWith("/")) {
            absolutePath = absolutePath + "/";
        }
        String str2 = (absolutePath + "msc/") + "res/";
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        String str3 = str2 + str;
        if (str3.endsWith(RES_SUFFIX)) {
            return str3;
        }
        return str3 + RES_SUFFIX;
    }

    public static void deleteFile(String str) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
    }

    public static boolean saveFile(MemoryFile memoryFile, long j, String str) {
        boolean z = false;
        if (memoryFile == null || TextUtils.isEmpty(str)) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    deleteFile(str);
                    makeDir(str);
                    FileOutputStream fileOutputStream2 = new FileOutputStream(str);
                    try {
                        byte[] bArr = new byte[65536];
                        int i = 0;
                        while (true) {
                            long j2 = i;
                            if (j2 >= j) {
                                break;
                            }
                            long j3 = j - j2;
                            if (j3 > PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                                j3 = 65536;
                            }
                            int i2 = (int) j3;
                            memoryFile.readBytes(bArr, i, 0, i2);
                            fileOutputStream2.write(bArr, 0, i2);
                            i += i2;
                        }
                        z = true;
                        fileOutputStream2.close();
                    } catch (Exception e) {
                        e = e;
                        fileOutputStream = fileOutputStream2;
                        DebugLog.LogE(e);
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        return z;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception unused) {
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
        } catch (Exception unused2) {
        }
        return z;
    }

    public static byte[] readFile(String str) {
        byte[] bArr;
        FileInputStream fileInputStream = null;
        byte[] bArr2 = null;
        fileInputStream = null;
        try {
            try {
                File file = new File(str);
                if (!file.exists()) {
                    return null;
                }
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    bArr2 = new byte[fileInputStream2.available()];
                    fileInputStream2.read(bArr2);
                    try {
                        fileInputStream2.close();
                    } catch (IOException e) {
                        DebugLog.LogE(e);
                    }
                    return bArr2;
                } catch (Exception e2) {
                    e = e2;
                    byte[] bArr3 = bArr2;
                    fileInputStream = fileInputStream2;
                    bArr = bArr3;
                    DebugLog.LogE(e);
                    if (fileInputStream == null) {
                        return bArr;
                    }
                    try {
                        fileInputStream.close();
                        return bArr;
                    } catch (IOException e3) {
                        DebugLog.LogE(e3);
                        return bArr;
                    }
                } catch (Throwable th) {
                    fileInputStream = fileInputStream2;
                    th = th;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e4) {
                            DebugLog.LogE(e4);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e5) {
            e = e5;
            bArr = null;
        }
    }

    public static byte[] readFileFromAssets(Context context, String str) {
        byte[] bArr;
        InputStream inputStream;
        InputStream inputStream2 = null;
        byte[] bArr2 = null;
        InputStream inputStream3 = null;
        try {
            try {
                inputStream = context.getAssets().open(str);
                try {
                    bArr2 = new byte[inputStream.available()];
                    inputStream.read(bArr2);
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            DebugLog.LogE(e);
                        }
                    }
                    bArr = bArr2;
                    inputStream2 = bArr2;
                } catch (Exception e2) {
                    e = e2;
                    byte[] bArr3 = bArr2;
                    inputStream3 = inputStream;
                    bArr = bArr3;
                    DebugLog.LogE(e);
                    inputStream2 = inputStream3;
                    if (inputStream3 != null) {
                        try {
                            inputStream3.close();
                            inputStream2 = inputStream3;
                        } catch (IOException e3) {
                            DebugLog.LogE(e3);
                            inputStream2 = inputStream3;
                        }
                    }
                    return bArr;
                } catch (Throwable th) {
                    th = th;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e4) {
                            DebugLog.LogE(e4);
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
                bArr = null;
            }
            return bArr;
        } catch (Throwable th2) {
            th = th2;
            inputStream = inputStream2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean saveFile(byte[] bArr, String str, boolean z, int i) {
        Exception e;
        RandomAccessFile randomAccessFile = null;
        try {
            if (!z) {
                try {
                    deleteFile(str);
                } catch (Exception e2) {
                    e = e2;
                    DebugLog.LogE(e);
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (Exception unused) {
                        }
                    }
                    return false;
                }
            }
            makeDir(str);
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(str, "rw");
            try {
                if (!z) {
                    randomAccessFile2.setLength(0L);
                } else if (i < 0) {
                    randomAccessFile2.seek(randomAccessFile2.length());
                } else {
                    randomAccessFile2.seek(i);
                }
                randomAccessFile2.write(bArr);
                try {
                    randomAccessFile2.close();
                    return true;
                } catch (Exception unused2) {
                    return true;
                }
            } catch (Exception e3) {
                e = e3;
                randomAccessFile = randomAccessFile2;
                DebugLog.LogE(e);
                if (randomAccessFile != null) {
                }
                return false;
            } catch (Throwable th) {
                th = th;
                randomAccessFile = randomAccessFile2;
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (Exception unused3) {
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean saveFile(ConcurrentLinkedQueue<byte[]> concurrentLinkedQueue, String str) {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                makeDir(str);
                fileOutputStream = new FileOutputStream(str);
                try {
                    Iterator<byte[]> it = concurrentLinkedQueue.iterator();
                    while (it.hasNext()) {
                        fileOutputStream.write(it.next());
                    }
                    fileOutputStream.close();
                    return true;
                } catch (Exception e) {
                    e = e;
                    fileOutputStream2 = fileOutputStream;
                    DebugLog.LogE(e);
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (Exception e2) {
                            DebugLog.LogE(e2);
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e3) {
                            DebugLog.LogE(e3);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = fileOutputStream2;
            }
        } catch (Exception e4) {
            e = e4;
        }
    }

    public static boolean pcm2Wav(String str, int i) {
        try {
            C3684d c3684d = new C3684d(new File(str), i);
            c3684d.m1756b();
            c3684d.m1757c();
            return true;
        } catch (IOException e) {
            DebugLog.LogE(e);
            return false;
        }
    }

    public static boolean formatPcm(String str, String str2, int i) {
        if (TextUtils.isEmpty(str) || FORMAT_PCM.equals(str)) {
            return true;
        }
        if (FORMAT_WAV.equals(str)) {
            return pcm2Wav(str2, i);
        }
        DebugLog.LogE("format not supported");
        return false;
    }

    public static void makeDir(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        File file = new File(str);
        if (!str.endsWith("/")) {
            file = file.getParentFile();
        }
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    public static void copyAssetsFile(Context context, String str, String str2) throws IOException {
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            inputStream = context.getAssets().open(str);
            try {
                makeDir(str2);
                fileOutputStream = new FileOutputStream(str2, false);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
        try {
            byte[] bArr = new byte[2048];
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    break;
                } else {
                    fileOutputStream.write(bArr, 0, read);
                }
            }
            if (inputStream != null) {
                inputStream.close();
            }
            fileOutputStream.close();
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream2 = fileOutputStream;
            if (inputStream != null) {
                inputStream.close();
            }
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw th;
        }
    }

    public static boolean checkFileMD5(String str, String str2) {
        if (str.equals(Encrypter.getFileMd5(new File(str2)))) {
            return true;
        }
        deleteFile(str2);
        return false;
    }
}
