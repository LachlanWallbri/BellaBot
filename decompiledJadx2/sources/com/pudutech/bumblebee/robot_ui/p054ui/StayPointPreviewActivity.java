package com.pudutech.bumblebee.robot_ui.p054ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallTargetBean;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.p054ui.custom_call.CustomCallActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.BeeperCallHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CustomCallHelper;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.location.view.SignalView;
import io.reactivex.disposables.Disposable;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: StayPointPreviewActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000O\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0010\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\rH\u0002J\b\u0010\u0015\u001a\u00020\rH\u0002J\b\u0010\u0016\u001a\u00020\tH\u0016J\u0010\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0012\u0010\u001a\u001a\u00020\r2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014J\b\u0010\u001d\u001a\u00020\rH\u0014J\u0010\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\tH\u0016J\b\u0010 \u001a\u00020\rH\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/StayPointPreviewActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "beeperCallHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/BeeperCallHelper;", "isFirstStart", "", "onCustomCallListener", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallTargetBean;", "", "previewContent", "singleBatteryListener", "com/pudutech/bumblebee/robot_ui/ui/StayPointPreviewActivity$singleBatteryListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/StayPointPreviewActivity$singleBatteryListener$1;", MqttServiceConstants.SUBSCRIBE_ACTION, "Lio/reactivex/disposables/Disposable;", "bindPresenter", "initView", "isBusyState", "jumpAndFinish", "intent", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onWindowFocusChanged", "hasFocus", "unbindPresenter", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class StayPointPreviewActivity extends MyBaseActivity {
    private HashMap _$_findViewCache;
    private String previewContent;
    private Disposable subscribe;
    private boolean isFirstStart = true;
    private String TAG = getClass().getSimpleName();
    private Function1<? super CustomCallTargetBean, Unit> onCustomCallListener = new Function1<CustomCallTargetBean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.StayPointPreviewActivity$onCustomCallListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(CustomCallTargetBean customCallTargetBean) {
            invoke2(customCallTargetBean);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(CustomCallTargetBean it) {
            String str;
            Intrinsics.checkParameterIsNotNull(it, "it");
            str = StayPointPreviewActivity.this.TAG;
            Pdlog.m3273d(str, "onCustomCallListener task: " + it);
            StayPointPreviewActivity.this.jumpAndFinish(CustomCallActivity.Companion.createIntent(StayPointPreviewActivity.this, it));
        }
    };
    private final BeeperCallHelper beeperCallHelper = new BeeperCallHelper();
    private final StayPointPreviewActivity$singleBatteryListener$1 singleBatteryListener = new StayPointPreviewActivity$singleBatteryListener$1(this);

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

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity
    public boolean isBusyState() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.previewContent = getIntent().getStringExtra(Constans.EXTRA_CRUISE_STAY_COPY_WRITE_PREVIEW);
        setContentView(C4188R.layout.activity_stay_point_preview);
        BatteryInfoManager.INSTANCE.addBatteryChangeNotifyListeners(this.singleBatteryListener);
        initView();
        this.singleBatteryListener.showPowerChange(BatteryInfoManager.INSTANCE.getPower());
        SignalView signal_view = (SignalView) _$_findCachedViewById(C4188R.id.signal_view);
        Intrinsics.checkExpressionValueIsNotNull(signal_view, "signal_view");
        setBindSignal(signal_view);
        bindPresenter();
    }

    private final void initView() {
        ((Button) _$_findCachedViewById(C4188R.id.btnClose)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.StayPointPreviewActivity$initView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                StayPointPreviewActivity.this.finish();
            }
        }, 3, null));
        String str = this.previewContent;
        if (str == null || StringsKt.isBlank(str)) {
            ((ViewStub) findViewById(C4188R.id.noCopyView)).inflate();
            return;
        }
        ((ViewStub) findViewById(C4188R.id.hasCopyView)).inflate();
        TextView tvStayPoint = (TextView) _$_findCachedViewById(C4188R.id.tvStayPoint);
        Intrinsics.checkExpressionValueIsNotNull(tvStayPoint, "tvStayPoint");
        String str2 = this.previewContent;
        if (str2 == null) {
            str2 = "";
        }
        tvStayPoint.setText(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        Disposable disposable = this.subscribe;
        if (disposable != null && !disposable.isDisposed()) {
            disposable.isDisposed();
        }
        super.onDestroy();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Pdlog.m3273d(this.TAG, "onWindowFocusChanged " + hasFocus);
        if (hasFocus && this.isFirstStart) {
            this.isFirstStart = false;
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, com.pudutech.bumblebee.robot_ui.p054ui.FinishInter
    public void jumpAndFinish(Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        BatteryInfoManager.INSTANCE.removeBatteryChangeNotifyListeners(this.singleBatteryListener);
        unbindPresenter();
        startActivity(intent);
        finish();
    }

    private final void bindPresenter() {
        CustomCallHelper.INSTANCE.addCallListener(this.onCustomCallListener);
        BeeperCallHelper.bind$default(this.beeperCallHelper, this, false, false, 6, null);
    }

    private final void unbindPresenter() {
        CustomCallHelper.INSTANCE.removeCallListener(this.onCustomCallListener);
        this.beeperCallHelper.unbind();
    }
}
