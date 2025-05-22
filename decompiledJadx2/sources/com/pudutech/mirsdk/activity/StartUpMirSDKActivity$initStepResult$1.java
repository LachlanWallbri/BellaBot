package com.pudutech.mirsdk.activity;

import android.widget.Button;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.mirsdk.activity.StartUpMirSDKActivity;
import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.mirsdk.aidl.serialize.StepState;
import com.pudutech.mirsdk.function.C4946R;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: StartUpMirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0002\b\b"}, m3961d2 = {"<anonymous>", "", "step", "Lcom/pudutech/mirsdk/aidl/serialize/InitStep;", "stepState", "Lcom/pudutech/mirsdk/aidl/serialize/StepState;", TmpConstant.SERVICE_DESC, "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class StartUpMirSDKActivity$initStepResult$1 extends Lambda implements Function3<InitStep, StepState, String, Unit> {
    final /* synthetic */ StartUpMirSDKActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    StartUpMirSDKActivity$initStepResult$1(StartUpMirSDKActivity startUpMirSDKActivity) {
        super(3);
        this.this$0 = startUpMirSDKActivity;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(InitStep initStep, StepState stepState, String str) {
        invoke2(initStep, stepState, str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(final InitStep step, final StepState stepState, final String desc) {
        Intrinsics.checkParameterIsNotNull(step, "step");
        Intrinsics.checkParameterIsNotNull(stepState, "stepState");
        Intrinsics.checkParameterIsNotNull(desc, "desc");
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.StartUpMirSDKActivity$initStepResult$1.1
            @Override // java.lang.Runnable
            public final void run() {
                Object obj;
                Iterator<T> it = StartUpMirSDKActivity$initStepResult$1.this.this$0.getStepStateList().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    } else {
                        obj = it.next();
                        if (Intrinsics.areEqual(((StartUpMirSDKActivity.InitState) obj).getStep(), step.toString())) {
                            break;
                        }
                    }
                }
                StartUpMirSDKActivity.InitState initState = (StartUpMirSDKActivity.InitState) obj;
                if (initState == null) {
                    StartUpMirSDKActivity$initStepResult$1.this.this$0.getStepStateList().add(0, new StartUpMirSDKActivity.InitState(step.toString(), stepState, desc));
                } else {
                    initState.setState(stepState);
                    initState.setDesc(desc);
                }
                StartUpMirSDKActivity.access$getListAdapter$p(StartUpMirSDKActivity$initStepResult$1.this.this$0).notifyDataSetChanged();
                if (step == InitStep.Finish) {
                    if (stepState == StepState.Success) {
                        StartUpMirSDKActivity$initStepResult$1.this.this$0.setCurrentState(StartUpMirSDKActivity.StartUpState.Finish);
                        Button buttonAction = (Button) StartUpMirSDKActivity$initStepResult$1.this.this$0._$_findCachedViewById(C4946R.id.buttonAction);
                        Intrinsics.checkExpressionValueIsNotNull(buttonAction, "buttonAction");
                        buttonAction.setText("Finish");
                    } else {
                        StartUpMirSDKActivity$initStepResult$1.this.this$0.setCurrentState(StartUpMirSDKActivity.StartUpState.NeedStartUp);
                        Button buttonAction2 = (Button) StartUpMirSDKActivity$initStepResult$1.this.this$0._$_findCachedViewById(C4946R.id.buttonAction);
                        Intrinsics.checkExpressionValueIsNotNull(buttonAction2, "buttonAction");
                        buttonAction2.setText("StartUp");
                    }
                    Button buttonAction3 = (Button) StartUpMirSDKActivity$initStepResult$1.this.this$0._$_findCachedViewById(C4946R.id.buttonAction);
                    Intrinsics.checkExpressionValueIsNotNull(buttonAction3, "buttonAction");
                    buttonAction3.setEnabled(true);
                }
            }
        });
    }
}
