package com.pudutech.bumblebee.robot_ui.advertise;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.advertise.AdverConfig;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt;
import com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.DimensionsKt;

/* compiled from: AdverBaseFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\"\u001a\u00020\u001dH\u0002J\b\u0010#\u001a\u00020\u001dH\u0004J\u001a\u0010$\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020\u00062\b\b\u0002\u0010&\u001a\u00020'H\u0016J\u0010\u0010(\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020*H\u0016J\u0010\u0010+\u001a\u00020\u001d2\u0006\u0010,\u001a\u00020\u0012H\u0016J\u0010\u0010-\u001a\u00020\u001d2\u0006\u0010.\u001a\u00020/H&R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R7\u0010\u0017\u001a\u001f\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u00060"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/advertise/AdverBaseFragment;", "VM", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "Lcom/pudutech/disinfect/baselib/base/fragment/BaseVmFragment;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "mAdverTaskDialog", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdverTaskDialog;", "mLastWidth", "", "getMLastWidth", "()I", "setMLastWidth", "(I)V", "mSceneConfig", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdSceneConfig;", "getMSceneConfig", "()Lcom/pudutech/bumblebee/robot_ui/advertise/AdSceneConfig;", "setMSceneConfig", "(Lcom/pudutech/bumblebee/robot_ui/advertise/AdSceneConfig;)V", "onOutsideTask", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdverConfig$TaskType;", "Lkotlin/ParameterName;", "name", "taskType", "", "getOnOutsideTask", "()Lkotlin/jvm/functions/Function1;", "setOnOutsideTask", "(Lkotlin/jvm/functions/Function1;)V", "initAdDialog", "initComListener", "setContinueTaskTip", "time", "isNeedUnit", "", "setVolume", "volume", "", "showOutsideAdScene", "sceneConfig", "updateAdverContent", AIUIConstant.KEY_CONTENT, "Lcom/pudutech/bumblebee/robot_ui/advertise/AdverBaseBean;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public abstract class AdverBaseFragment<VM extends BaseViewModel> extends BaseVmFragment<VM> {
    private final String TAG = "AdverBaseFragment";
    private HashMap _$_findViewCache;
    private AdverTaskDialog mAdverTaskDialog;
    private int mLastWidth;
    private AdSceneConfig mSceneConfig;
    private Function1<? super AdverConfig.TaskType, Unit> onOutsideTask;

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment
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

    @Override // com.pudutech.disinfect.baselib.base.fragment.BaseVmFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void setVolume(float volume) {
    }

    public abstract void updateAdverContent(AdverBaseBean content);

    public String getTAG() {
        return this.TAG;
    }

    public final Function1<AdverConfig.TaskType, Unit> getOnOutsideTask() {
        return this.onOutsideTask;
    }

    public final void setOnOutsideTask(Function1<? super AdverConfig.TaskType, Unit> function1) {
        this.onOutsideTask = function1;
    }

    public final AdSceneConfig getMSceneConfig() {
        return this.mSceneConfig;
    }

    public final void setMSceneConfig(AdSceneConfig adSceneConfig) {
        this.mSceneConfig = adSceneConfig;
    }

    public final int getMLastWidth() {
        return this.mLastWidth;
    }

    public final void setMLastWidth(int i) {
        this.mLastWidth = i;
    }

    public static /* synthetic */ void setContinueTaskTip$default(AdverBaseFragment adverBaseFragment, String str, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setContinueTaskTip");
        }
        if ((i & 2) != 0) {
            z = true;
        }
        adverBaseFragment.setContinueTaskTip(str, z);
    }

    public void setContinueTaskTip(String time, boolean isNeedUnit) {
        Intrinsics.checkParameterIsNotNull(time, "time");
        if (((TextView) _$_findCachedViewById(C4188R.id.adver_continue_task_time)) == null) {
            Pdlog.m3273d(getTAG(), "setContinueTaskTip() is null title =" + time);
            return;
        }
        if (isNeedUnit) {
            TextView adver_continue_task_unit = (TextView) _$_findCachedViewById(C4188R.id.adver_continue_task_unit);
            Intrinsics.checkExpressionValueIsNotNull(adver_continue_task_unit, "adver_continue_task_unit");
            ViewExtKt.isShowView(adver_continue_task_unit);
        } else {
            TextView adver_continue_task_unit2 = (TextView) _$_findCachedViewById(C4188R.id.adver_continue_task_unit);
            Intrinsics.checkExpressionValueIsNotNull(adver_continue_task_unit2, "adver_continue_task_unit");
            ViewExtKt.isGoneView(adver_continue_task_unit2);
        }
        TextView adver_continue_task_time = (TextView) _$_findCachedViewById(C4188R.id.adver_continue_task_time);
        Intrinsics.checkExpressionValueIsNotNull(adver_continue_task_time, "adver_continue_task_time");
        ViewExtKt.isShowView(adver_continue_task_time);
        int i = C4188R.dimen.adver_right_time_2;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        int dimen = DimensionsKt.dimen(requireActivity, i);
        if (time.length() > 3) {
            int i2 = C4188R.dimen.adver_right_time_4;
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkExpressionValueIsNotNull(requireActivity2, "requireActivity()");
            dimen = DimensionsKt.dimen(requireActivity2, i2);
        }
        TextView adver_continue_task_time2 = (TextView) _$_findCachedViewById(C4188R.id.adver_continue_task_time);
        Intrinsics.checkExpressionValueIsNotNull(adver_continue_task_time2, "adver_continue_task_time");
        adver_continue_task_time2.setText(time);
        if (this.mLastWidth != dimen) {
            TextView adver_continue_task_time3 = (TextView) _$_findCachedViewById(C4188R.id.adver_continue_task_time);
            Intrinsics.checkExpressionValueIsNotNull(adver_continue_task_time3, "adver_continue_task_time");
            ViewGroup.LayoutParams layoutParams = adver_continue_task_time3.getLayoutParams();
            layoutParams.height = -1;
            layoutParams.width = dimen;
            TextView adver_continue_task_time4 = (TextView) _$_findCachedViewById(C4188R.id.adver_continue_task_time);
            Intrinsics.checkExpressionValueIsNotNull(adver_continue_task_time4, "adver_continue_task_time");
            adver_continue_task_time4.setLayoutParams(layoutParams);
            String tag = getTAG();
            StringBuilder sb = new StringBuilder();
            sb.append("setContinueTaskTip() _width =");
            TextView adver_continue_task_time5 = (TextView) _$_findCachedViewById(C4188R.id.adver_continue_task_time);
            Intrinsics.checkExpressionValueIsNotNull(adver_continue_task_time5, "adver_continue_task_time");
            sb.append(adver_continue_task_time5.getLayoutParams().width);
            Pdlog.m3273d(tag, sb.toString());
        }
        this.mLastWidth = dimen;
        Pdlog.m3273d(getTAG(), "setContinueTaskTip() time =" + time + " isNeedUnit =" + isNeedUnit);
    }

    public void showOutsideAdScene(AdSceneConfig sceneConfig) {
        Intrinsics.checkParameterIsNotNull(sceneConfig, "sceneConfig");
        this.mSceneConfig = sceneConfig;
        if (((LinearLayout) _$_findCachedViewById(C4188R.id.adver_right_root)) == null) {
            Pdlog.m3273d(getTAG(), "showOutsideAdScene() ui is null AdSceneConfig =" + sceneConfig);
            return;
        }
        if (Intrinsics.areEqual(sceneConfig.getId(), AdSceneConfig.SOLICITING_PASSENGERS_MODE.getId())) {
            LinearLayout adver_right_root = (LinearLayout) _$_findCachedViewById(C4188R.id.adver_right_root);
            Intrinsics.checkExpressionValueIsNotNull(adver_right_root, "adver_right_root");
            ViewExtKt.isShowView(adver_right_root);
            TextView adver_continue_task_time = (TextView) _$_findCachedViewById(C4188R.id.adver_continue_task_time);
            Intrinsics.checkExpressionValueIsNotNull(adver_continue_task_time, "adver_continue_task_time");
            ViewExtKt.isGoneView(adver_continue_task_time);
            TextView adver_continue_task_unit = (TextView) _$_findCachedViewById(C4188R.id.adver_continue_task_unit);
            Intrinsics.checkExpressionValueIsNotNull(adver_continue_task_unit, "adver_continue_task_unit");
            ViewExtKt.isGoneView(adver_continue_task_unit);
            TextView adver_continue_task = (TextView) _$_findCachedViewById(C4188R.id.adver_continue_task);
            Intrinsics.checkExpressionValueIsNotNull(adver_continue_task, "adver_continue_task");
            adver_continue_task.setText(getString(C4188R.string.pdStr25_4));
            TextView adver_cancel_task = (TextView) _$_findCachedViewById(C4188R.id.adver_cancel_task);
            Intrinsics.checkExpressionValueIsNotNull(adver_cancel_task, "adver_cancel_task");
            adver_cancel_task.setText(getString(C4188R.string.pdStr25_1));
        } else if (Intrinsics.areEqual(sceneConfig.getId(), AdSceneConfig.CRUISE_MODE.getId())) {
            TextView adver_continue_task2 = (TextView) _$_findCachedViewById(C4188R.id.adver_continue_task);
            Intrinsics.checkExpressionValueIsNotNull(adver_continue_task2, "adver_continue_task");
            adver_continue_task2.setText(getString(C4188R.string.pdStr2_13));
            TextView adver_continue_task_time2 = (TextView) _$_findCachedViewById(C4188R.id.adver_continue_task_time);
            Intrinsics.checkExpressionValueIsNotNull(adver_continue_task_time2, "adver_continue_task_time");
            adver_continue_task_time2.setText("");
            TextView adver_continue_task_time3 = (TextView) _$_findCachedViewById(C4188R.id.adver_continue_task_time);
            Intrinsics.checkExpressionValueIsNotNull(adver_continue_task_time3, "adver_continue_task_time");
            ViewExtKt.isGoneView(adver_continue_task_time3);
            TextView adver_continue_task_unit2 = (TextView) _$_findCachedViewById(C4188R.id.adver_continue_task_unit);
            Intrinsics.checkExpressionValueIsNotNull(adver_continue_task_unit2, "adver_continue_task_unit");
            ViewExtKt.isGoneView(adver_continue_task_unit2);
            TextView adver_cancel_task2 = (TextView) _$_findCachedViewById(C4188R.id.adver_cancel_task);
            Intrinsics.checkExpressionValueIsNotNull(adver_cancel_task2, "adver_cancel_task");
            adver_cancel_task2.setText(getString(C4188R.string.pdStr3_8));
            LinearLayout adver_right_root2 = (LinearLayout) _$_findCachedViewById(C4188R.id.adver_right_root);
            Intrinsics.checkExpressionValueIsNotNull(adver_right_root2, "adver_right_root");
            ViewExtKt.isGoneView(adver_right_root2);
        } else if (Intrinsics.areEqual(sceneConfig.getId(), AdSceneConfig.CRUISE_MODE_PAUSE.getId())) {
            TextView adver_continue_task3 = (TextView) _$_findCachedViewById(C4188R.id.adver_continue_task);
            Intrinsics.checkExpressionValueIsNotNull(adver_continue_task3, "adver_continue_task");
            adver_continue_task3.setText(getString(C4188R.string.pdStr2_13));
            TextView adver_cancel_task3 = (TextView) _$_findCachedViewById(C4188R.id.adver_cancel_task);
            Intrinsics.checkExpressionValueIsNotNull(adver_cancel_task3, "adver_cancel_task");
            adver_cancel_task3.setText(getString(C4188R.string.pdStr3_8));
            TextView adver_continue_task_time4 = (TextView) _$_findCachedViewById(C4188R.id.adver_continue_task_time);
            Intrinsics.checkExpressionValueIsNotNull(adver_continue_task_time4, "adver_continue_task_time");
            ViewExtKt.isGoneView(adver_continue_task_time4);
            TextView adver_continue_task_unit3 = (TextView) _$_findCachedViewById(C4188R.id.adver_continue_task_unit);
            Intrinsics.checkExpressionValueIsNotNull(adver_continue_task_unit3, "adver_continue_task_unit");
            ViewExtKt.isGoneView(adver_continue_task_unit3);
            LinearLayout adver_right_root3 = (LinearLayout) _$_findCachedViewById(C4188R.id.adver_right_root);
            Intrinsics.checkExpressionValueIsNotNull(adver_right_root3, "adver_right_root");
            ViewExtKt.isShowView(adver_right_root3);
            FrameLayout adver_cancel_task_ft = (FrameLayout) _$_findCachedViewById(C4188R.id.adver_cancel_task_ft);
            Intrinsics.checkExpressionValueIsNotNull(adver_cancel_task_ft, "adver_cancel_task_ft");
            ViewExtKt.isShowView(adver_cancel_task_ft);
        } else if (Intrinsics.areEqual(sceneConfig.getId(), AdSceneConfig.CRUISE_MODE_STOP.getId())) {
            LinearLayout adver_right_root4 = (LinearLayout) _$_findCachedViewById(C4188R.id.adver_right_root);
            Intrinsics.checkExpressionValueIsNotNull(adver_right_root4, "adver_right_root");
            ViewExtKt.isShowView(adver_right_root4);
            FrameLayout adver_cancel_task_ft2 = (FrameLayout) _$_findCachedViewById(C4188R.id.adver_cancel_task_ft);
            Intrinsics.checkExpressionValueIsNotNull(adver_cancel_task_ft2, "adver_cancel_task_ft");
            ViewExtKt.isGoneView(adver_cancel_task_ft2);
        }
        Pdlog.m3273d(getTAG(), "showOutsideAdScene()  AdSceneConfig =" + sceneConfig);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void initComListener() {
        ((FrameLayout) _$_findCachedViewById(C4188R.id.adver_continue_task_ft)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.advertise.AdverBaseFragment$initComListener$1
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
                Function1<AdverConfig.TaskType, Unit> onOutsideTask = AdverBaseFragment.this.getOnOutsideTask();
                if (onOutsideTask != null) {
                    onOutsideTask.invoke(AdverConfig.TaskType.TaskContinue.INSTANCE);
                }
                AdSceneConfig mSceneConfig = AdverBaseFragment.this.getMSceneConfig();
                if (!Intrinsics.areEqual(mSceneConfig != null ? mSceneConfig.getId() : null, AdSceneConfig.SOLICITING_PASSENGERS_MODE.getId())) {
                    LinearLayout adver_right_root = (LinearLayout) AdverBaseFragment.this._$_findCachedViewById(C4188R.id.adver_right_root);
                    Intrinsics.checkExpressionValueIsNotNull(adver_right_root, "adver_right_root");
                    ViewExtKt.isGoneView(adver_right_root);
                }
                Pdlog.m3273d(AdverBaseFragment.this.getTAG(), "setOnClickListener() TaskContinue");
            }
        }, 3, null));
        ((TextView) _$_findCachedViewById(C4188R.id.adver_cancel_task)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.advertise.AdverBaseFragment$initComListener$2
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
                AdSceneConfig mSceneConfig = AdverBaseFragment.this.getMSceneConfig();
                if (Intrinsics.areEqual(mSceneConfig != null ? mSceneConfig.getId() : null, AdSceneConfig.CRUISE_MODE_PAUSE.getId())) {
                    AdverBaseFragment.this.initAdDialog();
                } else {
                    Function1<AdverConfig.TaskType, Unit> onOutsideTask = AdverBaseFragment.this.getOnOutsideTask();
                    if (onOutsideTask != null) {
                        onOutsideTask.invoke(AdverConfig.TaskType.TaskFinish.INSTANCE);
                    }
                }
                Pdlog.m3273d(AdverBaseFragment.this.getTAG(), "setOnClickListener() TaskFinish");
            }
        }, 3, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initAdDialog() {
        AdverTaskDialog adverTaskDialog;
        if (getMActivity() != null && this.mAdverTaskDialog == null) {
            Activity mActivity = getMActivity();
            if (mActivity == null) {
                Intrinsics.throwNpe();
            }
            this.mAdverTaskDialog = new AdverTaskDialog(mActivity);
            AdverTaskDialog adverTaskDialog2 = this.mAdverTaskDialog;
            if (adverTaskDialog2 != null) {
                adverTaskDialog2.setOnYesBack(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.advertise.AdverBaseFragment$initAdDialog$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        Function1<AdverConfig.TaskType, Unit> onOutsideTask = AdverBaseFragment.this.getOnOutsideTask();
                        if (onOutsideTask != null) {
                            onOutsideTask.invoke(AdverConfig.TaskType.TaskFinish.INSTANCE);
                        }
                    }
                });
            }
        }
        AdverTaskDialog adverTaskDialog3 = this.mAdverTaskDialog;
        if (adverTaskDialog3 == null || adverTaskDialog3.isShowing() || (adverTaskDialog = this.mAdverTaskDialog) == null) {
            return;
        }
        adverTaskDialog.show();
    }
}
