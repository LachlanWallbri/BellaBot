package com.pudutech.bumblebee.robot_ui.advertise;

import com.pudutech.disinfect.baselib.util.MMKVManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: AdConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R \u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u00128F@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R$\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00128F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0014\"\u0004\b\u0017\u0010\u0018R$\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00128F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0019\u0010\u0014\"\u0004\b\u001a\u0010\u0018R$\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00128F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u0018R$\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00128F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001d\u0010\u0014\"\u0004\b\u001e\u0010\u0018¨\u0006 "}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/advertise/AdverConfig;", "", "()V", "ADVER_DATA_LOCAL", "", "ADVER_TYPE_EXIST", "", "ADVER_TYPE_NOT_EXIST", "ADVER_TYPE_PIC", "ADVER_TYPE_VIDEO", "KEY_CRUISE_ADVER_PIC", "KEY_CRUISE_ADVER_VIDEO", "KEY_DOOR_WELCOME_PIC", "KEY_DOOR_WELCOME_VIDEO", "VIDEO_VOLUME_0_3", "", "VIDEO_VOLUME_1_0", "<set-?>", "", "isCruiseAdver", "()Z", ES6Iterator.VALUE_PROPERTY, "isCruiseAdverPic", "setCruiseAdverPic", "(Z)V", "isCruiseAdverVideo", "setCruiseAdverVideo", "isDoorWelcomeAdverPic", "setDoorWelcomeAdverPic", "isDoorWelcomeAdverVideo", "setDoorWelcomeAdverVideo", "TaskType", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class AdverConfig {
    public static final String ADVER_DATA_LOCAL = "key_adver_data";
    public static final int ADVER_TYPE_EXIST = -1;
    public static final int ADVER_TYPE_NOT_EXIST = -2;
    public static final int ADVER_TYPE_PIC = 0;
    public static final int ADVER_TYPE_VIDEO = 1;
    public static final AdverConfig INSTANCE = new AdverConfig();
    private static final String KEY_CRUISE_ADVER_PIC = "key_cruise_adver_pic";
    private static final String KEY_CRUISE_ADVER_VIDEO = "key_cruise_adver_video";
    private static final String KEY_DOOR_WELCOME_PIC = "key_door_welcom_pic";
    private static final String KEY_DOOR_WELCOME_VIDEO = "key_door_welcom_video";
    public static final float VIDEO_VOLUME_0_3 = 0.3f;
    public static final float VIDEO_VOLUME_1_0 = 1.0f;
    private static boolean isCruiseAdver;

    private AdverConfig() {
    }

    /* compiled from: AdConfig.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0005\u0006\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016\u0082\u0001\u0003\b\t\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/advertise/AdverConfig$TaskType;", "", "()V", "toString", "", "TaskContinue", "TaskFinish", "TaskScreen", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdverConfig$TaskType$TaskScreen;", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdverConfig$TaskType$TaskContinue;", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdverConfig$TaskType$TaskFinish;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static abstract class TaskType {

        /* compiled from: AdConfig.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/advertise/AdverConfig$TaskType$TaskScreen;", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdverConfig$TaskType;", "()V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes2.dex */
        public static final class TaskScreen extends TaskType {
            public static final TaskScreen INSTANCE = new TaskScreen();

            private TaskScreen() {
                super(null);
            }
        }

        private TaskType() {
        }

        public /* synthetic */ TaskType(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* compiled from: AdConfig.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/advertise/AdverConfig$TaskType$TaskContinue;", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdverConfig$TaskType;", "()V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes2.dex */
        public static final class TaskContinue extends TaskType {
            public static final TaskContinue INSTANCE = new TaskContinue();

            private TaskContinue() {
                super(null);
            }
        }

        /* compiled from: AdConfig.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/advertise/AdverConfig$TaskType$TaskFinish;", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdverConfig$TaskType;", "()V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes2.dex */
        public static final class TaskFinish extends TaskType {
            public static final TaskFinish INSTANCE = new TaskFinish();

            private TaskFinish() {
                super(null);
            }
        }

        public String toString() {
            String simpleName = getClass().getSimpleName();
            Intrinsics.checkExpressionValueIsNotNull(simpleName, "javaClass.simpleName");
            return simpleName;
        }
    }

    public final boolean isCruiseAdver() {
        return isCruiseAdverPic() || isCruiseAdverVideo();
    }

    public final void setCruiseAdverPic(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_CRUISE_ADVER_PIC, Boolean.valueOf(z));
    }

    public final boolean isCruiseAdverPic() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_CRUISE_ADVER_PIC, false);
    }

    public final void setCruiseAdverVideo(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_CRUISE_ADVER_VIDEO, Boolean.valueOf(z));
    }

    public final boolean isCruiseAdverVideo() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_CRUISE_ADVER_VIDEO, false);
    }

    public final void setDoorWelcomeAdverPic(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_DOOR_WELCOME_PIC, Boolean.valueOf(z));
    }

    public final boolean isDoorWelcomeAdverPic() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_DOOR_WELCOME_PIC, false);
    }

    public final void setDoorWelcomeAdverVideo(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_DOOR_WELCOME_VIDEO, Boolean.valueOf(z));
    }

    public final boolean isDoorWelcomeAdverVideo() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_DOOR_WELCOME_VIDEO, false);
    }
}
