package io.grpc.netty.shaded.io.netty.util;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ReferenceCounted {
    int refCnt();

    boolean release();

    boolean release(int i);

    ReferenceCounted retain();

    ReferenceCounted retain(int i);

    ReferenceCounted touch();

    ReferenceCounted touch(Object obj);
}
