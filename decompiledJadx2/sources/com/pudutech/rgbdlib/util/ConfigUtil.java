package com.pudutech.rgbdlib.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: ConfigUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/rgbdlib/util/ConfigUtil;", "", "()V", "CONFIG_BACKUP_DIR", "", "CONFIG_DIR", "RGBD_CONFIG_FILE_NAME", "backupFile", "", "fromDir", "toDir", "filename", "RGBDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ConfigUtil {
    public static final String CONFIG_BACKUP_DIR = "/sdcard/pudu/config_backup/";
    public static final String CONFIG_DIR = "/sdcard/pudu/config/";
    public static final ConfigUtil INSTANCE = new ConfigUtil();
    public static final String RGBD_CONFIG_FILE_NAME = "rgbd.json";

    private ConfigUtil() {
    }

    public final void backupFile(String fromDir, String toDir, String filename) {
        Intrinsics.checkParameterIsNotNull(fromDir, "fromDir");
        Intrinsics.checkParameterIsNotNull(toDir, "toDir");
        Intrinsics.checkParameterIsNotNull(filename, "filename");
        FileInputStream fileInputStream = (FileInputStream) null;
        FileOutputStream fileOutputStream = (FileOutputStream) null;
        try {
            try {
                File file = new File(fromDir + filename);
                File file2 = new File(toDir + filename);
                if (!file.exists()) {
                    return;
                }
                File file3 = new File(toDir);
                if (!file3.exists()) {
                    file3.mkdirs();
                }
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                    try {
                        byte[] bArr = new byte[1024];
                        Ref.IntRef intRef = new Ref.IntRef();
                        while (true) {
                            int read = fileInputStream2.read(bArr);
                            intRef.element = read;
                            if (read != -1) {
                                fileOutputStream2.write(bArr, 0, intRef.element);
                            } else {
                                fileOutputStream2.flush();
                                fileInputStream2.close();
                                fileOutputStream2.close();
                                return;
                            }
                        }
                    } catch (Exception e) {
                        e = e;
                        fileInputStream = fileInputStream2;
                        fileOutputStream = fileOutputStream2;
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream = fileInputStream2;
                        fileOutputStream = fileOutputStream2;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                    fileInputStream = fileInputStream2;
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream = fileInputStream2;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }
}
