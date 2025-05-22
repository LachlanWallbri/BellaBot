package com.pudutech.remotemaintenance.aliyun;

import com.iflytek.aiui.AIUIConstant;
import com.pudutech.remotemaintenance.bean.CIoTMsg;
import kotlin.Metadata;

/* compiled from: AliyunIoTMsg.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0019\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\"\u001a\u00020\u0004H\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0006\"\u0004\b\u0012\u0010\bR\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0006\"\u0004\b\u0015\u0010\bR\u001e\u0010\u0016\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000eR\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0006\"\u0004\b\u001b\u0010\bR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0006\"\u0004\b\u001e\u0010\bR\u001c\u0010\u001f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0006\"\u0004\b!\u0010\b¨\u0006#"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/aliyun/AliyunIoTMsg;", "Lcom/pudutech/remotemaintenance/bean/CIoTMsg;", "()V", AIUIConstant.KEY_CONTENT, "", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "flag", "", "getFlag", "()Ljava/lang/Integer;", "setFlag", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "instruct", "getInstruct", "setInstruct", "mac", "getMac", "setMac", "num", "getNum", "setNum", "sessionId", "getSessionId", "setSessionId", "topicType", "getTopicType", "setTopicType", "type", "getType", "setType", "toString", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class AliyunIoTMsg extends CIoTMsg {
    private String content;
    private Integer flag;
    private String instruct;
    private String mac;
    private Integer num;
    private String sessionId;
    private String topicType;
    private String type;

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        this.type = str;
    }

    public final String getInstruct() {
        return this.instruct;
    }

    public final void setInstruct(String str) {
        this.instruct = str;
    }

    public final Integer getFlag() {
        return this.flag;
    }

    public final void setFlag(Integer num) {
        this.flag = num;
    }

    public final Integer getNum() {
        return this.num;
    }

    public final void setNum(Integer num) {
        this.num = num;
    }

    public final String getContent() {
        return this.content;
    }

    public final void setContent(String str) {
        this.content = str;
    }

    public final String getSessionId() {
        return this.sessionId;
    }

    public final void setSessionId(String str) {
        this.sessionId = str;
    }

    public final String getMac() {
        return this.mac;
    }

    public final void setMac(String str) {
        this.mac = str;
    }

    public final String getTopicType() {
        return this.topicType;
    }

    public final void setTopicType(String str) {
        this.topicType = str;
    }

    public String toString() {
        return "AliyunIoTMsg(type=" + this.type + ", instruct=" + this.instruct + ", flag=" + this.flag + ", num=" + this.num + ", content=" + this.content + ", sessionId=" + this.sessionId + ", mac=" + this.mac + ", topicType=" + this.topicType + ')';
    }
}
