package com.pudutech.freeinstall_ui.dialog;

import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.pudutech.freeinstall_ui.AppContext;
import com.pudutech.module_freeinstall_ui.C5362R;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: CountDownLoadingDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0012\u001a\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0001H\u0016J\u000e\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u000eJ\b\u0010\u0017\u001a\u00020\u0004H\u0016J\u0010\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\bH\u0016J\u000e\u0010\u001b\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u001c\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/dialog/CountDownLoadingDialog;", "Lcom/pudutech/freeinstall_ui/dialog/BaseDialog;", "()V", "hasWaitTime", "", "needTime", "onCloseClick", "Lkotlin/Function0;", "", "getOnCloseClick", "()Lkotlin/jvm/functions/Function0;", "setOnCloseClick", "(Lkotlin/jvm/functions/Function0;)V", "stopCountDown", "", "title", "", "visible", "bindView", "rootView", "Landroid/view/View;", "dialog", "closeVisible", "getDialogLayoutId", "onDismiss", "Landroid/content/DialogInterface;", "onResume", "setTitle", "startCount", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CountDownLoadingDialog extends BaseDialog {
    private HashMap _$_findViewCache;
    private int hasWaitTime;
    private Function0<Unit> onCloseClick;
    private boolean stopCountDown;
    private String title = "";
    private boolean visible = true;
    private int needTime = 15;

    @Override // com.pudutech.freeinstall_ui.dialog.BaseDialog
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.freeinstall_ui.dialog.BaseDialog
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

    @Override // com.pudutech.freeinstall_ui.dialog.BaseDialog, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final Function0<Unit> getOnCloseClick() {
        return this.onCloseClick;
    }

    public final void setOnCloseClick(Function0<Unit> function0) {
        this.onCloseClick = function0;
    }

    @Override // com.pudutech.freeinstall_ui.dialog.BaseDialog
    public int getDialogLayoutId() {
        return C5362R.layout.dialog_count_down_loading;
    }

    @Override // com.pudutech.freeinstall_ui.dialog.BaseDialog, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        setFullScreen();
        startCount();
    }

    private final void startCount() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new CountDownLoadingDialog$startCount$1(this, null), 2, null);
    }

    @Override // com.pudutech.freeinstall_ui.dialog.BaseDialog
    public void bindView(View rootView, BaseDialog dialog) {
        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
        ((ImageView) _$_findCachedViewById(C5362R.id.iv_close)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.freeinstall_ui.dialog.CountDownLoadingDialog$bindView$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CountDownLoadingDialog.this.dismissDialog();
                Function0<Unit> onCloseClick = CountDownLoadingDialog.this.getOnCloseClick();
                if (onCloseClick != null) {
                    onCloseClick.invoke();
                }
            }
        });
        if (this.title.length() > 0) {
            TextView tv_tips = (TextView) _$_findCachedViewById(C5362R.id.tv_tips);
            Intrinsics.checkExpressionValueIsNotNull(tv_tips, "tv_tips");
            tv_tips.setText(this.title);
        }
        if (this.visible) {
            ImageView iv_close = (ImageView) _$_findCachedViewById(C5362R.id.iv_close);
            Intrinsics.checkExpressionValueIsNotNull(iv_close, "iv_close");
            iv_close.setVisibility(0);
        } else {
            ImageView iv_close2 = (ImageView) _$_findCachedViewById(C5362R.id.iv_close);
            Intrinsics.checkExpressionValueIsNotNull(iv_close2, "iv_close");
            iv_close2.setVisibility(8);
        }
        TextView tv_need_time = (TextView) _$_findCachedViewById(C5362R.id.tv_need_time);
        Intrinsics.checkExpressionValueIsNotNull(tv_need_time, "tv_need_time");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = AppContext.INSTANCE.getContext().getString(C5362R.string.expand_map_loading_content);
        Intrinsics.checkExpressionValueIsNotNull(string, "AppContext.context.getSt…pand_map_loading_content)");
        Object[] objArr = {Integer.valueOf(this.needTime)};
        String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        tv_need_time.setText(format);
    }

    public final void setTitle(String title) {
        Intrinsics.checkParameterIsNotNull(title, "title");
        this.title = title;
    }

    public final void closeVisible(boolean visible) {
        this.visible = visible;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
        super.onDismiss(dialog);
        this.stopCountDown = true;
        this.hasWaitTime = 0;
    }
}
