package com.slamtec.slamware.exceptions;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class UnauthorizedRequestException extends Exception {
    public UnauthorizedRequestException() {
        super("Unauthorized request.");
    }

    public UnauthorizedRequestException(String str) {
        super(str);
    }
}
