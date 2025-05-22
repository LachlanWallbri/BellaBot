package com.pudutech.bumblebee.business.ims.listener;

import androidx.core.app.NotificationCompat;
import com.pudutech.bumblebee.business.protobuf.MessageProtobuf;
import kotlin.Metadata;

/* compiled from: IMsgSentStatusListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/listener/IMsgSentStatusListener;", "", "onSentFailed", "", NotificationCompat.CATEGORY_MESSAGE, "Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$Msg;", "reason", "", "onSentSucceed", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface IMsgSentStatusListener {
    void onSentFailed(MessageProtobuf.Msg msg, String reason);

    void onSentSucceed(MessageProtobuf.Msg msg);
}
