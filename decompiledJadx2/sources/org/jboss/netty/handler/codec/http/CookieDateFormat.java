package org.jboss.netty.handler.codec.http;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes7.dex */
final class CookieDateFormat extends SimpleDateFormat {
    private static final long serialVersionUID = 1789486337887402640L;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CookieDateFormat() {
        super("E, d-MMM-y HH:mm:ss z", Locale.ENGLISH);
        setTimeZone(TimeZone.getTimeZone("GMT"));
    }
}
