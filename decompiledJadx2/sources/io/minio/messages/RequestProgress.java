package io.minio.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "RequestProgress", strict = false)
/* loaded from: classes7.dex */
public class RequestProgress {

    @Element(name = "Enabled")
    private boolean enabled = true;
}
