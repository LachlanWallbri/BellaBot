package io.grpc.netty.shaded.io.netty.util.concurrent;

import java.lang.Thread;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ThreadProperties {
    /* renamed from: id */
    long mo3921id();

    boolean isAlive();

    boolean isDaemon();

    boolean isInterrupted();

    String name();

    int priority();

    StackTraceElement[] stackTrace();

    Thread.State state();
}
