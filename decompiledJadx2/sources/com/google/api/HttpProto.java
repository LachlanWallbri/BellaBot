package com.google.api;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class HttpProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0015google/api/http.proto\u0012\ngoogle.api\"T\n\u0004Http\u0012#\n\u0005rules\u0018\u0001 \u0003(\u000b2\u0014.google.api.HttpRule\u0012'\n\u001ffully_decode_reserved_expansion\u0018\u0002 \u0001(\b\"\u0081\u0002\n\bHttpRule\u0012\u0010\n\bselector\u0018\u0001 \u0001(\t\u0012\r\n\u0003get\u0018\u0002 \u0001(\tH\u0000\u0012\r\n\u0003put\u0018\u0003 \u0001(\tH\u0000\u0012\u000e\n\u0004post\u0018\u0004 \u0001(\tH\u0000\u0012\u0010\n\u0006delete\u0018\u0005 \u0001(\tH\u0000\u0012\u000f\n\u0005patch\u0018\u0006 \u0001(\tH\u0000\u0012/\n\u0006custom\u0018\b \u0001(\u000b2\u001d.google.api.CustomHttpPatternH\u0000\u0012\f\n\u0004body\u0018\u0007 \u0001(\t\u0012\u0015\n\rresponse_body\u0018\f \u0001(\t\u00121\n\u0013additional_bindings\u0018\u000b \u0003(\u000b2\u0014.google.api.HttpRuleB\t\n\u0007pattern\"/\n\u0011CustomHttpPattern\u0012\f\n\u0004kind\u0018\u0001 \u0001(\t\u0012\f\n\u0004path\u0018\u0002 \u0001(\tBj\n\u000ecom.google.apiB\tHttpProtoP\u0001ZAgoogle.golang.org/genproto/googleapis/api/annotations;annotationsø\u0001\u0001¢\u0002\u0004GAPIb\u0006proto3"}, new Descriptors.FileDescriptor[0]);
    static final Descriptors.Descriptor internal_static_google_api_Http_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_Http_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_Http_descriptor, new String[]{"Rules", "FullyDecodeReservedExpansion"});
    static final Descriptors.Descriptor internal_static_google_api_HttpRule_descriptor = getDescriptor().getMessageTypes().get(1);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_HttpRule_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_HttpRule_descriptor, new String[]{"Selector", "Get", "Put", "Post", "Delete", "Patch", TypedValues.Custom.NAME, "Body", "ResponseBody", "AdditionalBindings", "Pattern"});
    static final Descriptors.Descriptor internal_static_google_api_CustomHttpPattern_descriptor = getDescriptor().getMessageTypes().get(2);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_CustomHttpPattern_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_CustomHttpPattern_descriptor, new String[]{"Kind", "Path"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private HttpProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }
}
