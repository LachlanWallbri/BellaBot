package org.apache.http;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class ContentTooLongException extends IOException {
    private static final long serialVersionUID = -924287689552495383L;

    public ContentTooLongException(String str) {
        super(str);
    }

    public ContentTooLongException(String str, Object... objArr) {
        super(HttpException.clean(String.format(str, objArr)));
    }
}
