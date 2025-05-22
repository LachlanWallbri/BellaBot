package io.minio.messages;

import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import org.simpleframework.xml.Root;

@Root(name = JsonDocumentFields.VERSION, strict = false)
/* loaded from: classes7.dex */
public class Version extends Item {
    public Version() {
    }

    public Version(String str) {
        super(str);
    }
}
