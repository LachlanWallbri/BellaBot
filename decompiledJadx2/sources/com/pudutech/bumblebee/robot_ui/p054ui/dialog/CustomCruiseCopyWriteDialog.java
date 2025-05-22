package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.base.BaseDialog;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.p054ui.StayPointPreviewActivity;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt;
import com.pudutech.bumblebee.robot_ui.util.KeyboardUtils;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: CustomCruiseCopyWriteDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u0001H\u0016J\b\u0010\u000b\u001a\u00020\u0007H\u0002J\b\u0010\f\u001a\u00020\rH\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/CustomCruiseCopyWriteDialog;", "Lcom/pudutech/bumblebee/robot_ui/base/BaseDialog;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "bindView", "", "rootView", "Landroid/view/View;", "dialog", "dismissCopyWriteDialog", "getDialogLayoutId", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CustomCruiseCopyWriteDialog extends BaseDialog {
    private final String TAG = getClass().getSimpleName();
    private HashMap _$_findViewCache;

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

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseDialog
    public int getDialogLayoutId() {
        return C4188R.layout.dialog_custom_copy_write;
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseDialog
    public void bindView(final View rootView, BaseDialog dialog) {
        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
        if (rootView != null) {
            ((EditText) _$_findCachedViewById(C4188R.id.etCustomCopyWrite)).setText(SpUtils.get(rootView.getContext(), Constans.KEY_CRUISE_STAY_COPY_WRITE_TYPE, ""));
            EditText etCustomCopyWrite = (EditText) _$_findCachedViewById(C4188R.id.etCustomCopyWrite);
            Intrinsics.checkExpressionValueIsNotNull(etCustomCopyWrite, "etCustomCopyWrite");
            ViewExtKt.disableCopyAndPaste(etCustomCopyWrite);
            ((Button) _$_findCachedViewById(C4188R.id.btnConfirm)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomCruiseCopyWriteDialog$bindView$$inlined$apply$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    Context context = rootView.getContext();
                    EditText etCustomCopyWrite2 = (EditText) this._$_findCachedViewById(C4188R.id.etCustomCopyWrite);
                    Intrinsics.checkExpressionValueIsNotNull(etCustomCopyWrite2, "etCustomCopyWrite");
                    Editable text = etCustomCopyWrite2.getText();
                    Intrinsics.checkExpressionValueIsNotNull(text, "etCustomCopyWrite.text");
                    SpUtils.set(context, Constans.KEY_CRUISE_STAY_COPY_WRITE_TYPE, StringsKt.trim(text).toString());
                    this.dismissCopyWriteDialog();
                }
            }, 3, null));
            ((Button) _$_findCachedViewById(C4188R.id.btnPreview)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomCruiseCopyWriteDialog$bindView$$inlined$apply$lambda$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                    KeyboardUtils.hideSoftInput((EditText) this._$_findCachedViewById(C4188R.id.etCustomCopyWrite));
                    EditText etCustomCopyWrite2 = (EditText) this._$_findCachedViewById(C4188R.id.etCustomCopyWrite);
                    Intrinsics.checkExpressionValueIsNotNull(etCustomCopyWrite2, "etCustomCopyWrite");
                    Editable text = etCustomCopyWrite2.getText();
                    Intrinsics.checkExpressionValueIsNotNull(text, "etCustomCopyWrite.text");
                    String obj = StringsKt.trim(text).toString();
                    String str = obj;
                    if (str == null || StringsKt.isBlank(str)) {
                        ToastUtils.show(rootView.getContext(), this.getString(C4188R.string.please_enter_text), new Object[0]);
                    } else {
                        this.startActivity(new Intent(rootView.getContext(), (Class<?>) StayPointPreviewActivity.class).putExtra(Constans.EXTRA_CRUISE_STAY_COPY_WRITE_PREVIEW, obj));
                    }
                }
            }, 3, null));
            ((ImageView) _$_findCachedViewById(C4188R.id.ivClose)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.CustomCruiseCopyWriteDialog$bindView$$inlined$apply$lambda$3
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
                    CustomCruiseCopyWriteDialog.this.dismissCopyWriteDialog();
                }
            }, 3, null));
            KeyboardUtils.showSoftInput((EditText) _$_findCachedViewById(C4188R.id.etCustomCopyWrite));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void dismissCopyWriteDialog() {
        KeyboardUtils.hideSoftInput((EditText) _$_findCachedViewById(C4188R.id.etCustomCopyWrite));
        dismissDialog();
    }
}
