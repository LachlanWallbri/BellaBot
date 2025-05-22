package io.minio.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Object")
/* loaded from: classes7.dex */
public class DeleteObject {

    @Element(name = "Key")
    private String name;

    @Element(name = "VersionId", required = false)
    private String versionId;

    public DeleteObject(String str) {
        this.name = str;
    }

    public DeleteObject(String str, String str2) {
        this.name = str;
        this.versionId = str2;
    }
}
