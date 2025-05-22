package com.pudutech.robot.opensdk.aliyun.topic;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: IotSubSeviceTopic.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\b\u0010\b\u001a\u00020\u0003H\u0016J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0003H\u0016R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082D¢\u0006\u0002\n\u0000¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/topic/IotSubSeviceTopic;", "Lcom/pudutech/robot/opensdk/aliyun/topic/BaseSubTopic;", "productKey", "", "deviceName", "(Ljava/lang/String;Ljava/lang/String;)V", "TAG", "getRole", "getTopic", "onMsg", "", "msgJson", "msgType", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class IotSubSeviceTopic extends BaseSubTopic {
    private final String TAG;

    @Override // com.pudutech.robot.opensdk.aliyun.topic.BaseSubTopic
    public String getRole() {
        return "";
    }

    @Override // com.pudutech.robot.opensdk.aliyun.topic.BaseSubTopic
    public void onMsg(String msgJson, String msgType) {
        Intrinsics.checkParameterIsNotNull(msgJson, "msgJson");
        Intrinsics.checkParameterIsNotNull(msgType, "msgType");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IotSubSeviceTopic(String productKey, String deviceName) {
        super(productKey, deviceName);
        Intrinsics.checkParameterIsNotNull(productKey, "productKey");
        Intrinsics.checkParameterIsNotNull(deviceName, "deviceName");
        this.TAG = "IotSubSeviceTopic";
    }

    @Override // com.pudutech.robot.opensdk.interf.ISubTopic
    public String getTopic() {
        return Constant.INSTANCE.subServiceTopicTemplate(getProductKey(), getDeviceName());
    }
}
