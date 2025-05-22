package com.pudutech.remotemaintenance.aliyun.config;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OSSConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/aliyun/config/OSSConfig;", "", "()V", "DEFAULT_MULTI_PART_UPLOAD_SIZE", "", "DEFAULT_PART_SIZE", "", "ENDPOINT", "", "getENDPOINT", "()Ljava/lang/String;", "setENDPOINT", "(Ljava/lang/String;)V", "PARAM_ACCESS_KEY_ID", "PARAM_ACCESS_KEY_SECRET", "PARAM_BUCKET", "PARAM_OSS_FILE_DIR", "PARAM_REGION", "PARAM_STS_TOKEN", "PREFIX_HTTPS", "TAG", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class OSSConfig {
    public static final int DEFAULT_MULTI_PART_UPLOAD_SIZE = 10485760;
    public static final long DEFAULT_PART_SIZE = 2097152;
    public static final String PARAM_ACCESS_KEY_ID = "accessKeyId";
    public static final String PARAM_ACCESS_KEY_SECRET = "accessKeySecret";
    public static final String PARAM_BUCKET = "bucket";
    public static final String PARAM_OSS_FILE_DIR = "ossFileDir";
    public static final String PARAM_REGION = "region";
    public static final String PARAM_STS_TOKEN = "stsToken";
    public static final String PREFIX_HTTPS = "https://";
    public static final String TAG = "OSS";
    public static final OSSConfig INSTANCE = new OSSConfig();
    private static String ENDPOINT = "oss-cn-beijing.aliyuncs.com";

    private OSSConfig() {
    }

    public final String getENDPOINT() {
        return ENDPOINT;
    }

    public final void setENDPOINT(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        ENDPOINT = str;
    }
}
