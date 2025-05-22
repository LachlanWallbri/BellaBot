package com.aliyun.alink.linksdk.tmp.device.payload;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class EventNotifyData {
    protected List<KeyValuePair> args;
    protected String name;

    public String getName() {
        return this.name;
    }

    public EventNotifyData setName(String str) {
        this.name = str;
        return this;
    }

    public List<KeyValuePair> getArgs() {
        return this.args;
    }

    public EventNotifyData setArgs(List<KeyValuePair> list) {
        this.args = list;
        return this;
    }
}
