package com.aliyun.alink.linksdk.tools.log;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class IDGenerater {

    /* renamed from: id */
    private static AtomicInteger f1040id = new AtomicInteger();

    public static int generateId() {
        return f1040id.incrementAndGet();
    }
}
