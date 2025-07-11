package com.alibaba.fastjson.asm;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class MethodCollector {
    protected boolean debugInfoPresent;
    private final int ignoreCount;
    private final int paramCount;
    private final StringBuffer result = new StringBuffer();
    private int currentParameter = 0;

    /* JADX INFO: Access modifiers changed from: protected */
    public MethodCollector(int i, int i2) {
        this.ignoreCount = i;
        this.paramCount = i2;
        this.debugInfoPresent = i2 == 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void visitLocalVariable(String str, int i) {
        int i2 = this.ignoreCount;
        if (i < i2 || i >= i2 + this.paramCount) {
            return;
        }
        if (!str.equals("arg" + this.currentParameter)) {
            this.debugInfoPresent = true;
        }
        this.result.append(',');
        this.result.append(str);
        this.currentParameter++;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getResult() {
        return this.result.length() != 0 ? this.result.substring(1) : "";
    }
}
