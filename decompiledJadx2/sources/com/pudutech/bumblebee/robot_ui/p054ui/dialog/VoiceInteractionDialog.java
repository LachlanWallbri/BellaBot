package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.robot_voices.GeneralVoiceProperty;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.manager.AiVoiceManager;
import com.pudutech.bumblebee.robot_ui.manager.AiVoiceTriggerHelper;
import com.pudutech.bumblebee.robot_ui.track.task.VoiceInteractionTrack;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.util.CountdownUtil;
import com.pudutech.bumblebee.robot_ui.util.NavigationBarUtil;
import com.pudutech.bumblebee.robot_ui.widget.VolumeChangeView;
import com.pudutech.bumblebee.robot_ui.widget.help.CTextSwitcherAnimation;
import com.pudutech.robot.module.report.track2.WakeUpType;
import com.pudutech.voiceinteraction.component.C5767R;
import com.pudutech.voiceinteraction.component.VoiceInteractionKit;
import com.pudutech.voiceinteraction.component.cmd.CmdBean;
import com.pudutech.voiceinteraction.component.config.Language;
import com.pudutech.voiceinteraction.component.config.VoiceInteractionState;
import com.pudutech.voiceinteraction.component.config.WakeupInfo;
import com.pudutech.widget.loading.CLoadingView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VoiceInteractionDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0015\u0018\u00002\u00020\u0001:\u0001QB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u00108\u001a\u00020\u0011H\u0016J\b\u00109\u001a\u00020\u0011H\u0002J\b\u0010:\u001a\u00020\u0011H\u0002J\u0012\u0010;\u001a\u00020\u00112\b\u0010<\u001a\u0004\u0018\u00010=H\u0014J\b\u0010>\u001a\u00020\u0011H\u0002J\b\u0010?\u001a\u00020\u0011H\u0002J\b\u0010@\u001a\u00020\u0011H\u0002J\u000e\u0010A\u001a\u00020\u00112\u0006\u0010B\u001a\u00020%J\u0010\u0010C\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\tH\u0002J\u0012\u0010D\u001a\u00020\u00112\b\u0010E\u001a\u0004\u0018\u00010\u000bH\u0002J\u0012\u0010F\u001a\u00020\u00112\b\b\u0001\u0010G\u001a\u00020\u0006H\u0002J\u0012\u0010H\u001a\u00020\u00112\b\u0010I\u001a\u0004\u0018\u00010\u000bH\u0002J\u0010\u0010J\u001a\u00020\u00112\u0006\u0010K\u001a\u00020\u0006H\u0002J\b\u0010L\u001a\u00020\u0011H\u0016J\u0010\u0010M\u001a\u00020\u00112\u0006\u0010N\u001a\u00020.H\u0002J\u0010\u0010O\u001a\u00020\u00112\u0006\u0010N\u001a\u00020.H\u0002J\u0010\u0010P\u001a\u00020\u00112\u0006\u0010N\u001a\u00020.H\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\rX\u0082.¢\u0006\u0004\n\u0002\u0010\u000eR \u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0016\u0012\u0004\u0012\u00020\u00110\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010+\u001a\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00110,X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010-\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010/\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u00110\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u001a\u00103\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00110\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u00106\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u000107\u0012\u0004\u0012\u00020\u00110\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006R"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/VoiceInteractionDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "theme", "", "(Landroid/content/Context;I)V", "COUNT_DOWN_TIME", "", "TAG", "", "TEXT_EXAMPLES", "", "[Ljava/lang/String;", "businessResponseListener", "Lkotlin/Function2;", "", "closeBtn", "Landroid/view/View;", "cmdRequestListener", "Lkotlin/Function1;", "Lcom/pudutech/voiceinteraction/component/cmd/CmdBean;", "countdown", "Lio/reactivex/disposables/Disposable;", "exampleLayout", "Landroid/view/ViewGroup;", "exampleTextSwitcher", "Landroid/widget/TextSwitcher;", "exampleTextSwitcherAnimation", "Lcom/pudutech/bumblebee/robot_ui/widget/help/CTextSwitcherAnimation;", "loadingView", "Lcom/pudutech/widget/loading/CLoadingView;", "mPosterDisplayDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/PosterDisplayDialog;", "mState", "onDialogDismissListener", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/VoiceInteractionDialog$OnDialogDismissListener;", "reWakeupTipsTextView", "Landroid/widget/TextView;", "requestLayout", "requestTextView", "responseTextView", "resultAnswerListener", "Lkotlin/Function3;", "resultQuestionListener", "", "statusChangeListener", "Lcom/pudutech/voiceinteraction/component/config/VoiceInteractionState;", "titleText", "view", "volumeChangeListener", "volumeChangeView", "Lcom/pudutech/bumblebee/robot_ui/widget/VolumeChangeView;", "wakeupListener", "Lcom/pudutech/voiceinteraction/component/config/WakeupInfo;", "dismiss", "initData", "initWidget", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "reset", "resetReWakeupTipsText", "setListeners", "setOnDialogDismissListener", "listener", "setReWakeupTipsTextCountdown", "setRequestText", "requestText", "setRequestTextColor", TypedValues.Custom.S_COLOR, "setResponseText", "responseText", "setVolume", "volume", "show", "showHideLoadingView", "isShow", "showHideReWakeupTipsTextView", "showHideVolumeChangeView", "OnDialogDismissListener", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class VoiceInteractionDialog extends Dialog {
    private final long COUNT_DOWN_TIME;
    private final String TAG;
    private String[] TEXT_EXAMPLES;
    private final Function2<String, String, Unit> businessResponseListener;
    private View closeBtn;
    private final Function1<CmdBean, Unit> cmdRequestListener;
    private Disposable countdown;
    private ViewGroup exampleLayout;
    private TextSwitcher exampleTextSwitcher;
    private CTextSwitcherAnimation exampleTextSwitcherAnimation;
    private CLoadingView loadingView;
    private PosterDisplayDialog mPosterDisplayDialog;
    private int mState;
    private OnDialogDismissListener onDialogDismissListener;
    private TextView reWakeupTipsTextView;
    private ViewGroup requestLayout;
    private TextView requestTextView;
    private TextView responseTextView;
    private final Function3<String, String, Integer, Unit> resultAnswerListener;
    private final Function2<String, Boolean, Unit> resultQuestionListener;
    private final Function1<VoiceInteractionState, Unit> statusChangeListener;
    private TextView titleText;
    private View view;
    private final Function1<Integer, Unit> volumeChangeListener;
    private VolumeChangeView volumeChangeView;
    private final Function1<WakeupInfo, Unit> wakeupListener;

    /* compiled from: VoiceInteractionDialog.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/VoiceInteractionDialog$OnDialogDismissListener;", "", "onDismiss", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public interface OnDialogDismissListener {
        void onDismiss();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public VoiceInteractionDialog(Context context) {
        this(context, C5767R.style.style_common_dialog);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VoiceInteractionDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "VoiceInteractionDialog";
        this.COUNT_DOWN_TIME = 10L;
        this.wakeupListener = new Function1<WakeupInfo, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceInteractionDialog$wakeupListener$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WakeupInfo wakeupInfo) {
                invoke2(wakeupInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WakeupInfo wakeupInfo) {
                String str;
                Disposable disposable;
                str = VoiceInteractionDialog.this.TAG;
                Pdlog.m3273d(str, "wakeupListener WakeupInfo: " + wakeupInfo + " VoiceInteractionTrack.sessionId:" + VoiceInteractionTrack.INSTANCE.getSessionId());
                VoiceInteractionTrack.INSTANCE.onCreateTask(VoiceInteractionTrack.INSTANCE.getPageKey(), VoiceInteractionTrack.INSTANCE.getSessionId());
                VoiceInteractionTrack.INSTANCE.onWakeup(WakeUpType.VOICE);
                disposable = VoiceInteractionDialog.this.countdown;
                if (disposable != null) {
                    disposable.dispose();
                }
                VoicePlayer.INSTANCE.play(new VoiceTask(-1L, new GeneralVoiceProperty(0L, AiVoiceManager.INSTANCE.getWAKEUP_VOICE(), 3)));
                AiVoiceManager.INSTANCE.cancelTTS();
                VoiceInteractionDialog.this.showHideVolumeChangeView(true);
                VoiceInteractionDialog.this.showHideReWakeupTipsTextView(false);
                VoiceInteractionDialog.this.showHideLoadingView(false);
                VoiceInteractionDialog.this.setResponseText("");
                VoiceInteractionDialog voiceInteractionDialog = VoiceInteractionDialog.this;
                voiceInteractionDialog.setRequestText(voiceInteractionDialog.getContext().getString(C5767R.string.pdStr14_5));
                VoiceInteractionDialog.this.setRequestTextColor(Color.parseColor("#fffefe"));
                VoiceInteractionDialog.this.resetReWakeupTipsText();
            }
        };
        this.resultQuestionListener = new Function2<String, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceInteractionDialog$resultQuestionListener$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) {
                invoke(str, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(String str, boolean z) {
                String str2;
                VoiceInteractionTrack.INSTANCE.onQuestion(str != null ? str : "");
                str2 = VoiceInteractionDialog.this.TAG;
                Pdlog.m3273d(str2, "resultQuestionListener : " + str + ',' + z + ' ');
                VoiceInteractionDialog.this.setRequestText(str);
            }
        };
        this.resultAnswerListener = new Function3<String, String, Integer, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceInteractionDialog$resultAnswerListener$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(String str, String str2, Integer num) {
                invoke(str, str2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(String str, String str2, int i2) {
                String str3;
                int i3;
                str3 = VoiceInteractionDialog.this.TAG;
                Pdlog.m3273d(str3, "resultAnswerListener : " + str + ',' + str2 + ',' + i2);
                i3 = VoiceInteractionDialog.this.mState;
                if (i3 != -3) {
                    VoiceInteractionDialog.this.mState = i2;
                }
                VoiceInteractionTrack.INSTANCE.onAnswer(str != null ? str : "");
                VoiceInteractionDialog.this.setResponseText(str);
                VoiceInteractionDialog.this.showHideVolumeChangeView(false);
                VoiceInteractionDialog.this.showHideReWakeupTipsTextView(true);
                VoiceInteractionDialog.this.showHideLoadingView(false);
            }
        };
        this.businessResponseListener = new Function2<String, String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceInteractionDialog$businessResponseListener$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
                invoke2(str, str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String imgUrl, String text) {
                int i2;
                PosterDisplayDialog posterDisplayDialog;
                PosterDisplayDialog posterDisplayDialog2;
                PosterDisplayDialog posterDisplayDialog3;
                String str;
                Intrinsics.checkParameterIsNotNull(imgUrl, "imgUrl");
                Intrinsics.checkParameterIsNotNull(text, "text");
                if (VoiceInteractionDialog.this.getContext() instanceof Activity) {
                    Context context2 = VoiceInteractionDialog.this.getContext();
                    if (context2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
                    }
                    if (((Activity) context2).isFinishing()) {
                        VoiceInteractionDialog.this.mState = -3;
                        str = VoiceInteractionDialog.this.TAG;
                        Pdlog.m3274e(str, "context isFinishing");
                    }
                }
                i2 = VoiceInteractionDialog.this.mState;
                if (i2 != -3) {
                    posterDisplayDialog = VoiceInteractionDialog.this.mPosterDisplayDialog;
                    if (posterDisplayDialog == null) {
                        VoiceInteractionDialog voiceInteractionDialog = VoiceInteractionDialog.this;
                        Context context3 = voiceInteractionDialog.getContext();
                        Intrinsics.checkExpressionValueIsNotNull(context3, "context");
                        voiceInteractionDialog.mPosterDisplayDialog = new PosterDisplayDialog(context3);
                        posterDisplayDialog3 = VoiceInteractionDialog.this.mPosterDisplayDialog;
                        if (posterDisplayDialog3 != null) {
                            posterDisplayDialog3.setOnClose(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceInteractionDialog$businessResponseListener$1.1
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
                                    VoiceInteractionDialog.this.dismiss();
                                }
                            });
                        }
                    }
                    posterDisplayDialog2 = VoiceInteractionDialog.this.mPosterDisplayDialog;
                    if (posterDisplayDialog2 == null || posterDisplayDialog2.isShowing()) {
                        return;
                    }
                    posterDisplayDialog2.show();
                    posterDisplayDialog2.setDisplayData(imgUrl, text);
                }
            }
        };
        this.statusChangeListener = new Function1<VoiceInteractionState, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceInteractionDialog$statusChangeListener$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(VoiceInteractionState voiceInteractionState) {
                invoke2(voiceInteractionState);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(VoiceInteractionState it) {
                String str;
                Disposable disposable;
                int i2;
                long j;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str = VoiceInteractionDialog.this.TAG;
                Pdlog.m3273d(str, "statusChangeListener : " + it);
                switch (it) {
                    case Idle:
                        VoiceInteractionDialog.this.showHideVolumeChangeView(false);
                        VoiceInteractionDialog.this.showHideReWakeupTipsTextView(true);
                        VoiceInteractionDialog.this.showHideLoadingView(false);
                        disposable = VoiceInteractionDialog.this.countdown;
                        if (disposable != null) {
                            disposable.dispose();
                        }
                        i2 = VoiceInteractionDialog.this.mState;
                        if (i2 != 1) {
                            VoiceInteractionDialog voiceInteractionDialog = VoiceInteractionDialog.this;
                            CountdownUtil countdownUtil = CountdownUtil.INSTANCE;
                            j = VoiceInteractionDialog.this.COUNT_DOWN_TIME;
                            voiceInteractionDialog.countdown = countdownUtil.createCountDown(j).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceInteractionDialog$statusChangeListener$1.1
                                @Override // io.reactivex.functions.Consumer
                                public final void accept(Long it2) {
                                    VoiceInteractionDialog voiceInteractionDialog2 = VoiceInteractionDialog.this;
                                    Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                                    voiceInteractionDialog2.setReWakeupTipsTextCountdown(it2.longValue());
                                }
                            }, new Consumer<Throwable>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceInteractionDialog$statusChangeListener$1.2
                                @Override // io.reactivex.functions.Consumer
                                public final void accept(Throwable th) {
                                }
                            }, new Action() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceInteractionDialog$statusChangeListener$1.3
                                @Override // io.reactivex.functions.Action
                                public final void run() {
                                    VoiceInteractionDialog.this.dismiss();
                                }
                            });
                            return;
                        }
                        return;
                    case Eos:
                        VoiceInteractionDialog.this.showHideLoadingView(true);
                        return;
                    case PlayCompleted:
                    case Recording:
                    case Speaking:
                    case BosTimeout:
                    default:
                        return;
                    case ErrorNetWork:
                        VoiceInteractionDialog voiceInteractionDialog2 = VoiceInteractionDialog.this;
                        voiceInteractionDialog2.setResponseText(voiceInteractionDialog2.getContext().getString(C5767R.string.pdStr14_7));
                        VoiceInteractionTrack.INSTANCE.onError(VoiceInteractionState.ErrorNetWork.getState(), VoiceInteractionState.ErrorNetWork.name());
                        return;
                    case Unknown:
                        VoiceInteractionTrack.INSTANCE.onError(VoiceInteractionState.Unknown.getState(), VoiceInteractionState.Unknown.name());
                        return;
                    case ErrorAudioError:
                        VoiceInteractionTrack.INSTANCE.onError(VoiceInteractionState.ErrorAudioError.getState(), VoiceInteractionState.ErrorAudioError.name());
                        return;
                }
            }
        };
        this.volumeChangeListener = new Function1<Integer, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceInteractionDialog$volumeChangeListener$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i2) {
                String str;
                str = VoiceInteractionDialog.this.TAG;
                Pdlog.m3273d(str, "volumeChangeListener : " + i2 + ' ');
                VoiceInteractionDialog.this.setVolume(i2);
            }
        };
        this.cmdRequestListener = new Function1<CmdBean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceInteractionDialog$cmdRequestListener$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(CmdBean cmdBean) {
                invoke2(cmdBean);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(CmdBean cmdBean) {
                String str;
                str = VoiceInteractionDialog.this.TAG;
                Pdlog.m3273d(str, "cmdRequestListener cmdBean:" + cmdBean + ",isShowing:" + VoiceInteractionDialog.this.isShowing() + ' ');
                if (VoiceInteractionDialog.this.isShowing()) {
                    VoiceInteractionDialog.this.dismiss();
                }
            }
        };
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View inflate = LayoutInflater.from(getContext()).inflate(C5767R.layout.dialog_voice_interaction, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(cont…_voice_interaction, null)");
        this.view = inflate;
        requestWindowFeature(1);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(17);
            window.setAttributes(attributes);
            View view = this.view;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            }
            setContentView(view);
        }
        setCancelable(false);
        initData();
        initWidget();
        setListeners();
    }

    private final void initData() {
        if (VoiceInteractionKit.INSTANCE.getINSTANCE().getCurrentLanguage() == null || VoiceInteractionKit.INSTANCE.getINSTANCE().getCurrentLanguage() == Language.Chinese) {
            this.TEXT_EXAMPLES = new String[]{'\"' + getContext().getString(C5767R.string.pdStr14_2) + '\"', '\"' + getContext().getString(C5767R.string.pdStr14_3) + '\"', '\"' + getContext().getString(C5767R.string.pdStr14_4) + '\"'};
            return;
        }
        this.TEXT_EXAMPLES = new String[]{'\"' + getContext().getString(C4188R.string.greeter_bella_hello) + '\"', '\"' + getContext().getString(C4188R.string.pdStr14_3) + '\"', '\"' + getContext().getString(C4188R.string.greeter_ask_from) + '\"'};
    }

    private final void initWidget() {
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        View findViewById = view.findViewById(C5767R.id.ts_example);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.ts_example)");
        this.exampleTextSwitcher = (TextSwitcher) findViewById;
        TextSwitcher textSwitcher = this.exampleTextSwitcher;
        if (textSwitcher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("exampleTextSwitcher");
        }
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceInteractionDialog$initWidget$1
            @Override // android.widget.ViewSwitcher.ViewFactory
            public final TextView makeView() {
                TextView textView = new TextView(VoiceInteractionDialog.this.getContext());
                textView.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
                textView.setMaxLines(1);
                textView.setGravity(1);
                textView.setEllipsize(TextUtils.TruncateAt.END);
                textView.setTextColor(Color.parseColor("#ffffff"));
                textView.setTextSize(2, 25.0f);
                return textView;
            }
        });
        TextSwitcher textSwitcher2 = this.exampleTextSwitcher;
        if (textSwitcher2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("exampleTextSwitcher");
        }
        String[] strArr = this.TEXT_EXAMPLES;
        if (strArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("TEXT_EXAMPLES");
        }
        this.exampleTextSwitcherAnimation = new CTextSwitcherAnimation(textSwitcher2, strArr);
        CTextSwitcherAnimation cTextSwitcherAnimation = this.exampleTextSwitcherAnimation;
        if (cTextSwitcherAnimation == null) {
            Intrinsics.throwUninitializedPropertyAccessException("exampleTextSwitcherAnimation");
        }
        cTextSwitcherAnimation.setDelayTime(1000);
        CTextSwitcherAnimation cTextSwitcherAnimation2 = this.exampleTextSwitcherAnimation;
        if (cTextSwitcherAnimation2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("exampleTextSwitcherAnimation");
        }
        cTextSwitcherAnimation2.setDuration(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        CTextSwitcherAnimation cTextSwitcherAnimation3 = this.exampleTextSwitcherAnimation;
        if (cTextSwitcherAnimation3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("exampleTextSwitcherAnimation");
        }
        cTextSwitcherAnimation3.create();
        View view2 = this.view;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        this.volumeChangeView = (VolumeChangeView) view2.findViewById(C5767R.id.vcv);
        View view3 = this.view;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        this.responseTextView = (TextView) view3.findViewById(C5767R.id.tv_response);
        View view4 = this.view;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        this.requestTextView = (TextView) view4.findViewById(C5767R.id.tv_request);
        View view5 = this.view;
        if (view5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        this.loadingView = (CLoadingView) view5.findViewById(C5767R.id.loading_view);
        View view6 = this.view;
        if (view6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        View findViewById2 = view6.findViewById(C5767R.id.btn_close);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById(R.id.btn_close)");
        this.closeBtn = findViewById2;
        View view7 = this.view;
        if (view7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        this.reWakeupTipsTextView = (TextView) view7.findViewById(C5767R.id.tv_re_wakeup_tips);
        showHideVolumeChangeView(true);
        showHideReWakeupTipsTextView(false);
        setRequestText(getContext().getString(C5767R.string.pdStr14_5));
        setRequestTextColor(Color.parseColor("#fffefe"));
        resetReWakeupTipsText();
        View view8 = this.view;
        if (view8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        View findViewById3 = view8.findViewById(C5767R.id.tv_title);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "view.findViewById(R.id.tv_title)");
        this.titleText = (TextView) findViewById3;
        View view9 = this.view;
        if (view9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        View findViewById4 = view9.findViewById(C5767R.id.layout_example);
        Intrinsics.checkExpressionValueIsNotNull(findViewById4, "view.findViewById(R.id.layout_example)");
        this.exampleLayout = (ViewGroup) findViewById4;
        View view10 = this.view;
        if (view10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        View findViewById5 = view10.findViewById(C5767R.id.layout_request);
        Intrinsics.checkExpressionValueIsNotNull(findViewById5, "view.findViewById(R.id.layout_request)");
        this.requestLayout = (ViewGroup) findViewById5;
    }

    private final void setListeners() {
        View view = this.closeBtn;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("closeBtn");
        }
        view.setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceInteractionDialog$setListeners$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                VoiceInteractionDialog.this.dismiss();
            }
        }, 3, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setVolume(int volume) {
        VolumeChangeView volumeChangeView = this.volumeChangeView;
        if (volumeChangeView != null) {
            volumeChangeView.setVolume(volume);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showHideVolumeChangeView(boolean isShow) {
        VolumeChangeView volumeChangeView = this.volumeChangeView;
        if (volumeChangeView != null) {
            volumeChangeView.setVisibility(isShow ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setResponseText(String responseText) {
        TextView textView = this.responseTextView;
        if (textView != null) {
            textView.setText(responseText);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRequestText(String requestText) {
        TextView textView = this.requestTextView;
        if (textView != null) {
            textView.setText(requestText);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRequestTextColor(int color) {
        TextView textView = this.requestTextView;
        if (textView != null) {
            textView.setTextColor(color);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showHideLoadingView(boolean isShow) {
        if (isShow) {
            CLoadingView cLoadingView = this.loadingView;
            if (cLoadingView != null) {
                cLoadingView.show();
                return;
            }
            return;
        }
        CLoadingView cLoadingView2 = this.loadingView;
        if (cLoadingView2 != null) {
            cLoadingView2.hide();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showHideReWakeupTipsTextView(boolean isShow) {
        TextView textView = this.reWakeupTipsTextView;
        if (textView != null) {
            textView.setVisibility(isShow ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setReWakeupTipsTextCountdown(long countdown) {
        TextView textView = this.reWakeupTipsTextView;
        if (textView != null) {
            textView.setText(getContext().getString(C5767R.string.pdStr14_6) + '(' + countdown + ')');
        }
    }

    @Override // android.app.Dialog
    public void show() {
        Window window = getWindow();
        if (window == null) {
            Intrinsics.throwNpe();
        }
        NavigationBarUtil.focusNotAle(window);
        super.show();
        Window window2 = getWindow();
        if (window2 == null) {
            Intrinsics.throwNpe();
        }
        NavigationBarUtil.hideNavigationBar(window2);
        Window window3 = getWindow();
        if (window3 == null) {
            Intrinsics.throwNpe();
        }
        NavigationBarUtil.clearFocusNotAle(window3);
        Window window4 = getWindow();
        if (window4 != null) {
            window4.setLayout(-1, -1);
        }
        reset();
        AiVoiceManager.INSTANCE.addWakeupListener(this.wakeupListener);
        AiVoiceManager.INSTANCE.addVolumeChangeListener(this.volumeChangeListener);
        AiVoiceManager.INSTANCE.addStatusListener(this.statusChangeListener);
        AiVoiceManager.INSTANCE.addResultQuestionListener(this.resultQuestionListener);
        AiVoiceManager.INSTANCE.addResultAnswerListener(this.resultAnswerListener);
        AiVoiceManager.INSTANCE.addBusinessResponseListener(this.businessResponseListener);
        AiVoiceManager.INSTANCE.addCmdResponseListener(this.cmdRequestListener);
        AiVoiceTriggerHelper.INSTANCE.onAiVoiceDialogShow();
        VoicePlayer.INSTANCE.play(new VoiceTask(-1L, new GeneralVoiceProperty(0L, AiVoiceManager.INSTANCE.getWAKEUP_VOICE(), 3)));
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        AiVoiceManager.INSTANCE.removeWakeupListener(this.wakeupListener);
        AiVoiceManager.INSTANCE.removeVolumeChangeListener(this.volumeChangeListener);
        AiVoiceManager.INSTANCE.removeStatusListener(this.statusChangeListener);
        AiVoiceManager.INSTANCE.removeResultQuestionListener(this.resultQuestionListener);
        AiVoiceManager.INSTANCE.removeResultAnswerListener(this.resultAnswerListener);
        AiVoiceManager.INSTANCE.removeBusinessResponseListener(this.businessResponseListener);
        AiVoiceManager.INSTANCE.removeCmdResponseListener(this.cmdRequestListener);
        AiVoiceManager.INSTANCE.cancelTTS();
        AiVoiceTriggerHelper.INSTANCE.onAiVoiceDialogDismiss();
        OnDialogDismissListener onDialogDismissListener = this.onDialogDismissListener;
        if (onDialogDismissListener != null) {
            onDialogDismissListener.onDismiss();
        }
        Disposable disposable = this.countdown;
        if (disposable != null) {
            disposable.dispose();
        }
        AiVoiceManager.INSTANCE.cancelCurrentRound();
        CTextSwitcherAnimation cTextSwitcherAnimation = this.exampleTextSwitcherAnimation;
        if (cTextSwitcherAnimation == null) {
            Intrinsics.throwUninitializedPropertyAccessException("exampleTextSwitcherAnimation");
        }
        cTextSwitcherAnimation.release();
        super.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetReWakeupTipsText() {
        TextView textView = this.reWakeupTipsTextView;
        if (textView != null) {
            textView.setText(getContext().getString(C5767R.string.pdStr14_6));
        }
    }

    private final void reset() {
        setRequestText(getContext().getString(C5767R.string.pdStr14_5));
        setRequestTextColor(Color.parseColor("#fffefe"));
        TextView textView = this.responseTextView;
        if (textView != null) {
            textView.setText((CharSequence) null);
        }
        TextView textView2 = this.reWakeupTipsTextView;
        if (textView2 != null) {
            textView2.setText((CharSequence) null);
        }
        showHideLoadingView(false);
        showHideReWakeupTipsTextView(false);
        resetReWakeupTipsText();
        showHideVolumeChangeView(true);
    }

    public final void setOnDialogDismissListener(OnDialogDismissListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.onDialogDismissListener = listener;
    }
}
