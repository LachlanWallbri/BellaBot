package com.pudutech.bumblebee.business.ims.listener;

import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.ims.config.MsgType;
import com.pudutech.bumblebee.business.ims.handler.IMsgHandler;
import com.pudutech.bumblebee.business.ims.handler.MsgHandlerFactory;
import com.pudutech.bumblebee.business.protobuf.MessageProtobuf;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OnMsgReceivedListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/listener/OnMsgReceivedListener;", "Lcom/pudutech/bumblebee/business/ims/listener/IMsgReceivedListener;", "()V", "onMsgReceived", "", NotificationCompat.CATEGORY_MESSAGE, "Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$Msg;", "Companion", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class OnMsgReceivedListener implements IMsgReceivedListener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String TAG = "OnMsgReceivedListener";
    private static int state;

    /* compiled from: OnMsgReceivedListener.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/listener/OnMsgReceivedListener$Companion;", "", "()V", "TAG", "", "state", "", "getState", "()I", "setState", "(I)V", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getState() {
            return OnMsgReceivedListener.state;
        }

        public final void setState(int i) {
            OnMsgReceivedListener.state = i;
        }
    }

    @Override // com.pudutech.bumblebee.business.ims.listener.IMsgReceivedListener
    public void onMsgReceived(MessageProtobuf.Msg msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (state == 0) {
            Pdlog.m3273d(TAG, "onMsgReceived() msg = \n" + msg);
            MsgType typeOf = MsgType.INSTANCE.typeOf(msg.getMsgType());
            if (typeOf == MsgType.UNKNOWN) {
                Pdlog.m3277w(TAG, "MsgType is UNKNOWN, type = " + msg.getMsgType());
                return;
            }
            IMsgHandler findHandlerByMsgType = MsgHandlerFactory.INSTANCE.findHandlerByMsgType(typeOf);
            if (findHandlerByMsgType == null) {
                Pdlog.m3277w(TAG, "Message processing handler not found.");
                return;
            }
            try {
                Pdlog.m3273d(TAG, "Find the msgHandler: " + findHandlerByMsgType.getClass().getSimpleName());
                findHandlerByMsgType.process(msg);
            } catch (Exception e) {
                e.printStackTrace();
                Pdlog.m3274e(TAG, "Message processing failed");
            }
        }
    }
}
