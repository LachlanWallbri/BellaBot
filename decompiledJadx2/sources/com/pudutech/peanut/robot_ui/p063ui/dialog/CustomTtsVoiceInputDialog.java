package com.pudutech.peanut.robot_ui.p063ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener;
import com.pudutech.peanut.robot_ui.p063ui.helper.TtsVoiceHelper;
import com.pudutech.peanut.robot_ui.util.KeyboardUtils;
import com.pudutech.peanut.robot_ui.util.NetStatusUtil;
import com.pudutech.peanut.robot_ui.util.ToastUtils;
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

/* compiled from: CustomTtsVoiceInputDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010-\u001a\u00020\u001fH\u0016J\b\u0010.\u001a\u00020\u001fH\u0016J\b\u0010/\u001a\u00020\u001fH\u0002J\u0012\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u000103H\u0016J&\u00104\u001a\u0004\u0018\u0001052\u0006\u00106\u001a\u0002072\b\u00108\u001a\u0004\u0018\u0001092\b\u00102\u001a\u0004\u0018\u000103H\u0016J\u0010\u0010:\u001a\u00020\u001f2\u0006\u0010;\u001a\u00020<H\u0016J\u0010\u0010=\u001a\u00020\u001f2\u0006\u0010>\u001a\u00020\u0014H\u0016J\b\u0010?\u001a\u00020\u001fH\u0016J\b\u0010@\u001a\u00020\u001fH\u0016J\u001a\u0010A\u001a\u00020\u001f2\u0006\u0010B\u001a\u0002052\b\u00102\u001a\u0004\u0018\u000103H\u0016J\u001a\u0010C\u001a\u00020\u001f2\u0006\u0010D\u001a\u00020E2\b\u0010F\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010G\u001a\u00020\u001fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R7\u0010\u001a\u001a\u001f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\n\"\u0004\b&\u0010\fR\u001a\u0010'\u001a\u00020(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,¨\u0006H"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/dialog/CustomTtsVoiceInputDialog;", "Landroidx/fragment/app/DialogFragment;", "()V", "INDEX_FIRST", "", "TAG", "", "kotlin.jvm.PlatformType", AIUIConstant.KEY_CONTENT, "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "editIndex", "getEditIndex", "()Ljava/lang/Integer;", "setEditIndex", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "isGening", "", "isSelect", "()Z", "setSelect", "(Z)V", "maxLength", "onContentChange", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "s", "", "getOnContentChange", "()Lkotlin/jvm/functions/Function1;", "setOnContentChange", "(Lkotlin/jvm/functions/Function1;)V", "title", "getTitle", "setTitle", "type", "Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "getType", "()Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "setType", "(Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;)V", "dismiss", "dismissAllowingStateLoss", "initView", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "onMultiWindowModeChanged", "isInMultiWindowMode", "onResume", "onStart", "onViewCreated", "view", "show", "manager", "Landroidx/fragment/app/FragmentManager;", AIUIConstant.KEY_TAG, "translucent", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CustomTtsVoiceInputDialog extends DialogFragment {
    private final int INDEX_FIRST;
    private HashMap _$_findViewCache;
    private Integer editIndex;
    private boolean isGening;
    private boolean isSelect;
    private Function1<? super String, Unit> onContentChange;
    private final String TAG = getClass().getSimpleName();
    private String title = "";
    private String content = "";
    private TtsVoiceHelper.TtsVoiceType type = TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE;
    private final int maxLength = 200;

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

    public final Integer getEditIndex() {
        return this.editIndex;
    }

    public final void setEditIndex(Integer num) {
        this.editIndex = num;
    }

    /* renamed from: isSelect, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    public final void setSelect(boolean z) {
        this.isSelect = z;
    }

    public final TtsVoiceHelper.TtsVoiceType getType() {
        return this.type;
    }

    public final void setType(TtsVoiceHelper.TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "<set-?>");
        this.type = ttsVoiceType;
    }

    public final Function1<String, Unit> getOnContentChange() {
        return this.onContentChange;
    }

    public final void setOnContentChange(Function1<? super String, Unit> function1) {
        this.onContentChange = function1;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        Pdlog.m3273d(this.TAG, "onCreateView");
        return inflater.inflate(C5508R.layout.dialog_voice_curise_input, container, false);
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Pdlog.m3273d(this.TAG, "onCreateDialog");
        setStyle(1, C5508R.style.AppTheme);
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
        View decorView = window.getDecorView();
        if (decorView == null) {
            Intrinsics.throwNpe();
        }
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.CustomTtsVoiceInputDialog$onViewCreated$1
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public final void onSystemUiVisibilityChange(int i) {
                CustomTtsVoiceInputDialog.this.translucent();
            }
        });
        translucent();
        initView();
    }

    private final void initView() {
        Pdlog.m3273d(this.TAG, "initView");
        setCancelable(false);
        TextView title_tv = (TextView) _$_findCachedViewById(C5508R.id.title_tv);
        Intrinsics.checkExpressionValueIsNotNull(title_tv, "title_tv");
        title_tv.setText(this.title);
        ((EditText) _$_findCachedViewById(C5508R.id.content_input)).setText(this.content);
        ((EditText) _$_findCachedViewById(C5508R.id.content_input)).setSelection(this.content.length());
        EditText content_input = (EditText) _$_findCachedViewById(C5508R.id.content_input);
        Intrinsics.checkExpressionValueIsNotNull(content_input, "content_input");
        content_input.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.CustomTtsVoiceInputDialog$initView$$inlined$addTextChangedListener$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                int i;
                EditText content_input2 = (EditText) CustomTtsVoiceInputDialog.this._$_findCachedViewById(C5508R.id.content_input);
                Intrinsics.checkExpressionValueIsNotNull(content_input2, "content_input");
                String obj = content_input2.getText().toString();
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                }
                int length = StringsKt.trim((CharSequence) obj).toString().length();
                i = CustomTtsVoiceInputDialog.this.maxLength;
                if (length > i) {
                    TextView alert_tv = (TextView) CustomTtsVoiceInputDialog.this._$_findCachedViewById(C5508R.id.alert_tv);
                    Intrinsics.checkExpressionValueIsNotNull(alert_tv, "alert_tv");
                    alert_tv.setVisibility(0);
                } else {
                    TextView alert_tv2 = (TextView) CustomTtsVoiceInputDialog.this._$_findCachedViewById(C5508R.id.alert_tv);
                    Intrinsics.checkExpressionValueIsNotNull(alert_tv2, "alert_tv");
                    alert_tv2.setVisibility(8);
                }
            }
        });
        ((Button) _$_findCachedViewById(C5508R.id.input_done)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.CustomTtsVoiceInputDialog$initView$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                int i;
                String str2;
                boolean z;
                String str3;
                String str4;
                String str5;
                EditText content_input2 = (EditText) CustomTtsVoiceInputDialog.this._$_findCachedViewById(C5508R.id.content_input);
                Intrinsics.checkExpressionValueIsNotNull(content_input2, "content_input");
                String obj = content_input2.getText().toString();
                if (obj != null) {
                    final String obj2 = StringsKt.trim((CharSequence) obj).toString();
                    str = CustomTtsVoiceInputDialog.this.TAG;
                    Pdlog.m3273d(str, "input_done " + obj2);
                    String str6 = obj2;
                    if (str6 == null || StringsKt.isBlank(str6)) {
                        ToastUtils.show(CustomTtsVoiceInputDialog.this.getContext(), CustomTtsVoiceInputDialog.this.getString(C5508R.string.pdStr7_72), new Object[0]);
                        return;
                    }
                    int length = obj2.length();
                    i = CustomTtsVoiceInputDialog.this.maxLength;
                    if (length > i) {
                        ToastUtils.show(CustomTtsVoiceInputDialog.this.getContext(), CustomTtsVoiceInputDialog.this.getString(C5508R.string.pdStr7_76), new Object[0]);
                        return;
                    }
                    if (Intrinsics.areEqual(obj2, CustomTtsVoiceInputDialog.this.getContent())) {
                        CustomTtsVoiceInputDialog.this.dismissAllowingStateLoss();
                        return;
                    }
                    boolean checkTtsExist = TtsVoiceHelper.INSTANCE.checkTtsExist(obj2, CustomTtsVoiceInputDialog.this.getType());
                    str2 = CustomTtsVoiceInputDialog.this.TAG;
                    Pdlog.m3273d(str2, "input_done " + obj2 + " ,Exist = " + checkTtsExist);
                    if (checkTtsExist) {
                        str5 = CustomTtsVoiceInputDialog.this.TAG;
                        Pdlog.m3273d(str5, "input done same");
                        ToastUtils.show(CustomTtsVoiceInputDialog.this.getContext(), CustomTtsVoiceInputDialog.this.getString(C5508R.string.pdStr7_92), new Object[0]);
                        return;
                    }
                    z = CustomTtsVoiceInputDialog.this.isGening;
                    if (z) {
                        str4 = CustomTtsVoiceInputDialog.this.TAG;
                        Pdlog.m3274e(str4, "input_done isGening ,do not restart");
                        return;
                    }
                    CustomTtsVoiceInputDialog.this.isGening = true;
                    Integer editIndex = CustomTtsVoiceInputDialog.this.getEditIndex();
                    int intValue = editIndex != null ? editIndex.intValue() : CustomTtsVoiceInputDialog.this.INDEX_FIRST;
                    str3 = CustomTtsVoiceInputDialog.this.TAG;
                    Pdlog.m3274e(str3, "addNewTtsVoice index=" + intValue);
                    TtsVoiceHelper.INSTANCE.addNewTtsVoice(obj2, CustomTtsVoiceInputDialog.this.getType(), Integer.valueOf(intValue), CustomTtsVoiceInputDialog.this.getIsSelect(), new Function1<Throwable, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.CustomTtsVoiceInputDialog$initView$2.1
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
                            String str7;
                            String str8;
                            if (th != null) {
                                str7 = CustomTtsVoiceInputDialog.this.TAG;
                                Pdlog.m3274e(str7, obj2 + " ----  onError :");
                                str8 = CustomTtsVoiceInputDialog.this.TAG;
                                Pdlog.m3274e(str8, Log.getStackTraceString(th));
                                Context context = RobotContext.INSTANCE.getContext();
                                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                                String string = CustomTtsVoiceInputDialog.this.getString(C5508R.string.pdStr7_74);
                                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_74)");
                                Object[] objArr = {th.getMessage()};
                                String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
                                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                                ToastUtils.show(context, format, new Object[0]);
                                CustomTtsVoiceInputDialog.this.isGening = false;
                                return;
                            }
                            CustomTtsVoiceInputDialog.this.dismissAllowingStateLoss();
                            CustomTtsVoiceInputDialog.this.isGening = false;
                            Function1<String, Unit> onContentChange = CustomTtsVoiceInputDialog.this.getOnContentChange();
                            if (onContentChange != null) {
                                String str9 = obj2;
                                if (str9 == null) {
                                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                                }
                                onContentChange.invoke(StringsKt.trim((CharSequence) str9).toString());
                            }
                        }
                    });
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
        });
        ((Button) _$_findCachedViewById(C5508R.id.try_play)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.CustomTtsVoiceInputDialog$initView$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i;
                boolean z;
                String str;
                EditText content_input2 = (EditText) CustomTtsVoiceInputDialog.this._$_findCachedViewById(C5508R.id.content_input);
                Intrinsics.checkExpressionValueIsNotNull(content_input2, "content_input");
                String obj = content_input2.getText().toString();
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                }
                final String obj2 = StringsKt.trim((CharSequence) obj).toString();
                if (StringsKt.isBlank(obj2)) {
                    ToastUtils.show(CustomTtsVoiceInputDialog.this.getContext(), CustomTtsVoiceInputDialog.this.getString(C5508R.string.pdStr7_72), new Object[0]);
                    return;
                }
                int length = obj2.length();
                i = CustomTtsVoiceInputDialog.this.maxLength;
                if (length > i) {
                    ToastUtils.show(CustomTtsVoiceInputDialog.this.getContext(), CustomTtsVoiceInputDialog.this.getString(C5508R.string.pdStr7_76), new Object[0]);
                    return;
                }
                if (NetStatusUtil.isConnected(RobotContext.INSTANCE.getContext())) {
                    z = CustomTtsVoiceInputDialog.this.isGening;
                    if (z) {
                        str = CustomTtsVoiceInputDialog.this.TAG;
                        Pdlog.m3274e(str, "isGening ,do not restart");
                        return;
                    } else {
                        CustomTtsVoiceInputDialog.this.isGening = true;
                        TtsVoiceHelper.INSTANCE.genTtsVoice(obj2, TtsVoiceHelper.INSTANCE.getCruiseTempFilePath(RobotContext.INSTANCE.getContext()), new OnTtsListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.CustomTtsVoiceInputDialog$initView$3.1
                            @Override // com.pudutech.tts_sdk.tts.OnTtsListener
                            public void onProgress(int proses) {
                            }

                            @Override // com.pudutech.tts_sdk.tts.OnTtsListener
                            public void onComplete(String filePath) {
                                Intrinsics.checkParameterIsNotNull(filePath, "filePath");
                                TtsVoiceHelper.INSTANCE.playTempCruiseTts();
                                CustomTtsVoiceInputDialog.this.isGening = false;
                            }

                            @Override // com.pudutech.tts_sdk.tts.OnTtsListener
                            public void onError(int code, String msg) {
                                String str2;
                                Intrinsics.checkParameterIsNotNull(msg, "msg");
                                str2 = CustomTtsVoiceInputDialog.this.TAG;
                                Pdlog.m3274e(str2, obj2 + " ----  onError :" + code + " , " + msg);
                                Context context = RobotContext.INSTANCE.getContext();
                                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                                String string = CustomTtsVoiceInputDialog.this.getString(C5508R.string.pdStr7_74);
                                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr7_74)");
                                Object[] objArr = {msg};
                                String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
                                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                                ToastUtils.show(context, format, new Object[0]);
                                CustomTtsVoiceInputDialog.this.isGening = false;
                            }
                        });
                        return;
                    }
                }
                ToastUtils.show(RobotContext.INSTANCE.getContext(), CustomTtsVoiceInputDialog.this.getString(C5508R.string.pdStr7_73), new Object[0]);
            }
        });
        ((FrameLayout) _$_findCachedViewById(C5508R.id.cancel)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.CustomTtsVoiceInputDialog$initView$4
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                String str;
                str = CustomTtsVoiceInputDialog.this.TAG;
                Pdlog.m3273d(str, "cancel");
                CustomTtsVoiceInputDialog.this.dismissAllowingStateLoss();
            }
        });
        if (((EditText) _$_findCachedViewById(C5508R.id.content_input)) != null) {
            KeyboardUtils.hideSoftInput((EditText) _$_findCachedViewById(C5508R.id.content_input));
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismiss() {
        if (((EditText) _$_findCachedViewById(C5508R.id.content_input)) != null) {
            KeyboardUtils.hideSoftInput((EditText) _$_findCachedViewById(C5508R.id.content_input));
        }
        super.dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismissAllowingStateLoss() {
        if (((EditText) _$_findCachedViewById(C5508R.id.content_input)) != null) {
            KeyboardUtils.hideSoftInput((EditText) _$_findCachedViewById(C5508R.id.content_input));
        }
        super.dismissAllowingStateLoss();
    }

    @Override // androidx.fragment.app.Fragment
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        translucent();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
        super.onDismiss(dialog);
        Pdlog.m3273d(this.TAG, "onDismiss");
        TtsVoiceHelper.INSTANCE.stopCruiseTts();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
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

    @Override // androidx.fragment.app.DialogFragment
    public void show(FragmentManager manager, String tag) {
        Intrinsics.checkParameterIsNotNull(manager, "manager");
        super.show(manager, tag);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void translucent() {
        Window window;
        if (Build.VERSION.SDK_INT >= 19) {
            Dialog dialog = getDialog();
            View decorView = (dialog == null || (window = dialog.getWindow()) == null) ? null : window.getDecorView();
            if (decorView != null) {
                decorView.setSystemUiVisibility(3846);
            }
        }
    }
}
