package com.pudutech.disinfect.baselib.callback;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: StatusBarControl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/callback/StatusBarControl;", "", "getStatusBarTheme", "", "isShowStatusBar", "", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public interface StatusBarControl {

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* compiled from: StatusBarControl.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        public static int getStatusBarTheme(StatusBarControl statusBarControl) {
            return 1;
        }

        public static boolean isShowStatusBar(StatusBarControl statusBarControl) {
            return false;
        }
    }

    int getStatusBarTheme();

    boolean isShowStatusBar();
}
