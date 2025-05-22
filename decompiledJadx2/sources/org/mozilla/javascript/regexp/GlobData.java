package org.mozilla.javascript.regexp;

import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* compiled from: RegExpImpl.java */
/* loaded from: classes2.dex */
final class GlobData {
    Scriptable arrayobj;
    StringBuilder charBuf;
    int dollar = -1;
    boolean global;
    Function lambda;
    int leftIndex;
    int mode;
    String repstr;
    String str;
}
