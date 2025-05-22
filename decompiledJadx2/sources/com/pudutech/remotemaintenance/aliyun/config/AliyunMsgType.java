package com.pudutech.remotemaintenance.aliyun.config;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AliyunMsgType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/aliyun/config/AliyunMsgType;", "", "type", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getType", "()Ljava/lang/String;", "setType", "(Ljava/lang/String;)V", "COMMON", "RRPC", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public enum AliyunMsgType {
    COMMON("common"),
    RRPC("rrpc");

    private String type;

    AliyunMsgType(String str) {
        this.type = str;
    }

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.type = str;
    }
}
