package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: AbstractDeserializedPackageFragmentProvider.kt */
/* loaded from: classes2.dex */
public abstract class AbstractDeserializedPackageFragmentProvider implements PackageFragmentProvider {
    protected DeserializationComponents components;
    private final KotlinMetadataFinder finder;
    private final MemoizedFunctionToNullable<FqName, PackageFragmentDescriptor> fragments;
    private final ModuleDescriptor moduleDescriptor;
    private final StorageManager storageManager;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract DeserializedPackageFragment findPackage(FqName fqName);

    public AbstractDeserializedPackageFragmentProvider(StorageManager storageManager, KotlinMetadataFinder finder, ModuleDescriptor moduleDescriptor) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(finder, "finder");
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "moduleDescriptor");
        this.storageManager = storageManager;
        this.finder = finder;
        this.moduleDescriptor = moduleDescriptor;
        this.fragments = storageManager.createMemoizedFunctionWithNullableValues(new Function1<FqName, DeserializedPackageFragment>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.AbstractDeserializedPackageFragmentProvider$fragments$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final DeserializedPackageFragment invoke(FqName fqName) {
                Intrinsics.checkParameterIsNotNull(fqName, "fqName");
                DeserializedPackageFragment findPackage = AbstractDeserializedPackageFragmentProvider.this.findPackage(fqName);
                if (findPackage == null) {
                    return null;
                }
                findPackage.initialize(AbstractDeserializedPackageFragmentProvider.this.getComponents());
                return findPackage;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final StorageManager getStorageManager() {
        return this.storageManager;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final KotlinMetadataFinder getFinder() {
        return this.finder;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ModuleDescriptor getModuleDescriptor() {
        return this.moduleDescriptor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final DeserializationComponents getComponents() {
        DeserializationComponents deserializationComponents = this.components;
        if (deserializationComponents == null) {
            Intrinsics.throwUninitializedPropertyAccessException("components");
        }
        return deserializationComponents;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setComponents(DeserializationComponents deserializationComponents) {
        Intrinsics.checkParameterIsNotNull(deserializationComponents, "<set-?>");
        this.components = deserializationComponents;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    public List<PackageFragmentDescriptor> getPackageFragments(FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        return CollectionsKt.listOfNotNull(this.fragments.invoke(fqName));
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    public Collection<FqName> getSubPackagesOf(FqName fqName, Function1<? super Name, Boolean> nameFilter) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        Intrinsics.checkParameterIsNotNull(nameFilter, "nameFilter");
        return SetsKt.emptySet();
    }
}
