package com.pudutech.bumblebee.presenter.robot_open_task.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: PublishPayload.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\bHÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/PublishPayload;", "", "topic", "", MqttServiceConstants.PAYLOAD, "isWait", "", "publishTimes", "", "(Ljava/lang/String;Ljava/lang/String;ZI)V", "()Z", "getPayload", "()Ljava/lang/String;", "getPublishTimes", "()I", "setPublishTimes", "(I)V", "getTopic", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class PublishPayload {
    private final boolean isWait;
    private final String payload;
    private int publishTimes;
    private final String topic;

    public static /* synthetic */ PublishPayload copy$default(PublishPayload publishPayload, String str, String str2, boolean z, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = publishPayload.topic;
        }
        if ((i2 & 2) != 0) {
            str2 = publishPayload.payload;
        }
        if ((i2 & 4) != 0) {
            z = publishPayload.isWait;
        }
        if ((i2 & 8) != 0) {
            i = publishPayload.publishTimes;
        }
        return publishPayload.copy(str, str2, z, i);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTopic() {
        return this.topic;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPayload() {
        return this.payload;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getIsWait() {
        return this.isWait;
    }

    /* renamed from: component4, reason: from getter */
    public final int getPublishTimes() {
        return this.publishTimes;
    }

    public final PublishPayload copy(String topic, String payload, boolean isWait, int publishTimes) {
        Intrinsics.checkParameterIsNotNull(topic, "topic");
        Intrinsics.checkParameterIsNotNull(payload, "payload");
        return new PublishPayload(topic, payload, isWait, publishTimes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PublishPayload)) {
            return false;
        }
        PublishPayload publishPayload = (PublishPayload) other;
        return Intrinsics.areEqual(this.topic, publishPayload.topic) && Intrinsics.areEqual(this.payload, publishPayload.payload) && this.isWait == publishPayload.isWait && this.publishTimes == publishPayload.publishTimes;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.topic;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.payload;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        boolean z = this.isWait;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return ((hashCode2 + i) * 31) + this.publishTimes;
    }

    public String toString() {
        return "PublishPayload(topic=" + this.topic + ", payload=" + this.payload + ", isWait=" + this.isWait + ", publishTimes=" + this.publishTimes + ")";
    }

    public PublishPayload(String topic, String payload, boolean z, int i) {
        Intrinsics.checkParameterIsNotNull(topic, "topic");
        Intrinsics.checkParameterIsNotNull(payload, "payload");
        this.topic = topic;
        this.payload = payload;
        this.isWait = z;
        this.publishTimes = i;
    }

    public /* synthetic */ PublishPayload(String str, String str2, boolean z, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, z, (i2 & 8) != 0 ? 0 : i);
    }

    public final String getPayload() {
        return this.payload;
    }

    public final int getPublishTimes() {
        return this.publishTimes;
    }

    public final String getTopic() {
        return this.topic;
    }

    public final boolean isWait() {
        return this.isWait;
    }

    public final void setPublishTimes(int i) {
        this.publishTimes = i;
    }
}
