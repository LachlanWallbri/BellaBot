package com.pudutech.peanut.robot_ui.module.setting.p062ui;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.bean.VoiceBean;
import com.pudutech.peanut.robot_ui.config.Constans;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.manager.AiVoiceManager;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.ShowChooseVoiceDialog;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.ShowDownloadingDialog;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.ShowTipMsgDialog;
import com.pudutech.peanut.robot_ui.p063ui.helper.TtsVoiceHelper;
import com.pudutech.peanut.robot_ui.ttsvoices.bean.TtsDownInfo;
import com.pudutech.peanut.robot_ui.viewmodel.TtsVoiceGenVm;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: TtsVoiceFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004H\u0002J\b\u0010\u001a\u001a\u00020\u0017H\u0002J\b\u0010\u001b\u001a\u00020\u0017H\u0002J\u0012\u0010\u001c\u001a\u00020\u00172\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0012\u0010\u001f\u001a\u00020\u00172\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J&\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010&\u001a\u00020\u0017H\u0016J\b\u0010'\u001a\u00020\u0017H\u0002J\u0010\u0010(\u001a\u00020\u00172\u0006\u0010)\u001a\u00020\u0004H\u0002J\b\u0010*\u001a\u00020\u0017H\u0002J\b\u0010+\u001a\u00020\u0017H\u0002J\b\u0010,\u001a\u00020\u0017H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013¨\u0006-"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/TtsVoiceFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "finishTip", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "isChangeVoice", "", "lastPath", "mPath", "mVoiceDialog", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowChooseVoiceDialog;", "ttsDownInfoDialog", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowDownloadingDialog;", "ttsVoiceGenVm", "Lcom/pudutech/peanut/robot_ui/viewmodel/TtsVoiceGenVm;", "getTtsVoiceGenVm", "()Lcom/pudutech/peanut/robot_ui/viewmodel/TtsVoiceGenVm;", "ttsVoiceGenVm$delegate", "Lkotlin/Lazy;", "checkTts", "", "getVoiceName", "type", "hideUpdate", "initVM", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onResume", "reStartGenerateVoice", "showTip", NotificationCompat.CATEGORY_MESSAGE, "showTtsDownInfoDialog", "showUpdate", "showVoiceChangeDialog", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class TtsVoiceFragment extends Fragment {
    private final String TAG = getClass().getSimpleName();
    private HashMap _$_findViewCache;
    private ShowTipMsgDialog finishTip;
    private boolean isChangeVoice;
    private String lastPath;
    private String mPath;
    private ShowChooseVoiceDialog mVoiceDialog;
    private ShowDownloadingDialog ttsDownInfoDialog;

    /* renamed from: ttsVoiceGenVm$delegate, reason: from kotlin metadata */
    private final Lazy ttsVoiceGenVm;

    private final TtsVoiceGenVm getTtsVoiceGenVm() {
        return (TtsVoiceGenVm) this.ttsVoiceGenVm.getValue();
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

    public TtsVoiceFragment() {
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.TtsVoiceFragment$$special$$inlined$viewModels$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        this.ttsVoiceGenVm = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(TtsVoiceGenVm.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.TtsVoiceFragment$$special$$inlined$viewModels$2
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
        this.mPath = "x_chongchong";
        this.lastPath = this.mPath;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mPath = Constans.INSTANCE.getTtsVoiceType();
        checkTts();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C5508R.layout.fragment_tts_voice, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RelativeLayout relativeLayout = (RelativeLayout) _$_findCachedViewById(C5508R.id.rlBg);
        if (relativeLayout != null) {
            ViewExtKt.onSingleClick(relativeLayout, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.TtsVoiceFragment$onActivityCreated$1
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
                    TtsVoiceFragment.this.showVoiceChangeDialog();
                }
            });
        }
        initVM();
    }

    private final void initVM() {
        getTtsVoiceGenVm().getNeedDown().observe(getViewLifecycleOwner(), new Observer<Boolean>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.TtsVoiceFragment$initVM$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Boolean bool) {
                String str;
                boolean z;
                str = TtsVoiceFragment.this.TAG;
                Pdlog.m3273d(str, "checkTts " + bool);
                if (!bool.booleanValue()) {
                    z = TtsVoiceFragment.this.isChangeVoice;
                    if (z) {
                        TtsVoiceFragment.this.showTtsDownInfoDialog();
                        TtsVoiceFragment.this.isChangeVoice = false;
                        return;
                    } else {
                        TtsVoiceFragment.this.showUpdate();
                        return;
                    }
                }
                TtsVoiceFragment.this.hideUpdate();
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        TextView tvName = (TextView) _$_findCachedViewById(C5508R.id.tvName);
        Intrinsics.checkExpressionValueIsNotNull(tvName, "tvName");
        tvName.setText(getVoiceName(Constans.INSTANCE.getTtsVoiceType()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0086 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String getVoiceName(String type) {
        switch (type.hashCode()) {
            case -1996608251:
                if (type.equals("x2_xiaoyue")) {
                    String string = getString(C5508R.string.xiaoyue);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.xiaoyue)");
                    return string;
                }
            case -1857599363:
                return type.equals("x_xiaojun") ? "小俊" : "";
            case -1765313651:
                if (type.equals("x2_xiaoyuan")) {
                    String string2 = getString(C5508R.string.xiaoyuan);
                    Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.xiaoyuan)");
                    return string2;
                }
            case -1649095859:
                if (type.equals("x2_ningning")) {
                    String string3 = getString(C5508R.string.ningning);
                    Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.ningning)");
                    return string3;
                }
            case 189714439:
                if (type.equals("x_chongchong")) {
                    String string4 = getString(C5508R.string.moren);
                    Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.moren)");
                    return string4;
                }
            case 648921947:
                if (type.equals("x2_qige")) {
                    return "七哥";
                }
                break;
            case 649157024:
                if (type.equals("x2_yezi")) {
                    return "小露";
                }
                break;
            case 767369118:
                if (type.equals("x_mengmengneutral")) {
                    return "萌萌";
                }
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkTts() {
        getTtsVoiceGenVm().check();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showUpdate() {
        TextView update_tv = (TextView) _$_findCachedViewById(C5508R.id.update_tv);
        Intrinsics.checkExpressionValueIsNotNull(update_tv, "update_tv");
        update_tv.setVisibility(0);
        TextView update_tv2 = (TextView) _$_findCachedViewById(C5508R.id.update_tv);
        Intrinsics.checkExpressionValueIsNotNull(update_tv2, "update_tv");
        ViewExtKt.onSingleClick(update_tv2, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.TtsVoiceFragment$showUpdate$1
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
                TtsVoiceFragment.this.showTtsDownInfoDialog();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideUpdate() {
        TextView update_tv = (TextView) _$_findCachedViewById(C5508R.id.update_tv);
        Intrinsics.checkExpressionValueIsNotNull(update_tv, "update_tv");
        update_tv.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showTtsDownInfoDialog() {
        if (this.ttsDownInfoDialog == null) {
            Context requireContext = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
            this.ttsDownInfoDialog = new ShowDownloadingDialog(requireContext);
            getTtsVoiceGenVm().getDownInfo().observe(getViewLifecycleOwner(), new Observer<TtsDownInfo>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.TtsVoiceFragment$showTtsDownInfoDialog$1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(TtsDownInfo ttsDownInfo) {
                    String str;
                    String valueOf;
                    ShowDownloadingDialog showDownloadingDialog;
                    ShowDownloadingDialog showDownloadingDialog2;
                    String str2;
                    ShowDownloadingDialog showDownloadingDialog3;
                    String str3;
                    String str4;
                    String str5;
                    String str6;
                    String str7;
                    String voiceName;
                    str = TtsVoiceFragment.this.TAG;
                    Pdlog.m3273d(str, "showTtsDownInfoDialog " + ttsDownInfo);
                    if (ttsDownInfo.getCode() != 0) {
                        showDownloadingDialog3 = TtsVoiceFragment.this.ttsDownInfoDialog;
                        if (showDownloadingDialog3 != null) {
                            showDownloadingDialog3.dismiss();
                        }
                        TtsVoiceFragment ttsVoiceFragment = TtsVoiceFragment.this;
                        String string = ttsVoiceFragment.getString(C5508R.string.dialog_tip_update_err);
                        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.dialog_tip_update_err)");
                        ttsVoiceFragment.showTip(string);
                        str3 = TtsVoiceFragment.this.mPath;
                        str4 = TtsVoiceFragment.this.lastPath;
                        if (!Intrinsics.areEqual(str3, str4)) {
                            Constans constans = Constans.INSTANCE;
                            str5 = TtsVoiceFragment.this.lastPath;
                            constans.setTtsVoiceType(str5);
                            TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                            str6 = TtsVoiceFragment.this.lastPath;
                            ttsVoiceHelper.setOtherVoice(str6);
                            AiVoiceManager aiVoiceManager = AiVoiceManager.INSTANCE;
                            str7 = TtsVoiceFragment.this.lastPath;
                            aiVoiceManager.setOtherVoice(str7);
                            TextView textView = (TextView) TtsVoiceFragment.this._$_findCachedViewById(C5508R.id.tvName);
                            if (textView != null) {
                                voiceName = TtsVoiceFragment.this.getVoiceName(Constans.INSTANCE.getTtsVoiceType());
                                textView.setText(voiceName);
                            }
                            TtsVoiceFragment.this.checkTts();
                        }
                    } else {
                        if (ttsDownInfo.getAll() == 0) {
                            valueOf = ttsDownInfo + ".all";
                        } else {
                            valueOf = String.valueOf((ttsDownInfo.getLeft() * 100) / ttsDownInfo.getAll());
                        }
                        showDownloadingDialog = TtsVoiceFragment.this.ttsDownInfoDialog;
                        if (showDownloadingDialog != null) {
                            showDownloadingDialog.showProgress(valueOf);
                        }
                    }
                    if (ttsDownInfo.getLeft() == ttsDownInfo.getAll()) {
                        showDownloadingDialog2 = TtsVoiceFragment.this.ttsDownInfoDialog;
                        if (showDownloadingDialog2 != null) {
                            showDownloadingDialog2.dismiss();
                        }
                        TtsVoiceFragment.this.hideUpdate();
                        Constans constans2 = Constans.INSTANCE;
                        str2 = TtsVoiceFragment.this.mPath;
                        constans2.setTtsVoiceType(str2);
                        TtsVoiceFragment ttsVoiceFragment2 = TtsVoiceFragment.this;
                        String string2 = ttsVoiceFragment2.getString(C5508R.string.dialog_tip_download_success);
                        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.dialog_tip_download_success)");
                        ttsVoiceFragment2.showTip(string2);
                    }
                }
            });
        }
        ShowDownloadingDialog showDownloadingDialog = this.ttsDownInfoDialog;
        if (showDownloadingDialog != null) {
            showDownloadingDialog.showProgress("0");
        }
        ShowDownloadingDialog showDownloadingDialog2 = this.ttsDownInfoDialog;
        if (showDownloadingDialog2 != null) {
            showDownloadingDialog2.show();
        }
        getTtsVoiceGenVm().down();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reStartGenerateVoice() {
        checkTts();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showVoiceChangeDialog() {
        ShowChooseVoiceDialog showChooseVoiceDialog;
        this.lastPath = this.mPath;
        if (this.mVoiceDialog == null) {
            Context requireContext = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
            this.mVoiceDialog = new ShowChooseVoiceDialog(requireContext);
            ShowChooseVoiceDialog showChooseVoiceDialog2 = this.mVoiceDialog;
            if (showChooseVoiceDialog2 != null) {
                showChooseVoiceDialog2.setOnSureBtnClick(new Function1<VoiceBean, Unit>() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.TtsVoiceFragment$showVoiceChangeDialog$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(VoiceBean voiceBean) {
                        invoke2(voiceBean);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(VoiceBean voiceBean) {
                        String str;
                        if (voiceBean != null) {
                            TtsVoiceFragment.this.isChangeVoice = true;
                            String path = voiceBean.getPath();
                            if (path != null) {
                                TtsVoiceFragment.this.mPath = path;
                                TtsVoiceHelper.INSTANCE.setOtherVoice(path);
                                AiVoiceManager.INSTANCE.setOtherVoice(path);
                                Constans constans = Constans.INSTANCE;
                                str = TtsVoiceFragment.this.mPath;
                                constans.setTtsVoiceType(str);
                            }
                            TtsVoiceFragment.this.reStartGenerateVoice();
                        }
                    }
                });
            }
            ShowChooseVoiceDialog showChooseVoiceDialog3 = this.mVoiceDialog;
            if (showChooseVoiceDialog3 != null) {
                showChooseVoiceDialog3.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.TtsVoiceFragment$showVoiceChangeDialog$2
                    @Override // android.content.DialogInterface.OnDismissListener
                    public final void onDismiss(DialogInterface dialogInterface) {
                        String voiceName;
                        TextView textView = (TextView) TtsVoiceFragment.this._$_findCachedViewById(C5508R.id.tvName);
                        if (textView != null) {
                            voiceName = TtsVoiceFragment.this.getVoiceName(Constans.INSTANCE.getTtsVoiceType());
                            textView.setText(voiceName);
                        }
                    }
                });
            }
        }
        this.isChangeVoice = false;
        ShowChooseVoiceDialog showChooseVoiceDialog4 = this.mVoiceDialog;
        if (showChooseVoiceDialog4 == null || showChooseVoiceDialog4.isShowing() || (showChooseVoiceDialog = this.mVoiceDialog) == null) {
            return;
        }
        showChooseVoiceDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showTip(String msg) {
        ShowTipMsgDialog showTipMsgDialog;
        if (this.finishTip == null) {
            Context requireContext = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
            this.finishTip = new ShowTipMsgDialog(requireContext);
        }
        ShowTipMsgDialog showTipMsgDialog2 = this.finishTip;
        if (showTipMsgDialog2 != null) {
            showTipMsgDialog2.showTipMsg(msg);
        }
        ShowTipMsgDialog showTipMsgDialog3 = this.finishTip;
        if (showTipMsgDialog3 != null) {
            showTipMsgDialog3.setCanCancel(true);
        }
        ShowTipMsgDialog showTipMsgDialog4 = this.finishTip;
        if (showTipMsgDialog4 == null || showTipMsgDialog4.isShowing() || (showTipMsgDialog = this.finishTip) == null) {
            return;
        }
        showTipMsgDialog.show();
    }
}
