package com.amazonaws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.metrics.RequestMetricCollector;

/* loaded from: classes.dex */
public abstract class AmazonWebServiceRequest implements Cloneable {
    private AmazonWebServiceRequest cloneSource;
    private AWSCredentials credentials;
    private ProgressListener generalProgressListener;
    private final RequestClientOptions requestClientOptions = new RequestClientOptions();

    @Deprecated
    private RequestMetricCollector requestMetricCollector;

    public void setRequestCredentials(AWSCredentials aWSCredentials) {
        this.credentials = aWSCredentials;
    }

    public AWSCredentials getRequestCredentials() {
        return this.credentials;
    }

    public RequestClientOptions getRequestClientOptions() {
        return this.requestClientOptions;
    }

    @Deprecated
    public RequestMetricCollector getRequestMetricCollector() {
        return this.requestMetricCollector;
    }

    @Deprecated
    public void setRequestMetricCollector(RequestMetricCollector requestMetricCollector) {
        this.requestMetricCollector = requestMetricCollector;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public <T extends AmazonWebServiceRequest> T withRequestMetricCollector(RequestMetricCollector requestMetricCollector) {
        setRequestMetricCollector(requestMetricCollector);
        return this;
    }

    public void setGeneralProgressListener(ProgressListener progressListener) {
        this.generalProgressListener = progressListener;
    }

    public ProgressListener getGeneralProgressListener() {
        return this.generalProgressListener;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T extends AmazonWebServiceRequest> T withGeneralProgressListener(ProgressListener progressListener) {
        setGeneralProgressListener(progressListener);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final <T extends AmazonWebServiceRequest> T copyBaseTo(T t) {
        t.setGeneralProgressListener(this.generalProgressListener);
        t.setRequestMetricCollector(this.requestMetricCollector);
        return t;
    }

    public AmazonWebServiceRequest getCloneSource() {
        return this.cloneSource;
    }

    public AmazonWebServiceRequest getCloneRoot() {
        AmazonWebServiceRequest amazonWebServiceRequest = this.cloneSource;
        if (amazonWebServiceRequest != null) {
            while (amazonWebServiceRequest.getCloneSource() != null) {
                amazonWebServiceRequest = amazonWebServiceRequest.getCloneSource();
            }
        }
        return amazonWebServiceRequest;
    }

    private void setCloneSource(AmazonWebServiceRequest amazonWebServiceRequest) {
        this.cloneSource = amazonWebServiceRequest;
    }

    @Override // 
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public AmazonWebServiceRequest mo4269clone() {
        try {
            AmazonWebServiceRequest amazonWebServiceRequest = (AmazonWebServiceRequest) super.clone();
            amazonWebServiceRequest.setCloneSource(this);
            return amazonWebServiceRequest;
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Got a CloneNotSupportedException from Object.clone() even though we're Cloneable!", e);
        }
    }
}
