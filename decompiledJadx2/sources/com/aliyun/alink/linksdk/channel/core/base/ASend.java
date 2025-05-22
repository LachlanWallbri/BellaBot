package com.aliyun.alink.linksdk.channel.core.base;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public abstract class ASend {
    protected final IOnCallListener listener;
    protected final ARequest request;
    protected AResponse response;
    protected ISendStatus status;

    public ASend(ARequest aRequest, IOnCallListener iOnCallListener) {
        this.response = null;
        this.status = null;
        this.request = aRequest;
        this.listener = iOnCallListener;
        this.status = ASendStatus.waitingToSend;
        this.response = new AResponse();
    }

    public ARequest getRequest() {
        return this.request;
    }

    public AResponse getResponse() {
        return this.response;
    }

    public IOnCallListener getListener() {
        return this.listener;
    }

    public ISendStatus getStatus() {
        return this.status;
    }
}
