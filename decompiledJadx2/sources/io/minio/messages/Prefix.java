package io.minio.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "CommonPrefixes", strict = false)
/* loaded from: classes7.dex */
public class Prefix {

    @Element(name = "Prefix")
    private String prefix;

    public Item toItem() {
        return new Contents(this.prefix);
    }
}
