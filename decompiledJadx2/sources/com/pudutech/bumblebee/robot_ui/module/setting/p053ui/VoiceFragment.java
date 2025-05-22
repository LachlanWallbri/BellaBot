package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseApplication;
import com.pudutech.bumblebee.presenter.robot_voices_task.VoicePackageHelper;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ShowDownloadingDialog;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ShowTipMsgDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.SelectSpeakerAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.TtsVoiceAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.CustomMsgDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.CustomTtsVoiceInputDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.TransparentLoadDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.VoiceModeDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceFlHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.view.MaxHeightRecyclerView;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceIndicatorSeekBarChangeListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceItemLongClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceRadioGroupChangeListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceSwitchChangeListener;
import com.pudutech.bumblebee.robot_ui.util.InputMethodUtil;
import com.pudutech.bumblebee.robot_ui.util.NavigationBarUtil;
import com.pudutech.bumblebee.robot_ui.util.NetStatusUtil;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import com.pudutech.bumblebee.robot_ui.viewmodel.VoiceViewModel;
import com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment;
import com.pudutech.disinfect.baselib.callback.livedata.event.EventLiveData;
import com.pudutech.disinfect.baselib.ext.util.CommonExtKt;
import com.pudutech.disinfect.baselib.network.response.FileUpdateResponse;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import com.pudutech.lib_update.util.FileUtil;
import com.pudutech.lib_update.util.PackageUtils;
import com.pudutech.light_network.download.DownLoadObserver;
import com.pudutech.light_network.download.DownloadInfo;
import com.pudutech.light_network.download.DownloadManager;
import com.pudutech.tts_sdk.tts.OnTtsListener;
import com.warkiz.widget.IndicatorSeekBar;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.jetbrains.anko.AsyncKt;

/* compiled from: VoiceFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0002J\b\u0010-\u001a\u00020*H\u0002J\"\u0010.\u001a\u00020*2\b\u0010/\u001a\u0004\u0018\u0001002\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u0005H\u0002J\b\u00104\u001a\u00020*H\u0002J\b\u00105\u001a\u00020*H\u0016J\b\u00106\u001a\u00020*H\u0002J\u0018\u00107\u001a\u00020*2\u0006\u00108\u001a\u00020\u00052\u0006\u00103\u001a\u00020\u0005H\u0002J\b\u00109\u001a\u00020*H\u0002J\b\u0010:\u001a\u00020*H\u0002J\b\u0010;\u001a\u00020*H\u0002J\b\u0010<\u001a\u00020*H\u0002J\b\u0010=\u001a\u00020*H\u0002J\b\u0010>\u001a\u00020*H\u0002J\u0012\u0010?\u001a\u00020*2\b\u0010@\u001a\u0004\u0018\u00010AH\u0016J\b\u0010B\u001a\u00020*H\u0002J\b\u0010C\u001a\u00020*H\u0002J\b\u0010D\u001a\u00020EH\u0016J\b\u0010F\u001a\u00020*H\u0016J\b\u0010G\u001a\u00020*H\u0016J\b\u0010H\u001a\u00020*H\u0016J\b\u0010I\u001a\u00020*H\u0016J\b\u0010J\u001a\u00020*H\u0002J\u0010\u0010K\u001a\u00020*2\u0006\u0010L\u001a\u00020MH\u0002J\b\u0010N\u001a\u00020*H\u0002J\u0018\u0010O\u001a\u00020*2\u0006\u0010L\u001a\u00020M2\u0006\u0010P\u001a\u00020QH\u0002J\b\u0010R\u001a\u00020*H\u0002J\u0010\u0010S\u001a\u00020*2\u0006\u0010T\u001a\u00020\u0005H\u0002J\u0012\u0010U\u001a\u00020*2\b\u0010V\u001a\u0004\u0018\u00010\u0005H\u0002R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R!\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0013j\b\u0012\u0004\u0012\u00020\u0005`\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R!\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0013j\b\u0012\u0004\u0012\u00020\u0005`\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006W"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/VoiceFragment;", "Lcom/pudutech/disinfect/baselib/base/fragment/BaseVmFragment;", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/VoiceViewModel;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "_LoadDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/TransparentLoadDialog;", "_voiceModeDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/VoiceModeDialog;", "customVoiceType", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "downFilePath", "installDisposable", "Lio/reactivex/disposables/Disposable;", "installTipDialog", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "listArray", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getListArray", "()Ljava/util/ArrayList;", "listArrays", "getListArrays", "mCustomMsgDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/CustomMsgDialog;", "mPopupWindow", "Landroid/widget/PopupWindow;", "getMPopupWindow", "()Landroid/widget/PopupWindow;", "setMPopupWindow", "(Landroid/widget/PopupWindow;)V", "selectSpeakerAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SelectSpeakerAdapter;", "switchSpeaker", "", "ttsVoiceAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/TtsVoiceAdapter;", "voicePackageHelper", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/VoicePackageHelper;", "bgAlpha", "", C3898x.f4339h, "", "changeCustomVoiceData", "checkInputMethodDownInfo", "downInfo", "Lcom/pudutech/light_network/download/DownloadInfo;", "dialog", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ShowDownloadingDialog;", "md5", "checkLanguage", "createObserver", "dismissLoadDialog", "doDownloadInputMethodFile", "url", "hideInstallTip", "initCruiseVoiceInterval", "initCustomVoiceView", "initDeliverFinishVoice", "initGreeterFaceDefaultVoiceSwitch", "initSpeedLimit", "initView", "savedInstanceState", "Landroid/os/Bundle;", "initVoice", "initVoicePack", "layoutId", "", "onDestroy", "onDestroyView", "onDetach", "release", "setTTsViewStatus", "showChangeCustomTtsPopupWindow", "view", "Landroid/view/View;", "showCustomInputDialog", "showDeletePopupWindow", "onClickListener", "Landroid/view/View$OnClickListener;", "showDownInputTis", "showInstallTip", NotificationCompat.CATEGORY_MESSAGE, "showLoadDialog", "tip", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class VoiceFragment extends BaseVmFragment<VoiceViewModel> {
    private HashMap _$_findViewCache;
    private TransparentLoadDialog _LoadDialog;
    private VoiceModeDialog _voiceModeDialog;
    private Disposable installDisposable;
    private ShowTipMsgDialog installTipDialog;
    private CustomMsgDialog mCustomMsgDialog;
    private PopupWindow mPopupWindow;
    private SelectSpeakerAdapter selectSpeakerAdapter;
    private boolean switchSpeaker;
    private TtsVoiceAdapter ttsVoiceAdapter;
    private final String TAG = getClass().getSimpleName();
    private TtsVoiceHelper.TtsVoiceType customVoiceType = TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE;
    private String downFilePath = "";
    private final VoicePackageHelper voicePackageHelper = new VoicePackageHelper();
    private final ArrayList<String> listArray = CollectionsKt.arrayListOf("5", "10", "15", "20", "25");
    private final ArrayList<String> listArrays = CollectionsKt.arrayListOf("5s", "10s", "15s", "20s", "25s");

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment
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

    public static final /* synthetic */ SelectSpeakerAdapter access$getSelectSpeakerAdapter$p(VoiceFragment voiceFragment) {
        SelectSpeakerAdapter selectSpeakerAdapter = voiceFragment.selectSpeakerAdapter;
        if (selectSpeakerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectSpeakerAdapter");
        }
        return selectSpeakerAdapter;
    }

    public static final /* synthetic */ TtsVoiceAdapter access$getTtsVoiceAdapter$p(VoiceFragment voiceFragment) {
        TtsVoiceAdapter ttsVoiceAdapter = voiceFragment.ttsVoiceAdapter;
        if (ttsVoiceAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        return ttsVoiceAdapter;
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment
    public int layoutId() {
        return C4188R.layout.fragment_voice;
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment
    public void initView(Bundle savedInstanceState) {
        initVoicePack();
        initCruiseVoiceInterval();
        initCustomVoiceView();
        checkLanguage();
        initDeliverFinishVoice();
        initGreeterFaceDefaultVoiceSwitch();
        initSpeedLimit();
        setTTsViewStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLoadDialog(String tip) {
        if (this._LoadDialog == null && getMActivity() != null) {
            Activity mActivity = getMActivity();
            if (mActivity == null) {
                Intrinsics.throwNpe();
            }
            this._LoadDialog = new TransparentLoadDialog(mActivity);
        }
        TransparentLoadDialog transparentLoadDialog = this._LoadDialog;
        if (transparentLoadDialog != null) {
            String str = tip;
            if (!(str == null || str.length() == 0)) {
                transparentLoadDialog.setContent(tip);
            }
            if (transparentLoadDialog.isShowing()) {
                return;
            }
            transparentLoadDialog.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void dismissLoadDialog() {
        TransparentLoadDialog transparentLoadDialog = this._LoadDialog;
        if (transparentLoadDialog != null) {
            transparentLoadDialog.dismiss();
        }
    }

    private final void initSpeedLimit() {
        Switch speed_limit_mode_switch = (Switch) _$_findCachedViewById(C4188R.id.speed_limit_mode_switch);
        Intrinsics.checkExpressionValueIsNotNull(speed_limit_mode_switch, "speed_limit_mode_switch");
        speed_limit_mode_switch.setChecked(Constans.INSTANCE.isSpeedLimitArea());
        LinearLayout speed_limit_time_root = (LinearLayout) _$_findCachedViewById(C4188R.id.speed_limit_time_root);
        Intrinsics.checkExpressionValueIsNotNull(speed_limit_time_root, "speed_limit_time_root");
        speed_limit_time_root.setVisibility(Constans.INSTANCE.isSpeedLimitArea() ? 0 : 8);
        ((Switch) _$_findCachedViewById(C4188R.id.speed_limit_mode_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$initSpeedLimit$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton v, boolean z) {
                Intrinsics.checkParameterIsNotNull(v, "v");
                Constans.INSTANCE.setSpeedLimitArea(z);
                LinearLayout speed_limit_time_root2 = (LinearLayout) VoiceFragment.this._$_findCachedViewById(C4188R.id.speed_limit_time_root);
                Intrinsics.checkExpressionValueIsNotNull(speed_limit_time_root2, "speed_limit_time_root");
                speed_limit_time_root2.setVisibility(z ? 0 : 8);
            }
        }, 7, null));
        ((TextView) _$_findCachedViewById(C4188R.id.speed_limit_time_reset_tv)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$initSpeedLimit$2
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
                if (Constans.INSTANCE.getSpeedLimitTime() == 3) {
                    return;
                }
                Constans.INSTANCE.setSpeedLimitTime(3);
                ((EditText) VoiceFragment.this._$_findCachedViewById(C4188R.id.speed_limit_time_et)).setText("3");
            }
        }, 3, null));
        ((EditText) _$_findCachedViewById(C4188R.id.speed_limit_time_et)).setText(String.valueOf(Constans.INSTANCE.getSpeedLimitTime()));
        EditText speed_limit_time_et = (EditText) _$_findCachedViewById(C4188R.id.speed_limit_time_et);
        Intrinsics.checkExpressionValueIsNotNull(speed_limit_time_et, "speed_limit_time_et");
        speed_limit_time_et.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$initSpeedLimit$$inlined$doAfterTextChanged$1
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
                str = VoiceFragment.this.TAG;
                Pdlog.m3273d(str, "auto_finish_et : " + valueOf);
                if (valueOf.length() == 0) {
                    valueOf = "1";
                }
                try {
                    int parseInt = Integer.parseInt(valueOf);
                    if (parseInt > 10) {
                        ((EditText) VoiceFragment.this._$_findCachedViewById(C4188R.id.speed_limit_time_et)).setText(String.valueOf(10));
                        parseInt = 10;
                    } else if (parseInt <= 0) {
                        ((EditText) VoiceFragment.this._$_findCachedViewById(C4188R.id.speed_limit_time_et)).setText(String.valueOf(1));
                        parseInt = 1;
                    }
                    Constans.INSTANCE.setSpeedLimitTime(parseInt);
                } catch (Exception e) {
                    str2 = VoiceFragment.this.TAG;
                    Pdlog.m3274e(str2, "speed_limit_time : " + Log.getStackTraceString(e));
                }
            }
        });
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment
    public void createObserver() {
        EventLiveData<FileUpdateResponse> mFileUpdateResponse = getMViewModel().getMFileUpdateResponse();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner, "viewLifecycleOwner");
        mFileUpdateResponse.observe(viewLifecycleOwner, new Observer<FileUpdateResponse>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$createObserver$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(FileUpdateResponse fileUpdateResponse) {
                CustomMsgDialog customMsgDialog;
                String str;
                if (fileUpdateResponse == 0) {
                    str = VoiceFragment.this.TAG;
                    Pdlog.m3274e(str, Log.getStackTraceString((Throwable) fileUpdateResponse));
                    ToastUtils.show(RobotContext.INSTANCE.getContext(), VoiceFragment.this.getString(C4188R.string.pdStr7_80), new Object[0]);
                } else {
                    if (!StringsKt.isBlank(fileUpdateResponse.getUrl())) {
                        VoiceFragment.this.doDownloadInputMethodFile(fileUpdateResponse.getUrl(), fileUpdateResponse.getMd5());
                        customMsgDialog = VoiceFragment.this.mCustomMsgDialog;
                        if (customMsgDialog == null || !customMsgDialog.isShowing()) {
                            return;
                        }
                        customMsgDialog.dismiss();
                        return;
                    }
                    ToastUtils.show(RobotContext.INSTANCE.getContext(), VoiceFragment.this.getString(C4188R.string.pdStr7_80), new Object[0]);
                }
            }
        });
    }

    private final void initDeliverFinishVoice() {
        Switch deliver_finish_voice_switch = (Switch) _$_findCachedViewById(C4188R.id.deliver_finish_voice_switch);
        Intrinsics.checkExpressionValueIsNotNull(deliver_finish_voice_switch, "deliver_finish_voice_switch");
        deliver_finish_voice_switch.setChecked(Constans.INSTANCE.getDeliverFinishVoiceAdvanceSwitch());
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("initDeliverFinishVoice ");
        Switch deliver_finish_voice_switch2 = (Switch) _$_findCachedViewById(C4188R.id.deliver_finish_voice_switch);
        Intrinsics.checkExpressionValueIsNotNull(deliver_finish_voice_switch2, "deliver_finish_voice_switch");
        sb.append(deliver_finish_voice_switch2.isChecked());
        Pdlog.m3273d(str, sb.toString());
        ((Switch) _$_findCachedViewById(C4188R.id.deliver_finish_voice_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$initDeliverFinishVoice$1
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
                str2 = VoiceFragment.this.TAG;
                Pdlog.m3273d(str2, "deliver_finish_voice_switch " + z);
                Constans.INSTANCE.setDeliverFinishVoiceAdvanceSwitch(z);
            }
        }, 7, null));
    }

    private final void initGreeterFaceDefaultVoiceSwitch() {
        if (Constans.INSTANCE.getGreeterFaceDefaultVoiceSwitch()) {
            ((RadioGroup) _$_findCachedViewById(C4188R.id.voice_play_mode_group)).check(C4188R.id.voice_play_mode_2);
            LinearLayout voice_play_mode_interval_layout = (LinearLayout) _$_findCachedViewById(C4188R.id.voice_play_mode_interval_layout);
            Intrinsics.checkExpressionValueIsNotNull(voice_play_mode_interval_layout, "voice_play_mode_interval_layout");
            voice_play_mode_interval_layout.setVisibility(0);
        } else {
            ((RadioGroup) _$_findCachedViewById(C4188R.id.voice_play_mode_group)).check(C4188R.id.voice_play_mode_1);
            LinearLayout voice_play_mode_interval_layout2 = (LinearLayout) _$_findCachedViewById(C4188R.id.voice_play_mode_interval_layout);
            Intrinsics.checkExpressionValueIsNotNull(voice_play_mode_interval_layout2, "voice_play_mode_interval_layout");
            voice_play_mode_interval_layout2.setVisibility(8);
        }
        ((RadioGroup) _$_findCachedViewById(C4188R.id.voice_play_mode_group)).setOnCheckedChangeListener(new VoiceRadioGroupChangeListener(null, 0, new Function2<RadioGroup, Integer, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$initGreeterFaceDefaultVoiceSwitch$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(RadioGroup radioGroup, Integer num) {
                invoke(radioGroup, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(RadioGroup group, int i) {
                Intrinsics.checkParameterIsNotNull(group, "group");
                if (i == C4188R.id.voice_play_mode_1) {
                    Constans.INSTANCE.setGreeterFaceDefaultVoiceSwitch(false);
                    LinearLayout voice_play_mode_interval_layout3 = (LinearLayout) VoiceFragment.this._$_findCachedViewById(C4188R.id.voice_play_mode_interval_layout);
                    Intrinsics.checkExpressionValueIsNotNull(voice_play_mode_interval_layout3, "voice_play_mode_interval_layout");
                    voice_play_mode_interval_layout3.setVisibility(8);
                    return;
                }
                if (i == C4188R.id.voice_play_mode_2) {
                    Constans.INSTANCE.setGreeterFaceDefaultVoiceSwitch(true);
                    LinearLayout voice_play_mode_interval_layout4 = (LinearLayout) VoiceFragment.this._$_findCachedViewById(C4188R.id.voice_play_mode_interval_layout);
                    Intrinsics.checkExpressionValueIsNotNull(voice_play_mode_interval_layout4, "voice_play_mode_interval_layout");
                    voice_play_mode_interval_layout4.setVisibility(0);
                }
            }
        }, 3, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initVoice() {
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.voice_interval_degree);
        Object[] array = this.listArrays.toArray(new String[0]);
        if (array != null) {
            indicatorSeekBar.customTickTexts((String[]) array);
            ((IndicatorSeekBar) _$_findCachedViewById(C4188R.id.voice_interval_degree)).setIndicatorTextFormat("${TICK_TEXT}");
            int timeInterval = TtsVoiceHelper.INSTANCE.getTimeInterval(this.customVoiceType);
            ArrayList<String> arrayList = this.listArray;
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                if (((String) obj).equals(String.valueOf(timeInterval))) {
                    arrayList2.add(obj);
                }
            }
            ArrayList arrayList3 = arrayList2;
            float indexOf = arrayList3.isEmpty() ? 0 : this.listArray.indexOf(arrayList3.get(0));
            ((IndicatorSeekBar) _$_findCachedViewById(C4188R.id.voice_interval_degree)).setProgress((indexOf / (this.listArray.size() - 1)) * 100.0f);
            IndicatorSeekBar indicatorSeekBar2 = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.voice_play_mode_interval_degree);
            Object[] array2 = this.listArrays.toArray(new String[0]);
            if (array2 != null) {
                indicatorSeekBar2.customTickTexts((String[]) array2);
                ((IndicatorSeekBar) _$_findCachedViewById(C4188R.id.voice_play_mode_interval_degree)).setIndicatorTextFormat("${TICK_TEXT}");
                ((IndicatorSeekBar) _$_findCachedViewById(C4188R.id.voice_play_mode_interval_degree)).setProgress((indexOf / (this.listArray.size() - 1)) * 100.0f);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public final ArrayList<String> getListArray() {
        return this.listArray;
    }

    public final ArrayList<String> getListArrays() {
        return this.listArrays;
    }

    private final void initCruiseVoiceInterval() {
        Pdlog.m3273d(this.TAG, "initCruiseVoiceInterval");
        initVoice();
        IndicatorSeekBar voice_interval_degree = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.voice_interval_degree);
        Intrinsics.checkExpressionValueIsNotNull(voice_interval_degree, "voice_interval_degree");
        voice_interval_degree.setOnSeekChangeListener(new VoiceIndicatorSeekBarChangeListener(null, 0, null, null, new Function1<IndicatorSeekBar, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$initCruiseVoiceInterval$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(IndicatorSeekBar indicatorSeekBar) {
                invoke2(indicatorSeekBar);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IndicatorSeekBar indicatorSeekBar) {
                String str;
                TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                if (indicatorSeekBar != null) {
                    int round = Math.round((indicatorSeekBar.getProgressFloat() / 100.0f) * (VoiceFragment.this.getListArray().size() - 1));
                    str = VoiceFragment.this.TAG;
                    Pdlog.m3275i(str, "voice_interval_degree onStopTrackingTouch:  " + indicatorSeekBar.getProgressFloat() + ' ' + round + ' ' + VoiceFragment.this.getListArray().get(round));
                    TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                    String str2 = VoiceFragment.this.getListArray().get(round);
                    Intrinsics.checkExpressionValueIsNotNull(str2, "listArray[index]");
                    ttsVoiceType = VoiceFragment.this.customVoiceType;
                    ttsVoiceHelper.setTimeInterval(str2, ttsVoiceType);
                }
            }
        }, 15, null));
        IndicatorSeekBar voice_play_mode_interval_degree = (IndicatorSeekBar) _$_findCachedViewById(C4188R.id.voice_play_mode_interval_degree);
        Intrinsics.checkExpressionValueIsNotNull(voice_play_mode_interval_degree, "voice_play_mode_interval_degree");
        voice_play_mode_interval_degree.setOnSeekChangeListener(new VoiceIndicatorSeekBarChangeListener(null, 0, null, null, new Function1<IndicatorSeekBar, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$initCruiseVoiceInterval$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(IndicatorSeekBar indicatorSeekBar) {
                invoke2(indicatorSeekBar);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IndicatorSeekBar indicatorSeekBar) {
                String str;
                TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                if (indicatorSeekBar != null) {
                    int round = Math.round((indicatorSeekBar.getProgressFloat() / 100.0f) * (VoiceFragment.this.getListArray().size() - 1));
                    str = VoiceFragment.this.TAG;
                    Pdlog.m3275i(str, "voice_play_mode_interval_degree onStopTrackingTouch:  " + indicatorSeekBar.getProgressFloat() + ' ' + round + ' ' + VoiceFragment.this.getListArray().get(round));
                    TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                    String str2 = VoiceFragment.this.getListArray().get(round);
                    Intrinsics.checkExpressionValueIsNotNull(str2, "listArray[index]");
                    ttsVoiceType = VoiceFragment.this.customVoiceType;
                    ttsVoiceHelper.setTimeInterval(str2, ttsVoiceType);
                }
            }
        }, 15, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTTsViewStatus() {
        TextView voice_name_tv = (TextView) _$_findCachedViewById(C4188R.id.voice_name_tv);
        Intrinsics.checkExpressionValueIsNotNull(voice_name_tv, "voice_name_tv");
        voice_name_tv.setText(this.voicePackageHelper.getCurrentVoiceName());
        if (((LinearLayout) _$_findCachedViewById(C4188R.id.tts_voice_root)) == null) {
            Pdlog.m3273d(this.TAG, "setTTsViewStatus() custom_voice_title_ly is null");
            return;
        }
        boolean isSelectMerchantTts = VoicePackageHelper.INSTANCE.isSelectMerchantTts();
        int i = isSelectMerchantTts ? 8 : 0;
        LinearLayout tts_voice_root = (LinearLayout) _$_findCachedViewById(C4188R.id.tts_voice_root);
        Intrinsics.checkExpressionValueIsNotNull(tts_voice_root, "tts_voice_root");
        if (i == tts_voice_root.getVisibility()) {
            Pdlog.m3273d(this.TAG, "setTTsViewStatus() status is same");
            return;
        }
        LinearLayout tts_voice_root2 = (LinearLayout) _$_findCachedViewById(C4188R.id.tts_voice_root);
        Intrinsics.checkExpressionValueIsNotNull(tts_voice_root2, "tts_voice_root");
        tts_voice_root2.setVisibility(i);
        Pdlog.m3273d(this.TAG, "setTTsViewStatus isGone =" + isSelectMerchantTts);
    }

    private final void checkLanguage() {
        if (!LanguageUtils.INSTANCE.isNoSupportTts()) {
            RelativeLayout cruise_voice_layout = (RelativeLayout) _$_findCachedViewById(C4188R.id.cruise_voice_layout);
            Intrinsics.checkExpressionValueIsNotNull(cruise_voice_layout, "cruise_voice_layout");
            cruise_voice_layout.setVisibility(0);
            CardView tts_cruise_voice_layout = (CardView) _$_findCachedViewById(C4188R.id.tts_cruise_voice_layout);
            Intrinsics.checkExpressionValueIsNotNull(tts_cruise_voice_layout, "tts_cruise_voice_layout");
            tts_cruise_voice_layout.setVisibility(0);
            TextView tv_tts_speaker = (TextView) _$_findCachedViewById(C4188R.id.tv_tts_speaker);
            Intrinsics.checkExpressionValueIsNotNull(tv_tts_speaker, "tv_tts_speaker");
            tv_tts_speaker.setVisibility(0);
            CardView tts_cruise_voice_tts = (CardView) _$_findCachedViewById(C4188R.id.tts_cruise_voice_tts);
            Intrinsics.checkExpressionValueIsNotNull(tts_cruise_voice_tts, "tts_cruise_voice_tts");
            tts_cruise_voice_tts.setVisibility(0);
            View tts_voice_line = _$_findCachedViewById(C4188R.id.tts_voice_line);
            Intrinsics.checkExpressionValueIsNotNull(tts_voice_line, "tts_voice_line");
            tts_voice_line.setVisibility(0);
            if (LanguageUtils.INSTANCE.isSupportCustomSpeaker()) {
                TextView tv_tts_speaker2 = (TextView) _$_findCachedViewById(C4188R.id.tv_tts_speaker);
                Intrinsics.checkExpressionValueIsNotNull(tv_tts_speaker2, "tv_tts_speaker");
                tv_tts_speaker2.setVisibility(0);
                CardView tts_cruise_voice_tts2 = (CardView) _$_findCachedViewById(C4188R.id.tts_cruise_voice_tts);
                Intrinsics.checkExpressionValueIsNotNull(tts_cruise_voice_tts2, "tts_cruise_voice_tts");
                tts_cruise_voice_tts2.setVisibility(0);
                return;
            }
            TextView tv_tts_speaker3 = (TextView) _$_findCachedViewById(C4188R.id.tv_tts_speaker);
            Intrinsics.checkExpressionValueIsNotNull(tv_tts_speaker3, "tv_tts_speaker");
            tv_tts_speaker3.setVisibility(8);
            CardView tts_cruise_voice_tts3 = (CardView) _$_findCachedViewById(C4188R.id.tts_cruise_voice_tts);
            Intrinsics.checkExpressionValueIsNotNull(tts_cruise_voice_tts3, "tts_cruise_voice_tts");
            tts_cruise_voice_tts3.setVisibility(8);
            return;
        }
        TextView custom_voice_title = (TextView) _$_findCachedViewById(C4188R.id.custom_voice_title);
        Intrinsics.checkExpressionValueIsNotNull(custom_voice_title, "custom_voice_title");
        custom_voice_title.setVisibility(8);
        LinearLayout cruise_voice_play_interval_layout = (LinearLayout) _$_findCachedViewById(C4188R.id.cruise_voice_play_interval_layout);
        Intrinsics.checkExpressionValueIsNotNull(cruise_voice_play_interval_layout, "cruise_voice_play_interval_layout");
        cruise_voice_play_interval_layout.setVisibility(8);
        RelativeLayout cruise_voice_layout2 = (RelativeLayout) _$_findCachedViewById(C4188R.id.cruise_voice_layout);
        Intrinsics.checkExpressionValueIsNotNull(cruise_voice_layout2, "cruise_voice_layout");
        cruise_voice_layout2.setVisibility(8);
        CardView tts_cruise_voice_layout2 = (CardView) _$_findCachedViewById(C4188R.id.tts_cruise_voice_layout);
        Intrinsics.checkExpressionValueIsNotNull(tts_cruise_voice_layout2, "tts_cruise_voice_layout");
        tts_cruise_voice_layout2.setVisibility(8);
        TextView tv_tts_speaker4 = (TextView) _$_findCachedViewById(C4188R.id.tv_tts_speaker);
        Intrinsics.checkExpressionValueIsNotNull(tv_tts_speaker4, "tv_tts_speaker");
        tv_tts_speaker4.setVisibility(8);
        CardView tts_cruise_voice_tts4 = (CardView) _$_findCachedViewById(C4188R.id.tts_cruise_voice_tts);
        Intrinsics.checkExpressionValueIsNotNull(tts_cruise_voice_tts4, "tts_cruise_voice_tts");
        tts_cruise_voice_tts4.setVisibility(8);
        View tts_voice_line2 = _$_findCachedViewById(C4188R.id.tts_voice_line);
        Intrinsics.checkExpressionValueIsNotNull(tts_voice_line2, "tts_voice_line");
        tts_voice_line2.setVisibility(8);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    private final void initVoicePack() {
        Pdlog.m3273d(this.TAG, "initVoicePack");
        ((LinearLayout) _$_findCachedViewById(C4188R.id.voice_mode_skip)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$initVoicePack$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VoiceModeDialog voiceModeDialog;
                VoiceModeDialog voiceModeDialog2;
                Activity mActivity;
                VoiceModeDialog voiceModeDialog3;
                voiceModeDialog = VoiceFragment.this._voiceModeDialog;
                if (voiceModeDialog == null) {
                    VoiceFragment voiceFragment = VoiceFragment.this;
                    mActivity = voiceFragment.getMActivity();
                    voiceFragment._voiceModeDialog = mActivity != null ? new VoiceModeDialog(mActivity) : null;
                    voiceModeDialog3 = VoiceFragment.this._voiceModeDialog;
                    if (voiceModeDialog3 != null) {
                        voiceModeDialog3.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$initVoicePack$1.2
                            @Override // android.content.DialogInterface.OnDismissListener
                            public final void onDismiss(DialogInterface dialogInterface) {
                                VoiceFragment.this.setTTsViewStatus();
                            }
                        });
                    }
                }
                voiceModeDialog2 = VoiceFragment.this._voiceModeDialog;
                if (voiceModeDialog2 == null || voiceModeDialog2.isShowing()) {
                    return;
                }
                voiceModeDialog2.show();
            }
        });
    }

    public final PopupWindow getMPopupWindow() {
        return this.mPopupWindow;
    }

    public final void setMPopupWindow(PopupWindow popupWindow) {
        this.mPopupWindow = popupWindow;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showChangeCustomTtsPopupWindow(final View view) {
        View inflate = getLayoutInflater().inflate(C4188R.layout.layout_popupwindow_change_curstom_tts, (ViewGroup) null, false);
        this.mPopupWindow = new PopupWindow(inflate, -2, -2, true);
        ((TextView) inflate.findViewById(C4188R.id.select_cruise_tv)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$showChangeCustomTtsPopupWindow$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                Intrinsics.checkParameterIsNotNull(it, "it");
                ttsVoiceType = VoiceFragment.this.customVoiceType;
                if (ttsVoiceType != TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE) {
                    VoiceFragment.this.customVoiceType = TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE;
                    VoiceFragment.this.changeCustomVoiceData();
                    VoiceFragment.this.initVoice();
                }
                PopupWindow mPopupWindow = VoiceFragment.this.getMPopupWindow();
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        }, 3, null));
        ((TextView) inflate.findViewById(C4188R.id.select_birthday_tv)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$showChangeCustomTtsPopupWindow$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                Intrinsics.checkParameterIsNotNull(it, "it");
                ttsVoiceType = VoiceFragment.this.customVoiceType;
                if (ttsVoiceType != TtsVoiceHelper.TtsVoiceType.BIRTHDAY_TYPE) {
                    VoiceFragment.this.customVoiceType = TtsVoiceHelper.TtsVoiceType.BIRTHDAY_TYPE;
                    VoiceFragment.this.changeCustomVoiceData();
                    VoiceFragment.this.initVoice();
                }
                PopupWindow mPopupWindow = VoiceFragment.this.getMPopupWindow();
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        }, 3, null));
        ((TextView) inflate.findViewById(C4188R.id.select_deliver_tv)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$showChangeCustomTtsPopupWindow$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                Intrinsics.checkParameterIsNotNull(it, "it");
                ttsVoiceType = VoiceFragment.this.customVoiceType;
                if (ttsVoiceType != TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE) {
                    VoiceFragment.this.customVoiceType = TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE;
                    VoiceFragment.this.changeCustomVoiceData();
                }
                PopupWindow mPopupWindow = VoiceFragment.this.getMPopupWindow();
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        }, 3, null));
        ((TextView) inflate.findViewById(C4188R.id.select_greeter_tv)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$showChangeCustomTtsPopupWindow$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                Intrinsics.checkParameterIsNotNull(it, "it");
                ttsVoiceType = VoiceFragment.this.customVoiceType;
                if (ttsVoiceType != TtsVoiceHelper.TtsVoiceType.GREETER_TYPE) {
                    VoiceFragment.this.customVoiceType = TtsVoiceHelper.TtsVoiceType.GREETER_TYPE;
                    VoiceFragment.this.changeCustomVoiceData();
                    VoiceFragment.this.initVoice();
                }
                PopupWindow mPopupWindow = VoiceFragment.this.getMPopupWindow();
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        }, 3, null));
        ((TextView) inflate.findViewById(C4188R.id.select_greeter_guide_tv)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$showChangeCustomTtsPopupWindow$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                Intrinsics.checkParameterIsNotNull(it, "it");
                ttsVoiceType = VoiceFragment.this.customVoiceType;
                if (ttsVoiceType != TtsVoiceHelper.TtsVoiceType.GREETER_GUIDE_TYPE) {
                    VoiceFragment.this.customVoiceType = TtsVoiceHelper.TtsVoiceType.GREETER_GUIDE_TYPE;
                    VoiceFragment.this.changeCustomVoiceData();
                    VoiceFragment.this.initVoice();
                }
                PopupWindow mPopupWindow = VoiceFragment.this.getMPopupWindow();
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        }, 3, null));
        ((TextView) inflate.findViewById(C4188R.id.select_guide_arrival_tv)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$showChangeCustomTtsPopupWindow$6
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                Intrinsics.checkParameterIsNotNull(it, "it");
                ttsVoiceType = VoiceFragment.this.customVoiceType;
                if (ttsVoiceType != TtsVoiceHelper.TtsVoiceType.GUIDE_ARRIVAL_TYPE) {
                    VoiceFragment.this.customVoiceType = TtsVoiceHelper.TtsVoiceType.GUIDE_ARRIVAL_TYPE;
                    VoiceFragment.this.changeCustomVoiceData();
                    VoiceFragment.this.initVoice();
                }
                PopupWindow mPopupWindow = VoiceFragment.this.getMPopupWindow();
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        }, 3, null));
        ((TextView) inflate.findViewById(C4188R.id.select_special_arrive_tv)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$showChangeCustomTtsPopupWindow$7
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                Intrinsics.checkParameterIsNotNull(it, "it");
                ttsVoiceType = VoiceFragment.this.customVoiceType;
                if (ttsVoiceType != TtsVoiceHelper.TtsVoiceType.SPECIAL_MODE_ARRIVE) {
                    VoiceFragment.this.customVoiceType = TtsVoiceHelper.TtsVoiceType.SPECIAL_MODE_ARRIVE;
                    VoiceFragment.this.changeCustomVoiceData();
                    VoiceFragment.this.initVoice();
                }
                PopupWindow mPopupWindow = VoiceFragment.this.getMPopupWindow();
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        }, 3, null));
        PopupWindow popupWindow = this.mPopupWindow;
        if (popupWindow != null) {
            NavigationBarUtil.hideNavigationBar(popupWindow.getContentView());
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$showChangeCustomTtsPopupWindow$$inlined$apply$lambda$1
                @Override // android.widget.PopupWindow.OnDismissListener
                public final void onDismiss() {
                    VoiceFragment.this.bgAlpha(1.0f);
                }
            });
            popupWindow.setOutsideTouchable(true);
            int[] iArr = new int[2];
            view.getLocationInWindow(iArr);
            String str = this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("showChangeCustomTtsPopupWindow Location: ");
            String arrays = Arrays.toString(iArr);
            Intrinsics.checkExpressionValueIsNotNull(arrays, "java.util.Arrays.toString(this)");
            sb.append(arrays);
            Pdlog.m3273d(str, sb.toString());
            if (CommonExtKt.dp2px(view, 270.0f) < iArr[1]) {
                popupWindow.showAsDropDown(view, 0, CommonExtKt.dp2px(view, 270.0f) - iArr[1]);
            } else {
                popupWindow.showAsDropDown(view, 0, 5);
            }
        }
        bgAlpha(0.5f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void bgAlpha(float f) {
        if (getActivity() == null) {
            return;
        }
        Activity mActivity = getMActivity();
        if (mActivity == null) {
            Intrinsics.throwNpe();
        }
        Window window = mActivity.getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window, "mActivity!!.window");
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = f;
        Activity mActivity2 = getMActivity();
        if (mActivity2 == null) {
            Intrinsics.throwNpe();
        }
        Window window2 = mActivity2.getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window2, "mActivity!!.window");
        window2.setAttributes(attributes);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDeletePopupWindow(View view, final View.OnClickListener onClickListener) {
        View inflate = getLayoutInflater().inflate(C4188R.layout.layout_popupwindow_delete, (ViewGroup) null, false);
        final PopupWindow popupWindow = new PopupWindow(inflate, -2, -2, false);
        inflate.setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$showDeletePopupWindow$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick(View v) {
                Intrinsics.checkParameterIsNotNull(v, "v");
                super.onSingleClick(v);
                onClickListener.onClick(v);
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void showCustomInputDialog() {
        if (TtsVoiceHelper.INSTANCE.getTtsConfigList(this.customVoiceType).size() >= 5) {
            ToastUtils.show(RobotContext.INSTANCE.getContext(), getString(C4188R.string.pdStr7_94), new Object[0]);
            return;
        }
        CustomTtsVoiceInputDialog customTtsVoiceInputDialog = new CustomTtsVoiceInputDialog();
        customTtsVoiceInputDialog.setMaxLength(200);
        customTtsVoiceInputDialog.setVoiceType(this.customVoiceType);
        customTtsVoiceInputDialog.show(getParentFragmentManager(), "voice_custom");
        customTtsVoiceInputDialog.setOnContentChange(new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$showCustomInputDialog$1
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

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Pdlog.m3273d(this.TAG, "onDestroyView");
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        Pdlog.m3273d(this.TAG, "onDetach");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void changeCustomVoiceData() {
        if (((LinearLayout) _$_findCachedViewById(C4188R.id.voice_play_mode_ll)) != null) {
            LinearLayout voice_play_mode_ll = (LinearLayout) _$_findCachedViewById(C4188R.id.voice_play_mode_ll);
            Intrinsics.checkExpressionValueIsNotNull(voice_play_mode_ll, "voice_play_mode_ll");
            voice_play_mode_ll.setVisibility(8);
        }
        switch (this.customVoiceType) {
            case CRUISE_TYPE:
                TtsVoiceAdapter ttsVoiceAdapter = this.ttsVoiceAdapter;
                if (ttsVoiceAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
                }
                ttsVoiceAdapter.setNewData(TtsVoiceHelper.INSTANCE.getTtsConfigList(this.customVoiceType));
                LinearLayout cruise_voice_play_interval_layout = (LinearLayout) _$_findCachedViewById(C4188R.id.cruise_voice_play_interval_layout);
                Intrinsics.checkExpressionValueIsNotNull(cruise_voice_play_interval_layout, "cruise_voice_play_interval_layout");
                cruise_voice_play_interval_layout.setVisibility(0);
                TextView cruise_context_tv = (TextView) _$_findCachedViewById(C4188R.id.cruise_context_tv);
                Intrinsics.checkExpressionValueIsNotNull(cruise_context_tv, "cruise_context_tv");
                cruise_context_tv.setText(getString(C4188R.string.pdStr7_65));
                TextView custom_voice_title = (TextView) _$_findCachedViewById(C4188R.id.custom_voice_title);
                Intrinsics.checkExpressionValueIsNotNull(custom_voice_title, "custom_voice_title");
                custom_voice_title.setText(getString(C4188R.string.pdStr7_64));
                break;
            case DELIVER_TYPE:
                TtsVoiceAdapter ttsVoiceAdapter2 = this.ttsVoiceAdapter;
                if (ttsVoiceAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
                }
                ttsVoiceAdapter2.setNewData(TtsVoiceHelper.INSTANCE.getTtsConfigList(this.customVoiceType));
                LinearLayout cruise_voice_play_interval_layout2 = (LinearLayout) _$_findCachedViewById(C4188R.id.cruise_voice_play_interval_layout);
                Intrinsics.checkExpressionValueIsNotNull(cruise_voice_play_interval_layout2, "cruise_voice_play_interval_layout");
                cruise_voice_play_interval_layout2.setVisibility(8);
                TextView cruise_context_tv2 = (TextView) _$_findCachedViewById(C4188R.id.cruise_context_tv);
                Intrinsics.checkExpressionValueIsNotNull(cruise_context_tv2, "cruise_context_tv");
                cruise_context_tv2.setText(getString(C4188R.string.pdStr7_122));
                TextView custom_voice_title2 = (TextView) _$_findCachedViewById(C4188R.id.custom_voice_title);
                Intrinsics.checkExpressionValueIsNotNull(custom_voice_title2, "custom_voice_title");
                custom_voice_title2.setText(getString(C4188R.string.pdStr7_121));
                break;
            case GREETER_TYPE:
                TtsVoiceAdapter ttsVoiceAdapter3 = this.ttsVoiceAdapter;
                if (ttsVoiceAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
                }
                ttsVoiceAdapter3.setNewData(TtsVoiceHelper.INSTANCE.getTtsConfigList(this.customVoiceType));
                LinearLayout cruise_voice_play_interval_layout3 = (LinearLayout) _$_findCachedViewById(C4188R.id.cruise_voice_play_interval_layout);
                Intrinsics.checkExpressionValueIsNotNull(cruise_voice_play_interval_layout3, "cruise_voice_play_interval_layout");
                cruise_voice_play_interval_layout3.setVisibility(0);
                TextView cruise_context_tv3 = (TextView) _$_findCachedViewById(C4188R.id.cruise_context_tv);
                Intrinsics.checkExpressionValueIsNotNull(cruise_context_tv3, "cruise_context_tv");
                cruise_context_tv3.setText(getString(C4188R.string.pdStr7_191));
                TextView custom_voice_title3 = (TextView) _$_findCachedViewById(C4188R.id.custom_voice_title);
                Intrinsics.checkExpressionValueIsNotNull(custom_voice_title3, "custom_voice_title");
                custom_voice_title3.setText(getString(C4188R.string.pdStr7_190));
                if (LanguageUtils.INSTANCE.isZh()) {
                    LinearLayout voice_play_mode_ll2 = (LinearLayout) _$_findCachedViewById(C4188R.id.voice_play_mode_ll);
                    Intrinsics.checkExpressionValueIsNotNull(voice_play_mode_ll2, "voice_play_mode_ll");
                    voice_play_mode_ll2.setVisibility(0);
                    LinearLayout cruise_voice_play_interval_layout4 = (LinearLayout) _$_findCachedViewById(C4188R.id.cruise_voice_play_interval_layout);
                    Intrinsics.checkExpressionValueIsNotNull(cruise_voice_play_interval_layout4, "cruise_voice_play_interval_layout");
                    cruise_voice_play_interval_layout4.setVisibility(8);
                    break;
                }
                break;
            case GREETER_GUIDE_TYPE:
                TtsVoiceAdapter ttsVoiceAdapter4 = this.ttsVoiceAdapter;
                if (ttsVoiceAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
                }
                ttsVoiceAdapter4.setNewData(TtsVoiceHelper.INSTANCE.getTtsConfigList(this.customVoiceType));
                LinearLayout cruise_voice_play_interval_layout5 = (LinearLayout) _$_findCachedViewById(C4188R.id.cruise_voice_play_interval_layout);
                Intrinsics.checkExpressionValueIsNotNull(cruise_voice_play_interval_layout5, "cruise_voice_play_interval_layout");
                cruise_voice_play_interval_layout5.setVisibility(0);
                TextView cruise_context_tv4 = (TextView) _$_findCachedViewById(C4188R.id.cruise_context_tv);
                Intrinsics.checkExpressionValueIsNotNull(cruise_context_tv4, "cruise_context_tv");
                cruise_context_tv4.setText(getString(C4188R.string.pdStr7_193));
                TextView custom_voice_title4 = (TextView) _$_findCachedViewById(C4188R.id.custom_voice_title);
                Intrinsics.checkExpressionValueIsNotNull(custom_voice_title4, "custom_voice_title");
                custom_voice_title4.setText(getString(C4188R.string.pdStr7_192));
                break;
            case GUIDE_ARRIVAL_TYPE:
                TtsVoiceAdapter ttsVoiceAdapter5 = this.ttsVoiceAdapter;
                if (ttsVoiceAdapter5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
                }
                ttsVoiceAdapter5.setNewData(TtsVoiceHelper.INSTANCE.getTtsConfigList(this.customVoiceType));
                LinearLayout cruise_voice_play_interval_layout6 = (LinearLayout) _$_findCachedViewById(C4188R.id.cruise_voice_play_interval_layout);
                Intrinsics.checkExpressionValueIsNotNull(cruise_voice_play_interval_layout6, "cruise_voice_play_interval_layout");
                cruise_voice_play_interval_layout6.setVisibility(8);
                TextView cruise_context_tv5 = (TextView) _$_findCachedViewById(C4188R.id.cruise_context_tv);
                Intrinsics.checkExpressionValueIsNotNull(cruise_context_tv5, "cruise_context_tv");
                cruise_context_tv5.setText(getString(C4188R.string.tts_guide_arrival));
                TextView custom_voice_title5 = (TextView) _$_findCachedViewById(C4188R.id.custom_voice_title);
                Intrinsics.checkExpressionValueIsNotNull(custom_voice_title5, "custom_voice_title");
                custom_voice_title5.setText(getString(C4188R.string.setting_tts_guide_arrival));
                break;
            case SPECIAL_MODE_ARRIVE:
                TtsVoiceAdapter ttsVoiceAdapter6 = this.ttsVoiceAdapter;
                if (ttsVoiceAdapter6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
                }
                ttsVoiceAdapter6.setNewData(TtsVoiceHelper.INSTANCE.getTtsConfigList(this.customVoiceType));
                LinearLayout cruise_voice_play_interval_layout7 = (LinearLayout) _$_findCachedViewById(C4188R.id.cruise_voice_play_interval_layout);
                Intrinsics.checkExpressionValueIsNotNull(cruise_voice_play_interval_layout7, "cruise_voice_play_interval_layout");
                cruise_voice_play_interval_layout7.setVisibility(8);
                TextView cruise_context_tv6 = (TextView) _$_findCachedViewById(C4188R.id.cruise_context_tv);
                Intrinsics.checkExpressionValueIsNotNull(cruise_context_tv6, "cruise_context_tv");
                cruise_context_tv6.setText(getString(C4188R.string.pdStr7_197));
                TextView custom_voice_title6 = (TextView) _$_findCachedViewById(C4188R.id.custom_voice_title);
                Intrinsics.checkExpressionValueIsNotNull(custom_voice_title6, "custom_voice_title");
                custom_voice_title6.setText(getString(C4188R.string.pdStr7_196));
                break;
            case BIRTHDAY_TYPE:
                TtsVoiceAdapter ttsVoiceAdapter7 = this.ttsVoiceAdapter;
                if (ttsVoiceAdapter7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
                }
                ttsVoiceAdapter7.setNewData(TtsVoiceHelper.INSTANCE.getTtsConfigList(this.customVoiceType));
                LinearLayout cruise_voice_play_interval_layout8 = (LinearLayout) _$_findCachedViewById(C4188R.id.cruise_voice_play_interval_layout);
                Intrinsics.checkExpressionValueIsNotNull(cruise_voice_play_interval_layout8, "cruise_voice_play_interval_layout");
                cruise_voice_play_interval_layout8.setVisibility(8);
                TextView cruise_context_tv7 = (TextView) _$_findCachedViewById(C4188R.id.cruise_context_tv);
                Intrinsics.checkExpressionValueIsNotNull(cruise_context_tv7, "cruise_context_tv");
                cruise_context_tv7.setText(getString(C4188R.string.birthday_arrival_voice));
                TextView custom_voice_title7 = (TextView) _$_findCachedViewById(C4188R.id.custom_voice_title);
                Intrinsics.checkExpressionValueIsNotNull(custom_voice_title7, "custom_voice_title");
                custom_voice_title7.setText(getString(C4188R.string.birthday_customized_voice_arrival));
                break;
        }
        Switch open_cruise_switch = (Switch) _$_findCachedViewById(C4188R.id.open_cruise_switch);
        Intrinsics.checkExpressionValueIsNotNull(open_cruise_switch, "open_cruise_switch");
        TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
        Context mContext = getMContext();
        if (mContext == null) {
            Intrinsics.throwNpe();
        }
        open_cruise_switch.setChecked(ttsVoiceHelper.checkTtsOpenType(mContext, this.customVoiceType) == TtsVoiceHelper.TtsVoiceOpenType.OPEN);
        TtsVoiceAdapter ttsVoiceAdapter8 = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        ttsVoiceAdapter8.setType(this.customVoiceType);
    }

    private final void initCustomVoiceView() {
        ((TextView) _$_findCachedViewById(C4188R.id.custom_voice_title)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$initCustomVoiceView$1
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
                VoiceFragment voiceFragment = VoiceFragment.this;
                TextView custom_voice_title = (TextView) voiceFragment._$_findCachedViewById(C4188R.id.custom_voice_title);
                Intrinsics.checkExpressionValueIsNotNull(custom_voice_title, "custom_voice_title");
                voiceFragment.showChangeCustomTtsPopupWindow(custom_voice_title);
            }
        }, 3, null));
        Context mContext = getMContext();
        if (mContext == null) {
            Intrinsics.throwNpe();
        }
        this.selectSpeakerAdapter = new SelectSpeakerAdapter(mContext);
        MaxHeightRecyclerView tts_speaker_recycler_view = (MaxHeightRecyclerView) _$_findCachedViewById(C4188R.id.tts_speaker_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(tts_speaker_recycler_view, "tts_speaker_recycler_view");
        tts_speaker_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        MaxHeightRecyclerView tts_speaker_recycler_view2 = (MaxHeightRecyclerView) _$_findCachedViewById(C4188R.id.tts_speaker_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(tts_speaker_recycler_view2, "tts_speaker_recycler_view");
        SelectSpeakerAdapter selectSpeakerAdapter = this.selectSpeakerAdapter;
        if (selectSpeakerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectSpeakerAdapter");
        }
        tts_speaker_recycler_view2.setAdapter(selectSpeakerAdapter);
        SelectSpeakerAdapter selectSpeakerAdapter2 = this.selectSpeakerAdapter;
        if (selectSpeakerAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectSpeakerAdapter");
        }
        selectSpeakerAdapter2.setNewData(TtsVoiceHelper.INSTANCE.getSpeakers());
        SelectSpeakerAdapter selectSpeakerAdapter3 = this.selectSpeakerAdapter;
        if (selectSpeakerAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectSpeakerAdapter");
        }
        selectSpeakerAdapter3.setPlaying(new Function1<SelectSpeakerAdapter.SpeakerData, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$initCustomVoiceView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SelectSpeakerAdapter.SpeakerData speakerData) {
                invoke2(speakerData);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SelectSpeakerAdapter.SpeakerData it) {
                boolean z;
                String cruiseTempFilePath;
                Intrinsics.checkParameterIsNotNull(it, "it");
                z = VoiceFragment.this.switchSpeaker;
                if (!z) {
                    String string = VoiceFragment.this.getString(C4188R.string.text_temp_voice);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.text_temp_voice)");
                    if (LanguageUtils.INSTANCE.isZh() || LanguageUtils.INSTANCE.isZhTw()) {
                        cruiseTempFilePath = TtsVoiceHelper.INSTANCE.getCruiseTempFilePath(RobotContext.INSTANCE.getContext());
                    } else {
                        cruiseTempFilePath = TtsVoiceFlHelper.INSTANCE.getTempPlayFilePath(RobotContext.INSTANCE.getContext());
                    }
                    TtsVoiceHelper.INSTANCE.genTtsVoice(string, cruiseTempFilePath, it.getSpeaker(), new OnTtsListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$initCustomVoiceView$2.1
                        @Override // com.pudutech.tts_sdk.tts.OnTtsListener
                        public void onProgress(int proses) {
                        }

                        @Override // com.pudutech.tts_sdk.tts.OnTtsListener
                        public void onComplete(String filePath) {
                            Intrinsics.checkParameterIsNotNull(filePath, "filePath");
                            TtsVoiceHelper.INSTANCE.playTempCruiseTts();
                        }

                        @Override // com.pudutech.tts_sdk.tts.OnTtsListener
                        public void onError(int code, String msg) {
                            Intrinsics.checkParameterIsNotNull(msg, "msg");
                            if (VoiceFragment.this.isVisible()) {
                                Context context = RobotContext.INSTANCE.getContext();
                                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                                String string2 = RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr7_74);
                                Intrinsics.checkExpressionValueIsNotNull(string2, "RobotContext.context.getString(R.string.pdStr7_74)");
                                Object[] objArr = {msg};
                                String format = String.format(string2, Arrays.copyOf(objArr, objArr.length));
                                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                                ToastUtils.show(context, format, new Object[0]);
                            }
                        }
                    });
                    return;
                }
                ToastUtils.show(VoiceFragment.this.getContext(), VoiceFragment.this.getString(C4188R.string.pdStr7_199), new Object[0]);
            }
        });
        SelectSpeakerAdapter selectSpeakerAdapter4 = this.selectSpeakerAdapter;
        if (selectSpeakerAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectSpeakerAdapter");
        }
        selectSpeakerAdapter4.setSelectSpeaker(new Function1<SelectSpeakerAdapter.SpeakerData, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$initCustomVoiceView$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SelectSpeakerAdapter.SpeakerData speakerData) {
                invoke2(speakerData);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SelectSpeakerAdapter.SpeakerData it) {
                boolean z;
                Intrinsics.checkParameterIsNotNull(it, "it");
                z = VoiceFragment.this.switchSpeaker;
                if (!z) {
                    TtsVoiceHelper.INSTANCE.setSpeaker(it.getSpeaker(), new TtsVoiceHelper.SetSpeakerListern() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$initCustomVoiceView$3.1
                        @Override // com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper.SetSpeakerListern
                        public void success() {
                            String str;
                            VoiceFragment.this.switchSpeaker = false;
                            VoiceFragment.access$getSelectSpeakerAdapter$p(VoiceFragment.this).setNewData(TtsVoiceHelper.INSTANCE.getSpeakers());
                            VoiceFragment.this.dismissLoadDialog();
                            str = VoiceFragment.this.TAG;
                            Pdlog.m3273d(str, "SetSpeakerListern##success()");
                            ToastUtils.show(BaseApplication.INSTANCE.getInstance(), BaseApplication.INSTANCE.getInstance().getString(C4188R.string.pdStr7_201), new Object[0]);
                        }

                        @Override // com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper.SetSpeakerListern
                        public void doing() {
                            String str;
                            VoiceFragment.this.showLoadDialog(VoiceFragment.this.getString(C4188R.string.dialog_swith_lora_channel));
                            VoiceFragment.this.switchSpeaker = true;
                            VoiceFragment.access$getSelectSpeakerAdapter$p(VoiceFragment.this).notifyDataSetChanged();
                            str = VoiceFragment.this.TAG;
                            Pdlog.m3273d(str, "SetSpeakerListern##doing()");
                        }

                        @Override // com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper.SetSpeakerListern
                        public void error() {
                            String str;
                            VoiceFragment.this.switchSpeaker = false;
                            VoiceFragment.access$getSelectSpeakerAdapter$p(VoiceFragment.this).setNewData(TtsVoiceHelper.INSTANCE.getSpeakers());
                            VoiceFragment.this.dismissLoadDialog();
                            str = VoiceFragment.this.TAG;
                            Pdlog.m3273d(str, "SetSpeakerListern##error()");
                            ToastUtils.show(BaseApplication.INSTANCE.getInstance(), BaseApplication.INSTANCE.getInstance().getString(C4188R.string.pdStr7_200), new Object[0]);
                        }
                    });
                } else {
                    ToastUtils.show(VoiceFragment.this.getContext(), VoiceFragment.this.getString(C4188R.string.pdStr7_199), new Object[0]);
                }
            }
        });
        Context mContext2 = getMContext();
        if (mContext2 == null) {
            Intrinsics.throwNpe();
        }
        this.ttsVoiceAdapter = new TtsVoiceAdapter(mContext2);
        MaxHeightRecyclerView tts_cruise_voice_recycler_view = (MaxHeightRecyclerView) _$_findCachedViewById(C4188R.id.tts_cruise_voice_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(tts_cruise_voice_recycler_view, "tts_cruise_voice_recycler_view");
        tts_cruise_voice_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        MaxHeightRecyclerView tts_cruise_voice_recycler_view2 = (MaxHeightRecyclerView) _$_findCachedViewById(C4188R.id.tts_cruise_voice_recycler_view);
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
        ttsVoiceAdapter2.setNewData(TtsVoiceHelper.INSTANCE.getTtsConfigList(this.customVoiceType));
        TtsVoiceAdapter ttsVoiceAdapter3 = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        ttsVoiceAdapter3.setType(this.customVoiceType);
        TtsVoiceHelper.INSTANCE.setOnChangeListener(new Function1<TtsVoiceHelper.TtsVoiceType, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$initCustomVoiceView$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TtsVoiceHelper.TtsVoiceType ttsVoiceType) {
                invoke2(ttsVoiceType);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TtsVoiceHelper.TtsVoiceType it) {
                TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (VoiceFragment.this.getContext() != null) {
                    ttsVoiceType = VoiceFragment.this.customVoiceType;
                    if (ttsVoiceType == it) {
                        VoiceFragment.access$getTtsVoiceAdapter$p(VoiceFragment.this).notifyDataSetChanged();
                    }
                }
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.add_voice_tv)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$initCustomVoiceView$5
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
                Boolean isGoInput = InputMethodUtil.isGoInput(VoiceFragment.this.getMContext());
                Intrinsics.checkExpressionValueIsNotNull(isGoInput, "InputMethodUtil.isGoInput(mContext)");
                if (!isGoInput.booleanValue()) {
                    LanguageUtils.Companion companion = LanguageUtils.INSTANCE;
                    Context mContext3 = VoiceFragment.this.getMContext();
                    if (mContext3 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (!companion.isEnglish(mContext3)) {
                        Boolean hasInputMethod = InputMethodUtil.hasInputMethod(VoiceFragment.this.getContext(), "gokey");
                        Intrinsics.checkExpressionValueIsNotNull(hasInputMethod, "InputMethodUtil.hasInput…hodUtil.INPUT_NAME_GOKEY)");
                        if (hasInputMethod.booleanValue()) {
                            str = VoiceFragment.this.TAG;
                            Pdlog.m3273d(str, "has go , to switch go input");
                            InputMethodUtil.setDefaultInputMethod(VoiceFragment.this.getContext(), "gokey");
                            VoiceFragment.this.showCustomInputDialog();
                            return;
                        }
                        VoiceFragment.this.showDownInputTis();
                        return;
                    }
                }
                VoiceFragment.this.showCustomInputDialog();
            }
        }, 3, null));
        TtsVoiceAdapter ttsVoiceAdapter4 = this.ttsVoiceAdapter;
        if (ttsVoiceAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ttsVoiceAdapter");
        }
        ttsVoiceAdapter4.setOnItemLongClickListener(new VoiceItemLongClickListener(null, 0, new VoiceFragment$initCustomVoiceView$6(this), 3, null));
        Switch open_cruise_switch = (Switch) _$_findCachedViewById(C4188R.id.open_cruise_switch);
        Intrinsics.checkExpressionValueIsNotNull(open_cruise_switch, "open_cruise_switch");
        TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
        Context mContext3 = getMContext();
        if (mContext3 == null) {
            Intrinsics.throwNpe();
        }
        open_cruise_switch.setChecked(ttsVoiceHelper.checkTtsOpenType(mContext3, this.customVoiceType) == TtsVoiceHelper.TtsVoiceOpenType.OPEN);
        ((Switch) _$_findCachedViewById(C4188R.id.open_cruise_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$initCustomVoiceView$7
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
                TtsVoiceHelper.TtsVoiceType ttsVoiceType;
                TtsVoiceHelper.TtsVoiceType ttsVoiceType2;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                str = VoiceFragment.this.TAG;
                Pdlog.m3273d(str, "open_cruise_switch " + z);
                if (z) {
                    TtsVoiceHelper ttsVoiceHelper2 = TtsVoiceHelper.INSTANCE;
                    Context mContext4 = VoiceFragment.this.getMContext();
                    if (mContext4 == null) {
                        Intrinsics.throwNpe();
                    }
                    TtsVoiceHelper.TtsVoiceOpenType ttsVoiceOpenType = TtsVoiceHelper.TtsVoiceOpenType.OPEN;
                    ttsVoiceType2 = VoiceFragment.this.customVoiceType;
                    ttsVoiceHelper2.setTtsType(mContext4, ttsVoiceOpenType, ttsVoiceType2);
                    return;
                }
                TtsVoiceHelper ttsVoiceHelper3 = TtsVoiceHelper.INSTANCE;
                Context mContext5 = VoiceFragment.this.getMContext();
                if (mContext5 == null) {
                    Intrinsics.throwNpe();
                }
                TtsVoiceHelper.TtsVoiceOpenType ttsVoiceOpenType2 = TtsVoiceHelper.TtsVoiceOpenType.CLOSE;
                ttsVoiceType = VoiceFragment.this.customVoiceType;
                ttsVoiceHelper3.setTtsType(mContext5, ttsVoiceOpenType2, ttsVoiceType);
            }
        }, 7, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDownInputTis() {
        Pdlog.m3273d(this.TAG, "showDownInputTis");
        if (this.mCustomMsgDialog == null) {
            Activity mActivity = getMActivity();
            if (mActivity == null) {
                Intrinsics.throwNpe();
            }
            this.mCustomMsgDialog = new CustomMsgDialog(mActivity);
            final CustomMsgDialog customMsgDialog = this.mCustomMsgDialog;
            if (customMsgDialog != null) {
                String string = getString(C4188R.string.pdStr7_77);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_77)");
                customMsgDialog.setBtnText(string);
                String string2 = getString(C4188R.string.pdStr7_78);
                Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.pdStr7_78)");
                customMsgDialog.setMsg(string2);
                customMsgDialog.setOnBtn1Click(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$showDownInputTis$$inlined$apply$lambda$1
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
                        str = this.TAG;
                        Pdlog.m3273d(str, "showDownInputTis onBtn1Click");
                        if (!NetStatusUtil.isConnected(CustomMsgDialog.this.getContext())) {
                            ToastUtils.show(CustomMsgDialog.this.getContext(), this.getString(C4188R.string.pdStr7_79), new Object[0]);
                        } else {
                            this.getMViewModel().getFileUpdate();
                        }
                    }
                });
                customMsgDialog.setOnCloseBtnClick(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$showDownInputTis$$inlined$apply$lambda$2
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
                        VoiceFragment.this.showCustomInputDialog();
                    }
                });
            }
        }
        CustomMsgDialog customMsgDialog2 = this.mCustomMsgDialog;
        if (customMsgDialog2 == null || customMsgDialog2.isShowing()) {
            return;
        }
        customMsgDialog2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doDownloadInputMethodFile(final String url, final String md5) {
        Pdlog.m3273d(this.TAG, "doDownloadInputMethodFile " + url);
        Activity mActivity = getMActivity();
        if (mActivity == null) {
            Intrinsics.throwNpe();
        }
        final ShowDownloadingDialog showDownloadingDialog = new ShowDownloadingDialog(mActivity);
        String string = getString(C4188R.string.pdStr7_81);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_81)");
        showDownloadingDialog.setTitle(string);
        showDownloadingDialog.showCancel();
        Context mContext = getMContext();
        if (mContext == null) {
            Intrinsics.throwNpe();
        }
        File cacheDir = mContext.getCacheDir();
        Intrinsics.checkExpressionValueIsNotNull(cacheDir, "mContext!!.cacheDir");
        this.downFilePath = cacheDir.getAbsolutePath() + "/download";
        showDownloadingDialog.setOnCloseBtnClick(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$doDownloadInputMethodFile$1
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
                Disposable disposable;
                str = VoiceFragment.this.TAG;
                Pdlog.m3273d(str, "doDownloadInputMethodFile cancel");
                DownloadManager.getInstance().cancel(url);
                disposable = VoiceFragment.this.installDisposable;
                if (disposable != null) {
                    disposable.dispose();
                }
                VoiceFragment.this.installDisposable = (Disposable) null;
                VoiceFragment.this.showCustomInputDialog();
            }
        });
        showDownloadingDialog.show();
        DownloadManager.getInstance().download(this.downFilePath, url, new DownLoadObserver() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$doDownloadInputMethodFile$2
            @Override // com.pudutech.light_network.download.DownLoadObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                VoiceFragment.this.checkInputMethodDownInfo(this.downloadInfo, showDownloadingDialog, md5);
            }

            @Override // com.pudutech.light_network.download.DownLoadObserver, io.reactivex.Observer
            public void onError(Throwable e) {
                String str;
                String str2;
                Intrinsics.checkParameterIsNotNull(e, "e");
                super.onError(e);
                str = VoiceFragment.this.TAG;
                Pdlog.m3274e(str, "DownloadManager onError");
                str2 = VoiceFragment.this.TAG;
                Pdlog.m3274e(str2, Log.getStackTraceString(e));
                if (VoiceFragment.this.getContext() == null) {
                    return;
                }
                Context context = VoiceFragment.this.getContext();
                if (context == null) {
                    Intrinsics.throwNpe();
                }
                ToastUtils.show(context, VoiceFragment.this.getString(C4188R.string.pdStr7_82), new Object[0]);
                showDownloadingDialog.dismiss();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.pudutech.light_network.download.DownLoadObserver, io.reactivex.Observer
            public void onNext(DownloadInfo downloadInfo) {
                String str;
                Intrinsics.checkParameterIsNotNull(downloadInfo, "downloadInfo");
                super.onNext(downloadInfo);
                str = VoiceFragment.this.TAG;
                Pdlog.m3273d(str, "DownloadManager onNext " + downloadInfo);
                int progress = (int) ((downloadInfo.getProgress() * ((long) 100)) / downloadInfo.getTotal());
                if (progress == 100) {
                    progress = 99;
                }
                showDownloadingDialog.showProgress(String.valueOf(progress));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkInputMethodDownInfo(DownloadInfo downInfo, final ShowDownloadingDialog dialog, final String md5) {
        Pdlog.m3273d(this.TAG, "checkInputMethodDownInfo " + md5 + " , " + downInfo);
        if (downInfo == null) {
            dialog.dismiss();
            Context mContext = getMContext();
            if (mContext == null) {
                Intrinsics.throwNpe();
            }
            ToastUtils.show(mContext, getString(C4188R.string.pdStr7_82), new Object[0]);
            return;
        }
        final File file = new File(this.downFilePath, downInfo.getFileName());
        if (!file.exists()) {
            dialog.dismiss();
            Context mContext2 = getMContext();
            if (mContext2 == null) {
                Intrinsics.throwNpe();
            }
            ToastUtils.show(mContext2, getString(C4188R.string.pdStr7_82), new Object[0]);
            return;
        }
        if (!StringsKt.isBlank(md5)) {
            Pdlog.m3273d(this.TAG, "checkInputMethodDownInfo start check md5  " + md5);
            this.installDisposable = Observable.fromCallable(new Callable<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$checkInputMethodDownInfo$1
                @Override // java.util.concurrent.Callable
                public /* bridge */ /* synthetic */ Object call() {
                    return Boolean.valueOf(call());
                }

                @Override // java.util.concurrent.Callable
                public final boolean call() {
                    String str;
                    String calculateFileMD5 = FileUtil.INSTANCE.calculateFileMD5(file);
                    str = VoiceFragment.this.TAG;
                    Pdlog.m3273d(str, "checkInputMethodDownInfo start check md5 , file md5 = " + md5);
                    String str2 = calculateFileMD5;
                    return ((str2 == null || StringsKt.isBlank(str2)) || (Intrinsics.areEqual(md5, calculateFileMD5) ^ true)) ? false : true;
                }
            }).map(new Function<T, R>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$checkInputMethodDownInfo$2
                @Override // io.reactivex.functions.Function
                public /* bridge */ /* synthetic */ Object apply(Object obj) {
                    return Boolean.valueOf(apply((Boolean) obj));
                }

                public final boolean apply(Boolean it) {
                    String str;
                    String str2;
                    String str3;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    if (it.booleanValue()) {
                        try {
                            Context context = VoiceFragment.this.getContext();
                            if (context != null) {
                                AsyncKt.runOnUiThread(context, new Function1<Context, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$checkInputMethodDownInfo$2.1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Context context2) {
                                        invoke2(context2);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(Context receiver) {
                                        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                                        dialog.dismiss();
                                    }
                                });
                            }
                            VoiceFragment voiceFragment = VoiceFragment.this;
                            String string = VoiceFragment.this.getString(C4188R.string.pdStr7_84);
                            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_84)");
                            voiceFragment.showInstallTip(string);
                            str2 = VoiceFragment.this.TAG;
                            Pdlog.m3273d(str2, "静默安装----》apk路径" + file.getAbsolutePath());
                            int installSilent = PackageUtils.installSilent(RobotContext.INSTANCE.getContext(), file.getAbsolutePath());
                            if (installSilent != 1) {
                                VoiceFragment.this.hideInstallTip();
                                str3 = VoiceFragment.this.TAG;
                                Pdlog.m3274e(str3, "install failed , " + installSilent);
                            } else {
                                Thread.sleep(2000L);
                                VoiceFragment voiceFragment2 = VoiceFragment.this;
                                String string2 = VoiceFragment.this.getString(C4188R.string.pdStr7_85);
                                Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.pdStr7_85)");
                                voiceFragment2.showInstallTip(string2);
                                Thread.sleep(1000L);
                                VoiceFragment.this.hideInstallTip();
                                return true;
                            }
                        } catch (IOException e) {
                            str = VoiceFragment.this.TAG;
                            Pdlog.m3274e(str, Log.getStackTraceString(e));
                        }
                    }
                    return false;
                }
            }).subscribeOn(Schedulers.m3958io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$checkInputMethodDownInfo$3
                @Override // io.reactivex.functions.Consumer
                public final void accept(Boolean bool) {
                    String str;
                    String str2;
                    if (!bool.booleanValue()) {
                        str2 = VoiceFragment.this.TAG;
                        Pdlog.m3273d(str2, "install failed");
                        ToastUtils.show(VoiceFragment.this.getContext(), VoiceFragment.this.getString(C4188R.string.pdStr7_83), new Object[0]);
                        return;
                    }
                    InputMethodUtil.setDefaultInputMethod(VoiceFragment.this.getContext(), "gokey");
                    Boolean isGoInput = InputMethodUtil.isGoInput(VoiceFragment.this.getContext());
                    Intrinsics.checkExpressionValueIsNotNull(isGoInput, "InputMethodUtil.isGoInput(context)");
                    if (isGoInput.booleanValue()) {
                        VoiceFragment.this.showCustomInputDialog();
                    } else {
                        str = VoiceFragment.this.TAG;
                        Pdlog.m3273d(str, "install failed ,InputMethodUtil check failed");
                        ToastUtils.show(VoiceFragment.this.getContext(), VoiceFragment.this.getString(C4188R.string.pdStr7_83), new Object[0]);
                    }
                    dialog.dismiss();
                    VoiceFragment.this.installDisposable = (Disposable) null;
                }
            }, new Consumer<Throwable>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$checkInputMethodDownInfo$4
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    String str;
                    String str2;
                    str = VoiceFragment.this.TAG;
                    Pdlog.m3274e(str, "checkInputMethodDownInfo error");
                    str2 = VoiceFragment.this.TAG;
                    Pdlog.m3274e(str2, Log.getStackTraceString(th));
                    dialog.dismiss();
                    VoiceFragment.this.installDisposable = (Disposable) null;
                }
            });
            return;
        }
        Pdlog.m3274e(this.TAG, "md5 is null ");
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showInstallTip(final String msg) {
        Context context = getContext();
        if (context != null) {
            AsyncKt.runOnUiThread(context, new Function1<Context, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$showInstallTip$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Context context2) {
                    invoke2(context2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Context receiver) {
                    ShowTipMsgDialog showTipMsgDialog;
                    ShowTipMsgDialog showTipMsgDialog2;
                    ShowTipMsgDialog showTipMsgDialog3;
                    ShowTipMsgDialog showTipMsgDialog4;
                    Activity mActivity;
                    Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                    showTipMsgDialog = VoiceFragment.this.installTipDialog;
                    if (showTipMsgDialog == null) {
                        VoiceFragment voiceFragment = VoiceFragment.this;
                        mActivity = voiceFragment.getMActivity();
                        if (mActivity == null) {
                            Intrinsics.throwNpe();
                        }
                        voiceFragment.installTipDialog = new ShowTipMsgDialog(mActivity);
                    }
                    showTipMsgDialog2 = VoiceFragment.this.installTipDialog;
                    if (showTipMsgDialog2 == null) {
                        Intrinsics.throwNpe();
                    }
                    showTipMsgDialog2.showTipMsg(msg);
                    showTipMsgDialog3 = VoiceFragment.this.installTipDialog;
                    if (showTipMsgDialog3 == null) {
                        Intrinsics.throwNpe();
                    }
                    showTipMsgDialog3.setCanCancel(false);
                    showTipMsgDialog4 = VoiceFragment.this.installTipDialog;
                    if (showTipMsgDialog4 == null) {
                        Intrinsics.throwNpe();
                    }
                    showTipMsgDialog4.show();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideInstallTip() {
        Context context = getContext();
        if (context != null) {
            AsyncKt.runOnUiThread(context, new Function1<Context, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VoiceFragment$hideInstallTip$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Context context2) {
                    invoke2(context2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Context receiver) {
                    ShowTipMsgDialog showTipMsgDialog;
                    Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                    showTipMsgDialog = VoiceFragment.this.installTipDialog;
                    if (showTipMsgDialog != null) {
                        showTipMsgDialog.hide();
                    }
                }
            });
        }
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment
    public void release() {
        super.release();
        Pdlog.m3273d(this.TAG, "release");
        TtsVoiceHelper.INSTANCE.setOnChangeListener((Function1) null);
        PopupWindow popupWindow = this.mPopupWindow;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
        TtsVoiceHelper.INSTANCE.stopCruiseTts();
        TtsVoiceHelper.INSTANCE.setMSetSpeakerListern((TtsVoiceHelper.SetSpeakerListern) null);
        TransparentLoadDialog transparentLoadDialog = this._LoadDialog;
        if (transparentLoadDialog != null) {
            transparentLoadDialog.dismiss();
        }
        VoiceModeDialog voiceModeDialog = this._voiceModeDialog;
        if (voiceModeDialog != null) {
            if (voiceModeDialog.isShowing()) {
                voiceModeDialog.dismiss();
            }
            voiceModeDialog.onDestory();
        }
        this._LoadDialog = (TransparentLoadDialog) null;
        this._voiceModeDialog = (VoiceModeDialog) null;
    }
}
