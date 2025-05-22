package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "Tagging", strict = false)
/* loaded from: classes7.dex */
public class Tags {
    private static final int MAX_KEY_LENGTH = 128;
    private static final int MAX_OBJECT_TAG_COUNT = 10;
    private static final int MAX_TAG_COUNT = 50;
    private static final int MAX_VALUE_LENGTH = 256;

    @ElementMap(attribute = false, entry = "Tag", inline = true, key = "Key", required = false, value = "Value")
    @Path("TagSet")
    Map<String, String> tags;

    public Tags() {
    }

    private Tags(Map<String, String> map, boolean z) throws IllegalArgumentException {
        if (map == null) {
            return;
        }
        int i = z ? 10 : 50;
        if (map.size() > i) {
            StringBuilder sb = new StringBuilder();
            sb.append("too many ");
            sb.append(z ? "object" : OSSConfig.PARAM_BUCKET);
            sb.append(" tags; allowed = ");
            sb.append(i);
            sb.append(", found = ");
            sb.append(map.size());
            throw new IllegalArgumentException(sb.toString());
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key.length() == 0 || key.length() > 128 || key.contains("&")) {
                throw new IllegalArgumentException("invalid tag key '" + key + "'");
            }
            String value = entry.getValue();
            if (value.length() > 256 || value.contains("&")) {
                throw new IllegalArgumentException("invalid tag value '" + value + "'");
            }
        }
        this.tags = Collections.unmodifiableMap(map);
    }

    public static Tags newBucketTags(Map<String, String> map) throws IllegalArgumentException {
        return new Tags(map, false);
    }

    public static Tags newObjectTags(Map<String, String> map) throws IllegalArgumentException {
        return new Tags(map, true);
    }

    public Map<String, String> get() {
        Map map = this.tags;
        if (map == null) {
            map = new HashMap();
        }
        return Collections.unmodifiableMap(map);
    }
}
