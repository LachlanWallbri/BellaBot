package com.pudutech.bumblebee.robot_ui.manager;

import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.rotation_task.RotationContract;
import com.pudutech.bumblebee.presenter.rotation_task.RotationPresenter;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.PalletCountHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceManager;
import com.pudutech.bumblebee.robot_ui.track.task.VoiceInteractionTrack;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.robot.module.report.track2.VoiceSkillType;
import com.pudutech.voiceinteraction.component.cmd.CmdBean;
import com.pudutech.voiceinteraction.component.cmd.TriggerIntent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: AiVoiceTriggerHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u00029:B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\f\u0010*\u001a\b\u0012\u0004\u0012\u00020'0\u0006J\b\u0010+\u001a\u00020'H\u0002J\b\u0010,\u001a\u00020'H\u0002J\u001c\u0010-\u001a\u00020'2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040/H\u0002J\u001c\u00100\u001a\u00020'2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040/H\u0002J\u0010\u00101\u001a\u00020'2\b\u00102\u001a\u0004\u0018\u000103J\u0006\u00104\u001a\u00020'J\u0006\u00105\u001a\u00020'J\u0010\u00106\u001a\u00020'2\u0006\u00107\u001a\u000208H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\"\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR7\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017RL\u0010\u0018\u001a4\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001b\u0010 \u001a\u00020!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b\"\u0010#R\u0016\u0010&\u001a\n\u0012\u0004\u0012\u00020'\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006;"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/AiVoiceTriggerHelper;", "Lcom/pudutech/bumblebee/presenter/rotation_task/RotationContract$ViewInterface;", "()V", "TAG", "", "onMissionAccomplished", "Lkotlin/Function0;", "", "getOnMissionAccomplished", "()Lkotlin/jvm/functions/Function0;", "setOnMissionAccomplished", "(Lkotlin/jvm/functions/Function0;)V", "onMissionStartListener", "getOnMissionStartListener", "setOnMissionStartListener", "onTaskGreeterListener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "destination", "getOnTaskGreeterListener", "()Lkotlin/jvm/functions/Function1;", "setOnTaskGreeterListener", "(Lkotlin/jvm/functions/Function1;)V", "onTaskInputListener", "Lkotlin/Function2;", "", "tray", "getOnTaskInputListener", "()Lkotlin/jvm/functions/Function2;", "setOnTaskInputListener", "(Lkotlin/jvm/functions/Function2;)V", "rotationPresenter", "Lcom/pudutech/bumblebee/presenter/rotation_task/RotationPresenter;", "getRotationPresenter", "()Lcom/pudutech/bumblebee/presenter/rotation_task/RotationPresenter;", "rotationPresenter$delegate", "Lkotlin/Lazy;", "rotationTask", "", "handlerBusiness", "text", "noPointCallBack", "handlerLookAtMe", "handlerStart", "handlerTaskGreeter", "slots", "", "handlerTaskInput", "handlerTriggerMsg", "cmdBean", "Lcom/pudutech/voiceinteraction/component/cmd/CmdBean;", "onAiVoiceDialogDismiss", "onAiVoiceDialogShow", "showRotateEvent", "event", "Lcom/pudutech/bumblebee/presenter/rotation_task/RotationContract$ViewEvent;", "SlotBean", "VoiceTriggerBean", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class AiVoiceTriggerHelper implements RotationContract.ViewInterface {
    private static Function0<Boolean> onMissionAccomplished;
    private static Function0<Boolean> onMissionStartListener;
    private static Function1<? super String, Boolean> onTaskGreeterListener;
    private static Function2<? super Integer, ? super String, Boolean> onTaskInputListener;
    private static Function0<Unit> rotationTask;
    public static final AiVoiceTriggerHelper INSTANCE = new AiVoiceTriggerHelper();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    /* renamed from: rotationPresenter$delegate, reason: from kotlin metadata */
    private static final Lazy rotationPresenter = LazyKt.lazy(new Function0<RotationPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.manager.AiVoiceTriggerHelper$rotationPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final RotationPresenter invoke() {
            RotationPresenter rotationPresenter2;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(RotationPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(RotationPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                rotationPresenter2 = new RotationPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(RotationPresenter.class).toString(), rotationPresenter2);
            } else {
                if (!(basePresenterInterface instanceof RotationPresenter)) {
                    basePresenterInterface = null;
                }
                rotationPresenter2 = (RotationPresenter) basePresenterInterface;
            }
            if (rotationPresenter2 == null) {
                Intrinsics.throwNpe();
            }
            RotationPresenter rotationPresenter3 = rotationPresenter2;
            rotationPresenter3.replaceView(AiVoiceTriggerHelper.INSTANCE);
            return rotationPresenter3;
        }
    });

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TriggerIntent.values().length];

        static {
            $EnumSwitchMapping$0[TriggerIntent.TaskInput.ordinal()] = 1;
            $EnumSwitchMapping$0[TriggerIntent.MissionStart.ordinal()] = 2;
            $EnumSwitchMapping$0[TriggerIntent.LookAtMe.ordinal()] = 3;
            $EnumSwitchMapping$0[TriggerIntent.MissionAccomplished.ordinal()] = 4;
            $EnumSwitchMapping$0[TriggerIntent.guide.ordinal()] = 5;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RotationPresenter getRotationPresenter() {
        return (RotationPresenter) rotationPresenter.getValue();
    }

    private AiVoiceTriggerHelper() {
    }

    public final Function2<Integer, String, Boolean> getOnTaskInputListener() {
        return onTaskInputListener;
    }

    public final void setOnTaskInputListener(Function2<? super Integer, ? super String, Boolean> function2) {
        onTaskInputListener = function2;
    }

    public final Function0<Boolean> getOnMissionStartListener() {
        return onMissionStartListener;
    }

    public final void setOnMissionStartListener(Function0<Boolean> function0) {
        onMissionStartListener = function0;
    }

    public final Function0<Boolean> getOnMissionAccomplished() {
        return onMissionAccomplished;
    }

    public final void setOnMissionAccomplished(Function0<Boolean> function0) {
        onMissionAccomplished = function0;
    }

    public final Function1<String, Boolean> getOnTaskGreeterListener() {
        return onTaskGreeterListener;
    }

    public final void setOnTaskGreeterListener(Function1<? super String, Boolean> function1) {
        onTaskGreeterListener = function1;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0043 A[Catch: Exception -> 0x000a, TryCatch #0 {Exception -> 0x000a, blocks: (B:56:0x0005, B:5:0x0010, B:10:0x0018, B:21:0x0032, B:23:0x0037, B:28:0x0043, B:30:0x004d, B:34:0x0060, B:36:0x0064, B:39:0x006b, B:41:0x006f, B:43:0x0073, B:45:0x0078, B:50:0x0084, B:52:0x008e), top: B:55:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x004d A[Catch: Exception -> 0x000a, TryCatch #0 {Exception -> 0x000a, blocks: (B:56:0x0005, B:5:0x0010, B:10:0x0018, B:21:0x0032, B:23:0x0037, B:28:0x0043, B:30:0x004d, B:34:0x0060, B:36:0x0064, B:39:0x006b, B:41:0x006f, B:43:0x0073, B:45:0x0078, B:50:0x0084, B:52:0x008e), top: B:55:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0084 A[Catch: Exception -> 0x000a, TryCatch #0 {Exception -> 0x000a, blocks: (B:56:0x0005, B:5:0x0010, B:10:0x0018, B:21:0x0032, B:23:0x0037, B:28:0x0043, B:30:0x004d, B:34:0x0060, B:36:0x0064, B:39:0x006b, B:41:0x006f, B:43:0x0073, B:45:0x0078, B:50:0x0084, B:52:0x008e), top: B:55:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008e A[Catch: Exception -> 0x000a, TRY_LEAVE, TryCatch #0 {Exception -> 0x000a, blocks: (B:56:0x0005, B:5:0x0010, B:10:0x0018, B:21:0x0032, B:23:0x0037, B:28:0x0043, B:30:0x004d, B:34:0x0060, B:36:0x0064, B:39:0x006b, B:41:0x006f, B:43:0x0073, B:45:0x0078, B:50:0x0084, B:52:0x008e), top: B:55:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void handlerTriggerMsg(CmdBean cmdBean) {
        HashMap<String, String> v;
        boolean z;
        boolean z2;
        if (cmdBean != null) {
            try {
                v = cmdBean.getV();
            } catch (Exception e) {
                Pdlog.m3274e(TAG, "handlerTriggerMsg : " + Log.getStackTraceString(e));
                return;
            }
        } else {
            v = null;
        }
        TriggerIntent intent = cmdBean != null ? cmdBean.getIntent() : null;
        if (intent == null) {
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[intent.ordinal()];
        if (i == 1) {
            HashMap<String, String> hashMap = v;
            if (hashMap != null && !hashMap.isEmpty()) {
                z = false;
                if (!z) {
                    Pdlog.m3273d(TAG, "handlerTriggerMsg : triggerList is empty ");
                    return;
                } else {
                    handlerTaskInput(v);
                    Pdlog.m3273d(TAG, String.valueOf(v));
                    return;
                }
            }
            z = true;
            if (!z) {
            }
        } else {
            if (i == 2) {
                handlerStart();
                return;
            }
            if (i == 3) {
                handlerLookAtMe();
                return;
            }
            if (i == 4) {
                Function0<Boolean> function0 = onMissionAccomplished;
                if (function0 != null) {
                    function0.invoke();
                    return;
                }
                return;
            }
            if (i != 5) {
                return;
            }
            HashMap<String, String> hashMap2 = v;
            if (hashMap2 != null && !hashMap2.isEmpty()) {
                z2 = false;
                if (!z2) {
                    Pdlog.m3273d(TAG, "handlerTriggerMsg : triggerList is empty ");
                    return;
                } else {
                    Pdlog.m3273d(TAG, String.valueOf(v));
                    handlerTaskGreeter(v);
                    return;
                }
            }
            z2 = true;
            if (!z2) {
            }
        }
    }

    private final void handlerLookAtMe() {
        final int lastWakeAngle = AiVoiceManager.INSTANCE.getLastWakeAngle();
        Pdlog.m3273d(TAG, "handlerLookAtMe " + lastWakeAngle);
        rotationTask = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.AiVoiceTriggerHelper$handlerLookAtMe$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                RotationPresenter rotationPresenter2;
                rotationPresenter2 = AiVoiceTriggerHelper.INSTANCE.getRotationPresenter();
                rotationPresenter2.rotate(Math.toRadians(lastWakeAngle));
            }
        };
    }

    public final void onAiVoiceDialogDismiss() {
        Pdlog.m3273d(TAG, "onAiVoiceDialogDismiss rotationTask = " + rotationTask);
        Function0<Unit> function0 = rotationTask;
        if (function0 != null) {
            function0.invoke();
        }
    }

    public final void onAiVoiceDialogShow() {
        rotationTask = (Function0) null;
    }

    private final void handlerStart() {
        Pdlog.m3273d(TAG, "handlerStart ");
        Function0<Boolean> function0 = onMissionStartListener;
        if (function0 != null) {
            function0.invoke();
        }
    }

    private final void handlerTaskGreeter(Map<String, String> slots) {
        if (onTaskGreeterListener == null) {
            return;
        }
        if (slots.size() != 1) {
            Pdlog.m3274e(TAG, "handlerTaskGreeter : slots = " + slots + "; error");
            return;
        }
        String str = "";
        for (Map.Entry<String, String> entry : slots.entrySet()) {
            if (Intrinsics.areEqual(entry.getKey(), "destination")) {
                str = entry.getValue();
            }
        }
        if (!RobotMapManager.INSTANCE.checkDestinationExist(str)) {
            ToastUtils.show(RobotContext.INSTANCE.getContext(), C4188R.string.voice_not_target);
            return;
        }
        VoiceInteractionTrack.INSTANCE.onSkill(VoiceSkillType.USHER);
        Function1<? super String, Boolean> function1 = onTaskGreeterListener;
        if (function1 != null) {
            function1.invoke(str);
        }
    }

    public final void handlerBusiness(String text, final String destination, Function0<Unit> noPointCallBack) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(noPointCallBack, "noPointCallBack");
        Pdlog.m3273d(TAG, "handlerBusiness() text =" + text + " destination =" + destination);
        if (!RobotMapManager.INSTANCE.checkDestinationExist(destination)) {
            ToastUtils.show(RobotContext.INSTANCE.getContext(), C4188R.string.voice_not_target);
            noPointCallBack.invoke();
        } else {
            TtsVoiceManager.playTtsVoice$default(TtsVoiceManager.INSTANCE, text, false, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.manager.AiVoiceTriggerHelper$handlerBusiness$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    String str;
                    Function1<String, Boolean> onTaskGreeterListener2 = AiVoiceTriggerHelper.INSTANCE.getOnTaskGreeterListener();
                    if (onTaskGreeterListener2 != null) {
                        onTaskGreeterListener2.invoke(destination);
                    }
                    AiVoiceTriggerHelper aiVoiceTriggerHelper = AiVoiceTriggerHelper.INSTANCE;
                    str = AiVoiceTriggerHelper.TAG;
                    Pdlog.m3273d(str, "handlerBusiness() onTaskGreeterListener destination =" + destination);
                }
            }, 2, null);
        }
    }

    private final void handlerTaskInput(Map<String, String> slots) {
        if (onTaskInputListener == null) {
            return;
        }
        if (slots.size() != 2) {
            Pdlog.m3274e(TAG, "handlerTaskInput : slots = " + slots + "; error");
            return;
        }
        String str = "";
        int i = 0;
        for (Map.Entry<String, String> entry : slots.entrySet()) {
            if (Intrinsics.areEqual(entry.getKey(), "tray")) {
                try {
                    if (!LanguageUtils.INSTANCE.isZh() && !LanguageUtils.INSTANCE.isZhTw()) {
                        i = PalletCountHelper.INSTANCE.getFirstTray() + 1;
                        Pdlog.m3273d(TAG, "handlerTaskInput tray=" + i);
                    }
                    i = Integer.parseInt(entry.getValue());
                    Pdlog.m3273d(TAG, "handlerTaskInput tray=" + i);
                } catch (Exception e) {
                    Pdlog.m3274e(TAG, "handlerTaskInput : " + Log.getStackTraceString(e));
                }
            } else if (Intrinsics.areEqual(entry.getKey(), "destination")) {
                str = entry.getValue();
            }
        }
        if (!RobotMapManager.INSTANCE.checkDestinationExist(str)) {
            ToastUtils.show(RobotContext.INSTANCE.getContext(), C4188R.string.voice_not_target);
            return;
        }
        int count = PalletCountHelper.INSTANCE.getCount();
        if (i == 0 || Math.abs(i) > count) {
            ToastUtils.show(RobotContext.INSTANCE.getContext(), RobotContext.INSTANCE.getContext().getString(C4188R.string.pallet_num_wrong) + " ：" + i, new Object[0]);
            return;
        }
        if (i < 0) {
            i += count + 1;
        }
        VoiceInteractionTrack.INSTANCE.onSkill(VoiceSkillType.DELIVERY);
        Function2<? super Integer, ? super String, Boolean> function2 = onTaskInputListener;
        if (function2 != null) {
            function2.invoke(Integer.valueOf(i), str);
        }
    }

    @Override // com.pudutech.bumblebee.presenter.rotation_task.RotationContract.ViewInterface
    public void showRotateEvent(RotationContract.ViewEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Pdlog.m3273d(TAG, "showRotateEvent : event = " + event + "; ");
        if (event != RotationContract.ViewEvent.FINISH) {
            RotationContract.ViewEvent viewEvent = RotationContract.ViewEvent.OVERTIME;
        }
    }

    /* compiled from: AiVoiceTriggerHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\r¢\u0006\u0002\u0010\u000eJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\bHÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\u0019\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rHÆ\u0003JU\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\u0018\b\u0002\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rHÆ\u0001J\u0013\u0010 \u001a\u00020\u00052\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R!\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\r¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010¨\u0006%"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/AiVoiceTriggerHelper$VoiceTriggerBean;", "", "entrypoint", "", "hazard", "", "intent", "score", "", "template", "slots", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/robot_ui/manager/AiVoiceTriggerHelper$SlotBean;", "Lkotlin/collections/ArrayList;", "(Ljava/lang/String;ZLjava/lang/String;DLjava/lang/String;Ljava/util/ArrayList;)V", "getEntrypoint", "()Ljava/lang/String;", "getHazard", "()Z", "getIntent", "getScore", "()D", "getSlots", "()Ljava/util/ArrayList;", "getTemplate", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    private static final /* data */ class VoiceTriggerBean {
        private final String entrypoint;
        private final boolean hazard;
        private final String intent;
        private final double score;
        private final ArrayList<SlotBean> slots;
        private final String template;

        public static /* synthetic */ VoiceTriggerBean copy$default(VoiceTriggerBean voiceTriggerBean, String str, boolean z, String str2, double d, String str3, ArrayList arrayList, int i, Object obj) {
            if ((i & 1) != 0) {
                str = voiceTriggerBean.entrypoint;
            }
            if ((i & 2) != 0) {
                z = voiceTriggerBean.hazard;
            }
            boolean z2 = z;
            if ((i & 4) != 0) {
                str2 = voiceTriggerBean.intent;
            }
            String str4 = str2;
            if ((i & 8) != 0) {
                d = voiceTriggerBean.score;
            }
            double d2 = d;
            if ((i & 16) != 0) {
                str3 = voiceTriggerBean.template;
            }
            String str5 = str3;
            if ((i & 32) != 0) {
                arrayList = voiceTriggerBean.slots;
            }
            return voiceTriggerBean.copy(str, z2, str4, d2, str5, arrayList);
        }

        /* renamed from: component1, reason: from getter */
        public final String getEntrypoint() {
            return this.entrypoint;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getHazard() {
            return this.hazard;
        }

        /* renamed from: component3, reason: from getter */
        public final String getIntent() {
            return this.intent;
        }

        /* renamed from: component4, reason: from getter */
        public final double getScore() {
            return this.score;
        }

        /* renamed from: component5, reason: from getter */
        public final String getTemplate() {
            return this.template;
        }

        public final ArrayList<SlotBean> component6() {
            return this.slots;
        }

        public final VoiceTriggerBean copy(String entrypoint, boolean hazard, String intent, double score, String template, ArrayList<SlotBean> slots) {
            Intrinsics.checkParameterIsNotNull(entrypoint, "entrypoint");
            Intrinsics.checkParameterIsNotNull(intent, "intent");
            Intrinsics.checkParameterIsNotNull(template, "template");
            Intrinsics.checkParameterIsNotNull(slots, "slots");
            return new VoiceTriggerBean(entrypoint, hazard, intent, score, template, slots);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VoiceTriggerBean)) {
                return false;
            }
            VoiceTriggerBean voiceTriggerBean = (VoiceTriggerBean) other;
            return Intrinsics.areEqual(this.entrypoint, voiceTriggerBean.entrypoint) && this.hazard == voiceTriggerBean.hazard && Intrinsics.areEqual(this.intent, voiceTriggerBean.intent) && Double.compare(this.score, voiceTriggerBean.score) == 0 && Intrinsics.areEqual(this.template, voiceTriggerBean.template) && Intrinsics.areEqual(this.slots, voiceTriggerBean.slots);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            String str = this.entrypoint;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            boolean z = this.hazard;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            int i2 = (hashCode + i) * 31;
            String str2 = this.intent;
            int hashCode2 = (((i2 + (str2 != null ? str2.hashCode() : 0)) * 31) + Double.hashCode(this.score)) * 31;
            String str3 = this.template;
            int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
            ArrayList<SlotBean> arrayList = this.slots;
            return hashCode3 + (arrayList != null ? arrayList.hashCode() : 0);
        }

        public String toString() {
            return "VoiceTriggerBean(entrypoint=" + this.entrypoint + ", hazard=" + this.hazard + ", intent=" + this.intent + ", score=" + this.score + ", template=" + this.template + ", slots=" + this.slots + ")";
        }

        public VoiceTriggerBean(String entrypoint, boolean z, String intent, double d, String template, ArrayList<SlotBean> slots) {
            Intrinsics.checkParameterIsNotNull(entrypoint, "entrypoint");
            Intrinsics.checkParameterIsNotNull(intent, "intent");
            Intrinsics.checkParameterIsNotNull(template, "template");
            Intrinsics.checkParameterIsNotNull(slots, "slots");
            this.entrypoint = entrypoint;
            this.hazard = z;
            this.intent = intent;
            this.score = d;
            this.template = template;
            this.slots = slots;
        }

        public final String getEntrypoint() {
            return this.entrypoint;
        }

        public final boolean getHazard() {
            return this.hazard;
        }

        public final String getIntent() {
            return this.intent;
        }

        public final double getScore() {
            return this.score;
        }

        public final String getTemplate() {
            return this.template;
        }

        public final ArrayList<SlotBean> getSlots() {
            return this.slots;
        }
    }

    /* compiled from: AiVoiceTriggerHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/AiVoiceTriggerHelper$SlotBean;", "", "begin", "", "end", "name", "", "normValue", ES6Iterator.VALUE_PROPERTY, "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBegin", "()I", "getEnd", "getName", "()Ljava/lang/String;", "getNormValue", "getValue", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    private static final /* data */ class SlotBean {
        private final int begin;
        private final int end;
        private final String name;
        private final String normValue;
        private final String value;

        public static /* synthetic */ SlotBean copy$default(SlotBean slotBean, int i, int i2, String str, String str2, String str3, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = slotBean.begin;
            }
            if ((i3 & 2) != 0) {
                i2 = slotBean.end;
            }
            int i4 = i2;
            if ((i3 & 4) != 0) {
                str = slotBean.name;
            }
            String str4 = str;
            if ((i3 & 8) != 0) {
                str2 = slotBean.normValue;
            }
            String str5 = str2;
            if ((i3 & 16) != 0) {
                str3 = slotBean.value;
            }
            return slotBean.copy(i, i4, str4, str5, str3);
        }

        /* renamed from: component1, reason: from getter */
        public final int getBegin() {
            return this.begin;
        }

        /* renamed from: component2, reason: from getter */
        public final int getEnd() {
            return this.end;
        }

        /* renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* renamed from: component4, reason: from getter */
        public final String getNormValue() {
            return this.normValue;
        }

        /* renamed from: component5, reason: from getter */
        public final String getValue() {
            return this.value;
        }

        public final SlotBean copy(int begin, int end, String name, String normValue, String value) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(normValue, "normValue");
            Intrinsics.checkParameterIsNotNull(value, "value");
            return new SlotBean(begin, end, name, normValue, value);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SlotBean)) {
                return false;
            }
            SlotBean slotBean = (SlotBean) other;
            return this.begin == slotBean.begin && this.end == slotBean.end && Intrinsics.areEqual(this.name, slotBean.name) && Intrinsics.areEqual(this.normValue, slotBean.normValue) && Intrinsics.areEqual(this.value, slotBean.value);
        }

        public int hashCode() {
            int hashCode = ((Integer.hashCode(this.begin) * 31) + Integer.hashCode(this.end)) * 31;
            String str = this.name;
            int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.normValue;
            int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.value;
            return hashCode3 + (str3 != null ? str3.hashCode() : 0);
        }

        public String toString() {
            return "SlotBean(begin=" + this.begin + ", end=" + this.end + ", name=" + this.name + ", normValue=" + this.normValue + ", value=" + this.value + ")";
        }

        public SlotBean(int i, int i2, String name, String normValue, String value) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(normValue, "normValue");
            Intrinsics.checkParameterIsNotNull(value, "value");
            this.begin = i;
            this.end = i2;
            this.name = name;
            this.normValue = normValue;
            this.value = value;
        }

        public final int getBegin() {
            return this.begin;
        }

        public final int getEnd() {
            return this.end;
        }

        public final String getName() {
            return this.name;
        }

        public final String getNormValue() {
            return this.normValue;
        }

        public final String getValue() {
            return this.value;
        }
    }
}
