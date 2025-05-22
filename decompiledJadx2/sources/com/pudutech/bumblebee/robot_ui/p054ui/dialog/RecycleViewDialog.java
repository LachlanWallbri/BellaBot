package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.content.Context;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.radiobtntv.IRadioBtnTvBean;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.disinfect.baselib.ext.view.ViewExtKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: RecycleViewDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010J\u001a\u00020\u0015H\u0016J\u0010\u0010K\u001a\u00020\u00102\u0006\u0010L\u001a\u00020MH\u0014J.\u0010N\u001a\u00020\u00102\u0012\u0010O\u001a\u000e\u0012\u0004\u0012\u00020Q\u0012\u0004\u0012\u00020R0P2\u0006\u0010S\u001a\u00020T2\n\b\u0002\u0010U\u001a\u0004\u0018\u00010VJ\b\u0010W\u001a\u00020\u0010H\u0002J\b\u0010X\u001a\u00020\u0010H\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\"\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R&\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0007\u001a\u00020\u00158\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u001d\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000b\"\u0004\b\u001f\u0010\rR&\u0010 \u001a\u00020\u00152\u0006\u0010\u0007\u001a\u00020\u00158\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0018\"\u0004\b\"\u0010\u001aR\"\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0012\"\u0004\b%\u0010\u0014R$\u0010'\u001a\u00020&2\u0006\u0010\u0007\u001a\u00020&@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010.\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u000b\"\u0004\b0\u0010\rR\u0010\u00101\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R$\u00102\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u000b\"\u0004\b4\u0010\rR&\u00105\u001a\u00020\u00152\u0006\u0010\u0007\u001a\u00020\u00158\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u0018\"\u0004\b7\u0010\u001aR$\u00108\u001a\u00020&2\u0006\u0010\u0007\u001a\u00020&@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010)\"\u0004\b:\u0010+R\u0010\u0010;\u001a\u0004\u0018\u00010<X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010=\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010\u000b\"\u0004\b?\u0010\rR&\u0010@\u001a\u00020\u00152\u0006\u0010\u0007\u001a\u00020\u00158\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\u0018\"\u0004\bB\u0010\u001aR$\u0010C\u001a\u00020&2\u0006\u0010\u0007\u001a\u00020&@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010)\"\u0004\bE\u0010+R\u0010\u0010F\u001a\u0004\u0018\u00010<X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010G\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010\u000b\"\u0004\bI\u0010\r¨\u0006Y"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/RecycleViewDialog;", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/BumbleBaseDialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "closeIv", "Landroid/widget/ImageView;", ES6Iterator.VALUE_PROPERTY, "", "closeIvVisibility", "getCloseIvVisibility", "()Z", "setCloseIvVisibility", "(Z)V", "closeListener", "Lkotlin/Function0;", "", "getCloseListener", "()Lkotlin/jvm/functions/Function0;", "setCloseListener", "(Lkotlin/jvm/functions/Function0;)V", "", "confirmBg", "getConfirmBg", "()I", "setConfirmBg", "(I)V", "confirmBtn", "Landroid/widget/Button;", "confirmBtnVisibility", "getConfirmBtnVisibility", "setConfirmBtnVisibility", "confirmFontColor", "getConfirmFontColor", "setConfirmFontColor", "confirmListener", "getConfirmListener", "setConfirmListener", "", "confirmText", "getConfirmText", "()Ljava/lang/String;", "setConfirmText", "(Ljava/lang/String;)V", "contentRv", "Landroidx/recyclerview/widget/RecyclerView;", "contentRvVisibility", "getContentRvVisibility", "setContentRvVisibility", "loadIv", "loadIvVisibility", "getLoadIvVisibility", "setLoadIvVisibility", "tipFontColor", "getTipFontColor", "setTipFontColor", "tipText", "getTipText", "setTipText", "tipTv", "Landroid/widget/TextView;", "tipTvVisibility", "getTipTvVisibility", "setTipTvVisibility", "titleFontColor", "getTitleFontColor", "setTitleFontColor", "titleText", "getTitleText", "setTitleText", "titleTv", "titleTvVisibility", "getTitleTvVisibility", "setTitleTvVisibility", "getLayoutId", "initView", "view", "Landroid/view/View;", "setRecycleView", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/radiobtntv/IRadioBtnTvBean;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "layoutManager", "Landroidx/recyclerview/widget/RecyclerView$LayoutManager;", "itemDecoration", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "startLoadAnimation", "stopLoadAnimation", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class RecycleViewDialog extends BumbleBaseDialog {
    private ImageView closeIv;
    private boolean closeIvVisibility;
    private Function0<Unit> closeListener;
    private int confirmBg;
    private Button confirmBtn;
    private boolean confirmBtnVisibility;
    private int confirmFontColor;
    private Function0<Unit> confirmListener;
    private String confirmText;
    private RecyclerView contentRv;
    private boolean contentRvVisibility;
    private ImageView loadIv;
    private boolean loadIvVisibility;
    private int tipFontColor;
    private String tipText;
    private TextView tipTv;
    private boolean tipTvVisibility;
    private int titleFontColor;
    private String titleText;
    private TextView titleTv;
    private boolean titleTvVisibility;

    public final Function0<Unit> getConfirmListener() {
        return this.confirmListener;
    }

    public final void setConfirmListener(Function0<Unit> function0) {
        this.confirmListener = function0;
    }

    public final Function0<Unit> getCloseListener() {
        return this.closeListener;
    }

    public final void setCloseListener(Function0<Unit> function0) {
        this.closeListener = function0;
    }

    public final String getTitleText() {
        return this.titleText;
    }

    public final void setTitleText(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.titleText = value;
        TextView textView = this.titleTv;
        if (textView != null) {
            textView.setText(this.titleText);
        }
    }

    public final String getTipText() {
        return this.tipText;
    }

    public final void setTipText(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.tipText = value;
        TextView textView = this.tipTv;
        if (textView != null) {
            textView.setText(this.tipText);
        }
    }

    public final String getConfirmText() {
        return this.confirmText;
    }

    public final void setConfirmText(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.confirmText = value;
        Button button = this.confirmBtn;
        if (button != null) {
            button.setText(this.confirmText);
        }
    }

    public final int getTitleFontColor() {
        return this.titleFontColor;
    }

    public final void setTitleFontColor(int i) {
        this.titleFontColor = i;
        TextView textView = this.titleTv;
        if (textView != null) {
            textView.setTextColor(this.titleFontColor);
        }
    }

    public final int getTipFontColor() {
        return this.tipFontColor;
    }

    public final void setTipFontColor(int i) {
        this.tipFontColor = i;
        TextView textView = this.tipTv;
        if (textView != null) {
            textView.setTextColor(this.tipFontColor);
        }
    }

    public final int getConfirmFontColor() {
        return this.confirmFontColor;
    }

    public final void setConfirmFontColor(int i) {
        this.confirmFontColor = i;
        Button button = this.confirmBtn;
        if (button != null) {
            button.setTextColor(this.confirmFontColor);
        }
    }

    public final int getConfirmBg() {
        return this.confirmBg;
    }

    public final void setConfirmBg(int i) {
        this.confirmBg = i;
        Button button = this.confirmBtn;
        if (button != null) {
            button.setBackgroundResource(this.confirmBg);
        }
    }

    public final boolean getCloseIvVisibility() {
        return this.closeIvVisibility;
    }

    public final void setCloseIvVisibility(boolean z) {
        this.closeIvVisibility = z;
        ImageView imageView = this.closeIv;
        if (imageView != null) {
            ViewExtKt.visibleOrGone(imageView, this.closeIvVisibility);
        }
    }

    public final boolean getTitleTvVisibility() {
        return this.titleTvVisibility;
    }

    public final void setTitleTvVisibility(boolean z) {
        this.titleTvVisibility = z;
        TextView textView = this.titleTv;
        if (textView != null) {
            ViewExtKt.visibleOrGone(textView, this.titleTvVisibility);
        }
    }

    public final boolean getTipTvVisibility() {
        return this.tipTvVisibility;
    }

    public final void setTipTvVisibility(boolean z) {
        this.tipTvVisibility = z;
        TextView textView = this.tipTv;
        if (textView != null) {
            ViewExtKt.visibleOrGone(textView, this.tipTvVisibility);
        }
    }

    public final boolean getConfirmBtnVisibility() {
        return this.confirmBtnVisibility;
    }

    public final void setConfirmBtnVisibility(boolean z) {
        this.confirmBtnVisibility = z;
        Button button = this.confirmBtn;
        if (button != null) {
            ViewExtKt.visibleOrInVisible(button, this.confirmBtnVisibility);
        }
    }

    public final boolean getLoadIvVisibility() {
        return this.loadIvVisibility;
    }

    public final void setLoadIvVisibility(boolean z) {
        this.loadIvVisibility = z;
        ImageView imageView = this.loadIv;
        if (imageView != null) {
            ViewExtKt.visibleOrGone(imageView, this.loadIvVisibility);
        }
        if (this.loadIvVisibility) {
            startLoadAnimation();
        } else {
            stopLoadAnimation();
        }
    }

    public final boolean getContentRvVisibility() {
        return this.contentRvVisibility;
    }

    public final void setContentRvVisibility(boolean z) {
        this.contentRvVisibility = z;
        RecyclerView recyclerView = this.contentRv;
        if (recyclerView != null) {
            ViewExtKt.visibleOrGone(recyclerView, this.contentRvVisibility);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecycleViewDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.titleText = "";
        this.tipText = "";
        this.confirmText = "";
        this.closeIvVisibility = true;
        this.titleTvVisibility = true;
        this.tipTvVisibility = true;
        this.confirmBtnVisibility = true;
        this.loadIvVisibility = true;
        this.contentRvVisibility = true;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog
    public int getLayoutId() {
        return C4188R.layout.dialog_recycleview;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog
    public void initView(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.initView(view);
        this.titleTv = (TextView) view.findViewById(C4188R.id.title_tv);
        this.tipTv = (TextView) view.findViewById(C4188R.id.tip_tv);
        this.closeIv = (ImageView) view.findViewById(C4188R.id.close_iv);
        this.contentRv = (RecyclerView) view.findViewById(C4188R.id.content_rv);
        this.confirmBtn = (Button) view.findViewById(C4188R.id.confirm_btn);
        this.loadIv = (ImageView) view.findViewById(C4188R.id.loading_iv);
        ImageView imageView = this.closeIv;
        if (imageView != null) {
            final Map emptyMap = MapsKt.emptyMap();
            final int i = 0;
            imageView.setOnClickListener(new OnLazyVoiceClickListener(emptyMap, i) { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.RecycleViewDialog$initView$$inlined$singleClick$1
                @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
                public void onSingleClick(View v) {
                    Intrinsics.checkParameterIsNotNull(v, "v");
                    super.onSingleClick(v);
                    Function0<Unit> closeListener = this.getCloseListener();
                    if (closeListener != null) {
                        closeListener.invoke();
                    }
                }
            });
        }
        Button button = this.confirmBtn;
        if (button != null) {
            final Map emptyMap2 = MapsKt.emptyMap();
            final int i2 = 0;
            button.setOnClickListener(new OnLazyVoiceClickListener(emptyMap2, i2) { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.RecycleViewDialog$initView$$inlined$singleClick$2
                @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
                public void onSingleClick(View v) {
                    Intrinsics.checkParameterIsNotNull(v, "v");
                    super.onSingleClick(v);
                    Function0<Unit> confirmListener = this.getConfirmListener();
                    if (confirmListener != null) {
                        confirmListener.invoke();
                    }
                }
            });
        }
    }

    private final void startLoadAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(1000L);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        ImageView imageView = this.loadIv;
        if (imageView != null) {
            imageView.startAnimation(rotateAnimation);
        }
    }

    private final void stopLoadAnimation() {
        ImageView imageView = this.loadIv;
        if (imageView != null) {
            imageView.clearAnimation();
        }
    }

    public static /* synthetic */ void setRecycleView$default(RecycleViewDialog recycleViewDialog, BaseQuickAdapter baseQuickAdapter, RecyclerView.LayoutManager layoutManager, RecyclerView.ItemDecoration itemDecoration, int i, Object obj) {
        if ((i & 4) != 0) {
            itemDecoration = (RecyclerView.ItemDecoration) null;
        }
        recycleViewDialog.setRecycleView(baseQuickAdapter, layoutManager, itemDecoration);
    }

    public final void setRecycleView(BaseQuickAdapter<IRadioBtnTvBean, BaseViewHolder> adapter, RecyclerView.LayoutManager layoutManager, RecyclerView.ItemDecoration itemDecoration) {
        RecyclerView recyclerView;
        Intrinsics.checkParameterIsNotNull(adapter, "adapter");
        Intrinsics.checkParameterIsNotNull(layoutManager, "layoutManager");
        adapter.bindToRecyclerView(this.contentRv);
        RecyclerView recyclerView2 = this.contentRv;
        if (recyclerView2 != null) {
            recyclerView2.setLayoutManager(layoutManager);
        }
        if (itemDecoration == null || (recyclerView = this.contentRv) == null) {
            return;
        }
        recyclerView.addItemDecoration(itemDecoration);
    }
}
