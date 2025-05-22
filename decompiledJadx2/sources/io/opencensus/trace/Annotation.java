package io.opencensus.trace;

import io.opencensus.internal.Utils;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class Annotation {
    private static final Map<String, AttributeValue> EMPTY_ATTRIBUTES = Collections.unmodifiableMap(Collections.emptyMap());

    public abstract Map<String, AttributeValue> getAttributes();

    public abstract String getDescription();

    public static Annotation fromDescription(String str) {
        return new AutoValue_Annotation(str, EMPTY_ATTRIBUTES);
    }

    public static Annotation fromDescriptionAndAttributes(String str, Map<String, AttributeValue> map) {
        return new AutoValue_Annotation(str, Collections.unmodifiableMap(new HashMap((Map) Utils.checkNotNull(map, "attributes"))));
    }
}
