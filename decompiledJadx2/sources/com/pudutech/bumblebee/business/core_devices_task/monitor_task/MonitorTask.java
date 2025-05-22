package com.pudutech.bumblebee.business.core_devices_task.monitor_task;

import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseMultiListenerImpl;
import com.pudutech.bumblebee.business.core_devices_task.monitor_task.protocol.Error;
import com.pudutech.bumblebee.business.core_devices_task.monitor_task.protocol.Errors;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import java.util.ArrayList;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: MonitorTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012:\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0003j\u0002`\u000bB\u0005¢\u0006\u0002\u0010\fJ\u001d\u0010\u0018\u001a\u00020\n2\b\u0010\u0019\u001a\u0004\u0018\u00010\u00042\b\u0010\u001a\u001a\u0004\u0018\u00010\bH\u0096\u0002J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\bH\u0002J\u0018\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u0013H\u0002R\u000e\u0010\r\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006 "}, m3961d2 = {"Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/MonitorTask;", "Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/MonitorListener;", "Lkotlin/Function2;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "Lkotlin/ParameterName;", "name", "p0", "", "p1", "", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotStateListener;", "()V", "TAG", "gson", "Lcom/google/gson/Gson;", "isError", "", "monitorEvent", "Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/MonitorEvent;", "getMonitorEvent", "()Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/MonitorEvent;", "setMonitorEvent", "(Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/MonitorEvent;)V", "invoke", "state", "description", "parse", "Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/protocol/Errors;", "string", "parseAndPost", "event", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MonitorTask extends BaseMultiListenerImpl<MonitorListener> implements Function2<RobotState, String, Unit> {
    private boolean isError;
    private final String TAG = "MonitorTask";
    private final Gson gson = new Gson();
    private MonitorEvent monitorEvent = new MonitorEvent(false, (Errors) null);

    public MonitorTask() {
        Errors errors = new Errors();
        Error error = new Error();
        error.error_type = "123";
        error.level = "Waring";
        error.detail = "456";
        errors.list.add(error);
        Pdlog.m3273d(this.TAG, "format type=" + this.gson.toJson(errors));
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(RobotState robotState, String str) {
        invoke2(robotState, str);
        return Unit.INSTANCE;
    }

    public final MonitorEvent getMonitorEvent() {
        return this.monitorEvent;
    }

    public final void setMonitorEvent(MonitorEvent monitorEvent) {
        Intrinsics.checkParameterIsNotNull(monitorEvent, "<set-?>");
        this.monitorEvent = monitorEvent;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public void invoke2(RobotState state, String description) {
        boolean z = true;
        Pdlog.m3276v(this.TAG, "运动状态 = " + state + "  描述 = " + description);
        if (state == null) {
            return;
        }
        final MonitorEvent monitorEvent = new MonitorEvent(false, (Errors) null);
        if (state == RobotState.Error) {
            monitorEvent.setError(true);
            this.isError = true;
            Pdlog.m3273d(this.TAG, "error event: " + description);
            String str = description;
            if (str != null && str.length() != 0) {
                z = false;
            }
            if (z) {
                this.monitorEvent = monitorEvent;
                getListeners().forEach(new Function1<MonitorListener, Unit>() { // from class: com.pudutech.bumblebee.business.core_devices_task.monitor_task.MonitorTask$invoke$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(MonitorListener monitorListener) {
                        invoke2(monitorListener);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(MonitorListener it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        it.onEvent(MonitorEvent.this);
                    }
                });
                return;
            } else {
                parseAndPost(StringsKt.replace$default(description, "\n", "", false, 4, (Object) null), monitorEvent);
                return;
            }
        }
        if (this.isError) {
            monitorEvent.setError(false);
            this.isError = false;
            this.monitorEvent = monitorEvent;
            getListeners().forEach(new Function1<MonitorListener, Unit>() { // from class: com.pudutech.bumblebee.business.core_devices_task.monitor_task.MonitorTask$invoke$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(MonitorListener monitorListener) {
                    invoke2(monitorListener);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(MonitorListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.onEvent(MonitorEvent.this);
                }
            });
        }
        Pdlog.m3273d(this.TAG, "no Error = " + this.isError);
    }

    private final void parseAndPost(String string, final MonitorEvent event) {
        ArrayList<Error> arrayList;
        ArrayList<Error> arrayList2;
        Errors errors = (Errors) null;
        try {
            if (string.charAt(0) == '{') {
                errors = parse("{\"list\":[" + string + "]}");
                if (errors != null && (arrayList2 = errors.list) != null) {
                    arrayList2.removeIf(new Predicate<Error>() { // from class: com.pudutech.bumblebee.business.core_devices_task.monitor_task.MonitorTask$parseAndPost$1
                        @Override // java.util.function.Predicate
                        public final boolean test(Error error) {
                            return error == null;
                        }
                    });
                }
            } else if (string.charAt(0) == '[') {
                errors = parse("{\"list\":" + string + '}');
                if (errors != null && (arrayList = errors.list) != null) {
                    arrayList.removeIf(new Predicate<Error>() { // from class: com.pudutech.bumblebee.business.core_devices_task.monitor_task.MonitorTask$parseAndPost$2
                        @Override // java.util.function.Predicate
                        public final boolean test(Error error) {
                            return error == null;
                        }
                    });
                }
            } else {
                Pdlog.m3274e(this.TAG, "parse fail.wrong description string=" + string);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Pdlog.m3274e(this.TAG, "wrong description string=" + string);
        }
        event.setError(true);
        event.setErrors(errors);
        this.monitorEvent = event;
        getListeners().forEach(new Function1<MonitorListener, Unit>() { // from class: com.pudutech.bumblebee.business.core_devices_task.monitor_task.MonitorTask$parseAndPost$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MonitorListener monitorListener) {
                invoke2(monitorListener);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MonitorListener it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.onEvent(MonitorEvent.this);
            }
        });
        Pdlog.m3273d(this.TAG, "错误结果：" + event);
    }

    private final Errors parse(String string) {
        Pdlog.m3273d(this.TAG, "parse " + string);
        return (Errors) this.gson.fromJson(string, Errors.class);
    }
}
