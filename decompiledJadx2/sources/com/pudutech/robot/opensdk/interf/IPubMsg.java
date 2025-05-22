package com.pudutech.robot.opensdk.interf;

import androidx.core.app.NotificationCompat;
import com.amitshekhar.utils.Constants;
import com.pudutech.robot.opensdk.aliyun.bean.BaseMsgBean;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: IPubMsg.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\u001c\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0005H&J\b\u0010\n\u001a\u00020\u000bH&J\u001c\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003H&J\u0018\u0010\r\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003H&¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/interf/IPubMsg;", "Lcom/pudutech/robot/opensdk/interf/IBody;", "getMsgType", "", "getQos", "", "getReplyTopic", Constants.f1200PK, "dn", "getRetryCount", "getTimeout", "", "getTopic", "parseObj", "Lcom/pudutech/robot/opensdk/aliyun/bean/BaseMsgBean;", NotificationCompat.CATEGORY_MESSAGE, "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public interface IPubMsg extends IBody {
    String getMsgType();

    int getQos();

    String getReplyTopic(String pk, String dn);

    int getRetryCount();

    long getTimeout();

    String getTopic(String pk, String dn);

    BaseMsgBean<?> parseObj(String msg);

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: IPubMsg.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ String getTopic$default(IPubMsg iPubMsg, String str, String str2, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getTopic");
            }
            if ((i & 1) != 0) {
                str = "";
            }
            if ((i & 2) != 0) {
                str2 = "";
            }
            return iPubMsg.getTopic(str, str2);
        }

        public static /* synthetic */ String getReplyTopic$default(IPubMsg iPubMsg, String str, String str2, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getReplyTopic");
            }
            if ((i & 1) != 0) {
                str = "";
            }
            if ((i & 2) != 0) {
                str2 = "";
            }
            return iPubMsg.getReplyTopic(str, str2);
        }
    }
}
