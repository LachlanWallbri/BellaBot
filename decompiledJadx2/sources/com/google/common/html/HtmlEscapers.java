package com.google.common.html;

import com.google.common.escape.Escaper;
import com.google.common.escape.Escapers;
import kotlin.text.Typography;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public final class HtmlEscapers {
    private static final Escaper HTML_ESCAPER = Escapers.builder().addEscape('\"', "&quot;").addEscape('\'', "&#39;").addEscape(Typography.amp, "&amp;").addEscape(Typography.less, "&lt;").addEscape(Typography.greater, "&gt;").build();

    public static Escaper htmlEscaper() {
        return HTML_ESCAPER;
    }

    private HtmlEscapers() {
    }
}
