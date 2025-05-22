package com.pudutech.robot.opensdk.aliyun.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ShadowData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowMetadata;", "", "desired", "Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowMetadataValue;", "reported", "(Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowMetadataValue;Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowMetadataValue;)V", "getDesired", "()Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowMetadataValue;", "setDesired", "(Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowMetadataValue;)V", "getReported", "setReported", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class ShadowMetadata {
    private ShadowMetadataValue desired;
    private ShadowMetadataValue reported;

    public static /* synthetic */ ShadowMetadata copy$default(ShadowMetadata shadowMetadata, ShadowMetadataValue shadowMetadataValue, ShadowMetadataValue shadowMetadataValue2, int i, Object obj) {
        if ((i & 1) != 0) {
            shadowMetadataValue = shadowMetadata.desired;
        }
        if ((i & 2) != 0) {
            shadowMetadataValue2 = shadowMetadata.reported;
        }
        return shadowMetadata.copy(shadowMetadataValue, shadowMetadataValue2);
    }

    /* renamed from: component1, reason: from getter */
    public final ShadowMetadataValue getDesired() {
        return this.desired;
    }

    /* renamed from: component2, reason: from getter */
    public final ShadowMetadataValue getReported() {
        return this.reported;
    }

    public final ShadowMetadata copy(ShadowMetadataValue desired, ShadowMetadataValue reported) {
        return new ShadowMetadata(desired, reported);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ShadowMetadata)) {
            return false;
        }
        ShadowMetadata shadowMetadata = (ShadowMetadata) other;
        return Intrinsics.areEqual(this.desired, shadowMetadata.desired) && Intrinsics.areEqual(this.reported, shadowMetadata.reported);
    }

    public int hashCode() {
        ShadowMetadataValue shadowMetadataValue = this.desired;
        int hashCode = (shadowMetadataValue != null ? shadowMetadataValue.hashCode() : 0) * 31;
        ShadowMetadataValue shadowMetadataValue2 = this.reported;
        return hashCode + (shadowMetadataValue2 != null ? shadowMetadataValue2.hashCode() : 0);
    }

    public String toString() {
        return "ShadowMetadata(desired=" + this.desired + ", reported=" + this.reported + ")";
    }

    public ShadowMetadata(ShadowMetadataValue shadowMetadataValue, ShadowMetadataValue shadowMetadataValue2) {
        this.desired = shadowMetadataValue;
        this.reported = shadowMetadataValue2;
    }

    public final ShadowMetadataValue getDesired() {
        return this.desired;
    }

    public final ShadowMetadataValue getReported() {
        return this.reported;
    }

    public final void setDesired(ShadowMetadataValue shadowMetadataValue) {
        this.desired = shadowMetadataValue;
    }

    public final void setReported(ShadowMetadataValue shadowMetadataValue) {
        this.reported = shadowMetadataValue;
    }
}
