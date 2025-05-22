package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.bean.CallHistoryData;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.CallHistoryAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CallHistoryManager;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.PalletCountHelper;
import com.pudutech.bumblebee.robot_ui.ui_utils.LinearSpacesItemDecoration;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.util.NavigationBarUtil;
import com.pudutech.bumblebee.robot_ui.util.UiUtils;
import com.warkiz.widget.SizeUtils;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeliveryHistoryTaskDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\u0018\u0000 /2\u00020\u0001:\u0001/B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u001eH\u0002J\b\u0010 \u001a\u00020\u001eH\u0002J\u0016\u0010!\u001a\u00020\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0\u0017H\u0002J\b\u0010$\u001a\u00020\u001eH\u0002J\b\u0010%\u001a\u00020\u001eH\u0002J\b\u0010&\u001a\u00020\u001eH\u0002J\u0010\u0010'\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020\u000bH\u0003J\u0010\u0010)\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020\u000bH\u0002J\u0010\u0010*\u001a\u00020\u001e2\u0006\u0010+\u001a\u00020\u000bH\u0002J\b\u0010,\u001a\u00020\u001eH\u0016J\b\u0010-\u001a\u00020\u001eH\u0016J\b\u0010.\u001a\u00020\u001eH\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000¨\u00060"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/DeliveryHistoryTaskDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "adapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/CallHistoryAdapter;", "callIndicator", "Landroid/view/View;", "clDelivery", "Landroidx/constraintlayout/widget/ConstraintLayout;", "deliveryIndicator", "initCallData", "", "initDeliveryData", "mainHandle", "Landroid/os/Handler;", "rvCallHistory", "Landroidx/recyclerview/widget/RecyclerView;", "tablesList", "Ljava/util/ArrayList;", "Landroid/widget/ScrollView;", "traysList", "Landroid/widget/TextView;", "tvCallTab", "tvDeliverTab", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "", "callSelected", "deliverySelected", "getTables", "", "tables", "init", "initCallHistoryData", "initData", "initRv", "view", "initTabView", "initView", "root", "onAttachedToWindow", "onDetachedFromWindow", "show", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class DeliveryHistoryTaskDialog extends Dialog {
    private static final int AUTO_CLOSE = 1001;
    private static final String LAST_OP_KEY = "history_last_operation";
    private static final String OP_CALL = "op_call";
    private static final String OP_DELIVERY = "op_delivery";
    private static final String TAG = "DeliveryHistoryTaskDialog";
    private CallHistoryAdapter adapter;
    private View callIndicator;
    private ConstraintLayout clDelivery;
    private View deliveryIndicator;
    private boolean initCallData;
    private boolean initDeliveryData;
    private final Handler mainHandle;
    private RecyclerView rvCallHistory;
    private final ArrayList<ScrollView> tablesList;
    private final ArrayList<TextView> traysList;
    private TextView tvCallTab;
    private TextView tvDeliverTab;

    public static final /* synthetic */ CallHistoryAdapter access$getAdapter$p(DeliveryHistoryTaskDialog deliveryHistoryTaskDialog) {
        CallHistoryAdapter callHistoryAdapter = deliveryHistoryTaskDialog.adapter;
        if (callHistoryAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        }
        return callHistoryAdapter;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeliveryHistoryTaskDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.traysList = new ArrayList<>();
        this.tablesList = new ArrayList<>();
        this.mainHandle = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.DeliveryHistoryTaskDialog$mainHandle$1
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message msg) {
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                if (msg.what == 1001) {
                    Pdlog.m3273d("DeliveryHistoryTaskDialog", "AUTO_CLOSE");
                    DeliveryHistoryTaskDialog.this.dismiss();
                }
                return true;
            }
        });
        init();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeliveryHistoryTaskDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.traysList = new ArrayList<>();
        this.tablesList = new ArrayList<>();
        this.mainHandle = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.DeliveryHistoryTaskDialog$mainHandle$1
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message msg) {
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                if (msg.what == 1001) {
                    Pdlog.m3273d("DeliveryHistoryTaskDialog", "AUTO_CLOSE");
                    DeliveryHistoryTaskDialog.this.dismiss();
                }
                return true;
            }
        });
        init();
    }

    private final void init() {
        build();
    }

    private final void build() {
        View view = getLayoutInflater().inflate(C4188R.layout.dialog_delivery_history_task, (ViewGroup) null);
        requestWindowFeature(1);
        Window window = getWindow();
        if (window != null) {
            View decorView = window.getDecorView();
            Intrinsics.checkExpressionValueIsNotNull(decorView, "decorView");
            decorView.setSystemUiVisibility(3846);
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(17);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = -1;
            attributes.height = -1;
            window.setAttributes(attributes);
        } else {
            window = null;
        }
        View findViewById = view.findViewById(C4188R.id.cl_delivery);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.cl_delivery)");
        this.clDelivery = (ConstraintLayout) findViewById;
        View findViewById2 = view.findViewById(C4188R.id.delivery_indicator);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById(R.id.delivery_indicator)");
        this.deliveryIndicator = findViewById2;
        View findViewById3 = view.findViewById(C4188R.id.call_indicator);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "view.findViewById(R.id.call_indicator)");
        this.callIndicator = findViewById3;
        Intrinsics.checkExpressionValueIsNotNull(view, "view");
        initView(view);
        initTabView(view);
        initRv(view);
        setContentView(view);
        if (window != null) {
            window.setLayout(-1, -1);
        }
        if (window != null) {
            window.setBackgroundDrawableResource(R.color.transparent);
        }
    }

    private final void initRv(View view) {
        View findViewById = view.findViewById(C4188R.id.rv_call_history);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.rv_call_history)");
        this.rvCallHistory = (RecyclerView) findViewById;
        RecyclerView recyclerView = this.rvCallHistory;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rvCallHistory");
        }
        final Context context = getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context) { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.DeliveryHistoryTaskDialog$initRv$1
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        });
        RecyclerView recyclerView2 = this.rvCallHistory;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rvCallHistory");
        }
        recyclerView2.addItemDecoration(new LinearSpacesItemDecoration(SizeUtils.dp2px(getContext(), 18.0f)));
        this.adapter = new CallHistoryAdapter();
        RecyclerView recyclerView3 = this.rvCallHistory;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rvCallHistory");
        }
        CallHistoryAdapter callHistoryAdapter = this.adapter;
        if (callHistoryAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        }
        recyclerView3.setAdapter(callHistoryAdapter);
        CallHistoryAdapter callHistoryAdapter2 = this.adapter;
        if (callHistoryAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        }
        callHistoryAdapter2.setEmptyView(LayoutInflater.from(getContext()).inflate(C4188R.layout.view_call_history_empty, (ViewGroup) null));
        CallHistoryAdapter callHistoryAdapter3 = this.adapter;
        if (callHistoryAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        }
        callHistoryAdapter3.setOnItemChildClickListener(new DeliveryHistoryTaskDialog$initRv$2(this));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void initView(View root) {
        this.traysList.add(root.findViewById(C4188R.id.tray_1_tv));
        this.traysList.add(root.findViewById(C4188R.id.tray_2_tv));
        this.traysList.add(root.findViewById(C4188R.id.tray_3_tv));
        this.traysList.add(root.findViewById(C4188R.id.tray_4_tv));
        this.traysList.add(root.findViewById(C4188R.id.tray_5_tv));
        this.tablesList.add(root.findViewById(C4188R.id.tray_1_tables_layout));
        this.tablesList.add(root.findViewById(C4188R.id.tray_2_tables_layout));
        this.tablesList.add(root.findViewById(C4188R.id.tray_3_tables_layout));
        this.tablesList.add(root.findViewById(C4188R.id.tray_4_tables_layout));
        this.tablesList.add(root.findViewById(C4188R.id.tray_5_tables_layout));
        root.findViewById(C4188R.id.close_iv).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.DeliveryHistoryTaskDialog$initView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                super.onSingleClick();
                DeliveryHistoryTaskDialog.this.dismiss();
            }
        });
    }

    private final void initTabView(View view) {
        View findViewById = view.findViewById(C4188R.id.tv_delivery_history);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.tv_delivery_history)");
        this.tvDeliverTab = (TextView) findViewById;
        View findViewById2 = view.findViewById(C4188R.id.tv_call_history);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById(R.id.tv_call_history)");
        this.tvCallTab = (TextView) findViewById2;
        TextView textView = this.tvDeliverTab;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvDeliverTab");
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.DeliveryHistoryTaskDialog$initTabView$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                boolean z;
                DeliveryHistoryTaskDialog.this.deliverySelected();
                SpUtils.set(RobotContext.INSTANCE.getContext(), "history_last_operation", "op_delivery");
                z = DeliveryHistoryTaskDialog.this.initDeliveryData;
                if (z) {
                    return;
                }
                DeliveryHistoryTaskDialog.this.initData();
            }
        });
        TextView textView2 = this.tvCallTab;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvCallTab");
        }
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.DeliveryHistoryTaskDialog$initTabView$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                boolean z;
                DeliveryHistoryTaskDialog.this.callSelected();
                SpUtils.set(RobotContext.INSTANCE.getContext(), "history_last_operation", "op_call");
                z = DeliveryHistoryTaskDialog.this.initCallData;
                if (z) {
                    return;
                }
                DeliveryHistoryTaskDialog.this.initCallHistoryData();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void callSelected() {
        TextView textView = this.tvCallTab;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvCallTab");
        }
        textView.setTextColor(Color.parseColor("#FF0066FF"));
        TextView textView2 = this.tvDeliverTab;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvDeliverTab");
        }
        textView2.setTextColor(Color.parseColor("#E6000000"));
        ConstraintLayout constraintLayout = this.clDelivery;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("clDelivery");
        }
        constraintLayout.setVisibility(8);
        RecyclerView recyclerView = this.rvCallHistory;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rvCallHistory");
        }
        recyclerView.setVisibility(0);
        View view = this.deliveryIndicator;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deliveryIndicator");
        }
        view.setVisibility(8);
        View view2 = this.callIndicator;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("callIndicator");
        }
        view2.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deliverySelected() {
        TextView textView = this.tvDeliverTab;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvDeliverTab");
        }
        textView.setTextColor(Color.parseColor("#FF0066FF"));
        TextView textView2 = this.tvCallTab;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvCallTab");
        }
        textView2.setTextColor(Color.parseColor("#E6000000"));
        ConstraintLayout constraintLayout = this.clDelivery;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("clDelivery");
        }
        constraintLayout.setVisibility(0);
        RecyclerView recyclerView = this.rvCallHistory;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rvCallHistory");
        }
        recyclerView.setVisibility(8);
        View view = this.callIndicator;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("callIndicator");
        }
        view.setVisibility(8);
        View view2 = this.deliveryIndicator;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deliveryIndicator");
        }
        view2.setVisibility(0);
    }

    @Override // android.app.Dialog
    public void show() {
        NavigationBarUtil.focusNotAle(getWindow());
        super.show();
        NavigationBarUtil.hideNavigationBar(getWindow());
        NavigationBarUtil.clearFocusNotAle(getWindow());
        if (Intrinsics.areEqual(SpUtils.get(RobotContext.INSTANCE.getContext(), LAST_OP_KEY, OP_DELIVERY), OP_CALL)) {
            callSelected();
            initCallHistoryData();
            return;
        }
        deliverySelected();
        try {
            initData();
        } catch (Exception e) {
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initCallHistoryData() {
        this.initCallData = true;
        CallHistoryManager.INSTANCE.asyncGetHistory(new Function1<List<? extends CallHistoryData>, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.DeliveryHistoryTaskDialog$initCallHistoryData$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends CallHistoryData> list) {
                invoke2((List<CallHistoryData>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final List<CallHistoryData> history) {
                Handler handler;
                Intrinsics.checkParameterIsNotNull(history, "history");
                handler = DeliveryHistoryTaskDialog.this.mainHandle;
                handler.post(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.DeliveryHistoryTaskDialog$initCallHistoryData$1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        DeliveryHistoryTaskDialog.access$getAdapter$p(DeliveryHistoryTaskDialog.this).setNewData(history);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initData() {
        this.initDeliveryData = true;
        ArrayList<ArrayList<String>> lastDeliveryTask = Constans.INSTANCE.getLastDeliveryTask();
        ArrayList<ArrayList<String>> arrayList = lastDeliveryTask;
        if (arrayList == null || arrayList.isEmpty()) {
            int count = PalletCountHelper.INSTANCE.getCount();
            ArrayList<ArrayList<String>> arrayList2 = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                arrayList2.add(new ArrayList<>());
            }
            lastDeliveryTask = arrayList2;
        }
        int size = this.traysList.size();
        for (int i2 = 0; i2 < size; i2++) {
            TextView textView = this.traysList.get(i2);
            Intrinsics.checkExpressionValueIsNotNull(textView, "traysList[i]");
            TextView textView2 = textView;
            ScrollView scrollView = this.tablesList.get(i2);
            Intrinsics.checkExpressionValueIsNotNull(scrollView, "tablesList[i]");
            ScrollView scrollView2 = scrollView;
            if (lastDeliveryTask.size() > i2) {
                ArrayList<String> arrayList3 = lastDeliveryTask.get(i2);
                Intrinsics.checkExpressionValueIsNotNull(arrayList3, "lastDeliveryTask[i]");
                ArrayList<String> arrayList4 = arrayList3;
                textView2.setVisibility(0);
                textView2.setTextSize(30.0f);
                int size2 = arrayList4.size();
                if (size2 == 0) {
                    textView2.setText("");
                    scrollView2.setVisibility(8);
                } else if (size2 == 1) {
                    textView2.setText(arrayList4.get(0));
                    scrollView2.setVisibility(8);
                } else {
                    String tables = getTables(arrayList4);
                    textView2.setText(tables);
                    View childAt = scrollView2.getChildAt(0);
                    if (childAt == null) {
                        throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
                    }
                    ((TextView) childAt).setText(tables);
                    scrollView2.setVisibility(0);
                }
                UiUtils.adjustTvTextSize(textView2, 100, textView2.getText().toString(), 22);
            } else {
                textView2.setVisibility(8);
                scrollView2.setVisibility(8);
            }
        }
    }

    private final String getTables(ArrayList<String> tables) {
        StringBuilder sb = new StringBuilder();
        int size = tables.size();
        for (int i = 0; i < size; i++) {
            if (i == tables.size() - 1) {
                sb.append(tables.get(i));
            } else {
                sb.append(tables.get(i));
                sb.append(",");
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "sb.toString()");
        return sb2;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Pdlog.m3273d(TAG, "onDetachedFromWindow");
        this.mainHandle.removeCallbacksAndMessages(null);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Pdlog.m3273d(TAG, "onAttachedToWindow");
        this.mainHandle.sendEmptyMessageDelayed(1001, 120000L);
    }
}
