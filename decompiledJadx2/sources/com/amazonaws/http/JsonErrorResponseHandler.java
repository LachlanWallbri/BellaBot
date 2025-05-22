package com.amazonaws.http;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.JsonUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes.dex */
public class JsonErrorResponseHandler implements HttpResponseHandler<AmazonServiceException> {
    private static final int HTTP_STATUS_INTERNAL_SERVER_ERROR = 500;
    private static final String X_AMZN_ERROR_TYPE = "x-amzn-ErrorType";
    private final List<? extends JsonErrorUnmarshaller> unmarshallerList;

    @Override // com.amazonaws.http.HttpResponseHandler
    public boolean needsConnectionLeftOpen() {
        return false;
    }

    public JsonErrorResponseHandler(List<? extends JsonErrorUnmarshaller> list) {
        this.unmarshallerList = list;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazonaws.http.HttpResponseHandler
    public AmazonServiceException handle(HttpResponse httpResponse) throws Exception {
        try {
            JsonErrorResponse fromResponse = JsonErrorResponse.fromResponse(httpResponse);
            AmazonServiceException runErrorUnmarshallers = runErrorUnmarshallers(fromResponse);
            if (runErrorUnmarshallers == null) {
                return null;
            }
            runErrorUnmarshallers.setStatusCode(httpResponse.getStatusCode());
            if (httpResponse.getStatusCode() < 500) {
                runErrorUnmarshallers.setErrorType(AmazonServiceException.ErrorType.Client);
            } else {
                runErrorUnmarshallers.setErrorType(AmazonServiceException.ErrorType.Service);
            }
            runErrorUnmarshallers.setErrorCode(fromResponse.getErrorCode());
            for (Map.Entry<String, String> entry : httpResponse.getHeaders().entrySet()) {
                if ("X-Amzn-RequestId".equalsIgnoreCase(entry.getKey())) {
                    runErrorUnmarshallers.setRequestId(entry.getValue());
                }
            }
            return runErrorUnmarshallers;
        } catch (IOException e) {
            throw new AmazonClientException("Unable to parse error response", e);
        }
    }

    private AmazonServiceException runErrorUnmarshallers(JsonErrorResponse jsonErrorResponse) throws Exception {
        for (JsonErrorUnmarshaller jsonErrorUnmarshaller : this.unmarshallerList) {
            if (jsonErrorUnmarshaller.match(jsonErrorResponse)) {
                return jsonErrorUnmarshaller.unmarshall(jsonErrorResponse);
            }
        }
        return null;
    }

    /* loaded from: classes.dex */
    public static final class JsonErrorResponse {
        private final String errorCode;
        private final Map<String, String> map;
        private final String message = get("message");
        private final int statusCode;

        private JsonErrorResponse(int i, String str, Map<String, String> map) {
            this.statusCode = i;
            this.errorCode = str;
            this.map = map;
        }

        public int getStatusCode() {
            return this.statusCode;
        }

        public String getErrorCode() {
            return this.errorCode;
        }

        public String getMessage() {
            return this.message;
        }

        public String get(String str) {
            if (str == null || str.length() == 0) {
                return null;
            }
            String str2 = StringUtils.lowerCase(str.substring(0, 1)) + str.substring(1);
            String str3 = StringUtils.upperCase(str.substring(0, 1)) + str.substring(1);
            if (this.map.containsKey(str3)) {
                return this.map.get(str3);
            }
            return this.map.containsKey(str2) ? this.map.get(str2) : "";
        }

        public static JsonErrorResponse fromResponse(HttpResponse httpResponse) throws IOException {
            int statusCode = httpResponse.getStatusCode();
            Map<String, String> jsonToStringMapWithList = JsonUtils.jsonToStringMapWithList(new BufferedReader(new InputStreamReader(httpResponse.getContent(), StringUtils.UTF8)));
            String str = httpResponse.getHeaders().get(JsonErrorResponseHandler.X_AMZN_ERROR_TYPE);
            if (str != null) {
                int indexOf = str.indexOf(58);
                if (indexOf != -1) {
                    str = str.substring(0, indexOf);
                }
            } else if (jsonToStringMapWithList.containsKey("__type")) {
                String str2 = jsonToStringMapWithList.get("__type");
                str = str2.substring(str2.lastIndexOf(MqttTopic.MULTI_LEVEL_WILDCARD) + 1);
            }
            return new JsonErrorResponse(statusCode, str, jsonToStringMapWithList);
        }
    }
}
