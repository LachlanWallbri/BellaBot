package com.pudutech.module_robot_selfcheck.p058ui;

import android.content.Context;
import android.view.View;
import com.google.android.material.badge.BadgeDrawable;
import com.pudutech.disinfect.baselib.base.BaseApp;
import com.pudutech.disinfect.baselib.base.BaseFloatView;
import com.pudutech.disinfect.baselib.widget.TopStatusBarLayout;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* compiled from: FloatStatusBar.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u000f\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/ui/FloatStatusBar;", "Lcom/pudutech/disinfect/baselib/base/BaseFloatView;", "()V", "mStatusBar", "Lcom/pudutech/disinfect/baselib/widget/TopStatusBarLayout;", "getMStatusBar", "()Lcom/pudutech/disinfect/baselib/widget/TopStatusBarLayout;", "mStatusBar$delegate", "Lkotlin/Lazy;", "getContentView", "Landroid/view/View;", "getLayoutId", "", "()Ljava/lang/Integer;", "onCreate", "", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class FloatStatusBar extends BaseFloatView {

    /* renamed from: mStatusBar$delegate, reason: from kotlin metadata */
    private final Lazy mStatusBar;

    @Override // com.pudutech.disinfect.baselib.base.BaseFloatView
    public Integer getLayoutId() {
        return null;
    }

    public final TopStatusBarLayout getMStatusBar() {
        return (TopStatusBarLayout) this.mStatusBar.getValue();
    }

    public FloatStatusBar() {
        super(BaseApp.INSTANCE.getINSTANCE());
        this.mStatusBar = LazyKt.lazy(new Function0<TopStatusBarLayout>() { // from class: com.pudutech.module_robot_selfcheck.ui.FloatStatusBar$mStatusBar$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TopStatusBarLayout invoke() {
                Context mContext;
                mContext = FloatStatusBar.this.getMContext();
                return new TopStatusBarLayout(mContext);
            }
        });
    }

    @Override // com.pudutech.disinfect.baselib.base.BaseFloatView
    public View getContentView() {
        return getMStatusBar();
    }

    @Override // com.pudutech.disinfect.baselib.base.BaseFloatView
    public void onCreate() {
        setGravity(BadgeDrawable.TOP_END, 0, 0);
        setSize(-2, -2);
    }
}
