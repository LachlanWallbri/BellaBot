package com.pudutech.robot.opensdk.aliyun.bean;

import com.iflytek.aiui.AIUIConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ShadowData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\tHÆ\u0003J9\u0010\u001f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020\u0007HÖ\u0001R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006&"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowData;", "", "metadata", "Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowMetadata;", "state", "Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowState;", "status", "", AIUIConstant.KEY_CONTENT, "Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowErrorContent;", "(Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowMetadata;Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowState;Ljava/lang/String;Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowErrorContent;)V", "getContent", "()Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowErrorContent;", "setContent", "(Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowErrorContent;)V", "getMetadata", "()Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowMetadata;", "setMetadata", "(Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowMetadata;)V", "getState", "()Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowState;", "setState", "(Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowState;)V", "getStatus", "()Ljava/lang/String;", "setStatus", "(Ljava/lang/String;)V", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class ShadowData {
    private ShadowErrorContent content;
    private ShadowMetadata metadata;
    private ShadowState state;
    private String status;

    public static /* synthetic */ ShadowData copy$default(ShadowData shadowData, ShadowMetadata shadowMetadata, ShadowState shadowState, String str, ShadowErrorContent shadowErrorContent, int i, Object obj) {
        if ((i & 1) != 0) {
            shadowMetadata = shadowData.metadata;
        }
        if ((i & 2) != 0) {
            shadowState = shadowData.state;
        }
        if ((i & 4) != 0) {
            str = shadowData.status;
        }
        if ((i & 8) != 0) {
            shadowErrorContent = shadowData.content;
        }
        return shadowData.copy(shadowMetadata, shadowState, str, shadowErrorContent);
    }

    /* renamed from: component1, reason: from getter */
    public final ShadowMetadata getMetadata() {
        return this.metadata;
    }

    /* renamed from: component2, reason: from getter */
    public final ShadowState getState() {
        return this.state;
    }

    /* renamed from: component3, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* renamed from: component4, reason: from getter */
    public final ShadowErrorContent getContent() {
        return this.content;
    }

    public final ShadowData copy(ShadowMetadata metadata, ShadowState state, String status, ShadowErrorContent content) {
        return new ShadowData(metadata, state, status, content);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ShadowData)) {
            return false;
        }
        ShadowData shadowData = (ShadowData) other;
        return Intrinsics.areEqual(this.metadata, shadowData.metadata) && Intrinsics.areEqual(this.state, shadowData.state) && Intrinsics.areEqual(this.status, shadowData.status) && Intrinsics.areEqual(this.content, shadowData.content);
    }

    public int hashCode() {
        ShadowMetadata shadowMetadata = this.metadata;
        int hashCode = (shadowMetadata != null ? shadowMetadata.hashCode() : 0) * 31;
        ShadowState shadowState = this.state;
        int hashCode2 = (hashCode + (shadowState != null ? shadowState.hashCode() : 0)) * 31;
        String str = this.status;
        int hashCode3 = (hashCode2 + (str != null ? str.hashCode() : 0)) * 31;
        ShadowErrorContent shadowErrorContent = this.content;
        return hashCode3 + (shadowErrorContent != null ? shadowErrorContent.hashCode() : 0);
    }

    public String toString() {
        return "ShadowData(metadata=" + this.metadata + ", state=" + this.state + ", status=" + this.status + ", content=" + this.content + ")";
    }

    public ShadowData(ShadowMetadata shadowMetadata, ShadowState shadowState, String str, ShadowErrorContent shadowErrorContent) {
        this.metadata = shadowMetadata;
        this.state = shadowState;
        this.status = str;
        this.content = shadowErrorContent;
    }

    public final ShadowMetadata getMetadata() {
        return this.metadata;
    }

    public final void setMetadata(ShadowMetadata shadowMetadata) {
        this.metadata = shadowMetadata;
    }

    public final ShadowState getState() {
        return this.state;
    }

    public final void setState(ShadowState shadowState) {
        this.state = shadowState;
    }

    public /* synthetic */ ShadowData(ShadowMetadata shadowMetadata, ShadowState shadowState, String str, ShadowErrorContent shadowErrorContent, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(shadowMetadata, shadowState, (i & 4) != 0 ? (String) null : str, (i & 8) != 0 ? (ShadowErrorContent) null : shadowErrorContent);
    }

    public final String getStatus() {
        return this.status;
    }

    public final void setStatus(String str) {
        this.status = str;
    }

    public final ShadowErrorContent getContent() {
        return this.content;
    }

    public final void setContent(ShadowErrorContent shadowErrorContent) {
        this.content = shadowErrorContent;
    }
}
