package com.pudutech.disinfect.baselib.base;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: FloatViewControlDecoration.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/base/FloatViewControlDecoration;", "Lcom/pudutech/disinfect/baselib/base/CommonFloatViewControlManager;", "()V", "mFloatViewControl", "getMFloatViewControl", "()Lcom/pudutech/disinfect/baselib/base/CommonFloatViewControlManager;", "setMFloatViewControl", "(Lcom/pudutech/disinfect/baselib/base/CommonFloatViewControlManager;)V", "dismissFloatView", "", "showFloatView", "isShow", "", "theme", "", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class FloatViewControlDecoration implements CommonFloatViewControlManager {
    public static final FloatViewControlDecoration INSTANCE = new FloatViewControlDecoration();
    private static CommonFloatViewControlManager mFloatViewControl;

    private FloatViewControlDecoration() {
    }

    public final CommonFloatViewControlManager getMFloatViewControl() {
        return mFloatViewControl;
    }

    public final void setMFloatViewControl(CommonFloatViewControlManager commonFloatViewControlManager) {
        mFloatViewControl = commonFloatViewControlManager;
    }

    @Override // com.pudutech.disinfect.baselib.base.CommonFloatViewControlManager
    public void showFloatView(boolean isShow, int theme) {
        CommonFloatViewControlManager commonFloatViewControlManager = mFloatViewControl;
        if (commonFloatViewControlManager != null) {
            commonFloatViewControlManager.showFloatView(isShow, theme);
        }
    }

    @Override // com.pudutech.disinfect.baselib.base.CommonFloatViewControlManager
    public void dismissFloatView() {
        CommonFloatViewControlManager commonFloatViewControlManager = mFloatViewControl;
        if (commonFloatViewControlManager != null) {
            commonFloatViewControlManager.dismissFloatView();
        }
    }
}
