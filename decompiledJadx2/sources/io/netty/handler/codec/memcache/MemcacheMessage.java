package io.netty.handler.codec.memcache;

import io.netty.util.ReferenceCounted;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface MemcacheMessage extends MemcacheObject, ReferenceCounted {
    MemcacheMessage retain();

    MemcacheMessage retain(int i);

    MemcacheMessage touch();

    MemcacheMessage touch(Object obj);
}
