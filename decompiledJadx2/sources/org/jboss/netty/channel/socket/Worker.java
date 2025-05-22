package org.jboss.netty.channel.socket;

/* loaded from: classes7.dex */
public interface Worker extends Runnable {
    void executeInIoThread(Runnable runnable);
}
