package io.opencensus.resource;

import io.opencensus.internal.StringUtils;
import io.opencensus.internal.Utils;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class Resource {
    private static final String ERROR_MESSAGE_INVALID_CHARS = " should be a ASCII string with a length greater than 0 and not exceed 255 characters.";
    private static final String ERROR_MESSAGE_INVALID_VALUE = " should be a ASCII string with a length not exceed 255 characters.";
    private static final String LABEL_KEY_VALUE_SPLITTER = "=";
    private static final String LABEL_LIST_SPLITTER = ",";
    static final int MAX_LENGTH = 255;
    private static final String OC_RESOURCE_TYPE_ENV = "OC_RESOURCE_TYPE";

    @Nullable
    private static final String ENV_TYPE = parseResourceType(System.getenv(OC_RESOURCE_TYPE_ENV));
    private static final String OC_RESOURCE_LABELS_ENV = "OC_RESOURCE_LABELS";
    private static final Map<String, String> ENV_LABEL_MAP = parseResourceLabels(System.getenv(OC_RESOURCE_LABELS_ENV));

    public abstract Map<String, String> getLabels();

    @Nullable
    public abstract String getType();

    public static Resource createFromEnvironmentVariables() {
        return createInternal(ENV_TYPE, ENV_LABEL_MAP);
    }

    public static Resource create(@Nullable String str, Map<String, String> map) {
        return createInternal(str, Collections.unmodifiableMap(new LinkedHashMap((Map) Utils.checkNotNull(map, "labels"))));
    }

    @Nullable
    public static Resource mergeResources(List<Resource> list) {
        Iterator<Resource> it = list.iterator();
        Resource resource = null;
        while (it.hasNext()) {
            resource = merge(resource, it.next());
        }
        return resource;
    }

    private static Resource createInternal(@Nullable String str, Map<String, String> map) {
        return new AutoValue_Resource(str, map);
    }

    @Nullable
    static String parseResourceType(@Nullable String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        Utils.checkArgument(isValidAndNotEmpty(str), "Type should be a ASCII string with a length greater than 0 and not exceed 255 characters.");
        return str.trim();
    }

    static Map<String, String> parseResourceLabels(@Nullable String str) {
        if (str == null) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap();
        for (String str2 : str.split(LABEL_LIST_SPLITTER, -1)) {
            String[] split = str2.split(LABEL_KEY_VALUE_SPLITTER, -1);
            if (split.length == 2) {
                String trim = split[0].trim();
                String replaceAll = split[1].trim().replaceAll("^\"|\"$", "");
                Utils.checkArgument(isValidAndNotEmpty(trim), "Label key should be a ASCII string with a length greater than 0 and not exceed 255 characters.");
                Utils.checkArgument(isValid(replaceAll), "Label value should be a ASCII string with a length not exceed 255 characters.");
                hashMap.put(trim, replaceAll);
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    @Nullable
    private static Resource merge(@Nullable Resource resource, @Nullable Resource resource2) {
        if (resource2 == null) {
            return resource;
        }
        if (resource == null) {
            return resource2;
        }
        String type = resource.getType() != null ? resource.getType() : resource2.getType();
        LinkedHashMap linkedHashMap = new LinkedHashMap(resource2.getLabels());
        for (Map.Entry<String, String> entry : resource.getLabels().entrySet()) {
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }
        return createInternal(type, Collections.unmodifiableMap(linkedHashMap));
    }

    private static boolean isValid(String str) {
        return str.length() <= 255 && StringUtils.isPrintableString(str);
    }

    private static boolean isValidAndNotEmpty(String str) {
        return !str.isEmpty() && isValid(str);
    }
}
