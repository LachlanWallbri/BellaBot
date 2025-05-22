package io.netty.handler.codec.smtp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
final class SmtpUtils {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<CharSequence> toUnmodifiableList(CharSequence... charSequenceArr) {
        if (charSequenceArr == null || charSequenceArr.length == 0) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(Arrays.asList(charSequenceArr));
    }

    private SmtpUtils() {
    }
}
