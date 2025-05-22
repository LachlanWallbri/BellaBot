package com.pudutech.disinfect.baselib.config;

import android.os.Environment;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: PathConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0012\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0019\u0010\u0011\u001a\n \u0012*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0006R\u0011\u0010\u0014\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0006¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/config/PathConfig;", "", "()V", "AUDIO_PATH", "", "getAUDIO_PATH", "()Ljava/lang/String;", "BASE_PATH", "getBASE_PATH", "DOWNLOAD_PATH", "getDOWNLOAD_PATH", "IMAGE_PATH", "getIMAGE_PATH", "PHOTO_PATH", "getPHOTO_PATH", "QRCODE_PATH", "getQRCODE_PATH", "SDCARD_PATH", "kotlin.jvm.PlatformType", "getSDCARD_PATH", "VIDEO_PATH", "getVIDEO_PATH", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class PathConfig {
    private static final String AUDIO_PATH;
    private static final String BASE_PATH;
    private static final String DOWNLOAD_PATH;
    private static final String IMAGE_PATH;
    public static final PathConfig INSTANCE = new PathConfig();
    private static final String PHOTO_PATH;
    private static final String QRCODE_PATH;
    private static final String SDCARD_PATH;
    private static final String VIDEO_PATH;

    static {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        Intrinsics.checkExpressionValueIsNotNull(externalStorageDirectory, "Environment.getExternalStorageDirectory()");
        SDCARD_PATH = externalStorageDirectory.getAbsolutePath();
        BASE_PATH = SDCARD_PATH + "/pudurobot_data";
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_PATH);
        sb.append("/imageCache");
        IMAGE_PATH = sb.toString();
        PHOTO_PATH = BASE_PATH + "/photo";
        AUDIO_PATH = BASE_PATH + "/audio";
        VIDEO_PATH = BASE_PATH + "/video";
        QRCODE_PATH = BASE_PATH + "/qrCodePhoto";
        DOWNLOAD_PATH = BASE_PATH + "/downloadCache";
    }

    private PathConfig() {
    }

    public final String getSDCARD_PATH() {
        return SDCARD_PATH;
    }

    public final String getBASE_PATH() {
        return BASE_PATH;
    }

    public final String getIMAGE_PATH() {
        return IMAGE_PATH;
    }

    public final String getPHOTO_PATH() {
        return PHOTO_PATH;
    }

    public final String getAUDIO_PATH() {
        return AUDIO_PATH;
    }

    public final String getVIDEO_PATH() {
        return VIDEO_PATH;
    }

    public final String getQRCODE_PATH() {
        return QRCODE_PATH;
    }

    public final String getDOWNLOAD_PATH() {
        return DOWNLOAD_PATH;
    }
}
