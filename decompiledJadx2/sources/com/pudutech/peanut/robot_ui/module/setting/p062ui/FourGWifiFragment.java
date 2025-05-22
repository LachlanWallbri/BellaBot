package com.pudutech.peanut.robot_ui.module.setting.p062ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import com.pudutech.base.Pdlog;
import com.pudutech.location.C4766R;
import com.pudutech.location.FlowCardManager;
import com.pudutech.location.net.FlowCardStatusRes;
import com.pudutech.location.net.NetWorkChangeEvent;
import com.pudutech.location.utils.FlowCardInfo;
import com.pudutech.location.utils.NetStatusUtil;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.FourGWifiFragment;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FourGWifiFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\b\b\u0016\u0018\u0000 Y2\u00020\u0001:\u0004YZ[\\B\u0005¢\u0006\u0002\u0010\u0002J\b\u00109\u001a\u00020:H\u0016J\b\u0010;\u001a\u00020:H\u0016J\b\u0010<\u001a\u00020:H\u0016J\u001c\u0010=\u001a\u0004\u0018\u00010>2\u0006\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010BH\u0016J\u0010\u0010C\u001a\u00020:2\u0006\u0010D\u001a\u00020\fH\u0016J\b\u0010E\u001a\u00020:H\u0016J\u0010\u0010F\u001a\u00020:2\u0006\u0010D\u001a\u00020\fH\u0016J\u0010\u0010G\u001a\u00020:2\u0006\u0010H\u001a\u00020>H\u0016J&\u0010I\u001a\u0004\u0018\u00010>2\u0006\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010B2\b\u0010J\u001a\u0004\u0018\u00010KH\u0016J\b\u0010L\u001a\u00020:H\u0016J\u0010\u0010M\u001a\u00020:2\u0006\u0010N\u001a\u00020\u001cH\u0016J\u001a\u0010O\u001a\u00020:2\u0006\u0010H\u001a\u00020>2\b\u0010J\u001a\u0004\u0018\u00010KH\u0016J\b\u0010P\u001a\u00020:H\u0004J\u0010\u0010Q\u001a\u00020:2\u0006\u0010R\u001a\u00020\u001cH\u0016J\u0010\u0010S\u001a\u00020:2\u0006\u0010T\u001a\u00020UH\u0016J\u000e\u0010V\u001a\u00020:2\u0006\u0010W\u001a\u00020.J\b\u0010X\u001a\u00020:H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\u001cX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001e\"\u0004\b#\u0010 R\u001a\u0010$\u001a\u00020\u001cX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001e\"\u0004\b&\u0010 R\u001a\u0010'\u001a\u00020\u001cX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u001e\"\u0004\b)\u0010 R\u001a\u0010*\u001a\u00020\u001cX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u001e\"\u0004\b,\u0010 R\u001c\u0010-\u001a\u0004\u0018\u00010.X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R \u00103\u001a\b\u0018\u000104R\u00020\u0000X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108¨\u0006]"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/FourGWifiFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "mActivity", "Landroid/app/Activity;", "getMActivity", "()Landroid/app/Activity;", "setMActivity", "(Landroid/app/Activity;)V", "mContext", "Landroid/content/Context;", "getMContext", "()Landroid/content/Context;", "setMContext", "(Landroid/content/Context;)V", "mCount", "", "mDefRunnable", "Ljava/lang/Runnable;", "mFlowCardManager", "Lcom/pudutech/location/FlowCardManager;", "getMFlowCardManager", "()Lcom/pudutech/location/FlowCardManager;", "setMFlowCardManager", "(Lcom/pudutech/location/FlowCardManager;)V", "mIsActived", "", "getMIsActived", "()Z", "setMIsActived", "(Z)V", "mIsFourG", "getMIsFourG", "setMIsFourG", "mIsNoSkipWifi", "getMIsNoSkipWifi", "setMIsNoSkipWifi", "mIsShowActivite", "getMIsShowActivite", "setMIsShowActivite", "mIsgoActivate", "getMIsgoActivate", "setMIsgoActivate", "mOnPageListener", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/FourGWifiFragment$OnFourGPageListener;", "getMOnPageListener", "()Lcom/pudutech/peanut/robot_ui/module/setting/ui/FourGWifiFragment$OnFourGPageListener;", "setMOnPageListener", "(Lcom/pudutech/peanut/robot_ui/module/setting/ui/FourGWifiFragment$OnFourGPageListener;)V", "netWorkChangeReceiver", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/FourGWifiFragment$NetWorkChangeReceiver;", "getNetWorkChangeReceiver", "()Lcom/pudutech/peanut/robot_ui/module/setting/ui/FourGWifiFragment$NetWorkChangeReceiver;", "setNetWorkChangeReceiver", "(Lcom/pudutech/peanut/robot_ui/module/setting/ui/FourGWifiFragment$NetWorkChangeReceiver;)V", "activeSimCard", "", "creatFlowManager", "detachNetWork", "getLayoutView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "initNetWork", "context", "initView", "onAttach", "onClickEvent", "view", "onCreateView", "savedInstanceState", "Landroid/os/Bundle;", "onDetach", "onNetStatus", "b", "onViewCreated", "setActiveShow", "setActiviteStatus", "activite", "setCardStatus", "delay", "", "setOnPageListener", "onPageListener", "translateWiFiPage", "Companion", "NetWorkChangeReceiver", "OnFourGPageListener", "OnLazyVoiceClickListener", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public class FourGWifiFragment extends Fragment {
    private HashMap _$_findViewCache;
    private Activity mActivity;
    private Context mContext;
    private int mCount;
    private FlowCardManager mFlowCardManager;
    private boolean mIsActived;
    private boolean mIsFourG;
    private boolean mIsNoSkipWifi;
    private boolean mIsgoActivate;
    private OnFourGPageListener mOnPageListener;
    private NetWorkChangeReceiver netWorkChangeReceiver;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String KEY_FOURG = KEY_FOURG;
    private static final String KEY_FOURG = KEY_FOURG;
    private static final String KEY_IS_NOSKIP_WIFI = KEY_IS_NOSKIP_WIFI;
    private static final String KEY_IS_NOSKIP_WIFI = KEY_IS_NOSKIP_WIFI;
    private static final String KEY_IS_SHOW_ACTIVITE = KEY_IS_SHOW_ACTIVITE;
    private static final String KEY_IS_SHOW_ACTIVITE = KEY_IS_SHOW_ACTIVITE;
    private final String TAG = "FourGWifiFragment";
    private boolean mIsShowActivite = true;
    private Runnable mDefRunnable = new Runnable() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.FourGWifiFragment$mDefRunnable$1
        @Override // java.lang.Runnable
        public void run() {
            FourGWifiFragment.this.mCount = 0;
        }
    };

    /* compiled from: FourGWifiFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0005H&J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0005H\u0016J\b\u0010\f\u001a\u00020\u0003H\u0016J\b\u0010\r\u001a\u00020\u0003H\u0016¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/FourGWifiFragment$OnFourGPageListener;", "", "on4GActivateResult", "", "suceesss", "", "on4GActivateStart", "on4GDataEnable", "isChecked", "onClickEvent", "view", "Landroid/view/View;", "onPlayClick", "onSkipWifi", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public interface OnFourGPageListener {

        /* compiled from: FourGWifiFragment.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes6.dex */
        public static final class DefaultImpls {
            public static void on4GActivateStart(OnFourGPageListener onFourGPageListener) {
            }

            public static void onClickEvent(OnFourGPageListener onFourGPageListener, View view, boolean z) {
                Intrinsics.checkParameterIsNotNull(view, "view");
            }

            public static void onPlayClick(OnFourGPageListener onFourGPageListener) {
            }

            public static void onSkipWifi(OnFourGPageListener onFourGPageListener) {
            }
        }

        void on4GActivateResult(boolean suceesss);

        void on4GActivateStart();

        void on4GDataEnable(boolean isChecked);

        void onClickEvent(View view, boolean isChecked);

        void onPlayClick();

        void onSkipWifi();
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

    public void onClickEvent(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final Context getMContext() {
        return this.mContext;
    }

    protected final void setMContext(Context context) {
        this.mContext = context;
    }

    protected final Activity getMActivity() {
        return this.mActivity;
    }

    protected final void setMActivity(Activity activity) {
        this.mActivity = activity;
    }

    protected final NetWorkChangeReceiver getNetWorkChangeReceiver() {
        return this.netWorkChangeReceiver;
    }

    protected final void setNetWorkChangeReceiver(NetWorkChangeReceiver netWorkChangeReceiver) {
        this.netWorkChangeReceiver = netWorkChangeReceiver;
    }

    protected final FlowCardManager getMFlowCardManager() {
        return this.mFlowCardManager;
    }

    protected final void setMFlowCardManager(FlowCardManager flowCardManager) {
        this.mFlowCardManager = flowCardManager;
    }

    protected final boolean getMIsFourG() {
        return this.mIsFourG;
    }

    protected final void setMIsFourG(boolean z) {
        this.mIsFourG = z;
    }

    public final boolean getMIsgoActivate() {
        return this.mIsgoActivate;
    }

    protected final void setMIsgoActivate(boolean z) {
        this.mIsgoActivate = z;
    }

    public final boolean getMIsActived() {
        return this.mIsActived;
    }

    public final void setMIsActived(boolean z) {
        this.mIsActived = z;
    }

    public final boolean getMIsNoSkipWifi() {
        return this.mIsNoSkipWifi;
    }

    protected final void setMIsNoSkipWifi(boolean z) {
        this.mIsNoSkipWifi = z;
    }

    public final boolean getMIsShowActivite() {
        return this.mIsShowActivite;
    }

    public final void setMIsShowActivite(boolean z) {
        this.mIsShowActivite = z;
    }

    /* compiled from: FourGWifiFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/FourGWifiFragment$Companion;", "", "()V", "KEY_FOURG", "", "KEY_IS_NOSKIP_WIFI", "KEY_IS_SHOW_ACTIVITE", "newInstance", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/FourGWifiFragment;", "isFourG", "", "isNoSkipWifi", "isShowActivite", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ FourGWifiFragment newInstance$default(Companion companion, boolean z, boolean z2, boolean z3, int i, Object obj) {
            if ((i & 2) != 0) {
                z2 = false;
            }
            if ((i & 4) != 0) {
                z3 = true;
            }
            return companion.newInstance(z, z2, z3);
        }

        public final FourGWifiFragment newInstance(boolean isFourG, boolean isNoSkipWifi, boolean isShowActivite) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(FourGWifiFragment.KEY_FOURG, isFourG);
            bundle.putBoolean(FourGWifiFragment.KEY_IS_NOSKIP_WIFI, isNoSkipWifi);
            bundle.putBoolean(FourGWifiFragment.KEY_IS_SHOW_ACTIVITE, isShowActivite);
            FourGWifiFragment fourGWifiFragment = new FourGWifiFragment();
            fourGWifiFragment.setArguments(bundle);
            return fourGWifiFragment;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return getLayoutView(inflater, container);
    }

    public View getLayoutView(LayoutInflater inflater, ViewGroup container) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C4766R.layout.fragment_wifi_4g, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    public void initView() {
        FlowCardInfo flowCardInfo = FlowCardInfo.INSTANCE;
        Context context = this.mContext;
        if (context == null) {
            Intrinsics.throwNpe();
        }
        setActiviteStatus(flowCardInfo.get4GActivateStatus(context));
        if (this.mContext == null) {
            this.mContext = requireContext();
        }
        if (this.mActivity == null) {
            this.mActivity = requireActivity();
        }
        ((ConstraintLayout) _$_findCachedViewById(C4766R.id.constraint_wifi_setting)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.FourGWifiFragment$initView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super();
            }

            @Override // com.pudutech.peanut.robot_ui.module.setting.ui.FourGWifiFragment.OnLazyVoiceClickListener
            public void onSingleClick() {
                FourGWifiFragment.OnFourGPageListener mOnPageListener = FourGWifiFragment.this.getMOnPageListener();
                if (mOnPageListener != null) {
                    mOnPageListener.onSkipWifi();
                }
                if (FourGWifiFragment.this.getMIsNoSkipWifi()) {
                    return;
                }
                FourGWifiFragment.this.translateWiFiPage();
            }
        });
        onNetStatus(NetStatusUtil.isConnected(this.mContext));
        ((Switch) _$_findCachedViewById(C4766R.id.cellular_switch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.FourGWifiFragment$initView$2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton buttonView, boolean z) {
                Intrinsics.checkExpressionValueIsNotNull(buttonView, "buttonView");
                if (buttonView.isPressed()) {
                    try {
                        FlowCardInfo flowCardInfo2 = FlowCardInfo.INSTANCE;
                        Context mContext = FourGWifiFragment.this.getMContext();
                        if (mContext == null) {
                            Intrinsics.throwNpe();
                        }
                        flowCardInfo2.set4GDataEnable(mContext, z);
                        NetStatusUtil.enableAggressiveHandover(FourGWifiFragment.this.getMContext(), z ? 1 : 0);
                        FourGWifiFragment.OnFourGPageListener mOnPageListener = FourGWifiFragment.this.getMOnPageListener();
                        if (mOnPageListener != null) {
                            mOnPageListener.on4GDataEnable(z);
                        }
                        FourGWifiFragment.OnFourGPageListener mOnPageListener2 = FourGWifiFragment.this.getMOnPageListener();
                        if (mOnPageListener2 != null) {
                            mOnPageListener2.onPlayClick();
                        }
                        FourGWifiFragment.OnFourGPageListener mOnPageListener3 = FourGWifiFragment.this.getMOnPageListener();
                        if (mOnPageListener3 != null) {
                            mOnPageListener3.onClickEvent(buttonView, z);
                        }
                    } catch (Exception e) {
                        String str = FourGWifiFragment.this.TAG;
                        e.printStackTrace();
                        Pdlog.m3273d(str, "cellular_switch is = " + e.getMessage(), Unit.INSTANCE);
                    }
                }
            }
        });
        if (!this.mIsFourG) {
            ConstraintLayout mConstraintCellular = (ConstraintLayout) _$_findCachedViewById(C4766R.id.mConstraintCellular);
            Intrinsics.checkExpressionValueIsNotNull(mConstraintCellular, "mConstraintCellular");
            mConstraintCellular.setVisibility(8);
            ConstraintLayout m_active_sim = (ConstraintLayout) _$_findCachedViewById(C4766R.id.m_active_sim);
            Intrinsics.checkExpressionValueIsNotNull(m_active_sim, "m_active_sim");
            m_active_sim.setVisibility(8);
        } else {
            ConstraintLayout mConstraintCellular2 = (ConstraintLayout) _$_findCachedViewById(C4766R.id.mConstraintCellular);
            Intrinsics.checkExpressionValueIsNotNull(mConstraintCellular2, "mConstraintCellular");
            mConstraintCellular2.setVisibility(0);
            if (this.mIsShowActivite) {
                ConstraintLayout m_active_sim2 = (ConstraintLayout) _$_findCachedViewById(C4766R.id.m_active_sim);
                Intrinsics.checkExpressionValueIsNotNull(m_active_sim2, "m_active_sim");
                m_active_sim2.setVisibility(0);
            }
        }
        FlowCardInfo flowCardInfo2 = FlowCardInfo.INSTANCE;
        Context context2 = this.mContext;
        if (context2 == null) {
            Intrinsics.throwNpe();
        }
        boolean z = flowCardInfo2.get4GDataEnable(context2);
        Switch cellular_switch = (Switch) _$_findCachedViewById(C4766R.id.cellular_switch);
        Intrinsics.checkExpressionValueIsNotNull(cellular_switch, "cellular_switch");
        cellular_switch.setChecked(z);
        if (z) {
            NetStatusUtil.enableAggressiveHandover(this.mContext, 1);
        }
        ((TextView) _$_findCachedViewById(C4766R.id.cellular_tv)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.FourGWifiFragment$initView$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Runnable runnable;
                int i;
                int i2;
                Runnable runnable2;
                int i3;
                ConstraintLayout m_active_sim3 = (ConstraintLayout) FourGWifiFragment.this._$_findCachedViewById(C4766R.id.m_active_sim);
                Intrinsics.checkExpressionValueIsNotNull(m_active_sim3, "m_active_sim");
                if (m_active_sim3.isShown()) {
                    return;
                }
                TextView textView = (TextView) FourGWifiFragment.this._$_findCachedViewById(C4766R.id.cellular_tv);
                runnable = FourGWifiFragment.this.mDefRunnable;
                textView.removeCallbacks(runnable);
                FourGWifiFragment fourGWifiFragment = FourGWifiFragment.this;
                i = fourGWifiFragment.mCount;
                fourGWifiFragment.mCount = i + 1;
                i2 = FourGWifiFragment.this.mCount;
                if (i2 >= 5) {
                    FourGWifiFragment.this.setMIsShowActivite(true);
                    FourGWifiFragment.this.setActiveShow();
                    String str = FourGWifiFragment.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("setActiveShow = ");
                    sb.append(FourGWifiFragment.this.getMIsShowActivite());
                    sb.append(" --mCount =");
                    i3 = FourGWifiFragment.this.mCount;
                    sb.append(i3);
                    Pdlog.m3273d(str, sb.toString());
                }
                TextView textView2 = (TextView) FourGWifiFragment.this._$_findCachedViewById(C4766R.id.cellular_tv);
                runnable2 = FourGWifiFragment.this.mDefRunnable;
                textView2.postDelayed(runnable2, 1000L);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(C4766R.id.m_active_sim)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.FourGWifiFragment$initView$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super();
            }

            @Override // com.pudutech.peanut.robot_ui.module.setting.ui.FourGWifiFragment.OnLazyVoiceClickListener
            public void onSingleClick() {
                if (FourGWifiFragment.this.getMIsActived()) {
                    Pdlog.m3273d(FourGWifiFragment.this.TAG, "m_active_sim 已经点击");
                    return;
                }
                FourGWifiFragment.this.setMIsActived(true);
                if (!FourGWifiFragment.this.getMIsgoActivate()) {
                    Pdlog.m3273d(FourGWifiFragment.this.TAG, "card activited");
                } else {
                    FourGWifiFragment.this.activeSimCard();
                }
            }
        });
        setActiveShow();
    }

    public final void setActiveShow() {
        if (this.mIsFourG && this.mIsShowActivite) {
            ConstraintLayout m_active_sim = (ConstraintLayout) _$_findCachedViewById(C4766R.id.m_active_sim);
            Intrinsics.checkExpressionValueIsNotNull(m_active_sim, "m_active_sim");
            m_active_sim.setVisibility(0);
            setCardStatus(0L);
        }
    }

    public void setCardStatus(long delay) {
        if (this.mIsShowActivite) {
            creatFlowManager();
            FlowCardManager flowCardManager = this.mFlowCardManager;
            if (flowCardManager != null) {
                flowCardManager.getStatusFlowCardSingle(delay, new Function1<FlowCardStatusRes, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.FourGWifiFragment$setCardStatus$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(FlowCardStatusRes flowCardStatusRes) {
                        invoke2(flowCardStatusRes);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(FlowCardStatusRes flowCardStatusRes) {
                        if ((flowCardStatusRes != null ? flowCardStatusRes.getData() : null) == null) {
                            FourGWifiFragment fourGWifiFragment = FourGWifiFragment.this;
                            FlowCardInfo flowCardInfo = FlowCardInfo.INSTANCE;
                            Context mContext = FourGWifiFragment.this.getMContext();
                            if (mContext == null) {
                                Intrinsics.throwNpe();
                            }
                            fourGWifiFragment.setActiviteStatus(flowCardInfo.get4GActivateStatus(mContext));
                            return;
                        }
                        if (FlowCardInfo.INSTANCE.getCanActivate(flowCardStatusRes.getData().getStatus())) {
                            FourGWifiFragment.this.setActiviteStatus(false);
                            FlowCardInfo flowCardInfo2 = FlowCardInfo.INSTANCE;
                            Context mContext2 = FourGWifiFragment.this.getMContext();
                            if (mContext2 == null) {
                                Intrinsics.throwNpe();
                            }
                            flowCardInfo2.set4GActivateStatus(mContext2, false);
                            return;
                        }
                        FourGWifiFragment.this.setActiviteStatus(true);
                        FlowCardInfo flowCardInfo3 = FlowCardInfo.INSTANCE;
                        Context mContext3 = FourGWifiFragment.this.getMContext();
                        if (mContext3 == null) {
                            Intrinsics.throwNpe();
                        }
                        flowCardInfo3.set4GActivateStatus(mContext3, true);
                    }
                });
            }
        }
    }

    public void setActiviteStatus(boolean activite) {
        if (((FrameLayout) _$_findCachedViewById(C4766R.id.m_active_sim_ft)) == null) {
            return;
        }
        this.mIsgoActivate = !activite;
        Pdlog.m3273d(this.TAG, "activite = " + activite);
        if (activite) {
            FrameLayout m_active_sim_ft = (FrameLayout) _$_findCachedViewById(C4766R.id.m_active_sim_ft);
            Intrinsics.checkExpressionValueIsNotNull(m_active_sim_ft, "m_active_sim_ft");
            m_active_sim_ft.setSelected(false);
            TextView m_active_sim_tv = (TextView) _$_findCachedViewById(C4766R.id.m_active_sim_tv);
            Intrinsics.checkExpressionValueIsNotNull(m_active_sim_tv, "m_active_sim_tv");
            m_active_sim_tv.setText(getString(C4766R.string.activated));
            TextView textView = (TextView) _$_findCachedViewById(C4766R.id.m_active_sim_tv);
            if (textView != null) {
                textView.setTextColor(getResources().getColor(C4766R.color.color_636363));
                return;
            }
            return;
        }
        FrameLayout m_active_sim_ft2 = (FrameLayout) _$_findCachedViewById(C4766R.id.m_active_sim_ft);
        Intrinsics.checkExpressionValueIsNotNull(m_active_sim_ft2, "m_active_sim_ft");
        m_active_sim_ft2.setSelected(true);
        TextView m_active_sim_tv2 = (TextView) _$_findCachedViewById(C4766R.id.m_active_sim_tv);
        Intrinsics.checkExpressionValueIsNotNull(m_active_sim_tv2, "m_active_sim_tv");
        m_active_sim_tv2.setText(getString(C4766R.string.active));
        TextView textView2 = (TextView) _$_findCachedViewById(C4766R.id.m_active_sim_tv);
        if (textView2 != null) {
            textView2.setTextColor(getResources().getColor(C4766R.color.color_1cc33d));
        }
    }

    public void activeSimCard() {
        OnFourGPageListener onFourGPageListener = this.mOnPageListener;
        if (onFourGPageListener != null) {
            onFourGPageListener.on4GActivateStart();
        }
        creatFlowManager();
        FlowCardManager flowCardManager = this.mFlowCardManager;
        if (flowCardManager != null) {
            flowCardManager.activateFlowCardSingle(new Function1<Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.FourGWifiFragment$activeSimCard$1
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
                    if (z) {
                        FlowCardInfo flowCardInfo = FlowCardInfo.INSTANCE;
                        Context mContext = FourGWifiFragment.this.getMContext();
                        if (mContext == null) {
                            Intrinsics.throwNpe();
                        }
                        if (!flowCardInfo.get4GDataEnable(mContext)) {
                            FlowCardInfo flowCardInfo2 = FlowCardInfo.INSTANCE;
                            Context mContext2 = FourGWifiFragment.this.getMContext();
                            if (mContext2 == null) {
                                Intrinsics.throwNpe();
                            }
                            flowCardInfo2.set4GDataEnable(mContext2, true);
                            NetStatusUtil.enableAggressiveHandover(FourGWifiFragment.this.getMContext(), 1);
                            Switch cellular_switch = (Switch) FourGWifiFragment.this._$_findCachedViewById(C4766R.id.cellular_switch);
                            Intrinsics.checkExpressionValueIsNotNull(cellular_switch, "cellular_switch");
                            cellular_switch.setChecked(true);
                            FourGWifiFragment.OnFourGPageListener mOnPageListener = FourGWifiFragment.this.getMOnPageListener();
                            if (mOnPageListener != null) {
                                mOnPageListener.on4GDataEnable(true);
                            }
                            Pdlog.m3273d(FourGWifiFragment.this.TAG, "activeSimCard 激活之后，默认打开移动数据");
                        }
                    }
                    FourGWifiFragment.this.setActiviteStatus(z);
                    FlowCardInfo flowCardInfo3 = FlowCardInfo.INSTANCE;
                    Context mContext3 = FourGWifiFragment.this.getMContext();
                    if (mContext3 == null) {
                        Intrinsics.throwNpe();
                    }
                    flowCardInfo3.set4GActivateStatus(mContext3, z);
                    FourGWifiFragment.OnFourGPageListener mOnPageListener2 = FourGWifiFragment.this.getMOnPageListener();
                    if (mOnPageListener2 != null) {
                        mOnPageListener2.on4GActivateResult(z);
                    }
                    FourGWifiFragment.this.setMIsActived(false);
                }
            });
        }
    }

    public void creatFlowManager() {
        Activity activity;
        if (this.mFlowCardManager != null || (activity = this.mActivity) == null) {
            return;
        }
        this.mFlowCardManager = new FlowCardManager(activity);
    }

    public void onNetStatus(boolean b) {
        if (this.mContext == null) {
            return;
        }
        if (b) {
            WifiInfo wifiInfo = NetStatusUtil.getWifiInfo(requireContext());
            Intrinsics.checkExpressionValueIsNotNull(wifiInfo, "NetStatusUtil.getWifiInfo(requireContext())");
            String ssid = wifiInfo.getSSID();
            String str = ssid;
            if (str == null || str.length() == 0) {
                TextView textView = (TextView) _$_findCachedViewById(C4766R.id.tv_connect_wifi);
                if (textView != null) {
                    textView.setTextColor(getResources().getColor(C4766R.color.color_222222));
                }
                TextView textView2 = (TextView) _$_findCachedViewById(C4766R.id.tv_connect_wifi);
                if (textView2 != null) {
                    textView2.setText(getString(C4766R.string.bluetooth_unconnect));
                    return;
                }
                return;
            }
            TextView textView3 = (TextView) _$_findCachedViewById(C4766R.id.tv_connect_wifi);
            if (textView3 != null) {
                textView3.setTextColor(getResources().getColor(C4766R.color.color_0072FF));
            }
            TextView textView4 = (TextView) _$_findCachedViewById(C4766R.id.tv_connect_wifi);
            if (textView4 != null) {
                int length = ssid.length() - 1;
                if (ssid == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String substring = ssid.substring(1, length);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                textView4.setText(substring);
                return;
            }
            return;
        }
        TextView textView5 = (TextView) _$_findCachedViewById(C4766R.id.tv_connect_wifi);
        if (textView5 != null) {
            textView5.setTextColor(getResources().getColor(C4766R.color.color_222222));
        }
        TextView textView6 = (TextView) _$_findCachedViewById(C4766R.id.tv_connect_wifi);
        if (textView6 != null) {
            textView6.setText(getString(C4766R.string.bluetooth_unconnect));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super.onAttach(context);
        initNetWork(context);
    }

    public void initNetWork(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.mContext = context;
        if (context instanceof Activity) {
            this.mActivity = (Activity) context;
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mIsFourG = arguments.getBoolean(KEY_FOURG, false);
            this.mIsNoSkipWifi = arguments.getBoolean(KEY_IS_NOSKIP_WIFI, false);
            this.mIsShowActivite = arguments.getBoolean(KEY_IS_SHOW_ACTIVITE, false);
            Pdlog.m3273d(this.TAG, "initNetWork mIsFourG =" + this.mIsFourG + " mIsNoSkipWifi =" + this.mIsNoSkipWifi + " mIsShowActivite =" + this.mIsShowActivite);
        }
        if (this.netWorkChangeReceiver == null) {
            this.netWorkChangeReceiver = new NetWorkChangeReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        try {
            context.registerReceiver(this.netWorkChangeReceiver, intentFilter);
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, Log.getStackTraceString(e));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        detachNetWork();
    }

    public void detachNetWork() {
        Context context;
        try {
            NetWorkChangeReceiver netWorkChangeReceiver = this.netWorkChangeReceiver;
            if (netWorkChangeReceiver != null && (context = getContext()) != null) {
                context.unregisterReceiver(netWorkChangeReceiver);
            }
            FlowCardManager flowCardManager = this.mFlowCardManager;
            if (flowCardManager != null) {
                flowCardManager.cancelNetTask();
            }
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, Log.getStackTraceString(e));
        }
    }

    public void translateWiFiPage() {
        startActivity(new Intent("android.settings.WIFI_SETTINGS"));
    }

    /* compiled from: FourGWifiFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0096\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/FourGWifiFragment$NetWorkChangeReceiver;", "Lcom/pudutech/location/net/NetWorkChangeEvent;", "(Lcom/pudutech/peanut/robot_ui/module/setting/ui/FourGWifiFragment;)V", "onNetworkChange", "", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public class NetWorkChangeReceiver extends NetWorkChangeEvent {
        public NetWorkChangeReceiver() {
        }

        @Override // com.pudutech.location.net.NetWorkChangeEvent
        public void onNetworkChange() {
            if (NetStatusUtil.isConnected(FourGWifiFragment.this.getMContext()) && NetStatusUtil.isWifi(FourGWifiFragment.this.getMContext())) {
                FourGWifiFragment.this.onNetStatus(true);
            } else {
                FourGWifiFragment.this.onNetStatus(false);
            }
            FourGWifiFragment.this.setCardStatus(1500L);
            Pdlog.m3273d(NetWorkChangeEvent.TAG, "NetWorkChangeReceiver");
        }
    }

    /* compiled from: FourGWifiFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0092\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\tH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/FourGWifiFragment$OnLazyVoiceClickListener;", "Landroid/view/View$OnClickListener;", "(Lcom/pudutech/peanut/robot_ui/module/setting/ui/FourGWifiFragment;)V", "lastViewId", "", "mLastClickTime", "", "timeInterval_ms", "onClick", "", "v", "Landroid/view/View;", "onSingleClick", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public class OnLazyVoiceClickListener implements View.OnClickListener {
        private long mLastClickTime;
        private final long timeInterval_ms = 600;
        private int lastViewId = -1;

        public void onSingleClick() {
        }

        public OnLazyVoiceClickListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View v) {
            Intrinsics.checkParameterIsNotNull(v, "v");
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.mLastClickTime <= this.timeInterval_ms && this.lastViewId == v.getId()) {
                Pdlog.m3274e(FourGWifiFragment.this.TAG, "快速重复点击了----");
                return;
            }
            onSingleClick();
            FourGWifiFragment.this.onClickEvent(v);
            this.mLastClickTime = currentTimeMillis;
            this.lastViewId = v.getId();
            OnFourGPageListener mOnPageListener = FourGWifiFragment.this.getMOnPageListener();
            if (mOnPageListener != null) {
                mOnPageListener.onClickEvent(v, false);
            }
            OnFourGPageListener mOnPageListener2 = FourGWifiFragment.this.getMOnPageListener();
            if (mOnPageListener2 != null) {
                mOnPageListener2.onPlayClick();
            }
        }
    }

    public final OnFourGPageListener getMOnPageListener() {
        return this.mOnPageListener;
    }

    protected final void setMOnPageListener(OnFourGPageListener onFourGPageListener) {
        this.mOnPageListener = onFourGPageListener;
    }

    public final void setOnPageListener(OnFourGPageListener onPageListener) {
        Intrinsics.checkParameterIsNotNull(onPageListener, "onPageListener");
        this.mOnPageListener = onPageListener;
    }
}
