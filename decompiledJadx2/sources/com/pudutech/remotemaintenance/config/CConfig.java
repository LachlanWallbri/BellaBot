package com.pudutech.remotemaintenance.config;

import android.os.Environment;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/config/CConfig;", "", "()V", "REMOTE_MAINTENANCE_FILE_DIRECTORY", "", "getREMOTE_MAINTENANCE_FILE_DIRECTORY", "()Ljava/lang/String;", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class CConfig {
    public static final CConfig INSTANCE = new CConfig();
    private static final String REMOTE_MAINTENANCE_FILE_DIRECTORY;

    static {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        Intrinsics.checkExpressionValueIsNotNull(externalStorageDirectory, "Environment.getExternalStorageDirectory()");
        REMOTE_MAINTENANCE_FILE_DIRECTORY = externalStorageDirectory.getPath() + "/pudu/files/remotemaintenance";
    }

    private CConfig() {
    }

    public final String getREMOTE_MAINTENANCE_FILE_DIRECTORY() {
        return REMOTE_MAINTENANCE_FILE_DIRECTORY;
    }
}
