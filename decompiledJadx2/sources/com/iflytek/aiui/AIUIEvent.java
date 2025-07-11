package com.iflytek.aiui;

import android.os.Bundle;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public final class AIUIEvent implements Cloneable {
    public int arg1;
    public int arg2;
    public Bundle data;
    public int eventType;
    public String info;

    public AIUIEvent() {
    }

    public AIUIEvent(int i, int i2, int i3, String str, Bundle bundle) {
        this.eventType = i;
        this.arg1 = i2;
        this.arg2 = i3;
        this.info = str;
        this.data = bundle;
    }

    public Object clone() throws CloneNotSupportedException {
        AIUIEvent aIUIEvent = (AIUIEvent) super.clone();
        aIUIEvent.data = (Bundle) this.data.clone();
        return aIUIEvent;
    }
}
