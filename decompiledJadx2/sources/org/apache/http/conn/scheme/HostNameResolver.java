package org.apache.http.conn.scheme;

import java.io.IOException;
import java.net.InetAddress;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
@Deprecated
/* loaded from: classes9.dex */
public interface HostNameResolver {
    InetAddress resolve(String str) throws IOException;
}
