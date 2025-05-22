package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseApplication;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceFlHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListenerKt;
import com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt;
import com.pudutech.bumblebee.robot_ui.util.KeyboardUtils;
import com.pudutech.bumblebee.robot_ui.util.NetStatusUtil;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
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

/* compiled from: CustomTtsVoiceInputDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000u\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\b\u000b*\u0001G\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010.\u001a\u00020\u001bH\u0002J\b\u0010/\u001a\u00020\u001bH\u0016J\b\u00100\u001a\u00020\u001bH\u0017J\b\u00101\u001a\u00020\u001bH\u0002J\b\u00102\u001a\u00020\u001bH\u0002J\b\u00103\u001a\u00020\u001bH\u0002J\u0012\u00104\u001a\u0002052\b\u00106\u001a\u0004\u0018\u000107H\u0016J&\u00108\u001a\u0004\u0018\u0001092\u0006\u0010:\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u00106\u001a\u0004\u0018\u000107H\u0016J\u0010\u0010>\u001a\u00020\u001b2\u0006\u0010?\u001a\u00020@H\u0016J\u0010\u0010A\u001a\u00020\u001b2\u0006\u0010B\u001a\u00020\fH\u0016J\b\u0010C\u001a\u00020\u001bH\u0002J\b\u0010D\u001a\u00020\u001bH\u0016J\b\u0010E\u001a\u00020\u001bH\u0002J\r\u0010F\u001a\u00020GH\u0002¢\u0006\u0002\u0010HJ\u001a\u0010I\u001a\u00020\u001b2\u0006\u0010J\u001a\u0002092\b\u00106\u001a\u0004\u0018\u000107H\u0016J\u0010\u0010K\u001a\u00020\u001b2\u0006\u0010L\u001a\u00020\u0011H\u0003J\u0012\u0010M\u001a\u00020\u001b2\b\b\u0002\u0010N\u001a\u00020\fH\u0002J\b\u0010O\u001a\u00020\u001bH\u0002J\b\u0010P\u001a\u00020\fH\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R7\u0010\u0016\u001a\u001f\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\b\"\u0004\b'\u0010\nR\u001a\u0010(\u001a\u00020)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-¨\u0006Q"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/CustomTtsVoiceInputDialog;", "Landroidx/fragment/app/DialogFragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", AIUIConstant.KEY_CONTENT, "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "isGenerating", "", "mCoroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "mLocaleStr", "maxLength", "", "getMaxLength", "()I", "setMaxLength", "(I)V", "onContentChange", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "s", "", "getOnContentChange", "()Lkotlin/jvm/functions/Function1;", "setOnContentChange", "(Lkotlin/jvm/functions/Function1;)V", "showPlayVoiceButton", "getShowPlayVoiceButton", "()Z", "setShowPlayVoiceButton", "(Z)V", "title", "getTitle", "setTitle", "voiceType", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "getVoiceType", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "setVoiceType", "(Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;)V", "disLoadDialog", "dismiss", "dismissAllowingStateLoss", "generateChinaVoice", "generateForeignVoice", "initView", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "onMultiWindowModeChanged", "isInMultiWindowMode", "onPlayTtsVoice", "onResume", "onTtsConfirm", "onTtsListener", "com/pudutech/bumblebee/robot_ui/ui/dialog/CustomTtsVoiceInputDialog$onTtsListener$1", "()Lcom/pudutech/bumblebee/robot_ui/ui/dialog/CustomTtsVoiceInputDialog$onTtsListener$1;", "onViewCreated", "view", "setTtsCount", "contentLength", "showLoadingView", "isLoading", "translucent", "wordLengthOverStep", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CustomTtsVoiceInputDialog extends DialogFragment {
    private HashMap _$_findViewCache;
    private boolean isGenerating;
    private CoroutineScope mCoroutineScope;
    private Function1<? super String, Unit> onContentChange;
    private final String TAG = getClass().getSimpleName();
    private String mLocaleStr = LanguageUtils.Companion.getLocaleStr$default(LanguageUtils.INSTANCE, false, 1, null);
    private TtsVoiceHelper.TtsVoiceType voiceType = TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE;
    private String title = "";
    private String content = "";
    private int maxLength = 50;
    private boolean showPlayVoiceButton = true;

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

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final TtsVoiceHelper.TtsVoiceType getVoiceType() {
        return this.voiceType;
    }

    public final void setVoiceType(TtsVoiceHelper.TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "<set-?>");
        this.voiceType = ttsVoiceType;
    }

    public final Function1<String, Unit> getOnContentChange() {
        return this.onContentChange;
    }

    public final void setOnContentChange(Function1<? super String, Unit> function1) {
        this.onContentChange = function1;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.title = str;
    }

    public final String getContent() {
        return this.content;
    }

    public final void setContent(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.content = str;
    }

    public final int getMaxLength() {
        return this.maxLength;
    }

    public final void setMaxLength(int i) {
        this.maxLength = i;
    }

    public final boolean getShowPlayVoiceButton() {
        return this.showPlayVoiceButton;
    }

    public final void setShowPlayVoiceButton(boolean z) {
        this.showPlayVoiceButton = z;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C4188R.layout.dialog_voice_curise_input, container, false);
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Pdlog.m3273d(this.TAG, "onCreateDialog");
        setStyle(1, C4188R.style.AppThemeDialog);
        Dialog onCreateDialog = super.onCreateDialog(savedInstanceState);
        Intrinsics.checkExpressionValueIsNotNull(onCreateDialog, "super.onCreateDialog(savedInstanceState)");
        return onCreateDialog;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        Dialog dialog = getDialog();
        if (dialog == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(dialog, "dialog!!");
        Window window = dialog.getWindow();
        if (window == null) {
            Intrinsics.throwNpe();
        }
        window.getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomTtsVoiceInputDialog$onViewCreated$1
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public final void onSystemUiVisibilityChange(int i) {
                CustomTtsVoiceInputDialog.this.translucent();
            }
        });
        translucent();
        initView();
        this.mCoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
    }

    @Override // androidx.fragment.app.Fragment
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        translucent();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        Window window;
        Window window2;
        super.onResume();
        translucent();
        Dialog dialog = getDialog();
        if (dialog != null && (window2 = dialog.getWindow()) != null) {
            window2.setBackgroundDrawable(new ColorDrawable(0));
        }
        Dialog dialog2 = getDialog();
        if (dialog2 == null || (window = dialog2.getWindow()) == null) {
            return;
        }
        window.setLayout(-1, -1);
    }

    private final void initView() {
        Pdlog.m3273d(this.TAG, "initView");
        setCancelable(false);
        Button btnTtsPlay = (Button) _$_findCachedViewById(C4188R.id.btnTtsPlay);
        Intrinsics.checkExpressionValueIsNotNull(btnTtsPlay, "btnTtsPlay");
        btnTtsPlay.setVisibility(this.showPlayVoiceButton ? 0 : 8);
        setTtsCount(this.content.length());
        final EditText editText = (EditText) _$_findCachedViewById(C4188R.id.etTtsContent);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(this.maxLength)});
        editText.setText(this.content);
        editText.setSelection(this.content.length());
        editText.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomTtsVoiceInputDialog$initView$$inlined$apply$lambda$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            /* JADX WARN: Code restructure failed: missing block: B:7:0x0016, code lost:
            
                if (r5 != null) goto L13;
             */
            @Override // android.text.TextWatcher
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void afterTextChanged(Editable s) {
                String str;
                boolean wordLengthOverStep;
                String obj;
                CustomTtsVoiceInputDialog customTtsVoiceInputDialog = this;
                if (s != null && (obj = s.toString()) != null) {
                    if (obj == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                    }
                    str = StringsKt.trim((CharSequence) obj).toString();
                }
                str = "";
                customTtsVoiceInputDialog.setContent(str);
                CustomTtsVoiceInputDialog customTtsVoiceInputDialog2 = this;
                customTtsVoiceInputDialog2.setTtsCount(customTtsVoiceInputDialog2.getContent().length());
                wordLengthOverStep = this.wordLengthOverStep();
                if (wordLengthOverStep) {
                    Context context = editText.getContext();
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String string = this.getString(C4188R.string.pdStr7_76);
                    Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_76)");
                    Object[] objArr = {String.valueOf(this.getMaxLength())};
                    String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
                    Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                    ToastUtils.showXY(context, format, 48, 0, 0);
                }
            }
        });
        ViewExtKt.disableCopyAndPaste(editText);
        Button btnTtsConfirm = (Button) _$_findCachedViewById(C4188R.id.btnTtsConfirm);
        Intrinsics.checkExpressionValueIsNotNull(btnTtsConfirm, "btnTtsConfirm");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(btnTtsConfirm, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomTtsVoiceInputDialog$initView$2
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
                CustomTtsVoiceInputDialog.this.onTtsConfirm();
            }
        }, 3, null);
        Button btnTtsPlay2 = (Button) _$_findCachedViewById(C4188R.id.btnTtsPlay);
        Intrinsics.checkExpressionValueIsNotNull(btnTtsPlay2, "btnTtsPlay");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(btnTtsPlay2, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomTtsVoiceInputDialog$initView$3
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
                CustomTtsVoiceInputDialog.this.onPlayTtsVoice();
            }
        }, 3, null);
        ImageView ivTtsClose = (ImageView) _$_findCachedViewById(C4188R.id.ivTtsClose);
        Intrinsics.checkExpressionValueIsNotNull(ivTtsClose, "ivTtsClose");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(ivTtsClose, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomTtsVoiceInputDialog$initView$4
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
                boolean z;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str = CustomTtsVoiceInputDialog.this.TAG;
                Pdlog.m3273d(str, "cancel");
                z = CustomTtsVoiceInputDialog.this.isGenerating;
                if (z) {
                    return;
                }
                CustomTtsVoiceInputDialog.this.dismissAllowingStateLoss();
            }
        }, 3, null);
        KeyboardUtils.showSoftInput((EditText) _$_findCachedViewById(C4188R.id.etTtsContent));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0030 A[Catch: Exception -> 0x00e3, TryCatch #0 {Exception -> 0x00e3, blocks: (B:3:0x0004, B:5:0x0024, B:10:0x0030, B:13:0x0040, B:15:0x006e, B:17:0x0074, B:19:0x0078, B:21:0x007c, B:22:0x008d, B:23:0x0094, B:24:0x0095, B:26:0x0099, B:28:0x00b4, B:30:0x00b8, B:32:0x00c4, B:34:0x00d2, B:37:0x00db, B:39:0x00df), top: B:2:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0040 A[Catch: Exception -> 0x00e3, TryCatch #0 {Exception -> 0x00e3, blocks: (B:3:0x0004, B:5:0x0024, B:10:0x0030, B:13:0x0040, B:15:0x006e, B:17:0x0074, B:19:0x0078, B:21:0x007c, B:22:0x008d, B:23:0x0094, B:24:0x0095, B:26:0x0099, B:28:0x00b4, B:30:0x00b8, B:32:0x00c4, B:34:0x00d2, B:37:0x00db, B:39:0x00df), top: B:2:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void onTtsConfirm() {
        boolean z;
        try {
            Pdlog.m3273d(this.TAG, "input_done " + this.content);
            String str = this.content;
            if (str != null && !StringsKt.isBlank(str)) {
                z = false;
                if (!z) {
                    ToastUtils.show(getContext(), getString(C4188R.string.pdStr7_72), new Object[0]);
                    return;
                }
                boolean checkTtsExist = TtsVoiceHelper.INSTANCE.checkTtsExist(this.content, this.voiceType);
                Pdlog.m3273d(this.TAG, "input_done " + this.content + " ,Exist = " + checkTtsExist);
                if (checkTtsExist) {
                    if (this.voiceType == TtsVoiceHelper.TtsVoiceType.PALLET_DELIVER_TYPE) {
                        Function1<? super String, Unit> function1 = this.onContentChange;
                        if (function1 != null) {
                            String str2 = this.content;
                            if (str2 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                            }
                            function1.invoke(StringsKt.trim((CharSequence) str2).toString());
                        }
                        dismissAllowingStateLoss();
                        return;
                    }
                    Pdlog.m3273d(this.TAG, "input done same");
                    ToastUtils.show(getContext(), getString(C4188R.string.pdStr7_92), new Object[0]);
                    return;
                }
                if (this.isGenerating) {
                    Pdlog.m3274e(this.TAG, "input_done isGening ,do not restart");
                    return;
                }
                showLoadingView$default(this, false, 1, null);
                this.isGenerating = true;
                if (!LanguageUtils.INSTANCE.isZh() && !LanguageUtils.INSTANCE.isZhTw()) {
                    generateForeignVoice();
                    return;
                }
                generateChinaVoice();
                return;
            }
            z = true;
            if (!z) {
            }
        } catch (Exception e) {
            disLoadDialog();
            Pdlog.m3273d(this.TAG, "input_done---Exception:" + e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onPlayTtsVoice() {
        try {
            if (StringsKt.isBlank(this.content)) {
                ToastUtils.show(getContext(), getString(C4188R.string.pdStr7_72), new Object[0]);
                return;
            }
            if (!NetStatusUtil.isConnected(RobotContext.INSTANCE.getContext())) {
                ToastUtils.show(RobotContext.INSTANCE.getContext(), getString(C4188R.string.pdStr7_73), new Object[0]);
            } else {
                if (this.isGenerating) {
                    Pdlog.m3274e(this.TAG, "isGening ,do not restart");
                    return;
                }
                showLoadingView$default(this, false, 1, null);
                this.isGenerating = true;
                TtsVoiceHelper.INSTANCE.setTtsTempPlay(this.content, onTtsListener());
            }
        } catch (Exception e) {
            disLoadDialog();
            Pdlog.m3273d(this.TAG, "setTtsTempPlay----Exception:" + e);
        }
    }

    private final void generateChinaVoice() {
        TtsVoiceHelper.addNewTtsVoice$default(TtsVoiceHelper.INSTANCE, this.content, this.voiceType, false, null, new Function1<Throwable, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomTtsVoiceInputDialog$generateChinaVoice$1
            /* JADX INFO: Access modifiers changed from: package-private */
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
                String str;
                String str2;
                if (th == null) {
                    CustomTtsVoiceInputDialog.this.disLoadDialog();
                    CustomTtsVoiceInputDialog.this.isGenerating = false;
                    Function1<String, Unit> onContentChange = CustomTtsVoiceInputDialog.this.getOnContentChange();
                    if (onContentChange != null) {
                        String content = CustomTtsVoiceInputDialog.this.getContent();
                        if (content == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                        }
                        onContentChange.invoke(StringsKt.trim((CharSequence) content).toString());
                    }
                    CustomTtsVoiceInputDialog.this.dismissAllowingStateLoss();
                    return;
                }
                str = CustomTtsVoiceInputDialog.this.TAG;
                Pdlog.m3274e(str, CustomTtsVoiceInputDialog.this.getContent() + " ----  onError :");
                str2 = CustomTtsVoiceInputDialog.this.TAG;
                Pdlog.m3274e(str2, Log.getStackTraceString(th));
                Context context = RobotContext.INSTANCE.getContext();
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String string = CustomTtsVoiceInputDialog.this.getString(C4188R.string.pdStr7_74);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_74)");
                Object[] objArr = {th.getMessage()};
                String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                ToastUtils.show(context, format, new Object[0]);
                CustomTtsVoiceInputDialog.this.disLoadDialog();
                CustomTtsVoiceInputDialog.this.isGenerating = false;
            }
        }, 12, null);
    }

    private final void generateForeignVoice() {
        CoroutineScope coroutineScope = this.mCoroutineScope;
        if (coroutineScope != null) {
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new CustomTtsVoiceInputDialog$generateForeignVoice$1(this, null), 3, null);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.pudutech.bumblebee.robot_ui.ui.dialog.CustomTtsVoiceInputDialog$onTtsListener$1] */
    private final CustomTtsVoiceInputDialog$onTtsListener$1 onTtsListener() {
        return new OnTtsListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomTtsVoiceInputDialog$onTtsListener$1
            @Override // com.pudutech.tts_sdk.tts.OnTtsListener
            public void onProgress(int proses) {
            }

            @Override // com.pudutech.tts_sdk.tts.OnTtsListener
            public void onComplete(String filePath) {
                String str;
                Intrinsics.checkParameterIsNotNull(filePath, "filePath");
                if (TtsVoiceFlHelper.INSTANCE.isGoogleTts(filePath)) {
                    PdTtsSdk.playTtsMp3File$default(PdTtsSdk.INSTANCE, filePath, null, 2, null);
                } else {
                    PdTtsSdk.playTtsPcmFile$default(PdTtsSdk.INSTANCE, filePath, null, 2, null);
                }
                str = CustomTtsVoiceInputDialog.this.TAG;
                Pdlog.m3274e(str, "setTtsTempPlay---tempText：" + CustomTtsVoiceInputDialog.this.getContent() + "---filePath:" + filePath);
                CustomTtsVoiceInputDialog.this.isGenerating = false;
                CustomTtsVoiceInputDialog.this.disLoadDialog();
            }

            @Override // com.pudutech.tts_sdk.tts.OnTtsListener
            public void onError(int code, String msg) {
                String str;
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                str = CustomTtsVoiceInputDialog.this.TAG;
                Pdlog.m3274e(str, "setTtsTempPlay---" + CustomTtsVoiceInputDialog.this.getContent() + " ----  onError :" + code + " , " + msg);
                if (!LanguageUtils.INSTANCE.isZh() && !LanguageUtils.INSTANCE.isZhTw()) {
                    msg = BaseApplication.INSTANCE.getInstance().getResources().getString(C4188R.string.pdStr7_79);
                    Intrinsics.checkExpressionValueIsNotNull(msg, "BaseApplication.instance…tring(R.string.pdStr7_79)");
                }
                Context context = RobotContext.INSTANCE.getContext();
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String string = CustomTtsVoiceInputDialog.this.getString(C4188R.string.pdStr7_74);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_74)");
                Object[] objArr = {msg};
                String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                ToastUtils.show(context, format, new Object[0]);
                CustomTtsVoiceInputDialog.this.isGenerating = false;
                CustomTtsVoiceInputDialog.this.disLoadDialog();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean wordLengthOverStep() {
        return this.content.length() > this.maxLength;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTtsCount(int contentLength) {
        TextView tvTtsCount = (TextView) _$_findCachedViewById(C4188R.id.tvTtsCount);
        Intrinsics.checkExpressionValueIsNotNull(tvTtsCount, "tvTtsCount");
        StringBuilder sb = new StringBuilder();
        sb.append(contentLength);
        sb.append('/');
        sb.append(this.maxLength);
        tvTtsCount.setText(sb.toString());
    }

    static /* synthetic */ void showLoadingView$default(CustomTtsVoiceInputDialog customTtsVoiceInputDialog, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        customTtsVoiceInputDialog.showLoadingView(z);
    }

    private final void showLoadingView(boolean isLoading) {
        if (isLoading) {
            KeyboardUtils.hideSoftInput((EditText) _$_findCachedViewById(C4188R.id.etTtsContent));
        }
        ConstraintLayout loadingView = (ConstraintLayout) _$_findCachedViewById(C4188R.id.loadingView);
        Intrinsics.checkExpressionValueIsNotNull(loadingView, "loadingView");
        loadingView.setVisibility(isLoading ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void disLoadDialog() {
        showLoadingView(false);
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismiss() {
        KeyboardUtils.hideSoftInput((EditText) _$_findCachedViewById(C4188R.id.etTtsContent));
        super.dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismissAllowingStateLoss() {
        ProgressBar pbTtsLoading = (ProgressBar) _$_findCachedViewById(C4188R.id.pbTtsLoading);
        Intrinsics.checkExpressionValueIsNotNull(pbTtsLoading, "pbTtsLoading");
        pbTtsLoading.setIndeterminateDrawable(ContextCompat.getDrawable(requireContext(), C4188R.drawable.ic_base_load));
        KeyboardUtils.hideSoftInput((EditText) _$_findCachedViewById(C4188R.id.etTtsContent));
        Pdlog.m3273d(this.TAG, "onDismiss");
        CoroutineScope coroutineScope = this.mCoroutineScope;
        if (coroutineScope != null) {
            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
        }
        this.mCoroutineScope = (CoroutineScope) null;
        TtsVoiceHelper.INSTANCE.stopCruiseTts();
        super.dismissAllowingStateLoss();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
        super.onDismiss(dialog);
        Pdlog.m3273d(this.TAG, "onDismiss");
        CoroutineScope coroutineScope = this.mCoroutineScope;
        if (coroutineScope != null) {
            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
        }
        TtsVoiceHelper.INSTANCE.stopCruiseTts();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void translucent() {
        Window window;
        Dialog dialog = getDialog();
        View decorView = (dialog == null || (window = dialog.getWindow()) == null) ? null : window.getDecorView();
        if (decorView != null) {
            decorView.setSystemUiVisibility(3846);
        }
    }
}
