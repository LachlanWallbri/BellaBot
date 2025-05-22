package com.pudutech.lib_update;

import com.pudutech.base.Pdlog;
import com.pudutech.lib_update.base.net.HttpResult;
import com.pudutech.lib_update.module.model.VerionResult;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import kotlin.Metadata;

/* compiled from: UpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001J\u0018\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0016¨\u0006\u0007"}, m3961d2 = {"com/pudutech/lib_update/UpdateManager$checkSoftWareVersion$1", "Lio/reactivex/functions/Consumer;", "Lcom/pudutech/lib_update/base/net/HttpResult;", "Lcom/pudutech/lib_update/module/model/VerionResult;", "accept", "", "t", "lib_update_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class UpdateManager$checkSoftWareVersion$1 implements Consumer<HttpResult<VerionResult>> {
    final /* synthetic */ Function $callBack;
    final /* synthetic */ Function $onError;

    UpdateManager$checkSoftWareVersion$1(Function function, Function function2) {
        this.$callBack = function;
        this.$onError = function2;
    }

    @Override // io.reactivex.functions.Consumer
    public void accept(HttpResult<VerionResult> t) {
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("------->");
        sb.append(t != null ? t.getData() : null);
        objArr[0] = sb.toString();
        Pdlog.m3274e("UpdateManager", objArr);
        if ((t != null ? t.getData() : null) != null) {
            Function function = this.$callBack;
            if (function != null) {
                return;
            }
            return;
        }
        Function function2 = this.$onError;
        if (function2 != null) {
        }
    }
}
