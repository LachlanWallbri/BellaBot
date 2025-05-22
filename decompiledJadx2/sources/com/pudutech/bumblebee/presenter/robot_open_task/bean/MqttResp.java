package com.pudutech.bumblebee.presenter.robot_open_task.bean;

import androidx.exifinterface.media.ExifInterface;
import com.aliyun.alink.linksdk.tmp.api.DevFoundOutputParams;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: MqttResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0002\u0010\u000bJ\t\u0010\u001f\u001a\u00020\u0004HÆ\u0003J\t\u0010 \u001a\u00020\u0004HÆ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003J\t\u0010\"\u001a\u00020\u0007HÆ\u0003J\t\u0010#\u001a\u00020\u0004HÆ\u0003J\u000e\u0010$\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u0015JP\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010&J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010*\u001a\u00020+HÖ\u0001J\t\u0010,\u001a\u00020\u0007HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\n\u001a\u00028\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\r\"\u0004\b\u001a\u0010\u000fR\u001a\u0010\u0005\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0011\"\u0004\b\u001c\u0010\u0013R\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0011\"\u0004\b\u001e\u0010\u0013¨\u0006-"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/MqttResp;", ExifInterface.GPS_DIRECTION_TRUE, "", "msg_id", "", "timestamp", DevFoundOutputParams.PARAMS_DEVICE_NAME, "", "product", "version", MqttServiceConstants.PAYLOAD, "(JJLjava/lang/String;Ljava/lang/String;JLjava/lang/Object;)V", "getDevice_name", "()Ljava/lang/String;", "setDevice_name", "(Ljava/lang/String;)V", "getMsg_id", "()J", "setMsg_id", "(J)V", "getPayload", "()Ljava/lang/Object;", "setPayload", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "getProduct", "setProduct", "getTimestamp", "setTimestamp", "getVersion", "setVersion", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(JJLjava/lang/String;Ljava/lang/String;JLjava/lang/Object;)Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/MqttResp;", "equals", "", "other", "hashCode", "", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class MqttResp<T> {
    private String device_name;
    private long msg_id;
    private T payload;
    private String product;
    private long timestamp;
    private long version;

    /* renamed from: component1, reason: from getter */
    public final long getMsg_id() {
        return this.msg_id;
    }

    /* renamed from: component2, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    /* renamed from: component3, reason: from getter */
    public final String getDevice_name() {
        return this.device_name;
    }

    /* renamed from: component4, reason: from getter */
    public final String getProduct() {
        return this.product;
    }

    /* renamed from: component5, reason: from getter */
    public final long getVersion() {
        return this.version;
    }

    public final T component6() {
        return this.payload;
    }

    public final MqttResp<T> copy(long msg_id, long timestamp, String device_name, String product, long version, T payload) {
        Intrinsics.checkParameterIsNotNull(device_name, "device_name");
        Intrinsics.checkParameterIsNotNull(product, "product");
        return new MqttResp<>(msg_id, timestamp, device_name, product, version, payload);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MqttResp)) {
            return false;
        }
        MqttResp mqttResp = (MqttResp) other;
        return this.msg_id == mqttResp.msg_id && this.timestamp == mqttResp.timestamp && Intrinsics.areEqual(this.device_name, mqttResp.device_name) && Intrinsics.areEqual(this.product, mqttResp.product) && this.version == mqttResp.version && Intrinsics.areEqual(this.payload, mqttResp.payload);
    }

    public int hashCode() {
        long j = this.msg_id;
        long j2 = this.timestamp;
        int i = ((((int) (j ^ (j >>> 32))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        String str = this.device_name;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.product;
        int hashCode2 = str2 != null ? str2.hashCode() : 0;
        long j3 = this.version;
        int i2 = (((hashCode + hashCode2) * 31) + ((int) ((j3 >>> 32) ^ j3))) * 31;
        T t = this.payload;
        return i2 + (t != null ? t.hashCode() : 0);
    }

    public String toString() {
        return "MqttResp(msg_id=" + this.msg_id + ", timestamp=" + this.timestamp + ", device_name=" + this.device_name + ", product=" + this.product + ", version=" + this.version + ", payload=" + this.payload + ")";
    }

    public MqttResp(long j, long j2, String device_name, String product, long j3, T t) {
        Intrinsics.checkParameterIsNotNull(device_name, "device_name");
        Intrinsics.checkParameterIsNotNull(product, "product");
        this.msg_id = j;
        this.timestamp = j2;
        this.device_name = device_name;
        this.product = product;
        this.version = j3;
        this.payload = t;
    }

    public final long getMsg_id() {
        return this.msg_id;
    }

    public final void setMsg_id(long j) {
        this.msg_id = j;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final void setTimestamp(long j) {
        this.timestamp = j;
    }

    public final String getDevice_name() {
        return this.device_name;
    }

    public final void setDevice_name(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.device_name = str;
    }

    public final String getProduct() {
        return this.product;
    }

    public final void setProduct(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.product = str;
    }

    public final T getPayload() {
        return this.payload;
    }

    public final long getVersion() {
        return this.version;
    }

    public final void setPayload(T t) {
        this.payload = t;
    }

    public final void setVersion(long j) {
        this.version = j;
    }
}
