package com.pudutech.schedulerlib.p065ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.pudutech.schedulerlib.C5725R;
import com.pudutech.schedulerlib.p065ui.ChannelListAdapter;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: ChannelChoiceBottomSheetDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000[\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0018\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\nB'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011H\u0002J\u0010\u0010\u001c\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u001eH\u0002J\u0012\u0010 \u001a\u00020\u001e2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0014J\b\u0010#\u001a\u00020\u001eH\u0002J\b\u0010$\u001a\u00020\u001eH\u0002J\b\u0010%\u001a\u00020\u001eH\u0002R\u0016\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0014j\b\u0012\u0004\u0012\u00020\b`\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0019R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, m3961d2 = {"Lcom/pudutech/schedulerlib/ui/ChannelChoiceBottomSheetDialog;", "Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "context", "Landroid/content/Context;", "callback", "Lcom/pudutech/schedulerlib/ui/ChannelListAdapter$ButtonTouchCallback;", "(Landroid/content/Context;Lcom/pudutech/schedulerlib/ui/ChannelListAdapter$ButtonTouchCallback;)V", "peekHeight", "", "maxHeight", "(Landroid/content/Context;IILcom/pudutech/schedulerlib/ui/ChannelListAdapter$ButtonTouchCallback;)V", "cancelable", "", "onCancelListener", "Landroid/content/DialogInterface$OnCancelListener;", "(Landroid/content/Context;ZLandroid/content/DialogInterface$OnCancelListener;Lcom/pudutech/schedulerlib/ui/ChannelListAdapter$ButtonTouchCallback;)V", "bottomSheetBehavior", "Lcom/google/android/material/bottomsheet/BottomSheetBehavior;", "Landroid/view/View;", "channelList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "isCreated", "mBottomSheetCallback", "com/pudutech/schedulerlib/ui/ChannelChoiceBottomSheetDialog$mBottomSheetCallback$1", "Lcom/pudutech/schedulerlib/ui/ChannelChoiceBottomSheetDialog$mBottomSheetCallback$1;", "touchListener", "getBottomSheetBehavior", "getStatusBarHeight", "initData", "", "initWidget", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setBottomSheetCallback", "setMaxHeight", "setPeekHeight", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class ChannelChoiceBottomSheetDialog extends BottomSheetDialog {
    private BottomSheetBehavior<View> bottomSheetBehavior;
    private ArrayList<Integer> channelList;
    private boolean isCreated;
    private final ChannelChoiceBottomSheetDialog$mBottomSheetCallback$1 mBottomSheetCallback;
    private int maxHeight;
    private int peekHeight;
    private ChannelListAdapter.ButtonTouchCallback touchListener;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v8, types: [com.pudutech.schedulerlib.ui.ChannelChoiceBottomSheetDialog$mBottomSheetCallback$1] */
    public ChannelChoiceBottomSheetDialog(Context context, ChannelListAdapter.ButtonTouchCallback callback) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Resources system = Resources.getSystem();
        Intrinsics.checkExpressionValueIsNotNull(system, "Resources.getSystem()");
        this.peekHeight = (int) ((system.getDisplayMetrics().density * 220) + 0.5f);
        this.channelList = new ArrayList<>();
        this.mBottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() { // from class: com.pudutech.schedulerlib.ui.ChannelChoiceBottomSheetDialog$mBottomSheetCallback$1
            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onSlide(View bottomSheet, float slideOffset) {
                Intrinsics.checkParameterIsNotNull(bottomSheet, "bottomSheet");
            }

            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onStateChanged(View bottomSheet, int newState) {
                Intrinsics.checkParameterIsNotNull(bottomSheet, "bottomSheet");
                if (newState == 5) {
                    ChannelChoiceBottomSheetDialog.this.dismiss();
                    BottomSheetBehavior from = BottomSheetBehavior.from(bottomSheet);
                    Intrinsics.checkExpressionValueIsNotNull(from, "BottomSheetBehavior.from(bottomSheet)");
                    from.setState(4);
                }
            }
        };
        this.touchListener = callback;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ChannelChoiceBottomSheetDialog(Context context, int i, int i2, ChannelListAdapter.ButtonTouchCallback callback) {
        this(context, callback);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.peekHeight = i;
        this.maxHeight = i2;
        this.touchListener = callback;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v8, types: [com.pudutech.schedulerlib.ui.ChannelChoiceBottomSheetDialog$mBottomSheetCallback$1] */
    public ChannelChoiceBottomSheetDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener, ChannelListAdapter.ButtonTouchCallback callback) {
        super(context, z, onCancelListener);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(onCancelListener, "onCancelListener");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Resources system = Resources.getSystem();
        Intrinsics.checkExpressionValueIsNotNull(system, "Resources.getSystem()");
        this.peekHeight = (int) ((system.getDisplayMetrics().density * 220) + 0.5f);
        this.channelList = new ArrayList<>();
        this.mBottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() { // from class: com.pudutech.schedulerlib.ui.ChannelChoiceBottomSheetDialog$mBottomSheetCallback$1
            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onSlide(View bottomSheet, float slideOffset) {
                Intrinsics.checkParameterIsNotNull(bottomSheet, "bottomSheet");
            }

            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onStateChanged(View bottomSheet, int newState) {
                Intrinsics.checkParameterIsNotNull(bottomSheet, "bottomSheet");
                if (newState == 5) {
                    ChannelChoiceBottomSheetDialog.this.dismiss();
                    BottomSheetBehavior from = BottomSheetBehavior.from(bottomSheet);
                    Intrinsics.checkExpressionValueIsNotNull(from, "BottomSheetBehavior.from(bottomSheet)");
                    from.setState(4);
                }
            }
        };
        this.touchListener = callback;
    }

    @Override // com.google.android.material.bottomsheet.BottomSheetDialog, androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.isCreated = true;
        setContentView(LayoutInflater.from(getContext()).inflate(C5725R.layout.schedulerlib_dialog_channel_choice, (ViewGroup) null));
        setPeekHeight();
        setMaxHeight();
        setBottomSheetCallback();
        initData();
        initWidget();
    }

    private final void initData() {
        for (int i = 2; i <= 13; i++) {
            this.channelList.add(Integer.valueOf(i));
        }
    }

    private final void initWidget() {
        RecyclerView recyclerView = (RecyclerView) findViewById(C5725R.id.recyclerView);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "recyclerView");
        recyclerView.setItemAnimator((RecyclerView.ItemAnimator) null);
        RecyclerView recyclerView2 = (RecyclerView) findViewById(C5725R.id.recyclerView);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "recyclerView");
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        ChannelListAdapter channelListAdapter = new ChannelListAdapter(context, this.channelList, this.touchListener);
        RecyclerView recyclerView3 = (RecyclerView) findViewById(C5725R.id.recyclerView);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView3, "recyclerView");
        recyclerView3.setAdapter(channelListAdapter);
    }

    private final void setPeekHeight() {
        BottomSheetBehavior<View> bottomSheetBehavior;
        if (this.peekHeight < 0 || getBottomSheetBehavior() == null || (bottomSheetBehavior = this.bottomSheetBehavior) == null) {
            return;
        }
        bottomSheetBehavior.setPeekHeight(this.peekHeight);
    }

    private final void setMaxHeight() {
        if (this.maxHeight <= 0) {
            Resources system = Resources.getSystem();
            Intrinsics.checkExpressionValueIsNotNull(system, "Resources.getSystem()");
            this.maxHeight = system.getDisplayMetrics().heightPixels;
        }
        int i = (this.maxHeight / 4) * 3;
        Window window = getWindow();
        if (window != null) {
            window.setLayout(-1, i);
        }
        Window window2 = getWindow();
        if (window2 != null) {
            window2.setGravity(80);
        }
    }

    private final BottomSheetBehavior<View> getBottomSheetBehavior() {
        View findViewById;
        BottomSheetBehavior<View> bottomSheetBehavior = this.bottomSheetBehavior;
        if (bottomSheetBehavior != null) {
            return bottomSheetBehavior;
        }
        Window window = getWindow();
        if (window == null || (findViewById = window.findViewById(C5725R.id.design_bottom_sheet)) == null) {
            return null;
        }
        if (findViewById == null) {
            Intrinsics.throwNpe();
        }
        BottomSheetBehavior<View> from = BottomSheetBehavior.from(findViewById);
        this.bottomSheetBehavior = from;
        return from;
    }

    private final void setBottomSheetCallback() {
        BottomSheetBehavior<View> bottomSheetBehavior;
        if (getBottomSheetBehavior() == null || (bottomSheetBehavior = this.bottomSheetBehavior) == null) {
            return;
        }
        bottomSheetBehavior.setBottomSheetCallback(this.mBottomSheetCallback);
    }

    private final int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }
}
