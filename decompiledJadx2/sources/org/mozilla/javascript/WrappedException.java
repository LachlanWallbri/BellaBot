package org.mozilla.javascript;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class WrappedException extends EvaluatorException {
    static final long serialVersionUID = -1551979216966520648L;
    private Throwable exception;

    public WrappedException(Throwable th) {
        super("Wrapped " + th.toString());
        this.exception = th;
        Kit.initCause(this, th);
        int[] iArr = {0};
        String sourcePositionFromStack = Context.getSourcePositionFromStack(iArr);
        int i = iArr[0];
        if (sourcePositionFromStack != null) {
            initSourceName(sourcePositionFromStack);
        }
        if (i != 0) {
            initLineNumber(i);
        }
    }

    public Throwable getWrappedException() {
        return this.exception;
    }

    @Deprecated
    public Object unwrap() {
        return getWrappedException();
    }
}
