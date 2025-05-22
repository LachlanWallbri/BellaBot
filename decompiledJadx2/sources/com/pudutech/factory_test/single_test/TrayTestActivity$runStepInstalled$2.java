package com.pudutech.factory_test.single_test;

import com.pudutech.bumblebee.robot.aidl.serialize.Pallet;
import com.pudutech.factory_test.single_test.TrayTestActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TrayTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.TrayTestActivity$runStepInstalled$2", m3970f = "TrayTestActivity.kt", m3971i = {0, 0}, m3972l = {126}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "isSuccess"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes.dex */
public final class TrayTestActivity$runStepInstalled$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5207p$;
    final /* synthetic */ TrayTestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TrayTestActivity$runStepInstalled$2(TrayTestActivity trayTestActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = trayTestActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TrayTestActivity$runStepInstalled$2 trayTestActivity$runStepInstalled$2 = new TrayTestActivity$runStepInstalled$2(this.this$0, completion);
        trayTestActivity$runStepInstalled$2.f5207p$ = (CoroutineScope) obj;
        return trayTestActivity$runStepInstalled$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TrayTestActivity$runStepInstalled$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Ref.BooleanRef booleanRef;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5207p$;
            Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
            booleanRef2.element = false;
            C45351 c45351 = new C45351(booleanRef2, null);
            this.L$0 = coroutineScope;
            this.L$1 = booleanRef2;
            this.label = 1;
            if (TimeoutKt.withTimeoutOrNull(20000L, c45351, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            booleanRef = booleanRef2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            booleanRef = (Ref.BooleanRef) this.L$1;
            ResultKt.throwOnFailure(obj);
        }
        this.this$0.setStep(booleanRef.element ? TrayTestActivity.Step.PLACE_REMOVE : TrayTestActivity.Step.FAIL);
        if (this.this$0.getStep() == TrayTestActivity.Step.FAIL) {
            if (this.this$0.getLastEvent().size() != 4) {
                this.this$0.getMTestItem().setFailDescription("软件接收值错误");
            } else {
                List<Pallet> lastEvent = this.this$0.getLastEvent();
                ArrayList arrayList = new ArrayList();
                for (Object obj2 : lastEvent) {
                    if (Boxing.boxBoolean(((Pallet) obj2).getPalletId() <= this.this$0.getTrayCount()).booleanValue()) {
                        arrayList.add(obj2);
                    }
                }
                StringBuilder sb = new StringBuilder();
                ArrayList arrayList2 = arrayList;
                ArrayList arrayList3 = new ArrayList();
                for (Object obj3 : arrayList2) {
                    if (Boxing.boxBoolean(!((Pallet) obj3).getIsInstalled()).booleanValue()) {
                        arrayList3.add(obj3);
                    }
                }
                Iterator it = arrayList3.iterator();
                while (it.hasNext()) {
                    sb.append("\n第" + ((Pallet) it.next()).getPalletId() + "层托盘未安装");
                }
                ArrayList arrayList4 = new ArrayList();
                for (Object obj4 : arrayList2) {
                    if (Boxing.boxBoolean(!((Pallet) obj4).getIsPowerOn()).booleanValue()) {
                        arrayList4.add(obj4);
                    }
                }
                Iterator it2 = arrayList4.iterator();
                while (it2.hasNext()) {
                    sb.append("\n第" + ((Pallet) it2.next()).getPalletId() + "层托盘未供电");
                }
                ArrayList arrayList5 = new ArrayList();
                for (Object obj5 : arrayList2) {
                    if (Boxing.boxBoolean(((Pallet) obj5).getIsPowerOn()).booleanValue()) {
                        arrayList5.add(obj5);
                    }
                }
                Iterator it3 = arrayList5.iterator();
                while (it3.hasNext()) {
                    sb.append("\n第" + ((Pallet) it3.next()).getPalletId() + "层托盘有物体");
                }
                this.this$0.getMTestItem().setFailDescription("有问题的托盘如下" + ((Object) sb));
            }
        }
        this.this$0.FSM();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: TrayTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.TrayTestActivity$runStepInstalled$2$1", m3970f = "TrayTestActivity.kt", m3971i = {0}, m3972l = {128}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull"}, m3975s = {"L$0"})
    /* renamed from: com.pudutech.factory_test.single_test.TrayTestActivity$runStepInstalled$2$1 */
    /* loaded from: classes.dex */
    public static final class C45351 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.BooleanRef $isSuccess;
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5208p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C45351(Ref.BooleanRef booleanRef, Continuation continuation) {
            super(2, continuation);
            this.$isSuccess = booleanRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C45351 c45351 = new C45351(this.$isSuccess, completion);
            c45351.f5208p$ = (CoroutineScope) obj;
            return c45351;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C45351) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        /* JADX WARN: Removed duplicated region for block: B:34:0x002f A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0040  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x002d -> B:5:0x0030). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r8.label
                r2 = 1
                if (r1 == 0) goto L1c
                if (r1 != r2) goto L14
                java.lang.Object r1 = r8.L$0
                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                kotlin.ResultKt.throwOnFailure(r9)
                r9 = r8
                goto L30
            L14:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L1c:
                kotlin.ResultKt.throwOnFailure(r9)
                kotlinx.coroutines.CoroutineScope r9 = r8.f5208p$
                r1 = r9
                r9 = r8
            L23:
                r3 = 100
                r9.L$0 = r1
                r9.label = r2
                java.lang.Object r3 = kotlinx.coroutines.DelayKt.delay(r3, r9)
                if (r3 != r0) goto L30
                return r0
            L30:
                com.pudutech.factory_test.single_test.TrayTestActivity$runStepInstalled$2 r3 = com.pudutech.factory_test.single_test.TrayTestActivity$runStepInstalled$2.this
                com.pudutech.factory_test.single_test.TrayTestActivity r3 = r3.this$0
                java.util.List r3 = r3.getLastEvent()
                int r3 = r3.size()
                r4 = 4
                if (r3 == r4) goto L40
                goto L23
            L40:
                com.pudutech.factory_test.single_test.TrayTestActivity$runStepInstalled$2 r3 = com.pudutech.factory_test.single_test.TrayTestActivity$runStepInstalled$2.this
                com.pudutech.factory_test.single_test.TrayTestActivity r3 = r3.this$0
                java.util.List r3 = r3.getLastEvent()
                java.lang.Iterable r3 = (java.lang.Iterable) r3
                java.util.Iterator r3 = r3.iterator()
                r5 = 1
            L4f:
                boolean r6 = r3.hasNext()
                if (r6 == 0) goto L75
                java.lang.Object r6 = r3.next()
                com.pudutech.bumblebee.robot.aidl.serialize.Pallet r6 = (com.pudutech.bumblebee.robot.aidl.serialize.Pallet) r6
                int r7 = r6.getPalletId()
                if (r7 >= r4) goto L4f
                boolean r7 = r6.getIsInstalled()
                if (r7 == 0) goto L73
                boolean r7 = r6.getIsPowerOn()
                if (r7 == 0) goto L73
                boolean r6 = r6.getIsPlaced()
                if (r6 == 0) goto L4f
            L73:
                r5 = 0
                goto L4f
            L75:
                if (r5 == 0) goto L23
                kotlin.jvm.internal.Ref$BooleanRef r9 = r9.$isSuccess
                r9.element = r2
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.pudutech.factory_test.single_test.TrayTestActivity$runStepInstalled$2.C45351.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
}
