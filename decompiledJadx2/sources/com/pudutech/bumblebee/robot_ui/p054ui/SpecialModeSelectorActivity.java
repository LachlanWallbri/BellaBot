package com.pudutech.bumblebee.robot_ui.p054ui;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.idle_move_task.IdleMoveContract;
import com.pudutech.bumblebee.presenter.idle_move_task.IdleMovePresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallTargetBean;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices_task.AppointVoice;
import com.pudutech.bumblebee.presenter.robot_voices_task.AppointVoiceContract;
import com.pudutech.bumblebee.presenter.robot_voices_task.AppointVoicePresenter;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.p054ui.SpecialModeSelectorActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.SelectMusicItem;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.SelectVoiceItem;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.SpecialModeMusicAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.SpecialModeVoiceAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.VoiceType;
import com.pudutech.bumblebee.robot_ui.p054ui.custom_call.CustomCallActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.FaceAnimationDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.HomeSettingDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.BeeperCallHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CustomCallHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TouchSensorEventHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoAnimation;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.SceneAnimationResources;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.util.DensityUtil;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.bumblebee.robot_ui.util.PlaySound;
import com.pudutech.bumblebee.robot_ui.util.UiUtils;
import com.pudutech.location.view.SignalView;
import com.pudutech.mpmodule.MusicPlayerHelper;
import com.pudutech.mpmodule.bean.ModeEnum;
import com.pudutech.mpmodule.bean.Playlist;
import com.pudutech.mpmodule.database.interf.QueryPlayListCallBacker;
import com.pudutech.mpmodule.media.Media;
import com.pudutech.resources.voice.VoiceItem;
import java.util.ArrayList;
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
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;

/* compiled from: SpecialModeSelectorActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0002%(\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010.\u001a\u00020#H\u0002J\b\u0010/\u001a\u00020\u0006H\u0002J\u0010\u00100\u001a\u00020\u00062\u0006\u00101\u001a\u000202H\u0002J\u0010\u00103\u001a\u00020\u00062\u0006\u00104\u001a\u000205H\u0002J\b\u00106\u001a\u00020#H\u0002J\b\u00107\u001a\u00020#H\u0002J\b\u00108\u001a\u00020#H\u0002J\b\u00109\u001a\u00020#H\u0002J\b\u0010:\u001a\u00020#H\u0002J\b\u0010;\u001a\u00020\u001cH\u0016J\u0010\u0010<\u001a\u00020#2\u0006\u0010=\u001a\u00020>H\u0016J\b\u0010?\u001a\u00020#H\u0002J\u0012\u0010@\u001a\u00020#2\b\u0010A\u001a\u0004\u0018\u00010BH\u0014J\b\u0010C\u001a\u00020#H\u0014J\b\u0010D\u001a\u00020#H\u0014J\u0010\u0010E\u001a\u00020#2\u0006\u0010F\u001a\u00020\u001cH\u0016J\b\u0010G\u001a\u00020#H\u0002J\u0010\u0010H\u001a\u00020#2\u0006\u0010I\u001a\u00020\u0006H\u0002J\u001c\u0010J\u001a\u00020#2\u0006\u0010K\u001a\u00020\u00062\n\b\u0002\u0010L\u001a\u0004\u0018\u00010MH\u0002J\u0010\u0010N\u001a\u00020#2\u0006\u0010O\u001a\u00020PH\u0002J\u0010\u0010Q\u001a\u00020#2\u0006\u0010R\u001a\u00020SH\u0016J \u0010T\u001a\u00020#2\u0016\u0010U\u001a\u0012\u0012\u0004\u0012\u0002050Vj\b\u0012\u0004\u0012\u000205`WH\u0016J\b\u0010X\u001a\u00020#H\u0002J\b\u0010Y\u001a\u00020#H\u0002J\b\u0010Z\u001a\u00020#H\u0002J\b\u0010[\u001a\u00020#H\u0002J\b\u0010\\\u001a\u00020#H\u0002R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\r\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#0!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0004\n\u0002\u0010&R\u0010\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0004\n\u0002\u0010)R\u000e\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082.¢\u0006\u0002\n\u0000¨\u0006]"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/SpecialModeSelectorActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/AppointVoiceContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMoveContract$ViewInterface;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "appointVoicePresenter", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/AppointVoicePresenter;", "getAppointVoicePresenter", "()Lcom/pudutech/bumblebee/presenter/robot_voices_task/AppointVoicePresenter;", "appointVoicePresenter$delegate", "Lkotlin/Lazy;", "beeperCallHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/BeeperCallHelper;", "faceAnimationDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/FaceAnimationDialog;", "faceAnimationView", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoView;", "homeSettingDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/HomeSettingDialog;", "idleMovePresenter", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter;", "getIdleMovePresenter", "()Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter;", "idleMovePresenter$delegate", "isFirstStart", "", "isJumpToMusicAc", "musicAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SpecialModeMusicAdapter;", "onCustomCallListener", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallTargetBean;", "", "onHomeSettingDialogClickListener", "com/pudutech/bumblebee/robot_ui/ui/SpecialModeSelectorActivity$onHomeSettingDialogClickListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/SpecialModeSelectorActivity$onHomeSettingDialogClickListener$1;", "singleBatteryListener", "com/pudutech/bumblebee/robot_ui/ui/SpecialModeSelectorActivity$singleBatteryListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/SpecialModeSelectorActivity$singleBatteryListener$1;", "touchSensorEventHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TouchSensorEventHelper;", "voiceAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SpecialModeVoiceAdapter;", "bindPresenter", "getSaveVoiceSelect", "getTTsVoiceSaveString", "ttsConfigData", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;", "getVoiceSaveString", "appointVoice", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/AppointVoice;", "hideFaceDialog", "initMusicData", "initMusicView", "initView", "initVoiceView", "isBusyState", "jumpAndFinish", "intent", "Landroid/content/Intent;", "onConfigChange", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onStart", "onWindowFocusChanged", "hasFocus", "release", "saveSelectMusic", "path", "saveVoiceSelect", "s", "voiceType", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/VoiceType;", "showFaceDialog", "animations", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoAnimation;", "showIdleEvent", "event", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMoveContract$ViewEvent;", "showList", "arrayList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "showSettingDialog", "showSleepAnimation", "showStandbyAnimation", "stopStandby", "unBindPresenter", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SpecialModeSelectorActivity extends MyBaseActivity implements AppointVoiceContract.ViewInterface, IdleMoveContract.ViewInterface {
    private HashMap _$_findViewCache;
    private FaceAnimationDialog faceAnimationDialog;
    private FaceVideoView faceAnimationView;
    private HomeSettingDialog homeSettingDialog;
    private boolean isJumpToMusicAc;
    private SpecialModeMusicAdapter musicAdapter;
    private SpecialModeVoiceAdapter voiceAdapter;
    private final String TAG = getClass().getSimpleName();

    /* renamed from: appointVoicePresenter$delegate, reason: from kotlin metadata */
    private final Lazy appointVoicePresenter = LazyKt.lazy(new Function0<AppointVoicePresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.SpecialModeSelectorActivity$appointVoicePresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final AppointVoicePresenter invoke() {
            AppointVoicePresenter appointVoicePresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(AppointVoicePresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(AppointVoicePresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                appointVoicePresenter = new AppointVoicePresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(AppointVoicePresenter.class).toString(), appointVoicePresenter);
            } else {
                if (!(basePresenterInterface instanceof AppointVoicePresenter)) {
                    basePresenterInterface = null;
                }
                appointVoicePresenter = (AppointVoicePresenter) basePresenterInterface;
            }
            if (appointVoicePresenter != null) {
                return appointVoicePresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.robot_voices_task.AppointVoicePresenter");
        }
    });

    /* renamed from: idleMovePresenter$delegate, reason: from kotlin metadata */
    private final Lazy idleMovePresenter = LazyKt.lazy(new Function0<IdleMovePresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.SpecialModeSelectorActivity$idleMovePresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final IdleMovePresenter invoke() {
            IdleMovePresenter idleMovePresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(IdleMovePresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "popOrCreateIt " + Reflection.getOrCreateKotlinClass(IdleMovePresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                idleMovePresenter = new IdleMovePresenter();
            } else {
                presenterHolder.getBox().remove(Reflection.getOrCreateKotlinClass(IdleMovePresenter.class).toString());
                if (!(basePresenterInterface instanceof IdleMovePresenter)) {
                    basePresenterInterface = null;
                }
                idleMovePresenter = (IdleMovePresenter) basePresenterInterface;
            }
            if (idleMovePresenter != null) {
                return idleMovePresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.idle_move_task.IdleMovePresenter");
        }
    });
    private boolean isFirstStart = true;
    private final BeeperCallHelper beeperCallHelper = new BeeperCallHelper();
    private final SpecialModeSelectorActivity$onHomeSettingDialogClickListener$1 onHomeSettingDialogClickListener = new SpecialModeSelectorActivity$onHomeSettingDialogClickListener$1(this);
    private Function1<? super CustomCallTargetBean, Unit> onCustomCallListener = new Function1<CustomCallTargetBean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.SpecialModeSelectorActivity$onCustomCallListener$1
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
            str = SpecialModeSelectorActivity.this.TAG;
            Pdlog.m3273d(str, "onCustomCallListener task: " + it);
            SpecialModeSelectorActivity.this.jumpAndFinish(CustomCallActivity.Companion.createIntent(SpecialModeSelectorActivity.this, it));
        }
    };
    private final TouchSensorEventHelper touchSensorEventHelper = new TouchSensorEventHelper();
    private final SpecialModeSelectorActivity$singleBatteryListener$1 singleBatteryListener = new SpecialModeSelectorActivity$singleBatteryListener$1(this);

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[VoiceType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[VoiceType.APPOINT_VOICE.ordinal()] = 1;
            $EnumSwitchMapping$0[VoiceType.TTS_VOICE.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[IdleMoveContract.ViewEvent.values().length];
            $EnumSwitchMapping$1[IdleMoveContract.ViewEvent.READY.ordinal()] = 1;
            $EnumSwitchMapping$1[IdleMoveContract.ViewEvent.STAND_BY.ordinal()] = 2;
            $EnumSwitchMapping$1[IdleMoveContract.ViewEvent.SLEEP.ordinal()] = 3;
        }
    }

    private final AppointVoicePresenter getAppointVoicePresenter() {
        return (AppointVoicePresenter) this.appointVoicePresenter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final IdleMovePresenter getIdleMovePresenter() {
        return (IdleMovePresenter) this.idleMovePresenter.getValue();
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

    public static final /* synthetic */ SpecialModeMusicAdapter access$getMusicAdapter$p(SpecialModeSelectorActivity specialModeSelectorActivity) {
        SpecialModeMusicAdapter specialModeMusicAdapter = specialModeSelectorActivity.musicAdapter;
        if (specialModeMusicAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicAdapter");
        }
        return specialModeMusicAdapter;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C4188R.layout.activity_special_mode_selector);
        initView();
        bindPresenter();
        SignalView signal_view = (SignalView) _$_findCachedViewById(C4188R.id.signal_view);
        Intrinsics.checkExpressionValueIsNotNull(signal_view, "signal_view");
        setBindSignal(signal_view);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        Pdlog.m3273d(this.TAG, "onStart ");
        if (this.isJumpToMusicAc) {
            this.isJumpToMusicAc = false;
            bindPresenter();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        initMusicData();
    }

    private final void initView() {
        initMusicView();
        initVoiceView();
        FaceVideoView face_animation_view = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view, "face_animation_view");
        this.faceAnimationView = face_animation_view;
        ((TextView) _$_findCachedViewById(C4188R.id.btnSure)).setOnClickListener(new OnLazyClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.SpecialModeSelectorActivity$initView$1
            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener
            public void onSingleClick() {
                String str;
                str = SpecialModeSelectorActivity.this.TAG;
                Pdlog.m3273d(str, "btn_sure onSingleClick");
                Intent intent = new Intent(SpecialModeSelectorActivity.this, (Class<?>) DeliverTaskEditActivity.class);
                intent.putExtra("MODE_TYPE", 8);
                PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
                SpecialModeSelectorActivity.this.jumpAndFinish(intent);
            }
        });
        ((ImageView) _$_findCachedViewById(C4188R.id.menu_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.SpecialModeSelectorActivity$initView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                SpecialModeSelectorActivity.this.showSettingDialog();
            }
        });
    }

    private final void initVoiceView() {
        RecyclerView arrive_voice_recycle_view = (RecyclerView) _$_findCachedViewById(C4188R.id.arrive_voice_recycle_view);
        Intrinsics.checkExpressionValueIsNotNull(arrive_voice_recycle_view, "arrive_voice_recycle_view");
        SpecialModeSelectorActivity specialModeSelectorActivity = this;
        arrive_voice_recycle_view.setLayoutManager(new LinearLayoutManager(specialModeSelectorActivity));
        this.voiceAdapter = new SpecialModeVoiceAdapter(specialModeSelectorActivity);
        RecyclerView arrive_voice_recycle_view2 = (RecyclerView) _$_findCachedViewById(C4188R.id.arrive_voice_recycle_view);
        Intrinsics.checkExpressionValueIsNotNull(arrive_voice_recycle_view2, "arrive_voice_recycle_view");
        SpecialModeVoiceAdapter specialModeVoiceAdapter = this.voiceAdapter;
        if (specialModeVoiceAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("voiceAdapter");
        }
        arrive_voice_recycle_view2.setAdapter(specialModeVoiceAdapter);
        SpecialModeVoiceAdapter specialModeVoiceAdapter2 = this.voiceAdapter;
        if (specialModeVoiceAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("voiceAdapter");
        }
        specialModeVoiceAdapter2.setOnItemClickListener(new OnLazyItemClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.SpecialModeSelectorActivity$initVoiceView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener
            public void onSingleItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                String voiceSaveString;
                String tTsVoiceSaveString;
                Intrinsics.checkParameterIsNotNull(adapter, "adapter");
                Intrinsics.checkParameterIsNotNull(view, "view");
                List<?> data = adapter.getData();
                Intrinsics.checkExpressionValueIsNotNull(data, "adapter.data");
                int i = 0;
                for (Object obj : data) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    if (obj != null) {
                        SelectVoiceItem selectVoiceItem = (SelectVoiceItem) obj;
                        if (position == i) {
                            selectVoiceItem.setSelect(!selectVoiceItem.isSelect());
                            if (selectVoiceItem.isSelect()) {
                                int i3 = SpecialModeSelectorActivity.WhenMappings.$EnumSwitchMapping$0[selectVoiceItem.getVoiceType().ordinal()];
                                if (i3 == 1) {
                                    SpecialModeSelectorActivity specialModeSelectorActivity2 = SpecialModeSelectorActivity.this;
                                    AppointVoice voice = selectVoiceItem.getVoice();
                                    if (voice == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    voiceSaveString = specialModeSelectorActivity2.getVoiceSaveString(voice);
                                    specialModeSelectorActivity2.saveVoiceSelect(voiceSaveString, selectVoiceItem.getVoiceType());
                                } else if (i3 == 2) {
                                    SpecialModeSelectorActivity specialModeSelectorActivity3 = SpecialModeSelectorActivity.this;
                                    TtsVoiceHelper.TtsConfigData ttsVoice = selectVoiceItem.getTtsVoice();
                                    if (ttsVoice == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    tTsVoiceSaveString = specialModeSelectorActivity3.getTTsVoiceSaveString(ttsVoice);
                                    specialModeSelectorActivity3.saveVoiceSelect(tTsVoiceSaveString, selectVoiceItem.getVoiceType());
                                }
                            } else {
                                SpecialModeSelectorActivity.saveVoiceSelect$default(SpecialModeSelectorActivity.this, "", null, 2, null);
                            }
                        } else {
                            selectVoiceItem.setSelect(false);
                        }
                        i = i2;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.adapter.SelectVoiceItem");
                    }
                }
                adapter.notifyDataSetChanged();
                SpecialModeSelectorActivity.this.onConfigChange();
            }
        });
    }

    private final void initMusicData() {
        RecyclerView music_recycle_view = (RecyclerView) _$_findCachedViewById(C4188R.id.music_recycle_view);
        Intrinsics.checkExpressionValueIsNotNull(music_recycle_view, "music_recycle_view");
        music_recycle_view.setVisibility(8);
        RelativeLayout empty_layout = (RelativeLayout) _$_findCachedViewById(C4188R.id.empty_layout);
        Intrinsics.checkExpressionValueIsNotNull(empty_layout, "empty_layout");
        empty_layout.setVisibility(8);
        ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) _$_findCachedViewById(C4188R.id.progressBar);
        if (contentLoadingProgressBar != null) {
            contentLoadingProgressBar.setVisibility(0);
        }
        MusicPlayerHelper.getInstance().getMusicListByMode(this, ModeEnum.SPECIAL, new QueryPlayListCallBacker() { // from class: com.pudutech.bumblebee.robot_ui.ui.SpecialModeSelectorActivity$initMusicData$1
            @Override // com.pudutech.mpmodule.database.interf.QueryPlayListCallBacker, com.pudutech.mpmodule.database.interf.IDatabaseInterface.DatabaseCallBack
            public void onGetPlaylist(Playlist playlist) {
                String str;
                String str2;
                String str3;
                List<Media> mediaList;
                List<Media> mediaList2;
                str = SpecialModeSelectorActivity.this.TAG;
                Pdlog.m3273d(str, "operateOnInitDefaultLists init " + playlist);
                if (SpecialModeSelectorActivity.this.isFinishing() || SpecialModeSelectorActivity.this.isDestroyed()) {
                    str2 = SpecialModeSelectorActivity.this.TAG;
                    Pdlog.m3274e(str2, "onLoadSuccessful ac is finish");
                    return;
                }
                ContentLoadingProgressBar contentLoadingProgressBar2 = (ContentLoadingProgressBar) SpecialModeSelectorActivity.this._$_findCachedViewById(C4188R.id.progressBar);
                if (contentLoadingProgressBar2 != null) {
                    contentLoadingProgressBar2.setVisibility(8);
                }
                if (((playlist == null || (mediaList2 = playlist.getMediaList()) == null) ? 0 : mediaList2.size()) == 0) {
                    RelativeLayout empty_layout2 = (RelativeLayout) SpecialModeSelectorActivity.this._$_findCachedViewById(C4188R.id.empty_layout);
                    Intrinsics.checkExpressionValueIsNotNull(empty_layout2, "empty_layout");
                    empty_layout2.setVisibility(0);
                    SpecialModeSelectorActivity.this.saveSelectMusic("");
                    return;
                }
                ArrayList arrayList = new ArrayList();
                String str4 = SpUtils.get(RobotContext.INSTANCE.getContext(), "key_spaces_mode_select_music", "");
                str3 = SpecialModeSelectorActivity.this.TAG;
                Pdlog.m3273d(str3, "operateOnInitDefaultLists selectPath = " + str4);
                if (playlist != null && (mediaList = playlist.getMediaList()) != null) {
                    for (Media it : mediaList) {
                        Intrinsics.checkExpressionValueIsNotNull(it, "it");
                        String str5 = str4;
                        arrayList.add(new SelectMusicItem(it, !(str5 == null || StringsKt.isBlank(str5)) && Intrinsics.areEqual(str4, it.getPath()), false));
                    }
                }
                RecyclerView music_recycle_view2 = (RecyclerView) SpecialModeSelectorActivity.this._$_findCachedViewById(C4188R.id.music_recycle_view);
                Intrinsics.checkExpressionValueIsNotNull(music_recycle_view2, "music_recycle_view");
                music_recycle_view2.setVisibility(0);
                SpecialModeSelectorActivity.access$getMusicAdapter$p(SpecialModeSelectorActivity.this).setNewData(arrayList);
                SpecialModeSelectorActivity.this.onConfigChange();
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices_task.AppointVoiceContract.ViewInterface
    public void showList(ArrayList<AppointVoice> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "arrayList");
        ArrayList arrayList2 = new ArrayList();
        String saveVoiceSelect = getSaveVoiceSelect();
        boolean z = false;
        Pdlog.m3273d(this.TAG, "showList voice =  " + arrayList + " , save = " + saveVoiceSelect);
        for (AppointVoice appointVoice : arrayList) {
            if (!z && !StringsKt.isBlank(saveVoiceSelect) && Intrinsics.areEqual(saveVoiceSelect, String.valueOf(appointVoice.getIndex()))) {
                arrayList2.add(new SelectVoiceItem(VoiceType.APPOINT_VOICE, appointVoice, null, true, false, null, 48, null));
                z = true;
            } else {
                arrayList2.add(new SelectVoiceItem(VoiceType.APPOINT_VOICE, appointVoice, null, false, false, null, 48, null));
            }
        }
        if (TtsVoiceHelper.INSTANCE.checkTtsOpenType(RobotContext.INSTANCE.getContext(), TtsVoiceHelper.TtsVoiceType.SPECIAL_MODE_ARRIVE) == TtsVoiceHelper.TtsVoiceOpenType.OPEN) {
            for (TtsVoiceHelper.TtsConfigData ttsConfigData : TtsVoiceHelper.INSTANCE.getTtsConfigList(TtsVoiceHelper.TtsVoiceType.SPECIAL_MODE_ARRIVE)) {
                if (ttsConfigData.isSelect()) {
                    if (!z && !StringsKt.isBlank(saveVoiceSelect) && Intrinsics.areEqual(saveVoiceSelect, ttsConfigData.getName())) {
                        arrayList2.add(new SelectVoiceItem(VoiceType.TTS_VOICE, null, ttsConfigData, true, false, null, 48, null));
                        z = true;
                    } else {
                        arrayList2.add(new SelectVoiceItem(VoiceType.TTS_VOICE, null, ttsConfigData, false, false, null, 48, null));
                    }
                }
            }
        }
        if (!z) {
            saveVoiceSelect$default(this, "", null, 2, null);
        }
        if (!arrayList2.isEmpty()) {
            TextView voice_empty_title_tv = (TextView) _$_findCachedViewById(C4188R.id.voice_empty_title_tv);
            Intrinsics.checkExpressionValueIsNotNull(voice_empty_title_tv, "voice_empty_title_tv");
            voice_empty_title_tv.setVisibility(8);
        }
        SpecialModeVoiceAdapter specialModeVoiceAdapter = this.voiceAdapter;
        if (specialModeVoiceAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("voiceAdapter");
        }
        specialModeVoiceAdapter.setNewData(arrayList2);
        onConfigChange();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getVoiceSaveString(AppointVoice appointVoice) {
        return String.valueOf(appointVoice.getIndex());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getTTsVoiceSaveString(TtsVoiceHelper.TtsConfigData ttsConfigData) {
        return ttsConfigData.getName();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void saveVoiceSelect$default(SpecialModeSelectorActivity specialModeSelectorActivity, String str, VoiceType voiceType, int i, Object obj) {
        if ((i & 2) != 0) {
            voiceType = (VoiceType) null;
        }
        specialModeSelectorActivity.saveVoiceSelect(str, voiceType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveVoiceSelect(String s, VoiceType voiceType) {
        Pdlog.m3273d(this.TAG, "saveVoiceSelect " + s);
        SpecialModeSelectorActivity specialModeSelectorActivity = this;
        SpUtils.set(specialModeSelectorActivity, "key_spaces_mode_select_arrive_voice", s);
        SpUtils.set(specialModeSelectorActivity, Constans.KEY_SPACES_MODE_SELECT_ARRIVE_VOICE_TYPE, String.valueOf(voiceType));
    }

    private final String getSaveVoiceSelect() {
        return SpUtils.get(this, "key_spaces_mode_select_arrive_voice", "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onConfigChange() {
        SpecialModeMusicAdapter specialModeMusicAdapter = this.musicAdapter;
        if (specialModeMusicAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicAdapter");
        }
        List<SelectMusicItem> data = specialModeMusicAdapter.getData();
        Intrinsics.checkExpressionValueIsNotNull(data, "musicAdapter.data");
        Iterator<T> it = data.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (((SelectMusicItem) it.next()).isSelect()) {
                z = true;
            }
        }
        SpecialModeVoiceAdapter specialModeVoiceAdapter = this.voiceAdapter;
        if (specialModeVoiceAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("voiceAdapter");
        }
        List<SelectVoiceItem> data2 = specialModeVoiceAdapter.getData();
        Intrinsics.checkExpressionValueIsNotNull(data2, "voiceAdapter.data");
        Iterator<T> it2 = data2.iterator();
        while (it2.hasNext()) {
            if (((SelectVoiceItem) it2.next()).isSelect()) {
                z = true;
            }
        }
        TextView btnSure = (TextView) _$_findCachedViewById(C4188R.id.btnSure);
        Intrinsics.checkExpressionValueIsNotNull(btnSure, "btnSure");
        btnSure.setVisibility(z ? 0 : 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveSelectMusic(String path) {
        Pdlog.m3273d(this.TAG, "saveSelectMusic " + path);
        SpUtils.set(RobotContext.INSTANCE.getContext(), "key_spaces_mode_select_music", path);
    }

    private final void initMusicView() {
        Pdlog.m3273d(this.TAG, "initMusicView");
        SpecialModeSelectorActivity specialModeSelectorActivity = this;
        this.musicAdapter = new SpecialModeMusicAdapter(specialModeSelectorActivity);
        ContentLoadingProgressBar progressBar = (ContentLoadingProgressBar) _$_findCachedViewById(C4188R.id.progressBar);
        Intrinsics.checkExpressionValueIsNotNull(progressBar, "progressBar");
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(specialModeSelectorActivity, C4188R.color.theme_main_color), PorterDuff.Mode.MULTIPLY);
        RecyclerView music_recycle_view = (RecyclerView) _$_findCachedViewById(C4188R.id.music_recycle_view);
        Intrinsics.checkExpressionValueIsNotNull(music_recycle_view, "music_recycle_view");
        music_recycle_view.setLayoutManager(new LinearLayoutManager(specialModeSelectorActivity));
        RecyclerView music_recycle_view2 = (RecyclerView) _$_findCachedViewById(C4188R.id.music_recycle_view);
        Intrinsics.checkExpressionValueIsNotNull(music_recycle_view2, "music_recycle_view");
        SpecialModeMusicAdapter specialModeMusicAdapter = this.musicAdapter;
        if (specialModeMusicAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicAdapter");
        }
        music_recycle_view2.setAdapter(specialModeMusicAdapter);
        SpecialModeMusicAdapter specialModeMusicAdapter2 = this.musicAdapter;
        if (specialModeMusicAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicAdapter");
        }
        specialModeMusicAdapter2.setOnItemClickListener(new OnLazyItemClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.SpecialModeSelectorActivity$initMusicView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener
            public void onSingleItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                String str;
                Intrinsics.checkParameterIsNotNull(adapter, "adapter");
                Intrinsics.checkParameterIsNotNull(view, "view");
                str = SpecialModeSelectorActivity.this.TAG;
                Pdlog.m3273d(str, "setOnItemChildClickListener " + position);
                List<?> data = adapter.getData();
                Intrinsics.checkExpressionValueIsNotNull(data, "adapter.data");
                int i = 0;
                for (Object obj : data) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    if (obj != null) {
                        SelectMusicItem selectMusicItem = (SelectMusicItem) obj;
                        if (position == i) {
                            selectMusicItem.setSelect(!selectMusicItem.isSelect());
                            if (!selectMusicItem.isSelect()) {
                                SpecialModeSelectorActivity.this.saveSelectMusic("");
                            } else {
                                SpecialModeSelectorActivity specialModeSelectorActivity2 = SpecialModeSelectorActivity.this;
                                String path = selectMusicItem.getMedia().getPath();
                                Intrinsics.checkExpressionValueIsNotNull(path, "item.media.path");
                                specialModeSelectorActivity2.saveSelectMusic(path);
                            }
                        } else {
                            selectMusicItem.setSelect(false);
                        }
                        i = i2;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.adapter.SelectMusicItem");
                    }
                }
                adapter.notifyDataSetChanged();
                SpecialModeSelectorActivity.this.onConfigChange();
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.add_music_tv)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.SpecialModeSelectorActivity$initMusicView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener, android.view.View.OnClickListener
            public void onClick(View v) {
                Intrinsics.checkParameterIsNotNull(v, "v");
                super.onClick(v);
                MusicPlayerHelper.getInstance().gotoAddMusicForMode(SpecialModeSelectorActivity.this, ModeEnum.SPECIAL);
                SpecialModeSelectorActivity.this.isJumpToMusicAc = true;
                SpecialModeSelectorActivity.this.unBindPresenter();
            }
        });
        TextView textView = (TextView) _$_findCachedViewById(C4188R.id.add_music_tv);
        int dp2px = DensityUtil.dp2px(specialModeSelectorActivity, 286.0f);
        TextView add_music_tv = (TextView) _$_findCachedViewById(C4188R.id.add_music_tv);
        Intrinsics.checkExpressionValueIsNotNull(add_music_tv, "add_music_tv");
        UiUtils.adjustTvTextSize(textView, dp2px, add_music_tv.getText().toString());
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, com.pudutech.bumblebee.robot_ui.p054ui.FinishInter
    public void jumpAndFinish(Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        Pdlog.m3273d(this.TAG, "jumpAndFinish");
        release();
        startActivity(intent);
        finish();
    }

    private final void bindPresenter() {
        Pdlog.m3273d(this.TAG, "bindPresenter");
        TouchSensorEventHelper touchSensorEventHelper = this.touchSensorEventHelper;
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        touchSensorEventHelper.bindPresenter(faceVideoView);
        SpecialModeSelectorActivity specialModeSelectorActivity = this;
        getAppointVoicePresenter().replaceView(specialModeSelectorActivity);
        getAppointVoicePresenter().syncList(this, VoiceItem.voice10_3);
        this.singleBatteryListener.showPowerChange(BatteryInfoManager.INSTANCE.getPower());
        this.singleBatteryListener.showChargerEvent(BatteryInfoManager.INSTANCE.getChargerEvent());
        BatteryInfoManager.INSTANCE.addBatteryChangeNotifyListeners(this.singleBatteryListener);
        getIdleMovePresenter().replaceView(specialModeSelectorActivity);
        getIdleMovePresenter().actionTimerCount(true);
        BeeperCallHelper.bind$default(this.beeperCallHelper, this, false, false, 6, null);
        CustomCallHelper.INSTANCE.addCallListener(this.onCustomCallListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void release() {
        MusicPlayerHelper.getInstance().release();
        unBindPresenter();
        VoicePlayer.INSTANCE.stop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void unBindPresenter() {
        Pdlog.m3273d(this.TAG, "unBindPresenter");
        this.beeperCallHelper.unbind();
        BatteryInfoManager.INSTANCE.removeBatteryChangeNotifyListeners(this.singleBatteryListener);
        SpecialModeSelectorActivity specialModeSelectorActivity = this;
        getAppointVoicePresenter().removeView(specialModeSelectorActivity);
        getIdleMovePresenter().actionTimerCount(false);
        getIdleMovePresenter().removeView(specialModeSelectorActivity);
        CustomCallHelper.INSTANCE.removeCallListener(this.onCustomCallListener);
        this.touchSensorEventHelper.unBindPresent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showSettingDialog() {
        Pdlog.m3273d(this.TAG, "showSettingDialog");
        if (this.homeSettingDialog == null) {
            this.homeSettingDialog = new HomeSettingDialog(this);
            HomeSettingDialog homeSettingDialog = this.homeSettingDialog;
            if (homeSettingDialog == null) {
                Intrinsics.throwNpe();
            }
            homeSettingDialog.setOnHomeSettingDialogClickListener(this.onHomeSettingDialogClickListener);
        }
        HomeSettingDialog homeSettingDialog2 = this.homeSettingDialog;
        if (homeSettingDialog2 == null) {
            Intrinsics.throwNpe();
        }
        homeSettingDialog2.show();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Pdlog.m3273d(this.TAG, "onWindowFocusChanged " + hasFocus);
        if (hasFocus && this.isFirstStart) {
            this.isFirstStart = false;
        }
    }

    @Override // com.pudutech.bumblebee.presenter.idle_move_task.IdleMoveContract.ViewInterface
    public void showIdleEvent(IdleMoveContract.ViewEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        int i = WhenMappings.$EnumSwitchMapping$1[event.ordinal()];
        if (i == 1) {
            stopStandby();
        } else if (i == 2) {
            showStandbyAnimation();
        } else {
            if (i != 3) {
                return;
            }
            showSleepAnimation();
        }
    }

    private final void stopStandby() {
        TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
        Pdlog.m3273d(this.TAG, "stopStandby");
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        faceVideoView.stopPlay();
        PeripheralsSceneUtil.idleState$default(PeripheralsSceneUtil.INSTANCE, false, 1, null);
        resetScreenLight();
    }

    private final void showStandbyAnimation() {
        Pdlog.m3273d(this.TAG, "showStandbyAnimation");
        TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        faceVideoView.playAnimation(SceneAnimationResources.INSTANCE.getStandby());
        PeripheralsSceneUtil.INSTANCE.standby();
        setScreenDark();
    }

    private final void showSleepAnimation() {
        Pdlog.m3273d(this.TAG, "showSleepAnimation");
        TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        faceVideoView.playAnimation(SceneAnimationResources.INSTANCE.getSleep());
        PeripheralsSceneUtil.INSTANCE.sleep();
        setScreenDark();
    }

    private final void showFaceDialog(FaceVideoAnimation animations) {
        Pdlog.m3273d(this.TAG, "showFaceDialog ");
        if (this.faceAnimationDialog == null) {
            this.faceAnimationDialog = new FaceAnimationDialog();
        }
        FaceAnimationDialog faceAnimationDialog = this.faceAnimationDialog;
        if (faceAnimationDialog == null) {
            Intrinsics.throwNpe();
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "supportFragmentManager");
        faceAnimationDialog.show(supportFragmentManager, "face_animation_dialog");
        FaceAnimationDialog faceAnimationDialog2 = this.faceAnimationDialog;
        if (faceAnimationDialog2 == null) {
            Intrinsics.throwNpe();
        }
        faceAnimationDialog2.playAnimation(animations);
        FaceAnimationDialog faceAnimationDialog3 = this.faceAnimationDialog;
        if (faceAnimationDialog3 == null) {
            Intrinsics.throwNpe();
        }
        faceAnimationDialog3.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.SpecialModeSelectorActivity$showFaceDialog$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                IdleMovePresenter idleMovePresenter;
                str = SpecialModeSelectorActivity.this.TAG;
                Pdlog.m3273d(str, "faceAnimationDialog OnClick ");
                idleMovePresenter = SpecialModeSelectorActivity.this.getIdleMovePresenter();
                idleMovePresenter.actionTimerCount(true);
            }
        });
    }

    private final void hideFaceDialog() {
        Pdlog.m3273d(this.TAG, "hideFaceDialog ");
        try {
            FaceAnimationDialog faceAnimationDialog = this.faceAnimationDialog;
            if (faceAnimationDialog != null) {
                faceAnimationDialog.dismissAllowingStateLoss();
            }
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "hideFaceDialog : " + Log.getStackTraceString(e));
        }
    }
}
