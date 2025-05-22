package org.jboss.netty.util;

/* loaded from: classes7.dex */
public interface Timeout {
    void cancel();

    TimerTask getTask();

    Timer getTimer();

    boolean isCancelled();

    boolean isExpired();
}
