package io.minio.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Owner", strict = false)
/* loaded from: classes7.dex */
public class Owner {

    @Element(name = "DisplayName", required = false)
    private String displayName;

    /* renamed from: id */
    @Element(name = "ID", required = false)
    private String f8442id;

    /* renamed from: id */
    public String m3927id() {
        return this.f8442id;
    }

    public String displayName() {
        return this.displayName;
    }
}
