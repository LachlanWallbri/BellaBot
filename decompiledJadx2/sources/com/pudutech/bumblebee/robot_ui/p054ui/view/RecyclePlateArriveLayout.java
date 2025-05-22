package com.pudutech.bumblebee.robot_ui.p054ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.recycle_task.RecycleContract;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.RecycleArriveTaskAdapter;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListenerKt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: RecyclePlateArriveLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ>\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\t2\u0006\u0010 \u001a\u00020!2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00150\u00102\u0010\b\u0002\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u001dH\u0002J\u0006\u0010$\u001a\u00020\u0015J\u0010\u0010%\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010&\u001a\u00020\u0011H\u0002J\u0006\u0010'\u001a\u00020\u0015J\u001e\u0010(\u001a\u00020\u00152\u0006\u0010)\u001a\u00020*2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00150\u001dH\u0002J)\u0010,\u001a\u00020\u00152!\u0010-\u001a\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\u0010J\u0014\u0010.\u001a\u00020\u00152\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00150\u001dJ\u0014\u0010/\u001a\u00020\u00152\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00150\u001dJ6\u00100\u001a\u00020\u00152\u0006\u00101\u001a\u00020\f2&\u00102\u001a\"\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u000204\u0018\u000103j\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u000204\u0018\u0001`5J\u0006\u00106\u001a\u00020\u0015J\u0010\u00107\u001a\u00020\u00152\b\b\u0002\u00108\u001a\u00020\tJ\b\u00109\u001a\u00020\u0015H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R+\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\f0\u001aj\b\u0012\u0004\u0012\u00020\f`\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006:"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/view/RecyclePlateArriveLayout;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "arriveTaskAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/RecycleArriveTaskAdapter;", "continueListener", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "isCountDownFinish", "", "countdownJob", "Lkotlinx/coroutines/Job;", "countdownMillis", "taskList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "timerFinishListener", "Lkotlin/Function0;", "countDownCoroutines", "total", "scope", "Lkotlinx/coroutines/CoroutineScope;", "onTick", "onFinish", "hideView", "initView", "isEndTask", "release", "setAutoFinish", "tvCountdown", "Landroid/widget/TextView;", "callback", "setContinueTaskClickListener", "listener", "setGoRecycleTableClickListener", "setLayoutOnclickListener", "setTask", "currentTaskName", "tasksMap", "Ljava/util/LinkedHashMap;", "Lcom/pudutech/bumblebee/presenter/recycle_task/RecycleContract$RecycleViewModel;", "Lkotlin/collections/LinkedHashMap;", "showArrivingView", "showView", "millisTime", "updateLayoutView", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RecyclePlateArriveLayout extends ConstraintLayout {
    private final String TAG;
    private HashMap _$_findViewCache;
    private RecycleArriveTaskAdapter arriveTaskAdapter;
    private Function1<? super Boolean, Unit> continueListener;
    private Job countdownJob;
    private int countdownMillis;
    private final ArrayList<String> taskList;
    private Function0<Unit> timerFinishListener;

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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecyclePlateArriveLayout(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "RecyclePlateArriveLayout";
        this.taskList = new ArrayList<>();
        this.countdownMillis = -1;
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        initView(context2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecyclePlateArriveLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "RecyclePlateArriveLayout";
        this.taskList = new ArrayList<>();
        this.countdownMillis = -1;
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        initView(context2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecyclePlateArriveLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "RecyclePlateArriveLayout";
        this.taskList = new ArrayList<>();
        this.countdownMillis = -1;
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        initView(context2);
    }

    public final void setTask(String currentTaskName, LinkedHashMap<String, RecycleContract.RecycleViewModel> tasksMap) {
        ArrayList arrayList;
        Intrinsics.checkParameterIsNotNull(currentTaskName, "currentTaskName");
        Pdlog.m3273d(this.TAG, "setTask " + currentTaskName + " , " + tasksMap);
        TextView tvArriveTaskName = (TextView) _$_findCachedViewById(C4188R.id.tvArriveTaskName);
        Intrinsics.checkExpressionValueIsNotNull(tvArriveTaskName, "tvArriveTaskName");
        String str = currentTaskName;
        tvArriveTaskName.setText(str);
        TextView tvArriveEndTaskName = (TextView) _$_findCachedViewById(C4188R.id.tvArriveEndTaskName);
        Intrinsics.checkExpressionValueIsNotNull(tvArriveEndTaskName, "tvArriveEndTaskName");
        tvArriveEndTaskName.setText(str);
        if (tasksMap != null) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry<String, RecycleContract.RecycleViewModel> entry : tasksMap.entrySet()) {
                if (!entry.getValue().getDone() && (Intrinsics.areEqual(entry.getValue().getName(), currentTaskName) ^ true)) {
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                }
            }
            ArrayList arrayList2 = new ArrayList(linkedHashMap.size());
            Iterator it = linkedHashMap.entrySet().iterator();
            while (it.hasNext()) {
                arrayList2.add(((RecycleContract.RecycleViewModel) ((Map.Entry) it.next()).getValue()).getName());
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        ArrayList<String> arrayList3 = this.taskList;
        arrayList3.clear();
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        arrayList3.addAll(arrayList);
        RecycleArriveTaskAdapter recycleArriveTaskAdapter = this.arriveTaskAdapter;
        if (recycleArriveTaskAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("arriveTaskAdapter");
        }
        recycleArriveTaskAdapter.notifyDataSetChanged();
        updateLayoutView();
    }

    public final void setContinueTaskClickListener(Function1<? super Boolean, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.continueListener = listener;
        LinearLayout viewArriveContinue = (LinearLayout) _$_findCachedViewById(C4188R.id.viewArriveContinue);
        Intrinsics.checkExpressionValueIsNotNull(viewArriveContinue, "viewArriveContinue");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(viewArriveContinue, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RecyclePlateArriveLayout$setContinueTaskClickListener$1
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
                Function1 function1;
                Intrinsics.checkParameterIsNotNull(it, "it");
                function1 = RecyclePlateArriveLayout.this.continueListener;
                if (function1 != null) {
                }
                RecyclePlateArriveLayout.this.release();
            }
        }, 3, null);
    }

    public final void setGoRecycleTableClickListener(final Function0<Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.timerFinishListener = listener;
        FrameLayout viewArriveToRecycle = (FrameLayout) _$_findCachedViewById(C4188R.id.viewArriveToRecycle);
        Intrinsics.checkExpressionValueIsNotNull(viewArriveToRecycle, "viewArriveToRecycle");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(viewArriveToRecycle, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RecyclePlateArriveLayout$setGoRecycleTableClickListener$1
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
                listener.invoke();
                RecyclePlateArriveLayout.this.release();
            }
        }, 3, null);
        LinearLayout viewArriveToRecycleTimer = (LinearLayout) _$_findCachedViewById(C4188R.id.viewArriveToRecycleTimer);
        Intrinsics.checkExpressionValueIsNotNull(viewArriveToRecycleTimer, "viewArriveToRecycleTimer");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(viewArriveToRecycleTimer, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RecyclePlateArriveLayout$setGoRecycleTableClickListener$2
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
                Function0 function0;
                Intrinsics.checkParameterIsNotNull(it, "it");
                function0 = RecyclePlateArriveLayout.this.timerFinishListener;
                if (function0 != null) {
                }
                RecyclePlateArriveLayout.this.release();
            }
        }, 3, null);
    }

    public final void setLayoutOnclickListener(final Function0<Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        FrameLayout layoutContainer = (FrameLayout) _$_findCachedViewById(C4188R.id.layoutContainer);
        Intrinsics.checkExpressionValueIsNotNull(layoutContainer, "layoutContainer");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(layoutContainer, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RecyclePlateArriveLayout$setLayoutOnclickListener$1
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
                Function0.this.invoke();
            }
        }, 3, null);
    }

    private final void updateLayoutView() {
        ConstraintLayout layoutArriveSingle = (ConstraintLayout) _$_findCachedViewById(C4188R.id.layoutArriveSingle);
        Intrinsics.checkExpressionValueIsNotNull(layoutArriveSingle, "layoutArriveSingle");
        layoutArriveSingle.setVisibility(isEndTask() ^ true ? 0 : 8);
        ConstraintLayout layoutArriveEnd = (ConstraintLayout) _$_findCachedViewById(C4188R.id.layoutArriveEnd);
        Intrinsics.checkExpressionValueIsNotNull(layoutArriveEnd, "layoutArriveEnd");
        layoutArriveEnd.setVisibility(isEndTask() ? 0 : 8);
        if (isEndTask()) {
            TextView tvArriveCountdown = (TextView) _$_findCachedViewById(C4188R.id.tvArriveCountdown);
            Intrinsics.checkExpressionValueIsNotNull(tvArriveCountdown, "tvArriveCountdown");
            setAutoFinish(tvArriveCountdown, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RecyclePlateArriveLayout$updateLayoutView$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Code restructure failed: missing block: B:5:0x000f, code lost:
                
                    r0 = r1.this$0.timerFinishListener;
                 */
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final void invoke2() {
                    Function0 function0;
                    if (!(RecyclePlateArriveLayout.this.getVisibility() == 0) || function0 == null) {
                        return;
                    }
                }
            });
        } else {
            TextView tvContinueCountdown = (TextView) _$_findCachedViewById(C4188R.id.tvContinueCountdown);
            Intrinsics.checkExpressionValueIsNotNull(tvContinueCountdown, "tvContinueCountdown");
            setAutoFinish(tvContinueCountdown, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.RecyclePlateArriveLayout$updateLayoutView$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Code restructure failed: missing block: B:5:0x0010, code lost:
                
                    r0 = r2.this$0.continueListener;
                 */
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final void invoke2() {
                    Function1 function1;
                    if (!(RecyclePlateArriveLayout.this.getVisibility() == 0) || function1 == null) {
                        return;
                    }
                }
            });
        }
    }

    private final void setAutoFinish(TextView tvCountdown, Function0<Unit> callback) {
        if (this.countdownMillis > 0) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new RecyclePlateArriveLayout$setAutoFinish$1(this, tvCountdown, callback, null), 2, null);
        } else {
            tvCountdown.setText("");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Job countDownCoroutines$default(RecyclePlateArriveLayout recyclePlateArriveLayout, int i, CoroutineScope coroutineScope, Function1 function1, Function0 function0, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            function0 = (Function0) null;
        }
        return recyclePlateArriveLayout.countDownCoroutines(i, coroutineScope, function1, function0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Job countDownCoroutines(int total, CoroutineScope scope, Function1<? super Integer, Unit> onTick, Function0<Unit> onFinish) {
        return FlowKt.launchIn(FlowKt.onCompletion(FlowKt.onEach(FlowKt.flowOn(FlowKt.flow(new RecyclePlateArriveLayout$countDownCoroutines$1(total, null)), Dispatchers.getMain()), new RecyclePlateArriveLayout$countDownCoroutines$2(onTick, null)), new RecyclePlateArriveLayout$countDownCoroutines$3(this, onFinish, null)), scope);
    }

    private final boolean isEndTask() {
        return this.taskList.size() <= 0;
    }

    public static /* synthetic */ void showView$default(RecyclePlateArriveLayout recyclePlateArriveLayout, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = -1;
        }
        recyclePlateArriveLayout.showView(i);
    }

    public final void showView(int millisTime) {
        if (1 <= millisTime && 999 >= millisTime) {
            millisTime = 1000;
        } else if (millisTime > 600000) {
            millisTime = 600000;
        }
        this.countdownMillis = millisTime;
        LinearLayout viewArriveContinue = (LinearLayout) _$_findCachedViewById(C4188R.id.viewArriveContinue);
        Intrinsics.checkExpressionValueIsNotNull(viewArriveContinue, "viewArriveContinue");
        viewArriveContinue.setVisibility(0);
        FrameLayout viewArriveToRecycle = (FrameLayout) _$_findCachedViewById(C4188R.id.viewArriveToRecycle);
        Intrinsics.checkExpressionValueIsNotNull(viewArriveToRecycle, "viewArriveToRecycle");
        viewArriveToRecycle.setVisibility(0);
        LinearLayout viewArriveToRecycleTimer = (LinearLayout) _$_findCachedViewById(C4188R.id.viewArriveToRecycleTimer);
        Intrinsics.checkExpressionValueIsNotNull(viewArriveToRecycleTimer, "viewArriveToRecycleTimer");
        viewArriveToRecycleTimer.setVisibility(0);
        setVisibility(0);
    }

    public final void showArrivingView() {
        LinearLayout viewArriveContinue = (LinearLayout) _$_findCachedViewById(C4188R.id.viewArriveContinue);
        Intrinsics.checkExpressionValueIsNotNull(viewArriveContinue, "viewArriveContinue");
        viewArriveContinue.setVisibility(8);
        FrameLayout viewArriveToRecycle = (FrameLayout) _$_findCachedViewById(C4188R.id.viewArriveToRecycle);
        Intrinsics.checkExpressionValueIsNotNull(viewArriveToRecycle, "viewArriveToRecycle");
        viewArriveToRecycle.setVisibility(8);
        LinearLayout viewArriveToRecycleTimer = (LinearLayout) _$_findCachedViewById(C4188R.id.viewArriveToRecycleTimer);
        Intrinsics.checkExpressionValueIsNotNull(viewArriveToRecycleTimer, "viewArriveToRecycleTimer");
        viewArriveToRecycleTimer.setVisibility(8);
        setVisibility(0);
    }

    public final void release() {
        Job job = this.countdownJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.countdownMillis = -1;
    }

    private final void initView(Context context) {
        Object systemService = context.getSystemService("layout_inflater");
        if (systemService != null) {
            ((LayoutInflater) systemService).inflate(C4188R.layout.layout_recycle_plate_arrive, this);
            this.arriveTaskAdapter = new RecycleArriveTaskAdapter(context);
            RecyclerView rvArriveTask = (RecyclerView) _$_findCachedViewById(C4188R.id.rvArriveTask);
            Intrinsics.checkExpressionValueIsNotNull(rvArriveTask, "rvArriveTask");
            RecycleArriveTaskAdapter recycleArriveTaskAdapter = this.arriveTaskAdapter;
            if (recycleArriveTaskAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("arriveTaskAdapter");
            }
            rvArriveTask.setAdapter(recycleArriveTaskAdapter);
            RecycleArriveTaskAdapter recycleArriveTaskAdapter2 = this.arriveTaskAdapter;
            if (recycleArriveTaskAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("arriveTaskAdapter");
            }
            recycleArriveTaskAdapter2.setNewData(this.taskList);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    public final void hideView() {
        setVisibility(8);
    }
}
