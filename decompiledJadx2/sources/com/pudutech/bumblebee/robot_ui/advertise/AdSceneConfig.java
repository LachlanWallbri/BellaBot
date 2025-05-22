package com.pudutech.bumblebee.robot_ui.advertise;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import kotlin.Metadata;

/* compiled from: AdConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\t\u001a\u00020\u0003H\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007j\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/advertise/AdSceneConfig;", "", "id", "", TmpConstant.SERVICE_DESC, "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getDesc", "()Ljava/lang/String;", "getId", "toString", "DELIVER_FOOD_MODE", "USHER_MODE", "BIRTHDAY_DELIVER_MODE", "BACK_DISK_MODE", "MID_MEAL_MODE", "SOLICITING_PASSENGERS_MODE", "CRUISE_MODE", "CRUISE_MODE_PAUSE", "CRUISE_MODE_STOP", "GENERALLY_MODE", "UN_KNOWN_MODE", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public enum AdSceneConfig {
    DELIVER_FOOD_MODE("deliver_food_mode", "送餐模式-送餐到达"),
    USHER_MODE("guide_mode", "领位模式-领位到达"),
    BIRTHDAY_DELIVER_MODE("birthday_mode", "生日模式-送餐到达"),
    BACK_DISK_MODE("back_disk_mode", "回盘模式-停留点"),
    MID_MEAL_MODE("mid_meal_mode", "餐中模式-服务到达"),
    SOLICITING_PASSENGERS_MODE("soliciting_passengers_mode", "揽客模式-揽客中"),
    CRUISE_MODE("cruise_mode", "巡航模式-巡航中"),
    CRUISE_MODE_PAUSE("cruise_pause_mode", "巡航模式-巡航暂停"),
    CRUISE_MODE_STOP("cruise_stop_mode", "巡航模式-逗留点"),
    GENERALLY_MODE("generally_mode", "通用模式"),
    UN_KNOWN_MODE("un_known_mode", "未知模式");

    private final String desc;
    private final String id;

    AdSceneConfig(String str, String str2) {
        this.id = str;
        this.desc = str2;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final String getId() {
        return this.id;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "AdSceneConfig(id='" + this.id + "', desc='" + this.desc + "')";
    }
}
