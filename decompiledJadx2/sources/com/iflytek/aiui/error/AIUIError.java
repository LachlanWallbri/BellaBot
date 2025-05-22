package com.iflytek.aiui.error;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class AIUIError extends Exception {
    private static final long serialVersionUID = 1;

    /* renamed from: a */
    private int f2195a;

    /* renamed from: b */
    private String f2196b;

    public AIUIError(int i) {
        this.f2195a = 0;
        this.f2196b = "";
        this.f2195a = i;
        if (20006 == i) {
            this.f2196b = "record audio error.";
        }
    }

    public AIUIError(int i, String str) {
        this.f2195a = 0;
        this.f2196b = "";
        this.f2195a = i;
        this.f2196b = str;
    }

    public String getDes() {
        return this.f2196b;
    }

    public int getErrorCode() {
        return this.f2195a;
    }

    public void setDes(String str) {
        this.f2196b = str;
    }

    public void setErrorCode(int i) {
        this.f2195a = i;
    }
}
