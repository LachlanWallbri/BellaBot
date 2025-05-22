package io.minio.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "FilterRule", strict = false)
/* loaded from: classes7.dex */
public class FilterRule {

    @Element(name = "Name")
    private String name;

    @Element(name = "Value")
    private String value;

    public FilterRule() {
    }

    public FilterRule(String str, String str2) {
        this.name = str;
        this.value = str2;
    }

    public String name() {
        return this.name;
    }

    public String value() {
        return this.value;
    }
}
