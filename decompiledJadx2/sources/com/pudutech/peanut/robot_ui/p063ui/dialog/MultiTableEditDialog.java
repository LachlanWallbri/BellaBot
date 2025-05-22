package com.pudutech.peanut.robot_ui.p063ui.dialog;

import android.R;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.presenter.delivery_task.DeliveryModel;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.listener.OnLazyClickListener;
import com.pudutech.peanut.robot_ui.p063ui.adapter.MultiTableEditAdapter;
import com.pudutech.peanut.robot_ui.util.NavigationBarUtil;
import com.pudutech.peanut.robot_ui.util.PlaySound;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MultiTableEditDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u001d\u001a\u00020\u0016H\u0002J\u0012\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0010\u0010\"\u001a\u00020\u00162\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010#\u001a\u00020\u0016H\u0002J\b\u0010$\u001a\u00020\u0016H\u0016J\b\u0010%\u001a\u00020\u0016H\u0016J\u001e\u0010&\u001a\u00020\u00162\u0016\u0010'\u001a\u0012\u0012\u0004\u0012\u00020\u00150\u000bj\b\u0012\u0004\u0012\u00020\u0015`\rJ\b\u0010(\u001a\u00020\u0016H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R8\u0010\u0013\u001a \u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00150\u000bj\b\u0012\u0004\u0012\u00020\u0015`\r\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000¨\u0006)"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/dialog/MultiTableEditDialog;", "Lcom/pudutech/peanut/robot_ui/ui/dialog/TouchEventDialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "AUTO_CLOSE", "", "TAG", "", "kotlin.jvm.PlatformType", "dataList", "Ljava/util/ArrayList;", "Lcom/pudutech/peanut/robot_ui/ui/adapter/MultiTableEditAdapter$DishInfoEdit;", "Lkotlin/collections/ArrayList;", "mContext", "mainHandle", "Landroid/os/Handler;", "multiTableEditAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/MultiTableEditAdapter;", "onDateEditFinishListener", "Lkotlin/Function1;", "Lcom/pudutech/peanut/presenter/delivery_task/DeliveryModel;", "", "getOnDateEditFinishListener", "()Lkotlin/jvm/functions/Function1;", "setOnDateEditFinishListener", "(Lkotlin/jvm/functions/Function1;)V", "view", "Landroid/view/View;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "dispatchTouchEvent", "", "ev", "Landroid/view/MotionEvent;", "init", "initView", "onAttachedToWindow", "onDetachedFromWindow", "setDishInfo", "datas", "show", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MultiTableEditDialog extends TouchEventDialog {
    private final int AUTO_CLOSE;
    private final String TAG;
    private final ArrayList<MultiTableEditAdapter.DishInfoEdit> dataList;
    private Context mContext;
    private final Handler mainHandle;
    private MultiTableEditAdapter multiTableEditAdapter;
    private Function1<? super ArrayList<DeliveryModel>, Unit> onDateEditFinishListener;
    private View view;

    public static final /* synthetic */ MultiTableEditAdapter access$getMultiTableEditAdapter$p(MultiTableEditDialog multiTableEditDialog) {
        MultiTableEditAdapter multiTableEditAdapter = multiTableEditDialog.multiTableEditAdapter;
        if (multiTableEditAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("multiTableEditAdapter");
        }
        return multiTableEditAdapter;
    }

    public final Function1<ArrayList<DeliveryModel>, Unit> getOnDateEditFinishListener() {
        return this.onDateEditFinishListener;
    }

    public final void setOnDateEditFinishListener(Function1<? super ArrayList<DeliveryModel>, Unit> function1) {
        this.onDateEditFinishListener = function1;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultiTableEditDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.AUTO_CLOSE = 101;
        this.dataList = new ArrayList<>();
        this.mainHandle = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.MultiTableEditDialog$mainHandle$1
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                int i;
                String str;
                int i2 = message.what;
                i = MultiTableEditDialog.this.AUTO_CLOSE;
                if (i2 == i) {
                    str = MultiTableEditDialog.this.TAG;
                    Pdlog.m3273d(str, "AUTO_CLOSE");
                    MultiTableEditDialog.this.dismiss();
                }
                return true;
            }
        });
        init(context);
    }

    private final void init(Context context) {
        this.mContext = context;
        build();
    }

    private final void build() {
        View inflate = getLayoutInflater().inflate(C5508R.layout.dialog_multi_table_edit, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…g_multi_table_edit, null)");
        this.view = inflate;
        requestWindowFeature(1);
        Window window = getWindow();
        if (window == null) {
            Intrinsics.throwNpe();
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setGravity(17);
        window.setAttributes(attributes);
        window.setBackgroundDrawableResource(R.color.transparent);
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        setContentView(view);
        setCanceledOnTouchOutside(true);
        initView();
    }

    private final void initView() {
        ((TextView) findViewById(C5508R.id.btn_sure)).setOnClickListener(new OnLazyClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.MultiTableEditDialog$initView$1
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyClickListener
            public void onSingleClick() {
                String str;
                str = MultiTableEditDialog.this.TAG;
                Pdlog.m3273d(str, "btn_return btn_sure");
                ArrayList<DeliveryModel> arrayList = new ArrayList<>();
                List<MultiTableEditAdapter.DishInfoEdit> data = MultiTableEditDialog.access$getMultiTableEditAdapter$p(MultiTableEditDialog.this).getData();
                Intrinsics.checkExpressionValueIsNotNull(data, "multiTableEditAdapter.data");
                for (MultiTableEditAdapter.DishInfoEdit dishInfoEdit : data) {
                    if (!dishInfoEdit.isClose()) {
                        arrayList.add(dishInfoEdit.getInfo());
                    }
                }
                Function1<ArrayList<DeliveryModel>, Unit> onDateEditFinishListener = MultiTableEditDialog.this.getOnDateEditFinishListener();
                if (onDateEditFinishListener != null) {
                    onDateEditFinishListener.invoke(arrayList);
                }
                PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                MultiTableEditDialog.this.dismiss();
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.mContext, 4);
        RecyclerView multi_table_recycler_view = (RecyclerView) findViewById(C5508R.id.multi_table_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(multi_table_recycler_view, "multi_table_recycler_view");
        multi_table_recycler_view.setLayoutManager(gridLayoutManager);
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        this.multiTableEditAdapter = new MultiTableEditAdapter(context);
        MultiTableEditAdapter multiTableEditAdapter = this.multiTableEditAdapter;
        if (multiTableEditAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("multiTableEditAdapter");
        }
        multiTableEditAdapter.setNewData(this.dataList);
        RecyclerView multi_table_recycler_view2 = (RecyclerView) findViewById(C5508R.id.multi_table_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(multi_table_recycler_view2, "multi_table_recycler_view");
        MultiTableEditAdapter multiTableEditAdapter2 = this.multiTableEditAdapter;
        if (multiTableEditAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("multiTableEditAdapter");
        }
        multi_table_recycler_view2.setAdapter(multiTableEditAdapter2);
        final int i = 8;
        ((RecyclerView) findViewById(C5508R.id.multi_table_recycler_view)).addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.MultiTableEditDialog$initView$2
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                Intrinsics.checkParameterIsNotNull(outRect, "outRect");
                Intrinsics.checkParameterIsNotNull(view, "view");
                Intrinsics.checkParameterIsNotNull(parent, "parent");
                Intrinsics.checkParameterIsNotNull(state, "state");
                outRect.top = i;
                outRect.left = 0;
                outRect.right = 13;
                outRect.bottom = 0;
            }
        });
    }

    public final void setDishInfo(ArrayList<DeliveryModel> datas) {
        Intrinsics.checkParameterIsNotNull(datas, "datas");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = datas.iterator();
        while (it.hasNext()) {
            arrayList.add(new MultiTableEditAdapter.DishInfoEdit((DeliveryModel) it.next(), false, 2, null));
        }
        this.dataList.addAll(arrayList);
        MultiTableEditAdapter multiTableEditAdapter = this.multiTableEditAdapter;
        if (multiTableEditAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("multiTableEditAdapter");
        }
        multiTableEditAdapter.notifyDataSetChanged();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Pdlog.m3273d(this.TAG, "onAttachedToWindow");
        this.mainHandle.sendEmptyMessageDelayed(this.AUTO_CLOSE, 120000L);
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.dialog.TouchEventDialog, android.app.Dialog, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev != null && ev.getAction() == 0) {
            Pdlog.m3273d(this.TAG, "dispatchTouchEvent down");
            this.mainHandle.removeMessages(this.AUTO_CLOSE);
            this.mainHandle.sendEmptyMessageDelayed(this.AUTO_CLOSE, 120000L);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Pdlog.m3273d(this.TAG, "onDetachedFromWindow");
        this.mainHandle.removeMessages(this.AUTO_CLOSE);
    }

    @Override // android.app.Dialog
    public void show() {
        Window window = getWindow();
        if (window == null) {
            Intrinsics.throwNpe();
        }
        NavigationBarUtil.focusNotAle(window);
        super.show();
        Window window2 = getWindow();
        if (window2 == null) {
            Intrinsics.throwNpe();
        }
        NavigationBarUtil.hideNavigationBar(window2);
        Window window3 = getWindow();
        if (window3 == null) {
            Intrinsics.throwNpe();
        }
        NavigationBarUtil.clearFocusNotAle(window3);
        Window window4 = getWindow();
        if (window4 != null) {
            window4.setLayout(-1, -1);
        }
    }
}
