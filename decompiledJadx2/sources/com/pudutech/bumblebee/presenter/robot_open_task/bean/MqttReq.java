package com.pudutech.bumblebee.presenter.robot_open_task.bean;

import androidx.exifinterface.media.ExifInterface;
import com.aliyun.alink.linksdk.tmp.api.DevFoundOutputParams;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: MqttReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002BA\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\fJ\u0010\u0010 \u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0012J\u0010\u0010!\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0012J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0010\u0010$\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0010\u0010%\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\u0017J\\\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00018\u0000HÆ\u0001¢\u0006\u0002\u0010'J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010+\u001a\u00020\nHÖ\u0001J\t\u0010,\u001a\u00020\u0007HÖ\u0001R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0015\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0015\u0010\u000b\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000e\"\u0004\b\u001a\u0010\u0010R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0015\u001a\u0004\b\u001b\u0010\u0012\"\u0004\b\u001c\u0010\u0014R\u0015\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b\u001d\u0010\u001e¨\u0006-"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/MqttReq;", ExifInterface.GPS_DIRECTION_TRUE, "", "msg_id", "", "timestamp", DevFoundOutputParams.PARAMS_DEVICE_NAME, "", "product", "version", "", MqttServiceConstants.PAYLOAD, "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Object;)V", "getDevice_name", "()Ljava/lang/String;", "setDevice_name", "(Ljava/lang/String;)V", "getMsg_id", "()Ljava/lang/Long;", "setMsg_id", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getPayload", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getProduct", "setProduct", "getTimestamp", "setTimestamp", "getVersion", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Object;)Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/MqttReq;", "equals", "", "other", "hashCode", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class MqttReq<T> {
    private String device_name;
    private Long msg_id;
    private final T payload;
    private String product;
    private Long timestamp;
    private final Integer version;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MqttReq copy$default(MqttReq mqttReq, Long l, Long l2, String str, String str2, Integer num, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            l = mqttReq.msg_id;
        }
        if ((i & 2) != 0) {
            l2 = mqttReq.timestamp;
        }
        Long l3 = l2;
        if ((i & 4) != 0) {
            str = mqttReq.device_name;
        }
        String str3 = str;
        if ((i & 8) != 0) {
            str2 = mqttReq.product;
        }
        String str4 = str2;
        if ((i & 16) != 0) {
            num = mqttReq.version;
        }
        Integer num2 = num;
        T t = obj;
        if ((i & 32) != 0) {
            t = mqttReq.payload;
        }
        return mqttReq.copy(l, l3, str3, str4, num2, t);
    }

    /* renamed from: component1, reason: from getter */
    public final Long getMsg_id() {
        return this.msg_id;
    }

    /* renamed from: component2, reason: from getter */
    public final Long getTimestamp() {
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
    public final Integer getVersion() {
        return this.version;
    }

    public final T component6() {
        return this.payload;
    }

    public final MqttReq<T> copy(Long msg_id, Long timestamp, String device_name, String product, Integer version, T payload) {
        return new MqttReq<>(msg_id, timestamp, device_name, product, version, payload);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MqttReq)) {
            return false;
        }
        MqttReq mqttReq = (MqttReq) other;
        return Intrinsics.areEqual(this.msg_id, mqttReq.msg_id) && Intrinsics.areEqual(this.timestamp, mqttReq.timestamp) && Intrinsics.areEqual(this.device_name, mqttReq.device_name) && Intrinsics.areEqual(this.product, mqttReq.product) && Intrinsics.areEqual(this.version, mqttReq.version) && Intrinsics.areEqual(this.payload, mqttReq.payload);
    }

    public int hashCode() {
        Long l = this.msg_id;
        int hashCode = (l != null ? l.hashCode() : 0) * 31;
        Long l2 = this.timestamp;
        int hashCode2 = (hashCode + (l2 != null ? l2.hashCode() : 0)) * 31;
        String str = this.device_name;
        int hashCode3 = (hashCode2 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.product;
        int hashCode4 = (hashCode3 + (str2 != null ? str2.hashCode() : 0)) * 31;
        Integer num = this.version;
        int hashCode5 = (hashCode4 + (num != null ? num.hashCode() : 0)) * 31;
        T t = this.payload;
        return hashCode5 + (t != null ? t.hashCode() : 0);
    }

    public String toString() {
        return "MqttReq(msg_id=" + this.msg_id + ", timestamp=" + this.timestamp + ", device_name=" + this.device_name + ", product=" + this.product + ", version=" + this.version + ", payload=" + this.payload + ")";
    }

    public MqttReq(Long l, Long l2, String str, String str2, Integer num, T t) {
        this.msg_id = l;
        this.timestamp = l2;
        this.device_name = str;
        this.product = str2;
        this.version = num;
        this.payload = t;
    }

    public final Long getMsg_id() {
        return this.msg_id;
    }

    public final void setMsg_id(Long l) {
        this.msg_id = l;
    }

    public final Long getTimestamp() {
        return this.timestamp;
    }

    public final void setTimestamp(Long l) {
        this.timestamp = l;
    }

    public final String getDevice_name() {
        return this.device_name;
    }

    public final void setDevice_name(String str) {
        this.device_name = str;
    }

    public final String getProduct() {
        return this.product;
    }

    public final void setProduct(String str) {
        this.product = str;
    }

    public final Integer getVersion() {
        return this.version;
    }

    public final T getPayload() {
        return this.payload;
    }
}
