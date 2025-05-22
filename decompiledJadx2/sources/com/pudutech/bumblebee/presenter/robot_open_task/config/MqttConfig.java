package com.pudutech.bumblebee.presenter.robot_open_task.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.annotation.AnnotationRetention;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MqttConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\b\u001b\bÆ\u0002\u0018\u00002\u00020\u0001:\u000212B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u001d\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00040\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u0018X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0018X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u001d\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00040\u0017¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u001d\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00040\u0017¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001aR\u000e\u0010$\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0018X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0018X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0018X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0018X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0018X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0018X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010-\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0011\"\u0004\b/\u00100¨\u00063"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/config/MqttConfig;", "", "()V", "CALLMOVETASKSTATEFRAGMENT", "", "HOME_FRAGMENT", "IDLEFACE_FRAGMENT", "INVALID_POINT", "", "IN_HUMAN_COMPUTER_INTERACTION", "IN_VOICE_CONVERSATION", "IS_NOT_WORK", "LASERLOCATION_FRAGMENT", "LOW_BATTERY", "POINT_IS_EMPTY", "PRODUCT", "getPRODUCT", "()Ljava/lang/String;", "ROBOTCALLFRAGMENT", "SELFCHECK_FAIL_FRAGMENT", "SELFCHECK_FRAGMENT", "STOP_BUTTON_WAS_PRESSED", "ScheduleMap", "", "", "getScheduleMap", "()Ljava/util/Map;", "ScheduleStatusNo", "ScheduleStatusYes", "TAKE_LINE_CHARGING", "TO_LOCATE_FAILURE", "WORKING", "WorkAbilityMap", "getWorkAbilityMap", "WorkMap", "getWorkMap", "WorkResultStatusCancel", "WorkResultStatusComplete", "WorkResultStatusFailure", "WorkStatusError", "WorkStatusFinish", "WorkStatusMoveCancel", "WorkStatusNo", "WorkStatusPause", "WorkStatusYes", "mac", "getMac", "setMac", "(Ljava/lang/String;)V", "WorkResultStatus", "WorkStatus", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MqttConfig {
    public static final String CALLMOVETASKSTATEFRAGMENT = "CallMoveTaskStateFragment";
    public static final String HOME_FRAGMENT = "HomeFragment";
    public static final String IDLEFACE_FRAGMENT = "IdleFaceFragment";
    public static final long IS_NOT_WORK = 60001;
    public static final String LASERLOCATION_FRAGMENT = "LaserLocationFragment";
    public static final String ROBOTCALLFRAGMENT = "RobotCallFragment";
    public static final String SELFCHECK_FAIL_FRAGMENT = "SelfCheckFailFragment";
    public static final String SELFCHECK_FRAGMENT = "SelfCheckFragment";
    public static final int ScheduleStatusNo = -1;
    public static final int ScheduleStatusYes = 1;
    public static final long WorkResultStatusCancel = 0;
    public static final long WorkResultStatusComplete = 2;
    public static final long WorkResultStatusFailure = 1;
    public static final int WorkStatusError = 2;
    public static final int WorkStatusFinish = 3;
    public static final int WorkStatusMoveCancel = 5;
    public static final int WorkStatusNo = -1;
    public static final int WorkStatusPause = 4;
    public static final int WorkStatusYes = 1;
    public static final MqttConfig INSTANCE = new MqttConfig();
    private static final String PRODUCT = PRODUCT;
    private static final String PRODUCT = PRODUCT;
    private static String mac = "";
    private static final Map<Integer, String> ScheduleMap = MapsKt.mutableMapOf(TuplesKt.m3968to(1, "可调度"), TuplesKt.m3968to(-1, "不可调度"));
    private static final Map<Integer, String> WorkMap = MapsKt.mutableMapOf(TuplesKt.m3968to(1, "工作中"), TuplesKt.m3968to(-1, "空闲"), TuplesKt.m3968to(2, "异常"), TuplesKt.m3968to(3, "完成任务"), TuplesKt.m3968to(4, "暂停"), TuplesKt.m3968to(5, "取消任务"));
    public static final long POINT_IS_EMPTY = 50000;
    public static final long IN_HUMAN_COMPUTER_INTERACTION = 50001;
    public static final long TAKE_LINE_CHARGING = 50002;
    public static final long LOW_BATTERY = 50003;
    public static final long WORKING = 50004;
    public static final long INVALID_POINT = 50005;
    public static final long STOP_BUTTON_WAS_PRESSED = 50006;
    public static final long TO_LOCATE_FAILURE = 50007;
    public static final long IN_VOICE_CONVERSATION = 50008;
    private static final Map<Long, String> WorkAbilityMap = MapsKt.mutableMapOf(TuplesKt.m3968to(Long.valueOf(POINT_IS_EMPTY), "目标点是空的"), TuplesKt.m3968to(Long.valueOf(IN_HUMAN_COMPUTER_INTERACTION), "人机交互中"), TuplesKt.m3968to(Long.valueOf(TAKE_LINE_CHARGING), "机器人带线充电，不能执行任务"), TuplesKt.m3968to(Long.valueOf(LOW_BATTERY), "电量不足10%，不能执行任务"), TuplesKt.m3968to(Long.valueOf(WORKING), "机器人处于工作状态，不能执行任务"), TuplesKt.m3968to(Long.valueOf(INVALID_POINT), "目标点无效"), TuplesKt.m3968to(Long.valueOf(STOP_BUTTON_WAS_PRESSED), "机器人按下了急停按钮,请恢复"), TuplesKt.m3968to(Long.valueOf(TO_LOCATE_FAILURE), "机器人当前定位失败，请重新定位"), TuplesKt.m3968to(Long.valueOf(IN_VOICE_CONVERSATION), "人机语音对话中"));

    /* compiled from: MqttConfig.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/config/MqttConfig$WorkResultStatus;", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    @Retention(RetentionPolicy.SOURCE)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    /* loaded from: classes4.dex */
    public @interface WorkResultStatus {
    }

    /* compiled from: MqttConfig.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/config/MqttConfig$WorkStatus;", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    @Retention(RetentionPolicy.SOURCE)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    /* loaded from: classes4.dex */
    public @interface WorkStatus {
    }

    private MqttConfig() {
    }

    public final String getPRODUCT() {
        return PRODUCT;
    }

    public final String getMac() {
        return mac;
    }

    public final void setMac(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        mac = str;
    }

    public final Map<Integer, String> getScheduleMap() {
        return ScheduleMap;
    }

    public final Map<Integer, String> getWorkMap() {
        return WorkMap;
    }

    public final Map<Long, String> getWorkAbilityMap() {
        return WorkAbilityMap;
    }
}
