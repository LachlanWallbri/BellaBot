package com.google.api.gax.grpc;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import io.grpc.CallOptions;
import io.grpc.Metadata;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class CallOptionsUtil {
    private static final CallOptions.Key<Map<Metadata.Key<String>, String>> DYNAMIC_HEADERS_CALL_OPTION_KEY = CallOptions.Key.createWithDefault("gax_dynamic_headers", Collections.emptyMap());
    static Metadata.Key<String> REQUEST_PARAMS_HEADER_KEY = Metadata.Key.m3901of("x-goog-request-params", Metadata.ASCII_STRING_MARSHALLER);
    private static final CallOptions.Key<ResponseMetadataHandler> METADATA_HANDLER_CALL_OPTION_KEY = CallOptions.Key.createWithDefault("gax_metadata_handler", null);

    private CallOptionsUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CallOptions putRequestParamsDynamicHeaderOption(CallOptions callOptions, String str) {
        if (callOptions == null || str.isEmpty()) {
            return callOptions;
        }
        return callOptions.withOption(DYNAMIC_HEADERS_CALL_OPTION_KEY, ImmutableMap.builder().putAll((Map) callOptions.getOption(DYNAMIC_HEADERS_CALL_OPTION_KEY)).put(REQUEST_PARAMS_HEADER_KEY, str).build());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<Metadata.Key<String>, String> getDynamicHeadersOption(CallOptions callOptions) {
        return (Map) callOptions.getOption(DYNAMIC_HEADERS_CALL_OPTION_KEY);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CallOptions putMetadataHandlerOption(CallOptions callOptions, ResponseMetadataHandler responseMetadataHandler) {
        Preconditions.checkNotNull(callOptions);
        Preconditions.checkNotNull(responseMetadataHandler);
        return callOptions.withOption(METADATA_HANDLER_CALL_OPTION_KEY, responseMetadataHandler);
    }

    public static ResponseMetadataHandler getMetadataHandlerOption(CallOptions callOptions) {
        return (ResponseMetadataHandler) callOptions.getOption(METADATA_HANDLER_CALL_OPTION_KEY);
    }
}
