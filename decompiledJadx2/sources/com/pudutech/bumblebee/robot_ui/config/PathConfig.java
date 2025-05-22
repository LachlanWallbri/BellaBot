package com.pudutech.bumblebee.robot_ui.config;

import android.os.Environment;

/* loaded from: classes2.dex */
public class PathConfig {

    /* loaded from: classes2.dex */
    static class Path {
        static final String AUDIO_CATCH = "/audio";
        static final String DOWNLOAD_CATCH = "/downloadCache";
        static final String IMAGE_CATCH = "/imageCache";
        static final String PHOTO_CATCH = "/photo";
        static final String QR_CODE_CATCH = "/qrCodePhoto";
        static final String VIDEO_CATCH = "/video";
        static final String SD_CARD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
        static final String BASE_PATH = SD_CARD_PATH + "/pudurobot_data";

        Path() {
        }
    }

    public static final String getBasePath() {
        return Path.BASE_PATH;
    }

    public static final String getImagePath() {
        return Path.BASE_PATH + "/imageCache";
    }

    public static final String getPhotoPath() {
        return Path.BASE_PATH + "/photo";
    }

    public static final String getAudioPath() {
        return Path.BASE_PATH + "/audio";
    }

    public static final String getVideoPath() {
        return Path.BASE_PATH + "/video";
    }

    public static final String getDownloadPath() {
        return Path.BASE_PATH + "/downloadCache";
    }

    public static final String getQrCodePath() {
        return Path.BASE_PATH + "/qrCodePhoto";
    }
}
