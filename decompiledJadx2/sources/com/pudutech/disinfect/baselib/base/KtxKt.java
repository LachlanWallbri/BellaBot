package com.pudutech.disinfect.baselib.base;

import android.app.Application;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: Ktx.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u001b\u0010\u0000\u001a\u00020\u00018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0006"}, m3961d2 = {"appContext", "Landroid/app/Application;", "getAppContext", "()Landroid/app/Application;", "appContext$delegate", "Lkotlin/Lazy;", "module_baselib_robot_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class KtxKt {
    private static final Lazy appContext$delegate = LazyKt.lazy(new Function0<Application>() { // from class: com.pudutech.disinfect.baselib.base.KtxKt$appContext$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Application invoke() {
            return Ktx.Companion.getApp();
        }
    });

    public static final Application getAppContext() {
        return (Application) appContext$delegate.getValue();
    }
}
