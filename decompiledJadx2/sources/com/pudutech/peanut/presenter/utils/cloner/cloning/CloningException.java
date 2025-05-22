package com.pudutech.peanut.presenter.utils.cloner.cloning;

/* loaded from: classes5.dex */
public class CloningException extends RuntimeException {
    private static final long serialVersionUID = 3815175312001146867L;

    public CloningException(String str, Throwable th) {
        super(str, th);
    }

    public CloningException(String str) {
        super(str);
    }

    public CloningException(Exception exc) {
        super(exc);
    }
}
