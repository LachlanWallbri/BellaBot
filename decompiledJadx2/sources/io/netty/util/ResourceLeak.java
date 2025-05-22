package io.netty.util;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
@Deprecated
/* loaded from: classes.dex */
public interface ResourceLeak {
    boolean close();

    void record();

    void record(Object obj);
}
