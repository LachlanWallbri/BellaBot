package com.google.gson.internal.reflect;

import java.lang.reflect.AccessibleObject;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes3.dex
 */
/* loaded from: classes.dex */
final class PreJava9ReflectionAccessor extends ReflectionAccessor {
    @Override // com.google.gson.internal.reflect.ReflectionAccessor
    public void makeAccessible(AccessibleObject accessibleObject) {
        accessibleObject.setAccessible(true);
    }
}
