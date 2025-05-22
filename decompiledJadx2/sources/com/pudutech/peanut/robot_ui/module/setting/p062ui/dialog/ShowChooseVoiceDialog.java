package com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.bean.VoiceBean;
import com.pudutech.peanut.robot_ui.config.Constans;
import com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener;
import com.pudutech.peanut.robot_ui.p063ui.adapter.GridSpacingItemDecoration;
import com.pudutech.peanut.robot_ui.p063ui.adapter.VoiceChangeAdapter;
import com.pudutech.peanut.robot_ui.util.NavigationBarUtil;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ShowChooseVoiceDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010$\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020%2\u0006\u0010&\u001a\u00020%H\u0002J\b\u0010'\u001a\u00020\u0014H\u0002J\u0006\u0010(\u001a\u00020\u0014J\u0010\u0010)\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010*\u001a\u00020\u0014H\u0002J\b\u0010+\u001a\u00020\u0014H\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R9\u0010\u0019\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\u000f¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowChooseVoiceDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "_context", "cancel", "Landroid/view/View;", "mAdapter", "Lcom/pudutech/peanut/robot_ui/ui/adapter/VoiceChangeAdapter;", "mLists", "Ljava/util/ArrayList;", "Lcom/pudutech/peanut/robot_ui/bean/VoiceBean;", "Lkotlin/collections/ArrayList;", "mVoiceBean", "onCloseBtnClick", "Lkotlin/Function0;", "", "getOnCloseBtnClick", "()Lkotlin/jvm/functions/Function0;", "setOnCloseBtnClick", "(Lkotlin/jvm/functions/Function0;)V", "onSureBtnClick", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "item", "getOnSureBtnClick", "()Lkotlin/jvm/functions/Function1;", "setOnSureBtnClick", "(Lkotlin/jvm/functions/Function1;)V", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "addVoice", "", "path", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "dismisscheckUpdateDownloadingDialog", "init", "setData", "show", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ShowChooseVoiceDialog extends Dialog {
    private Context _context;
    private View cancel;
    private VoiceChangeAdapter mAdapter;
    private final ArrayList<VoiceBean> mLists;
    private VoiceBean mVoiceBean;
    private Function0<Unit> onCloseBtnClick;
    private Function1<? super VoiceBean, Unit> onSureBtnClick;
    private RecyclerView recyclerView;

    public final Function0<Unit> getOnCloseBtnClick() {
        return this.onCloseBtnClick;
    }

    public final void setOnCloseBtnClick(Function0<Unit> function0) {
        this.onCloseBtnClick = function0;
    }

    public final Function1<VoiceBean, Unit> getOnSureBtnClick() {
        return this.onSureBtnClick;
    }

    public final void setOnSureBtnClick(Function1<? super VoiceBean, Unit> function1) {
        this.onSureBtnClick = function1;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShowChooseVoiceDialog(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.mLists = new ArrayList<>();
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShowChooseVoiceDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.mLists = new ArrayList<>();
        init(context);
    }

    private final void init(Context context) {
        this._context = context;
        build();
    }

    private final void build() {
        View inflate = getLayoutInflater().inflate(C5508R.layout.fragment_choose_voice_dialog, (ViewGroup) null);
        requestWindowFeature(1);
        Window window = getWindow();
        if (window != null) {
            setContentView(inflate);
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(0));
            if (attributes != null) {
                attributes.width = -1;
            }
            if (attributes != null) {
                attributes.height = -1;
            }
            window.setAttributes(attributes);
            this.recyclerView = inflate != null ? (RecyclerView) inflate.findViewById(C5508R.id.recycler_view) : null;
            this.cancel = inflate != null ? inflate.findViewById(C5508R.id.cancel) : null;
            Context context = this._context;
            this.mAdapter = context != null ? new VoiceChangeAdapter(context) : null;
            RecyclerView recyclerView = this.recyclerView;
            if (recyclerView != null) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this._context, 2, 1, false);
                recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 32, true));
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(this.mAdapter);
            }
        }
        View view = this.cancel;
        if (view != null) {
            view.setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.dialog.ShowChooseVoiceDialog$build$2
                @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
                public void onSingleClick() {
                    ShowChooseVoiceDialog.this.dismiss();
                    Function0<Unit> onCloseBtnClick = ShowChooseVoiceDialog.this.getOnCloseBtnClick();
                    if (onCloseBtnClick != null) {
                        onCloseBtnClick.invoke();
                    }
                }
            });
        }
        Button button = (Button) findViewById(C5508R.id.tvSure);
        if (button != null) {
            button.setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.dialog.ShowChooseVoiceDialog$build$3
                @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
                public void onSingleClick() {
                    VoiceBean voiceBean;
                    ShowChooseVoiceDialog.this.dismiss();
                    Function1<VoiceBean, Unit> onSureBtnClick = ShowChooseVoiceDialog.this.getOnSureBtnClick();
                    if (onSureBtnClick != null) {
                        voiceBean = ShowChooseVoiceDialog.this.mVoiceBean;
                        onSureBtnClick.invoke(voiceBean);
                    }
                }
            });
        }
        VoiceChangeAdapter voiceChangeAdapter = this.mAdapter;
        if (voiceChangeAdapter != null) {
            voiceChangeAdapter.setItemClickListener(new VoiceChangeAdapter.ItemClickListener() { // from class: com.pudutech.peanut.robot_ui.module.setting.ui.dialog.ShowChooseVoiceDialog$build$4
                @Override // com.pudutech.peanut.robot_ui.ui.adapter.VoiceChangeAdapter.ItemClickListener
                public void itemClick(VoiceBean item) {
                    Intrinsics.checkParameterIsNotNull(item, "item");
                    ShowChooseVoiceDialog.this.mVoiceBean = item;
                }
            });
        }
        setCancelable(false);
    }

    private final void setData() {
        this.mLists.clear();
        if (getContext() != null) {
            String string = getContext().getString(C5508R.string.moren);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.moren)");
            addVoice(string, "x_chongchong");
            String string2 = getContext().getString(C5508R.string.ningning);
            Intrinsics.checkExpressionValueIsNotNull(string2, "context.getString(R.string.ningning)");
            addVoice(string2, "x2_ningning");
            String string3 = getContext().getString(C5508R.string.xiaoyuan);
            Intrinsics.checkExpressionValueIsNotNull(string3, "context.getString(R.string.xiaoyuan)");
            addVoice(string3, "x2_xiaoyuan");
            String string4 = getContext().getString(C5508R.string.xiaoyue);
            Intrinsics.checkExpressionValueIsNotNull(string4, "context.getString(R.string.xiaoyue)");
            addVoice(string4, "x2_xiaoyue");
            VoiceChangeAdapter voiceChangeAdapter = this.mAdapter;
            if (voiceChangeAdapter != null) {
                voiceChangeAdapter.setNewData(this.mLists);
            }
        }
    }

    public final void dismisscheckUpdateDownloadingDialog() {
        Pdlog.m3273d("ShowDownloadingDialog", "dismisscheckUpdateDownloadingDialog");
        dismiss();
    }

    private final void addVoice(String name, String path) {
        if (Intrinsics.areEqual(Constans.INSTANCE.getTtsVoiceType(), path)) {
            this.mLists.add(new VoiceBean(name, path, true));
        } else {
            this.mLists.add(new VoiceBean(name, path, false));
        }
    }

    @Override // android.app.Dialog
    public void show() {
        setData();
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
    }
}
