package com.pudutech.bumblebee.robot_ui.config;

import com.pudutech.pd_network.bean.NetEnvironment;
import java.io.File;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UrlManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\f\u0010\bR\u001b\u0010\u000e\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\n\u001a\u0004\b\u000f\u0010\bR \u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00068F@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\bR\u000e\u0010\u0013\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R \u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00158F@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/config/UrlManager;", "", "()V", "devFile", "", "hasDevTestFile", "", "getHasDevTestFile", "()Z", "hasDevTestFile$delegate", "Lkotlin/Lazy;", "hasPreTestFile", "getHasPreTestFile", "hasPreTestFile$delegate", "hasTestFile", "getHasTestFile", "hasTestFile$delegate", "<set-?>", "isTest", "pretestServerFile", "testServerFile", "Lcom/pudutech/pd_network/bean/NetEnvironment;", "testType", "getTestType", "()Lcom/pudutech/pd_network/bean/NetEnvironment;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class UrlManager {
    private static boolean isTest = false;
    public static final UrlManager INSTANCE = new UrlManager();
    private static final String testServerFile = testServerFile;
    private static final String testServerFile = testServerFile;
    private static final String pretestServerFile = pretestServerFile;
    private static final String pretestServerFile = pretestServerFile;
    private static final String devFile = devFile;
    private static final String devFile = devFile;

    /* renamed from: hasTestFile$delegate, reason: from kotlin metadata */
    private static final Lazy hasTestFile = LazyKt.lazy(new Function0<Boolean>() { // from class: com.pudutech.bumblebee.robot_ui.config.UrlManager$hasTestFile$2
        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(invoke2());
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2() {
            String str;
            UrlManager urlManager = UrlManager.INSTANCE;
            str = UrlManager.testServerFile;
            return new File(str).exists();
        }
    });

    /* renamed from: hasPreTestFile$delegate, reason: from kotlin metadata */
    private static final Lazy hasPreTestFile = LazyKt.lazy(new Function0<Boolean>() { // from class: com.pudutech.bumblebee.robot_ui.config.UrlManager$hasPreTestFile$2
        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(invoke2());
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2() {
            String str;
            UrlManager urlManager = UrlManager.INSTANCE;
            str = UrlManager.pretestServerFile;
            return new File(str).exists();
        }
    });

    /* renamed from: hasDevTestFile$delegate, reason: from kotlin metadata */
    private static final Lazy hasDevTestFile = LazyKt.lazy(new Function0<Boolean>() { // from class: com.pudutech.bumblebee.robot_ui.config.UrlManager$hasDevTestFile$2
        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(invoke2());
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2() {
            String str;
            UrlManager urlManager = UrlManager.INSTANCE;
            str = UrlManager.devFile;
            return new File(str).exists();
        }
    });
    private static NetEnvironment testType = NetEnvironment.Product.INSTANCE;

    private final boolean getHasDevTestFile() {
        return ((Boolean) hasDevTestFile.getValue()).booleanValue();
    }

    private final boolean getHasPreTestFile() {
        return ((Boolean) hasPreTestFile.getValue()).booleanValue();
    }

    private final boolean getHasTestFile() {
        return ((Boolean) hasTestFile.getValue()).booleanValue();
    }

    private UrlManager() {
    }

    public final NetEnvironment getTestType() {
        if (getHasTestFile()) {
            return NetEnvironment.Test.INSTANCE;
        }
        if (getHasPreTestFile()) {
            return NetEnvironment.PreTest.INSTANCE;
        }
        if (getHasDevTestFile()) {
            return NetEnvironment.Dev.INSTANCE;
        }
        return NetEnvironment.Product.INSTANCE;
    }

    public final boolean isTest() {
        return !Intrinsics.areEqual(getTestType(), NetEnvironment.Product.INSTANCE);
    }
}
