package com.pudutech.robot.opensdk.aliyun.topic;

import android.os.SystemClock;
import com.google.gson.reflect.TypeToken;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.robot.opensdk.aliyun.IotShadow;
import com.pudutech.robot.opensdk.aliyun.bean.BaseMsgBean;
import com.pudutech.robot.opensdk.bean.OutsourcingBeeper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: IotSubBeeperTopic.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0003H\u0016J\b\u0010\u000e\u001a\u00020\u0003H\u0016J\b\u0010\u000f\u001a\u00020\u0003H\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0003H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u0003X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/topic/IotSubBeeperTopic;", "Lcom/pudutech/robot/opensdk/aliyun/topic/BaseIotDirectTopic;", "productKey", "", "deviceName", "(Ljava/lang/String;Ljava/lang/String;)V", "MAX_TIME", "", "TAG", "getTAG", "()Ljava/lang/String;", "checkSource", "", MapElement.Source.SOURCE, "getRole", "getTopic", "onOtherMsg", "", "msgJson", "msgType", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class IotSubBeeperTopic extends BaseIotDirectTopic {
    private final int MAX_TIME;
    private final String TAG;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IotSubBeeperTopic(String productKey, String deviceName) {
        super(productKey, deviceName);
        Intrinsics.checkParameterIsNotNull(productKey, "productKey");
        Intrinsics.checkParameterIsNotNull(deviceName, "deviceName");
        this.MAX_TIME = 7200000;
        this.TAG = "IotSubBeeperTopic";
    }

    @Override // com.pudutech.robot.opensdk.aliyun.topic.BaseIotDirectTopic
    public void onOtherMsg(String msgJson, String msgType) {
        Intrinsics.checkParameterIsNotNull(msgJson, "msgJson");
        Intrinsics.checkParameterIsNotNull(msgType, "msgType");
        Pdlog.m3273d(getTAG(), "IotSubBeeperTopic: onOtherMsg=" + msgType + ' ' + msgJson);
        if (msgType.hashCode() == 946163068 && msgType.equals(Constant.MSG_TYPE_BROADCAST_BEEPER)) {
            Object fromJson = getGson().fromJson(msgJson, new TypeToken<BaseMsgBean<OutsourcingBeeper>>() { // from class: com.pudutech.robot.opensdk.aliyun.topic.IotSubBeeperTopic$onOtherMsg$1
            }.getType());
            Intrinsics.checkExpressionValueIsNotNull(fromJson, "gson.fromJson<BaseMsgBea…urcingBeeper>>() {}.type)");
            notifyEvent((BaseMsgBean) fromJson);
        }
    }

    @Override // com.pudutech.robot.opensdk.aliyun.topic.BaseIotDirectTopic
    public boolean checkSource(String source) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        Long l = IotShadow.INSTANCE.getWhiteDevices().get(source);
        if (l == null) {
            l = 0L;
        }
        Intrinsics.checkExpressionValueIsNotNull(l, "IotShadow.whiteDevices[source] ?: 0L");
        long longValue = l.longValue();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.MAX_TIME + longValue <= elapsedRealtime || longValue == 0) {
            return false;
        }
        Pdlog.m3273d(getTAG(), "onMsg error msg , white timeout :  time = " + longValue + " , new = " + elapsedRealtime);
        return true;
    }

    @Override // com.pudutech.robot.opensdk.aliyun.topic.BaseSubTopic
    public String getRole() {
        return Constant.INSTANCE.getROLE_BEEPER();
    }

    @Override // com.pudutech.robot.opensdk.interf.ISubTopic
    public String getTopic() {
        return Constant.INSTANCE.subBeeperTopicTemplate(getProductKey(), getDeviceName());
    }

    @Override // com.pudutech.robot.opensdk.aliyun.topic.BaseIotDirectTopic
    public String getTAG() {
        return this.TAG;
    }
}
