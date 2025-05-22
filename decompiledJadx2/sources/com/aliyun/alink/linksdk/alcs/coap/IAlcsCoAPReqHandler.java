package com.aliyun.alink.linksdk.alcs.coap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IAlcsCoAPReqHandler {
    public static final int SUCCESS = 0;

    void onReqComplete(AlcsCoAPContext alcsCoAPContext, int i, AlcsCoAPResponse alcsCoAPResponse);
}
