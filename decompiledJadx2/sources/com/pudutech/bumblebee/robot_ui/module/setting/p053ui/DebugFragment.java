package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ValidationDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.util.AppUtil;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DebugFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\tH\u0002J\u0006\u0010\n\u001a\u00020\tJ&\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0013\u001a\u00020\tH\u0016J\b\u0010\u0014\u001a\u00020\tH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/DebugFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "checkPermissionDialog", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ValidationDialog;", "packageName", "clickDebug", "", "initDatas", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "showCheckPermissionDialog", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class DebugFragment extends Fragment {
    private static boolean isInputPassword;
    private HashMap _$_findViewCache;
    private ValidationDialog checkPermissionDialog;
    private final String TAG = "DebugFragment";
    private final String packageName = "com.pudutech.mirsdk";

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
        return inflater.inflate(C4188R.layout.fragment_debug_setup, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        initDatas();
    }

    public final void initDatas() {
        ((Button) _$_findCachedViewById(C4188R.id.btn_debug)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DebugFragment$initDatas$1
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
                DebugFragment.this.clickDebug();
            }
        }, 3, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clickDebug() {
        Context it = getContext();
        if (it != null) {
            if (!isInputPassword) {
                LanguageUtils.Companion companion = LanguageUtils.INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (!companion.isZh(it)) {
                    showCheckPermissionDialog();
                    return;
                }
            }
            AppUtil appUtil = AppUtil.INSTANCE;
            if (it == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.MyBaseActivity");
            }
            appUtil.startDebugFunction((MyBaseActivity) it);
        }
    }

    private final void showCheckPermissionDialog() {
        if (this.checkPermissionDialog == null) {
            Context context = getContext();
            if (context == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
            this.checkPermissionDialog = new ValidationDialog(context);
        }
        Pdlog.m3273d(this.TAG, "showCheckPermissionDialog");
        ValidationDialog validationDialog = this.checkPermissionDialog;
        if (validationDialog == null) {
            Intrinsics.throwNpe();
        }
        validationDialog.setOnPermissionCheckResult(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.DebugFragment$showCheckPermissionDialog$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                String str;
                ValidationDialog validationDialog2;
                str = DebugFragment.this.TAG;
                Pdlog.m3273d(str, "showCheckPermissionDialog onPermissionCheckResult " + z);
                if (z) {
                    DebugFragment.isInputPassword = true;
                    validationDialog2 = DebugFragment.this.checkPermissionDialog;
                    if (validationDialog2 != null) {
                        validationDialog2.dismiss();
                    }
                    Context context2 = DebugFragment.this.getContext();
                    if (context2 != null) {
                        AppUtil appUtil = AppUtil.INSTANCE;
                        if (context2 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.MyBaseActivity");
                        }
                        appUtil.startDebugFunction((MyBaseActivity) context2);
                    }
                }
            }
        });
        ValidationDialog validationDialog2 = this.checkPermissionDialog;
        if (validationDialog2 == null) {
            Intrinsics.throwNpe();
        }
        validationDialog2.show();
    }
}
