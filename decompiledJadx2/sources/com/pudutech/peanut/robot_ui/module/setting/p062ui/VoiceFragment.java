package com.pudutech.peanut.robot_ui.module.setting.p062ui;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.config.Constans;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.ShowDownloadingDialog;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.ShowTipMsgDialog;
import com.pudutech.peanut.robot_ui.p063ui.adapter.SelectVoicePackAdapter;
import com.pudutech.peanut.robot_ui.p063ui.adapter.TtsVoiceAdapter;
import com.pudutech.peanut.robot_ui.p063ui.dialog.CustomTtsVoiceInputDialog;
import com.pudutech.peanut.robot_ui.p063ui.helper.TtsVoiceHelper;
import com.pudutech.peanut.robot_ui.p063ui.view.MaxHeightRecyclerView;
import com.pudutech.peanut.robot_ui.util.InputMethodUtil;
import com.pudutech.peanut.robot_ui.util.LanguageUtils;
import com.pudutech.peanut.robot_ui.util.PlaySound;
import com.pudutech.peanut.robot_ui.util.ToastUtils;
import com.pudutech.peanut.robot_ui.viewmodel.VoicePkgWm;
import com.pudutech.robot.module.voice.VoicePackageManager;
import com.pudutech.robot.module.voice.pkg.VoicePackageInfo;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.math.MathKt;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: VoiceFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\u001dH\u0002J\b\u0010!\u001a\u00020\u001dH\u0002J\b\u0010\"\u001a\u00020\u001dH\u0002J\b\u0010#\u001a\u00020\u001dH\u0002J\b\u0010$\u001a\u00020\u001dH\u0002J\b\u0010%\u001a\u00020\u001dH\u0002J\u0012\u0010&\u001a\u00020\u001d2\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J&\u0010)\u001a\u0004\u0018\u00010\r2\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\b\u0010.\u001a\u00020\u001dH\u0016J\b\u0010/\u001a\u00020\u001dH\u0016J\b\u00100\u001a\u00020\u001dH\u0016J\u001a\u00101\u001a\u00020\u001d2\u0006\u00102\u001a\u00020\r2\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\u0010\u00103\u001a\u00020\u001d2\u0006\u00102\u001a\u00020\rH\u0002J\b\u00104\u001a\u00020\u001dH\u0002J\u0018\u00105\u001a\u00020\u001d2\u0006\u00102\u001a\u00020\r2\u0006\u00106\u001a\u00020\u000fH\u0002J\b\u00107\u001a\u00020\u001dH\u0002J\b\u00108\u001a\u00020\u001dH\u0002J\u0010\u00109\u001a\u00020\u001d2\u0006\u0010:\u001a\u00020;H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0018\u0010\u0019¨\u0006<"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/VoiceFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "customVoiceType", "Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "downProgressDialog", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowDownloadingDialog;", "handler", "Landroid/os/Handler;", "layout", "Landroid/view/View;", "onBtnClickListener", "Landroid/view/View$OnClickListener;", "popupWindow", "Landroid/widget/PopupWindow;", "ttsVoiceAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/TtsVoiceAdapter;", "voicePackAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/SelectVoicePackAdapter;", "voicePkgWm", "Lcom/pudutech/peanut/robot_ui/viewmodel/VoicePkgWm;", "getVoicePkgWm", "()Lcom/pudutech/peanut/robot_ui/viewmodel/VoicePkgWm;", "voicePkgWm$delegate", "Lkotlin/Lazy;", "bgAlpha", "", C3898x.f4339h, "", "changeCustomVoiceData", "checkLanguage", "initCruiseVoiceInterval", "initCustomVoiceView", "initDeliverFinishVoice", "initVoicePack", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onDestroyView", "onDetach", "onViewCreated", "view", "showChangeCustomTtsPopupWindow", "showCustomInputDialog", "showDeletePopupWindow", "onClickListener", "showDownloadFinishTip", "showFailedTip", "showVoicePackDownloadDialog", "item", "Lcom/pudutech/robot/module/voice/pkg/VoicePackageInfo;", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class VoiceFragment extends Fragment {
    private HashMap _$_findViewCache;
    private ShowDownloadingDialog downProgressDialog;
    private final Handler handler;
    private View layout;
    private final View.OnClickListener onBtnClickListener;
    private PopupWindow popupWindow;
    private TtsVoiceAdapter ttsVoiceAdapter;
    private SelectVoicePackAdapter voicePackAdapter;

    /* renamed from: voicePkgWm$delegate, reason: from kotlin metadata */
    private final Lazy voicePkgWm;
    private final String TAG = getClass().getSimpleName();
    private TtsVoiceHelper.TtsVoiceType customVoiceType = TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TtsVoiceHelper.TtsVoiceType.values().length];

        static {
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE.ordinal()] = 1;
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.SOLICIT_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.USHER_TYPE.ordinal()] = 4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final VoicePkgWm getVoicePkgWm() {
        return (VoicePkgWm) this.voicePkgWm.getValue();
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

    public VoiceFragment() {
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VoiceFragment$$special$$inlined$viewModels$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        this.voicePkgWm = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(VoicePkgWm.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VoiceFragment$$special$$inlined$viewModels$2
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
        this.onBtnClickListener = new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VoiceFragment$onBtnClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                String str;
                VoicePkgWm voicePkgWm;
                String str2;
                VoicePkgWm voicePkgWm2;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                Object tag = it.getTag();
                if (tag == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.module.voice.pkg.VoicePackageInfo");
                }
                VoicePackageInfo voicePackageInfo = (VoicePackageInfo) tag;
                if (voicePackageInfo.getIsExist() && voicePackageInfo.getNewVersionAvailable()) {
                    str2 = VoiceFragment.this.TAG;
                    Pdlog.m3273d(str2, "start update " + voicePackageInfo);
                    voicePkgWm2 = VoiceFragment.this.getVoicePkgWm();
                    voicePkgWm2.downloadPkg(voicePackageInfo);
                    return;
                }
                if (voicePackageInfo.getIsExist()) {
                    return;
                }
                str = VoiceFragment.this.TAG;
                Pdlog.m3273d(str, "start download " + voicePackageInfo);
                voicePkgWm = VoiceFragment.this.getVoicePkgWm();
                voicePkgWm.downloadPkg(voicePackageInfo);
            }
        };
        this.handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VoiceFragment$handler$1
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                PopupWindow popupWindow;
                String str;
                TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                popupWindow = VoiceFragment.this.popupWindow;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
                str = VoiceFragment.this.TAG;
                ttsVoiceType = VoiceFragment.this.customVoiceType;
                Pdlog.m3273d(str, String.valueOf(ttsVoiceType));
                return true;
            }
        });
    }

    public static final /* synthetic */ ShowDownloadingDialog access$getDownProgressDialog$p(VoiceFragment voiceFragment) {
        ShowDownloadingDialog showDownloadingDialog = voiceFragment.downProgressDialog;
        if (showDownloadingDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downProgressDialog");
        }
        return showDownloadingDialog;
    }

    public static final /* synthetic */ TtsVoiceAdapter access$getTtsVoiceAdapter$p(VoiceFragment voiceFragment) {
        TtsVoiceAdapter ttsVoiceAdapter = voiceFragment.ttsVoiceAdapter;
        if (ttsVoiceAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        return ttsVoiceAdapter;
    }

    public static final /* synthetic */ SelectVoicePackAdapter access$getVoicePackAdapter$p(VoiceFragment voiceFragment) {
        SelectVoicePackAdapter selectVoicePackAdapter = voiceFragment.voicePackAdapter;
        if (selectVoicePackAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("voicePackAdapter");
        }
        return selectVoicePackAdapter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showVoicePackDownloadDialog(final VoicePackageInfo item) {
        ShowDownloadingDialog showDownloadingDialog = this.downProgressDialog;
        if (showDownloadingDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downProgressDialog");
        }
        showDownloadingDialog.showCancel();
        ShowDownloadingDialog showDownloadingDialog2 = this.downProgressDialog;
        if (showDownloadingDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downProgressDialog");
        }
        String string = getString(C5508R.string.pdStr7_91);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_91)");
        showDownloadingDialog2.setTitle(string);
        ShowDownloadingDialog showDownloadingDialog3 = this.downProgressDialog;
        if (showDownloadingDialog3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downProgressDialog");
        }
        showDownloadingDialog3.showProgress(String.valueOf(0));
        ShowDownloadingDialog showDownloadingDialog4 = this.downProgressDialog;
        if (showDownloadingDialog4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downProgressDialog");
        }
        showDownloadingDialog4.setOnCloseBtnClick(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VoiceFragment$showVoicePackDownloadDialog$1
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

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                String str;
                VoicePkgWm voicePkgWm;
                str = VoiceFragment.this.TAG;
                Pdlog.m3273d(str, "cancelDownload " + item);
                voicePkgWm = VoiceFragment.this.getVoicePkgWm();
                voicePkgWm.cancelDownload(item);
            }
        });
        ShowDownloadingDialog showDownloadingDialog5 = this.downProgressDialog;
        if (showDownloadingDialog5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downProgressDialog");
        }
        showDownloadingDialog5.show();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initVoicePack();
        initCruiseVoiceInterval();
        initCustomVoiceView();
        checkLanguage();
        initDeliverFinishVoice();
    }

    private final void initDeliverFinishVoice() {
        Switch deliver_finish_voice_switch = (Switch) _$_findCachedViewById(C5508R.id.deliver_finish_voice_switch);
        Intrinsics.checkExpressionValueIsNotNull(deliver_finish_voice_switch, "deliver_finish_voice_switch");
        deliver_finish_voice_switch.setChecked(Constans.INSTANCE.getDeliverFinishVoiceAdvanceSwitch());
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("initDeliverFinishVoice ");
        Switch deliver_finish_voice_switch2 = (Switch) _$_findCachedViewById(C5508R.id.deliver_finish_voice_switch);
        Intrinsics.checkExpressionValueIsNotNull(deliver_finish_voice_switch2, "deliver_finish_voice_switch");
        sb.append(deliver_finish_voice_switch2.isChecked());
        Pdlog.m3273d(str, sb.toString());
        ((Switch) _$_findCachedViewById(C5508R.id.deliver_finish_voice_switch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VoiceFragment$initDeliverFinishVoice$1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                String str2;
                str2 = VoiceFragment.this.TAG;
                Pdlog.m3273d(str2, "deliver_finish_voice_switch " + z);
                Constans.INSTANCE.setDeliverFinishVoiceAdvanceSwitch(z);
            }
        });
    }

    private final void initCruiseVoiceInterval() {
        Pdlog.m3273d(this.TAG, "initCruiseVoiceInterval");
        final ArrayList arrayListOf = CollectionsKt.arrayListOf("5", "10", "15", "20", "25");
        String str = SpUtils.get(RobotContext.INSTANCE.getContext(), "key_cruise_voice_interval", "20");
        ArrayList arrayList = new ArrayList();
        for (Object obj : arrayListOf) {
            if (Integer.parseInt((String) obj) == Integer.parseInt(str)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        int indexOf = arrayList2.isEmpty() ? 0 : arrayListOf.indexOf(arrayList2.get(0));
        Pdlog.m3273d(this.TAG, "cruiseSpeed list " + arrayListOf + " level index " + indexOf + " level " + str);
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) _$_findCachedViewById(C5508R.id.voice_interval_degree);
        Object[] array = arrayListOf.toArray(new String[0]);
        if (array != null) {
            indicatorSeekBar.customTickTexts((String[]) array);
            ((IndicatorSeekBar) _$_findCachedViewById(C5508R.id.voice_interval_degree)).setIndicatorTextFormat("${TICK_TEXT}");
            ((IndicatorSeekBar) _$_findCachedViewById(C5508R.id.voice_interval_degree)).setProgress((indexOf / (arrayListOf.size() - 1)) * 100.0f);
            IndicatorSeekBar voice_interval_degree = (IndicatorSeekBar) _$_findCachedViewById(C5508R.id.voice_interval_degree);
            Intrinsics.checkExpressionValueIsNotNull(voice_interval_degree, "voice_interval_degree");
            voice_interval_degree.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VoiceFragment$initCruiseVoiceInterval$1
                private int interval;

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onStartTrackingTouch(IndicatorSeekBar seekBar) {
                }

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onSeeking(SeekParams params) {
                    String str2;
                    Intrinsics.checkParameterIsNotNull(params, "params");
                    str2 = VoiceFragment.this.TAG;
                    Pdlog.m3275i(str2, "onSeeking voice_interval ====" + params.progressFloat);
                    this.interval = params.progress;
                }

                @Override // com.warkiz.widget.OnSeekChangeListener
                public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
                    String str2;
                    Intrinsics.checkParameterIsNotNull(seekBar, "seekBar");
                    int roundToInt = MathKt.roundToInt((this.interval / 100.0f) * (arrayListOf.size() - 1));
                    str2 = VoiceFragment.this.TAG;
                    Pdlog.m3275i(str2, "voice_interval onStopTrackingTouch:  " + this.interval + ' ' + roundToInt + ' ' + ((String) arrayListOf.get(roundToInt)));
                    Context context = RobotContext.INSTANCE.getContext();
                    Object obj2 = arrayListOf.get(roundToInt);
                    Intrinsics.checkExpressionValueIsNotNull(obj2, "listArray[index]");
                    SpUtils.set(context, "key_cruise_voice_interval", (String) obj2);
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                }
            });
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final void checkLanguage() {
        LanguageUtils.Companion companion = LanguageUtils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        boolean isZh = companion.isZh(requireContext);
        LanguageUtils.Companion companion2 = LanguageUtils.INSTANCE;
        Context requireContext2 = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext2, "requireContext()");
        boolean isEnglish = companion2.isEnglish(requireContext2);
        if (isZh || isEnglish) {
            RelativeLayout cruise_voice_layout = (RelativeLayout) _$_findCachedViewById(C5508R.id.cruise_voice_layout);
            Intrinsics.checkExpressionValueIsNotNull(cruise_voice_layout, "cruise_voice_layout");
            cruise_voice_layout.setVisibility(0);
            CardView tts_cruise_voice_layout = (CardView) _$_findCachedViewById(C5508R.id.tts_cruise_voice_layout);
            Intrinsics.checkExpressionValueIsNotNull(tts_cruise_voice_layout, "tts_cruise_voice_layout");
            tts_cruise_voice_layout.setVisibility(0);
            return;
        }
        RelativeLayout cruise_voice_layout2 = (RelativeLayout) _$_findCachedViewById(C5508R.id.cruise_voice_layout);
        Intrinsics.checkExpressionValueIsNotNull(cruise_voice_layout2, "cruise_voice_layout");
        cruise_voice_layout2.setVisibility(8);
        CardView tts_cruise_voice_layout2 = (CardView) _$_findCachedViewById(C5508R.id.tts_cruise_voice_layout);
        Intrinsics.checkExpressionValueIsNotNull(tts_cruise_voice_layout2, "tts_cruise_voice_layout");
        tts_cruise_voice_layout2.setVisibility(8);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    private final void initVoicePack() {
        Pdlog.m3273d(this.TAG, "initVoicePack");
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        this.downProgressDialog = new ShowDownloadingDialog(requireContext);
        Context requireContext2 = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext2, "requireContext()");
        this.voicePackAdapter = new SelectVoicePackAdapter(requireContext2);
        SelectVoicePackAdapter selectVoicePackAdapter = this.voicePackAdapter;
        if (selectVoicePackAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("voicePackAdapter");
        }
        selectVoicePackAdapter.setOnBtnClickListener(this.onBtnClickListener);
        MaxHeightRecyclerView voice_pack_recycler_view = (MaxHeightRecyclerView) _$_findCachedViewById(C5508R.id.voice_pack_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(voice_pack_recycler_view, "voice_pack_recycler_view");
        voice_pack_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        MaxHeightRecyclerView voice_pack_recycler_view2 = (MaxHeightRecyclerView) _$_findCachedViewById(C5508R.id.voice_pack_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(voice_pack_recycler_view2, "voice_pack_recycler_view");
        SelectVoicePackAdapter selectVoicePackAdapter2 = this.voicePackAdapter;
        if (selectVoicePackAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("voicePackAdapter");
        }
        voice_pack_recycler_view2.setAdapter(selectVoicePackAdapter2);
        getVoicePkgWm().updatePkgList();
        SelectVoicePackAdapter selectVoicePackAdapter3 = this.voicePackAdapter;
        if (selectVoicePackAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("voicePackAdapter");
        }
        selectVoicePackAdapter3.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VoiceFragment$initVoicePack$1
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter<Object, BaseViewHolder> adapter, View view, int i) {
                String str;
                VoicePkgWm voicePkgWm;
                Intrinsics.checkExpressionValueIsNotNull(adapter, "adapter");
                Object obj = adapter.getData().get(i);
                if (obj != null) {
                    VoicePackageInfo voicePackageInfo = (VoicePackageInfo) obj;
                    if (voicePackageInfo.getIsExist()) {
                        str = VoiceFragment.this.TAG;
                        Pdlog.m3273d(str, "setOnItemClickListener select " + obj);
                        voicePkgWm = VoiceFragment.this.getVoicePkgWm();
                        voicePkgWm.switchPkgIfNeedDownload(voicePackageInfo);
                        return;
                    }
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.module.voice.pkg.VoicePackageInfo");
            }
        });
        SelectVoicePackAdapter selectVoicePackAdapter4 = this.voicePackAdapter;
        if (selectVoicePackAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("voicePackAdapter");
        }
        selectVoicePackAdapter4.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VoiceFragment$initVoicePack$2
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemLongClickListener
            public final boolean onItemLongClick(BaseQuickAdapter<Object, BaseViewHolder> adapter, View view, int i) {
                if (i == 0) {
                    return true;
                }
                Intrinsics.checkExpressionValueIsNotNull(adapter, "adapter");
                Object obj = adapter.getData().get(i);
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.module.voice.pkg.VoicePackageInfo");
                }
                final VoicePackageInfo voicePackageInfo = (VoicePackageInfo) obj;
                if (!voicePackageInfo.getIsExist()) {
                    return true;
                }
                VoiceFragment voiceFragment = VoiceFragment.this;
                Intrinsics.checkExpressionValueIsNotNull(view, "view");
                voiceFragment.showDeletePopupWindow(view, new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VoiceFragment$initVoicePack$2.1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        VoicePkgWm voicePkgWm;
                        voicePkgWm = VoiceFragment.this.getVoicePkgWm();
                        voicePkgWm.deletePkg(voicePackageInfo);
                    }
                });
                return true;
            }
        });
        getVoicePkgWm().getVoicePkgList().observe(getViewLifecycleOwner(), new Observer<ArrayList<VoicePackageInfo>>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VoiceFragment$initVoicePack$3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(ArrayList<VoicePackageInfo> arrayList) {
                String str;
                str = VoiceFragment.this.TAG;
                Pdlog.m3273d(str, "initVoicePack " + arrayList);
                VoiceFragment.access$getVoicePackAdapter$p(VoiceFragment.this).setNewData(arrayList);
            }
        });
        getVoicePkgWm().getDownloadInfo().observe(getViewLifecycleOwner(), new Observer<VoicePackageManager.DownloadVoice>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VoiceFragment$initVoicePack$4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(VoicePackageManager.DownloadVoice downloadVoice) {
                VoicePkgWm voicePkgWm;
                String str;
                VoicePackageInfo info = downloadVoice.getInfo();
                if (downloadVoice.getResult() == VoicePackageManager.DownloadResult.DOWNLOADING && !VoiceFragment.access$getDownProgressDialog$p(VoiceFragment.this).isShowing()) {
                    VoiceFragment.this.showVoicePackDownloadDialog(downloadVoice.getInfo());
                }
                if (VoiceFragment.access$getDownProgressDialog$p(VoiceFragment.this).isShowing()) {
                    str = VoiceFragment.this.TAG;
                    Pdlog.m3273d(str, "showDownloadProgress " + info);
                    VoiceFragment.access$getDownProgressDialog$p(VoiceFragment.this).showProgress(String.valueOf(info.getDownloadProgress()));
                }
                if (downloadVoice.getResult() == VoicePackageManager.DownloadResult.SUCCESS) {
                    VoiceFragment.access$getDownProgressDialog$p(VoiceFragment.this).dismiss();
                    VoiceFragment.this.showDownloadFinishTip();
                    VoiceFragment.access$getVoicePackAdapter$p(VoiceFragment.this).notifyDataSetChanged();
                } else if (downloadVoice.getResult() == VoicePackageManager.DownloadResult.FAIL) {
                    VoiceFragment.access$getDownProgressDialog$p(VoiceFragment.this).dismiss();
                    VoiceFragment.this.showFailedTip();
                    voicePkgWm = VoiceFragment.this.getVoicePkgWm();
                    voicePkgWm.updatePkgList();
                }
            }
        });
        getVoicePkgWm().getUpdateResult().observe(getViewLifecycleOwner(), new Observer<VoicePackageManager.UpdateResult>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VoiceFragment$initVoicePack$5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(VoicePackageManager.UpdateResult updateResult) {
                if (updateResult.getSuccess()) {
                    return;
                }
                ToastUtils.show(RobotContext.INSTANCE.getContext(), VoiceFragment.this.getString(C5508R.string.pdStr7_88), new Object[0]);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showChangeCustomTtsPopupWindow(View view) {
        RelativeLayout relativeLayout;
        RelativeLayout relativeLayout2;
        RelativeLayout relativeLayout3;
        RelativeLayout relativeLayout4;
        if (this.layout == null) {
            this.layout = getLayoutInflater().inflate(C5508R.layout.layout_popupwindow_change_curstom_tts, (ViewGroup) null, false);
        }
        this.popupWindow = new PopupWindow(this.layout, -1, -2, false);
        View view2 = this.layout;
        if (view2 != null && (relativeLayout4 = (RelativeLayout) view2.findViewById(C5508R.id.rlBg)) != null) {
            relativeLayout4.setClickable(true);
        }
        View view3 = this.layout;
        if (view3 != null && (relativeLayout3 = (RelativeLayout) view3.findViewById(C5508R.id.rlBg1)) != null) {
            relativeLayout3.setClickable(true);
        }
        View view4 = this.layout;
        if (view4 != null && (relativeLayout2 = (RelativeLayout) view4.findViewById(C5508R.id.rlBg)) != null) {
            relativeLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VoiceFragment$showChangeCustomTtsPopupWindow$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view5) {
                    TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                    Handler handler;
                    View view6;
                    View view7;
                    View view8;
                    View view9;
                    View view10;
                    View view11;
                    RelativeLayout relativeLayout5;
                    RelativeLayout relativeLayout6;
                    RelativeLayout relativeLayout7;
                    RelativeLayout relativeLayout8;
                    ImageView imageView;
                    ImageView imageView2;
                    ttsVoiceType = VoiceFragment.this.customVoiceType;
                    if (ttsVoiceType != TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE) {
                        VoiceFragment.this.customVoiceType = TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE;
                        view6 = VoiceFragment.this.layout;
                        if (view6 != null && (imageView2 = (ImageView) view6.findViewById(C5508R.id.cruise_ic)) != null) {
                            imageView2.setVisibility(0);
                        }
                        view7 = VoiceFragment.this.layout;
                        if (view7 != null && (imageView = (ImageView) view7.findViewById(C5508R.id.deliver_ic)) != null) {
                            imageView.setVisibility(8);
                        }
                        view8 = VoiceFragment.this.layout;
                        if (view8 != null && (relativeLayout8 = (RelativeLayout) view8.findViewById(C5508R.id.rlBg)) != null) {
                            Sdk27PropertiesKt.setBackgroundResource(relativeLayout8, C5508R.drawable.rectangle_setting_item_select_top);
                        }
                        view9 = VoiceFragment.this.layout;
                        if (view9 != null && (relativeLayout7 = (RelativeLayout) view9.findViewById(C5508R.id.rlBg1)) != null) {
                            relativeLayout7.setBackgroundColor(VoiceFragment.this.getResources().getColor(C5508R.color.transparent));
                        }
                        VoiceFragment.this.changeCustomVoiceData();
                        view10 = VoiceFragment.this.layout;
                        if (view10 != null && (relativeLayout6 = (RelativeLayout) view10.findViewById(C5508R.id.rlBg)) != null) {
                            relativeLayout6.setClickable(false);
                        }
                        view11 = VoiceFragment.this.layout;
                        if (view11 != null && (relativeLayout5 = (RelativeLayout) view11.findViewById(C5508R.id.rlBg1)) != null) {
                            relativeLayout5.setClickable(false);
                        }
                    }
                    handler = VoiceFragment.this.handler;
                    handler.sendEmptyMessageDelayed(1, 1000L);
                }
            });
        }
        View view5 = this.layout;
        if (view5 != null && (relativeLayout = (RelativeLayout) view5.findViewById(C5508R.id.rlBg1)) != null) {
            relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VoiceFragment$showChangeCustomTtsPopupWindow$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view6) {
                    TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                    Handler handler;
                    View view7;
                    View view8;
                    View view9;
                    View view10;
                    View view11;
                    View view12;
                    RelativeLayout relativeLayout5;
                    RelativeLayout relativeLayout6;
                    RelativeLayout relativeLayout7;
                    RelativeLayout relativeLayout8;
                    ImageView imageView;
                    ImageView imageView2;
                    ttsVoiceType = VoiceFragment.this.customVoiceType;
                    if (ttsVoiceType != TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE) {
                        VoiceFragment.this.customVoiceType = TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE;
                        view7 = VoiceFragment.this.layout;
                        if (view7 != null && (imageView2 = (ImageView) view7.findViewById(C5508R.id.cruise_ic)) != null) {
                            imageView2.setVisibility(8);
                        }
                        view8 = VoiceFragment.this.layout;
                        if (view8 != null && (imageView = (ImageView) view8.findViewById(C5508R.id.deliver_ic)) != null) {
                            imageView.setVisibility(0);
                        }
                        view9 = VoiceFragment.this.layout;
                        if (view9 != null && (relativeLayout8 = (RelativeLayout) view9.findViewById(C5508R.id.rlBg)) != null) {
                            relativeLayout8.setBackgroundColor(VoiceFragment.this.getResources().getColor(C5508R.color.transparent));
                        }
                        view10 = VoiceFragment.this.layout;
                        if (view10 != null && (relativeLayout7 = (RelativeLayout) view10.findViewById(C5508R.id.rlBg1)) != null) {
                            Sdk27PropertiesKt.setBackgroundResource(relativeLayout7, C5508R.drawable.rectangle_setting_item_select_bottom);
                        }
                        VoiceFragment.this.changeCustomVoiceData();
                        view11 = VoiceFragment.this.layout;
                        if (view11 != null && (relativeLayout6 = (RelativeLayout) view11.findViewById(C5508R.id.rlBg)) != null) {
                            relativeLayout6.setClickable(false);
                        }
                        view12 = VoiceFragment.this.layout;
                        if (view12 != null && (relativeLayout5 = (RelativeLayout) view12.findViewById(C5508R.id.rlBg1)) != null) {
                            relativeLayout5.setClickable(false);
                        }
                    }
                    handler = VoiceFragment.this.handler;
                    handler.sendEmptyMessageDelayed(1, 1000L);
                }
            });
        }
        PopupWindow popupWindow = this.popupWindow;
        if (popupWindow != null) {
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VoiceFragment$showChangeCustomTtsPopupWindow$3
                @Override // android.widget.PopupWindow.OnDismissListener
                public final void onDismiss() {
                    VoiceFragment.this.bgAlpha(1.0f);
                }
            });
        }
        PopupWindow popupWindow2 = this.popupWindow;
        if (popupWindow2 != null) {
            popupWindow2.setOutsideTouchable(true);
        }
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        if (600 - iArr[1] < 130) {
            PopupWindow popupWindow3 = this.popupWindow;
            if (popupWindow3 != null) {
                popupWindow3.showAsDropDown(view, 0, -130);
            }
        } else {
            PopupWindow popupWindow4 = this.popupWindow;
            if (popupWindow4 != null) {
                popupWindow4.showAsDropDown(view, 0, 5);
            }
        }
        bgAlpha(0.5f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void bgAlpha(float f) {
        if (getActivity() == null) {
            return;
        }
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        Window window = requireActivity.getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window, "requireActivity().window");
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = f;
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity2, "requireActivity()");
        Window window2 = requireActivity2.getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window2, "requireActivity().window");
        window2.setAttributes(attributes);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDeletePopupWindow(View view, final View.OnClickListener onClickListener) {
        View inflate = getLayoutInflater().inflate(C5508R.layout.layout_popupwindow_delete, (ViewGroup) null, false);
        final PopupWindow popupWindow = new PopupWindow(inflate, -2, -2, false);
        inflate.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VoiceFragment$showDeletePopupWindow$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                onClickListener.onClick(view2);
                popupWindow.dismiss();
            }
        });
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setOutsideTouchable(true);
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        if (600 - iArr[1] < 150) {
            popupWindow.showAsDropDown(view, (view.getWidth() / 2) - (popupWindow.getWidth() / 2), -view.getHeight());
        } else {
            popupWindow.showAsDropDown(view, (view.getWidth() / 2) - (popupWindow.getWidth() / 2), -10);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C5508R.layout.fragment_voice, container, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showCustomInputDialog() {
        String string;
        if (TtsVoiceHelper.getTtsConfigList$default(TtsVoiceHelper.INSTANCE, this.customVoiceType, false, 2, null).size() >= 30) {
            ToastUtils.show(RobotContext.INSTANCE.getContext(), getString(C5508R.string.custom_word_arrived_max), new Object[0]);
            return;
        }
        CustomTtsVoiceInputDialog customTtsVoiceInputDialog = new CustomTtsVoiceInputDialog();
        int i = WhenMappings.$EnumSwitchMapping$0[this.customVoiceType.ordinal()];
        if (i == 1) {
            string = getString(C5508R.string.pdStr7_120);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_120)");
        } else if (i == 2) {
            string = getString(C5508R.string.pdStr7_121);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_121)");
        } else if (i == 3) {
            string = getString(C5508R.string.solicit_my_self_setting);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.solicit_my_self_setting)");
        } else {
            if (i != 4) {
                throw new NoWhenBranchMatchedException();
            }
            string = getString(C5508R.string.solicit_my_self_setting);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.solicit_my_self_setting)");
        }
        customTtsVoiceInputDialog.setTitle(string);
        customTtsVoiceInputDialog.setType(this.customVoiceType);
        FragmentManager requireFragmentManager = requireFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(requireFragmentManager, "requireFragmentManager()");
        customTtsVoiceInputDialog.show(requireFragmentManager, "voice_custom");
        customTtsVoiceInputDialog.setOnContentChange(new Function1<String, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VoiceFragment$showCustomInputDialog$1
            /* JADX INFO: Access modifiers changed from: package-private */
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
                if (VoiceFragment.this.getContext() != null) {
                    VoiceFragment.access$getTtsVoiceAdapter$p(VoiceFragment.this).notifyDataSetChanged();
                }
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Pdlog.m3273d(this.TAG, "onDestroyView");
        Function0<Unit> function0 = (Function0) null;
        TtsVoiceHelper.INSTANCE.setOnCruiseConfigChangeListener(function0);
        TtsVoiceHelper.INSTANCE.setOnDeliverConfigChangeListener(function0);
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        Pdlog.m3273d(this.TAG, "onDetach");
        Function0<Unit> function0 = (Function0) null;
        TtsVoiceHelper.INSTANCE.setOnCruiseConfigChangeListener(function0);
        TtsVoiceHelper.INSTANCE.setOnDeliverConfigChangeListener(function0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void changeCustomVoiceData() {
        if (this.customVoiceType == TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE) {
            TtsVoiceAdapter ttsVoiceAdapter = this.ttsVoiceAdapter;
            if (ttsVoiceAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
            }
            ttsVoiceAdapter.setNewData(TtsVoiceHelper.getTtsConfigList$default(TtsVoiceHelper.INSTANCE, this.customVoiceType, false, 2, null));
            LinearLayout cruise_voice_play_interval_layout = (LinearLayout) _$_findCachedViewById(C5508R.id.cruise_voice_play_interval_layout);
            Intrinsics.checkExpressionValueIsNotNull(cruise_voice_play_interval_layout, "cruise_voice_play_interval_layout");
            cruise_voice_play_interval_layout.setVisibility(0);
            TextView cruise_context_tv = (TextView) _$_findCachedViewById(C5508R.id.cruise_context_tv);
            Intrinsics.checkExpressionValueIsNotNull(cruise_context_tv, "cruise_context_tv");
            cruise_context_tv.setText(getString(C5508R.string.pdStr7_65));
            TextView custom_voice_title = (TextView) _$_findCachedViewById(C5508R.id.custom_voice_title);
            Intrinsics.checkExpressionValueIsNotNull(custom_voice_title, "custom_voice_title");
            custom_voice_title.setText(getString(C5508R.string.pdStr7_64));
        } else if (this.customVoiceType == TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE) {
            TtsVoiceAdapter ttsVoiceAdapter2 = this.ttsVoiceAdapter;
            if (ttsVoiceAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
            }
            ttsVoiceAdapter2.setNewData(TtsVoiceHelper.getTtsConfigList$default(TtsVoiceHelper.INSTANCE, this.customVoiceType, false, 2, null));
            LinearLayout cruise_voice_play_interval_layout2 = (LinearLayout) _$_findCachedViewById(C5508R.id.cruise_voice_play_interval_layout);
            Intrinsics.checkExpressionValueIsNotNull(cruise_voice_play_interval_layout2, "cruise_voice_play_interval_layout");
            cruise_voice_play_interval_layout2.setVisibility(8);
            TextView cruise_context_tv2 = (TextView) _$_findCachedViewById(C5508R.id.cruise_context_tv);
            Intrinsics.checkExpressionValueIsNotNull(cruise_context_tv2, "cruise_context_tv");
            cruise_context_tv2.setText(getString(C5508R.string.pdStr7_122));
            TextView custom_voice_title2 = (TextView) _$_findCachedViewById(C5508R.id.custom_voice_title);
            Intrinsics.checkExpressionValueIsNotNull(custom_voice_title2, "custom_voice_title");
            custom_voice_title2.setText(getString(C5508R.string.pdStr7_121));
        }
        Switch open_cruise_switch = (Switch) _$_findCachedViewById(C5508R.id.open_cruise_switch);
        Intrinsics.checkExpressionValueIsNotNull(open_cruise_switch, "open_cruise_switch");
        TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        open_cruise_switch.setChecked(ttsVoiceHelper.checkTtsOpenType(requireContext, this.customVoiceType) == TtsVoiceHelper.TtsVoiceOpenType.OPEN);
        TtsVoiceAdapter ttsVoiceAdapter3 = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        ttsVoiceAdapter3.setType(this.customVoiceType);
    }

    private final void initCustomVoiceView() {
        ((TextView) _$_findCachedViewById(C5508R.id.custom_voice_title)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VoiceFragment$initCustomVoiceView$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Context it = VoiceFragment.this.getContext();
                if (it != null) {
                    LanguageUtils.Companion companion = LanguageUtils.INSTANCE;
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    if (!companion.isZh(it) && !LanguageUtils.INSTANCE.isEnglish(it)) {
                        return;
                    }
                }
                VoiceFragment voiceFragment = VoiceFragment.this;
                TextView custom_voice_title = (TextView) voiceFragment._$_findCachedViewById(C5508R.id.custom_voice_title);
                Intrinsics.checkExpressionValueIsNotNull(custom_voice_title, "custom_voice_title");
                voiceFragment.showChangeCustomTtsPopupWindow(custom_voice_title);
            }
        });
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        this.ttsVoiceAdapter = new TtsVoiceAdapter(requireContext);
        MaxHeightRecyclerView tts_cruise_voice_recycler_view = (MaxHeightRecyclerView) _$_findCachedViewById(C5508R.id.tts_cruise_voice_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(tts_cruise_voice_recycler_view, "tts_cruise_voice_recycler_view");
        tts_cruise_voice_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        MaxHeightRecyclerView tts_cruise_voice_recycler_view2 = (MaxHeightRecyclerView) _$_findCachedViewById(C5508R.id.tts_cruise_voice_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(tts_cruise_voice_recycler_view2, "tts_cruise_voice_recycler_view");
        TtsVoiceAdapter ttsVoiceAdapter = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        tts_cruise_voice_recycler_view2.setAdapter(ttsVoiceAdapter);
        TtsVoiceAdapter ttsVoiceAdapter2 = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        ttsVoiceAdapter2.setNewData(TtsVoiceHelper.getTtsConfigList$default(TtsVoiceHelper.INSTANCE, this.customVoiceType, false, 2, null));
        TtsVoiceAdapter ttsVoiceAdapter3 = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        ttsVoiceAdapter3.setType(this.customVoiceType);
        TtsVoiceHelper.INSTANCE.setOnCruiseConfigChangeListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VoiceFragment$initCustomVoiceView$2
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
                TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                if (VoiceFragment.this.getContext() != null) {
                    ttsVoiceType = VoiceFragment.this.customVoiceType;
                    if (ttsVoiceType == TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE) {
                        VoiceFragment.access$getTtsVoiceAdapter$p(VoiceFragment.this).notifyDataSetChanged();
                    }
                }
            }
        });
        TtsVoiceHelper.INSTANCE.setOnDeliverConfigChangeListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VoiceFragment$initCustomVoiceView$3
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
                TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                if (VoiceFragment.this.getContext() != null) {
                    ttsVoiceType = VoiceFragment.this.customVoiceType;
                    if (ttsVoiceType == TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE) {
                        VoiceFragment.access$getTtsVoiceAdapter$p(VoiceFragment.this).notifyDataSetChanged();
                    }
                }
            }
        });
        ((TextView) _$_findCachedViewById(C5508R.id.add_voice_tv)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VoiceFragment$initCustomVoiceView$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InputMethodUtil.setDefaultInputMethod(VoiceFragment.this.getContext(), "gokey");
                VoiceFragment.this.showCustomInputDialog();
            }
        });
        TtsVoiceAdapter ttsVoiceAdapter4 = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        ttsVoiceAdapter4.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VoiceFragment$initCustomVoiceView$5
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemLongClickListener
            public final boolean onItemLongClick(final BaseQuickAdapter<Object, BaseViewHolder> baseQuickAdapter, View view, final int i) {
                VoiceFragment voiceFragment = VoiceFragment.this;
                Intrinsics.checkExpressionValueIsNotNull(view, "view");
                voiceFragment.showDeletePopupWindow(view, new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VoiceFragment$initCustomVoiceView$5.1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                        TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                        BaseQuickAdapter adapter = baseQuickAdapter;
                        Intrinsics.checkExpressionValueIsNotNull(adapter, "adapter");
                        Object obj = adapter.getData().get(i);
                        if (obj != null) {
                            ttsVoiceType = VoiceFragment.this.customVoiceType;
                            ttsVoiceHelper.deleteConfig((TtsVoiceHelper.TtsConfigData) obj, ttsVoiceType);
                            VoiceFragment.access$getTtsVoiceAdapter$p(VoiceFragment.this).notifyDataSetChanged();
                            return;
                        }
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.peanut.robot_ui.ui.helper.TtsVoiceHelper.TtsConfigData");
                    }
                });
                return true;
            }
        });
        Switch open_cruise_switch = (Switch) _$_findCachedViewById(C5508R.id.open_cruise_switch);
        Intrinsics.checkExpressionValueIsNotNull(open_cruise_switch, "open_cruise_switch");
        TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
        Context requireContext2 = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext2, "requireContext()");
        open_cruise_switch.setChecked(ttsVoiceHelper.checkTtsOpenType(requireContext2, this.customVoiceType) == TtsVoiceHelper.TtsVoiceOpenType.OPEN);
        ((Switch) _$_findCachedViewById(C5508R.id.open_cruise_switch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.VoiceFragment$initCustomVoiceView$6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                String str;
                TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                TtsVoiceHelper.TtsVoiceType ttsVoiceType2;
                str = VoiceFragment.this.TAG;
                Pdlog.m3273d(str, "open_cruise_switch " + z);
                if (z) {
                    TtsVoiceHelper ttsVoiceHelper2 = TtsVoiceHelper.INSTANCE;
                    Context requireContext3 = VoiceFragment.this.requireContext();
                    Intrinsics.checkExpressionValueIsNotNull(requireContext3, "requireContext()");
                    TtsVoiceHelper.TtsVoiceOpenType ttsVoiceOpenType = TtsVoiceHelper.TtsVoiceOpenType.OPEN;
                    ttsVoiceType2 = VoiceFragment.this.customVoiceType;
                    ttsVoiceHelper2.setTtsType(requireContext3, ttsVoiceOpenType, ttsVoiceType2);
                } else {
                    TtsVoiceHelper ttsVoiceHelper3 = TtsVoiceHelper.INSTANCE;
                    Context requireContext4 = VoiceFragment.this.requireContext();
                    Intrinsics.checkExpressionValueIsNotNull(requireContext4, "requireContext()");
                    TtsVoiceHelper.TtsVoiceOpenType ttsVoiceOpenType2 = TtsVoiceHelper.TtsVoiceOpenType.CLOSE;
                    ttsVoiceType = VoiceFragment.this.customVoiceType;
                    ttsVoiceHelper3.setTtsType(requireContext4, ttsVoiceOpenType2, ttsVoiceType);
                }
                VoiceFragment.access$getTtsVoiceAdapter$p(VoiceFragment.this).notifyDataSetChanged();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showFailedTip() {
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        ShowTipMsgDialog showTipMsgDialog = new ShowTipMsgDialog(requireContext);
        String string = getString(C5508R.string.pdStr7_90);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_90)");
        showTipMsgDialog.showTipMsg(string);
        showTipMsgDialog.setCanCancel(true);
        showTipMsgDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDownloadFinishTip() {
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        ShowTipMsgDialog showTipMsgDialog = new ShowTipMsgDialog(requireContext);
        String string = getString(C5508R.string.pdStr7_89);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_89)");
        showTipMsgDialog.showTipMsg(string);
        showTipMsgDialog.setCanCancel(false);
        showTipMsgDialog.show();
        showTipMsgDialog.autoHide(1000L);
    }
}
