package org.apache.http.impl.client;

import java.util.concurrent.FutureTask;
import org.apache.http.client.methods.HttpUriRequest;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class HttpRequestFutureTask<V> extends FutureTask<V> {
    private final HttpRequestTaskCallable<V> callable;
    private final HttpUriRequest request;

    public HttpRequestFutureTask(HttpUriRequest httpUriRequest, HttpRequestTaskCallable<V> httpRequestTaskCallable) {
        super(httpRequestTaskCallable);
        this.request = httpUriRequest;
        this.callable = httpRequestTaskCallable;
    }

    @Override // java.util.concurrent.FutureTask, java.util.concurrent.Future
    public boolean cancel(boolean z) {
        this.callable.cancel();
        if (z) {
            this.request.abort();
        }
        return super.cancel(z);
    }

    public long scheduledTime() {
        return this.callable.getScheduled();
    }

    public long startedTime() {
        return this.callable.getStarted();
    }

    public long endedTime() {
        if (isDone()) {
            return this.callable.getEnded();
        }
        throw new IllegalStateException("Task is not done yet");
    }

    public long requestDuration() {
        if (isDone()) {
            return endedTime() - startedTime();
        }
        throw new IllegalStateException("Task is not done yet");
    }

    public long taskDuration() {
        if (isDone()) {
            return endedTime() - scheduledTime();
        }
        throw new IllegalStateException("Task is not done yet");
    }

    @Override // java.util.concurrent.FutureTask
    public String toString() {
        return this.request.getRequestLine().getUri();
    }
}
