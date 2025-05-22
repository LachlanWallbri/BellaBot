package com.pudutech.bumblebee.robot_ui.p054ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.behavior.Behavior;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.delivery_task.DeliverSettingContract;
import com.pudutech.bumblebee.presenter.delivery_task.DeliverSettingPresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallTargetBean;
import com.pudutech.bumblebee.presenter.utils.SoundPoolVoiceUtil;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.bean.PalletTtsScheme;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.PalletTtsTaskNameAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.custom_call.CustomCallActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.CustomInputDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.CustomTextInputDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.CustomTtsVoiceInputDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.BeeperCallHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CustomCallHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.bumblebee.robot_ui.roomdata.TtsDataBaseManager;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceSwitchChangeListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceSwitchChangeListenerKt;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import com.pudutech.disinfect.baselib.widget.MarqueeTextView;
import com.pudutech.location.view.SignalView;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: DeliverSettingActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 B2\u00020\u00012\u00020\u0002:\u0002BCB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u001f\u001a\u00020\u0015H\u0002J\u0010\u0010 \u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u000bH\u0002J\u0010\u0010\"\u001a\u00020\u00152\u0006\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020\u0015H\u0002J\b\u0010&\u001a\u00020\u0015H\u0002J\u0010\u0010'\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u000bH\u0002J\b\u0010(\u001a\u00020)H\u0016J\u0010\u0010*\u001a\u00020\u00152\u0006\u0010+\u001a\u00020,H\u0016J\b\u0010-\u001a\u00020\u0015H\u0002J\u0012\u0010.\u001a\u00020\u00152\b\u0010/\u001a\u0004\u0018\u000100H\u0014J\b\u00101\u001a\u00020\u0015H\u0014J\b\u00102\u001a\u00020\u0015H\u0002J\b\u00103\u001a\u00020\u0015H\u0002J\u0010\u00104\u001a\u00020\u00152\u0006\u00105\u001a\u00020)H\u0016J\b\u00106\u001a\u00020\u0015H\u0002J\b\u00107\u001a\u00020\u0015H\u0002J\u0018\u00108\u001a\u00020\u00152\b\u00109\u001a\u0004\u0018\u00010:2\u0006\u0010;\u001a\u00020<J\b\u0010=\u001a\u00020\u0015H\u0002J\u0010\u0010>\u001a\u00020\u00152\u0006\u00105\u001a\u00020)H\u0016J\b\u0010?\u001a\u00020\u0015H\u0002J\u0010\u0010@\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u000bH\u0002J\b\u0010A\u001a\u00020\u0015H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u001bj\b\u0012\u0004\u0012\u00020\u0005`\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006D"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/DeliverSettingActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliverSettingContract$ViewInterface;", "()V", "TAG", "", "beeperCallHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/BeeperCallHelper;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "currentPalletTtsScheme", "Lcom/pudutech/bumblebee/robot_ui/bean/PalletTtsScheme;", "deliverSettingPresenter", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliverSettingPresenter;", "getDeliverSettingPresenter", "()Lcom/pudutech/bumblebee/presenter/delivery_task/DeliverSettingPresenter;", "deliverSettingPresenter$delegate", "Lkotlin/Lazy;", "onCustomCallListener", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallTargetBean;", "", "palletTtsSchemeList", "", "palletTtsTaskNameAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/PalletTtsTaskNameAdapter;", "playIntervalList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "popupWindowPalletTtsEdit", "Landroid/widget/PopupWindow;", "bindPresenter", "deletePalletTtsScheme", "palletTtsScheme", "deleteTtsConfigDataIfNeed", "ttsConfigData", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;", "initPalletTtsView", "initView", "insertPalletTtsScheme", "isBusyState", "", "jumpAndFinish", "intent", "Landroid/content/Intent;", "loadPalletTtsScheme", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "release", "showInputDialog", "showOutletSelectSwitch", "state", "showPalletTaskNameDialog", "showPalletTtsArrivedDialog", "showPalletTtsEditWindow", "view", "Landroid/view/View;", RequestParameters.POSITION, "", "showPalletTtsMovingDialog", "showVoiceSwitch", "unbindPresenter", "updateCurrentPalletTtsScheme", "updateCurrentPalletTtsSchemeView", "Companion", "SpacesItemDecoration", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class DeliverSettingActivity extends MyBaseActivity implements DeliverSettingContract.ViewInterface {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private HashMap _$_findViewCache;
    private PalletTtsScheme currentPalletTtsScheme;
    private PalletTtsTaskNameAdapter palletTtsTaskNameAdapter;
    private PopupWindow popupWindowPalletTtsEdit;
    private final String TAG = "DeliverSettingActivity";

    /* renamed from: deliverSettingPresenter$delegate, reason: from kotlin metadata */
    private final Lazy deliverSettingPresenter = LazyKt.lazy(new Function0<DeliverSettingPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$deliverSettingPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final DeliverSettingPresenter invoke() {
            DeliverSettingPresenter deliverSettingPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(DeliverSettingPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(DeliverSettingPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                deliverSettingPresenter = new DeliverSettingPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(DeliverSettingPresenter.class).toString(), deliverSettingPresenter);
            } else {
                if (!(basePresenterInterface instanceof DeliverSettingPresenter)) {
                    basePresenterInterface = null;
                }
                deliverSettingPresenter = (DeliverSettingPresenter) basePresenterInterface;
            }
            if (deliverSettingPresenter != null) {
                return deliverSettingPresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.delivery_task.DeliverSettingPresenter");
        }
    });
    private Function1<? super CustomCallTargetBean, Unit> onCustomCallListener = new Function1<CustomCallTargetBean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$onCustomCallListener$1
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
            str = DeliverSettingActivity.this.TAG;
            Pdlog.m3273d(str, "onCustomCallListener task: " + it);
            DeliverSettingActivity.this.jumpAndFinish(CustomCallActivity.Companion.createIntent(DeliverSettingActivity.this, it));
        }
    };
    private final BeeperCallHelper beeperCallHelper = new BeeperCallHelper();
    private final List<PalletTtsScheme> palletTtsSchemeList = new ArrayList();
    private final ArrayList<String> playIntervalList = CollectionsKt.arrayListOf("5s", "10s", "15s", "20s", "25s");
    private final CoroutineScope coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());

    /* JADX INFO: Access modifiers changed from: private */
    public final DeliverSettingPresenter getDeliverSettingPresenter() {
        return (DeliverSettingPresenter) this.deliverSettingPresenter.getValue();
    }

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

    /* compiled from: DeliverSettingActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/DeliverSettingActivity$Companion;", "", "()V", "createIntent", "Landroid/content/Intent;", AIUIConstant.KEY_CONTENT, "Landroid/content/Context;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Intent createIntent(Context content) {
            Intrinsics.checkParameterIsNotNull(content, "content");
            return new Intent(content, (Class<?>) DeliverSettingActivity.class);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C4188R.layout.activity_deliver_setting);
        bindPresenter();
        initView();
        SignalView signal_view = (SignalView) _$_findCachedViewById(C4188R.id.signal_view);
        Intrinsics.checkExpressionValueIsNotNull(signal_view, "signal_view");
        setBindSignal(signal_view);
        if (getIntent().getIntExtra("MODE_TYPE", 0) == 0) {
            initPalletTtsView();
            updateCurrentPalletTtsSchemeView();
            loadPalletTtsScheme();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        CoroutineScopeKt.cancel$default(this.coroutineScope, null, 1, null);
    }

    private final void bindPresenter() {
        getDeliverSettingPresenter().replaceView(this);
        CustomCallHelper.INSTANCE.addCallListener(this.onCustomCallListener);
        BeeperCallHelper.bind$default(this.beeperCallHelper, this, false, false, 6, null);
    }

    private final void unbindPresenter() {
        getDeliverSettingPresenter().removeView(this);
        CustomCallHelper.INSTANCE.removeCallListener(this.onCustomCallListener);
        this.beeperCallHelper.unbind();
    }

    private final void initView() {
        ((LinearLayout) _$_findCachedViewById(C4188R.id.back_ll)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$initView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                Intent intent = new Intent(DeliverSettingActivity.this, (Class<?>) DeliverTaskEditActivity.class);
                intent.putExtra("MODE_TYPE", 0);
                DeliverSettingActivity.this.jumpAndFinish(intent);
            }
        });
        ((Switch) _$_findCachedViewById(C4188R.id.multi_voice_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$initView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton buttonView, boolean z) {
                DeliverSettingPresenter deliverSettingPresenter;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                if (buttonView.isPressed()) {
                    deliverSettingPresenter = DeliverSettingActivity.this.getDeliverSettingPresenter();
                    deliverSettingPresenter.actionSaveVoiceSwitchState(z);
                }
            }
        }, 7, null));
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.swOutlet), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$initView$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton compoundButton, boolean z) {
                DeliverSettingPresenter deliverSettingPresenter;
                Intrinsics.checkParameterIsNotNull(compoundButton, "<anonymous parameter 0>");
                deliverSettingPresenter = DeliverSettingActivity.this.getDeliverSettingPresenter();
                deliverSettingPresenter.actionSaveOutletSelectSwitchState(z);
            }
        }, 7, null);
        ((ImageView) _$_findCachedViewById(C4188R.id.item_edit_iv_2)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$initView$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                DeliverSettingActivity.this.showInputDialog();
            }
        });
        if (Constans.INSTANCE.getCustomFinishBtnContent().length() > 0) {
            TextView item_content_tv_2 = (TextView) _$_findCachedViewById(C4188R.id.item_content_tv_2);
            Intrinsics.checkExpressionValueIsNotNull(item_content_tv_2, "item_content_tv_2");
            item_content_tv_2.setText(Constans.INSTANCE.getCustomFinishBtnContent());
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, com.pudutech.bumblebee.robot_ui.p054ui.FinishInter
    public void jumpAndFinish(Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        release();
        startActivity(intent);
        finish();
    }

    private final void release() {
        unbindPresenter();
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DeliverSettingContract.ViewInterface
    public void showVoiceSwitch(boolean state) {
        Switch multi_voice_switch = (Switch) _$_findCachedViewById(C4188R.id.multi_voice_switch);
        Intrinsics.checkExpressionValueIsNotNull(multi_voice_switch, "multi_voice_switch");
        multi_voice_switch.setChecked(state);
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DeliverSettingContract.ViewInterface
    public void showOutletSelectSwitch(boolean state) {
        Switch swOutlet = (Switch) _$_findCachedViewById(C4188R.id.swOutlet);
        Intrinsics.checkExpressionValueIsNotNull(swOutlet, "swOutlet");
        swOutlet.setChecked(state);
    }

    private final void initPalletTtsView() {
        Switch swPalletTts = (Switch) _$_findCachedViewById(C4188R.id.swPalletTts);
        Intrinsics.checkExpressionValueIsNotNull(swPalletTts, "swPalletTts");
        DeliverSettingActivity deliverSettingActivity = this;
        swPalletTts.setChecked(TtsVoiceHelper.INSTANCE.isOpen(deliverSettingActivity, TtsVoiceHelper.TtsVoiceType.PALLET_DELIVER_TYPE));
        ((Switch) _$_findCachedViewById(C4188R.id.swPalletTts)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$initPalletTtsView$1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    TtsVoiceHelper.INSTANCE.setTtsType(DeliverSettingActivity.this, TtsVoiceHelper.TtsVoiceOpenType.OPEN, TtsVoiceHelper.TtsVoiceType.PALLET_DELIVER_TYPE);
                } else {
                    TtsVoiceHelper.INSTANCE.setTtsType(DeliverSettingActivity.this, TtsVoiceHelper.TtsVoiceOpenType.CLOSE, TtsVoiceHelper.TtsVoiceType.PALLET_DELIVER_TYPE);
                }
                DeliverSettingActivity.this.updateCurrentPalletTtsSchemeView();
            }
        });
        RecyclerView rvPalletScheme = (RecyclerView) _$_findCachedViewById(C4188R.id.rvPalletScheme);
        Intrinsics.checkExpressionValueIsNotNull(rvPalletScheme, "rvPalletScheme");
        rvPalletScheme.setLayoutManager(new LinearLayoutManager(deliverSettingActivity, 0, false));
        ((RecyclerView) _$_findCachedViewById(C4188R.id.rvPalletScheme)).addItemDecoration(new SpacesItemDecoration(getResources().getDimensionPixelSize(C4188R.dimen.deliver_task_interval_32dp)));
        this.palletTtsTaskNameAdapter = new PalletTtsTaskNameAdapter(deliverSettingActivity);
        RecyclerView rvPalletScheme2 = (RecyclerView) _$_findCachedViewById(C4188R.id.rvPalletScheme);
        Intrinsics.checkExpressionValueIsNotNull(rvPalletScheme2, "rvPalletScheme");
        rvPalletScheme2.setAdapter(this.palletTtsTaskNameAdapter);
        PalletTtsTaskNameAdapter palletTtsTaskNameAdapter = this.palletTtsTaskNameAdapter;
        if (palletTtsTaskNameAdapter != null) {
            palletTtsTaskNameAdapter.setNewData(this.palletTtsSchemeList);
        }
        PalletTtsTaskNameAdapter palletTtsTaskNameAdapter2 = this.palletTtsTaskNameAdapter;
        if (palletTtsTaskNameAdapter2 != null) {
            palletTtsTaskNameAdapter2.setOnItemClickListener(new OnLazyItemClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$initPalletTtsView$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(null, 0, 3, null);
                }

                @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener
                public void onSingleItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                    PalletTtsTaskNameAdapter palletTtsTaskNameAdapter3;
                    PalletTtsScheme palletTtsScheme;
                    Intrinsics.checkParameterIsNotNull(adapter, "adapter");
                    Intrinsics.checkParameterIsNotNull(view, "view");
                    DeliverSettingActivity deliverSettingActivity2 = DeliverSettingActivity.this;
                    Object obj = adapter.getData().get(position);
                    if (obj != null) {
                        deliverSettingActivity2.currentPalletTtsScheme = (PalletTtsScheme) obj;
                        palletTtsTaskNameAdapter3 = DeliverSettingActivity.this.palletTtsTaskNameAdapter;
                        if (palletTtsTaskNameAdapter3 != null) {
                            palletTtsScheme = DeliverSettingActivity.this.currentPalletTtsScheme;
                            palletTtsTaskNameAdapter3.setSelectedPalletTtsScheme(palletTtsScheme);
                            palletTtsTaskNameAdapter3.notifyDataSetChanged();
                        }
                        DeliverSettingActivity.this.updateCurrentPalletTtsSchemeView();
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.bean.PalletTtsScheme");
                }
            });
        }
        PalletTtsTaskNameAdapter palletTtsTaskNameAdapter3 = this.palletTtsTaskNameAdapter;
        if (palletTtsTaskNameAdapter3 != null) {
            palletTtsTaskNameAdapter3.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$initPalletTtsView$3
                @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemLongClickListener
                public boolean onItemLongClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                    DeliverSettingActivity.this.showPalletTtsEditWindow(view, position);
                    return true;
                }
            });
        }
        ((ImageView) _$_findCachedViewById(C4188R.id.tvAddScheme)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$initPalletTtsView$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                PalletTtsScheme palletTtsScheme;
                List list;
                PalletTtsTaskNameAdapter palletTtsTaskNameAdapter4;
                PalletTtsScheme palletTtsScheme2;
                super.onSingleClick();
                DeliverSettingActivity.this.currentPalletTtsScheme = new PalletTtsScheme();
                palletTtsScheme = DeliverSettingActivity.this.currentPalletTtsScheme;
                if (palletTtsScheme != null) {
                    palletTtsScheme.setLocale(LanguageUtils.Companion.getLocaleStr$default(LanguageUtils.INSTANCE, false, 1, null));
                    StringBuilder sb = new StringBuilder();
                    sb.append(DeliverSettingActivity.this.getString(C4188R.string.pallet_tts));
                    list = DeliverSettingActivity.this.palletTtsSchemeList;
                    sb.append(list.size() + 1);
                    palletTtsScheme.setTaskName(sb.toString());
                    palletTtsTaskNameAdapter4 = DeliverSettingActivity.this.palletTtsTaskNameAdapter;
                    if (palletTtsTaskNameAdapter4 != null) {
                        palletTtsScheme2 = DeliverSettingActivity.this.currentPalletTtsScheme;
                        palletTtsTaskNameAdapter4.setSelectedPalletTtsScheme(palletTtsScheme2);
                        palletTtsTaskNameAdapter4.notifyDataSetChanged();
                    }
                    DeliverSettingActivity.this.insertPalletTtsScheme(palletTtsScheme);
                    DeliverSettingActivity.this.updateCurrentPalletTtsSchemeView();
                }
            }
        });
        Switch swPalletTtsOnTheWay = (Switch) _$_findCachedViewById(C4188R.id.swPalletTtsOnTheWay);
        Intrinsics.checkExpressionValueIsNotNull(swPalletTtsOnTheWay, "swPalletTtsOnTheWay");
        PalletTtsScheme palletTtsScheme = this.currentPalletTtsScheme;
        swPalletTtsOnTheWay.setChecked(palletTtsScheme != null && palletTtsScheme.getIsTtsPalletMovingOn());
        ((Switch) _$_findCachedViewById(C4188R.id.swPalletTtsOnTheWay)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$initPalletTtsView$5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                PalletTtsScheme palletTtsScheme2;
                palletTtsScheme2 = DeliverSettingActivity.this.currentPalletTtsScheme;
                if (palletTtsScheme2 != null) {
                    palletTtsScheme2.setTtsPalletMovingOn(z);
                    DeliverSettingActivity.this.updateCurrentPalletTtsScheme(palletTtsScheme2);
                    DeliverSettingActivity.this.updateCurrentPalletTtsSchemeView();
                }
            }
        });
        Switch swPalletTtsArrived = (Switch) _$_findCachedViewById(C4188R.id.swPalletTtsArrived);
        Intrinsics.checkExpressionValueIsNotNull(swPalletTtsArrived, "swPalletTtsArrived");
        PalletTtsScheme palletTtsScheme2 = this.currentPalletTtsScheme;
        swPalletTtsArrived.setChecked(palletTtsScheme2 != null && palletTtsScheme2.getIsTtsPalletArrivedOn());
        ((Switch) _$_findCachedViewById(C4188R.id.swPalletTtsArrived)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$initPalletTtsView$6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                PalletTtsScheme palletTtsScheme3;
                palletTtsScheme3 = DeliverSettingActivity.this.currentPalletTtsScheme;
                if (palletTtsScheme3 != null) {
                    palletTtsScheme3.setTtsPalletArrivedOn(z);
                    DeliverSettingActivity.this.updateCurrentPalletTtsScheme(palletTtsScheme3);
                    DeliverSettingActivity.this.updateCurrentPalletTtsSchemeView();
                }
            }
        });
        ((ImageView) _$_findCachedViewById(C4188R.id.ivEditPalletTtsSchemeName)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$initPalletTtsView$7
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick(View v) {
                Intrinsics.checkParameterIsNotNull(v, "v");
                super.onSingleClick(v);
                DeliverSettingActivity.this.showPalletTaskNameDialog();
            }
        });
        ((ImageView) _$_findCachedViewById(C4188R.id.ivEditPalletTtsOnTheWay)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$initPalletTtsView$8
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick(View v) {
                Intrinsics.checkParameterIsNotNull(v, "v");
                super.onSingleClick(v);
                DeliverSettingActivity.this.showPalletTtsMovingDialog();
            }
        });
        ((ImageView) _$_findCachedViewById(C4188R.id.ivEditPalletTtsArrived)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$initPalletTtsView$9
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick(View v) {
                Intrinsics.checkParameterIsNotNull(v, "v");
                super.onSingleClick(v);
                DeliverSettingActivity.this.showPalletTtsArrivedDialog();
            }
        });
        ((ImageView) _$_findCachedViewById(C4188R.id.ivPlayPalletTtsOnTheWay)).setOnClickListener(new DeliverSettingActivity$initPalletTtsView$10(this));
        ((ImageView) _$_findCachedViewById(C4188R.id.ivPlayPalletTtsArrived)).setOnClickListener(new DeliverSettingActivity$initPalletTtsView$11(this));
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.isPalletTtsInterval);
        indicatorSeekBar.setTickCount(this.playIntervalList.size());
        Object[] array = this.playIntervalList.toArray(new String[0]);
        if (array != null) {
            indicatorSeekBar.customTickTexts((String[]) array);
            indicatorSeekBar.setIndicatorTextFormat("${TICK_TEXT}");
            PalletTtsScheme palletTtsScheme3 = this.currentPalletTtsScheme;
            long interval = palletTtsScheme3 != null ? palletTtsScheme3.getInterval() : 15L;
            ArrayList<String> arrayList = this.playIntervalList;
            StringBuilder sb = new StringBuilder();
            sb.append(interval);
            sb.append('s');
            indicatorSeekBar.setProgress((arrayList.indexOf(sb.toString()) * 100.0f) / (this.playIntervalList.size() - 1));
            indicatorSeekBar.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$initPalletTtsView$$inlined$apply$lambda$1
                private float currentIntervalProgress;

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onStartTrackingTouch(IndicatorSeekBar indicatorSeekBar2) {
                }

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onSeeking(SeekParams seekParams) {
                    Intrinsics.checkParameterIsNotNull(seekParams, "seekParams");
                    this.currentIntervalProgress = seekParams.progressFloat;
                }

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onStopTrackingTouch(IndicatorSeekBar indicatorSeekBar2) {
                    ArrayList arrayList2;
                    ArrayList arrayList3;
                    PalletTtsScheme palletTtsScheme4;
                    String str;
                    SoundPoolVoiceUtil.INSTANCE.play(SoundPoolVoiceUtil.Voice.CLICK_1);
                    float f = this.currentIntervalProgress / 100.0f;
                    arrayList2 = DeliverSettingActivity.this.playIntervalList;
                    int rint = (int) Math.rint(f * (arrayList2.size() - 1));
                    arrayList3 = DeliverSettingActivity.this.playIntervalList;
                    Object obj = arrayList3.get(rint);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "playIntervalList[index]");
                    String str2 = (String) obj;
                    palletTtsScheme4 = DeliverSettingActivity.this.currentPalletTtsScheme;
                    if (palletTtsScheme4 != null) {
                        str = DeliverSettingActivity.this.TAG;
                        Pdlog.m3273d(str, "PalletTtsInterval: " + str2);
                        int length = str2.length() - 1;
                        if (str2 != null) {
                            String substring = str2.substring(0, length);
                            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                            palletTtsScheme4.setInterval(Long.parseLong(substring));
                            DeliverSettingActivity.this.updateCurrentPalletTtsScheme(palletTtsScheme4);
                            return;
                        }
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                }
            });
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0296  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void updateCurrentPalletTtsSchemeView() {
        boolean z;
        boolean z2;
        String str;
        String str2;
        String name;
        String replace;
        String name2;
        String replace2;
        String replace3;
        PalletTtsScheme palletTtsScheme = this.currentPalletTtsScheme;
        if (palletTtsScheme != null) {
            Regex regex = new Regex("\r|\n+");
            TextView tvPalletTtsSchemeName = (TextView) _$_findCachedViewById(C4188R.id.tvPalletTtsSchemeName);
            Intrinsics.checkExpressionValueIsNotNull(tvPalletTtsSchemeName, "tvPalletTtsSchemeName");
            String taskName = palletTtsScheme.getTaskName();
            String str3 = null;
            if (taskName == null || (replace3 = regex.replace(taskName, " ")) == null) {
                str = null;
            } else {
                if (replace3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                }
                str = StringsKt.trim((CharSequence) replace3).toString();
            }
            tvPalletTtsSchemeName.setText(str);
            MarqueeTextView tvPalletTtsOnTheWay = (MarqueeTextView) _$_findCachedViewById(C4188R.id.tvPalletTtsOnTheWay);
            Intrinsics.checkExpressionValueIsNotNull(tvPalletTtsOnTheWay, "tvPalletTtsOnTheWay");
            TtsVoiceHelper.TtsConfigData ttsPalletMoving = palletTtsScheme.getTtsPalletMoving();
            if (ttsPalletMoving == null || (name2 = ttsPalletMoving.getName()) == null || (replace2 = regex.replace(name2, " ")) == null) {
                str2 = null;
            } else {
                if (replace2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                }
                str2 = StringsKt.trim((CharSequence) replace2).toString();
            }
            tvPalletTtsOnTheWay.setText(str2);
            MarqueeTextView tvPalletTtsArrived = (MarqueeTextView) _$_findCachedViewById(C4188R.id.tvPalletTtsArrived);
            Intrinsics.checkExpressionValueIsNotNull(tvPalletTtsArrived, "tvPalletTtsArrived");
            TtsVoiceHelper.TtsConfigData ttsPalletArrived = palletTtsScheme.getTtsPalletArrived();
            if (ttsPalletArrived != null && (name = ttsPalletArrived.getName()) != null && (replace = regex.replace(name, " ")) != null) {
                if (replace == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                }
                str3 = StringsKt.trim((CharSequence) replace).toString();
            }
            tvPalletTtsArrived.setText(str3);
        }
        Switch swPalletTtsOnTheWay = (Switch) _$_findCachedViewById(C4188R.id.swPalletTtsOnTheWay);
        Intrinsics.checkExpressionValueIsNotNull(swPalletTtsOnTheWay, "swPalletTtsOnTheWay");
        PalletTtsScheme palletTtsScheme2 = this.currentPalletTtsScheme;
        swPalletTtsOnTheWay.setChecked(palletTtsScheme2 != null && palletTtsScheme2.getIsTtsPalletMovingOn());
        Switch swPalletTtsArrived = (Switch) _$_findCachedViewById(C4188R.id.swPalletTtsArrived);
        Intrinsics.checkExpressionValueIsNotNull(swPalletTtsArrived, "swPalletTtsArrived");
        PalletTtsScheme palletTtsScheme3 = this.currentPalletTtsScheme;
        swPalletTtsArrived.setChecked(palletTtsScheme3 != null && palletTtsScheme3.getIsTtsPalletArrivedOn());
        TtsVoiceHelper.INSTANCE.stopCruiseTts();
        ((LottieAnimationView) _$_findCachedViewById(C4188R.id.avPalletTtsArrived)).cancelAnimation();
        LottieAnimationView avPalletTtsArrived = (LottieAnimationView) _$_findCachedViewById(C4188R.id.avPalletTtsArrived);
        Intrinsics.checkExpressionValueIsNotNull(avPalletTtsArrived, "avPalletTtsArrived");
        avPalletTtsArrived.setVisibility(8);
        ((ImageView) _$_findCachedViewById(C4188R.id.ivPlayPalletTtsArrived)).setImageResource(C4188R.drawable.ic_stop);
        ((LottieAnimationView) _$_findCachedViewById(C4188R.id.avPalletTtsOnTheWay)).cancelAnimation();
        LottieAnimationView avPalletTtsOnTheWay = (LottieAnimationView) _$_findCachedViewById(C4188R.id.avPalletTtsOnTheWay);
        Intrinsics.checkExpressionValueIsNotNull(avPalletTtsOnTheWay, "avPalletTtsOnTheWay");
        avPalletTtsOnTheWay.setVisibility(8);
        ((ImageView) _$_findCachedViewById(C4188R.id.ivPlayPalletTtsOnTheWay)).setImageResource(C4188R.drawable.ic_stop);
        ConstraintLayout viewPalletTts = (ConstraintLayout) _$_findCachedViewById(C4188R.id.viewPalletTts);
        Intrinsics.checkExpressionValueIsNotNull(viewPalletTts, "viewPalletTts");
        viewPalletTts.setVisibility(Constans.INSTANCE.isOpenPalletDeliverTtsFunction() && !LanguageUtils.INSTANCE.isNoSupportTts() ? 0 : 8);
        Group groupPalletTtsSchemeTitle = (Group) _$_findCachedViewById(C4188R.id.groupPalletTtsSchemeTitle);
        Intrinsics.checkExpressionValueIsNotNull(groupPalletTtsSchemeTitle, "groupPalletTtsSchemeTitle");
        Group group = groupPalletTtsSchemeTitle;
        Switch swPalletTts = (Switch) _$_findCachedViewById(C4188R.id.swPalletTts);
        Intrinsics.checkExpressionValueIsNotNull(swPalletTts, "swPalletTts");
        group.setVisibility(swPalletTts.isChecked() && (this.palletTtsSchemeList.isEmpty() ^ true) ? 0 : 8);
        ImageView tvAddScheme = (ImageView) _$_findCachedViewById(C4188R.id.tvAddScheme);
        Intrinsics.checkExpressionValueIsNotNull(tvAddScheme, "tvAddScheme");
        ImageView imageView = tvAddScheme;
        Switch swPalletTts2 = (Switch) _$_findCachedViewById(C4188R.id.swPalletTts);
        Intrinsics.checkExpressionValueIsNotNull(swPalletTts2, "swPalletTts");
        imageView.setVisibility(swPalletTts2.isChecked() && this.palletTtsSchemeList.size() < 4 ? 0 : 8);
        ConstraintLayout viewPalletTtsScheme = (ConstraintLayout) _$_findCachedViewById(C4188R.id.viewPalletTtsScheme);
        Intrinsics.checkExpressionValueIsNotNull(viewPalletTtsScheme, "viewPalletTtsScheme");
        ConstraintLayout constraintLayout = viewPalletTtsScheme;
        Switch swPalletTts3 = (Switch) _$_findCachedViewById(C4188R.id.swPalletTts);
        Intrinsics.checkExpressionValueIsNotNull(swPalletTts3, "swPalletTts");
        constraintLayout.setVisibility(swPalletTts3.isChecked() && (this.palletTtsSchemeList.isEmpty() ^ true) ? 0 : 8);
        Group groupPalletTtsOnTheWay = (Group) _$_findCachedViewById(C4188R.id.groupPalletTtsOnTheWay);
        Intrinsics.checkExpressionValueIsNotNull(groupPalletTtsOnTheWay, "groupPalletTtsOnTheWay");
        Group group2 = groupPalletTtsOnTheWay;
        Switch swPalletTtsOnTheWay2 = (Switch) _$_findCachedViewById(C4188R.id.swPalletTtsOnTheWay);
        Intrinsics.checkExpressionValueIsNotNull(swPalletTtsOnTheWay2, "swPalletTtsOnTheWay");
        group2.setVisibility(swPalletTtsOnTheWay2.isChecked() ? 0 : 8);
        Group groupPalletTtsOnTheWay2 = (Group) _$_findCachedViewById(C4188R.id.groupPalletTtsOnTheWay);
        Intrinsics.checkExpressionValueIsNotNull(groupPalletTtsOnTheWay2, "groupPalletTtsOnTheWay");
        if (groupPalletTtsOnTheWay2.getVisibility() == 0) {
            ImageView ivPlayPalletTtsOnTheWay = (ImageView) _$_findCachedViewById(C4188R.id.ivPlayPalletTtsOnTheWay);
            Intrinsics.checkExpressionValueIsNotNull(ivPlayPalletTtsOnTheWay, "ivPlayPalletTtsOnTheWay");
            ImageView imageView2 = ivPlayPalletTtsOnTheWay;
            MarqueeTextView tvPalletTtsOnTheWay2 = (MarqueeTextView) _$_findCachedViewById(C4188R.id.tvPalletTtsOnTheWay);
            Intrinsics.checkExpressionValueIsNotNull(tvPalletTtsOnTheWay2, "tvPalletTtsOnTheWay");
            Intrinsics.checkExpressionValueIsNotNull(tvPalletTtsOnTheWay2.getText(), "tvPalletTtsOnTheWay.text");
            if (!StringsKt.isBlank(r2)) {
                Switch swPalletTtsOnTheWay3 = (Switch) _$_findCachedViewById(C4188R.id.swPalletTtsOnTheWay);
                Intrinsics.checkExpressionValueIsNotNull(swPalletTtsOnTheWay3, "swPalletTtsOnTheWay");
                if (swPalletTtsOnTheWay3.isChecked()) {
                    z2 = true;
                    imageView2.setVisibility(!z2 ? 0 : 8);
                }
            }
            z2 = false;
            imageView2.setVisibility(!z2 ? 0 : 8);
        }
        Group groupPalletTtsArrived = (Group) _$_findCachedViewById(C4188R.id.groupPalletTtsArrived);
        Intrinsics.checkExpressionValueIsNotNull(groupPalletTtsArrived, "groupPalletTtsArrived");
        Group group3 = groupPalletTtsArrived;
        Switch swPalletTtsArrived2 = (Switch) _$_findCachedViewById(C4188R.id.swPalletTtsArrived);
        Intrinsics.checkExpressionValueIsNotNull(swPalletTtsArrived2, "swPalletTtsArrived");
        group3.setVisibility(swPalletTtsArrived2.isChecked() ? 0 : 8);
        Group groupPalletTtsArrived2 = (Group) _$_findCachedViewById(C4188R.id.groupPalletTtsArrived);
        Intrinsics.checkExpressionValueIsNotNull(groupPalletTtsArrived2, "groupPalletTtsArrived");
        if (groupPalletTtsArrived2.getVisibility() == 0) {
            ImageView ivPlayPalletTtsArrived = (ImageView) _$_findCachedViewById(C4188R.id.ivPlayPalletTtsArrived);
            Intrinsics.checkExpressionValueIsNotNull(ivPlayPalletTtsArrived, "ivPlayPalletTtsArrived");
            ImageView imageView3 = ivPlayPalletTtsArrived;
            MarqueeTextView tvPalletTtsArrived2 = (MarqueeTextView) _$_findCachedViewById(C4188R.id.tvPalletTtsArrived);
            Intrinsics.checkExpressionValueIsNotNull(tvPalletTtsArrived2, "tvPalletTtsArrived");
            Intrinsics.checkExpressionValueIsNotNull(tvPalletTtsArrived2.getText(), "tvPalletTtsArrived.text");
            if (!StringsKt.isBlank(r1)) {
                Switch swPalletTtsArrived3 = (Switch) _$_findCachedViewById(C4188R.id.swPalletTtsArrived);
                Intrinsics.checkExpressionValueIsNotNull(swPalletTtsArrived3, "swPalletTtsArrived");
                if (swPalletTtsArrived3.isChecked()) {
                    z = true;
                    imageView3.setVisibility(z ? 0 : 8);
                }
            }
            z = false;
            imageView3.setVisibility(z ? 0 : 8);
        }
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.isPalletTtsInterval);
        indicatorSeekBar.setTickCount(this.playIntervalList.size());
        Object[] array = this.playIntervalList.toArray(new String[0]);
        if (array != null) {
            indicatorSeekBar.customTickTexts((String[]) array);
            PalletTtsScheme palletTtsScheme4 = this.currentPalletTtsScheme;
            long interval = palletTtsScheme4 != null ? palletTtsScheme4.getInterval() : 15L;
            ArrayList<String> arrayList = this.playIntervalList;
            StringBuilder sb = new StringBuilder();
            sb.append(interval);
            sb.append('s');
            indicatorSeekBar.setProgress((arrayList.indexOf(sb.toString()) * 100.0f) / (this.playIntervalList.size() - 1));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final void loadPalletTtsScheme() {
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new DeliverSettingActivity$loadPalletTtsScheme$1(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateCurrentPalletTtsScheme(final PalletTtsScheme palletTtsScheme) {
        Pdlog.m3273d(this.TAG, "updateCurrentPalletTtsScheme, palletTtsScheme: " + palletTtsScheme.toString());
        PalletTtsTaskNameAdapter palletTtsTaskNameAdapter = this.palletTtsTaskNameAdapter;
        if (palletTtsTaskNameAdapter != null) {
            palletTtsTaskNameAdapter.notifyItemChanged(this.palletTtsSchemeList.indexOf(palletTtsScheme));
        }
        Behavior.INSTANCE.runOnTaskThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$updateCurrentPalletTtsScheme$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                TtsDataBaseManager.INSTANCE.getInstance().getMPalletTtsSchemeDao().updatePalletTtsScheme(PalletTtsScheme.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void insertPalletTtsScheme(final PalletTtsScheme palletTtsScheme) {
        this.palletTtsSchemeList.add(palletTtsScheme);
        PalletTtsTaskNameAdapter palletTtsTaskNameAdapter = this.palletTtsTaskNameAdapter;
        if (palletTtsTaskNameAdapter != null) {
            palletTtsTaskNameAdapter.notifyItemChanged(this.palletTtsSchemeList.size() - 1);
        }
        Behavior.INSTANCE.runOnTaskThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$insertPalletTtsScheme$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                PalletTtsScheme.this.setMid(TtsDataBaseManager.INSTANCE.getInstance().getMPalletTtsSchemeDao().insertPalletTtsScheme(PalletTtsScheme.this));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deletePalletTtsScheme(final PalletTtsScheme palletTtsScheme) {
        PalletTtsScheme palletTtsScheme2;
        int indexOf = this.palletTtsSchemeList.indexOf(palletTtsScheme);
        this.palletTtsSchemeList.remove(palletTtsScheme);
        TtsVoiceHelper.TtsConfigData ttsPalletArrived = palletTtsScheme.getTtsPalletArrived();
        if (ttsPalletArrived != null) {
            deleteTtsConfigDataIfNeed(ttsPalletArrived);
        }
        TtsVoiceHelper.TtsConfigData ttsPalletMoving = palletTtsScheme.getTtsPalletMoving();
        if (ttsPalletMoving != null) {
            deleteTtsConfigDataIfNeed(ttsPalletMoving);
        }
        if (this.palletTtsSchemeList.size() <= 0) {
            this.currentPalletTtsScheme = (PalletTtsScheme) null;
        } else {
            PalletTtsTaskNameAdapter palletTtsTaskNameAdapter = this.palletTtsTaskNameAdapter;
            if (Intrinsics.areEqual(palletTtsTaskNameAdapter != null ? palletTtsTaskNameAdapter.getSelectedPalletTtsScheme() : null, palletTtsScheme)) {
                if (indexOf >= 0 && indexOf <= this.palletTtsSchemeList.size() - 1) {
                    palletTtsScheme2 = this.palletTtsSchemeList.get(indexOf);
                } else if (indexOf > this.palletTtsSchemeList.size() - 1) {
                    palletTtsScheme2 = this.palletTtsSchemeList.get(r0.size() - 1);
                } else {
                    palletTtsScheme2 = this.palletTtsSchemeList.get(0);
                }
                this.currentPalletTtsScheme = palletTtsScheme2;
            }
        }
        PalletTtsTaskNameAdapter palletTtsTaskNameAdapter2 = this.palletTtsTaskNameAdapter;
        if (palletTtsTaskNameAdapter2 != null) {
            palletTtsTaskNameAdapter2.setSelectedPalletTtsScheme(this.currentPalletTtsScheme);
        }
        PalletTtsTaskNameAdapter palletTtsTaskNameAdapter3 = this.palletTtsTaskNameAdapter;
        if (palletTtsTaskNameAdapter3 != null) {
            palletTtsTaskNameAdapter3.notifyDataSetChanged();
        }
        updateCurrentPalletTtsSchemeView();
        Behavior.INSTANCE.runOnTaskThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$deletePalletTtsScheme$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                TtsDataBaseManager.INSTANCE.getInstance().getMPalletTtsSchemeDao().deletePalletTtsScheme(PalletTtsScheme.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteTtsConfigDataIfNeed(TtsVoiceHelper.TtsConfigData ttsConfigData) {
        boolean z;
        List<PalletTtsScheme> list = this.palletTtsSchemeList;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            for (PalletTtsScheme palletTtsScheme : list) {
                if (Intrinsics.areEqual(palletTtsScheme.getTtsPalletMoving(), ttsConfigData) || Intrinsics.areEqual(palletTtsScheme.getTtsPalletArrived(), ttsConfigData)) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        Pdlog.m3273d(this.TAG, "otherHold:" + z);
        if (z) {
            return;
        }
        TtsVoiceHelper.INSTANCE.deleteConfig(ttsConfigData, TtsVoiceHelper.TtsVoiceType.PALLET_DELIVER_TYPE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPalletTaskNameDialog() {
        String str;
        CustomTextInputDialog customTextInputDialog = new CustomTextInputDialog();
        PalletTtsScheme palletTtsScheme = this.currentPalletTtsScheme;
        if (palletTtsScheme == null || (str = palletTtsScheme.getTaskName()) == null) {
            str = "";
        }
        customTextInputDialog.setContent(str);
        customTextInputDialog.setMaxLength(10);
        customTextInputDialog.show(getSupportFragmentManager(), "text_custom");
        customTextInputDialog.setOnContentChange(new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$showPalletTaskNameDialog$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str2) {
                invoke2(str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String newName) {
                PalletTtsScheme palletTtsScheme2;
                String str2;
                String replace;
                Intrinsics.checkParameterIsNotNull(newName, "newName");
                palletTtsScheme2 = DeliverSettingActivity.this.currentPalletTtsScheme;
                if (palletTtsScheme2 != null) {
                    palletTtsScheme2.setTaskName(newName);
                    DeliverSettingActivity.this.updateCurrentPalletTtsScheme(palletTtsScheme2);
                    Regex regex = new Regex("\r|\n+");
                    TextView tvPalletTtsSchemeName = (TextView) DeliverSettingActivity.this._$_findCachedViewById(C4188R.id.tvPalletTtsSchemeName);
                    Intrinsics.checkExpressionValueIsNotNull(tvPalletTtsSchemeName, "tvPalletTtsSchemeName");
                    String taskName = palletTtsScheme2.getTaskName();
                    if (taskName == null || (replace = regex.replace(taskName, " ")) == null) {
                        str2 = null;
                    } else {
                        if (replace == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                        }
                        str2 = StringsKt.trim((CharSequence) replace).toString();
                    }
                    tvPalletTtsSchemeName.setText(str2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPalletTtsMovingDialog() {
        String str;
        TtsVoiceHelper.TtsConfigData ttsPalletMoving;
        CustomTtsVoiceInputDialog customTtsVoiceInputDialog = new CustomTtsVoiceInputDialog();
        PalletTtsScheme palletTtsScheme = this.currentPalletTtsScheme;
        if (palletTtsScheme == null || (ttsPalletMoving = palletTtsScheme.getTtsPalletMoving()) == null || (str = ttsPalletMoving.getName()) == null) {
            str = "";
        }
        customTtsVoiceInputDialog.setContent(str);
        customTtsVoiceInputDialog.setVoiceType(TtsVoiceHelper.TtsVoiceType.PALLET_DELIVER_TYPE);
        customTtsVoiceInputDialog.setMaxLength(200);
        customTtsVoiceInputDialog.show(getSupportFragmentManager(), "voice_custom");
        customTtsVoiceInputDialog.setOnContentChange(new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$showPalletTtsMovingDialog$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str2) {
                invoke2(str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String newName) {
                PalletTtsScheme palletTtsScheme2;
                Object obj;
                Intrinsics.checkParameterIsNotNull(newName, "newName");
                palletTtsScheme2 = DeliverSettingActivity.this.currentPalletTtsScheme;
                if (palletTtsScheme2 != null) {
                    TtsVoiceHelper.TtsConfigData ttsPalletMoving2 = palletTtsScheme2.getTtsPalletMoving();
                    Iterator<T> it = TtsVoiceHelper.INSTANCE.getTtsConfigList(TtsVoiceHelper.TtsVoiceType.PALLET_DELIVER_TYPE).iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            obj = null;
                            break;
                        } else {
                            obj = it.next();
                            if (Intrinsics.areEqual(((TtsVoiceHelper.TtsConfigData) obj).getName(), newName)) {
                                break;
                            }
                        }
                    }
                    palletTtsScheme2.setTtsPalletMoving((TtsVoiceHelper.TtsConfigData) obj);
                    if (ttsPalletMoving2 != null) {
                        DeliverSettingActivity.this.deleteTtsConfigDataIfNeed(ttsPalletMoving2);
                    }
                    DeliverSettingActivity.this.updateCurrentPalletTtsScheme(palletTtsScheme2);
                    DeliverSettingActivity.this.updateCurrentPalletTtsSchemeView();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPalletTtsArrivedDialog() {
        String str;
        TtsVoiceHelper.TtsConfigData ttsPalletArrived;
        CustomTtsVoiceInputDialog customTtsVoiceInputDialog = new CustomTtsVoiceInputDialog();
        PalletTtsScheme palletTtsScheme = this.currentPalletTtsScheme;
        if (palletTtsScheme == null || (ttsPalletArrived = palletTtsScheme.getTtsPalletArrived()) == null || (str = ttsPalletArrived.getName()) == null) {
            str = "";
        }
        customTtsVoiceInputDialog.setContent(str);
        customTtsVoiceInputDialog.setVoiceType(TtsVoiceHelper.TtsVoiceType.PALLET_DELIVER_TYPE);
        customTtsVoiceInputDialog.setMaxLength(200);
        customTtsVoiceInputDialog.show(getSupportFragmentManager(), "voice_custom");
        customTtsVoiceInputDialog.setOnContentChange(new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$showPalletTtsArrivedDialog$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str2) {
                invoke2(str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String newName) {
                PalletTtsScheme palletTtsScheme2;
                Object obj;
                Intrinsics.checkParameterIsNotNull(newName, "newName");
                palletTtsScheme2 = DeliverSettingActivity.this.currentPalletTtsScheme;
                if (palletTtsScheme2 != null) {
                    TtsVoiceHelper.TtsConfigData ttsPalletArrived2 = palletTtsScheme2.getTtsPalletArrived();
                    Iterator<T> it = TtsVoiceHelper.INSTANCE.getTtsConfigList(TtsVoiceHelper.TtsVoiceType.PALLET_DELIVER_TYPE).iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            obj = null;
                            break;
                        } else {
                            obj = it.next();
                            if (Intrinsics.areEqual(((TtsVoiceHelper.TtsConfigData) obj).getName(), newName)) {
                                break;
                            }
                        }
                    }
                    palletTtsScheme2.setTtsPalletArrived((TtsVoiceHelper.TtsConfigData) obj);
                    if (ttsPalletArrived2 != null) {
                        DeliverSettingActivity.this.deleteTtsConfigDataIfNeed(ttsPalletArrived2);
                    }
                    DeliverSettingActivity.this.updateCurrentPalletTtsScheme(palletTtsScheme2);
                    DeliverSettingActivity.this.updateCurrentPalletTtsSchemeView();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DeliverSettingActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/DeliverSettingActivity$SpacesItemDecoration;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "space", "", "(I)V", "getItemOffsets", "", "outRect", "Landroid/graphics/Rect;", "view", "Landroid/view/View;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private final int space;

        public SpacesItemDecoration(int i) {
            this.space = i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            Intrinsics.checkParameterIsNotNull(outRect, "outRect");
            Intrinsics.checkParameterIsNotNull(view, "view");
            Intrinsics.checkParameterIsNotNull(parent, "parent");
            Intrinsics.checkParameterIsNotNull(state, "state");
            super.getItemOffsets(outRect, view, parent, state);
            outRect.right = this.space;
        }
    }

    public final void showPalletTtsEditWindow(final View view, final int position) {
        final View inflate = LayoutInflater.from(this).inflate(C4188R.layout.pallet_tts_edit_popupview, (ViewGroup) null, false);
        this.popupWindowPalletTtsEdit = new PopupWindow();
        final PopupWindow popupWindow = this.popupWindowPalletTtsEdit;
        if (popupWindow != null) {
            popupWindow.setContentView(inflate);
            popupWindow.setWidth(-2);
            popupWindow.setHeight(-2);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(false);
            ((TextView) popupWindow.getContentView().findViewById(C4188R.id.tv_delete_scheme)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$showPalletTtsEditWindow$$inlined$apply$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(null, 0, 3, null);
                }

                @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
                public void onSingleClick() {
                    List list;
                    super.onSingleClick();
                    popupWindow.dismiss();
                    DeliverSettingActivity deliverSettingActivity = this;
                    list = deliverSettingActivity.palletTtsSchemeList;
                    deliverSettingActivity.deletePalletTtsScheme((PalletTtsScheme) list.get(position));
                }
            });
            popupWindow.showAsDropDown(view, 0, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showInputDialog() {
        final CustomInputDialog customInputDialog = new CustomInputDialog(this);
        if (Constans.INSTANCE.getCustomFinishBtnContent().length() > 0) {
            customInputDialog.setContent(Constans.INSTANCE.getCustomFinishBtnContent());
        }
        String string = getString(C4188R.string.custom_finished_btn_content);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.custom_finished_btn_content)");
        customInputDialog.setContentHint(string);
        String string2 = getString(C4188R.string.pdStr8_13);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.pdStr8_13)");
        customInputDialog.setBtnText(string2);
        customInputDialog.setInputSize(10);
        customInputDialog.setOnInputResult(new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverSettingActivity$showInputDialog$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                String str = it;
                if (str.length() == 0) {
                    TextView item_content_tv_2 = (TextView) DeliverSettingActivity.this._$_findCachedViewById(C4188R.id.item_content_tv_2);
                    Intrinsics.checkExpressionValueIsNotNull(item_content_tv_2, "item_content_tv_2");
                    item_content_tv_2.setText(DeliverSettingActivity.this.getString(C4188R.string.pdStr2_15));
                } else {
                    TextView item_content_tv_22 = (TextView) DeliverSettingActivity.this._$_findCachedViewById(C4188R.id.item_content_tv_2);
                    Intrinsics.checkExpressionValueIsNotNull(item_content_tv_22, "item_content_tv_2");
                    item_content_tv_22.setText(str);
                }
                Constans.INSTANCE.setCustomFinishBtnContent(it);
                customInputDialog.dismiss();
            }
        });
        customInputDialog.show();
    }
}
