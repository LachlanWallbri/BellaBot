package com.pudutech.sysstoragetempuse.lib;

import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SysStorageTempUseTool.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0010B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/sysstoragetempuse/lib/SysStorageTempUseTool;", "", "()V", "TAG", "", "filePath", "fos", "Ljava/io/FileOutputStream;", "isRun", "", "tempUseSize", "", "thread", "Ljava/lang/Thread;", "init", "", "FileUseRunner", "sys_storage_temp_use"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class SysStorageTempUseTool {
    private static final String filePath = "/sdcard/tempuse.txt";
    private static FileOutputStream fos = null;
    private static boolean isRun = false;
    private static final long tempUseSize = 131072000;
    private static Thread thread;
    public static final SysStorageTempUseTool INSTANCE = new SysStorageTempUseTool();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private SysStorageTempUseTool() {
    }

    public static final /* synthetic */ FileOutputStream access$getFos$p(SysStorageTempUseTool sysStorageTempUseTool) {
        return fos;
    }

    public static final /* synthetic */ String access$getTAG$p(SysStorageTempUseTool sysStorageTempUseTool) {
        return TAG;
    }

    public final void init() {
        if (isRun) {
            return;
        }
        isRun = true;
        thread = new Thread(new FileUseRunner(), TAG);
        Thread thread2 = thread;
        if (thread2 != null) {
            thread2.start();
        }
    }

    /* compiled from: SysStorageTempUseTool.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/sysstoragetempuse/lib/SysStorageTempUseTool$FileUseRunner;", "Ljava/lang/Runnable;", "()V", "getFileSize", "", "getLeftExternalStorage", "run", "", "sys_storage_temp_use"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final class FileUseRunner implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            long fileSize;
            long j;
            File file = new File(SysStorageTempUseTool.filePath);
            try {
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    fileSize = getFileSize();
                    j = 0;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (fileSize < 0) {
                    Log.e(SysStorageTempUseTool.access$getTAG$p(SysStorageTempUseTool.INSTANCE), "getFileSize : fSize is wrong " + fileSize);
                    return;
                }
                long j2 = fileSize / 1048576;
                if (j2 < 0) {
                    Log.e(SysStorageTempUseTool.access$getTAG$p(SysStorageTempUseTool.INSTANCE), "index :  " + j2);
                    return;
                }
                Log.d(SysStorageTempUseTool.access$getTAG$p(SysStorageTempUseTool.INSTANCE), "start write " + fileSize);
                SysStorageTempUseTool sysStorageTempUseTool = SysStorageTempUseTool.INSTANCE;
                SysStorageTempUseTool.fos = new FileOutputStream(file);
                FileOutputStream access$getFos$p = SysStorageTempUseTool.access$getFos$p(SysStorageTempUseTool.INSTANCE);
                FileChannel channel = access$getFos$p != null ? access$getFos$p.getChannel() : null;
                if (0 <= j2) {
                    while (true) {
                        ByteBuffer allocate = ByteBuffer.allocate(1048576);
                        if (channel != null) {
                            channel.write(allocate);
                        }
                        if (j == j2) {
                            break;
                        } else {
                            j++;
                        }
                    }
                }
                String access$getTAG$p = SysStorageTempUseTool.access$getTAG$p(SysStorageTempUseTool.INSTANCE);
                StringBuilder sb = new StringBuilder();
                sb.append("temp file write success ");
                long j3 = 1024;
                sb.append((fileSize / j3) / j3);
                sb.append("mb");
                Log.d(access$getTAG$p, sb.toString());
            } finally {
                Tools.execCommand("rm -rf /sdcard/tempuse.txt", false);
            }
        }

        private final long getFileSize() {
            long leftExternalStorage = getLeftExternalStorage();
            if (leftExternalStorage <= 0) {
                Log.e(SysStorageTempUseTool.access$getTAG$p(SysStorageTempUseTool.INSTANCE), "getFileSize : getLeftExternalStorage  size is fail ?? = " + leftExternalStorage);
                return -1L;
            }
            if (leftExternalStorage > SysStorageTempUseTool.tempUseSize) {
                String access$getTAG$p = SysStorageTempUseTool.access$getTAG$p(SysStorageTempUseTool.INSTANCE);
                StringBuilder sb = new StringBuilder();
                sb.append("getFileSize : left size = ");
                long j = 1024;
                sb.append((leftExternalStorage / j) / j);
                Log.d(access$getTAG$p, sb.toString());
                return SysStorageTempUseTool.tempUseSize;
            }
            Log.w(SysStorageTempUseTool.access$getTAG$p(SysStorageTempUseTool.INSTANCE), "getFileSize : LeftExternalStorage too small " + leftExternalStorage + " - 131072000");
            return (long) (leftExternalStorage * 0.95d);
        }

        private final long getLeftExternalStorage() {
            String externalStorageState = Environment.getExternalStorageState();
            File file = Environment.getExternalStorageDirectory();
            Intrinsics.checkExpressionValueIsNotNull(file, "file");
            StatFs statFs = new StatFs(file.getPath());
            if (Intrinsics.areEqual(externalStorageState, "mounted")) {
                return statFs.getAvailableBytes();
            }
            return -1L;
        }
    }
}
