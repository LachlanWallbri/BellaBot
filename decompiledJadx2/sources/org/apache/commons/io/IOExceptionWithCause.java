package org.apache.commons.io;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
@Deprecated
/* loaded from: classes2.dex */
public class IOExceptionWithCause extends IOException {
    private static final long serialVersionUID = 1;

    public IOExceptionWithCause(String str, Throwable th) {
        super(str, th);
    }

    public IOExceptionWithCause(Throwable th) {
        super(th);
    }
}
