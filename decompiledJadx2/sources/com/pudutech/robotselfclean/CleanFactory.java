package com.pudutech.robotselfclean;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CleanFactory.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/robotselfclean/CleanFactory;", "", "()V", "createClean", "Lcom/pudutech/robotselfclean/ICleanInterface;", "type", "Lcom/pudutech/robotselfclean/CleanFactory$CleanType;", "CleanType", "RobotSelfClean_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class CleanFactory {
    public static final CleanFactory INSTANCE = new CleanFactory();

    /* compiled from: CleanFactory.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0003\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/robotselfclean/CleanFactory$CleanType;", "", "(Ljava/lang/String;I)V", "DEF_CLEAN_TYPE", "RobotSelfClean_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public enum CleanType {
        DEF_CLEAN_TYPE
    }

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[CleanType.values().length];

        static {
            $EnumSwitchMapping$0[CleanType.DEF_CLEAN_TYPE.ordinal()] = 1;
        }
    }

    private CleanFactory() {
    }

    public final ICleanInterface createClean(CleanType type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        if (WhenMappings.$EnumSwitchMapping$0[type.ordinal()] == 1) {
            return new DefCleanImpl();
        }
        throw new NoWhenBranchMatchedException();
    }
}
