package com.pudutech.bumblebee.presenter.monitor_task;

import android.os.SystemClock;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.behavior.Behavior;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.core_devices_task.monitor_task.MonitorEvent;
import com.pudutech.bumblebee.business.core_devices_task.monitor_task.MonitorListener;
import com.pudutech.bumblebee.business.core_devices_task.monitor_task.protocol.Error;
import com.pudutech.bumblebee.business.core_devices_task.monitor_task.protocol.Errors;
import com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.business.utils.FactoryTestHelper;
import com.pudutech.bumblebee.presenter.monitor_task.MonitorContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.report_cloud.task.ReportError;
import com.pudutech.bumblebee.presenter.report_cloud.task.ReportErrorProcess;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveError;
import com.pudutech.robot.module.report.protocol.FallDownType;
import com.pudutech.robot.module.report.task.ReportFallDown;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* compiled from: MonitorPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0087\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0013\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0002BCB\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020 H\u0002J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0006H\u0002J\u0016\u0010%\u001a\u00020 2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u000b0'H\u0002J\u0010\u0010(\u001a\u00020 2\u0006\u0010)\u001a\u00020\u0006H\u0002J\u0010\u0010*\u001a\u00020 2\u0006\u0010+\u001a\u00020,H\u0002J>\u0010-\u001a\u00020 2\u0006\u0010$\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\u00062\u001c\u0010/\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020#01\u0012\u0006\u0012\u0004\u0018\u00010200H\u0002ø\u0001\u0000¢\u0006\u0002\u00103J\u0010\u00104\u001a\u00020\u000b2\u0006\u00105\u001a\u00020\u0006H\u0002J\b\u00106\u001a\u00020 H\u0014J\u0010\u00107\u001a\u00020 2\u0006\u00108\u001a\u00020\u000bH\u0002J\b\u00109\u001a\u00020 H\u0002J\u0010\u0010:\u001a\u00020 2\u0006\u0010;\u001a\u00020\u0002H\u0016J(\u0010<\u001a\u00020 2\u0006\u0010=\u001a\u00020\u00182\u0016\u0010>\u001a\u0012\u0012\u0004\u0012\u00020\u000b0?j\b\u0012\u0004\u0012\u00020\u000b`@H\u0002J\b\u0010A\u001a\u00020 H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0014R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0019\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u001b0\u001aj\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u001b`\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006D"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/monitor_task/MonitorPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/monitor_task/MonitorContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/monitor_task/MonitorContract$PresenterInterface;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "all", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/protocol/Error;", "getAll", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "setAll", "(Ljava/util/concurrent/CopyOnWriteArrayList;)V", "autoResumeJobs", "Lcom/pudutech/bumblebee/presenter/monitor_task/MonitorPresenter$JobInfo;", "monitorListener", "com/pudutech/bumblebee/presenter/monitor_task/MonitorPresenter$monitorListener$1", "Lcom/pudutech/bumblebee/presenter/monitor_task/MonitorPresenter$monitorListener$1;", "rebootPower", "restartSoftware", "suggestion", "Lcom/pudutech/bumblebee/presenter/monitor_task/MonitorContract$Suggestion;", "timesCheckers", "Ljava/util/HashMap;", "Lcom/pudutech/bumblebee/presenter/monitor_task/MonitorPresenter$TimesChecker;", "Lkotlin/collections/HashMap;", "userTry", "wait", "cancelThisTime", "", "checkAgainAfterAutoResume", "checkJobAlreadyExist", "", TransferTable.COLUMN_KEY, "classify", "needProcess", "", "cleanAll", "process", "collectError", "errors", "Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/protocol/Errors;", "doJob", "errorType", "method", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "newErrorRebootFail", "string", "onViewRemoved", "processAutoResumeBackground", "it", "processIt", "replaceView", "view", "showOnUI", "s", "list", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "tryResume", "JobInfo", "TimesChecker", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MonitorPresenter extends BaseOneViewPresenter<MonitorContract.ViewInterface> implements MonitorContract.PresenterInterface {
    private final String TAG = "MonitorPresenter";
    private CopyOnWriteArrayList<Error> all = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Error> rebootPower = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Error> restartSoftware = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Error> userTry = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Error> wait = new CopyOnWriteArrayList<>();
    private final MonitorPresenter$monitorListener$1 monitorListener = new MonitorListener() { // from class: com.pudutech.bumblebee.presenter.monitor_task.MonitorPresenter$monitorListener$1
        @Override // com.pudutech.bumblebee.business.core_devices_task.monitor_task.MonitorListener
        public void onEvent(MonitorEvent event) {
            MonitorContract.Suggestion suggestion;
            MonitorContract.Suggestion suggestion2;
            MonitorContract.Suggestion suggestion3;
            MonitorContract.Suggestion suggestion4;
            CopyOnWriteArrayList copyOnWriteArrayList;
            CopyOnWriteArrayList copyOnWriteArrayList2;
            CopyOnWriteArrayList<Error> copyOnWriteArrayList3;
            ArrayList<Error> arrayList;
            Intrinsics.checkParameterIsNotNull(event, "event");
            Pdlog.m3273d(MonitorPresenter.this.getTAG(), "onEvent " + event);
            Errors errors = event.getErrors();
            if (errors != null) {
                MonitorPresenter.this.collectError(errors);
            }
            if (!event.getIsError()) {
                MonitorPresenter.this.cleanAll("robot resume");
                MonitorPresenter.this.showOnUI(MonitorContract.Suggestion.NO_ERROR_LEFT, new ArrayList());
                return;
            }
            Errors errors2 = event.getErrors();
            if (errors2 != null) {
                ArrayList<Error> arrayList2 = errors2.list;
                Intrinsics.checkExpressionValueIsNotNull(arrayList2, "it.list");
                for (Error error : arrayList2) {
                    if (error.error_type.equals(StrConstant.FallDropOccur)) {
                        ReportFallDown reportFallDown = new ReportFallDown();
                        FallDownType data = reportFallDown.getFallDownReport().getData();
                        String str = error.level;
                        Intrinsics.checkExpressionValueIsNotNull(str, "it.level");
                        if (str == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        String lowerCase = str.toLowerCase();
                        Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
                        data.setLevel(lowerCase);
                        reportFallDown.report();
                        Pdlog.m3273d(MonitorPresenter.this.getTAG(), "FallDropOccur 上传report");
                    }
                }
                ReportError.INSTANCE.toCloud(errors2);
            }
            Errors errors3 = event.getErrors();
            if (errors3 != null && (arrayList = errors3.list) != null) {
                MonitorPresenter.this.getAll().addAll(arrayList);
                ArrayList arrayList3 = new ArrayList();
                for (Object obj : arrayList) {
                    Error error2 = (Error) obj;
                    if (Intrinsics.areEqual(error2.level, MoveError.LEVEL_ERROR) || Intrinsics.areEqual(error2.level, MoveError.LEVEL_FATAL) || Intrinsics.areEqual(error2.level, MoveError.LEVEL_EVENT)) {
                        arrayList3.add(obj);
                    }
                }
                ArrayList arrayList4 = arrayList3;
                if (arrayList4.isEmpty()) {
                    Pdlog.m3273d(MonitorPresenter.this.getTAG(), "is all waring. no need to process");
                    return;
                } else {
                    FactoryTestHelper.INSTANCE.recordError();
                    MonitorPresenter.this.classify(arrayList4);
                }
            }
            suggestion = MonitorPresenter.this.suggestion;
            if (suggestion != MonitorContract.Suggestion.RESTART) {
                suggestion3 = MonitorPresenter.this.suggestion;
                if (suggestion3 != MonitorContract.Suggestion.REBOOT) {
                    suggestion4 = MonitorPresenter.this.suggestion;
                    if (suggestion4 == MonitorContract.Suggestion.NO_ERROR_LEFT) {
                        MonitorPresenter.this.processIt();
                    }
                    copyOnWriteArrayList = MonitorPresenter.this.rebootPower;
                    if (copyOnWriteArrayList.isEmpty()) {
                        copyOnWriteArrayList2 = MonitorPresenter.this.restartSoftware;
                        if (copyOnWriteArrayList2.isEmpty()) {
                            copyOnWriteArrayList3 = MonitorPresenter.this.wait;
                            for (Error it : copyOnWriteArrayList3) {
                                MonitorPresenter monitorPresenter = MonitorPresenter.this;
                                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                                monitorPresenter.processAutoResumeBackground(it);
                            }
                        }
                    }
                    Pdlog.m3273d(MonitorPresenter.this.getTAG(), "onEvent end " + event);
                    return;
                }
            }
            String tag = MonitorPresenter.this.getTAG();
            StringBuilder sb = new StringBuilder();
            sb.append("suggestion=");
            suggestion2 = MonitorPresenter.this.suggestion;
            sb.append(suggestion2);
            sb.append(". no need process");
            Pdlog.m3277w(tag, sb.toString());
        }
    };
    private final HashMap<String, TimesChecker> timesCheckers = new HashMap<>();
    private final CopyOnWriteArrayList<JobInfo> autoResumeJobs = new CopyOnWriteArrayList<>();
    private MonitorContract.Suggestion suggestion = MonitorContract.Suggestion.NO_ERROR_LEFT;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Process.values().length];

        static {
            $EnumSwitchMapping$0[Process.REBOOT_POWER.ordinal()] = 1;
            $EnumSwitchMapping$0[Process.RESTART_SOFTWARE.ordinal()] = 2;
            $EnumSwitchMapping$0[Process.TRY.ordinal()] = 3;
            $EnumSwitchMapping$0[Process.AUTO_RESUME.ordinal()] = 4;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void replaceView(MonitorContract.ViewInterface view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.replaceView((MonitorPresenter) view);
        CoreDevices.INSTANCE.getMonitorTask().addListener(this.monitorListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewRemoved() {
        super.onViewRemoved();
        CoreDevices.INSTANCE.getMonitorTask().removeListener(this.monitorListener);
    }

    @Override // com.pudutech.bumblebee.presenter.monitor_task.MonitorContract.PresenterInterface
    public void tryResume() {
        Pdlog.m3273d(getTAG(), "tryResume");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.monitor_task.MonitorPresenter$tryResume$1
            /* JADX INFO: Access modifiers changed from: package-private */
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
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                MonitorPresenter.this.processIt();
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.monitor_task.MonitorContract.PresenterInterface
    public void cancelThisTime() {
        Pdlog.m3273d(getTAG(), "cancelThisTime");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.monitor_task.MonitorPresenter$cancelThisTime$1
            /* JADX INFO: Access modifiers changed from: package-private */
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
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                MonitorPresenter.this.cleanAll("user cancel");
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.monitor_task.MonitorContract.PresenterInterface
    public CopyOnWriteArrayList<Error> getAll() {
        return this.all;
    }

    @Override // com.pudutech.bumblebee.presenter.monitor_task.MonitorContract.PresenterInterface
    public void setAll(CopyOnWriteArrayList<Error> copyOnWriteArrayList) {
        Intrinsics.checkParameterIsNotNull(copyOnWriteArrayList, "<set-?>");
        this.all = copyOnWriteArrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showOnUI(final MonitorContract.Suggestion s, ArrayList<Error> list) {
        final ArrayList arrayList = (ArrayList) getCloner().deepClone(list);
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.monitor_task.MonitorPresenter$showOnUI$1
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
                MonitorContract.ViewInterface theView;
                theView = MonitorPresenter.this.getTheView();
                if (theView != null) {
                    MonitorContract.Suggestion suggestion = s;
                    ArrayList<Error> replicate = arrayList;
                    Intrinsics.checkExpressionValueIsNotNull(replicate, "replicate");
                    theView.showSuggestion(suggestion, replicate);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void collectError(final Errors errors) {
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.monitor_task.MonitorPresenter$collectError$1
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
                MonitorContract.ViewInterface theView;
                theView = MonitorPresenter.this.getTheView();
                if (theView != null) {
                    theView.collectError(errors);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cleanAll(String process) {
        Pdlog.m3273d(getTAG(), "cleanAll process=" + process + ", now=" + this.suggestion + ' ' + this.rebootPower.size() + ' ' + this.restartSoftware.size() + ' ' + this.userTry.size() + ' ' + this.wait.size());
        this.suggestion = MonitorContract.Suggestion.NO_ERROR_LEFT;
        Iterator<T> it = this.autoResumeJobs.iterator();
        while (it.hasNext()) {
            Job.DefaultImpls.cancel$default(((JobInfo) it.next()).getJob(), (CancellationException) null, 1, (Object) null);
        }
        this.autoResumeJobs.clear();
        for (Error it2 : this.rebootPower) {
            ReportErrorProcess reportErrorProcess = ReportErrorProcess.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            reportErrorProcess.toCloud(it2, process);
        }
        this.rebootPower.clear();
        for (Error it3 : this.restartSoftware) {
            ReportErrorProcess reportErrorProcess2 = ReportErrorProcess.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(it3, "it");
            reportErrorProcess2.toCloud(it3, process);
        }
        this.restartSoftware.clear();
        for (Error it4 : this.userTry) {
            ReportErrorProcess reportErrorProcess3 = ReportErrorProcess.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(it4, "it");
            reportErrorProcess3.toCloud(it4, process);
        }
        this.userTry.clear();
        for (Error it5 : this.wait) {
            ReportErrorProcess reportErrorProcess4 = ReportErrorProcess.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(it5, "it");
            reportErrorProcess4.toCloud(it5, process);
        }
        this.wait.clear();
        getAll().clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void classify(List<? extends Error> needProcess) {
        Pdlog.m3273d(getTAG(), "classify size=" + needProcess.size() + ' ' + this.suggestion);
        for (Error error : needProcess) {
            Classify classify = ClassifyDefineKt.getErrorProcessMap().get(ClassifyDefineKt.toProcessKey(error));
            if (classify != null) {
                int i = WhenMappings.$EnumSwitchMapping$0[classify.getProcess().ordinal()];
                if (i == 1) {
                    this.rebootPower.add(error);
                } else if (i == 2) {
                    this.restartSoftware.add(error);
                } else if (i == 3) {
                    this.userTry.add(error);
                } else {
                    if (i != 4) {
                        throw new NoWhenBranchMatchedException();
                    }
                    this.wait.add(error);
                }
            }
            if (classify == null) {
                CopyOnWriteArrayList<Error> copyOnWriteArrayList = this.restartSoftware;
                Error error2 = new Error();
                error2.error_type = "NoDefine";
                error2.level = MoveError.LEVEL_ERROR;
                error2.detail = new Gson().toJson(error);
                ReportErrorProcess.INSTANCE.toCloud(error, "classify fail. no define");
                Pdlog.m3274e(getTAG(), "no define. please check define and mirsdk. " + error2.detail);
                Boolean.valueOf(copyOnWriteArrayList.add(error2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MonitorPresenter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/monitor_task/MonitorPresenter$TimesChecker;", "", "()V", "happenTimes", "", "lastTimestamp", "", "checkTooManyTimesInShortTime", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class TimesChecker {
        private int happenTimes;
        private long lastTimestamp = SystemClock.elapsedRealtime();

        public final boolean checkTooManyTimesInShortTime() {
            Pdlog.m3273d("MonitorPresenter", "happen again in " + (SystemClock.elapsedRealtime() - this.lastTimestamp) + ". cnt=" + this.happenTimes);
            if (SystemClock.elapsedRealtime() - this.lastTimestamp > 20000) {
                this.happenTimes = 0;
            }
            this.happenTimes++;
            this.lastTimestamp = SystemClock.elapsedRealtime();
            return this.happenTimes > 3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MonitorPresenter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/monitor_task/MonitorPresenter$JobInfo;", "", "name", "", "job", "Lkotlinx/coroutines/Job;", "(Ljava/lang/String;Lkotlinx/coroutines/Job;)V", "getJob", "()Lkotlinx/coroutines/Job;", "getName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final /* data */ class JobInfo {
        private final Job job;
        private final String name;

        public static /* synthetic */ JobInfo copy$default(JobInfo jobInfo, String str, Job job, int i, Object obj) {
            if ((i & 1) != 0) {
                str = jobInfo.name;
            }
            if ((i & 2) != 0) {
                job = jobInfo.job;
            }
            return jobInfo.copy(str, job);
        }

        /* renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* renamed from: component2, reason: from getter */
        public final Job getJob() {
            return this.job;
        }

        public final JobInfo copy(String name, Job job) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(job, "job");
            return new JobInfo(name, job);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof JobInfo)) {
                return false;
            }
            JobInfo jobInfo = (JobInfo) other;
            return Intrinsics.areEqual(this.name, jobInfo.name) && Intrinsics.areEqual(this.job, jobInfo.job);
        }

        public int hashCode() {
            String str = this.name;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            Job job = this.job;
            return hashCode + (job != null ? job.hashCode() : 0);
        }

        public String toString() {
            return "JobInfo(name=" + this.name + ", job=" + this.job + ")";
        }

        public JobInfo(String name, Job job) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(job, "job");
            this.name = name;
            this.job = job;
        }

        public final Job getJob() {
            return this.job;
        }

        public final String getName() {
            return this.name;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processIt() {
        Pdlog.m3275i(getTAG(), "processIt. now=" + this.suggestion + ' ' + this.rebootPower.size() + ' ' + this.restartSoftware.size() + ' ' + this.userTry.size() + ' ' + this.wait.size());
        if (!this.rebootPower.isEmpty()) {
            this.suggestion = MonitorContract.Suggestion.REBOOT;
            Iterator<T> it = this.autoResumeJobs.iterator();
            while (it.hasNext()) {
                Job.DefaultImpls.cancel$default(((JobInfo) it.next()).getJob(), (CancellationException) null, 1, (Object) null);
            }
            showOnUI(MonitorContract.Suggestion.REBOOT, new ArrayList<>(this.rebootPower));
            return;
        }
        if (!this.restartSoftware.isEmpty()) {
            this.suggestion = MonitorContract.Suggestion.RESTART;
            Iterator<T> it2 = this.autoResumeJobs.iterator();
            while (it2.hasNext()) {
                Job.DefaultImpls.cancel$default(((JobInfo) it2.next()).getJob(), (CancellationException) null, 1, (Object) null);
            }
            showOnUI(MonitorContract.Suggestion.RESTART, new ArrayList<>(this.restartSoftware));
            return;
        }
        if (!this.userTry.isEmpty()) {
            this.suggestion = MonitorContract.Suggestion.USER_TRY;
            showOnUI(MonitorContract.Suggestion.USER_TRY, new ArrayList<>(this.userTry));
        } else {
            if (!this.wait.isEmpty()) {
                this.suggestion = MonitorContract.Suggestion.WAIT;
                showOnUI(MonitorContract.Suggestion.WAIT, new ArrayList<>(this.wait));
                return;
            }
            Pdlog.m3275i(getTAG(), "no more error");
            showOnUI(MonitorContract.Suggestion.NO_ERROR_LEFT, new ArrayList<>());
            BaseTaskInterface movementTask = Behavior.INSTANCE.getMovementTask();
            if (movementTask != null) {
                movementTask.setActive(true);
            }
            Peripherals.INSTANCE.getFunctionButton().setMute(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processAutoResumeBackground(Error it) {
        String str = it.error_type;
        if (str != null) {
            int hashCode = str.hashCode();
            if (hashCode != -1854713221) {
                if (hashCode == -1666029548 && str.equals("LostLidar")) {
                    String str2 = it.error_type;
                    Intrinsics.checkExpressionValueIsNotNull(str2, "it.error_type");
                    doJob("rebootLidar", str2, new MonitorPresenter$processAutoResumeBackground$1(null));
                    return;
                }
            } else if (str.equals("LostRGBD")) {
                String str3 = it.error_type;
                Intrinsics.checkExpressionValueIsNotNull(str3, "it.error_type");
                doJob("rebootRGBD", str3, new MonitorPresenter$processAutoResumeBackground$2(null));
                return;
            }
        }
        Pdlog.m3274e(getTAG(), "no define " + it);
    }

    private final void doJob(String key, final String errorType, Function1<? super Continuation<? super Boolean>, ? extends Object> method) {
        Job launch$default;
        Pdlog.m3275i(getTAG(), "doJob " + key);
        if (checkJobAlreadyExist(key)) {
            return;
        }
        if (!this.timesCheckers.containsKey(key)) {
            this.timesCheckers.put(key, new TimesChecker());
        }
        TimesChecker timesChecker = this.timesCheckers.get(key);
        if (timesChecker == null) {
            Intrinsics.throwNpe();
        }
        if (!timesChecker.checkTooManyTimesInShortTime()) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new MonitorPresenter$doJob$job$1(this, method, key, errorType, null), 3, null);
            this.autoResumeJobs.add(new JobInfo(key, launch$default));
            return;
        }
        Pdlog.m3277w(getTAG(), "try " + key + " too many times in short time. no more retry but reboot");
        CopyOnWriteArrayList<Error> copyOnWriteArrayList = this.wait;
        ArrayList<Error> arrayList = new ArrayList();
        for (Object obj : copyOnWriteArrayList) {
            if (Intrinsics.areEqual(((Error) obj).error_type, errorType)) {
                arrayList.add(obj);
            }
        }
        for (Error it : arrayList) {
            ReportErrorProcess reportErrorProcess = ReportErrorProcess.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            reportErrorProcess.toCloud(it, "too many times in short time. no more retry");
        }
        copyOnWriteArrayList.removeIf(new Predicate<Error>() { // from class: com.pudutech.bumblebee.presenter.monitor_task.MonitorPresenter$doJob$$inlined$apply$lambda$1
            @Override // java.util.function.Predicate
            public final boolean test(Error error) {
                return Intrinsics.areEqual(error.error_type, errorType);
            }
        });
        this.rebootPower.add(newErrorRebootFail(errorType));
        checkAgainAfterAutoResume();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkAgainAfterAutoResume() {
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.monitor_task.MonitorPresenter$checkAgainAfterAutoResume$1
            /* JADX INFO: Access modifiers changed from: package-private */
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
                MonitorContract.Suggestion suggestion;
                MonitorContract.Suggestion suggestion2;
                CopyOnWriteArrayList copyOnWriteArrayList;
                CopyOnWriteArrayList copyOnWriteArrayList2;
                CopyOnWriteArrayList copyOnWriteArrayList3;
                String tag = MonitorPresenter.this.getTAG();
                StringBuilder sb = new StringBuilder();
                sb.append("checkAgainAfterAutoResume suggestion=");
                suggestion = MonitorPresenter.this.suggestion;
                sb.append(suggestion);
                Pdlog.m3273d(tag, sb.toString());
                suggestion2 = MonitorPresenter.this.suggestion;
                if (suggestion2 != MonitorContract.Suggestion.WAIT) {
                    return;
                }
                copyOnWriteArrayList = MonitorPresenter.this.wait;
                if (!copyOnWriteArrayList.isEmpty()) {
                    copyOnWriteArrayList2 = MonitorPresenter.this.rebootPower;
                    if (!(!copyOnWriteArrayList2.isEmpty())) {
                        copyOnWriteArrayList3 = MonitorPresenter.this.restartSoftware;
                        if (!(!copyOnWriteArrayList3.isEmpty())) {
                            return;
                        }
                    }
                }
                MonitorPresenter.this.processIt();
            }
        });
    }

    private final boolean checkJobAlreadyExist(String key) {
        CopyOnWriteArrayList<JobInfo> copyOnWriteArrayList = this.autoResumeJobs;
        ArrayList arrayList = new ArrayList();
        for (Object obj : copyOnWriteArrayList) {
            if (Intrinsics.areEqual(((JobInfo) obj).getName(), key)) {
                arrayList.add(obj);
            }
        }
        if (!(!arrayList.isEmpty())) {
            return false;
        }
        Pdlog.m3276v(getTAG(), key + " is already working");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Error newErrorRebootFail(String string) {
        Error error = new Error();
        error.error_type = string;
        error.level = MoveError.LEVEL_ERROR;
        error.detail = "reboot fail";
        return error;
    }
}
