package kotlin.reflect.jvm.internal.impl.serialization;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: SerializerExtensionProtocol.kt */
/* loaded from: classes2.dex */
public class SerializerExtensionProtocol {
    private final GeneratedMessageLite.GeneratedExtension<ProtoBuf.Class, List<ProtoBuf.Annotation>> classAnnotation;
    private final GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, ProtoBuf.Annotation.Argument.Value> compileTimeValue;
    private final GeneratedMessageLite.GeneratedExtension<ProtoBuf.Constructor, List<ProtoBuf.Annotation>> constructorAnnotation;
    private final GeneratedMessageLite.GeneratedExtension<ProtoBuf.EnumEntry, List<ProtoBuf.Annotation>> enumEntryAnnotation;
    private final ExtensionRegistryLite extensionRegistry;
    private final GeneratedMessageLite.GeneratedExtension<ProtoBuf.Function, List<ProtoBuf.Annotation>> functionAnnotation;
    private final GeneratedMessageLite.GeneratedExtension<ProtoBuf.Package, Integer> packageFqName;
    private final GeneratedMessageLite.GeneratedExtension<ProtoBuf.ValueParameter, List<ProtoBuf.Annotation>> parameterAnnotation;
    private final GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, List<ProtoBuf.Annotation>> propertyAnnotation;
    private final GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, List<ProtoBuf.Annotation>> propertyGetterAnnotation;
    private final GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, List<ProtoBuf.Annotation>> propertySetterAnnotation;
    private final GeneratedMessageLite.GeneratedExtension<ProtoBuf.Type, List<ProtoBuf.Annotation>> typeAnnotation;
    private final GeneratedMessageLite.GeneratedExtension<ProtoBuf.TypeParameter, List<ProtoBuf.Annotation>> typeParameterAnnotation;

    public SerializerExtensionProtocol(ExtensionRegistryLite extensionRegistry, GeneratedMessageLite.GeneratedExtension<ProtoBuf.Package, Integer> packageFqName, GeneratedMessageLite.GeneratedExtension<ProtoBuf.Constructor, List<ProtoBuf.Annotation>> constructorAnnotation, GeneratedMessageLite.GeneratedExtension<ProtoBuf.Class, List<ProtoBuf.Annotation>> classAnnotation, GeneratedMessageLite.GeneratedExtension<ProtoBuf.Function, List<ProtoBuf.Annotation>> functionAnnotation, GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, List<ProtoBuf.Annotation>> propertyAnnotation, GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, List<ProtoBuf.Annotation>> propertyGetterAnnotation, GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, List<ProtoBuf.Annotation>> propertySetterAnnotation, GeneratedMessageLite.GeneratedExtension<ProtoBuf.EnumEntry, List<ProtoBuf.Annotation>> enumEntryAnnotation, GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, ProtoBuf.Annotation.Argument.Value> compileTimeValue, GeneratedMessageLite.GeneratedExtension<ProtoBuf.ValueParameter, List<ProtoBuf.Annotation>> parameterAnnotation, GeneratedMessageLite.GeneratedExtension<ProtoBuf.Type, List<ProtoBuf.Annotation>> typeAnnotation, GeneratedMessageLite.GeneratedExtension<ProtoBuf.TypeParameter, List<ProtoBuf.Annotation>> typeParameterAnnotation) {
        Intrinsics.checkParameterIsNotNull(extensionRegistry, "extensionRegistry");
        Intrinsics.checkParameterIsNotNull(packageFqName, "packageFqName");
        Intrinsics.checkParameterIsNotNull(constructorAnnotation, "constructorAnnotation");
        Intrinsics.checkParameterIsNotNull(classAnnotation, "classAnnotation");
        Intrinsics.checkParameterIsNotNull(functionAnnotation, "functionAnnotation");
        Intrinsics.checkParameterIsNotNull(propertyAnnotation, "propertyAnnotation");
        Intrinsics.checkParameterIsNotNull(propertyGetterAnnotation, "propertyGetterAnnotation");
        Intrinsics.checkParameterIsNotNull(propertySetterAnnotation, "propertySetterAnnotation");
        Intrinsics.checkParameterIsNotNull(enumEntryAnnotation, "enumEntryAnnotation");
        Intrinsics.checkParameterIsNotNull(compileTimeValue, "compileTimeValue");
        Intrinsics.checkParameterIsNotNull(parameterAnnotation, "parameterAnnotation");
        Intrinsics.checkParameterIsNotNull(typeAnnotation, "typeAnnotation");
        Intrinsics.checkParameterIsNotNull(typeParameterAnnotation, "typeParameterAnnotation");
        this.extensionRegistry = extensionRegistry;
        this.packageFqName = packageFqName;
        this.constructorAnnotation = constructorAnnotation;
        this.classAnnotation = classAnnotation;
        this.functionAnnotation = functionAnnotation;
        this.propertyAnnotation = propertyAnnotation;
        this.propertyGetterAnnotation = propertyGetterAnnotation;
        this.propertySetterAnnotation = propertySetterAnnotation;
        this.enumEntryAnnotation = enumEntryAnnotation;
        this.compileTimeValue = compileTimeValue;
        this.parameterAnnotation = parameterAnnotation;
        this.typeAnnotation = typeAnnotation;
        this.typeParameterAnnotation = typeParameterAnnotation;
    }

    public final ExtensionRegistryLite getExtensionRegistry() {
        return this.extensionRegistry;
    }

    public final GeneratedMessageLite.GeneratedExtension<ProtoBuf.Constructor, List<ProtoBuf.Annotation>> getConstructorAnnotation() {
        return this.constructorAnnotation;
    }

    public final GeneratedMessageLite.GeneratedExtension<ProtoBuf.Class, List<ProtoBuf.Annotation>> getClassAnnotation() {
        return this.classAnnotation;
    }

    public final GeneratedMessageLite.GeneratedExtension<ProtoBuf.Function, List<ProtoBuf.Annotation>> getFunctionAnnotation() {
        return this.functionAnnotation;
    }

    public final GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, List<ProtoBuf.Annotation>> getPropertyAnnotation() {
        return this.propertyAnnotation;
    }

    public final GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, List<ProtoBuf.Annotation>> getPropertyGetterAnnotation() {
        return this.propertyGetterAnnotation;
    }

    public final GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, List<ProtoBuf.Annotation>> getPropertySetterAnnotation() {
        return this.propertySetterAnnotation;
    }

    public final GeneratedMessageLite.GeneratedExtension<ProtoBuf.EnumEntry, List<ProtoBuf.Annotation>> getEnumEntryAnnotation() {
        return this.enumEntryAnnotation;
    }

    public final GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, ProtoBuf.Annotation.Argument.Value> getCompileTimeValue() {
        return this.compileTimeValue;
    }

    public final GeneratedMessageLite.GeneratedExtension<ProtoBuf.ValueParameter, List<ProtoBuf.Annotation>> getParameterAnnotation() {
        return this.parameterAnnotation;
    }

    public final GeneratedMessageLite.GeneratedExtension<ProtoBuf.Type, List<ProtoBuf.Annotation>> getTypeAnnotation() {
        return this.typeAnnotation;
    }

    public final GeneratedMessageLite.GeneratedExtension<ProtoBuf.TypeParameter, List<ProtoBuf.Annotation>> getTypeParameterAnnotation() {
        return this.typeParameterAnnotation;
    }
}
