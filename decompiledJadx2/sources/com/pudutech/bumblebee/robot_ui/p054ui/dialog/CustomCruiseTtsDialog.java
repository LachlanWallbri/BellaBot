package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseApplication;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.base.BaseDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceFlHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt;
import com.pudutech.bumblebee.robot_ui.util.KeyboardUtils;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import com.pudutech.disinfect.baselib.util.NetStatusUtil;
import com.pudutech.tts_sdk.PdTtsSdk;
import com.pudutech.tts_sdk.tts.OnTtsListener;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: CustomCruiseTtsDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u001d\u001a\u00020\u00122\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020\u0001H\u0016J\u0006\u0010!\u001a\u00020\u0012J\b\u0010\"\u001a\u00020\u0012H\u0002J\b\u0010#\u001a\u00020\u0012H\u0002J\b\u0010$\u001a\u00020\u0012H\u0002J\b\u0010%\u001a\u00020\fH\u0016J\u001a\u0010&\u001a\u00020\u00122\u0006\u0010'\u001a\u00020\u001f2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u0010*\u001a\u00020\u0012H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R7\u0010\r\u001a\u001f\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006+"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/CustomCruiseTtsDialog;", "Lcom/pudutech/bumblebee/robot_ui/base/BaseDialog;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "isGenerating", "", "mCoroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "mLocaleStr", "maxLength", "", "onTtsAddSuccessListener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", AIUIConstant.KEY_CONTENT, "", "getOnTtsAddSuccessListener", "()Lkotlin/jvm/functions/Function1;", "setOnTtsAddSuccessListener", "(Lkotlin/jvm/functions/Function1;)V", "type", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "getType", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "setType", "(Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;)V", "bindView", "rootView", "Landroid/view/View;", "dialog", "createLoadDialog", "disLoadDialog", "dismissTTsDialog", "generateTTs", "getDialogLayoutId", "onViewCreated", "view", "savedInstanceState", "Landroid/os/Bundle;", "pilotVoice", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CustomCruiseTtsDialog extends BaseDialog {
    private HashMap _$_findViewCache;
    private boolean isGenerating;
    private CoroutineScope mCoroutineScope;
    private Function1<? super String, Unit> onTtsAddSuccessListener;
    private final String TAG = getClass().getSimpleName();
    private TtsVoiceHelper.TtsVoiceType type = TtsVoiceHelper.TtsVoiceType.CRUISE_STAY_TYPE;
    private final int maxLength = 200;
    private String mLocaleStr = LanguageUtils.Companion.getLocaleStr$default(LanguageUtils.INSTANCE, false, 1, null);

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseDialog
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseDialog
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

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseDialog, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final TtsVoiceHelper.TtsVoiceType getType() {
        return this.type;
    }

    public final void setType(TtsVoiceHelper.TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "<set-?>");
        this.type = ttsVoiceType;
    }

    public final Function1<String, Unit> getOnTtsAddSuccessListener() {
        return this.onTtsAddSuccessListener;
    }

    public final void setOnTtsAddSuccessListener(Function1<? super String, Unit> function1) {
        this.onTtsAddSuccessListener = function1;
    }

    public final void createLoadDialog() {
        FrameLayout ft_loading_custom = (FrameLayout) _$_findCachedViewById(C4188R.id.ft_loading_custom);
        Intrinsics.checkExpressionValueIsNotNull(ft_loading_custom, "ft_loading_custom");
        if (ft_loading_custom.isShown()) {
            return;
        }
        FrameLayout ft_loading_custom2 = (FrameLayout) _$_findCachedViewById(C4188R.id.ft_loading_custom);
        Intrinsics.checkExpressionValueIsNotNull(ft_loading_custom2, "ft_loading_custom");
        ft_loading_custom2.setVisibility(0);
        ProgressBar progressBar = (ProgressBar) _$_findCachedViewById(C4188R.id.loading_pgb_custom);
        Intrinsics.checkExpressionValueIsNotNull(progressBar, "this");
        ViewGroup.LayoutParams layoutParams = progressBar.getLayoutParams();
        if (layoutParams == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
        }
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
        if (layoutParams2.topMargin <= 10) {
            RelativeLayout customVoice_rt = (RelativeLayout) _$_findCachedViewById(C4188R.id.customVoice_rt);
            Intrinsics.checkExpressionValueIsNotNull(customVoice_rt, "customVoice_rt");
            layoutParams2.topMargin = customVoice_rt.getMeasuredHeight() / 2;
            progressBar.setLayoutParams(layoutParams2);
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseDialog
    public int getDialogLayoutId() {
        return C4188R.layout.dialog_custom_voice;
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseDialog, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Window window;
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        this.mCoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
        Dialog dialog = getDialog();
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        window.addFlags(8);
        window.clearFlags(131080);
        window.setSoftInputMode(32);
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseDialog
    public void bindView(View rootView, BaseDialog dialog) {
        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
        if (rootView != null) {
            ((ImageView) _$_findCachedViewById(C4188R.id.ivClose)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomCruiseTtsDialog$bindView$$inlined$apply$lambda$1
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
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    z = CustomCruiseTtsDialog.this.isGenerating;
                    if (z) {
                        return;
                    }
                    CustomCruiseTtsDialog.this.dismissTTsDialog();
                }
            }, 3, null));
            ((FrameLayout) _$_findCachedViewById(C4188R.id.ft_loading_custom)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomCruiseTtsDialog$bindView$$inlined$apply$lambda$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    String str;
                    str = CustomCruiseTtsDialog.this.TAG;
                    Pdlog.m3273d(str, "loading背景点击");
                }
            });
            ((Button) _$_findCachedViewById(C4188R.id.btnConfirm)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomCruiseTtsDialog$bindView$$inlined$apply$lambda$3
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
                    CustomCruiseTtsDialog.this.generateTTs();
                }
            }, 3, null));
            ((Button) _$_findCachedViewById(C4188R.id.btnPreview)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomCruiseTtsDialog$bindView$$inlined$apply$lambda$4
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
                    CustomCruiseTtsDialog.this.pilotVoice();
                }
            }, 3, null));
            KeyboardUtils.showSoftInput((EditText) _$_findCachedViewById(C4188R.id.etCustomVoice));
            EditText etCustomVoice = (EditText) _$_findCachedViewById(C4188R.id.etCustomVoice);
            Intrinsics.checkExpressionValueIsNotNull(etCustomVoice, "etCustomVoice");
            ViewExtKt.disableCopyAndPaste(etCustomVoice);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void dismissTTsDialog() {
        ((ProgressBar) _$_findCachedViewById(C4188R.id.loading_pgb_custom)).setIndeterminateDrawable(getResources().getDrawable(C4188R.drawable.ic_base_load));
        EditText editText = (EditText) _$_findCachedViewById(C4188R.id.etCustomVoice);
        if (editText != null) {
            editText.setText("");
            KeyboardUtils.hideSoftInput((EditText) _$_findCachedViewById(C4188R.id.etCustomVoice));
            CoroutineScope coroutineScope = this.mCoroutineScope;
            if (coroutineScope != null) {
                CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
            }
            TtsVoiceHelper.INSTANCE.stopCruiseTts();
            dismissDialog();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void generateTTs() {
        EditText etCustomVoice = (EditText) _$_findCachedViewById(C4188R.id.etCustomVoice);
        Intrinsics.checkExpressionValueIsNotNull(etCustomVoice, "etCustomVoice");
        String obj = etCustomVoice.getText().toString();
        if (obj == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        final String obj2 = StringsKt.trim((CharSequence) obj).toString();
        Pdlog.m3273d(this.TAG, "input_done " + obj2);
        String str = obj2;
        if (str == null || StringsKt.isBlank(str)) {
            ToastUtils.show(getContext(), getString(C4188R.string.pdStr7_72), new Object[0]);
            return;
        }
        if (obj2.length() > this.maxLength) {
            Context context = getContext();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(C4188R.string.pdStr7_76);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_76)");
            Object[] objArr = {String.valueOf(this.maxLength)};
            String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            ToastUtils.show(context, format, new Object[0]);
            return;
        }
        boolean checkTtsExist = TtsVoiceHelper.INSTANCE.checkTtsExist(obj2, this.type);
        Pdlog.m3273d(this.TAG, "input_done " + obj2 + " ,Exist = " + checkTtsExist);
        if (checkTtsExist) {
            Pdlog.m3273d(this.TAG, "input done same");
            ToastUtils.show(getContext(), getString(C4188R.string.pdStr7_92), new Object[0]);
            return;
        }
        if (this.isGenerating) {
            Pdlog.m3274e(this.TAG, "input_done isGenerating ,do not restart");
            return;
        }
        createLoadDialog();
        this.isGenerating = true;
        if (LanguageUtils.INSTANCE.isZh() || LanguageUtils.INSTANCE.isZhTw()) {
            TtsVoiceHelper.addNewTtsVoice$default(TtsVoiceHelper.INSTANCE, obj2, this.type, false, null, new Function1<Throwable, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomCruiseTtsDialog$generateTTs$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                    invoke2(th);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Throwable th) {
                    String str2;
                    String str3;
                    String str4;
                    CustomCruiseTtsDialog customCruiseTtsDialog = CustomCruiseTtsDialog.this;
                    if (th == null) {
                        customCruiseTtsDialog.disLoadDialog();
                        Function1<String, Unit> onTtsAddSuccessListener = CustomCruiseTtsDialog.this.getOnTtsAddSuccessListener();
                        if (onTtsAddSuccessListener != null) {
                            String str5 = obj2;
                            if (str5 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                            }
                            onTtsAddSuccessListener.invoke(StringsKt.trim((CharSequence) str5).toString());
                        }
                        CustomCruiseTtsDialog.this.dismissTTsDialog();
                        str4 = CustomCruiseTtsDialog.this.TAG;
                        Pdlog.m3273d(str4, "addNewTtsVoice---content:" + obj2);
                    } else {
                        str2 = customCruiseTtsDialog.TAG;
                        Pdlog.m3274e(str2, obj2 + " ----  onError :");
                        str3 = CustomCruiseTtsDialog.this.TAG;
                        Pdlog.m3274e(str3, Log.getStackTraceString(th));
                        Application companion = BaseApplication.INSTANCE.getInstance();
                        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                        String string2 = CustomCruiseTtsDialog.this.getString(C4188R.string.pdStr7_74);
                        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.pdStr7_74)");
                        Object[] objArr2 = {th.getMessage()};
                        String format2 = String.format(string2, Arrays.copyOf(objArr2, objArr2.length));
                        Intrinsics.checkExpressionValueIsNotNull(format2, "java.lang.String.format(format, *args)");
                        ToastUtils.show(companion, format2, new Object[0]);
                        CustomCruiseTtsDialog.this.disLoadDialog();
                    }
                    customCruiseTtsDialog.isGenerating = false;
                }
            }, 12, null);
            return;
        }
        CoroutineScope coroutineScope = this.mCoroutineScope;
        if (coroutineScope != null) {
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new CustomCruiseTtsDialog$generateTTs$2(this, obj2, null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void pilotVoice() {
        EditText etCustomVoice = (EditText) _$_findCachedViewById(C4188R.id.etCustomVoice);
        Intrinsics.checkExpressionValueIsNotNull(etCustomVoice, "etCustomVoice");
        String obj = etCustomVoice.getText().toString();
        if (obj == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        final String obj2 = StringsKt.trim((CharSequence) obj).toString();
        if (StringsKt.isBlank(obj2)) {
            ToastUtils.show(getContext(), getString(C4188R.string.pdStr7_72), new Object[0]);
            return;
        }
        if (obj2.length() > this.maxLength) {
            Context context = getContext();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(C4188R.string.pdStr7_76);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_76)");
            Object[] objArr = {String.valueOf(this.maxLength)};
            String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            ToastUtils.show(context, format, new Object[0]);
            return;
        }
        if (!NetStatusUtil.isConnected(RobotContext.INSTANCE.getContext())) {
            ToastUtils.show(RobotContext.INSTANCE.getContext(), getString(C4188R.string.pdStr7_73), new Object[0]);
        } else {
            if (this.isGenerating) {
                Pdlog.m3274e(this.TAG, "isGenerating ,do not restart");
                return;
            }
            createLoadDialog();
            this.isGenerating = true;
            TtsVoiceHelper.INSTANCE.setTtsTempPlay(obj2, new OnTtsListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomCruiseTtsDialog$pilotVoice$1
                @Override // com.pudutech.tts_sdk.tts.OnTtsListener
                public void onProgress(int proses) {
                }

                @Override // com.pudutech.tts_sdk.tts.OnTtsListener
                public void onComplete(String filePath) {
                    String str;
                    Intrinsics.checkParameterIsNotNull(filePath, "filePath");
                    str = CustomCruiseTtsDialog.this.TAG;
                    Pdlog.m3274e(str, "setTtsTempPlay---tempText:" + obj2 + " ----  filePath :" + filePath);
                    if (TtsVoiceFlHelper.INSTANCE.isGoogleTts(filePath)) {
                        PdTtsSdk.playTtsMp3File$default(PdTtsSdk.INSTANCE, filePath, null, 2, null);
                    } else {
                        PdTtsSdk.playTtsPcmFile$default(PdTtsSdk.INSTANCE, filePath, null, 2, null);
                    }
                    CustomCruiseTtsDialog.this.isGenerating = false;
                    CustomCruiseTtsDialog.this.disLoadDialog();
                }

                @Override // com.pudutech.tts_sdk.tts.OnTtsListener
                public void onError(int code, String msg) {
                    String str;
                    Intrinsics.checkParameterIsNotNull(msg, "msg");
                    str = CustomCruiseTtsDialog.this.TAG;
                    Pdlog.m3274e(str, "setTtsTempPlay---tempText:" + obj2 + " ----  onError :" + code + " , " + msg);
                    if (!LanguageUtils.INSTANCE.isZh() && !LanguageUtils.INSTANCE.isZhTw()) {
                        msg = BaseApplication.INSTANCE.getInstance().getResources().getString(C4188R.string.pdStr7_79);
                        Intrinsics.checkExpressionValueIsNotNull(msg, "BaseApplication.instance…tring(R.string.pdStr7_79)");
                    }
                    Context context2 = RobotContext.INSTANCE.getContext();
                    StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                    String string2 = CustomCruiseTtsDialog.this.getString(C4188R.string.pdStr7_74);
                    Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.pdStr7_74)");
                    Object[] objArr2 = {msg};
                    String format2 = String.format(string2, Arrays.copyOf(objArr2, objArr2.length));
                    Intrinsics.checkExpressionValueIsNotNull(format2, "java.lang.String.format(format, *args)");
                    ToastUtils.show(context2, format2, new Object[0]);
                    CustomCruiseTtsDialog.this.isGenerating = false;
                    CustomCruiseTtsDialog.this.disLoadDialog();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void disLoadDialog() {
        FrameLayout ft_loading_custom = (FrameLayout) _$_findCachedViewById(C4188R.id.ft_loading_custom);
        Intrinsics.checkExpressionValueIsNotNull(ft_loading_custom, "ft_loading_custom");
        if (ft_loading_custom.isShown()) {
            FrameLayout ft_loading_custom2 = (FrameLayout) _$_findCachedViewById(C4188R.id.ft_loading_custom);
            Intrinsics.checkExpressionValueIsNotNull(ft_loading_custom2, "ft_loading_custom");
            ft_loading_custom2.setVisibility(8);
        }
    }
}
