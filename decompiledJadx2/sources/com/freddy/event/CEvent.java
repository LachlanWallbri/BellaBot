package com.freddy.event;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public class CEvent implements PooledObject {
    private int msgCode;
    private Object obj;
    private int resultCode;
    private String topic;

    public CEvent() {
    }

    public CEvent(String str, int i, int i2, Object obj) {
        this.topic = str;
        this.msgCode = i;
        this.resultCode = i2;
        this.obj = obj;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String str) {
        this.topic = str;
    }

    public int getMsgCode() {
        return this.msgCode;
    }

    public void setMsgCode(int i) {
        this.msgCode = i;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public Object getObj() {
        return this.obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    @Override // com.freddy.event.PooledObject
    public void reset() {
        this.topic = null;
        this.msgCode = 0;
        this.resultCode = 0;
        this.obj = null;
    }
}
