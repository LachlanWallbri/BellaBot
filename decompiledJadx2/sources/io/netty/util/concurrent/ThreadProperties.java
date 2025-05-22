package io.netty.util.concurrent;

import java.lang.Thread;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface ThreadProperties {
    /* renamed from: id */
    long mo3945id();

    boolean isAlive();

    boolean isDaemon();

    boolean isInterrupted();

    String name();

    int priority();

    StackTraceElement[] stackTrace();

    Thread.State state();
}
