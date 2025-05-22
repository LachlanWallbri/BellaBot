package org.nanohttpd.protocols.http.content;

import com.amazonaws.util.DateUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class Cookie {

    /* renamed from: e */
    private final String f10285e;

    /* renamed from: n */
    private final String f10286n;

    /* renamed from: v */
    private final String f10287v;

    public static String getHTTPTime(int i) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.RFC822_DATE_PATTERN, Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        calendar.add(5, i);
        return simpleDateFormat.format(calendar.getTime());
    }

    public Cookie(String str, String str2) {
        this(str, str2, 30);
    }

    public Cookie(String str, String str2, int i) {
        this.f10286n = str;
        this.f10287v = str2;
        this.f10285e = getHTTPTime(i);
    }

    public Cookie(String str, String str2, String str3) {
        this.f10286n = str;
        this.f10287v = str2;
        this.f10285e = str3;
    }

    public String getHTTPHeader() {
        return String.format("%s=%s; expires=%s", this.f10286n, this.f10287v, this.f10285e);
    }
}
