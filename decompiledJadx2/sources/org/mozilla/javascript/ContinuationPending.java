package org.mozilla.javascript;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class ContinuationPending extends RuntimeException {
    private static final long serialVersionUID = 4956008116771118856L;
    private Object applicationState;
    private NativeContinuation continuationState;

    /* JADX INFO: Access modifiers changed from: protected */
    public ContinuationPending(NativeContinuation nativeContinuation) {
        this.continuationState = nativeContinuation;
    }

    public Object getContinuation() {
        return this.continuationState;
    }

    public void setContinuation(NativeContinuation nativeContinuation) {
        this.continuationState = nativeContinuation;
    }

    NativeContinuation getContinuationState() {
        return this.continuationState;
    }

    public void setApplicationState(Object obj) {
        this.applicationState = obj;
    }

    public Object getApplicationState() {
        return this.applicationState;
    }
}
