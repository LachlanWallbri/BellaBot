package org.apache.http.client.methods;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicMarkableReference;
import org.apache.http.HttpRequest;
import org.apache.http.client.utils.CloneUtils;
import org.apache.http.concurrent.Cancellable;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionReleaseTrigger;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.HeaderGroup;
import org.apache.http.params.HttpParams;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public abstract class AbstractExecutionAwareRequest extends AbstractHttpMessage implements HttpExecutionAware, AbortableHttpRequest, Cloneable, HttpRequest {
    private final AtomicMarkableReference<Cancellable> cancellableRef = new AtomicMarkableReference<>(null, false);

    @Override // org.apache.http.client.methods.AbortableHttpRequest
    @Deprecated
    public void setConnectionRequest(final ClientConnectionRequest clientConnectionRequest) {
        setCancellable(new Cancellable() { // from class: org.apache.http.client.methods.AbstractExecutionAwareRequest.1
            @Override // org.apache.http.concurrent.Cancellable
            public boolean cancel() {
                clientConnectionRequest.abortRequest();
                return true;
            }
        });
    }

    @Override // org.apache.http.client.methods.AbortableHttpRequest
    @Deprecated
    public void setReleaseTrigger(final ConnectionReleaseTrigger connectionReleaseTrigger) {
        setCancellable(new Cancellable() { // from class: org.apache.http.client.methods.AbstractExecutionAwareRequest.2
            @Override // org.apache.http.concurrent.Cancellable
            public boolean cancel() {
                try {
                    connectionReleaseTrigger.abortConnection();
                    return true;
                } catch (IOException unused) {
                    return false;
                }
            }
        });
    }

    @Override // org.apache.http.client.methods.AbortableHttpRequest
    public void abort() {
        while (!this.cancellableRef.isMarked()) {
            Cancellable reference = this.cancellableRef.getReference();
            if (this.cancellableRef.compareAndSet(reference, reference, false, true) && reference != null) {
                reference.cancel();
            }
        }
    }

    @Override // org.apache.http.client.methods.HttpExecutionAware
    public boolean isAborted() {
        return this.cancellableRef.isMarked();
    }

    @Override // org.apache.http.client.methods.HttpExecutionAware
    public void setCancellable(Cancellable cancellable) {
        if (this.cancellableRef.compareAndSet(this.cancellableRef.getReference(), cancellable, false, false)) {
            return;
        }
        cancellable.cancel();
    }

    public Object clone() throws CloneNotSupportedException {
        AbstractExecutionAwareRequest abstractExecutionAwareRequest = (AbstractExecutionAwareRequest) super.clone();
        abstractExecutionAwareRequest.headergroup = (HeaderGroup) CloneUtils.cloneObject(this.headergroup);
        abstractExecutionAwareRequest.params = (HttpParams) CloneUtils.cloneObject(this.params);
        return abstractExecutionAwareRequest;
    }

    @Deprecated
    public void completed() {
        this.cancellableRef.set(null, false);
    }

    public void reset() {
        boolean isMarked;
        Cancellable reference;
        do {
            isMarked = this.cancellableRef.isMarked();
            reference = this.cancellableRef.getReference();
            if (reference != null) {
                reference.cancel();
            }
        } while (!this.cancellableRef.compareAndSet(reference, null, isMarked, false));
    }
}
