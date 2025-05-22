package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.information_system_task.InformationSystemContract;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.DishInfoEditAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.StaggeredDividerItemDecoration;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener;
import com.pudutech.bumblebee.robot_ui.util.NavigationBarUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DishInfoListEditDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u001e\u001a\u00020\u0017H\u0002J\u0010\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010 \u001a\u00020\u0017H\u0002J\b\u0010!\u001a\u00020\u0017H\u0016J\b\u0010\"\u001a\u00020\u0017H\u0016J\u001e\u0010#\u001a\u00020\u00172\u0016\u0010$\u001a\u0012\u0012\u0004\u0012\u00020\u00160\fj\b\u0012\u0004\u0012\u00020\u0016`\u000eJ\b\u0010%\u001a\u00020\u0017H\u0016J\u000e\u0010&\u001a\u00020\u00172\u0006\u0010'\u001a\u00020\u0012R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R8\u0010\u0014\u001a \u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00160\fj\b\u0012\u0004\u0012\u00020\u0016`\u000e\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082.¢\u0006\u0002\n\u0000¨\u0006("}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/DishInfoListEditDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResID", "", "(Landroid/content/Context;I)V", "TAG", "", "kotlin.jvm.PlatformType", "dataList", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/DishInfoEditAdapter$DishInfoEdit;", "Lkotlin/collections/ArrayList;", "dishInfoEditAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/DishInfoEditAdapter;", "isBirthdayTheme", "", "mContext", "onDateEditFinishListener", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/presenter/information_system_task/InformationSystemContract$OrderInfo;", "", "getOnDateEditFinishListener", "()Lkotlin/jvm/functions/Function1;", "setOnDateEditFinishListener", "(Lkotlin/jvm/functions/Function1;)V", "view", "Landroid/view/View;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "init", "initView", "onAttachedToWindow", "onDetachedFromWindow", "setDishInfo", "datas", "show", "switchBirthdayTheme", "boolean", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class DishInfoListEditDialog extends Dialog {
    private final String TAG;
    private final ArrayList<DishInfoEditAdapter.DishInfoEdit> dataList;
    private DishInfoEditAdapter dishInfoEditAdapter;
    private boolean isBirthdayTheme;
    private Context mContext;
    private Function1<? super ArrayList<InformationSystemContract.OrderInfo>, Unit> onDateEditFinishListener;
    private View view;

    public static final /* synthetic */ DishInfoEditAdapter access$getDishInfoEditAdapter$p(DishInfoListEditDialog dishInfoListEditDialog) {
        DishInfoEditAdapter dishInfoEditAdapter = dishInfoListEditDialog.dishInfoEditAdapter;
        if (dishInfoEditAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dishInfoEditAdapter");
        }
        return dishInfoEditAdapter;
    }

    public final Function1<ArrayList<InformationSystemContract.OrderInfo>, Unit> getOnDateEditFinishListener() {
        return this.onDateEditFinishListener;
    }

    public final void setOnDateEditFinishListener(Function1<? super ArrayList<InformationSystemContract.OrderInfo>, Unit> function1) {
        this.onDateEditFinishListener = function1;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DishInfoListEditDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.dataList = new ArrayList<>();
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DishInfoListEditDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.dataList = new ArrayList<>();
        init(context);
    }

    private final void init(Context context) {
        this.mContext = context;
        build();
    }

    public final void switchBirthdayTheme(boolean r3) {
        if (this.isBirthdayTheme != r3) {
            this.isBirthdayTheme = r3;
            if (this.isBirthdayTheme) {
                ((CardView) findViewById(C4188R.id.list_layout)).setCardBackgroundColor(getContext().getColor(C4188R.color.table_birthday_bg));
            } else {
                ((CardView) findViewById(C4188R.id.list_layout)).setCardBackgroundColor(getContext().getColor(C4188R.color.white));
            }
            DishInfoEditAdapter dishInfoEditAdapter = this.dishInfoEditAdapter;
            if (dishInfoEditAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dishInfoEditAdapter");
            }
            dishInfoEditAdapter.setBirthdayTheme(this.isBirthdayTheme);
            DishInfoEditAdapter dishInfoEditAdapter2 = this.dishInfoEditAdapter;
            if (dishInfoEditAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dishInfoEditAdapter");
            }
            dishInfoEditAdapter2.notifyDataSetChanged();
        }
    }

    private final void build() {
        View inflate = getLayoutInflater().inflate(C4188R.layout.dialog_dish_info_list_edit, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…ish_info_list_edit, null)");
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
        ((TextView) findViewById(C4188R.id.btn_return)).setOnClickListener(new OnLazyClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.DishInfoListEditDialog$initView$1
            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener
            public void onSingleClick() {
                String str;
                str = DishInfoListEditDialog.this.TAG;
                Pdlog.m3273d(str, "btn_return onSingleClick");
                DishInfoListEditDialog.this.dismiss();
            }
        });
        ((TextView) findViewById(C4188R.id.btnSure)).setOnClickListener(new OnLazyClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.DishInfoListEditDialog$initView$2
            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener
            public void onSingleClick() {
                String str;
                str = DishInfoListEditDialog.this.TAG;
                Pdlog.m3273d(str, "btn_return btn_sure");
                ArrayList<InformationSystemContract.OrderInfo> arrayList = new ArrayList<>();
                List<DishInfoEditAdapter.DishInfoEdit> data = DishInfoListEditDialog.access$getDishInfoEditAdapter$p(DishInfoListEditDialog.this).getData();
                Intrinsics.checkExpressionValueIsNotNull(data, "dishInfoEditAdapter.data");
                for (DishInfoEditAdapter.DishInfoEdit dishInfoEdit : data) {
                    if (!dishInfoEdit.isClose()) {
                        arrayList.add(dishInfoEdit.getInfo());
                    }
                }
                Function1<ArrayList<InformationSystemContract.OrderInfo>, Unit> onDateEditFinishListener = DishInfoListEditDialog.this.getOnDateEditFinishListener();
                if (onDateEditFinishListener != null) {
                    onDateEditFinishListener.invoke(arrayList);
                }
                DishInfoListEditDialog.this.dismiss();
            }
        });
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        RecyclerView dish_info_recycler_view = (RecyclerView) findViewById(C4188R.id.dish_info_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(dish_info_recycler_view, "dish_info_recycler_view");
        dish_info_recycler_view.setLayoutManager(staggeredGridLayoutManager);
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        this.dishInfoEditAdapter = new DishInfoEditAdapter(context);
        DishInfoEditAdapter dishInfoEditAdapter = this.dishInfoEditAdapter;
        if (dishInfoEditAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dishInfoEditAdapter");
        }
        dishInfoEditAdapter.setNewData(this.dataList);
        RecyclerView dish_info_recycler_view2 = (RecyclerView) findViewById(C4188R.id.dish_info_recycler_view);
        Intrinsics.checkExpressionValueIsNotNull(dish_info_recycler_view2, "dish_info_recycler_view");
        DishInfoEditAdapter dishInfoEditAdapter2 = this.dishInfoEditAdapter;
        if (dishInfoEditAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dishInfoEditAdapter");
        }
        dish_info_recycler_view2.setAdapter(dishInfoEditAdapter2);
        ((RecyclerView) findViewById(C4188R.id.dish_info_recycler_view)).addItemDecoration(new StaggeredDividerItemDecoration(getContext(), 20.0f, 2));
        DishInfoEditAdapter dishInfoEditAdapter3 = this.dishInfoEditAdapter;
        if (dishInfoEditAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dishInfoEditAdapter");
        }
        dishInfoEditAdapter3.setOnItemClickListener(new OnLazyItemClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.DishInfoListEditDialog$initView$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, 0 == true ? 1 : 0);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener
            public void onSingleItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                Intrinsics.checkParameterIsNotNull(adapter, "adapter");
                Intrinsics.checkParameterIsNotNull(view, "view");
                Object obj = adapter.getData().get(position);
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.adapter.DishInfoEditAdapter.DishInfoEdit");
                }
                ((DishInfoEditAdapter.DishInfoEdit) obj).setClose(!r2.isClose());
                DishInfoListEditDialog.access$getDishInfoEditAdapter$p(DishInfoListEditDialog.this).notifyItemChanged(position);
            }
        });
    }

    public final void setDishInfo(ArrayList<InformationSystemContract.OrderInfo> datas) {
        Intrinsics.checkParameterIsNotNull(datas, "datas");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = datas.iterator();
        while (it.hasNext()) {
            arrayList.add(new DishInfoEditAdapter.DishInfoEdit((InformationSystemContract.OrderInfo) it.next(), false, 2, null));
        }
        this.dataList.addAll(arrayList);
        DishInfoEditAdapter dishInfoEditAdapter = this.dishInfoEditAdapter;
        if (dishInfoEditAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dishInfoEditAdapter");
        }
        dishInfoEditAdapter.notifyDataSetChanged();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Pdlog.m3273d(this.TAG, "onAttachedToWindow");
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Pdlog.m3273d(this.TAG, "onDetachedFromWindow");
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
