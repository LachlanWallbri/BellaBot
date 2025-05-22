package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.robotsdk.RobotSetting;
import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.robot_open_task.BeeperBindContract;
import com.pudutech.bumblebee.presenter.robot_open_task.BeeperBindPresenter;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.AdvancedSettingsFragment;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ShowTipMsgDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.GridSpacingItemDecoration;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.radiobtntv.IRadioBtnTvBean;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.radiobtntv.RadioBtnTvAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.RecycleViewDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.TransparentLoadDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.FallDropHelper;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListenerKt;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceIndicatorSeekBarChangeListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceSwitchChangeListener;
import com.pudutech.bumblebee.robot_ui.util.BeeperUtils;
import com.pudutech.bumblebee.robot_ui.util.DensityUtil;
import com.pudutech.bumblebee.robot_ui.util.FileUtil;
import com.pudutech.bumblebee.robot_ui.util.RobotSpeedUtil;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import com.pudutech.bumblebee.robot_ui.viewmodel.advancedsetting.AdvancedSettingVM;
import com.pudutech.bumblebee.robot_ui.viewmodel.advancedsetting.ServerInfoBean;
import com.pudutech.importmusic.QRCodeUtils;
import com.pudutech.mirsdk.mircore.coreparcel.SmoothMode;
import com.pudutech.mirsdkwrap.lib.robot.RobotConfig;
import com.pudutech.robot.opensdk.aliyun.bean.BindCodeData;
import com.pudutech.robot.peripherals.BuildConfig;
import com.warkiz.widget.IndicatorSeekBar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.math.MathKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: AdvancedSettingsFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000ä\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002:\u0002\u0092\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010F\u001a\u00020\u0015H\u0002J\u0010\u0010G\u001a\u00020\u00152\u0006\u0010H\u001a\u00020&H\u0002J\b\u0010I\u001a\u00020\u0015H\u0002J\u0012\u0010J\u001a\u0004\u0018\u00010K2\u0006\u0010L\u001a\u00020\u0007H\u0002J\b\u0010M\u001a\u00020NH\u0002J\b\u0010O\u001a\u00020NH\u0002J\b\u0010P\u001a\u00020NH\u0002J\u0010\u0010Q\u001a\u00020\u00152\u0006\u0010R\u001a\u00020&H\u0002J\u0010\u0010S\u001a\u00020\u00152\u0006\u0010H\u001a\u00020&H\u0002J\b\u0010T\u001a\u00020\u0015H\u0002J\b\u0010U\u001a\u00020\u0015H\u0002J\b\u0010V\u001a\u00020\u0015H\u0002J\b\u0010W\u001a\u00020\u0015H\u0002J\b\u0010X\u001a\u00020\u0015H\u0002J\b\u0010Y\u001a\u00020\u0015H\u0002J\b\u0010Z\u001a\u00020\u0015H\u0002J\b\u0010[\u001a\u00020\u0015H\u0002J\b\u0010\\\u001a\u00020\u0015H\u0002J\b\u0010]\u001a\u00020\u0015H\u0002J\b\u0010^\u001a\u00020\u0015H\u0002J\b\u0010_\u001a\u00020\u0015H\u0002J\b\u0010`\u001a\u00020\u0015H\u0002J\b\u0010a\u001a\u00020\u0015H\u0002J\b\u0010b\u001a\u00020\u0015H\u0002J\b\u0010c\u001a\u00020\u0015H\u0002J\b\u0010d\u001a\u00020\u0015H\u0002J\b\u0010e\u001a\u00020\u0015H\u0002J\b\u0010f\u001a\u00020\u0015H\u0002J\b\u0010g\u001a\u00020\u0015H\u0002J\b\u0010h\u001a\u00020\u0015H\u0002J\u0010\u0010i\u001a\u00020\u00152\u0006\u0010j\u001a\u00020kH\u0016J&\u0010l\u001a\u0004\u0018\u00010m2\u0006\u0010n\u001a\u00020o2\b\u0010p\u001a\u0004\u0018\u00010q2\b\u0010r\u001a\u0004\u0018\u00010sH\u0016J\b\u0010t\u001a\u00020\u0015H\u0016J\u001a\u0010u\u001a\u00020\u00152\u0006\u0010v\u001a\u00020m2\b\u0010r\u001a\u0004\u0018\u00010sH\u0016J\u0010\u0010w\u001a\u00020\u00152\u0006\u0010x\u001a\u00020NH\u0002J\u0012\u0010y\u001a\u00020\u00152\b\u0010z\u001a\u0004\u0018\u00010{H\u0002J\u0010\u0010|\u001a\u00020\u00152\u0006\u0010}\u001a\u00020NH\u0002J\u0010\u0010~\u001a\u00020\u00152\u0006\u0010x\u001a\u00020NH\u0002J\n\u0010\u007f\u001a\u0004\u0018\u00010\u0017H\u0002J(\u0010\u0080\u0001\u001a\u00020\u00152\n\u0010\u0081\u0001\u001a\u0005\u0018\u00010\u0082\u00012\u0011\u0010\u0083\u0001\u001a\f\u0018\u00010\u0084\u0001j\u0005\u0018\u0001`\u0085\u0001H\u0016J\t\u0010\u0086\u0001\u001a\u00020\u0015H\u0002J\u0012\u0010\u0087\u0001\u001a\u00020\u00152\u0007\u0010\u0088\u0001\u001a\u00020\u0005H\u0002J\t\u0010\u0089\u0001\u001a\u00020\u0015H\u0002J\u0011\u0010\u008a\u0001\u001a\u00020\u00052\u0006\u0010x\u001a\u00020NH\u0002J\t\u0010\u008b\u0001\u001a\u00020\u0015H\u0002J\u001a\u0010\u008c\u0001\u001a\u00020\u00152\u000f\u0010\u008d\u0001\u001a\n\u0012\u0005\u0012\u00030\u008f\u00010\u008e\u0001H\u0002J\u0012\u0010\u0090\u0001\u001a\u00020\u00152\u0007\u0010\u0091\u0001\u001a\u00020$H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000ej\b\u0012\u0004\u0012\u00020\u0007`\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\u0010\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001bR\u001e\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000ej\b\u0012\u0004\u0012\u00020\u0007`\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010-X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\"\u00102\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010-X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010/\"\u0004\b4\u00101R\u001e\u00105\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000ej\b\u0012\u0004\u0012\u00020\u0007`\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00106\u001a\u0004\u0018\u000107X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00108\u001a\u0004\u0018\u000109X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010;\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u000ej\b\u0012\u0004\u0012\u00020\u0007`\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010<\u001a\u00020=8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b@\u0010\u001d\u001a\u0004\b>\u0010?R\u001b\u0010A\u001a\u00020B8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bE\u0010\u001d\u001a\u0004\bC\u0010D¨\u0006\u0093\u0001"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/AdvancedSettingsFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/BeeperBindContract$ViewInterface;", "()V", "BATTERY_GUARD_MIN", "", "TAG", "", "TYPE_CRUISE", "TYPE_DELIVER", "TYPE_ESCORTING", "TYPE_GOHOME", "TYPE_RECYCLING", "batteryGuardList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "batteryLevelListener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "level", "", "batteryWaitDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/TransparentLoadDialog;", "beeperBindPresenter", "Lcom/pudutech/bumblebee/presenter/robot_open_task/BeeperBindPresenter;", "getBeeperBindPresenter", "()Lcom/pudutech/bumblebee/presenter/robot_open_task/BeeperBindPresenter;", "beeperBindPresenter$delegate", "Lkotlin/Lazy;", "brakeList", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "dialog", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "dialogUIStatus", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/AdvancedSettingsFragment$ServerDialogUIStatus;", "isDialogCruiseShow", "", "isDialogDeliverShow", "isDialogEscortingShow", "isDialogGoHomeShow", "mainHandler", "Landroid/os/Handler;", "onAboutInfoSwitchChange", "Lkotlin/Function0;", "getOnAboutInfoSwitchChange", "()Lkotlin/jvm/functions/Function0;", "setOnAboutInfoSwitchChange", "(Lkotlin/jvm/functions/Function0;)V", "onBoxNoticeSwitchChange", "getOnBoxNoticeSwitchChange", "setOnBoxNoticeSwitchChange", "passageCapacityList", "serverAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/radiobtntv/RadioBtnTvAdapter;", "serverDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/RecycleViewDialog;", "serverPosition", "speedList", "steadTypeLayout", "Landroid/widget/RadioGroup;", "getSteadTypeLayout", "()Landroid/widget/RadioGroup;", "steadTypeLayout$delegate", "vm", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/advancedsetting/AdvancedSettingVM;", "getVm", "()Lcom/pudutech/bumblebee/robot_ui/viewmodel/advancedsetting/AdvancedSettingVM;", "vm$delegate", "bindPresenter", "closeCallReached", "isChecked", "createObserver", "generateQRCodeBitmap", "Landroid/graphics/Bitmap;", "code", "getBatteryCurrentLevel", "", "getBrakeCurrentLevel", "getPassageCapacityLevel", "hideReachedSwitch", "isVisiable", "hideReachedTime", "initAboutInfoSwitch", "initAutoWifi", "initBatteryGuard", "initBrake", "initBumper", "initClean", "initCruiseSpeed", "initDeliverSpeed", "initEscortingSpeed", "initGoHomeSpeed", "initMapSyncSwitch", "initPalletDeliverTtsFunction", "initPassageCapacity", "initQrCodeView", "initRecycleModeSwitch", "initRecyclingSpeed", "initServer", "initSpeedData", "initSpeedView", "initSteadyModeSwitch", "initView", "onAttach", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDetach", "onViewCreated", "view", "setBatteryCurrentLevel", ES6Iterator.VALUE_PROPERTY, "setBatterySeekBarNotAvailable", "seekbar", "Lcom/warkiz/widget/IndicatorSeekBar;", "setBrakeLevel", "progress", "setPassageCapacityLevel", "showBatteryWaitDialog", "showBindCode", "current", "Lcom/pudutech/robot/opensdk/aliyun/bean/BindCodeData;", C3898x.f4338g, "Ljava/lang/Exception;", "Lkotlin/Exception;", "showCleanWaitDialog", "showFastSpeedTip", "type", "showServerDialog", "transformBatteryLevel", "unBindPresenter", "updateServerDialog", "data", "", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/advancedsetting/ServerInfoBean;", "updateServerDialogUI", "status", "ServerDialogUIStatus", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class AdvancedSettingsFragment extends Fragment implements BeeperBindContract.ViewInterface {
    private final int BATTERY_GUARD_MIN;
    private final String TAG = "AdvancedSettingsFragment";
    private final int TYPE_CRUISE;
    private final int TYPE_DELIVER;
    private final int TYPE_ESCORTING;
    private final int TYPE_GOHOME;
    private final int TYPE_RECYCLING;
    private HashMap _$_findViewCache;
    private final ArrayList<String> batteryGuardList;
    private final Function1<Integer, Unit> batteryLevelListener;
    private TransparentLoadDialog batteryWaitDialog;

    /* renamed from: beeperBindPresenter$delegate, reason: from kotlin metadata */
    private final Lazy beeperBindPresenter;
    private final ArrayList<String> brakeList;
    private final CoroutineScope coroutineScope;
    private ShowTipMsgDialog dialog;
    private ServerDialogUIStatus dialogUIStatus;
    private boolean isDialogCruiseShow;
    private boolean isDialogDeliverShow;
    private boolean isDialogEscortingShow;
    private boolean isDialogGoHomeShow;
    private final Handler mainHandler;
    private Function0<Unit> onAboutInfoSwitchChange;
    private Function0<Unit> onBoxNoticeSwitchChange;
    private final ArrayList<String> passageCapacityList;
    private RadioBtnTvAdapter serverAdapter;
    private RecycleViewDialog serverDialog;
    private int serverPosition;
    private final ArrayList<String> speedList;

    /* renamed from: steadTypeLayout$delegate, reason: from kotlin metadata */
    private final Lazy steadTypeLayout;

    /* renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SmoothMode.values().length];

        static {
            $EnumSwitchMapping$0[SmoothMode.NoSmooth.ordinal()] = 1;
            $EnumSwitchMapping$0[SmoothMode.LightLoad.ordinal()] = 2;
            $EnumSwitchMapping$0[SmoothMode.HeavyLoad.ordinal()] = 3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BeeperBindPresenter getBeeperBindPresenter() {
        return (BeeperBindPresenter) this.beeperBindPresenter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RadioGroup getSteadTypeLayout() {
        return (RadioGroup) this.steadTypeLayout.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AdvancedSettingVM getVm() {
        return (AdvancedSettingVM) this.vm.getValue();
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public AdvancedSettingsFragment() {
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$$special$$inlined$viewModels$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        this.vm = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(AdvancedSettingVM.class), new Function0<ViewModelStore>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$$special$$inlined$viewModels$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) Function0.this.invoke()).getViewModelStore();
                Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "ownerProducer().viewModelStore");
                return viewModelStore;
            }
        }, (Function0) null);
        this.speedList = CollectionsKt.arrayListOf("0.2", "0.3", "0.4", Constans.KEY_DEFAULT_SPEED_CONFIG, "0.6", "0.7", "0.8", "0.9", "1.0", BuildConfig.VERSION_NAME, "1.2");
        this.brakeList = CollectionsKt.arrayListOf("1", "2", "3");
        this.batteryGuardList = CollectionsKt.arrayListOf("5%", "10%", "15%");
        this.passageCapacityList = CollectionsKt.arrayListOf("70", "80", "90");
        this.TYPE_CRUISE = 1;
        this.TYPE_DELIVER = 2;
        this.TYPE_GOHOME = 3;
        this.TYPE_ESCORTING = 4;
        this.TYPE_RECYCLING = 5;
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
        this.BATTERY_GUARD_MIN = 30;
        this.beeperBindPresenter = LazyKt.lazy(new Function0<BeeperBindPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$beeperBindPresenter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BeeperBindPresenter invoke() {
                BeeperBindPresenter beeperBindPresenter;
                PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
                BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(BeeperBindPresenter.class).toString());
                Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(BeeperBindPresenter.class) + ' ' + basePresenterInterface);
                if (basePresenterInterface == null) {
                    beeperBindPresenter = new BeeperBindPresenter();
                    presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(BeeperBindPresenter.class).toString(), beeperBindPresenter);
                } else {
                    if (!(basePresenterInterface instanceof BeeperBindPresenter)) {
                        basePresenterInterface = null;
                    }
                    beeperBindPresenter = (BeeperBindPresenter) basePresenterInterface;
                }
                if (beeperBindPresenter == null) {
                    Intrinsics.throwNpe();
                }
                return beeperBindPresenter;
            }
        });
        this.mainHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$mainHandler$1
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                String str;
                BeeperBindPresenter beeperBindPresenter;
                if (AdvancedSettingsFragment.this.getContext() != null) {
                    str = AdvancedSettingsFragment.this.TAG;
                    Pdlog.m3273d(str, "auto gen qrcode");
                    beeperBindPresenter = AdvancedSettingsFragment.this.getBeeperBindPresenter();
                    beeperBindPresenter.genBindCode();
                }
                return true;
            }
        });
        this.dialogUIStatus = ServerDialogUIStatus.LoadStatus.INSTANCE;
        this.steadTypeLayout = LazyKt.lazy(new AdvancedSettingsFragment$steadTypeLayout$2(this));
        this.batteryLevelListener = new Function1<Integer, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$batteryLevelListener$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                int transformBatteryLevel;
                TransparentLoadDialog transparentLoadDialog;
                float batteryCurrentLevel;
                IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) AdvancedSettingsFragment.this._$_findCachedViewById(C4188R.id.battery_guard_seek_bar);
                if (indicatorSeekBar != null) {
                    transformBatteryLevel = AdvancedSettingsFragment.this.transformBatteryLevel(indicatorSeekBar.getProgressFloat());
                    if (transformBatteryLevel != i) {
                        batteryCurrentLevel = AdvancedSettingsFragment.this.getBatteryCurrentLevel();
                        indicatorSeekBar.setProgress(batteryCurrentLevel);
                    }
                    AdvancedSettingsFragment.this.setBatterySeekBarNotAvailable(indicatorSeekBar);
                    transparentLoadDialog = AdvancedSettingsFragment.this.batteryWaitDialog;
                    if (transparentLoadDialog == null || !transparentLoadDialog.isShowing()) {
                        return;
                    }
                    transparentLoadDialog.dismiss();
                }
            }
        };
    }

    public final Function0<Unit> getOnAboutInfoSwitchChange() {
        return this.onAboutInfoSwitchChange;
    }

    public final void setOnAboutInfoSwitchChange(Function0<Unit> function0) {
        this.onAboutInfoSwitchChange = function0;
    }

    public final Function0<Unit> getOnBoxNoticeSwitchChange() {
        return this.onBoxNoticeSwitchChange;
    }

    public final void setOnBoxNoticeSwitchChange(Function0<Unit> function0) {
        this.onBoxNoticeSwitchChange = function0;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C4188R.layout.fragment_advanced_setting, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
        createObserver();
    }

    private final void initView() {
        initSpeedData();
        initRecycleModeSwitch();
        initAboutInfoSwitch();
        initSteadyModeSwitch();
        initAutoWifi();
        initMapSyncSwitch();
        initSpeedView();
        initQrCodeView();
        initBumper();
        initBrake();
        initClean();
        initBatteryGuard();
        initPalletDeliverTtsFunction();
        initPassageCapacity();
        initServer();
    }

    private final void createObserver() {
        LiveData<ServerInfoBean> localserverListLV = getVm().getLocalserverListLV();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        localserverListLV.observe(requireActivity, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$createObserver$$inlined$observe$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                String str;
                ServerInfoBean serverInfoBean = (ServerInfoBean) t;
                TextView server_name_tv = (TextView) AdvancedSettingsFragment.this._$_findCachedViewById(C4188R.id.server_name_tv);
                Intrinsics.checkExpressionValueIsNotNull(server_name_tv, "server_name_tv");
                if (serverInfoBean == null || (str = serverInfoBean.getServerName()) == null) {
                    str = "公有云";
                }
                server_name_tv.setText(str);
            }
        });
        LiveData<List<ServerInfoBean>> serverListLV = getVm().getServerListLV();
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity2, "requireActivity()");
        serverListLV.observe(requireActivity2, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$createObserver$$inlined$observe$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                RecycleViewDialog recycleViewDialog;
                List list = (List) t;
                if (list.isEmpty()) {
                    recycleViewDialog = AdvancedSettingsFragment.this.serverDialog;
                    if (recycleViewDialog != null) {
                        recycleViewDialog.dismiss();
                    }
                    ToastUtils.show(AdvancedSettingsFragment.this.requireContext(), C4188R.string.query_server_error);
                    return;
                }
                AdvancedSettingsFragment.this.updateServerDialog(list);
                AdvancedSettingsFragment.this.updateServerDialogUI(AdvancedSettingsFragment.ServerDialogUIStatus.ReadyStatus.INSTANCE);
            }
        });
        LiveData<Boolean> serverStateLV = getVm().getServerStateLV();
        FragmentActivity requireActivity3 = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity3, "requireActivity()");
        serverStateLV.observe(requireActivity3, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$createObserver$$inlined$observe$3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                RecycleViewDialog recycleViewDialog;
                if (((Boolean) t).booleanValue()) {
                    AdvancedSettingsFragment.this.updateServerDialogUI(AdvancedSettingsFragment.ServerDialogUIStatus.ConfirmStatus.INSTANCE);
                    return;
                }
                recycleViewDialog = AdvancedSettingsFragment.this.serverDialog;
                if (recycleViewDialog != null) {
                    recycleViewDialog.dismiss();
                }
                ToastUtils.show(AdvancedSettingsFragment.this.requireContext(), C4188R.string.pdStr7_200);
            }
        });
    }

    private final void initServer() {
        TextView server_name_tv = (TextView) _$_findCachedViewById(C4188R.id.server_name_tv);
        Intrinsics.checkExpressionValueIsNotNull(server_name_tv, "server_name_tv");
        final Map emptyMap = MapsKt.emptyMap();
        final int i = 0;
        server_name_tv.setOnClickListener(new OnLazyVoiceClickListener(emptyMap, i) { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initServer$$inlined$singleClick$1
            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick(View v) {
                AdvancedSettingVM vm;
                Intrinsics.checkParameterIsNotNull(v, "v");
                super.onSingleClick(v);
                this.showServerDialog();
                vm = this.getVm();
                vm.requestServerList();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showServerDialog() {
        if (this.serverDialog == null) {
            Context requireContext = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
            this.serverDialog = new RecycleViewDialog(requireContext);
        }
        final RecycleViewDialog recycleViewDialog = this.serverDialog;
        if (recycleViewDialog != null) {
            updateServerDialogUI(ServerDialogUIStatus.LoadStatus.INSTANCE);
            recycleViewDialog.setConfirmListener(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$showServerDialog$$inlined$apply$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Code restructure failed: missing block: B:39:0x007c, code lost:
                
                    r0 = r2.serverAdapter;
                 */
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final void invoke2() {
                    AdvancedSettingsFragment.ServerDialogUIStatus serverDialogUIStatus;
                    RadioBtnTvAdapter radioBtnTvAdapter;
                    List<IRadioBtnTvBean> data;
                    Object obj;
                    AdvancedSettingVM vm;
                    RadioBtnTvAdapter radioBtnTvAdapter2;
                    List<IRadioBtnTvBean> data2;
                    Object obj2;
                    AdvancedSettingVM vm2;
                    AdvancedSettingVM vm3;
                    serverDialogUIStatus = this.dialogUIStatus;
                    if (Intrinsics.areEqual(serverDialogUIStatus, AdvancedSettingsFragment.ServerDialogUIStatus.LoadStatus.INSTANCE)) {
                        return;
                    }
                    if (Intrinsics.areEqual(serverDialogUIStatus, AdvancedSettingsFragment.ServerDialogUIStatus.ReadyStatus.INSTANCE)) {
                        radioBtnTvAdapter2 = this.serverAdapter;
                        if (radioBtnTvAdapter2 == null || (data2 = radioBtnTvAdapter2.getData()) == null) {
                            return;
                        }
                        Iterator<T> it = data2.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                obj2 = null;
                                break;
                            } else {
                                obj2 = it.next();
                                if (((IRadioBtnTvBean) obj2).isCheck()) {
                                    break;
                                }
                            }
                        }
                        IRadioBtnTvBean iRadioBtnTvBean = (IRadioBtnTvBean) obj2;
                        if (iRadioBtnTvBean != null) {
                            vm2 = this.getVm();
                            if (iRadioBtnTvBean == null) {
                                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.viewmodel.advancedsetting.ServerInfoBean");
                            }
                            ServerInfoBean serverInfoBean = (ServerInfoBean) iRadioBtnTvBean;
                            if (!vm2.compareSelectedLocal(serverInfoBean)) {
                                String host = serverInfoBean.getHost();
                                if (host != null) {
                                    vm3 = this.getVm();
                                    vm3.checkServerState(host);
                                    return;
                                }
                                return;
                            }
                            RecycleViewDialog.this.dismiss();
                            return;
                        }
                        return;
                    }
                    if (!Intrinsics.areEqual(serverDialogUIStatus, AdvancedSettingsFragment.ServerDialogUIStatus.ConfirmStatus.INSTANCE) || radioBtnTvAdapter == null || (data = radioBtnTvAdapter.getData()) == null) {
                        return;
                    }
                    Iterator<T> it2 = data.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            obj = null;
                            break;
                        } else {
                            obj = it2.next();
                            if (((IRadioBtnTvBean) obj).isCheck()) {
                                break;
                            }
                        }
                    }
                    IRadioBtnTvBean iRadioBtnTvBean2 = (IRadioBtnTvBean) obj;
                    if (iRadioBtnTvBean2 != null) {
                        vm = this.getVm();
                        if (iRadioBtnTvBean2 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.viewmodel.advancedsetting.ServerInfoBean");
                        }
                        vm.saveServerInfo((ServerInfoBean) iRadioBtnTvBean2);
                    }
                }
            });
            recycleViewDialog.setCloseListener(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$showServerDialog$1$2
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
                    RecycleViewDialog.this.dismiss();
                }
            });
            recycleViewDialog.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateServerDialog(List<ServerInfoBean> data) {
        if (this.serverAdapter == null) {
            this.serverAdapter = new RadioBtnTvAdapter();
            RecycleViewDialog recycleViewDialog = this.serverDialog;
            if (recycleViewDialog != null) {
                RadioBtnTvAdapter radioBtnTvAdapter = this.serverAdapter;
                if (radioBtnTvAdapter == null) {
                    Intrinsics.throwNpe();
                }
                recycleViewDialog.setRecycleView(radioBtnTvAdapter, new GridLayoutManager(requireContext(), 2), new GridSpacingItemDecoration(2, DensityUtil.dp2px(getContext(), 24.0f), false));
            }
            RadioBtnTvAdapter radioBtnTvAdapter2 = this.serverAdapter;
            if (radioBtnTvAdapter2 != null) {
                radioBtnTvAdapter2.setOnItemClickListener(new OnLazyItemClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$updateServerDialog$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(null, 0, 3, null);
                    }

                    @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener
                    public void onSingleItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                        RadioBtnTvAdapter radioBtnTvAdapter3;
                        RadioBtnTvAdapter radioBtnTvAdapter4;
                        List<IRadioBtnTvBean> data2;
                        Intrinsics.checkParameterIsNotNull(adapter, "adapter");
                        Intrinsics.checkParameterIsNotNull(view, "view");
                        AdvancedSettingsFragment.this.serverPosition = position;
                        radioBtnTvAdapter3 = AdvancedSettingsFragment.this.serverAdapter;
                        if (radioBtnTvAdapter3 != null && (data2 = radioBtnTvAdapter3.getData()) != null) {
                            List<IRadioBtnTvBean> list = data2;
                            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                            int i = 0;
                            for (Object obj : list) {
                                int i2 = i + 1;
                                if (i < 0) {
                                    CollectionsKt.throwIndexOverflow();
                                }
                                IRadioBtnTvBean iRadioBtnTvBean = (IRadioBtnTvBean) obj;
                                if (iRadioBtnTvBean != null) {
                                    ((ServerInfoBean) iRadioBtnTvBean).setSelect(i == position);
                                    arrayList.add(Unit.INSTANCE);
                                    i = i2;
                                } else {
                                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.viewmodel.advancedsetting.ServerInfoBean");
                                }
                            }
                        }
                        radioBtnTvAdapter4 = AdvancedSettingsFragment.this.serverAdapter;
                        if (radioBtnTvAdapter4 != null) {
                            radioBtnTvAdapter4.notifyDataSetChanged();
                        }
                    }
                });
            }
        }
        RadioBtnTvAdapter radioBtnTvAdapter3 = this.serverAdapter;
        if (radioBtnTvAdapter3 != null) {
            radioBtnTvAdapter3.setNewData(data);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateServerDialogUI(ServerDialogUIStatus status) {
        RecycleViewDialog recycleViewDialog;
        this.dialogUIStatus = status;
        if (Intrinsics.areEqual(status, ServerDialogUIStatus.LoadStatus.INSTANCE)) {
            RecycleViewDialog recycleViewDialog2 = this.serverDialog;
            if (recycleViewDialog2 != null) {
                recycleViewDialog2.setLoadIvVisibility(true);
                recycleViewDialog2.setContentRvVisibility(false);
                recycleViewDialog2.setConfirmBtnVisibility(false);
                recycleViewDialog2.setTipTvVisibility(false);
                recycleViewDialog2.setCloseIvVisibility(true);
                recycleViewDialog2.setTitleText(recycleViewDialog2.getString(C4188R.string.select_server_title));
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(status, ServerDialogUIStatus.ReadyStatus.INSTANCE)) {
            RecycleViewDialog recycleViewDialog3 = this.serverDialog;
            if (recycleViewDialog3 != null) {
                recycleViewDialog3.setLoadIvVisibility(false);
                recycleViewDialog3.setContentRvVisibility(true);
                recycleViewDialog3.setConfirmBtnVisibility(true);
                recycleViewDialog3.setTipTvVisibility(false);
                recycleViewDialog3.setCloseIvVisibility(true);
                recycleViewDialog3.setConfirmText(recycleViewDialog3.getString(C4188R.string.pdStr8_13));
                recycleViewDialog3.setConfirmBg(C4188R.drawable.selector_confirm_dialog_btn_blu);
                return;
            }
            return;
        }
        if (!Intrinsics.areEqual(status, ServerDialogUIStatus.ConfirmStatus.INSTANCE) || (recycleViewDialog = this.serverDialog) == null) {
            return;
        }
        recycleViewDialog.setLoadIvVisibility(false);
        recycleViewDialog.setContentRvVisibility(false);
        recycleViewDialog.setConfirmBtnVisibility(true);
        recycleViewDialog.setTipTvVisibility(true);
        recycleViewDialog.setCloseIvVisibility(false);
        recycleViewDialog.setTipText(recycleViewDialog.getString(C4188R.string.switch_server_success));
        recycleViewDialog.setConfirmText(recycleViewDialog.getString(C4188R.string.reboot));
        recycleViewDialog.setConfirmBg(C4188R.drawable.selector_confirm_dialog_btn_green);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AdvancedSettingsFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/AdvancedSettingsFragment$ServerDialogUIStatus;", "", "()V", "ConfirmStatus", "LoadStatus", "ReadyStatus", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/AdvancedSettingsFragment$ServerDialogUIStatus$LoadStatus;", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/AdvancedSettingsFragment$ServerDialogUIStatus$ReadyStatus;", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/AdvancedSettingsFragment$ServerDialogUIStatus$ConfirmStatus;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static abstract class ServerDialogUIStatus {

        /* compiled from: AdvancedSettingsFragment.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/AdvancedSettingsFragment$ServerDialogUIStatus$LoadStatus;", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/AdvancedSettingsFragment$ServerDialogUIStatus;", "()V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes3.dex */
        public static final class LoadStatus extends ServerDialogUIStatus {
            public static final LoadStatus INSTANCE = new LoadStatus();

            private LoadStatus() {
                super(null);
            }
        }

        private ServerDialogUIStatus() {
        }

        public /* synthetic */ ServerDialogUIStatus(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* compiled from: AdvancedSettingsFragment.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/AdvancedSettingsFragment$ServerDialogUIStatus$ReadyStatus;", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/AdvancedSettingsFragment$ServerDialogUIStatus;", "()V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes3.dex */
        public static final class ReadyStatus extends ServerDialogUIStatus {
            public static final ReadyStatus INSTANCE = new ReadyStatus();

            private ReadyStatus() {
                super(null);
            }
        }

        /* compiled from: AdvancedSettingsFragment.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/AdvancedSettingsFragment$ServerDialogUIStatus$ConfirmStatus;", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/AdvancedSettingsFragment$ServerDialogUIStatus;", "()V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes3.dex */
        public static final class ConfirmStatus extends ServerDialogUIStatus {
            public static final ConfirmStatus INSTANCE = new ConfirmStatus();

            private ConfirmStatus() {
                super(null);
            }
        }
    }

    private final void initAutoWifi() {
        Switch switch_wifi_auto_open_mode = (Switch) _$_findCachedViewById(C4188R.id.switch_wifi_auto_open_mode);
        Intrinsics.checkExpressionValueIsNotNull(switch_wifi_auto_open_mode, "switch_wifi_auto_open_mode");
        switch_wifi_auto_open_mode.setChecked(Constans.INSTANCE.getWifiAutoOpenType() == 1);
        ((Switch) _$_findCachedViewById(C4188R.id.switch_wifi_auto_open_mode)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initAutoWifi$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton buttonView, boolean z) {
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                if (z) {
                    Constans.INSTANCE.setWifiAutoOpenType(1);
                } else {
                    Constans.INSTANCE.setWifiAutoOpenType(0);
                }
            }
        }, 7, null));
    }

    private final void initSteadyModeSwitch() {
        Switch cover_around_sw = (Switch) _$_findCachedViewById(C4188R.id.cover_around_sw);
        Intrinsics.checkExpressionValueIsNotNull(cover_around_sw, "cover_around_sw");
        cover_around_sw.setChecked(RobotConfig.INSTANCE.getCoverAround());
        ((Switch) _$_findCachedViewById(C4188R.id.cover_around_sw)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initSteadyModeSwitch$1
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
                String str;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                RobotConfig.INSTANCE.setCoverAround(z);
                str = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str, "cover_around_sw() coverAround =" + RobotConfig.INSTANCE.getCoverAround());
            }
        }, 7, null));
        Switch steady_mode_switch = (Switch) _$_findCachedViewById(C4188R.id.steady_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(steady_mode_switch, "steady_mode_switch");
        steady_mode_switch.setChecked(Constans.INSTANCE.getSettingSteadyModeSwitch());
        Switch steady_mode_switch2 = (Switch) _$_findCachedViewById(C4188R.id.steady_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(steady_mode_switch2, "steady_mode_switch");
        if (steady_mode_switch2.isChecked()) {
            getSteadTypeLayout().setVisibility(0);
            View steady_mode_line = _$_findCachedViewById(C4188R.id.steady_mode_line);
            Intrinsics.checkExpressionValueIsNotNull(steady_mode_line, "steady_mode_line");
            steady_mode_line.setVisibility(0);
        }
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("initSteadyModeSwitch ");
        Switch steady_mode_switch3 = (Switch) _$_findCachedViewById(C4188R.id.steady_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(steady_mode_switch3, "steady_mode_switch");
        sb.append(steady_mode_switch3.isChecked());
        Pdlog.m3273d(str, sb.toString());
        ((Switch) _$_findCachedViewById(C4188R.id.steady_mode_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initSteadyModeSwitch$2
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
                String str2;
                RadioGroup steadTypeLayout;
                RadioGroup steadTypeLayout2;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                str2 = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str2, "steady_mode_switch " + z);
                Constans.INSTANCE.setSettingSteadyModeSwitch(z);
                int i = z ? 0 : 8;
                steadTypeLayout = AdvancedSettingsFragment.this.getSteadTypeLayout();
                steadTypeLayout.setVisibility(i);
                View steady_mode_line2 = AdvancedSettingsFragment.this._$_findCachedViewById(C4188R.id.steady_mode_line);
                Intrinsics.checkExpressionValueIsNotNull(steady_mode_line2, "steady_mode_line");
                steady_mode_line2.setVisibility(i);
                if (z) {
                    return;
                }
                steadTypeLayout2 = AdvancedSettingsFragment.this.getSteadTypeLayout();
                steadTypeLayout2.check(C4188R.id.rbt_single);
                Constans.INSTANCE.setSteadyModeType(0);
                Constans.INSTANCE.setSteadyRunning(false);
            }
        }, 7, null));
    }

    private final void initMapSyncSwitch() {
        Switch setting_map_sync_switch = (Switch) _$_findCachedViewById(C4188R.id.setting_map_sync_switch);
        Intrinsics.checkExpressionValueIsNotNull(setting_map_sync_switch, "setting_map_sync_switch");
        setting_map_sync_switch.setChecked(Constans.INSTANCE.isMapSyncSwitch());
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("initMapSyncSwitch: ");
        Switch steady_mode_switch = (Switch) _$_findCachedViewById(C4188R.id.steady_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(steady_mode_switch, "steady_mode_switch");
        sb.append(steady_mode_switch.isChecked());
        Pdlog.m3273d(str, sb.toString());
        ((Switch) _$_findCachedViewById(C4188R.id.setting_map_sync_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initMapSyncSwitch$1
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
                String str2;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                str2 = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str2, "setting_map_sync_switch: " + z);
                Constans.INSTANCE.setMapSyncSwitch(z);
            }
        }, 7, null));
    }

    private final void initSpeedData() {
        List<String> speedLevels = RobotSetting.INSTANCE.getSpeedLevels();
        List<String> list = speedLevels;
        if (list == null || list.isEmpty()) {
            return;
        }
        Pdlog.m3273d(this.TAG, "speedLevels " + speedLevels);
        this.speedList.clear();
        this.speedList.addAll(list);
    }

    private final void initBumper() {
        Switch bumper_switch = (Switch) _$_findCachedViewById(C4188R.id.bumper_switch);
        Intrinsics.checkExpressionValueIsNotNull(bumper_switch, "bumper_switch");
        bumper_switch.setChecked(RobotConfig.INSTANCE.isOpenBumper());
        ((Switch) _$_findCachedViewById(C4188R.id.bumper_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initBumper$1
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
                String str;
                String str2;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                RobotConfig.INSTANCE.setOpenBumper(z);
                str = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str, "initBumper()###isOpenBumper:" + RobotConfig.INSTANCE.isOpenBumper());
                boolean z2 = z ^ true;
                int bumperState = RobotConfig.INSTANCE.setBumperState(z2);
                str2 = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str2, "isUploadError:" + z2 + "####bumperState:" + bumperState);
            }
        }, 7, null));
    }

    private final void initQrCodeView() {
        Switch setting_advanced_fall_switch = (Switch) _$_findCachedViewById(C4188R.id.setting_advanced_fall_switch);
        Intrinsics.checkExpressionValueIsNotNull(setting_advanced_fall_switch, "setting_advanced_fall_switch");
        setting_advanced_fall_switch.setChecked(FallDropHelper.INSTANCE.getDropDetStatus());
        ((Switch) _$_findCachedViewById(C4188R.id.setting_advanced_fall_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initQrCodeView$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton buttonView, boolean z) {
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                FallDropHelper.INSTANCE.setEnableDropDet(z);
            }
        }, 7, null));
        Switch beeper_call_switch = (Switch) _$_findCachedViewById(C4188R.id.beeper_call_switch);
        Intrinsics.checkExpressionValueIsNotNull(beeper_call_switch, "beeper_call_switch");
        beeper_call_switch.setChecked(getBeeperBindPresenter().getCallSwitch());
        ((Switch) _$_findCachedViewById(C4188R.id.beeper_call_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initQrCodeView$2
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
                String str;
                BeeperBindPresenter beeperBindPresenter;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                str = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str, "beeper_call_switch " + z);
                beeperBindPresenter = AdvancedSettingsFragment.this.getBeeperBindPresenter();
                beeperBindPresenter.setCallSwitch(z, false);
                AdvancedSettingsFragment.this.closeCallReached(z);
            }
        }, 7, null));
        ((CardView) _$_findCachedViewById(C4188R.id.gen_code_cv)).setOnClickListener(new OnLazyClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initQrCodeView$3
            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener
            public void onSingleClick() {
                BeeperBindPresenter beeperBindPresenter;
                String str;
                BeeperBindPresenter beeperBindPresenter2;
                beeperBindPresenter = AdvancedSettingsFragment.this.getBeeperBindPresenter();
                if (!beeperBindPresenter.getCallSwitch()) {
                    ToastUtils.show(AdvancedSettingsFragment.this.getContext(), AdvancedSettingsFragment.this.getString(C4188R.string.pdStr7_151), new Object[0]);
                    return;
                }
                ProgressBar gen_code_progress = (ProgressBar) AdvancedSettingsFragment.this._$_findCachedViewById(C4188R.id.gen_code_progress);
                Intrinsics.checkExpressionValueIsNotNull(gen_code_progress, "gen_code_progress");
                gen_code_progress.setVisibility(0);
                CardView gen_code_cv = (CardView) AdvancedSettingsFragment.this._$_findCachedViewById(C4188R.id.gen_code_cv);
                Intrinsics.checkExpressionValueIsNotNull(gen_code_cv, "gen_code_cv");
                gen_code_cv.setEnabled(false);
                str = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str, "initQrCodeView onSingleClick");
                beeperBindPresenter2 = AdvancedSettingsFragment.this.getBeeperBindPresenter();
                beeperBindPresenter2.genBindCode();
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.unbind_all_tv)).setOnClickListener(new AdvancedSettingsFragment$initQrCodeView$4(this));
        ((TextView) _$_findCachedViewById(C4188R.id.beeper_call_open_tv)).setOnClickListener(new OnLazyClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initQrCodeView$5
            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener
            public void onSingleClick() {
                String str;
                str = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str, "beeper_call_open_tv onSingleClick : ");
                RelativeLayout call_open_layout = (RelativeLayout) AdvancedSettingsFragment.this._$_findCachedViewById(C4188R.id.call_open_layout);
                Intrinsics.checkExpressionValueIsNotNull(call_open_layout, "call_open_layout");
                if (call_open_layout.getVisibility() == 0) {
                    RelativeLayout call_open_layout2 = (RelativeLayout) AdvancedSettingsFragment.this._$_findCachedViewById(C4188R.id.call_open_layout);
                    Intrinsics.checkExpressionValueIsNotNull(call_open_layout2, "call_open_layout");
                    call_open_layout2.setVisibility(8);
                    TextView beeper_call_open_tv = (TextView) AdvancedSettingsFragment.this._$_findCachedViewById(C4188R.id.beeper_call_open_tv);
                    Intrinsics.checkExpressionValueIsNotNull(beeper_call_open_tv, "beeper_call_open_tv");
                    beeper_call_open_tv.setText(AdvancedSettingsFragment.this.getString(C4188R.string.pdStr7_143));
                    Drawable drawable = AdvancedSettingsFragment.this.getResources().getDrawable(C4188R.drawable.icon_settings_advanced_open);
                    Intrinsics.checkExpressionValueIsNotNull(drawable, "drawable");
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    ((TextView) AdvancedSettingsFragment.this._$_findCachedViewById(C4188R.id.beeper_call_open_tv)).setCompoundDrawables(null, null, drawable, null);
                    return;
                }
                RelativeLayout call_open_layout3 = (RelativeLayout) AdvancedSettingsFragment.this._$_findCachedViewById(C4188R.id.call_open_layout);
                Intrinsics.checkExpressionValueIsNotNull(call_open_layout3, "call_open_layout");
                call_open_layout3.setVisibility(0);
                TextView beeper_call_open_tv2 = (TextView) AdvancedSettingsFragment.this._$_findCachedViewById(C4188R.id.beeper_call_open_tv);
                Intrinsics.checkExpressionValueIsNotNull(beeper_call_open_tv2, "beeper_call_open_tv");
                beeper_call_open_tv2.setText(AdvancedSettingsFragment.this.getString(C4188R.string.pdStr7_144));
                Drawable drawable2 = AdvancedSettingsFragment.this.getResources().getDrawable(C4188R.drawable.icon_settings_advanced_packup);
                Intrinsics.checkExpressionValueIsNotNull(drawable2, "drawable");
                drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                ((TextView) AdvancedSettingsFragment.this._$_findCachedViewById(C4188R.id.beeper_call_open_tv)).setCompoundDrawables(null, null, drawable2, null);
            }
        });
        hideReachedSwitch(getBeeperBindPresenter().getCallSwitch());
        Switch call_reached_switch = (Switch) _$_findCachedViewById(C4188R.id.call_reached_switch);
        Intrinsics.checkExpressionValueIsNotNull(call_reached_switch, "call_reached_switch");
        call_reached_switch.setChecked(BeeperUtils.INSTANCE.isCallReachedoConfirmationSwitch());
        ((Switch) _$_findCachedViewById(C4188R.id.call_reached_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initQrCodeView$6
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
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                BeeperUtils.INSTANCE.setCallReachedoConfirmationSwitch(z);
                if (!z) {
                    Switch call_reached_time_switch = (Switch) AdvancedSettingsFragment.this._$_findCachedViewById(C4188R.id.call_reached_time_switch);
                    Intrinsics.checkExpressionValueIsNotNull(call_reached_time_switch, "call_reached_time_switch");
                    call_reached_time_switch.setChecked(false);
                    BeeperUtils.INSTANCE.setAutoCompleteCallTimeSwitch(false);
                    BeeperUtils.INSTANCE.setGetAutoCompleteCallTime(120000L);
                    ((EditText) AdvancedSettingsFragment.this._$_findCachedViewById(C4188R.id.call_reached_time_et)).setText(String.valueOf(120L));
                }
                AdvancedSettingsFragment.this.hideReachedTime(z);
            }
        }, 7, null));
        hideReachedTime(BeeperUtils.INSTANCE.isCallReachedoConfirmationSwitch());
        Switch call_reached_time_switch = (Switch) _$_findCachedViewById(C4188R.id.call_reached_time_switch);
        Intrinsics.checkExpressionValueIsNotNull(call_reached_time_switch, "call_reached_time_switch");
        call_reached_time_switch.setChecked(BeeperUtils.INSTANCE.isAutoCompleteCallTimeSwitch());
        ((Switch) _$_findCachedViewById(C4188R.id.call_reached_time_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initQrCodeView$7
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton buttonView, boolean z) {
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                BeeperUtils.INSTANCE.setAutoCompleteCallTimeSwitch(z);
            }
        }, 7, null));
        long j = 1000;
        ((EditText) _$_findCachedViewById(C4188R.id.call_reached_time_et)).setText(String.valueOf(BeeperUtils.INSTANCE.getGetAutoCompleteCallTime() / j));
        EditText call_reached_time_et = (EditText) _$_findCachedViewById(C4188R.id.call_reached_time_et);
        Intrinsics.checkExpressionValueIsNotNull(call_reached_time_et, "call_reached_time_et");
        call_reached_time_et.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initQrCodeView$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                String str;
                String valueOf = String.valueOf(s);
                long j2 = 120000;
                if (!TextUtils.isEmpty(valueOf)) {
                    try {
                        long parseLong = Long.parseLong(valueOf);
                        if (parseLong > 600) {
                            parseLong = 600;
                            try {
                                ((EditText) AdvancedSettingsFragment.this._$_findCachedViewById(C4188R.id.call_reached_time_et)).setText(String.valueOf(600L));
                            } catch (Exception unused) {
                                j2 = parseLong;
                                str = AdvancedSettingsFragment.this.TAG;
                                Pdlog.m3273d(str, "call_reached_time_et parse is error : " + valueOf);
                                BeeperUtils.INSTANCE.setGetAutoCompleteCallTime(j2);
                            }
                        }
                        if (parseLong <= 0) {
                            parseLong = 1;
                            ((EditText) AdvancedSettingsFragment.this._$_findCachedViewById(C4188R.id.call_reached_time_et)).setText(String.valueOf(1L));
                        }
                        j2 = parseLong * 1000;
                    } catch (Exception unused2) {
                    }
                }
                BeeperUtils.INSTANCE.setGetAutoCompleteCallTime(j2);
            }
        });
        TextView call_freeze_time_active_reset_tv = (TextView) _$_findCachedViewById(C4188R.id.call_freeze_time_active_reset_tv);
        Intrinsics.checkExpressionValueIsNotNull(call_freeze_time_active_reset_tv, "call_freeze_time_active_reset_tv");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(call_freeze_time_active_reset_tv, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initQrCodeView$9
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
                String str;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str, "call_freeze_time_active_reset_tv");
                BusinessSetting.INSTANCE.setCallFreezeTime_ms(1000 * 120);
                ((EditText) AdvancedSettingsFragment.this._$_findCachedViewById(C4188R.id.call_freeze_time_active_et)).setText(String.valueOf(120L));
            }
        }, 3, null);
        ((EditText) _$_findCachedViewById(C4188R.id.call_freeze_time_active_et)).setText(String.valueOf(BusinessSetting.INSTANCE.getCallFreezeTime_ms() / j));
        EditText call_freeze_time_active_et = (EditText) _$_findCachedViewById(C4188R.id.call_freeze_time_active_et);
        Intrinsics.checkExpressionValueIsNotNull(call_freeze_time_active_et, "call_freeze_time_active_et");
        call_freeze_time_active_et.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initQrCodeView$$inlined$doAfterTextChanged$2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                String str;
                String str2;
                String valueOf = String.valueOf(s);
                str = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str, "call_freeze_time_active_et : " + valueOf);
                if (valueOf.length() == 0) {
                    valueOf = "1";
                }
                try {
                    long parseLong = Long.parseLong(valueOf);
                    if (parseLong > 180) {
                        parseLong = 180;
                        ((EditText) AdvancedSettingsFragment.this._$_findCachedViewById(C4188R.id.call_freeze_time_active_et)).setText(String.valueOf(180L));
                    } else if (parseLong < 1) {
                        parseLong = 1;
                    }
                    BusinessSetting.INSTANCE.setCallFreezeTime_ms(parseLong * 1000);
                } catch (Exception e) {
                    str2 = AdvancedSettingsFragment.this.TAG;
                    Pdlog.m3274e(str2, "call_freeze_time_active_et : " + Log.getStackTraceString(e));
                }
            }
        });
    }

    private final void hideReachedSwitch(boolean isVisiable) {
        LinearLayout call_reached_root = (LinearLayout) _$_findCachedViewById(C4188R.id.call_reached_root);
        Intrinsics.checkExpressionValueIsNotNull(call_reached_root, "call_reached_root");
        call_reached_root.setVisibility(isVisiable ? 0 : 8);
        View call_reached_root_line = _$_findCachedViewById(C4188R.id.call_reached_root_line);
        Intrinsics.checkExpressionValueIsNotNull(call_reached_root_line, "call_reached_root_line");
        call_reached_root_line.setVisibility(isVisiable ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideReachedTime(boolean isChecked) {
        LinearLayout call_reached_time_root = (LinearLayout) _$_findCachedViewById(C4188R.id.call_reached_time_root);
        Intrinsics.checkExpressionValueIsNotNull(call_reached_time_root, "call_reached_time_root");
        call_reached_time_root.setVisibility(isChecked ? 0 : 8);
        View call_reached_time_line = _$_findCachedViewById(C4188R.id.call_reached_time_line);
        Intrinsics.checkExpressionValueIsNotNull(call_reached_time_line, "call_reached_time_line");
        call_reached_time_line.setVisibility(isChecked ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void closeCallReached(boolean isChecked) {
        hideReachedSwitch(isChecked);
        if (isChecked) {
            return;
        }
        Switch call_reached_switch = (Switch) _$_findCachedViewById(C4188R.id.call_reached_switch);
        Intrinsics.checkExpressionValueIsNotNull(call_reached_switch, "call_reached_switch");
        call_reached_switch.setChecked(false);
        Switch call_reached_time_switch = (Switch) _$_findCachedViewById(C4188R.id.call_reached_time_switch);
        Intrinsics.checkExpressionValueIsNotNull(call_reached_time_switch, "call_reached_time_switch");
        call_reached_time_switch.setChecked(false);
        BeeperUtils.INSTANCE.setCallReachedoConfirmationSwitch(false);
        BeeperUtils.INSTANCE.setAutoCompleteCallTimeSwitch(false);
        BeeperUtils.INSTANCE.setGetAutoCompleteCallTime(120000L);
        ((EditText) _$_findCachedViewById(C4188R.id.call_reached_time_et)).setText(String.valueOf(120L));
        hideReachedTime(false);
    }

    @Override // com.pudutech.bumblebee.presenter.robot_open_task.BeeperBindContract.ViewInterface
    public void showBindCode(BindCodeData current, Exception e) {
        Pdlog.m3273d(this.TAG, "showBindCode : current = " + current + "; e = " + e + "; ");
        if (getContext() == null) {
            return;
        }
        ProgressBar gen_code_progress = (ProgressBar) _$_findCachedViewById(C4188R.id.gen_code_progress);
        Intrinsics.checkExpressionValueIsNotNull(gen_code_progress, "gen_code_progress");
        gen_code_progress.setVisibility(8);
        CardView gen_code_cv = (CardView) _$_findCachedViewById(C4188R.id.gen_code_cv);
        Intrinsics.checkExpressionValueIsNotNull(gen_code_cv, "gen_code_cv");
        gen_code_cv.setEnabled(true);
        if (e == null && current != null) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AdvancedSettingsFragment$showBindCode$1(this, current, null), 2, null);
        } else {
            ToastUtils.show(getContext(), getString(C4188R.string.pdStr7_136), new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap generateQRCodeBitmap(String code) {
        return QRCodeUtils.createQRCodeBitmap(code, 400, 400);
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super.onAttach(context);
        bindPresenter();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        Pdlog.m3273d(this.TAG, "onDetach ");
        unBindPresenter();
        this.mainHandler.removeMessages(0);
        CoroutineScopeKt.cancel$default(this.coroutineScope, null, 1, null);
        TransparentLoadDialog transparentLoadDialog = this.batteryWaitDialog;
        if (transparentLoadDialog == null || !transparentLoadDialog.isShowing()) {
            return;
        }
        transparentLoadDialog.dismiss();
    }

    private final void bindPresenter() {
        getBeeperBindPresenter().replaceView(this);
    }

    private final void unBindPresenter() {
        getBeeperBindPresenter().removeView(this);
    }

    private final void initAboutInfoSwitch() {
        Switch about_info_setting_mode_switch = (Switch) _$_findCachedViewById(C4188R.id.about_info_setting_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(about_info_setting_mode_switch, "about_info_setting_mode_switch");
        about_info_setting_mode_switch.setChecked(Constans.INSTANCE.getSettingAboutInfoSwitch());
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("initAboutInfoSwitch ");
        Switch about_info_setting_mode_switch2 = (Switch) _$_findCachedViewById(C4188R.id.about_info_setting_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(about_info_setting_mode_switch2, "about_info_setting_mode_switch");
        sb.append(about_info_setting_mode_switch2.isChecked());
        Pdlog.m3273d(str, sb.toString());
        ((Switch) _$_findCachedViewById(C4188R.id.about_info_setting_mode_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initAboutInfoSwitch$1
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
                String str2;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                str2 = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str2, "about_info_setting_mode_switch " + z);
                Constans.INSTANCE.setSettingAboutInfoSwitch(z);
                Function0<Unit> onAboutInfoSwitchChange = AdvancedSettingsFragment.this.getOnAboutInfoSwitchChange();
                if (onAboutInfoSwitchChange != null) {
                    onAboutInfoSwitchChange.invoke();
                }
            }
        }, 7, null));
    }

    private final void initPalletDeliverTtsFunction() {
        Switch sw_pallet_tts_function = (Switch) _$_findCachedViewById(C4188R.id.sw_pallet_tts_function);
        Intrinsics.checkExpressionValueIsNotNull(sw_pallet_tts_function, "sw_pallet_tts_function");
        sw_pallet_tts_function.setChecked(Constans.INSTANCE.isOpenPalletDeliverTtsFunction());
        ((Switch) _$_findCachedViewById(C4188R.id.sw_pallet_tts_function)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initPalletDeliverTtsFunction$1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Constans.INSTANCE.setOpenPalletDeliverTtsFunction(z);
            }
        });
    }

    private final void initRecycleModeSwitch() {
        Switch recycle_mode_switch = (Switch) _$_findCachedViewById(C4188R.id.recycle_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(recycle_mode_switch, "recycle_mode_switch");
        recycle_mode_switch.setChecked(Constans.INSTANCE.getRecyclingPlateSwitch());
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("initRecycleModeSwitch ");
        Switch recycle_mode_switch2 = (Switch) _$_findCachedViewById(C4188R.id.recycle_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(recycle_mode_switch2, "recycle_mode_switch");
        sb.append(recycle_mode_switch2.isChecked());
        Pdlog.m3273d(str, sb.toString());
        ((Switch) _$_findCachedViewById(C4188R.id.recycle_mode_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initRecycleModeSwitch$1
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
                String str2;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                str2 = AdvancedSettingsFragment.this.TAG;
                Pdlog.m3273d(str2, "recycle_mode_switch " + z);
                Constans.INSTANCE.setRecyclingPlateSwitch(z);
            }
        }, 7, null));
    }

    private final void initSpeedView() {
        initDeliverSpeed();
        initCruiseSpeed();
        initGoHomeSpeed();
        initEscortingSpeed();
        initRecyclingSpeed();
    }

    private final void initRecyclingSpeed() {
        int size = this.speedList.size();
        String recyclingSpeedLevel = RobotSpeedUtil.INSTANCE.getRecyclingSpeedLevel(RobotContext.INSTANCE.getContext());
        ArrayList<String> arrayList = this.speedList;
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (Float.parseFloat((String) next) == Float.parseFloat(recyclingSpeedLevel)) {
                arrayList2.add(next);
            }
        }
        ArrayList arrayList3 = arrayList2;
        int indexOf = arrayList3.isEmpty() ? 0 : this.speedList.indexOf(arrayList3.get(0));
        Pdlog.m3273d(this.TAG, "recyclingSpeed list " + this.speedList + " level index " + indexOf + " level " + recyclingSpeedLevel);
        IndicatorSeekBar recycle_speed_bar = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.recycle_speed_bar);
        Intrinsics.checkExpressionValueIsNotNull(recycle_speed_bar, "recycle_speed_bar");
        recycle_speed_bar.setTickCount(size);
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.recycle_speed_bar);
        Object[] array = this.speedList.toArray(new String[0]);
        if (array != null) {
            indicatorSeekBar.customTickTexts((String[]) array);
            ((IndicatorSeekBar) _$_findCachedViewById(C4188R.id.recycle_speed_bar)).setIndicatorTextFormat("${TICK_TEXT}");
            ((IndicatorSeekBar) _$_findCachedViewById(C4188R.id.recycle_speed_bar)).setProgress((indexOf / (size - 1)) * 100.0f);
            IndicatorSeekBar recycle_speed_bar2 = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.recycle_speed_bar);
            Intrinsics.checkExpressionValueIsNotNull(recycle_speed_bar2, "recycle_speed_bar");
            recycle_speed_bar2.setOnSeekChangeListener(new VoiceIndicatorSeekBarChangeListener(null, 0, null, null, new AdvancedSettingsFragment$initRecyclingSpeed$1(this, size), 15, null));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final void initGoHomeSpeed() {
        int size = this.speedList.size();
        String goHomeSpeedLevel = RobotSpeedUtil.INSTANCE.getGoHomeSpeedLevel(RobotContext.INSTANCE.getContext());
        ArrayList<String> arrayList = this.speedList;
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (Float.parseFloat((String) next) == Float.parseFloat(goHomeSpeedLevel)) {
                arrayList2.add(next);
            }
        }
        ArrayList arrayList3 = arrayList2;
        int indexOf = arrayList3.isEmpty() ? 0 : this.speedList.indexOf(arrayList3.get(0));
        Pdlog.m3273d(this.TAG, "gohomeSpeed list " + this.speedList + " level index " + indexOf + " level " + goHomeSpeedLevel);
        IndicatorSeekBar go_home_speed_bar = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.go_home_speed_bar);
        Intrinsics.checkExpressionValueIsNotNull(go_home_speed_bar, "go_home_speed_bar");
        go_home_speed_bar.setTickCount(size);
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.go_home_speed_bar);
        Object[] array = this.speedList.toArray(new String[0]);
        if (array != null) {
            indicatorSeekBar.customTickTexts((String[]) array);
            ((IndicatorSeekBar) _$_findCachedViewById(C4188R.id.go_home_speed_bar)).setIndicatorTextFormat("${TICK_TEXT}");
            ((IndicatorSeekBar) _$_findCachedViewById(C4188R.id.go_home_speed_bar)).setProgress((indexOf / (size - 1)) * 100.0f);
            IndicatorSeekBar go_home_speed_bar2 = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.go_home_speed_bar);
            Intrinsics.checkExpressionValueIsNotNull(go_home_speed_bar2, "go_home_speed_bar");
            go_home_speed_bar2.setOnSeekChangeListener(new VoiceIndicatorSeekBarChangeListener(null, 0, null, null, new AdvancedSettingsFragment$initGoHomeSpeed$1(this, size), 15, null));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final void initEscortingSpeed() {
        int size = this.speedList.size();
        String greeterSpeedLevel = RobotSpeedUtil.INSTANCE.getGreeterSpeedLevel(RobotContext.INSTANCE.getContext());
        ArrayList<String> arrayList = this.speedList;
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (Float.parseFloat((String) next) == Float.parseFloat(greeterSpeedLevel)) {
                arrayList2.add(next);
            }
        }
        ArrayList arrayList3 = arrayList2;
        int indexOf = arrayList3.isEmpty() ? 0 : this.speedList.indexOf(arrayList3.get(0));
        Pdlog.m3273d(this.TAG, "gohomeSpeed list " + this.speedList + " level index " + indexOf + " level " + greeterSpeedLevel);
        IndicatorSeekBar escorting_speed_bar = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.escorting_speed_bar);
        Intrinsics.checkExpressionValueIsNotNull(escorting_speed_bar, "escorting_speed_bar");
        escorting_speed_bar.setTickCount(size);
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.escorting_speed_bar);
        Object[] array = this.speedList.toArray(new String[0]);
        if (array != null) {
            indicatorSeekBar.customTickTexts((String[]) array);
            ((IndicatorSeekBar) _$_findCachedViewById(C4188R.id.escorting_speed_bar)).setIndicatorTextFormat("${TICK_TEXT}");
            ((IndicatorSeekBar) _$_findCachedViewById(C4188R.id.escorting_speed_bar)).setProgress((indexOf / (size - 1)) * 100.0f);
            IndicatorSeekBar escorting_speed_bar2 = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.escorting_speed_bar);
            Intrinsics.checkExpressionValueIsNotNull(escorting_speed_bar2, "escorting_speed_bar");
            escorting_speed_bar2.setOnSeekChangeListener(new VoiceIndicatorSeekBarChangeListener(null, 0, null, null, new AdvancedSettingsFragment$initEscortingSpeed$1(this, size), 15, null));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final void initCruiseSpeed() {
        int size = this.speedList.size();
        String cruiseSpeedLevel = RobotSpeedUtil.INSTANCE.getCruiseSpeedLevel(RobotContext.INSTANCE.getContext());
        ArrayList<String> arrayList = this.speedList;
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (Float.parseFloat((String) next) == Float.parseFloat(cruiseSpeedLevel)) {
                arrayList2.add(next);
            }
        }
        ArrayList arrayList3 = arrayList2;
        int indexOf = arrayList3.isEmpty() ? 0 : this.speedList.indexOf(arrayList3.get(0));
        Pdlog.m3273d(this.TAG, "cruiseSpeed list " + this.speedList + " level index " + indexOf + " level " + cruiseSpeedLevel);
        IndicatorSeekBar cruise_speed_bar = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.cruise_speed_bar);
        Intrinsics.checkExpressionValueIsNotNull(cruise_speed_bar, "cruise_speed_bar");
        cruise_speed_bar.setTickCount(size);
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.cruise_speed_bar);
        Object[] array = this.speedList.toArray(new String[0]);
        if (array != null) {
            indicatorSeekBar.customTickTexts((String[]) array);
            ((IndicatorSeekBar) _$_findCachedViewById(C4188R.id.cruise_speed_bar)).setIndicatorTextFormat("${TICK_TEXT}");
            ((IndicatorSeekBar) _$_findCachedViewById(C4188R.id.cruise_speed_bar)).setProgress((indexOf / (size - 1)) * 100.0f);
            IndicatorSeekBar cruise_speed_bar2 = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.cruise_speed_bar);
            Intrinsics.checkExpressionValueIsNotNull(cruise_speed_bar2, "cruise_speed_bar");
            cruise_speed_bar2.setOnSeekChangeListener(new VoiceIndicatorSeekBarChangeListener(null, 0, null, null, new AdvancedSettingsFragment$initCruiseSpeed$1(this, size), 15, null));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final void initDeliverSpeed() {
        int size = this.speedList.size();
        String deliverSpeedLevel = RobotSpeedUtil.INSTANCE.getDeliverSpeedLevel(RobotContext.INSTANCE.getContext());
        ArrayList<String> arrayList = this.speedList;
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (Float.parseFloat((String) next) == Float.parseFloat(deliverSpeedLevel)) {
                arrayList2.add(next);
            }
        }
        ArrayList arrayList3 = arrayList2;
        int indexOf = arrayList3.isEmpty() ? 0 : this.speedList.indexOf(arrayList3.get(0));
        Pdlog.m3273d(this.TAG, "speed list " + this.speedList + " level index " + indexOf + " level " + deliverSpeedLevel);
        IndicatorSeekBar speed_degree = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.speed_degree);
        Intrinsics.checkExpressionValueIsNotNull(speed_degree, "speed_degree");
        speed_degree.setTickCount(size);
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.speed_degree);
        Object[] array = this.speedList.toArray(new String[0]);
        if (array != null) {
            indicatorSeekBar.customTickTexts((String[]) array);
            ((IndicatorSeekBar) _$_findCachedViewById(C4188R.id.speed_degree)).setIndicatorTextFormat("${TICK_TEXT}");
            ((IndicatorSeekBar) _$_findCachedViewById(C4188R.id.speed_degree)).setProgress((indexOf / (size - 1)) * 100.0f);
            IndicatorSeekBar speed_degree2 = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.speed_degree);
            Intrinsics.checkExpressionValueIsNotNull(speed_degree2, "speed_degree");
            speed_degree2.setOnSeekChangeListener(new VoiceIndicatorSeekBarChangeListener(null, 0, null, null, new AdvancedSettingsFragment$initDeliverSpeed$1(this, size), 15, null));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showFastSpeedTip(int type) {
        Pdlog.m3273d(this.TAG, "showFastSpeedTip type = " + type);
        if (type == this.TYPE_CRUISE) {
            if (this.isDialogCruiseShow) {
                return;
            } else {
                this.isDialogCruiseShow = true;
            }
        } else if (type == this.TYPE_DELIVER) {
            if (this.isDialogDeliverShow) {
                return;
            } else {
                this.isDialogDeliverShow = true;
            }
        } else if (type == this.TYPE_GOHOME) {
            if (this.isDialogGoHomeShow) {
                return;
            } else {
                this.isDialogGoHomeShow = true;
            }
        } else if (type == this.TYPE_ESCORTING) {
            if (this.isDialogEscortingShow) {
                return;
            } else {
                this.isDialogEscortingShow = true;
            }
        } else if (type == this.TYPE_RECYCLING) {
            if (this.isDialogEscortingShow) {
                return;
            } else {
                this.isDialogEscortingShow = true;
            }
        }
        if (getContext() == null) {
            return;
        }
        if (this.dialog == null) {
            Context requireContext = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
            this.dialog = new ShowTipMsgDialog(requireContext);
            ShowTipMsgDialog showTipMsgDialog = this.dialog;
            if (showTipMsgDialog == null) {
                Intrinsics.throwNpe();
            }
            String string = getString(C4188R.string.pdStr7_62);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_62)");
            showTipMsgDialog.showTipMsg(string);
        }
        ShowTipMsgDialog showTipMsgDialog2 = this.dialog;
        if (showTipMsgDialog2 == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog2.show();
    }

    private final void initBrake() {
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.brake_bar);
        indicatorSeekBar.setTickCount(this.brakeList.size());
        Object[] array = this.brakeList.toArray(new String[0]);
        if (array != null) {
            indicatorSeekBar.customTickTexts((String[]) array);
            indicatorSeekBar.setProgress(getBrakeCurrentLevel());
            indicatorSeekBar.setOnSeekChangeListener(new VoiceIndicatorSeekBarChangeListener(null, 0, null, null, new Function1<IndicatorSeekBar, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initBrake$$inlined$apply$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(IndicatorSeekBar indicatorSeekBar2) {
                    invoke2(indicatorSeekBar2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IndicatorSeekBar indicatorSeekBar2) {
                    String str;
                    str = AdvancedSettingsFragment.this.TAG;
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("initBrake onStopTrackingTouch seekBar = ");
                    sb.append(indicatorSeekBar2 != null ? Float.valueOf(indicatorSeekBar2.getProgressFloat()) : null);
                    objArr[0] = sb.toString();
                    Pdlog.m3273d(str, objArr);
                    AdvancedSettingsFragment.this.setBrakeLevel(indicatorSeekBar2 != null ? indicatorSeekBar2.getProgressFloat() : 0.0f);
                }
            }, 15, null));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final float getBrakeCurrentLevel() {
        SmoothMode smoothRunAndStopMode = SDK.INSTANCE.getSmoothRunAndStopMode();
        Pdlog.m3273d(this.TAG, "getBrakeCurrentLevel level = " + smoothRunAndStopMode);
        int i = WhenMappings.$EnumSwitchMapping$0[smoothRunAndStopMode.ordinal()];
        if (i == 1) {
            return 0.0f;
        }
        if (i != 2) {
            return i != 3 ? 0.0f : 100.0f;
        }
        return 50.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBrakeLevel(float progress) {
        Pdlog.m3273d(this.TAG, "setBrakeLevel progress = " + progress);
        if (progress == 0.0f) {
            SDK.INSTANCE.setSmoothRunAndStopMode(SmoothMode.NoSmooth);
        } else if (progress == 50.0f) {
            SDK.INSTANCE.setSmoothRunAndStopMode(SmoothMode.LightLoad);
        } else if (progress == 100.0f) {
            SDK.INSTANCE.setSmoothRunAndStopMode(SmoothMode.HeavyLoad);
        }
    }

    private final void initClean() {
        ((ConstraintLayout) _$_findCachedViewById(C4188R.id.clean_cl)).setOnClickListener(new SingleVoiceClickListener(null, 0, new AdvancedSettingsFragment$initClean$1(this), 3, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showCleanWaitDialog() {
        Context it = getContext();
        if (it != null) {
            FileUtil fileUtil = FileUtil.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            FileUtil.cleanFactory$default(fileUtil, it, this.coroutineScope, null, null, 12, null);
        }
    }

    private final void initBatteryGuard() {
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.battery_guard_seek_bar);
        this.batteryGuardList.add(0, indicatorSeekBar.getContext().getString(C4188R.string.advance_battery_guard_level_default));
        setBatterySeekBarNotAvailable(indicatorSeekBar);
        indicatorSeekBar.setTickCount(this.batteryGuardList.size());
        Object[] array = this.batteryGuardList.toArray(new String[0]);
        if (array != null) {
            indicatorSeekBar.customTickTexts((String[]) array);
            indicatorSeekBar.setProgress(getBatteryCurrentLevel());
            indicatorSeekBar.setOnSeekChangeListener(new VoiceIndicatorSeekBarChangeListener(null, 0, null, null, new Function1<IndicatorSeekBar, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initBatteryGuard$$inlined$apply$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(IndicatorSeekBar indicatorSeekBar2) {
                    invoke2(indicatorSeekBar2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IndicatorSeekBar indicatorSeekBar2) {
                    String str;
                    if (indicatorSeekBar2 != null) {
                        AdvancedSettingsFragment.this.setBatteryCurrentLevel(indicatorSeekBar2.getProgressFloat());
                    }
                    str = AdvancedSettingsFragment.this.TAG;
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("initBatteryGuard onStopTrackingTouch seekBar = ");
                    sb.append(indicatorSeekBar2 != null ? Float.valueOf(indicatorSeekBar2.getProgressFloat()) : null);
                    objArr[0] = sb.toString();
                    Pdlog.m3273d(str, objArr);
                }
            }, 15, null));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBatterySeekBarNotAvailable(IndicatorSeekBar seekbar) {
        Context context;
        int powerPercent = CoreDevices.INSTANCE.getBattery().getPowerPercent();
        Pdlog.m3273d(this.TAG, "initBatteryGuard power:" + powerPercent);
        if (powerPercent > this.BATTERY_GUARD_MIN || (context = getContext()) == null) {
            return;
        }
        if (seekbar != null) {
            seekbar.thumbColorStateList(ColorStateList.valueOf(context.getColor(C4188R.color.c_a8a8a8)));
        }
        if (seekbar != null) {
            seekbar.setUserSeekAble(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float getBatteryCurrentLevel() {
        float f;
        int size;
        if (CoreDevices.INSTANCE.getBattery().getBatteryLevel() == 5) {
            return 0.0f;
        }
        if (CoreDevices.INSTANCE.getBattery().getBatteryLevel() == 10) {
            f = 1.0f;
            size = this.batteryGuardList.size();
        } else if (CoreDevices.INSTANCE.getBattery().getBatteryLevel() == 15) {
            f = 2.0f;
            size = this.batteryGuardList.size();
        } else {
            if (CoreDevices.INSTANCE.getBattery().getBatteryLevel() != 20) {
                return 0.0f;
            }
            f = 3.0f;
            size = this.batteryGuardList.size();
        }
        return (f / (size - 1)) * 100.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBatteryCurrentLevel(float value) {
        int transformBatteryLevel = transformBatteryLevel(value);
        Pdlog.m3273d(this.TAG, "setBatteryCurrentLevel level:" + transformBatteryLevel + ",value:" + value + ' ');
        if (transformBatteryLevel > CoreDevices.INSTANCE.getBattery().getPowerPercent()) {
            Context context = getContext();
            if (context != null) {
                ToastUtils.show(context, C4188R.string.advance_battery_guard_warn);
                ((IndicatorSeekBar) _$_findCachedViewById(C4188R.id.battery_guard_seek_bar)).setProgress(getBatteryCurrentLevel());
                return;
            }
            return;
        }
        this.batteryWaitDialog = showBatteryWaitDialog();
        CoreDevices.INSTANCE.getBattery().setBatteryLevel(transformBatteryLevel, this.batteryLevelListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int transformBatteryLevel(float value) {
        return MathKt.roundToInt(((((this.batteryGuardList.size() - 1) * value) / 100.0f) * 5.0f) + 5);
    }

    private final TransparentLoadDialog showBatteryWaitDialog() {
        Context it = getContext();
        if (it == null) {
            return null;
        }
        Intrinsics.checkExpressionValueIsNotNull(it, "it");
        TransparentLoadDialog transparentLoadDialog = new TransparentLoadDialog(it);
        String string = getString(C4188R.string.loading_tip);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.loading_tip)");
        transparentLoadDialog.setContent(string);
        transparentLoadDialog.show();
        return transparentLoadDialog;
    }

    private final void initPassageCapacity() {
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.passage_capacity_seek_bar);
        indicatorSeekBar.setTickCount(this.passageCapacityList.size());
        Object[] array = this.passageCapacityList.toArray(new String[0]);
        if (array != null) {
            indicatorSeekBar.customTickTexts((String[]) array);
            indicatorSeekBar.setProgress(getPassageCapacityLevel());
            indicatorSeekBar.setOnSeekChangeListener(new VoiceIndicatorSeekBarChangeListener(null, 0, null, null, new Function1<IndicatorSeekBar, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.AdvancedSettingsFragment$initPassageCapacity$$inlined$apply$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(IndicatorSeekBar indicatorSeekBar2) {
                    invoke2(indicatorSeekBar2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IndicatorSeekBar indicatorSeekBar2) {
                    String str;
                    if (indicatorSeekBar2 != null) {
                        AdvancedSettingsFragment.this.setPassageCapacityLevel(indicatorSeekBar2.getProgressFloat());
                    }
                    str = AdvancedSettingsFragment.this.TAG;
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("initPassageCapacity onStopTrackingTouch seekBar = ");
                    sb.append(indicatorSeekBar2 != null ? Float.valueOf(indicatorSeekBar2.getProgressFloat()) : null);
                    objArr[0] = sb.toString();
                    Pdlog.m3273d(str, objArr);
                }
            }, 15, null));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final float getPassageCapacityLevel() {
        int aroundLevel = RobotConfig.INSTANCE.getAroundLevel();
        if (aroundLevel != 1) {
            return aroundLevel != 2 ? 0.0f : 100.0f;
        }
        return 50.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setPassageCapacityLevel(float value) {
        if (value == 50.0f) {
            RobotConfig.INSTANCE.setAroundLevel(1);
        } else if (value == 100.0f) {
            RobotConfig.INSTANCE.setAroundLevel(2);
        } else {
            RobotConfig.INSTANCE.setAroundLevel(0);
        }
    }
}
