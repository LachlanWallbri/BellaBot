package kotlin.reflect.jvm.internal.impl.incremental.components;

import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: LookupTracker.kt */
/* loaded from: classes2.dex */
public interface LookupTracker {
    boolean getRequiresPosition();

    void record(String str, Position position, String str2, ScopeKind scopeKind, String str3);

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: LookupTracker.kt */
    /* loaded from: classes2.dex */
    public static final class DO_NOTHING implements LookupTracker {
        public static final DO_NOTHING INSTANCE = new DO_NOTHING();

        @Override // kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker
        public boolean getRequiresPosition() {
            return false;
        }

        @Override // kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker
        public void record(String filePath, Position position, String scopeFqName, ScopeKind scopeKind, String name) {
            Intrinsics.checkParameterIsNotNull(filePath, "filePath");
            Intrinsics.checkParameterIsNotNull(position, "position");
            Intrinsics.checkParameterIsNotNull(scopeFqName, "scopeFqName");
            Intrinsics.checkParameterIsNotNull(scopeKind, "scopeKind");
            Intrinsics.checkParameterIsNotNull(name, "name");
        }

        private DO_NOTHING() {
        }
    }
}
