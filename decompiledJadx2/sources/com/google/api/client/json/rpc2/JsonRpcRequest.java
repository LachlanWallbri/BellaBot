package com.google.api.client.json.rpc2;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class JsonRpcRequest extends GenericData {

    /* renamed from: id */
    @Key
    private Object f1340id;

    @Key
    private final String jsonrpc = "2.0";

    @Key
    private String method;

    @Key
    private Object params;

    public String getVersion() {
        return "2.0";
    }

    public Object getId() {
        return this.f1340id;
    }

    public void setId(Object obj) {
        this.f1340id = obj;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String str) {
        this.method = str;
    }

    public Object getParameters() {
        return this.params;
    }

    public void setParameters(Object obj) {
        this.params = obj;
    }

    @Override // com.google.api.client.util.GenericData
    public JsonRpcRequest set(String str, Object obj) {
        return (JsonRpcRequest) super.set(str, obj);
    }

    @Override // com.google.api.client.util.GenericData, java.util.AbstractMap
    public JsonRpcRequest clone() {
        return (JsonRpcRequest) super.clone();
    }
}
