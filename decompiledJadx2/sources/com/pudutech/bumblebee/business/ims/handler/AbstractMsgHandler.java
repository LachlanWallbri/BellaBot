package com.pudutech.bumblebee.business.ims.handler;

import androidx.core.app.NotificationCompat;
import com.pudutech.bumblebee.business.protobuf.MessageProtobuf;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AbstractMsgHandler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/handler/AbstractMsgHandler;", "Lcom/pudutech/bumblebee/business/ims/handler/IMsgHandler;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "action", "", NotificationCompat.CATEGORY_MESSAGE, "Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$Msg;", "process", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public abstract class AbstractMsgHandler implements IMsgHandler {
    private final String TAG = getClass().getSimpleName();

    public abstract void action(MessageProtobuf.Msg msg);

    public final String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.bumblebee.business.ims.handler.IMsgHandler
    public void process(MessageProtobuf.Msg msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        action(msg);
    }
}
