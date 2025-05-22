package com.pudutech.bumblebee.robot_ui.module.check_self;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.initialization_task.InitStepViewModel;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.manager.InitAppManager;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ValidationDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity;
import com.pudutech.bumblebee.robot_ui.util.AppUtil;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import com.pudutech.mirsdk.aidl.serialize.StepState;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: SelfCheckResultActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 #2\u00020\u0001:\u0001#B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\rH\u0002J\b\u0010\u0017\u001a\u00020\rH\u0002J\u0012\u0010\u0018\u001a\u00020\u000f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0012\u0010\u001b\u001a\u00020\r2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\b\u0010\u001e\u001a\u00020\rH\u0014J\b\u0010\u001f\u001a\u00020\rH\u0002J\b\u0010 \u001a\u00020\rH\u0002J\u0010\u0010!\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\tH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R)\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/check_self/SelfCheckResultActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "()V", "TAG", "", "checkPermissionDialog", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ValidationDialog;", "initStepListener", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/presenter/initialization_task/InitStepViewModel;", "Lkotlin/ParameterName;", "name", "b", "", "isDev", "", "isStepFinish", "isTimeout", "lastHitTimestamp", "", "mDevHitCountdown", "", "addTimeoutContent", "bindPresenter", "dispatchTouchEvent", "ev", "Landroid/view/MotionEvent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "openDevMode", "showCheckPermissionDialog", "showStep", "viewModel", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SelfCheckResultActivity extends MyBaseActivity {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String IS_TIME_OUT = "IS_TIME_OUT";
    private HashMap _$_findViewCache;
    private ValidationDialog checkPermissionDialog;
    private boolean isDev;
    private boolean isStepFinish;
    private boolean isTimeout;
    private long lastHitTimestamp;
    private final String TAG = "SelfCheckResultActivity";
    private int mDevHitCountdown = 7;
    private final Function1<InitStepViewModel, Unit> initStepListener = new Function1<InitStepViewModel, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.check_self.SelfCheckResultActivity$initStepListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(InitStepViewModel initStepViewModel) {
            invoke2(initStepViewModel);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(InitStepViewModel viewModel) {
            String str;
            Intrinsics.checkParameterIsNotNull(viewModel, "viewModel");
            str = SelfCheckResultActivity.this.TAG;
            Pdlog.m3273d(str, "initStepListener viewModel:" + viewModel);
            SelfCheckResultActivity.this.showStep(viewModel);
        }
    };

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity
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

    /* compiled from: SelfCheckResultActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/check_self/SelfCheckResultActivity$Companion;", "", "()V", SelfCheckResultActivity.IS_TIME_OUT, "", "startIsTimeOut", "", "boolean", "", "context", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
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
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C4188R.layout.activity_layout_selfcheck_result);
        this.isTimeout = getIntent().getBooleanExtra(IS_TIME_OUT, false);
        PeripheralsSceneUtil.INSTANCE.appInitFailed();
        TextView version_name_tv = (TextView) _$_findCachedViewById(C4188R.id.version_name_tv);
        Intrinsics.checkExpressionValueIsNotNull(version_name_tv, "version_name_tv");
        version_name_tv.setText("v6.12.0.10");
        if (this.isTimeout) {
            addTimeoutContent();
        } else {
            bindPresenter();
        }
    }

    private final void addTimeoutContent() {
        TextView textView = new TextView(this);
        textView.setText(getString(C4188R.string.pdStr1_4));
        Sdk27PropertiesKt.setTextColor(textView, getColor(C4188R.color.white));
        textView.setTextSize(25.0f);
        ((LinearLayout) _$_findCachedViewById(C4188R.id.layoutResult)).addView(textView);
    }

    private final void bindPresenter() {
        for (InitStepViewModel initStepViewModel : InitAppManager.INSTANCE.getInitStep()) {
            Pdlog.m3273d(this.TAG, "bindPresenter getInitStep forEach:" + initStepViewModel);
            showStep(initStepViewModel);
        }
        InitAppManager.INSTANCE.addInitStepListener(this.initStepListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showStep(InitStepViewModel viewModel) {
        Pdlog.m3274e(this.TAG, "showStep viewModel:" + viewModel);
        if (viewModel.getState() == StepState.Fail) {
            Pdlog.m3274e(this.TAG, "自检失败====" + viewModel.getStep() + ' ' + viewModel.getDescription());
            TextView textView = new TextView(this);
            textView.setText(viewModel.getStep() + ' ' + viewModel.getDescription());
            textView.setTextSize(25.0f);
            Sdk27PropertiesKt.setTextColor(textView, getColor(C4188R.color.white));
            ((LinearLayout) _$_findCachedViewById(C4188R.id.layoutResult)).addView(textView);
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Integer valueOf = ev != null ? Integer.valueOf(ev.getActionMasked()) : null;
        if (valueOf != null && valueOf.intValue() == 1) {
            openDevMode();
        }
        return super.dispatchTouchEvent(ev);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        InitAppManager.INSTANCE.removeInitStepListener(this.initStepListener);
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
                ToastUtils.show(this, getString(C4188R.string.pdStr1_6), new Object[0]);
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
            SelfCheckResultActivity selfCheckResultActivity = this;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(C4188R.string.pdStr1_5);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr1_5)");
            Object[] objArr = new Object[1];
            objArr[0] = LanguageUtils.INSTANCE.isALaBo() ? String.valueOf(this.mDevHitCountdown) : Integer.valueOf(this.mDevHitCountdown);
            String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            ToastUtils.show(selfCheckResultActivity, format, new Object[0]);
        }
    }

    private final void showCheckPermissionDialog() {
        if (this.checkPermissionDialog == null) {
            this.checkPermissionDialog = new ValidationDialog(this);
        }
        Pdlog.m3273d(this.TAG, "showCheckPermissionDialog");
        ValidationDialog validationDialog = this.checkPermissionDialog;
        if (validationDialog == null) {
            Intrinsics.throwNpe();
        }
        validationDialog.setOnPermissionCheckResult(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.check_self.SelfCheckResultActivity$showCheckPermissionDialog$1
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
                ValidationDialog validationDialog2;
                str = SelfCheckResultActivity.this.TAG;
                Pdlog.m3273d(str, "showCheckPermissionDialog onPermissionCheckResult " + z);
                if (z) {
                    validationDialog2 = SelfCheckResultActivity.this.checkPermissionDialog;
                    if (validationDialog2 != null) {
                        validationDialog2.dismiss();
                    }
                    AppUtil.INSTANCE.startDebugFunction(SelfCheckResultActivity.this);
                }
            }
        });
        ValidationDialog validationDialog2 = this.checkPermissionDialog;
        if (validationDialog2 == null) {
            Intrinsics.throwNpe();
        }
        validationDialog2.show();
    }
}
