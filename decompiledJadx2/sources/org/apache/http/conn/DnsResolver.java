package org.apache.http.conn;

import java.net.InetAddress;
import java.net.UnknownHostException;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public interface DnsResolver {
    InetAddress[] resolve(String str) throws UnknownHostException;
}
