package com.pudutech.mqtt.component.client.callback;

import com.pudutech.mqtt.component.client.config.UnsubscribeState;
import java.util.List;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: UnsubscribeStateCallback.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H&¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/mqtt/component/client/callback/UnsubscribeStateCallback;", "", "callbackUnsubscribeState", "", "state", "Lcom/pudutech/mqtt/component/client/config/UnsubscribeState;", "topicList", "", "", "component_mqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface UnsubscribeStateCallback {
    void callbackUnsubscribeState(UnsubscribeState state, List<String> topicList);

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* compiled from: UnsubscribeStateCallback.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class DefaultImpls {
        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void callbackUnsubscribeState$default(UnsubscribeStateCallback unsubscribeStateCallback, UnsubscribeState unsubscribeState, List list, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: callbackUnsubscribeState");
            }
            if ((i & 2) != 0) {
                list = (List) null;
            }
            unsubscribeStateCallback.callbackUnsubscribeState(unsubscribeState, list);
        }
    }
}
