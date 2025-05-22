package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.TuplesKt;
import kotlin._Assertions;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.InvalidModuleException;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.MultiTargetPlatform;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: ModuleDescriptorImpl.kt */
/* loaded from: classes2.dex */
public final class ModuleDescriptorImpl extends DeclarationDescriptorImpl implements ModuleDescriptor {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ModuleDescriptorImpl.class), "packageFragmentProviderForWholeModuleWithDependencies", "getPackageFragmentProviderForWholeModuleWithDependencies()Lorg/jetbrains/kotlin/descriptors/impl/CompositePackageFragmentProvider;"))};
    private final KotlinBuiltIns builtIns;
    private final Map<ModuleDescriptor.Capability<? extends Object>, Object> capabilities;
    private ModuleDependencies dependencies;
    private boolean isValid;
    private PackageFragmentProvider packageFragmentProviderForModuleContent;
    private final Lazy packageFragmentProviderForWholeModuleWithDependencies$delegate;
    private final MemoizedFunctionToNotNull<FqName, PackageViewDescriptor> packages;
    private final Name stableName;
    private final StorageManager storageManager;

    public ModuleDescriptorImpl(Name name, StorageManager storageManager, KotlinBuiltIns kotlinBuiltIns, MultiTargetPlatform multiTargetPlatform) {
        this(name, storageManager, kotlinBuiltIns, multiTargetPlatform, null, null, 48, null);
    }

    private final CompositePackageFragmentProvider getPackageFragmentProviderForWholeModuleWithDependencies() {
        Lazy lazy = this.packageFragmentProviderForWholeModuleWithDependencies$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (CompositePackageFragmentProvider) lazy.getValue();
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> visitor, D d) {
        Intrinsics.checkParameterIsNotNull(visitor, "visitor");
        return (R) ModuleDescriptor.DefaultImpls.accept(this, visitor, d);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public DeclarationDescriptor getContainingDeclaration() {
        return ModuleDescriptor.DefaultImpls.getContainingDeclaration(this);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
    public KotlinBuiltIns getBuiltIns() {
        return this.builtIns;
    }

    public /* synthetic */ ModuleDescriptorImpl(Name name, StorageManager storageManager, KotlinBuiltIns kotlinBuiltIns, MultiTargetPlatform multiTargetPlatform, Map map, Name name2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(name, storageManager, kotlinBuiltIns, (i & 8) != 0 ? (MultiTargetPlatform) null : multiTargetPlatform, (i & 16) != 0 ? MapsKt.emptyMap() : map, (i & 32) != 0 ? (Name) null : name2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModuleDescriptorImpl(Name moduleName, StorageManager storageManager, KotlinBuiltIns builtIns, MultiTargetPlatform multiTargetPlatform, Map<ModuleDescriptor.Capability<?>, ? extends Object> capabilities, Name name) {
        super(Annotations.Companion.getEMPTY(), moduleName);
        Map mapOf;
        Intrinsics.checkParameterIsNotNull(moduleName, "moduleName");
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(builtIns, "builtIns");
        Intrinsics.checkParameterIsNotNull(capabilities, "capabilities");
        this.storageManager = storageManager;
        this.builtIns = builtIns;
        this.stableName = name;
        if (!moduleName.isSpecial()) {
            throw new IllegalArgumentException("Module name must be special: " + moduleName);
        }
        this.capabilities = MapsKt.plus(capabilities, (multiTargetPlatform == null || (mapOf = MapsKt.mapOf(TuplesKt.m3968to(MultiTargetPlatform.CAPABILITY, multiTargetPlatform))) == null) ? MapsKt.emptyMap() : mapOf);
        this.isValid = true;
        this.packages = this.storageManager.createMemoizedFunction(new Function1<FqName, LazyPackageViewDescriptorImpl>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDescriptorImpl$packages$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final LazyPackageViewDescriptorImpl invoke(FqName fqName) {
                StorageManager storageManager2;
                Intrinsics.checkParameterIsNotNull(fqName, "fqName");
                ModuleDescriptorImpl moduleDescriptorImpl = ModuleDescriptorImpl.this;
                storageManager2 = moduleDescriptorImpl.storageManager;
                return new LazyPackageViewDescriptorImpl(moduleDescriptorImpl, fqName, storageManager2);
            }
        });
        this.packageFragmentProviderForWholeModuleWithDependencies$delegate = LazyKt.lazy(new Function0<CompositePackageFragmentProvider>() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDescriptorImpl$packageFragmentProviderForWholeModuleWithDependencies$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final CompositePackageFragmentProvider invoke() {
                ModuleDependencies moduleDependencies;
                String id;
                PackageFragmentProvider packageFragmentProvider;
                boolean isInitialized;
                String id2;
                String id3;
                String id4;
                moduleDependencies = ModuleDescriptorImpl.this.dependencies;
                if (moduleDependencies == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Dependencies of module ");
                    id = ModuleDescriptorImpl.this.getId();
                    sb.append(id);
                    sb.append(" were not set before querying module content");
                    throw new AssertionError(sb.toString());
                }
                List<ModuleDescriptorImpl> allDependencies = moduleDependencies.getAllDependencies();
                boolean contains = allDependencies.contains(ModuleDescriptorImpl.this);
                if (_Assertions.ENABLED && !contains) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Module ");
                    id4 = ModuleDescriptorImpl.this.getId();
                    sb2.append(id4);
                    sb2.append(" is not contained in his own dependencies, this is probably a misconfiguration");
                    throw new AssertionError(sb2.toString());
                }
                List<ModuleDescriptorImpl> list = allDependencies;
                for (ModuleDescriptorImpl moduleDescriptorImpl : list) {
                    isInitialized = moduleDescriptorImpl.isInitialized();
                    if (_Assertions.ENABLED && !isInitialized) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Dependency module ");
                        id2 = moduleDescriptorImpl.getId();
                        sb3.append(id2);
                        sb3.append(" was not initialized by the time contents of dependent module ");
                        id3 = ModuleDescriptorImpl.this.getId();
                        sb3.append(id3);
                        sb3.append(" were queried");
                        throw new AssertionError(sb3.toString());
                    }
                }
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    packageFragmentProvider = ((ModuleDescriptorImpl) it.next()).packageFragmentProviderForModuleContent;
                    if (packageFragmentProvider == null) {
                        Intrinsics.throwNpe();
                    }
                    arrayList.add(packageFragmentProvider);
                }
                return new CompositePackageFragmentProvider(arrayList);
            }
        });
    }

    public boolean isValid() {
        return this.isValid;
    }

    public void assertValid() {
        if (isValid()) {
            return;
        }
        throw new InvalidModuleException("Accessing invalid module descriptor " + this);
    }

    public List<ModuleDescriptor> getExpectedByModules() {
        ModuleDependencies moduleDependencies = this.dependencies;
        if (moduleDependencies != null) {
            return moduleDependencies.getExpectedByDependencies();
        }
        throw new AssertionError("Dependencies of module " + getId() + " were not set");
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
    public PackageViewDescriptor getPackage(FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        assertValid();
        return this.packages.invoke(fqName);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
    public Collection<FqName> getSubPackagesOf(FqName fqName, Function1<? super Name, Boolean> nameFilter) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        Intrinsics.checkParameterIsNotNull(nameFilter, "nameFilter");
        assertValid();
        return getPackageFragmentProvider().getSubPackagesOf(fqName, nameFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isInitialized() {
        return this.packageFragmentProviderForModuleContent != null;
    }

    public final void setDependencies(ModuleDependencies dependencies) {
        Intrinsics.checkParameterIsNotNull(dependencies, "dependencies");
        boolean z = this.dependencies == null;
        if (!_Assertions.ENABLED || z) {
            this.dependencies = dependencies;
            return;
        }
        throw new AssertionError("Dependencies of " + getId() + " were already set");
    }

    public final void setDependencies(ModuleDescriptorImpl... descriptors) {
        Intrinsics.checkParameterIsNotNull(descriptors, "descriptors");
        setDependencies(ArraysKt.toList(descriptors));
    }

    public final void setDependencies(List<ModuleDescriptorImpl> descriptors) {
        Intrinsics.checkParameterIsNotNull(descriptors, "descriptors");
        setDependencies(descriptors, SetsKt.emptySet());
    }

    public final void setDependencies(List<ModuleDescriptorImpl> descriptors, Set<ModuleDescriptorImpl> friends) {
        Intrinsics.checkParameterIsNotNull(descriptors, "descriptors");
        Intrinsics.checkParameterIsNotNull(friends, "friends");
        setDependencies(new ModuleDependenciesImpl(descriptors, friends, CollectionsKt.emptyList()));
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
    public boolean shouldSeeInternalsOf(ModuleDescriptor targetModule) {
        Intrinsics.checkParameterIsNotNull(targetModule, "targetModule");
        if (!Intrinsics.areEqual(this, targetModule)) {
            ModuleDependencies moduleDependencies = this.dependencies;
            if (moduleDependencies == null) {
                Intrinsics.throwNpe();
            }
            if (!CollectionsKt.contains(moduleDependencies.getModulesWhoseInternalsAreVisible(), targetModule) && !getExpectedByModules().contains(targetModule)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getId() {
        String name = getName().toString();
        Intrinsics.checkExpressionValueIsNotNull(name, "name.toString()");
        return name;
    }

    public final void initialize(PackageFragmentProvider providerForModuleContent) {
        Intrinsics.checkParameterIsNotNull(providerForModuleContent, "providerForModuleContent");
        boolean z = !isInitialized();
        if (!_Assertions.ENABLED || z) {
            this.packageFragmentProviderForModuleContent = providerForModuleContent;
            return;
        }
        throw new AssertionError("Attempt to initialize module " + getId() + " twice");
    }

    public final PackageFragmentProvider getPackageFragmentProvider() {
        assertValid();
        return getPackageFragmentProviderForWholeModuleWithDependencies();
    }
}
