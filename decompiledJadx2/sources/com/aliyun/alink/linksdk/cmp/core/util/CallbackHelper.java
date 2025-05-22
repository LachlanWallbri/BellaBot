package com.aliyun.alink.linksdk.cmp.core.util;

import com.aliyun.alink.linksdk.cmp.core.base.CmpError;
import com.aliyun.alink.linksdk.cmp.core.listener.IBaseListener;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CallbackHelper {
    public static void paramError(IBaseListener iBaseListener, String str) {
        if (iBaseListener == null) {
            return;
        }
        CmpError PARAMS_ERROR = CmpError.PARAMS_ERROR();
        PARAMS_ERROR.setSubMsg(str);
        iBaseListener.onFailure(PARAMS_ERROR);
    }
}
