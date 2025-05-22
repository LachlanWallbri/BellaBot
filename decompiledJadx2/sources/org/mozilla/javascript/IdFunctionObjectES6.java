package org.mozilla.javascript;

import org.simpleframework.xml.strategy.Name;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class IdFunctionObjectES6 extends IdFunctionObject {
    private static final int Id_length = 1;
    private static final int Id_name = 3;
    private boolean myLength;
    private boolean myName;

    public IdFunctionObjectES6(IdFunctionCall idFunctionCall, Object obj, int i, String str, int i2, Scriptable scriptable) {
        super(idFunctionCall, obj, i, str, i2, scriptable);
        this.myLength = true;
        this.myName = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.IdScriptableObject
    public int findInstanceIdInfo(String str) {
        return str.equals(Name.LENGTH) ? instanceIdInfo(3, 1) : str.equals("name") ? instanceIdInfo(3, 3) : super.findInstanceIdInfo(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.IdScriptableObject
    public Object getInstanceIdValue(int i) {
        if (i == 1 && !this.myLength) {
            return NOT_FOUND;
        }
        if (i == 3 && !this.myName) {
            return NOT_FOUND;
        }
        return super.getInstanceIdValue(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.mozilla.javascript.BaseFunction, org.mozilla.javascript.IdScriptableObject
    public void setInstanceIdValue(int i, Object obj) {
        if (i == 1 && obj == NOT_FOUND) {
            this.myLength = false;
        } else if (i == 3 && obj == NOT_FOUND) {
            this.myName = false;
        } else {
            super.setInstanceIdValue(i, obj);
        }
    }
}
