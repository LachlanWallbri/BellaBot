package com.aliyun.alink.linksdk.tmp.device.payload;

import com.airbnb.lottie.utils.Utils;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CommonRequestPayload<T> {

    /* renamed from: id */
    protected String f1026id;
    protected String method;
    protected T params;
    protected String sessionKey;
    protected String version;

    public CommonRequestPayload(String str, String str2) {
        this();
    }

    public CommonRequestPayload() {
        this.version = "1.0";
        this.f1026id = String.valueOf(new SecureRandom().nextInt(Utils.SECOND_IN_NANOS));
    }

    public T getParams() {
        return this.params;
    }

    public void setParams(T t) {
        this.params = t;
    }

    public String getSessionKey() {
        return this.sessionKey;
    }

    public void setSessionKey(String str) {
        this.sessionKey = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getId() {
        return this.f1026id;
    }

    public void setId(String str) {
        this.f1026id = str;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String str) {
        this.method = str;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class CommonRequestPayloadJsonDeSerializer implements JsonDeserializer<CommonRequestPayload> {
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.gson.JsonDeserializer
        public CommonRequestPayload deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject asJsonObject;
            Object obj = null;
            if (jsonElement == null || !jsonElement.isJsonObject() || (asJsonObject = jsonElement.getAsJsonObject()) == null) {
                return null;
            }
            CommonRequestPayload commonRequestPayload = new CommonRequestPayload();
            JsonElement jsonElement2 = asJsonObject.get("params");
            if (jsonElement2 != null) {
                if (jsonElement2.isJsonArray()) {
                    obj = jsonDeserializationContext.deserialize(jsonElement2, new TypeToken<List<String>>() { // from class: com.aliyun.alink.linksdk.tmp.device.payload.CommonRequestPayload.CommonRequestPayloadJsonDeSerializer.1
                    }.getType());
                } else if (jsonElement2.isJsonObject()) {
                    obj = jsonDeserializationContext.deserialize(jsonElement2, new TypeToken<Map<String, ValueWrapper>>() { // from class: com.aliyun.alink.linksdk.tmp.device.payload.CommonRequestPayload.CommonRequestPayloadJsonDeSerializer.2
                    }.getType());
                } else {
                    obj = jsonDeserializationContext.deserialize(jsonElement2, new TypeToken<Object>() { // from class: com.aliyun.alink.linksdk.tmp.device.payload.CommonRequestPayload.CommonRequestPayloadJsonDeSerializer.3
                    }.getType());
                }
            }
            commonRequestPayload.setParams(obj);
            JsonElement jsonElement3 = asJsonObject.get("id");
            if (jsonElement3 != null && jsonElement3.isJsonPrimitive()) {
                commonRequestPayload.setId(jsonElement3.getAsJsonPrimitive().getAsString());
            }
            JsonElement jsonElement4 = asJsonObject.get("version");
            if (jsonElement4 != null && jsonElement4.isJsonPrimitive()) {
                commonRequestPayload.setVersion(jsonElement4.getAsJsonPrimitive().getAsString());
            }
            JsonElement jsonElement5 = asJsonObject.get("method");
            if (jsonElement5 != null && jsonElement5.isJsonPrimitive()) {
                commonRequestPayload.setMethod(jsonElement5.getAsJsonPrimitive().getAsString());
            }
            return commonRequestPayload;
        }
    }
}
