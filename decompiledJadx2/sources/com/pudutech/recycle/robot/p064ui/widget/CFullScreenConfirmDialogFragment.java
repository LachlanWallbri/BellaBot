package com.pudutech.recycle.robot.p064ui.widget;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.recycle.robot.p064ui.widget.CFullScreenConfirmDialogFragment;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CFullScreenConfirmDialogFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u001aB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J&\u0010\u0014\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0018\u001a\u00020\rH\u0016J\b\u0010\u0019\u001a\u00020\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/recycle/robot/ui/widget/CFullScreenConfirmDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "builder", "Lcom/pudutech/recycle/robot/ui/widget/CFullScreenConfirmDialogFragment$Builder;", "(Lcom/pudutech/recycle/robot/ui/widget/CFullScreenConfirmDialogFragment$Builder;)V", "negetiveButton", "Landroid/widget/Button;", "positiveButton", "rootLayout", "Landroid/view/ViewGroup;", "tipsTextView", "Landroid/widget/TextView;", "initWidget", "", "view", "Landroid/view/View;", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "onResume", "setListeners", "Builder", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class CFullScreenConfirmDialogFragment extends DialogFragment {
    private HashMap _$_findViewCache;
    private Builder builder;
    private Button negetiveButton;
    private Button positiveButton;
    private ViewGroup rootLayout;
    private TextView tipsTextView;

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
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public CFullScreenConfirmDialogFragment(Builder builder) {
        Intrinsics.checkParameterIsNotNull(builder, "builder");
        this.builder = builder;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        View view = inflater.inflate(2131427373, container);
        Intrinsics.checkExpressionValueIsNotNull(view, "view");
        initWidget(view);
        setListeners();
        return view;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        Dialog dialog = new Dialog(activity, 2131624445);
        dialog.requestWindowFeature(1);
        FragmentActivity activity2 = getActivity();
        if (activity2 == null) {
            Intrinsics.throwNpe();
        }
        dialog.setContentView(LayoutInflater.from(activity2).inflate(2131427373, (ViewGroup) null));
        Window window = dialog.getWindow();
        if (window == null) {
            Intrinsics.throwNpe();
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 0;
        attributes.width = -1;
        attributes.height = -1;
        int backgroundColor = this.builder.getBackgroundColor();
        if (backgroundColor == 0) {
            backgroundColor = Color.parseColor("#aa000000");
        }
        window.setBackgroundDrawable(new ColorDrawable(backgroundColor));
        window.setAttributes(attributes);
        return dialog;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow();
        }
    }

    private final void initWidget(View view) {
        View findViewById = view.findViewById(2131230909);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.layout_root)");
        this.rootLayout = (ViewGroup) findViewById;
        View findViewById2 = view.findViewById(2131231089);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById(R.id.tv_tips)");
        this.tipsTextView = (TextView) findViewById2;
        View findViewById3 = view.findViewById(2131230791);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "view.findViewById(R.id.btn_positive)");
        this.positiveButton = (Button) findViewById3;
        View findViewById4 = view.findViewById(2131230790);
        Intrinsics.checkExpressionValueIsNotNull(findViewById4, "view.findViewById(R.id.btn_negative)");
        this.negetiveButton = (Button) findViewById4;
        TextView textView = this.tipsTextView;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tipsTextView");
        }
        textView.setText(this.builder.getTips());
        Button button = this.positiveButton;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("positiveButton");
        }
        button.setText(this.builder.getPositiveText());
        Button button2 = this.negetiveButton;
        if (button2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("negetiveButton");
        }
        button2.setText(this.builder.getNegativeText());
        Button button3 = this.positiveButton;
        if (button3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("positiveButton");
        }
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.recycle.robot.ui.widget.CFullScreenConfirmDialogFragment$initWidget$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CFullScreenConfirmDialogFragment.Builder builder;
                CFullScreenConfirmDialogFragment.this.dismiss();
                builder = CFullScreenConfirmDialogFragment.this.builder;
                builder.getPositiveClickListener().onClick(view2);
            }
        });
        Button button4 = this.negetiveButton;
        if (button4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("negetiveButton");
        }
        CharSequence text = button4.getText();
        Intrinsics.checkExpressionValueIsNotNull(text, "negetiveButton.text");
        if (text.length() == 0) {
            Button button5 = this.negetiveButton;
            if (button5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("negetiveButton");
            }
            button5.setVisibility(4);
            return;
        }
        Button button6 = this.negetiveButton;
        if (button6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("negetiveButton");
        }
        button6.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.recycle.robot.ui.widget.CFullScreenConfirmDialogFragment$initWidget$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CFullScreenConfirmDialogFragment.Builder builder;
                CFullScreenConfirmDialogFragment.this.dismiss();
                builder = CFullScreenConfirmDialogFragment.this.builder;
                builder.getNegativeClickListener().onClick(view2);
            }
        });
    }

    private final void setListeners() {
        ViewGroup viewGroup = this.rootLayout;
        if (viewGroup == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootLayout");
        }
        viewGroup.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.recycle.robot.ui.widget.CFullScreenConfirmDialogFragment$setListeners$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CFullScreenConfirmDialogFragment.this.dismiss();
            }
        });
    }

    /* compiled from: CFullScreenConfirmDialogFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u0016\u0010 \u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010!\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\nJ\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0010R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR\u001a\u0010\u0018\u001a\u00020\u0010X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u0014R\u001a\u0010\u001b\u001a\u00020\u0010X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0012\"\u0004\b\u001d\u0010\u0014¨\u0006\""}, m3961d2 = {"Lcom/pudutech/recycle/robot/ui/widget/CFullScreenConfirmDialogFragment$Builder;", "", "()V", "backgroundColor", "", "getBackgroundColor", "()I", "setBackgroundColor", "(I)V", "negativeClickListener", "Landroid/view/View$OnClickListener;", "getNegativeClickListener", "()Landroid/view/View$OnClickListener;", "setNegativeClickListener", "(Landroid/view/View$OnClickListener;)V", "negativeText", "", "getNegativeText", "()Ljava/lang/String;", "setNegativeText", "(Ljava/lang/String;)V", "positiveClickListener", "getPositiveClickListener", "setPositiveClickListener", "positiveText", "getPositiveText", "setPositiveText", "tips", "getTips", "setTips", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/pudutech/recycle/robot/ui/widget/CFullScreenConfirmDialogFragment;", "setNegativeButton", "setPositiveButton", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Builder {
        private int backgroundColor;
        public View.OnClickListener negativeClickListener;
        private String negativeText;
        public View.OnClickListener positiveClickListener;
        public String positiveText;
        public String tips;

        public final String getTips() {
            String str = this.tips;
            if (str == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tips");
            }
            return str;
        }

        /* renamed from: setTips, reason: collision with other method in class */
        public final void m4471setTips(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.tips = str;
        }

        public final String getPositiveText() {
            String str = this.positiveText;
            if (str == null) {
                Intrinsics.throwUninitializedPropertyAccessException("positiveText");
            }
            return str;
        }

        public final void setPositiveText(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.positiveText = str;
        }

        public final View.OnClickListener getPositiveClickListener() {
            View.OnClickListener onClickListener = this.positiveClickListener;
            if (onClickListener == null) {
                Intrinsics.throwUninitializedPropertyAccessException("positiveClickListener");
            }
            return onClickListener;
        }

        public final void setPositiveClickListener(View.OnClickListener onClickListener) {
            Intrinsics.checkParameterIsNotNull(onClickListener, "<set-?>");
            this.positiveClickListener = onClickListener;
        }

        public final String getNegativeText() {
            return this.negativeText;
        }

        public final void setNegativeText(String str) {
            this.negativeText = str;
        }

        public final View.OnClickListener getNegativeClickListener() {
            View.OnClickListener onClickListener = this.negativeClickListener;
            if (onClickListener == null) {
                Intrinsics.throwUninitializedPropertyAccessException("negativeClickListener");
            }
            return onClickListener;
        }

        public final void setNegativeClickListener(View.OnClickListener onClickListener) {
            Intrinsics.checkParameterIsNotNull(onClickListener, "<set-?>");
            this.negativeClickListener = onClickListener;
        }

        public final int getBackgroundColor() {
            return this.backgroundColor;
        }

        /* renamed from: setBackgroundColor, reason: collision with other method in class */
        public final void m4470setBackgroundColor(int i) {
            this.backgroundColor = i;
        }

        public final Builder setTips(String tips) {
            Intrinsics.checkParameterIsNotNull(tips, "tips");
            this.tips = tips;
            return this;
        }

        public final Builder setPositiveButton(String positiveText, View.OnClickListener positiveClickListener) {
            Intrinsics.checkParameterIsNotNull(positiveText, "positiveText");
            Intrinsics.checkParameterIsNotNull(positiveClickListener, "positiveClickListener");
            this.positiveText = positiveText;
            this.positiveClickListener = positiveClickListener;
            return this;
        }

        public final Builder setNegativeButton(String negativeText, View.OnClickListener negativeClickListener) {
            Intrinsics.checkParameterIsNotNull(negativeText, "negativeText");
            Intrinsics.checkParameterIsNotNull(negativeClickListener, "negativeClickListener");
            this.negativeText = negativeText;
            this.negativeClickListener = negativeClickListener;
            return this;
        }

        public final Builder setBackgroundColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public final CFullScreenConfirmDialogFragment build() {
            return new CFullScreenConfirmDialogFragment(this);
        }
    }
}
