package com.google.api;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class SystemParameterProto {
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n!google/api/system_parameter.proto\u0012\ngoogle.api\"B\n\u0010SystemParameters\u0012.\n\u0005rules\u0018\u0001 \u0003(\u000b2\u001f.google.api.SystemParameterRule\"X\n\u0013SystemParameterRule\u0012\u0010\n\bselector\u0018\u0001 \u0001(\t\u0012/\n\nparameters\u0018\u0002 \u0003(\u000b2\u001b.google.api.SystemParameter\"Q\n\u000fSystemParameter\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0013\n\u000bhttp_header\u0018\u0002 \u0001(\t\u0012\u001b\n\u0013url_query_parameter\u0018\u0003 \u0001(\tBv\n\u000ecom.google.apiB\u0014SystemParameterProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig¢\u0002\u0004GAPIb\u0006proto3"}, new Descriptors.FileDescriptor[0]);
    static final Descriptors.Descriptor internal_static_google_api_SystemParameters_descriptor = getDescriptor().getMessageTypes().get(0);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_SystemParameters_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_SystemParameters_descriptor, new String[]{"Rules"});
    static final Descriptors.Descriptor internal_static_google_api_SystemParameterRule_descriptor = getDescriptor().getMessageTypes().get(1);

    /* renamed from: internal_static_google_api_SystemParameterRule_fieldAccessorTable */
    static final GeneratedMessageV3.FieldAccessorTable f1339xe30c9cd2 = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_SystemParameterRule_descriptor, new String[]{"Selector", "Parameters"});
    static final Descriptors.Descriptor internal_static_google_api_SystemParameter_descriptor = getDescriptor().getMessageTypes().get(2);
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_SystemParameter_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_api_SystemParameter_descriptor, new String[]{"Name", "HttpHeader", "UrlQueryParameter"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private SystemParameterProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }
}
