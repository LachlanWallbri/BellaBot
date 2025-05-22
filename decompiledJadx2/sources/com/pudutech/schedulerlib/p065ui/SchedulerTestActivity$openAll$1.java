package com.pudutech.schedulerlib.p065ui;

import android.app.Application;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.schedulerlib.C5725R;
import com.pudutech.schedulerlib.ScheduleController;
import com.pudutech.schedulerlib.connection.PeanutEspService;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.TimeoutKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: SchedulerTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.ui.SchedulerTestActivity$openAll$1", m3970f = "SchedulerTestActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes2.dex */
public final class SchedulerTestActivity$openAll$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7494p$;
    final /* synthetic */ SchedulerTestActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SchedulerTestActivity$openAll$1(SchedulerTestActivity schedulerTestActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = schedulerTestActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SchedulerTestActivity$openAll$1 schedulerTestActivity$openAll$1 = new SchedulerTestActivity$openAll$1(this.this$0, completion);
        schedulerTestActivity$openAll$1.f7494p$ = (CoroutineScope) obj;
        return schedulerTestActivity$openAll$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SchedulerTestActivity$openAll$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ScheduleController scheduleController;
        ScheduleController scheduleController2;
        ScheduleController scheduleController3;
        ScheduleController scheduleController4;
        ScheduleController scheduleController5;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        scheduleController = this.this$0.controller;
        if (!scheduleController.udpIsConnected()) {
            scheduleController3 = this.this$0.controller;
            Boolean espIsConnected = scheduleController3.espIsConnected();
            if (espIsConnected == null) {
                Intrinsics.throwNpe();
            }
            if (!espIsConnected.booleanValue()) {
                scheduleController4 = this.this$0.controller;
                Application application = this.this$0.getApplication();
                Intrinsics.checkExpressionValueIsNotNull(application, "this@SchedulerTestActivity.application");
                scheduleController4.init(application, MachineInfo.ESP32Type.EasyNode);
                scheduleController5 = this.this$0.controller;
                Pdlog.m3273d("top", "Version:", scheduleController5.getEspVersion());
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C57482(null), 3, null);
                return Unit.INSTANCE;
            }
        }
        scheduleController2 = this.this$0.controller;
        scheduleController2.destroyScheduler();
        this.this$0.debuger = false;
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C57471(null), 2, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* compiled from: SchedulerTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.schedulerlib.ui.SchedulerTestActivity$openAll$1$1", m3970f = "SchedulerTestActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.schedulerlib.ui.SchedulerTestActivity$openAll$1$1 */
    /* loaded from: classes2.dex */
    public static final class C57471 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7495p$;

        C57471(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C57471 c57471 = new C57471(completion);
            c57471.f7495p$ = (CoroutineScope) obj;
            return c57471;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C57471) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            int i;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            ((Button) SchedulerTestActivity$openAll$1.this.this$0._$_findCachedViewById(C5725R.id.comm_key)).setText("通信开关（已关闭）");
            SchedulerTestActivity$openAll$1.this.this$0.sendIndex = 0;
            TextView send_tv = (TextView) SchedulerTestActivity$openAll$1.this.this$0._$_findCachedViewById(C5725R.id.send_tv);
            Intrinsics.checkExpressionValueIsNotNull(send_tv, "send_tv");
            StringBuilder sb = new StringBuilder();
            sb.append("发送：");
            i = SchedulerTestActivity$openAll$1.this.this$0.sendIndex;
            sb.append(i);
            send_tv.setText(sb.toString());
            SchedulerTestActivity.INSTANCE.setClick(false);
            Button pause_btn = (Button) SchedulerTestActivity$openAll$1.this.this$0._$_findCachedViewById(C5725R.id.pause_btn);
            Intrinsics.checkExpressionValueIsNotNull(pause_btn, "pause_btn");
            pause_btn.setText("开始");
            Button pause_btn2 = (Button) SchedulerTestActivity$openAll$1.this.this$0._$_findCachedViewById(C5725R.id.pause_btn);
            Intrinsics.checkExpressionValueIsNotNull(pause_btn2, "pause_btn");
            pause_btn2.setEnabled(false);
            PeanutEspService.INSTANCE.setReceiveIndex(0);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* compiled from: SchedulerTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.schedulerlib.ui.SchedulerTestActivity$openAll$1$2", m3970f = "SchedulerTestActivity.kt", m3971i = {0}, m3972l = {217}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
    /* renamed from: com.pudutech.schedulerlib.ui.SchedulerTestActivity$openAll$1$2 */
    /* loaded from: classes2.dex */
    public static final class C57482 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7496p$;

        C57482(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C57482 c57482 = new C57482(completion);
            c57482.f7496p$ = (CoroutineScope) obj;
            return c57482;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C57482) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f7496p$;
                SchedulerTestActivity$openAll$1$2$openResult$1 schedulerTestActivity$openAll$1$2$openResult$1 = new SchedulerTestActivity$openAll$1$2$openResult$1(this, null);
                this.L$0 = coroutineScope;
                this.label = 1;
                obj = TimeoutKt.withTimeoutOrNull(5000L, schedulerTestActivity$openAll$1$2$openResult$1, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            if (Intrinsics.areEqual((Boolean) obj, Boxing.boxBoolean(true))) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass1(null), 2, null);
                SchedulerTestActivity$openAll$1.this.this$0.startSend();
            } else {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass2(null), 2, null);
            }
            PeanutEspService.INSTANCE.setReceiveIndex(0);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
          classes6.dex
          classes7.dex
         */
        /* compiled from: SchedulerTestActivity.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
        @DebugMetadata(m3969c = "com.pudutech.schedulerlib.ui.SchedulerTestActivity$openAll$1$2$1", m3970f = "SchedulerTestActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
        /* renamed from: com.pudutech.schedulerlib.ui.SchedulerTestActivity$openAll$1$2$1, reason: invalid class name */
        /* loaded from: classes2.dex */
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;

            /* renamed from: p$ */
            private CoroutineScope f7497p$;

            AnonymousClass1(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(completion);
                anonymousClass1.f7497p$ = (CoroutineScope) obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                ScheduleController scheduleController;
                ScheduleController scheduleController2;
                ScheduleController scheduleController3;
                int i;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                TextView textView = (TextView) SchedulerTestActivity$openAll$1.this.this$0._$_findCachedViewById(C5725R.id.channel_id);
                Intrinsics.checkExpressionValueIsNotNull(textView, "this@SchedulerTestActivity.channel_id");
                scheduleController = SchedulerTestActivity$openAll$1.this.this$0.controller;
                textView.setText(String.valueOf(scheduleController.getCurrentChannel$schedulerlib_release()));
                TextView textView2 = (TextView) SchedulerTestActivity$openAll$1.this.this$0._$_findCachedViewById(C5725R.id.multi_ip);
                Intrinsics.checkExpressionValueIsNotNull(textView2, "this@SchedulerTestActivity.multi_ip");
                scheduleController2 = SchedulerTestActivity$openAll$1.this.this$0.controller;
                textView2.setText(scheduleController2.getMulticastAddress$schedulerlib_release());
                ((Button) SchedulerTestActivity$openAll$1.this.this$0._$_findCachedViewById(C5725R.id.comm_key)).setText("通信开关（已打开）");
                TextView textView3 = (TextView) SchedulerTestActivity$openAll$1.this.this$0._$_findCachedViewById(C5725R.id.map_flag);
                scheduleController3 = SchedulerTestActivity$openAll$1.this.this$0.controller;
                String map_md5 = scheduleController3.getMap_md5();
                if (map_md5 == null) {
                    map_md5 = "未获取到地图";
                }
                textView3.setText(map_md5);
                SchedulerTestActivity$openAll$1.this.this$0.sendIndex = 0;
                TextView send_tv = (TextView) SchedulerTestActivity$openAll$1.this.this$0._$_findCachedViewById(C5725R.id.send_tv);
                Intrinsics.checkExpressionValueIsNotNull(send_tv, "send_tv");
                StringBuilder sb = new StringBuilder();
                sb.append("发送：");
                i = SchedulerTestActivity$openAll$1.this.this$0.sendIndex;
                sb.append(i);
                send_tv.setText(sb.toString());
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
          classes6.dex
          classes7.dex
         */
        /* compiled from: SchedulerTestActivity.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
        @DebugMetadata(m3969c = "com.pudutech.schedulerlib.ui.SchedulerTestActivity$openAll$1$2$2", m3970f = "SchedulerTestActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
        /* renamed from: com.pudutech.schedulerlib.ui.SchedulerTestActivity$openAll$1$2$2, reason: invalid class name */
        /* loaded from: classes2.dex */
        public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;

            /* renamed from: p$ */
            private CoroutineScope f7498p$;

            AnonymousClass2(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(completion);
                anonymousClass2.f7498p$ = (CoroutineScope) obj;
                return anonymousClass2;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                Toast.makeText(SchedulerTestActivity$openAll$1.this.this$0, "打开失败", 0).show();
                return Unit.INSTANCE;
            }
        }
    }
}
