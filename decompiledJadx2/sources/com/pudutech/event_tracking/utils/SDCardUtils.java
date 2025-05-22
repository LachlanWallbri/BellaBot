package com.pudutech.event_tracking.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SDCardUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001eB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u0010\u0010\u0011\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u000e\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0004J\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0004J\u0010\u0010\u001a\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u0010\u0010\u001b\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u000e\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0010\u0010\u001d\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\bR\u0013\u0010\t\u001a\u0004\u0018\u00010\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/event_tracking/utils/SDCardUtils;", "", "()V", "EXTERNAL_STORAGE", "", "INTERNAL_STORAGE", "isSDCardMount", "", "()Z", "sDCardInfo", "Lcom/pudutech/event_tracking/utils/SDCardUtils$SDCardInfo;", "getSDCardInfo", "()Lcom/pudutech/event_tracking/utils/SDCardUtils$SDCardInfo;", "getAvailableExternalMemorySize", "", "context", "Landroid/content/Context;", "getAvailableInternalMemorySize", "getAvailableRAM", "getRAMInfo", "getSDCardTotalStorage", "totalByte", "", "getStorageInfo", "type", "getStoragePath", "getTotalExternalMemorySize", "getTotalInternalMemorySize", "getTotalRAM", "getTotalRAMOther", "SDCardInfo", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SDCardUtils {
    private static final int EXTERNAL_STORAGE = 1;
    public static final SDCardUtils INSTANCE = new SDCardUtils();
    private static final int INTERNAL_STORAGE = 0;

    private SDCardUtils() {
    }

    public final String getRAMInfo(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("activity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) systemService).getMemoryInfo(memoryInfo);
        long j = memoryInfo.totalMem;
        return "可用/总共：" + Formatter.formatFileSize(context, memoryInfo.availMem) + "/" + Formatter.formatFileSize(context, j);
    }

    public final boolean isSDCardMount() {
        return Intrinsics.areEqual(Environment.getExternalStorageState(), "mounted");
    }

    public final String getStorageInfo(Context context, int type) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        String storagePath = getStoragePath(context, type);
        if (!isSDCardMount() || TextUtils.isEmpty(storagePath) || storagePath == null) {
            return "无外置SD卡";
        }
        StatFs statFs = new StatFs(new File(storagePath).getPath());
        long blockCountLong = statFs.getBlockCountLong();
        long blockSizeLong = statFs.getBlockSizeLong();
        return "可用/总共：" + Formatter.formatFileSize(context, statFs.getAvailableBlocksLong() * blockSizeLong) + "/" + Formatter.formatFileSize(context, blockCountLong * blockSizeLong);
    }

    public final String getStoragePath(Context context, int type) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("storage");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.os.storage.StorageManager");
        }
        StorageManager storageManager = (StorageManager) systemService;
        try {
            Object invoke = storageManager.getClass().getMethod("getVolumePaths", (Class) null).invoke(storageManager, null);
            if (invoke == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<kotlin.String>");
            }
            String[] strArr = (String[]) invoke;
            if (type == 0) {
                return strArr[type];
            }
            if (type == 1 && strArr.length > 1) {
                return strArr[type];
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public final String getTotalRAM(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("activity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) systemService).getMemoryInfo(memoryInfo);
        String formatFileSize = Formatter.formatFileSize(context, memoryInfo.totalMem);
        Intrinsics.checkExpressionValueIsNotNull(formatFileSize, "Formatter.formatFileSize(context, size)");
        return formatFileSize;
    }

    public final String getTotalRAMOther(Context context) {
        BufferedReader bufferedReader;
        Object[] array;
        String str = (String) null;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            String readLine = bufferedReader.readLine();
            Intrinsics.checkExpressionValueIsNotNull(readLine, "br.readLine()");
            array = StringsKt.split$default((CharSequence) readLine, new String[]{"\\s+"}, false, 0, 6, (Object) null).toArray(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (array != null) {
            str = ((String[]) array)[1];
            bufferedReader.close();
            String formatFileSize = Formatter.formatFileSize(context, str != null ? (int) Math.ceil(Float.valueOf(str).floatValue() / 1048576) : 0);
            Intrinsics.checkExpressionValueIsNotNull(formatFileSize, "Formatter.formatFileSize…ntext, totalRam.toLong())");
            return formatFileSize;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public final String getAvailableRAM(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("activity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) systemService).getMemoryInfo(memoryInfo);
        String formatFileSize = Formatter.formatFileSize(context, memoryInfo.availMem);
        Intrinsics.checkExpressionValueIsNotNull(formatFileSize, "Formatter.formatFileSize(context, size)");
        return formatFileSize;
    }

    public final String getTotalInternalMemorySize(Context context) {
        File file = Environment.getDataDirectory();
        Intrinsics.checkExpressionValueIsNotNull(file, "file");
        StatFs statFs = new StatFs(file.getPath());
        String formatFileSize = Formatter.formatFileSize(context, statFs.getBlockCountLong() * statFs.getBlockSizeLong());
        Intrinsics.checkExpressionValueIsNotNull(formatFileSize, "Formatter.formatFileSize(context, size)");
        return formatFileSize;
    }

    public final String getAvailableInternalMemorySize(Context context) {
        File file = Environment.getDataDirectory();
        Intrinsics.checkExpressionValueIsNotNull(file, "file");
        StatFs statFs = new StatFs(file.getPath());
        String formatFileSize = Formatter.formatFileSize(context, statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong());
        Intrinsics.checkExpressionValueIsNotNull(formatFileSize, "Formatter.formatFileSize…ocksLong * blockSizeLong)");
        return formatFileSize;
    }

    public final String getTotalExternalMemorySize(Context context) {
        File file = Environment.getExternalStorageDirectory();
        Intrinsics.checkExpressionValueIsNotNull(file, "file");
        StatFs statFs = new StatFs(file.getPath());
        String formatFileSize = Formatter.formatFileSize(context, statFs.getBlockCountLong() * statFs.getBlockSizeLong());
        Intrinsics.checkExpressionValueIsNotNull(formatFileSize, "Formatter.formatFileSize…ountLong * blockSizeLong)");
        return formatFileSize;
    }

    public final String getAvailableExternalMemorySize(Context context) {
        File file = Environment.getExternalStorageDirectory();
        Intrinsics.checkExpressionValueIsNotNull(file, "file");
        StatFs statFs = new StatFs(file.getPath());
        String formatFileSize = Formatter.formatFileSize(context, statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong());
        Intrinsics.checkExpressionValueIsNotNull(formatFileSize, "Formatter.formatFileSize…ocksLong * blockSizeLong)");
        return formatFileSize;
    }

    public final SDCardInfo getSDCardInfo() {
        SDCardInfo sDCardInfo = new SDCardInfo();
        if (!isSDCardMount()) {
            return null;
        }
        sDCardInfo.setExist(true);
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        Intrinsics.checkExpressionValueIsNotNull(externalStorageDirectory, "Environment.getExternalStorageDirectory()");
        StatFs statFs = new StatFs(externalStorageDirectory.getPath());
        sDCardInfo.setTotalBlocks(statFs.getBlockCountLong());
        sDCardInfo.setBlockByteSize(statFs.getBlockSizeLong());
        sDCardInfo.setAvailableBlocks(statFs.getAvailableBlocksLong());
        sDCardInfo.setAvailableBytes(statFs.getAvailableBytes());
        sDCardInfo.setFreeBlocks(statFs.getFreeBlocksLong());
        sDCardInfo.setFreeBytes(statFs.getFreeBytes());
        sDCardInfo.setTotalBytes(statFs.getTotalBytes());
        return sDCardInfo;
    }

    public final String getSDCardTotalStorage(long totalByte) {
        double d = (totalByte / 1024.0d) / 1024.0d;
        double d2 = d / 1024.0d;
        double d3 = 1;
        if (d2 > d3) {
            d = Math.ceil(d2);
            if (d > d3 && d < 3) {
                return "2.0GB";
            }
            if (d > 2 && d < 5) {
                return "4.0GB";
            }
            if (d >= 5 && d < 10) {
                return "8.0GB";
            }
            if (d >= 10 && d < 18) {
                return "16.0GB";
            }
            if (d >= 18 && d < 34) {
                return "32.0GB";
            }
            if (d >= 34 && d < 50) {
                return "48.0GB";
            }
            if (d >= 50 && d < 66) {
                return "64.0GB";
            }
            if (d >= 66 && d < 130) {
                return "128.0GB";
            }
        } else {
            double d4 = 515;
            if (d >= d4 && d < 1024) {
                return "1GB";
            }
            double d5 = 260;
            if (d >= d5 && d < d4) {
                return "512MB";
            }
            double d6 = 130;
            if (d >= d6 && d < d5) {
                return "256MB";
            }
            double d7 = 70;
            if (d > d7 && d < d6) {
                return "128MB";
            }
            if (d > 50 && d < d7) {
                return "64MB";
            }
        }
        return String.valueOf(d) + "GB";
    }

    /* compiled from: SDCardUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010 \u001a\u00020!H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0006\"\u0004\b\u001c\u0010\bR\u001a\u0010\u001d\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0006\"\u0004\b\u001f\u0010\b¨\u0006\""}, m3961d2 = {"Lcom/pudutech/event_tracking/utils/SDCardUtils$SDCardInfo;", "", "()V", "availableBlocks", "", "getAvailableBlocks", "()J", "setAvailableBlocks", "(J)V", "availableBytes", "getAvailableBytes", "setAvailableBytes", "blockByteSize", "getBlockByteSize", "setBlockByteSize", "freeBlocks", "getFreeBlocks", "setFreeBlocks", "freeBytes", "getFreeBytes", "setFreeBytes", "isExist", "", "()Z", "setExist", "(Z)V", "totalBlocks", "getTotalBlocks", "setTotalBlocks", "totalBytes", "getTotalBytes", "setTotalBytes", "toString", "", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class SDCardInfo {
        private long availableBlocks;
        private long availableBytes;
        private long blockByteSize;
        private long freeBlocks;
        private long freeBytes;
        private boolean isExist;
        private long totalBlocks;
        private long totalBytes;

        /* renamed from: isExist, reason: from getter */
        public final boolean getIsExist() {
            return this.isExist;
        }

        public final void setExist(boolean z) {
            this.isExist = z;
        }

        public final long getTotalBlocks() {
            return this.totalBlocks;
        }

        public final void setTotalBlocks(long j) {
            this.totalBlocks = j;
        }

        public final long getFreeBlocks() {
            return this.freeBlocks;
        }

        public final void setFreeBlocks(long j) {
            this.freeBlocks = j;
        }

        public final long getAvailableBlocks() {
            return this.availableBlocks;
        }

        public final void setAvailableBlocks(long j) {
            this.availableBlocks = j;
        }

        public final long getBlockByteSize() {
            return this.blockByteSize;
        }

        public final void setBlockByteSize(long j) {
            this.blockByteSize = j;
        }

        public final long getTotalBytes() {
            return this.totalBytes;
        }

        public final void setTotalBytes(long j) {
            this.totalBytes = j;
        }

        public final long getFreeBytes() {
            return this.freeBytes;
        }

        public final void setFreeBytes(long j) {
            this.freeBytes = j;
        }

        public final long getAvailableBytes() {
            return this.availableBytes;
        }

        public final void setAvailableBytes(long j) {
            this.availableBytes = j;
        }

        public String toString() {
            return "isExist=" + this.isExist + "\ntotalBlocks=" + this.totalBlocks + "\nfreeBlocks=" + this.freeBlocks + "\navailableBlocks=" + this.availableBlocks + "\nblockByteSize=" + this.blockByteSize + "\ntotalBytes=" + this.totalBytes + "\nfreeBytes=" + this.freeBytes + "\navailableBytes=" + this.availableBytes;
        }
    }
}
