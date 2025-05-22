package com.slamtec.slamware.exceptions;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class ParseInvalidException extends Exception {
    public ParseInvalidException() {
        super("Failed to parse device data.");
    }

    public ParseInvalidException(String str) {
        super(str);
    }
}
