package com.pudutech.bumblebee.robot_ui.ui_helpers;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.setting.FunctionSettingContract;
import com.pudutech.bumblebee.presenter.setting.FunctionSettingPresenter;
import com.pudutech.bumblebee.robot_ui.p054ui.DeliverTaskEditActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.greeter.GreeterFaceActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.greeter.GreeterMenuActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.recycle.RecycleTaskActivity;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.anko.internals.AnkoInternals;

/* compiled from: SceneRecord.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0013B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui_helpers/SceneRecord;", "", "()V", "KEY", "", "TAG", "functionSettingPresenter", "Lcom/pudutech/bumblebee/presenter/setting/FunctionSettingPresenter;", "getFunctionSettingPresenter", "()Lcom/pudutech/bumblebee/presenter/setting/FunctionSettingPresenter;", "functionSettingPresenter$delegate", "Lkotlin/Lazy;", "saveScene", "", "context", "Landroid/content/Context;", "scene", "Lcom/pudutech/bumblebee/robot_ui/ui_helpers/SceneRecord$Scene;", "startLastSceneActivity", SceneRecord.KEY, "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SceneRecord {
    public static final SceneRecord INSTANCE = new SceneRecord();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    /* renamed from: functionSettingPresenter$delegate, reason: from kotlin metadata */
    private static final Lazy functionSettingPresenter = LazyKt.lazy(new Function0<FunctionSettingPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui_helpers.SceneRecord$functionSettingPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final FunctionSettingPresenter invoke() {
            FunctionSettingPresenter functionSettingPresenter2;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(FunctionSettingPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(FunctionSettingPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                functionSettingPresenter2 = new FunctionSettingPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(FunctionSettingPresenter.class).toString(), functionSettingPresenter2);
            } else {
                if (!(basePresenterInterface instanceof FunctionSettingPresenter)) {
                    basePresenterInterface = null;
                }
                functionSettingPresenter2 = (FunctionSettingPresenter) basePresenterInterface;
            }
            if (functionSettingPresenter2 != null) {
                return functionSettingPresenter2;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.setting.FunctionSettingPresenter");
        }
    });
    private static final String KEY = KEY;
    private static final String KEY = KEY;

    /* compiled from: SceneRecord.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui_helpers/SceneRecord$Scene;", "", "(Ljava/lang/String;I)V", "DELIVERY_SCENE", "GREETER_SCENE", "GREETER_FACE", "RECYCLE_SCENE", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum Scene {
        DELIVERY_SCENE,
        GREETER_SCENE,
        GREETER_FACE,
        RECYCLE_SCENE
    }

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Scene.values().length];

        static {
            $EnumSwitchMapping$0[Scene.DELIVERY_SCENE.ordinal()] = 1;
            $EnumSwitchMapping$0[Scene.GREETER_SCENE.ordinal()] = 2;
            $EnumSwitchMapping$0[Scene.GREETER_FACE.ordinal()] = 3;
            $EnumSwitchMapping$0[Scene.RECYCLE_SCENE.ordinal()] = 4;
        }
    }

    private final FunctionSettingPresenter getFunctionSettingPresenter() {
        return (FunctionSettingPresenter) functionSettingPresenter.getValue();
    }

    private SceneRecord() {
    }

    public final void startLastSceneActivity(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        String str = SpUtils.get(context, KEY, Scene.DELIVERY_SCENE.name());
        Scene valueOf = Scene.valueOf(str);
        Pdlog.m3273d(TAG, "startLastSceneActivity context=" + context + " load name=" + str + " scene=" + valueOf);
        int i = WhenMappings.$EnumSwitchMapping$0[valueOf.ordinal()];
        if (i == 1) {
            AnkoInternals.internalStartActivity(context, DeliverTaskEditActivity.class, new Pair[0]);
            return;
        }
        if (i == 2) {
            if (getFunctionSettingPresenter().checkFunctionState(FunctionSettingContract.FunctionType.GREETER_FUNCTION)) {
                AnkoInternals.internalStartActivity(context, GreeterMenuActivity.class, new Pair[0]);
                return;
            } else {
                AnkoInternals.internalStartActivity(context, DeliverTaskEditActivity.class, new Pair[0]);
                return;
            }
        }
        if (i == 3) {
            if (getFunctionSettingPresenter().checkFunctionState(FunctionSettingContract.FunctionType.GREETER_FUNCTION)) {
                AnkoInternals.internalStartActivity(context, GreeterFaceActivity.class, new Pair[0]);
                return;
            } else {
                AnkoInternals.internalStartActivity(context, DeliverTaskEditActivity.class, new Pair[0]);
                return;
            }
        }
        if (i != 4) {
            return;
        }
        if (getFunctionSettingPresenter().checkFunctionState(FunctionSettingContract.FunctionType.RECYCLE_FUNCTION)) {
            AnkoInternals.internalStartActivity(context, RecycleTaskActivity.class, new Pair[0]);
        } else {
            AnkoInternals.internalStartActivity(context, DeliverTaskEditActivity.class, new Pair[0]);
        }
    }

    public final void saveScene(Context context, Scene scene) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(scene, "scene");
        Pdlog.m3273d(TAG, "saveScene context=" + context + "  scene=" + scene);
        SpUtils.set(context, KEY, scene.name());
    }
}
