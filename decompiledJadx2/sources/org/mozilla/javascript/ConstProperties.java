package org.mozilla.javascript;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public interface ConstProperties {
    void defineConst(String str, Scriptable scriptable);

    boolean isConst(String str);

    void putConst(String str, Scriptable scriptable, Object obj);
}
