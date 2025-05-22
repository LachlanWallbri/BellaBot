package com.slamtec.slamware.exceptions;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class ConnectionLostException extends Exception {
    public ConnectionLostException() {
        super("Connection Lost.");
    }

    public ConnectionLostException(String str) {
        super(str);
    }
}
