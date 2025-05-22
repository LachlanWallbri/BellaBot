package com.pudutech.bumblebee.presenter.map_switch_task;

import android.util.Log;
import com.danikula.videocache.file.TotalSizeLruDiskUsage;
import com.pudutech.base.Pdlog;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: BackupMapManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004J\u0018\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/map_switch_task/BackupMapManager;", "", "()V", "BACKUP_PATH", "", "MAX_SIZE", "", "TAG", "formatter", "Ljava/text/DateFormat;", "totalCountLruDiskUsage", "Lcom/danikula/videocache/file/TotalSizeLruDiskUsage;", "backup", "", "file", "Ljava/io/File;", "createTarget", "path", "name", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class BackupMapManager {
    private static final String BACKUP_PATH = "sdcard/pudu/decoration/backup_map";
    private static final String TAG = "BackupMapManager";
    public static final BackupMapManager INSTANCE = new BackupMapManager();
    private static final long MAX_SIZE = 104857600;
    private static final TotalSizeLruDiskUsage totalCountLruDiskUsage = new TotalSizeLruDiskUsage(MAX_SIZE);
    private static final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    private BackupMapManager() {
    }

    public final synchronized void backup(File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        Pdlog.m3273d(TAG, "backup file: " + file);
        if (file.exists() && file.isFile()) {
            String name = file.getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "file.name");
            File createTarget = createTarget(BACKUP_PATH, name);
            Pdlog.m3273d(TAG, "backup targetFile: " + createTarget.getAbsolutePath());
            FilesKt.copyTo$default(file, createTarget, true, 0, 4, null);
            totalCountLruDiskUsage.touch(createTarget);
        }
    }

    public final synchronized void backup(String file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        backup(new File(file));
    }

    private final File createTarget(String path, String name) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        File[] listFiles = file.listFiles();
        int i = 0;
        Pdlog.m3273d(TAG, "createTarget parent:" + file.getAbsolutePath() + ' ');
        if (listFiles != null) {
            int i2 = 0;
            for (File it : listFiles) {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append("createTarget child:");
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    sb.append(it.getAbsolutePath());
                    sb.append(' ');
                    Pdlog.m3273d(TAG, sb.toString());
                    String name2 = it.getName();
                    Intrinsics.checkExpressionValueIsNotNull(name2, "it.name");
                    int parseInt = Integer.parseInt(StringsKt.substringAfterLast$default(StringsKt.replace$default(name2, ".pdmap", "", false, 4, (Object) null), "_", (String) null, 2, (Object) null));
                    Pdlog.m3273d(TAG, "createTarget tempTag:" + parseInt + ", tag:" + i2 + ' ');
                    if (parseInt > i2) {
                        i2 = parseInt;
                    }
                } catch (Exception e) {
                    Pdlog.m3274e(TAG, "createTarget:" + Log.getStackTraceString(e) + ' ');
                }
            }
            i = i2;
        }
        return new File(path, StringsKt.replace$default(name, ".pdmap", "", false, 4, (Object) null) + '_' + formatter.format(new Date()) + '_' + (i + 1) + ".pdmap");
    }
}
