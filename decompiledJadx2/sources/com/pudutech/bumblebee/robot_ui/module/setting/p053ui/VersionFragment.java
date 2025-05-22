package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.manager.AppUpdateManager;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ShowTipMsgDialog;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.util.AppUtil;
import com.pudutech.bumblebee.robot_ui.util.NavigationBarUtil;
import com.pudutech.bumblebee.robot_ui.util.NetStatusUtil;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VersionFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\nH\u0002J&\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u001a\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0016\u001a\u00020\nH\u0002J\b\u0010\u0017\u001a\u00020\nH\u0002J\u0010\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/VersionFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "checkUpdateDialog", "Landroid/app/Dialog;", "mShowTipDialog", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "checkAppUpdate", "", "initView", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "recheckAppUpdate", "showCheckUpdateDialog", "showTipDialog", "tips", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class VersionFragment extends Fragment {
    private final String TAG = "VersionFragment";
    private HashMap _$_findViewCache;
    private Dialog checkUpdateDialog;
    private ShowTipMsgDialog mShowTipDialog;

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

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C4188R.layout.fragment_version_setup, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
        checkAppUpdate();
        Pdlog.m3273d(this.TAG, "onViewCreated()");
    }

    private final void initView() {
        TextView version_show = (TextView) _$_findCachedViewById(C4188R.id.version_show);
        Intrinsics.checkExpressionValueIsNotNull(version_show, "version_show");
        version_show.setText(AppUtil.INSTANCE.getVersionName(getActivity()));
        TextView show_current_app_version = (TextView) _$_findCachedViewById(C4188R.id.show_current_app_version);
        Intrinsics.checkExpressionValueIsNotNull(show_current_app_version, "show_current_app_version");
        show_current_app_version.setText(AppUtil.INSTANCE.getVersionName(getActivity()));
        ((FrameLayout) _$_findCachedViewById(C4188R.id.check_update)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VersionFragment$initView$1
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
                VersionFragment.this.recheckAppUpdate();
            }
        }, 3, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recheckAppUpdate() {
        showCheckUpdateDialog();
        AppUpdateManager.INSTANCE.recheckAppUpdate(new VersionFragment$recheckAppUpdate$1(this));
    }

    private final void checkAppUpdate() {
        if (!NetStatusUtil.isConnected(RobotContext.INSTANCE.getContext())) {
            String string = getString(C4188R.string.pdStr11_13);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr11_13)");
            showTipDialog(string);
        } else {
            Pdlog.m3273d(this.TAG, "checkAppUpdate()");
            AppUpdateManager.INSTANCE.checkAppUpdate(new VersionFragment$checkAppUpdate$1(this));
        }
    }

    private final void showCheckUpdateDialog() {
        Button button;
        FragmentActivity activity = getActivity();
        LayoutInflater layoutInflater = activity != null ? activity.getLayoutInflater() : null;
        View inflate = layoutInflater != null ? layoutInflater.inflate(C4188R.layout.fragment_version_setup_check_dialog, (ViewGroup) null) : null;
        this.checkUpdateDialog = new Dialog(getActivity());
        Dialog dialog = this.checkUpdateDialog;
        if (dialog != null) {
            dialog.requestWindowFeature(1);
        }
        Dialog dialog2 = this.checkUpdateDialog;
        if (dialog2 != null) {
            dialog2.setCancelable(false);
        }
        Dialog dialog3 = this.checkUpdateDialog;
        Window window = dialog3 != null ? dialog3.getWindow() : null;
        NavigationBarUtil.focusNotAle(window);
        Dialog dialog4 = this.checkUpdateDialog;
        if (dialog4 != null) {
            dialog4.show();
        }
        NavigationBarUtil.hideNavigationBar(window);
        NavigationBarUtil.clearFocusNotAle(window);
        WindowManager.LayoutParams attributes = window != null ? window.getAttributes() : null;
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        if (window != null) {
            window.setGravity(17);
        }
        if (attributes != null) {
            attributes.width = -1;
        }
        if (attributes != null) {
            attributes.height = -1;
        }
        if (window != null) {
            window.setAttributes(attributes);
        }
        Dialog dialog5 = this.checkUpdateDialog;
        if (dialog5 != null) {
            dialog5.setContentView(inflate);
        }
        if (inflate == null || (button = (Button) inflate.findViewById(C4188R.id.cancel)) == null) {
            return;
        }
        button.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VersionFragment$showCheckUpdateDialog$1
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
                Dialog dialog6;
                Dialog dialog7;
                Dialog dialog8;
                Intrinsics.checkParameterIsNotNull(it, "it");
                dialog6 = VersionFragment.this.checkUpdateDialog;
                if (dialog6 == null) {
                    dialog8 = VersionFragment.this.checkUpdateDialog;
                    Boolean valueOf = dialog8 != null ? Boolean.valueOf(dialog8.isShowing()) : null;
                    if (valueOf == null) {
                        Intrinsics.throwNpe();
                    }
                    if (valueOf.booleanValue()) {
                        return;
                    }
                }
                dialog7 = VersionFragment.this.checkUpdateDialog;
                if (dialog7 != null) {
                    dialog7.dismiss();
                }
            }
        }, 3, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showTipDialog(String tips) {
        FragmentActivity it;
        Dialog dialog = this.checkUpdateDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
        if (this.mShowTipDialog == null && (it = getActivity()) != null) {
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            this.mShowTipDialog = new ShowTipMsgDialog(it);
        }
        ShowTipMsgDialog showTipMsgDialog = this.mShowTipDialog;
        if (showTipMsgDialog == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog.showTipMsg(tips);
        ShowTipMsgDialog showTipMsgDialog2 = this.mShowTipDialog;
        if (showTipMsgDialog2 == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog2.setCanCancel(true);
        ShowTipMsgDialog showTipMsgDialog3 = this.mShowTipDialog;
        if (showTipMsgDialog3 == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog3.setCallback(new ShowTipMsgDialog.Callback() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.VersionFragment$showTipDialog$2
            @Override // com.pudutech.bumblebee.robot_ui.module.setting.ui.dialog.ShowTipMsgDialog.Callback
            public void onDissmiss(ShowTipMsgDialog dailog) {
                Intrinsics.checkParameterIsNotNull(dailog, "dailog");
                VersionFragment.this.mShowTipDialog = (ShowTipMsgDialog) null;
            }
        });
        ShowTipMsgDialog showTipMsgDialog4 = this.mShowTipDialog;
        if (showTipMsgDialog4 == null) {
            Intrinsics.throwNpe();
        }
        showTipMsgDialog4.show();
    }
}
