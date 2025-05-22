package org.mozilla.javascript.debug;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public interface DebuggableScript {
    DebuggableScript getFunction(int i);

    int getFunctionCount();

    String getFunctionName();

    int[] getLineNumbers();

    int getParamAndVarCount();

    int getParamCount();

    String getParamOrVarName(int i);

    DebuggableScript getParent();

    String getSourceName();

    boolean isFunction();

    boolean isGeneratedScript();

    boolean isTopLevel();
}
