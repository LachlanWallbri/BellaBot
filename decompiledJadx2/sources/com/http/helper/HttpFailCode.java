package com.http.helper;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes.dex */
public class HttpFailCode extends Throwable {
    int code;

    public HttpFailCode(int i) {
        this.code = -1;
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }
}
