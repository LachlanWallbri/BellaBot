package com.pudutech.bumblebee.presenter.robot_open_task.bean;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MqttReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0002\u0010\u0006J\t\u0010\f\u001a\u00020\u0004HÆ\u0003J\u000e\u0010\r\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\bJ(\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0004HÖ\u0001R\u0013\u0010\u0005\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/SubTopic;", ExifInterface.GPS_DIRECTION_TRUE, "", "sub_topic", "", "data", "(Ljava/lang/String;Ljava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getSub_topic", "()Ljava/lang/String;", "component1", "component2", "copy", "(Ljava/lang/String;Ljava/lang/Object;)Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/SubTopic;", "equals", "", "other", "hashCode", "", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class SubTopic<T> {
    private final T data;
    private final String sub_topic;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SubTopic copy$default(SubTopic subTopic, String str, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            str = subTopic.sub_topic;
        }
        if ((i & 2) != 0) {
            obj = subTopic.data;
        }
        return subTopic.copy(str, obj);
    }

    /* renamed from: component1, reason: from getter */
    public final String getSub_topic() {
        return this.sub_topic;
    }

    public final T component2() {
        return this.data;
    }

    public final SubTopic<T> copy(String sub_topic, T data) {
        Intrinsics.checkParameterIsNotNull(sub_topic, "sub_topic");
        return new SubTopic<>(sub_topic, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SubTopic)) {
            return false;
        }
        SubTopic subTopic = (SubTopic) other;
        return Intrinsics.areEqual(this.sub_topic, subTopic.sub_topic) && Intrinsics.areEqual(this.data, subTopic.data);
    }

    public int hashCode() {
        String str = this.sub_topic;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        T t = this.data;
        return hashCode + (t != null ? t.hashCode() : 0);
    }

    public String toString() {
        return "SubTopic(sub_topic=" + this.sub_topic + ", data=" + this.data + ")";
    }

    public SubTopic(String sub_topic, T t) {
        Intrinsics.checkParameterIsNotNull(sub_topic, "sub_topic");
        this.sub_topic = sub_topic;
        this.data = t;
    }

    public final T getData() {
        return this.data;
    }

    public final String getSub_topic() {
        return this.sub_topic;
    }
}
