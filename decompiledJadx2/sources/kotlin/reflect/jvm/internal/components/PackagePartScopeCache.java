package kotlin.reflect.jvm.internal.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EmptyPackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializedDescriptorResolver;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinderKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ChainedMemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: PackagePartScopeCache.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rR\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m3961d2 = {"Lkotlin/reflect/jvm/internal/components/PackagePartScopeCache;", "", "resolver", "Lkotlin/reflect/jvm/internal/impl/load/kotlin/DeserializedDescriptorResolver;", "kotlinClassFinder", "Lkotlin/reflect/jvm/internal/components/ReflectKotlinClassFinder;", "(Lorg/jetbrains/kotlin/load/kotlin/DeserializedDescriptorResolver;Lkotlin/reflect/jvm/internal/components/ReflectKotlinClassFinder;)V", "cache", "Ljava/util/concurrent/ConcurrentHashMap;", "Lkotlin/reflect/jvm/internal/impl/name/ClassId;", "Lkotlin/reflect/jvm/internal/impl/resolve/scopes/MemberScope;", "getPackagePartScope", "fileClass", "Lkotlin/reflect/jvm/internal/components/ReflectKotlinClass;", "descriptors.runtime"}, m3962k = 1, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class PackagePartScopeCache {
    private final ConcurrentHashMap<ClassId, MemberScope> cache;
    private final ReflectKotlinClassFinder kotlinClassFinder;
    private final DeserializedDescriptorResolver resolver;

    public PackagePartScopeCache(DeserializedDescriptorResolver resolver, ReflectKotlinClassFinder kotlinClassFinder) {
        Intrinsics.checkParameterIsNotNull(resolver, "resolver");
        Intrinsics.checkParameterIsNotNull(kotlinClassFinder, "kotlinClassFinder");
        this.resolver = resolver;
        this.kotlinClassFinder = kotlinClassFinder;
        this.cache = new ConcurrentHashMap<>();
    }

    public final MemberScope getPackagePartScope(ReflectKotlinClass fileClass) {
        ArrayList listOf;
        Intrinsics.checkParameterIsNotNull(fileClass, "fileClass");
        ConcurrentHashMap<ClassId, MemberScope> concurrentHashMap = this.cache;
        ClassId classId = fileClass.getClassId();
        MemberScope memberScope = concurrentHashMap.get(classId);
        if (memberScope == null) {
            FqName packageFqName = fileClass.getClassId().getPackageFqName();
            Intrinsics.checkExpressionValueIsNotNull(packageFqName, "fileClass.classId.packageFqName");
            if (fileClass.getClassHeader().getKind() == KotlinClassHeader.Kind.MULTIFILE_CLASS) {
                List<String> multifilePartNames = fileClass.getClassHeader().getMultifilePartNames();
                ArrayList arrayList = new ArrayList();
                Iterator<T> it = multifilePartNames.iterator();
                while (it.hasNext()) {
                    JvmClassName byInternalName = JvmClassName.byInternalName((String) it.next());
                    Intrinsics.checkExpressionValueIsNotNull(byInternalName, "JvmClassName.byInternalName(partName)");
                    ClassId classId2 = ClassId.topLevel(byInternalName.getFqNameForTopLevelClassMaybeWithDollars());
                    Intrinsics.checkExpressionValueIsNotNull(classId2, "ClassId.topLevel(JvmClas…velClassMaybeWithDollars)");
                    KotlinJvmBinaryClass findKotlinClass = KotlinClassFinderKt.findKotlinClass(this.kotlinClassFinder, classId2);
                    if (findKotlinClass != null) {
                        arrayList.add(findKotlinClass);
                    }
                }
                listOf = arrayList;
            } else {
                listOf = CollectionsKt.listOf(fileClass);
            }
            EmptyPackageFragmentDescriptor emptyPackageFragmentDescriptor = new EmptyPackageFragmentDescriptor(this.resolver.getComponents().getModuleDescriptor(), packageFqName);
            ArrayList arrayList2 = new ArrayList();
            Iterator it2 = listOf.iterator();
            while (it2.hasNext()) {
                MemberScope createKotlinPackagePartScope = this.resolver.createKotlinPackagePartScope(emptyPackageFragmentDescriptor, (KotlinJvmBinaryClass) it2.next());
                if (createKotlinPackagePartScope != null) {
                    arrayList2.add(createKotlinPackagePartScope);
                }
            }
            List<? extends MemberScope> list = CollectionsKt.toList(arrayList2);
            memberScope = ChainedMemberScope.Companion.create("package " + packageFqName + " (" + fileClass + ')', list);
            MemberScope putIfAbsent = concurrentHashMap.putIfAbsent(classId, memberScope);
            if (putIfAbsent != null) {
                memberScope = putIfAbsent;
            }
        }
        Intrinsics.checkExpressionValueIsNotNull(memberScope, "cache.getOrPut(fileClass…ileClass)\", scopes)\n    }");
        return memberScope;
    }
}
