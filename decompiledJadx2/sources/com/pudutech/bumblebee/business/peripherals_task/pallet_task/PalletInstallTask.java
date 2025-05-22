package com.pudutech.bumblebee.business.peripherals_task.pallet_task;

import android.content.Context;
import android.content.SharedPreferences;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseMultiListenerImpl;
import com.pudutech.bumblebee.business.robotsdk.peripherals_listeners.PalletStateListener;
import com.pudutech.bumblebee.robot.aidl.serialize.Pallet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PalletInstallTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0016J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/peripherals_task/pallet_task/PalletInstallTask;", "Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "Lcom/pudutech/bumblebee/business/peripherals_task/pallet_task/PalletInstallListener;", "Lcom/pudutech/bumblebee/business/robotsdk/peripherals_listeners/PalletStateListener;", "()V", "onPalletsResponse", "", "p0", "", "Lcom/pudutech/bumblebee/robot/aidl/serialize/Pallet;", "setInstall", "id", "", "b", "", "Companion", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PalletInstallTask extends BaseMultiListenerImpl<PalletInstallListener> implements PalletStateListener {
    private static final String TAG = "PalletInstallTask";
    private static Function0<Unit> requestMethod;
    private static SharedPreferences sharedPreferences;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy installStates$delegate = LazyKt.lazy(new Function0<HashMap<Integer, Boolean>>() { // from class: com.pudutech.bumblebee.business.peripherals_task.pallet_task.PalletInstallTask$Companion$installStates$2
        @Override // kotlin.jvm.functions.Function0
        public final HashMap<Integer, Boolean> invoke() {
            HashMap<Integer, Boolean> hashMap = new HashMap<>();
            for (int i = 1; i <= 4; i++) {
                hashMap.put(Integer.valueOf(i), true);
            }
            return hashMap;
        }
    });
    private static final String FileName = FileName;
    private static final String FileName = FileName;
    private static final String KEY = KEY;
    private static final String KEY = KEY;

    /* compiled from: PalletInstallTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R7\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bj\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n`\u000b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR4\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/peripherals_task/pallet_task/PalletInstallTask$Companion;", "", "()V", "FileName", "", "KEY", "TAG", "installStates", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "getInstallStates", "()Ljava/util/HashMap;", "installStates$delegate", "Lkotlin/Lazy;", "<set-?>", "Lkotlin/Function0;", "", "requestMethod", "getRequestMethod", "()Lkotlin/jvm/functions/Function0;", "setRequestMethod", "(Lkotlin/jvm/functions/Function0;)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "initConfig", "context", "Landroid/content/Context;", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        public final HashMap<Integer, Boolean> getInstallStates() {
            Lazy lazy = PalletInstallTask.installStates$delegate;
            Companion companion = PalletInstallTask.INSTANCE;
            return (HashMap) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void initConfig(Context context, Function0<Unit> requestMethod) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(requestMethod, "requestMethod");
            Pdlog.m3275i(PalletInstallTask.TAG, "initConfig " + context);
            PalletInstallTask.sharedPreferences = context.getSharedPreferences(PalletInstallTask.FileName, 0);
            Companion companion = this;
            for (Map.Entry<Integer, Boolean> entry : companion.getInstallStates().entrySet()) {
                SharedPreferences sharedPreferences = PalletInstallTask.sharedPreferences;
                Boolean valueOf = sharedPreferences != null ? Boolean.valueOf(sharedPreferences.getBoolean(PalletInstallTask.KEY + entry.getKey().intValue(), entry.getValue().booleanValue())) : null;
                if (valueOf != null) {
                    PalletInstallTask.INSTANCE.getInstallStates().put(entry.getKey(), valueOf);
                }
            }
            companion.setRequestMethod(requestMethod);
            requestMethod.invoke();
        }

        private final void setRequestMethod(Function0<Unit> function0) {
            PalletInstallTask.requestMethod = function0;
        }

        public final Function0<Unit> getRequestMethod() {
            return PalletInstallTask.requestMethod;
        }
    }

    @Override // com.pudutech.bumblebee.business.robotsdk.peripherals_listeners.PalletStateListener
    public void onPalletsResponse(List<Pallet> p0) {
        if (p0 != null) {
            for (Pallet pallet : p0) {
                setInstall(pallet.getPalletId(), pallet.getIsInstalled());
            }
        }
    }

    private final void setInstall(int id, boolean b) {
        SharedPreferences.Editor edit;
        INSTANCE.getInstallStates().put(Integer.valueOf(id), Boolean.valueOf(b));
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 != null && (edit = sharedPreferences2.edit()) != null) {
            SharedPreferences.Editor putBoolean = edit.putBoolean(KEY + id, b);
            if (putBoolean != null) {
                putBoolean.apply();
            }
        }
        getListeners().forEach(new Function1<PalletInstallListener, Unit>() { // from class: com.pudutech.bumblebee.business.peripherals_task.pallet_task.PalletInstallTask$setInstall$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PalletInstallListener palletInstallListener) {
                invoke2(palletInstallListener);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PalletInstallListener it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.onInstallSatesChange();
            }
        });
    }
}
