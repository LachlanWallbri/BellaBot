package org.apache.http.client.methods;

import java.net.URI;
import org.apache.http.HttpRequest;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public interface HttpUriRequest extends HttpRequest {
    void abort() throws UnsupportedOperationException;

    String getMethod();

    URI getURI();

    boolean isAborted();
}
