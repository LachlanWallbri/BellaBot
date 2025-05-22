package com.pudutech.robot.opensdk.bean.resp;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: RespResultBody.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0013\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u0007R\u001c\u0010\u000b\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\u0002\u001a\u0004\b\r\u0010\u0007R\u001c\u0010\u000e\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0002\u001a\u0004\b\u0010\u0010\u0007R\u001c\u0010\u0011\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0002\u001a\u0004\b\u0013\u0010\u0007R\u001c\u0010\u0014\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0002\u001a\u0004\b\u0016\u0010\u0007¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/resp/ErrorCode;", "", "()V", "CALL_FAILED_DISABLE", "", "CALL_FAILED_DISABLE$annotations", "getCALL_FAILED_DISABLE", "()I", "CALL_FAILED_NO_TARGET", "CALL_FAILED_NO_TARGET$annotations", "getCALL_FAILED_NO_TARGET", "CALL_FAILED_TARGET_REPEAT", "CALL_FAILED_TARGET_REPEAT$annotations", "getCALL_FAILED_TARGET_REPEAT", "CUSTOM_CALL_CANCEL_TASK_ID_FAILED", "CUSTOM_CALL_CANCEL_TASK_ID_FAILED$annotations", "getCUSTOM_CALL_CANCEL_TASK_ID_FAILED", "CUSTOM_CALL_FAILED", "CUSTOM_CALL_FAILED$annotations", "getCUSTOM_CALL_FAILED", "CUSTOM_CALL_FAILED_NO_TARGET", "CUSTOM_CALL_FAILED_NO_TARGET$annotations", "getCUSTOM_CALL_FAILED_NO_TARGET", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ErrorCode {
    public static final ErrorCode INSTANCE = new ErrorCode();
    private static final int CALL_FAILED_NO_TARGET = 10001;
    private static final int CALL_FAILED_DISABLE = 10002;
    private static final int CALL_FAILED_TARGET_REPEAT = CALL_FAILED_TARGET_REPEAT;
    private static final int CALL_FAILED_TARGET_REPEAT = CALL_FAILED_TARGET_REPEAT;
    private static final int CUSTOM_CALL_FAILED_NO_TARGET = 21001;
    private static final int CUSTOM_CALL_FAILED = 21002;
    private static final int CUSTOM_CALL_CANCEL_TASK_ID_FAILED = 21003;

    @JvmStatic
    public static /* synthetic */ void CALL_FAILED_DISABLE$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void CALL_FAILED_NO_TARGET$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void CALL_FAILED_TARGET_REPEAT$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void CUSTOM_CALL_CANCEL_TASK_ID_FAILED$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void CUSTOM_CALL_FAILED$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void CUSTOM_CALL_FAILED_NO_TARGET$annotations() {
    }

    private ErrorCode() {
    }

    public static final int getCALL_FAILED_NO_TARGET() {
        return CALL_FAILED_NO_TARGET;
    }

    public static final int getCALL_FAILED_DISABLE() {
        return CALL_FAILED_DISABLE;
    }

    public static final int getCALL_FAILED_TARGET_REPEAT() {
        return CALL_FAILED_TARGET_REPEAT;
    }

    public static final int getCUSTOM_CALL_FAILED_NO_TARGET() {
        return CUSTOM_CALL_FAILED_NO_TARGET;
    }

    public static final int getCUSTOM_CALL_FAILED() {
        return CUSTOM_CALL_FAILED;
    }

    public static final int getCUSTOM_CALL_CANCEL_TASK_ID_FAILED() {
        return CUSTOM_CALL_CANCEL_TASK_ID_FAILED;
    }
}
