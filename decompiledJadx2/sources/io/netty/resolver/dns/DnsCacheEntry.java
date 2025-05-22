package io.netty.resolver.dns;

import java.net.InetAddress;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface DnsCacheEntry {
    InetAddress address();

    Throwable cause();
}
