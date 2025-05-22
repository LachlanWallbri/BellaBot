package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.view.View;
import android.widget.Button;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.base.BaseDialog;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeleteBoxDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\r\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0001H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0005H\u0016R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\"\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\t¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/DeleteBoxDialog;", "Lcom/pudutech/bumblebee/robot_ui/base/BaseDialog;", "()V", "onNegativeClickListener", "Lkotlin/Function0;", "", "getOnNegativeClickListener", "()Lkotlin/jvm/functions/Function0;", "setOnNegativeClickListener", "(Lkotlin/jvm/functions/Function0;)V", "onPositiveClickListener", "getOnPositiveClickListener", "setOnPositiveClickListener", "bindView", "rootView", "Landroid/view/View;", "dialog", "getDialogLayoutId", "", "onResume", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class DeleteBoxDialog extends BaseDialog {
    private HashMap _$_findViewCache;
    private Function0<Unit> onNegativeClickListener;
    private Function0<Unit> onPositiveClickListener;

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

    public final Function0<Unit> getOnPositiveClickListener() {
        return this.onPositiveClickListener;
    }

    public final void setOnPositiveClickListener(Function0<Unit> function0) {
        this.onPositiveClickListener = function0;
    }

    public final Function0<Unit> getOnNegativeClickListener() {
        return this.onNegativeClickListener;
    }

    public final void setOnNegativeClickListener(Function0<Unit> function0) {
        this.onNegativeClickListener = function0;
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseDialog
    public int getDialogLayoutId() {
        return C4188R.layout.dialog_delete_box;
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseDialog, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setFullScreen();
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseDialog
    public void bindView(View rootView, BaseDialog dialog) {
        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
        ((Button) _$_findCachedViewById(C4188R.id.btnCancel)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.DeleteBoxDialog$bindView$1
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
                Function0<Unit> onNegativeClickListener = DeleteBoxDialog.this.getOnNegativeClickListener();
                if (onNegativeClickListener != null) {
                    onNegativeClickListener.invoke();
                }
            }
        }, 3, null));
        ((Button) _$_findCachedViewById(C4188R.id.btnSure)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.DeleteBoxDialog$bindView$2
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
                Function0<Unit> onPositiveClickListener = DeleteBoxDialog.this.getOnPositiveClickListener();
                if (onPositiveClickListener != null) {
                    onPositiveClickListener.invoke();
                }
            }
        }, 3, null));
    }
}
