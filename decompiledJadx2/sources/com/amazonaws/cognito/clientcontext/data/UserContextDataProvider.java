package com.amazonaws.cognito.clientcontext.data;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import com.amazonaws.cognito.clientcontext.datacollection.ContextDataAggregator;
import com.amazonaws.cognito.clientcontext.util.SignatureGenerator;
import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import java.util.Map;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class UserContextDataProvider {
    private static final String TAG = UserContextDataProvider.class.getSimpleName();
    public static final String VERSION = "ANDROID20171114";
    private ContextDataAggregator aggregator;
    private SignatureGenerator signatureGenerator;

    /* loaded from: classes.dex */
    private static class InstanceHolder {
        private static final UserContextDataProvider INSTANCE = new UserContextDataProvider();

        private InstanceHolder() {
        }
    }

    private UserContextDataProvider() {
        this(ContextDataAggregator.getInstance(), new SignatureGenerator());
    }

    protected UserContextDataProvider(ContextDataAggregator contextDataAggregator, SignatureGenerator signatureGenerator) {
        this.aggregator = contextDataAggregator;
        this.signatureGenerator = signatureGenerator;
    }

    public static UserContextDataProvider getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public String getEncodedContextData(Context context, String str, String str2, String str3) {
        new JSONObject();
        try {
            String jSONObject = getJsonPayload(this.aggregator.getAggregatedData(context), str, str2).toString();
            return getEncodedResponse(getJsonResponse(jSONObject, this.signatureGenerator.getSignature(jSONObject, str3, VERSION)));
        } catch (Exception unused) {
            Log.e(TAG, "Exception in creating JSON from context data");
            return null;
        }
    }

    private JSONObject getJsonPayload(Map<String, String> map, String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("contextData", new JSONObject(map));
        jSONObject.put(CognitoUserPoolsSignInProvider.AttributeKeys.USERNAME, str);
        jSONObject.put("userPoolId", str2);
        jSONObject.put("timestamp", getTimestamp());
        return jSONObject;
    }

    protected String getTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    private JSONObject getJsonResponse(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(MqttServiceConstants.PAYLOAD, str);
        jSONObject.put("signature", str2);
        jSONObject.put("version", VERSION);
        return jSONObject;
    }

    protected String getEncodedResponse(JSONObject jSONObject) {
        return Base64.encodeToString(jSONObject.toString().getBytes(ConfigurationConstant.DEFAULT_CHARSET), 0);
    }

    /* loaded from: classes.dex */
    private class ContextDataJsonKeys {
        private static final String CONTEXT_DATA = "contextData";
        private static final String DATA_PAYLOAD = "payload";
        private static final String SIGNATURE = "signature";
        private static final String TIMESTAMP_MILLI_SEC = "timestamp";
        private static final String USERNAME = "username";
        private static final String USER_POOL_ID = "userPoolId";
        private static final String VERSION = "version";

        private ContextDataJsonKeys() {
        }
    }
}
