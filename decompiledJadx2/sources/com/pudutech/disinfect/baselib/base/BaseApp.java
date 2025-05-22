package com.pudutech.disinfect.baselib.base;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineContextKt;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: BaseApp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \u00102\u00020\u00012\u00020\u0002:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014J\b\u0010\f\u001a\u00020\u0007H\u0002J\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\u0005H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/base/BaseApp;", "Landroid/app/Application;", "Landroidx/lifecycle/ViewModelStoreOwner;", "()V", "mAppViewModelStore", "Landroidx/lifecycle/ViewModelStore;", "mFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "attachBaseContext", "", "base", "Landroid/content/Context;", "getAppFactory", "getAppViewModelProvider", "Landroidx/lifecycle/ViewModelProvider;", "getViewModelStore", "Companion", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public class BaseApp extends Application implements ViewModelStoreOwner {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static Application INSTANCE;
    private ViewModelStore mAppViewModelStore;
    private ViewModelProvider.Factory mFactory;

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* compiled from: BaseApp.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/base/BaseApp$Companion;", "", "()V", "<set-?>", "Landroid/app/Application;", "INSTANCE", "getINSTANCE", "()Landroid/app/Application;", "setINSTANCE", "(Landroid/app/Application;)V", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final void setINSTANCE(Application application) {
            BaseApp.INSTANCE = application;
        }

        public final Application getINSTANCE() {
            Application application = BaseApp.INSTANCE;
            if (application == null) {
                Intrinsics.throwUninitializedPropertyAccessException("INSTANCE");
            }
            return application;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context base) {
        System.setProperty(CoroutineContextKt.COROUTINES_SCHEDULER_PROPERTY_NAME, "off");
        INSTANCE = this;
        this.mAppViewModelStore = new ViewModelStore();
        super.attachBaseContext(base);
    }

    @Override // androidx.lifecycle.ViewModelStoreOwner
    public ViewModelStore getViewModelStore() {
        ViewModelStore viewModelStore = this.mAppViewModelStore;
        if (viewModelStore == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAppViewModelStore");
        }
        return viewModelStore;
    }

    public final ViewModelProvider getAppViewModelProvider() {
        return new ViewModelProvider(this, getAppFactory());
    }

    private final ViewModelProvider.Factory getAppFactory() {
        if (this.mFactory == null) {
            this.mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this);
        }
        ViewModelProvider.Factory factory = this.mFactory;
        if (factory != null) {
            return factory;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.lifecycle.ViewModelProvider.Factory");
    }
}
