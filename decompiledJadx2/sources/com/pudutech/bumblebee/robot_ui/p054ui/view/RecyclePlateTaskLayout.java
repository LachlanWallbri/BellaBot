package com.pudutech.bumblebee.robot_ui.p054ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryModel;
import com.pudutech.bumblebee.presenter.delivery_task.TrayModel;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.SelectRecycleTaskAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecyclePlateTaskLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\fJ\u0016\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0012j\b\u0012\u0004\u0012\u00020\u0013`\u0014J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0012\u0010\u001b\u001a\u00020\u00162\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0002J\u000e\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001eJ\u001e\u0010 \u001a\u00020\u00162\u0016\u0010!\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0012j\b\u0012\u0004\u0012\u00020\u0013`\u0014J\u000e\u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020\u001aR\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0012j\b\u0012\u0004\u0012\u00020\u0013`\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/view/RecyclePlateTaskLayout;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attributeSet", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "TAG", "", "kotlin.jvm.PlatformType", "selectRecycleTaskAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SelectRecycleTaskAdapter;", "taskLimit", "taskList", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/presenter/delivery_task/TrayModel;", "Lkotlin/collections/ArrayList;", "addTask", "", "task", "getAllTask", "hasTask", "", "initView", "setGoRecycleClickListener", "listener", "Landroid/view/View$OnClickListener;", "setGoWashRoomClickListener", "setTask", "list", "showBottomBtn", "b", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RecyclePlateTaskLayout extends RelativeLayout {
    private final String TAG;
    private HashMap _$_findViewCache;
    private SelectRecycleTaskAdapter selectRecycleTaskAdapter;
    private final int taskLimit;
    private final ArrayList<TrayModel> taskList;

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

    public RecyclePlateTaskLayout(Context context) {
        super(context);
        this.TAG = getClass().getSimpleName();
        this.taskLimit = 5;
        this.taskList = new ArrayList<>();
        initView(getContext());
    }

    public RecyclePlateTaskLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = getClass().getSimpleName();
        this.taskLimit = 5;
        this.taskList = new ArrayList<>();
        initView(getContext());
    }

    public RecyclePlateTaskLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = getClass().getSimpleName();
        this.taskLimit = 5;
        this.taskList = new ArrayList<>();
        initView(getContext());
    }

    public final void setTask(ArrayList<TrayModel> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        Pdlog.m3273d(this.TAG, "setTask " + list);
        this.taskList.clear();
        this.taskList.addAll(list);
        SelectRecycleTaskAdapter selectRecycleTaskAdapter = this.selectRecycleTaskAdapter;
        if (selectRecycleTaskAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectRecycleTaskAdapter");
        }
        selectRecycleTaskAdapter.notifyDataSetChanged();
    }

    public final void addTask(String task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        if (this.taskList.size() >= this.taskLimit) {
            Pdlog.m3273d(this.TAG, "addTask more " + this.taskLimit);
            return;
        }
        Iterator<TrayModel> it = this.taskList.iterator();
        while (it.hasNext()) {
            if (it.next().getDeliveryModel(task) != null) {
                return;
            }
        }
        TrayModel trayModel = new TrayModel();
        trayModel.getAllDestinations().add(new DeliveryModel(task, null, null, null, 12, null));
        this.taskList.add(trayModel);
        SelectRecycleTaskAdapter selectRecycleTaskAdapter = this.selectRecycleTaskAdapter;
        if (selectRecycleTaskAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectRecycleTaskAdapter");
        }
        selectRecycleTaskAdapter.notifyDataSetChanged();
    }

    public final ArrayList<TrayModel> getAllTask() {
        return this.taskList;
    }

    public final boolean hasTask() {
        return this.taskList.size() > 0;
    }

    public final void showBottomBtn(boolean b) {
        LinearLayout bottom_btn_layout = (LinearLayout) _$_findCachedViewById(C4188R.id.bottom_btn_layout);
        Intrinsics.checkExpressionValueIsNotNull(bottom_btn_layout, "bottom_btn_layout");
        bottom_btn_layout.setVisibility(b ? 0 : 8);
    }

    public final void setGoWashRoomClickListener(View.OnClickListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        ((TextView) _$_findCachedViewById(C4188R.id.go_to_washroom_tv)).setOnClickListener(listener);
    }

    public final void setGoRecycleClickListener(View.OnClickListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        ((TextView) _$_findCachedViewById(C4188R.id.go_to_recycle_tv)).setOnClickListener(listener);
    }

    private final void initView(Context context) {
        if (context != null) {
            Object systemService = context.getSystemService("layout_inflater");
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
            }
            LayoutInflater layoutInflater = (LayoutInflater) systemService;
            if (layoutInflater != null) {
                layoutInflater.inflate(C4188R.layout.layout_recycle_plate_task, this);
            }
        }
        if (context == null) {
            Intrinsics.throwNpe();
        }
        this.selectRecycleTaskAdapter = new SelectRecycleTaskAdapter(context);
        RecyclerView task_recycler_view = (RecyclerView) _$_findCachedViewById(C4188R.id.task_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(task_recycler_view, "task_recycler_view");
        task_recycler_view.setLayoutManager(new LinearLayoutManager(context));
        RecyclerView task_recycler_view2 = (RecyclerView) _$_findCachedViewById(C4188R.id.task_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(task_recycler_view2, "task_recycler_view");
        SelectRecycleTaskAdapter selectRecycleTaskAdapter = this.selectRecycleTaskAdapter;
        if (selectRecycleTaskAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectRecycleTaskAdapter");
        }
        task_recycler_view2.setAdapter(selectRecycleTaskAdapter);
        SelectRecycleTaskAdapter selectRecycleTaskAdapter2 = this.selectRecycleTaskAdapter;
        if (selectRecycleTaskAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectRecycleTaskAdapter");
        }
        selectRecycleTaskAdapter2.setNewData(this.taskList);
    }
}
