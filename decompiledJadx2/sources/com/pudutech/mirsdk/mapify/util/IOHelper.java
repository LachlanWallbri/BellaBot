package com.pudutech.mirsdk.mapify.util;

import android.content.Context;
import android.graphics.Bitmap;
import com.pudutech.base.FileInfo;
import com.pudutech.base.Pdlog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes5.dex */
public class IOHelper {
    private static final String TAG = "IOHelper";

    /* JADX WARN: Removed duplicated region for block: B:47:0x0086 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0081 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean copyFileFromAssets(Context context, String str, String str2, boolean z) {
        FileOutputStream fileOutputStream;
        Throwable th;
        InputStream inputStream;
        Exception e;
        File file = new File(str2);
        Pdlog.m3275i(TAG, String.format("copyFileFromAssets, dest %s exist? %s , override %s", str2, Boolean.valueOf(file.exists()), Boolean.valueOf(z)));
        if (file.exists() && !z) {
            return true;
        }
        try {
            inputStream = context.getAssets().open(str);
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (Exception e2) {
                e = e2;
                fileOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
                if (inputStream != null) {
                }
                if (fileOutputStream == null) {
                }
            }
        } catch (Exception e3) {
            fileOutputStream = null;
            e = e3;
            inputStream = null;
        } catch (Throwable th3) {
            fileOutputStream = null;
            th = th3;
            inputStream = null;
        }
        try {
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr, 0, 1024);
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                    }
                }
                try {
                    fileOutputStream.close();
                } catch (IOException unused2) {
                }
                return true;
            } catch (Throwable th4) {
                th = th4;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                if (fileOutputStream == null) {
                    try {
                        fileOutputStream.close();
                        throw th;
                    } catch (IOException unused4) {
                        throw th;
                    }
                }
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            e.printStackTrace();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused5) {
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused6) {
                }
            }
            return false;
        }
    }

    public static void copy(File file, File file2) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read > 0) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        fileOutputStream.close();
                        fileInputStream.close();
                        return;
                    }
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    fileInputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public static void ensureDirExists(String str) {
        File file = new File(str);
        if (file.exists() && !file.isDirectory()) {
            Pdlog.m3274e(TAG, String.format("%s should be a dir but a file", new Object[0]));
            throw new IllegalStateException("");
        }
        if (file.exists() || file.mkdirs()) {
            return;
        }
        String format = String.format("%s not exists and cannot mkdirs", str);
        Pdlog.m3274e(TAG, format);
        throw new IllegalStateException(format);
    }

    public static boolean saveImage(Bitmap bitmap, File file) {
        ensureDirExists(file.getParent());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                boolean compress = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                if (!compress) {
                    Pdlog.m3277w(TAG, "calling Bitmap.compress() return false");
                }
                fileOutputStream.close();
                return compress;
            } finally {
            }
        } catch (IOException e) {
            Pdlog.m3274e(TAG, String.format("save image to %s failed", file), e);
            return false;
        }
    }

    public static int imageCount(File file) {
        int i = 0;
        for (String str : file.list()) {
            if (str.endsWith(FileInfo.EXTEND_JPG)) {
                i++;
            }
        }
        return i;
    }

    public static void cleanDirectory(File file) throws IOException {
        IOException e = null;
        for (File file2 : verifiedListFiles(file)) {
            try {
                forceDelete(file2);
            } catch (IOException e2) {
                e = e2;
            }
        }
        if (e != null) {
            throw e;
        }
    }

    private static File[] verifiedListFiles(File file) throws IOException {
        if (!file.exists()) {
            throw new IllegalArgumentException(file + " does not exist");
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(file + " is not a directory");
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            return listFiles;
        }
        throw new IOException("Failed to list contents of " + file);
    }

    public static void forceDelete(File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectory(file);
            return;
        }
        boolean exists = file.exists();
        if (file.delete()) {
            return;
        }
        if (!exists) {
            throw new FileNotFoundException("File does not exist: " + file);
        }
        throw new IOException("Unable to delete file: " + file);
    }

    public static void deleteDirectory(File file) throws IOException {
        if (file.exists()) {
            if (!isSymlink(file)) {
                cleanDirectory(file);
            }
            if (file.delete()) {
                return;
            }
            throw new IOException("Unable to delete directory " + file + ".");
        }
    }

    public static boolean isSymlink(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException("File must not be null");
        }
        if (file.getParent() != null) {
            file = new File(file.getParentFile().getCanonicalFile(), file.getName());
        }
        return !file.getCanonicalFile().equals(file.getAbsoluteFile());
    }
}
