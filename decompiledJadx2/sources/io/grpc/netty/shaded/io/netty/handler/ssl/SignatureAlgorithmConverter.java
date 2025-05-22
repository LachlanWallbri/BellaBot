package io.grpc.netty.shaded.io.netty.handler.ssl;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class SignatureAlgorithmConverter {
    private static final Pattern PATTERN = Pattern.compile("(?:(^[a-zA-Z].+)With(.+)Encryption$)|(?:(^[a-zA-Z].+)(?:_with_|-with-|_pkcs1_|_pss_rsae_)(.+$))|(?:(^[a-zA-Z].+)_(.+$))");

    private SignatureAlgorithmConverter() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String toJavaName(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = PATTERN.matcher(str);
        if (matcher.matches()) {
            String group = matcher.group(1);
            if (group != null) {
                return group.toUpperCase(Locale.ROOT) + JsonPOJOBuilder.DEFAULT_WITH_PREFIX + matcher.group(2).toUpperCase(Locale.ROOT);
            }
            if (matcher.group(3) != null) {
                return matcher.group(4).toUpperCase(Locale.ROOT) + JsonPOJOBuilder.DEFAULT_WITH_PREFIX + matcher.group(3).toUpperCase(Locale.ROOT);
            }
            if (matcher.group(5) != null) {
                return matcher.group(6).toUpperCase(Locale.ROOT) + JsonPOJOBuilder.DEFAULT_WITH_PREFIX + matcher.group(5).toUpperCase(Locale.ROOT);
            }
        }
        return null;
    }
}
