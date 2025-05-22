package com.google.common.collect;

import com.google.errorprone.annotations.DoNotMock;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
@DoNotMock("Use ImmutableClassToInstanceMap or MutableClassToInstanceMap")
/* loaded from: classes3.dex */
public interface ClassToInstanceMap<B> extends Map<Class<? extends B>, B> {
    <T extends B> T getInstance(Class<T> cls);

    <T extends B> T putInstance(Class<T> cls, T t);
}
