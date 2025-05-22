package com.pudutech.peanut.robot_ui.p063ui.helper;

import android.content.Context;
import com.pudutech.base.MD5Util;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.p063ui.helper.TtsVoiceHelper;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: TtsWordsTemplateHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u0010\u0011B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/helper/TtsWordsTemplateHelper;", "", "()V", TtsWordsTemplateHelper.KEY_WORD_TEMPLATE_INIT, "", "TAG", "kotlin.jvm.PlatformType", "getWordsTemplates", "", "Lcom/pudutech/peanut/robot_ui/ui/helper/TtsWordsTemplateHelper$WordsTemplateBean;", "init", "", "initConfig", "wordsTemplateBeans", "isInitSuccess", "", "WordsTemplateBean", "WordsTemplateType", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class TtsWordsTemplateHelper {
    public static final TtsWordsTemplateHelper INSTANCE;
    private static final String KEY_WORD_TEMPLATE_INIT = "KEY_WORD_TEMPLATE_INIT";
    private static final String TAG;

    static {
        TtsWordsTemplateHelper ttsWordsTemplateHelper = new TtsWordsTemplateHelper();
        INSTANCE = ttsWordsTemplateHelper;
        TAG = ttsWordsTemplateHelper.getClass().getSimpleName();
    }

    private TtsWordsTemplateHelper() {
    }

    public final void init() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new TtsWordsTemplateHelper$init$1(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initConfig(List<WordsTemplateBean> wordsTemplateBeans) {
        String filePath;
        TtsVoiceHelper.TtsVoiceType ttsVoiceType;
        TtsVoiceHelper.TtsVoiceType ttsVoiceType2;
        String str;
        for (WordsTemplateBean wordsTemplateBean : wordsTemplateBeans) {
            String md5 = MD5Util.toMD5(wordsTemplateBean.getName());
            Pdlog.m3273d(TAG, "gen md5 name = " + md5, wordsTemplateBean);
            String str2 = (String) null;
            TtsVoiceHelper.TtsVoiceType ttsVoiceType3 = (TtsVoiceHelper.TtsVoiceType) null;
            String type = wordsTemplateBean.getType();
            int hashCode = type.hashCode();
            if (hashCode == -1290435484) {
                if (type.equals("CRUISE_TYPE")) {
                    TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                    Context context = RobotContext.INSTANCE.getContext();
                    Intrinsics.checkExpressionValueIsNotNull(md5, "md5");
                    filePath = ttsVoiceHelper.getFilePath(context, md5, TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE);
                    ttsVoiceType = TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE;
                    str = filePath;
                    ttsVoiceType2 = ttsVoiceType;
                }
                ttsVoiceType2 = ttsVoiceType3;
                str = str2;
            } else if (hashCode == -1289222604) {
                if (type.equals("DELIVER_TYPE")) {
                    TtsVoiceHelper ttsVoiceHelper2 = TtsVoiceHelper.INSTANCE;
                    Context context2 = RobotContext.INSTANCE.getContext();
                    Intrinsics.checkExpressionValueIsNotNull(md5, "md5");
                    filePath = ttsVoiceHelper2.getFilePath(context2, md5, TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE);
                    ttsVoiceType = TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE;
                    str = filePath;
                    ttsVoiceType2 = ttsVoiceType;
                }
                ttsVoiceType2 = ttsVoiceType3;
                str = str2;
            } else {
                if (hashCode == 960808674 && type.equals("USHER_TYPE")) {
                    TtsVoiceHelper ttsVoiceHelper3 = TtsVoiceHelper.INSTANCE;
                    Context context3 = RobotContext.INSTANCE.getContext();
                    Intrinsics.checkExpressionValueIsNotNull(md5, "md5");
                    filePath = ttsVoiceHelper3.getFilePath(context3, md5, TtsVoiceHelper.TtsVoiceType.USHER_TYPE);
                    ttsVoiceType = TtsVoiceHelper.TtsVoiceType.USHER_TYPE;
                    str = filePath;
                    ttsVoiceType2 = ttsVoiceType;
                }
                ttsVoiceType2 = ttsVoiceType3;
                str = str2;
            }
            if (str != null && ttsVoiceType2 != null) {
                Pdlog.m3273d(TAG, "gen file path = " + str + ";type=" + ttsVoiceType2);
                TtsVoiceHelper ttsVoiceHelper4 = TtsVoiceHelper.INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(md5, "md5");
                TtsVoiceHelper.addConfigItem$default(ttsVoiceHelper4, str, md5, wordsTemplateBean.getName(), ttsVoiceType2, null, false, 48, null);
            }
        }
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_WORD_TEMPLATE_INIT, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<WordsTemplateBean> getWordsTemplates() {
        Pdlog.m3273d(TAG, "getWordsTemplate start");
        String string = RobotContext.INSTANCE.getContext().getString(C5508R.string.custom_deliver_1);
        Intrinsics.checkExpressionValueIsNotNull(string, "RobotContext.context.get….string.custom_deliver_1)");
        String string2 = RobotContext.INSTANCE.getContext().getString(C5508R.string.custom_deliver_2);
        Intrinsics.checkExpressionValueIsNotNull(string2, "RobotContext.context.get….string.custom_deliver_2)");
        String string3 = RobotContext.INSTANCE.getContext().getString(C5508R.string.custom_deliver_3);
        Intrinsics.checkExpressionValueIsNotNull(string3, "RobotContext.context.get….string.custom_deliver_3)");
        String string4 = RobotContext.INSTANCE.getContext().getString(C5508R.string.custom_cruise_1);
        Intrinsics.checkExpressionValueIsNotNull(string4, "RobotContext.context.get…R.string.custom_cruise_1)");
        String string5 = RobotContext.INSTANCE.getContext().getString(C5508R.string.custom_cruise_2);
        Intrinsics.checkExpressionValueIsNotNull(string5, "RobotContext.context.get…R.string.custom_cruise_2)");
        String string6 = RobotContext.INSTANCE.getContext().getString(C5508R.string.custom_cruise_3);
        Intrinsics.checkExpressionValueIsNotNull(string6, "RobotContext.context.get…R.string.custom_cruise_3)");
        String string7 = RobotContext.INSTANCE.getContext().getString(C5508R.string.custom_usher_1);
        Intrinsics.checkExpressionValueIsNotNull(string7, "RobotContext.context.get…(R.string.custom_usher_1)");
        String string8 = RobotContext.INSTANCE.getContext().getString(C5508R.string.custom_usher_2);
        Intrinsics.checkExpressionValueIsNotNull(string8, "RobotContext.context.get…(R.string.custom_usher_2)");
        String string9 = RobotContext.INSTANCE.getContext().getString(C5508R.string.custom_usher_3);
        Intrinsics.checkExpressionValueIsNotNull(string9, "RobotContext.context.get…(R.string.custom_usher_3)");
        return CollectionsKt.arrayListOf(new WordsTemplateBean("DELIVER_TYPE", string), new WordsTemplateBean("DELIVER_TYPE", string2), new WordsTemplateBean("DELIVER_TYPE", string3), new WordsTemplateBean("CRUISE_TYPE", string4), new WordsTemplateBean("CRUISE_TYPE", string5), new WordsTemplateBean("CRUISE_TYPE", string6), new WordsTemplateBean("USHER_TYPE", string7), new WordsTemplateBean("USHER_TYPE", string8), new WordsTemplateBean("USHER_TYPE", string9));
    }

    public final boolean isInitSuccess() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_WORD_TEMPLATE_INIT, false);
    }

    /* compiled from: TtsWordsTemplateHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/helper/TtsWordsTemplateHelper$WordsTemplateBean;", "", "type", "", "name", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getType", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final /* data */ class WordsTemplateBean {
        private final String name;
        private final String type;

        public static /* synthetic */ WordsTemplateBean copy$default(WordsTemplateBean wordsTemplateBean, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = wordsTemplateBean.type;
            }
            if ((i & 2) != 0) {
                str2 = wordsTemplateBean.name;
            }
            return wordsTemplateBean.copy(str, str2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final WordsTemplateBean copy(String type, String name) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            Intrinsics.checkParameterIsNotNull(name, "name");
            return new WordsTemplateBean(type, name);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof WordsTemplateBean)) {
                return false;
            }
            WordsTemplateBean wordsTemplateBean = (WordsTemplateBean) other;
            return Intrinsics.areEqual(this.type, wordsTemplateBean.type) && Intrinsics.areEqual(this.name, wordsTemplateBean.name);
        }

        public int hashCode() {
            String str = this.type;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.name;
            return hashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "WordsTemplateBean(type=" + this.type + ", name=" + this.name + ")";
        }

        public WordsTemplateBean(String type, String name) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            Intrinsics.checkParameterIsNotNull(name, "name");
            this.type = type;
            this.name = name;
        }

        public final String getName() {
            return this.name;
        }

        public final String getType() {
            return this.type;
        }
    }

    /* compiled from: TtsWordsTemplateHelper.kt */
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\b\u0002\b\u0087\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002B\u0000¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/helper/TtsWordsTemplateHelper$WordsTemplateType;", "", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    @kotlin.annotation.Target(allowedTargets = {AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    /* loaded from: classes5.dex */
    public @interface WordsTemplateType {
        public static final String CRUISE_TYPE = "CRUISE_TYPE";

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;
        public static final String DELIVER_TYPE = "DELIVER_TYPE";
        public static final String USHER_TYPE = "USHER_TYPE";

        /* compiled from: TtsWordsTemplateHelper.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/helper/TtsWordsTemplateHelper$WordsTemplateType$Companion;", "", "()V", "CRUISE_TYPE", "", "DELIVER_TYPE", "USHER_TYPE", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();
            public static final String CRUISE_TYPE = "CRUISE_TYPE";
            public static final String DELIVER_TYPE = "DELIVER_TYPE";
            public static final String USHER_TYPE = "USHER_TYPE";

            private Companion() {
            }
        }
    }
}
