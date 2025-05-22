package com.pudutech.schedulerlib.p065ui;

import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;
import com.pudutech.base.Pdlog;
import com.pudutech.schedulerlib.C5725R;
import com.pudutech.schedulerlib.ScheduleConstant;
import com.pudutech.schedulerlib.ScheduleController;
import com.pudutech.schedulerlib.p065ui.ChannelListAdapter;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: SchedulerTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "code", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class SchedulerTestActivity$choiceChannel$1 extends Lambda implements Function1<Integer, Unit> {
    final /* synthetic */ SchedulerTestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SchedulerTestActivity$choiceChannel$1(SchedulerTestActivity schedulerTestActivity) {
        super(1);
        this.this$0 = schedulerTestActivity;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
        invoke(num.intValue());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes7.dex
     */
    /* compiled from: SchedulerTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.schedulerlib.ui.SchedulerTestActivity$choiceChannel$1$1", m3970f = "SchedulerTestActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.schedulerlib.ui.SchedulerTestActivity$choiceChannel$1$1 */
    /* loaded from: classes2.dex */
    public static final class C57431 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7491p$;

        C57431(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C57431 c57431 = new C57431(completion);
            c57431.f7491p$ = (CoroutineScope) obj;
            return c57431;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C57431) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ScheduleController scheduleController;
            ChannelChoiceBottomSheetDialog channelChoiceBottomSheetDialog;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            scheduleController = SchedulerTestActivity$choiceChannel$1.this.this$0.controller;
            if (!scheduleController.udpIsConnected()) {
                Toast.makeText(SchedulerTestActivity$choiceChannel$1.this.this$0, "please open udp connection.", 0).show();
                return Unit.INSTANCE;
            }
            SchedulerTestActivity$choiceChannel$1.this.this$0.dialog = new ChannelChoiceBottomSheetDialog(SchedulerTestActivity$choiceChannel$1.this.this$0, new ChannelListAdapter.ButtonTouchCallback() { // from class: com.pudutech.schedulerlib.ui.SchedulerTestActivity.choiceChannel.1.1.1
                @Override // com.pudutech.schedulerlib.ui.ChannelListAdapter.ButtonTouchCallback
                public void touch(int channelId) {
                    ScheduleController scheduleController2;
                    ScheduleController scheduleController3;
                    ChannelChoiceBottomSheetDialog channelChoiceBottomSheetDialog2;
                    int i = channelId + 1;
                    int i2 = i + 1;
                    scheduleController2 = SchedulerTestActivity$choiceChannel$1.this.this$0.controller;
                    Integer currentChannel$schedulerlib_release = scheduleController2.getCurrentChannel$schedulerlib_release();
                    if (currentChannel$schedulerlib_release == null || i2 != currentChannel$schedulerlib_release.intValue()) {
                        scheduleController3 = SchedulerTestActivity$choiceChannel$1.this.this$0.controller;
                        scheduleController3.resetChannel$schedulerlib_release(i);
                        TextView textView = (TextView) SchedulerTestActivity$choiceChannel$1.this.this$0._$_findCachedViewById(C5725R.id.channel_id);
                        Intrinsics.checkExpressionValueIsNotNull(textView, "this@SchedulerTestActivity.channel_id");
                        textView.setText(String.valueOf(i2));
                        TextView textView2 = (TextView) SchedulerTestActivity$choiceChannel$1.this.this$0._$_findCachedViewById(C5725R.id.multi_ip);
                        Intrinsics.checkExpressionValueIsNotNull(textView2, "this@SchedulerTestActivity.multi_ip");
                        textView2.setText(ScheduleConstant.INSTANCE.getMULTICAST_ADDRESS_LIST()[i]);
                        Pdlog.m3273d(SchedulerTestActivity$choiceChannel$1.this.this$0.getTAG(), "reset channel id to " + i2);
                    }
                    SharedPreferences.Editor edit = SchedulerTestActivity$choiceChannel$1.this.this$0.getSharedPreferences("mirsdk", 0).edit();
                    edit.putInt(ScheduleConstant.PREFERENCE_KEY, i);
                    edit.apply();
                    channelChoiceBottomSheetDialog2 = SchedulerTestActivity$choiceChannel$1.this.this$0.dialog;
                    if (channelChoiceBottomSheetDialog2 != null) {
                        channelChoiceBottomSheetDialog2.dismiss();
                    }
                }
            });
            channelChoiceBottomSheetDialog = SchedulerTestActivity$choiceChannel$1.this.this$0.dialog;
            if (channelChoiceBottomSheetDialog != null) {
                channelChoiceBottomSheetDialog.show();
            }
            return Unit.INSTANCE;
        }
    }

    public final void invoke(int i) {
        if (i != 0) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C57431(null), 2, null);
    }
}
