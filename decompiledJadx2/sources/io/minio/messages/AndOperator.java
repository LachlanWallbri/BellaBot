package io.minio.messages;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;

@Root(name = "And")
/* loaded from: classes7.dex */
public class AndOperator {

    @Element(name = "Prefix", required = false)
    @Convert(PrefixConverter.class)
    private String prefix;

    @ElementMap(attribute = false, entry = "Tag", inline = true, key = "Key", required = false, value = "Value")
    private Map<String, String> tags;

    public AndOperator(@Element(name = "Prefix", required = false) @Nullable String str, @Nullable @ElementMap(attribute = false, entry = "Tag", inline = true, key = "Key", required = false, value = "Value") Map<String, String> map) {
        if (str == null && map == null) {
            throw new IllegalArgumentException("At least Prefix or Tags must be set");
        }
        if (map != null) {
            Iterator<String> it = map.keySet().iterator();
            while (it.hasNext()) {
                if (it.next().isEmpty()) {
                    throw new IllegalArgumentException("Tags must not contain empty key");
                }
            }
        }
        this.prefix = str;
        this.tags = map != null ? Collections.unmodifiableMap(map) : null;
    }

    public String prefix() {
        return this.prefix;
    }

    public Map<String, String> tags() {
        return this.tags;
    }
}
