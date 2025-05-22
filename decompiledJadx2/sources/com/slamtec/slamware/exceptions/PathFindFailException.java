package com.slamtec.slamware.exceptions;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class PathFindFailException extends Exception {
    public PathFindFailException() {
        super("Failed to find path.");
    }

    public PathFindFailException(String str) {
        super(str);
    }
}
