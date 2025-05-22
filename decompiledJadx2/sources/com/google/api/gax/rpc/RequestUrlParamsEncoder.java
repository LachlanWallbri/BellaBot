package com.google.api.gax.rpc;

import com.google.api.core.InternalApi;
import com.google.common.base.Preconditions;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi("For use by transport-specific implementations")
/* loaded from: classes2.dex */
public class RequestUrlParamsEncoder<RequestT> implements RequestParamsEncoder<RequestT> {
    private static final String STR_ENCODING = "UTF-8";
    private final RequestParamsExtractor<RequestT> paramsExtractor;
    private final boolean validateExtractedParameters;

    public RequestUrlParamsEncoder(RequestParamsExtractor<RequestT> requestParamsExtractor, boolean z) {
        this.paramsExtractor = (RequestParamsExtractor) Preconditions.checkNotNull(requestParamsExtractor);
        this.validateExtractedParameters = z;
    }

    @Override // com.google.api.gax.rpc.RequestParamsEncoder
    public String encode(RequestT requestt) {
        Map<String, String> extract = this.paramsExtractor.extract(requestt);
        if (extract.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : extract.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            String key = entry.getKey();
            String value = entry.getValue();
            if (key == null) {
                throw new IllegalArgumentException("Request parameter name cannot be null");
            }
            if (value != null) {
                if (!isValid(key, value)) {
                    throw new IllegalArgumentException("Invalid url-encoded request parameter name-value pair: " + key + "=" + value);
                }
                sb.append(key);
                sb.append("=");
                sb.append(value);
            }
        }
        return sb.toString();
    }

    private boolean isValid(String str, String str2) {
        try {
            if (this.validateExtractedParameters) {
                if (!str.equals(URLEncoder.encode(URLDecoder.decode(str, "UTF-8"), "UTF-8"))) {
                    return false;
                }
                if (!str2.equals(URLEncoder.encode(URLDecoder.decode(str2, "UTF-8"), "UTF-8"))) {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException unused) {
            return false;
        }
    }
}
