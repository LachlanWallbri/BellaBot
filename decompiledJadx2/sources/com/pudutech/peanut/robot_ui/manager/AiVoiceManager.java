package com.pudutech.peanut.robot_ui.manager;

import android.content.Context;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.config.Constans;
import com.pudutech.resources.language.LanguageUtils;
import com.pudutech.resources.language.SupportedLocale;
import com.pudutech.voiceinteraction.component.VoiceInteractionHelper;
import com.pudutech.voiceinteraction.component.config.Language;
import com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener;
import com.pudutech.voiceinteraction.component.p069ui.VoiceInteractionDialog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AiVoiceManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010 \u001a\u00020\u000b2\u0014\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\tJ\u001c\u0010\"\u001a\u00020\u000b2\u0014\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\tJ\u000e\u0010#\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020%J\u000e\u0010&\u001a\u00020\u000b2\u0006\u0010'\u001a\u00020(J\u000e\u0010)\u001a\u00020\u000b2\u0006\u0010*\u001a\u00020+J\u0006\u0010,\u001a\u00020\u000bJ\u0006\u0010-\u001a\u00020\u000bJ\u0006\u0010.\u001a\u00020\u000bJ\b\u0010/\u001a\u000200H\u0002J\u000e\u00101\u001a\u00020\u000b2\u0006\u00102\u001a\u000203J\u0010\u00104\u001a\u00020\u000b2\b\u00105\u001a\u0004\u0018\u00010\u0004J\u0006\u00106\u001a\u00020\u000bJ\u001c\u00107\u001a\u00020\u000b2\u0014\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\tJ\u001c\u00108\u001a\u00020\u000b2\u0014\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\tJ\u000e\u00109\u001a\u00020\u000b2\u0006\u0010:\u001a\u00020\rJ\u000e\u0010;\u001a\u00020\u000b2\u0006\u0010<\u001a\u00020\u0004J\u000e\u0010=\u001a\u00020\u000b2\u0006\u0010>\u001a\u00020\u0011J\u0006\u0010?\u001a\u00020\u000bJ\u0016\u0010@\u001a\u00020\u000b2\u0006\u0010A\u001a\u00020\u00112\u0006\u0010B\u001a\u00020\u0011J\u0006\u0010C\u001a\u00020\u000bJ\u0006\u0010D\u001a\u00020\u000bJ\u0010\u0010E\u001a\u0002002\u0006\u0010*\u001a\u00020+H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R:\u0010\u0016\u001a.\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\t0\u0017j\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\t`\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R:\u0010\u0019\u001a.\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\t0\u0017j\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\t`\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\u001a\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006F"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/manager/AiVoiceManager;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "aiVoiceHelper", "Lcom/pudutech/voiceinteraction/component/VoiceInteractionHelper;", "dismissListener", "Lkotlin/Function1;", "Lcom/pudutech/voiceinteraction/component/ui/VoiceInteractionDialog;", "", "isAiVoiceDialogShow", "", "isInitSuccess", "isNeedRecording", "lastWakeAngle", "", "getLastWakeAngle", "()I", "setLastWakeAngle", "(I)V", "onDialogDismissList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "onDialogShowList", "onIntentTriggerListener", "Lkotlin/ParameterName;", "name", "v", "onWakeAngleChangedListener", "showListener", "addonDialogDismiss", "listener", "addonDialogShow", "attachActivity", "ac", "Landroidx/appcompat/app/AppCompatActivity;", "attachNullActivity", "iVoiceReponseTextListener", "Lcom/pudutech/voiceinteraction/component/listener/IVoiceReponseTextListener;", "changLanguage", "locale", "Ljava/util/Locale;", "detachActivity", "detachNullActivity", "forceStop", "getLanguage", "Lcom/pudutech/voiceinteraction/component/config/Language;", "init", "context", "Landroid/content/Context;", "playTtsVoice", "text", "release", "removeonDialogDismiss", "removeonDialogShow", "setAiUiEnable", "enable", "setOtherVoice", "ttsName", "setVolume", "i", "startAiRecording", "startWakeUp", "angle", "beam", "stopAiRecording", "stopPlayVoice", "transLanguage", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class AiVoiceManager {
    public static final AiVoiceManager INSTANCE;
    private static final String TAG;
    private static VoiceInteractionHelper aiVoiceHelper;
    private static final Function1<VoiceInteractionDialog, Unit> dismissListener;
    private static boolean isAiVoiceDialogShow;
    private static boolean isInitSuccess;
    private static boolean isNeedRecording;
    private static int lastWakeAngle;
    private static final ArrayList<Function1<VoiceInteractionDialog, Unit>> onDialogDismissList;
    private static final ArrayList<Function1<VoiceInteractionDialog, Unit>> onDialogShowList;
    private static final Function1<String, Unit> onIntentTriggerListener;
    private static final Function1<Integer, Unit> onWakeAngleChangedListener;
    private static final Function1<VoiceInteractionDialog, Unit> showListener;

    static {
        AiVoiceManager aiVoiceManager = new AiVoiceManager();
        INSTANCE = aiVoiceManager;
        TAG = aiVoiceManager.getClass().getSimpleName();
        onDialogDismissList = new ArrayList<>();
        onDialogShowList = new ArrayList<>();
        onWakeAngleChangedListener = new Function1() { // from class: com.pudutech.peanut.robot_ui.manager.AiVoiceManager$onWakeAngleChangedListener$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return invoke(((Number) obj).intValue());
            }

            public final Void invoke(int i) {
                String str;
                AiVoiceManager aiVoiceManager2 = AiVoiceManager.INSTANCE;
                str = AiVoiceManager.TAG;
                Pdlog.m3273d(str, "onAngleChanged : angle = " + i + "; ");
                AiVoiceManager.INSTANCE.setLastWakeAngle(i);
                return null;
            }
        };
        dismissListener = new Function1<VoiceInteractionDialog, Unit>() { // from class: com.pudutech.peanut.robot_ui.manager.AiVoiceManager$dismissListener$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(VoiceInteractionDialog voiceInteractionDialog) {
                invoke2(voiceInteractionDialog);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(VoiceInteractionDialog it) {
                String str;
                ArrayList arrayList;
                ArrayList arrayList2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                AiVoiceManager aiVoiceManager2 = AiVoiceManager.INSTANCE;
                str = AiVoiceManager.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onDialogDismiss ");
                AiVoiceManager aiVoiceManager3 = AiVoiceManager.INSTANCE;
                arrayList = AiVoiceManager.onDialogDismissList;
                sb.append(arrayList.size());
                Pdlog.m3273d(str, sb.toString());
                AiVoiceManager aiVoiceManager4 = AiVoiceManager.INSTANCE;
                AiVoiceManager.isAiVoiceDialogShow = false;
                AiVoiceManager aiVoiceManager5 = AiVoiceManager.INSTANCE;
                arrayList2 = AiVoiceManager.onDialogDismissList;
                Iterator it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    ((Function1) it2.next()).invoke(it);
                }
                AiVoiceTriggerHelper.INSTANCE.onAiVoiceDialogDismiss();
            }
        };
        showListener = new Function1<VoiceInteractionDialog, Unit>() { // from class: com.pudutech.peanut.robot_ui.manager.AiVoiceManager$showListener$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(VoiceInteractionDialog voiceInteractionDialog) {
                invoke2(voiceInteractionDialog);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(VoiceInteractionDialog it) {
                String str;
                ArrayList arrayList;
                ArrayList arrayList2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                AiVoiceManager aiVoiceManager2 = AiVoiceManager.INSTANCE;
                str = AiVoiceManager.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onDialogShow ");
                AiVoiceManager aiVoiceManager3 = AiVoiceManager.INSTANCE;
                arrayList = AiVoiceManager.onDialogShowList;
                sb.append(arrayList.size());
                Pdlog.m3273d(str, sb.toString());
                AiVoiceManager aiVoiceManager4 = AiVoiceManager.INSTANCE;
                AiVoiceManager.isAiVoiceDialogShow = true;
                AiVoiceManager aiVoiceManager5 = AiVoiceManager.INSTANCE;
                arrayList2 = AiVoiceManager.onDialogShowList;
                Iterator it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    ((Function1) it2.next()).invoke(it);
                }
                AiVoiceTriggerHelper.INSTANCE.onAiVoiceDialogShow();
            }
        };
        onIntentTriggerListener = new Function1<String, Unit>() { // from class: com.pudutech.peanut.robot_ui.manager.AiVoiceManager$onIntentTriggerListener$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                String str;
                Intrinsics.checkParameterIsNotNull(it, "it");
                AiVoiceManager aiVoiceManager2 = AiVoiceManager.INSTANCE;
                str = AiVoiceManager.TAG;
                Pdlog.m3273d(str, "onIntentTriggerListener " + it + ' ');
            }
        };
    }

    private AiVoiceManager() {
    }

    public static final /* synthetic */ VoiceInteractionHelper access$getAiVoiceHelper$p(AiVoiceManager aiVoiceManager) {
        VoiceInteractionHelper voiceInteractionHelper = aiVoiceHelper;
        if (voiceInteractionHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("aiVoiceHelper");
        }
        return voiceInteractionHelper;
    }

    public final int getLastWakeAngle() {
        return lastWakeAngle;
    }

    public final void setLastWakeAngle(int i) {
        lastWakeAngle = i;
    }

    public final void startWakeUp(int angle, int beam) {
        if (isInitSuccess) {
            VoiceInteractionHelper voiceInteractionHelper = aiVoiceHelper;
            if (voiceInteractionHelper == null) {
                Intrinsics.throwUninitializedPropertyAccessException("aiVoiceHelper");
            }
            voiceInteractionHelper.wakeup(angle, beam);
        }
    }

    public final void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Pdlog.m3273d(TAG, "init");
        aiVoiceHelper = new VoiceInteractionHelper();
        VoiceInteractionHelper voiceInteractionHelper = aiVoiceHelper;
        if (voiceInteractionHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("aiVoiceHelper");
        }
        voiceInteractionHelper.setOnDialogShowListener(showListener);
        VoiceInteractionHelper voiceInteractionHelper2 = aiVoiceHelper;
        if (voiceInteractionHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("aiVoiceHelper");
        }
        voiceInteractionHelper2.setOnDialogDismissListener(dismissListener);
        VoiceInteractionHelper voiceInteractionHelper3 = aiVoiceHelper;
        if (voiceInteractionHelper3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("aiVoiceHelper");
        }
        voiceInteractionHelper3.setOnIntentTriggerListener(onIntentTriggerListener);
        VoiceInteractionHelper voiceInteractionHelper4 = aiVoiceHelper;
        if (voiceInteractionHelper4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("aiVoiceHelper");
        }
        voiceInteractionHelper4.setOnWakeAngleChangedListener(onWakeAngleChangedListener);
        try {
            Pdlog.m3273d(TAG, "init aiVoiceHelper start", Constans.INSTANCE.getTtsVoiceType());
            VoiceInteractionHelper voiceInteractionHelper5 = aiVoiceHelper;
            if (voiceInteractionHelper5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("aiVoiceHelper");
            }
            voiceInteractionHelper5.init(context, getLanguage(), new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.manager.AiVoiceManager$init$1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    String str;
                    boolean z;
                    boolean z2;
                    String str2;
                    AiVoiceManager aiVoiceManager = AiVoiceManager.INSTANCE;
                    str = AiVoiceManager.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onInitSuccessListener isNeedRecording = ");
                    AiVoiceManager aiVoiceManager2 = AiVoiceManager.INSTANCE;
                    z = AiVoiceManager.isNeedRecording;
                    sb.append(z);
                    Pdlog.m3273d(str, sb.toString());
                    AiVoiceManager aiVoiceManager3 = AiVoiceManager.INSTANCE;
                    AiVoiceManager.isInitSuccess = true;
                    VoiceInteractionHelper access$getAiVoiceHelper$p = AiVoiceManager.access$getAiVoiceHelper$p(AiVoiceManager.INSTANCE);
                    if (access$getAiVoiceHelper$p != null) {
                        access$getAiVoiceHelper$p.setSpeaker(Constans.INSTANCE.getTtsVoiceType());
                    }
                    AiVoiceManager aiVoiceManager4 = AiVoiceManager.INSTANCE;
                    z2 = AiVoiceManager.isNeedRecording;
                    if (z2) {
                        try {
                            AiVoiceManager.access$getAiVoiceHelper$p(AiVoiceManager.INSTANCE).startRecording();
                        } catch (Exception e) {
                            AiVoiceManager aiVoiceManager5 = AiVoiceManager.INSTANCE;
                            str2 = AiVoiceManager.TAG;
                            Pdlog.m3274e(str2, Log.getStackTraceString(e));
                        }
                    }
                }
            }, new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.manager.AiVoiceManager$init$2
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    String str;
                    AiVoiceManager aiVoiceManager = AiVoiceManager.INSTANCE;
                    AiVoiceManager.isInitSuccess = false;
                    AiVoiceManager aiVoiceManager2 = AiVoiceManager.INSTANCE;
                    str = AiVoiceManager.TAG;
                    Pdlog.m3274e(str, "onInitSuccessListener onInitFailure");
                }
            });
            Pdlog.m3273d(TAG, "init aiVoiceHelper end");
        } catch (Exception e) {
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        }
    }

    public final void setOtherVoice(String ttsName) {
        Intrinsics.checkParameterIsNotNull(ttsName, "ttsName");
        VoiceInteractionHelper voiceInteractionHelper = aiVoiceHelper;
        if (voiceInteractionHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("aiVoiceHelper");
        }
        if (voiceInteractionHelper != null) {
            voiceInteractionHelper.setSpeaker(ttsName);
        }
    }

    public final void setVolume(int i) {
        VoiceInteractionHelper voiceInteractionHelper = aiVoiceHelper;
        if (voiceInteractionHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("aiVoiceHelper");
        }
        voiceInteractionHelper.setVolume(i);
    }

    public final void playTtsVoice(String text) {
        if (text != null) {
            VoiceInteractionHelper voiceInteractionHelper = aiVoiceHelper;
            if (voiceInteractionHelper == null) {
                Intrinsics.throwUninitializedPropertyAccessException("aiVoiceHelper");
            }
            if (voiceInteractionHelper != null) {
                voiceInteractionHelper.startTts(text);
            }
        }
    }

    private final Language getLanguage() {
        return transLanguage(new LanguageUtils(RobotContext.INSTANCE.getContext()).getCurrent().getLocale());
    }

    private final Language transLanguage(Locale locale) {
        String language = locale.getLanguage();
        if (Intrinsics.areEqual(language, SupportedLocale.INSTANCE.getENGLISH().getLocale().getLanguage())) {
            return Language.English;
        }
        if (Intrinsics.areEqual(language, SupportedLocale.INSTANCE.getCHINESE().getLocale().getLanguage()) || Intrinsics.areEqual(language, SupportedLocale.INSTANCE.getCHINESE_HK().getLocale().getLanguage())) {
            return Language.Chinese;
        }
        if (Intrinsics.areEqual(language, SupportedLocale.INSTANCE.getJAPANESE().getLocale().getLanguage())) {
            return Language.Japanese;
        }
        if (Intrinsics.areEqual(language, SupportedLocale.INSTANCE.getSPANISH().getLocale().getLanguage())) {
            return Language.Spanish;
        }
        if (Intrinsics.areEqual(language, SupportedLocale.INSTANCE.getKOREAN().getLocale().getLanguage())) {
            return Language.Korean;
        }
        if (Intrinsics.areEqual(language, SupportedLocale.INSTANCE.getGERMAN().getLocale().getLanguage())) {
            return Language.German;
        }
        return Language.English;
    }

    public final void changLanguage(Locale locale) {
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        Pdlog.m3273d(TAG, "changLanguage : locale = " + locale + "; ");
        VoiceInteractionHelper voiceInteractionHelper = aiVoiceHelper;
        if (voiceInteractionHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("aiVoiceHelper");
        }
        voiceInteractionHelper.switchLanguage(transLanguage(locale), new Function0() { // from class: com.pudutech.peanut.robot_ui.manager.AiVoiceManager$changLanguage$1
            @Override // kotlin.jvm.functions.Function0
            public final Void invoke() {
                String str;
                AiVoiceManager aiVoiceManager = AiVoiceManager.INSTANCE;
                str = AiVoiceManager.TAG;
                Pdlog.m3273d(str, "changLanguage onSwitchLanguageSucceedListener");
                return null;
            }
        }, new Function0() { // from class: com.pudutech.peanut.robot_ui.manager.AiVoiceManager$changLanguage$2
            @Override // kotlin.jvm.functions.Function0
            public final Void invoke() {
                String str;
                AiVoiceManager aiVoiceManager = AiVoiceManager.INSTANCE;
                str = AiVoiceManager.TAG;
                Pdlog.m3273d(str, "changLanguage onSwitchLanguageFailedListener");
                return null;
            }
        });
    }

    public final void attachActivity(AppCompatActivity ac) {
        Intrinsics.checkParameterIsNotNull(ac, "ac");
        Pdlog.m3273d(TAG, "attachActivity " + ac);
        VoiceInteractionHelper voiceInteractionHelper = aiVoiceHelper;
        if (voiceInteractionHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("aiVoiceHelper");
        }
        voiceInteractionHelper.attachActivity(ac);
    }

    public final void attachNullActivity(IVoiceReponseTextListener iVoiceReponseTextListener) {
        Intrinsics.checkParameterIsNotNull(iVoiceReponseTextListener, "iVoiceReponseTextListener");
        VoiceInteractionHelper voiceInteractionHelper = aiVoiceHelper;
        if (voiceInteractionHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("aiVoiceHelper");
        }
        voiceInteractionHelper.attachNullActivity(iVoiceReponseTextListener);
    }

    public final void detachNullActivity() {
        Pdlog.m3273d(TAG, "detachNullActivity");
        VoiceInteractionHelper voiceInteractionHelper = aiVoiceHelper;
        if (voiceInteractionHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("aiVoiceHelper");
        }
        voiceInteractionHelper.detachNullActivity();
    }

    public final void detachActivity() {
        Pdlog.m3273d(TAG, "detachActivity");
        VoiceInteractionHelper voiceInteractionHelper = aiVoiceHelper;
        if (voiceInteractionHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("aiVoiceHelper");
        }
        voiceInteractionHelper.detachActivity();
    }

    public final void startAiRecording() {
        Pdlog.m3273d(TAG, "startAiRecording");
        if (!isInitSuccess) {
            Pdlog.m3273d(TAG, "startAiRecording failed, need wait init");
            isNeedRecording = true;
            return;
        }
        try {
            VoiceInteractionHelper voiceInteractionHelper = aiVoiceHelper;
            if (voiceInteractionHelper == null) {
                Intrinsics.throwUninitializedPropertyAccessException("aiVoiceHelper");
            }
            voiceInteractionHelper.startRecording();
        } catch (Exception e) {
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        }
    }

    public final void stopAiRecording() {
        Pdlog.m3273d(TAG, "stopAiRecording " + isInitSuccess);
        if (!isInitSuccess) {
            isNeedRecording = false;
            return;
        }
        try {
            VoiceInteractionHelper voiceInteractionHelper = aiVoiceHelper;
            if (voiceInteractionHelper == null) {
                Intrinsics.throwUninitializedPropertyAccessException("aiVoiceHelper");
            }
            voiceInteractionHelper.stopRecording();
        } catch (Exception e) {
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        }
    }

    public final void stopPlayVoice() {
        Pdlog.m3273d(TAG, "stopPlayVoice " + isInitSuccess);
        try {
            VoiceInteractionHelper voiceInteractionHelper = aiVoiceHelper;
            if (voiceInteractionHelper == null) {
                Intrinsics.throwUninitializedPropertyAccessException("aiVoiceHelper");
            }
            voiceInteractionHelper.stopPlayVoice();
        } catch (Exception e) {
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        }
    }

    public final void forceStop() {
        Pdlog.m3273d(TAG, "forceStop " + isInitSuccess);
        if (!isInitSuccess) {
            isNeedRecording = false;
            return;
        }
        try {
            VoiceInteractionHelper voiceInteractionHelper = aiVoiceHelper;
            if (voiceInteractionHelper == null) {
                Intrinsics.throwUninitializedPropertyAccessException("aiVoiceHelper");
            }
            voiceInteractionHelper.stopRecording();
            VoiceInteractionHelper voiceInteractionHelper2 = aiVoiceHelper;
            if (voiceInteractionHelper2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("aiVoiceHelper");
            }
            voiceInteractionHelper2.resetCAE();
        } catch (Exception e) {
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        }
        VoiceInteractionHelper voiceInteractionHelper3 = aiVoiceHelper;
        if (voiceInteractionHelper3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("aiVoiceHelper");
        }
        voiceInteractionHelper3.dismissVoiceInteractionDialog();
    }

    public final void release() {
        try {
            VoiceInteractionHelper voiceInteractionHelper = aiVoiceHelper;
            if (voiceInteractionHelper == null) {
                Intrinsics.throwUninitializedPropertyAccessException("aiVoiceHelper");
            }
            voiceInteractionHelper.release();
        } catch (Exception e) {
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        }
    }

    public final void setAiUiEnable(boolean enable) {
        Pdlog.m3273d(TAG, "setAiUiEnable :" + enable + "; ");
        try {
            VoiceInteractionHelper voiceInteractionHelper = aiVoiceHelper;
            if (voiceInteractionHelper == null) {
                Intrinsics.throwUninitializedPropertyAccessException("aiVoiceHelper");
            }
            voiceInteractionHelper.setAiUiEnable(enable);
        } catch (Exception e) {
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        }
    }

    public final void addonDialogDismiss(Function1<? super VoiceInteractionDialog, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Pdlog.m3273d(TAG, "addonDialogDismiss " + listener);
        if (onDialogDismissList.contains(listener)) {
            return;
        }
        onDialogDismissList.add(listener);
    }

    public final void addonDialogShow(Function1<? super VoiceInteractionDialog, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Pdlog.m3273d(TAG, "addonDialogShow " + listener);
        if (onDialogShowList.contains(listener)) {
            return;
        }
        onDialogShowList.add(listener);
    }

    public final void removeonDialogDismiss(Function1<? super VoiceInteractionDialog, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Pdlog.m3273d(TAG, "removeonDialogDismiss " + listener);
        onDialogDismissList.remove(listener);
    }

    public final void removeonDialogShow(Function1<? super VoiceInteractionDialog, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Pdlog.m3273d(TAG, "removeonDialogDismiss " + listener);
        onDialogShowList.remove(listener);
    }
}
