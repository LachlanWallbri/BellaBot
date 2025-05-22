package com.pudutech.disinfect.baselib.network.response.ad;

import kotlin.Metadata;

/* compiled from: AdRespIotPushMsg.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/ad/AdRespIotPushMsg;", "", "iotMsg", "", "iotUpdate", "", "(Ljava/lang/String;Ljava/lang/Integer;)V", "getIotMsg", "()Ljava/lang/String;", "setIotMsg", "(Ljava/lang/String;)V", "getIotUpdate", "()Ljava/lang/Integer;", "setIotUpdate", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class AdRespIotPushMsg {
    private String iotMsg;
    private Integer iotUpdate;

    public AdRespIotPushMsg(String str, Integer num) {
        this.iotMsg = str;
        this.iotUpdate = num;
    }

    public final String getIotMsg() {
        return this.iotMsg;
    }

    public final Integer getIotUpdate() {
        return this.iotUpdate;
    }

    public final void setIotMsg(String str) {
        this.iotMsg = str;
    }

    public final void setIotUpdate(Integer num) {
        this.iotUpdate = num;
    }
}
