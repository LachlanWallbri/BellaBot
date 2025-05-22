package com.pudutech.bumblebee.business.ims.handler;

import androidx.core.app.NotificationCompat;
import com.pudutech.bumblebee.business.ims.event.CEvent;
import com.pudutech.bumblebee.business.ims.event.CEventCenter;
import com.pudutech.bumblebee.business.ims.event.Events;
import com.pudutech.bumblebee.business.protobuf.MessageProtobuf;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CancelCallMsgHandler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/handler/CancelCallMsgHandler;", "Lcom/pudutech/bumblebee/business/ims/handler/AbstractMsgHandler;", "()V", "action", "", NotificationCompat.CATEGORY_MESSAGE, "Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$Msg;", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CancelCallMsgHandler extends AbstractMsgHandler {
    @Override // com.pudutech.bumblebee.business.ims.handler.AbstractMsgHandler
    public void action(MessageProtobuf.Msg msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (msg.getResp()) {
            return;
        }
        CEvent cEvent = new CEvent();
        cEvent.setTopic(Events.EVENT_CANCEL_CALL);
        cEvent.setObj(msg);
        CEventCenter.dispatchEvent(cEvent);
    }
}
