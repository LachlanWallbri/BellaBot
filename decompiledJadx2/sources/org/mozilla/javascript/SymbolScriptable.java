package org.mozilla.javascript;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public interface SymbolScriptable {
    void delete(Symbol symbol);

    Object get(Symbol symbol, Scriptable scriptable);

    boolean has(Symbol symbol, Scriptable scriptable);

    void put(Symbol symbol, Scriptable scriptable, Object obj);
}
