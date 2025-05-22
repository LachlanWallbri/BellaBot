package io.minio;

import com.amazonaws.util.DateUtils;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/* loaded from: classes7.dex */
public class Time {
    public static final ZoneId UTC = ZoneId.of("Z");
    public static final DateTimeFormatter AMZ_DATE_FORMAT = DateTimeFormatter.ofPattern(DateUtils.COMPRESSED_DATE_PATTERN, Locale.US).withZone(UTC);
    public static final DateTimeFormatter RESPONSE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH':'mm':'ss'.'SSS'Z'", Locale.US).withZone(UTC);
    public static final DateTimeFormatter SIGNER_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd", Locale.US).withZone(UTC);
    public static final DateTimeFormatter HTTP_HEADER_DATE_FORMAT = DateTimeFormatter.ofPattern("EEE',' dd MMM yyyy HH':'mm':'ss 'GMT'", Locale.US).withZone(UTC);
    public static final DateTimeFormatter EXPIRATION_DATE_FORMAT = RESPONSE_DATE_FORMAT;

    private Time() {
    }
}
