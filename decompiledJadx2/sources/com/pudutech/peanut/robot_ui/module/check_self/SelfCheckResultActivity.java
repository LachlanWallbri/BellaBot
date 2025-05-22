package com.pudutech.peanut.robot_ui.module.check_self;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.mirsdkwrap.lib.MirSdkManager;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.CheckPermissionDialog;
import com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity;
import com.pudutech.peanut.robot_ui.util.AppUtil;
import com.pudutech.peanut.robot_ui.util.ToastUtils;
import com.pudutech.peanut.robot_ui.viewmodel.CheckInitViewModel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: SelfCheckResultActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 !2\u00020\u0001:\u0001!B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u0012\u0010\u0016\u001a\u00020\u000e2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0012\u0010\u0019\u001a\u00020\u00152\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\b\u0010\u001c\u001a\u00020\u0015H\u0014J\b\u0010\u001d\u001a\u00020\u0015H\u0002J\b\u0010\u001e\u001a\u00020\u0015H\u0002J\u0010\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/check_self/SelfCheckResultActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/MyBaseActivity;", "()V", "TAG", "", "checkInitViewModel", "Lcom/pudutech/peanut/robot_ui/viewmodel/CheckInitViewModel;", "getCheckInitViewModel", "()Lcom/pudutech/peanut/robot_ui/viewmodel/CheckInitViewModel;", "checkInitViewModel$delegate", "Lkotlin/Lazy;", "checkPermissionDialog", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/CheckPermissionDialog;", "isDev", "", "isTimeout", "lastHitTimestamp", "", "mDevHitCountdown", "", "addTimeoutContent", "", "dispatchTouchEvent", "ev", "Landroid/view/MotionEvent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "openDevMode", "showCheckPermissionDialog", "showErrorStep", "error", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SelfCheckResultActivity extends MyBaseActivity {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String IS_TIME_OUT = "IS_TIME_OUT";
    private static final String PHONE_NUM = "400-0826-660";
    private HashMap _$_findViewCache;
    private CheckPermissionDialog checkPermissionDialog;
    private boolean isDev;
    private boolean isTimeout;
    private long lastHitTimestamp;
    private final String TAG = "SelfCheckResultActivity";

    /* renamed from: checkInitViewModel$delegate, reason: from kotlin metadata */
    private final Lazy checkInitViewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(CheckInitViewModel.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.module.check_self.SelfCheckResultActivity$$special$$inlined$viewModels$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelStore invoke() {
            ViewModelStore viewModelStore = ComponentActivity.this.getViewModelStore();
            Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "viewModelStore");
            return viewModelStore;
        }
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.peanut.robot_ui.module.check_self.SelfCheckResultActivity$$special$$inlined$viewModels$1
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelProvider.Factory invoke() {
            ViewModelProvider.Factory defaultViewModelProviderFactory = ComponentActivity.this.getDefaultViewModelProviderFactory();
            Intrinsics.checkExpressionValueIsNotNull(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
            return defaultViewModelProviderFactory;
        }
    });
    private int mDevHitCountdown = 7;

    private final CheckInitViewModel getCheckInitViewModel() {
        return (CheckInitViewModel) this.checkInitViewModel.getValue();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public SelfCheckResultActivity() {
    }

    /* compiled from: SelfCheckResultActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/check_self/SelfCheckResultActivity$Companion;", "", "()V", SelfCheckResultActivity.IS_TIME_OUT, "", "PHONE_NUM", "startIsTimeOut", "", "boolean", "", "context", "Lcom/pudutech/peanut/robot_ui/ui/base/MyBaseActivity;", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void startIsTimeOut(boolean r4, MyBaseActivity context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intent intent = new Intent(context, (Class<?>) SelfCheckResultActivity.class);
            intent.putExtra(SelfCheckResultActivity.IS_TIME_OUT, r4);
            context.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5508R.layout.activity_layout_selfcheck_result);
        this.isTimeout = getIntent().getBooleanExtra(IS_TIME_OUT, false);
        TextView version_name_tv = (TextView) _$_findCachedViewById(C5508R.id.version_name_tv);
        Intrinsics.checkExpressionValueIsNotNull(version_name_tv, "version_name_tv");
        version_name_tv.setText("v9.1.2");
        TextView tv_get_us = (TextView) _$_findCachedViewById(C5508R.id.tv_get_us);
        Intrinsics.checkExpressionValueIsNotNull(tv_get_us, "tv_get_us");
        tv_get_us.setText(getString(C5508R.string.connect_us, new Object[]{PHONE_NUM}));
        if (this.isTimeout) {
            addTimeoutContent();
            return;
        }
        Iterator<T> it = getCheckInitViewModel().getError().iterator();
        while (it.hasNext()) {
            showErrorStep((String) it.next());
        }
    }

    private final void addTimeoutContent() {
        TextView textView = new TextView(this);
        textView.setText(getString(C5508R.string.pdStr1_4));
        Sdk27PropertiesKt.setTextColor(textView, getColor(C5508R.color.white));
        textView.setTextSize(25.0f);
        ((LinearLayout) _$_findCachedViewById(C5508R.id.layoutResult)).addView(textView);
    }

    private final void showErrorStep(String error) {
        Pdlog.m3274e(this.TAG, "自检失败==== " + error);
        TextView textView = new TextView(this);
        textView.setText(error);
        textView.setTextSize(25.0f);
        Sdk27PropertiesKt.setTextColor(textView, getColor(C5508R.color.white));
        ((LinearLayout) _$_findCachedViewById(C5508R.id.layoutResult)).addView(textView);
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Integer valueOf = ev != null ? Integer.valueOf(ev.getActionMasked()) : null;
        if (valueOf != null && valueOf.intValue() == 1) {
            openDevMode();
        }
        return super.dispatchTouchEvent(ev);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    private final void openDevMode() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.lastHitTimestamp;
        if (elapsedRealtime - j > 500 && j != 0) {
            this.mDevHitCountdown = 7;
            this.lastHitTimestamp = SystemClock.elapsedRealtime();
            return;
        }
        this.lastHitTimestamp = SystemClock.elapsedRealtime();
        if (this.isDev) {
            this.mDevHitCountdown = 7;
            this.isDev = false;
        }
        int i = this.mDevHitCountdown;
        if (i <= 0) {
            if (i < 0) {
                ToastUtils.show(this, getString(C5508R.string.pdStr1_6), new Object[0]);
                return;
            }
            return;
        }
        this.mDevHitCountdown = i - 1;
        int i2 = this.mDevHitCountdown;
        if (i2 == 0) {
            Pdlog.m3277w(this.TAG, "go to debug activity");
            showCheckPermissionDialog();
            this.isDev = true;
        } else {
            if (i2 <= 0 || i2 >= 5) {
                return;
            }
            Pdlog.m3277w(this.TAG, "go to debug activity");
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(C5508R.string.pdStr1_5, new Object[]{Integer.valueOf(this.mDevHitCountdown)});
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr1_5, mDevHitCountdown)");
            Object[] objArr = new Object[0];
            String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            ToastUtils.show(this, format, new Object[0]);
        }
    }

    private final void showCheckPermissionDialog() {
        if (this.checkPermissionDialog == null) {
            this.checkPermissionDialog = new CheckPermissionDialog(this);
        }
        Pdlog.m3273d(this.TAG, "showCheckPermissionDialog");
        CheckPermissionDialog checkPermissionDialog = this.checkPermissionDialog;
        if (checkPermissionDialog == null) {
            Intrinsics.throwNpe();
        }
        checkPermissionDialog.setOnPermissionCheckResult(new Function1<Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.check_self.SelfCheckResultActivity$showCheckPermissionDialog$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                String str;
                CheckPermissionDialog checkPermissionDialog2;
                str = SelfCheckResultActivity.this.TAG;
                Pdlog.m3273d(str, "showCheckPermissionDialog onPermissionCheckResult " + z);
                if (z) {
                    checkPermissionDialog2 = SelfCheckResultActivity.this.checkPermissionDialog;
                    if (checkPermissionDialog2 != null) {
                        checkPermissionDialog2.dismiss();
                    }
                    if (SpUtils.get((Context) SelfCheckResultActivity.this, "isFactoryrobot_server_ac_key", false)) {
                        MirSdkManager.INSTANCE.closeAuthMirSdk();
                    }
                    AppUtil.startDebugFunction(SelfCheckResultActivity.this);
                }
            }
        });
        CheckPermissionDialog checkPermissionDialog2 = this.checkPermissionDialog;
        if (checkPermissionDialog2 == null) {
            Intrinsics.throwNpe();
        }
        checkPermissionDialog2.show();
    }
}
