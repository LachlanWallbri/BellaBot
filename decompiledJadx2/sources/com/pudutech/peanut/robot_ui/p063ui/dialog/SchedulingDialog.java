package com.pudutech.peanut.robot_ui.p063ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.airbnb.lottie.LottieAnimationView;
import com.pudutech.disinfect.baselib.util.NavigationBarUtil;
import com.pudutech.peanut.robot_ui.C5508R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SchedulingDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u001a\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\u0006H\u0002J\u0012\u0010$\u001a\u0004\u0018\u00010!2\u0006\u0010%\u001a\u00020&H\u0002J\u0010\u0010'\u001a\u00020\u00112\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010(\u001a\u00020\u0011H\u0002J\u0010\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020+H\u0002J\u000e\u0010,\u001a\u00020\u00112\u0006\u0010-\u001a\u00020.J\b\u0010/\u001a\u00020\u0011H\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\"\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015R\"\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/dialog/SchedulingDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "resId", "", "(Landroid/content/Context;I)V", "animationView", "Lcom/airbnb/lottie/LottieAnimationView;", "clSchedule", "Landroid/widget/ImageView;", "dialogCancel", "", "mContext", "onBtnResume", "Lkotlin/Function0;", "", "getOnBtnResume", "()Lkotlin/jvm/functions/Function0;", "setOnBtnResume", "(Lkotlin/jvm/functions/Function0;)V", "onClose", "getOnClose", "setOnClose", "onRootLayoutClick", "getOnRootLayoutClick", "setOnRootLayoutClick", "rootLayout", "Landroidx/constraintlayout/widget/ConstraintLayout;", "tvSchedule", "Landroid/widget/TextView;", "blurBitmap", "Landroid/graphics/Bitmap;", "bitmap", "radius", "drawableToBitmap", "drawable", "Landroid/graphics/drawable/Drawable;", "init", "initListener", "initView", "view", "Landroid/view/View;", "setContentTips", "text", "", "show", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SchedulingDialog extends Dialog {
    private LottieAnimationView animationView;
    private ImageView clSchedule;
    private boolean dialogCancel;
    private Context mContext;
    private Function0<Unit> onBtnResume;
    private Function0<Unit> onClose;
    private Function0<Unit> onRootLayoutClick;
    private ConstraintLayout rootLayout;
    private TextView tvSchedule;

    public final Function0<Unit> getOnClose() {
        return this.onClose;
    }

    public final void setOnClose(Function0<Unit> function0) {
        this.onClose = function0;
    }

    public final Function0<Unit> getOnBtnResume() {
        return this.onBtnResume;
    }

    public final void setOnBtnResume(Function0<Unit> function0) {
        this.onBtnResume = function0;
    }

    public final Function0<Unit> getOnRootLayoutClick() {
        return this.onRootLayoutClick;
    }

    public final void setOnRootLayoutClick(Function0<Unit> function0) {
        this.onRootLayoutClick = function0;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SchedulingDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SchedulingDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        init(context);
    }

    private final void init(Context context) {
        this.mContext = context;
        View viewLayout = getLayoutInflater().inflate(C5508R.layout.dialog_scheduling, (ViewGroup) null);
        requestWindowFeature(1);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setAttributes(attributes);
            window.setContentView(viewLayout);
            window.setFlags(1024, 1024);
            window.setWindowAnimations(C5508R.style.NoAnimationDialog);
        }
        Intrinsics.checkExpressionValueIsNotNull(viewLayout, "viewLayout");
        initView(viewLayout);
        setCancelable(this.dialogCancel);
        initListener();
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

    private final void initListener() {
        ConstraintLayout constraintLayout = this.rootLayout;
        if (constraintLayout != null) {
            constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.SchedulingDialog$initListener$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    Function0<Unit> onRootLayoutClick = SchedulingDialog.this.getOnRootLayoutClick();
                    if (onRootLayoutClick != null) {
                        onRootLayoutClick.invoke();
                    }
                }
            });
        }
    }

    private final void initView(View view) {
        this.tvSchedule = (TextView) view.findViewById(C5508R.id.tv_schedule);
        this.clSchedule = (ImageView) view.findViewById(C5508R.id.cl_schedule);
        this.rootLayout = (ConstraintLayout) view.findViewById(C5508R.id.root_layout);
        this.animationView = (LottieAnimationView) view.findViewById(C5508R.id.animation_view);
    }

    public final void setContentTips(String text) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        TextView textView = this.tvSchedule;
        if (textView != null) {
            textView.setText(text);
        }
    }

    private final Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    private final Bitmap blurBitmap(Bitmap bitmap, int radius) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        RenderScript create = RenderScript.create(this.mContext);
        Intrinsics.checkExpressionValueIsNotNull(create, "RenderScript.create(mContext)");
        Allocation createFromBitmap = Allocation.createFromBitmap(create, bitmap);
        Intrinsics.checkExpressionValueIsNotNull(createFromBitmap, "Allocation.createFromBitmap(rs, bitmap)");
        Allocation createFromBitmap2 = Allocation.createFromBitmap(create, createBitmap);
        Intrinsics.checkExpressionValueIsNotNull(createFromBitmap2, "Allocation.createFromBitmap(rs, outBitmap)");
        ScriptIntrinsicBlur create2 = ScriptIntrinsicBlur.create(create, Element.U8_4(create));
        Intrinsics.checkExpressionValueIsNotNull(create2, "ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))");
        create2.setRadius(radius);
        create2.setInput(createFromBitmap);
        create2.forEach(createFromBitmap2);
        createFromBitmap2.copyTo(createBitmap);
        bitmap.recycle();
        create.destroy();
        return createBitmap;
    }
}
