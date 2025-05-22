package com.slamtec.slamware.exceptions;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class AlreadyConnectedException extends Exception {
    public AlreadyConnectedException() {
        super("Already Connected to the device.");
    }

    public AlreadyConnectedException(String str) {
        super(str);
    }
}
