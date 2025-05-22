package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: LazyScopeAdapter.kt */
/* loaded from: classes2.dex */
public final class LazyScopeAdapter extends AbstractScopeAdapter {
    private final NotNullLazyValue<MemberScope> scope;

    /* JADX WARN: Multi-variable type inference failed */
    public LazyScopeAdapter(NotNullLazyValue<? extends MemberScope> scope) {
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        this.scope = scope;
    }

    @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.AbstractScopeAdapter
    protected MemberScope getWorkerScope() {
        return this.scope.invoke();
    }
}
