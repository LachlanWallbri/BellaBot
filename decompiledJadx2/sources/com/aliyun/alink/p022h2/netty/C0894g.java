package com.aliyun.alink.p022h2.netty;

import java.lang.Thread;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ThreadFactoryBuilder.java */
/* renamed from: com.aliyun.alink.h2.netty.g */
/* loaded from: classes.dex */
public final class C0894g {

    /* renamed from: a */
    private String f611a = null;

    /* renamed from: b */
    private Boolean f612b = null;

    /* renamed from: c */
    private Integer f613c = null;

    /* renamed from: d */
    private Thread.UncaughtExceptionHandler f614d = null;

    /* renamed from: e */
    private ThreadFactory f615e = null;

    /* renamed from: a */
    public C0894g m275a(String str) {
        m274b(str, 0);
        this.f611a = str;
        return this;
    }

    /* renamed from: a */
    public C0894g m276a(boolean z) {
        this.f612b = Boolean.valueOf(z);
        return this;
    }

    /* renamed from: a */
    public ThreadFactory m277a() {
        return m273a(this);
    }

    /* renamed from: a */
    private static ThreadFactory m273a(C0894g c0894g) {
        final String str = c0894g.f611a;
        final Boolean bool = c0894g.f612b;
        final Integer num = c0894g.f613c;
        final Thread.UncaughtExceptionHandler uncaughtExceptionHandler = c0894g.f614d;
        ThreadFactory threadFactory = c0894g.f615e;
        if (threadFactory == null) {
            threadFactory = Executors.defaultThreadFactory();
        }
        final ThreadFactory threadFactory2 = threadFactory;
        final AtomicLong atomicLong = str != null ? new AtomicLong(0L) : null;
        return new ThreadFactory() { // from class: com.aliyun.alink.h2.netty.g.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread newThread = threadFactory2.newThread(runnable);
                String str2 = str;
                if (str2 != null) {
                    newThread.setName(C0894g.m274b(str2, Long.valueOf(atomicLong.getAndIncrement())));
                }
                Boolean bool2 = bool;
                if (bool2 != null) {
                    newThread.setDaemon(bool2.booleanValue());
                }
                Integer num2 = num;
                if (num2 != null) {
                    newThread.setPriority(num2.intValue());
                }
                Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = uncaughtExceptionHandler;
                if (uncaughtExceptionHandler2 != null) {
                    newThread.setUncaughtExceptionHandler(uncaughtExceptionHandler2);
                }
                return newThread;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static String m274b(String str, Object... objArr) {
        return String.format(Locale.ROOT, str, objArr);
    }
}
