package com.pudutech.bumblebee.robot_ui.p054ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseApplication;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.p052ui.CheckLocationActivity;
import com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.p052ui.LaserRunningLocationLostActivity;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ValidationDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CheckLocationHelper;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.resources.voice.VoiceItem;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.anko.internals.AnkoInternals;

/* compiled from: DropCheckActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010)\u001a\u00020*H\u0002J\b\u0010+\u001a\u00020*H\u0002J\u0012\u0010,\u001a\u00020*2\b\u0010-\u001a\u0004\u0018\u00010.H\u0014J\b\u0010/\u001a\u00020*H\u0014J\b\u00100\u001a\u00020*H\u0002J\b\u00101\u001a\u00020*H\u0002J\b\u00102\u001a\u00020*H\u0002J\b\u00103\u001a\u00020*H\u0002J\b\u00104\u001a\u00020*H\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001c\u0010#\u001a\u0004\u0018\u00010$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(¨\u00065"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/DropCheckActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "()V", "LOSE_WAY_INTERVAL", "", "getLOSE_WAY_INTERVAL", "()J", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "WHAT_ABNORMAL_PLAY", "", "getWHAT_ABNORMAL_PLAY", "()I", "isCheckGoto", "", "mHandler", "Landroid/os/Handler;", "getMHandler", "()Landroid/os/Handler;", "setMHandler", "(Landroid/os/Handler;)V", "mIsLoseWay", "getMIsLoseWay", "()Z", "setMIsLoseWay", "(Z)V", "mScopThread", "Lkotlinx/coroutines/CoroutineScope;", "getMScopThread", "()Lkotlinx/coroutines/CoroutineScope;", "setMScopThread", "(Lkotlinx/coroutines/CoroutineScope;)V", "mValidationDialog", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ValidationDialog;", "getMValidationDialog", "()Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ValidationDialog;", "setMValidationDialog", "(Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ValidationDialog;)V", "finishActivity", "", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "openDeliverEdit", "openLoseWay", "setData", "startPlay", "stopPlay", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class DropCheckActivity extends MyBaseActivity {
    private HashMap _$_findViewCache;
    private boolean isCheckGoto;
    private Handler mHandler;
    private boolean mIsLoseWay;
    private ValidationDialog mValidationDialog;
    private final String TAG = getClass().getSimpleName();
    private final int WHAT_ABNORMAL_PLAY = 96;
    private final long LOSE_WAY_INTERVAL = 40000;
    private CoroutineScope mScopThread = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[LocateCase.values().length];

        static {
            $EnumSwitchMapping$0[LocateCase.Marker.ordinal()] = 1;
            $EnumSwitchMapping$0[LocateCase.LaserMark.ordinal()] = 2;
            $EnumSwitchMapping$0[LocateCase.Laser.ordinal()] = 3;
            $EnumSwitchMapping$0[LocateCase.Slamware.ordinal()] = 4;
        }
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

    public DropCheckActivity() {
        final Looper mainLooper = Looper.getMainLooper();
        this.mHandler = new Handler(mainLooper) { // from class: com.pudutech.bumblebee.robot_ui.ui.DropCheckActivity$mHandler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Integer valueOf = msg != null ? Integer.valueOf(msg.what) : null;
                int what_abnormal_play = DropCheckActivity.this.getWHAT_ABNORMAL_PLAY();
                if (valueOf != null && valueOf.intValue() == what_abnormal_play) {
                    VoicePlayer.INSTANCE.play(new VoiceTask(-1L, VoiceItem.voice41_1));
                    sendEmptyMessageDelayed(DropCheckActivity.this.getWHAT_ABNORMAL_PLAY(), DropCheckActivity.this.getLOSE_WAY_INTERVAL());
                }
            }
        };
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final int getWHAT_ABNORMAL_PLAY() {
        return this.WHAT_ABNORMAL_PLAY;
    }

    public final long getLOSE_WAY_INTERVAL() {
        return this.LOSE_WAY_INTERVAL;
    }

    public final ValidationDialog getMValidationDialog() {
        return this.mValidationDialog;
    }

    public final void setMValidationDialog(ValidationDialog validationDialog) {
        this.mValidationDialog = validationDialog;
    }

    public final boolean getMIsLoseWay() {
        return this.mIsLoseWay;
    }

    public final void setMIsLoseWay(boolean z) {
        this.mIsLoseWay = z;
    }

    public final CoroutineScope getMScopThread() {
        return this.mScopThread;
    }

    public final void setMScopThread(CoroutineScope coroutineScope) {
        Intrinsics.checkParameterIsNotNull(coroutineScope, "<set-?>");
        this.mScopThread = coroutineScope;
    }

    public final Handler getMHandler() {
        return this.mHandler;
    }

    public final void setMHandler(Handler handler) {
        Intrinsics.checkParameterIsNotNull(handler, "<set-?>");
        this.mHandler = handler;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C4188R.layout.activity_drop_check);
        Pdlog.m3273d(this.TAG, "onCreate进入防跌落界面");
        initView();
        setData();
    }

    private final void setData() {
        startPlay();
    }

    private final void stopPlay() {
        if (this.mHandler.hasMessages(this.WHAT_ABNORMAL_PLAY)) {
            this.mHandler.removeMessages(this.WHAT_ABNORMAL_PLAY);
        }
        if (VoicePlayer.INSTANCE.isPlay()) {
            VoicePlayer.INSTANCE.stop();
        }
    }

    private final void startPlay() {
        PeripheralsSceneUtil.INSTANCE.showRunError();
        if (LanguageUtils.INSTANCE.isZh(BaseApplication.INSTANCE.getInstance())) {
            this.mHandler.sendEmptyMessage(this.WHAT_ABNORMAL_PLAY);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openDeliverEdit() {
        AnkoInternals.internalStartActivity(this, DeliverTaskEditActivity.class, new Pair[]{new Pair("MODE_TYPE", 0)});
        Pdlog.m3273d(this.TAG, "openDeliverEdit");
        finishActivity();
    }

    private final void finishActivity() {
        ValidationDialog validationDialog = this.mValidationDialog;
        if (validationDialog != null && validationDialog.isShowing()) {
            validationDialog.dismiss();
        }
        stopPlay();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openLoseWay() {
        Pair pair = new Pair(Constans.SKIP_KEY, 1);
        int i = WhenMappings.$EnumSwitchMapping$0[CheckLocationHelper.INSTANCE.getLocateCase().ordinal()];
        if (i == 1 || i == 2) {
            AnkoInternals.internalStartActivity(this, CheckLocationActivity.class, new Pair[]{pair});
        } else if (i == 3 || i == 4) {
            AnkoInternals.internalStartActivity(this, LaserRunningLocationLostActivity.class, new Pair[]{pair});
        } else {
            AnkoInternals.internalStartActivity(this, CheckLocationActivity.class, new Pair[]{pair});
        }
        Pdlog.m3274e(this.TAG, "openLoseWay " + CheckLocationHelper.INSTANCE.getLocateCase());
        finishActivity();
    }

    private final void initView() {
        if (this.mValidationDialog == null) {
            this.mValidationDialog = new ValidationDialog(this);
            ValidationDialog validationDialog = this.mValidationDialog;
            if (validationDialog != null) {
                validationDialog.setCheckResultClose(false);
            }
            ValidationDialog validationDialog2 = this.mValidationDialog;
            if (validationDialog2 != null) {
                validationDialog2.setMPasswordConfig(CollectionsKt.arrayListOf(Constans.INSTANCE.getSettingPassword(), "pudu666", "pudupw"));
            }
            ValidationDialog validationDialog3 = this.mValidationDialog;
            if (validationDialog3 != null) {
                validationDialog3.setOnPermissionCheckResult(new DropCheckActivity$initView$1(this));
            }
        }
        ((Button) _$_findCachedViewById(C4188R.id.drop_check_goto)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DropCheckActivity$initView$2
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
                boolean z;
                ValidationDialog mValidationDialog;
                boolean z2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                z = DropCheckActivity.this.isCheckGoto;
                if (z) {
                    String tag = DropCheckActivity.this.getTAG();
                    StringBuilder sb = new StringBuilder();
                    sb.append("drop_check_goto:已经点击了--isCheckGoto:");
                    z2 = DropCheckActivity.this.isCheckGoto;
                    sb.append(z2);
                    Pdlog.m3273d(tag, sb.toString());
                    return;
                }
                if (DropCheckActivity.this.getMValidationDialog() != null && (mValidationDialog = DropCheckActivity.this.getMValidationDialog()) != null && mValidationDialog.isShowing()) {
                    Pdlog.m3273d(DropCheckActivity.this.getTAG(), "drop_check_goto:弹窗已经出来了");
                    return;
                }
                ValidationDialog mValidationDialog2 = DropCheckActivity.this.getMValidationDialog();
                if (mValidationDialog2 != null) {
                    mValidationDialog2.show();
                }
            }
        }, 3, null));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        CoroutineScope coroutineScope = this.mScopThread;
        if (coroutineScope != null) {
            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
        }
        this.isCheckGoto = false;
    }
}
