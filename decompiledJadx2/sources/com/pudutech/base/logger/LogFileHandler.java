package com.pudutech.base.logger;

import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class LogFileHandler {
    private static final String DST = "sdcard/PuduLogCache";
    private static final String SRC = "/tmp";
    public static final String TAG = "LogFileHandler";
    private Callback callback;

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* loaded from: classes.dex */
    public interface Callback {
        void onPrintLog(String str, boolean z);
    }

    public LogFileHandler(Callback callback) {
        this.callback = null;
        this.callback = callback;
    }

    private boolean copy(File[] fileArr) throws IOException {
        byte[] bArr;
        int i;
        int i2;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        File[] fileArr2 = fileArr;
        byte[] bArr2 = new byte[1024];
        int length = fileArr2.length;
        long j = 0;
        long j2 = 0;
        int i3 = 0;
        while (i3 < length) {
            File file = fileArr2[i3];
            String name = file.getName();
            print("name=" + name + "    size=" + (file.length() / 1024.0d) + "kB");
            if (file.isFile() && file.length() != j) {
                long length2 = file.length();
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                long availableBlocksLong = statFs.getAvailableBlocksLong();
                i2 = i3;
                long j3 = j2;
                long blockSizeLong = availableBlocksLong * statFs.getBlockSizeLong();
                long j4 = (blockSizeLong / 1024) / 1024;
                StringBuilder sb = new StringBuilder();
                sb.append("sdcard available=");
                sb.append(blockSizeLong);
                sb.append(" ");
                sb.append(j4);
                sb.append("MB blocks=");
                byte[] bArr3 = bArr2;
                sb.append(availableBlocksLong);
                sb.append(" blockSize=");
                sb.append(statFs.getBlockSizeLong());
                print(sb.toString());
                if (blockSizeLong < length2) {
                    print("sdcard available no enough");
                    return false;
                }
                if (j4 < 350) {
                    print("sdcard available must more than 350mb");
                    return false;
                }
                File file2 = new File(DST + File.separator + name);
                if (!file2.exists()) {
                    file2.createNewFile();
                }
                FileInputStream fileInputStream3 = new FileInputStream(file);
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                long elapsedRealtime = SystemClock.elapsedRealtime();
                print("copy 0/" + length2);
                long j5 = length2;
                long j6 = 0;
                while (true) {
                    if (j5 <= j6) {
                        fileInputStream = fileInputStream3;
                        bArr = bArr3;
                        i = length;
                        break;
                    }
                    bArr = bArr3;
                    int read = fileInputStream3.read(bArr);
                    if (read != -1) {
                        long j7 = read;
                        i = length;
                        if (j5 > j7) {
                            fileOutputStream.write(bArr);
                            if (SystemClock.elapsedRealtime() - elapsedRealtime > 100) {
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("copy ");
                                fileInputStream2 = fileInputStream3;
                                sb2.append((length2 - j5) / 1024.0d);
                                sb2.append("kB/all ");
                                sb2.append(length2 / 1024.0d);
                                sb2.append("kB");
                                printWithOverridesLastLine(sb2.toString());
                                elapsedRealtime = SystemClock.elapsedRealtime();
                            } else {
                                fileInputStream2 = fileInputStream3;
                            }
                            j5 -= j7;
                            fileInputStream3 = fileInputStream2;
                            length = i;
                            j6 = 0;
                            bArr3 = bArr;
                        } else {
                            fileInputStream = fileInputStream3;
                            fileOutputStream.write(bArr, 0, (int) j5);
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append("copy ");
                            double d = length2 / 1024.0d;
                            sb3.append(d);
                            sb3.append("kB/all ");
                            sb3.append(d);
                            sb3.append("kB");
                            printWithOverridesLastLine(sb3.toString());
                            j5 = 0;
                            break;
                        }
                    } else {
                        fileInputStream = fileInputStream3;
                        i = length;
                        break;
                    }
                }
                long j8 = j3 + (length2 - j5);
                print("copy done, current has copied " + ((j8 / 1024.0d) / 1024.0d) + "MB");
                fileInputStream.close();
                fileOutputStream.flush();
                fileOutputStream.close();
                j2 = j8;
            } else {
                bArr = bArr2;
                i = length;
                i2 = i3;
            }
            i3 = i2 + 1;
            bArr2 = bArr;
            length = i;
            j = 0;
            fileArr2 = fileArr;
        }
        return true;
    }

    public boolean copyAllToSdcard() throws IOException {
        print("copy all");
        File file = new File(SRC);
        if (!file.exists() || !file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (listFiles.length == 0) {
            return false;
        }
        File file2 = new File(DST);
        if (!file2.exists()) {
            file2.mkdir();
        }
        return copy(listFiles);
    }

    public boolean copy50MB() throws IOException {
        int i;
        String str;
        String str2;
        print("copy least 50MB");
        File file = new File(SRC);
        if (!file.exists() || !file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (listFiles.length == 0) {
            return false;
        }
        File file2 = new File(DST);
        if (!file2.exists()) {
            file2.mkdir();
        }
        int length = listFiles.length;
        int i2 = 0;
        while (true) {
            i = 1;
            if (i2 >= length) {
                break;
            }
            Pdlog.m3273d(TAG, "log files: " + listFiles[i2].getName());
            i2++;
        }
        sort(listFiles);
        int length2 = listFiles.length;
        int i3 = 0;
        while (true) {
            str = "MB";
            if (i3 >= length2) {
                break;
            }
            Pdlog.m3273d(TAG, "sort log files: " + listFiles[i3].getName() + " size " + ((r11.length() / 1024.0d) / 1024.0d) + "MB");
            i3++;
        }
        long j = 0;
        int length3 = listFiles.length - 1;
        int length4 = listFiles.length - 1;
        while (true) {
            if (length4 <= 0) {
                str2 = str;
                break;
            }
            j += listFiles[length4].length();
            Object[] objArr = new Object[i];
            StringBuilder sb = new StringBuilder();
            sb.append("all size ");
            sb.append(j);
            sb.append(" want size ");
            sb.append(52428800L);
            sb.append(" i modified ");
            str2 = str;
            sb.append(listFiles[length4].lastModified());
            objArr[0] = sb.toString();
            Pdlog.m3273d(TAG, objArr);
            int i4 = length4 - 1;
            if (listFiles[length4].lastModified() != listFiles[i4].lastModified() && j > 52428800) {
                break;
            }
            length4--;
            length3 = i4;
            str = str2;
            i = 1;
        }
        int length5 = listFiles.length - length3;
        File[] fileArr = new File[length5];
        System.arraycopy(listFiles, length3, fileArr, 0, length5);
        for (int i5 = 0; i5 < length5; i5++) {
            Pdlog.m3273d(TAG, "want copy file: " + fileArr[i5].getName() + " size " + ((r2.length() / 1024.0d) / 1024.0d) + str2);
        }
        return copy(fileArr);
    }

    public void cleanSDCardLogFile() {
        print("clean SD card log file cache");
        File file = new File(DST);
        if (file.exists() && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                file2.delete();
            }
        }
    }

    private void sort(File[] fileArr) {
        Collections.sort(Arrays.asList(fileArr), new Comparator<File>() { // from class: com.pudutech.base.logger.LogFileHandler.1
            @Override // java.util.Comparator
            public int compare(File file, File file2) {
                if (file.lastModified() == file2.lastModified()) {
                    return 0;
                }
                return file.lastModified() > file2.lastModified() ? 1 : -1;
            }
        });
    }

    private void print(String str) {
        Pdlog.m3273d(TAG, str);
        Callback callback = this.callback;
        if (callback != null) {
            callback.onPrintLog(str, false);
        }
    }

    private void printWithOverridesLastLine(String str) {
        Callback callback = this.callback;
        if (callback != null) {
            callback.onPrintLog(str, true);
        }
    }
}
