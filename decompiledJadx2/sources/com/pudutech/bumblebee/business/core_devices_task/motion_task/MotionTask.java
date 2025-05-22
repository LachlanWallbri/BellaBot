package com.pudutech.bumblebee.business.core_devices_task.motion_task;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.TimerThread;
import com.pudutech.bumblebee.business.base.BaseMultiListenerImpl;
import com.pudutech.bumblebee.business.core_devices_task.motion_task.MotionTask;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.SpeedListener;
import com.pudutech.bumblebee.business.utils.MoveModel;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* compiled from: MotionTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032:\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0004j\u0002`\f:\u0001&B\u0005¢\u0006\u0002\u0010\rJ\b\u0010\u001e\u001a\u00020\u000bH\u0002J\u001d\u0010\u001f\u001a\u00020\u000b2\b\u0010 \u001a\u0004\u0018\u00010\u00052\b\u0010!\u001a\u0004\u0018\u00010\tH\u0096\u0002J\u0010\u0010\"\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020\u001dH\u0002J\u0018\u0010$\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020%2\u0006\u0010\n\u001a\u00020%H\u0016R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0012j\b\u0012\u0004\u0012\u00020\u0013`\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/core_devices_task/motion_task/MotionTask;", "Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "Lcom/pudutech/bumblebee/business/core_devices_task/motion_task/MotionListener;", "Lcom/pudutech/bumblebee/business/robotsdk/sdk_listeners/SpeedListener;", "Lkotlin/Function2;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "Lkotlin/ParameterName;", "name", "p0", "", "p1", "", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotStateListener;", "()V", "SPEED_PERIOD_MS", "", "TAG", "history", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/business/core_devices_task/motion_task/MotionTask$Record;", "Lkotlin/collections/ArrayList;", "isMoving", "", "isNormalAutoMoving", "moveModel", "Lcom/pudutech/bumblebee/business/utils/MoveModel;", "speedTimestamp_ms", "", "stuckState", "Lcom/pudutech/bumblebee/business/core_devices_task/motion_task/MotionEvent;", "FSM", "invoke", "state", "description", "notificationStuckState", "now", "onSpeed", "", "Record", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MotionTask extends BaseMultiListenerImpl<MotionListener> implements SpeedListener, Function2<RobotState, String, Unit> {
    private final int SPEED_PERIOD_MS;
    private final String TAG = "MotionTask";
    private final ArrayList<Record> history = new ArrayList<>();
    private boolean isMoving;
    private boolean isNormalAutoMoving;
    private MoveModel moveModel;
    private long speedTimestamp_ms;
    private MotionEvent stuckState;

    /* compiled from: MotionTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.bumblebee.business.core_devices_task.motion_task.MotionTask$1 */
    /* loaded from: classes4.dex */
    static final /* synthetic */ class C40071 extends FunctionReference implements Function0<Unit> {
        C40071(MotionTask motionTask) {
            super(0, motionTask);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "FSM";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(MotionTask.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "FSM()V";
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            ((MotionTask) this.receiver).FSM();
        }
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(RobotState robotState, String str) {
        invoke2(robotState, str);
        return Unit.INSTANCE;
    }

    public MotionTask() {
        TimerThread.INSTANCE.loop(new C40071(this), 100L);
        this.stuckState = MotionEvent.NO_OBSTRUCTED;
        this.SPEED_PERIOD_MS = 80;
        this.moveModel = new MoveModel();
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public void invoke2(final RobotState state, String description) {
        boolean z = true;
        this.isNormalAutoMoving = state == RobotState.Moving;
        if (state != RobotState.Moving && state != RobotState.Stuck && state != RobotState.Approaching && state != RobotState.Resume) {
            z = false;
        }
        this.isMoving = z;
        TimerThread.INSTANCE.post(new Function0<Unit>() { // from class: com.pudutech.bumblebee.business.core_devices_task.motion_task.MotionTask$invoke$1
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
                ArrayList arrayList;
                RobotState robotState = state;
                if (robotState != null) {
                    arrayList = MotionTask.this.history;
                    arrayList.add(new MotionTask.Record(robotState, SystemClock.elapsedRealtime()));
                }
                MotionTask.this.FSM();
            }
        });
    }

    private final void notificationStuckState(final MotionEvent now) {
        if (this.stuckState != now) {
            this.stuckState = now;
            getListeners().forEach(new Function1<MotionListener, Unit>() { // from class: com.pudutech.bumblebee.business.core_devices_task.motion_task.MotionTask$notificationStuckState$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(MotionListener motionListener) {
                    invoke2(motionListener);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(MotionListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.onMotion(MotionEvent.this);
                }
            });
        }
    }

    @Override // com.pudutech.bumblebee.business.robotsdk.sdk_listeners.SpeedListener
    public void onSpeed(double p0, double p1) {
        if (SystemClock.elapsedRealtime() - this.speedTimestamp_ms < this.SPEED_PERIOD_MS) {
            return;
        }
        this.speedTimestamp_ms = SystemClock.elapsedRealtime();
        MoveModel.State updateFSM = this.moveModel.updateFSM(p0, p1);
        if (this.isNormalAutoMoving) {
            if (updateFSM == MoveModel.State.TURN_LEFT) {
                getListeners().forEach(new Function1<MotionListener, Unit>() { // from class: com.pudutech.bumblebee.business.core_devices_task.motion_task.MotionTask$onSpeed$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(MotionListener motionListener) {
                        invoke2(motionListener);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(MotionListener it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        it.onMotion(MotionEvent.TURN_LEFT);
                    }
                });
                return;
            }
            if (updateFSM == MoveModel.State.TURN_RIGHT) {
                getListeners().forEach(new Function1<MotionListener, Unit>() { // from class: com.pudutech.bumblebee.business.core_devices_task.motion_task.MotionTask$onSpeed$2
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(MotionListener motionListener) {
                        invoke2(motionListener);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(MotionListener it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        it.onMotion(MotionEvent.TURN_RIGHT);
                    }
                });
            } else if (updateFSM == MoveModel.State.BRAKE) {
                getListeners().forEach(new Function1<MotionListener, Unit>() { // from class: com.pudutech.bumblebee.business.core_devices_task.motion_task.MotionTask$onSpeed$3
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(MotionListener motionListener) {
                        invoke2(motionListener);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(MotionListener it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        it.onMotion(MotionEvent.BRAKE);
                    }
                });
            } else {
                getListeners().forEach(new Function1<MotionListener, Unit>() { // from class: com.pudutech.bumblebee.business.core_devices_task.motion_task.MotionTask$onSpeed$4
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(MotionListener motionListener) {
                        invoke2(motionListener);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(MotionListener it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        it.onMotion(MotionEvent.MOVE_FORWARD);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MotionTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/core_devices_task/motion_task/MotionTask$Record;", "", "state", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "timestamp", "", "(Lcom/pudutech/mirsdk/aidl/serialize/RobotState;J)V", "getState", "()Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "setState", "(Lcom/pudutech/mirsdk/aidl/serialize/RobotState;)V", "getTimestamp", "()J", "setTimestamp", "(J)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final /* data */ class Record {
        private RobotState state;
        private long timestamp;

        public static /* synthetic */ Record copy$default(Record record, RobotState robotState, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                robotState = record.state;
            }
            if ((i & 2) != 0) {
                j = record.timestamp;
            }
            return record.copy(robotState, j);
        }

        /* renamed from: component1, reason: from getter */
        public final RobotState getState() {
            return this.state;
        }

        /* renamed from: component2, reason: from getter */
        public final long getTimestamp() {
            return this.timestamp;
        }

        public final Record copy(RobotState state, long timestamp) {
            Intrinsics.checkParameterIsNotNull(state, "state");
            return new Record(state, timestamp);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Record)) {
                return false;
            }
            Record record = (Record) other;
            return Intrinsics.areEqual(this.state, record.state) && this.timestamp == record.timestamp;
        }

        public int hashCode() {
            RobotState robotState = this.state;
            int hashCode = robotState != null ? robotState.hashCode() : 0;
            long j = this.timestamp;
            return (hashCode * 31) + ((int) (j ^ (j >>> 32)));
        }

        public String toString() {
            return "Record(state=" + this.state + ", timestamp=" + this.timestamp + ")";
        }

        public Record(RobotState state, long j) {
            Intrinsics.checkParameterIsNotNull(state, "state");
            this.state = state;
            this.timestamp = j;
        }

        public final RobotState getState() {
            return this.state;
        }

        public final long getTimestamp() {
            return this.timestamp;
        }

        public final void setState(RobotState robotState) {
            Intrinsics.checkParameterIsNotNull(robotState, "<set-?>");
            this.state = robotState;
        }

        public final void setTimestamp(long j) {
            this.timestamp = j;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void FSM() {
        long j;
        if (this.history.size() >= 1 && this.isMoving) {
            int size = this.history.size() - 2;
            long j2 = 0;
            if (size >= 0) {
                long j3 = 0;
                j = 0;
                int i = 0;
                while (true) {
                    int i2 = i + 1;
                    long timestamp = this.history.get(i2).getTimestamp() - this.history.get(i).getTimestamp();
                    if (this.history.get(i).getState() == RobotState.Stuck) {
                        j += timestamp;
                    } else {
                        j3 += timestamp;
                    }
                    if (i == size) {
                        break;
                    } else {
                        i = i2;
                    }
                }
                j2 = j3;
            } else {
                j = 0;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime() - ((Record) CollectionsKt.last((List) this.history)).getTimestamp();
            if (((Record) CollectionsKt.last((List) this.history)).getState() == RobotState.Stuck) {
                j += elapsedRealtime;
            } else {
                j2 += elapsedRealtime;
            }
            Pdlog.m3273d(this.TAG, "obstructedTime=" + j + "   noObstructedTime=" + j2 + "  total=" + (SystemClock.elapsedRealtime() - ((Record) CollectionsKt.first((List) this.history)).getTimestamp()));
            String str = this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("first=");
            sb.append((Record) CollectionsKt.first((List) this.history));
            sb.append("  end=");
            sb.append((Record) CollectionsKt.last((List) this.history));
            Pdlog.m3273d(str, sb.toString());
            if (j > 2000) {
                notificationStuckState(MotionEvent.OBSTRUCTED);
            } else {
                notificationStuckState(MotionEvent.NO_OBSTRUCTED);
            }
            final long elapsedRealtime2 = SystemClock.elapsedRealtime() - 5000;
            int size2 = this.history.size();
            int i3 = 1;
            while (true) {
                if (i3 >= size2) {
                    break;
                }
                int i4 = i3 - 1;
                if (this.history.get(i4).getTimestamp() < elapsedRealtime2 && this.history.get(i3).getTimestamp() > elapsedRealtime2) {
                    this.history.get(i4).setTimestamp(elapsedRealtime2);
                    Pdlog.m3273d(this.TAG, "remove history. size=" + this.history.size() + " index=" + i3 + ' ');
                    Pdlog.m3273d(this.TAG, "i-1=" + this.history.get(i4) + "   i=" + this.history.get(i3));
                    break;
                }
                i3++;
            }
            this.history.removeIf(new Predicate<Record>() { // from class: com.pudutech.bumblebee.business.core_devices_task.motion_task.MotionTask$FSM$1
                @Override // java.util.function.Predicate
                public final boolean test(MotionTask.Record it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return it.getTimestamp() < elapsedRealtime2;
                }
            });
        }
    }
}
