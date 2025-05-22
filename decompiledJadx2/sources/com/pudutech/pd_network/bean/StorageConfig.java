package com.pudutech.pd_network.bean;

import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: storage.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\nHÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003JY\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\u0006\u0010&\u001a\u00020\nJ\t\u0010'\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000e¨\u0006("}, m3961d2 = {"Lcom/pudutech/pd_network/bean/StorageConfig;", "", OSSConfig.PARAM_REGION, "", "access_key", "access_secret", "security_token", "oss_url", OSSConfig.PARAM_BUCKET, "expire_time", "", "isp", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;)V", "getAccess_key", "()Ljava/lang/String;", "getAccess_secret", "getBucket", "getExpire_time", "()J", "initTimeStamp", "getIsp", "getOss_url", "getRegion", "getSecurity_token", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "invalidTime", "toString", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class StorageConfig {
    private final String access_key;
    private final String access_secret;
    private final String bucket;
    private final long expire_time;
    private final long initTimeStamp;
    private final String isp;
    private final String oss_url;
    private final String region;
    private final String security_token;

    /* renamed from: component1, reason: from getter */
    public final String getRegion() {
        return this.region;
    }

    /* renamed from: component2, reason: from getter */
    public final String getAccess_key() {
        return this.access_key;
    }

    /* renamed from: component3, reason: from getter */
    public final String getAccess_secret() {
        return this.access_secret;
    }

    /* renamed from: component4, reason: from getter */
    public final String getSecurity_token() {
        return this.security_token;
    }

    /* renamed from: component5, reason: from getter */
    public final String getOss_url() {
        return this.oss_url;
    }

    /* renamed from: component6, reason: from getter */
    public final String getBucket() {
        return this.bucket;
    }

    /* renamed from: component7, reason: from getter */
    public final long getExpire_time() {
        return this.expire_time;
    }

    /* renamed from: component8, reason: from getter */
    public final String getIsp() {
        return this.isp;
    }

    public final StorageConfig copy(String region, String access_key, String access_secret, String security_token, String oss_url, String bucket, long expire_time, String isp) {
        Intrinsics.checkParameterIsNotNull(region, "region");
        Intrinsics.checkParameterIsNotNull(access_key, "access_key");
        Intrinsics.checkParameterIsNotNull(access_secret, "access_secret");
        Intrinsics.checkParameterIsNotNull(security_token, "security_token");
        Intrinsics.checkParameterIsNotNull(oss_url, "oss_url");
        Intrinsics.checkParameterIsNotNull(bucket, "bucket");
        Intrinsics.checkParameterIsNotNull(isp, "isp");
        return new StorageConfig(region, access_key, access_secret, security_token, oss_url, bucket, expire_time, isp);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StorageConfig)) {
            return false;
        }
        StorageConfig storageConfig = (StorageConfig) other;
        return Intrinsics.areEqual(this.region, storageConfig.region) && Intrinsics.areEqual(this.access_key, storageConfig.access_key) && Intrinsics.areEqual(this.access_secret, storageConfig.access_secret) && Intrinsics.areEqual(this.security_token, storageConfig.security_token) && Intrinsics.areEqual(this.oss_url, storageConfig.oss_url) && Intrinsics.areEqual(this.bucket, storageConfig.bucket) && this.expire_time == storageConfig.expire_time && Intrinsics.areEqual(this.isp, storageConfig.isp);
    }

    public int hashCode() {
        String str = this.region;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.access_key;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.access_secret;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.security_token;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.oss_url;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.bucket;
        int hashCode6 = (((hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31) + Long.hashCode(this.expire_time)) * 31;
        String str7 = this.isp;
        return hashCode6 + (str7 != null ? str7.hashCode() : 0);
    }

    public String toString() {
        return "StorageConfig(region=" + this.region + ", access_key=" + this.access_key + ", access_secret=" + this.access_secret + ", security_token=" + this.security_token + ", oss_url=" + this.oss_url + ", bucket=" + this.bucket + ", expire_time=" + this.expire_time + ", isp=" + this.isp + ")";
    }

    public StorageConfig(String region, String access_key, String access_secret, String security_token, String oss_url, String bucket, long j, String isp) {
        Intrinsics.checkParameterIsNotNull(region, "region");
        Intrinsics.checkParameterIsNotNull(access_key, "access_key");
        Intrinsics.checkParameterIsNotNull(access_secret, "access_secret");
        Intrinsics.checkParameterIsNotNull(security_token, "security_token");
        Intrinsics.checkParameterIsNotNull(oss_url, "oss_url");
        Intrinsics.checkParameterIsNotNull(bucket, "bucket");
        Intrinsics.checkParameterIsNotNull(isp, "isp");
        this.region = region;
        this.access_key = access_key;
        this.access_secret = access_secret;
        this.security_token = security_token;
        this.oss_url = oss_url;
        this.bucket = bucket;
        this.expire_time = j;
        this.isp = isp;
        this.initTimeStamp = System.currentTimeMillis();
    }

    public final String getRegion() {
        return this.region;
    }

    public final String getAccess_key() {
        return this.access_key;
    }

    public final String getAccess_secret() {
        return this.access_secret;
    }

    public final String getSecurity_token() {
        return this.security_token;
    }

    public final String getOss_url() {
        return this.oss_url;
    }

    public final String getBucket() {
        return this.bucket;
    }

    public final long getExpire_time() {
        return this.expire_time;
    }

    public final String getIsp() {
        return this.isp;
    }

    public final long invalidTime() {
        return this.initTimeStamp + (this.expire_time * 1000);
    }
}
