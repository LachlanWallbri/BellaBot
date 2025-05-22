package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoAnimation;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FaceAnimationDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J&\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u0018\u001a\u00020\nH\u0016J\u0010\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u001bH\u0016J\b\u0010\u001e\u001a\u00020\nH\u0016J\u001a\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020\u00132\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u000e\u0010!\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\"\u001a\u00020\n2\u0006\u0010#\u001a\u00020\bJ\u001a\u0010$\u001a\u00020\n2\u0006\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010(\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/FaceAnimationDialog;", "Landroidx/fragment/app/DialogFragment;", "()V", "TAG", "", "animation", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoAnimation;", "onClickListener", "Landroid/view/View$OnClickListener;", "initView", "", "onAttach", "context", "Landroid/content/Context;", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDetach", "onHiddenChanged", "hidden", "", "onMultiWindowModeChanged", "isInMultiWindowMode", "onResume", "onViewCreated", "view", "playAnimation", "setOnClickListener", "listener", "show", "manager", "Landroidx/fragment/app/FragmentManager;", AIUIConstant.KEY_TAG, "translucent", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class FaceAnimationDialog extends DialogFragment {
    private final String TAG = "FaceAnimationDialog";
    private HashMap _$_findViewCache;
    private FaceVideoAnimation animation;
    private View.OnClickListener onClickListener;

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

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        Pdlog.m3273d(this.TAG, "onCreateView");
        return inflater.inflate(C4188R.layout.dialog_face_animation, container, false);
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Pdlog.m3273d(this.TAG, "onCreateDialog");
        setStyle(1, C4188R.style.AppTheme);
        Dialog onCreateDialog = super.onCreateDialog(savedInstanceState);
        Intrinsics.checkExpressionValueIsNotNull(onCreateDialog, "super.onCreateDialog(savedInstanceState)");
        return onCreateDialog;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Window window;
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        Dialog dialog = getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.addFlags(8);
        }
        Dialog dialog2 = getDialog();
        if (dialog2 == null) {
            Intrinsics.throwNpe();
        }
        dialog2.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.FaceAnimationDialog$onViewCreated$1
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                Window window2;
                Dialog dialog3 = FaceAnimationDialog.this.getDialog();
                if (dialog3 != null && (window2 = dialog3.getWindow()) != null) {
                    window2.clearFlags(8);
                }
                FaceAnimationDialog.this.translucent();
            }
        });
        Dialog dialog3 = getDialog();
        if (dialog3 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(dialog3, "dialog!!");
        Window window2 = dialog3.getWindow();
        if (window2 == null) {
            Intrinsics.throwNpe();
        }
        View decorView = window2.getDecorView();
        if (decorView == null) {
            Intrinsics.throwNpe();
        }
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.FaceAnimationDialog$onViewCreated$2
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public final void onSystemUiVisibilityChange(int i) {
                FaceAnimationDialog.this.translucent();
            }
        });
        translucent();
        initView();
    }

    private final void initView() {
        FaceVideoView faceVideoView;
        Pdlog.m3273d(this.TAG, "initView ");
        FaceVideoAnimation faceVideoAnimation = this.animation;
        if (faceVideoAnimation != null) {
            ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).playAnimation(faceVideoAnimation);
        }
        View.OnClickListener onClickListener = this.onClickListener;
        if (onClickListener == null || (faceVideoView = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)) == null) {
            return;
        }
        faceVideoView.setOnClickListener(onClickListener);
    }

    public final void playAnimation(FaceVideoAnimation animation) {
        Intrinsics.checkParameterIsNotNull(animation, "animation");
        Pdlog.m3273d(this.TAG, "playAnimation : animation = " + animation + "; ");
        if (((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)) == null) {
            this.animation = animation;
        } else {
            ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).playAnimation(animation);
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public void show(FragmentManager manager, String tag) {
        Intrinsics.checkParameterIsNotNull(manager, "manager");
        if (isVisible()) {
            Pdlog.m3273d(this.TAG, "show failed , dialog is visible: tag = " + tag + "; ");
            return;
        }
        if (Build.VERSION.SDK_INT <= 16 || !manager.isDestroyed()) {
            try {
                manager.beginTransaction().remove(this).commit();
                super.show(manager, tag);
            } catch (Exception e) {
                Pdlog.m3274e(this.TAG, "show : " + Log.getStackTraceString(e));
            }
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super.onAttach(context);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        Pdlog.m3273d(this.TAG, "onDetach");
        FaceVideoView faceVideoView = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        if (faceVideoView != null) {
            faceVideoView.stopPlay();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        FaceVideoView faceVideoView;
        super.onHiddenChanged(hidden);
        Pdlog.m3273d(this.TAG, "onHiddenChanged " + hidden);
        if (!hidden || (faceVideoView = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)) == null) {
            return;
        }
        faceVideoView.stopPlay();
    }

    public final void setOnClickListener(View.OnClickListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.onClickListener = listener;
        FaceVideoView faceVideoView = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        if (faceVideoView != null) {
            faceVideoView.setOnClickListener(listener);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        translucent();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        Window window;
        super.onResume();
        translucent();
        Dialog dialog = getDialog();
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        window.setLayout(-1, -1);
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
