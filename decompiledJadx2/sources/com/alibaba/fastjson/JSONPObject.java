package com.alibaba.fastjson;

import com.alibaba.fastjson.serializer.JSONSerializable;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class JSONPObject implements JSONSerializable {
    private static final int BrowserSecureMask = SerializerFeature.BrowserSecure.mask;
    public static String SECURITY_PREFIX = "/**/";
    private String function;
    private final List<Object> parameters = new ArrayList();

    public JSONPObject() {
    }

    public JSONPObject(String str) {
        this.function = str;
    }

    public String getFunction() {
        return this.function;
    }

    public void setFunction(String str) {
        this.function = str;
    }

    public List<Object> getParameters() {
        return this.parameters;
    }

    public void addParameter(Object obj) {
        this.parameters.add(obj);
    }

    public String toJSONString() {
        return toString();
    }

    @Override // com.alibaba.fastjson.serializer.JSONSerializable
    public void write(JSONSerializer jSONSerializer, Object obj, Type type, int i) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        int i2 = BrowserSecureMask;
        if ((i & i2) != 0 || serializeWriter.isEnabled(i2)) {
            serializeWriter.write(SECURITY_PREFIX);
        }
        serializeWriter.write(this.function);
        serializeWriter.write(40);
        for (int i3 = 0; i3 < this.parameters.size(); i3++) {
            if (i3 != 0) {
                serializeWriter.write(44);
            }
            jSONSerializer.write(this.parameters.get(i3));
        }
        serializeWriter.write(41);
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
