package com.pudutech.module_robot_selfcheck.oss;

import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Config.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b-\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0014\u0010\u000f\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0014\u0010\u0011\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR\u0014\u0010\u0013\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\fR\u0014\u0010\u0015\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\fR\u0014\u0010\u0017\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\fR\u0014\u0010\u0019\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\fR\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0006\"\u0004\b\u001f\u0010\bR\u001a\u0010 \u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0006\"\u0004\b\"\u0010\bR\u0014\u0010#\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\fR\u0014\u0010%\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\fR\u0014\u0010'\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\fR\u0014\u0010)\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\fR\u0014\u0010+\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\fR\u0011\u0010-\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0006R\u0014\u0010/\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\fR\u0014\u00101\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\fR\u0014\u00103\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\fR\u0014\u00105\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\f¨\u00067"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/oss/Config;", "", "()V", "BUCKET_NAME", "", "getBUCKET_NAME", "()Ljava/lang/String;", "setBUCKET_NAME", "(Ljava/lang/String;)V", "BUCKET_SUC", "", "getBUCKET_SUC", "()I", "DOWNLOAD_Fail", "getDOWNLOAD_Fail", "DOWNLOAD_SUC", "getDOWNLOAD_SUC", "FAIL", "getFAIL", "GET_STS_SUC", "getGET_STS_SUC", "HEAD_SUC", "getHEAD_SUC", "LIST_SUC", "getLIST_SUC", "MULTIPART_SUC", "getMULTIPART_SUC", "OSS_ACCESS_KEY_ID", "OSS_ACCESS_KEY_SECRET", "OSS_ENDPOINT", "getOSS_ENDPOINT", "setOSS_ENDPOINT", "REGION", "getREGION", "setREGION", "REQUESTCODE_AUTH", "getREQUESTCODE_AUTH", "REQUESTCODE_LOCALPHOTOS", "getREQUESTCODE_LOCALPHOTOS", "REQUESTCODE_OPEN_DOCUMENT", "getREQUESTCODE_OPEN_DOCUMENT", "RESUMABLE_SUC", "getRESUMABLE_SUC", "SIGN_SUC", "getSIGN_SUC", "STS_SERVER_URL", "getSTS_SERVER_URL", "STS_TOKEN_SUC", "getSTS_TOKEN_SUC", "UPLOAD_Fail", "getUPLOAD_Fail", "UPLOAD_PROGRESS", "getUPLOAD_PROGRESS", "UPLOAD_SUC", "getUPLOAD_SUC", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class Config {
    private static String BUCKET_NAME = null;
    private static final int BUCKET_SUC;
    private static final int DOWNLOAD_Fail;
    private static final int DOWNLOAD_SUC;
    private static final int FAIL;
    private static final int GET_STS_SUC;
    private static final int HEAD_SUC;
    private static final int LIST_SUC;
    private static final int MULTIPART_SUC;
    public static final String OSS_ACCESS_KEY_ID = "*************";
    public static final String OSS_ACCESS_KEY_SECRET = "*************";
    private static final int REQUESTCODE_AUTH;
    private static final int REQUESTCODE_LOCALPHOTOS;
    private static final int REQUESTCODE_OPEN_DOCUMENT;
    private static final int RESUMABLE_SUC;
    private static final int SIGN_SUC;
    private static final String STS_SERVER_URL;
    private static final int STS_TOKEN_SUC;
    private static final int UPLOAD_Fail;
    private static final int UPLOAD_PROGRESS;
    private static final int UPLOAD_SUC;
    public static final Config INSTANCE = new Config();
    private static String OSS_ENDPOINT = "http://oss-cn-beijing.aliyuncs.com";
    private static String REGION = "oss-cn-beijing";

    static {
        STS_SERVER_URL = NetWorkApiManager.INSTANCE.isTestServer() ? "http://rmp-test.pudutech.com/api/map/get_oss_token/v1" : "http://rmp.pudutech.com/api/map/get_oss_token/v1";
        BUCKET_NAME = "pudu-file-host";
        DOWNLOAD_SUC = 1;
        DOWNLOAD_Fail = 2;
        UPLOAD_SUC = 3;
        UPLOAD_Fail = 4;
        UPLOAD_PROGRESS = 5;
        LIST_SUC = 6;
        HEAD_SUC = 7;
        RESUMABLE_SUC = 8;
        SIGN_SUC = 9;
        BUCKET_SUC = 10;
        GET_STS_SUC = 11;
        MULTIPART_SUC = 12;
        STS_TOKEN_SUC = 13;
        FAIL = 9999;
        REQUESTCODE_AUTH = 10111;
        REQUESTCODE_LOCALPHOTOS = 10112;
        REQUESTCODE_OPEN_DOCUMENT = 10113;
    }

    private Config() {
    }

    public final String getOSS_ENDPOINT() {
        return OSS_ENDPOINT;
    }

    public final void setOSS_ENDPOINT(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        OSS_ENDPOINT = str;
    }

    public final String getREGION() {
        return REGION;
    }

    public final void setREGION(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        REGION = str;
    }

    public final String getSTS_SERVER_URL() {
        return STS_SERVER_URL;
    }

    public final String getBUCKET_NAME() {
        return BUCKET_NAME;
    }

    public final void setBUCKET_NAME(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        BUCKET_NAME = str;
    }

    public final int getDOWNLOAD_SUC() {
        return DOWNLOAD_SUC;
    }

    public final int getDOWNLOAD_Fail() {
        return DOWNLOAD_Fail;
    }

    public final int getUPLOAD_SUC() {
        return UPLOAD_SUC;
    }

    public final int getUPLOAD_Fail() {
        return UPLOAD_Fail;
    }

    public final int getUPLOAD_PROGRESS() {
        return UPLOAD_PROGRESS;
    }

    public final int getLIST_SUC() {
        return LIST_SUC;
    }

    public final int getHEAD_SUC() {
        return HEAD_SUC;
    }

    public final int getRESUMABLE_SUC() {
        return RESUMABLE_SUC;
    }

    public final int getSIGN_SUC() {
        return SIGN_SUC;
    }

    public final int getBUCKET_SUC() {
        return BUCKET_SUC;
    }

    public final int getGET_STS_SUC() {
        return GET_STS_SUC;
    }

    public final int getMULTIPART_SUC() {
        return MULTIPART_SUC;
    }

    public final int getSTS_TOKEN_SUC() {
        return STS_TOKEN_SUC;
    }

    public final int getFAIL() {
        return FAIL;
    }

    public final int getREQUESTCODE_AUTH() {
        return REQUESTCODE_AUTH;
    }

    public final int getREQUESTCODE_LOCALPHOTOS() {
        return REQUESTCODE_LOCALPHOTOS;
    }

    public final int getREQUESTCODE_OPEN_DOCUMENT() {
        return REQUESTCODE_OPEN_DOCUMENT;
    }
}
