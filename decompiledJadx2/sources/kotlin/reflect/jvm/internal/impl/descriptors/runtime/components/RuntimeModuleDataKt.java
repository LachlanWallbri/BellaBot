package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.ReflectionTypes;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassesTracker;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaPropertyInitializerEvaluator;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache;
import kotlin.reflect.jvm.internal.impl.load.java.components.SamConversionResolver;
import kotlin.reflect.jvm.internal.impl.load.java.components.SignaturePropagator;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverComponents;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverSettings;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaPackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ModuleClassResolver;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.BinaryClassAnnotationAndConstantLoaderImpl;
import kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializationComponentsForJava;
import kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializedDescriptorResolver;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JavaClassDataFinder;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder;
import kotlin.reflect.jvm.internal.impl.load.kotlin.PackagePartProvider;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ContractDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.utils.Jsr305State;

/* compiled from: RuntimeModuleData.kt */
/* loaded from: classes8.dex */
public final class RuntimeModuleDataKt {
    public static final LazyJavaPackageFragmentProvider makeLazyJavaPackageFragmentFromClassLoaderProvider(ClassLoader classLoader, ModuleDescriptor module, StorageManager storageManager, NotFoundClasses notFoundClasses, KotlinClassFinder reflectKotlinClassFinder, DeserializedDescriptorResolver deserializedDescriptorResolver, ModuleClassResolver singleModuleClassResolver, PackagePartProvider packagePartProvider) {
        Intrinsics.checkParameterIsNotNull(classLoader, "classLoader");
        Intrinsics.checkParameterIsNotNull(module, "module");
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(notFoundClasses, "notFoundClasses");
        Intrinsics.checkParameterIsNotNull(reflectKotlinClassFinder, "reflectKotlinClassFinder");
        Intrinsics.checkParameterIsNotNull(deserializedDescriptorResolver, "deserializedDescriptorResolver");
        Intrinsics.checkParameterIsNotNull(singleModuleClassResolver, "singleModuleClassResolver");
        Intrinsics.checkParameterIsNotNull(packagePartProvider, "packagePartProvider");
        AnnotationTypeQualifierResolver annotationTypeQualifierResolver = new AnnotationTypeQualifierResolver(storageManager, Jsr305State.DISABLED);
        ReflectJavaClassFinder reflectJavaClassFinder = new ReflectJavaClassFinder(classLoader);
        SignaturePropagator signaturePropagator = SignaturePropagator.DO_NOTHING;
        Intrinsics.checkExpressionValueIsNotNull(signaturePropagator, "SignaturePropagator.DO_NOTHING");
        RuntimeErrorReporter runtimeErrorReporter = RuntimeErrorReporter.INSTANCE;
        JavaResolverCache javaResolverCache = JavaResolverCache.EMPTY;
        Intrinsics.checkExpressionValueIsNotNull(javaResolverCache, "JavaResolverCache.EMPTY");
        return new LazyJavaPackageFragmentProvider(new JavaResolverComponents(storageManager, reflectJavaClassFinder, reflectKotlinClassFinder, deserializedDescriptorResolver, signaturePropagator, runtimeErrorReporter, javaResolverCache, JavaPropertyInitializerEvaluator.DoNothing.INSTANCE, SamConversionResolver.Empty.INSTANCE, RuntimeSourceElementFactory.INSTANCE, singleModuleClassResolver, packagePartProvider, SupertypeLoopChecker.EMPTY.INSTANCE, LookupTracker.DO_NOTHING.INSTANCE, module, new ReflectionTypes(module, notFoundClasses), annotationTypeQualifierResolver, new SignatureEnhancement(annotationTypeQualifierResolver, Jsr305State.DISABLED), JavaClassesTracker.Default.INSTANCE, JavaResolverSettings.Default.INSTANCE, NewKotlinTypeChecker.Companion.getDefault()));
    }

    public static final DeserializationComponentsForJava makeDeserializationComponentsForJava(ModuleDescriptor module, StorageManager storageManager, NotFoundClasses notFoundClasses, LazyJavaPackageFragmentProvider lazyJavaPackageFragmentProvider, KotlinClassFinder reflectKotlinClassFinder, DeserializedDescriptorResolver deserializedDescriptorResolver) {
        Intrinsics.checkParameterIsNotNull(module, "module");
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(notFoundClasses, "notFoundClasses");
        Intrinsics.checkParameterIsNotNull(lazyJavaPackageFragmentProvider, "lazyJavaPackageFragmentProvider");
        Intrinsics.checkParameterIsNotNull(reflectKotlinClassFinder, "reflectKotlinClassFinder");
        Intrinsics.checkParameterIsNotNull(deserializedDescriptorResolver, "deserializedDescriptorResolver");
        return new DeserializationComponentsForJava(storageManager, module, DeserializationConfiguration.Default.INSTANCE, new JavaClassDataFinder(reflectKotlinClassFinder, deserializedDescriptorResolver), new BinaryClassAnnotationAndConstantLoaderImpl(module, notFoundClasses, storageManager, reflectKotlinClassFinder), lazyJavaPackageFragmentProvider, notFoundClasses, RuntimeErrorReporter.INSTANCE, LookupTracker.DO_NOTHING.INSTANCE, ContractDeserializer.Companion.getDEFAULT(), NewKotlinTypeChecker.Companion.getDefault());
    }
}
