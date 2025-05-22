package androidx.navigation.p002ui;

import androidx.navigation.p002ui.AppBarConfiguration;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: AppBarConfiguration.kt */
@Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: androidx.navigation.ui.AppBarConfigurationKt$sam$i$androidx_navigation_ui_AppBarConfiguration_OnNavigateUpListener$0 */
/* loaded from: classes.dex */
public final class C0535x56421ee5 implements AppBarConfiguration.OnNavigateUpListener {
    private final /* synthetic */ Function0 function;

    public C0535x56421ee5(Function0 function0) {
        this.function = function0;
    }

    @Override // androidx.navigation.ui.AppBarConfiguration.OnNavigateUpListener
    public final /* synthetic */ boolean onNavigateUp() {
        Object invoke = this.function.invoke();
        Intrinsics.checkExpressionValueIsNotNull(invoke, "invoke(...)");
        return ((Boolean) invoke).booleanValue();
    }
}
