package com.google.auth.oauth2;

import com.google.common.base.Preconditions;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class AwsDates {
    private static final String HTTP_DATE_FORMAT = "E, dd MMM yyyy HH:mm:ss z";
    private static final String X_AMZ_DATE_FORMAT = "yyyyMMdd'T'HHmmss'Z'";
    private final String originalDate;
    private final String xAmzDate;

    private AwsDates(String str) {
        this.xAmzDate = (String) Preconditions.checkNotNull(str);
        this.originalDate = str;
    }

    private AwsDates(String str, String str2) {
        this.xAmzDate = (String) Preconditions.checkNotNull(str);
        this.originalDate = (String) Preconditions.checkNotNull(str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getOriginalDate() {
        return this.originalDate;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getXAmzDate() {
        return this.xAmzDate;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getFormattedDate() {
        return this.xAmzDate.substring(0, 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AwsDates fromXAmzDate(String str) throws ParseException {
        new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'").parse(str);
        return new AwsDates(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AwsDates fromDateHeader(String str) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return new AwsDates(simpleDateFormat.format(new SimpleDateFormat(HTTP_DATE_FORMAT).parse(str)), str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AwsDates generateXAmzDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return new AwsDates(simpleDateFormat.format(new Date(System.currentTimeMillis())));
    }
}
