package org.jboss.netty.handler.codec.http.multipart;

import java.io.IOException;

/* loaded from: classes7.dex */
public interface Attribute extends HttpData {
    String getValue() throws IOException;

    void setValue(String str) throws IOException;
}
