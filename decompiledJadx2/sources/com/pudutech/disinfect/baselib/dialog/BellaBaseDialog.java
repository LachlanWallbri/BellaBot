package com.pudutech.disinfect.baselib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import com.pudutech.disinfect.baselib.C4429R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BellaBaseDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u00020\u0006H$J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\u0012\u0010\u0010\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u000e\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u00020\u000eH\u0016R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0004¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/dialog/BellaBaseDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "themeResId", "", "(Landroid/content/Context;I)V", "mContext", "getMContext", "()Landroid/content/Context;", "setMContext", "getLayout", "init", "", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setAllCancel", "isCancel", "", "setData", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public abstract class BellaBaseDialog extends Dialog {
    private Context mContext;

    protected abstract int getLayout();

    public void initView() {
    }

    public void setData() {
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final void setMContext(Context context) {
        this.mContext = context;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BellaBaseDialog(Context context) {
        this(context, C4429R.style.Plane_Dialog);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BellaBaseDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        init(context);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        setData();
    }

    private final void init(Context context) {
        this.mContext = context;
    }

    public final void setAllCancel(boolean isCancel) {
        setCancelable(isCancel);
        setCanceledOnTouchOutside(isCancel);
    }
}
