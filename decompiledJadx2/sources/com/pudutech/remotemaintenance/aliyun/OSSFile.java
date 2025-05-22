package com.pudutech.remotemaintenance.aliyun;

import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import com.pudutech.remotemaintenance.bean.CFile;
import kotlin.Metadata;

/* compiled from: OSSFile.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0016\u0018\u00002\u00020\u0001BA\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tJ\b\u0010\u0018\u001a\u00020\u0003H\u0016R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\rR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000b\"\u0004\b\u0017\u0010\r¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/aliyun/OSSFile;", "Lcom/pudutech/remotemaintenance/bean/CFile;", OSSConfig.PARAM_REGION, "", OSSConfig.PARAM_ACCESS_KEY_ID, OSSConfig.PARAM_ACCESS_KEY_SECRET, "stsToken", OSSConfig.PARAM_BUCKET, OSSConfig.PARAM_OSS_FILE_DIR, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccessKeyId", "()Ljava/lang/String;", "setAccessKeyId", "(Ljava/lang/String;)V", "getAccessKeySecret", "setAccessKeySecret", "getBucket", "setBucket", "getOssFileDir", "setOssFileDir", "getRegion", "setRegion", "getStsToken", "setStsToken", "toString", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class OSSFile extends CFile {
    private String accessKeyId;
    private String accessKeySecret;
    private String bucket;
    private String ossFileDir;
    private String region;
    private String stsToken;

    public final String getRegion() {
        return this.region;
    }

    public final void setRegion(String str) {
        this.region = str;
    }

    public final String getAccessKeyId() {
        return this.accessKeyId;
    }

    public final void setAccessKeyId(String str) {
        this.accessKeyId = str;
    }

    public final String getAccessKeySecret() {
        return this.accessKeySecret;
    }

    public final void setAccessKeySecret(String str) {
        this.accessKeySecret = str;
    }

    public final String getStsToken() {
        return this.stsToken;
    }

    public final void setStsToken(String str) {
        this.stsToken = str;
    }

    public final String getBucket() {
        return this.bucket;
    }

    public final void setBucket(String str) {
        this.bucket = str;
    }

    public final String getOssFileDir() {
        return this.ossFileDir;
    }

    public final void setOssFileDir(String str) {
        this.ossFileDir = str;
    }

    public OSSFile(String str, String str2, String str3, String str4, String str5, String str6) {
        this.region = str;
        this.accessKeyId = str2;
        this.accessKeySecret = str3;
        this.stsToken = str4;
        this.bucket = str5;
        this.ossFileDir = str6;
    }

    public String toString() {
        return "OSSFile(region=" + this.region + ", accessKeyId=" + this.accessKeyId + ", accessKeySecret=" + this.accessKeySecret + ", stsToken=" + this.stsToken + ", bucket=" + this.bucket + ", ossFileDir=" + this.ossFileDir + ')';
    }
}
