package org.nanohttpd.protocols.util;

import java.lang.Throwable;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public interface IFactoryThrowing<T, E extends Throwable> {
    T create() throws Throwable;
}
