package kotlin.reflect.jvm.internal.impl.incremental;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LocationInfo;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker;
import kotlin.reflect.jvm.internal.impl.incremental.components.Position;
import kotlin.reflect.jvm.internal.impl.incremental.components.ScopeKind;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: utils.kt */
/* loaded from: classes2.dex */
public final class UtilsKt {
    public static final void record(LookupTracker record, LookupLocation from, ClassDescriptor scopeOwner, Name name) {
        LocationInfo location;
        Intrinsics.checkParameterIsNotNull(record, "$this$record");
        Intrinsics.checkParameterIsNotNull(from, "from");
        Intrinsics.checkParameterIsNotNull(scopeOwner, "scopeOwner");
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (record == LookupTracker.DO_NOTHING.INSTANCE || (location = from.getLocation()) == null) {
            return;
        }
        Position position = record.getRequiresPosition() ? location.getPosition() : Position.Companion.getNO_POSITION();
        String filePath = location.getFilePath();
        String asString = DescriptorUtils.getFqName(scopeOwner).asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "DescriptorUtils.getFqName(scopeOwner).asString()");
        ScopeKind scopeKind = ScopeKind.CLASSIFIER;
        String asString2 = name.asString();
        Intrinsics.checkExpressionValueIsNotNull(asString2, "name.asString()");
        record.record(filePath, position, asString, scopeKind, asString2);
    }

    public static final void record(LookupTracker record, LookupLocation from, PackageFragmentDescriptor scopeOwner, Name name) {
        Intrinsics.checkParameterIsNotNull(record, "$this$record");
        Intrinsics.checkParameterIsNotNull(from, "from");
        Intrinsics.checkParameterIsNotNull(scopeOwner, "scopeOwner");
        Intrinsics.checkParameterIsNotNull(name, "name");
        String asString = scopeOwner.getFqName().asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "scopeOwner.fqName.asString()");
        String asString2 = name.asString();
        Intrinsics.checkExpressionValueIsNotNull(asString2, "name.asString()");
        recordPackageLookup(record, from, asString, asString2);
    }

    public static final void recordPackageLookup(LookupTracker recordPackageLookup, LookupLocation from, String packageFqName, String name) {
        LocationInfo location;
        Intrinsics.checkParameterIsNotNull(recordPackageLookup, "$this$recordPackageLookup");
        Intrinsics.checkParameterIsNotNull(from, "from");
        Intrinsics.checkParameterIsNotNull(packageFqName, "packageFqName");
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (recordPackageLookup == LookupTracker.DO_NOTHING.INSTANCE || (location = from.getLocation()) == null) {
            return;
        }
        recordPackageLookup.record(location.getFilePath(), recordPackageLookup.getRequiresPosition() ? location.getPosition() : Position.Companion.getNO_POSITION(), packageFqName, ScopeKind.PACKAGE, name);
    }
}
