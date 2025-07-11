package com.google.api;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class FieldBehaviorProto {
    public static final int FIELD_BEHAVIOR_FIELD_NUMBER = 1052;
    public static final GeneratedMessage.GeneratedExtension<DescriptorProtos.FieldOptions, List<FieldBehavior>> fieldBehavior = GeneratedMessage.newFileScopedGeneratedExtension(FieldBehavior.class, null);
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u001fgoogle/api/field_behavior.proto\u0012\ngoogle.api\u001a google/protobuf/descriptor.proto*\u008f\u0001\n\rFieldBehavior\u0012\u001e\n\u001aFIELD_BEHAVIOR_UNSPECIFIED\u0010\u0000\u0012\f\n\bOPTIONAL\u0010\u0001\u0012\f\n\bREQUIRED\u0010\u0002\u0012\u000f\n\u000bOUTPUT_ONLY\u0010\u0003\u0012\u000e\n\nINPUT_ONLY\u0010\u0004\u0012\r\n\tIMMUTABLE\u0010\u0005\u0012\u0012\n\u000eUNORDERED_LIST\u0010\u0006:Q\n\u000efield_behavior\u0012\u001d.google.protobuf.FieldOptions\u0018\u009c\b \u0003(\u000e2\u0019.google.api.FieldBehaviorBp\n\u000ecom.google.apiB\u0012FieldBehaviorProtoP\u0001ZAgoogle.golang.org/genproto/googleapis/api/annotations;annotations¢\u0002\u0004GAPIb\u0006proto3"}, new Descriptors.FileDescriptor[]{DescriptorProtos.getDescriptor()});

    private FieldBehaviorProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
        extensionRegistryLite.add(fieldBehavior);
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    static {
        fieldBehavior.internalInit(descriptor.getExtensions().get(0));
        DescriptorProtos.getDescriptor();
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }
}
