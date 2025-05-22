package com.pudutech.peanut.robot_ui.config;

import java.io.File;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* compiled from: UrlManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001e\u001a\u00020\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u000e\u0010\u0015\u001a\u00020\u0016X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u0018\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0019\u0010\u0006R\u0011\u0010\u001c\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0006R\u000e\u0010\u001d\u001a\u00020\u0016X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/config/UrlManager;", "", "()V", "IS_AI_SERVER_TEST", "", "getIS_AI_SERVER_TEST", "()Z", "setIS_AI_SERVER_TEST", "(Z)V", "IS_API_SEVER_TEST", "getIS_API_SEVER_TEST", "setIS_API_SEVER_TEST", "IS_IOT_SERVER_TEST", "getIS_IOT_SERVER_TEST", "setIS_IOT_SERVER_TEST", "IS_LEASE_SERVER_TEST", "getIS_LEASE_SERVER_TEST", "setIS_LEASE_SERVER_TEST", "IS_REPORT_SERVER_TEST", "getIS_REPORT_SERVER_TEST", "setIS_REPORT_SERVER_TEST", "UPDATE_HOST", "", "UPDATE_HOST_TEST", "hasTestFile", "getHasTestFile", "hasTestFile$delegate", "Lkotlin/Lazy;", "isTest", "testServerFile", "getUpdateHost", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class UrlManager {
    public static final UrlManager INSTANCE;
    private static boolean IS_AI_SERVER_TEST = false;
    private static boolean IS_API_SEVER_TEST = false;
    private static boolean IS_IOT_SERVER_TEST = false;
    private static boolean IS_LEASE_SERVER_TEST = false;
    private static boolean IS_REPORT_SERVER_TEST = false;
    private static final String UPDATE_HOST;
    private static final String UPDATE_HOST_TEST;

    /* renamed from: hasTestFile$delegate, reason: from kotlin metadata */
    private static final Lazy hasTestFile;
    private static final String testServerFile;

    private final boolean getHasTestFile() {
        return ((Boolean) hasTestFile.getValue()).booleanValue();
    }

    static {
        UrlManager urlManager = new UrlManager();
        INSTANCE = urlManager;
        testServerFile = testServerFile;
        hasTestFile = LazyKt.lazy(new Function0<Boolean>() { // from class: com.pudutech.peanut.robot_ui.config.UrlManager$hasTestFile$2
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Boolean invoke() {
                return Boolean.valueOf(invoke2());
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2() {
                String str;
                UrlManager urlManager2 = UrlManager.INSTANCE;
                str = UrlManager.testServerFile;
                return new File(str).exists();
            }
        });
        UPDATE_HOST = UPDATE_HOST;
        UPDATE_HOST_TEST = UPDATE_HOST_TEST;
        IS_API_SEVER_TEST = urlManager.isTest();
        IS_REPORT_SERVER_TEST = urlManager.isTest();
        IS_LEASE_SERVER_TEST = urlManager.isTest();
        IS_IOT_SERVER_TEST = urlManager.isTest();
        IS_AI_SERVER_TEST = urlManager.isTest();
    }

    private UrlManager() {
    }

    public final boolean isTest() {
        try {
            return getHasTestFile();
        } catch (Exception unused) {
            return false;
        }
    }

    public final String getUpdateHost() {
        return isTest() ? UPDATE_HOST_TEST : UPDATE_HOST;
    }

    public final boolean getIS_API_SEVER_TEST() {
        return IS_API_SEVER_TEST;
    }

    public final void setIS_API_SEVER_TEST(boolean z) {
        IS_API_SEVER_TEST = z;
    }

    public final boolean getIS_REPORT_SERVER_TEST() {
        return IS_REPORT_SERVER_TEST;
    }

    public final void setIS_REPORT_SERVER_TEST(boolean z) {
        IS_REPORT_SERVER_TEST = z;
    }

    public final boolean getIS_LEASE_SERVER_TEST() {
        return IS_LEASE_SERVER_TEST;
    }

    public final void setIS_LEASE_SERVER_TEST(boolean z) {
        IS_LEASE_SERVER_TEST = z;
    }

    public final boolean getIS_IOT_SERVER_TEST() {
        return IS_IOT_SERVER_TEST;
    }

    public final void setIS_IOT_SERVER_TEST(boolean z) {
        IS_IOT_SERVER_TEST = z;
    }

    public final boolean getIS_AI_SERVER_TEST() {
        return IS_AI_SERVER_TEST;
    }

    public final void setIS_AI_SERVER_TEST(boolean z) {
        IS_AI_SERVER_TEST = z;
    }
}
