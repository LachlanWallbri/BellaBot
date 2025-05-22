package com.pudutech.event_tracking.bean;

import com.iflytek.aiui.AIUIConstant;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: upload.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J-\u0010\u0018\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0007HÖ\u0001R \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/event_tracking/bean/UpLoadReq;", "", AIUIConstant.KEY_CONTENT, "", "uploadTime", "", "topic", "", "(Ljava/util/List;JLjava/lang/String;)V", "getContent", "()Ljava/util/List;", "setContent", "(Ljava/util/List;)V", "getTopic", "()Ljava/lang/String;", "setTopic", "(Ljava/lang/String;)V", "getUploadTime", "()J", "setUploadTime", "(J)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class UpLoadReq {
    private List<? extends Object> content;
    private String topic;
    private long uploadTime;

    public UpLoadReq() {
        this(null, 0L, null, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ UpLoadReq copy$default(UpLoadReq upLoadReq, List list, long j, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            list = upLoadReq.content;
        }
        if ((i & 2) != 0) {
            j = upLoadReq.uploadTime;
        }
        if ((i & 4) != 0) {
            str = upLoadReq.topic;
        }
        return upLoadReq.copy(list, j, str);
    }

    public final List<Object> component1() {
        return this.content;
    }

    /* renamed from: component2, reason: from getter */
    public final long getUploadTime() {
        return this.uploadTime;
    }

    /* renamed from: component3, reason: from getter */
    public final String getTopic() {
        return this.topic;
    }

    public final UpLoadReq copy(List<? extends Object> content, long uploadTime, String topic) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        Intrinsics.checkParameterIsNotNull(topic, "topic");
        return new UpLoadReq(content, uploadTime, topic);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UpLoadReq)) {
            return false;
        }
        UpLoadReq upLoadReq = (UpLoadReq) other;
        return Intrinsics.areEqual(this.content, upLoadReq.content) && this.uploadTime == upLoadReq.uploadTime && Intrinsics.areEqual(this.topic, upLoadReq.topic);
    }

    public int hashCode() {
        List<? extends Object> list = this.content;
        int hashCode = (((list != null ? list.hashCode() : 0) * 31) + Long.hashCode(this.uploadTime)) * 31;
        String str = this.topic;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "UpLoadReq(content=" + this.content + ", uploadTime=" + this.uploadTime + ", topic=" + this.topic + ")";
    }

    public UpLoadReq(List<? extends Object> content, long j, String topic) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        Intrinsics.checkParameterIsNotNull(topic, "topic");
        this.content = content;
        this.uploadTime = j;
        this.topic = topic;
    }

    public /* synthetic */ UpLoadReq(List list, long j, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt.emptyList() : list, (i & 2) != 0 ? System.currentTimeMillis() : j, (i & 4) != 0 ? "TRACK_EVENT" : str);
    }

    public final List<Object> getContent() {
        return this.content;
    }

    public final void setContent(List<? extends Object> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.content = list;
    }

    public final long getUploadTime() {
        return this.uploadTime;
    }

    public final void setUploadTime(long j) {
        this.uploadTime = j;
    }

    public final String getTopic() {
        return this.topic;
    }

    public final void setTopic(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.topic = str;
    }
}
