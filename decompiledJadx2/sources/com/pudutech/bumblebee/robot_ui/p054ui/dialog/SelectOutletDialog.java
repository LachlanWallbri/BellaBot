package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.GridSpacingItemDecoration;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.SelectDishPortAdapter;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.util.DensityUtil;
import com.pudutech.bumblebee.robot_ui.util.NavigationBarUtil;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SelectOutletDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u001c\u001a\u00020\u0017H\u0016J\b\u0010\u001d\u001a\u00020\u0017H\u0003J\b\u0010\u001e\u001a\u00020\u0017H\u0002J\b\u0010\u001f\u001a\u00020\u0017H\u0016J\b\u0010 \u001a\u00020\u0017H\u0002J\b\u0010!\u001a\u00020\u0017H\u0016J\u0012\u0010\"\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0005H\u0002R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R9\u0010\u0012\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/SelectOutletDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "selectedDishPort", "", "(Landroid/content/Context;Ljava/lang/String;)V", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "TAG", "TIME_OUT", "", "adapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SelectDishPortAdapter;", "dismissRunnable", "Ljava/lang/Runnable;", "onComplete", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "dishPort", "", "getOnComplete", "()Lkotlin/jvm/functions/Function1;", "setOnComplete", "(Lkotlin/jvm/functions/Function1;)V", "dismiss", "init", "initView", "onAttachedToWindow", "setAutoClose", "show", "update", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SelectOutletDialog extends Dialog {
    private final String TAG;
    private final long TIME_OUT;
    private SelectDishPortAdapter adapter;
    private final Runnable dismissRunnable;
    private Function1<? super String, Unit> onComplete;
    private String selectedDishPort;

    public final Function1<String, Unit> getOnComplete() {
        return this.onComplete;
    }

    public final void setOnComplete(Function1<? super String, Unit> function1) {
        this.onComplete = function1;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectOutletDialog(Context context, String str) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "SelectDishSpotDialog";
        this.TIME_OUT = 10000L;
        this.dismissRunnable = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.SelectOutletDialog$dismissRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                SelectOutletDialog.this.dismiss();
            }
        };
        this.selectedDishPort = str;
        init();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectOutletDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "SelectDishSpotDialog";
        this.TIME_OUT = 10000L;
        this.dismissRunnable = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.SelectOutletDialog$dismissRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                SelectOutletDialog.this.dismiss();
            }
        };
        init();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectOutletDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "SelectDishSpotDialog";
        this.TIME_OUT = 10000L;
        this.dismissRunnable = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.SelectOutletDialog$dismissRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                SelectOutletDialog.this.dismiss();
            }
        };
        init();
    }

    private final void init() {
        final View inflate = getLayoutInflater().inflate(C4188R.layout.dialog_select_outlet, (ViewGroup) null);
        requestWindowFeature(1);
        final Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(17);
            window.setAttributes(attributes);
            setContentView(inflate);
            findViewById(C4188R.id.mask).setOnTouchListener(new View.OnTouchListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.SelectOutletDialog$init$$inlined$let$lambda$1
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent event) {
                    Intrinsics.checkExpressionValueIsNotNull(event, "event");
                    if (event.getAction() != 0) {
                        return false;
                    }
                    SelectOutletDialog.this.setAutoClose();
                    return false;
                }
            });
        }
        setCancelable(false);
        initView();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Pdlog.m3273d(this.TAG, "onAttachedToWindow");
    }

    @Override // android.app.Dialog
    public void show() {
        NavigationBarUtil.focusNotAle(getWindow());
        super.show();
        NavigationBarUtil.hideNavigationBar(getWindow());
        NavigationBarUtil.clearFocusNotAle(getWindow());
        Window window = getWindow();
        if (window != null) {
            window.setLayout(-1, -1);
        }
        setAutoClose();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAutoClose() {
        ((ImageView) findViewById(C4188R.id.close_iv)).removeCallbacks(this.dismissRunnable);
        ((ImageView) findViewById(C4188R.id.close_iv)).postDelayed(this.dismissRunnable, this.TIME_OUT);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        ImageView imageView = (ImageView) findViewById(C4188R.id.close_iv);
        if (imageView != null) {
            imageView.removeCallbacks(this.dismissRunnable);
        }
        super.dismiss();
    }

    private final void initView() {
        ((ImageView) findViewById(C4188R.id.close_iv)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.SelectOutletDialog$initView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                SelectOutletDialog.this.dismiss();
            }
        });
        ((Button) findViewById(C4188R.id.confirm_btn)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.SelectOutletDialog$initView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                String str;
                Function1<String, Unit> onComplete = SelectOutletDialog.this.getOnComplete();
                if (onComplete != null) {
                    str = SelectOutletDialog.this.selectedDishPort;
                    onComplete.invoke(str);
                }
                SelectOutletDialog.this.dismiss();
            }
        });
        this.adapter = new SelectDishPortAdapter();
        SelectDishPortAdapter selectDishPortAdapter = this.adapter;
        if (selectDishPortAdapter != null) {
            int i = C4188R.layout.dialog_select_outlet_empty;
            RecyclerView rv_outlet = (RecyclerView) findViewById(C4188R.id.rv_outlet);
            Intrinsics.checkExpressionValueIsNotNull(rv_outlet, "rv_outlet");
            ViewParent parent = rv_outlet.getParent();
            if (parent == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
            }
            selectDishPortAdapter.setEmptyView(i, (ViewGroup) parent);
        }
        ArrayList<String> dinningOutLets = RobotMapManager.INSTANCE.getDinningOutLets();
        SelectDishPortAdapter selectDishPortAdapter2 = this.adapter;
        if (selectDishPortAdapter2 != null) {
            selectDishPortAdapter2.setSelectedDishPort(this.selectedDishPort);
        }
        SelectDishPortAdapter selectDishPortAdapter3 = this.adapter;
        if (selectDishPortAdapter3 != null) {
            selectDishPortAdapter3.setNewData(dinningOutLets);
        }
        RecyclerView rv_outlet2 = (RecyclerView) findViewById(C4188R.id.rv_outlet);
        Intrinsics.checkExpressionValueIsNotNull(rv_outlet2, "rv_outlet");
        rv_outlet2.setAdapter(this.adapter);
        RecyclerView rv_outlet3 = (RecyclerView) findViewById(C4188R.id.rv_outlet);
        Intrinsics.checkExpressionValueIsNotNull(rv_outlet3, "rv_outlet");
        rv_outlet3.setLayoutManager(new GridLayoutManager(getContext(), 2));
        SelectDishPortAdapter selectDishPortAdapter4 = this.adapter;
        if (selectDishPortAdapter4 != null) {
            selectDishPortAdapter4.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.SelectOutletDialog$initView$3
                @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter<Object, BaseViewHolder> baseQuickAdapter, View view, int i2) {
                    String str;
                    ArrayList<String> dinningOutLets2 = RobotMapManager.INSTANCE.getDinningOutLets();
                    SelectOutletDialog.this.selectedDishPort = dinningOutLets2.get(i2);
                    SelectOutletDialog selectOutletDialog = SelectOutletDialog.this;
                    str = selectOutletDialog.selectedDishPort;
                    selectOutletDialog.update(str);
                }
            });
        }
        ((RecyclerView) findViewById(C4188R.id.rv_outlet)).addItemDecoration(new GridSpacingItemDecoration(2, DensityUtil.dp2px(getContext(), 24.0f), false));
        Pdlog.m3273d(this.TAG, "outlet:" + dinningOutLets + ", selectedDishPort:" + this.selectedDishPort);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void update(String dishPort) {
        SelectDishPortAdapter selectDishPortAdapter = this.adapter;
        if (selectDishPortAdapter != null) {
            selectDishPortAdapter.setSelectedDishPort(dishPort);
        }
        SelectDishPortAdapter selectDishPortAdapter2 = this.adapter;
        if (selectDishPortAdapter2 != null) {
            selectDishPortAdapter2.notifyDataSetChanged();
        }
    }
}
