package kotlin.reflect.jvm.internal.impl.incremental.components;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: LookupLocation.kt */
/* loaded from: classes2.dex */
public enum NoLookupLocation implements LookupLocation {
    FROM_IDE,
    FROM_BACKEND,
    FROM_TEST,
    FROM_BUILTINS,
    WHEN_CHECK_DECLARATION_CONFLICTS,
    WHEN_CHECK_OVERRIDES,
    FOR_SCRIPT,
    FROM_REFLECTION,
    WHEN_RESOLVE_DECLARATION,
    WHEN_GET_DECLARATION_SCOPE,
    WHEN_RESOLVING_DEFAULT_TYPE_ARGUMENTS,
    FOR_ALREADY_TRACKED,
    WHEN_GET_ALL_DESCRIPTORS,
    WHEN_TYPING,
    WHEN_GET_SUPER_MEMBERS,
    FOR_NON_TRACKED_SCOPE,
    FROM_SYNTHETIC_SCOPE,
    FROM_DESERIALIZATION,
    FROM_JAVA_LOADER,
    WHEN_GET_LOCAL_VARIABLE,
    WHEN_FIND_BY_FQNAME,
    WHEN_GET_COMPANION_OBJECT,
    FOR_DEFAULT_IMPORTS;

    @Override // kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation
    public LocationInfo getLocation() {
        return null;
    }
}
