package com.aliyun.alink.linksdk.tools.log;

import com.alibaba.fastjson.JSON;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class Request<T> {

    /* renamed from: id */
    public String f1041id;
    public String method;
    public T params;
    public String version;

    private Request(Builder<T> builder) {
        this.f1041id = null;
        this.version = null;
        this.method = null;
        this.params = null;
        this.f1041id = String.valueOf(IDGenerater.generateId());
        this.version = ((Builder) builder).version;
        this.method = ((Builder) builder).method;
        this.params = (T) ((Builder) builder).params;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class Builder<K> {
        private String method;
        private K params;
        private String version;

        public Builder version(String str) {
            this.version = str;
            return this;
        }

        public Builder method(String str) {
            this.method = str;
            return this;
        }

        public Builder params(K k) {
            this.params = k;
            return this;
        }

        public Request<K> build() {
            return new Request<>(this);
        }
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
