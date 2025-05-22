package org.mozilla.javascript;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class EcmaError extends RhinoException {
    static final long serialVersionUID = -6261226256957286699L;
    private String errorMessage;
    private String errorName;

    @Deprecated
    public Scriptable getErrorObject() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EcmaError(String str, String str2, String str3, int i, String str4, int i2) {
        recordErrorOrigin(str3, i, str4, i2);
        this.errorName = str;
        this.errorMessage = str2;
    }

    @Deprecated
    public EcmaError(Scriptable scriptable, String str, int i, int i2, String str2) {
        this("InternalError", ScriptRuntime.toString(scriptable), str, i, str2, i2);
    }

    @Override // org.mozilla.javascript.RhinoException
    public String details() {
        return this.errorName + ": " + this.errorMessage;
    }

    public String getName() {
        return this.errorName;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    @Deprecated
    public String getSourceName() {
        return sourceName();
    }

    @Deprecated
    public int getLineNumber() {
        return lineNumber();
    }

    @Deprecated
    public int getColumnNumber() {
        return columnNumber();
    }

    @Deprecated
    public String getLineSource() {
        return lineSource();
    }
}
