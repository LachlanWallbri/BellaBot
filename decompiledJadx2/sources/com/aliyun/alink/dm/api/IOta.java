package com.aliyun.alink.dm.api;

import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IOta {
    public static final int ERROR_HTTP_EXCEPTION = 2;
    public static final int ERROR_MQTT_EXCEPTION = 1;
    public static final int ERROR_SAVE_OTA_EXCEPTION = 3;
    public static final int NO_ERROR = 0;
    public static final int STEP_DOWNLOAD = 4;
    public static final int STEP_RCVD_OTA = 3;
    public static final int STEP_REPORT_VERSION = 1;
    public static final int STEP_SUBSCRIBE = 2;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class OtaConfig {
        public String deviceVersion;
        public File otaFile;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public interface OtaListener {
        boolean onOtaProgress(int i, OtaResult otaResult);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public interface OtaResult {
        Object getData();

        int getErrorCode();
    }

    void reportProgress(int i, String str, ResultCallback<String> resultCallback);

    void reportVersion(String str, ResultCallback<String> resultCallback);

    void tryStartOta(OtaConfig otaConfig, OtaListener otaListener);

    void tryStopOta();
}
