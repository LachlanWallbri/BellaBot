package io.minio.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Initiator", strict = false)
/* loaded from: classes7.dex */
public class Initiator {

    @Element(name = "DisplayName", required = false)
    private String displayName;

    /* renamed from: id */
    @Element(name = "ID", required = false)
    private String f8439id;

    /* renamed from: id */
    public String m3924id() {
        return this.f8439id;
    }

    public String displayName() {
        return this.displayName;
    }
}
