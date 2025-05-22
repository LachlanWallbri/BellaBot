package com.pudutech.mirsdk.robotsdk;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: YouDianMqtt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000f¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/mirsdk/robotsdk/YouDianMqtt;", "", "msgId", "", "fromId", "body", "Lcom/pudutech/mirsdk/robotsdk/YouDianBody;", "(Ljava/lang/String;Ljava/lang/String;Lcom/pudutech/mirsdk/robotsdk/YouDianBody;)V", "getBody", "()Lcom/pudutech/mirsdk/robotsdk/YouDianBody;", "setBody", "(Lcom/pudutech/mirsdk/robotsdk/YouDianBody;)V", "getFromId", "()Ljava/lang/String;", "setFromId", "(Ljava/lang/String;)V", "getMsgId", "setMsgId", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class YouDianMqtt {
    private YouDianBody body;
    private String fromId;
    private String msgId;

    public YouDianMqtt(String str, String str2, YouDianBody youDianBody) {
        this.msgId = str;
        this.fromId = str2;
        this.body = youDianBody;
    }

    public final YouDianBody getBody() {
        return this.body;
    }

    public final String getFromId() {
        return this.fromId;
    }

    public final String getMsgId() {
        return this.msgId;
    }

    public final void setBody(YouDianBody youDianBody) {
        this.body = youDianBody;
    }

    public final void setFromId(String str) {
        this.fromId = str;
    }

    public final void setMsgId(String str) {
        this.msgId = str;
    }
}
