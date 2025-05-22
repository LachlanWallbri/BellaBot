package com.pudutech.bumblebee.presenter.monitor_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.core_devices_task.monitor_task.protocol.Error;
import com.pudutech.bumblebee.presenter.monitor_task.MonitorPresenter;
import com.pudutech.bumblebee.presenter.report_cloud.task.ReportErrorProcess;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MonitorPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.monitor_task.MonitorPresenter$doJob$job$1", m3970f = "MonitorPresenter.kt", m3971i = {0}, m3972l = {321}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
public final class MonitorPresenter$doJob$job$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $errorType;
    final /* synthetic */ String $key;
    final /* synthetic */ Function1 $method;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4696p$;
    final /* synthetic */ MonitorPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MonitorPresenter$doJob$job$1(MonitorPresenter monitorPresenter, Function1 function1, String str, String str2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = monitorPresenter;
        this.$method = function1;
        this.$key = str;
        this.$errorType = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MonitorPresenter$doJob$job$1 monitorPresenter$doJob$job$1 = new MonitorPresenter$doJob$job$1(this.this$0, this.$method, this.$key, this.$errorType, completion);
        monitorPresenter$doJob$job$1.f4696p$ = (CoroutineScope) obj;
        return monitorPresenter$doJob$job$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MonitorPresenter$doJob$job$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CopyOnWriteArrayList copyOnWriteArrayList;
        CopyOnWriteArrayList copyOnWriteArrayList2;
        CopyOnWriteArrayList copyOnWriteArrayList3;
        CopyOnWriteArrayList copyOnWriteArrayList4;
        CopyOnWriteArrayList copyOnWriteArrayList5;
        Error newErrorRebootFail;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4696p$;
            Function1 function1 = this.$method;
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = function1.invoke(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        final boolean booleanValue = ((Boolean) obj).booleanValue();
        Pdlog.m3275i(this.this$0.getTAG(), this.$key + " result=" + booleanValue);
        copyOnWriteArrayList = this.this$0.wait;
        ArrayList<Error> arrayList = new ArrayList();
        for (Object obj2 : copyOnWriteArrayList) {
            if (Boxing.boxBoolean(Intrinsics.areEqual(((Error) obj2).error_type, this.$errorType)).booleanValue()) {
                arrayList.add(obj2);
            }
        }
        for (Error it : arrayList) {
            ReportErrorProcess reportErrorProcess = ReportErrorProcess.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            reportErrorProcess.toCloud(it, "auto resume " + booleanValue);
        }
        copyOnWriteArrayList.removeIf(new Predicate<Error>() { // from class: com.pudutech.bumblebee.presenter.monitor_task.MonitorPresenter$doJob$job$1$invokeSuspend$$inlined$apply$lambda$1
            @Override // java.util.function.Predicate
            public final boolean test(Error error) {
                return Intrinsics.areEqual(error.error_type, MonitorPresenter$doJob$job$1.this.$errorType);
            }
        });
        String tag = this.this$0.getTAG();
        StringBuilder sb = new StringBuilder();
        sb.append("size=");
        copyOnWriteArrayList2 = this.this$0.autoResumeJobs;
        sb.append(copyOnWriteArrayList2.size());
        sb.append(" before remove");
        Pdlog.m3273d(tag, sb.toString());
        copyOnWriteArrayList3 = this.this$0.autoResumeJobs;
        copyOnWriteArrayList3.removeIf(new Predicate<MonitorPresenter.JobInfo>() { // from class: com.pudutech.bumblebee.presenter.monitor_task.MonitorPresenter$doJob$job$1.2
            @Override // java.util.function.Predicate
            public final boolean test(MonitorPresenter.JobInfo jobInfo) {
                return Intrinsics.areEqual(jobInfo.getName(), MonitorPresenter$doJob$job$1.this.$key);
            }
        });
        String tag2 = this.this$0.getTAG();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("size=");
        copyOnWriteArrayList4 = this.this$0.autoResumeJobs;
        sb2.append(copyOnWriteArrayList4.size());
        sb2.append(" after remove");
        Pdlog.m3273d(tag2, sb2.toString());
        if (!booleanValue) {
            copyOnWriteArrayList5 = this.this$0.rebootPower;
            newErrorRebootFail = this.this$0.newErrorRebootFail(this.$errorType);
            copyOnWriteArrayList5.add(newErrorRebootFail);
        }
        this.this$0.checkAgainAfterAutoResume();
        return Unit.INSTANCE;
    }
}
