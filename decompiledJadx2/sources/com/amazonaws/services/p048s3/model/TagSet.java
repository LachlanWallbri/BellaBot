package com.amazonaws.services.p048s3.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class TagSet {
    private Map<String, String> tags = new HashMap(1);

    public TagSet() {
    }

    public TagSet(Map<String, String> map) {
        this.tags.putAll(map);
    }

    public String getTag(String str) {
        return this.tags.get(str);
    }

    public void setTag(String str, String str2) {
        this.tags.put(str, str2);
    }

    public Map<String, String> getAllTags() {
        return this.tags;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");
        stringBuffer.append("Tags: " + getAllTags());
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
