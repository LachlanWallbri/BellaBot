package com.pudutech.mirsdk.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.mirsdk.activity.StartUpMirSDKActivity;
import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.mirsdk.aidl.serialize.StepState;
import com.pudutech.mirsdk.function.C4946R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: StartUpMirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0004\u001b\u001c\u001d\u001eB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\b\u0010\u001a\u001a\u00020\u0017H\u0014R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/mirsdk/activity/StartUpMirSDKActivity;", "Landroid/app/Activity;", "()V", "currentState", "Lcom/pudutech/mirsdk/activity/StartUpMirSDKActivity$StartUpState;", "getCurrentState", "()Lcom/pudutech/mirsdk/activity/StartUpMirSDKActivity$StartUpState;", "setCurrentState", "(Lcom/pudutech/mirsdk/activity/StartUpMirSDKActivity$StartUpState;)V", "listAdapter", "Lcom/pudutech/mirsdk/activity/StartUpMirSDKActivity$InitStateAdapter;", "getListAdapter", "()Lcom/pudutech/mirsdk/activity/StartUpMirSDKActivity$InitStateAdapter;", "setListAdapter", "(Lcom/pudutech/mirsdk/activity/StartUpMirSDKActivity$InitStateAdapter;)V", "stepStateList", "", "Lcom/pudutech/mirsdk/activity/StartUpMirSDKActivity$InitState;", "getStepStateList", "()Ljava/util/List;", "setStepStateList", "(Ljava/util/List;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "InitState", "InitStateAdapter", "StartUpState", "StepViewHolder", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class StartUpMirSDKActivity extends Activity {
    private HashMap _$_findViewCache;
    public InitStateAdapter listAdapter;
    private List<InitState> stepStateList = new ArrayList();
    private StartUpState currentState = StartUpState.NeedStartUp;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: StartUpMirSDKActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/mirsdk/activity/StartUpMirSDKActivity$StartUpState;", "", "(Ljava/lang/String;I)V", "NeedStartUp", "StartingUp", "Finish", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum StartUpState {
        NeedStartUp,
        StartingUp,
        Finish
    }

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
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: StartUpMirSDKActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/mirsdk/activity/StartUpMirSDKActivity$InitState;", "", "step", "", "state", "Lcom/pudutech/mirsdk/aidl/serialize/StepState;", TmpConstant.SERVICE_DESC, "(Ljava/lang/String;Lcom/pudutech/mirsdk/aidl/serialize/StepState;Ljava/lang/String;)V", "getDesc", "()Ljava/lang/String;", "setDesc", "(Ljava/lang/String;)V", "getState", "()Lcom/pudutech/mirsdk/aidl/serialize/StepState;", "setState", "(Lcom/pudutech/mirsdk/aidl/serialize/StepState;)V", "getStep", "setStep", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final /* data */ class InitState {
        private String desc;
        private StepState state;
        private String step;

        public static /* synthetic */ InitState copy$default(InitState initState, String str, StepState stepState, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = initState.step;
            }
            if ((i & 2) != 0) {
                stepState = initState.state;
            }
            if ((i & 4) != 0) {
                str2 = initState.desc;
            }
            return initState.copy(str, stepState, str2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getStep() {
            return this.step;
        }

        /* renamed from: component2, reason: from getter */
        public final StepState getState() {
            return this.state;
        }

        /* renamed from: component3, reason: from getter */
        public final String getDesc() {
            return this.desc;
        }

        public final InitState copy(String step, StepState state, String desc) {
            Intrinsics.checkParameterIsNotNull(step, "step");
            Intrinsics.checkParameterIsNotNull(state, "state");
            Intrinsics.checkParameterIsNotNull(desc, "desc");
            return new InitState(step, state, desc);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof InitState)) {
                return false;
            }
            InitState initState = (InitState) other;
            return Intrinsics.areEqual(this.step, initState.step) && Intrinsics.areEqual(this.state, initState.state) && Intrinsics.areEqual(this.desc, initState.desc);
        }

        public int hashCode() {
            String str = this.step;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            StepState stepState = this.state;
            int hashCode2 = (hashCode + (stepState != null ? stepState.hashCode() : 0)) * 31;
            String str2 = this.desc;
            return hashCode2 + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "InitState(step=" + this.step + ", state=" + this.state + ", desc=" + this.desc + ")";
        }

        public InitState(String step, StepState state, String desc) {
            Intrinsics.checkParameterIsNotNull(step, "step");
            Intrinsics.checkParameterIsNotNull(state, "state");
            Intrinsics.checkParameterIsNotNull(desc, "desc");
            this.step = step;
            this.state = state;
            this.desc = desc;
        }

        public final String getDesc() {
            return this.desc;
        }

        public final StepState getState() {
            return this.state;
        }

        public final String getStep() {
            return this.step;
        }

        public final void setDesc(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.desc = str;
        }

        public final void setState(StepState stepState) {
            Intrinsics.checkParameterIsNotNull(stepState, "<set-?>");
            this.state = stepState;
        }

        public final void setStep(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.step = str;
        }
    }

    public final InitStateAdapter getListAdapter() {
        InitStateAdapter initStateAdapter = this.listAdapter;
        if (initStateAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("listAdapter");
        }
        return initStateAdapter;
    }

    public final void setListAdapter(InitStateAdapter initStateAdapter) {
        Intrinsics.checkParameterIsNotNull(initStateAdapter, "<set-?>");
        this.listAdapter = initStateAdapter;
    }

    public final List<InitState> getStepStateList() {
        return this.stepStateList;
    }

    public final void setStepStateList(List<InitState> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.stepStateList = list;
    }

    public final StartUpState getCurrentState() {
        return this.currentState;
    }

    public final void setCurrentState(StartUpState startUpState) {
        Intrinsics.checkParameterIsNotNull(startUpState, "<set-?>");
        this.currentState = startUpState;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C4946R.layout.mirsdk_activity_init);
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 0);
            }
            if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 0);
            }
        }
        ((Button) _$_findCachedViewById(C4946R.id.buttonAction)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.activity.StartUpMirSDKActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (StartUpMirSDKActivity.this.getCurrentState() == StartUpMirSDKActivity.StartUpState.NeedStartUp) {
                    StartUpMirSDKActivity.this.getStepStateList().clear();
                    SDKServiceConnection.INSTANCE.init();
                    StartUpMirSDKActivity.this.setCurrentState(StartUpMirSDKActivity.StartUpState.StartingUp);
                    Button buttonAction = (Button) StartUpMirSDKActivity.this._$_findCachedViewById(C4946R.id.buttonAction);
                    Intrinsics.checkExpressionValueIsNotNull(buttonAction, "buttonAction");
                    buttonAction.setEnabled(false);
                    return;
                }
                if (StartUpMirSDKActivity.this.getCurrentState() == StartUpMirSDKActivity.StartUpState.Finish) {
                    StartUpMirSDKActivity.this.finish();
                }
            }
        });
        StartUpMirSDKActivity startUpMirSDKActivity = this;
        RecyclerView stepList = (RecyclerView) _$_findCachedViewById(C4946R.id.stepList);
        Intrinsics.checkExpressionValueIsNotNull(stepList, "stepList");
        this.listAdapter = new InitStateAdapter(startUpMirSDKActivity, stepList);
        RecyclerView stepList2 = (RecyclerView) _$_findCachedViewById(C4946R.id.stepList);
        Intrinsics.checkExpressionValueIsNotNull(stepList2, "stepList");
        stepList2.setLayoutManager(new LinearLayoutManager(startUpMirSDKActivity));
        RecyclerView stepList3 = (RecyclerView) _$_findCachedViewById(C4946R.id.stepList);
        Intrinsics.checkExpressionValueIsNotNull(stepList3, "stepList");
        InitStateAdapter initStateAdapter = this.listAdapter;
        if (initStateAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("listAdapter");
        }
        stepList3.setAdapter(initStateAdapter);
        InitStateAdapter initStateAdapter2 = this.listAdapter;
        if (initStateAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("listAdapter");
        }
        initStateAdapter2.setData(this.stepStateList);
        SDKServiceConnection.INSTANCE.setOnInitStepResult(new Function3<InitStep, StepState, String, Unit>() { // from class: com.pudutech.mirsdk.activity.StartUpMirSDKActivity$onCreate$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(3);
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
                StartUpMirSDKActivity.this.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.StartUpMirSDKActivity$onCreate$2.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Object obj;
                        Iterator<T> it = StartUpMirSDKActivity.this.getStepStateList().iterator();
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
                            StartUpMirSDKActivity.this.getStepStateList().add(0, new StartUpMirSDKActivity.InitState(step.toString(), stepState, desc));
                        } else {
                            initState.setState(stepState);
                            initState.setDesc(desc);
                        }
                        StartUpMirSDKActivity.this.getListAdapter().setData(StartUpMirSDKActivity.this.getStepStateList());
                        StartUpMirSDKActivity.this.getListAdapter().notifyDataSetChanged();
                        if (step == InitStep.Finish) {
                            if (stepState == StepState.Success) {
                                StartUpMirSDKActivity.this.setCurrentState(StartUpMirSDKActivity.StartUpState.Finish);
                                Button buttonAction = (Button) StartUpMirSDKActivity.this._$_findCachedViewById(C4946R.id.buttonAction);
                                Intrinsics.checkExpressionValueIsNotNull(buttonAction, "buttonAction");
                                buttonAction.setText("Finish");
                            } else {
                                StartUpMirSDKActivity.this.setCurrentState(StartUpMirSDKActivity.StartUpState.NeedStartUp);
                                Button buttonAction2 = (Button) StartUpMirSDKActivity.this._$_findCachedViewById(C4946R.id.buttonAction);
                                Intrinsics.checkExpressionValueIsNotNull(buttonAction2, "buttonAction");
                                buttonAction2.setText("StartUp");
                            }
                            Button buttonAction3 = (Button) StartUpMirSDKActivity.this._$_findCachedViewById(C4946R.id.buttonAction);
                            Intrinsics.checkExpressionValueIsNotNull(buttonAction3, "buttonAction");
                            buttonAction3.setEnabled(true);
                        }
                    }
                });
            }
        });
        SDKServiceConnection.INSTANCE.init();
        this.currentState = StartUpState.StartingUp;
        Button buttonAction = (Button) _$_findCachedViewById(C4946R.id.buttonAction);
        Intrinsics.checkExpressionValueIsNotNull(buttonAction, "buttonAction");
        buttonAction.setEnabled(false);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        SDKServiceConnection.INSTANCE.setOnInitStepResult((Function3) null);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: StartUpMirSDKActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0019\u001a\u00020\u0014H\u0016J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u0014H\u0016J\u0018\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0014H\u0016J\u0014\u0010\"\u001a\u00020\u001b2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u000e0\rR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006$"}, m3961d2 = {"Lcom/pudutech/mirsdk/activity/StartUpMirSDKActivity$InitStateAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/pudutech/mirsdk/activity/StartUpMirSDKActivity$StepViewHolder;", "context", "Landroid/content/Context;", "destination_list", "Landroidx/recyclerview/widget/RecyclerView;", "(Landroid/content/Context;Landroidx/recyclerview/widget/RecyclerView;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "data", "", "Lcom/pudutech/mirsdk/activity/StartUpMirSDKActivity$InitState;", "getDestination_list", "()Landroidx/recyclerview/widget/RecyclerView;", "setDestination_list", "(Landroidx/recyclerview/widget/RecyclerView;)V", "selectedItem", "", "getSelectedItem", "()I", "setSelectedItem", "(I)V", "getItemCount", "onBindViewHolder", "", "holder", RequestParameters.POSITION, "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setData", "_data", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class InitStateAdapter extends RecyclerView.Adapter<StepViewHolder> {
        private Context context;
        private List<InitState> data;
        private RecyclerView destination_list;
        private int selectedItem;

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[StepState.values().length];

            static {
                $EnumSwitchMapping$0[StepState.Success.ordinal()] = 1;
                $EnumSwitchMapping$0[StepState.Fail.ordinal()] = 2;
                $EnumSwitchMapping$0[StepState.Running.ordinal()] = 3;
            }
        }

        public InitStateAdapter(Context context, RecyclerView destination_list) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(destination_list, "destination_list");
            this.context = context;
            this.destination_list = destination_list;
        }

        public final Context getContext() {
            return this.context;
        }

        public final RecyclerView getDestination_list() {
            return this.destination_list;
        }

        public final void setContext(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "<set-?>");
            this.context = context;
        }

        public final void setDestination_list(RecyclerView recyclerView) {
            Intrinsics.checkParameterIsNotNull(recyclerView, "<set-?>");
            this.destination_list = recyclerView;
        }

        public final int getSelectedItem() {
            return this.selectedItem;
        }

        public final void setSelectedItem(int i) {
            this.selectedItem = i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Intrinsics.checkParameterIsNotNull(parent, "parent");
            View view = LayoutInflater.from(this.context).inflate(C4946R.layout.mirsdk_init_step_item, parent, false);
            Intrinsics.checkExpressionValueIsNotNull(view, "view");
            return new StepViewHolder(view);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(StepViewHolder holder, int position) {
            Intrinsics.checkParameterIsNotNull(holder, "holder");
            List<InitState> list = this.data;
            if (list == null) {
                Intrinsics.throwNpe();
            }
            String step = list.get(position).getStep();
            List<InitState> list2 = this.data;
            if (list2 == null) {
                Intrinsics.throwNpe();
            }
            String desc = list2.get(position).getDesc();
            if (desc.length() > 0) {
                step = step + '(' + desc + ')';
            }
            holder.getStep().setText(step);
            List<InitState> list3 = this.data;
            if (list3 == null) {
                Intrinsics.throwNpe();
            }
            int i = WhenMappings.$EnumSwitchMapping$0[list3.get(position).getState().ordinal()];
            if (i == 1) {
                holder.getState().setText("pass");
                holder.getState().setTextColor(Color.parseColor("#33cc33"));
            } else if (i == 2) {
                holder.getState().setText("fail");
                holder.getState().setTextColor(Color.parseColor("#cc3333"));
            } else {
                if (i != 3) {
                    return;
                }
                holder.getState().setText("running");
                holder.getState().setTextColor(Color.parseColor("#999933"));
            }
        }

        /* compiled from: StartUpMirSDKActivity.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0080\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\n¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/mirsdk/activity/StartUpMirSDKActivity$InitStateAdapter$StepViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/pudutech/mirsdk/activity/StartUpMirSDKActivity$InitStateAdapter;Landroid/view/View;)V", "state", "Landroid/widget/TextView;", "getState", "()Landroid/widget/TextView;", "setState", "(Landroid/widget/TextView;)V", "step", "getStep", "setStep", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        public final class StepViewHolder extends RecyclerView.ViewHolder {
            private TextView state;
            private TextView step;
            final /* synthetic */ InitStateAdapter this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public StepViewHolder(InitStateAdapter initStateAdapter, View view) {
                super(view);
                Intrinsics.checkParameterIsNotNull(view, "view");
                this.this$0 = initStateAdapter;
                View findViewById = view.findViewById(C4946R.id.textViewStep);
                Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.textViewStep)");
                this.step = (TextView) findViewById;
                View findViewById2 = view.findViewById(C4946R.id.textViewState);
                Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById(R.id.textViewState)");
                this.state = (TextView) findViewById2;
            }

            public final TextView getStep() {
                return this.step;
            }

            public final void setStep(TextView textView) {
                Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
                this.step = textView;
            }

            public final TextView getState() {
                return this.state;
            }

            public final void setState(TextView textView) {
                Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
                this.state = textView;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            List<InitState> list = this.data;
            if (list == null) {
                return 0;
            }
            if (list == null) {
                Intrinsics.throwNpe();
            }
            return list.size();
        }

        public final void setData(List<InitState> _data) {
            Intrinsics.checkParameterIsNotNull(_data, "_data");
            this.data = _data;
        }
    }

    /* compiled from: StartUpMirSDKActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\n¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/mirsdk/activity/StartUpMirSDKActivity$StepViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "state", "Landroid/widget/TextView;", "getState$MirFunction_packRelease", "()Landroid/widget/TextView;", "setState$MirFunction_packRelease", "(Landroid/widget/TextView;)V", "step", "getStep$MirFunction_packRelease", "setStep$MirFunction_packRelease", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class StepViewHolder extends RecyclerView.ViewHolder {
        private TextView state;
        private TextView step;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public StepViewHolder(View view) {
            super(view);
            Intrinsics.checkParameterIsNotNull(view, "view");
            View findViewById = view.findViewById(C4946R.id.textViewStep);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.textViewStep)");
            this.step = (TextView) findViewById;
            View findViewById2 = view.findViewById(C4946R.id.textViewState);
            Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById(R.id.textViewState)");
            this.state = (TextView) findViewById2;
        }

        /* renamed from: getStep$MirFunction_packRelease, reason: from getter */
        public final TextView getStep() {
            return this.step;
        }

        public final void setStep$MirFunction_packRelease(TextView textView) {
            Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
            this.step = textView;
        }

        /* renamed from: getState$MirFunction_packRelease, reason: from getter */
        public final TextView getState() {
            return this.state;
        }

        public final void setState$MirFunction_packRelease(TextView textView) {
            Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
            this.state = textView;
        }
    }
}
