package com.pudutech.robot.opensdk.aliyun.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ShadowData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowStateValue;", "", "authConfig", "Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowAuthConfig;", "groupId", "", "(Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowAuthConfig;Ljava/lang/String;)V", "getAuthConfig", "()Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowAuthConfig;", "setAuthConfig", "(Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowAuthConfig;)V", "getGroupId", "()Ljava/lang/String;", "setGroupId", "(Ljava/lang/String;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class ShadowStateValue {
    private ShadowAuthConfig authConfig;
    private String groupId;

    public static /* synthetic */ ShadowStateValue copy$default(ShadowStateValue shadowStateValue, ShadowAuthConfig shadowAuthConfig, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            shadowAuthConfig = shadowStateValue.authConfig;
        }
        if ((i & 2) != 0) {
            str = shadowStateValue.groupId;
        }
        return shadowStateValue.copy(shadowAuthConfig, str);
    }

    /* renamed from: component1, reason: from getter */
    public final ShadowAuthConfig getAuthConfig() {
        return this.authConfig;
    }

    /* renamed from: component2, reason: from getter */
    public final String getGroupId() {
        return this.groupId;
    }

    public final ShadowStateValue copy(ShadowAuthConfig authConfig, String groupId) {
        return new ShadowStateValue(authConfig, groupId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ShadowStateValue)) {
            return false;
        }
        ShadowStateValue shadowStateValue = (ShadowStateValue) other;
        return Intrinsics.areEqual(this.authConfig, shadowStateValue.authConfig) && Intrinsics.areEqual(this.groupId, shadowStateValue.groupId);
    }

    public int hashCode() {
        ShadowAuthConfig shadowAuthConfig = this.authConfig;
        int hashCode = (shadowAuthConfig != null ? shadowAuthConfig.hashCode() : 0) * 31;
        String str = this.groupId;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "ShadowStateValue(authConfig=" + this.authConfig + ", groupId=" + this.groupId + ")";
    }

    public ShadowStateValue(ShadowAuthConfig shadowAuthConfig, String str) {
        this.authConfig = shadowAuthConfig;
        this.groupId = str;
    }

    public final ShadowAuthConfig getAuthConfig() {
        return this.authConfig;
    }

    public final String getGroupId() {
        return this.groupId;
    }

    public final void setAuthConfig(ShadowAuthConfig shadowAuthConfig) {
        this.authConfig = shadowAuthConfig;
    }

    public final void setGroupId(String str) {
        this.groupId = str;
    }
}
