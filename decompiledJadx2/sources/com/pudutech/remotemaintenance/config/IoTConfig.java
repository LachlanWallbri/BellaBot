package com.pudutech.remotemaintenance.config;

import kotlin.Metadata;

/* compiled from: IoTConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0012\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/config/IoTConfig;", "", "()V", "IOT_PROGRAM", "", "getIOT_PROGRAM", "()Ljava/lang/String;", "setIOT_PROGRAM", "(Ljava/lang/String;)V", "IOT_PROGRAM_ALIYUN", "PARAM_CMD", "PARAM_DATA", "PARAM_ERROR_MSG", "PARAM_FILE_NAME", "PARAM_FILE_OPERATION_TYPE", "PARAM_FILE_URI", "PARAM_IS_SU", "PARAM_PROGRESS", "PARAM_PULL_FILE", "PARAM_PUSH_FILE", "PARAM_RET", "TAG", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class IoTConfig {
    public static final String PARAM_CMD = "cmd";
    public static final String PARAM_DATA = "data";
    public static final String PARAM_ERROR_MSG = "errorMsg";
    public static final String PARAM_FILE_NAME = "filename";
    public static final String PARAM_FILE_OPERATION_TYPE = "type";
    public static final String PARAM_FILE_URI = "fileUri";
    public static final String PARAM_IS_SU = "isSu";
    public static final String PARAM_PROGRESS = "progress";
    public static final String PARAM_PULL_FILE = "pull";
    public static final String PARAM_PUSH_FILE = "push";
    public static final String PARAM_RET = "ret";
    public static final String TAG = "component_iot";
    public static final IoTConfig INSTANCE = new IoTConfig();
    public static final String IOT_PROGRAM_ALIYUN = "aliyun";
    private static String IOT_PROGRAM = IOT_PROGRAM_ALIYUN;

    private IoTConfig() {
    }

    public final String getIOT_PROGRAM() {
        return IOT_PROGRAM;
    }

    public final void setIOT_PROGRAM(String str) {
        IOT_PROGRAM = str;
    }
}
